package com.jomolangma.app.spring.context;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor{
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {		
		if(beanName.equals("car")){
			Car car = (Car)bean;
			if(car.getColor() == null){
				System.out.println("Call MyBeanPostProcessor.postProcessBeforeInitialization(), color is null, set the color as black");
				car.setColor("Black");
			}
		}
		return bean;
	}
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {		
		if(beanName.equals("car")){
			Car car = (Car)bean;
			if(car.getMaxSpeed() >= 200){
				System.out.println("Call MyBeanPostProcessor.postProcessAfterInitialization(), set the speed as 200");
				car.setMaxSpeed(200);
			}
		}
		return bean;
	}
}
