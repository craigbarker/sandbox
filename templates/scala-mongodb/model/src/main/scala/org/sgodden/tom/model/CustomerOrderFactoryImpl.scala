package org.sgodden.tom.model

class CustomerOrderFactoryImpl extends CustomerOrderFactory {
  override def create: ICustomerOrder = {
    new CustomerOrder
  }
}
