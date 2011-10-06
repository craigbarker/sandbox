package org.sgodden.tom.model;

public interface ICustomerOrderLine extends Identity {

    String getPackageType();
    void setPackageType(String packageType);

    String getDescriptionOfGoods();
    void setDescriptionOfGoods(String descriptionOfGoods);

}
