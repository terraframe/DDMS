#-------------------------------------------------------------------------------
# Copyright (C) 2018 IVCC
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#-------------------------------------------------------------------------------
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
