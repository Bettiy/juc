package com.betty.juc.lock;

public class ReEntryLockDemo {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "\t ---come in");

        m2();

        System.out.println(Thread.currentThread().getName() + "\t ---end m1");
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "\t ---come in");
        m3();
    }

    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t ---come in");
    }

    public static void main(String[] args) {
        ReEntryLockDemo demo = new ReEntryLockDemo();

        new Thread(demo::m1).start();
    }

    private static void reEntry() {
        final Object object = new Object();

        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " 外层调用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + " 中层调用");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + " 内层调用");
                    }
                }
            }
        }).start();
    }
}
