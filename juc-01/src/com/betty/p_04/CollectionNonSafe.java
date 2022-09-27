package com.betty.p_04;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionNonSafe {

    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    list.add(UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(Thread.currentThread().getName() + "::" + list);
                }
            }, "Thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }

    }

}
