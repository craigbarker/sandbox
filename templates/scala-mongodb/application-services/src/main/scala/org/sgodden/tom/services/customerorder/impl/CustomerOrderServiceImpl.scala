package org.sgodden.tom.services.customerorder.impl

import org.sgodden.tom.services.customerorder.CustomerOrderService
import org.sgodden.tom.model.{ICustomerOrder, CustomerOrderFactory, CustomerOrderRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerOrderServiceImpl extends CustomerOrderService {

  @Autowired private var repository: CustomerOrderRepository = null
  @Autowired private var factory: CustomerOrderFactory = null

  override def create: ICustomerOrder = factory.create

  override def persist(customerOrder: ICustomerOrder) = {
    repository.persist(customerOrder)
    customerOrder.getId
  }

  override def remove(id: String) = repository.remove(repository.findById(id))

  override def findAll: List[ICustomerOrder] = repository.findAll

  override def merge(order: ICustomerOrder) =  repository merge order

  override def findById(id: String): ICustomerOrder = repository.findById(id)

}
