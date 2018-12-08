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
<div class="panel" data-id="{{attribute.mdAttributeId}}">
  <h4 class="panel-title">
    <a class="opener-link" data-toggle="collapse" ng-href="#collapse00{{identifier}}">{{attribute.label}}</a>
          
    <a href="#" class="opener attributeLayer" data-toggle="tooltip" data-original-title="New map layer" data-placement="left" ng-if="ctrl.canEdit()" ng-click="ctrl.newLayer(attribute.mdAttributeId)">
      <span><mdss:localize key="dashboardViewer.mapIt"/></span>
    </a>
  </h4>
    
  <!-- slide block -->
  <div id="{{'collapse00' + identifier}}" class="panel-collapse collapse">
    <div class="panel-body">
      <div class="filter-block">
        <div class="row-holder">
          <label><mdss:localize key="dashboardViewer.filter"/></label>
        </div>
        
        <div class="row-holder" ng-switch on="attribute.attributeType">
          <number-type whole="true" attribute="attribute" ng-switch-when="com.runwaysdk.system.metadata.MdAttributeInteger"></number-type>
          <number-type whole="true" attribute="attribute" ng-switch-when="com.runwaysdk.system.metadata.MdAttributeLong"></number-type>
          <number-type whole="false" attribute="attribute" ng-switch-when="com.runwaysdk.system.metadata.MdAttributeDecimal"></number-type>
          <number-type whole="false" attribute="attribute" ng-switch-when="com.runwaysdk.system.metadata.MdAttributeFloat"></number-type>
          <number-type whole="false" attribute="attribute" ng-switch-when="com.runwaysdk.system.metadata.MdAttributeDouble"></number-type>
          <date-type attribute="attribute" ng-switch-when="com.runwaysdk.system.metadata.MdAttributeDate"></date-type>
          <character-type attribute="attribute" ng-switch-when="com.runwaysdk.system.metadata.MdAttributeText"></character-type>
          <character-type attribute="attribute" ng-switch-when="com.runwaysdk.system.metadata.MdAttributeCharacter"></character-type>
          <ontology-type attribute="attribute" ng-switch-when="com.runwaysdk.system.metadata.MdAttributeReference"></ontology-type>
          <boolean-type attribute="attribute" ng-switch-when="com.runwaysdk.system.metadata.MdAttributeBoolean"></boolean-type>
        </div>      
      </div>
    </div>
  </div>
</div>