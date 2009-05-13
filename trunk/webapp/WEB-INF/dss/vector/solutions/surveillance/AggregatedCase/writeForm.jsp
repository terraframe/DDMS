<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

  <dl>
    <mjl:component item="${item}" param="dto">
      <input type="hidden" name="#dto.actualType" value="${item.type}DTO" />
      <mjl:input type="hidden" param="geoEntity" value="${item.geoEntity.id}" />
      <mjl:input type="hidden" param="ageGroup" value="${item.ageGroup.id}" />
      <mjl:input type="hidden" param="period" value="${item.period}" />
      <mjl:input type="hidden" param="periodType" value="${item.periodTypeEnumNames[0]}"/>
      <mjl:input type="hidden" param="periodYear" value="${item.periodYear}"/>
      <mjl:input type="hidden" param="caseId" value="${item.caseId}" />
     
      <mjl:dt attribute="cases" type="text" />
      <mjl:dt attribute="casesFemale" type="text"/>
      <mjl:dt attribute="casesMale" type="text"/>
      <mjl:dt attribute="casesPregnant" type="text"/>
      <mjl:dt attribute="clinicallyDiagnosed" type="text"/>
      <mjl:dt attribute="clinicallyDiagnosedDeath" type="text"/>
      <mjl:dt attribute="daysOutOfStock" type="text"/>
      <mjl:dt attribute="deaths" type="text"/>
      <mjl:dt attribute="deathsFemale" type="text"/>
      <mjl:dt attribute="deathsMale" type="text"/>
      <mjl:dt attribute="deathsPregnant" type="text"/>
      <mjl:dt attribute="definitivelyDiagnosed" type="text"/>
      <mjl:dt attribute="definitivelyDiagnosedDeath" type="text"/>
      <mjl:dt attribute="inPatients" type="text"/>
      <mjl:dt attribute="inPatientsAnemia" type="text"/>
      <mjl:dt attribute="inPatientsClinically" type="text"/>
      <mjl:dt attribute="inPatientsDefinitive" type="text"/>
      <mjl:dt attribute="inPatientsDischarged" type="text"/>
      <mjl:dt attribute="inPatientsFemale" type="text"/>
      <mjl:dt attribute="inPatientsMale" type="text"/>
      <mjl:dt attribute="inPatientsNotTreated" type="text"/>
      <mjl:dt attribute="inPatientsPregnantAnemia" type="text"/>
      <mjl:dt attribute="inPatientsPregnantDianosis" type="text"/>
      <mjl:dt attribute="inPatientsTotal" type="text"/>
      <mjl:dt attribute="outPatients" type="text"/>
      <mjl:dt attribute="outPatientsFemale" type="text"/>
      <mjl:dt attribute="outPatientsMale" type="text"/>
      <mjl:dt attribute="outPatientsNotTreated" type="text"/>
      <mjl:dt attribute="outPatientsTotal" type="text"/>
      <mjl:dt attribute="patientsNotTreated" type="text"/>
      <mjl:dt attribute="pregnantDiagnosis" type="text"/>
      <mjl:dt attribute="pregnantDiagnosisDeath" type="text"/>
      <mjl:dt attribute="pregnantReferralsReceived" type="text"/>
      <mjl:dt attribute="referralsReceived" type="text"/>
      <mjl:dt attribute="referralsSent" type="text"/>
      <mjl:dt attribute="stillBirths" type="text"/>
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
              <mjl:boolean param="outOfStock"/>
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
              <mjl:input type="text" param="amount" />
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
          <th><fmt:message key="Total_Tests"/></th>
          <th><fmt:message key="Positive"/></th>
        </tr>
      
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
              <mjl:input type="text" param="amount" />
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
              <mjl:input type="text" param="amount" />
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