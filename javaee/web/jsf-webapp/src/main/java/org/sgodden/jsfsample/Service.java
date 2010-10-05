package org.sgodden.jsfsample;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="demoService")
@ApplicationScoped
public class Service {

  public String reverse(String name) {
    return new StringBuffer(name).reverse().toString().toLowerCase();
  }
}
