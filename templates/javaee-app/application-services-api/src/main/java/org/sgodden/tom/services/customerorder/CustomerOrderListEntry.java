package org.sgodden.tom.services.customerorder;

import org.sgodden.tom.model.ICustomerOrder;

import java.io.Serializable;
import java.util.Calendar;

public class CustomerOrderListEntry {

    private Long id;
    private String customerReference;
    private String orderNumber;
    private Calendar bookingDate;

    public CustomerOrderListEntry() {}

    public CustomerOrderListEntry(ICustomerOrder order) {
        // TODO - dozer?
        this.id = (Long) order.getId();
        this.customerReference = order.getCustomerReference();
        this.orderNumber = order.getOrderNumber();
        this.bookingDate = order.getBookingDate();
    }

    public Long getId() {
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

    public ICustomerOrder merge(ICustomerOrder order) {
        order.setCustomerReference(nullIfEmpty(getCustomerReference()));
        order.setBookingDate(getBookingDate());
        order.setOrderNumber(nullIfEmpty(getOrderNumber()));
        return order;
    }
    
    private <T extends Object> T nullIfEmpty(T value) {
        if (value instanceof String && ((String)value).equals("")) {
            return null;
        }
        return value;
    }
}
