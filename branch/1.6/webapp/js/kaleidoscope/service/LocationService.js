(function(){

  function LocationService(runwayService) {
    var service = {};
    
    service.select = function(connection, id, universalId, existingLayers) {
      var req = {
        method: 'POST',
        url: com.runwaysdk.__applicationContextPath + '/location/select',
        data : {
          id : id,
          universalId : universalId,
          existingLayers : existingLayers
        }
      }      
      
      runwayService.execute(req, connection);      
    }
    
    service.open = function(connection, id, existingLayers) {
      var req = {
        method: 'POST',
        url: com.runwaysdk.__applicationContextPath + '/location/open',
        data : {
          id : id,
          existingLayers : existingLayers            
        }
      }      
      
      runwayService.execute(req, connection);      
    }
    
    service.getGeoEntitySuggestions = function(connection, text, limit) {
      var req = {
        method: 'POST',
        url: com.runwaysdk.__applicationContextPath + '/location/suggestions',
        data : {
          text : text,
          limit : limit
        }
      }      
              
      runwayService.execute(req, connection);      
    }
    
    service.apply = function(connection, entity, parentId, existingLayers) {
      var req = {
        method: 'POST',
        url: com.runwaysdk.__applicationContextPath + '/location/apply',
        data : {
          entity : entity,
          parentId : parentId,          
          existingLayers : existingLayers
        }
      }      
      
      runwayService.execute(req, connection);      
    }
      
    service.edit = function(connection, entityId) {
      var req = {
        method: 'POST',
        url: com.runwaysdk.__applicationContextPath + '/location/edit',
        data : {
          entityId : entityId
        }
      }      
      
      runwayService.execute(req, connection);      
    }
    
    service.unlock = function(connection, entityId) {
      var req = {
        method: 'POST',
        url: com.runwaysdk.__applicationContextPath + '/location/unlock',
        data : {
          entityId : entityId
        }
      }      
      
      runwayService.execute(req, connection);      
    }
    
    service.remove = function(connection, entityId, existingLayers) {
      var req = {
          method: 'POST',
          url: com.runwaysdk.__applicationContextPath + '/location/remove',
          data : {
            entityId : entityId,
            existingLayers : existingLayers            
          }
      }      
      
      runwayService.execute(req, connection);      
    }
    
    return service;  
  }
  
  angular.module("location-service", ["runway-service"]);
  angular.module("location-service")
    .factory('locationService', LocationService)
})();
