<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<portlet:defineObjects />

<b>
    <p>PortalTest1 - VIEW MODE</p>
    <p><c:out value="${count}"/></p>
    <form id="portlet1form" action="<portlet:actionURL/>" method="POST">
        <input type="text" name="blah" value="${blah}"/>
        <input type="hidden" name="<%= ActionRequest.ACTION_NAME %>" value="DEFAULT"/>
        <c:if test="${blah ne null}">
            <p>You typed ${blah}</p>
        </c:if>
        <input type="submit"/>
    </form>
</b>