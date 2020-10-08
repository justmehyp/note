package com.hyp;

// 据说notify这样用可能导致死锁，原因是: 调用notify的线程也是minuser,之后刚好j<=0进入wait set。
// 当前线程获得monitor后，发现 j <= 0，又再次进入wait set
// 然而并没有notify其他线程，于是所有在wait set中等待这个monitor的线程将永远等待

public class WaitNotifyTest {
    public volatile int j = 0;

    public static void main(String[] args) {

        final WaitNotifyTest waitNotifyTest = new WaitNotifyTest();

        Runnable increaser = () -> {
            while (true) {
                waitNotifyTest.increase();
            }
        };

        Runnable decreaser = () -> {
            while (true) {
                waitNotifyTest.decrease();
            }
        };

        new Thread(increaser).start();
        new Thread(increaser).start();
        new Thread(decreaser).start();
        new Thread(decreaser).start();
    }

    public synchronized void increase() {
        while (j >= 10) {
            try {
                System.out.println(Thread.currentThread() + "-increase wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread() + "-increase work");
        j++;
        notify();
    }

    public synchronized void decrease() {
        while (j <= 0) {
            try {
                System.out.println(Thread.currentThread() + "-decrease wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread() + "-decrease work");
        j--;
        notify();
    }

}
