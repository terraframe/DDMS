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
<%@ page import="com.runwaysdk.web.WebClientSession"%>
<%@ page import="com.runwaysdk.ClientSession"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.Enumeration"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="./imgs/favicon.png" >
<script>document.cookie = "PrevLoadTime=;path=/;expires=Thu, 01-Jan-1970 00:00:01 GMT";</script>
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
OrientationTypeDTO orientation = LocalizationFacadeDTO.getSessionLocaleOrientation(clientRequest);


if (orientation.equals(OrientationTypeDTO.LTR)) { 
%>
<jwr:style src="/bundles/mdssScreen.css" media="all" useRandomParam="false"/>
<%
} else if (orientation.equals(OrientationTypeDTO.RTL)) {
%>
<jwr:style src="/bundles/mdssScreen-rtl.css" media="all" useRandomParam="false"/>
<%
}
%>
<jwr:script src="/bundles/yuiBundle.js" useRandomParam="false"/>
<jwr:script src="/bundles/Mojo.js" useRandomParam="false"/>
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
</head>
<body class="yui-skin-sam">
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

