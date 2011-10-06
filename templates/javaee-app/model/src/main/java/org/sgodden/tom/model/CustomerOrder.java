package org.sgodden.tom.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An order from a customer to transport goods from an origin
 * to a destination.
 * @author sgodden
 */
@SuppressWarnings("serial")
@Configurable
public class CustomerOrder extends AbstractIdentity {

    @NotNull
    private String customerReference;

    @NotNull
    private String orderNumber;

    @NotNull
    private CustomerOrderStatus status;

    private CollectionDetails collectionDetails;

    private DeliveryDetails deliveryDetails;

    private Set<CustomerOrderLine> orderLines = new HashSet<CustomerOrderLine>();

    @Autowired
    private Map<String, CustomerOrderState> stateObjects;

    public CustomerOrder() {
        status = CustomerOrderStatus.NEW;
    }

    public void cancel() {
        getStateObject().cancel(this);
    }

    public void confirm() {
        getStateObject().confirm(this);
    }

    public void ship() {
        getStateObject().ship(this);
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    /**
     * Returns the company's order number.
     * @return the company's order number.
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public CollectionDetails getCollectionDetails() {
        return collectionDetails;
    }

    public void setCollectionDetails(CollectionDetails collectionDetails) {
        this.collectionDetails = collectionDetails;
    }

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    /**
     * Returns an immutable set of the lines on this order.
     * @return an immutable set of the lines on this order.
     */
    public Set<CustomerOrderLine> getOrderLines() {
        return Collections.unmodifiableSet(orderLines);
    }

    /**
     * Adds a line to this order.
     * @param line the line to add.
     */
    public void addOrderLine(CustomerOrderLine line) {
        orderLines.add(line);
    }

    /**
     * Removes a line from this order.
     * @param line the line to remove.
     */
    public void removeOrderLine(CustomerOrderLine line) {
        orderLines.remove(line);
    }

    public CustomerOrderStatus getStatus() {
        return status;
    }

    // intentionally package private
    void setStatus(CustomerOrderStatus status) {
        this.status = status;
    }

    private CustomerOrderState getStateObject() {
        return stateObjects.get(getStatus().name());
    }

    public Map<String, CustomerOrderState> getStateObjects() {
        return stateObjects;
    }

    public void setStateObjects(Map<String, CustomerOrderState> stateObjects) {
        this.stateObjects = stateObjects;
    }
}