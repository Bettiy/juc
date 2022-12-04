package com.betty.juc.atomic;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {

    public static void main(String[] args) {
        Book javaBook = new Book("JAVA", 20);

        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javaBook, 1);

        System.out.println(stampedReference.getReference() + "\t" + stampedReference.getStamp());

        Book mySQLBook = new Book("MySQL", 50);

        boolean b;
        b = stampedReference.compareAndSet(javaBook, mySQLBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);

        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

        b = stampedReference.compareAndSet(mySQLBook, javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);

        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
    }
}

@AllArgsConstructor
@ToString
class Book {
    String bookName;

    int price;
}