package com.jomolangma.app.spring.resource;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


public class FileSourceExample {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			String filePath = "E:/Workspace/jomolangma/app-spring/src/main/resources/log4j.properties";
			Resource res1 = new FileSystemResource(filePath);
			
			System.out.println(System.getProperty("java.class.path"));
			
            Resource res2 = new ClassPathResource("log4j.properties");
            
            InputStream ins1 = res1.getInputStream();
            InputStream ins2 = res2.getInputStream();
            
            System.out.println("res1:"+res1.getFilename());
            System.out.println("res2:"+res2.getFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
