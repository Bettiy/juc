package com.betty.p_08;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 * <p>
 *     产生死锁的原因有三个
 */
public class Client {

    static final Object o = new Object();
    static final Object o2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " 持有锁o，试图获取锁o2");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " 获取锁o");
                }
            }
        }, "T1").start();

        new Thread(() -> {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " 持有锁o2，试图获取锁o");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + " 获取锁o2");
                }
            }
        }, "T2").start();
    }

}
