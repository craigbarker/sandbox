package org.sgodden.issuetracker.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Repository for the {@link CustomerOrder} entity.
 * @author sgodden
 */
public interface IssueRepository {

    /**
     * Removes the passed order from the repository.
     * @param order the order to remove.
     */
    void remove(Issue issue);

    /**
     * Returns all customer orders.
     * @return all customer orders.
     */
    List<Issue> findAll();

    /**
     * Persists the passed order.
     * @param order the order to persist.
     */
    void persist(Issue issue);
    
    void persist(Set<Issue> issues);
    
    /**
     * Merges the passed order in to the persistent state.
     * @param order the order to merge.
     */
    void merge(Issue issue);

    /**
     * Returns the {@link CustomerOrder} instance
     * having the passed id.
     * @param id the id.
     * @return the customer order instance, or <code>null</code> if one does not exist.
     */
    Issue findById(Serializable id);

    /**
     * Returns the number of customer orders.
     * @return the number of customer orders.
     */
    long count();
}
