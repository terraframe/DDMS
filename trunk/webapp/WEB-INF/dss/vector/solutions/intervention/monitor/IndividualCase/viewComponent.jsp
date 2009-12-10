<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_IndividualCase" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.IndividualCase.form.name" id="dss.vector.solutions.intervention.monitor.IndividualCase.form.id" method="POST">
    <%@include file="personHeader.jsp" %>
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="caseReportDate">
        <span class="formatDate">
          ${item.caseReportDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="caseEntryDate">
        <span class="formatDate">
          ${item.createDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="diagnosisDate">
        <span class="formatDate">
          ${item.diagnosisDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="patient">
        ${item.patient.person}
      </mjl:dt>
      <mjl:dt attribute="probableSource">
        ${item.probableSource}
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.IndividualCaseController.edit.mojo" name="dss.vector.solutions.intervention.monitor.IndividualCase.form.edit.button" />
  </mjl:form>
</dl>
<dl>
  <dt> <label><fmt:message key="Case_instances" /> </label></dt>
  <dd>
<mjl:table var="row" query="${query}" odd="oddRow" classes="displayTable" even="evenRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="facilityVisit">
      <mjl:row>
        <fmt:formatDate value="${row.facilityVisit}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="symptomOnset">
      <mjl:row>
        <fmt:formatDate value="${row.symptomOnset}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="clinicalDiagnosis">
    </mjl:attributeColumn>
    <%--
    <mjl:attributeColumn attributeName="admissionDate">
      <mjl:row>
        <fmt:formatDate value="${row.admissionDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="anaemiaPatient">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="diedInFacility">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labTest">
      <mjl:row>
        ${row.labTest.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labTestDate">
      <mjl:row>
        <fmt:formatDate value="${row.labTestDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="patientCategory">
      <mjl:row>
        ${row.patientCategory.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pregnant">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="properlyRelease">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="referralReason">
      <mjl:row>
        ${row.referralReason.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="referredFrom">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="referredTo">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="releaseDate">
      <mjl:row>
        <fmt:formatDate value="${row.releaseDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="testSampleDate">
      <mjl:row>
        <fmt:formatDate value="${row.testSampleDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="treatment">
      <mjl:row>
        ${row.treatment.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    --%>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.view.mojo" name="view.link">
          <fmt:message key="View" />
          <mjl:property value="${row.id}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
</mjl:table>
</dd>

<mjl:commandLink action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.newInstance.mojo" name="IndividualInstanceController.newInstance">
  <fmt:message key="Create_a_new_Individual_Instance" />
  <mjl:property name="caseId" value="${item.id}"/>
</mjl:commandLink>
</dl>

<mjl:commandLink action="dss.vector.solutions.intervention.monitor.IndividualCaseController.newInstance.mojo" name="dss.vector.solutions.intervention.monitor.IndividualCase.viewAll.link">
  <fmt:message key="Search" />
</mjl:commandLink>
