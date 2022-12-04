package com.betty.juc.interrupt;

public class Demo4 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " " + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + " " + Thread.interrupted());
        System.out.println("--- 1");

        Thread.currentThread().interrupt();

        System.out.println(Thread.currentThread().getName() + " " + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + " " + Thread.interrupted());
        System.out.println("--- 2");

        Thread.interrupted();
        Thread.currentThread().interrupt();
    }
}
