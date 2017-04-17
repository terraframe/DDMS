(function(){
  
  function LocationFilterController($scope, dashboardService, $timeout) {
    var controller = this;
    
    controller.source = function( request, response ) {
      var onSuccess = function(query){
        var resultSet = query.getResultSet()
                                    
        var results = [];
                  
        $.each(resultSet, function( index, result ) {
          var label = result.getValue('entityLabel');
          var id = result.getValue('id');
                    
          results.push({'label':label, 'value':label, 'id':id});
        });
                  
        response( results );
      };
                      
      dashboardService.getGeoEntitySuggestions($scope.dashboardId, request.term, 10, onSuccess);
    }
    
    controller.remove = function($index) {
      $scope.filter.locations.splice($index, 1);
      
      controller.setDatasetListHeight();
    }
    
    controller.add = function(item) {
      if(!controller.contains(item.id)) {
        $scope.filter.locations.push({
          label : item.label,
          value : item.id
        });
      }
    }
    
    
	controller.setDatasetListHeight = function(){
		var offset = function(){
			var dataSetListContainer = document.getElementById("type-accordion");
			var offset = $(".nav-bar.sidebar-nav-bar").height() + $("#location-filter-container").height() + 45;
			dataSetListContainer.style.maxHeight = "calc(100% - "+ offset +"px)";
		}
		
		$timeout(offset, 5);
	}
    
    controller.contains = function(id) {
      for(var i = 0; i < $scope.filter.locations.length; i++) {
        if($scope.filter.locations[i].value == id) {
          return true;
        }
      }
      
      return false;
    }
    
    $scope.$watch('filter.locations', function(newValue, oldValue) {
        var dataSetListContainer = document.getElementById("type-accordion");
        if(newValue && dataSetListContainer){
        	if(!oldValue){
        		controller.setDatasetListHeight();
        	}
        }
    });
  }
  
  function LocationFilter() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/location-filter.jsp',      
      scope: {
        filter:'=',
        dashboardId:'='
      },
      controller : LocationFilterController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
        var input = $(element).find(".filter-geo");
        
        input.autocomplete({
          source: ctrl.source,
          select: function(event, ui) {
        	ctrl.add(ui.item);
            
            scope.$apply();
            
            ctrl.setDatasetListHeight();
            
            $(this).val(''); return false;
          }, 
          minLength: 2
        });        
      }
    }    
  }  
  
  function TypeAccordionController($scope, $timeout, dashboardService, widgetService) {
    var controller = this;

    controller.move = function(element, event, ui) {
      if(dashboardService.canEdit()){

        var typeIds = [];
         
        var children = element.children();
          
        for (var i = 0; i < children.length; i++) {
          var typeId = $(children[i]).data('id');
          
          typeIds.push(typeId);
        }      
        
        var dashboardId = dashboardService.getDashboard().getDashboardId();
      
        dashboardService.setDataSetOrder(dashboardId, typeIds);
      }
    }
    
    controller.init = function(element) {
      // Add support for sorting the dashboard data sets    	
      if(element != null && dashboardService.canEdit()) {
        widgetService.sortable(element, function(e, ui){
          controller.move(element, e, ui);
        });        
      }
    }
  }
  
  function TypeAccordion() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/dashboard-accordion.jsp',      
      scope: {
        types:'=',
        newLayer:'&'
      },
      controller : TypeAccordionController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
        /* Hook-up drag and drop */        
        ctrl.init(element);
      }
    }    
  }
  
  function AttributePanelController($scope, dashboardService, widgetService) {
    var controller = this;
    
    controller.move = function(element, event, ui) {
      if(dashboardService.canEdit()){

        var attributeIds = [];
             
        var children = element.children();
              
        for (var i = 0; i < children.length; i++) {
          var attributeId = $(children[i]).data('id');
              
          attributeIds.push(attributeId);
        }      
            
        var dashboardId = dashboardService.getDashboard().getDashboardId();
        var typeId = $scope.type.id;
          
        dashboardService.setDataSetAttributeOrder(dashboardId, typeId, attributeIds);
      }
    }
    
    controller.init = function(element) {
      if(element != null && dashboardService.canEdit()) {      
        widgetService.sortable(element, function(e, ui){
          controller.move(element, e, ui);
        });        
      }
    }
  }
  
  function AttributePanel() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/attribute-panel.jsp',      
      scope: {
        type:'=',
        index:'='
      },
      controller : AttributePanelController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
        /* Hook-up drag and drop */        
        ctrl.init(element);
      }
    }    
  }
  
  function AccordionAttributeController($scope, $timeout, dashboardService) {
    var controller = this;
    
    controller.canEdit = function() {
      return dashboardService.canEdit();
    }
    
    controller.newLayer = function(mdAttributeId) {
      $scope.$emit('newThematicLayer', {mdAttributeId:mdAttributeId});
    }
    
    controller.expand = function(element) {
      /*
       * There is a timing issue between when the angular finishes process
       * the ng-href and ng-attr attributes.  Thus we need to delay the
       * acutal opening of the attribute block.
       */  
      $timeout(function(){
        $(element).find(".opener-link").click();
      }, 1000);
    }
    
    
  }
  
  function AccordionAttribute($timeout, dashboardService) {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/accordion-attribute.jsp', 
      scope: {
        attribute:'=',
        identifier:'@'
      },
      controller : AccordionAttributeController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrl) {
      
        // Don't collapse the element if there are filtering values            
        $timeout(function(){        
          if(!dashboardService.isEmptyFilter(scope.attribute.filter)) {
            ctrl.expand(element);
          }      
        }, 0);
      }
    }    
  }
  
  function AttributeTypeController($scope, $timeout) {
    var controller = this;

    controller.init = function(element) {
    }
    
    controller.getTextSuggestions = function(mdAttributeId, request, response ) {
        
      var limit = 10;
              
      var onSuccess = function(results){
              
        response( results );
      };
            
      var onFailure = function(e){
        console.log(e);
      };
         
      var text = request.term;
      
      dashboardService.getTextSuggestions(mdAttributeId, term, 10, onSuccess, onFailure);
    }
  }
  
  function NumberType() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/number-type.jsp',      
      require: ['^form', 'numberType'],
      controller : AttributeTypeController,
      controllerAs : 'ctrl',
      scope: {
        attribute:'=',
        whole:'=',
      },
      link: function (scope, element, attrs, ctrls) {
        scope.form = ctrls[0];

        ctrls[1].init(element);
      }
    }    
  }
  
  function DateType($parse) {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/date-type.jsp',      
      require: ['^form'],
      scope: {
        attribute:'='
      },
      link: function (scope, element, attrs, ctrl) {
        scope.form = ctrl;
        scope.attribute.filter.type = "DATE_CONDITION";
        
        /* Hook up the jquery datepicker*/
        var checkin = $(element).find('.checkin');
        var checkout = $(element).find('.checkout');
        
        $(element).find('.datapicker-opener').on('click', function(e){
          e.preventDefault();
          
          $(this).prev().datepicker('show');
        });

        var startDate = false;
        var endDate = false;
        
        checkin.datepicker($.datepicker.regional.local);
        checkin.datepicker("option", {
          showOtherMonths: true,
          selectOtherMonths: true,
          onSelect: function(dateText, inst){
            startDate = new Date(dateText);
            
            scope.attribute.filter.startDate = dateText;
            scope.$apply();
          },
          onClose: function( selectedDate ) {
            checkout.datepicker( "option", "minDate", selectedDate );
          },
          beforeShowDay: function(date){
            return [true, startDate && ((date.getTime() == startDate.getTime()) || (endDate && date >= startDate && date <= endDate)) ? "dp-highlight" : ""];
          }
        });
        
        checkout.datepicker($.datepicker.regional.local);
        checkout.datepicker("option", {
          showOtherMonths: true,
          selectOtherMonths: true,
          onSelect: function(dateText, inst){
            endDate = new Date(dateText);
            
            scope.attribute.filter.endDate = dateText;
            scope.$apply();
          },
          onClose: function( selectedDate ) {
            checkin.datepicker( "option", "maxDate", selectedDate );
          },
          beforeShowDay: function(date){
            return [true, startDate && ((date.getTime() == startDate.getTime()) || (endDate && date >= startDate && date <= endDate)) ? "dp-highlight" : ""];
          }
        });            
      }
    }    
  }
  
  function CharacterTypeController($scope, $timeout, dashboardService) {
    var controller = this;

    controller.init = function(element) {
    }
    
    controller.getTextSuggestions = function(request, response ) {
        
      var limit = 10;
              
      var onSuccess = function(results){
              
        response( results );
      };
            
      var onFailure = function(e){
        console.log(e);
      };
         
      var term = request.term;
      var mdAttributeId = $scope.attribute.mdAttributeId;
      
      dashboardService.getTextSuggestions(mdAttributeId, term, 10, onSuccess, onFailure);
    }
  
  }
  
  function CharacterType() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/character-type.jsp',      
      require: ['^form', 'characterType'],      
      scope: {
        attribute:'='
      },
      controller : CharacterTypeController,
      controllerAs : 'ctrl',
      link: function (scope, element, attrs, ctrls) {
        scope.form = ctrls[0];
        
        ctrls[1].init(element);        
      }
    }    
  }
  
  function OntologyTypeController($scope, dashboardService) {
      var controller = this;

      controller.init = function(element) {
        if($scope.attribute.filter.value === undefined) {
          $scope.attribute.filter.value = [];
        }
      }
      
      controller.source = function(request, response ) {
          
        var limit = 10;
                
        var onSuccess = function(results){
          var terms = JSON.parse(results);
                              
          response( terms );
        };
              
        var onFailure = function(e){
          console.log(e);
        };
           
        var term = request.term;
        var mdAttributeId = $scope.attribute.mdAttributeId;
        
        dashboardService.getTermSuggestions(mdAttributeId, term, 10, onSuccess, onFailure);
      }
      
      controller.remove = function($index) {
        $scope.attribute.filter.value.splice($index, 1);
      }
        
      controller.add = function(item) {
        if(!controller.contains(item.id)) {
          $scope.attribute.filter.value.push({
            label : item.label,
            id : item.id
          });
        }
      }
            
      controller.contains = function(id) {
        for(var i = 0; i < $scope.attribute.filter.value.length; i++) {
          if($scope.attribute.filter.value[i].id == id) {
            return true;
          }
        }
        
        return false;
      }
      
      controller.init();
  }
  
  function OntologyType($timeout) {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/ontology-type.jsp',
      controller : OntologyTypeController,
      controllerAs : 'ctrl',
      scope: {
        attribute:'='
      },
      require: ['^form', 'ontologyType'],      
      link: function (scope, element, attrs, ctrls) {
        var ctrl = ctrls[1];
        
        scope.form = ctrls[0];
        scope.attribute.filter.type = "CLASSIFIER_CONDITION";
        
        var input = $(element).find(".filter-term");
        
        input.autocomplete({
          source: ctrl.source,
          select: function(event, ui) {
        	ctrl.add(ui.item);
            
            scope.$apply();
            $(this).val(''); 
            
            return false;
          }, 
          minLength: 2
        });                
      }
    }    
  }
  
  function BooleanType() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/dashboard/boolean-type.jsp',
      require: '^form',
      scope: {
        attribute:'='
      },
      link: function (scope, element, attrs, ctrl) {
        scope.form = ctrl;        
      }
    }    
  }
  
  
  angular.module("dashboard-accordion", ["dashboard-service", "widget-service", "styled-inputs"]);
  angular.module('dashboard-accordion')
  .directive('locationFilter', LocationFilter)
  .directive('typeAccordion', TypeAccordion)
  .directive('attributePanel', AttributePanel)
  .directive('accordionAttribute', AccordionAttribute)
  .directive('numberType', NumberType)
  .directive('dateType', DateType)
  .directive('characterType', CharacterType)
  .directive('ontologyType', OntologyType)
  .directive('booleanType', BooleanType);
})();