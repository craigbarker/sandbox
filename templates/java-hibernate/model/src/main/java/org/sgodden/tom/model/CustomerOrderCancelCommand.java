package org.sgodden.tom.model;

import org.springframework.stereotype.Component;

import static org.sgodden.tom.model.CustomerOrderStatus.CANCELLED;

@Component
public class CustomerOrderCancelCommand {

    public void cancel(CustomerOrder order) {
        order.setStatus(CANCELLED);
    }

}
