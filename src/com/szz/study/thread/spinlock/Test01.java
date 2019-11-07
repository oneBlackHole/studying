package com.szz.study.thread.spinlock;
//自旋锁测试

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Test01 {
	
	//原子引用
	AtomicReference<Thread> atomicReference = new AtomicReference<Thread>();
	
	public void lock() {
		Thread thread = Thread.currentThread();
		System.out.println(Thread.currentThread().getName() + "\t come in lock()");
		while(!atomicReference.compareAndSet(null, thread)) {
			
		}
	}
	
	public void unlock() {
		Thread thread = Thread.currentThread();
		atomicReference.compareAndSet(thread, null);
		System.out.println(Thread.currentThread().getName() + "\t invoked unlock()");
	}
	
	
	public static void main(String[] args) {
		
		Test01 t = new Test01();
		
		new Thread(()->{
			t.lock();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.unlock();
		},"AA").start();
		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		new Thread(()->{
			t.lock();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.unlock();
		}, "BB").start();
		
	}
}
