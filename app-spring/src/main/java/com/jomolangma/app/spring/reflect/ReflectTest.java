package com.jomolangma.app.spring.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class ReflectTest {
  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Car  initByDefaultConst() throws Throwable
	{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();		
		Class clazz = loader.loadClass("com.jomolangma.app.spring.reflect.Car");
		
		Constructor cons = clazz.getDeclaredConstructor((Class[])null);
		Car car = (Car)cons.newInstance();
		
		Method setBrand = clazz.getMethod("setBrand",String.class);
		setBrand.invoke(car,"红旗CA72");
		Method setColor = clazz.getMethod("setColor",String.class);
		setColor.invoke(car,"黑色");
		Method setMaxSpeed = clazz.getMethod("setMaxSpeed",int.class);
		setMaxSpeed.invoke(car,200);	
		return car;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Car initByParamConst()  throws Throwable{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class clazz = loader.loadClass("com.jomolangma.app.spring.reflect.Car");
		Constructor cons = clazz.getDeclaredConstructor(new Class[]{String.class,String.class,int.class});
		Car car = (Car)cons.newInstance(new Object[]{"红旗CA72","黑色",200});
		return car;	
	}
	
	public static void main(String[] args) throws Throwable {
		Car car1 = initByDefaultConst();
		Car car2 = initByParamConst();
		car1.introduce();
		car2.introduce();
	}
}
