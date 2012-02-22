package org.sgodden.tom.model

class CustomerOrderCancelCommand {

  def cancel(order: CustomerOrder): Unit = {
    order.setStatus(CustomerOrderStatus.CANCELLED)
  }

}
