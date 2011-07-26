package org.sgodden.tom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * An order from a customer to transport goods from an origin
 * to a destination.
 *
 * @author sgodden
 */
@Entity
@SuppressWarnings("serial")
public class CustomerOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Basic
    @NotNull
    private String customerReference;

    @Basic
    @NotNull
    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CustomerOrderLine> lines = new ArrayList<CustomerOrderLine>();

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

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public List<CustomerOrderLine> getLines() {
        return lines;
    }
}