package com.betty.p_10;

import java.util.concurrent.CountDownLatch;

/**
 * JUC辅助类
 * CountDownLatch
 */
public class CountDownLatchDemo {


    // 六个同学陆续离开教室，班长锁门
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            int num = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " :: [" + num + "]号同学离开了教室");
                latch.countDown();
            }, "T" + i).start();
        }

        latch.await();
        System.out.println(Thread.currentThread().getName() + " :: " + "班长锁门走人了");
    }

}
