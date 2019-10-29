<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ page import="dss.vector.solutions.util.Halp"%>
<%@ page import="dss.vector.solutions.util.LocalizationFacadeDTO"%>
<%@ page import="dss.vector.solutions.util.OrientationTypeDTO"%>
<%@ page import="dss.vector.solutions.util.SessionInfoUtilDTO"%>
<%@ page import="dss.vector.solutions.util.SessionInfoController"%>
<%@ page import="dss.vector.solutions.util.OrientationType"%>
<%@ page import="com.runwaysdk.web.WebClientSession"%>
<%@ page import="com.runwaysdk.ClientSession"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.Enumeration"%>

<%@ page import="dss.vector.solutions.AnalyticsProperties"%>

<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
request.setAttribute("generateJavaScriptClasses", (clientRequest != null && !clientRequest.isPublicUser()));
%>
<% Halp.getDateFormatString(request);  //we set the date format here, incase we are using non-inside out rendering%>
<c:choose>
  <c:when test='${diseaseName != null}'>
    <fmt:setBundle basename="MDSS-${diseaseName}" scope="session"/>
  </c:when>
  <c:otherwise>
	<fmt:setBundle basename="MDSS" scope="session"/>
  </c:otherwise>
</c:choose>

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>

  <!-- Google Tag Manager -->
  <script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
  new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
  j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
  'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
  })(window,document,'script','dataLayer','<%=AnalyticsProperties.getGoogleTagManagerTrackingId()%>');</script>
  <!-- End Google Tag Manager -->

    
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="./imgs/favicon.png" >
<script>document.cookie = "PrevLoadTime=;path=/;expires=Thu, 01-Jan-1970 00:00:01 GMT";</script>
    
<script>
  window.com = window.com || {};
  window.com.runwaysdk = window.com.runwaysdk || {};
  window.com.runwaysdk.__applicationContextPath = "<%=request.getContextPath()%>";
  window.com.googletagmanagertrackingcode = "<%=AnalyticsProperties.getGoogleTagManagerTrackingId()%>";
  window.com.googleanalyticstrackingcode = "<%=AnalyticsProperties.getGoogleAnalyticsTrackingId()%>";
</script>  

<jwr:style src="/bundles/yuiStyle.css" useRandomParam="false"/>
<jwr:style src="/bundles/yui3Style.css" useRandomParam="false"/>
<%
ClientSession clientSession = (ClientSession) pageContext.findAttribute(ClientConstants.CLIENTSESSION);
if (clientSession==null)
{
  ArrayList<Locale> arrayList = new ArrayList<Locale>();
  Enumeration<Locale> locales = pageContext.getRequest().getLocales();
  while (locales.hasMoreElements())
  {
    arrayList.add(locales.nextElement());
  }
  Locale[] array = arrayList.toArray(new Locale[arrayList.size()]);
  
  clientSession = ClientSession.createAnonymousSession(array);
  clientRequest = clientSession.getRequest();
}
OrientationType orientation = SessionInfoController.getSessionLocaleOrientation();


if (orientation.equals(OrientationType.LTR)) { 
%>
<jwr:style src="/bundles/mdssScreen.css" media="all" useRandomParam="false"/>
<%
} else if (orientation.equals(OrientationType.RTL)) {
%>
<jwr:style src="/bundles/mdssScreen-rtl.css" media="all" useRandomParam="false"/>
<%
}
%>
<jwr:script src="/bundles/yuiBundle.js" useRandomParam="false"/>
<jwr:script src="/bundles/Mojo.js" useRandomParam="false"/>
<jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>

<c:if test="${generateJavaScriptClasses}">
  <script type="text/javascript" src="js/Localized.js.jsp"></script>
</c:if>
<jwr:script src="/bundles/mdssBundle.js" useRandomParam="false"/>
<c:if test="${generateJavaScriptClasses}">
  <script type="text/javascript" src="js/getClass.js.jsp?includeUniversalTypes=true"></script>
</c:if>
<c:choose>
  <c:when test='${localized_page_title != null }'>
    <title>${localized_page_title}</title>
  </c:when>
  <c:when test='${window_title != null}'>
    <title><mdss:localize key="${window_title}"/></title>
  </c:when>
  <c:otherwise>
    <title><mdss:localize key="${page_title}"/></title>
  </c:otherwise>
</c:choose>

  <c:choose>
    <c:when test='${session_info_polling != null }'>
      <!-- SessionInfoPoller (Ticket 3997) -->
      <%=Halp.loadTypes(new String[]{SessionInfoUtilDTO.CLASS,SessionInfoController.CLASS})%>
      <jwr:script src="/bundles/jqueryBundle.js" useRandomParam="false"/>
      <jwr:script src="/bundles/sessioninfo.js" useRandomParam="false"/>
    </c:when>
  </c:choose>
</head>
<body class="yui-skin-sam">

  <!-- Google Tag Manager (noscript) -->
  <noscript><iframe src="https://www.googletagmanager.com/ns.html?id=<%=AnalyticsProperties.getGoogleTagManagerTrackingId()%>"
   height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
  <!-- End Google Tag Manager (noscript) -->
  
<div id="header">
	<div id="headerleft">
		<div id="freeText">
			<mdss:localize key="Country_Tagline" /><br />
			<mdss:localize key="Country" />
		</div>
	</div>
	<div id="headerright"></div>
	<div id="headermiddle"><mdss:localize key="System_Name" /></div>
</div>

