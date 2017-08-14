<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<!DOCTYPE html>

<c:set var="page_title" value="dhis2.title"  scope="request"/>

<head>
  <base href="<%=request.getContextPath()%>/dss.vector.solutions.kaleidoscope.UserMenuController.dhis2Management.mojo">
  
  <!-- CSS imports -->
  <jwr:style src="/bundles/main.css" useRandomParam="false" />
  <jwr:style src="/bundles/dashboard.css" useRandomParam="false" />
  
  <script>
    window.acp = "<%=request.getContextPath()%>";  
    window.appname = "dhis2-app";  
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
  
  .modal-backdrop.in {
    background: #fff;
    opacity: 1;
  }  
  
  .node-content-wrapper {
    width:100%
  }
  
  .tree li {
    background:inherit;
    list-style-type: inherit;
    padding: inherit;
    height: inherit;
    color: inherit;
  }  
  
  .tree.dropdown-menu li:hover, .tree.dropdown-menu li:focus {
    color:  inherit;
    background:  inherit;
    text-decoration: inherit;
  }  

  </style>
  

</head>

<div>
  <dhis2-app>
  
    <!-- TEMP CONTENT WHILE THE PAGE IS LOADING -->
    <mdss:localize key="bootstrap.loading"/>  
  </dhis2-app>
  <script type="text/javascript" src="${path}/dist/app.js"></script>  
<!-- 
  <script type="text/javascript" src="http://localhost:8040/dist/app.js"></script>  
 -->  
  
  
</div>
