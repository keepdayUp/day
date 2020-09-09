package com.mashibing.juc.c_000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArray_1 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        AtomicIntegerArray array = new AtomicIntegerArray(arr);
        Thread thread = new Thread(() -> array.compareAndSet(0,1,2));
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(array);
    }
}
