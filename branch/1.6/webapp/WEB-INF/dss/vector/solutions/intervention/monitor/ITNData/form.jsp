<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

  <mjl:component item="${item}" param="dto">
    <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />  
    <mjl:input type="hidden" param="geoId" value="${item.geoId}" />
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
    <mjl:dt attribute="batchNumber" type="text" />
    <mjl:dt attribute="receivedForTargetGroups" type="text" />      
    <mjl:dt attribute="receivedForCommunityResponse" type="text" />      
    <mjl:dt attribute="numberDistributed" type="text" />      
    <mjl:dt attribute="numberSold" type="text" />      
    <mjl:dt attribute="currencyReceived" >
      <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" var="formattedCurrencyReceived" value="${item.currencyReceived}" />
      <mjl:input param="currencyReceived" type="text" id="currencyReceived" value="${formattedCurrencyReceived}"/>      
    </mjl:dt>      
  </mjl:component>

  <c:if test="${item.isDisplayServicesReadable}">
    <dt>
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th>${item.displayServicesMd.displayLabel}</th>
          <th><mdss:localize key="Amount"/></th>
        </tr>      
        <mjl:components items="${services}" param="services" var="current" varStatus="status">
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

  <c:if test="${item.isDisplayTargetGroupsReadable}">
    <dt>
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th>${item.displayTargetGroupsMd.displayLabel}</th>
          <th><mdss:localize key="Amount"/></th>
        </tr>      
        <mjl:components items="${targetGroups}" param="targetGroups" var="current" varStatus="status">
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

  <c:if test="${item.isDisplayNetsReadable}">
    <dt>
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th>${item.displayNetsMd.displayLabel}</th>
          <th><mdss:localize key="Amount"/></th>
        </tr>      
        <mjl:components items="${nets}" param="nets" var="current" varStatus="status">
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
  