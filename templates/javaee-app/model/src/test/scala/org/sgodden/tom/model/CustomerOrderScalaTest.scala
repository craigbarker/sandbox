package org.sgodden.tom.model

import org.testng.annotations.Test
import org.testng.Assert._
import javax.validation.Validation

@Test
class CustomerOrderScalaTest {

  private val validator = Validation.buildDefaultValidatorFactory.getValidator

  def testValidation {
    assertEquals(validator.validate(new CustomerOrder).size, 2, "Wrong number of violations")
  }

}