package com.mashibing.juc.c_000;

import java.util.stream.IntStream;

public class Volatile_1 {
    static volatile  int  num = 0;
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> increase());
        Thread t2 = new Thread(() -> decrease());
        try {
            t1.join();
            t2.join();
            System.out.println(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void decrease() {
        for (int i = 0; i < 10000; i++) {
            num++;
        }
    }

    private static void increase() {
        for (int i = 0; i <10000 ; i++) {
            num--;
        }
    }

}
