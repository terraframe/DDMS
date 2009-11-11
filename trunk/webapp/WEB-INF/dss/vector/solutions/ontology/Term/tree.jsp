<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%>
<%@page import="dss.vector.solutions.ontology.TermController"%>
<c:set var="page_title" value="Ontology_Admin"  scope="request"/>

<jsp:include page="/WEB-INF/templates/header.jsp" />

<script type="text/javascript">
<% 
String[] types = new String[]{TermViewDTO.CLASS, TermDTO.CLASS, TermController.CLASS};
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
String js = JSONController.importTypes(clientRequest.getSessionId(), types, true);
out.write(js);
%>

  YAHOO.util.Event.onDOMReady(function(){

    var tree = new MDSS.OntologyTree('treeView');
    tree.render();
    
  }, null, true);
</script>

<div class="yui-skin-sam">
  <div id="treeView" style="margin: 10px"></div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />