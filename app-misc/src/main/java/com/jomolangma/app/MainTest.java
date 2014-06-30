package com.jomolangma.app;

public class MainTest {

	public static String destString(int dividend,int divisor){
		if(divisor==0){
			return "100%";
		}
		
		Double result = Math.round(dividend*1.0/divisor*10000)*1.0/100;
		
		if (result == result.intValue())
				System.out.println(result.intValue());
		
		return String.valueOf(dividend) + '(' + result + '%' + ')';
	}
	
	
	public static void qiuyuTest(){
		System.out.println(0%2);
		System.out.println(1%2);
		System.out.println(2%2);
		System.out.println(3%2);
	}
	
	public static void main(String[] args) {
		//System.out.println(destString(15,51));
		qiuyuTest();
	}

}
