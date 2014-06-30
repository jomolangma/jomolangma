package com.jomolangma.app.restful.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class JAXBStudy {
	public static void main(String[] args) {
		Address address = new Address();
		address.setCity("hangzhou");
		address.setStreet("fengqing avenue");
		address.setZip("310000");

		Person person = new Person();
		person.setAddress(address);
		person.setFirstName("Lijun");
		person.setLastName("Zhang");
		person.setAge(30);

		JAXBContext ctx;
		try {
			ctx = JAXBContext.newInstance(Person.class);

			StringWriter writer = new StringWriter();
			ctx.createMarshaller().marshal(person, writer);
			
			String personString = writer.toString();
			
			System.out.println(personString);
			
			person = (Person) ctx.createUnmarshaller().unmarshal(new StringReader(personString));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
