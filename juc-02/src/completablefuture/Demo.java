package completablefuture;

import java.util.concurrent.*;

public class Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        ExecutorService pool = Executors.newCachedThreadPool();
        FutureTask<Integer> future = new FutureTask<>(()-> {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
            return 1024;
        });
        pool.submit(future);
        FutureTask<Integer> future2 = new FutureTask<>(()-> {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            return 1024;
        });
        pool.submit(future2);
        FutureTask<Integer> future3 = new FutureTask<>(()-> {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
            return 1024;
        });
        pool.submit(future3);
        pool.shutdown();
        System.out.println("future.get() = " + future.get(2, TimeUnit.SECONDS));
        System.out.println("future2.get() = " + future2.get());
        System.out.println("future3.get() = " + future3.get());
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

}
