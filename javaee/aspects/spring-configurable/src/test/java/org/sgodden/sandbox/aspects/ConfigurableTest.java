package org.sgodden.sandbox.aspects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class ConfigurableTest {
	
	ApplicationContext ctx;
	
	@BeforeClass
	public void beforeClass() {
		ctx = new ClassPathXmlApplicationContext("/beans.xml");
	}
	
	public void testConfigure() {
		new Dependent();
	}

}
