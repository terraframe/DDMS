<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%@ include file="form.jsp"%>

<div class="pageTitle"> <fmt:message key="Enter_data_for_ages"/> ${item.ageGroup.startAge} &lt; ${item.ageGroup.endAge} </div> 

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
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th><fmt:message key="Treatment_out_of_Stock"/></th>
          <th><fmt:message key="Out_of_Stock"/></th>
        </tr>      
      
        <mjl:components items="${stock}" param="stock" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td>
              ${current.outOfStock ? current.outOfStockMd.positiveDisplayLabel :current.outOfStockMd.negativeDisplayLabel}
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
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th><fmt:message key="Facility_referred"/></th>      
          <th><fmt:message key="Amount"/></th>
        </tr>      
      
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
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th><fmt:message key="Diagnostic_methods"/></th>      
          <th><fmt:message key="Amount"/></th>
          <th><fmt:message key="Positive"/></th>
        </tr>      
      
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
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th><fmt:message key="Treatment_methods"/></th>      
          <th><fmt:message key="Amount"/></th>
        </tr>            
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
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th><fmt:message key="Treatments"/></th>      
          <th><fmt:message key="Amount"/></th>
        </tr>            
      
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