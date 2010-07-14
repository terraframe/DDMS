<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_IndividualIPTCase" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<table class="displayTable">
  <tr>
    <th> ${view.patientMd.displayLabel} </th>
    <th> ${view.serviceDateMd.displayLabel} </th>
    <th> ${view.facilityMd.displayLabel} </th>
    <th> </th>
  </tr>
  <c:forEach items="${cases}" var="current" varStatus="status">
    <tr>
      <td class = "${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
        ${current.patient.firstName} ${current.patient.lastName}
      </td>
      <td class = "${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}"> 
        <span class="formatDate">${current.serviceDate}</span>
      </td>
      <td class = "${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}"> 
        ${current.facilityName}
      </td>
      <td class = "${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}"> 
        <mjl:commandLink name="view.link" action="dss.vector.solutions.intervention.monitor.IndividualIPTCaseController.view.mojo">
          <fmt:message key="View" />
          <mjl:property name="id" value="${current.concreteId}" />
          <mjl:property name="serviceDate" value="${serviceDate}"/>
        </mjl:commandLink>
      </td>
    </tr>
  </c:forEach>
</table>
<br />
<mjl:commandLink name="IndividualIPTCaseController.newInstance" action="dss.vector.solutions.intervention.monitor.IndividualIPTCaseController.newInstance.mojo">
  <mjl:property name="patientId" value="${patientId}"/>
  <mjl:property name="serviceDate" value="${serviceDate}"/>
  <fmt:message key="Create_a_new_Individual_IPT_Case" />
</mjl:commandLink>
