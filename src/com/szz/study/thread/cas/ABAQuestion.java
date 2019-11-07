package com.szz.study.thread.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAQuestion {
	
	private static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);
	
	private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100, 1);
	
	
	public static void main(String[] args) {
		
		System.out.println("===========ABA����Ĳ���==================");
		
		new Thread(()->{
			atomicReference.compareAndSet(100, 101);
			atomicReference.compareAndSet(101, 100);
		}, "t1").start();
		
		new Thread(()->{
			try {
				Thread.sleep(1000);
				boolean result = atomicReference.compareAndSet(100, 2019);
				System.out.println(Thread.currentThread().getName() +"\t�Ƿ��޸ĳɹ�:" + result + "\t" + atomicReference.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "t2").start();
		
		
		
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.println("===========ABA����Ľ��==================");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		new Thread(()-> {
			try {
				int stamp = atomicStampedReference.getStamp();
				System.out.println(Thread.currentThread().getName() + "\t��1�ΰ汾��" + stamp);
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName() + "\t��1�ΰ汾��" + stamp);
				atomicStampedReference.compareAndSet(100, 101, 1, atomicStampedReference.getStamp() +1);
				System.out.println(Thread.currentThread().getName() + "\t��2�ΰ汾��" + atomicStampedReference.getStamp());
				atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() +1);
				System.out.println(Thread.currentThread().getName() + "\t��3�ΰ汾��" + atomicStampedReference.getStamp());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}, "t3").start();
		
		new Thread(()->{
			try {
				int stamp = atomicStampedReference.getStamp();
				System.out.println(Thread.currentThread().getName() + "\t��1�ΰ汾��" + stamp);
				Thread.sleep(4000);
				boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, atomicStampedReference.getStamp());
				System.out.println(Thread.currentThread().getName() + "\t�����޸��Ƿ�ɹ�:" + result + "\t��ǰ���°汾��:" + atomicStampedReference.getStamp());
				System.out.println(Thread.currentThread().getName() + "\t��ǰ����ֵ:" + atomicStampedReference.getReference());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		},"t4").start();
		
	}
	
	
	
}
