package com.jomolangma.app.spring.event;

import org.springframework.context.ApplicationListener;

public class MailSendListener implements ApplicationListener<MailSendEvent>{

	public void onApplicationEvent(MailSendEvent event) {
			MailSendEvent mse = (MailSendEvent) event;
			System.out.println("MailSendListener:向" + mse.getTo() + "发送一封邮件");
	}
}
