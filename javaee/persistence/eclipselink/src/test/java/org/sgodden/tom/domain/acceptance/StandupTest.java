package org.sgodden.tom.domain.acceptance;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.sgodden.tom.domain.CustomerOrder;
import org.sgodden.tom.domain.CustomerOrderRepository;
import org.sgodden.tom.domain.TomDomainModule;
import org.sgodden.tom.domain.ValidationException;
import org.sgodden.transaction.EntityManagerFactory;
import org.sgodden.transaction.EntityManagerFactoryImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * A basic stand-up test to see that persistence is working.
 * @author sgodden
 */
@Test(groups = "acceptance")
public class StandupTest {
	
	private Injector injector;
	private EntityManagerFactory emf;
	
	@BeforeClass
	public void beforeClass() {
		emf = new EntityManagerFactoryImpl("tom-domain-pu");
		injector = Guice.createInjector(new TestModule(emf), new TomDomainModule());
	}
	
	@AfterClass
	public void afterClass() {
		emf.getJpaEntityManagerFactory().close();
	}

    @Test(expectedExceptions = ValidationException.class)
    public void testValidationException() {
        CustomerOrder order = new CustomerOrder();
        CustomerOrderRepository rep = injector.getInstance(CustomerOrderRepository.class);
        rep.persist(order);
    }

    public void testStandup() {
        CustomerOrder order = new CustomerOrder();
        order.setOrderNumber("ORD1");
        order.setCustomerReference("REF1");
        CustomerOrderRepository rep = injector.getInstance(CustomerOrderRepository.class);
        rep.persist(order);
        assertEquals(rep.count(), 1, "Wrong number of customer orders");
        CustomerOrder newOrder = rep.findById(order.getId());
        assertEquals(newOrder.getId(), order.getId());
    }

    public void testValidation() {
        CustomerOrder order = new CustomerOrder();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CustomerOrder>> violations = validator.validate(order);
        assertEquals(violations.size(), 2, "Wrong number of violations");
    }
}
