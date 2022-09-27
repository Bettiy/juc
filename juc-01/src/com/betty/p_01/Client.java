package com.betty.p_01;

public class Client {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.soul();
            }
        },"AA").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.soul();
            }
        },"BB").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.soul();
            }
        },"CC").start();
    }

}
