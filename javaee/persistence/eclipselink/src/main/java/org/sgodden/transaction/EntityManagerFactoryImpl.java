package org.sgodden.transaction;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactoryImpl implements EntityManagerFactory {
	
	private javax.persistence.EntityManagerFactory jpaFactory;
	
	public EntityManagerFactoryImpl(String puName) {
		jpaFactory = Persistence.createEntityManagerFactory(puName);
	}

	public EntityManager create() {
		return jpaFactory.createEntityManager();
	}

	public javax.persistence.EntityManagerFactory getJpaEntityManagerFactory() {
		return jpaFactory;
	}

}
