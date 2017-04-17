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
