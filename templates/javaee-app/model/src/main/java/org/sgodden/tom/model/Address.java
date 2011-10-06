package org.sgodden.tom.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A geographical address.
 */
@SuppressWarnings("serial")
public class Address implements Serializable, IAddress {

    @NotNull
    private String line1;
    @NotNull
    private String line2;
    private String line3;
    private String line4;
    @NotNull
    private String town;
    private String postalCode;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getLine4() {
        return line4;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
