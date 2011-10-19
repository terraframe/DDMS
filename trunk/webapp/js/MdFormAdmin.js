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
		CANCEL_DELETE_BUTTON : 'cancelDeleteButton',
		CONFIRM_DELETE_BUTTON : 'confirmDeleteButton',
		CREATE_NEW_FORM : 'createNewForm',
		VIEW_FORM : 'viewForm',
		EXISTING_FORMS : 'existingForms',
		FORM_CONTENT : 'formContent',
		FORM_CONTENT_BOX : 'formContentBox',
		FORM_ACTION_ROW : 'formActionRow',
		FORM_ITEM_ROW : 'formItemRow',
		EDIT_BUTTON : 'editBtn',
		TABBED_FORM_BOX : 'tabbedFormBox',
		WORKFLOW_TREE : 'workflowTree'
  },
  Instance : {
    initialize : function()
    {
      UI.Manager.setFactory("YUI3");
      this._Factory = UI.Manager.getFactory();
     
      // Dialog for selecting the available MdFields
      this._fieldsDialog = null;
      
      this._fieldFormDialog = null;
			
			this._confirmDeleteDialog = null;
			
      this._fieldList = this._Factory.newList("Field List", {id: "fieldList"});
      
      this._MdFormAdminController = dss.vector.solutions.form.MdFormAdminController;
      this._MdFormUtil = dss.vector.solutions.generator.MdFormUtil;
      var cancelB = Mojo.Util.bind(this, this._cancelListener);
      this._MdFormAdminController.setCancelListener(cancelB);
      
			var updateB = Mojo.Util.bind(this, this._updateListener);
      this._MdFormAdminController.setUpdateListener(updateB);
			
			var createB = Mojo.Util.bind(this, this._createListener);
			this._MdFormAdminController.setCreateListener(createB);
      
			var deleteB = Mojo.Util.bind(this, this.confirmDeleteForm);
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
      
      this._tabview = new this._Y.TabView({
        srcNode: '#tabbedFormBox'
      });

      this._tabview.render();
      
      this._tree = null;
    },
    /**
     * Handler to model the drop operation between a source node (the one being dragged) 
     * and the destinaton node (the one being dropped on).
     */
    dragAndDropHandler : function(sourceDDNode, destId)
    {
      var destNode = YAHOO.util.DDM.getDDById(destId).node;
      var sourceNode = sourceDDNode.node;
      
      // remove the source node from its parent
      var originalParent = sourceNode.parent;
      sourceNode.tree.popNode(sourceNode);
      
      // put the source node beneath the destination node
      sourceNode.insertAfter(destNode);
      
      originalParent.refresh();
      destNode.parent.refresh();
      
      // starting at the root, traverse the tree to gather the nodes in reverse pre-order
      var formNode = this._tree.getRoot().children[0];
      var orderedIds = [];
      this._traverseNode(formNode, orderedIds);
      
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(){
          // refresh the preview mode of the fields.
          that.fetchFormFields(false);
        }
      });
      
      this._MdFormUtil.reorderFields(request, orderedIds);
    },
    _traverseNode : function(parent, orderedIds)
    {
      var children = parent.children;
      for(var i=0, len=children.length; i<len; i++)
      {
        var child = children[i];
        var fieldId = child.data.fieldId;
        orderedIds.push(fieldId);
        
        this._traverseNode(child, orderedIds);
      }
    },
    render : function()
    {
			// hide the form content DOM elements
      this._Y.one('#'+this.constructor.FORM_CONTENT).setStyle('visibility', 'hidden');
			
      // attach the event handlers to the DOM elements
      YAHOO.util.Event.on(this.constructor.AVAILABLE_FIELDS, 'click', this.availableFields, null, this);
      YAHOO.util.Event.on(this.constructor.CREATE_NEW_FORM, 'click', this.createNewForm, null, this);
      this._Y.one('#'+this.constructor.EXISTING_FORMS).delegate('click', this.viewForm, 'li', this);
      this._Y.one('#'+this.constructor.FORM_ITEM_ROW).delegate('click', this.confirmDeleteMdField, 'a.form-item-row-delete', this);
      this._Y.one('#'+this.constructor.FORM_ITEM_ROW).delegate('click', this.editField, 'li', this);
      
      // show the existing forms
      this.existingForms();
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
        this._fieldFormDialog = this._Factory.newDialog('', {close: false});
				this._fieldFormDialog.getContentEl().addClassName("form-dialog-scroll");
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.render();
      }
			
      eval(executable);
    },
    updateMdField : function(fieldMap)
    {
      var fieldId = fieldMap['mdField.componentId'];
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html)
        {
          that._fieldFormDialog.getImpl().hide();
          that.fetchFormFields(false);
          
          that.updateNode(fieldId);
        }
      });
      
      return request;
    },
    /**
     * Resets the contents of the field node with the given id.
     */
    updateNode : function(fieldId){
      
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(field){
          
          var node = that._tree.getNodeByProperty('fieldId', fieldId);
          node.setHtml(field.toString());
        }
      });
      
      com.runwaysdk.system.metadata.MdField.get(request, fieldId);
    },
    createMdField : function(fieldMap)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html)
        {
          that._fieldFormDialog.getImpl().hide();
          that.fetchFormFields(true);
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
      this.fetchFormFields(true);
      this._Y.one('#'+this.constructor.FORM_CONTENT).setStyle('visibility', 'visible');
      this._Y.one('#'+this.constructor.TABBED_FORM_BOX).setStyle('visibility', 'visible');
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
		fetchFormFields : function(refreshTree)
		{
			var id = this._currentMdFormId;
      var that = this;
      var request = new MDSS.Request({
				onSuccess : function(fieldList){
          that.updateFields(fieldList);
          
          if(refreshTree)
          {
            that.addFieldsToTree(fieldList);
          }
        }        
      });
      
			this._MdFormUtil.getFieldsById(request, id);
		},
		addFieldsToTree : function(fieldList)
		{
		  // destroy the tree entirely and start fresh
		  if(this._tree !== null)
		  {
		    this._tree.destroy();
		  }
      
      var dAndDHandler = Mojo.Util.bind(this, this.dragAndDropHandler);
      this._tree = new YAHOO.widget.TreeViewDD(this.constructor.WORKFLOW_TREE, [], dAndDHandler);
		
		
		  // grab the form's display label as the new quasi-root
		  var formDisplay = this._Y.one(document.getElementById(this._currentMdFormId)).get('text'); // workaround for invalid ids
		  var formNode = new YAHOO.widget.TextNode({label:formDisplay,formId:this._currentMdFormId}, this._tree.getRoot());
		  
		  for(var i=0, len=fieldList.length; i<len; i++)
		  {
		    var field = fieldList[i];
		    var content = field.toString();
		    new YAHOO.widget.HTMLNode({html:content,fieldId:field.getId()}, formNode);
		  }
		  
  		this._tree.render();
		},
		/**
		 * Clears a single field by removing it from the tree and field list.
		 */
		clearField : function(fieldId)
		{
		  // remove the field from list
		  var li = document.getElementById(fieldId);
		  var item = this._fieldList.getItemByLI(li);
		  this._fieldList.removeItem(item);
		  
		  // remove the field from the tree
		  var node = this._tree.getNodeByProperty('fieldId', fieldId);
		  this._tree.removeNode(node, true);
		},
		updateFields : function(fields){
		  
		  // clear any prior field items
		  this._fieldList.clearItems();
		
			for (var i = 0; i < fields.length; i++)
			{
				var field = fields[i];
				var itemContent = "[" + field.getFieldName() + "] Type: " + field.getType();
				var fieldId = field.getId();
	      var listItem = this._Factory.newListItem(field);
				listItem.getEl().setId(fieldId);
				
		    var deleteLink = this._Factory.newElement("a");
				deleteLink.addClassName("form-item-row-delete edit-mode-functionality");
				deleteLink.setInnerHTML("Delete");
				deleteLink.setId(fieldId);
				
				listItem.appendChild(deleteLink);
				
	      this._fieldList.addItem(listItem);
			}

      var fieldDiv = document.getElementById(this.constructor.FORM_ITEM_ROW);
			this._fieldList.render(fieldDiv);
    },
		_createListener : function(fieldMap)
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
          that._currentMdFormId = document.getElementsByName("form.componentId")[0].value;
          that._Y.one('#'+that.constructor.TABBED_FORM_BOX).setStyle('visibility', 'visible');
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
    _cancelListener : function(fieldMap)
    {
			if (fieldMap["form.isNew"] === "true") {
			  document.getElementById(this.constructor.FORM_CONTENT_BOX).innerHTML = "";
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
						eval(executable);
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
		confirmDeleteForm : function(e)
		{
			var that = this;
			var request = new MDSS.Request({
				onFailure : function(e)
				{
					var wrapperDiv = that._Factory.newElement('div');
          
          var upperDiv = that._Factory.newElement('div');
          upperDiv.addClassName('alertbox modalAlertBox');

          var message = that._Factory.newElement('span');
          message.setInnerHTML(e.getLocalizedMessage());
          upperDiv.appendChild(message);

          // yes/no buttons
          var lowerDiv = that._Factory.newElement('div');
          lowerDiv.addClassName('alertbox modalAlertBox');
          
          var confirmButton = that._Factory.newElement('button', {id:'confirmDeleteButton'});
          confirmButton.setInnerHTML("Confirm");
          
          var cancelButton = that._Factory.newElement('button', {id:'cancelDeleteButton'});
          cancelButton.setInnerHTML("Cancel");
          
          var lowerDiv = that._Factory.newElement('div');
          lowerDiv.appendChild(confirmButton);
          lowerDiv.appendChild(cancelButton);
          
          wrapperDiv.appendChild(upperDiv);
          wrapperDiv.appendChild(lowerDiv);
          
          if(that._confirmDeleteDialog !== null)
          {
						that._confirmDeleteDialog.setInnerHTML("");
            that._confirmDeleteDialog.appendChild(wrapperDiv);
            that._confirmDeleteDialog.getImpl().show();
          }
          else
          {
            that._confirmDeleteDialog = that._Factory.newDialog('', {close: false});
            that._confirmDeleteDialog.appendChild(wrapperDiv);
            that._confirmDeleteDialog.render();
          }
          YAHOO.util.Event.on(that.constructor.CONFIRM_DELETE_BUTTON, 'click', that.deleteForm, null, that);
          YAHOO.util.Event.on(that.constructor.CANCEL_DELETE_BUTTON, 'click', that.cancelDeleteDialog, null, that);
				}
			});
			
			this._MdFormUtil.confirmDeleteForm(request, this._currentMdFormId);
		},
		confirmDeleteMdField : function(e)
    {
      var fieldId = e.currentTarget.get('id');
      var that = this;
      var request = new MDSS.Request({
        onFailure : function(e)
        {
          var wrapperDiv = that._Factory.newElement('div');
					
          var upperDiv = that._Factory.newElement('div');
          upperDiv.addClassName('alertbox modalAlertBox');

          var message = that._Factory.newElement('span');
					message.setInnerHTML(e.getLocalizedMessage());
          upperDiv.appendChild(message);

          // yes/no buttons
          var lowerDiv = that._Factory.newElement('div');
          lowerDiv.addClassName('alertbox modalAlertBox');
          
          var confirmButton = that._Factory.newElement('button', {id:'confirmDeleteButton'});
					confirmButton.setInnerHTML("Confirm");
					
          var cancelButton = that._Factory.newElement('button', {id:'cancelDeleteButton'});
					cancelButton.setInnerHTML("Cancel");
					
          var lowerDiv = that._Factory.newElement('div');
          lowerDiv.appendChild(confirmButton);
          lowerDiv.appendChild(cancelButton);
					
					wrapperDiv.appendChild(upperDiv);
					wrapperDiv.appendChild(lowerDiv);
          
          if(that._confirmDeleteDialog !== null)
          {
						that._confirmDeleteDialog.setInnerHTML("");
            that._confirmDeleteDialog.appendChild(wrapperDiv);
            that._confirmDeleteDialog.getImpl().show();
          }
          else
          {
            that._confirmDeleteDialog = that._Factory.newDialog('', {close: false});
            that._confirmDeleteDialog.appendChild(wrapperDiv);
            that._confirmDeleteDialog.render();
          }
          YAHOO.util.Event.on(that.constructor.CONFIRM_DELETE_BUTTON, 'click', that.deleteField, fieldId, that);
          YAHOO.util.Event.on(that.constructor.CANCEL_DELETE_BUTTON, 'click', that.cancelDeleteDialog, null, that);
        }
      });
      
      this._MdFormUtil.confirmDeleteMdField(request, this._currentMdFormId, fieldId);
    },
		cancelDeleteDialog : function(e)
		{
			this._confirmDeleteDialog.getImpl().hide();
		},
		editField : function(e)
		{
			var type = e.target.get('nodeName');
			if (type !== 'A') {
				var fieldId = e.currentTarget.get('id');
				var that = this;
				var request = new MDSS.Request({
					onSuccess: function(html){
						that.editMdFieldDialog(html);
					}
				});
				
				this._MdFormAdminController.editMdField(request, fieldId);
			}
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
        this._fieldFormDialog = this._Factory.newDialog('', {close: false});
        this._fieldFormDialog.getContentEl().addClassName("form-dialog-scroll");
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.render();
      }
      
      eval(executable);
    },
    cancelMdField : function(fieldMap)
    {
      if (fieldMap["mdField.isNew"] === "true") {
          this._fieldFormDialog.getImpl().hide();
			}
			else {
				var that = this;
				var request = new MDSS.Request({
					onSuccess: function(html){
						that._fieldFormDialog.getImpl().hide();
					}
				});
				return request;
			}
    },
		deleteField : function(e, fieldId)
    {
      this._confirmDeleteDialog.getImpl().hide();
      var that = this;
      var request = new MDSS.Request({
       onSuccess : function(html)
       {
         that.clearField(fieldId);
       }
      });

      this._MdFormAdminController.deleteField(request, this._currentMdFormId, fieldId);
    },
	  deleteForm : function(e)
    {
      this._confirmDeleteDialog.getImpl().hide();
      var that = this;
      var request = new MDSS.Request({
       onSuccess : function(html)
       {
         that._fieldList.clearItems();
         that._Y.one('#'+that.constructor.FORM_CONTENT).setStyle('visibility', 'hidden');
         that._Y.one('#'+that.constructor.TABBED_FORM_BOX).setStyle('visibility', 'hidden');
       }
      });

      this._MdFormAdminController.deleteForm(request, this._currentMdFormId);
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
         
         that._fieldList.clearItems();
         that._Y.one('#'+that.constructor.FORM_CONTENT).setStyle('visibility', 'visible');
         that._Y.one('#'+that.constructor.TABBED_FORM_BOX).setStyle('visibility', 'hidden');
       }
      });
      that._MdFormAdminController.newInstance(request);
		}
  }
});

})();