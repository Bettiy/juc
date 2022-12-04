package com.betty.juc.spinlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t" + "lock...");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t" + "unLock...");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLock = new SpinLockDemo();

        new Thread(() -> {
            spinLock.lock();
            try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {throw new RuntimeException(e);}
            spinLock.unLock();
        }, "A").start();

        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {throw new RuntimeException(e);}


        new Thread(() -> {
            spinLock.lock();
            spinLock.unLock();
        }, "B").start();
    }
}
