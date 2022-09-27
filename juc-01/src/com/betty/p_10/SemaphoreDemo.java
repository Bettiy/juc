package com.betty.p_10;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * JUC辅助类
 * 信号灯
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);


        // 模拟6辆汽车停车
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到了车位");

                    // 设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));


                    System.out.println(Thread.currentThread().getName() + " 离开了车位...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // 释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }

}
