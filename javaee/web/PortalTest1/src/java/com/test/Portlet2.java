package com.test;

import javax.portlet.GenericPortlet;
import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.ProcessEvent;

/**
 * Portlet2 Portlet Class
 */
public class Portlet2 extends GenericPortlet {

    private boolean eventReceived = false;

    @Override
    public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
    }

    @ProcessEvent(qname = "{http://test.com/events}TestEvent")
    public void processTestEvent(EventRequest request, EventResponse response) {
        System.out.println("Portlet 2 responding to test event");
        eventReceived = true;
    }

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("View Mode portlet 2");
        if (eventReceived) {
            writer.println("<p>Event received!</p>");
            eventReceived = false;
        }
    }

    @Override
    public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("Edit Mode portlet 2");
    }

    @Override
    public void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("Help Mode portlet 2");
    }
}
