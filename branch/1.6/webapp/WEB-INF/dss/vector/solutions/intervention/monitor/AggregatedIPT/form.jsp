<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


  <mjl:component item="${item}" param="dto">
    <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />  
    <mjl:input type="hidden" param="geoId" value="${item.geoId}" />
    <mjl:input type="hidden" param="period" value="${item.period}" />
    <mjl:input type="hidden" param="periodType" value="${item.periodTypeEnumNames[0]}"/>
    <mjl:input type="hidden" param="periodYear" value="${item.periodYear}"/>  
    <mjl:input type="hidden" param="startDate" value="${item.startDate}" classes="DatePick"/>  
    <mjl:input type="hidden" param="endDate" value="${item.endDate}" classes="DatePick"/>  
    <mjl:dt attribute="geoId">
      ${entity.displayString}
    </mjl:dt>
    <mjl:dt attribute="startDate">
      <span class="formatDate">${item.startDate}</span>
    </mjl:dt>
    <mjl:dt attribute="endDate">
      <span class="formatDate">${item.endDate}</span>      
    </mjl:dt>          
<!--    
    <mjl:dt attribute="periodType">
        <ul>
          <c:forEach var="enumName" items="${item.periodTypeEnumNames}">
            <li>${item.periodTypeMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>      
    </mjl:dt>
    <mjl:dt attribute="period">
      ${item.period}
    </mjl:dt>
    <mjl:dt attribute="periodYear">
      ${item.periodYear}
    </mjl:dt>
-->
    <mjl:dt attribute="numberPregnant" type="text" />
    <mjl:dt attribute="numberNatalCare" type="text" />
    <mjl:dt attribute="numberPregnantIron" type="text" />
    <mjl:dt attribute="numberPregnantITN" type="text" />
    <mjl:dt attribute="totalITN" type="text" />
  </mjl:component>
  
  <c:if test="${item.isDisplayPatientsReadable}">
    <dt>
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th>${item.displayPatientsMd.displayLabel}</th>
          <th><mdss:localize key="Amount"/></th>
        </tr>      
        <mjl:components items="${patients}" param="patients" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td class="integerColumn">
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
  
  <c:if test="${item.isDisplayVisitsReadable}">
    <dt>
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th><mdss:localize key="ANC_Visit"/></th>
          <th><mdss:localize key="Amount"/></th>
        </tr>      
        <mjl:components items="${visits}" param="visits" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td class="integerColumn">
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
  
  <c:if test="${item.isDisplayDoseReadable}">
    <dt>
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th><mdss:localize key="IPT_Dose"/></th>
          <th><mdss:localize key="Amount"/></th>          
        </tr>      
        <mjl:components items="${doses}" param="doses" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td class="integerColumn">
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
  
  <c:if test="${item.isDisplayTreatmentsReadable}">
    <dt>
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th><mdss:localize key="IPT_Type"/></th>
          <th><mdss:localize key="Amount"/></th>          
        </tr>      
        <mjl:components items="${treatments}" param="treatments" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td class="integerColumn">
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
  
