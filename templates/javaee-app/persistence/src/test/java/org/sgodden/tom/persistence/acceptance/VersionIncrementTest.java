package org.sgodden.tom.persistence.acceptance;

import javax.persistence.EntityManager;

import org.sgodden.tom.model.CustomerOrderLine;
import org.sgodden.tom.model.CustomerOrder;
import org.sgodden.tom.model.CustomerOrderRepository;
import org.sgodden.tom.persistence.AbstractIntegrationTest;
import org.testng.annotations.Test;

import java.io.Serializable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Test using fetch in JPA-QL.
 * @author sgodden
 */
@Test(groups = "acceptance")
public class VersionIncrementTest extends AbstractIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionIncrementTest.class);
    @Autowired
    private CustomerOrderRepository rep;
    @PersistenceUnit
    private EntityManagerFactory emf;
    private Serializable id1;

    @BeforeMethod
    public void setUp() {
        id1 = createOrder("A", 1);
    }

    @AfterMethod
    public void tearDown() {
    }

    public void testChildUpdateDoesNotIncrementParentVersion() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CustomerOrder ord = em.find(CustomerOrder.class, id1);
        Long version = ord.getVersion();
        CustomerOrderLine line = ord.getOrderLines().iterator().next();
        Long childVersion = line.getVersion();
        String updatedDescriptionOfGoods = line.getDescriptionOfGoods() + " updated";
        line.setDescriptionOfGoods(updatedDescriptionOfGoods);
        em.merge(ord);

        em.getTransaction().commit();

        ord = em.find(CustomerOrder.class, id1);
        line = ord.getOrderLines().iterator().next();
        // first make sure we actually updated the child
        assertEquals(line.getDescriptionOfGoods(), updatedDescriptionOfGoods);
        // and that its version was incremented
        assertEquals(line.getVersion().longValue(), childVersion + 1);
        // and that the parent's version was not incremented
        assertEquals(ord.getVersion(), version);
        
        em.close();
    }

    public void testChildAdditionIncrementsParentVersion() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CustomerOrder ord = em.find(CustomerOrder.class, id1);
        long version = ord.getVersion();
        CustomerOrderLine line = new CustomerOrderLine();
        line.setDescriptionOfGoods("SECOND DESC");
        ord.addOrderLine(line);
        em.merge(ord);

        em.getTransaction().commit();

        ord = em.find(CustomerOrder.class, id1);
        // first make sure we actually added the child
        assertEquals(ord.getOrderLines().size(), 2);
        assertEquals(ord.getVersion().longValue(), version + 1);

        em.close();
    }

    public void testChildRemovalIncrementsParentVersion() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CustomerOrder ord = em.find(CustomerOrder.class, id1);
        long version = ord.getVersion();
        ord.removeOrderLine(ord.getOrderLines().iterator().next());
        em.merge(ord);

        em.getTransaction().commit();

        ord = em.find(CustomerOrder.class, id1);
        // first make sure we actually added the child
        assertEquals(ord.getOrderLines().size(), 0);
        assertEquals(ord.getVersion().longValue(), version + 1);

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
