package org.sgodden.example;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class PurchaseOrderStore {

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private int nextId = 1;
    private Map<Integer, PurchaseOrder> orders = populate();

    public List<PurchaseOrder> findAll() {
        List<PurchaseOrder> ret = new ArrayList<PurchaseOrder>();
        ret.addAll(orders.values());
        return ret;
    }

    public PurchaseOrder findById(int id) {
        return orders.get(id);
    }
    
    public PurchaseOrder saveOrUpdate(PurchaseOrder order) {
        if (order.getId() == null) {
            order.setId(nextId);
            nextId++;
        }
        orders.put(order.getId(), order);
        return order;
    }

    public void remove(int id) {
        orders.remove(id);
    }
    
    private Map<Integer, PurchaseOrder> populate() {
        ObjectMapper mapper = new ObjectMapper();
        PurchaseOrder[] orders = new PurchaseOrder[0];
        try {
            orders = mapper.readValue(
                    getClass().getClassLoader().getResource("/purchase-orders.json"),
                    PurchaseOrder[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<Integer, PurchaseOrder> ret = new HashMap<Integer, PurchaseOrder>();
        for (PurchaseOrder order : orders) {
            ret.put(order.getId(), order);
        }
        for (int i = 10000; i < 10010; i++) {
            PurchaseOrder po = makePurchaseOrder(i);
            ret.put(po.getId(), po);
            nextId = i;
        }
        nextId++;
        return ret;
    }
    
    private PurchaseOrder makePurchaseOrder(int id) {
        PurchaseOrder ret = new PurchaseOrder();
        ret.setId(id);
        ret.setPoNumber("PO " + id);
        ret.setPart("PART-" + id);
        ret.setDescription("Exhaust Set MRL");
        try {
            ret.setCommittedDate(df.parse("2012-08-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ret.setCommittedQuantity(5);
        ret.setForecastDate(new Date());
        ret.setForecastQuantity(5);
        return ret;
    }
    
}
