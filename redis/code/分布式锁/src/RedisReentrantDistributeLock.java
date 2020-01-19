import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Timer;
import java.util.TimerTask;

public class RedisReentrantDistributeLock {
    private Jedis redis;
    private String resource;
    private ThreadLocal<Integer> lockCount = new ThreadLocal<Integer>();
    private String monitor;

    private Timer timer;

    private static final String MONITOR_PREFIX = "lock:";
    private static final int DEFAULT_EXPIRATION_SECONDS = 5;

    public RedisReentrantDistributeLock(Jedis redis, String resource) {
        this.redis = redis;
        this.resource = resource;
        this.monitor = MONITOR_PREFIX + resource;
    }

    /**
     * lock
     * @return true if lock success, false if lock fail
     */
    public boolean tryLock() {
        if (locked() || doLock()) {
            incrCount();
            return true;
        } else {
            return false;
        }
    }

    private boolean locked() {
        return null != lockCount.get();
    }

    private boolean doLock() {
        SetParams params = SetParams.setParams().ex(DEFAULT_EXPIRATION_SECONDS).nx();
        boolean locked = "OK".equals(redis.set(monitor, "", params));

        if (locked) {
            startRemainLock();
            return true;
        } else {
            return false;
        }
    }

    private void startRemainLock() {
        timer = new Timer();
        long fixRate = (DEFAULT_EXPIRATION_SECONDS - 1) * 1000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                remainLock();
            }
        }, fixRate, fixRate);
    }

    private void remainLock() {
        SetParams params = SetParams.setParams().ex(DEFAULT_EXPIRATION_SECONDS).xx();
        redis.set(monitor, "", params);
    }

    private void incrCount() {
        Integer lockCount = this.lockCount.get();
        if (null == lockCount) {
            this.lockCount.set(1);
        } else {
            this.lockCount.set(lockCount + 1);
        }
    }

    /**
     * unlock
     * @throws IllegalMonitorStateException if the current thread does not hold this lock
     */
    public void unlock() {
        if (!locked()) {
            throw new IllegalMonitorStateException("未获得锁");
        } else {
            doUnLock();
        }
    }

    private void doUnLock() {
        Integer lockCount = this.lockCount.get();
        if (1 == lockCount) {
            remainLock(); //防止当前剩余的有效时间太小，下面的 stopRemainLock() 执行完了，别的进程去上了锁，被下面的 redis.del(monitor) 给干掉了
            stopRemainLock();
            redis.del(monitor);
            this.lockCount.remove();
        } else {
            this.lockCount.set(lockCount - 1);
        }
    }

    private void stopRemainLock() {
        timer.cancel();
    }
}
