(function(){
  
  function DashboardService(runwayService) {
    var service = {};
    service.edit = false;
    service.editData = false;
    service.dashboard = null;
    
    service.setEdit = function(edit) {
      service.edit = edit;
    };
    
    service.canEdit = function() {
      return service.edit;
    };
    
    service.setEditData = function(editData) {
      service.editData = editData;
    };
    
    service.canEditData = function() {
      return service.editData;
    };
    
    service.setDashboard = function(dashboard) {
      service.dashboard = dashboard;
    };
    
    service.getDashboard = function() {
      return service.dashboard;
    };
    
    service.getSelectedFeatureInfo = function() {
    	return service.getDashboard().feature;
    }
        
    service.updateLegend = function(layer, onSuccess, onFailure) {
      if(service.canEdit()) {
        var request = runwayService.createRequest(onSuccess, onFailure);
                   
        dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer.updateLegend(request, layer.layerId, layer.legendXPosition, layer.legendYPosition, layer.groupedInLegend);        
      }
    };
    
    service.removeLayer = function(layerId, elementId, onSuccess, onFailure) {
      if(service.canEdit()) {      
        var request = runwayService.createStandbyRequest(elementId, onSuccess, onFailure);
          
        com.runwaysdk.Facade.deleteEntity(request, layerId);
      }
    };
    
    service.orderLayers = function(mapId, layerIds, elementId, onSuccess, onFailure) {
      if(service.canEdit()) {      
        var request = runwayService.createStandbyRequest(elementId, onSuccess, onFailure);
        
        dss.vector.solutions.kaleidoscope.dashboard.DashboardMap.orderLayers(request, mapId, layerIds);
      }
    };
    
    service.setDashboardBaseLayer = function(dashboardId, baseMap, onSuccess, onFailure) {
      if(service.canEdit()){
        var request = runwayService.createRequest(onSuccess, onFailure);
      
        dss.vector.solutions.kaleidoscope.dashboard.Dashboard.setBaseLayerState(request, dashboardId, baseMap);
      }
    }
    
    service.refreshMap = function(state, elementId, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
              
      dss.vector.solutions.kaleidoscope.dashboard.DashboardMap.refresh(request, state.mapId, state);
    }
    
    service.getDrillDownUniversals = function(layerId, geoId, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
      var mapId = this.getDashboard().getModel().mapId;      
      
      dss.vector.solutions.kaleidoscope.dashboard.DashboardMap.getDrillDownUniversals(request, mapId, layerId, geoId);
    }
    
    service.getDashboardJSON = function(dashboardId, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
          
      dss.vector.solutions.kaleidoscope.dashboard.Dashboard.getJSON(request, dashboardId);
    }
    
    service.getAvailableDashboardsAsJSON = function(dashboardId, onSuccess, onFailure) {
//      var request = runwayService.createRequest(onSuccess, onFailure);
      var request = runwayService.createStandbyRequest("#container", onSuccess, onFailure);
    
      dss.vector.solutions.kaleidoscope.dashboard.Dashboard.getAvailableDashboardsAsJSON(request, dashboardId);
    }
    
    service.saveDashboardState = function(dashboardId, state, global, elementId, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
      
      dss.vector.solutions.kaleidoscope.dashboard.Dashboard.saveState(request, dashboardId, state, global);      
    }
    
    service.getGeoEntitySuggestions = function(dashboardId, text, size, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
    
      dss.vector.solutions.kaleidoscope.dashboard.Dashboard.getGeoEntitySuggestions(request, dashboardId, text, size);
    }
    
    service.getTextSuggestions = function(mdAttributeId, term, limit, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);

      dss.vector.solutions.kaleidoscope.dashboard.Dashboard.getTextSuggestions(request, mdAttributeId, term, limit);
    }
    
    service.getTermSuggestions = function(mdAttributeId, term, limit, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
    
      dss.vector.solutions.kaleidoscope.dashboard.Dashboard.getTermSuggestions(request, mdAttributeId, term, limit);
    }
    
    service.getFeatureInformation = function(feature, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);

      var layerId = feature.layerId;
      var geoId = feature.geoId;

      dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayer.getFeatureInformation(request, layerId, geoId); 	
    }
    
    service.hasReport = function(dashboardId, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
      
      dss.vector.solutions.kaleidoscope.dashboard.Dashboard.hasReport(request, dashboardId);    	
    }
    
    service.runReport = function(dashboardId, configuration, elementId, onSuccess, onFailure) {
      var request = runwayService.createStandbyRequest(elementId, onSuccess, onFailure);
    	
      dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportController.run(request, dashboardId, configuration);
    }
    
    service.removeDashboard = function(dashboardId, elementId, onSuccess, onFailure) {
      var request = runwayService.createStandbyRequest(elementId, onSuccess, onFailure);
            
      com.runwaysdk.Facade.deleteEntity(request, dashboardId);    	
    }
    
    service.removeReport = function(dashboardId, elementId, onSuccess, onFailure) {
      var request = runwayService.createStandbyRequest(elementId, onSuccess, onFailure);
        
      dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportController.remove(request, dashboardId);
    }
    
    service.cloneDashboard = function(dashboardId, label, elementId, onSuccess, onFailure) {
      var request = runwayService.createStandbyRequest(elementId, onSuccess, onFailure);
    
      dss.vector.solutions.kaleidoscope.dashboard.DashboardController.clone(request, dashboardId, label);                    
    }
    
    service.newClone = function(dashboardId, elementId, onSuccess, onFailure) {
      var request = runwayService.createStandbyRequest(elementId, onSuccess, onFailure);
      
      dss.vector.solutions.kaleidoscope.dashboard.DashboardController.newClone(request, dashboardId);    
    }
    
    service.getClassifierTree = function(mdAttributeId, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);    
      
      dss.vector.solutions.kaleidoscope.dashboard.Dashboard.getClassifierTree(request, mdAttributeId);
    }
    
    service.generateThumbnailImage = function(dashboardId, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);    
      
      dss.vector.solutions.kaleidoscope.dashboard.Dashboard.generateThumbnailImage(request, dashboardId);      
    }
    
    service.setDataSetOrder = function(dashboardId, typeIds, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
        
      dss.vector.solutions.kaleidoscope.dashboard.Dashboard.setMetadataWrapperOrder(request, dashboardId, typeIds);
    }
    
    service.setDataSetAttributeOrder = function(dashboardId, typeId, attributeIds, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
    
      dss.vector.solutions.kaleidoscope.dashboard.Dashboard.setDashboardAttributesOrder(request, dashboardId, typeId, attributeIds);
    }
    
    service.getJobJSON = function(onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
      var dashboardId = this.getDashboard().model.id;      
      
      dss.vector.solutions.kaleidoscope.dashboard.DashboardJob.getJSON(request, dashboardId);
    }
    
    service.applyJob = function(job, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
      
      dss.vector.solutions.kaleidoscope.dashboard.DashboardJob.applyJSON(request, job);
    }
    
    service.unlockJob = function(id, onSuccess, onFailure) {
      var request = runwayService.createRequest(onSuccess, onFailure);
      
      dss.vector.solutions.kaleidoscope.dashboard.DashboardJob.unlock(request, id);
    }
    
    service.isEmptyFilter = function(filter) {
      for(var key in filter) {
        if(key != 'type' && key != 'operation' && key != 'mdAttribute' && filter.hasOwnProperty(key)) {
          var value = filter[key];
          
          if(value != null) {
            if( $.type( value ) === "string") {
              if(value.length > 0) {
                return false;
              }
            }
            else if (typeof value === 'object') {
              return (Object.keys(value).length === 0);
            }
            else {
              return false;            
            }
          }
        }
      }
      return true;
    }
    
    return service;
  }
  
  angular.module("dashboard-service", ["runway-service"]);
  angular.module("dashboard-service")
    .factory('dashboardService', DashboardService)
})();