package org.sgodden.example;

import org.apache.commons.beanutils.BeanComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path("/")
@Produces("application/json")
@Component
public class PurchaseOrderBean {
    
    private static final Logger LOG = LoggerFactory.getLogger(PurchaseOrderBean.class);

    @Autowired
    private PurchaseOrderStore store;

    @GET
    public List<PurchaseOrder> list(@Context HttpServletRequest request) {
        List<PurchaseOrder> ret = store.findAll();
        SortInfo sortInfo = getSortInfo(request.getQueryString());
        if (sortInfo != null) {
            Collections.sort(ret, new BeanComparator(sortInfo.propertyName));
            if (!sortInfo.ascending) {
                Collections.reverse(ret);
            }
        }
        return ret;
    }

    private SortInfo getSortInfo(String queryParams) {
        SortInfo ret = null;
        if (queryParams != null) {
            Pattern p = Pattern.compile(".*(\\+|-)([a-zA-Z]+).*");
            Matcher m = p.matcher(queryParams);
            if (m.find()) {
                String plusMinus = m.group(1);
                ret = new SortInfo();
                ret.ascending = plusMinus.equals("+") ? true : false;
                ret.propertyName = m.group(2);
            }
        }
        return ret;
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

    private static class SortInfo {
        private String propertyName;
        private boolean ascending;
    }
}
