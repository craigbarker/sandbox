package org.sgodden.example;

import java.util.Date;

public class PurchaseOrder {

    private Integer id;
    private String part;
    private String description;
    private String poNumber;
    private Integer committedQuantity;
    private Date committedDate;
    private Integer forecastQuantity;
    private Date forecastDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Integer getCommittedQuantity() {
        return committedQuantity;
    }

    public void setCommittedQuantity(Integer committedQuantity) {
        this.committedQuantity = committedQuantity;
    }

    public Date getCommittedDate() {
        return committedDate;
    }

    public void setCommittedDate(Date committedDate) {
        this.committedDate = committedDate;
    }

    public Integer getForecastQuantity() {
        return forecastQuantity;
    }

    public void setForecastQuantity(Integer forecastQuantity) {
        this.forecastQuantity = forecastQuantity;
    }

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }
}
