package com.jomolangma.app.spring.autoproxy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jomolangma.app.spring.advisor.Seller;
import com.jomolangma.app.spring.advisor.Waiter;

public class TestBeanNameAutoProxyCreator {
	
	@Test
	public void testBeanNameAutoProxyCreator() {
		String configPath = "APPLICATION_AUTOPROXY_BEAN.xml";
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		Waiter waiter = (Waiter) ctx.getBean("waiter");
		Seller seller = (Seller) ctx.getBean("seller");
		waiter.serveTo("John");
		waiter.greetTo("John");
		seller.greetTo("Tom");
	}
}
