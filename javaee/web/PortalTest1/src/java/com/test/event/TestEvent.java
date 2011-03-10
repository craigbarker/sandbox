package com.test.event;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

@XmlRootElement
public class TestEvent implements Serializable {

    public static final QName QNAME = new QName("http://test.com/events",
            "TestEvent");

    private String foo;

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

}
