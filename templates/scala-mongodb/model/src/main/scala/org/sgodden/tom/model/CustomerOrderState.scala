package org.sgodden.tom.model

trait CustomerOrderState {

  def willEnter(order: CustomerOrder): Boolean

  def willLeave(order: CustomerOrder): Boolean
  
  def getAvailableTransitions(order: CustomerOrder): Array[String]

  def transition(transitionName: String)

}
