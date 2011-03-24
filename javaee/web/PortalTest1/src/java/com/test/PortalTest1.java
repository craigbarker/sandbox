package com.test;
import com.test.event.TestEvent;
import javax.portlet.GenericPortlet;
import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;
import javax.portlet.PortletException;
import java.io.IOException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessAction;

/**
 * PortalTest1 Portlet Class
 */
public class PortalTest1 extends GenericPortlet {

    private static int count = 0;
    private String blah = null;

    @ProcessAction(name="DEFAULT")
    public void processActionDefault(ActionRequest request, ActionResponse response) {
        blah = request.getParameter("blah");
        System.out.println("Portlet 1: processActionDefault; " + blah);
        TestEvent event = new TestEvent();
        response.setEvent(TestEvent.QNAME, event);
    }
    
    @Override
    public void doView(RenderRequest request,RenderResponse response) throws PortletException,IOException {
        System.out.println("Portlet 1: doView");
        response.setContentType("text/html");
        request.setAttribute("count", Integer.valueOf(++count));
        request.setAttribute("blah", blah);
        PortletRequestDispatcher dispatcher =
        getPortletContext().getRequestDispatcher("/WEB-INF/jsp/PortalTest1_view.jsp");
        dispatcher.include(request, response);
    }
    @Override
    public void doEdit(RenderRequest request,RenderResponse response) throws PortletException,IOException {
            response.setContentType("text/html");        
        PortletRequestDispatcher dispatcher =
        getPortletContext().getRequestDispatcher("/WEB-INF/jsp/PortalTest1_edit.jsp");
        dispatcher.include(request, response);
    }
    @Override
    public void doHelp(RenderRequest request, RenderResponse response) throws PortletException,IOException {

        response.setContentType("text/html");        
        PortletRequestDispatcher dispatcher =
        getPortletContext().getRequestDispatcher("/WEB-INF/jsp/PortalTest1_help.jsp");
        dispatcher.include(request, response);
    }
}