package com.jomolangma.app.ioc.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jomolangma.app.ioc.XmlBeanFactory;
import com.jomolangma.app.ioc.domain.Address;
import com.jomolangma.app.ioc.domain.Person;
import com.jomolangma.app.ioc.domain.User;

public class NormalJunitTest {

	@Test
	public void normalTest(){
		Person person = new Person();
		person.setName("zhanglijun");
		
		Address address = new Address();
		address.setNationality("China");
		address.setCity("Hangzhou");
		address.setStreet("Fengqing Avenue");
		
		person.setAddress(address);
		
		assertEquals("Hangzhou",person.getAddress().getCity());
	}
	
	@Test
	public void iocTest(){
		String filePath = "E:/Workspace/Jomolangma/app-misc/src/main/java/com/jomolangma/app/ioc/domain/ioc.xml";
		
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(filePath);
		
		Person person = (Person) xmlBeanFactory.getBean("person");
		assertEquals("Hangzhou",person.getAddress().getCity());
	}
	
	@Test
	public void iocTest2(){
		String filePath = "E:/Workspace/Jomolangma/app-misc/src/main/java/*/**/app/ioc/domain/ioc*.xml";
		
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(filePath);
		
		User user = (User) xmlBeanFactory.getBean("user");
		assertEquals("ZhangLijun",user.getName());
	}
}
