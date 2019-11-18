package com.szz.study.thread.producer_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test01 {
	public static void main(String[] args) {
		ShateData shateData = new ShateData();
		
		new Thread(()->{
			for (int i = 0; i < 5; i++) {
				shateData.increment();
			}
		}, "producer").start();
		
		new Thread(()->{
			for (int i = 0; i < 5; i++) {
				shateData.decrease();
			}
		}, "consumer").start();
		
		
		
	}
}


class ShateData{
	private static int number = 0;
	
	private Lock lock = new ReentrantLock();
	
	private Condition condition = lock.newCondition();
	
	public void increment() {
		lock.lock();
		
		try {
			//1.判断
			while(number != 0) {
				//等待
				condition.await();
			}
			//2.干活
			number ++;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			//3.通知唤醒
			condition.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
		
	}
	
	public void decrease() {
		lock.lock();
		try {
			while(number == 0) {
				condition.await();
			}
			number --;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
	
	
	
	
}
