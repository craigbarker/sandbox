package org.sgodden.tom.persistence;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations={"/org/sgodden/tom/persistence/beans.xml","/org/sgodden/tom/model/beans.xml"})
public class AbstractIntegrationTest extends AbstractTestNGSpringContextTests {
}
