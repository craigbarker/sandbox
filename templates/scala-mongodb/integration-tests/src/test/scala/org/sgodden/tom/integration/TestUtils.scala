package org.sgodden.tom.integration

import org.sgodden.tom.model.CustomerOrderRepository

object TestUtils {
  def removeAllCustomerOrders(rep: CustomerOrderRepository): Unit = {
    for (order <- rep.findAll) {
      rep.remove(order)
    }
  }
}
