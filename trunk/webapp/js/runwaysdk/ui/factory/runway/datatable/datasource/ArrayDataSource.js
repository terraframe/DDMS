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
//define(["../../../../../ClassFramework", "../../../../../Util", "../../../generic/datatable/datasource/DataSourceIF", "../../runway"], function(ClassFramework, Util, DataSourceIF) {
(function(){  

  var ClassFramework = Mojo.Meta;
  var Util = Mojo.Util;
  var DataSourceIF = com.runwaysdk.ui.factory.generic.datatable.datasource.DataSourceIF;
  
  var RW = ClassFramework.alias(Mojo.RW_PACKAGE + "*");
  var UI = ClassFramework.alias(Mojo.UI_PACKAGE + "*");
  
  var arrayDataSource = ClassFramework.newClass(Mojo.RW_PACKAGE+'.datatable.datasource.ArrayDataSource', {
    
    Implements : [DataSourceIF],
    
    Instance : {
      
      initialize : function(cfg)
      {
        Util.requireParameter("columns (ArrayDataSource)", cfg.columns);
        Util.requireParameter("data (ArrayDataSource)", cfg.data);
        
        this._config = cfg;
        
        this._columns = cfg.columns;
        this._data = cfg.data;
      },
      
      getConfig : function() {
        throw new com.runwaysdk.Exception("Not implemented.");
      },
      
      getColumns : function(callback) {
        if (callback != null) {
          callback(this._columns);
        }
        else {
          return this._columns;
        }
      },
      
      setColumns : function(cols) {
        this._columns = cols;
      },
      
      getData : function() {
        return this._data;
      }
      
    }
  });
  
  return arrayDataSource;
  
})();
