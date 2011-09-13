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
    AVAILABLE_FIELDS : 'availableFields',
		VIEW_FORM : 'viewForm',
		EXISTING_FORMS : 'existingForms'
  },
  Instance : {
    initialize : function()
    {
      UI.Manager.setFactory("YUI3");
      this._Factory = UI.Manager.getFactory();
    
      this._MdFormAdminController = dss.vector.solutions.form.MdFormAdminController;
    },
    render : function()
    {
      // attach the event handlers to the DOM elements
      YAHOO.util.Event.on(this.constructor.AVAILABLE_FIELDS, 'click', this.availableFields, null, this);
			YAHOO.util.Event.onAvailable(this.constructor.EXISTING_FORMS, this.existingForms, null, this);
			YAHOO.util.Event.delegate(this.constructor.EXISTING_FORMS, 'click', this.viewForm, "li");
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
    },
		/**
		 * Makes a request to display all existing forms on the sidebar
		 */
		existingForms : function()
		{
      var request = new MDSS.Request({
        onSuccess : function(html){
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          document.getElementById("existingForms").innerHTML = pureHTML;
        },
        onFailure : function(html){
          alert("Fetching existing forms failed");
        }
      });
      
      this._MdFormAdminController.existingForms(request);
		},
		viewForm : function(e, matchedEl, container)
		{
			var request = new MDSS.Request({
	      onSuccess : function(html){
					var executable = MDSS.util.extractScripts(html);
	        var pureHTML = MDSS.util.removeScripts(html);
					alert(pureHTML);
	      },
	      onFailure : function(html){
	        alert("Fetching existing forms failed");
	      }
      });
      
      this._MdFormAdminController.view(request, matchedEl.id);
		}
		
  }
});

})();