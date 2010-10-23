package org.sgodden.tom.domain;

import com.google.appengine.api.datastore.Key;
import java.io.Serializable;
import javax.jdo.annotations.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 */
@Entity
public class CollectionDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;

    @Embedded
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
