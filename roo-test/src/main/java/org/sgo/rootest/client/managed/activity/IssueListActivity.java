package org.sgo.rootest.client.managed.activity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.view.client.Range;
import java.util.List;
import org.sgo.rootest.client.managed.request.ApplicationRequestFactory;
import org.sgo.rootest.client.managed.request.IssueProxy;
import org.sgo.rootest.client.scaffold.ScaffoldMobileApp;
import org.sgo.rootest.client.scaffold.activity.IsScaffoldMobileActivity;
import org.sgo.rootest.client.scaffold.place.AbstractProxyListActivity;
import org.sgo.rootest.client.scaffold.place.ProxyListView;

public class IssueListActivity extends AbstractProxyListActivity<IssueProxy> implements IsScaffoldMobileActivity {

    private final ApplicationRequestFactory requests;

    public IssueListActivity(ApplicationRequestFactory requests, ProxyListView<org.sgo.rootest.client.managed.request.IssueProxy> view, PlaceController placeController) {
        super(placeController, view, IssueProxy.class);
        this.requests = requests;
    }

    public Place getBackButtonPlace() {
        return ScaffoldMobileApp.ROOT_PLACE;
    }

    public String getBackButtonText() {
        return "Entities";
    }

    public Place getEditButtonPlace() {
        return null;
    }

    public String getTitleText() {
        return "Issues";
    }

    public boolean hasEditButton() {
        return false;
    }

    protected Request<java.util.List<org.sgo.rootest.client.managed.request.IssueProxy>> createRangeRequest(Range range) {
        return requests.issueRequest().findIssueEntries(range.getStart(), range.getLength());
    }

    protected void fireCountRequest(Receiver<Long> callback) {
        requests.issueRequest().countIssues().fire(callback);
    }
}
