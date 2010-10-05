package org.sgodden.sandbox.aspects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class Dependent {
	
	@Autowired
	private SomeSpringInterface ssi;
	
	public Dependent() {
		ssi.doSomething();
	}

}
