package com.mashibing.juc.c_000;

import java.util.concurrent.Callable;

public class T02_HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRun!");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(()->{
            System.out.println("Hello Lambda!");
        }).start();

        try {
          String result =  new Callable<String>(){
                @Override
                public String call() throws Exception {
                    return "str1";
                }
            }.call();

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

//请你告诉我启动线程的三种方式 1：Thread 2: Runnable 3:Executors.newCachedThrad
