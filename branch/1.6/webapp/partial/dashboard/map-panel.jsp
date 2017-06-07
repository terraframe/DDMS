<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss" %>
<form action="#" class="control-form" id="control-form" ng-cloak>
  <div id="control-form-collapse-button">
    <i class="fa toggle-left" ng-click="ctrl.toggle()" ng-class="{'fa-angle-double-left' : ctrl.expanded, 'fa-angle-double-right' : !ctrl.expanded}"></i>
  </div>
  <fieldset>
    <legend class="none"><mdss:localize key="dashboardViewer.controlForm"/></legend>
    <button class="none"><mdss:localize key="dashboardViewer.save"/></button>

    <!-- Header icon -->
    <div class="info-box" style="height: 91px;width: 210px;">    
	  <div class="headerleft" style="height:80px;-moz-border-radius: 10px;width: 200px;padding: 20px 5px 5px 5px;background: #FFFFFF url(imgs/flags/current) no-repeat 95% 50%;">
		<div class="freeText" style="color: #000000;">
			<mdss:localize key="Country_Tagline" /><br />
			<mdss:localize key="Country" />
		</div>
	  </div>
	</div>
        
    <!-- Overlay Layers Panel -->
    <thematic-panel cache="dashboard.thematicLayerCache" dashboard="dashboard" ></thematic-panel>
        
    <!-- Reference Layer Panel -->       
    <reference-panel cache="dashboard.referenceLayerCache"></reference-panel>

    <!-- Base Layers Panel -->     
    <base-panel layers="dashboard.baseLayers" dashboard="dashboard" ></base-panel>
        
    <!-- Legend Panel --> 
    <legend-panel thematic-cache="dashboard.thematicLayerCache" reference-cache="dashboard.referenceLayerCache"></legend-panel>      
     
    <!-- Map Tools Panel -->       
    <article class="accordion info-box" id="map-tools-collapse-container">
      <div class="accordion-group sales-accortion" id="map-tools-sub-container">
        <div id="collapse-map-tools" class="accordion-body">
          <div class="accordion-inner holder" id="mapToolsContainer">
            <a ng-click="ctrl.exportMap()" class="fa fa-file-image-o map-tool-icon" download="map.png" title="<mdss:localize key='dashboardViewer.exportMapTooltip'/>" ></a>
            <a ng-click="ctrl.centerMap()" class="fa fa-arrows-alt map-tool-icon" title="<mdss:localize key='dashboardViewer.zoomMapToExtentTooltip'/>" ></a>
            <a ng-click="ctrl.toggleScale()" class="fa fa-balance-scale map-tool-icon" title="<mdss:localize key='dashboardViewer.toggleScaleTooltip'/>" ></a>
            <a ng-click="ctrl.toggleArrow()" class="fa fa-arrow-up map-tool-icon" title="<mdss:localize key='dashboardViewer.toggleArrowTooltip'/>" ></a>
            <a ng-click="ctrl.generateImages()" class="fa fa-newspaper-o map-tool-icon" title="<mdss:localize key='dashboardViewer.generateImageTooltip'/>" ></a>
          </div>              
        </div>
      </div>
    </article>     
  </fieldset>
</form>