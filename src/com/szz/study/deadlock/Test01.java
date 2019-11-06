package com.szz.study.deadlock;


/**
 * windows系统
 * 查看所有进程   jps
 * 查看指定进程的运行信息   jstack 端口号
 * 结束指定进程     taskkill /f /t /im 端口号
 * @author szz
 *
 */
public class Test01 {
	public static void main(String[] args) {
		DeadLock dl = new DeadLock();
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					dl.runRight();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		}).start();
		
		new Thread(()-> {
			try {
				dl.runLeft();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "Thread_Left").start();
		
		new Thread(() -> {
			try {
				dl.runRight();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} ,"Thread_Right").start();
		
		while(true);
		
	}

}


class DeadLock{
	private Object right = new Object();
	
	private Object left = new Object();
	
	public void runRight() throws Exception {
		synchronized (left) {
			Thread.sleep(2000);
			synchronized (right) {
				System.out.println("Right end...");
			}
		}
	}
	
	public void runLeft() throws Exception {
		synchronized (right) {
			Thread.sleep(2000);
			synchronized (left) {
				System.out.println("Left end...");
			}
		}
	}
		
}
