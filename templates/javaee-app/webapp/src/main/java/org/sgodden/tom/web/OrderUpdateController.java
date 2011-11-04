package org.sgodden.tom.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.sgodden.tom.model.ICustomerOrder;
import org.sgodden.tom.services.customerorder.CustomerOrderListEntry;
import org.sgodden.tom.services.customerorder.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class OrderUpdateController {

    private static final Logger LOG = Logger.getLogger(OrderUpdateController.class.getName());

    @Autowired
    private CustomerOrderService orderService;

    private int count = 1;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public String listOrders() throws Exception {

        makeOrder();

        List<CustomerOrderListEntry> orders = orderService.list();

        Response response = new Response();
        response.total = orders.size();
        response.orders = orders;

        String responseString = objectMapper().writeValueAsString(response);

        LOG.info(responseString);

        return responseString;
    }

    private ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    @RequestMapping(value="/orders/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getOrder(@RequestParam("id") Long id) throws Exception {
        return new ObjectMapper().writeValueAsString(orderService.findById(id));
    }

    @RequestMapping(value="/orders", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrder(@RequestBody String requestBody) {
        LOG.info(requestBody);
        return "";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateOrder(@RequestBody String string) throws Exception {
        LOG.info(string);
        CustomerOrderListEntry entry = objectMapper().readValue(string, CustomerOrderListEntry.class);
        orderService.merge(entry);
        return "";
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
