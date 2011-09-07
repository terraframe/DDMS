/*
 * Manages control flow for the MdForm admin page.
 */
(function(){

var UI = Mojo.Meta.alias("com.runwaysdk.ui.*");

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
    },
    /**
     * Make a request for a new instance of an MdField.
     */
    newField : function(mdFieldType)
    {
    }
  }
});

})();