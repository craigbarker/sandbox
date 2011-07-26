package org.sgodden.tom.domain.acceptance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.sgodden.tom.domain.CustomerOrder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * A basic stand-up test to see that persistence is working.
 * @author sgodden
 */
@Test
public class StandupTest {
    
    private EntityManager em;

    @BeforeTest
    public void beforeClass() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tom-domain-pu");
        em = emf.createEntityManager();
    }

    @AfterTest
    public void afterClass() {
        em.close();
    }

    @Test(expectedExceptions=RollbackException.class)
    public void testValidationException() {
        em.getTransaction().begin();
        CustomerOrder order = new CustomerOrder();
        em.persist(order);
        em.getTransaction().commit();
        
        Query q = em.createQuery("select obj from CustomerOrder obj");
        System.out.println(q.getSingleResult());
    }

    @Test
    public void testSimpleCreate() {
        em.getTransaction().begin();
        CustomerOrder order = new CustomerOrder();
        order.setOrderNumber("ON1");
        order.setCustomerReference("CR1");
        em.persist(order);
        em.getTransaction().commit();

        Query q = em.createQuery("select count(o) from CustomerOrder o");
        Long count = (Long) q.getSingleResult();
        assertEquals(count.longValue(), 1l, "Wrong number of customers");
    }

}
