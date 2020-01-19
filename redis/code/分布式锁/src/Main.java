import redis.clients.jedis.Jedis;

public class Main {
    public static void main(String[] args) throws Exception {

        //redis 初步实现分布式锁，锁的持有时间是5秒
//        Thread thread1 = new Thread(new Locker());
//        Thread thread2 = new Thread(new Locker());
//        Thread thread3 = new Thread(new Locker());
//        Thread thread4 = new Thread(new Locker());
//        Thread thread5 = new Thread(new Locker());
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
//
//        while (true) {
//
//        }




        //redis 实现分布式锁，获取了锁，只要进程还活着，就不会失效，除非 手动unlock
        class LockTest extends Thread {
            @Override
            public void run() {
                while (true) {
                    Jedis jedis = new Jedis();
                    String resource = "hello";

                    RedisReentrantDistributeLock lock = new RedisReentrantDistributeLock(jedis, resource);

                    boolean locked = lock.tryLock();
                    System.out.println(this.getId() + " locked: " + locked);
                    try {
                        Thread.sleep(11000); // 执行长时间的任务
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (locked) {
                            lock.unlock();
                        }
                    }
                }
            }
        }

        LockTest lockTest1 = new LockTest();
        LockTest lockTest2 = new LockTest();
        LockTest lockTest3 = new LockTest();

        lockTest1.start();
        lockTest2.start();
        lockTest3.start();

        while (true) {
        }




//        ReentrantLock reentrantLock = new ReentrantLock();
//        System.out.println(reentrantLock.tryLock());
//        System.out.println(reentrantLock.tryLock());
//        reentrantLock.unlock();
//        reentrantLock.unlock();
//        reentrantLock.unlock();
//
//
//        ReentrantLock reentrantLock = new ReentrantLock();
//        System.out.println(reentrantLock.tryLock());
//        new Thread() {
//            @Override
//            public void run() {
//                System.out.println("locked: " + reentrantLock.tryLock());
//            }
//        }.start();

    }
}


class Locker implements Runnable {

    public Locker() {
    }

    @Override
    public void run() {

        while (true) {
            Jedis jedis = new Jedis();
            RedisReentrantDistributeLock lock = new RedisReentrantDistributeLock(jedis, "hello");

            boolean locked = lock.tryLock();
            if (locked) {
                System.out.println(Thread.currentThread().getId() + " locked");
            } else {
                System.out.println(Thread.currentThread().getId() + " lock fail");
            }

            try {
                Thread.sleep(5500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}