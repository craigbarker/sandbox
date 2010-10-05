package org.sgodden.tom.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.sgodden.transaction.Transactional;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class CustomerOrderRepositoryImpl implements CustomerOrderRepository {

	@Inject
	private Provider<EntityManager> em;

	@Transactional
	public void persist(CustomerOrder order) {
		em.get().persist(order);
	}

	@Transactional
	public CustomerOrder findById(long id) {
		return em.get().find(CustomerOrder.class, id);
	}

	public long count() {
		Query q = em.get().createQuery("select count(o) from CustomerOrder o");
		return (Long) q.getSingleResult();
	}

}
