package com.betty.juc.atomic.fieldupdate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 多线程实例化一个类，只能让一个线程实例成功
 */
public class P_AtomicReferenceFieldUpdater {

    public static void main(String[] args) {
        Resource resource = new Resource();
        for (int i = 0; i < 5; i++) {
            new Thread(resource::init, "T" + i).start();
        }
    }
}

class Resource {

    public volatile Boolean isInit = false;

    static final AtomicReferenceFieldUpdater<Resource, Boolean> fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Resource.class, Boolean.class, "isInit");

    public void init() {
        if (fieldUpdater.compareAndSet(this, false, true)) {
            System.out.println(Thread.currentThread().getName() + "\t" + "开始实例化...");
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t" + "实例化完成...");
        } else {
            System.out.println(Thread.currentThread().getName() + "\t" + "存在线程进行实例化中...");
        }
    }
}