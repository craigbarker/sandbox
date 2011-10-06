package org.sgodden.tom.model.services.impl;

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

    public Long persist(ICustomerOrder customerOrder) {
        repository.persist(customerOrder);
        return (Long) customerOrder.getId();
    }

    public void remove(Long id) {
        repository.remove(repository.findById(id));
    }

    public List<ICustomerOrder> findAll() {
        return repository.findAll();
    }

    public void merge(ICustomerOrder order) {
        repository.merge(order);
    }

    public ICustomerOrder findById(Long id) {
        return repository.findById(id);
    }

}
