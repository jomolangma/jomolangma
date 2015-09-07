package com.jomolangma.app;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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
		//qiuyuTest();
//		DecimalFormat df = new DecimalFormat("0.000");
//		Double totalWeight = 0.0;
//		Double weight = 0.0;
//
//		totalWeight += Double.valueOf(4);
//		weight = weight + Double.valueOf(4);
//
//		totalWeight += Double.valueOf(4);
//		weight = weight + Double.valueOf(4);
//
//		totalWeight += Double.valueOf(4);
//
//
//		Double weightResult = weight/totalWeight;
//
//		System.out.println(df.format(weightResult));

//		System.out.println(System.getProperty("user.dir"));

		System.out.println(System.getProperty("java.version"));

        System.out.println(System.currentTimeMillis());

        //Date date = new Date();

        //MessageFormat mf = new MessageFormat(msg);
        //MessageFormat mf = new MessageFormat("{0}:{1}:{2}:{3}");

        //System.out.print(String.format("%s:%s:%s:%s", "1", "1234-5678", 100L, "201503151210"));

        //System.out.print(mf.format(new Object[]{"1","1234-5678", 100L, "201503151210"}));

//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.DAY_OF_MONTH, -1);
//
//        System.out.println(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()));

//        String cityStr = "";
//        String[] cityArray = cityStr.split(":");
//        System.out.println(cityArray[0]);

//		int a=16;
//		int c=-16;
//		int b=2;
//		int d=2;
//		System.out.println("a 的移位结果："+(a>>b));
//		System.out.println("c 的移位结果："+(c>>d));

//		String value = "1234:5255:fwef-fwe:1427773558115";
//		System.out.println(value.substring(value.lastIndexOf(":")+1));

		Date date1 = new Date(115,2,28,12,12,12);
		long date1M = date1.getTime();
		Date date2 = new Date(115,2,31,12,12,12);
		long date2M = date2.getTime();

		long expiredDaysMilliSeconds = 2*24*60*60*1000;
		System.out.println(expiredDaysMilliSeconds);

		if ((date1M + expiredDaysMilliSeconds) < System.currentTimeMillis()){
			System.out.println("date1 true");
		}

		if ((date2M + expiredDaysMilliSeconds) < System.currentTimeMillis()){
			System.out.println("date2 true");
		}
	}

}
