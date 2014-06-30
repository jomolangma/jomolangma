package com.jomolangma.app.spring.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApplicationContextTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:APPLICATION_CONTEXT_BEAN.xml");
		Car car1 = ctx.getBean("car",Car.class);
		car1.setMaxSpeed(200);
		
		Boss boss = ctx.getBean("boss",Boss.class);
		System.out.println(boss.getCar());
		System.out.println(boss.getHourseList());
	}
}