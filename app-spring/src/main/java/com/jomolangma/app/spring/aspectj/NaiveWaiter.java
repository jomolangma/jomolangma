package com.jomolangma.app.spring.aspectj;

public class NaiveWaiter {

	public void greetTo(String name) {
		System.out.println("greet to "+name+"...");
	}
	
	public void serveTo(String name){
		System.out.println("serving "+name+"...");
	}
}
