package org.sgo.rootest.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.sgo.rootest.client.managed.activity.IssueEditActivityWrapper.View;
import org.sgo.rootest.client.managed.request.ApplicationRequestFactory;
import org.sgo.rootest.client.managed.request.IssueProxy;
import org.sgo.rootest.client.scaffold.activity.IsScaffoldMobileActivity;
import org.sgo.rootest.client.scaffold.place.ProxyEditView;
import org.sgo.rootest.client.scaffold.place.ProxyListPlace;
import org.sgo.rootest.client.scaffold.place.ProxyPlace;

public class IssueEditActivityWrapper extends IssueEditActivityWrapper_Roo_Gwt {

    private final EntityProxyId<IssueProxy> proxyId;

    public IssueEditActivityWrapper(ApplicationRequestFactory requests, View<?> view, Activity activity, EntityProxyId<org.sgo.rootest.client.managed.request.IssueProxy> proxyId) {
        this.requests = requests;
        this.view = view;
        this.wrapped = activity;
        this.proxyId = proxyId;
    }

    public Place getBackButtonPlace() {
        return (proxyId == null) ? new ProxyListPlace(IssueProxy.class) : new ProxyPlace(proxyId, ProxyPlace.Operation.DETAILS);
    }

    public String getBackButtonText() {
        return "Cancel";
    }

    public Place getEditButtonPlace() {
        return null;
    }

    public String getTitleText() {
        return (proxyId == null) ? "New Issue" : "Edit Issue";
    }

    public boolean hasEditButton() {
        return false;
    }

    @Override
    public String mayStop() {
        return wrapped.mayStop();
    }

    @Override
    public void onCancel() {
        wrapped.onCancel();
    }

    @Override
    public void onStop() {
        wrapped.onStop();
    }

    public interface View<V extends org.sgo.rootest.client.scaffold.place.ProxyEditView<org.sgo.rootest.client.managed.request.IssueProxy, V>> extends View_Roo_Gwt<V> {
    }
}
