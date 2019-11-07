package com.szz.study.designpatterns.singleton;

public class TestDCL {
	
	public static void main(String[] args) {
		
	}
	
	
}

class Dcl{
	
	private volatile static Dcl instance = null;
	
	private Dcl() {
		
	}
	
	
	public static Dcl getInstance() {
		if(instance == null) {
			synchronized (Dcl.class) {
				if (instance == null) {
					instance = new Dcl();
				}
			}
		}
		return instance;
	} 
	
	
}
