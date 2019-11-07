package com.szz.study.thread.readWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 读写锁测试类
 * @author szz
 *
 */
public class Test {
	
	public static void main(String[] args) {
		MyCache myCache = new MyCache();
		for (int i = 0; i < 5; i++) {
			final int tempInt = i; 
			new Thread(()->{
				myCache.put(tempInt + "", tempInt + "");
			},String.valueOf(i)).start();
		}
		
		for (int i = 0; i < 5; i++) {
			final int tempInt = i;
			new Thread(()->{
				myCache.get(tempInt + "");
			}, String.valueOf(i)).start();
		}
		
	}
}

//资源类
class  MyCache{
	
	private volatile Map<String, Object> map = new HashMap<String, Object>();
	
	//lock锁：读，写操作一起锁定，降低了并发量，保证了数据一致性
	//private Lock lock = new ReentrantLock();
	
	//readWriteLock：读写分离锁
	private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
	
	
	public void put(String key, Object value) {
		rwlock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
			map.put(key, value);
			TimeUnit.MILLISECONDS.sleep(300);
			System.out.println(Thread.currentThread().getName() + "\t 正在写入完成");
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			rwlock.writeLock().unlock();
			
		}
		
	}
	
	public void get(String key) {
		rwlock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t 正在读出");
			TimeUnit.MILLISECONDS.sleep(300);
			Object result = map.get(key);
			System.out.println(Thread.currentThread().getName() + "\t 正在读出完成:" + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			rwlock.readLock().unlock();
		}
		
		
		
		
	}
	
	public void clean() {
		map.clear();
	}
	
	
}
