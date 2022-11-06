package com.betty.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Demo {

    static volatile boolean isStop = false;

    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " isInterrupted() set true, exit...");
                    break;
                }
                System.out.println("--------- run ---------");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(t1::interrupt, "t2").start();
    }

    private static void atomicBoolean() {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + " atomicBoolean set true, exit");
                    break;
                }
                System.out.println("------ he ------");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }

    private static void _volatile() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + " isStop set true, exit");
                    break;
                }
                System.out.println("--------------he");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}
