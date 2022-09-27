package com.betty.p_04;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapNonSage {
    public static void main(String[] args) {
//        Set<String> set = new CopyOnWriteArraySet<>();

        Map<Integer, String> map = new ConcurrentHashMap<>();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            final int key = i;
            threads[i] = new Thread(() -> {
                map.put(key, UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "::" + map);
            }, "Thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
