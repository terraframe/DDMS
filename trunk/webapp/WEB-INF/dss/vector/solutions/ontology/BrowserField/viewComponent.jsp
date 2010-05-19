<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.ontology.BrowserFieldDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootController"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootViewDTO"%>
<%@page import="dss.vector.solutions.ontology.TermViewQueryDTO"%>
<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%>
<%@page import="dss.vector.solutions.ontology.FieldDefaultViewDTO"%>
<c:set var="page_title" value="Ontology_Fields" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<div id="ontologyFields">

<dl>
  <dt>
    <button value="${field.browserFieldId}" class="addRootBtn"><fmt:message key="Add_Root" /></button>
    ${field.mdClassLabel} : ${field.mdAttributeLabel}
  </dt> 
  <dd>
    <div class="defaultFieldTerm">
      <fmt:message key="Default_Term" />:
      <mdss:mo value="${defaultValue}" enabled="true" script="false" param="${field.mdAttributeId}_defaultTerm" />
    </div>
   
    <table id="${field.browserFieldId}_table" cellpadding="3" cellspacing="0" border="1" class="ontologyFields">
      <tr><th><fmt:message key="Term" /></th><th><fmt:message key="Selectable" /></th><th><fmt:message key="Edit" /></th><th><fmt:message key="Delete" /></th></tr>
     
      <c:forEach items="${roots}" var="root">
        <tr id="${root.browserRootId}_row">
          <td>${root.displayLabel}</td>
          <td>${root.selectable}</td>
          <td><button class="editRootBtn" value="${root.browserRootId}"><fmt:message key="Edit" /></button></td>
          <td><button class="deleteRootBtn" value="${root.browserRootId}"><fmt:message key="Delete" /></button></td>
       </tr>
      </c:forEach>
    </table>
  </dd>
</dl>

</div>

<script type="text/javascript">
YAHOO.util.Event.onDOMReady(function() {
  new MDSS.OntologyFields({id:'<%=request.getAttribute("buttonId").toString()%>'});
}); 
</script>
