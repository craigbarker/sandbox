package org.sgodden.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

@Path("/")
@Produces("application/json")
@Component
public class PurchaseOrderBean {
    
    private static final Logger LOG = LoggerFactory.getLogger(PurchaseOrderBean.class);

    @Autowired
    private PurchaseOrderStore store;

    @GET
    public List<PurchaseOrder> list() {
        return store.findAll();
    }

    @GET
    @Path("/{id}")
    public PurchaseOrder findById(@PathParam("id") int id) {
        LOG.info("Get: " + id);
        return store.findById(id);
    }

    @POST
    public void save(PurchaseOrder order) {
        store.saveOrUpdate(order);
    }

    @PUT
    public void update(PurchaseOrder order) {
        store.saveOrUpdate(order);
    }

    @DELETE
    @Path("/{id}")
    public void removeOrder(@PathParam("id") int id) {
        store.remove(id);
    }
}
