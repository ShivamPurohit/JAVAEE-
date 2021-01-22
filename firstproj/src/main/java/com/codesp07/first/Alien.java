package com.codesp07.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Alien {
	
	@Autowired
	Laptop lap;
	
	public void code() {	
		lap.compile(); 
		System.out.println("I am Coding..");
	}
}
