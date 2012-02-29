package org.sgodden.tom.integration.persistence

import org.springframework.transaction.annotation.Transactional
import org.testng.Assert._
import org.sgodden.tom.model._
import org.sgodden.tom.integration.{TestUtils, AbstractIntegrationTest}
import javax.validation.{ConstraintViolation, Validator, Validation, ValidatorFactory}
import org.testng.annotations.{BeforeMethod, AfterMethod, Test}
import javax.persistence.{PersistenceContext, EntityManager}
import org.springframework.beans.factory.annotation.Autowired
import collection.JavaConverters._

@Test (groups = Array ("integration"), enabled = false)
class CustomerOrderRepositoryTest extends AbstractIntegrationTest {
  @Autowired private var rep: CustomerOrderRepository = null
  @PersistenceContext private var em: EntityManager = null

  @BeforeMethod def setUp: Unit = {
  }

  @AfterMethod def tearDown: Unit = {
  }

  def testValidation: Unit = {
    var order: CustomerOrder = new CustomerOrder
    var factory: ValidatorFactory = Validation.buildDefaultValidatorFactory
    var validator: Validator = factory.getValidator
    var violations = validator.validate(order).asScala
    assertEquals(violations.size, 2, "Wrong number of violations")
  }

  def testExtraValidation: Unit = {
    var order: CustomerOrder = new CustomerOrder
    order.setCustomerReference("YIKES")
    var factory: ValidatorFactory = Validation.buildDefaultValidatorFactory
    var validator: Validator = factory.getValidator
    var violations = validator.validate(order)
    assertEquals(violations.size, 2, "Wrong number of violations")
  }

  def testValidationException: Unit = {
    var order: CustomerOrder = new CustomerOrder
    rep.persist(order)
  }

  @Transactional def testPersistCustomerOrder: Unit = {
    var order: CustomerOrder = new CustomerOrder
    order.setOrderNumber("ORD1")
    order.setCustomerReference("REF1")
    var cd: CollectionDetails = new CollectionDetails
    order.setCollectionDetails(cd)
    var ca: Address = new Address
    cd.setAddress(ca)
    ca.setLine1("Collection address line 1")
    var dd: DeliveryDetails = new DeliveryDetails
    order.setDeliveryDetails(dd)
    var da: Address = new Address
    dd.setAddress(da)
    da.setLine1("Delivery address line 1")
    rep.persist(order)
    em.flush
    assertEquals(rep.count, 1, "Wrong number of customer orders")
    TestUtils.removeAllCustomerOrders(rep)
    assertEquals(0, rep.count)
  }
}
