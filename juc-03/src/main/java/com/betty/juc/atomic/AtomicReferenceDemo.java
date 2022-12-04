package com.betty.juc.atomic;

import lombok.AllArgsConstructor;
import lombok.ToString;

public class AtomicReferenceDemo {

    /*public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User z3 = new User("张三", 18);
        User li4 = new User("李四", 28);

        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get());
    }*/

    public static void main(String[] args) {
        int i = 0;
        do {
            System.out.println(++i);
        } while (i < 20);
    }
}

@AllArgsConstructor
@ToString
class User {

    String name;

    int age;
}