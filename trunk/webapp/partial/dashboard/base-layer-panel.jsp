<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>

<article class="accordion info-box" id="base-map-container">
  <div class="accordion-group sales-accortion">
    <div class="accordion-heading">
      <a class="map-layers-opener opener" data-toggle="collapse" data-parent="#base-map-container" href="#collapse-base-maps"><mdss:localize key="dashboardViewer.baseMaps"/></a>
    </div>
    <div id="collapse-base-maps" class="accordion-body" style="height: 0px;">
      <div class="accordion-inner holder" id="baseLayerContainer">
        <div class="checkbox-container" ng-repeat='layer in layers'>
          <div class="row-form jcf-class-check chk-area com-runwaysdk-ui-factory-runway-checkbox-CheckBox com-runwaysdk-ui-factory-runway-Widget" ng-class="{'checked' : layer.isActive}" ng-click="ctrl.toggle(layer.layerId)"></div>
          <label class="checkbox-label">{{layer.layerLabel}}</label>
        </div>      
      </div>
    </div>
  </div>
</article>
