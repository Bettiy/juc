package com.betty.p_11;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache {

    private volatile Map<String, Object> map = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " - 正在执行写操作...");
            map.put(key, value);
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " - 写操作完成...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        lock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + " - 正在执行读操作...");
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " - 读操作完成...");
            result = map.get(key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
        return result;
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 0; i < 5; i++) {
            final int key = i;
            new Thread(() -> {
                cache.put(key + "", key + "-value");
            }, "write:" + key + "").start();
        }
        for (int i = 0; i < 5; i++) {
            final int key = i;
            new Thread(() -> {
                cache.get(key + "");
            }, "read:" + key + "").start();
        }
    }
}
