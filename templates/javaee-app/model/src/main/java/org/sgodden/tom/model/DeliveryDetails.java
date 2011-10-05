package org.sgodden.tom.model;

import java.io.Serializable;

/**
 */
public class DeliveryDetails implements Serializable {
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
