package org.sgodden.tom.model;

public class CustomerOrderFactoryImpl implements CustomerOrderFactory {

    public ICustomerOrder create() {
        return new CustomerOrder();
    }

}
