package com.jomolangma.app.spring.aspect.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AscpectJTest {
	public static void main(String[] args) {
		String resourceFile = "APPLICATION_ASPECT_BEAN.xml";
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext(resourceFile);	
		NaiveWaiter waiter = (NaiveWaiter) ctx.getBean("waiter");
		waiter.greetTo("Jomolangma");
	}
}