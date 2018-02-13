/*
 * Copyright (C) 2008 IVCC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
//define(["../../../../ClassFramework", "../../../../Util", "../../../RunwaySDK_UI"], function(ClassFramework, Util, UI) {
(function(){  

  var ClassFramework = Mojo.Meta;
  var Util = Mojo.Util;
  
  var pack = "com.runwaysdk.ui.factory.generic.datatable.events."; 
  
  var rowAddEvent = ClassFramework.newClass(pack+'NewRowEvent', {
    
    Extends : com.runwaysdk.event.CustomEvent,
    
    Instance : {
      
      initialize : function(row) {
        this.$initialize();
        
        this._row = row;
      },
      
      getRow : function() {
        return this._row;
      }
      
    }
    
  });
  
  var headerRowAddEvent = ClassFramework.newClass(pack+"NewHeaderRowEvent", {
    
    Extends: rowAddEvent,
    
    Instance : {
      
      initialize : function(row) {
        this.$initialize(row)
      }
      
    }
  });
  
  return Mojo.Meta.alias(pack+"*");
  
})();
