package com.betty.p_14;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 拆分、合并
 */
class MyTask extends RecursiveTask<Integer> {
    private static final int VALUE = 10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) >= 10) {
            for (int i = 0; i <= end; i++) {
                result += i;
            }
        } else {
            int mid = (end - begin) / 2;
            MyTask myTask = new MyTask(begin, mid);
            MyTask myTask2 = new MyTask(mid + 1, end);
            myTask.fork();
            myTask.fork();
            result = myTask.join() + myTask2.join();
        }
        return result;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> task = forkJoinPool.submit(new MyTask(1, 100));
        System.out.println(task.get());
        forkJoinPool.shutdown();
    }
}
