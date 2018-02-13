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

<div class="ol-overlay-container">
  <div id="popup" class="ol-popup">
    <a href="#" ng-click="ctrl.close()" id="popup-closer" class="ol-popup-closer"></a>
    <h3 class="popup-heading">{{feature.layerDisplayName}}</h3>
    <div class="map-popup-table-wrapper">
	    <table class="table">
	      <thead class="popup-table-heading">
	        <tr>
	          <th><mdss:localize key="dss.vector.solutions.kaleidoscope.gis.OpenLayersMap.location"/></th>  
	          <th><mdss:localize key="dss.vector.solutions.kaleidoscope.gis.OpenLayersMap.aggregationMethod"/></th> 
	          <th><mdss:localize key="dss.vector.solutions.kaleidoscope.gis.OpenLayersMap.aggregateValue"/></th> 
	        </tr>  
	      </thead>
	      <tbody>  
	        <tr> 
	          <td>{{feature.featureDisplayName}}</td>  
	          <td>{{feature.aggregationMethod}}</td>  
	          <td>{{feature.attributeValue}}</td>  
	        </tr>
	        <tr ng-if="ctrl.canEditData()"> 
	          <td colspan="3">
	            <a href="#" ng-click="ctrl.editData()" class="edit-feature"><mdss:localize key="dss.vector.solutions.kaleidoscope.gis.OpenLayersMap.editFeature"/></a>
	          </td>  
	        </tr>
	      </tbody>
	    </table>
    </div>
    <div>
      <a href="#" ng-click="ctrl.zoomToFeatureExtent()"><mdss:localize key="dss.vector.solutions.kaleidoscope.gis.OpenLayersMap.zoomToFeatureBtnLabel"/></a>
    </div>
    <div ng-if="feature.featureGeom.type != 'Point'">
      <a href="#" ng-click="ctrl.getDrillUniversal()"><mdss:localize key="net.geoprism.gis.DynamicMap.drilldown"/></a>    
    </div>
    <div ng-if="feature.featureGeom.type != 'Point' && drilldown.length > 0">
      <a href="#" ng-click="ctrl.rollup()"><mdss:localize key="net.geoprism.gis.DynamicMap.rollup"/></a>    
    </div>
  </div>         
</div>