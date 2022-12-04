package com.betty.juc.atomic.fieldupdate;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class P_AtomicIntegerFieldUpdate {

    @SneakyThrows
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        Bank bank = new Bank();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        bank.transfer2();
                    }
                } finally {
                    latch.countDown();
                }
            }, "T" + i).start();
        }

        latch.await();
        System.out.println("bank.money = " + bank.money);
    }
}

class Bank {

    String bankName = "CCB";

    String bankNo = "12121545661112";

    String owner = "z3";

    public volatile int money = 0;

    public /*synchronized*/ void transfer() {
        money++;
    }

    static final AtomicIntegerFieldUpdater<Bank> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Bank.class, "money");

    public void transfer2() {
        fieldUpdater.incrementAndGet(this);
    }
}