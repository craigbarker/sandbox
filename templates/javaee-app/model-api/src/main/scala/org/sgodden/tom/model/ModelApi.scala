package org.sgodden.tom.model

import java.util.Calendar
import org.apache.commons.lang.builder.EqualsBuilder

trait Identity[T] extends Serializable {

  def getId: Long

  def getVersion: Long

  override def equals(other: Any): Boolean = {
    if (other == null
      || !other.isInstanceOf[Identity[T]] ) {
      return false
    }

    val o = other.asInstanceOf[Identity[T]]

    if (o.getId == 0 || getId == 0) {
      return super.equals(other)
    }
    else {
      return new EqualsBuilder().append(this.getId, o.getId).isEquals
    }
  }

  def equals(other: Identity[T]) = {
  }

}

trait ICustomerOrder extends Identity[ICustomerOrder] {

  def cancel

  def confirm

  def ship

  def getBookingDate: Calendar
  def setBookingDate(cal: Calendar)

  def getCustomerReference: String
  def setCustomerReference(reference: String)

  def getOrderNumber: String
  def setOrderNumber(orderNumber: String)

  def getCollectionDetails: ICollectionDetails
  def setCollectionDetails(details: ICollectionDetails)

  def getDeliveryDetails: IDeliveryDetails
  def setDeliveryDetails(details: IDeliveryDetails)

  def getOrderLines: Set[ICustomerOrderLine]
  def addOrderLine(line: ICustomerOrderLine)
  def removeOrderLine(line: ICustomerOrderLine)

  def getStatus: CustomerOrderStatus.Value
}

trait ICollectionDetails {
  def getAddress: IAddress
  def setAddress(address: IAddress)
}

trait IDeliveryDetails {
  def getAddress: IAddress
  def setAddress(address: IAddress)
}

trait IAddress {
  def getLine1: String
  def setLine1(line1: String)

  def getLine2: String
  def setLine2(line2: String)

  def getLine3: String
  def setLine3(line3: String)

  def getLine4: String
  def setLine4(line4: String)

  def getTown: String
  def setTown(town: String)

  def getPostalCode: String
  def setPostalCode(postalCode: String)
}

trait ICustomerOrderLine {
  def getPackageType: String
  def setPackageType(packageType: String)

  def getDescriptionOfGoods: String
  def setDescriptionOfGoods(descriptionOfGoods: String)
}