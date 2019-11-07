package com.szz.study.inherit;

public class Test01 {
	
	public static void main(String[] args) {
		Super sooper = new Super();
		//Sub sub = new Sub();
		Super sub = new Sub();
		System.out.println(sooper.getLength() + "," + sub.getLength());
	}
	
	
}


class Super{
	
	public int getLength() {
		return 4;
	}
	
}


class Sub extends Super{
	//´úÂë±¨´í
//	public long getLength() {
//		return 5;
//	}
	
}