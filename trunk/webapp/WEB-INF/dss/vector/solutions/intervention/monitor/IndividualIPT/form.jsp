<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootController"%>
<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootViewDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootDTO"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp" />

<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = true;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;
MDSS.AbstractSelectSearch.ExtraUniversals.push('${healthFacility}*');
</script>



<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:input param="iptCase" type="hidden" value="${item.iptCase.id}" />
  <mjl:dt attribute="facility">
    <input type="hidden" id="typeSearchFilter" value="${healthFacility}" />      
    <mjl:input classes="geoInput" id="geoIdEl" param="facility" type="text" />
  </mjl:dt>
  <mjl:dt attribute="serviceDate" classes="DatePick" id="serviceDate" type="text"/>  
  <mjl:dt attribute="patientType">
    <span class="clickable browserLauncher" id="patientTypeBtn"> <fmt:message key="Browser"/></span>
    <div id="patientTypeDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${patientType != null}">
          ${patientType.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="patientType" id="patientType" value="${patientType != null ? patientType.id : ''}" />
  </mjl:dt>
  <mjl:dt attribute="isANCVisit">
    <mjl:boolean param="isANCVisit" id="isANCVisit" />
  </mjl:dt>
  <mjl:dt attribute="visitNumber" classes="visitNumber">
    <span class="clickable visitNumber" id="visitNumberBtn"> <fmt:message key="Browser"/></span>
    <div id="visitNumberDisplay" class="ontologyDisplay visitNumber">
      <c:choose>
        <c:when test="${visitNumber != null}">
          ${visitNumber.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="visitNumber" id="visitNumber" classes="visitNumber" value="${visitNumber != null ? visitNumber.id : ''}" />
  </mjl:dt>
  <mjl:dt attribute="doseNumber">
    <span class="clickable browserLauncher" id="doseNumberBtn"> <fmt:message key="Browser"/></span>
    <div id="doseNumberDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${doseNumber != null}">
          ${doseNumber.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="doseNumber" id="doseNumber" value="${doseNumber != null ? doseNumber.id : ''}" />
  </mjl:dt>
  <mjl:dt attribute="doseType">
    <span class="clickable browserLauncher" id="doseTypeBtn"> <fmt:message key="Browser"/></span>
    <div id="doseTypeDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${doseType != null}">
          ${doseType.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="doseType" id="doseType" value="${doseType != null ? doseType.id : ''}" />
  </mjl:dt>
  <mjl:dt attribute="recievedSupplement">
    <mjl:boolean param="recievedSupplement" />
  </mjl:dt>
  <mjl:dt attribute="recievedITN">
    <mjl:boolean param="recievedITN" id="recievedITN" />
  </mjl:dt>
  <mjl:dt attribute="numberOfRecievedITNs" classes="numberOfRecievedITNs">
    <mjl:input param="numberOfRecievedITNs" type="text" classes="numberOfRecievedITNs" />
  </mjl:dt>
  <mjl:dt attribute="administratorName">
    <mjl:input param="administratorName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="administratorSurname">
    <mjl:input param="administratorSurname" type="text" />
  </mjl:dt>
</mjl:component>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    var attrs = [{attributeName:'patientType'}, {attributeName:'visitNumber'}, {attributeName:'doseNumber'}, {attributeName:'doseType'}];

    new MDSS.GenericOntologyBrowser("<%=IndividualIPTViewDTO.CLASS%>", attrs);

//    MDSS.ElementHandler.setupBooleanHandler('isANCVisit.positive', 'isANCVisit.negative', 'visitNumber');
//    MDSS.ElementHandler.setupBooleanHandler('isANCVisit.positive', 'isANCVisit.negative', 'visitNumberBtn', false);
//    MDSS.ElementHandler.setupBooleanHandler('recievedITN.positive', 'recievedITN.negative', 'numberOfRecievedITNs');
  })
})();
</script>
