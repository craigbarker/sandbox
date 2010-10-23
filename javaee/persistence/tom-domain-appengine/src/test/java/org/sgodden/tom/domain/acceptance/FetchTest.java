package org.sgodden.tom.domain.acceptance;

import javax.persistence.EntityManager;

import org.sgodden.tom.domain.CustomerOrder;
import org.sgodden.tom.domain.CustomerOrderLine;
import org.sgodden.tom.domain.CustomerOrderRepository;
import org.sgodden.tom.domain.TomDomainModule;
import org.sgodden.tom.jpa.EntityManagerFactory;
import org.sgodden.tom.jpa.EntityManagerFactoryImpl;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.Serializable;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Test using fetch in JPA-QL.
 * @author sgodden
 */
@Test(groups = "acceptance")
public class FetchTest extends AbstractAppEngineTest {

    private Injector injector;
    private EntityManagerFactory emf;
    private CustomerOrderRepository rep;

    private Serializable id1;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        emf = EntityManagerFactoryImpl.getInstance("tom-domain-pu");
        injector = Guice.createInjector(new TestModule(emf), new TomDomainModule());
        rep = injector.getInstance(CustomerOrderRepository.class);
        id1 = createOrder("A", 2);
        createOrder("B", 4);
        createOrder("C", 6);
    }

    @AfterMethod
    @Override
    public void tearDown() {
        TestUtils.removeAllCustomerOrders(rep);
        assertEquals(0, rep.count());
        emf.getJpaEntityManagerFactory().close();
        super.tearDown();
    }

    public void testLazyLoad() {
        EntityManager em = emf.create();
        //em.getTransaction().begin();
        System.out.println(id1);
        CustomerOrder co = em.find(CustomerOrder.class, id1);
        for (CustomerOrderLine line : co.getOrderLines()) {
            System.out.println(line.getDescriptionOfGoods());
        }
        //em.getTransaction().rollback();
        em.close();
    }

    private Serializable createOrder(String prefix, int lines) {
        CustomerOrder order = new CustomerOrder();
        order.setCustomerReference(prefix + " co");
        order.setOrderNumber(prefix + " on");

        for (int i = 0; i < lines; i++) {
            CustomerOrderLine line = new CustomerOrderLine();
            line.setDescriptionOfGoods("Description " + i);
            line.setPackageType("package type " + i);
            order.addOrderLine(line);
        }

        CustomerOrderRepository rep = injector.getInstance(CustomerOrderRepository.class);
        rep.persist(order);

        return order.getId();
    }
}
