package org.sgodden.tom.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class CustomerOrderNewState implements CustomerOrderState {

    @Autowired
    private CustomerOrderConfirmCommand confirmCommand;

    public void confirm(CustomerOrder order) {
        confirmCommand.execute(order);
    }

    public void ship(CustomerOrder order) {
        throw new IllegalStateException();
    }

    public void cancel(CustomerOrder order) {
        order.setStatus(CustomerOrderStatus.CANCELLED);
    }
}
