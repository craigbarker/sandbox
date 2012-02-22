package org.sgodden.tom.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.sgodden.tom.model.Identity;

public abstract class AbstractIdentityJpaRepositoryImpl<T extends Identity> {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public void persist(final T entity) {
        em.persist(entity);
    }

    public void remove(final T entity) {
        em.remove(em.find(getEntityClass(), entity.getId()));
    }

    public void merge(final T entity) {
        em.merge(entity);
    }

    public void refresh(final T entity) {
        em.refresh(entity);
    }

    public T findById(final Serializable id) {
        return em.find(getEntityClass(), id);
    }

    protected abstract Class<? extends T> getEntityClass();

    public long count() {
        Query q = em.createQuery("select count(o) from " +
                getEntityClass().getName() + " o");
        return (Long) q.getSingleResult();
    }

    @SuppressWarnings("unchecked")
	public List<T> findAll() {
        Query q = em.createQuery("from " + getEntityClass().getName());
        return q.getResultList();
    }

}
