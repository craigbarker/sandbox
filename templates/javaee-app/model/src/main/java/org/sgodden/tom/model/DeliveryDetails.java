package org.sgodden.tom.model;

import java.io.Serializable;

/**
 */
public class DeliveryDetails implements Serializable, IDeliveryDetails {
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(IAddress address) {
        this.address = (Address) address;
    }
}
