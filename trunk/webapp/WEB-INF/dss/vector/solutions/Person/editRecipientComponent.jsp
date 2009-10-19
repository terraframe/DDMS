<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.PersonViewDTO"%>

<c:set var="page_title" value="Confirm_IPT_Recipient" scope="request" />

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="IndividualIPT.form.name" id="form.id" method="POST">
    <mjl:component item="${item}" param="patient">
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
      <mjl:dt attribute="age" >
        <mjl:input type="text" param="age" size="3" maxlength="3" />
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
        <mjl:input id="residentialGeoId" param="residentialGeoId" type="text" value="${item.residentialGeoId}" maxlength="16" classes="geoInput"/>
      </mjl:dt>
      <mjl:dt attribute="residentialInformation">
        <mjl:input type="text" param="residentialInformation" />
      </mjl:dt>
      <mjl:dt attribute="workGeoId" >
        <mjl:input id="workGeoId" param="workGeoId" type="text" value="${item.residentialGeoId}" maxlength="16" classes="geoInput"/>
      </mjl:dt>
      <mjl:dt attribute="workInformation">
        <mjl:input type="text" param="workInformation" />
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Confirm" action="dss.vector.solutions.PersonController.updateRecipient.mojo" name="confirm" />
    <mjl:command value="Cancel" action="dss.vector.solutions.PersonController.viewAll.mojo" name="cancel" />
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