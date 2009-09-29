<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_IndividualIPT" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.IndividualIPT.form.id" name="dss.vector.solutions.intervention.monitor.IndividualIPT.form.name" method="POST">
    <mjl:input param="id" value="${item.concreteId}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="facility">
        ${item.facility}
      </mjl:dt>
      <mjl:dt attribute="serviceDate">
        <span class="formatDate">${item.serviceDate}</span>
      </mjl:dt>
      <mjl:dt attribute="patientType">
        ${item.patientType.displayLabel}      
      </mjl:dt>
      <mjl:dt attribute="age">
        ${item.age}
      </mjl:dt>
      <mjl:dt attribute="isANCVisit">
        ${item.isANCVisit ? item.isANCVisitMd.positiveDisplayLabel : item.isANCVisitMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="visitNumber">
        ${item.visitNumber.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="doseNumber">
        ${item.doseNumber.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="doseType">
        ${item.doseType.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="recievedSupplement">
        ${item.recievedSupplement ? item.recievedSupplementMd.positiveDisplayLabel : item.recievedSupplementMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="recievedITN">
        ${item.recievedITN ? item.recievedITNMd.positiveDisplayLabel : item.recievedITNMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="numberOfRecievedITNs">
        ${item.numberOfRecievedITNs}
      </mjl:dt>
      <mjl:dt attribute="administratorName">
        ${item.administratorName}
      </mjl:dt>
      <mjl:dt attribute="administratorSurname">
        ${item.administratorSurname}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.intervention.monitor.IndividualIPT.form.edit.button" value="Edit" action="dss.vector.solutions.intervention.monitor.IndividualIPTController.edit.mojo" />
  </mjl:form>
</dl>

<mjl:commandLink name="viewCase.link" action="dss.vector.solutions.intervention.monitor.IndividualIPTCaseController.view.mojo">
  <fmt:message key="View_IPT_Case_Instances" />
  <mjl:property name="id" value="${item.iptCase.id}"/>    
</mjl:commandLink>

<br />

<mjl:commandLink name="search.link" action="dss.vector.solutions.intervention.monitor.IndividualIPTCaseController.search.mojo">
  <fmt:message key="Search_another_individual_IPT_Case" />
</mjl:commandLink>
