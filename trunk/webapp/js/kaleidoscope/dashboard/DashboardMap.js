/*
 * Copyright (C) 2008 IVCC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
    
    controller.rollup = function() {
      $scope.$emit('rollup', {});      
    }
    
    controller.getDrillUniversal = function(){
      var feature = $scope.feature;
      
      var onSuccess = function(response) {
        var layers = JSON.parse(response);
        
        var drilldown = {
          geoId : feature.geoId,
          universals : {}
        };
        
        if(layers.length > 0) {          
          var handler = function(index) {
          
            if(index == layers.length) {
              $scope.$emit('drillDown', drilldown);
            }
            else {
              var layer = layers[index];
              var universals = layer.universals
                            
              if(universals.length > 1) {
                // Pop-message determining the right universal to use
                var modalInstance = $uibModal.open({
                  animation: false,
                  ariaLabelledBy: 'modal-title',
                  ariaDescribedBy: 'modal-body',
                  templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/drilldown-modal.jsp',
                  backdrop: 'static',
                  size: 600,
                  windowClass: 'basic-modal',
                  backdropClass: 'basic-modal',                  
                  controller: 'DrilldownModalCtrl',
                  controllerAs: 'ctrl',
                  resolve: {
                    universals: function () {
                      return universals;
                    },
                    header: function () {
                      var message = localizationService.localize("net.geoprism.gis.DynamicMap.drilldown", "header");
                      message = message.replace("{0}", layer.label);
                      
                      return message;
                    }
                  }
                });

                modalInstance.result.then(function (universal) {
                	
                  for(var i = 0; i < layer.ids.length; i++) {
                	var id = layer.ids[i];
                	
                    drilldown.universals[id] = universal.universalId;    
                  }
                    
                  if(index < layers.length) {
                    handler(index + 1);                    
                  }
                }, function () {});          
              }
              else if(universals.length == 1) {
                for(var i = 0; i < layer.ids.length; i++) {
              	var id = layer.ids[i];
              	
                  drilldown.universals[id] = universals[0].universalId;    
                }
                
                if(index < layers.length) {
                  handler(index + 1);                  
                }
              }
            }
          }
          
          handler(0);
        }
        else {
          // Nothing to drill down into  
          localizationService.errorModal("net.geoprism.gis.DynamicMap.lowestUniversal");          
        }
      };
      
      var dashboard = dashboardService.getDashboard();
      var state = dashboard.getCompressedState();
      
      dashboardService.getDrillDownUniversals(state, feature.geoId, onSuccess);
    } 
  }
  
  function DrilldownModalCtrl ($uibModalInstance, universals, header) {
    var controller = this;
    controller.universals = universals;
    controller.header = header;
      
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
        feature:'=',
        drilldown :'='
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
  
  angular.module("dashboard-map", ['ui.bootstrap', "dashboard-service", "map-service", "localization-service", "styled-inputs"]);
  angular.module('dashboard-map')
    .directive('mapPopup', MapPopup)
    .controller('DrilldownModalCtrl', DrilldownModalCtrl);
})();