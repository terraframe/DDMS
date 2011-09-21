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
		CREATE_NEW_FORM : 'createNewForm',
		VIEW_FORM : 'viewForm',
		EXISTING_FORMS : 'existingForms',
		FORM_CONTENT : 'formContent',
		FORM_CONTENT_BOX : 'formContentBox',
		FORM_ACTION_ROW : 'formActionRow',
		EDIT_BUTTON : 'editBtn'
  },
  Instance : {
    initialize : function()
    {
      UI.Manager.setFactory("YUI3");
      this._Factory = UI.Manager.getFactory();
      
      // Dialog for selecting the available MdFields
      this._fieldsDialog = null;
      
      this._fieldFormDialog = null;
      
			this._MdFormAdminController = dss.vector.solutions.form.MdFormAdminController;
			var cancelB = Mojo.Util.bind(this, this._cancelListener);
      this._MdFormAdminController.setCancelListener(cancelB);
      
			var updateB = Mojo.Util.bind(this, this._updateListener);
      this._MdFormAdminController.setUpdateListener(updateB);
			
			var createB = Mojo.Util.bind(this, this._createListener);
			this._MdFormAdminController.setCreateListener(createB);
      
			var editB = Mojo.Util.bind(this, this.requestEdit);
			this._MdFormAdminController.setEditFormAttributesListener(editB);
      
      // A reference to the MdForm that is being operated on.
      this._currentMdFormId = null;
      this._Y = YUI().use('*'); // YUI3 reference
    },
    render : function()
    {
      // attach the event handlers to the DOM elements
      YAHOO.util.Event.on(this.constructor.AVAILABLE_FIELDS, 'click', this.availableFields, null, this);
      YAHOO.util.Event.on(this.constructor.CREATE_NEW_FORM, 'click', this.createNewForm, null, this);
      YAHOO.util.Event.onAvailable(this.constructor.EXISTING_FORMS, this.existingForms, null, this);
			
      this._Y.one('#'+this.constructor.EXISTING_FORMS).delegate('click', this.viewForm, 'li', this);
    },
    destroy : function()
    {
      // TODO destroy all dialogs if they are not null
      //this._fieldsDialog.destroy();
    },
    /**
     * Makes a request to display all available MdField types for the Form Generator.
     */
    availableFields : function()
    {
      if(this._fieldsDialog !== null)
      {
        this._fieldsDialog.getImpl().show(); // FIXME add show/hide to widgets
      }
      else
      {
        // first request, so create the dialog with the available field options.
        this._fieldsDialog = this._Factory.newDialog('');
        
        var that = this;
        var request = new MDSS.Request({
          onSuccess : function(html){
            var executable = MDSS.util.extractScripts(html);
            var pureHTML = MDSS.util.removeScripts(html);
            
            that.createAvailableFieldsDialog(pureHTML);            
          }
        });
        
        this._MdFormAdminController.availableFields(request);
      }
    },
    
    createAvailableFieldsDialog : function(html)
    {
      this._fieldsDialog.setInnerHTML(html);
      this._fieldsDialog.render();
      
      // hook the click handler to the dialog to detect which field type was selected.
      var id = this._fieldsDialog.getContentEl().getId();
      this._Y.one('#'+id).delegate('click', this.newField, 'li', this);
    },
    
    /**
     * Make a request for a new instance of an MdField.
     */
    newField : function(e)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html){
          that.newMdFieldDialog(html);
        }
      });
      
      var mdFieldId = e.target.get('id');
      this._MdFormAdminController.newMdField(request, mdFieldId);
    },
    newMdFieldDialog : function(html)
    {
      this._fieldsDialog.getImpl().hide();
    
      var executable = MDSS.util.extractScripts(html);
      var pureHTML = MDSS.util.removeScripts(html);
      
      if(this._fieldFormDialog !== null)
      {
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.getImpl().show();
      }
      else
      {
        this._fieldFormDialog = this._Factory.newDialog('');
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.render();
        
        //this._Y.one(this._fieldFormDialog.getContentEl().getId()).delegate('click', this._
      }
      
      // No controllers exist for the MdField classes (at least not yet),
      // so we do not execute the javascript that will fire the MdAction listeners.
      //exec(executable);
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
		viewForm : function(e)
		{
			var id = e.currentTarget.get('id');
			this._currentMdFormId = id;
			var that = this;
			var request = new MDSS.Request({
	      onSuccess : function(html){
					var executable = MDSS.util.extractScripts(html);
	        var pureHTML = MDSS.util.removeScripts(html);
					document.getElementById(that.constructor.FORM_CONTENT).innerHTML = pureHTML;
					eval(executable);
          //YAHOO.util.Event.on(that.constructor.EDIT_BUTTON, 'click', that.requestEdit, null, this);
	      }
      });
      
      this._MdFormAdminController.fetchFormAttributes(request, id);
		},
		_createListener : function(form)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html)
        {
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          document.getElementById(that.constructor.FORM_CONTENT).innerHTML = pureHTML;
          eval(executable);
					that.existingForms();
          alert('Update Successful!');  
        }
      });
      
      return request;
    },
		_updateListener : function(form)
		{
			var that = this;
			var request = new MDSS.Request({
				onSuccess : function(html)
				{
					var executable = MDSS.util.extractScripts(html);
					var pureHTML = MDSS.util.removeScripts(html);
					document.getElementById(that.constructor.FORM_CONTENT).innerHTML = pureHTML;
					eval(executable);
					that.existingForms();
					alert('Update Successful!');	
				}
			});
			
			return request;
		},
    _cancelListener : function(params)
    {
      var that = this;
      var request = new MDSS.ClientRequest({
        onSuccess : function(html)
        {
          alert('Update Canceled!');
					var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          document.getElementById(that.constructor.FORM_CONTENT).innerHTML = pureHTML;
        }
      });
			      
      return request;
    },
		requestEdit : function()
		{
			var that = this;
			var request = new MDSS.Request({
			 onSuccess : function(html)
			 {
			   var executable = MDSS.util.extractScripts(html);
			   var pureHTML = MDSS.util.removeScripts(html);
				 document.getElementById(that.constructor.FORM_CONTENT_BOX).innerHTML = pureHTML;
				 eval(executable);
			 }
			});
			var id = document.getElementById("MdFormId").value;
		  this._MdFormAdminController.editFormAttributes(request, id);
		},
		createNewForm : function()
		{
			var that = this;
      var request = new MDSS.Request({
       onSuccess : function(html)
       {
         var executable = MDSS.util.extractScripts(html);
         var pureHTML = MDSS.util.removeScripts(html);
         document.getElementById(that.constructor.FORM_CONTENT).innerHTML = pureHTML;
         eval(executable);
       }
      });
      that._MdFormAdminController.newInstance(request);
		}
  }
});

})();