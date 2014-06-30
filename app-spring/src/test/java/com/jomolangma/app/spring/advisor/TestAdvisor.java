package com.jomolangma.app.spring.advisor;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jomolangma.app.spring.introduce.ForumService;
import com.jomolangma.app.spring.introduce.Monitorable;

public class TestAdvisor {

	static ApplicationContext ctx;
	
	@BeforeClass
	public static void setUp(){
		ctx = new ClassPathXmlApplicationContext("APPLICATION_ADVISOR_BEAN.xml");
	}
	
	@Test
	public void testStaticMethodAdvisor() {
		Waiter waiter = (Waiter) ctx.getBean("waiter");
		Seller seller = (Seller) ctx.getBean("seller");
		waiter.greetTo("John");
		waiter.serveTo("John");
		seller.greetTo("John");
	}
	
	@Test
	public void testRegexpMethodPointcutAdvisor() {
		Waiter waiter = (Waiter) ctx.getBean("waiter1");
		waiter.greetTo("John");
		waiter.serveTo("John");
	}
	
	@Test
	public void testDynamicMethodMatcherPointcutAdvisor() {
		Waiter waiter = (Waiter) ctx.getBean("waiter2");
		waiter.serveTo("Peter");
		waiter.greetTo("Peter");		
		waiter.serveTo("Peter");
		waiter.greetTo("John");
	}
	
	@Test
	public void testControlFlowAdvisor() {
		Waiter waiter = (Waiter) ctx.getBean("waiter3");
		WaiterDelegate wd = new WaiterDelegate();
        wd.setWaiter(waiter);
		waiter.serveTo("Peter");
		waiter.greetTo("Peter");
		wd.service("Peter");
	}
	
	@Test
	public void testComposableAdvisor() {
		Waiter waiter = (Waiter) ctx.getBean("waiter4");
		WaiterDelegate wd = new WaiterDelegate();
        wd.setWaiter(waiter);
		waiter.serveTo("Peter");
		waiter.greetTo("Peter");
		wd.service("Peter");
	}
	
	@Test
	public void testIntroduceAdvisor() {
        ForumService forumService = (ForumService)ctx.getBean("forumService");
        forumService.removeForum(10);
        Monitorable moniterable = (Monitorable)forumService;
        moniterable.setMonitorActive(true);
        forumService.removeForum(10);
	}
}
