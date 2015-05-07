package com.jomolangma.app;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NumberFormatTest {
	public static void main(String[] args){
		
		System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
	}

}
