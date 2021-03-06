// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package org.sgo.rootest.client.managed.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import java.util.Set;
import org.sgo.rootest.client.managed.request.ApplicationRequestFactory;
import org.sgo.rootest.client.managed.request.IssueProxy;
import org.sgo.rootest.client.scaffold.activity.IsScaffoldMobileActivity;
import org.sgo.rootest.client.scaffold.place.ProxyDetailsView;
import org.sgo.rootest.client.scaffold.place.ProxyDetailsView.Delegate;
import org.sgo.rootest.client.scaffold.place.ProxyListPlace;
import org.sgo.rootest.client.scaffold.place.ProxyPlace;
import org.sgo.rootest.client.scaffold.place.ProxyPlace.Operation;

public abstract class IssueDetailsActivity_Roo_Gwt extends AbstractActivity implements Delegate, IsScaffoldMobileActivity {

    protected ApplicationRequestFactory requests;

    protected EntityProxyId<IssueProxy> proxyId;

    protected void find(Receiver<EntityProxy> callback) {
        requests.find(proxyId).with().fire(callback);
    }
}
