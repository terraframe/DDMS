/*
 * Manages control flow for the MdForm admin page.
 */
(function(){

var UI = Mojo.Meta.alias("com.runwaysdk.ui.*");
UI.Manager.setFactory("YUI3");
var FACTORY = RUNWAY_UI.Manager.getFactory();

/**
 * Primary class to handle control flow in the UI.
 */
Mojo.Meta.newClass('dss.vector.solutions.MdFormAdmin', 
{
  Instance : {
    initialize : function(adminPanelId, formList)
    {
			this._parentDiv = adminPanelId;
      this._adminLayout = FACTORY.newLayout("horizontal");
			this._formList = FACTORY.newList("Form List");
			for (var i = 0; i < formList.length; i++)
			{
				this._formList.addItem(formList[i]);
			}
			this._adminLayout.appendChild(this._formList);
    },
    render : function()
    {
      this._adminLayout.render();
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