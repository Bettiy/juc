package com.betty.p_01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket {

    private int ticketNumber = 30;
    private final Lock lock = new ReentrantLock();

    public synchronized void soul() {
        if (ticketNumber > 0) {
            ticketNumber--;
            System.out.println(Thread.currentThread().getName() + "::" + ticketNumber);
        }
        /*lock.lock();
        try {
            if (ticketNumber > 0) {
                ticketNumber--;
                System.out.println(Thread.currentThread().getName() + "::" + ticketNumber);
            }
        } finally {
            lock.unlock();
        }*/
    }

}
