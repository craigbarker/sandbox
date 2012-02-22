package org.sgodden.tom.model;

import java.io.Serializable;

public interface Identity extends Serializable {

    Long getId();

    Long getVersion();

}
