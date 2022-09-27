package com.betty.p_12;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 */
public class BlockingQueueDemo {


    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);


        new Thread(() -> {
            try {
                queue.put(UUID.randomUUID().toString().substring(0, 8));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(queue);
        }, "T1").start();

        try {

            // 1
            queue.add("a");
            queue.remove();
            queue.element();
            // 2
            queue.offer("a");
            queue.poll();
            queue.peek();

            // 3
            queue.put("a");
            queue.take();

            // 4
            queue.offer("a", 5, TimeUnit.SECONDS);
            queue.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "T2").start();

    }

}
