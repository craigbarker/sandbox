package org.sgo.rootest.client.managed.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import java.util.HashSet;
import java.util.Set;
import org.sgo.rootest.client.managed.request.IssueProxy;
import org.sgo.rootest.client.managed.ui.IssueListView.Binder;
import org.sgo.rootest.client.scaffold.place.AbstractProxyListView;

public class IssueListView extends IssueListView_Roo_Gwt {

    private static final Binder BINDER = GWT.create(Binder.class);

    private static org.sgo.rootest.client.managed.ui.IssueListView instance;

    @UiField
    Button newButton;

    public IssueListView() {
        init(BINDER.createAndBindUi(this), table, newButton);
        table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
        init();
    }

    public static org.sgo.rootest.client.managed.ui.IssueListView instance() {
        if (instance == null) {
            instance = new IssueListView();
        }
        return instance;
    }

    public String[] getPaths() {
        return paths.toArray(new String[paths.size()]);
    }

    interface Binder extends UiBinder<HTMLPanel, IssueListView> {
    }
}
