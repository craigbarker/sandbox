package org.sgodden.tom.domain;

import com.google.inject.*;
import org.sgodden.tom.domain.acceptance.AbstractAppEngineTest;
import org.sgodden.tom.domain.acceptance.TestModule;
import org.sgodden.tom.domain.acceptance.TestUtils;
import org.sgodden.tom.jpa.EntityManagerFactory;
import org.sgodden.tom.jpa.EntityManagerFactoryImpl;
import org.sgodden.transaction.Transactional;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
@Test(groups = "integration")
public class CustomerOrderRepositoryTest extends AbstractAppEngineTest {

    private Injector injector;
    private EntityManagerFactory emf;
    private CustomerOrderRepository rep;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        emf = EntityManagerFactoryImpl.getInstance("tom-domain-pu");
        injector = Guice.createInjector(new TestModule(emf), new TomDomainModule(), new InternalTestModule());
        rep = injector.getInstance(CustomerOrderRepository.class);
    }

    @AfterMethod
    @Override
    public void tearDown() {
        TestUtils.removeAllCustomerOrders(rep);
        emf.getJpaEntityManagerFactory().close();
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

        injector.getInstance(InternalTestInterface.class).assertAllGetters(order);
    }

    public static class InternalTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(InternalTestInterface.class);
        }
    }

    @ImplementedBy(InternalTestInterfaceImpl.class)
    public interface InternalTestInterface {
        public void assertAllGetters(CustomerOrder original);
    }

    public static class InternalTestInterfaceImpl implements InternalTestInterface {

        @Inject
        private CustomerOrderRepository rep;

        @Transactional
        public void assertAllGetters(CustomerOrder order) {
            CustomerOrder newOrder = rep.findById(order.getId());
            assertEquals(newOrder.getId(), order.getId());
            assertEquals(newOrder.getCollectionDetails().getAddress().getLine1(),
                    order.getCollectionDetails().getAddress().getLine1());
        }
    }

}