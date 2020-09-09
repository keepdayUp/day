/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 这样行不行？
 *
 * 容易产生脏读问题（dirtyRead）
 */

package com.mashibing.juc.c_008;

import com.lmax.disruptor.EventTranslatorOneArg;
import jdk.jshell.EvalException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;

public class Account {
	String name;
	double balance;
	Object obj = new Object();
//	AtomicReference reference = new AtomicReference<>(obj);

	
	public synchronized void set(String name, double balance) {
		this.name = name;
//		reference.accumulateAndGet(obj, new BinaryOperator() {
//			@Override
//			public Object apply(Object o, Object o2) {
//				return o;
//			}
//		});
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		this.balance = balance;
	}
	
	public /*synchronized*/ double getBalance(String name) {
		return this.balance;
	}
	
	
	public static void main(String[] args) {
		Account a = new Account();
		new Thread(()->a.set("zhangsan", 100.0)).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(a.getBalance("zhangsan"));
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(a.getBalance("zhangsan"));
	}
}
