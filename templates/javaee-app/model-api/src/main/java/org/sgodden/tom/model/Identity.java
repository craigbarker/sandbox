package org.sgodden.tom.model;

import java.io.Serializable;

public interface Identity extends Serializable {

    Serializable getId();

    Long getVersion();

}
