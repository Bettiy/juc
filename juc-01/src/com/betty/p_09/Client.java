package com.betty.p_09;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Callable
 */
public class Client {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        T t = new T();
        FutureTask<Integer> task = new FutureTask<>(t);

        new Thread(task, "T1").start();

        System.out.println("task.isDone() = " + task.isDone());

        System.out.println("task.get() = " + task.get());
    }

}

class T implements Callable<Integer> {
    @Override
    public Integer call() throws InterruptedException {
        int num = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " :: " + num++);
        }
        TimeUnit.SECONDS.sleep(4);
        return num;
    }
}