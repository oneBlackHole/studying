package com.szz.study.thread.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAQuestion {
	
	private static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);
	
	private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100, 1);
	
	
	public static void main(String[] args) {
		
		System.out.println("===========ABA问题的产生==================");
		
		new Thread(()->{
			atomicReference.compareAndSet(100, 101);
			atomicReference.compareAndSet(101, 100);
		}, "t1").start();
		
		new Thread(()->{
			try {
				Thread.sleep(1000);
				boolean result = atomicReference.compareAndSet(100, 2019);
				System.out.println(Thread.currentThread().getName() +"\t是否修改成功:" + result + "\t" + atomicReference.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "t2").start();
		
		
		
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.println("===========ABA问题的解决==================");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		new Thread(()-> {
			try {
				int stamp = atomicStampedReference.getStamp();
				System.out.println(Thread.currentThread().getName() + "\t第1次版本号" + stamp);
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName() + "\t第1次版本号" + stamp);
				atomicStampedReference.compareAndSet(100, 101, 1, atomicStampedReference.getStamp() +1);
				System.out.println(Thread.currentThread().getName() + "\t第2次版本号" + atomicStampedReference.getStamp());
				atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() +1);
				System.out.println(Thread.currentThread().getName() + "\t第3次版本号" + atomicStampedReference.getStamp());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}, "t3").start();
		
		new Thread(()->{
			try {
				int stamp = atomicStampedReference.getStamp();
				System.out.println(Thread.currentThread().getName() + "\t第1次版本号" + stamp);
				Thread.sleep(4000);
				boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, atomicStampedReference.getStamp());
				System.out.println(Thread.currentThread().getName() + "\t最新修改是否成功:" + result + "\t当前最新版本号:" + atomicStampedReference.getStamp());
				System.out.println(Thread.currentThread().getName() + "\t当前最新值:" + atomicStampedReference.getReference());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		},"t4").start();
		
	}
	
	
	
}
