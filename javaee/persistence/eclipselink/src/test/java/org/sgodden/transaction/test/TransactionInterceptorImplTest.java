package org.sgodden.transaction.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.sgodden.transaction.EntityManagerFactory;
import org.sgodden.transaction.TransactionInterceptor;
import org.sgodden.transaction.TransactionInterceptorImpl;
import org.sgodden.transaction.Transactional;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;

import static org.mockito.Mockito.*;

@Test
public class TransactionInterceptorImplTest {
	
	public void testStartTransaction() {
		/*
		 * Create mock entity manager factory which returns a mock
		 * entity manager when asked.
		 */
		EntityManagerFactory emf = mock(EntityManagerFactory.class);
		EntityManager em = mock(EntityManager.class);
		when (emf.create()).thenReturn(em);
		EntityTransaction tx = mock(EntityTransaction.class);
		when(em.getTransaction()).thenReturn(tx);
		
		Injector injector = Guice.createInjector(new TestModule(emf, false));
		OuterTransactedClass outer = injector.getInstance(OuterTransactedClass.class);
		outer.doSomethingInATransaction();
		
		// emf.create should only have been called once.
		verify(emf).create();
		verify(tx).begin();
		verify(tx).commit();
		verify(em).close();
	}
	
	/**
	 * Ensures that transactions are rolled back when a runtime
	 * exception occurs.
	 */
	public void testTransactionRolledBackOnRuntimeException() {
		/*
		 * Create mock entity manager factory which returns a mock
		 * entity manager when asked.
		 */
		EntityManagerFactory emf = mock(EntityManagerFactory.class);
		EntityManager em = mock(EntityManager.class);
		when (emf.create()).thenReturn(em);
		EntityTransaction tx = mock(EntityTransaction.class);
		when(em.getTransaction()).thenReturn(tx);
		
		Injector injector = Guice.createInjector(new TestModule(emf, true));
		OuterTransactedClass outer = injector.getInstance(OuterTransactedClass.class);
		try {
			outer.doSomethingInATransaction();
			fail("A runtime exception should have been thrown");
		} catch(RuntimeException ignored) {
		}
		
		// emf.create should only have been called once.
		verify(emf).create();
		verify(tx).begin();
		verify(tx).rollback();
		verify(em).close();
	}
	
	public static class TestModule extends AbstractModule {
		
		private EntityManagerFactory emf;
		private boolean causeException;
		
		public TestModule(EntityManagerFactory emf, boolean causeException) {
			this.emf = emf;
			this.causeException = causeException;
		}

		@Override
		protected void configure() {
			
			bind(EntityManagerFactory.class).toInstance(emf);
			
			TransactionInterceptor txInterceptor = new TransactionInterceptorImpl();
			requestInjection(txInterceptor);
			bind(TransactionInterceptor.class).toInstance(txInterceptor);
			bindInterceptor(Matchers.any(), Matchers
					.annotatedWith(Transactional.class), txInterceptor);

			bind(OuterTransactedClass.class);
			if (causeException) {
				bind(NestedTransactedClass.class).to(NestedTransactedClassWhichThrowsRuntimeExceptionImpl.class);
			} else {
				bind(NestedTransactedClass.class).to(NestedTransactedClassImpl.class);
			}
		}
	}
	
	@Singleton
	public static class OuterTransactedClass {
		
		@Inject
		private NestedTransactedClass nested;
		
		@Transactional
		public void doSomethingInATransaction() {
			nested.doSomethingElseInATransaction();
		}
		
	}
	
	public static interface NestedTransactedClass {
		public void doSomethingElseInATransaction();
	}
	
	@Singleton
	public static class NestedTransactedClassImpl implements NestedTransactedClass {
		
		@Transactional
		public void doSomethingElseInATransaction() {
		}
		
	}

	@Singleton
	public static class NestedTransactedClassWhichThrowsRuntimeExceptionImpl implements NestedTransactedClass {

		@Transactional
		public void doSomethingElseInATransaction() {
			throw new RuntimeException("An error occurred");
		}
		
	}

}
