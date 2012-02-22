package org.sgodden.tom.model;


/**
 */
public class DeliveryDetails implements IDeliveryDetails {
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(IAddress address) {
        this.address = (Address) address;
    }
}
