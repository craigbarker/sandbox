package org.sgodden.tom.web

import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.sgodden.tom.services.customerorder.{CustomerOrderListEntry, CustomerOrderService}
import scala.collection.JavaConversions._
import org.springframework.web.bind.annotation._
import javax.servlet.http.HttpServletResponse
import org.sgodden.tom.model.ValidationException
import collection.mutable.{ArrayBuffer, Buffer}
import org.codehaus.jackson.map.{SerializationConfig, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import scala.Predef._

@Controller
@RequestMapping(value = Array("/customerOrders"))
class CustomerOrdersController {

  @Autowired private val orderService: CustomerOrderService = null

  // TODO - jackson generator configuration to not use millisecond timestamps, and scala config
  private final val mapper = {
    val om: ObjectMapper = new ObjectMapper()
    om.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false)
    om.withModule(DefaultScalaModule)
    om
  }
  private def generate(o: AnyRef) = mapper.writeValueAsString(o)

  @RequestMapping(method = Array(RequestMethod.GET))
  @ResponseBody def list: String = {
    val orders = orderService.list
    generate(new ListResponse(true, orders.size, null, orderService.list))
  }

  @RequestMapping(value = Array("/{id}"), method = Array(RequestMethod.GET))
  @ResponseBody def get(@RequestParam("id") id: Long): String = generate(orderService.findById(id))

  @RequestMapping(method = Array(RequestMethod.POST, RequestMethod.PUT))
  @ResponseBody def saveOrUpdate(@RequestBody entry: CustomerOrderListEntry, httpResponse: HttpServletResponse): String = {
    var responseOrder: CustomerOrderListEntry = null
    var success: Boolean = true;
    var errors: Buffer[Error] = ArrayBuffer()

    try {
      var id = entry.getId
      if (id == null) {
        val order = orderService.create
        entry merge order
        id = orderService.persist(order)
      } else {
        orderService.merge(entry)
      }
      responseOrder = orderService.findById(id)
    }
    catch {
      case e: Exception => {
        success = false
        responseOrder = entry
        errors = getErrors(e)
      }
    }
    val orders = ArrayBuffer(responseOrder)
    generate(new ListResponse(success, orders.size, errors, orders))
  }
  
  private def getErrors(e: Exception): Buffer[Error] = {
    val ve: ValidationException = {
      if (e.isInstanceOf[ValidationException])
        e.asInstanceOf[ValidationException]
      else
        e.getCause.getCause.asInstanceOf[ValidationException]
    }
    val errors = new ArrayBuffer[Error]
    for (cv <- ve.getViolations) errors.add(new Error(cv.getPropertyPath.toString, cv.getMessage))
    errors
  }
}

case class ListResponse ( success: Boolean = true,
                    total:Int = 0,
                    errors: Buffer[Error],
                    customerOrders: Buffer[CustomerOrderListEntry])

case class Error(path: String,  message:String)
