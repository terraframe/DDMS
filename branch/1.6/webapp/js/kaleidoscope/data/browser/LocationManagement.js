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
  function LocationController($scope, $rootScope, locationService, localizationService, widgetService) {
    var controller = this;
    
    controller.init = function() {
      var connection = {
        elementId : '#innerFrameHtml',
        onSuccess : function(data) {
          $scope.previous.push(data.entity);
          
          controller.load(data);
        }      
      };      
      
      $scope.children = [];
      $scope.previous = [];
      
      locationService.select(connection, "", "", "");
    }
    
    controller.load = function(data) {
      $scope.children = data.children.resultSet;
      $scope.layers = data.layers;
      
      $scope.entity = data.entity;
      $scope.universal = {
        value : data.universal,
        options : data.universals
      };
      
      $scope.$broadcast('sharedGeoData', data);
    }
    
    controller.select = function(entity, event) {
      if(!$(event.target).hasClass('inner-action')) {
        var connection = {
          elementId : '#innerFrameHtml',      
          onSuccess : function(data) {
            $scope.previous.push(entity);          
              
            controller.load(data);
          }
        };    
        
        locationService.select(connection, entity.id, "", $scope.layers);        
      }
    }
    
    controller.back = function(index) {
      if(index !== ($scope.previous.length - 1)) {
        var connection = {
          elementId : '#innerFrameHtml',            
          onSuccess : function(data) {            
            $scope.previous.splice(index + 1);
            
            controller.load(data);
          }
        };    
                
        var id = $scope.previous[index].id;
                
        locationService.select(connection, id, "", $scope.layers);        
      }
    }
    
    controller.setUniversal = function() {
      var connection = {
        elementId : '#innerFrameHtml',
        onSuccess : function(data) {          
          $scope.children = data.children.resultSet;
          $scope.layers = data.layers;
            
          $scope.$broadcast('sharedGeoData', data);          
        }
      };
      
      console.log(JSON.stringify($scope.universal));

      locationService.select(connection, $scope.entity.id, $scope.universal.value, $scope.layers);      
    }
    
    controller.getGeoEntitySuggestions = function( request, response ) {
      var limit = 20;
      
      if(request.term && request.term.length > 0) {
    	  
        var connection = {
          onSuccess : function(data){
            var resultSet = data.resultSet;
            
            var results = [];
            
            $.each(resultSet, function( index, result ) {
              var label = result.displayLabel;
              var id = result.id;
              
              results.push({'label':label, 'value':label, 'id':id});
            });
            
            response( results );
          }
        };
      
        var text = request.term;
        
        locationService.getGeoEntitySuggestions(connection, text, limit);
      }
    }
    
    controller.open = function(entityId) {
    	
      if(entityId && entityId.length > 0) {
    	  
        var connection = {
          elementId : '#innerFrameHtml',
          onSuccess : function(data) {
            $scope.previous = data.ancestors;
                  
            controller.load(data);
          }      
        };      
               
        $scope.children = [];
        $scope.previous = [];
        locationService.open(connection, entityId, $scope.layers);
      }
    }
    
    controller.edit = function(entity) {
      var connection = {
        elementId : '#innerFrameHtml',
        onSuccess : function(entity) {
          $scope.$emit('locationEdit', {
            universal : $scope.universal,
            parent : $scope.entity,
            entity : entity
          });
        }      
      };      
      
      locationService.edit(connection, entity.id);
    }
    
    controller.remove = function(entity) {
      var title = localizationService.localize("location.management", "removeOptionTitle", "Delete location");

      var message = localizationService.localize("location.management", "removeConfirm", "Are you sure you want to delete the location [{0}]?");
      message = message.replace('{0}', entity.displayLabel);
              
      var buttons = [];
      buttons.push({
        label : localizationService.localize("layer.category", "ok", "Ok"),
        config : {class:'btn btn-primary'},
        callback : function(){
          controller.performRemove(entity);
        }
      });
      buttons.push({
        label : localizationService.localize("dataset", "cancel", "Cancel"),
        config : {class:'btn'}
      });
              
      widgetService.createDialog(title, message, buttons);    
    }
    
    
    controller.performRemove = function(entity) {
      var connection = {
        elementId : '#innerFrameHtml',
        onSuccess : function(response) {
          var index = controller.findIndex(entity.id);
          
          if(index != -1){
            $scope.children.splice(index, 1);
          }          
          
          $scope.$broadcast('sharedGeoData', {});          
        }
      };
      
      locationService.remove(connection, entity.id, $scope.layers);
    }    
    
    controller.newInstance = function() {
      $scope.$emit('locationEdit', {
        wkt : '',
        universal : $scope.universal,
        parent : $scope.entity
      });
    }
    
    controller.findIndex = function(entityId) {
      for(var i = 0; i < $scope.children.length; i++) {
        if($scope.children[i].id == entityId) {
          return i;
        };
      }
      
      return -1;
    }
    
    
    controller.listItemHover = function(entity, event){
    	$scope.$broadcast('listHoverOver', entity);
    }
    
    controller.listItemHoverOff = function(entity, event){
    	$scope.$broadcast('listHoverOff', entity);
    }
    
    
    $scope.$on('hoverChange', function(event, data){
      $scope.hoverId = data.id;
    });
    
    $rootScope.$on('locationChange', function(event, data) {
      var id = (data.entity.oid !== undefined) ? data.entity.oid : data.entity.id;
      
      var index = controller.findIndex(id);
      
      if(index !== -1) {
        $scope.children[index] = data.entity;
      }
      else {
        $scope.children.push(data.entity);
      }
    });    

    $rootScope.$on('locationLock', function(event, data) {
      if(data.entityId != null) {
        var connection = {
          elementId : '#innerFrameHtml',
          onSuccess : function(entity) {
            entity.wkt = data.wkt;
            
            $scope.parent = $scope.entity;
            $scope.entity = entity;
            
            controller.apply();
          }      
        };      
        
        locationService.edit(connection, data.entityId);        
      }
    });    
    
    controller.apply = function() {
        var connection = {
          elementId : '#innerFrameHtml',
          onSuccess : function(entity) {
            
            $scope.$emit('locationChange', {
              entity : entity  
            });
          },
          onFailure : function(e){
            $scope.errors.push(e.localizedMessage);
          }                
        };
                                
        $scope.errors = [];
            
        locationService.apply(connection, $scope.entity, $scope.parent.id, $scope.layers);        
    }
    
    controller.init();
  }
  
  function LocationModalController($scope, $rootScope, locationService) {
    var controller = this;
        
    controller.init = function() {
      $scope.show = false;
    }
        
    controller.load = function(data) {
      if(data.entity == null) {
        $scope.entity = {
          type : 'com.runwaysdk.system.gis.geo.GeoEntity',
          wkt : data.wkt,
          universal : data.universal.value
        };        
      }
      else {
        $scope.entity = data.entity;        
      }
      
      $scope.universals = data.universal.options;
      $scope.parent = data.parent;
      $scope.show = true;
    }
        
    controller.clear = function() { 
      $scope.entity = undefined;
      $scope.parent = undefined;
      $scope.show = false;
    }
    
    controller.cancel = function() {
      if($scope.entity.id !== undefined) {
        var connection = {
          elementId : '#innerFrameHtml',
          onSuccess : function(entity) {
            controller.clear();
            
            $scope.$emit('locationCancel', {});
          },
          onFailure : function(e){
            $scope.errors.push(e.localizedMessage);
          }                
        };
                                        
        $scope.errors = [];
                    
        locationService.unlock(connection, $scope.entity.id);                      
      }
      else {
        controller.clear();
        
        $scope.$emit('locationCancel', {});        
      }
    }
    
    controller.apply = function() {
      var connection = {
        elementId : '#innerFrameHtml',
        onSuccess : function(entity) {
          controller.clear();
          
          $scope.$emit('locationChange', {
            entity : entity  
          });
        },
        onFailure : function(e){
          $scope.errors.push(e.localizedMessage);
        }                
      };
                              
      $scope.errors = [];
          
      locationService.apply(connection, $scope.entity, $scope.parent.id, $scope.layers);        
    }
      
    $rootScope.$on('locationEdit', function(event, data) {
      controller.load(data);
    });      
       
    controller.init();
  }
    
  function LocationModal() {
    return {
      restrict: 'E',
      replace: true,
      templateUrl: com.runwaysdk.__applicationContextPath + '/partial/data/browser/location-modal.jsp',
      scope: {
        layers : '='
      },
      controller : LocationModalController,
      controllerAs : 'ctrl',      
      link: function (scope, element, attrs, ctrl) {
      }
    }   
  }  
  
  angular.module("location-management", ["location-service", "styled-inputs", "editable-map", "widget-service", "localization-service"]);
  angular.module("location-management")
   .controller('LocationController', LocationController)
   .directive('locationModal', LocationModal)
})();
