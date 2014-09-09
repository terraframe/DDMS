<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.report.ReportItemDTO"%>



<%
  String webappRoot = request.getContextPath() + "/";
%>

<html>
  <head>
    <title>${pageTitle}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <link rel="icon" type="image/png" href="./imgs/favicon.png" >
      <jwr:style src="/bundles/yuiStyle.css" useRandomParam="false"/>
      <jwr:style src="/bundles/yui3Style.css" useRandomParam="false"/>
      <jwr:style src="/bundles/mdssScreen.css" media="all" useRandomParam="false"/>
      <jwr:script src="/bundles/yuiBundle.js" useRandomParam="false"/>
      <jwr:script src="/bundles/Mojo.js" useRandomParam="false"/>
      <jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>
      <jwr:style src="/bundles/jqueryStyle.css" useRandomParam="false"/>
      <jwr:script src="/bundles/jqueryBundle.js" useRandomParam="false"/>
      <jwr:script src="/bundles/genericDatatable.js" useRandomParam="false"/>
      <jwr:script src="/bundles/runwayDatatable.js" useRandomParam="false"/>      
      <script type="text/javascript" src="js/Localized.js.jsp" ></script>
      <script type="text/javascript" src="js/report.js"></script>
    
      <style type="text/css">
          div#navigationBar {
            background-color:#CA1413;
            border-bottom-style:solid;
            border-bottom: medium;
            border-bottom-color: black;
            color:#FFF
          }
          
          div#navigationBar label {
            color:#FFF
          }
          
          div#navigationBar img {
            max-height: 15px;
            max-width: 30px;
            margin: 5px;
          }
          
          div#menu-container {
            position: absolute;
          }
          
          #lower {
            width: 100%;
            position: absolute;
            bottom: 0;
          }
          
          #panel {
            display: none;
            padding: 10px;
            height : 200px;   
            overflow-y: scroll;   
            background-color: rgba(219, 219, 218, 1);     
            border-top: 1px solid #000;                           
          }
           
          .error-message {
            color: #F00;
            display: inline;
          }
          
          .clearfix:after {
            clear: both;
          }
          
          .clearfix:before, .clearfix:after {
            content: " ";
            display: table;
          }

          
          *, *:before, *:after {
          }          
                    
          .submit-form .field-row {
            padding: 0px 0px 5px 20px;
          }
          
          .submit-form fieldset {
            padding: 0px;
            border: none;
          }

          .submit-form .label-text, .submit-form label {
            width: 225px;
            float: left;
            margin: 0px;
            padding: 8px 70px 0px 0px;
            font-size: 13px;
            line-height: 18px;
            color: #333;
          }
          
          .submit-form input[type="text"], .submit-form .custom-combobox-input {
            float: left;
            width: 269px;
            height: 32px;
            margin: 0px 11px 0px 0px;
            padding: 3px 5px;
            font-size: 14px;
            line-height: 18px;
            color: #333;
            border: 1px solid #BBB;
            background: none repeat scroll 0% 0% #EEE;
            outline: medium none;   
          }
                    
          .submit-form select {
            float: left;
            width: 280px;
            margin: 0px 11px 0px 0px;
            padding: 3px 5px;
            font-size: 14px;
            line-height: 18px;
            color: #333;
            border: 1px solid #BBB;
            background: none repeat scroll 0% 0% #EEE;
            outline: medium none;   
          }
          
          .submit-form input[type="radio"] {
            float: left;
          }          
          
          .submit-form input[type="checkbox"] {
            float: left;
          }          
          
          .submit-form .checks-frame label {
            width: 120px;
            float: left;
            margin-left: 5px;
            padding: 0px 0px 0px 0px;
            font-size: 13px;
            line-height: 18px;
          }          
          
          .submit-form input.field-error[type="text"] {
            border-style: solid;
            border-color: #F00;
            border-width: 1px;
          }
          
          #parameters-buttons {
            float:right;
          }
                             
          .btn {
            display: inline-block;
            vertical-align: top;
            padding: 4px 18px;
            font-size: 14px;
            line-height: 18px;
            color: #F8F8F8;
            border: 1px solid #979796;
            box-shadow: none;
            outline: 0px none;
            border-radius: 0px;
            background: none repeat scroll 0% 0% #BBB;
            margin-left: 5px;
          }

          .btn-primary {
            color: #FFF;
            border-color: #009EDC;
            background: none repeat scroll 0% 0% #00BFFF;
          }     
          
           .ui-menu {
            width: 150px;
          }    
          
          .pageCount {
            display: inline-block;
            width: 50px;
            padding-top: 7px;
          }
          
          .ui-widget-header {
            border: none;
            background: #ffffff;
            color: rgb(102, 102, 102);
            font-weight: bold;
          }
          
.ui-state-active a,
.ui-state-active a:link,
.ui-state-active a:visited {
	color: #333;
	text-decoration: none;
}	

.ui-state-default a,
.ui-state-default a:link,
.ui-state-default a:visited {
    color: #333;
    text-decoration: none;
}

.ui-state-default,
.ui-widget-content .ui-state-default,
.ui-widget-header .ui-state-default {
	border: none;
	background: #ffffff;
	font-weight: bold;
	color: #333;
	cursor:pointer;	
}

.ui-state-hover,
.ui-widget-content .ui-state-hover,
.ui-widget-header .ui-state-hover,
.ui-state-focus,
.ui-widget-content .ui-state-focus,
.ui-widget-header .ui-state-focus {
	border: none;
	background: #ffffff;
	font-weight: bold;
	color: #333;
	cursor:pointer;	
}

.ui-state-active,
.ui-widget-content .ui-state-active,
.ui-widget-header .ui-state-active {
	border: 1px solid rgb(213, 214, 215);
	background: #ffffff;
	font-weight: bold;
	color: #333;
	cursor:pointer;	
}

.ui-state-default .ui-icon {
	background-image: none;
}
          
          
      </style>
  </head>
  <body class="style_0" style="margin:0px;">    
    <DIV id="navigationBar">
      <TABLE CELLSPACING="0" CELLPADDING="0" WIDTH="100%" HEIGHT="25px" CLASS="birtviewer_navbar">
        <TR height="30px">
          <TD>
            <a href="#" id="menu-button"><img src="imgs/icons/Menu.png" alt="${menu}" title="${menu}" /></a>
          </TD>
          <TD WIDTH="12px">&nbsp;</TD>          
          <TD WIDTH="100%">
            <B>
            </B>
          </TD>
          <TD WIDTH="15px">
            <mdss:localize key="first" var="first_page"/>            
            <a href="#" id="first-page"><img src="imgs/icons/First.png" alt="${first_page}" title="${first_page}" /></a>
          </TD>
          <TD WIDTH="15px">
            <mdss:localize key="prev" var="prev_page"/>            
            <a href="#" id="prev-page"><img src="imgs/icons/Prev.png" alt="${prev_page}" title="${prev_page}" /></a>
          </TD>
                              
          <TD WIDTH="8px">&nbsp;&nbsp;</TD>
          
          <TD ALIGN="right" NOWRAP><LABEL for="gotoPage"><b><mdss:localize key="goto"/></b></LABEL></TD>
          <TD WIDTH="2px">&nbsp;</TD>
          <TD ALIGN="right" WIDTH="30px">
            <INPUT ID='gotoPage' TYPE='text' VALUE='${pageNumber}' MAXLENGTH="8" SIZE='5' CLASS="birtviewer_navbar_input">
          </TD>
          <TD WIDTH="6px">&nbsp;</TD>
          <TD class="pageCount">
            / ${pageCount}
          </TD>
          <TD WIDTH="6px">&nbsp;</TD>
          <TD WIDTH="2px">&nbsp;</TD>
          <TD WIDTH="15px">
            <mdss:localize key="next" var="next_page"/>            
            <a href="#" id="next-page"><img src="imgs/icons/Next.png" alt="${next_page}" title="${next_page}" /></a>
          </TD>          
          <TD WIDTH="15px">
            <mdss:localize key="last" var="last_page"/>            
            <a href="#" id="last-page"><img src="imgs/icons/Last.png" alt="${last_page}" title="${last_page}" /></a>
          </TD>          
        </TR>
      </TABLE>
    </DIV>
    <div id="menu-container">
      <ul id="menu">
        <li>
          <a href="#"><mdss:localize key="export"/></a>                    
          <ul>
            <li ><a href="#" class="export" id="docx"><mdss:localize key="docx"/></a></li>
            <li ><a href="#" class="export" id="xlsx"><mdss:localize key="xlsx"/></a></li>
            <li ><a href="#" class="export" id="pdf"><mdss:localize key="pdf"/></a></li>
          </ul>
        </li>
        <li ><a href="#" id="parameters"><mdss:localize key="parameters"/></a></li>
      </ul>    
    </div>
            
    
    <div class="reportDiv">
      ${report}
    </div>
    <div id="lower">   
      <div id="panel">
          <div id="panel-content">
          </div>
      </div>
    </div>
    
    <%=Halp.loadTypes(new String[]{ReportItemDTO.CLASS})%>
    
    <script type="text/javascript">

      (function(){
        YAHOO.util.Event.onDOMReady(function(){   
          com.runwaysdk.ui.Manager.setFactory("JQuery");
          
          var id = '<%=request.getAttribute("id")%>';
          var pageNumber = <%=request.getAttribute("pageNumber")%>;
          var pageCount = <%=request.getAttribute("pageCount")%>;
          var required = <%=request.getAttribute("report") == null%>;
          var cached = <%=request.getAttribute("cache")%>;

          var report = new MDSS.report.ReportPage(id, pageNumber, pageCount, required, cached);
          report.init();
        })
      })();      
    </script>    
  </body>
</html>
