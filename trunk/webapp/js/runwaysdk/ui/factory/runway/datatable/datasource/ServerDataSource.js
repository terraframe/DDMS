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
define(["../../../../../ClassFramework", "../../../../../Util", "../../../generic/datatable/datasource/DataSourceIF", "../../runway"], function(ClassFramework, Util, DataSourceIF) {
  
  var RW = ClassFramework.alias(Mojo.RW_PACKAGE + "*");
  var UI = ClassFramework.alias(Mojo.UI_PACKAGE + "*");
  
  var serverDataSource = ClassFramework.newClass(Mojo.RW_PACKAGE+'datatable.datasource.ServerDataSource', {
    
    Implements : [DataSourceIF],
    
    Instance : {
      initialize : function(config) {
        
        this._config = config;
        
        throw new com.runwaysdk.Exception("Not implemented yet.");
      },
      
      getConfig : function() {
        throw new com.runwaysdk.Exception("Not implemented.");
      },
      
      getData : function(callback) {
        throw new com.runwaysdk.Exception("Not implemented yet.");
      },
      
      getColumns : function(callback) {
        throw new com.runwaysdk.Exception("Not implemented yet.");
      },
      
      setColumns : function(cols) {
        this._columns = cols;
      }
    }
  });
  
  return serverDataSource;
});