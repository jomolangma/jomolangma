package com.jomolangma.app.spring.aspectj;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAspectJ {
	
	static ApplicationContext ctx;
	
	@BeforeClass
	public static void setUp(){
		ctx = new ClassPathXmlApplicationContext("APPLICATION_ASPECTJ_BEAN.xml");
	}
	
	@Test
	public void testAspectJ(){
		NaiveWaiter waiter = (NaiveWaiter)ctx.getBean("waiter");
		waiter.greetTo("John");
	}
}