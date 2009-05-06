<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

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
  <mjl:command classes="submitButton" action="dss.vector.solutions.surveillance.AggregatedCaseController.searchByGeoIdAndEpiWeek.mojo" name="search.button" value="Change_age_group" />
</mjl:form>

<mjl:form name="dss.vector.solutions.surveillance.AggregatedCase.form.name" id="dss.vector.solutions.surveillance.AggregatedCase.form.id" method="POST">
  <dl>
    <mjl:input value="${item.caseId}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="cases"> ${item.cases} </mjl:dt>
      <mjl:dt attribute="casesFemale"> ${item.casesFemale} </mjl:dt>
      <mjl:dt attribute="casesMale"> ${item.casesMale} </mjl:dt>
      <mjl:dt attribute="casesPregnant"> ${item.casesPregnant} </mjl:dt>
      <mjl:dt attribute="clinicallyDiagnosed"> ${item.clinicallyDiagnosed} </mjl:dt>
      <mjl:dt attribute="clinicallyDiagnosedDeath"> ${item.clinicallyDiagnosedDeath} </mjl:dt>
      <mjl:dt attribute="daysOutOfStock"> ${item.daysOutOfStock} </mjl:dt>
      <mjl:dt attribute="deaths"> ${item.deaths} </mjl:dt>
      <mjl:dt attribute="deathsFemale"> ${item.deathsFemale} </mjl:dt>
      <mjl:dt attribute="deathsMale"> ${item.deathsMale} </mjl:dt>
      <mjl:dt attribute="deathsPregnant"> ${item.deathsPregnant} </mjl:dt>
      <mjl:dt attribute="definitivelyDiagnosed"> ${item.definitivelyDiagnosed} </mjl:dt>
      <mjl:dt attribute="definitivelyDiagnosedDeath"> ${item.definitivelyDiagnosedDeath} </mjl:dt>
      <mjl:dt attribute="inPatients"> ${item.inPatients} </mjl:dt>
      <mjl:dt attribute="inPatientsAnemia"> ${item.inPatientsAnemia} </mjl:dt>
      <mjl:dt attribute="inPatientsClinically"> ${item.inPatientsClinically} </mjl:dt>
      <mjl:dt attribute="inPatientsDefinitive"> ${item.inPatientsDefinitive} </mjl:dt>
      <mjl:dt attribute="inPatientsDischarged"> ${item.inPatientsDischarged} </mjl:dt>
      <mjl:dt attribute="inPatientsFemale"> ${item.inPatientsFemale} </mjl:dt>
      <mjl:dt attribute="inPatientsMale"> ${item.inPatientsMale} </mjl:dt>
      <mjl:dt attribute="inPatientsNotTreated"> ${item.inPatientsNotTreated} </mjl:dt>
      <mjl:dt attribute="inPatientsPregnantAnemia"> ${item.inPatientsPregnantAnemia} </mjl:dt>
      <mjl:dt attribute="inPatientsPregnantDianosis"> ${item.inPatientsPregnantDianosis} </mjl:dt>
      <mjl:dt attribute="inPatientsTotal"> ${item.inPatientsTotal} </mjl:dt>
      <mjl:dt attribute="outPatients"> ${item.outPatients} </mjl:dt>
      <mjl:dt attribute="outPatientsFemale"> ${item.outPatientsFemale} </mjl:dt>
      <mjl:dt attribute="outPatientsMale"> ${item.outPatientsMale} </mjl:dt>
      <mjl:dt attribute="outPatientsNotTreated"> ${item.outPatientsNotTreated} </mjl:dt>
      <mjl:dt attribute="outPatientsTotal"> ${item.outPatientsTotal} </mjl:dt>
      <mjl:dt attribute="patientsNotTreated"> ${item.patientsNotTreated} </mjl:dt>
      <mjl:dt attribute="pregnantDiagnosis"> ${item.pregnantDiagnosis} </mjl:dt>
      <mjl:dt attribute="pregnantDiagnosisDeath"> ${item.pregnantDiagnosisDeath} </mjl:dt>
      <mjl:dt attribute="pregnantReferralsReceived"> ${item.pregnantReferralsReceived} </mjl:dt>
      <mjl:dt attribute="referralsReceived"> ${item.referralsReceived} </mjl:dt>
      <mjl:dt attribute="referralsSent"> ${item.referralsSent} </mjl:dt>
      <mjl:dt attribute="stillBirths"> ${item.stillBirths} </mjl:dt>
    </mjl:component>

    <c:if test="${item.isCaseStocksReadable}">
    <dt>
      <fmt:message key="Treatment_out_of_Stock"/>
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
    </c:if>

    <c:if test="${item.isCaseReferralsReadable}">
    <dt>
      <fmt:message key="Facility_referred"/>
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
    </c:if>

    <c:if test="${item.isCaseDiagnosticReadable}">
    <dt>
      <fmt:message key="Diagnostic_methods"/>
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
    </c:if>

    <c:if test="${item.isCaseTreatmentMethodReadable}">
    <dt>
      <fmt:message key="Treatment_methods"/>
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
    </c:if>

    <c:if test="${item.isCaseTreatmentsReadable}">
    <dt>
      <fmt:message key="Treatments"/>
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
    </c:if>

  </dl>


  <mjl:command value="Edit" action="dss.vector.solutions.surveillance.AggregatedCaseController.edit.mojo" name="dss.vector.solutions.surveillance.AggregatedCase.form.edit.button" />
  <br />
</mjl:form>