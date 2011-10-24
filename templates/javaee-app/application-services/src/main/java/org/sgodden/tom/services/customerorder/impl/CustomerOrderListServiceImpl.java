package org.sgodden.tom.services.customerorder.impl;

import org.sgodden.tom.model.CustomerOrderRepository;
import org.sgodden.tom.model.ICustomerOrder;
import org.sgodden.tom.services.customerorder.CustomerOrderListEntry;
import org.sgodden.tom.services.customerorder.CustomerOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
public class CustomerOrderListServiceImpl implements CustomerOrderListService {

    @Autowired
    private CustomerOrderRepository repository;

    @Override
    public List<CustomerOrderListEntry> list() {
        List<CustomerOrderListEntry> ret = new ArrayList<CustomerOrderListEntry>();
        for (ICustomerOrder order : repository.findAll()) {
            ret.add(new CustomerOrderListEntry(order));
        }
        return Collections.unmodifiableList(ret);
    }
}
