package org.sgodden.tom.domain.acceptance;

import javax.persistence.EntityManager;

import org.sgodden.tom.domain.CustomerOrder;
import org.sgodden.tom.domain.CustomerOrderLine;
import org.sgodden.tom.domain.CustomerOrderRepository;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.io.Serializable;
import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Test using fetch in JPA-QL.
 * @author sgodden
 */
@Test(groups = "acceptance")
@ContextConfiguration(locations="/org/sgodden/tom/domain/beans.xml")
public class FetchTest extends AbstractAppEngineTest {

    @Autowired
    private CustomerOrderRepository rep;
    
    @PersistenceUnit
    private EntityManagerFactory emf;

    private Serializable id1;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        id1 = createOrder("A", 2);
        createOrder("B", 4);
        createOrder("C", 6);
    }

    @AfterMethod
    @Override
    public void tearDown() {
        super.tearDown();
    }

    public void testLazyLoad() {
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();
        System.out.println(id1);
        CustomerOrder co = em.find(CustomerOrder.class, id1);
        for (CustomerOrderLine line : co.getOrderLines()) {
            System.out.println(line.getDescriptionOfGoods());
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
