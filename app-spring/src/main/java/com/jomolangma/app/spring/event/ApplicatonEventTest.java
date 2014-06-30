package com.jomolangma.app.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicatonEventTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String resourceFile = "APPLICATION_EVENT_BEAN.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(resourceFile);	
		MailSender mailSender = ctx.getBean(MailSender.class);
		mailSender.sendMail("test mail.");
	    System.out.println("done.");
	}
}
