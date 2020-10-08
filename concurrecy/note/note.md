# synchronized 和 Lock 的异同
- monitor只有一个wait-set，而通过Lock.newCondition()可以获得多个Condition对象，可以实现一个Lock对应多个wait-set
  这可以解决传统方式对Object.notify()方法使用不当可能导致死锁的问题。


#
- 锁 轻量级锁 重量级锁 synchronized Lock 
- 线程间通信 CountDownLatch CyclicBarrier Phaser Object.wait Condition.await
- AQS
- CAS
- 线程池 Executor ExecutorService ThreadPool ThreadPoolExecutor Executors ForkJoinPool CompletionService
- Future RunnableFuture FutureTask CompletableFuture CompletionStage
- ThreadLocal ThreadLocalRandom 
- JUC

无锁 - 偏向锁 - 轻量级锁（自旋） - 重量级锁