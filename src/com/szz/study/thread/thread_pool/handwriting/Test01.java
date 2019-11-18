package com.szz.study.thread.thread_pool.handwriting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test01 {
	public static void main(String[] args) {
		
		System.out.println("CUP核数:\t" + Runtime.getRuntime().availableProcessors());
		
		ExecutorService threadPool = new ThreadPoolExecutor(2, 
				5,
				1L, 
				TimeUnit.SECONDS, 
				new LinkedBlockingDeque<Runnable>(3), 
				Executors.defaultThreadFactory(), 
				new ThreadPoolExecutor.CallerRunsPolicy());
		//new ThreadPoolExecutor.AbortPolicy()
		//new ThreadPoolExecutor.CallerRunsPolicy()
		//new ThreadPoolExecutor.DiscardOldestPolicy()
		//new ThreadPoolExecutor.DiscardPolicy()
		
		
		
		try {
			for (int i = 0; i < 10; i++) {
				threadPool.execute(()->{
					System.err.println(Thread.currentThread().getName() + "\t 执行业务");
				});
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			threadPool.shutdown();
		}
		
		
	}
	
	

}
