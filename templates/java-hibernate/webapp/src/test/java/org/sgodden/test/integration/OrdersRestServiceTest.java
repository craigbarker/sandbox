package org.sgodden.test.integration;

import com.fasterxml.jackson.module.scala.DefaultScalaModule;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.sgodden.tom.model.CustomerOrder;
import org.sgodden.tom.services.customerorder.CustomerOrderListEntry;
import org.sgodden.tom.web.CustomerOrdersController;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

@Test(groups = "integration")
public class OrdersRestServiceTest {

    private String baseUri = "http://localhost:8080/webapp/services/customerOrders";

    @Test(priority = 1)
    public void shouldBeNoOrders() throws Exception {
//        ListResponse response = listOrders();
//        Assert.assertEquals(response.customerOrders().size(), 0);
    }

    @Test(priority = 2)
    public void testCreateOrder() throws Exception {
        CustomerOrderListEntry order = new CustomerOrderListEntry();
        order.setBookingDate(Calendar.getInstance());
        order.setCustomerReference("CREF001");
        order.setOrderNumber("ORD001");

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(baseUri);
        StringEntity entity = new StringEntity(objectMapper().writeValueAsString(order));
        entity.setContentType("application/json");
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        System.out.println("Create order:" + response.toString());
    }

//    private ListResponse listOrders() throws Exception {
//        String ordersString = getListOrdersResponse();
//        ListResponse response = objectMapper().reader(ListResponse.class)
//                .readValue(ordersString);
//        return response;
//    }

    private ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    private String getListOrdersResponse() throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(baseUri);
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        return getResponseString(entity);
    }

    private String getResponseString(HttpEntity entity) throws Exception {
        if (entity == null) {
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
        StringBuilder ret = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            ret.append(line);
        }
        return ret.toString();
    }

}
