package org.sgodden.test.integration;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

@Test(groups = "integration")
public class OrdersRestServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(OrdersRestServiceTest.class);

    private String baseUri = "http://localhost:8080/webapp/services/orders";

    public void testList() throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(baseUri);
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        LOG.info(getResponse(entity));
    }

    private String getResponse(HttpEntity entity) throws Exception {
        if (entity == null) {
            return  null;
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
