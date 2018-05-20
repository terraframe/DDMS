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
	
  function DashboardPanelController($scope, $timeout, widgetService, dashboardService) {
    var controller = this;
    controller.expanded = true;
    dashboardService.setDashboardPanelCollapsed(false);
    
    controller.toggle = function() {
      var speed = 500;
        
      if(controller.expanded){
        widgetService.animate("#dashboardMetadata", {right: "-=300"}, speed, function(){
          controller.expanded = false;
          dashboardService.setDashboardPanelCollapsed(true);
          $scope.$apply();
          
          $scope.$emit("isExpandRight", false);          
        });

        // Report Panel background
        widgetService.animate("#report-viewport", {marginRight: "0px"}, speed);
        
        // Repprt panel toolbar
        widgetService.animate("#report-toolbar", {marginRight: "0px"}, speed);
      }
      else{    	  
        widgetService.animate("#dashboardMetadata", {right: "+=300"}, speed, function(){
          controller.expanded = true;
          dashboardService.setDashboardPanelCollapsed(false);
          $scope.$apply();
          
          $scope.$emit("isExpandRight", true);                    
        });

        // Report Panel background
        widgetService.animate("#report-viewport", {marginRight: "300px"}, speed);
            
        // Repprt panel toolbar
        widgetService.animate("#report-toolbar", {marginRight: "300px"}, speed);
      }
    }
    
    controller.init = function(state) {
      if(state.isExpandRight != null && controller.expanded !== state.isExpandRight) {
        controller.toggle();
      }
    }    
    
    $scope.$on('dashboardStateChange', function(event, data){
      controller.init(data.state);
    });        
  }
	  
  function DashboardPanel() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/dashboard-panel.jsp',
      scope: {
        dashboard:'='
      },
      controller : DashboardPanelController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
      }
    }    
  }  
	
  angular.module("dashboard-panel", ["widget-service", "dashboard-accordion", "dashboard-service"]);
  angular.module('dashboard-panel')
    .directive('dashboardPanel', DashboardPanel);  
})();