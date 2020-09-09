package com.mashibing.juc.c_000;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.DoubleBinaryOperator;

public class T01_WhatIsThread {
    private static class T1 extends Thread {
        @Override
        public void run() {
           for(int i=0; i<10; i++) {
               try {
                   TimeUnit.MICROSECONDS.sleep(100000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(i+"T1");
           }
        }
    }

    public static void main(String[] args) {
        //new T1().run();
//        T1 t1 = new T1();
//        t1.start();
//        for(int i=0; i<10; i++) {
//            try {
//                TimeUnit.MICROSECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(i+"main");
//        }

//        try {
//            t1.interrupt();
////            t1.join();
//            System.out.println("=====");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        T2 t2 = new T2();
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
//            t2.interrupt();
            LockSupport.unpark(t2);
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static class T2 extends  Thread{

        @Override
        public void run() {

                LockSupport.park();
                System.out.println(111);

        }
    }
}
