package org.sgodden.tom.services.customerorder;

import org.sgodden.tom.model.ICustomerOrder;

import java.io.Serializable;
import java.util.Calendar;

public class CustomerOrderListEntry {

    private Serializable id;
    private String customerReference;
    private String orderNumber;
    private Calendar bookingDate;

    public CustomerOrderListEntry(ICustomerOrder order) {
        // TODO - dozer
        this.id = order.getId();
        this.customerReference = order.getCustomerReference();
        this.orderNumber = order.getOrderNumber();
        this.bookingDate = order.getBookingDate();
    }

    public Serializable getId() {
        return id;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Calendar getBookingDate() {
        return bookingDate;
    }
}
