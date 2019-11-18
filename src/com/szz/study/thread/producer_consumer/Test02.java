package com.szz.study.thread.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Test02 {
	public static void main(String[] args) {
		Myresource myresource = new Myresource(new ArrayBlockingQueue<String>(5));
		new Thread(()->{
			try {
				myresource.prod();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"prod").start();
		
		
		new Thread(()->{
			try {
				myresource.consumer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"consum").start();
		
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + "\t 大老板叫停....");
		myresource.stop();
		
		
	}
}

class Myresource{
	private volatile boolean FLAG = true;
	
	private AtomicInteger atomicInteger = new AtomicInteger();
	
	private BlockingQueue<String> blockingQueue = null;

	public Myresource(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	
	public void prod() throws Exception {
		String data = null;
		boolean result;
		while(FLAG) {
			data = atomicInteger.incrementAndGet() + "";
			result = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
			if(result) {
				 System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
			 }else {
				 System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
			}
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println(Thread.currentThread().getName() + "\t 大老板叫停，生产结束");
	}
	
	
	public void consumer() throws Exception {
		String result = null;
		while(FLAG) {
			result = blockingQueue.poll(2, TimeUnit.SECONDS);
			if(null ==result || result.equalsIgnoreCase("")) {
				FLAG = false;
				 System.out.println(Thread.currentThread().getName() + "\t 超过2秒钟,没有取到蛋糕消费退出");
				 return;//一定要加，保证消费队列是空的情况下，消费线程可以退出
			}
			System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
		}
		
	}	
	
	public void stop() {
		FLAG = false;
	}
	
}
