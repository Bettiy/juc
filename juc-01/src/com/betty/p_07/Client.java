package com.betty.p_07;

/**
 * 可重入锁 synchronized
 */
public class Client {


    public synchronized void hello() {
        System.out.println(Thread.currentThread().getName() + " :: " + "hello...");
        world();
    }

    public synchronized void world() {
        System.out.println(Thread.currentThread().getName() + " :: " + "world...");
    }

    public static void main(String[] args) {
        Client client = new Client();
        new Thread(() -> {
            client.hello();
        }, "AA").start();

        new Thread(() -> {
            client.hello();
        }, "BB").start();
    }

}
