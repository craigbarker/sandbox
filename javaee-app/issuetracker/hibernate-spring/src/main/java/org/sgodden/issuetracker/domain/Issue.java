package org.sgodden.issuetracker.domain;

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
import javax.persistence.Transient;

import javax.validation.constraints.NotNull;

import org.sgodden.issuetracker.domain.listener.ValidatorEntityListener;
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
    @NamedQuery(name=Issue.QUERY_COUNT, query="select count(o) from Issue o"),
    @NamedQuery(name=Issue.QUERY_FIND_ALL, query="select o from Issue o")
})
@Configurable
public class Issue implements Serializable {

    public static final String QUERY_COUNT = "issue.count";
    public static final String QUERY_FIND_ALL = "issue.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String issueNumber;

    @NotNull
    private String summary;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "fk_customerorder")
    private Set<Comment> comments = new HashSet<Comment>();

    @Autowired
    @Transient
    private SomeInterface someInterface;

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String customerReference) {
        this.issueNumber = customerReference;
    }

    /**
     * Returns the id of the customer order.
     * @return the id of the customer order
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the company's order number.
     * @return the company's order number.
     */
    public String getSummary() {
        return summary;
    }

    public void setSummary(String orderNumber) {
        this.summary = orderNumber;
    }

    /**
     * Returns an immutable set of the lines on this order.
     * @return an immutable set of the lines on this order.
     */
    public Set<Comment> getComments() {
        return Collections.unmodifiableSet(comments);
    }

    /**
     * Adds a line to this order.
     * @param line the line to add.
     */
    public void addComment(Comment line) {
        comments.add(line);
        someInterface.doSomething();
    }

    /**
     * Removes a line from this order.
     * @param line the line to remove.
     */
    public void removeComment(Comment line) {
        comments.remove(line);
    }
}