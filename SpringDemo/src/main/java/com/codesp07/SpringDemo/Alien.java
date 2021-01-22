package com.codesp07.SpringDemo;

public class Alien {
	private int age;
	private Computer com; //reference variable
	
	
	

	public Computer getCom() {
		return com;
	}

	public void setCom(Computer com) {
		this.com = com;
	}

	public Alien(){
		System.out.println("Alien Object Created");
	}
	
	public void code() {
		System.out.println("I am Coding..");
		com.compile();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


}

