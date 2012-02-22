package org.sgodden.tom.model;

import org.springframework.stereotype.Component;

@Component
public class CustomerOrderConfirmCommand {

    void execute(CustomerOrder order) {
        order.setStatus(CustomerOrderStatus.CONFIRMED);
    }

}
