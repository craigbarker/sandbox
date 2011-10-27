package org.sgodden.tom.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.sgodden.tom.model.ICustomerOrder;
import org.sgodden.tom.services.customerorder.CustomerOrderListEntry;
import org.sgodden.tom.services.customerorder.CustomerOrderListService;
import org.sgodden.tom.services.customerorder.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderUpdateController {

    @Autowired
    private CustomerOrderService orderService;

    private int count = 1;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseBody
    public String listOrders(@RequestBody String input) throws Exception {

        System.out.println(input);

        return "";
    }

    public static class Response {
        public boolean success = true;
    }

}
