package org.sgodden.transaction;

import javax.persistence.EntityManager;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

public class TransactionInterceptorImpl implements TransactionInterceptor {

	private static final transient Logger LOG = LoggerFactory
			.getLogger(TransactionInterceptorImpl.class);

	@Inject
	private EntityManagerFactory factory;

	private static ThreadLocal<EntityManager> em = new ThreadLocal<EntityManager>();

	public Object invoke(MethodInvocation arg0) throws Throwable {
		Object ret = null;

		boolean iStartedTheTransaction = false;

		if (em.get() == null) {
			LOG.debug("Creating an entity manager and beginning the transaction");
			em.set(factory.create());
			em.get().getTransaction().begin();
			iStartedTheTransaction = true;
		}

		try {
			ret = arg0.proceed();
			if (iStartedTheTransaction) {
				em.get().getTransaction().commit();
				LOG.debug("Committed the transaction");
			}
		} catch (RuntimeException e) {
			if (iStartedTheTransaction) {
				LOG.error("Runtime exception occurred, rolling back the transaction");
				em.get().getTransaction().rollback();
			}
			throw e;
		} finally {
			if (iStartedTheTransaction) {
				em.get().close();
				LOG.debug("Closed the entity manager");
				em.set(null);
			}
		}

		return ret;
	}

	public EntityManager getTransactedEntityManager() {
		return em.get();
	}

}
