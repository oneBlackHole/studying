package com.szz.study.thread.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test01 {
	public static void main(String[] args) {
		//ExecutorService threadPool = Executors.newFixedThreadPool(5);
		//ExecutorService threadPool = Executors.newSingleThreadExecutor();
		ExecutorService threadPool = Executors.newCachedThreadPool();
		try {
			for (int i = 0; i < 10; i++) {
				threadPool.execute(()->{
					System.out.println(Thread.currentThread().getName() + "\t 执行业务");
				});
				TimeUnit.MICROSECONDS.sleep(200);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			threadPool.shutdown();
		}
		
		
	}
	
}
