package com.szz.study.inherit;

public class Test02{
	public static void main(String[] args) {
		Teacher t = new Teacher();
		Student s = new Student();
		if( t instanceof Person) {
			//s = (Student)t;
		}
	}
}

class Person{
	
}

class Teacher extends Person{
	
}

class Student extends Person{
	
}