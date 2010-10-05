package org.sgodden.tom.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * An order from a customer to transport goods from an origin
 * to a destination.
 * @author sgodden
 */
@Entity
@SuppressWarnings("serial")
public class CustomerOrder implements Serializable {

    private long id;

    private String customerReference;

    private String orderNumber;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @NotNull
    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

}