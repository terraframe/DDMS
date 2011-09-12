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
  Constants : {
    AVAILABLE_FIELDS : 'availableFields'
  },
  Instance : {
    initialize : function(adminPanelId, formList)
    {
      this._MdFormAdminController = dss.vector.solutions.form.MdFormAdminController;
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
      // attach the event handlers to the DOM elements
      YAHOO.util.Event.on(this.constructor.AVAILABLE_FIELDS, 'click', this.availableFields, null, this);
      this._adminLayout.render();
    },
    /**
     * Makes a request to display all available MdField types for the Form Generator.
     */
    availableFields : function()
    {
      var request = new MDSS.Request({
        onSuccess : function(html){
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          
          // insert HTML into a popup modal
        }
      });
      
      this._MdFormAdminController.availableFields(request);
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