(function(){
  
  function LocalizationService() {
    var service = {};    
    service.parser = null; // Globalize.numberParser();
    service.formatter = null; // Globalize.numberFormatter();
    
    service.parseNumber = function(value) {
      if(value != null && value.length > 0) {            
        //convert data from view format to model format
        var number = parseFloat(value); //service.parser( value );
        
        return number;
      }
          
      return null;
    }
    
    service.formatNumber = function(value) {
      if(value != null) {
        var number = value;
            
        if(typeof number === 'string') {
          if(number.length > 0 && Number(number)) {
            number = Number(value);        	  
          }
          else {
            return "";
          }
        }
            
        //convert data from model format to view format
        return MDSS.formatNumber(number);
      }
            
      return null;
    }
    
    service.localize = function(bundle, key) {
      return com.runwaysdk.Localize.localize(bundle, key);
    }
    
    service.get = function(key) {
      return com.runwaysdk.Localize.get(key);
    }
    
    service.addCalendar = function(element, callback) {
      MDSS.Calendar.addCalendar(element, callback);
    }
    
    service.destroyCalendar = function(element) {
      MDSS.Calendar.destroyCalendar(element);
    }    

    return service;
  }
  
  angular.module("localization-service", []);
  angular.module("localization-service")
    .factory('localizationService', LocalizationService)
})();