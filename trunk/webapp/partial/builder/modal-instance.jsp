<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>

<div>
  <div class="modal-header">
    <h3 class="modal-title">
      <mdss:localize key="dashboardViewer.information"/>
    </h3>
  </div>
  <div class="modal-body" id="modal-body">
    <mdss:localize key="dashboardViewer.removeLayers"/>
    <ul>
      <li ng-repeat="layerName in ctrl.layerNames">{{layerName}}</li>
    </ul>  
  </div>
  <div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="ctrl.cancel()"><mdss:localize key="dashboardViewer.cancel"/></button>
    <button class="btn btn-primary" type="button" ng-click="ctrl.ok()"><mdss:localize key="dashboardViewer.ok"/></button>
  </div>
</div>
