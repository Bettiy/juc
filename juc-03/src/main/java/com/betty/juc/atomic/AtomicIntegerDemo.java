package com.betty.juc.atomic;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    static final int SIZE = 50;

    static final CountDownLatch LATCH = new CountDownLatch(SIZE);

    @SneakyThrows
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();

        for (int i = 0; i < SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        myNumber.addPlusPlus();
                    }
                } finally {
                    LATCH.countDown();
                }
            }, String.valueOf(i)).start();
        }

        LATCH.await();
        System.out.println("atomicInteger = " + myNumber.atomicInteger.get());
    }
}

class MyNumber {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlusPlus() {
        atomicInteger.getAndIncrement();
    }
}