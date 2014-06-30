package com.jomolangma.app.spring.advisor;

import org.springframework.aop.support.IntroductionInfoSupport;

import com.jomolangma.app.spring.introduce.Monitorable;

@SuppressWarnings("serial")
public class MyIntroduceInfo extends IntroductionInfoSupport{
   public MyIntroduceInfo(){
	   super();
	   super.publishedInterfaces.add(Monitorable.class);
   }
}
