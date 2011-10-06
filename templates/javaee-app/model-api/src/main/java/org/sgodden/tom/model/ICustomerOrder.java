package org.sgodden.tom.model;

import java.util.Set;

public interface ICustomerOrder extends Identity {

    void cancel();

    void confirm();

    void ship();

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
