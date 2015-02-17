<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootController"%>
<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootViewDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootDTO"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<jsp:include page="/WEB-INF/selectSearch.jsp" />


<jsp:include page="/WEB-INF/selectSearch.jsp" />

<mjl:component param="instance" item="${instance}">
  <mjl:input type="hidden" param="concreteId" value="${instance.concreteId}"/>
  <mjl:dt attribute="facility">
    <mdss:geo param="facility" concrete="false" filter="${healthFacility}" />
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
  <mjl:dt attribute="receivedSupplement">
    <mjl:boolean param="receivedSupplement" />
  </mjl:dt>
  <mjl:dt attribute="receivedITN">
    <mjl:boolean param="receivedITN" id="receivedITN" />
  </mjl:dt>
  <div class="numberOfReceivedITNs">
    <mjl:dt attribute="numberOfReceivedITNs" classes="numberOfReceivedITNs">
      <mjl:input param="numberOfReceivedITNs" type="text" id="numberOfReceivedITNs" />
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
    MDSS.ElementHandler.setupBooleanHandler('receivedITN.positive', 'receivedITN.negative', MDSS.HiddenInputElement.toArray(['numberOfReceivedITNs']));    
  })
})();
</script>
