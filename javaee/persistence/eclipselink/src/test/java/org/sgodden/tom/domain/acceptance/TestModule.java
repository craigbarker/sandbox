package org.sgodden.tom.domain.acceptance;

import org.sgodden.transaction.EntityManagerFactory;

import com.google.inject.AbstractModule;

public class TestModule extends AbstractModule {

	private EntityManagerFactory emf;
	
	public TestModule(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	protected void configure() {
		bind(EntityManagerFactory.class).toInstance(emf);
	}

}
