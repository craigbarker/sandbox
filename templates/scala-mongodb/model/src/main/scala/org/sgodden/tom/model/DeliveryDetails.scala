package org.sgodden.tom.model

import com.novus.salat.annotations.raw.Persist

case class DeliveryDetails() extends IDeliveryDetails {
  @Persist
  private var address: Address = null

  def getAddress = address

  def setAddress(address: IAddress) = this.address = address.asInstanceOf[Address]

}
