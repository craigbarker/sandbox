package org.sgodden.tom.integration;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeSuite;

import java.util.logging.Level;
import java.util.logging.Logger;

@ContextConfiguration(locations={
        "/org/sgodden/tom/services/impl/beans.xml",
        "/org/sgodden/tom/persistence/beans.xml",
        "/org/sgodden/tom/model/beans.xml"})
public class AbstractIntegrationTest extends AbstractTestNGSpringContextTests {

    @BeforeSuite
    public void beforeSuite() {
        Logger.getLogger("org").setLevel(Level.INFO);
    }

}
