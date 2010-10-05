package org.sgodden.tom.domain;

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

        instance.setId(123l);
        assertEquals(123l, instance.getId());

        instance.setCustomerReference("C");
        assertEquals("C", instance.getCustomerReference());

        instance.setOrderNumber("O");
        assertEquals("O", instance.getOrderNumber());
    }

}