<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootController"%>
<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootViewDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootDTO"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO"%>
<%@page import="dss.vector.solutions.geo.generated.HealthFacilityDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp" />

<c:set var="healthFacility" scope="request"><%=HealthFacilityDTO.CLASS%></c:set>

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
    <mdss:mo param="patientType" value="${patientType}"/>
  </mjl:dt>
  <mjl:dt attribute="isANCVisit">
    <mjl:boolean param="isANCVisit" id="isANCVisit" />
  </mjl:dt>
  <div class="visitNumber">
    <mjl:dt attribute="visitNumber">
      <mdss:mo param="visitNumber" value="${visitNumber}"/>
    </mjl:dt>
  </div>
  <mjl:dt attribute="doseNumber">
    <mdss:mo param="doseNumber" value="${doseNumber}"/>
  </mjl:dt>
  <mjl:dt attribute="doseType">
    <mdss:mo param="doseType" value="${doseType}"/>
  </mjl:dt>
  <mjl:dt attribute="recievedSupplement">
    <mjl:boolean param="recievedSupplement" />
  </mjl:dt>
  <mjl:dt attribute="recievedITN">
    <mjl:boolean param="recievedITN" id="recievedITN" />
  </mjl:dt>
  <div class="numberOfRecievedITNs">
    <mjl:dt attribute="numberOfRecievedITNs" classes="numberOfRecievedITNs">
      <mjl:input param="numberOfRecievedITNs" type="text" id="numberOfRecievedITNs" />
    </mjl:dt>
  </div>
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
    MDSS.ElementHandler.setupBooleanHandler('isANCVisit.positive', 'isANCVisit.negative', MDSS.HiddenInputElement.toArray(['visitNumber']));
    MDSS.ElementHandler.setupBooleanHandler('recievedITN.positive', 'recievedITN.negative', MDSS.HiddenInputElement.toArray(['numberOfRecievedITNs']));    
  })
})();
</script>
