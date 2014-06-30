package com.jomolangma.app.spring.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class TestForumService {

	@Test
	public void testJDKDynamicProxy() {
		ForumService target = new ForumServiceImpl();
		PerformaceHandler handler = new PerformaceHandler(target);
		ForumService proxy = (ForumService) Proxy.newProxyInstance(target
				.getClass().getClassLoader(),
				target.getClass().getInterfaces(), handler);
		proxy.removeForum(10);
		proxy.removeTopic(1012);
	}
	
	@Test
	public void testCglibProxy() {
		CglibProxy cglibProxy = new CglibProxy();
		ForumService forumService = (ForumService) cglibProxy
				.getProxy(ForumServiceImpl.class);
		forumService.removeForum(10);
		forumService.removeTopic(1023);
	}
}
