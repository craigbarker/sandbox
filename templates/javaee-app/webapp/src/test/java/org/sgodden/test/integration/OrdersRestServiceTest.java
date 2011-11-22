package org.sgodden.test.integration;

import org.testng.annotations.Test;

@Test(groups = "integration")
public class OrdersRestServiceTest {

    private String baseUri = "http://localhost:8080/webapp/services/orders";

    public void testList() {
        System.out.println("ASD");
    }

}
