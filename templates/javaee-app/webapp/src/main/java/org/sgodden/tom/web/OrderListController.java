package org.sgodden.tom.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.sgodden.tom.model.ICustomerOrder;
import org.sgodden.tom.services.customerorder.CustomerOrderListEntry;
import org.sgodden.tom.services.customerorder.CustomerOrderListService;
import org.sgodden.tom.services.customerorder.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderListController {

    @Autowired
    private CustomerOrderListService listService;
    @Autowired
    private CustomerOrderService orderService;

    private int count = 1;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public String listOrders() throws Exception {

        makeOrder();

        List<CustomerOrderListEntry> orders = listService.list();

        Response response = new Response();
        response.total = orders.size();
        response.orders = orders;

        return new ObjectMapper().writeValueAsString(response);
    }

    private void makeOrder() {
        ICustomerOrder order = orderService.create();
        order.setOrderNumber("ORD" + count);
        order.setCustomerReference("CREF" + count);
        orderService.persist(order);
        count++;
    }

    public static class Response {
        public boolean success = true;
        public int total = 0;
        public List<CustomerOrderListEntry> orders;
    }

}
