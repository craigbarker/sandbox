package org.sgodden.tom.model.services;

import org.sgodden.tom.model.ICustomerOrder;

import java.util.List;

public interface CustomerOrderService {

    /**
     * Persists a new unsaved customer order and returns its id.
     * @param customerOrder the order to persist.
     * @return the id of the order.
     */
    Long persist(ICustomerOrder customerOrder);

    /**
     * Removes the passed order from the repository.
     * @param id the id of the order to remove.
     */
    void remove(Long id);

    /**
     * Returns all customer orders.
     * @return all customer orders.
     */
    List<ICustomerOrder> findAll();

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
    ICustomerOrder findById(Long id);

}
