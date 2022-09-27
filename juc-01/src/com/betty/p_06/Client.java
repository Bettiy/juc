package com.betty.p_06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁、非公平锁
 */
public class Client {

    private int ticketNum = 30;
    private final Lock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        try {
            if (ticketNum > 0) {
                System.out.println(Thread.currentThread().getName() + " :: " + ticketNum--);
            }
            if (ticketNum == 0) {
                System.out.println(Thread.currentThread().getName() + " :: " + "ticket out...");
                ticketNum = -1;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                client.sale();
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                client.sale();
            }
        }, "BB").start();
    }

}


