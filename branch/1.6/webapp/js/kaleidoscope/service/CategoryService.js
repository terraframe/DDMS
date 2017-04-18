(function(){

  function CategoryService(runwayService) {
    var service = {};
    
    service.getAll = function(connection) {
      var request = runwayService.createConnectionRequest(connection);
    
      dss.vector.solutions.kaleidoscope.ontology.ClassifierController.getAllCategories(request);
    }
    
    service.edit = function(connection, id) {
      var request = runwayService.createConnectionRequest(connection);
        
      dss.vector.solutions.kaleidoscope.ontology.ClassifierController.editOption(request, id);
    }
    
    service.unlock = function(connection, id) {
      var request = runwayService.createConnectionRequest(connection);
      
      dss.vector.solutions.kaleidoscope.ontology.ClassifierController.unlockCategory(request, id);
    }
    
    service.get = function(connection, id) {
      var request = runwayService.createConnectionRequest(connection);
          
      dss.vector.solutions.kaleidoscope.ontology.ClassifierController.getCategory(request, id);
    }
    
    service.createOption = function(connection, option) {
      var request = runwayService.createConnectionRequest(connection);
      
      dss.vector.solutions.kaleidoscope.ontology.ClassifierController.createOption(request, option);
    }
    
    service.deleteOption = function(connection, id) {
      var request = runwayService.createConnectionRequest(connection);
      
      dss.vector.solutions.kaleidoscope.ontology.ClassifierController.deleteOption(request, id);
    }
    
    service.applyOption = function(connection, config) {
      var request = runwayService.createConnectionRequest(connection);
      
      dss.vector.solutions.kaleidoscope.ontology.ClassifierController.applyOption(request, config);
    }
    
    service.updateCategory = function(connection, category) {
      var request = runwayService.createConnectionRequest(connection);
      
      dss.vector.solutions.kaleidoscope.ontology.ClassifierController.updateCategory(request, category);
    }
    
    return service;  
  }
  
  angular.module("category-service", ["runway-service"]);
  angular.module("category-service")
    .factory('categoryService', CategoryService)
})();
