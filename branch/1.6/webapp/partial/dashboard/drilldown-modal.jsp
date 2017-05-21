<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>

<div>
  <div class="modal-header">
    <h3 class="modal-title">
      <mdss:localize key="net.geoprism.gis.DynamicMap.drilldown.header"/>
    </h3>
  </div>
  <div class="modal-body" id="modal-body">
    <ul>
      <li ng-repeat="universal in ctrl.universals">
        <a ng-click="ctrl.select(universal)">{{universal.label}}</a>
      </li>
    </ul>  
    <mdss:localize key="net.geoprism.gis.DynamicMap.drilldown.message"/>    
  </div>
</div>
