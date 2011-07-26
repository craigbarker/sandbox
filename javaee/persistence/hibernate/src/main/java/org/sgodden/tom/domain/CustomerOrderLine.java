package org.sgodden.tom.domain;

import javax.persistence.*;

@Entity
public class CustomerOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Basic
    private int pieces;

    public long getId() {
        return id;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }
}
