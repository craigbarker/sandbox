package org.sgodden.tom.persistence;

import org.sgodden.tom.model.SomeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeInterfaceImpl implements SomeInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(SomeInterfaceImpl.class);

    public void doSomething() {
        LOGGER.info("SomeInterfaceImpl.doSomething()");
    }

}
