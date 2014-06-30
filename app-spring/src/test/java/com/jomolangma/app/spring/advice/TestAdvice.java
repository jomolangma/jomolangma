package com.jomolangma.app.spring.advice;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAdvice {
	
	static ApplicationContext ctx;
	
	@BeforeClass
	public static void setUp(){
		ctx = new ClassPathXmlApplicationContext("APPLICATION_ADVICE_BEAN.xml");
	}
	
	@Test
	public void testMethodBeforeAdice(){
		Waiter waiter = (Waiter)ctx.getBean("waiter1");
		waiter.greetTo("John");
	}
	
	@Test
	public void testAfterReturningAdice(){
		Waiter waiter = (Waiter)ctx.getBean("waiter4");
		waiter.greetTo("John");
	}
	
	@Test
	public void testMethodInterceptor(){
		Waiter waiter = (Waiter)ctx.getBean("waiter3");
		waiter.greetTo("John");
	}
	
	@Test
	public void testThrowsAdvice() {
		ForumService forumService = (ForumService)ctx.getBean("forumService");
		
		try{
			forumService.removeForum(10);
		} catch (Exception e) {}		
		
		try{
			forumService.updateForum(new Forum());
		} catch (Exception e) {}			
	}
}