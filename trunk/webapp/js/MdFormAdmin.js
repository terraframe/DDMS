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
  Constants : {
    AVAILABLE_FIELDS : 'availableFields'
  },
  Instance : {
    initialize : function(adminPanelId, formList)
    {
      UI.Manager.setFactory("YUI3");
      this._Factory = UI.Manager.getFactory();
    
      this._MdFormAdminController = dss.vector.solutions.form.MdFormAdminController;
    },
    render : function()
    {
      // attach the event handlers to the DOM elements
      YAHOO.util.Event.on(this.constructor.AVAILABLE_FIELDS, 'click', this.availableFields, null, this);
    },
    /**
     * Makes a request to display all available MdField types for the Form Generator.
     */
    availableFields : function()
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html){
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          
          var dialog = that._Factory.newDialog('foo');
          dialog.setInnerHTML(pureHTML);
          dialog.render();
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