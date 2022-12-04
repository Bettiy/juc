package completablefuture;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AllOfDemo {

    static List<CompletableFuture<Integer>> list = new LinkedList<>();

    static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
           list.add(CompletableFuture.supplyAsync(() -> {
               try {
                   semaphore.acquire();
                   System.out.println(Thread.currentThread().getName() + " come...");
                   try {TimeUnit.SECONDS.sleep(new Random().nextInt(5));} catch (InterruptedException e) {e.printStackTrace();}
                   System.out.println(Thread.currentThread().getName() + " out...");
               } catch (Exception e) {
                   throw new RuntimeException(e);
               } finally {
                   semaphore.release();
               }
               return new Random().nextInt(10);
           }));
        }

        CompletableFuture<Void> future = CompletableFuture.allOf(list.toArray(new CompletableFuture[0]));
        List<Integer> result = future.thenApply(n -> list.stream().map(CompletableFuture::join).collect(Collectors.toList())).join();
        System.out.println(result);
    }
}
