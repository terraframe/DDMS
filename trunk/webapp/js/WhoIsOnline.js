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
(function(){

  var ClassFramework = Mojo.Meta;
  var Util = Mojo.Util;
  var Widget = com.runwaysdk.ui.factory.runway.Widget;
  var JSONDataSource = com.runwaysdk.ui.datatable.datasource.JSONDataSource;
  
  // In miliseconds
  var POLLING_INTERVAL = 3500;

  var widgetName = 'dss.vector.solutions.WhoIsOnline';
  
  /**
   * LANGUAGE
   */
  com.runwaysdk.Localize.defineLanguage(widgetName, {
    "username" : "Username",
    "locale" : "Locale"
  });
  
  var WhoIsOnline = ClassFramework.newClass(widgetName, {
    
    Extends : Widget,
    
    Instance : {
      
      initialize : function(config) {
        this.$initialize("div");
        
        this._config = config || {};
      },
      
      render : function(parent) {
        
        var ds = new com.runwaysdk.ui.datatable.datasource.MdMethodDataSource({
          method : function(clientRequest) {
            dss.vector.solutions.WhoIsOnlineView.getViews(clientRequest);
          },
          columns : [
                     { header: this.localize("username"), queryAttr: "username" },
                     { header: this.localize("locale"), queryAttr: "locale" }
                    ]
        });
        this._config.dataSource = ds;
        
        // Create the element that will contain the DataTable
        var tableEl = this.getFactory().newElement("table");
        this.appendChild(tableEl);
        this._config.el = tableEl;
        
        // Create the DataTable impl
        this._config.sDom = '<"top"i>rt<"bottom"lp><"clear">';  // TODO : This statement hides a (datatables.net) search bar that isn't hooked up yet.
        this._table = this.getFactory().newDataTable(this._config);
        
        this._table.render(parent);
        
        var that = this;
        this._pollingRequest = new com.runwaysdk.ui.PollingRequest({
          callback: {
            onSuccess: function(data) {
              
            },
            onFailure: function(ex) {
              
            }
          },
          performRequest : function(callback) {
            that._table.refresh(callback);
          },
          pollingInterval : POLLING_INTERVAL
        });
        
        this._pollingRequest.enable();
      },
      
      getPollingRequest : function() {
        return this._pollingRequest;
      },
      
      destroy : function() {
        this.$destroy();
        this._pollingRequest.destroy();
      }
    }
    
  });
  
  return WhoIsOnline;
  
})();
