package com.szz.study.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author szz
 * jps
 * jstack 进程号
 *taskkill /f /t /im 进程号
 */
public class Test02 {
	public static void main(String[] args) {
		String lockA = "lockA";
		String lockB = "lockB";
		
		new Thread(()->{new Dead_Lock(lockA, lockB).execute_deadLock();}).start();
		new Thread(()->{new Dead_Lock(lockB, lockA).execute_deadLock();}).start();
	}
	
}


class Dead_Lock {
	private String lockA;
	private String lockB;
	
	public Dead_Lock(String lockA, String lockB) {
		super();
		this.lockA = lockA;
		this.lockB = lockB;
	}

	public void execute_deadLock() {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName() + "\t持有" + lockA + "\t期望" + lockB);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized(lockB) {
				System.out.println(Thread.currentThread().getName() + "\t持有" + lockB + "\t期望" + lockA);
			}
		}
	}
	
}
