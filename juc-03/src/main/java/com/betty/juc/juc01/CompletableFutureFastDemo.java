package com.betty.juc.juc01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureFastDemo {

    public static void main(String[] args) {
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A come in");
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            return "playA";
        });

        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B come in");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            return "playB";
        });

        CompletableFuture<String> completableFuture = playA.applyToEither(playB, f -> f + " is winner");

        System.out.println(Thread.currentThread().getName() + "\t --------" + completableFuture.join());
    }
}
