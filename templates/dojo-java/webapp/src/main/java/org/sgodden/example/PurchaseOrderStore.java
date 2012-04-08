package org.sgodden.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PurchaseOrderStore {

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
        if (order.id == null) {
            order.id = nextId;
            nextId++;
        }
        orders.put(order.id, order);
        return order;
    }

    public void remove(int id) {
        orders.remove(id);
    }
    
    private Map<Integer, PurchaseOrder> populate() {
        List<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();
        for (int i = 0; i < 10; i++) {
            orders.add(makePurchaseOrder(nextId));
            nextId++;
        }
        Map<Integer, PurchaseOrder> ret = new HashMap<Integer, PurchaseOrder>();
        for (PurchaseOrder order : orders) {
            ret.put(order.id, order);
        }
        return ret;
    }
    
    private PurchaseOrder makePurchaseOrder(int id) {
        PurchaseOrder ret = new PurchaseOrder();
        ret.id = id;
        ret.orderNumber = "PO " + id;
        ret.customerReference = "Customer Ref " + id;
        return ret;
    }
    
}
