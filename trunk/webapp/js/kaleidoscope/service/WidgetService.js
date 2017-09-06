(function(){
  
  function WidgetService() {
    var service = {};    
    
    service.animate = function(element, properties, duration, complete) {
      $(element).animate(properties, duration, complete);              
    }
    
    service.sortable = function(element, update) {
      element.sortable({
        update: update
      });
    }
    
    service.createDialog = function(title, content, buttons) {
      MDSS.confirmModal(content, buttons[0].callback, buttons[1].callback);    	
    }
      
    return service;
  }
  
  angular.module("widget-service", []);
  angular.module("widget-service")
    .factory('widgetService', WidgetService)
})();