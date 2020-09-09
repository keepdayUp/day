/**
 * 不要以字符串常量作为锁定对象
 * 在下面的例子中，m1和m2其实锁定的是同一个对象
 * 这种情况还会发生比较诡异的现象，比如你用到了一个类库，在该类库中代码锁定了字符串“Hello”，
 * 但是你读不到源码，所以你在自己的代码中也锁定了"Hello",这时候就有可能发生非常诡异的死锁阻塞，
 * 因为你的程序和你用到的类库不经意间使用了同一把锁
 * 
 * jetty
 * 
 * @author mashibing
 */
package com.mashibing.juc.c_017_MoreAboutSync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DoNotLockString {
	
	String s1 = "Hello";
	String s2 = "Hello";

	Integer num1 = 100;
	Integer num2 = 100;
	void m1() {
		synchronized(s1) {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	void m2() {
		synchronized(num2) {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		DoNotLockString lock = new DoNotLockString();
		long start = System.currentTimeMillis();
		List<Thread> list = new ArrayList();
		list.add(new Thread(() ->lock.m1()));
		list.add(new Thread(() ->lock.m2()));
		list.stream().forEach(Thread::start);
		list.stream().forEach(i->{
			try {
				i.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println((System.currentTimeMillis()-start)/1000);
	}

}
