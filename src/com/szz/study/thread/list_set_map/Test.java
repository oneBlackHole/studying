package com.szz.study.thread.list_set_map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合多线程并发异常
 * concurrentModifyException
 * 
 * 解决方法
 * 第1种：集合工具类 ࣺCollections
 * 第2种：
 * juc包原子引用类
 * Java.util.concurrent
 * @author szz
 *
 */
public class Test {
	
	public static void main(String[] args) {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		for (int i = 0; i < 3; i++) {
			new Thread(()->{
				map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
				System.out.println(map);
			},String.valueOf(i)).start();
		}
	}
	
	public static void mapSafe1() {
		Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
		for (int i = 0; i < 3; i++) {
			new Thread(()->{
				map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
				System.out.println(map);
			}, String.valueOf(i)).start();
		}
	}
	
	public static void setSafe2() {
		Set<String> set = new CopyOnWriteArraySet<String>();
		for (int i = 0; i < 3; i++) {
			new Thread(()->{
				set.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(set);
			},String.valueOf(i)).start();
		}
	}
	
	public static void setSafe1() {
		Set<String> set = Collections.synchronizedSet(new HashSet<String>());
		for (int i = 0; i < 3; i++) {
			new Thread(()->{
				set.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(set);
			}, String.valueOf(i)).start();
		}
		
	}
	
	public static void listSafe2() {
		List<String> list = new CopyOnWriteArrayList<String>();
		
		for (int i = 0; i < 3; i++) {
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
			}, String.valueOf(i)).start();
		}
		
	}
	
	public static void listSafe1() {
		
		List<String> list = Collections.synchronizedList(new ArrayList<String>());
		
		for (int i = 0; i < 3; i++) {
			new Thread(()-> {
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
			},String.valueOf(i)).start();
		}
		
	}
	
	
	
	
	
	
	
	
	
}
