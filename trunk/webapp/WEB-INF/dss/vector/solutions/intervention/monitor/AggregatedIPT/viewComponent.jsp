<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="View_Aggregated_IPT"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.name" id="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.id" method="POST">
  <dl>
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="geoId">
        ${item.geoId}
      </mjl:dt>
      <mjl:dt attribute="periodType">
        ${item.periodTypeEnumNames[0]}
      </mjl:dt>
      <mjl:dt attribute="period">
        ${item.period}
      </mjl:dt>
      <mjl:dt attribute="periodYear">
        ${item.periodYear}
      </mjl:dt>
      <mjl:dt attribute="numberPregnant">
        ${item.numberPregnant}
      </mjl:dt>    
      <mjl:dt attribute="numberNatalCare" >
        ${item.numberNatalCare}
      </mjl:dt>
      <mjl:dt attribute="numberPregnantIron">
        ${item.numberPregnantIron}
      </mjl:dt>    
      <mjl:dt attribute="numberPregnantITN" >
        ${item.numberPregnantITN}
      </mjl:dt>    
      <mjl:dt attribute="totalITN">
        ${item.totalITN}
      </mjl:dt>    
    </mjl:component>
  
    <c:if test="${item.isDisplayPatientsReadable}">
      <dt>
      
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th><fmt:message key="Number_per_person_category"/></th>
            <th><fmt:message key="Amount"/></th>
          </tr>      
          <mjl:components items="${patients}" param="patients" var="current" varStatus="status">
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
  
    <c:if test="${item.isDisplayVisitsReadable}">
      <dt>
      
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th><fmt:message key="ANC_Visit"/></th>
            <th><fmt:message key="Amount"/></th>
          </tr>      
          <mjl:components items="${visits}" param="visits" var="current" varStatus="status">
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
  
    <c:if test="${item.isDisplayDoseReadable}">
      <dt>
      
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th><fmt:message key="IPT_Dose"/></th>
            <th><fmt:message key="Amount"/></th>
          </tr>      
          <mjl:components items="${doses}" param="doses" var="current" varStatus="status">
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
  
    <c:if test="${item.isDisplayTreatmentsReadable}">
      <dt>  
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th><fmt:message key="IPT_Type"/></th>
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
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.AggregatedIPTController.edit.mojo" name="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.edit.button" />
  </dl>
</mjl:form>