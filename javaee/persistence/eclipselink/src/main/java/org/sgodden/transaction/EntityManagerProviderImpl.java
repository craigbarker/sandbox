package org.sgodden.transaction;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class EntityManagerProviderImpl implements Provider<EntityManager> {
	
	private static final Logger LOG = LoggerFactory.getLogger(EntityManagerProviderImpl.class);

	private TransactionInterceptor txInterceptor;
	private EntityManagerFactory emFactory;
	
	@Inject
	public EntityManagerProviderImpl(TransactionInterceptor txInterceptor,
			EntityManagerFactory emFactory) {
		this.txInterceptor = txInterceptor;
		this.emFactory = emFactory;
	}

	public EntityManager get() {
		if (txInterceptor.getTransactedEntityManager() != null) {
			LOG.debug("Returning entity manager from current transaction");
			return txInterceptor.getTransactedEntityManager();
		} else {
			LOG.debug("Returning a new entity manager from the factory");
			return emFactory.create();
		}
	}

}
