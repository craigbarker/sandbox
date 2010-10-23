package org.sgodden.tom.domain;

import org.sgodden.tom.domain.acceptance.AbstractAppEngineTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import org.sgodden.tom.domain.acceptance.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.testng.Assert.assertEquals;

/**
 * A basic stand-up test to see that persistence is working.
 * @author sgodden
 */
@Test(groups = "integration")
@ContextConfiguration(locations="/org/sgodden/tom/domain/beans.xml")
public class CustomerOrderRepositoryTest extends AbstractAppEngineTest {

    @Autowired
    private CustomerOrderRepository rep;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
    }

    @AfterMethod
    @Override
    public void tearDown() {
        super.tearDown();
    }

    public void testValidation() {
        CustomerOrder order = new CustomerOrder();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CustomerOrder>> violations = validator.validate(order);
        assertEquals(violations.size(), 2, "Wrong number of violations");
    }

    @Test(expectedExceptions = ValidationException.class)
    public void testValidationException() {
        CustomerOrder order = new CustomerOrder();
        rep.persist(order);
    }

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
        assertEquals(rep.count(), 1, "Wrong number of customer orders");
        TestUtils.removeAllCustomerOrders(rep);
        assertEquals(0, rep.count());
    }

}