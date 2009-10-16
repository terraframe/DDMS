<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp" />


<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />
  <mjl:input type="hidden" param="recipient" />
  <mjl:dt attribute="facility">
    <mjl:input value="${item.facility}" type="text" param="facility" classes="geoInput" id="geoIdEl" />
  </mjl:dt>
  <mjl:dt attribute="distributionDate" >
    <mjl:input type="text" param="distributionDate" id="distributionDate" classes="DatePick NoFuture"/>
  </mjl:dt>
  <mjl:dt attribute="service">
    <span class="clickable" id="serviceBtn"> <fmt:message key="Browser"/></span>
    <div id="serviceDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${service != null}">
          ${service.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="service" id="service" value="${service != null ? service.id : ''}" />
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
  
  <mjl:dt attribute="net">
    <span class="clickable" id="netBtn"> <fmt:message key="Browser"/></span>
    <div id="netDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${net != null}">
          ${net.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="net" id="net" value="${net != null ? net.id : ''}" />
  </mjl:dt>
  <mjl:dt attribute="numberSold">
    <mjl:input type="text" param="numberSold" />
  </mjl:dt>
  <mjl:dt attribute="currencyReceived">
    <mjl:input type="text" param="currencyReceived" />
  </mjl:dt>
  <mjl:dt attribute="distributorName">
    <mjl:input type="text" param="distributorName" />
  </mjl:dt>
  <mjl:dt attribute="distributorSurname">
    <mjl:input type="text" param="distributorSurname" />
  </mjl:dt>
</mjl:component>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    var attrs = [
      {attributeName:'service'},
      {attributeName:'net'}
    ];

    new MDSS.GenericOntologyBrowser("<%=ITNDistributionViewDTO.CLASS%>", attrs);
  })
})();
</script>
