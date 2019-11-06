package com.szz.study.designpatterns.singleton;

public class Singleton {
	
	private static volatile Singleton instance = null;
	
	private Singleton() {
		
	}
	
	public static synchronized Singleton getInstance() {
		if(instance == null) {
			return new Singleton();
		}else {
			return instance;
		}
	} 
	
	
	
	
}


class HungrySingleton{
	
	private static final HungrySingleton instance = new HungrySingleton(); 
	
	private HungrySingleton() {
		
	}
	
	public static HungrySingleton getInstance() {
		return instance;
	}
	
}

//Ë«ÖØÐ£ÑéËø
class DoubleLock{
	
	private static volatile DoubleLock instance = null;
	
	private DoubleLock() {
		
	}
	
	public static DoubleLock getInstance() {
		if( instance == null) {
			synchronized (DoubleLock.class) {
				if(instance == null) {
					instance = new DoubleLock();
				}
			}
		}
		return instance;
		
	}
	
	
	
}


