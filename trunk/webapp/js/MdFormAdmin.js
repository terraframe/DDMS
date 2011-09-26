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
		FORM_ITEM_ROW : 'formItemRow',
		EDIT_BUTTON : 'editBtn'
  },
  Instance : {
    initialize : function()
    {
      UI.Manager.setFactory("YUI3");
      this._Factory = UI.Manager.getFactory();
      
      // Dialog for selecting the available MdFields
      this._fieldsDialog = null;
      this._editMode = false; // FIXME implement and cleanup lifecycle
      
      this._fieldFormDialog = null;
      
			this._MdFormAdminController = dss.vector.solutions.form.MdFormAdminController;
			var cancelB = Mojo.Util.bind(this, this._cancelListener);
      this._MdFormAdminController.setCancelListener(cancelB);
      
			var updateB = Mojo.Util.bind(this, this._updateListener);
      this._MdFormAdminController.setUpdateListener(updateB);
			
			var createB = Mojo.Util.bind(this, this._createListener);
			this._MdFormAdminController.setCreateListener(createB);
      
			var deleteB = Mojo.Util.bind(this, this._deleteListener);
			this._MdFormAdminController.setDeleteListener(deleteB);
      
			var editB = Mojo.Util.bind(this, this.requestEdit);
			this._MdFormAdminController.setEditFormAttributesListener(editB);
			
			var updateMdFieldB = Mojo.Util.bind(this, this.updateMdField);
			this._MdFormAdminController.setUpdateMdFieldListener(updateMdFieldB);
			
			var createMdFieldB = Mojo.Util.bind(this, this.createMdField);
			this._MdFormAdminController.setCreateMdFieldListener(createMdFieldB);
			
      var cancelMdFieldB = Mojo.Util.bind(this, this.cancelMdField);
      this._MdFormAdminController.setCancelMdFieldListener(cancelMdFieldB);
      
      // A reference to the MdForm that is being operated on.
      this._currentMdFormId = null;
      this._Y = YUI().use('*'); // YUI3 reference
      
    },
    render : function()
    {
      // attach the event handlers to the DOM elements
      YAHOO.util.Event.on(this.constructor.AVAILABLE_FIELDS, 'click', this.availableFields, null, this);
      YAHOO.util.Event.on(this.constructor.CREATE_NEW_FORM, 'click', this.createNewForm, null, this);
      this._Y.one('#'+this.constructor.EXISTING_FORMS).delegate('click', this.viewForm, 'li', this);
      this._Y.one('#'+this.constructor.FORM_ITEM_ROW).delegate('click', this.deleteField, 'a.form-item-row-delete', this);
      this._Y.one('#'+this.constructor.FORM_ITEM_ROW).delegate('click', this.editField, 'input', this);
      
      // show the existing forms
      this.existingForms();
    },
    /**
     * Shows or hides the edit mode functionality of a form (i.e., the CRUD operatons on the fields).
     * 
     * @param enable true if edit mode should be shown or false if everything should be in read mode only.
     */
    toggleEditMode : function(enable)
    {
      var nodeCol = this._Y.all('.edit-mode-functionality'); // TODO optimize or use another call?
      nodeCol.setStyle('visibility', enable ? 'visible' : 'hidden');
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
      
      var mdFieldType = e.currentTarget.get('id');
      this._MdFormAdminController.newMdField(request, mdFieldType);
    },
    newMdFieldDialog : function(html)
    {
      this._fieldsDialog.getImpl().hide(); // FIXME add show/hide to widgets
    
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
      }
      
      eval(executable);
    },
    
    updateMdField : function(fieldMap)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html)
        {
          that._fieldFormDialog.getImpl().hide();
        
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          document.getElementById(that.constructor.FORM_ITEM_ROW).innerHTML = pureHTML;
          eval(executable);
        }
      });
      
      return request;
    },
    createMdField : function(fieldMap)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html)
        {
          that._fieldFormDialog.getImpl().hide();
        
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          document.getElementById(that.constructor.FORM_ITEM_ROW).innerHTML = pureHTML;
          eval(executable);
        }
      });
      
      // manually set the MdForm id because it is not in the actual form.
      fieldMap['mdFormId'] = this._currentMdFormId;

      return request;
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
        }
      });
      
      this._MdFormAdminController.existingForms(request);
		},
	  viewForm : function(e)
    {
      var id = e.currentTarget.get('id');
      this._currentMdFormId = id;
      this.fetchFormAttributes();
      this.fetchFormFields();
      this._Y.one('#'+this.constructor.FORM_CONTENT).setStyle('visibility', 'visible');
		},
	  fetchFormAttributes : function()
		{
			var id = this._currentMdFormId;
			var that = this;
			var request = new MDSS.Request({
	      onSuccess : function(html){
					var executable = MDSS.util.extractScripts(html);
	        var pureHTML = MDSS.util.removeScripts(html);
					document.getElementById(that.constructor.FORM_CONTENT_BOX).innerHTML = pureHTML;
          eval(executable);
	      }
      });
      
      this._MdFormAdminController.fetchFormAttributes(request, id);
		},
		fetchFormFields : function()
		{
			var id = this._currentMdFormId;
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html){
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          document.getElementById(that.constructor.FORM_ITEM_ROW).innerHTML = pureHTML;
          eval(executable);
        }
      });
      
      this._MdFormAdminController.fetchFormFields(request, id);
		},
		_createListener : function(form)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html)
        {
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          document.getElementById(that.constructor.FORM_CONTENT_BOX).innerHTML = pureHTML;
          eval(executable);
					that.existingForms();
        }
      });
      
      return request;
    },
		_deleteListener : function(form)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html)
        {
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          document.getElementById(that.constructor.FORM_CONTENT).innerHTML = "";
          eval(executable);
          that.existingForms();
          
          this._currentMdFormId = null;
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
					document.getElementById(that.constructor.FORM_CONTENT_BOX).innerHTML = pureHTML;
					eval(executable);
					that.existingForms();
				}
			});
			
			return request;
		},
    _cancelListener : function(params)
    {
			if (params["form.isNew"] === "true") {
			  document.getElementById(this.constructor.FORM_CONTENT_BOX).innerHTML = "";
			  document.getElementById(this.constructor.FORM_ITEM_ROW).innerHTML = "";
			  this._Y.one('#'+this.constructor.FORM_CONTENT).setStyle('visibility', 'hidden');
			  this._currentMdFormId = null;
			}
			else {
        var that = this;
				var request = new MDSS.Request({
					onSuccess: function(html){
						var executable = MDSS.util.extractScripts(html);
						var pureHTML = MDSS.util.removeScripts(html);
						document.getElementById(that.constructor.FORM_CONTENT_BOX).innerHTML = pureHTML;
					}
				});
				
				return request;
			}
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
			
		  this._MdFormAdminController.editFormAttributes(request, this._currentMdFormId);
		},
		editField : function(e)
		{
			var fieldId = e.currentTarget.get('id');
			var that = this;
		  var request = new MDSS.Request({
		    onSuccess : function(html){
		      that.editMdFieldDialog(html);
		    }
		  });

      this._MdFormAdminController.editMdField(request, fieldId);
		},
    editMdFieldDialog : function(html)
    {
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
      }
      
      eval(executable);
    },
    cancelMdField : function(fieldMap)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html){
          that._fieldFormDialog.getImpl().hide();
        }
      });
      return request;
    },
		deleteField : function(e)
    {
			var fieldId = e.currentTarget.get('id');
      var that = this;
      var request = new MDSS.Request({
       onSuccess : function(html)
       {
         var executable = MDSS.util.extractScripts(html);
         var pureHTML = MDSS.util.removeScripts(html);
         document.getElementById(that.constructor.FORM_ITEM_ROW).innerHTML = pureHTML;
         eval(executable);
       }
      });

      this._MdFormAdminController.deleteField(request, this._currentMdFormId, fieldId);
    },
		createNewForm : function()
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
      that._MdFormAdminController.newInstance(request);
		}
  }
});

})();