package com.betty.juc.atomic.array;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {

    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1, 2 , 3, 4, 5});

        int andIncrement = atomicIntegerArray.incrementAndGet(3);
        System.out.println(andIncrement);

        atomicIntegerArray.set(0, 2022);
        System.out.println("atomicIntegerArray.get(0) = " + atomicIntegerArray.get(0));
    }
}
