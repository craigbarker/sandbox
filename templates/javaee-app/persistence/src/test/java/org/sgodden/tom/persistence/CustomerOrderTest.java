package org.sgodden.tom.persistence;

import org.sgodden.tom.model.CustomerOrder;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Tests for the CustomerOrder class.
 * @author g106ahe
 */
@Test(groups="unit")
public class CustomerOrderTest {

    /**
     * Simple test of getters and setters.
     */
    public void testGettersAndSetters() {
        CustomerOrder instance = new CustomerOrder();

        assertNull(instance.getId());

        instance.setCustomerReference("C");
        assertEquals("C", instance.getCustomerReference());

        instance.setOrderNumber("O");
        assertEquals("O", instance.getOrderNumber());
    }

}