package com.jomolangma.app.performance.ch2.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class TestSingleton {
	
	public static class AccessSingletonThread extends Thread{
		long begintime;
		public AccessSingletonThread(long begintime){
			this.begintime=begintime;
		}
		@Override
		public void run(){
			System.out.println("try to get instance");
			for(int i=0;i<100000;i++)
				//Singleton.getInstance();
				LazySingleton.getInstance();
			System.out.println("spend:"+(System.currentTimeMillis()-begintime));	
		}
	}
	
	@Test
	public void testPerformance() throws InterruptedException{
		ExecutorService exe=Executors.newFixedThreadPool(5);
		long begintime=System.currentTimeMillis();
		exe.submit(new AccessSingletonThread(begintime));
		exe.submit(new AccessSingletonThread(begintime));
		exe.submit(new AccessSingletonThread(begintime));
		exe.submit(new AccessSingletonThread(begintime));
		exe.submit(new AccessSingletonThread(begintime));
		
		Thread.sleep(10000);
	}
	
	@Test
	public void testSingleton(){
		StaticSingleton.createString();
		StaticSingleton.getInstance();
	}

}
