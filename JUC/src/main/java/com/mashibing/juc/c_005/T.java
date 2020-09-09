/**
 * 分析一下这个程序的输出
 * @author mashibing
 */

package com.mashibing.juc.c_005;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class T implements Runnable {

	private static  volatile int count = 0;

	static List<Thread> list = new ArrayList(100);
	
	public synchronized void run() {
		for (int i = 0; i <10000 ; i++) {
			count++;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}
	
	public static void main(String[] args) {
		T t = new T();
		for(int i=0; i<100; i++) {
			Thread thread = new Thread(t, "THREAD" + i);
			list.add(thread);
			thread.start();

		}

		list.stream().forEach(thread-> {
			try {
				thread.join();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(count);
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
