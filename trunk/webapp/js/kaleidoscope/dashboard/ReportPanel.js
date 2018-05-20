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
  function ReportPanelController($scope, $timeout, localizationService, dashboardService) {
    var controller = this;
    controller.state = 'min';
    
    controller.init = function(state) {
      $scope.vertical = state.isReportVertical || false;
      $scope.opaque = state.isReportOpaque || false;
      
      if($scope.vertical) {
    	$timeout(function(){
          controller.setReportPanelWidth(state.reportXPosition, (state.reportXPosition === state.savedWidth));    		
    	}, 2);
      }
      else {
      	$timeout(function(){    	  
          controller.setReportPanelHeight(state.reportYPosition, (state.reportYPosition === state.savedHeight));    	  
    	}, 2);          
      }
      
      controller.setupMenu($scope.hasReport);      
    }
    
    controller.setupMenu = function(hasReport) {
      $scope.menuOptions = [];      
      
      if(controller.canEdit()) {        
        $scope.menuOptions.push([localizationService.localize("dashboardViewer", "upload"), function ($itemScope, $event, modelValue, text, $li) {
          controller.upload();
        }]);
        
        if(hasReport) {
          
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
          $scope.$emit("isReportOpaque", {opaque:false});
          
          controller.setupMenu($scope.hasReport);
        }]);
      }
      else {
        $scope.menuOptions.push([localizationService.localize("report", "opaque"), function ($itemScope, $event, modelValue, text, $li) {
          $scope.opaque = true;
          $scope.$emit("isReportOpaque", {opaque:true});

          controller.setupMenu($scope.hasReport);
        }]);        
      }
      
      if($scope.vertical) {
        $scope.menuOptions.push([localizationService.localize("report", "horizontal"), function ($itemScope, $event, modelValue, text, $li) {
          $scope.vertical = false;
          $scope.$emit("isReportVertical", {vertical:false});
          
          controller.state = 'min';
          
          var panelCollapsed = dashboardService.getDashboardPanelCollapsed();
          $scope.panelCollapsed = panelCollapsed;
          
          controller.setupMenu($scope.hasReport);          
          
          // Copy the current report html over to the new report div
          var html = $('#report-content').html();
          
          $timeout(function(){
            $('#report-content').html(html);        	  
          }, 500);          
        }]);
      }
      else {
        $scope.menuOptions.push([localizationService.localize("report", "vertical"), function ($itemScope, $event, modelValue, text, $li) {
          $scope.vertical = true;
          $scope.$emit("isReportVertical", {vertical:true});
          
          controller.state = 'min';  
          
          controller.setupMenu($scope.hasReport);          
          
          // Copy the current report html over to the new report div
          var html = $('#report-content').html();
          
          $timeout(function(){
            $('#report-content').html(html);        	  
          }, 500);
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
        var reportToolbarHeight = 15;
        
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
      
      $scope.$emit("reportYPosition", {height:height});
    }
    
    controller.horizontalCollapse = function() {
      var width = $("#mapDivId").width();
        
      if(controller.state === 'split'){
        controller.setReportPanelWidth(0, false);
        controller.state = 'min';
      }
      else if(controller.state === 'max'){
        var splitWidth = Math.floor(width / 2);
          
        controller.setReportPanelWidth(splitWidth, true);
        controller.state = 'split';
      }
    }
      
    controller.horizontalExpand = function() {
      var width = $("#mapDivId").width();
        
      if(controller.state === 'min'){
        var splitWidth = Math.floor(width / 2);
          
        controller.setReportPanelWidth(splitWidth, false);
        controller.state = 'split';
      }
      else if(controller.state === 'split'){
        var reportToolbarWidth = 15;
          
        controller.setReportPanelWidth(width + reportToolbarWidth, true);        
        controller.state = 'max';
      }      
    }
    
    controller.setReportPanelWidth = function (width, flipButton) {
      var current = $("#reporticng-container").width();
      var toolbar = $("#report-toolbar").width();        
  
      // Minimize
      if(current > width)
      {
        var difference = (current - width);
            
        $("#reporticng-container").css("left", "0px");
        
        $("#reporticng-container").animate({ width: "-=" + difference + "px" }, {duration: 1000, queue: false, complete: function(){
          if(flipButton){
            $("#report-toggle-container").toggleClass("maxed");
          }
        }});     
        
        $("#report-viewport").animate({ width: "-=" + difference + "px" }, {duration: 1000, queue: false, complete: function(){
	    }});    
            
        // animate the loading spinner
        $(".standby-overlay").animate({left: "+=" + difference + "px"}, 1000);
      }
      // Maximize
      else if (current < width){
        var difference = (width - current);
            
        $("#reporticng-container").css("left", "0px");
        $("#reporticng-container").animate({width: "+=" + difference + "px"}, {duration: 1000, queue: false, complete: function() {
          if(flipButton){
            $("#report-toggle-container").toggleClass("maxed");
          }
        }});
        
        $("#report-viewport").animate({width: "+=" + difference + "px"}, {duration: 1000, queue: false, complete: function() {
        }});
            
        // animate the loading spinner
        $(".standby-overlay").animate({left: "-=" + difference + "px"}, 1000);
        $(".standby-overlay").css("width", current + difference);
      }          

      $scope.$emit("reportXPosition", {width:width});
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
        $scope.$apply();        
      };
      
      dashboardService.removeReport(dashboardId, "#report-viewport", onSuccess);
    }
    
    $scope.$watch('hasReport', function(newValue, oldValue){        
      controller.setupMenu(newValue);      
    });        
    
    $scope.$on('angular-resizable.resizing', function(event, info){
      if($scope.vertical) {
        $("#report-viewport").width(info.width);            
      }
      else {
        $("#report-viewport").height(info.height);    
      }
    });            
    
    $scope.$on('angular-resizable.resizing', function(event, info){
      if($scope.vertical) {
        var max = $("#mapDivId").width();
          
        if((max-info.width) <= 0) {
          $("#report-toggle-container").addClass("maxed");
          $("#reporticng-container").width(max + 15);
          $("#report-viewport").width(max);
                  
          controller.state = 'max';
        }
        
        $scope.$emit("reportXPosition", {width:info.width});        
      }
      else {      	
        var max = $("#mapDivId").height();
          
        if((max-info.height) <= 0) {
          $("#report-toggle-container").addClass("maxed");
          $("#reporticng-container").height(max + 15);
          $("#report-viewport").height(max);
                
          controller.state = 'max';
        }
        
        $scope.$emit("reportYPosition", {height:info.height});        
      }
    });        
    
    $scope.$on('dashboardStateChange', function(event, data){
      controller.init(data.state);
    });    
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

        element.on('click', function(event) {
          var target = event.target;
          
          if(target.tagName === 'A') {
            var hash = target.hash;
            
            if(hash.startsWith('#report')) {
              var split = hash.split('/');
              
              if(split.length == 3) {
                scope.$emit('refreshReport', {pageNumber : split[2], id : split[1]})
              }
              else if(split.length == 2) {
                // Goto Hash
                var top = $('#report-viewport').scrollTop();
                var offset = $('#report-viewport').offset().top;
                var elemOff = $('#' + split[1]).offset().top;
                
                $('#report-viewport').scrollTop(top - offset + elemOff);
              }
            }
            
          }
          
        });        
        
      }
    }    
  }
  
  angular.module("report-panel", ["ui.bootstrap.contextMenu", "angularResizable", "localization-service", "dashboard-service"]);
  angular.module('report-panel')
    .directive('reportPanel', ReportPanel);
})();