package org.sgodden.tom.web

import org.springframework.beans.factory.annotation.Autowired
import org.codehaus.jackson.map.{SerializationConfig, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.sgodden.tom.model.{ICustomerOrder, ValidationException}
import org.sgodden.tom.services.customerorder.CustomerOrderService
import org.joda.time.DateTime
import org.springframework.stereotype.Component
import javax.ws.rs._
import core.Response

@Component
@Consumes(Array("application/json"))
@Produces(Array("application/json"))
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

  @GET
  def list: String = {
    // for ext
//    generate(new ListResponse(true, null,
//      service.findAll.map(orderToListEntry(_)).toSet))
    // for dojo
    generate(service.findAll.map(orderToListEntry(_)).toSet)
  }

  @GET
  @Path("/{id}")
  def get(@PathParam("id") id: String): String =
    generate(orderToListEntry(service.findById(id)))

  @DELETE
  @Path("/{id}")
  def delete(@PathParam("id") id: String) {
    service.remove(id)
  }

  @PUT
  @POST
  def saveOrUpdate(entryString: String): Response = {
    val entry: ListEntry = mapper.readValue(entryString, classOf[ListEntry])
    var responseOrder: ListEntry = null
    var success: Boolean = true;
    var errors: Set[Error] = null
    var responseEntity: String = null

    try {
      var id = entry.id
      println("id is: " + id)
      if (id == null || id == "") {
        val order = service.create
        entry merge order
        id = service persist order
      } else {
        val order = service findById id
        entry merge order
        service merge order
      }
      responseOrder = service findById id
      responseEntity = generate(responseOrder) // dojo
    }
    catch {
      case ve: ValidationException => {
        success = false
        responseOrder = entry
        errors = getErrors(ve)
        responseEntity = generate(errors) // dojo
        return Response.status(Response.Status.BAD_REQUEST).entity(responseEntity).build()
      }
      case e: Exception => {
        throw new RuntimeException(e)
      }
    }
    // ext
//    val orders = ArrayBuffer(responseOrder)
//    generate(new ListResponse(success, errors, orders.toSet))
    Response.ok(responseEntity).build() // dojo
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
                    bookingDate: DateTime) {
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
