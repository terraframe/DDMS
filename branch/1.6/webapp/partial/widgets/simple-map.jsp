<%--

    Copyright (c) 2015 TerraFrame, Inc. All rights reserved.

    This file is part of Runway SDK(tm).

    Runway SDK(tm) is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Runway SDK(tm) is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>

<div>

  <div ng-if="errors.length > 0" class="error-container" ng-cloak>
    <div class="label-holder">
      <strong><mdss:localize key='dashboard.errorsLabel'/></strong>
    </div>
    <div class="holder">
      <div ng-repeat="error in errors" >
        <p class="error-message">{{error}}</p>
      </div>
    </div>
  </div>
  
  <!-- TODO: this isn't tested and so most likely doesn't work -->
  <div ng-if="map.data === null"><mdss:localize key='category.management.loadingData'/></div>
  
  <!-- map container -->
  <div class="map-wrapper">
    <div id="mapDivId" class="dynamicMap">
    	<div id="base-layer-switcher-wrapper" ng-show="showBaseLayerPanel" ng-mouseleave="ctrl.baseLayerPanelMouseOut()">
    		<div class="layer-toggle-wrapper" ng-repeat="lyr in baseLayers">
    			<input type="radio" name="baseLayer" ng-value="lyr" ng-model="activeBaseLayer" ng-click="ctrl.toggleBaseLayer(lyr)">
    			{{lyr.layerLabel}} 
    			<br>
    		</div>
    	</div>
    </div>
  </div>
  
   
</div>
