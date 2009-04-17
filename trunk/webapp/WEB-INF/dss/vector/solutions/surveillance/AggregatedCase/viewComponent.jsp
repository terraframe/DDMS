<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="search.form.name" id="search.form" method="POST">
  <mjl:input type="hidden" param="geoId" value="${item.geoEntity.geoId}" />
  <mjl:input type="hidden" param="period" value="${item.period}" />
  <mjl:input type="hidden" param="periodType" value="${item.periodTypeEnumNames[0]}"/>
  <mjl:input type="hidden" param="year" value="${item.periodYear}"/>
  ${item.ageGroupMd.displayLabel}
  <mjl:select var="current" valueAttribute="id" items="${ageGroups}" param="ageGroup.componentId">
    <mjl:option selected="${current.id == item.ageGroup.id ? 'selected' : 'false'}">
      ${current.displayLabel}
    </mjl:option>
  </mjl:select>
  <mjl:command classes="submitButton" action="dss.vector.solutions.surveillance.AggregatedCaseController.searchByGeoIdAndEpiWeek.mojo" name="search.button" value="Change age group" />
</mjl:form>

<mjl:form name="dss.vector.solutions.surveillance.AggregatedCase.form.name" id="dss.vector.solutions.surveillance.AggregatedCase.form.id" method="POST">
  <mjl:input value="${item.caseId}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.casesMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.cases}
    </dd>
    <dt>
      <label>
        ${item.casesFemaleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.casesFemale}
    </dd>
    <dt>
      <label>
        ${item.casesMaleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.casesMale}
    </dd>
    <dt>
      <label>
        ${item.casesPregnantMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.casesPregnant}
    </dd>
    <dt>
      <label>
        ${item.clinicallyDiagnosedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.clinicallyDiagnosed}
    </dd>
    <dt>
      <label>
        ${item.clinicallyDiagnosedDeathMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.clinicallyDiagnosedDeath}
    </dd>
    <dt>
      <label>
        ${item.daysOutOfStockMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.daysOutOfStock}
    </dd>
    <dt>
      <label>
        ${item.deathsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.deaths}
    </dd>
    <dt>
      <label>
        ${item.deathsFemaleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.deathsFemale}
    </dd>
    <dt>
      <label>
        ${item.deathsMaleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.deathsMale}
    </dd>
    <dt>
      <label>
        ${item.deathsPregnantMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.deathsPregnant}
    </dd>
    <dt>
      <label>
        ${item.definitivelyDiagnosedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.definitivelyDiagnosed}
    </dd>
    <dt>
      <label>
        ${item.definitivelyDiagnosedDeathMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.definitivelyDiagnosedDeath}
    </dd>
    <dt>
      <label>
        ${item.geoEntityMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.geoEntity.keyName}" action="dss.vector.solutions.geo.generated.GeoEntityController.view.mojo" name="dss.vector.solutions.geo.generated.GeoEntity.form.view.link">
        <mjl:property value="${item.geoEntity.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.inPatientsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inPatients}
    </dd>
    <dt>
      <label>
        ${item.inPatientsAnemiaMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inPatientsAnemia}
    </dd>
    <dt>
      <label>
        ${item.inPatientsClinicallyMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inPatientsClinically}
    </dd>
    <dt>
      <label>
        ${item.inPatientsDefinitiveMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inPatientsDefinitive}
    </dd>
    <dt>
      <label>
        ${item.inPatientsDischargedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inPatientsDischarged}
    </dd>
    <dt>
      <label>
        ${item.inPatientsFemaleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inPatientsFemale}
    </dd>
    <dt>
      <label>
        ${item.inPatientsMaleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inPatientsMale}
    </dd>
    <dt>
      <label>
        ${item.inPatientsNotTreatedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inPatientsNotTreated}
    </dd>
    <dt>
      <label>
        ${item.inPatientsPregnantAnemiaMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inPatientsPregnantAnemia}
    </dd>
    <dt>
      <label>
        ${item.inPatientsPregnantDianosisMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inPatientsPregnantDianosis}
    </dd>
    <dt>
      <label>
        ${item.inPatientsTotalMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inPatientsTotal}
    </dd>
    <dt>
      <label>
        ${item.outPatientsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.outPatients}
    </dd>
    <dt>
      <label>
        ${item.outPatientsFemaleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.outPatientsFemale}
    </dd>
    <dt>
      <label>
        ${item.outPatientsMaleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.outPatientsMale}
    </dd>
    <dt>
      <label>
        ${item.outPatientsNotTreatedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.outPatientsNotTreated}
    </dd>
    <dt>
      <label>
        ${item.outPatientsTotalMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.outPatientsTotal}
    </dd>
    <dt>
      <label>
        ${item.patientsNotTreatedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.patientsNotTreated}
    </dd>
    <dt>
      <label>
        ${item.pregnantDiagnosisMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.pregnantDiagnosis}
    </dd>
    <dt>
      <label>
        ${item.pregnantDiagnosisDeathMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.pregnantDiagnosisDeath}
    </dd>
    <dt>
      <label>
        ${item.pregnantReferralsReceivedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.pregnantReferralsReceived}
    </dd>
    <dt>
      <label>
        ${item.referralsReceivedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.referralsReceived}
    </dd>
    <dt>
      <label>
        ${item.referralsSentMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.referralsSent}
    </dd>
    <dt>
      <label>
        ${item.stillBirthsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.stillBirths}
    </dd>
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
              <c:choose>
                <c:when test="${current.outOfStock}">Yes</c:when>
                <c:otherwise>No</c:otherwise>
              </c:choose>
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
              ${current.amount}
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
              ${current.amount}
              <mjl:messages attribute="amount">
                <mjl:message />
              </mjl:messages>
            </td>
            <td>
              ${current.amountPositive}
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
              ${current.amount}
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
              ${current.amount}
              <mjl:messages attribute="amount">
                <mjl:message />
              </mjl:messages>
            </td>
          </tr>
        </mjl:components>
      </table>
    </dd>

  </dl>


  <mjl:command value="Edit" action="dss.vector.solutions.surveillance.AggregatedCaseController.edit.mojo" name="dss.vector.solutions.surveillance.AggregatedCase.form.edit.button" />
  <br />
</mjl:form>