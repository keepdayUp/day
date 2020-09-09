/**
 * �����������⣺���Ա�����
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 * 
 * ��lists���volatile֮��t2�ܹ��ӵ�֪ͨ�����ǣ�t2�̵߳���ѭ�����˷�cpu�����������ѭ����
 * ���ң������if �� break֮�䱻����̴߳�ϣ��õ��Ľ��Ҳ����ȷ��
 * ����ô���أ�
 * @author mashibing
 */
package com.mashibing.juc.c_020_01_Interview;


import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;


public class T02_WithVolatile {

	//���volatile��ʹt2�ܹ��õ�֪ͨ
//	volatile List lists = new LinkedList();

	static List lists;
	public  void add(Object o) {
		lists.add(o);
	}

	public  int size() {
		return lists.size();
	}

	public static void main(String[] args) {

		lists = Collections.synchronizedList(new LinkedList<>());
		T02_WithVolatile c = new T02_WithVolatile();
		Thread t1 = new Thread(() -> {
			for(int i=0; i<10; i++) {
				c.add(new Object());
				System.out.println("add " + i);

			}
		}, "t1");
		t1.start();
		
		new Thread(() -> {
			while(true) {
				if(c.size() == 5) {
					System.out.println("t2 ����");
					break;
				}
			}

		}, "t2").start();
	}

}
