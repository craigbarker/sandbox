package org.sgodden.tom.model;

import static org.sgodden.tom.model.CustomerOrderStatus.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class CustomerOrderNewState extends AbstractCustomerOrderState{

    @Autowired
    private CustomerOrderConfirmCommand confirmCommand;

    public void confirm(CustomerOrder order) {
        confirmCommand.execute(order);
    }

    public void ship(CustomerOrder order) {
        throw new IllegalStateException("An order may not be shipped from the new state");
    }

    public void cancel(CustomerOrder order) {
        // TODO - defer to cancel command
        order.setStatus(CANCELLED);
    }

    @Override
    public void save(CustomerOrder order) {
        order.setStatus(REQUESTED);
    }
}
