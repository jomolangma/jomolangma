package com.jomolangma.app.performance.ch2.proxy.dynamic;

import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;

import com.jomolangma.app.performance.ch2.proxy.IDBQuery;

public class PerformanceMain {
	public static final int CIRCLE=30000000;
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		IDBQuery d=null;
		long begin=System.currentTimeMillis();
		d=createJdkProxy();
		System.out.println("createJdkProxy:"+(System.currentTimeMillis()-begin));
		System.out.println("JdkProxy class:"+d.getClass().getName());
		begin=System.currentTimeMillis();
		for(int i=0;i<CIRCLE;i++)
			d.request();
		System.out.println("callJdkProxy:"+(System.currentTimeMillis()-begin));

		begin=System.currentTimeMillis();
		d=createCglibProxy();
		System.out.println("createCglibProxy:"+(System.currentTimeMillis()-begin));
		System.out.println("CglibProxy class:"+d.getClass().getName());
		begin=System.currentTimeMillis();
		for(int i=0;i<CIRCLE;i++)
			d.request();
		System.out.println("callCglibProxy:"+(System.currentTimeMillis()-begin));
	}
	
	public static IDBQuery createJdkProxy(){
		IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(
				ClassLoader.getSystemClassLoader(),  
	            new Class[] { IDBQuery.class }, 
	            new JdkDbQeuryHandler());  
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
