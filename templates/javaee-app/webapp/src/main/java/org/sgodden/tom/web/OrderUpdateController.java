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
@RequestMapping(value="/customerOrders")
public class OrderUpdateController {

    private static final Logger LOG = Logger.getLogger(OrderUpdateController.class.getName());

    @Autowired
    private CustomerOrderService orderService;

    private int count = 1;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listOrders() throws Exception {
        List<CustomerOrderListEntry> orders = orderService.list();
        Response response = new Response();
        response.total = orders.size();
        response.orders = orders;
        String responseString = objectMapper().writeValueAsString(response);
        LOG.info("List orders");
        return responseString;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getOrder(@RequestParam("id") Long id) throws Exception {
        return new ObjectMapper().writeValueAsString(orderService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String saveOrder(@RequestBody CustomerOrderListEntry requestBody) throws Exception{
        LOG.info(requestBody.toString());
        ICustomerOrder order = orderService.create();
        requestBody.merge(order);
        Long id = orderService.persist(order);
        LOG.info("Saved order");
        return objectMapper().writeValueAsString(orderService.findById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateOrder(@RequestBody String string) throws Exception {
        CustomerOrderListEntry entry = objectMapper().readValue(string, CustomerOrderListEntry.class);
        orderService.merge(entry);
        return "";
    }

    private ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    public static class Response {
        public boolean success = true;
        public int total = 0;
        public List<CustomerOrderListEntry> orders;
    }

}
