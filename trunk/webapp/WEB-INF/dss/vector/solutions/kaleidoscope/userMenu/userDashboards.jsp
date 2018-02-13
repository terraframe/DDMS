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
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>


<c:set var="page_title" value="userDashboards.heading"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<head>
  <!-- User account CSS -->
  <jwr:style src="/bundles/main.css" useRandomParam="false"/> 
  <jwr:style src="/bundles/widget.css" useRandomParam="false"/>    
  <jwr:style src="/bundles/user-dashboards.css" useRandomParam="false"/> 
  
  <!-- User account Javascript -->
  <jwr:script src="/bundles/builder.js" useRandomParam="false"/>

  <script type="text/javascript">${js}</script>
  
  <style type="text/css">
  body {
    background-color: #333;
    margin: 0;
    min-width: 100%;
    background-image: url(${pageContext.request.contextPath}/imgs/earth-profile.jpg);
    background-size: cover;
  }
  
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
/* 	height: 80%; */
  height: calc(100% - 210px);
	overflow-y: auto;
  }
  
  .pageFooter {
     color: white;
  }
  
  h3 {
    font-size: 16px;
    margin-top: 0;
    margin-bottom: 0;
  }
  
  .dashboard-thumnail-ico-ctrl {
    font-size: 18px !important;
  }
  
  .pageTitle {
    margin-top: 15px;
    text-align: center;
  }

  .ng-modal-dialog {
	z-index: 999;
	position: absolute;
	width: 50%;
	top: 40%;
	left: 50%;
	transform: translate(-50%, -50%);
	-webkit-transform: translate(-50%, -50%);
	-moz-transform: translate(-50%, -50%);
	overflow-y: auto;
	height: 500px;
	background-color: rgba(255,255,255,0.95);
	border: 2px solid black;
	overflow-y: scroll;
  }
  
  </style>
</head>

<div ng-app="dashboard-menu" ng-cloak>

  <c:if test="${not empty param.errorMessage}">
    <div class="error-message">
      <p>${param.errorMessage}</p>
    </div>
  </c:if>  
  
  <div id="container" ng-controller="DashboardMenuController as ctrl" >
    
    <div class="row"></div>
    <div class="col-md-2"></div>
    <div class="col-md-8">
    <div ng-repeat="id in ctrl.ids" ng-init="dashboard = ctrl.dashboards[id]" ng-cloak>
    <div ng-if="($index ) % 3 === 0" class="row">
        <div ng-if="ctrl.dashboards[ctrl.ids[$index]]" class="col-sm-6 col-md-4">
          <div  class="thumbnail text-center">
            <a ng-href="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.createMapForSession.mojo?dashboard={{ctrl.dashboards[ctrl.ids[$index]].dashboardId}}" target="_blank" class="" >
              <!-- NOTE: the onerror method that sets the default icon if now saved dashboard exists -->
              <img ng-src="dss.vector.solutions.kaleidoscope.UserMenuController.getDashboardMapThumbnail.mojo?dashboardId={{ctrl.dashboards[ctrl.ids[$index]].dashboardId}}" 
              onerror="if (this.src != 'imgs/dashboard_icon_small.png') this.src = 'imgs/dashboard_icon_small.png';"              
              alt="Dashboard">
              
              <div class="caption">
                <h3>{{ctrl.dashboards[ctrl.ids[$index]].label}}</h3>
                <p>{{ctrl.dashboards[ctrl.ids[$index]].description}}</p>
<div class="dashboard-card-ico-button-container">     
            <div class="dashboard-thumbnail-ico-group">
            <a href="#" class="fa fa-cog ico-dashboard-options dashboard-thumnail-ico-ctrl" title="<mdss:localize key="userDashboards.editDashboardTooltip"/>" ng-click="ctrl.edit(ctrl.dashboards[ctrl.ids[$index]].dashboardId)" ></a> 
<!-- 
            <a href="#" class="fa fa-clone ico-dashboard dashboard-thumnail-ico-ctrl" title="<mdss:localize key='dashboardViewer.newDashboardTooltip'/>" ng-click="ctrl.cloneDashboard(ctrl.dashboards[ctrl.ids[$index]].dashboardId)"></a>
 -->            
            <a href="#" class="fa fa-trash-o ico-remove dashboard-thumnail-ico-ctrl" title="<mdss:localize key="userDashboards.deleteDashboardTooltip"/>" ng-click="ctrl.remove(ctrl.dashboards[ctrl.ids[$index]].dashboardId)" ></a>
            </div>
            </div>
              </div>
            </a>              
          </div>
        </div>
        
        <!-- CREATE DASHBOARD CARD #2 
        Why 3 semi-redundant blocks you might ask? To wrap groups of 3 in a bootstrap ROW. -->
        <div ng-if="ctrl.dashboards[ctrl.ids[$index + 1]]" class="col-sm-6 col-md-4">
          <div  class="thumbnail text-center">
            <a ng-href="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.createMapForSession.mojo?dashboard={{ctrl.dashboards[ctrl.ids[$index + 1]].dashboardId}}" target="_blank" class="" >
              
              <!-- NOTE: the onerror method that sets the default icon if now saved dashboard exists -->
              <img ng-src="dss.vector.solutions.kaleidoscope.UserMenuController.getDashboardMapThumbnail.mojo?dashboardId={{ctrl.dashboards[ctrl.ids[$index + 1]].dashboardId}}" onerror="if (this.src != 'imgs/dashboard_icon_small.png') this.src = 'imgs/dashboard_icon_small.png';" alt="Dashboard">
              
              <div class="caption">
                <h3>{{ctrl.dashboards[ctrl.ids[$index + 1]].label}}</h3>
                <p>{{ctrl.dashboards[ctrl.ids[$index + 1]].description}}</p>
          <div ng-if="ctrl.editDashboard" class="dashboard-card-ico-button-container">     
            <div class="dashboard-thumbnail-ico-group">
            <a href="#" class="fa fa-cog ico-dashboard-options dashboard-thumnail-ico-ctrl" title="<mdss:localize key="userDashboards.editDashboardTooltip"/>" ng-click="ctrl.edit(ctrl.dashboards[ctrl.ids[$index + 1]].dashboardId)" ></a> 
<!-- 
            <a href="#" class="fa fa-clone ico-dashboard dashboard-thumnail-ico-ctrl" title="<mdss:localize key='dashboardViewer.newDashboardTooltip'/>" ng-click="ctrl.cloneDashboard(ctrl.dashboards[ctrl.ids[$index + 1]].dashboardId)"></a>
 -->            
            <a href="#" class="fa fa-trash-o ico-remove dashboard-thumnail-ico-ctrl" title="<mdss:localize key="userDashboards.deleteDashboardTooltip"/>" ng-click="ctrl.remove(ctrl.dashboards[ctrl.ids[$index + 1]].dashboardId)" ></a>
            </div>
            </div>
              </div>
            </a>              
          </div>
        </div>
        
        <!-- CREATE DASHBOARD CARD #3 
        Why 3 semi-redundant blocks you might ask? To wrap groups of 3 in a bootstrap ROW.-->
        <div ng-if="ctrl.dashboards[ctrl.ids[$index + 2]]" class="col-sm-6 col-md-4">
          <div  class="thumbnail text-center">
            <a ng-href="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.createMapForSession.mojo?dashboard={{ctrl.dashboards[ctrl.ids[$index + 2]].dashboardId}}" target="_blank" class="" >
              
              <!-- NOTE: the onerror method that sets the default icon if now saved dashboard exists -->
              <img ng-src="dss.vector.solutions.kaleidoscope.UserMenuController.getDashboardMapThumbnail.mojo?dashboardId={{ctrl.dashboards[ctrl.ids[$index + 2]].dashboardId}}" onerror="if (this.src != 'imgs/dashboard_icon_small.png') this.src = 'imgs/dashboard_icon_small.png';" alt="Dashboard">
              
              <div class="caption">
                <h3>{{ctrl.dashboards[ctrl.ids[$index + 2]].label}}</h3>
                <p>{{ctrl.dashboards[ctrl.ids[$index + 2]].description}}</p>
          <div ng-if="ctrl.editDashboard" class="dashboard-card-ico-button-container">     
            <div class="dashboard-thumbnail-ico-group">
            <a href="#" class="fa fa-cog ico-dashboard-options dashboard-thumnail-ico-ctrl" title="<mdss:localize key="userDashboards.editDashboardTooltip"/>" ng-click="ctrl.edit(ctrl.dashboards[ctrl.ids[$index + 2]].dashboardId)" ></a> 
<!-- 
            <a href="#" class="fa fa-clone ico-dashboard dashboard-thumnail-ico-ctrl" title="<mdss:localize key='dashboardViewer.newDashboardTooltip'/>" ng-click="ctrl.cloneDashboard(ctrl.dashboards[ctrl.ids[$index + 2]].dashboardId)"></a>
 -->            
            <a href="#" class="fa fa-trash-o ico-remove dashboard-thumnail-ico-ctrl" title="<mdss:localize key="userDashboards.deleteDashboardTooltip"/>" ng-click="ctrl.remove(ctrl.dashboards[ctrl.ids[$index + 2]].dashboardId)" ></a>
            </div>
            </div>
              </div>
            </a>              
          </div>
        </div>
        
<!-- CREATE NEW DASHBOARD LINK IN EXISTING ROW -->
        <div ng-if="ctrl.dashboards[ctrl.ids[$index]].isLastDashboard || 
        ctrl.dashboards[ctrl.ids[$index + 1]].isLastDashboard && 
        ctrl.editDashboard" 
        class="col-sm-6 col-md-4" ng-click="ctrl.newDashboard()">
         <a href="#" class="new-dashboard-btn" >
            <div class="thumbnail text-center">
              <div class="frame-box">
                <div class="inner-frame-box">
                  <i class="fa fa-plus"></i>
                </div>
              </div>
              <div class="caption">
                <h3><mdss:localize key="userDashboards.newDashboardTitle"/></h3>
              </div>
            </div>
          </a>
        </div>
    </div>
    </div>
        
        <!-- CREATE NEW LINK IN NEW ROW -->
        <div ng-if="!ctrl.isInExistingRow && ctrl.editDashboard" class="row">
        <div class="col-sm-6 col-md-4" ng-click="ctrl.newDashboard()">
          <a href="#" class="new-dashboard-btn" >
            <div class="thumbnail text-center">
              <div class="frame-box">
                <div class="inner-frame-box">
                  <i class="fa fa-plus"></i>
                </div>
              </div>
              <div class="caption">
                <h3><mdss:localize key="userDashboards.newDashboardTitle"/></h3>
              </div>
            </div>
          </a>
        </div> 
      </div>
    </div>      
    <div class="col-md-2"></div>
       
    <builder-dialog></builder-dialog>
    
    <uploader-dialog></uploader-dialog>
    
    <clone-form></clone-form>
  </div>
</div>