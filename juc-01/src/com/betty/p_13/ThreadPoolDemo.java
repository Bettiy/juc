package com.betty.p_13;

import java.util.concurrent.*;

/**
 * 线程池
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        try {
            for (int i = 0; i < 10; i++) {
                pool.execute(() -> {
                    System.out.println("newFixedThreadPool " + Thread.currentThread().getName() + " 正在办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }

        ExecutorService pool2 = Executors.newSingleThreadExecutor();
        try {
            for (int i = 0; i < 10; i++) {
                pool2.execute(() -> {
                    System.out.println("newSingleThreadExecutor " + Thread.currentThread().getName() + " 正在办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool2.shutdown();
        }

        ExecutorService pool3 = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 20; i++) {
                pool3.execute(() -> {
                    System.out.println("newCachedThreadPool " + Thread.currentThread().getName() + " 正在办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool3.shutdown();
        }


        // 自定义线程池
        ExecutorService pool4 = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 0; i < 10; i++) {
                pool4.execute(() -> {
                    System.out.println("customThreadExecutor " + Thread.currentThread().getName() + " 正在办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool4.shutdown();
        }
    }

}
