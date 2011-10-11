package org.sgodden.tom.integration.services;

import org.sgodden.tom.integration.AbstractIntegrationTest;
import org.sgodden.tom.model.CustomerOrderStatus;
import org.sgodden.tom.model.ICustomerOrder;
import org.sgodden.tom.model.services.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class CustomerServiceTest extends AbstractIntegrationTest {

    @Autowired
    private CustomerOrderService customerOrderService;

    public void testCreate() {
        ICustomerOrder order = customerOrderService.create();
        assertEquals(CustomerOrderStatus.NEW, order.getStatus());
    }

    public void testPersist() {
        ICustomerOrder order = customerOrderService.create();
        order.setCustomerReference("ASD");
        order.setOrderNumber("WER");
        Long id = customerOrderService.persist(order);
        assertEquals(1, customerOrderService.findAll().size(), "Wrong number of orders");
    }

}
