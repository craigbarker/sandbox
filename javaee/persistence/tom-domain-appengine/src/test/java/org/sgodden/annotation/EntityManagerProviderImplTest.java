package org.sgodden.annotation;

import javax.persistence.EntityManager;

import org.sgodden.tom.jpa.EntityManagerFactory;
import org.sgodden.tom.jpa.EntityManagerProviderImpl;
import org.sgodden.transaction.TransactionInterceptor;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;

@Test(groups = {"unit"})
public class EntityManagerProviderImplTest {
	
	/**
	 * Ensures that if a transacted entity manager is available,
	 * it is used.
	 */
	public void testGetTransactedEntityManagerIfAvailable() {
		EntityManager transactedEntityManager = mock(EntityManager.class);
		TransactionInterceptor interceptor = mock(TransactionInterceptor.class);
		when(interceptor.getTransactedEntityManager()).thenReturn(transactedEntityManager);
		
		EntityManagerProviderImpl p = new EntityManagerProviderImpl(interceptor, null);
		
		assertEquals(p.get(), transactedEntityManager, "Wrong entity manager");
	}
	
	public void testGetNewEntityManagerIfTransactedNotAvailable() {
		TransactionInterceptor interceptor = mock(TransactionInterceptor.class);
		when(interceptor.getTransactedEntityManager()).thenReturn(null);
		
		EntityManager nonTransactedEntityManager = mock(EntityManager.class);
		
		EntityManagerFactory factory = mock(EntityManagerFactory.class);
		when(factory.create()).thenReturn(nonTransactedEntityManager);
		
		EntityManagerProviderImpl p = new EntityManagerProviderImpl(interceptor, factory);
		
		assertEquals(p.get(), nonTransactedEntityManager, "Wrong entity manager");
	}

}