package com.betty.juc.juc01;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureMallDemo {

    static final List<NetMall> list = Arrays.asList(new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("pdd"));

    public static List<String> getPrice(List<NetMall> list, final String productName) {
        return list.stream()
                .map(m -> String.format("%s in %s price is %.2f", productName, m.getNetMallName(), m.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    public static List<String> getCompletableFuturePrice(List<NetMall> list, final String productName) {
        return list.stream()
                .map(m -> CompletableFuture.supplyAsync(() -> String.format("%s in %s price is %.2f", productName, m.getNetMallName(), m.calcPrice(productName))))
                .collect(Collectors.toList()).stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        getPrice(list, "mysql").forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start + " ms");

        long start2 = System.currentTimeMillis();
        getCompletableFuturePrice(list, "JAVA").forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start2 + " ms");
    }

}


@AllArgsConstructor
class NetMall {
    @Getter
    private String netMallName;

    public double calcPrice(String productName) {
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}