package com.betty.juc.interrupt;

import java.util.concurrent.TimeUnit;

public class Demo2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 300 ; i++) {
                System.out.println("----- "+ i);
            }
            System.out.println("t1 线程调用interrupt() 后的中断标识02 " + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        System.out.println("t1线程默认的中断标识 " + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t1.interrupt();
        System.out.println("t1线程调用interrupt() 后的中断标识01 " + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("t1线程调用interrupt() 后的中断标识03 " + t1.isInterrupted());
    }
}
