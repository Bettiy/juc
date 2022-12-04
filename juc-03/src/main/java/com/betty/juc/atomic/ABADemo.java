package com.betty.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t" + "首次版本号: " + stamp);
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {throw new RuntimeException(e);}

            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t" + "第二次版本号: " + stampedReference.getStamp());

            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t" + "第三次版本号: " + stampedReference.getStamp());
        }, "T3").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t" + "首次版本号: " + stamp);

            try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {throw new RuntimeException(e);}
            boolean b = stampedReference.compareAndSet(100, 2022, stamp, stamp + 1);
            System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
        }, "T4").start();
    }

    private static void abaProblem() {
        new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            try {TimeUnit.MILLISECONDS.sleep(10);} catch (InterruptedException e) {throw new RuntimeException(e);}
            atomicInteger.compareAndSet(101, 100);
        }, "T1").start();

        new Thread(() -> {
            try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {throw new RuntimeException(e);}
            atomicInteger.compareAndSet(100, 2022);
            System.out.println("atomicInteger.get() = " + atomicInteger.get());
        }, "T2").start();
    }
}
