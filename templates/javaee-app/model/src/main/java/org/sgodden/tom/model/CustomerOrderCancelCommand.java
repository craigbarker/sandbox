package org.sgodden.tom.model;

import static org.sgodden.tom.model.CustomerOrderStatus.CANCELLED;

public class CustomerOrderCancelCommand {

    public void cancel(CustomerOrder order) {
        order.setStatus(CANCELLED);
    }

}
