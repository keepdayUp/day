/**
 * ��Ҫ���ַ���������Ϊ��������
 * ������������У�m1��m2��ʵ��������ͬһ������
 * ����������ᷢ���ȽϹ�������󣬱������õ���һ����⣬�ڸ�����д����������ַ�����Hello����
 * �����������Դ�룬���������Լ��Ĵ�����Ҳ������"Hello",��ʱ����п��ܷ����ǳ����������������
 * ��Ϊ��ĳ�������õ�����ⲻ�����ʹ����ͬһ����
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
