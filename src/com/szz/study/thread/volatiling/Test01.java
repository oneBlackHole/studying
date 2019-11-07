package com.szz.study.thread.volatiling;

import java.util.concurrent.atomic.AtomicInteger;

public class Test01 {
	
	public static void main(String[] args) {
		Test02 t = new Test02();
		for (int i = 0; i < 20; i++) {
			new Thread(()->{t.intPlusPlus();}).start();
			new Thread(()->{t.atomicPlusPlus();}).start();
		}
		
		while(Thread.activeCount() > 2) {
			Thread.yield();
		}
		
		System.out.println(Thread.currentThread().getName() + "int finally number value: " + t.getNumber());
		System.out.println(Thread.currentThread().getName() + "atomic finally number value: " + t.getAtomicInteger());
	}
	
}


class Test02 {
	
	private int number = 0;
	
	private AtomicInteger atomicInteger = new AtomicInteger();
	
	public void intPlusPlus() {
		for (int i = 0; i < 1000; i++) {
			number ++ ;
		}
	}
	
	public void atomicPlusPlus() {
		for (int i = 0; i < 1000; i++) {
			atomicInteger.getAndIncrement();
		}
	}
	
	public int getNumber() {
		return number;
	}
	
	public AtomicInteger getAtomicInteger() {
		return atomicInteger;
	}
	
	
}
