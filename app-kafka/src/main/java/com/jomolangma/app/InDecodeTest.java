package com.jomolangma.app;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class InDecodeTest {
	public static void main(String[] args) throws UnsupportedEncodingException{
		
		//return new String(request.getParameter(key).getBytes("ISO-8859-1"),"GBK");
		String encode = URLEncoder.encode("测试1","GBK") ;
		System.out.println(encode);

		String decode = URLDecoder.decode("%E6%B5%B7%E5%B0%94","utf8");
		System.out.println(decode);
	}

}
