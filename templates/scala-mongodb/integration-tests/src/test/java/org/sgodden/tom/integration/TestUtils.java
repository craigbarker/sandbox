package org.sgodden.tom.integration;

import org.sgodden.tom.model.CustomerOrder;
import org.sgodden.tom.model.CustomerOrderRepository;
import org.sgodden.tom.model.ICustomerOrder;

/**
 * Provides utility methods to test classes.
 * 
 * @author Simon Godden
 */
public class TestUtils {

    /**
     * Removes all {@link CustomerOrder} instances from the passed
     * repository.
     * 
     * @param rep the repository.
     */
    public static void removeAllCustomerOrders(CustomerOrderRepository rep) {
        for (ICustomerOrder order : rep.findAll()) {
            rep.remove(order);
        }
    }
}
