package org.sgodden.tom.model

import javax.validation.constraints.{Pattern,NotNull}
import collection.mutable.{HashSet, Set => MutableSet}
import org.springframework.beans.factory.annotation.{Configurable, Autowired}
import com.novus.salat.annotations._
import java.util.{Date, Map => JavaMap}
import org.joda.time.{DateTime}
import org.bson.types.ObjectId

// TODO - modify to use joda time
@Configurable
class CustomerOrder() extends ICustomerOrder {

  var _id: String = null

  @NotNull
  @Pattern(regexp = "cr.*", message = "{customerReferenceMustBeginWithCr}")
  var customerReference: String = null
  @NotNull
  var orderNumber: String = null
  @NotNull
  var status: CustomerOrderStatus.Value = CustomerOrderStatus.NEW
  @NotNull
  var bookingDate: DateTime = new DateTime(new Date().getTime)
  var collectionDetails: CollectionDetails = null
  var deliveryDetails: DeliveryDetails = null
  val orderLines: MutableSet[CustomerOrderLine] = new HashSet[CustomerOrderLine]

  @Autowired
  private var stateObjects: JavaMap[String, CustomerOrderState] = null

  def getId = _id

  def setId(id: String) {
    this._id = id
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
  override def setBookingDate(date: DateTime) = this.bookingDate = date

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
  def setStatus(status: CustomerOrderStatus.Value) = this.status = status

  private def getStateObject: CustomerOrderState = stateObjects.get(getStatus.toString)

  def getStateObjects: JavaMap[String, CustomerOrderState] = stateObjects
  def setStateObjects(stateObjects: JavaMap[String, CustomerOrderState]) = this.stateObjects = stateObjects
}
