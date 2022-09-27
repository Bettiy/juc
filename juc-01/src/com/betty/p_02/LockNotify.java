package com.betty.p_02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share2 {
    private int num = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "::" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public  void sub() throws InterruptedException {
        lock.lock();
        try {
            while (num != 1) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "::" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
public class LockNotify {
    public static void main(String[] args) {
        Share2 share = new Share2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.add();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.sub();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.add();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.sub();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "DD").start();
    }
}
