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
  
  .modal-form .text input[type="text"] {
    font-size: 14px;
  }  
  
  .navbar {
    padding-right: 5px;
    width: 200px;
  }
  
  .bordered {
    border: 1px blue solid;
    padding-top: 25px;
    padding-bottom: 25px;    
    width: calc(100% - 220px);    
    margin-left: 220px;
    background-color: white;         
  }
  
  .geo-mapping .text input[type="text"] {
    background: #eee;
    border: 1px solid #aaa;
    width: 100%;
    margin: 0;
    height: inherit;
    padding: inherit;
    float:none;
    font-size: 14px;
    line-height: 20px;
    color: #333;
    box-shadow: none;
    outline: none;
  }  
  
  .geo-mapping .check-block {
    float: inherit;
    position: inherit;
    margin: inherit;
    padding: inherit;
    min-width: inherit;
    display: inherit;
    white-space: inherit;
  }  
  
  .geo-mapping .text {
    float: inherit;
    position: inherit;
    width: inherit;
  }  
  
  .geo-mapping .check-block .chk-area {
    margin: 3px 11px 0 0;
  }
  
  .geo-mapping .holder {
    width: calc(100% - 300px);
  }
  
  .section-title {
    margin-left: 220px;
  }
  
  .pageTitle {
    color: black;
  }  
    
  body {
    background-color: #e6e6e6;
  }
  
  .geo-mapping .chk-area .rad-area {
    width: 15px;
    height: 15px;
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
