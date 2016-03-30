package com.jomolangma.app.spring.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean,DisposableBean {
	private String brand;
	private String color;
	private int maxSpeed;
	private String name;
	private BeanFactory beanFactory;
	private String beanName;

	public Car() {
		System.out.println("Call Car() Constructor");
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		System.out.println("Call setBrand() method");
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public String toString() {
		return "brand:" + brand + "/color:" + color + "/maxSpeed:"+ maxSpeed;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public void introduce(){
		System.out.println("introduce:"+this.toString());
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("Call BeanFactoryAware.setBeanFactory()");
		this.beanFactory = beanFactory;
	}

	public void setBeanName(String beanName) {
		System.out.println("Call BeanNameAware.setBeanName()");
		this.beanName = beanName;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("Call InitializingBean.afterPropertiesSet()");
	}

	public void destroy() throws Exception {
		System.out.println("Call DisposableBean.destory()");
	}

	public void myInit() {		
		System.out.println("Call myInit() set maxSpeed as 240");
		this.maxSpeed = 240;
	}

	public void myDestory() {
		System.out.println("Call myDestroy()");
	}	
}