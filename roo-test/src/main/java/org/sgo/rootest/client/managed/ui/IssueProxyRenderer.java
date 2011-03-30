package org.sgo.rootest.client.managed.ui;

import com.google.gwt.requestfactory.ui.client.ProxyRenderer;
import org.sgo.rootest.client.managed.request.IssueProxy;

public class IssueProxyRenderer extends ProxyRenderer<IssueProxy> {

    private static org.sgo.rootest.client.managed.ui.IssueProxyRenderer INSTANCE;

    protected IssueProxyRenderer() {
        super(new String[] { "summary" });
    }

    public static org.sgo.rootest.client.managed.ui.IssueProxyRenderer instance() {
        if (INSTANCE == null) {
            INSTANCE = new IssueProxyRenderer();
        }
        return INSTANCE;
    }

    public String render(IssueProxy object) {
        if (object == null) {
            return "";
        }
        return object.getSummary() + " (" + object.getId() + ")";
    }
}
