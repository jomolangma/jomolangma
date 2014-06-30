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
package com.jomolangma.app.hibernate.tutorial.envers;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 *
 * @author Steve Ebersole
 */
public class EnversManagerIllustrationTest{
	private static EntityManagerFactory entityManagerFactory;

	@BeforeClass
	public static void init() throws Exception {
		// like discussed with regards to SessionFactory, an EntityManagerFactory is set up once for an application
		// 		IMPORTANT: notice how the name here matches the name we gave the persistence-unit in persistence.xml!
		entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
	}

	@AfterClass
	public static void destroy() throws Exception {
		entityManagerFactory.close();
	}

	@Test
	public void testBasicUsage() {
		// create a couple of events
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist( new EnversEvent( "Our very first event!", new Date() ) );
		entityManager.persist( new EnversEvent( "A follow up event", new Date() ) );
		entityManager.getTransaction().commit();
		entityManager.close();

		// now lets pull events from the database and list them
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
        List<EnversEvent> result = entityManager.createQuery( "from EnversEvent", EnversEvent.class ).getResultList();
		for ( EnversEvent event : result ) {
			System.out.println( "Event (" + event.getDate() + ") : " + event.getTitle() );
		}
        entityManager.getTransaction().commit();
        entityManager.close();

		// so far the code is the same as we have seen in previous tutorials.  Now lets leverage Envers...

		// first lets create some revisions
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		EnversEvent myEvent = entityManager.find( EnversEvent.class, 2L ); // we are using the increment generator, so we know 2 is a valid id
		myEvent.setDate( new Date() );
		myEvent.setTitle( myEvent.getTitle() + " (rescheduled)" );
        entityManager.getTransaction().commit();
        entityManager.close();

		// and then use an AuditReader to look back through history
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		myEvent = entityManager.find( EnversEvent.class, 2L );
		Assert.assertEquals( "A follow up event (rescheduled)", myEvent.getTitle() );
		AuditReader reader = AuditReaderFactory.get( entityManager );
		EnversEvent firstRevision = reader.find( EnversEvent.class, 2L, 1 );
		Assert.assertFalse( firstRevision.getTitle().equals( myEvent.getTitle() ) );
		Assert.assertFalse( firstRevision.getDate().equals( myEvent.getDate() ) );
		EnversEvent secondRevision = reader.find( EnversEvent.class, 2L, 2 );
		Assert.assertTrue( secondRevision.getTitle().equals( myEvent.getTitle() ) );
		Assert.assertTrue( secondRevision.getDate().equals( myEvent.getDate() ) );
		entityManager.getTransaction().commit();
        entityManager.close();
	}
}
