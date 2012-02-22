package org.sgodden.tom.model

object CustomerOrderStatus extends Enumeration {

  val
    NEW,
    REQUESTED,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    CLOSED = Value

}
