package com.jomolangma.app.spring.advisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.support.DynamicMethodMatcherPointcut;

public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut {
	private static List<String> specialClientList = new ArrayList<String>();
	static {
		specialClientList.add("John");
		specialClientList.add("Tom");
	}
//	public ClassFilter getClassFilter() {
//		return new ClassFilter() {
//			public boolean matches(Class clazz) {
//				System.out.println("getClassFilter()"+clazz.getName());
//				return Waiter.class.isAssignableFrom(clazz);
//			}
//		};
//	}
//	public boolean matches(Method method, Class clazz) {
//		System.out.println("matches(method,clazz)"+clazz.getName()+"."+method.getName()");
//		return "greetTo".equals(method.getName());
//	}
	@SuppressWarnings("rawtypes")
	public boolean matches(Method method, Class clazz, Object[] args) {
		System.out.println("matches(method,clazz)"+clazz.getName()+"."+method.getName());
		String clientName = (String) args[0];
		return specialClientList.contains(clientName);
	}

}
