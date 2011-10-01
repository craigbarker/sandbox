package org.sgodden.tom.domain.acceptance;

import org.sgodden.tom.domain.CustomerOrder;
import org.sgodden.tom.domain.CustomerOrderRepository;

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
        for (CustomerOrder order : rep.findAll()) {
            rep.remove(order);
        }
    }
}
