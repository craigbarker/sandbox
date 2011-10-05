package org.sgodden.tom.model;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

@Test(groups = "integration")
public class CustomerOrderTest extends AbstractIntegrationTest{

    @Test(expectedExceptions = {IllegalStateException.class})
    public void shipmentNotAllowedFromNewState() {
        CustomerOrder order = new CustomerOrder();
        order.ship();
    }

    public void orderIsInNewStateAfterCreation() {
        CustomerOrder order = new CustomerOrder();
        assertEquals(order.getStatus(), CustomerOrderStatus.NEW);
    }

    public void canConfirmFromNewState() {
        CustomerOrder order = new CustomerOrder();
        order.confirm();
        assertEquals(order.getStatus(), CustomerOrderStatus.CONFIRMED);
    }

}
