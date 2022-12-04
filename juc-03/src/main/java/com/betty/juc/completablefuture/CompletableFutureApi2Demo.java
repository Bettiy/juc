package com.betty.juc.completablefuture;

import java.util.concurrent.*;

/**
 * 对计算结果进行处理
 */
public class CompletableFutureApi2Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { throw new RuntimeException(e); }
            System.out.println("111");
            return 1;
        }, pool).handle((f, e) -> {
            int i = 1 / 0;
            System.out.println("222");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----------计算结果: " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + " 主线程继续执行");

        pool.shutdown();
    }
}
