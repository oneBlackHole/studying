package com.szz.study.thread.count_down_latch;

import java.util.concurrent.CountDownLatch;


public class Test02 {
	public static void main(String[] args) {
		int num = 6;
		CountDownLatch countDownLatch = new CountDownLatch(num);
		
		for (int i = 1; i <= 6; i++) {
			new Thread(()->{
				System.out.println(Thread.currentThread().getName() +"\t 被灭");
				countDownLatch.countDown();
			}, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
		}
		
		try {
			countDownLatch.await();
			System.out.println(Thread.currentThread().getName() + "\t 秦帝国一统天下");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


enum CountryEnum{
	ONE(1,"韩国"),
	TWO(2,"赵国"),
	THREE(3,"魏国"),
	FOUR(4,"楚国"),
	FIVE(5,"燕国"),
	SIX(6,"齐国");
	private Integer retCode;
	private  String retMessage;
	private CountryEnum(Integer retCode, String retMessage) {
		this.retCode = retCode;
		this.retMessage = retMessage;
	}
	
	public static CountryEnum forEach_CountryEnum(int index) {
		CountryEnum[] countryEnums = CountryEnum.values();
		for (CountryEnum countryEnum : countryEnums) {
			if(index == countryEnum.getRetCode()) {
				return countryEnum;
			}
		}
		return null;
	}
	
	
	
	public Integer getRetCode() {
		return retCode;
	}
	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}
	public String getRetMessage() {
		return retMessage;
	}
	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}
	
	
	
}