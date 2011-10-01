package org.sgodden.tom.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerOrderJpaRepository implements CustomerOrderRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void persist(final CustomerOrder order) {
        em.persist(order);
    }

    @Transactional
    public CustomerOrder findById(final Serializable id) {
        return em.find(CustomerOrder.class, id);
    }

    @Transactional
    public long count() {
        Query q = em.createNamedQuery(CustomerOrder.QUERY_COUNT);
        return (Long) q.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<CustomerOrder> findAll() {
        Query q = em.createNamedQuery(CustomerOrder.QUERY_FIND_ALL);
        return q.getResultList();
    }

    @Transactional
    public void remove(final CustomerOrder order) {
        em.remove(em.find(CustomerOrder.class, order.getId()));
    }

    @Transactional
    public void merge(final CustomerOrder order) {
        em.merge(order);
    }
}