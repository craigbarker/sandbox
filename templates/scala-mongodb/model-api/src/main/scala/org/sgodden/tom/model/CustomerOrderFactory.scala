package org.sgodden.tom.model

import javax.validation.ConstraintViolation

trait CustomerOrderFactory {
  def create: ICustomerOrder
}

@SuppressWarnings(Array("serial"))
class ValidationException(violations: Set[ConstraintViolation[Serializable]]) extends RuntimeException {

  def getViolations = violations

}


