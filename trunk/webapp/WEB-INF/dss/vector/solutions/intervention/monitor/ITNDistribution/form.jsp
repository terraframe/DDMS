<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>

<jsp:include page="/WEB-INF/selectSearch.jsp" />

<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />
  <mjl:input type="hidden" param="person" value="${item.person.id}" />
  <mjl:dt attribute="facility">
    <mdss:geo param="facility" concrete="false" value="${item.facility}" /> <!-- filter="${healthFacility}" -->
  </mjl:dt>
  <mjl:dt attribute="distributionDate" >
    <mjl:input type="text" param="distributionDate" id="distributionDate" classes="DatePick NoFuture"/>
  </mjl:dt>
  <mjl:dt attribute="service">
    <mdss:mo param="service" value="${service}"/>
  </mjl:dt>
  <mjl:dt attribute="batchNumber">
    <mjl:input type="text" param="batchNumber" />
  </mjl:dt>
  
  <c:if test="${item.isTargetGroupsReadable}">
    <dt>
      
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th>${item.targetGroupsMd.displayLabel}</th>
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
  
  <mjl:dt attribute="net">
    <mdss:mo param="net" value="${net}"/>
  </mjl:dt>
  <mjl:dt attribute="numberSold">
    <mjl:input type="text" param="numberSold" />
  </mjl:dt>
  <mjl:dt attribute="currencyReceived">
    <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" var="formattedCurrencyReceived" value="${item.currencyReceived}" />
    <mjl:input param="currencyReceived" type="text" id="currencyReceived" value="${formattedCurrencyReceived}"/>      
  </mjl:dt>
  <mjl:dt attribute="distributorName">
    <mjl:input type="text" param="distributorName" />
  </mjl:dt>
  <mjl:dt attribute="distributorSurname">
    <mjl:input type="text" param="distributorSurname" />
  </mjl:dt>
</mjl:component>