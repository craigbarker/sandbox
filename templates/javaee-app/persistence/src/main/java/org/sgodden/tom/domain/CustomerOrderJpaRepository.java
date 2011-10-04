package org.sgodden.tom.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerOrderJpaRepository  extends AbstractJpaRepositoryImpl<CustomerOrder>
        implements CustomerOrderRepository {

    @Override
    protected Class<CustomerOrder> getEntityClass() {
        return CustomerOrder.class;
    }
}