package org.sgodden.issuetracker.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class IssueJpaRepository implements IssueRepository {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void persist(final Issue issue) {
        em.persist(issue);
    }
    
    @Transactional
    public void persist(Set<Issue> issues) {
    	for (Issue issue : issues) {
    		persist(issue);
    	}
    }

    @Transactional(readOnly=true)
    public Issue findById(final Serializable id) {
        return em.find(Issue.class, id);
    }

    @Transactional(readOnly = true)
	public Issue findByIssueNumber(String issueNumber) {
        Query q = em.createNamedQuery(Issue.QUERY_FIND_BY_ISSUE_NUMBER);
        q.setParameter("issueNumber", issueNumber);
        return (Issue) q.getSingleResult();
	}

    @Transactional(readOnly=true)
    public long count() {
        Query q = em.createNamedQuery(Issue.QUERY_COUNT);
        return (Long) q.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Issue> findAll() {
        Query q = em.createNamedQuery(Issue.QUERY_FIND_ALL);
        return q.getResultList();
    }

    @Transactional
    public void remove(final Issue issue) {
        em.remove(em.find(Issue.class, issue.getId()));
    }

    @Transactional
    public void merge(final Issue issue) {
        em.merge(issue);
    }
}