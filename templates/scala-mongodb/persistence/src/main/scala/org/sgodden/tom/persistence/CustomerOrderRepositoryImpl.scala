package org.sgodden.tom.persistence

import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.conversions.scala._
import model.CustomerOrderP
import org.slf4j.{LoggerFactory, Logger}
import javax.validation.{Validation, Validator, ConstraintViolation}
import collection.JavaConverters
import JavaConverters._
import org.sgodden.tom.model.{ValidationException, CustomerOrder, ICustomerOrder, CustomerOrderRepository}

class CustomerOrderRepositoryImpl extends CustomerOrderRepository {
  private final val LOG: Logger = LoggerFactory.getLogger(classOf[CustomerOrderRepositoryImpl])
  private final val validator: Validator = Validation.buildDefaultValidatorFactory.getValidator

  val conn = MongoConnection()
  val db = conn("orderManagement")
  val coll = db("customerOrders")

  RegisterConversionHelpers()
  RegisterJodaTimeConversionHelpers()

  def remove(order: ICustomerOrder) {
    coll.remove(MongoDBObject("_id" -> new ObjectId(order.getId)))
  }

  def findAll = {
    coll.map(dbo => {
      grater[CustomerOrderP].asObject(dbo).asObject
    }).toList
  }

  def persist(order: ICustomerOrder) {
    validate(order)
    coll.save(grater[CustomerOrderP].asDBObject(CustomerOrderP(order)))
    order.asInstanceOf[CustomerOrder].setId(coll.last.get("_id").toString)
  }

  def merge(order: ICustomerOrder) {
    validate(order)
    coll.save(grater[CustomerOrderP].asDBObject(CustomerOrderP(order)))
  }

  def findById(id: String) =
    grater[CustomerOrderP].asObject(coll.findOne(MongoDBObject("_id" -> new ObjectId(id))).get).asObject

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
