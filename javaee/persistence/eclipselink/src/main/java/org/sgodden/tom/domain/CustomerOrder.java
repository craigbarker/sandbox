package org.sgodden.tom.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.sgodden.tom.domain.listener.ValidatorEntityListener;

/**
 * An order from a customer to transport goods from an origin
 * to a destination.
 * @author sgodden
 */
@SuppressWarnings("serial")
@Entity
@EntityListeners({ValidatorEntityListener.class})
public class CustomerOrder implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private long id;

    @NotNull
    private String customerReference;

    @NotNull
    private String orderNumber;

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

}