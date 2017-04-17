<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_AggregatedCase" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.surveillance.AggregatedCaseController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="ageGroup">
      <mjl:row>
        ${item.ageGroup.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="cases">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="casesFemale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="casesMale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="casesPregnant">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="clinicallyDiagnosed">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="clinicallyDiagnosedDeath">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="daysOutOfStock">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="deaths">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="deathsFemale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="deathsMale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="deathsPregnant">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="definitivelyDiagnosed">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="definitivelyDiagnosedDeath">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="endAge">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="endDate">
      <mjl:row>
        <fmt:formatDate pattern="${dateFormatPattern}" value="${item.endDate}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:row>
        ${item.geoEntity.geoId}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatients">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsAnemia">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsClinically">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsDefinitive">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsDischarged">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsFemale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsMale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsNotTreated">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsPregnantAnemia">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsPregnantDianosis">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsTotal">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="outPatients">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="outPatientsFemale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="outPatientsMale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="outPatientsNotTreated">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="outPatientsTotal">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="patientsNotTreated">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pregnantDiagnosis">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pregnantDiagnosisDeath">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pregnantReferralsReceived">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="referralsReceived">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="referralsSent">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="startAge">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="startDate">
      <mjl:row>
        <fmt:formatDate pattern="${dateFormatPattern}" value="${item.startDate}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="stillBirths">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.surveillance.AggregatedCaseController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.id}" />
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
<mjl:commandLink name="AggregatedCaseController.newInstance" action="dss.vector.solutions.surveillance.AggregatedCaseController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Aggregated_Case" />
</mjl:commandLink>
