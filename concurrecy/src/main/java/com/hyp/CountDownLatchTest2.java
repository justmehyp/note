package com.hyp;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CountDownLatchTest2 {
    public static void main(String[] args) throws InterruptedException {
        int count = 10;

        final CountDownLatch countDownLatch = new CountDownLatch(count);
        IntStream.range(0, count)
                .forEach((i) -> {
                    new Thread(() -> {
                        try {
                            System.out.println(Thread.currentThread().getName() + " - before await: " + new Date());
                            countDownLatch.await();
                            System.out.println(Thread.currentThread().getName() + " - after await: " + new Date());
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                });

        for (int i = 0; i < count; i++) {
            System.out.println(count - i);
            Thread.sleep(1000);
            countDownLatch.countDown();
        }
    }
}
