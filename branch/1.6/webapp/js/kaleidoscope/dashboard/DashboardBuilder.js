(function(){
	
  function ModalInstanceCtrl ($uibModalInstance, layerNames) {
    var controller = this;
    controller.layerNames = layerNames;
    
    controller.ok = function () {
      $uibModalInstance.close();
    };

    controller.cancel = function () {
      $uibModalInstance.dismiss('cancel');
    };
  };

  function BuilderDialogController($scope, $rootScope, $timeout, $uibModal, builderService, datasetService) {
    var controller = this;
    
    controller.clear = function() {
      // Flag indicating if the modal and all of its elements should be destroyed
      $scope.show = false;
      
      // Flag indicating if the modal should be hidden, but preserve the elements
      $scope.hidden = false;
      
      $scope.busy = false;   
      $scope.showWidgetType = 'DESCRIPTION';
      $scope.errors = [];
      $scope.fileErrors = [];
        
      $scope.fields = null;
      $scope.dashboard = null;    
    };
    
    controller.cancel = function() {
      var onSuccess = function(newInstance) {      
        controller.clear();
        
        if(!newInstance) {
          $scope.$apply();
        }
      };
        
      builderService.unlock($scope.dashboard, onSuccess);
    };
    
    controller.persist = function() {
    	
      $scope.busy = true;
   
      var onSuccess = function(result) {      
        var layerNames = JSON.parse(result);
        
        if(layerNames.length > 0) {
        	
          var modalInstance = $uibModal.open({
            animation: false,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: com.runwaysdk.__applicationContextPath + '/partial/builder/modal-instance.jsp',
            controller: 'ModalInstanceCtrl',
            controllerAs: 'ctrl',
            size: 800,
            resolve: {
              layerNames: function () {
                return layerNames;
              }
            }
          });

          modalInstance.result.then(function () {
            $scope.busy = false;            
              
            controller.applyWithOptions(); 
          }, function () {
            $scope.busy = false;            
          });        	
        }
        else {
          $scope.busy = false;            
          $scope.$apply();
        	
          controller.applyWithOptions();
        }
      };    
      
      builderService.getLayersToDelete($scope.dashboard,'#ng-modal-overlay', onSuccess);
    };
    
    controller.applyWithOptions = function() {
      var onSuccess = function(result) {      
        controller.clear();
            
        $scope.$apply();

        var dashboard = JSON.parse(result);

        $scope.$emit('dashboardChange', {dashboard:dashboard});            
      }
          
      var onFailure = function(e){
        $scope.errors.push(e.message);
                       
        $scope.$apply();
              
        $('#builder-div').parent().parent().animate({ scrollTop: 0 }, 'slow');
      };             
                     
      // Clear all the errors
      $scope.errors = [];
          
      builderService.applyWithOptions($scope.dashboard,'#ng-modal-overlay', onSuccess, onFailure);
    }
    
    controller.init = function(result) {
      controller.clear();
      
      $scope.fields = result.fields;    
      $scope.dashboard = result.object;
      $scope.show = true;
    }
    
    controller.load = function(dashboardId, element) {
        
      var onSuccess = function(result) {
        
        controller.init(result);
      
        $scope.$apply();
      }
               
      builderService.loadDashboard(dashboardId, element, onSuccess);    
    }
    
    controller.setCategoryWidgetType = function(typeName) {
      $scope.showWidgetType = typeName;
    }
    
    controller.addDatasets = function(datasets) {
    	
      if($scope.dashboard != null) {
        for(var i = 0; i < datasets.length; i++) {
        }
      }
    }
    
    controller.addDatasets = function(datasets) {
      if($scope.dashboard != null) {
    	
        for(var i = 0; i < datasets.length; i++) {
          var dataset = datasets[i];
        
          if(!controller.exists(dataset)) {
            $scope.dashboard.options.types.push(dataset);
          }
        }
      }
    }
      
    controller.exists = function(dataset) {
      for(var i = 0; i < $scope.dashboard.options.types.length; i++) {
        if($scope.dashboard.options.types[i].id == dataset.id) {
          return true;
        }
      }
        
      return false;
    }
    
    /*
     * Data Upload Section
     */
    controller.uploadFile = function(files) {
      var connection = {
    	elementId : '#builder-div',
        onSuccess : function(result) {
          $scope.$emit('dataUpload', {information:result.information, options:result.options});            
                
          // Hide modal, but preserve the elements and values        
          $scope.hidden = true;
          $scope.$apply();
        },            
        onFailure : function(e){
          $scope.fileErrors.push(e.message);
                                 
          $scope.$apply();
                        
          $('#builder-div').parent().parent().animate({ scrollTop: 0 }, 'slow');
        },
      };
      
      // Reset the file Errors
      $scope.fileErrors = [];
      datasetService.uploadSpreadsheet(connection, files[0]);
    }
    
    /*
     * Event Listeners
     */
    $rootScope.$on('editDashboard', function(event, data){
      controller.load(data.dashboardId, data.element);
    });
    
    $rootScope.$on('newDashboard', function(event, data){
      controller.load(null, data.element);
    });
    
    $rootScope.$on('datasetChange', function(event, data){
    	
      if(data.datasets != null) {
        controller.addDatasets(data.datasets);    	  
      }
      
      if(data.finished) {
        $scope.hidden = false;
        $scope.$apply();        
      }
    }); 
    
    // Initialize an empty controller
    controller.clear();
  }
  
  function BuilderDialog() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/builder/builder-dialog.jsp',
      scope: {
      },
      controller : BuilderDialogController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
      }
    }    
  }
  
  function TextField($timeout) {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/builder/text-field.jsp',
      require: '^form',      
      scope: {
        field:'=',
        model:'=',
        maxlength:'=',
        minlength:'=',
        placeholdertext:'='
      },
      link: function (scope, element, attrs, form) {
        scope.form = form;  
        
        $timeout(function() {
          if(attrs.maxlength && attrs.maxlength > 0){
              scope.field.maxlength = attrs.maxlength;
            }
            else{
              scope.field.maxlength = 5000; // arbitrary default
            }
            
            if(attrs.minlength && attrs.minlength > 0){
              scope.field.minlength = attrs.minlength;
            }
            else{
              scope.field.minlength = 0; // arbitrary default
            }
            
            if(attrs.placeholdertext && attrs.placeholdertext.length > 0){
              if(attrs.placeholdertext.indexOf("'") == 0){
                scope.field.placeholdertext = attrs.placeholdertext.replace("'", "").slice(0, -1);
              }
              else{
                scope.field.placeholdertext = attrs.placeholdertext;
              }
            }
            else{
              scope.field.placeholdertext = ""; // arbitrary default
            }
          }, 500);
      }
    }    
  }
  
  function TextAreaField($timeout) {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/builder/text-area-field.jsp',
      require: '^form',      
      scope: {
        field:'=',
        model:'=',
        maxlength:'=',
        minlength:'=',
        placeholdertext:'='
      },
      link: function (scope, element, attrs, form) {
        scope.form = form;  
        
        $timeout(function() {
          if(attrs.maxlength && attrs.maxlength > 0){
              scope.field.maxlength = attrs.maxlength;
            }
            else{
              scope.field.maxlength = 5000; // arbitrary default
            }
            
            if(attrs.minlength && attrs.minlength > 0){
              scope.field.minlength = attrs.minlength;
            }
            else{
              scope.field.minlength = 0; // arbitrary default
            }
            
            if(attrs.placeholdertext && attrs.placeholdertext.length > 0){
              if(attrs.placeholdertext.indexOf("'") == 0){
                scope.field.placeholdertext = attrs.placeholdertext.replace("'", "").slice(0, -1);
              }
              else{
                scope.field.placeholdertext = attrs.placeholdertext;
              }
            }
            else{
              scope.field.placeholdertext = ""; // arbitrary default
            }
          }, 500);
      }
    }    
  }
  
  function SelectField() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/builder/select-field.jsp',
      scope: {
        field:'=',
        model:'='
      },
      require: ['^form', 'selectField'],            
      link: function (scope, element, attrs, ctrls) {
        scope.form = ctrls[0];
      }
    }    
  }
  
  function TypeAttributeController($scope) {
    $scope.$watch('attribute.selected', function() {
      // if an attribute is selected with the parent type not yet checked
      if($scope.attribute.selected && !$scope.type.value){
    	// set flag property when we know the user selected an attribute
    	$scope.srcToggledElType = "ATTRIBUTE";
        $scope.type.value = true;       
      }
      // else if an attribute is un-selected and the parent type is checked
      else if(!$scope.attribute.selected && $scope.type.value){
    	var othersSelected = false;
    	for(var i=0; i<$scope.type.attributes.length; i++){
    		var attr = $scope.type.attributes[i];
    		if(attr.selected){
    			othersSelected = true;
    		}
    	}
    	
    	// if no other children attributes are selected un-select the parent type
    	if(!othersSelected){
    		$scope.srcToggledElType = "ATTRIBUTE";
    		$scope.type.value = false;     
    	}
      }
    }, true);
        
    $scope.$watch('type.value', function() {
      ///////
      ////
      //// NOTE: This logic to handle checkbox behavior is a temporary solution and is hard to follow. 
      //// This will all be re-placed when we re-design the data set/attribute selection widget.
      ////
      ///////
    	
      // if parent type is un-selected clear the srcToggledElType because we 
      // only want to know if the user toggled an ATTRIBUTE
      if(!$scope.type.value){
    	  if($scope.srcToggledElType === "ATTRIBUTE"){
    		  $scope.srcToggledElType = "";
    	  }
      }
      
      // if parent type is selected because the user selected one of its child attributes 
      // before any others
      if($scope.type.value && $scope.srcToggledElType === "ATTRIBUTE"){
        $scope.attribute.selected = true;          
      }
      // else if parent type is selected and the parent type was not triggered by an attribute selection
      else if($scope.type.value && $scope.srcToggledElType !== "ATTRIBUTE"){
    	// are there any selected attributes under the parent type?
    	// selected attributes would occur when a parent type is selected after a user selects an attribute
    	// when no other attributes are selected yet
    	var selectedAttrId;
    	var lastAttrId;
      	for(var i=0; i<$scope.type.attributes.length; i++){
    		var attr = $scope.type.attributes[i];
    		if(attr.selected){
    			selectedAttrId = attr.id;
    		}
    		
    		if(i === $scope.type.attributes.length - 1){
    			lastAttrId = attr.id;
    		}
    	}
      	
      	// re-select the previously selected attribute (there must be only one at this point) when the parent 
      	// type is selected after a user selected a child attribute
      	if(selectedAttrId && selectedAttrId === $scope.attribute.id){
      		$scope.attribute.selected = true;   
      	}
      	// else if this attribute is the last attribute for the parent type and there is still no
      	// selected attribute
      	else if(lastAttrId === $scope.attribute.id && !selectedAttrId){
      		// select all the attributes under the parent because the parent type must have been 
      		// selected due to the lack of child attributes being selected up to this point
          	for(var i=0; i<$scope.type.attributes.length; i++){
        		var attr = $scope.type.attributes[i];
        		attr.selected = true;
        	}
      	}
      }
      else{
    	  $scope.attribute.selected = false;  
      }
    }, true);              
  }
  
  function TypeAttribute() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/builder/type-attribute.jsp',
      scope: {
        attribute:'=',
        type:'='
      },
      controller : TypeAttributeController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
      }
    }    
  }  

  angular.module("dashboard-builder", ["builder-service", "dataset-service", "styled-inputs", 'ngFileUpload', 'ui.bootstrap']);
  angular.module("dashboard-builder")
   .directive('textField', TextField)
   .directive('textAreaField', TextAreaField)
   .directive('selectField', SelectField)
   .directive('typeAttribute', TypeAttribute)
   .directive('builderDialog', BuilderDialog)
   .controller('ModalInstanceCtrl', ModalInstanceCtrl);
})();