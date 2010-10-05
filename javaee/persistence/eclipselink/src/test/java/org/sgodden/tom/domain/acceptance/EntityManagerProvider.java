package org.sgodden.tom.domain.acceptance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.Provider;

public class EntityManagerProvider implements Provider<EntityManager> {

	private static EntityManagerFactory factory;
	static {
		factory = Persistence.createEntityManagerFactory("tom-domain-pu");
	}
	
	public EntityManager get() {
		return factory.createEntityManager();
	}

}
