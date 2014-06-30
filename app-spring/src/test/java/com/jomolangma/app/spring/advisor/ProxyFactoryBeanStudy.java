package com.jomolangma.app.spring.advisor;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyFactoryBeanStudy {

	static ApplicationContext ctx;
	
	@BeforeClass
	public static void setUp(){
		ctx = new ClassPathXmlApplicationContext("APPLICATION_ADVISOR_BEAN_STUDY.xml");
	}
	
	@Test
	public void testStaticMethodAdvisor() {
		WaiterInterface waiter = (WaiterInterface) ctx.getBean("waiter");
		Seller seller = (Seller) ctx.getBean("seller");
		waiter.greetTo("John");
		waiter.serveTo("John");
		seller.greetTo("John");
	}
}
