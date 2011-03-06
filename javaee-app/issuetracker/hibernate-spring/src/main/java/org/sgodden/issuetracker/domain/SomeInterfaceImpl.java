package org.sgodden.issuetracker.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeInterfaceImpl implements SomeInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(SomeInterfaceImpl.class);

    public void doSomething() {
        LOGGER.info("SomeInterfaceImpl.doSomething()");
    }

}
