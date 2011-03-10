package org.sgodden.tom.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import javax.validation.constraints.NotNull;

import org.sgodden.tom.domain.listener.ValidatorEntityListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * An order from a customer to transport goods from an origin
 * to a destination.
 * @author sgodden
 */
@SuppressWarnings("serial")
@Entity
@EntityListeners({ValidatorEntityListener.class})
@NamedQueries({
    @NamedQuery(name=CustomerOrder.QUERY_COUNT, query="select count(o) from CustomerOrder o"),
    @NamedQuery(name=CustomerOrder.QUERY_FIND_ALL, query="select o from CustomerOrder o")
})
@Configurable
public class CustomerOrder implements Serializable {

    public static final String QUERY_COUNT = "customerOrder.count";
    public static final String QUERY_FIND_ALL = "customerOrder.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @NotNull
    private String customerReference;

    @NotNull
    private String orderNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private CollectionDetails collectionDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private DeliveryDetails deliveryDetails;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "fk_customerorder")
    private Set<CustomerOrderLine> orderLines = new HashSet<CustomerOrderLine>();

    @Autowired
    @Transient
    private SomeInterface someInterface;

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    /**
     * Returns the id of the customer order.
     * @return the id of the customer order
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the version used for optimistic locking.
     * @return the version.
     */
    public Long getVersion() {
        return version;
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
        someInterface.doSomething();
    }

    /**
     * Removes a line from this order.
     * @param line the line to remove.
     */
    public void removeOrderLine(CustomerOrderLine line) {
        orderLines.remove(line);
    }
}