package com.jomolangma.app.spring.advice;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAdviceStudy {
	
	static ApplicationContext ctx;
	
	@BeforeClass
	public static void setUp(){
		ctx = new ClassPathXmlApplicationContext("APPLICATION_ADVICE_BEAN_STUDY.xml");
	}
	
	@Test
	public void testMethodBeforeAdice(){
		Waiter waiter = (Waiter)ctx.getBean("waiter1");
		waiter.greetTo("John");
	}
}