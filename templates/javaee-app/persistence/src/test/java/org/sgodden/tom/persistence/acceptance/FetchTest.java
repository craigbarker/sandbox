package org.sgodden.tom.persistence.acceptance;

import org.sgodden.tom.model.CustomerOrder;
import org.sgodden.tom.model.CustomerOrderLine;
import org.sgodden.tom.model.CustomerOrderRepository;
import org.sgodden.tom.model.ICustomerOrderLine;
import org.sgodden.tom.persistence.AbstractIntegrationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.io.Serializable;

/**
 * Test using fetch in JPA-QL.
 * @author sgodden
 */
@Test(groups = "acceptance")
public class FetchTest extends AbstractIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchTest.class);

    @Autowired
    private CustomerOrderRepository rep;
    
    @PersistenceUnit
    private EntityManagerFactory emf;

    private Serializable id1;

    @BeforeMethod
    public void setUp() {
        id1 = createOrder("A", 2);
        createOrder("B", 4);
        createOrder("C", 6);
    }

    @AfterMethod
    public void tearDown() {
    }

    public void testLazyLoad() {
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();
        LOGGER.info(id1.toString());
        CustomerOrder co = em.find(CustomerOrder.class, id1);
        for (ICustomerOrderLine line : co.getOrderLines()) {
            LOGGER.info(line.getDescriptionOfGoods());
        }
        //em.getTransaction().rollback();
        //TestUtils.removeAllCustomerOrders(rep);
        //assertEquals(0, rep.count());
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

        rep.persist(order);

        return order.getId();
    }
}