package org.sgodden.tom.model;

import java.io.Serializable;
import java.util.List;

/**
 * Repository for the {@link CustomerOrder} entity.
 * @author sgodden
 */
public interface CustomerOrderRepository {

    /**
     * Removes the passed order from the repository.
     * @param order the order to remove.
     */
    void remove(CustomerOrder order);

    /**
     * Returns all customer orders.
     * @return all customer orders.
     */
    List<CustomerOrder> findAll();

    /**
     * Persists the passed order.
     * @param order the order to persist.
     */
    void persist(CustomerOrder order);
    
    /**
     * Merges the passed order in to the persistent state.
     * @param order the order to merge.
     */
    void merge(CustomerOrder order);

    /**
     * Returns the {@link CustomerOrder} instance
     * having the passed id.
     * @param id the id.
     * @return the customer order instance, or <code>null</code> if one does not exist.
     */
    CustomerOrder findById(Serializable id);

    /**
     * Returns the number of customer orders.
     * @return the number of customer orders.
     */
    long count();
}
