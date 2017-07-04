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
        });

        // Report Panel background
        widgetService.animate("#report-viewport", {marginRight: "300px"}, speed);
            
        // Repprt panel toolbar
        widgetService.animate("#report-toolbar", {marginRight: "300px"}, speed);
      }
    }
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