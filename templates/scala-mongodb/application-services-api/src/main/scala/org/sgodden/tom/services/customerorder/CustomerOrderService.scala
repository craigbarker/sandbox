package org.sgodden.tom.services.customerorder

import org.sgodden.tom.model.ICustomerOrder

trait CustomerOrderService {

  def create: ICustomerOrder

  def persist(customerOrder: ICustomerOrder): String

  def remove(id: String)

  def findAll: List[ICustomerOrder]

  def merge(order: ICustomerOrder)

  def findById(id: String): ICustomerOrder
}
