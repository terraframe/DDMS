<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.ontology.BrowserFieldDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootController"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootViewDTO"%>
<%@page import="dss.vector.solutions.ontology.TermViewQueryDTO"%>
<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%><c:set var="page_title" value="Ontology_Fields" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<script type="text/javascript">
<% 
  String[] types = new String[]{BrowserRootController.CLASS, BrowserFieldDTO.CLASS, BrowserRootDTO.CLASS, BrowserRootViewDTO.CLASS};
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
  String js = JSONController.importTypes(clientRequest.getSessionId(), types, true);
  out.write(js);
%>
YAHOO.util.Event.onDOMReady(function() { 

	  new MDSS.OntologyFields();

	}); 
</script>

<div id="ontologyFields">

<dl>
<c:forEach items="${fields}" var="field">

 <dt>
    <button value="${field.browserFieldId}" class="addRootBtn"><fmt:message key="Add_Root" /></button>
    ${field.mdClassLabel} : ${field.mdAttributeLabel}
 </dt> 
 <dd>
   <div class="defaultFieldTerm">
        <fmt:message key="Default_Term" />:
        <span class="clickable browserLauncher" id="termBtn">
          <fmt:message key="Browser" />
        </span>
        <div class="ontologyDisplay defaultTermBrowser" id="${field.mdAttributeId}_defaultDisplay">
          <c:choose>
            <c:when test="${defaultTerms[field.browserFieldId] != ''}">
              ${terms[defaultTerms[field.browserFieldId]].displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <input type="hidden" value="${defaultTerms[field.browserFieldId]}" type="hidden" id="${field.mdAttributeId}_defaultTerm" />
   </div>
   
   <table id="${field.browserFieldId}_table" cellpadding="3" cellspacing="0" border="1" class="ontologyFields">
     <tr><th><fmt:message key="Term" /></th><th><fmt:message key="Selectable" /></th><th><fmt:message key="Edit" /></th><th><fmt:message key="Delete" /></th></tr>
     
     <c:forEach items="${rootMap[field.mdAttributeId]}" var="root">
       <tr id="${root.browserRootId}_row">
         <td>${root.termName} (${root.termOntologyId})</td>
         <td>${root.selectable}</td>
         <td><button class="editRootBtn" value="${root.browserRootId}"><fmt:message key="Edit" /></button></td>
         <td><button class="deleteRootBtn" value="${root.browserRootId}"><fmt:message key="Delete" /></button></td>
       </tr>
     </c:forEach>
   </table>
 </dd>
</c:forEach>
</dl>

</div>