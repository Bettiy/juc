package completablefuture;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo03 {

    public static void main(String[] args) {
        Resources resources = new Resources();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            executor.execute(() -> {
                resources.setMessage(String.valueOf(finalI));
            });
        }
        executor.shutdown();
    }
}
