package com.jomolangma.app.gc;

public class MinorGCTest {
	
	private static final int _1MB = 1024*1024;

	/**
	 * VM Parameters:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
	 */
	
	@SuppressWarnings("unused")
	public static void testAllocation(){
		byte[] allocation1,allocation2,allocation3,allocation4;
		
		allocation1 = new byte[2*_1MB];
		allocation2 = new byte[2*_1MB];
		allocation3 = new byte[2*_1MB];
		allocation4 = new byte[4*_1MB];
	}
	
	/**
	 * VM Parameters:-Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
	 * -XX:PretenureSizeThreshold=3145728
	 * @param args
	 */
	
	@SuppressWarnings("unused")
	public static void testPretenureSizeThreshold(){
		byte[] allocation;
		allocation = new byte[4*_1MB];
	}
	
	public static void main(String[] args){
		MinorGCTest.testPretenureSizeThreshold();
	}
}
