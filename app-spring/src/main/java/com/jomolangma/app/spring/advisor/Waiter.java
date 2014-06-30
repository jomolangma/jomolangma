package com.jomolangma.app.spring.advisor;

public class Waiter implements WaiterInterface {

	public void greetTo(String name) {
		System.out.println("waiter greet to "+name+"...");
	}
	
	public void serveTo(String name){
		System.out.println("waiter serving "+name+"...");
	}
}
