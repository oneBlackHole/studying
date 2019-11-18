package com.szz.study.thread.blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Test02 {
	public static void main(String[] args) {
		BlockingQueue<String> blockingQueue = new SynchronousQueue<String>();
		
		new Thread(()->{
			try {
				System.out.println(Thread.currentThread().getName() + "\t put 1");
				blockingQueue.put("1");
				
				System.out.println(Thread.currentThread().getName() + "\t put 2");
				blockingQueue.put("2");
				
				System.out.println(Thread.currentThread().getName() + "\t put 3");
				blockingQueue.put("3");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "AAA").start();
		
		new Thread(()->{
			
			try {
				TimeUnit.SECONDS.sleep(5);
				System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
				
				TimeUnit.SECONDS.sleep(5);
				System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
				
				TimeUnit.SECONDS.sleep(5);
				System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}, "BBB").start();
		
	}
}
