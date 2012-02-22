package org.sgodden.tom.model

trait CustomerOrderState {

  def confirm(order: CustomerOrder)

  def ship(order: CustomerOrder)

  def cancel(order: CustomerOrder)

  def save(order: CustomerOrder)
}
