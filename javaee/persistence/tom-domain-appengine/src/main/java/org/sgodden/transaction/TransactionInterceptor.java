package org.sgodden.transaction;

import javax.persistence.EntityManager;

import org.aopalliance.intercept.MethodInterceptor;

public interface TransactionInterceptor extends MethodInterceptor {
	
	public EntityManager getTransactedEntityManager();

}
