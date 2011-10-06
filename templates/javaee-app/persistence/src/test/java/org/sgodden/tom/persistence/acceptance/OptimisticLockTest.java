package org.sgodden.tom.persistence.acceptance;

import org.sgodden.tom.model.CustomerOrderLine;
import org.sgodden.tom.model.CustomerOrder;
import org.sgodden.tom.model.CustomerOrderRepository;
import org.sgodden.tom.persistence.AbstractIntegrationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.RollbackException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Test using fetch in JPA-QL.
 * @author sgodden
 */
@Test(groups = "acceptance")
public class OptimisticLockTest extends AbstractIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptimisticLockTest.class);
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

    @Test(expectedExceptions = {RollbackException.class})
    public void testOptimisticLock() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CustomerOrder ord = em.find(CustomerOrder.class, id1);
        updateVersionOutsideOfTx(ord.getId(), ord.getVersion());
        ord.setOrderNumber("THIS SHOULD FAIL OPTIMISTIC LOCK");
        em.merge(ord);

        em.getTransaction().commit();
        em.close();
    }

    private void updateVersionOutsideOfTx(long orderId, long currentVersion) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DriverManager.getConnection("jdbc:h2:mem:test");
            stm = conn.prepareStatement(
                    "update CustomerOrder set version=? where id=?");
            stm.setLong(1, ++currentVersion);
            stm.setLong(2, orderId);
            stm.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                    conn.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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
