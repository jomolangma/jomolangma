package com.jomolangma.app;

public class ZfgjjCalc {
	public static final double monthRate = 0.045/12;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double result = 0.0;
		
		result = (500000*monthRate*Math.pow(1+monthRate, 360))/(Math.pow(1+monthRate, 360) - 1);
		
		System.out.println(result);
	}

}
