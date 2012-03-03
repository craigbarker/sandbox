package org.sgodden.tom.model

import javax.validation.ConstraintViolation

@SuppressWarnings(Array("serial"))
class ValidationException(violations: Set[ConstraintViolation[AnyRef]]) extends RuntimeException {

  def getViolations = violations

}
