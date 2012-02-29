package org.sgodden.tom.web

import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import scala.collection.JavaConverters._
import org.springframework.web.bind.annotation._
import javax.servlet.http.HttpServletResponse
import collection.mutable.{ArrayBuffer, Buffer}
import org.codehaus.jackson.map.{SerializationConfig, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.sgodden.tom.model.{ICustomerOrder, ValidationException}
import java.util.Calendar
import org.sgodden.tom.services.customerorder.CustomerOrderService

@Controller
@RequestMapping(value = Array("/customerOrders"))
class CustomerOrdersController {

  @Autowired
  private val service: CustomerOrderService = null

  implicit def orderToListEntry(order: ICustomerOrder): ListEntry = {
    new ListEntry (
      id = order.getId,
      customerReference = order.getCustomerReference,
      orderNumber = order.getOrderNumber,
      bookingDate = order.getBookingDate
    )
  }

  @RequestMapping(method = Array(RequestMethod.GET))
  @ResponseBody
  def list: String ={
    generate(new ListResponse(true, null,
      service.findAll.map(orderToListEntry(_)).toSet))
  }

  @RequestMapping(value = Array("/{id}"), method = Array(RequestMethod.GET))
  @ResponseBody
  def get(@RequestParam("id") id: String): String =
    generate(service.findById(id).asInstanceOf[ListEntry])

  @RequestMapping(method = Array(RequestMethod.POST, RequestMethod.PUT))
  @ResponseBody
  def saveOrUpdate(@RequestBody entryString: String, httpResponse: HttpServletResponse): String = {
    val entry: ListEntry = mapper.readValue(entryString, classOf[ListEntry])
    var responseOrder: ListEntry = null
    var success: Boolean = true;
    var errors: Set[Error] = null

    try {
      var id = entry.id
      println("id is: " + id)
      if (id == null || id == "") {
        val order = service.create
        entry merge order
        id = service persist order
        println("new id is: " + id)
      } else {
        val order = service findById id
        entry merge order
        service merge order
      }
      responseOrder = service findById id
    }
    catch {
      case e: Exception => {
        responseOrder = entry
        if (e.getCause.isInstanceOf[ValidationException])
          errors = getErrors(e.getCause.asInstanceOf[ValidationException])
        else
          throw new RuntimeException(e)
      }
    }
    val orders = ArrayBuffer(responseOrder)
    generate(new ListResponse(success, errors, orders.toSet))
  }
  
  private def getErrors(e: ValidationException) =
    e.getViolations.map(cv => new Error(cv.getPropertyPath.toString, cv.getMessage)).toSet

  private def getRootCause(e: Throwable): Throwable = {
    if (e.getCause == null) e
    else getRootCause(e.getCause)
  }

  // TODO - jackson generator configuration to not use millisecond timestamps, and scala config
  private final val mapper = {
    new ObjectMapper() {
      configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false)
      withModule(DefaultScalaModule)
    }
  }

  private def generate(o: AnyRef) = mapper.writeValueAsString(o)
}

case class ListEntry(
                    id: String,
                    customerReference: String,
                    orderNumber: String,
                    bookingDate: Calendar) {
  def merge(order: ICustomerOrder) = {
    order.setOrderNumber(orderNumber)
    order.setCustomerReference(customerReference)
    order.setBookingDate(bookingDate)
  }
}

case class ListResponse ( success: Boolean = true,
                    errors: Set[Error],
                    customerOrders: Set[ListEntry]) {
  def total = customerOrders.size
}

case class Error(path: String,  message:String)