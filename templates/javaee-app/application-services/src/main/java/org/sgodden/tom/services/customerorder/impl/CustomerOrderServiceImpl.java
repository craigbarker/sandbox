package org.sgodden.tom.services.customerorder.impl;

import org.sgodden.tom.model.CustomerOrderFactory;
import org.sgodden.tom.model.CustomerOrderRepository;
import org.sgodden.tom.model.ICustomerOrder;
import org.sgodden.tom.services.customerorder.CustomerOrderListEntry;
import org.sgodden.tom.services.customerorder.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository repository;

    @Autowired
    private CustomerOrderFactory factory;

    @Override
    public ICustomerOrder create() {
        return factory.create();
    }

    @Override
    public Long persist(ICustomerOrder customerOrder) {
        repository.persist(customerOrder);
        return (Long) customerOrder.getId();
    }

    @Override
    public void remove(Serializable id) {
        repository.remove(repository.findById(id));
    }

    @Override
    public List<CustomerOrderListEntry> list() {
        List<CustomerOrderListEntry> ret = new ArrayList<CustomerOrderListEntry>();
        for (ICustomerOrder order : repository.findAll()) {
            ret.add(new CustomerOrderListEntry(order));
        }
        return Collections.unmodifiableList(ret);
    }

    @Override
    public void merge(CustomerOrderListEntry order) {
        repository.merge(order.merge(repository.findById(order.getId())));
    }

    @Override
    public CustomerOrderListEntry findById(Long id) {
        return new CustomerOrderListEntry(repository.findById(id));
    }

}
