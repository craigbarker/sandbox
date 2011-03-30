package org.sgo.rootest.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.RequestContext;
import org.sgo.rootest.client.managed.request.ApplicationRequestFactory;
import org.sgo.rootest.client.managed.request.IssueProxy;
import org.sgo.rootest.client.managed.request.IssueRequest;
import org.sgo.rootest.client.managed.ui.IssueDetailsView;
import org.sgo.rootest.client.managed.ui.IssueEditView;
import org.sgo.rootest.client.managed.ui.IssueListView;
import org.sgo.rootest.client.managed.ui.IssueMobileDetailsView;
import org.sgo.rootest.client.managed.ui.IssueMobileEditView;
import org.sgo.rootest.client.scaffold.ScaffoldApp;
import org.sgo.rootest.client.scaffold.place.CreateAndEditProxy;
import org.sgo.rootest.client.scaffold.place.FindAndEditProxy;
import org.sgo.rootest.client.scaffold.place.ProxyPlace;

public class IssueActivitiesMapper {

    private final ApplicationRequestFactory requests;

    private final PlaceController placeController;

    public IssueActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }

    public Activity getActivity(ProxyPlace place) {
        switch(place.getOperation()) {
            case DETAILS:
                return new IssueDetailsActivity((EntityProxyId<IssueProxy>) place.getProxyId(), requests, placeController, ScaffoldApp.isMobile() ? IssueMobileDetailsView.instance() : IssueDetailsView.instance());
            case EDIT:
                return makeEditActivity(place);
            case CREATE:
                return makeCreateActivity();
        }
        throw new IllegalArgumentException("Unknown operation " + place.getOperation());
    }

    @SuppressWarnings("unchecked")
    private EntityProxyId<org.sgo.rootest.client.managed.request.IssueProxy> coerceId(ProxyPlace place) {
        return (EntityProxyId<IssueProxy>) place.getProxyId();
    }

    private Activity makeCreateActivity() {
        IssueEditView.instance().setCreating(true);
        final IssueRequest request = requests.issueRequest();
        Activity activity = new CreateAndEditProxy<IssueProxy>(IssueProxy.class, request, ScaffoldApp.isMobile() ? IssueMobileEditView.instance() : IssueEditView.instance(), placeController) {

            @Override
            protected RequestContext createSaveRequest(IssueProxy proxy) {
                request.persist().using(proxy);
                return request;
            }
        };
        return new IssueEditActivityWrapper(requests, ScaffoldApp.isMobile() ? IssueMobileEditView.instance() : IssueEditView.instance(), activity, null);
    }

    private Activity makeEditActivity(ProxyPlace place) {
        IssueEditView.instance().setCreating(false);
        EntityProxyId<IssueProxy> proxyId = coerceId(place);
        Activity activity = new FindAndEditProxy<IssueProxy>(proxyId, requests, ScaffoldApp.isMobile() ? IssueMobileEditView.instance() : IssueEditView.instance(), placeController) {

            @Override
            protected RequestContext createSaveRequest(IssueProxy proxy) {
                IssueRequest request = requests.issueRequest();
                request.persist().using(proxy);
                return request;
            }
        };
        return new IssueEditActivityWrapper(requests, ScaffoldApp.isMobile() ? IssueMobileEditView.instance() : IssueEditView.instance(), activity, proxyId);
    }
}
