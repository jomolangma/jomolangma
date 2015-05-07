package com.jomolangma.app;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
@SuppressWarnings("restriction")
public class Base64EncodeDecode {
	
	public static void main(String[] args) throws Base64DecodingException{
		System.out.println(new String(Base64.encode("eoims".getBytes())));
		System.out.println(new String(Base64.encode("eoims@2011".getBytes())));
		//System.out.println(new String(Base64.decode("cG9zdGdyZXM=")));
		//System.out.println(new String(Base64.decode("cG9zdGdyZVNRTEAyMDEx")));
		
		
	}

}
