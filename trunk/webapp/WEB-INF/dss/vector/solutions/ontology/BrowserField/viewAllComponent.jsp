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

<script type="text/javascript" src="js/OntologyFields.js"></script>

<script type="text/javascript">
<% 
  String[] types = new String[]{BrowserFieldDTO.CLASS, BrowserRootDTO.CLASS, BrowserRootViewDTO.CLASS, BrowserRootController.CLASS, TermViewDTO.CLASS, TermDTO.CLASS};
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
  String js = JSONController.importTypes(clientRequest.getSessionId(), types, true);
  out.write(js);
%>
</script>

<div id="ontologyFields">

<dl>
<c:forEach items="${fields}" var="field">

 <dt>
    <button value="${field.browserFieldId}" class="addRootBtn">Add Root</button>  ${field.mdClassLabel} : ${field.mdAttributeLabel}
 </dt> 
 <dd>
   <table id="${field.browserFieldId}_table" cellpadding="3" cellspacing="0" border="1" class="ontologyFields">
     <tr><th>Term</th><th>Selectable</th><th>Edit</th><th>Delete</th></tr>
     
     <c:forEach items="${rootMap[field.mdAttributeId]}" var="root">
       <tr id="${root.browserRootId}_row">
         <td>${root.termName}</td>
         <td>${root.selectable}</td>
         <td><button class="editRootBtn" value="${root.browserRootId}">Edit</button></td>
         <td><button class="deleteRootBtn" value="${root.browserRootId}">Delete</button></td>
       </tr>
     </c:forEach>
   </table>
 </dd>
</c:forEach>
</dl>

</div>