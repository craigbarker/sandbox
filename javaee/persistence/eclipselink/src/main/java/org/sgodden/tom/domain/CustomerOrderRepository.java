package org.sgodden.tom.domain;

import com.google.inject.ImplementedBy;

/**
 * Repository for the {@link CustomerOrder} entity.
 * @author sgodden
 */
@ImplementedBy(CustomerOrderRepositoryImpl.class)
public interface CustomerOrderRepository {
	
	void persist(CustomerOrder order);
	
	/**
	 * Returns the {@link CustomerOrder} instance
	 * having the passed id.
	 * @param id the id.
	 * @return the customer order instance, or <code>null</code> if one does not exist.
	 */
	CustomerOrder findById(long id);
	
	/**
	 * Returns the number of customer orders.
	 * @return the number of customer orders.
	 */
	long count();

}