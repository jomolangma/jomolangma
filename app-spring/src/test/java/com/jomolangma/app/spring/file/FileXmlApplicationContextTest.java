package com.jomolangma.app.spring.file;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.jomolangma.app.spring.context.Boss;
import com.jomolangma.app.spring.context.Car;

public class FileXmlApplicationContextTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("E:/Workspace/Jomolangma/app-spring/src/main/resources/APPLICATION_CONTEXT_BEAN.xml");
		Car car1 = ctx.getBean("car",Car.class);
		car1.setMaxSpeed(200);
		
		Boss boss = ctx.getBean("boss",Boss.class);
		System.out.println(boss.getCar());
		System.out.println(boss.getHourseList());
	}
}