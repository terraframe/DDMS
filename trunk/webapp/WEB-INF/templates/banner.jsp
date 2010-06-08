<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ page import="dss.vector.solutions.util.Halp"%>
<% Halp.getDateFormatString(request);  //we set the date format here, incase we are using non-inside out rendering%>
<fmt:setBundle basename="MDSS-${diseaseName}" scope="session"/>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="./imgs/favicon.png" >
<script>document.cookie = "PrevLoadTime=;path=/;expires=Thu, 01-Jan-1970 00:00:01 GMT";</script>
<jwr:style src="/bundles/yuiStyle.css" useRandomParam="false"/>
<jwr:style src="/bundles/mdssScreen.css" media="all" useRandomParam="false"/>
<jwr:script src="/bundles/yuiBundle.js" useRandomParam="false"/>
<jwr:script src="/bundles/Mojo.js" useRandomParam="false"/>
<script type="text/javascript" src="js/Localized.js.jsp"></script>
<jwr:script src="/bundles/mdssBundle.js" useRandomParam="false"/>
<script type="text/javascript" src="js/getClass.js.jsp?includeUniversalTypes=true"></script>
<c:choose>
  <c:when test='${window_title != null}'>
    <title><fmt:message key="${window_title}"/></title>
  </c:when>
  <c:otherwise>
    <title><fmt:message key="${page_title}"/></title>
  </c:otherwise>
</c:choose>
</head>
<body class="yui-skin-sam">
<div id="header">
	<div id="headerleft">
		<div id="freeText">
			<fmt:message key="Country_Tagline" /><br />
			<fmt:message key="Country" />
		</div>
	</div>
	<div id="headerright"></div>
	<div id="headermiddle"><fmt:message key="System_Name" /></div>
</div>

