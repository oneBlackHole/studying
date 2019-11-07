package com.szz.study.thread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Test01 {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);//模拟3个资源：停车位
		for (int i = 0; i < 6; i++) {
			new Thread(()->{
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
					TimeUnit.SECONDS.sleep(3);
					System.out.println(Thread.currentThread().getName() + "\t停车3秒后离开");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
			}, String.valueOf(i)).start();
		}
		
	}
}
