package org.sgodden.tom.model;

import org.testng.annotations.Test;

@Test(groups = "integration")
public class CustomerOrderTest extends AbstractIntegrationTest{

    @Test(expectedExceptions = {IllegalStateException.class})
    public void shipmentNotAllowedFromNewState() {
        CustomerOrder order = new CustomerOrder();
        order.ship();
    }

}
