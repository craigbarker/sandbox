package org.sgodden.tom.domain.acceptance;

import org.sgodden.tom.domain.CustomerOrder;
import org.sgodden.tom.domain.CustomerOrderLine;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Test
public class DeepReadOnlyTest {

    private EntityManager em;

    @BeforeTest
    public void beforeClass() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tom-domain-pu");
        em = emf.createEntityManager();
    }

    @Test
    public void testDeepReadOnly() {
        long id = createTestData();

        em.getTransaction().begin();
        CustomerOrder order = em.find(CustomerOrder.class, id);
        em.detach(order);
        order.getLines().get(0).setPieces(333);
        em.getTransaction().commit();

        em.getTransaction().begin();
        order = em.find(CustomerOrder.class, id);
        assertEquals(order.getLines().get(0).getPieces(), 10);
        em.getTransaction().commit();
    }

    private long createTestData() {
        em.getTransaction().begin();

        CustomerOrder order = new CustomerOrder();
        order.setCustomerReference("ASDASD");
        order.setOrderNumber("123123");

        order.getLines().add(makeCustomerOrderLine(10));

        em.persist(order);

        em.getTransaction().commit();

        return order.getId();
    }

    private CustomerOrderLine makeCustomerOrderLine(int pieces) {
        CustomerOrderLine ret = new CustomerOrderLine();
        ret.setPieces(pieces);
        return ret;
    }

}
