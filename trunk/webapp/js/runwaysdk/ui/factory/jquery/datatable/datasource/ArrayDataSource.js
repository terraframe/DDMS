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
//define(["../../../../../ClassFramework", "../../../../../Util", "../../../generic/datatable/datasource/DataSourceIF"], function(ClassFramework, Util, DataSourceIF) {
(function(){  

  var ClassFramework = Mojo.Meta;
  var Util = Mojo.Util;
  var DataSourceIF = com.runwaysdk.ui.factory.generic.datatable.datasource.DataSourceIF;
  
  var RW = ClassFramework.alias(Mojo.RW_PACKAGE + "*");
  var UI = ClassFramework.alias(Mojo.UI_PACKAGE + "*");
  
  var arrayDataSource = ClassFramework.newClass(Mojo.JQUERY_PACKAGE+'datatable.datasource.ArrayDataSource', {
    
    Implements : DataSourceIF,
    
    Instance : {
      
      initialize : function(cfg)
      {
        this._columns = cfg.columns;
        this._data = cfg.data;
      },
      
      getConfig : function() {
        var config = {};
        
        config["aoColumns"] = [];
        for (var i = 0; i < this._columns.length; ++i) {
          config.aoColumns.push({"sTitle" : this._columns[i]});
        }
        
        config.aaData = this._data;
        
        return config;
      },
      
      getColumns : function(callback) {
        callback(this._columns);
      },
      
      setColumns : function(cols) {
        this._columns = cols;
      },
      
      getData : function(callback) {
        callback(this._data);
      }
      
    }
  });
  
  return arrayDataSource;
  
})();
