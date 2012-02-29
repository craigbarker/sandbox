package org.sgodden.tom.model

trait CustomerOrderRepository {

  def remove(order: ICustomerOrder)

  def findAll: List[ICustomerOrder]

  def persist(order: ICustomerOrder)

  def merge(order: ICustomerOrder)

  def findById(id: String): ICustomerOrder

  def count: Long
}
