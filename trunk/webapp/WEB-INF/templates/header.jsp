<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<html>
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
<div id="freeText"><strong>National Malaria Control Program</strong><br/>MALAWI</div>
<div id="uploadLogo"><img src="./imgs/design/sample_logo.gif" width="85" height="57" /></div>
<div class="header">
<div class="hTitle"></div>
</div>
<%@ include file="/WEB-INF/templates/navMenu.jsp"%>