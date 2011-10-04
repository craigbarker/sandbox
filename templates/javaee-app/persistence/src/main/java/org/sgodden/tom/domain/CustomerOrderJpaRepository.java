package org.sgodden.tom.domain;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerOrderJpaRepository  extends AbstractIdentityJpaRepositoryImpl<CustomerOrder>
        implements CustomerOrderRepository {

    @Override
    protected Class<CustomerOrder> getEntityClass() {
        return CustomerOrder.class;
    }
}