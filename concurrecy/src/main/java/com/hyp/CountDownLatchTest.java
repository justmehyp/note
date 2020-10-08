package com.hyp;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(5);

        IntStream.range(0, 10)
                .forEach((i) -> {
                    new Thread(() -> {
                        if (i % 2 == 0) {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName() + " - countdown");
                            countDownLatch.countDown();
                        }
                        else {
                            try {
                                System.out.println(Thread.currentThread().getName() + " before await: " + new Date());
                                countDownLatch.await();
                                System.out.println(Thread.currentThread().getName() + " after await: " + new Date());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                });

        System.out.println(Thread.currentThread().getName() + " before await: " + new Date());
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " after await: " + new Date());
    }
}
