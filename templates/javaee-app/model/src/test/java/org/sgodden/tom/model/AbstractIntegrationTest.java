package org.sgodden.tom.model;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations="/org/sgodden/tom/domain/beans.xml")
public class AbstractIntegrationTest extends AbstractTestNGSpringContextTests {
}
