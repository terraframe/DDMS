(function(){
  function ReportPanelController($scope, localizationService, dashboardService) {
    var controller = this;
    controller.state = 'min';
    
    
    controller.setupMenu = function() {
      $scope.menuOptions = [];      
      
      if(controller.canEdit()) {        
        $scope.menuOptions.push([localizationService.localize("dashboardViewer", "upload"), function ($itemScope, $event, modelValue, text, $li) {
          controller.upload();
        }]);
        
        if($scope.hasReport) {
        	
          // Report actions
          $scope.menuOptions.push([localizationService.localize("dashboardViewer", "remove"), function ($itemScope, $event, modelValue, text, $li) {
            controller.remove();
          }]);
          
          $scope.menuOptions.push([localizationService.localize("report", "rptdesign"), function ($itemScope, $event, modelValue, text, $li) {
            controller.exportReport('rptdesign');
          }]);
          
          // Divider
          $scope.menuOptions.push(null);

          // Export actions
          $scope.menuOptions.push([localizationService.localize("report", "docx"), function ($itemScope, $event, modelValue, text, $li) {
            controller.exportReport('docx');
          }]);
          
          $scope.menuOptions.push([localizationService.localize("report", "xlsx"), function ($itemScope, $event, modelValue, text, $li) {
            controller.exportReport('xlsx');
          }]);
          
          $scope.menuOptions.push([localizationService.localize("report", "pdf"), function ($itemScope, $event, modelValue, text, $li) {
            controller.exportReport('pdf');
          }]);
        }        
        
        // Divider
        $scope.menuOptions.push(null);        
      }
      
      if($scope.opaque) {
        $scope.menuOptions.push([localizationService.localize("report", "translucent"), function ($itemScope, $event, modelValue, text, $li) {
          $scope.opaque = false;
          
          controller.setupMenu();
        }]);
      }
      else {
        $scope.menuOptions.push([localizationService.localize("report", "opaque"), function ($itemScope, $event, modelValue, text, $li) {
          $scope.opaque = true;
              
          controller.setupMenu();
        }]);        
      }
    }
      
    controller.canEdit = function() {
      return dashboardService.canEdit();
    }
    
    controller.collapse = function() {
      var height = $("#mapDivId").height();
    	
      if(controller.state === 'split'){
  	    controller.setReportPanelHeight(0, false);
        controller.state = 'min';
      }
      else if(controller.state === 'max'){
        var splitHeight = Math.floor(height / 2);
        
        controller.setReportPanelHeight(splitHeight, true);
        controller.state = 'split';
      }
    }
    
    controller.expand = function() {
      var height = $("#mapDivId").height();
    	
      if(controller.state === 'min'){
        var splitHeight = Math.floor(height / 2);
        
        controller.setReportPanelHeight(splitHeight, false);
        controller.state = 'split';
      }
      else if(controller.state === 'split'){
        var reportToolbarHeight = 30;
        
        controller.setReportPanelHeight(height + reportToolbarHeight, true);        
        controller.state = 'max';
      }    	
    }
    
    controller.setReportPanelHeight = function (height, flipButton) {
      var current = $("#reporticng-container").height();
      var toolbar = $("#report-toolbar").height();        
        
      // Minimize
      if(current > height)
      {
        var difference = (current - height);
          
        $("#reporticng-container").animate({ bottom: "-=" + difference + "px" }, 1000, function(){
            
          if(flipButton){
            $("#report-toggle-container").toggleClass("maxed");
          }
            
          $("#reporticng-container").css("bottom", "0px");                                                  
          $("#report-viewport").height(height-toolbar);
          $("#reporticng-container").height(height);
        });     
          
        // animate the loading spinner
        $(".standby-overlay").animate({top: "+=" + difference + "px"}, 1000);
      }
      // Maximize
      else if (current < height){
        var difference = (height - current);
          
        $("#reporticng-container").css("bottom", "-" + difference + "px");
        $("#reporticng-container").height(height);
        $("#report-viewport").height(height-toolbar);
              
        $("#reporticng-container").animate({bottom: "+=" + difference + "px"}, 1000, function() {
          if(flipButton){
            $("#report-toggle-container").toggleClass("maxed");
          }
        });
          
        // animate the loading spinner
        $(".standby-overlay").animate({top: "-=" + difference + "px"}, 1000);
        $(".standby-overlay").css("height", current + difference);
      }          
    }
    
    controller.upload = function(e) {
      var dashboardId = dashboardService.getDashboard().getDashboardId();
      
      var config = {
        type: 'dss.vector.solutions.kaleidoscope.report.KaleidoscopeReport',
        action: "update",
        viewAction: "edit",
        viewParams: {id: dashboardId},          
        width: 600,
        onSuccess : function(dto) {
          $("#report-export-container").show();
          
          $scope.hasReport = true;
          controller.setupMenu();
                    
          $scope.$apply();
        },
        onFailure : function(e) {
          	MDSS.ErrorModal(e.getLocalizedMessage());
        },
        onCancel : function(e) {
          var request = new Mojo.ClientRequest({
            onSuccess : function () {
              // Close the dialog ??
            },
            onFailure : function(e) {
              	MDSS.ErrorModal(e.getLocalizedMessage());
            }
          });
            
          dss.vector.solutions.kaleidoscope.report.KaleidoscopeReport.unlockByDashboard(request, dashboardId);
        }
      };
            
      new com.runwaysdk.ui.RunwayControllerFormDialog(config).render();
    }    
    
    controller.exportReport = function(format){
      $scope.$emit('exportReport', {format:format});
    }
    
    controller.remove = function() {
      var dashboardId = dashboardService.getDashboard().getDashboardId();
      
      var onSuccess = function(){
        $scope.hasReport = false;
        controller.setupMenu();        
        $scope.$apply();    	  
      };
      
      dashboardService.removeReport(dashboardId, "#report-viewport", onSuccess);
    }
    
    controller.setupMenu();
  }
  
  function ReportPanel() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/report-panel.jsp',
      scope: {
        hasReport:'=',
      },
      controller : ReportPanelController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
      }
    }    
  }
  
  angular.module("report-panel", ["ui.bootstrap.contextMenu", "angularResizable", "localization-service", "dashboard-service"]);
  angular.module('report-panel')
    .directive('reportPanel', ReportPanel);
})();