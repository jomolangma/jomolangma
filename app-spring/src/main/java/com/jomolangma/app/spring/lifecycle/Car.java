package com.jomolangma.app.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@SuppressWarnings("unused")
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean,
		DisposableBean {
	private String brand;
	private String color;
	private int maxSpeed;
	private String name;
	private BeanFactory beanFactory;
	private String beanName;

	public Car() {
		System.out.println("Car的构造函数");
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		System.out.println("setBrand");
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
		System.out.println("BeanFactoryAware.setBeanFactory()");
		this.beanFactory = beanFactory;
	}

	public void setBeanName(String beanName) {
		System.out.println("BeanNameAware.setBeanName()");
		this.beanName = beanName;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean.afterPropertiesSet()");
	}

	public void destroy() throws Exception {
		System.out.println("DisposableBean.destory()");
	}

	public void myInit() {		
		System.out.println("Init() maxSpeed");
		this.maxSpeed = 240;
	}

	public void myDestory() {
		System.out.println("myDestroy()");
	}
	
}
