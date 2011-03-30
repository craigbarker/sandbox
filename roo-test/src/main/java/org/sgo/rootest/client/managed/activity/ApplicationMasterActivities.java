package org.sgo.rootest.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import org.sgo.rootest.client.managed.request.ApplicationEntityTypesProcessor;
import org.sgo.rootest.client.managed.request.ApplicationRequestFactory;
import org.sgo.rootest.client.managed.request.IssueProxy;
import org.sgo.rootest.client.managed.ui.IssueListView;
import org.sgo.rootest.client.managed.ui.IssueMobileListView;
import org.sgo.rootest.client.scaffold.ScaffoldApp;
import org.sgo.rootest.client.scaffold.place.ProxyListPlace;

public final class ApplicationMasterActivities extends ApplicationMasterActivities_Roo_Gwt {

    @Inject
    public ApplicationMasterActivities(ApplicationRequestFactory requests, PlaceController placeController) {
        this.requests = requests;
        this.placeController = placeController;
    }
}
