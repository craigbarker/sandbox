package org.sgodden.tom.model;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * An order from a customer to transport goods from an origin
 * to a destination.
 * @author sgodden
 */
@SuppressWarnings("serial")
@Configurable
public class CustomerOrder extends AbstractIdentity implements ICustomerOrder {

    @NotNull
    @Pattern(regexp = "cr.*", message = "{customerReferenceMustBeginWithCr}")
    private String customerReference;
    @NotNull
    private String orderNumber;
    @NotNull
    private CustomerOrderStatus status;
    @NotNull
    private Calendar bookingDate;
    private CollectionDetails collectionDetails;
    private DeliveryDetails deliveryDetails;
    private Set<CustomerOrderLine> orderLines = new HashSet<CustomerOrderLine>();
    @Autowired
    private Map<String, CustomerOrderState> stateObjects;

    public CustomerOrder() {
        status = CustomerOrderStatus.NEW;
        bookingDate = Calendar.getInstance(); // TODO - this should be in user's time zone
    }

    @Override
    public void cancel() {
        getStateObject().cancel(this);
    }

    @Override
    public void confirm() {
        getStateObject().confirm(this);
    }

    @Override
    public void ship() {
        getStateObject().ship(this);
    }

    @Override
    public Calendar getBookingDate() {
        return bookingDate;
    }

    @Override
    public void setBookingDate(Calendar date) {
        this.bookingDate = date;
    }

    @Override
    public String getCustomerReference() {
        return customerReference;
    }

    @Override
    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    /**
     * Returns the company's order number.
     * @return the company's order number.
     */
    @Override
    public String getOrderNumber() {
        return orderNumber;
    }

    @Override
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public ICollectionDetails getCollectionDetails() {
        return collectionDetails;
    }

    @Override
    public void setCollectionDetails(ICollectionDetails collectionDetails) {
        this.collectionDetails = (CollectionDetails) collectionDetails;
    }

    @Override
    public IDeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    @Override
    public void setDeliveryDetails(IDeliveryDetails deliveryDetails) {
        this.deliveryDetails = (DeliveryDetails) deliveryDetails;
    }

    /**
     * Returns an immutable set of the lines on this order.
     * @return an immutable set of the lines on this order.
     */
    @Override
    public Set<ICustomerOrderLine> getOrderLines() {
        Set<ICustomerOrderLine> ret = new HashSet<ICustomerOrderLine>();
        ret.addAll(orderLines);
        return Collections.unmodifiableSet(ret);
    }

    /**
     * Adds a line to this order.
     * @param line the line to add.
     */
    @Override
    public void addOrderLine(ICustomerOrderLine line) {
        orderLines.add((CustomerOrderLine) line);
    }

    /**
     * Removes a line from this order.
     * @param line the line to remove.
     */
    @Override
    public void removeOrderLine(ICustomerOrderLine line) {
        orderLines.remove((CustomerOrderLine) line);
    }

    @Override
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