<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>





<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.AttributeGeoHierarchyDTO"%>

<%@page import="dss.vector.solutions.form.FormObjectController"%>
<c:set var="page_title" value="Form_Generator"  scope="request"/>

<jsp:include page="../templates/header.jsp"></jsp:include>

<script type="text/javascript">


<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

String[] types = new String[]{FormObjectController.CLASS};

String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
out.print(js);
%>


YAHOO.util.Event.onDOMReady(function(){

  var mdFormId = '<%= request.getParameter("mdFormId") %>';
  var request = new MDSS.Request({
    onSuccess : function(formObjectJSON){
      
      var webForm = new com.runwaysdk.form.web.WebFormObject.parseFromJSON(formObjectJSON);
      var formRender = new com.runwaysdk.form.FormObjectRenderer(webForm);
      formRender.render('formContainer');
    }
  });
  
  dss.vector.solutions.form.FormObjectController.newInstance(request, mdFormId);
});
</script>

<div id="formContainer">
</div>

<jsp:include page="../templates/footer.jsp"></jsp:include>