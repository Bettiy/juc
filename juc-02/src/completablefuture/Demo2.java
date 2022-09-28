package completablefuture;

import java.util.concurrent.*;

public class Demo2 {

    private static final ExecutorService POOL = new ThreadPoolExecutor(5, 10, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " come in...");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int i = 1 / 0;
            System.out.println("result: " + result);
            return result;
        }, POOL).whenComplete((r, e) -> {
            if (e == null) System.out.println("success: " + r);
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return null;
        });
        POOL.shutdown();
    }

    private static void demo() {
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        }, POOL);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 1024;
        });
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 1024;
        }, POOL);
        POOL.shutdown();
    }

}
