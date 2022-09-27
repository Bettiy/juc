package com.betty.p_15;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步回调
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 没有返回值的异步调用
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        completableFuture.get();

        // 有返回值的异步调用
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return (int) (Math.random() * 10);
        });

        completableFuture2.whenComplete((key, e) -> {
            System.out.println("key = " + key);
            System.out.println("e = " + e);
        });
    }

}
