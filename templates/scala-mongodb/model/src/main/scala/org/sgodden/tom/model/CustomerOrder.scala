package org.sgodden.tom.model

import java.util.{Collections, Calendar}
import javax.validation.constraints.{Pattern,NotNull}
import org.springframework.beans.factory.annotation.Autowired
import collection.mutable.{HashSet, Set => MutableSet}

// TODO - modify to use joda time
class CustomerOrder extends ICustomerOrder {
  @NotNull
  @Pattern(regexp = "cr.*", message = "{customerReferenceMustBeginWithCr}")
  private var customerReference: String = null
  @NotNull
  private var orderNumber: String = null
  @NotNull
  private var status: CustomerOrderStatus.Value = null
  @NotNull
  private var bookingDate: Calendar = null
  private var collectionDetails: CollectionDetails = null
  private var deliveryDetails: DeliveryDetails = null
  private val orderLines: MutableSet[CustomerOrderLine] = new HashSet[CustomerOrderLine]
  @Autowired
  private var stateObjects: Map[String, CustomerOrderState] = null

  def CustomerOrder() {
    status = CustomerOrderStatus.NEW
    bookingDate = Calendar.getInstance
  }

  override def cancel = {
    getStateObject.cancel(this)
  }

  override def confirm = {
    getStateObject.confirm(this)
  }

  override def ship = {
    getStateObject.ship(this)
  }

  override def getBookingDate = bookingDate
  override def setBookingDate(date: Calendar) = this.bookingDate = date

  override def getCustomerReference = customerReference
  override def setCustomerReference(customerReference: String) = this.customerReference = customerReference

  override def getOrderNumber: String = orderNumber
  override def setOrderNumber(orderNumber: String) = this.orderNumber = orderNumber

  override def getCollectionDetails: ICollectionDetails = collectionDetails
  override def setCollectionDetails(collectionDetails: ICollectionDetails) =
    this.collectionDetails = collectionDetails.asInstanceOf[CollectionDetails]

  override def getDeliveryDetails: IDeliveryDetails = deliveryDetails
  override def setDeliveryDetails(deliveryDetails: IDeliveryDetails) =
    this.deliveryDetails = deliveryDetails.asInstanceOf[DeliveryDetails]

  override def getOrderLines: Set[ICustomerOrderLine] = orderLines.map(_.asInstanceOf[ICustomerOrderLine]).toSet
  override def addOrderLine(line: ICustomerOrderLine) =
    orderLines.add(line.asInstanceOf[CustomerOrderLine])
  override def removeOrderLine(line: ICustomerOrderLine) =
    orderLines.remove(line.asInstanceOf[CustomerOrderLine])

  override def getStatus: CustomerOrderStatus.Value = status
  private[model] def setStatus(status: CustomerOrderStatus.Value) = this.status = status

  private def getStateObject: CustomerOrderState = stateObjects.get(getStatus.toString).get

  def getStateObjects: Map[String, CustomerOrderState] = stateObjects
  def setStateObjects(stateObjects: Map[String, CustomerOrderState]) = this.stateObjects = stateObjects
}
