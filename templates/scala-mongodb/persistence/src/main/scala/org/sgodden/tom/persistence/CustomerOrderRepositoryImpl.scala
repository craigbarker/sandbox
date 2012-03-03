package org.sgodden.tom.persistence

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.conversions.scala._
import org.codehaus.jackson.map.ObjectMapper
import de.undercouch.bson4jackson.BsonFactory
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import org.codehaus.jackson.`type`.{JavaType, TypeReference}
import org.bson.{BSON, BSONEncoder, BSONDecoder}
import org.slf4j.{LoggerFactory, Logger}
import javax.validation.{Validation, Validator, ConstraintViolation}
import collection.JavaConverters
import JavaConverters._
import org.sgodden.tom.model.{ValidationException, CustomerOrder, ICustomerOrder, CustomerOrderRepository}

class CustomerOrderRepositoryImpl extends CustomerOrderRepository {
  private final val LOG: Logger = LoggerFactory.getLogger(classOf[CustomerOrderRepositoryImpl])
  private final val validator: Validator = Validation.buildDefaultValidatorFactory.getValidator

  RegisterJodaTimeConversionHelpers()

  val conn = MongoConnection()
  val db = conn("orderManagement")
  val coll = db("customerOrders")

  private val mapper = new ObjectMapper(new BsonFactory())

  def remove(order: ICustomerOrder) {
    coll.remove(MongoDBObject("_id" -> order.getId))
  }

  def findAll = {
    coll.map(dbo => {
      CustomerOrderAdapter(dbo)
    }).toList
  }

  def persist(order: ICustomerOrder) {
    validate(order)
    coll.save(CustomerOrderAdapter(order))
    order.asInstanceOf[CustomerOrder].setId(coll.last.get("_id").toString)
  }

  def merge(order: ICustomerOrder) {
    validate(order)
    coll.save(CustomerOrderAdapter(order))
  }

  def findById(id: String) = {
    CustomerOrderAdapter(coll.findOne(MongoDBObject("_id" -> new ObjectId(id))).get)
  }

  def count = {
    coll.size
  }

  private def validate (order: ICustomerOrder) {
    val constraints = validator.validate(order).asScala.toSet
    if (constraints.size > 0) {
      if (LOG.isDebugEnabled) {
        for (violation <- constraints) {
          LOG.debug(violation.toString)
        }
      }
      throw new ValidationException(constraints.asInstanceOf[Set[ConstraintViolation[AnyRef]]])
    }
  }
}
