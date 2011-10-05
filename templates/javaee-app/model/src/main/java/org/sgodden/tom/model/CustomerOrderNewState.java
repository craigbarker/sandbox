package org.sgodden.tom.model;

public class CustomerOrderNewState implements CustomerOrderState {

    public void confirm(CustomerOrder order) {
        order.setStatus(CustomerOrderStatus.CONFIRMED);
    }

    public void ship(CustomerOrder order) {
        throw new IllegalStateException();
    }

    public void cancel(CustomerOrder order) {
        order.setStatus(CustomerOrderStatus.CANCELLED);
    }
}
