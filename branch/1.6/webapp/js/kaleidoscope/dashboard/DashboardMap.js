/*
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
(function(){
  function MapPopupController($scope, dashboardService, mapService) {
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
  }
  
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
  
  angular.module("dashboard-map", ["dashboard-service", "map-service"]);
  angular.module('dashboard-map')
    .directive('mapPopup', MapPopup);
})();