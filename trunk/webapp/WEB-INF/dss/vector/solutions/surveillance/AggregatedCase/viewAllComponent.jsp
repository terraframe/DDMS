<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.surveillance.AggregatedCaseController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="cases">
      <mjl:header>
        Cases
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="casesFemale">
      <mjl:header>
        Female Cases
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="casesMale">
      <mjl:header>
        Male Cases
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="casesPregnant">
      <mjl:header>
        Pregnant Cases
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="clinicallyDiagnosed">
      <mjl:header>
        Cases clinically diagnosed
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="clinicallyDiagnosedDeath">
      <mjl:header>
        Cases clinically diagnosed, deaths
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="daysOutOfStock">
      <mjl:header>
        Days that the facility/s were out of stock of malaria medication
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="deaths">
      <mjl:header>
        Deaths
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="deathsFemale">
      <mjl:header>
        Female Deaths
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="deathsMale">
      <mjl:header>
        Male Deaths
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="deathsPregnant">
      <mjl:header>
        Pregnant Deaths
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="definitivelyDiagnosed">
      <mjl:header>
        Cases definitively diagnosed
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="definitivelyDiagnosedDeath">
      <mjl:header>
        Cases definitively diagnosed, deaths
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="endDate">
      <mjl:header>
        End Date
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:header>
        Geo Entity
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatients">
      <mjl:header>
        In-Patients
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsAnemia">
      <mjl:header>
        In-patients with anaemia
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsClinically">
      <mjl:header>
        In-patients, clinically diagnosed
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsDefinitive">
      <mjl:header>
        In-patients, definitively diagnosed
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsDischarged">
      <mjl:header>
        In-patient discharges
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsFemale">
      <mjl:header>
        In-patient female cases
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsMale">
      <mjl:header>
        In-patient male cases
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsNotTreated">
      <mjl:header>
        Patients who did not receive treatment in-patient
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsPregnantAnemia">
      <mjl:header>
        In-patients, pregnant, with anaemia
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsPregnantDianosis">
      <mjl:header>
        In-patients who were pregnant with definitive diagnosis
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inPatientsTotal">
      <mjl:header>
        In-patients total (not limited to malaria)
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="outPatients">
      <mjl:header>
        Out-Patients
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="outPatientsFemale">
      <mjl:header>
        Out-patient female cases
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="outPatientsMale">
      <mjl:header>
        Out-patient male cases
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="outPatientsNotTreated">
      <mjl:header>
        Patients who did not receive treatment out-patient
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="outPatientsTotal">
      <mjl:header>
        Out-patients total (not limited to malaria)
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="patientsNotTreated">
      <mjl:header>
        Patients who did not receive treatment
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pregnantDiagnosis">
      <mjl:header>
        Patients who were pregnant with definitive diagnosis
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pregnantDiagnosisDeath">
      <mjl:header>
        Patients who were pregnant with definitive diagnosis who died
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="pregnantReferralsReceived">
      <mjl:header>
        Pregnant referrals received from other facilities, definitively diagnosed
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="referralsReceived">
      <mjl:header>
        Referrals received from other facilities
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="referralsSent">
      <mjl:header>
        Referrals to other facilities
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="startDate">
      <mjl:header>
        Start Date
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="stillBirths">
      <mjl:header>
        Still births
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.surveillance.AggregatedCaseController.view.mojo" name="view.link">
          <fmt:message key="View" />
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
<mjl:commandLink action="dss.vector.solutions.surveillance.AggregatedCaseController.newInstance.mojo" name="AggregatedCaseController.newInstance">
<fmt:message key="Create_a_new_Age_Group" />
</mjl:commandLink>
