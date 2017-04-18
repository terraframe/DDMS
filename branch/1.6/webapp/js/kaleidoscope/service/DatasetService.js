(function(){

  function DatasetService(runwayService) {
    var service = {};
    var _datasetConfiguration = {};
    
    service.setDatasetConfiguration = function(config) {
      this._datasetConfiguration = config;
    }
      
    service.getDatasetConfiguration = function() {
      return this._datasetConfiguration;
    }
    
    service.uploadSpreadsheet = function(connection, file) {
      
      var data = new FormData();
      data.append('file', file);
      
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/getAttributeInformation',
        headers: {
          'Content-Type': undefined
        },
        data: data,
        transformRequest: angular.identity        
      }
            
      runwayService.execute(req, connection);            
    }
    
    service.importData = function(connection, configuration) {
//      var request = runwayService.createConnectionRequest(connection);
//      dss.vector.solutions.kaleidoscope.DataUploaderController.importData(request, JSON.stringify(configuration));
      
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/importData',
        data : {configuration : configuration }
      }
      
      runwayService.execute(req, connection);
    }
    
    service.cancelImport = function(connection, configuration) {
//      var request = runwayService.createConnectionRequest(connection);          
//      dss.vector.solutions.kaleidoscope.DataUploaderController.cancelImport(request, JSON.stringify(configuration));
    
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/cancelImport',
        data : {configuration : configuration }
      }
              
      runwayService.execute(req, connection);    
    }
    
    service.getSavedConfiguration = function(connection, id, sheetName) {
//      var request = runwayService.createConnectionRequest(connection);            
//      dss.vector.solutions.kaleidoscope.DataUploaderController.getSavedConfiguration(request, id, sheetName);
            
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/getSavedConfiguration',
        data : {
          id : id,
          sheetName : sheetName
        }
      }
              
      runwayService.execute(req, connection);      
    }
    
    service.createGeoEntitySynonym = function(connection, entityId, label) {
//      var request = runwayService.createConnectionRequest(connection);        
//      dss.vector.solutions.kaleidoscope.DataUploaderController.createGeoEntitySynonym(request, entityId, label);
      
        
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/createGeoEntitySynonym',
        data : {
          entityId : entityId,
          label : label
        }
      }
                
      runwayService.execute(req, connection);            
    }
    
    service.createClassifierSynonym = function(connection, classifierId, label) {
//      var request = runwayService.createConnectionRequest(connection);      
//      dss.vector.solutions.kaleidoscope.DataUploaderController.createClassifierSynonym(request, classifierId, label);
    
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/createClassifierSynonym',
        data : {
          classifierId : classifierId,
          label : label
        }
      }
                        
      runwayService.execute(req, connection);    
    }
    
    service.createGeoEntity = function(connection, parentId, universalId, label) {
//      var request = runwayService.createConnectionRequest(connection);      
//      dss.vector.solutions.kaleidoscope.DataUploaderController.createGeoEntity(request, parentId, universalId, label);
      
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/createGeoEntity',
        data : {
          parentId : parentId,
          universalId : universalId,
          label : label
        }
      }
                            
      runwayService.execute(req, connection);          
    }    

    service.deleteGeoEntity = function(connection, entityId) {
//      var request = runwayService.createConnectionRequest(connection);      
//      dss.vector.solutions.kaleidoscope.DataUploaderController.deleteGeoEntity(request, entityId);
    
        
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/deleteGeoEntity',
        data : {
          entityId : entityId
        }
      }
                              
      runwayService.execute(req, connection);
    }    
    
    service.deleteGeoEntitySynonym = function(connection, synonymId) {
//      var request = runwayService.createConnectionRequest(connection);      
//      dss.vector.solutions.kaleidoscope.DataUploaderController.deleteGeoEntitySynonym(request, synonymId);
    
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/deleteGeoEntitySynonym',
        data : {
          synonymId : synonymId
        }
      }
      
      runwayService.execute(req, connection);
    }
    
    service.deleteClassifierSynonym = function(connection, synonymId) {
//      var request = runwayService.createConnectionRequest(connection);      
//      dss.vector.solutions.kaleidoscope.DataUploaderController.deleteClassifierSynonym(request, synonymId);    
    
      var req = {
        method: 'POST',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/deleteClassifierSynonym',
        data : {
          synonymId : synonymId
        }
      }
      
      runwayService.execute(req, connection);
    }    
    
    service.getClassifierSuggestions = function(connection, mdAttributeId, text, limit) {
//      var request = runwayService.createConnectionRequest(connection);        
//      dss.vector.solutions.kaleidoscope.DataUploaderController.getClassifierSuggestions(request, mdAttributeId, text, limit);
        
      var req = {
        method: 'GET',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/getClassifierSuggestions',
        params : {
          mdAttributeId : mdAttributeId,
          text : text,
          limit : limit          
        }
      }
      
      runwayService.execute(req, connection);      
    }
      
    service.validateDatasetName = function(connection, name, id) {
//      var request = runwayService.createConnectionRequest(connection);          
//      dss.vector.solutions.kaleidoscope.DataUploaderController.validateDatasetName(request, label, id);      
      
      var req = {
        method: 'GET',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/validateDatasetName',
        params : {
          name : name,
          id : id          
        }
      }
              
      runwayService.execute(req, connection);
    }
      
    service.validateCategoryName = function(connection, name, id) {
//      var request = runwayService.createConnectionRequest(connection);      
//      dss.vector.solutions.kaleidoscope.DataUploaderController.validateCategoryName(request, label, id);
      
      var req = {
        method: 'GET',
        url: window.com.runwaysdk.__applicationContextPath + '/uploader/validateCategoryName',
        params : {
          name : name,
          id : id          
        }
      }
                      
      runwayService.execute(req, connection);      
    }
    
    service.getAll = function(connection) {
      var request = runwayService.createConnectionRequest(connection);
    
      dss.vector.solutions.kaleidoscope.DataSetController.getAll(request);
    }
    
    service.remove = function(connection, id) {
      var request = runwayService.createConnectionRequest(connection);
      
      dss.vector.solutions.kaleidoscope.DataSetController.remove(request, id);
    }
    
    service.edit = function(connection, id) {
      var request = runwayService.createConnectionRequest(connection);
    
      dss.vector.solutions.kaleidoscope.DataSetController.edit(request, id);
    }
    
    service.cancel = function(connection, id) {
      var request = runwayService.createConnectionRequest(connection);
      
      dss.vector.solutions.kaleidoscope.DataSetController.cancel(request, id);
    }
    
    service.applyDatasetUpdate = function(connection, dataset) {
      var request = runwayService.createConnectionRequest(connection);
    
      dss.vector.solutions.kaleidoscope.DataSetController.applyDatasetUpdate(request, JSON.stringify(dataset));
    }
    
    service.apply = function(connection, dataset) {
      var request = runwayService.createConnectionRequest(connection);
      
      dss.vector.solutions.kaleidoscope.DataSetController.apply(request, JSON.stringify(dataset));
    }
    
    service.getGeoEntitySuggestions = function(connection, parentId, universalId, text, limit) {
      var request = runwayService.createConnectionRequest(connection);
    
      dss.vector.solutions.kaleidoscope.ontology.GeoEntityUtil.getGeoEntitySuggestions(request, parentId, universalId, text, limit);
    }
    
    //
    // Used to structure the data uploader steps widget
    // @config - Configuration array containing additional steps. Can be an empty array.
    //
    service.getUploaderSteps = function(config){
      var basicSteps = [
        {"label": "1", "page":"INITIAL"},
        {"label": "2", "page":"FIELDS"},
        {"label": "3", "page":"SUMMARY"}];
      
      var locationStep = {"label": "4", "page":"LOCATION"};
      var coordinateStep = {"label": "5", "page":"COORDINATE"};
      var geoProblemResStep = {"label": "6", "page":"GEO-VALIDATION"}; 
      var categoryProblemResStep = {"label": "7", "page":"CATEGORY-VALIDATION"}; 
      
      if(config.indexOf("LOCATION") > -1 && config.indexOf("COORDINATE") > -1 && config.indexOf("CATEGORY") > -1){
        basicSteps.splice(2, 0, locationStep, coordinateStep);
        basicSteps.splice(5, 0, geoProblemResStep);
        basicSteps.splice(6, 0, categoryProblemResStep);
      }
      else if(config.indexOf("LOCATION") === -1 && config.indexOf("COORDINATE") > -1 && config.indexOf("CATEGORY") > -1){
      basicSteps.splice(2, 0, coordinateStep);
        basicSteps.splice(4, 0, geoProblemResStep);
        basicSteps.splice(5, 0, categoryProblemResStep);
      }
      else if(config.indexOf("LOCATION") > -1 && config.indexOf("COORDINATE") === -1 && config.indexOf("CATEGORY") > -1){
      basicSteps.splice(2, 0, locationStep);
        basicSteps.splice(4, 0, geoProblemResStep);
        basicSteps.splice(5, 0, categoryProblemResStep);
      }
      else if(config.indexOf("LOCATION") > -1 && config.indexOf("COORDINATE") > -1 && config.indexOf("CATEGORY") === -1){
        basicSteps.splice(2, 0, locationStep, coordinateStep);
        basicSteps.splice(5, 0, geoProblemResStep);
      }
      else if(config.indexOf("LOCATION") > -1 ){
        basicSteps.splice(2, 0, locationStep);
        basicSteps.splice(4, 0, geoProblemResStep);
      }
      else if(config.indexOf("COORDINATE") > -1){
        basicSteps.splice(2, 0, coordinateStep);
        basicSteps.splice(4, 0, geoProblemResStep);
      }
      else if(config.indexOf("CATEGORY") > -1){
        basicSteps.splice(3, 0, categoryProblemResStep);
      }

      return basicSteps;
    }

    // List of runway class dependencies which must be loaded from the server
    return service;  
  }
  
  angular.module("dataset-service", ["runway-service"]);
  angular.module("dataset-service")
    .factory('datasetService', DatasetService)
})();
