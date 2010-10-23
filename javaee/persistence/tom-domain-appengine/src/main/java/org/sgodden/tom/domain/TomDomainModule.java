package org.sgodden.tom.domain;

import javax.persistence.EntityManager;

import org.sgodden.tom.jpa.EntityManagerProviderImpl;
import org.sgodden.transaction.TransactionInterceptor;
import org.sgodden.transaction.TransactionInterceptorImpl;
import org.sgodden.transaction.Transactional;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class TomDomainModule extends AbstractModule {

	@Override
	protected void configure() {
		TransactionInterceptor txInterceptor = new TransactionInterceptorImpl();
		requestInjection(txInterceptor);
		bind(TransactionInterceptor.class).toInstance(txInterceptor);

		bindInterceptor(Matchers.any(), Matchers
				.annotatedWith(Transactional.class), txInterceptor);

		bind(EntityManager.class).toProvider(EntityManagerProviderImpl.class);

		bind(CustomerOrderRepository.class);
	}

}
