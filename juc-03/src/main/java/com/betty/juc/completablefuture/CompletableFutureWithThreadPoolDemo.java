package com.betty.juc.completablefuture;

import java.util.concurrent.*;

/**
 * 对计算结果进行处理
 */
public class CompletableFutureWithThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { throw new RuntimeException(e); }
                System.out.println("1 号任务\t" + Thread.currentThread().getName());
                return "abcd";
            }, pool).thenRunAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { throw new RuntimeException(e); }
                System.out.println("2 号任务\t" + Thread.currentThread().getName());
            }, pool).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { throw new RuntimeException(e); }
                System.out.println("3 号任务\t" + Thread.currentThread().getName());
            }).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { throw new RuntimeException(e); }
                System.out.println("4 号任务\t" + Thread.currentThread().getName());
            });

            System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 主线程继续执行");

        pool.shutdown();
    }
}
