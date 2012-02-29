package org.sgodden.tom.integration.services

import org.sgodden.tom.services.customerorder.CustomerOrderService
import org.testng.Assert._
import org.sgodden.tom.model.{ValidationException, CustomerOrderStatus, ICustomerOrder}
import org.springframework.beans.factory.annotation.Autowired
import org.testng.annotations.{AfterMethod, Test}

@Test
class CustomerServiceTest {
  @Autowired private var customerOrderService: CustomerOrderService = null

  @AfterMethod def afterTest {
    for (order <- customerOrderService.findAll) {
      customerOrderService.remove(order.getId)
    }
  }

  def testCreate {
    var order: ICustomerOrder = customerOrderService.create
    assertEquals(order.getStatus, CustomerOrderStatus.NEW)
  }

  def testPersist {
    createOrder(1)
    assertEquals(customerOrderService.findAll.size, 1)
  }

  def testFindAll {
    (1 to 10).foreach(createOrder(_))
    assertEquals(customerOrderService.findAll.size, 10)
  }

  @Test
  def shouldFailWithNullOrderNumber {
    var order: ICustomerOrder = customerOrderService.create
    try {
      customerOrderService.persist(order)
      fail("Validation exception should have been thrown")
    } catch {
      case ex: ValidationException => {}
    }
  }

  private def createOrder(seq: Int): Unit = {
    var order: ICustomerOrder = customerOrderService.create
    order.setCustomerReference("REFERENCE: " + seq)
    order.setOrderNumber("ORDER NUMBER: " + seq)
    customerOrderService.persist(order)
  }
}
