package org.sgodden.tom.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sgodden.tom.model.ICustomerOrder;
import org.sgodden.tom.model.services.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderListController {

    @Autowired
    private CustomerOrderService service;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public String listOrders() {
        List<ICustomerOrder> orders = service.findAll();

        JSONObject ret = new JSONObject();
        ret.put("success", "true");
        ret.put("total", orders.size());

        JSONArray jsonOrders = new JSONArray();
        ret.put("orders", jsonOrders);

        for (ICustomerOrder order : orders) {
            JSONObject jsonOrder = new JSONObject();
            jsonOrders.add(jsonOrder);
            jsonOrder.put("orderNumber", order.getOrderNumber());
            jsonOrder.put("reference", order.getCustomerReference());
        }

        return ret.toJSONString();
    }

}
