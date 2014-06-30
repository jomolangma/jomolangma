package com.jomolangma.app.performance.ch2.proxy.dynamic;

import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;

import com.jomolangma.app.performance.ch2.proxy.IDBQuery;

public class FunctionMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		IDBQuery d=null;
		d=createJdkProxy();
		System.out.println(d.request());
		d=createCglibProxy();
		System.out.println(d.request());
	}
	
	public static IDBQuery createJdkProxy(){
		IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),  
	                new Class[] { IDBQuery.class }, new JdkDbQeuryHandler());  
	        return jdkProxy;  
	}
	
	public static IDBQuery createCglibProxy(){
        Enhancer enhancer = new Enhancer();  
        enhancer.setCallback(new CglibDbQueryInterceptor());  
        enhancer.setInterfaces(new Class[] { IDBQuery.class });  
        IDBQuery cglibProxy = (IDBQuery) enhancer.create();  
        return cglibProxy;  
	}
}
