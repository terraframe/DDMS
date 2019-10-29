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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>

<div>    
  <article ng-if="!vertical" id="reporticng-container" class="reporticng-container report-panel-closed"  resizable r-directions="['top']" r-flex="false">
    <div id="report-toolbar" ng-class="{widePanel: panelCollapsed}" class="report-toolbar">
      <div id="report-toggle-container" class="report-toggle-container">
        <i ng-show="ctrl.state != 'min'" ng-click="ctrl.collapse()" id="report-collapse-toggle" class="fa fa-angle-double-down report-height-toggle"></i>
        <i id="report-menu-toggle" class="fa fa-bars report-height-toggle" context-menu="menuOptions" context-menu-on="click"></i>        
        <i ng-show="ctrl.state != 'max'" ng-click="ctrl.expand()" id="report-expand-toggle" class="fa fa-angle-double-up report-height-toggle"></i>
      </div>      
    </div>
    <div id="report-viewport" class="report-viewport" ng-class="{'opaque' : opaque, 'translucent' : !opaque, widePanel: panelCollapsed }">    
      <div id="report-content">
      </div>   
    </div>
  </article>
  
  <article ng-if="vertical" id="reporticng-container" class="h-reporticng-container report-panel-closed"  resizable r-directions="['right']" r-flex="false">
    <div id="report-toggle-container" class="h-report-toggle-container">
      <div class="h-vert-center-container">
        <i ng-show="ctrl.state != 'min'" ng-click="ctrl.horizontalCollapse()" id="report-collapse-toggle" class="fa fa-angle-double-left report-height-toggle"></i>
        <i id="report-menu-toggle" class="fa fa-bars report-height-toggle" context-menu="menuOptions" context-menu-on="click"></i>        
        <i ng-show="ctrl.state != 'max'" ng-click="ctrl.horizontalExpand()" id="report-expand-toggle" class="fa fa-angle-double-right report-height-toggle"></i>
      </div>
    </div>      
    
    <div id="report-viewport" class="h-report-viewport" ng-class="{'opaque' : opaque, 'translucent' : !opaque }">    
      <div id="report-content">
      </div>   
    </div>
  </article>
</div>
