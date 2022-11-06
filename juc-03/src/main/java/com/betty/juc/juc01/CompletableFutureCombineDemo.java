package com.betty.juc.juc01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 结果合并 thenCombine
 */
public class CompletableFutureCombineDemo {

    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -- start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -- start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        CompletableFuture<Integer> combine = completableFuture.thenCombine(completableFuture2, (i, i2) -> {
            System.out.println("合并结果");
            return i + i2;
        });

        System.out.println(Thread.currentThread().getName() + "\t --------" + combine.join());

    }
}
