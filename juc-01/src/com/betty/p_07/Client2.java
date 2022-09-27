package com.betty.p_07;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁 lock
 * 可重入锁也称为递归锁
 */
public class Client2 {

    public static void main(String[] args) {
        final Lock lock1 = new ReentrantLock();
        new Thread(() -> {
            try {
                lock1.lock();
                System.out.println(Thread.currentThread().getName() + " 外层");
                try {
                    lock1.lock();
                    System.out.println(Thread.currentThread().getName() + " 内层");
                } finally {
                     lock1.unlock();
                }
            } finally {
                lock1.unlock();
            }
        }, "T1").start();


        new Thread(() -> {
            lock1.lock();

            System.out.println(Thread.currentThread().getName() + " other");

            lock1.unlock();
        }, "T2").start();
    }

}
