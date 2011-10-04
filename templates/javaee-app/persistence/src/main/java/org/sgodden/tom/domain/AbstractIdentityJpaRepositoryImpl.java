package org.sgodden.tom.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractIdentityJpaRepositoryImpl<T extends Identity> {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void persist(final T entity) {
        em.persist(entity);
    }

    @Transactional
    public void remove(final T entity) {
        em.remove(em.find(getEntityClass(), entity.getId()));
    }

    @Transactional
    public void merge(final T entity) {
        em.merge(entity);
    }

    @Transactional
    public void refresh(final T entity) {
        em.refresh(entity);
    }

    @Transactional
    public T findById(final Serializable id) {
        return em.find(getEntityClass(), id);
    }

    protected abstract Class<T> getEntityClass();

    @Transactional
    public long count() {
        Query q = em.createQuery("select count(o) from " +
                getEntityClass().getName() + " o");
        return (Long) q.getSingleResult();
    }

    @Transactional
    public List<T> findAll() {
        Query q = em.createQuery("from " + getEntityClass().getName());
        return q.getResultList();
    }

}
