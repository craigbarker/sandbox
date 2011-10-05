package org.sgodden.tom.persistence;

import org.sgodden.tom.model.CustomerOrder;
import org.sgodden.tom.model.CustomerOrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerOrderJpaRepository  extends AbstractIdentityJpaRepositoryImpl<CustomerOrder>
        implements CustomerOrderRepository {

    @Override
    protected Class<CustomerOrder> getEntityClass() {
        return CustomerOrder.class;
    }
}