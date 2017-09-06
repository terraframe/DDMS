(function(){

  function CategoryIconService(runwayService) {
    var service = {};
    
    service.create = function(connection, file, label) {
      
      var data = new FormData();
      data.append('file', file);
      data.append('label', label)

      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/iconimage/create',
        headers: {
          'Content-Type': undefined
        },
        data: data,
        transformRequest: angular.identity        
      }
      
      runwayService.execute(req, connection);      
    }
    
    service.apply = function(connection, id, file, label) {
      
      var data = new FormData();
      data.append('id', id);
      data.append('label', label)
      
      if(file.fileReference !== "NONE"){
    	  data.append('file', file);
      }

      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/iconimage/apply',
        headers: {
          'Content-Type': undefined
        },
        data: data,
        transformRequest: angular.identity        
      }      
      
      runwayService.execute(req, connection);
    }    
    
    service.getAll = function(connection) {
      var req = {
        method: 'GET',
        url: window.com.runwaysdk.__applicationContextPath + '/iconimage/getAll'
      }      
      
      runwayService.execute(req, connection);      
    }
    
    service.remove = function(connection, id) {
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/iconimage/remove',
        data : {id : id}
      }      
      
      runwayService.execute(req, connection);      
    }
    
    service.edit = function(connection, id) {
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/iconimage/edit',
        data : {id : id}
      }      
            
      runwayService.execute(req, connection);      
    }
    
    service.getImage = function(connection, id) {      
      var req = {
        method: 'GET',
        url: window.com.runwaysdk.__applicationContextPath + '/iconimage/getCategoryIconImage',
        params: {id : id}
      }      
                  
      runwayService.execute(req, connection);      
    }
    
    service.getDefaultIconModel = function() {
      return {enableIcon:false, icon:"", iconSize:50};
    }
    
    return service;  
  }
  
  angular.module("category-icon-service", ["runway-service"]);
  angular.module("category-icon-service")
    .factory('categoryIconService', CategoryIconService)
})();
