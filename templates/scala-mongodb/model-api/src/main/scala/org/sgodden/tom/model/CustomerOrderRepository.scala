package org.sgodden.tom.model

trait CustomerOrderRepository {

  def remove(order: ICustomerOrder)

  def findAll: List[ICustomerOrder]

  def persist(order: ICustomerOrder)

  def merge(order: ICustomerOrder)

  def findById(id: Serializable): ICustomerOrder

  def count: Long
}
