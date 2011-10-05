package org.sgodden.tom.model;

interface CustomerOrderState {

    void confirm(CustomerOrder order);

    void ship(CustomerOrder order);

    void cancel(CustomerOrder order);

    void save(CustomerOrder order);

}
