/*
 * Manages control flow for the MdForm admin page.
 */
(function(){

var UI = Mojo.Meta.alias("com.runwaysdk.ui.*");
var FACTORY = UI.Manager.setFactory("YUI3");

/**
 * Primary class to handle control flow in the UI.
 */
Mojo.Meta.newClass('dss.vector.solutions.MdFormAdmin', 
{
  Instance : {
    initialize : function(adminPanelId)
    {
      this._adminLayout = FACTORY.newLayout("horizontal");
    },
    render : function()
    {
    
    },
    availableFields : function()
    {
    }
  }
});

})();