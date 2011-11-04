package org.sgodden.tom.integration.services;

import org.sgodden.tom.integration.AbstractIntegrationTest;
import org.sgodden.tom.model.CustomerOrderStatus;
import org.sgodden.tom.model.ICustomerOrder;
import org.sgodden.tom.services.customerorder.CustomerOrderListEntry;
import org.sgodden.tom.services.customerorder.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class CustomerServiceTest extends AbstractIntegrationTest {

    @Autowired
    private CustomerOrderService customerOrderService;

    @AfterMethod
    public void afterTest() {
        for (CustomerOrderListEntry order : customerOrderService.list()) {
            customerOrderService.remove(order.getId());
        }
    }

    public void testCreate() {
        ICustomerOrder order = customerOrderService.create();
        assertEquals(order.getStatus(), CustomerOrderStatus.NEW);
    }

    public void testPersist() {
        createOrder(1);
        assertEquals(customerOrderService.list().size(), 1);
    }

    public void testFindAll() {
        for (int i = 0; i < 10; i++) {
            createOrder(i + 1);
        }
        assertEquals(customerOrderService.list().size(), 10);
    }

    private void createOrder(int seq) {
        ICustomerOrder order = customerOrderService.create();
        order.setCustomerReference("REFERENCE: " + seq);
        order.setOrderNumber("ORDER NUMBER: " + seq);
        customerOrderService.persist(order);
    }

}
