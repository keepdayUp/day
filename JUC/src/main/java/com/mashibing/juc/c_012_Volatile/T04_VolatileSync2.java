/**
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * 运行下面的程序，并分析结果
 * @author mashibing
 */
package com.mashibing.juc.c_012_Volatile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class T04_VolatileSync2 {
	volatile AtomicInteger count = new AtomicInteger(0);
	void m() {
		for (int i = 0; i < 10000; i++) count.incrementAndGet();
	}

	public static void main(String[] args) {
		T04_VolatileSync2 t = new T04_VolatileSync2();

		List<Thread> threads = new ArrayList();

		IntStream.range(0,10).forEach(i->threads.add(new Thread(t::m, "thread-"+i)));

		threads.forEach((o)->o.start());

		threads.forEach((o)->{
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(t.count);


	}

}


