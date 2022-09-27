package com.betty.p_05;

import java.util.concurrent.TimeUnit;

/**
 * 锁对象不同 Class this
 */
public class Phone {
    public synchronized void sendSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------------send sms...");
    }

    public synchronized void sendEmail() {
        System.out.println("------------send email...");
    }

    public void getHello() {
        System.out.println("hello...");
    }
}
class Client {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "AA").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
//            phone.sendEmail();
//            phone.getHello();
            phone2.sendEmail();
        }, "BB").start();
    }
}