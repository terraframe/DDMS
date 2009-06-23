<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

  <mjl:component item="${item}" param="dto">
    <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />  
    <mjl:input type="hidden" param="geoId" value="${item.geoId}" />
    <mjl:input type="hidden" param="period" value="${item.period}" />
    <mjl:input type="hidden" param="periodType" value="${item.periodTypeEnumNames[0]}"/>
    <mjl:input type="hidden" param="periodYear" value="${item.periodYear}"/>  
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
    <mjl:dt attribute="batchNumber" type="text" />
    <mjl:dt attribute="receivedForTargetGroups" type="text" />      
    <mjl:dt attribute="receivedForCommunityResponse" type="text" />      
    <mjl:dt attribute="numberDistributed" type="text" />      
    <mjl:dt attribute="numberSold" type="text" />      
    <mjl:dt attribute="currencyReceived" type="text" />      
  </mjl:component>

  <c:if test="${item.isDisplayServicesReadable}">
    <dt>
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th><fmt:message key="service_received"/></th>
          <th><fmt:message key="Amount"/></th>
        </tr>      
        <mjl:components items="${services}" param="services" var="current" varStatus="status">
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

  <c:if test="${item.isDisplayTargetGroupsReadable}">
    <dt>
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th><fmt:message key="target_groups"/></th>
          <th><fmt:message key="Amount"/></th>
        </tr>      
        <mjl:components items="${targetGroups}" param="targetGroups" var="current" varStatus="status">
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

  <c:if test="${item.isDisplayTargetGroupsReadable}">
    <dt>
    
    </dt>
    <dd>
      <table class="displayTable">
        <tr>
          <th><fmt:message key="total_itn_distributed" /></th>
          <th><fmt:message key="Amount" /></th>
        </tr>
        <mjl:components items="${nets}" param="nets" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <c:choose>
              <c:when test="${current.child.isAbstract}">
                <td colspan="2">${current.child.displayLabel}</td>
              </c:when>
              <c:otherwise>
                <td style="padding-left:2em">${current.child.displayLabel}</td>
                <td>
                  <mjl:input type="text" param="amount" />
                  <mjl:messages attribute="amount">
                   <mjl:message />
                  </mjl:messages>
                </td>
              </c:otherwise>
            </c:choose>
          </tr>
        </mjl:components>
      </table>
    </dd>  
  </c:if>
  