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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<!DOCTYPE html>

<c:set var="page_title" value="prism.management.title"  scope="request"/>

<head>
  <base href="<%=request.getContextPath()%>/dss.vector.solutions.kaleidoscope.DataSetController.management.mojo">
  
  <!-- CSS imports -->
  <jwr:style src="/bundles/main.css" useRandomParam="false" />
  <jwr:style src="/bundles/dashboard.css" useRandomParam="false" />
  
  <script>
    window.acp = "<%=request.getContextPath()%>";  
    window.appname = "my-app";  
  </script>
  <script type="text/javascript">${js}</script>
  
  <!-- IE required polyfills, in this exact order -->
  <script type="text/javascript" src="${path}/dist/polyfills.js"></script>  
  <script type="text/javascript" src="${path}/dist/vendor.js"></script>  
<!-- 
  <script type="text/javascript" src="http://localhost:8040/dist/polyfills.js"></script>  
  <script type="text/javascript" src="http://localhost:8040/dist/vendor.js"></script>  
 -->  
  
  <style type="text/css">
  #headermiddle {
	-moz-border-radius: 10px;
	margin: 0 95px 5px 220px;
	padding: 22px 0 0 10px;
	height: 80px;
	background-color: #CA1413;
	color: #FFFFFF;
	font-size: 32px;
  }

  #headerleft {
	-moz-border-radius: 10px;
	height: 80px;
	width: 210px;
	margin: 0 0 0 5px;
	padding: 22px 5px 0 5px;
	position: absolute;
	background: #FFFFFF url(${pageContext.request.contextPath}/imgs/flags/current) no-repeat 95% 50%;
  }

  div.pageContent {
	padding: 13px 25px 0 25px;
	height: 80%;
	overflow-y: auto;
  }

  </style>
  

</head>

<script type="text/javascript">
<c:choose>
  <c:when test="${not empty requestScope.reconstructionJSON}">
    window.reconstructionJSON = ${requestScope.reconstructionJSON};
  </c:when>
  <c:otherwise>
    window.reconstructionJSON = "";
  </c:otherwise>
</c:choose>
</script>

<div>
  <my-app>
  
    <!-- TEMP CONTENT WHILE THE PAGE IS LOADING -->
    <mdss:localize key="bootstrap.loading"/>  
  </my-app>
  <script type="text/javascript" src="${path}/dist/app.js"></script>  
<!-- 
  <script type="text/javascript" src="http://localhost:8040/dist/app.js"></script>  
 -->  
  
  
</div>
