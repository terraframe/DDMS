<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.PersonViewDTO"%><c:set var="page_title" value="Confirm_ITN_Recipient" scope="request" />

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.ITNDistribution.form.name" id="dss.vector.solutions.intervention.monitor.ITNDistribution.form.id" method="POST">
    <mjl:component item="${recipient}" param="recipient">
      <mjl:input type="hidden" param="personId"/>
      <mjl:dt attribute="firstName">
        <mjl:input type="text" param="firstName" />
      </mjl:dt>
      <mjl:dt attribute="lastName">
        <mjl:input type="text" param="lastName" />
      </mjl:dt>
      <mjl:dt attribute="dateOfBirth" >
        <mjl:input type="text" param="dateOfBirth" id="dateOfBirth" classes="DatePick NoFuture"/>
      </mjl:dt>
      <mjl:dt attribute="sex">
        <span class="clickable browserLauncher" id="sexBtn"> <fmt:message key="Browser"/></span>
        <div id="sexDisplay" class="ontologyDisplay">
          <c:choose>
            <c:when test="${sex != null}">
              ${sex.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <mjl:input type="hidden" param="sex" id="sex" value="${sex != null ? sex.id : ''}" />
      </mjl:dt>                  
      <mjl:dt attribute="residentialGeoId" >
        <mjl:input id="geoIdEl" param="residentialGeoId" type="text" value="${recipient.residentialGeoId}" maxlength="16" classes="geoInput"/>
      </mjl:dt>
    </mjl:component>
    
    <mjl:component item="${itn}" param="itn">
      <mjl:input type="hidden" param="concreteId" value="${itn.concreteId}" />  
      <mjl:input type="hidden" param="batchNumber"/>
      <mjl:input type="hidden" param="currencyReceived" value="${itn.currencyReceived}"/>
      <fmt:formatDate value="${itn.distributionDate}" pattern="${dateFormatPattern}" var="formattedDistributionDate"/>
      <mjl:input type="hidden" param="distributionDate" value="${formattedDistributionDate}"/>
      <mjl:input type="hidden" param="distributorName" value="${itn.distributorName}"/>
      <mjl:input type="hidden" param="distributorSurname" value="${itn.distributorSurname}"/>
      <mjl:input type="hidden" param="facility" value="${itn.facility}"/>
      <mjl:input type="hidden" param="net" value="${itn.net != null ? itn.net.id : ''}"/>
      <mjl:input type="hidden" param="numberSold" value="${itn.numberSold}"/>
      <mjl:input type="hidden" param="recipient" value="${itn.recipient != null ? itn.recipient.id : ''}"/>
      <mjl:input type="hidden" param="service" value="${itn.service != null ? itn.service.id : ''}"/>
    </mjl:component>
    
    <mjl:command value="Confirm" action="dss.vector.solutions.intervention.monitor.ITNDistributionController.updateRecipient.mojo" name="confirm" />
  </mjl:form>
</dl>

<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   
    var attributes = [
         {attributeName:'sex'}
    ];
    
    new MDSS.GenericOntologyBrowser("<%=PersonViewDTO.CLASS%>", attributes);
  })
})();
</script>
