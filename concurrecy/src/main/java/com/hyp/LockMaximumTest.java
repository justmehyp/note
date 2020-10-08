package com.hyp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockMaximumTest {
    public static void main(String[] args) {

//        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//        Lock lock = readWriteLock.writeLock();

        Lock lock = new ReentrantLock();
        for (long i = 0; i < 0x7fffffff + 0L; i++) {
//            System.out.println(i);
            lock.lock();
        }
        System.out.println("end");
    }
}
