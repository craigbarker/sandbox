package org.sgodden.tom.integration.persistence;

import org.sgodden.tom.integration.AbstractIntegrationTest;
import org.sgodden.tom.integration.TestUtils;
import org.sgodden.tom.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * A basic stand-up test to see that persistence is working.
 * @author sgodden
 */
@Test(groups = "integration", enabled = false)
@Transactional
public class CustomerOrderRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private CustomerOrderRepository rep;

    @PersistenceContext
    private EntityManager em;

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    public void testValidation() {
        CustomerOrder order = new CustomerOrder();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CustomerOrder>> violations = validator.validate(order);
        assertEquals(violations.size(), 2, "Wrong number of violations");
    }

    public void testValidationException() {
        CustomerOrder order = new CustomerOrder();
        rep.persist(order);
    }

    @Transactional
    public void testPersistCustomerOrder() {
        CustomerOrder order = new CustomerOrder();
        order.setOrderNumber("ORD1");
        order.setCustomerReference("REF1");

        CollectionDetails cd = new CollectionDetails();
        order.setCollectionDetails(cd);
        Address ca = new Address();
        cd.setAddress(ca);
        ca.setLine1("Collection address line 1");

        DeliveryDetails dd = new DeliveryDetails();
        order.setDeliveryDetails(dd);
        Address da = new Address();
        dd.setAddress(da);
        da.setLine1("Delivery address line 1");

        rep.persist(order);
        em.flush();
        assertEquals(rep.count(), 1, "Wrong number of customer orders");
        TestUtils.removeAllCustomerOrders(rep);
        assertEquals(0, rep.count());
    }

}