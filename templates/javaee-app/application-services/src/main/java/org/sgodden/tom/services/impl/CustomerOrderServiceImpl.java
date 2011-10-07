package org.sgodden.tom.services.impl;

import org.sgodden.tom.model.CustomerOrderFactory;
import org.sgodden.tom.model.CustomerOrderRepository;
import org.sgodden.tom.model.ICustomerOrder;
import org.sgodden.tom.model.services.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void remove(Long id) {
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
