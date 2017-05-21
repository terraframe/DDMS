(function(){
  function MapPopupController($scope, $uibModal, dashboardService, localizationService, mapService) {
    var controller = this;
    
    controller.canEditData = function() {
      var feature = $scope.feature;
      
      if(feature != null && feature.aggregationStrategy != null) {
        var strategyType = feature.aggregationStrategy.type;
          
        return (dashboardService.canEditData() && feature.geoId != null && (strategyType == 'GeometryAggregationStrategy' || strategyType == 'GEOMETRY'));      
      }
      
      return false;
    }
    
    controller.zoomToFeatureExtent = function() {
      var selectedFeatureInfo = dashboardService.getSelectedFeatureInfo();
      mapService.zoomToFeatureExtent(selectedFeatureInfo);
    }
    
    controller.editData = function() {
              
      var onSuccess = function(json) {
        var information = JSON.parse(json);
        var dashboard = dashboardService.getDashboard();
          
        var form = new dss.vector.solutions.kaleidoscope.FeatureForm(dashboard, information);
        form.render();
      };
      
      dashboardService.getFeatureInformation($scope.feature, onSuccess);
    }
    
    controller.close = function() {
      mapService.clearOverlays();
      
      $scope.feature.show = false;      
    }
    
    controller.addOverlay = function(element, coordinate) {
      mapService.clearOverlays();      
      mapService.addOverlay(element, coordinate);      
    }
    
    controller.getDrillUniversal = function(){
      var feature = $scope.feature;
      
      var onSuccess = function(response) {
        var universals = JSON.parse(response);
        
        if(universals.length > 1) {
          // Pop-message determining the right universal to use
          var modalInstance = $uibModal.open({
            animation: false,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/drilldown-modal.jsp',
            controller: 'DrilldownModalCtrl',
            controllerAs: 'ctrl',
            size: 800,
            resolve: {
              universals: function () {
                return universals;
              }
            }
          });

          modalInstance.result.then(function (universal) {
            $scope.$emit('drillDown', {layerId : feature.layerId, geoId : feature.geoId, universalId : universal.universalId});
          }, function () {});          
        }
        else if(universals.length == 1) {
          // Drill-down
          $scope.$emit('drillDown', {layerId : feature.layerId, geoId : feature.geoId, universalId : universals[0].universalId});
        }
        else {
          // Nothing to drill down into  
          localizationService.errorModal("net.geoprism.gis.DynamicMap.lowestUniversal");          
        }
      };
      
      dashboardService.getDrillDownUniversals(feature.layerId, feature.geoId, onSuccess);
    } 
  }
  
  function DrilldownModalCtrl ($uibModalInstance, universals) {
    var controller = this;
    controller.universals = universals;
      
    controller.select = function (universal) {
      $uibModalInstance.close(universal);
    };
  };
  
  function MapPopup() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/map-popup.jsp',
      scope: {
        feature:'='
      },
      controller : MapPopupController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
        scope.$watch('feature', function(){
          if(scope.feature != null) {
            ctrl.addOverlay(element[0], scope.feature.coordinate);
          }  
        }, true);
      }
    }    
  }
  
  angular.module("dashboard-map", ['ui.bootstrap', "dashboard-service", "map-service", "localization-service"]);
  angular.module('dashboard-map')
    .directive('mapPopup', MapPopup)
    .controller('DrilldownModalCtrl', DrilldownModalCtrl);
})();