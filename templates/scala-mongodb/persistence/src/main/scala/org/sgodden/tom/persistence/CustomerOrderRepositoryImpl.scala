package org.sgodden.tom.persistence

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.conversions.scala._
import org.codehaus.jackson.map.ObjectMapper
import de.undercouch.bson4jackson.BsonFactory
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import org.sgodden.tom.model.{CustomerOrder, ICustomerOrder, CustomerOrderRepository}
import org.codehaus.jackson.`type`.{JavaType, TypeReference}
import org.bson.{BSON, BSONEncoder, BSONDecoder}

class CustomerOrderRepositoryImpl extends CustomerOrderRepository {

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
    val result = coll.save(CustomerOrderAdapter(order))
    order.asInstanceOf[CustomerOrder].setId(coll.last.get("_id").toString)
  }

  def merge(order: ICustomerOrder) {
    coll.save(CustomerOrderAdapter(order))
  }

  def findById(id: String) = {
    CustomerOrderAdapter(coll.findOne(MongoDBObject("_id" -> new ObjectId(id))).get)
  }

  def count = {
    coll.size
  }
}
