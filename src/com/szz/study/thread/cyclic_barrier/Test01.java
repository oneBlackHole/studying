package com.szz.study.thread.cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test01 {
	public static void main(String[] args) {
		int num = 7;
		CyclicBarrier barrier = new CyclicBarrier(num, ()->{System.out.println("召唤神龙");});
		
		for (int i = 1; i <= 7; i++) {
			final int tempInt = i;
			new Thread(()->{
				System.out.println(Thread.currentThread().getName() + "\t收集到第" + tempInt + "颗龙珠");
				try {
					barrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			},String.valueOf(i)).start();
		}
		
	}
}
