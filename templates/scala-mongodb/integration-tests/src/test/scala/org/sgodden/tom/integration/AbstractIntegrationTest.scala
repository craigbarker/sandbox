package org.sgodden.tom.integration

import org.springframework.test.context.ContextConfiguration
import java.util.logging.{Level, Logger}
import org.testng.annotations.BeforeSuite

@ContextConfiguration (locations = Array (
  "/org/sgodden/tom/services/impl/beans.xml",
  "/org/sgodden/tom/persistence/beans.xml",
  "/org/sgodden/tom/model/beans.xml") )
class AbstractIntegrationTest {
  @BeforeSuite def beforeSuite: Unit = {
    Logger.getLogger("org").setLevel(Level.INFO)
  }
}
