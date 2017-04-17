<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_All_IndividualInstance" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" odd="oddRow" classes="displayTable" even="evenRow">
  <mjl:context action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="admissionDate">
      <mjl:row>
        <fmt:formatDate value="${item.admissionDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="anaemiaPatient">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="clinicalDiagnosis">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="diedInFacility">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="facilityVisit">
      <mjl:row>
        <fmt:formatDate value="${item.facilityVisit}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labTest">
      <mjl:row>
        ${item.labTest.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="labTestDate">
      <mjl:row>
        <fmt:formatDate value="${item.labTestDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="patientCategory">
      <mjl:row>
        ${item.patientCategory.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pregnant">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="properlyRelease">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="referralReason">
      <mjl:row>
        ${item.referralReason.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="referredFrom">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="referredTo">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="releaseDate">
      <mjl:row>
        <fmt:formatDate value="${item.releaseDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="testSampleDate">
      <mjl:row>
        <fmt:formatDate value="${item.testSampleDate}" pattern="${dateFormatPattern}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="treatment">
      <mjl:row>
        ${item.treatment.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.view.mojo" name="view.link">
          <mdss:localize key="View" />
          <mjl:property value="${item.id}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
