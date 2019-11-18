package com.szz.study.thread.lock_condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test01 {
	public static void main(String[] args) {
		ShareResource shareResource = new ShareResource();
		new Thread(()->{
			for (int i = 0; i < 10; i++) {
				shareResource.print5();
			}
		}, "A").start();
		
		new Thread(()->{
			for (int i = 0; i < 10; i++) {
				shareResource.print10();
			}
		}, "B").start();
		
		new Thread(()->{
			for (int i = 0; i < 10; i++) {
				shareResource.print15();
			}
		}, "C").start();
	}

}

class ShareResource{
	private int number = 1;//1, A线程 2,B线程 3,C线程
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();
	
	public void print5() {
		lock.lock();
		try {
			while(number != 1) {
				c1.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			
			number = 2;
			c2.signal();
		}catch (Exception e) {
			// TODO: handle exception
		} 
		finally {
			// TODO: handle finally clause
			lock.unlock();
		}
	}
	
	public void print10() {
		lock.lock();
		try {
			while(number != 2) {
				c2.await();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			number = 3;
			c3.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
	
	public void print15() {
		lock.lock();
		try {
			while(number != 3) {
				c3.await();
			}
			for (int i = 0; i < 15; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			number = 1;
			c1.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
		
	}
	
	
}
