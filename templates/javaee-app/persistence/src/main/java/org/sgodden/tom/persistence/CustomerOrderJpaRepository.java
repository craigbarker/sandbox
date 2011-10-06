package org.sgodden.tom.persistence;

import org.sgodden.tom.model.CustomerOrder;
import org.sgodden.tom.model.CustomerOrderRepository;
import org.sgodden.tom.model.ICustomerOrder;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerOrderJpaRepository  extends AbstractIdentityJpaRepositoryImpl<ICustomerOrder>
        implements CustomerOrderRepository {

    @Override
    protected Class<? extends ICustomerOrder> getEntityClass() {
        return CustomerOrder.class;
    }
}