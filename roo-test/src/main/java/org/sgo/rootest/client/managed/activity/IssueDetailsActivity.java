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
import org.sgo.rootest.client.scaffold.place.ProxyListPlace;
import org.sgo.rootest.client.scaffold.place.ProxyPlace;
import org.sgo.rootest.client.scaffold.place.ProxyPlace.Operation;

public class IssueDetailsActivity extends IssueDetailsActivity_Roo_Gwt {

    private final PlaceController placeController;

    private final ProxyDetailsView<IssueProxy> view;

    private AcceptsOneWidget display;

    public IssueDetailsActivity(EntityProxyId<org.sgo.rootest.client.managed.request.IssueProxy> proxyId, ApplicationRequestFactory requests, PlaceController placeController, ProxyDetailsView<org.sgo.rootest.client.managed.request.IssueProxy> view) {
        this.placeController = placeController;
        this.proxyId = proxyId;
        this.requests = requests;
        view.setDelegate(this);
        this.view = view;
    }

    public void deleteClicked() {
        if (!view.confirm("Really delete this entry? You cannot undo this change.")) {
            return;
        }
        requests.issueRequest().remove().using(view.getValue()).fire(new Receiver<Void>() {

            public void onSuccess(Void ignore) {
                if (display == null) {
                    return;
                }
                placeController.goTo(getBackButtonPlace());
            }
        });
    }

    public void editClicked() {
        placeController.goTo(getEditButtonPlace());
    }

    public Place getBackButtonPlace() {
        return new ProxyListPlace(IssueProxy.class);
    }

    public String getBackButtonText() {
        return "Back";
    }

    public Place getEditButtonPlace() {
        return new ProxyPlace(view.getValue().stableId(), Operation.EDIT);
    }

    public String getTitleText() {
        return "View Issue";
    }

    public boolean hasEditButton() {
        return true;
    }

    public void onCancel() {
        onStop();
    }

    public void onStop() {
        display = null;
    }

    public void start(AcceptsOneWidget displayIn, EventBus eventBus) {
        this.display = displayIn;
        Receiver<EntityProxy> callback = new Receiver<EntityProxy>() {

            public void onSuccess(EntityProxy proxy) {
                if (proxy == null) {
                    placeController.goTo(getBackButtonPlace());
                    return;
                }
                if (display == null) {
                    return;
                }
                view.setValue((IssueProxy) proxy);
                display.setWidget(view);
            }
        };
        find(callback);
    }
}
