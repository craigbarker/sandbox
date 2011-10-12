package org.sgodden.tom.model;

import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.Set;

public interface ICustomerOrder extends Identity {

    void cancel();

    void confirm();

    void ship();

    Calendar getBookingDate();
    void setBookingDate(Calendar bookingDate);

    String getCustomerReference();
    void setCustomerReference(String customerReference);

    String getOrderNumber();
    void setOrderNumber(String orderNumber);

    ICollectionDetails getCollectionDetails();
    void setCollectionDetails(ICollectionDetails collectionDetails);

    IDeliveryDetails getDeliveryDetails();
    void setDeliveryDetails(IDeliveryDetails deliveryDetails);

    Set<ICustomerOrderLine> getOrderLines();
    void addOrderLine(ICustomerOrderLine line);
    void removeOrderLine(ICustomerOrderLine line);

    CustomerOrderStatus getStatus();

}
