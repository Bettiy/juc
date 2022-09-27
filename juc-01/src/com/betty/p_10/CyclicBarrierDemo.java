package com.betty.p_10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * JUC辅助类
 * CyclicBarrier
 */
public class CyclicBarrierDemo {

    private static final int NUMBER = 7;

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(NUMBER, () -> {
            System.out.println("集齐7颗龙珠即可召唤神龙");
        });

        // 集齐7颗龙珠的过程
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "星龙珠被收集到了");
                    cb.await();
                } catch (BrokenBarrierException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }
    }

}
