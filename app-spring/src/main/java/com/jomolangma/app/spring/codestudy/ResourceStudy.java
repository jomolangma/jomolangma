package com.jomolangma.app.spring.codestudy;

import java.io.IOException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceStudy {
	
	public static void main(String[] args) throws IOException{
		String path = "classpath:APPLICATION_BEANFACTORY_BEAN.xml";
		ResourceLoader loader = new DefaultResourceLoader();
		Resource res = loader.getResource(path);
		
		System.out.println(res);
	}

}
