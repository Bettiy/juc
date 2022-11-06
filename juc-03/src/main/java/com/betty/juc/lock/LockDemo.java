package com.betty.juc.lock;

import java.util.concurrent.TimeUnit;

public class LockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        new Thread(() -> {
            Phone.staticSendSMS();
        }, "b").start();
    }
}

class Phone {

    public synchronized void sendEmail() {
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { throw new RuntimeException(e); }
        System.out.println(Thread.currentThread().getName() + ": send email...");
    }

    public static synchronized void staticSendEmail() {
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { throw new RuntimeException(e); }
        System.out.println(Thread.currentThread().getName() + ": send email...");
    }

    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + ": send sms...");
    }

    public static synchronized void staticSendSMS() {
        System.out.println(Thread.currentThread().getName() + ": send sms...");
    }
}