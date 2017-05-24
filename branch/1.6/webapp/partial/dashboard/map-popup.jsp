<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>

<div class="ol-overlay-container">
  <div id="popup" class="ol-popup">
    <a href="#" ng-click="ctrl.close()" id="popup-closer" class="ol-popup-closer"></a>
    <h3 class="popup-heading">{{feature.layerDisplayName}}</h3>
    <div class="map-popup-table-wrapper">
	    <table class="table">
	      <thead class="popup-table-heading">
	        <tr>
	          <th><mdss:localize key="net.geoprism.gis.DynamicMap.location"/></th>  
	          <th><mdss:localize key="net.geoprism.gis.DynamicMap.aggregationMethod"/></th> 
	          <th><mdss:localize key="net.geoprism.gis.DynamicMap.aggregateValue"/></th> 
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
	            <a href="#" ng-click="ctrl.editData()" class="edit-feature"><mdss:localize key="net.geoprism.gis.DynamicMap.editFeature"/></a>
	          </td>  
	        </tr>
	      </tbody>
	    </table>
    </div>
    <div>
      <a href="#" ng-click="ctrl.zoomToFeatureExtent()"><mdss:localize key="net.geoprism.gis.DynamicMap.zoomToFeatureBtnLabel"/></a>
    </div>
    <div ng-if="feature.featureGeom.type != 'Point'">
      <a href="#" ng-click="ctrl.getDrillUniversal()"><mdss:localize key="net.geoprism.gis.DynamicMap.drilldown"/></a>    
    </div>
    <div ng-if="feature.featureGeom.type != 'Point' && drilldown.length > 0">
      <a href="#" ng-click="ctrl.rollup()"><mdss:localize key="net.geoprism.gis.DynamicMap.rollup"/></a>    
    </div>
  </div>         
</div>