package org.sgodden.tom.model

import org.springframework.stereotype.Component

@Component
class CustomerOrderConfirmCommand {

  private[model] def execute(order: CustomerOrder): Unit = {
    order.setStatus(CustomerOrderStatus.CONFIRMED)
  }

}
