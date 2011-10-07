package org.sgodden.tom.integration;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations={
        "/org/sgodden/tom/services/impl/beans.xml",
        "/org/sgodden/tom/persistence/beans.xml",
        "/org/sgodden/tom/model/beans.xml"})
public class AbstractIntegrationTest extends AbstractTestNGSpringContextTests {
}
