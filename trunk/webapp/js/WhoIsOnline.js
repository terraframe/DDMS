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
    "inactive" : "Inactive",
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
        
        var ds = new JSONDataSource({
          method : function(clientRequest) {
            Util.invokeControllerAction("dss.vector.solutions.WhoIsOnline", "getUpdateJSON", [], clientRequest);
          },
          columns: [
            { header: this.localize("username") },
            { header: this.localize("inactive") },
            { header: this.localize("locale") }
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
