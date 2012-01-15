package org.sgodden.tom.web;

import java.util.ArrayList;
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
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import org.apache.http.HttpStatus;
import org.sgodden.tom.model.ValidationException;

@Controller
@RequestMapping(value="/customerOrders")
public class CustomerOrderController {

    private static final Logger LOG = Logger.getLogger(CustomerOrderController.class.getName());

    @Autowired
    private CustomerOrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String listOrders() throws Exception {
        // FIXME - dates being returned to milliseconds by default Spring JSON provider
        List<CustomerOrderListEntry> orders = orderService.list();
        ListResponse response = new ListResponse();
        response.total = orders.size();
        response.customerOrders = orders;
        return toJson(response);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getOrder(@RequestParam("id") Long id) throws Exception {
        return toJson(orderService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String saveOrder(@RequestBody CustomerOrderListEntry requestBody, HttpServletResponse httpResponse) throws Exception {
        ICustomerOrder order = orderService.create();
        requestBody.merge(order);
        ListResponse response = new ListResponse();
        CustomerOrderListEntry responseOrder;
        try {
            Long id = orderService.persist(order);
            responseOrder = orderService.findById(id);
        } catch(ValidationException e) {
            httpResponse.setStatus(HttpStatus.SC_NOT_ACCEPTABLE);
            response.success = false;
            responseOrder = requestBody;
            for (ConstraintViolation cv : e.getViolations()) {
                Error error = new Error();
                error.path = cv.getPropertyPath().toString();
                error.message = cv.getMessage();
                response.errors.add(error);
            }
        }
        response.customerOrders.add(responseOrder);
        return toJson(response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateOrder(@RequestBody String string) throws Exception {
        CustomerOrderListEntry entry = fromJson(string, CustomerOrderListEntry.class);
        orderService.merge(entry);
        return "";
    }
    
    private String toJson(Object o) throws Exception {
        return objectMapper().writeValueAsString(o);
    }
    
    private <T extends Object> T fromJson(String s, Class<T> type) throws Exception {
        return objectMapper().readValue(s, type);
    }

    private ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    public static class ListResponse {
        public boolean success = true;
        public int total = 0;
        public List<Error> errors = new ArrayList<Error>();
        public List<CustomerOrderListEntry> customerOrders = new ArrayList<CustomerOrderListEntry>();
    }
    
    public static class Error {
        public String path;
        public String message;
    }

}
