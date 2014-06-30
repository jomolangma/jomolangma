package com.jomolangma.app.spring.editor;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEditor {
	
	static ApplicationContext ctx;
	
	@BeforeClass
	public static void setUp(){
		ctx = new ClassPathXmlApplicationContext("APPLICATION_EDITOR_BEAN.xml");
	}
	
	@Test
	public void testEditor(){
		Boss boss = (Boss)ctx.getBean("boss");
	    System.out.println(boss);
	}
}