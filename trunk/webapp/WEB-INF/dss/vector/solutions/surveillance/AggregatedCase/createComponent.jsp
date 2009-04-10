<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.AggregatedCase.form.name" id="dss.vector.solutions.surveillance.AggregatedCase.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <input type="hidden" name="#dto.actualType" value="${item.type}DTO" />
    <mjl:input type="hidden" param="geoEntity" value="${item.geoEntity.id}" />
    <mjl:input type="hidden" param="ageGroup" value="${item.ageGroup.id}" />
    <mjl:input type="hidden" param="period" value="${period}" />
    <mjl:input type="hidden" param="periodType" value="${periodType.name}"/>
    <mjl:input type="hidden" param="periodYear" value="${periodYear}"/>
    <dl>
      <dt>
        <label>
          ${item.casesMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="cases" />
        <mjl:messages attribute="cases">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.casesFemaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="casesFemale" />
        <mjl:messages attribute="casesFemale">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.casesMaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="casesMale" />
        <mjl:messages attribute="casesMale">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.casesPregnantMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="casesPregnant" />
        <mjl:messages attribute="casesPregnant">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.clinicallyDiagnosedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="clinicallyDiagnosed" />
        <mjl:messages attribute="clinicallyDiagnosed">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.clinicallyDiagnosedDeathMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="clinicallyDiagnosedDeath" />
        <mjl:messages attribute="clinicallyDiagnosedDeath">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.daysOutOfStockMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="daysOutOfStock" />
        <mjl:messages attribute="daysOutOfStock">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.deathsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="deaths" />
        <mjl:messages attribute="deaths">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.deathsFemaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="deathsFemale" />
        <mjl:messages attribute="deathsFemale">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.deathsMaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="deathsMale" />
        <mjl:messages attribute="deathsMale">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.deathsPregnantMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="deathsPregnant" />
        <mjl:messages attribute="deathsPregnant">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.definitivelyDiagnosedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="definitivelyDiagnosed" />
        <mjl:messages attribute="definitivelyDiagnosed">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.definitivelyDiagnosedDeathMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="definitivelyDiagnosedDeath" />
        <mjl:messages attribute="definitivelyDiagnosedDeath">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inPatientsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inPatients" />
        <mjl:messages attribute="inPatients">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inPatientsAnemiaMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inPatientsAnemia" />
        <mjl:messages attribute="inPatientsAnemia">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inPatientsClinicallyMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inPatientsClinically" />
        <mjl:messages attribute="inPatientsClinically">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inPatientsDefinitiveMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inPatientsDefinitive" />
        <mjl:messages attribute="inPatientsDefinitive">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inPatientsDischargedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inPatientsDischarged" />
        <mjl:messages attribute="inPatientsDischarged">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inPatientsFemaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inPatientsFemale" />
        <mjl:messages attribute="inPatientsFemale">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inPatientsMaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inPatientsMale" />
        <mjl:messages attribute="inPatientsMale">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inPatientsNotTreatedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inPatientsNotTreated" />
        <mjl:messages attribute="inPatientsNotTreated">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inPatientsPregnantAnemiaMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inPatientsPregnantAnemia" />
        <mjl:messages attribute="inPatientsPregnantAnemia">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inPatientsPregnantDianosisMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inPatientsPregnantDianosis" />
        <mjl:messages attribute="inPatientsPregnantDianosis">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.inPatientsTotalMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="inPatientsTotal" />
        <mjl:messages attribute="inPatientsTotal">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.outPatientsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="outPatients" />
        <mjl:messages attribute="outPatients">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.outPatientsFemaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="outPatientsFemale" />
        <mjl:messages attribute="outPatientsFemale">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.outPatientsMaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="outPatientsMale" />
        <mjl:messages attribute="outPatientsMale">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.outPatientsNotTreatedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="outPatientsNotTreated" />
        <mjl:messages attribute="outPatientsNotTreated">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.outPatientsTotalMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="outPatientsTotal" />
        <mjl:messages attribute="outPatientsTotal">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.patientsNotTreatedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="patientsNotTreated" />
        <mjl:messages attribute="patientsNotTreated">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.pregnantDiagnosisMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="pregnantDiagnosis" />
        <mjl:messages attribute="pregnantDiagnosis">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.pregnantDiagnosisDeathMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="pregnantDiagnosisDeath" />
        <mjl:messages attribute="pregnantDiagnosisDeath">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.pregnantReferralsReceivedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="pregnantReferralsReceived" />
        <mjl:messages attribute="pregnantReferralsReceived">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.referralsReceivedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="referralsReceived" />
        <mjl:messages attribute="referralsReceived">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.referralsSentMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="referralsSent" />
        <mjl:messages attribute="referralsSent">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.stillBirthsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="stillBirths" />
        <mjl:messages attribute="stillBirths">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>

  <dl>
    <dt>
      <label>
        Treatment out of Stock
      </label>
    </dt>
    <dd>
      <table class="displayTable">
        <mjl:components items="${stock}" param="stock" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td>
              <mjl:boolean param="outOfStock" trueLabel="Yes" falseLabel="No"/>
              <mjl:messages attribute="outOfStock">
                <mjl:message />
              </mjl:messages>
            </td>
          </tr>
        </mjl:components>
      </table>
    </dd>

    <dt>
      <label>
        Reasons why facility referred the patient to another facility
      </label>
    </dt>
    <dd>
      <table class="displayTable">
        <mjl:components items="${referrals}" param="referrals" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td>
              <mjl:input type="text" param="amount" />
              <mjl:messages attribute="amount">
                <mjl:message />
              </mjl:messages>
            </td>
          </tr>
        </mjl:components>
      </table>
    </dd>

    <dt>
      <label>
        Diagnostic methods
      </label>
    </dt>
    <dd>
      <table class="displayTable">
        <mjl:components items="${diagnostics}" param="diagnosticMethods" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td>
              <mjl:input type="text" param="amount" />
              <mjl:messages attribute="amount">
                <mjl:message />
              </mjl:messages>
            </td>
            <td>
              <mjl:input type="text" param="amountPositive" />
              <mjl:messages attribute="amountPositive">
                <mjl:message />
              </mjl:messages>
            </td>
          </tr>
        </mjl:components>
      </table>
    </dd>

    <dt>
      <label>
        Treatment methods
      </label>
    </dt>
    <dd>
      <table class="displayTable">
        <mjl:components items="${treatmentMethods}" param="treatmentMethods" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td>
              <mjl:input type="text" param="amount" />
              <mjl:messages attribute="amount">
                <mjl:message />
              </mjl:messages>
            </td>
          </tr>
        </mjl:components>
      </table>
    </dd>

    <dt>
      <label>
        Treatments
      </label>
    </dt>
    <dd>
      <table class="displayTable">
        <mjl:components items="${treatments}" param="treatments" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td>
              <mjl:input type="text" param="amount" />
              <mjl:messages attribute="amount">
                <mjl:message />
              </mjl:messages>
            </td>
          </tr>
        </mjl:components>
      </table>
    </dd>

  </dl>

  <mjl:command value="Create" action="dss.vector.solutions.surveillance.AggregatedCaseController.create.mojo" name="dss.vector.solutions.surveillance.AggregatedCase.form.create.button" />
</mjl:form>
