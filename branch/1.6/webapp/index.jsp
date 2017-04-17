<%@page import="dss.vector.solutions.util.LocalizationFacadeDTO"%>
<%@page import="dss.vector.solutions.MDSSUserDTO"%>
<%@page import="com.runwaysdk.web.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="dss.vector.solutions.global.CredentialsSingleton"%>

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.generated.EarthDTO"%><c:set var="page_title" value="welcome"  scope="request"/>
<jsp:include page="/WEB-INF/templates/header.jsp" />
<div class="pageContent">
<div class="pageTitle"><mdss:localize key="welcome"/></div>

<jsp:include page="/WEB-INF/inlineError.jsp" />


<script type="text/javascript">

  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
  
    String[] types = {GeoHierarchyDTO.CLASS};
    
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
    out.print(js);
  %>

  (function(){
   
    // start process of refreshing the allowed in tree, 
    // and don't worry about errors or returns.
    var request = new MDSS.Request({
      onSuccess : function(){},
      onFailure : function(){},
      onSend : function(){},
      onComplete : function(){}
    });

    Mojo.$.dss.vector.solutions.geo.GeoHierarchy.defineAllowedTree(request, '<%= EarthDTO.getEarthInstance(requestIF).getId() %>');

  })();
</script>

<br><br>
  <%
    Locale[] locales = ServletUtility.getLocales(request);
  %>
  <mdss:localize key="Detected_locales"/> 
  <%
    out.println(Arrays.toString(locales));
  %><br/>
  <mdss:localize key="Best_fit_locale"/> 
  <%
    out.println(LocalizationFacadeDTO.getSessionLocale(requestIF));
  %><br/>
  <br>
  <mdss:localize key="Current_disease"/> ${diseaseLabel}.
  <br>
<pre>

<br/><br/>
</pre>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />