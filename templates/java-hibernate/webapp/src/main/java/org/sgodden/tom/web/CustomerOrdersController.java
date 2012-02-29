package org.sgodden.tom.web;

import org.sgodden.tom.model.ICustomerOrder;
import org.sgodden.tom.services.customerorder.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Set;

@Controller
@RequestMapping(value = {"/customerOrders"})
public class CustomerOrdersController {

    @Autowired
    private CustomerOrderService service;
    
    public static class ListEntry {
        public Long id;
        public String customerReference;
        public String orderNumber;
        public Calendar bookingDate;

        public ListEntry(Long id, String customerReference, String orderNumber, Calendar bookingDate) {
            this.id = id;
            this.customerReference = customerReference;
            this.orderNumber = orderNumber;
            this.bookingDate = bookingDate;
        }
        
        public void merge(ICustomerOrder order) {
            order.setOrderNumber(orderNumber);
            order.setCustomerReference(customerReference);
            order.setBookingDate(bookingDate);
        }

    }

    public static class ListResponse {
        public boolean success = false;
        public Set<Error> errors;
        public Set<ListEntry> customerOrders;

        public ListResponse(boolean success, Set<Error> errors, Set<ListEntry> customerOrders) {
            this.success = success;
            this.errors = errors;
            this.customerOrders = customerOrders;
        }
    }

    public static class Error {
        public String path;
        public String error;

        public Error(String path, String error) {
            this.path = path;
            this.error = error;
        }
    }

}
