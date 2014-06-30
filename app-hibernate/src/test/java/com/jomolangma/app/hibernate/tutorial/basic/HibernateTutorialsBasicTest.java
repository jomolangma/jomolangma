/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package com.jomolangma.app.hibernate.tutorial.basic;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Illustrates use of Hibernate native APIs.
 *
 * @author Steve Ebersole
 */
public class HibernateTutorialsBasicTest {
	private SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		// A SessionFactory is set up once for an application
		// configures settings from hibernate.cfg.xml
        sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	@After
	public void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}

	@Test
	public void testBasicUsage() {
		// create a couple of events...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save( new Event( "Our very first event!", new Date() ) );
		session.save( new Event( "A follow up event", new Date() ) );

		Person person = new Person();
		person.setAge(30);
		person.setFirstName("Lijun");
		person.setLastName("Zhang");
		session.save(person);
		
		session.getTransaction().commit();	
		session.close();

		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
        session.beginTransaction();
        
        Event event = (Event) session.load(Event.class, new Long(1));
        Person person1 = (Person) session.load(Person.class, new Long(1));
        person1.getEvents().add(event);
        
        session.getTransaction().commit();
        session.close();
	}
	
	@Test
	public void getAllEvents(){
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Event> list = session.createQuery("from Event").list();
		for (Event event:list){
			System.out.println("Event( " + event.getDate() + " ) " + event.getTitle());
		}
		
		session.getTransaction().commit();
		session.close();
	}
}
