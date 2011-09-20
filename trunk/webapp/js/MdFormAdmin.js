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
		EXISTING_FORMS : 'existingForms',
		FORM_CONTENT_BOX : 'formContentBox',
		FORM_ACTION_ROW : 'formActionRow',
		CANCEL_BTN : 'cancelBtn',
		UPDATE_BTN : 'updateBtn'
  },
  Instance : {
    initialize : function()
    {
      UI.Manager.setFactory("YUI3");
      this._Factory = UI.Manager.getFactory();
    
			this._MdFormAdminController = dss.vector.solutions.form.MdFormAdminController;
			var cancelB = Mojo.Util.bind(this, this._cancelListener);
      this._MdFormAdminController.setCancelListener(cancelB);
      
			var updateB = Mojo.Util.bind(this, this._updateListener);
      this._MdFormAdminController.setUpdateListener(updateB);
    },
    render : function()
    {
      // attach the event handlers to the DOM elements
      YAHOO.util.Event.on(this.constructor.AVAILABLE_FIELDS, 'click', this.availableFields, null, this);
      YAHOO.util.Event.onAvailable(this.constructor.EXISTING_FORMS, this.existingForms, null, this);
			
			// Could not get this to work, at all:
      //YAHOO.util.Event.delegate('existingForms', 'click', this.viewForm, 'li', null, this);
			
			// Got this to work but it's kinda wonky, behavior does not match API
      var Y = YUI().use('*');
      Y.one('#existingForms').delegate('click', this.viewForm, 'li', null, this);
			
      //YAHOO.util.Event.on(this.constructor.CANCEL_BTN, 'click', this._cancelListener, null, this);
      //YAHOO.util.Event.on(this.constructor.UPDATE_BTN, 'click', this._updateListener, null, this);
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
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html){
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          document.getElementById(that.constructor.EXISTING_FORMS).innerHTML = pureHTML;
        },
        onFailure : function(html){
          alert("Fetching existing forms failed");
        }
      });
      
      this._MdFormAdminController.existingForms(request);
		},
		viewForm : function(e, that)
		{
			var id = e.currentTarget._node.id;
			var request = new MDSS.Request({
	      onSuccess : function(html){
					var executable = MDSS.util.extractScripts(html);
	        var pureHTML = MDSS.util.removeScripts(html);
					document.getElementById(that.constructor.FORM_CONTENT_BOX).innerHTML = pureHTML;
					
					try {
						eval(executable);
					} catch (e) {
						console.log(e);
					}
					
	      },
	      onFailure : function(html){
	        alert("Fetching form with id "+id+" failed");
	      }
      });
      
      that._MdFormAdminController.fetchFormAttributes(request, id);
		},
		_updateListener : function(form)
		{
			var that = this;
			var request = new MDSS.Request({
				onSuccess : function(html)
				{
					var executable = MDSS.util.extractScripts(html);
					var pureHTML = MDSS.util.removeScripts(html);
					document.getElementById(that.constructor.FORM_CONTENT_BOX).innerHTML = pureHTML;
					eval(executable);
					alert('Update Successful!');	
				},
				onFailure : function(message)
				{
					alert(message);
				}
			});
			
			return request;
		},
		_populateForm : function(params)
		{
			
		},
    _cancelListener : function(params)
    {
      var that = this;
      var request = new MDSS.ClientRequest({
        onSuccess : function()
        {
          alert('Update Canceled!');
          
        },
        onFailure : function(message)
        {
          alert(message);
        }
      });
			      
      return request;
    },
		requestEdit : function(formId)
		{
			var that = this;
			var request = new MDSS.Request({
			 onSuccess : function(html)
			 {
			   var executable = MDSS.util.extractScripts(html);
			   var pureHTML = MDSS.util.removeScripts(html);
			 },
			 onFailure : function(message)
			 {
			   alert(message);
			 }
			});
		}
  }
});

})();