package com.szz.study.thread.count_down_latch;

import java.util.concurrent.CountDownLatch;

public class Test01 {
	public static void main(String[] args) {
		int num = 6;
		CountDownLatch countDownLatch = new CountDownLatch(num);
		
		for (int i = 0; i < 6; i++) {
			new Thread(()->{
				System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教学楼...");
				countDownLatch.countDown();
			}, String.valueOf(i)).start();
		}
		
		try {
			countDownLatch.await();
			System.out.println(Thread.currentThread().getName() + "\t 保洁阿姨要锁门了...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
