<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="dss.vector.solutions.util.Halp"%>
<%@ page import="dss.vector.solutions.util.LocalizationFacadeDTO"%>
<%@ page import="dss.vector.solutions.util.OrientationTypeDTO"%>
<%@ page import="com.runwaysdk.web.WebClientSession"%>
<%@ page import="com.runwaysdk.ClientSession"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@ page import="com.runwaysdk.constants.ClientConstants"%>

<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
  request.setAttribute("generateJavaScriptClasses", (clientRequest != null && !clientRequest.isPublicUser()));

  Halp.getDateFormatString(request);  //we set the date format here, incase we are using non-inside out rendering
%>

<c:choose>
  <c:when test='${diseaseName != null}'>
    <fmt:setBundle basename="MDSS-${diseaseName}" scope="session"/>
  </c:when>
  <c:otherwise>
  <fmt:setBundle basename="MDSS" scope="session"/>
  </c:otherwise>
</c:choose>

<html ng-app="dashboard">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="cache-control" content="max-age=0" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="pragma" content="no-cache" />
  
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/imgs/favicon.png" >
    <script>document.cookie = "PrevLoadTime=;path=/;expires=Thu, 01-Jan-1970 00:00:01 GMT";</script>
    <!-- Tell Runway what the application context path is. -->
    <script>
      window.com = window.com || {};
      window.com.runwaysdk = window.com.runwaysdk || {};
      window.com.runwaysdk.__applicationContextPath = "<%=request.getContextPath()%>";
    </script>  
        
    <jwr:style src="/bundles/yuiStyle.css" useRandomParam="false"/>
    <jwr:style src="/bundles/yui3Style.css" useRandomParam="false"/>

    <jwr:script src="/bundles/yuiBundle.js" useRandomParam="false"/>
    <jwr:script src="/bundles/Mojo.js" useRandomParam="false"/>
    <jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>
    <jwr:script src="/bundles/mdssBundle.js" useRandomParam="false"/>
    
    <c:if test="${generateJavaScriptClasses}">
      <script type="text/javascript" src="js/Localized.js.jsp"></script>
    </c:if>
    <c:if test="${generateJavaScriptClasses}">
      <script type="text/javascript" src="js/getClass.js.jsp?includeUniversalTypes=true"></script>
    </c:if>

    <title><mdss:localize key="dashboardViewer.title"/></title>
    
    <!-- CSS imports -->
    <jwr:style src="/bundles/main.css" useRandomParam="false" />
    <jwr:style src="/bundles/dashboard.css" useRandomParam="false" />
    <jwr:style src="/bundles/widget.css" useRandomParam="false"/>  
        
    <!-- Dynamic map CSS -->
    <jwr:style src="/js/kaleidoscope/report/ReportTable.css" useRandomParam="false"/>  
    <jwr:style src="/bundles/dynamic-map.css" useRandomParam="false" />
    <jwr:style src="/bundles/termtree.css" useRandomParam="false"/>
    
    <!-- Default imports -->
    <jwr:script src="/bundles/Mojo.js" useRandomParam="false"/> 
    <jwr:script src="/bundles/main.js" useRandomParam="false"/>  
    <jwr:script src="/bundles/widget.js" useRandomParam="false"/>  
    
    <!-- Dynamic map Javascript -->
    <jwr:script src="/bundles/termtree.js" useRandomParam="false"/>
    <jwr:script src="/bundles/dynamic-map.js" useRandomParam="false"/> 
    <jwr:script src="/bundles/ontology.js" useRandomParam="false"/>
    
    <script type="text/javascript">${js}</script>
    <script src="${pageContext.request.contextPath}/js/kaleidoscope/MapConfig.json"></script>
    
    <jwr:script src="/bundles/dashboard.js" useRandomParam="false"/> 
    
<!-- 
    <script type="text/javascript">
        $(document).ready(function(){
        com.runwaysdk.ui.Manager.setFactory("JQuery");
        });
    </script>  
 -->      

  </head>

  <body ng-controller="DashboardController as dashboard" ng-init="init('${geoserver}', '${workspace}', ${editDashboard}, ${editData})" style="text-align: left;" class="yui-skin-sam">
    <map-panel dashboard="dashboard"></map-panel>
    <dashboard-panel dashboard="dashboard"></dashboard-panel>
  
    <!-- modal -->
    <!-- <div class="modal fade" id="modal01" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false"> -->
    <div class="modal fade" id="modal01" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
      <!-- Filled in by ajax call to create/edit layer -->
    </div>
  
    <!-- modal -->
    <div class="modal fade" id="dashboardModal01" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
      <!-- Filled in by ajax call to create new dashboard -->
    </div>

    <floating-legends thematic-cache="dashboard.thematicLayerCache" reference-cache="dashboard.referenceLayerCache"></floating-legends>  
  
    <!-- map container -->
    <div class="bg-stretch">
      <div id="mapDivId" class="dynamicMap">
        <map-popup ng-if="dashboard.feature != null && dashboard.feature.show" feature="dashboard.feature"></map-popup>
      </div>
    </div>
  
    <!-- reporting container -->
    <report-panel has-report="dashboard.model.hasReport"></report-panel>
  
    <!-- allow a user to go to the top of the page -->
    <div class="skip">
      <a href="#wrapper"><mdss:localize key="dashboardViewer.backToTop"/></a>
    </div>

    <!-- dashboard builder modal -->  
    <builder-dialog></builder-dialog>
    
    <uploader-dialog></uploader-dialog>
    
    <!-- Dialog for cloning a dashboard  -->
    <div id="clone-container"></div>  
    <div id="dashboard-edit-container"></div>      
    
    <!-- thematic layer modal -->  
    <thematic-layer></thematic-layer>
    
    <!-- reference layer modal -->  
    <reference-layer></reference-layer>    
  </body>
</html>
