package com.betty.juc.atomic;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {

    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
    }
}
