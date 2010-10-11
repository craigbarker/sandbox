<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>
  </head>
  <body>
  <g:link action="list">Back to list</g:link>
  <g:form name="updateCustomerOrderForm"
          url="[controller: 'customerOrder', action: 'update']">
    <g:hiddenField name="id" value="${customerOrderInstance.id}"/>
    <table>
      <tr>
        <td><g:message code="org.sgodden.CustomerOrder.id.label"/></td>
        <td><g:fieldValue bean="${customerOrderInstance}" field="id"/></td>
      </tr>
      <tr>
        <td><g:message code="org.sgodden.CustomerOrder.orderNumber.label"/></td>
        <td><g:textField name="orderNumber" 
                         value="${customerOrderInstance.orderNumber}"/></td>
      </tr>
      <tr>
        <td><g:message code="org.sgodden.CustomerOrder.bookingDate.label"/></td>
        <td><joda:datePicker name="bookingDate"
                         value="${customerOrderInstance.bookingDate}"/></td>
      </tr>
      <%--
      --%>
    </table>
    <g:submitButton name="update" value="default.button.update.label"/>
  </g:form>
  </body>
</html>
