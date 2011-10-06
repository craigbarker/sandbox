package org.sgodden.tom.model;

import java.io.Serializable;
import java.util.List;

/**
 * Repository for the {@link ICustomerOrder} entity.
 * @author sgodden
 */
public interface CustomerOrderRepository {

    /**
     * Removes the passed order from the repository.
     * @param order the order to remove.
     */
    void remove(ICustomerOrder order);

    /**
     * Returns all customer orders.
     * @return all customer orders.
     */
    List<ICustomerOrder> findAll();

    /**
     * Persists the passed order.
     * @param order the order to persist.
     */
    void persist(ICustomerOrder order);
    
    /**
     * Merges the passed order in to the persistent state.
     * @param order the order to merge.
     */
    void merge(ICustomerOrder order);

    /**
     * Returns the {@link ICustomerOrder} instance
     * having the passed id.
     * @param id the id.
     * @return the customer order instance, or <code>null</code> if one does not exist.
     */
    ICustomerOrder findById(Serializable id);

    /**
     * Returns the number of customer orders.
     * @return the number of customer orders.
     */
    long count();
}
