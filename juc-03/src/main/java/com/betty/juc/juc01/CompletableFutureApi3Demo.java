package com.betty.juc.juc01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 对计算结果进行消费
 */
public class CompletableFutureApi3Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(f -> f + 1)
                .thenApply(f -> f + 2)
                .thenApply(f -> f + 3)
                .thenAccept(System.out::println);
    }
}
