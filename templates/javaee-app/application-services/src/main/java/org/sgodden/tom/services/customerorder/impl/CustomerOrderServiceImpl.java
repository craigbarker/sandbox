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
        return customerOrder.getId();
    }

    @Override
    public void remove(Serializable id) {
        repository.remove(repository.findById(id));
    }

    @Override
    public List<ICustomerOrder> findAll() {
        return repository.findAll();
    }

    @Override
    public void merge(ICustomerOrder order) {
        repository.merge(order);
    }

    @Override
    public ICustomerOrder findById(Long id) {
        return repository.findById(id);
    }

}
