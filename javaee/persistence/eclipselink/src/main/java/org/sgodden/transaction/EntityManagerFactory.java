package org.sgodden.transaction;

import javax.persistence.EntityManager;

public interface EntityManagerFactory {
	
	public EntityManager create();
	
	public javax.persistence.EntityManagerFactory getJpaEntityManagerFactory();

}
