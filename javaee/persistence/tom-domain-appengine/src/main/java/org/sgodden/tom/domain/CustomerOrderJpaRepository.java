package org.sgodden.tom.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.sgodden.transaction.Transactional;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class CustomerOrderJpaRepository implements CustomerOrderRepository {

    @Inject
    private Provider<EntityManager> em;

    @Transactional
    public void persist(CustomerOrder order) {
        em.get().persist(order);
    }

    @Transactional
    public CustomerOrder findById(Serializable id) {
        return em.get().find(CustomerOrder.class, id);
    }

    public int count() {
        Query q = em.get().createNamedQuery(CustomerOrder.QUERY_COUNT);
        return (Integer) q.getSingleResult();
    }

    @SuppressWarnings("unchecked")
	@Transactional
    public List<CustomerOrder> findAll() {
        Query q = em.get().createNamedQuery(CustomerOrder.QUERY_FIND_ALL);
        return q.getResultList();
    }

    @Transactional
    public void remove(CustomerOrder order) {
        em.get().remove(findById(order.getId()));
    }

	@Transactional
	public void merge(CustomerOrder order) {
		em.get().merge(order);
	}
}
