package org.sgodden.tom.integration

import org.springframework.test.context.ContextConfiguration
import java.util.logging.{Level, Logger}
import org.testng.annotations.BeforeSuite
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests

@ContextConfiguration (locations = Array (
  "/org/sgodden/tom/services/impl/beans.xml",
  "/org/sgodden/tom/persistence/beans.xml",
  "/org/sgodden/tom/model/beans.xml") )
abstract class AbstractIntegrationTest extends AbstractTestNGSpringContextTests {
  @BeforeSuite def beforeSuite: Unit = {
    Logger.getLogger("org").setLevel(Level.INFO)
  }
}
