/*
 * Manages control flow for the MdForm admin page.
 */
(function(){

var UI = Mojo.Meta.alias("com.runwaysdk.ui.*");

/**
 * Class wrapper around an object literal to pass into a ListItem.
 */
var FieldItem = Mojo.Meta.newClass('dss.vector.solutions.FieldItem', {
  Instance : {
    initialize : function(id, label){
      this._id = id;
      this._label = label;
    },
    getId : function() { return this._id; },
    getHashCode : function() { return this.getId(); },
    toString : function() { return this._label; }
  }
});

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
    IMPORT_FORM : 'importForm',
    VIEW_FORM : 'viewForm',
    EXISTING_FORMS : 'existingForms',
    FORM_CONTENT : 'formContent',
    FORM_CONTENT_BOX : 'formContentBox',
    FORM_ACTION_ROW : 'formActionRow',
    FORM_ITEM_ROW : 'formItemRow',
    EDIT_BUTTON : 'editBtn',
    TABBED_FORM_BOX : 'tabbedFormBox',
    WORKFLOW_TREE : 'workflowTree',
    FORM_NODE : 'formNode',
    FIELD_NODE : 'fieldNode',
    GROUP_NODE : 'groupNode'
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
      
      this._MdFormAdminController.setExportDefinitionListener(Mojo.Util.bind(this, this.requestExport));
      
      var updateMdFieldB = Mojo.Util.bind(this, this.updateMdField);
      this._MdFormAdminController.setUpdateMdFieldListener(updateMdFieldB);
      this._MdFormAdminController.setUpdateGeoFieldListener(updateMdFieldB);
      
      var createMdFieldB = Mojo.Util.bind(this, this.createMdField);
      this._MdFormAdminController.setCreateMdFieldListener(createMdFieldB);
      this._MdFormAdminController.setCreateGeoFieldListener(createMdFieldB);
      
      var cancelMdFieldB = Mojo.Util.bind(this, this.cancelMdField);
      this._MdFormAdminController.setCancelMdFieldListener(cancelMdFieldB);
      
      var createCondition = Mojo.Util.bind(this, this.createCondition);
      this._MdFormAdminController.setCreateConditionListener(createCondition);

      var editCondition = Mojo.Util.bind(this, this.editCondition);
      this._MdFormAdminController.setEditConditionListener(editCondition);
      
      var updateCondition = Mojo.Util.bind(this, this.updateCondition);
      this._MdFormAdminController.setUpdateConditionListener(updateCondition);
      
      var cancelCondition = Mojo.Util.bind(this, this.cancelCondition);
      this._MdFormAdminController.setCancelConditionListener(cancelCondition);
      
      var deleteCondition = Mojo.Util.bind(this, this.deleteCondition);
      this._MdFormAdminController.setDeleteConditionListener(deleteCondition);      
            
      // A reference to the MdForm that is being operated on.
      this._currentMdFormId = null;
      this._Y = YUI().use('*'); // YUI3 reference
      
      this._tabview = new this._Y.TabView({
        srcNode: '#tabbedFormBox'
      });

      this._tabview.render();
      
      /**
       * A mapping between the type of node and the menu items allowed on it. Each array
       * value is a function that returns a single menu item.
       */
      this._menuItems = {};
      this._menuItems[this.constructor.FORM_NODE] = [this._addGroupItem];
      this._menuItems[this.constructor.FIELD_NODE] = [this._viewConditionsItem];
      this._menuItems[this.constructor.GROUP_NODE] = [this._addGroupItem, this._editGroupItem, 
      this._deleteGroupItem, this._viewConditionsItem];

      this._menu = new YAHOO.widget.ContextMenu('workflowTreeMenu', {
        trigger:this.constructor.WORKFLOW_TREE,
        lazyload:true,
        zindex:500
      });
      this._menu.subscribe("triggerContextMenu", this.showContextMenu, null, this);
      
      /**
       * Dialog to contain all the condition related CRUD operations for an MdField.
       */
      this._conditionsDialog = null;
      
      var dAndDHandler = Mojo.Util.bind(this, this.dragAndDropHandler);
      var enterHandler = Mojo.Util.bind(this, this.enterHandler);
      var exitHandler = Mojo.Util.bind(this, this.exitHandler);
      var dragHandler = Mojo.Util.bind(this, this.dragHandler);
      this._tree = new YAHOO.widget.TreeViewDD(this.constructor.WORKFLOW_TREE, [], dAndDHandler, enterHandler, exitHandler, dragHandler);
      
      // Region reference to the plus sign of a group node for giving visual hints of an add-to-group operation.
      this._dropRegion = null;
    },
    _viewConditionsItem : function()
    {
      var viewConditions = new YAHOO.widget.ContextMenuItem(MDSS.localize('view_conditions'));
      viewConditions.subscribe("click", this.viewConditions, null, this);
      return viewConditions;
    },
    _addGroupItem : function()
    {
      var addGroup = new YAHOO.widget.ContextMenuItem(MDSS.localize('add_group'));
      addGroup.subscribe("click", this.addGroup, null, this);
      return addGroup;
    },
    _editGroupItem : function()
    {
      var editGroup = new YAHOO.widget.ContextMenuItem(MDSS.localize('edit_group'));
      editGroup.subscribe("click", this.editGroup, null, this);
      return editGroup;
    },
    _deleteGroupItem : function()
    {
      var deleteGroup = new YAHOO.widget.ContextMenuItem(MDSS.localize('delete_group'));
      deleteGroup.subscribe("click", this.deleteGroup, null, this);
      return deleteGroup;
    },
    /**
     * Shows the context menu and displays the items allowed for that node type.
     */
    showContextMenu : function(eventType, eventArgs)
    {
      // ensure an actual node element was clicked
      var oTarget = this._menu.contextEventTarget;
      var htmlNode = YAHOO.util.Dom.hasClass(oTarget, "ygtvcell") ? oTarget : YAHOO.util.Dom.getAncestorByClassName(oTarget, "ygtvcell");
      this._selectedNode = this._tree.getNodeByElement(oTarget);
      
      if (this._selectedNode) {

        this._menu.clearContent();
        
        var itemFunctions = this._menuItems[this._selectedNode.data.nodeType];
        var items = [itemFunctions.length];
        for(var i=0; i<itemFunctions.length; i++)
        {
          items[i] = itemFunctions[i].apply(this);
        }
        
        this._menu.addItems(items);
      
        if(this._menuRendered)
        {
          this._menu.render();
        }
        else
        {
          this._menu.render(this._menu.cfg.getProperty("container"));
          this._menuRendered = true;
        }
      }
      else {
        this._menu.cancel();
      }      
    },
    addGroup : function()
    {
      this.newField('com.runwaysdk.system.metadata.MdWebGroup');
    },
    editGroup : function()
    {
      var fieldId = this._selectedNode.data.fieldId;
      this.editField(fieldId);
    },
    deleteGroup : function()
    {
      var fieldId = this._selectedNode.data.fieldId;
      this.confirmDeleteMdField(fieldId);
    },
    getConditions : function()
    {
      var mdFieldId = this._selectedNode.data.fieldId;
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html)
        {
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
            
          that._Factory.newElement('#conditionsList').setInnerHTML(pureHTML);

          eval(executable);
        }
      });
      
      this._MdFormAdminController.getConditions(request, mdFieldId);
    },    
    createCondition : function(params)
    {
      var mdFieldId = this._selectedNode.data.fieldId;
      params['mdFieldId'] = mdFieldId;

      var that = this;      
      var request = new MDSS.Request({
        onSuccess : function(){
          that._Factory.newElement('#newConditionForm').setInnerHTML('');
          
          that.getConditions();        
        }
      });
      
      return request;
    },
    updateCondition : function(params)
    {
      var mdFieldId = this._selectedNode.data.fieldId;
      params['mdFieldId'] = mdFieldId;

      var that = this;      
      var request = new MDSS.Request({
        onSuccess : function(){
          that.getConditions();        
        }
      });
      
      return request;
    },
    editCondition : function(params)
    {
      var conditionId = params['conditionId'];
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html){
          // replace the contents of the LI with the condition form.
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          
          that._Factory.newElement('#'+conditionId).setInnerHTML(pureHTML);
          
          eval(executable);      
        }
      });
    
      return request;
    },
    deleteCondition : function(params)
    {
      var mdFieldId = this._selectedNode.data.fieldId;
      var conditionId = params['condition.componentId'];

      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(){
          that.getConditions();
        }
      });
    
      this._MdFormAdminController.deleteCondition(request, mdFieldId, conditionId);
    },
    /**
     * Requests the HTML to create a new Condition that references the MdField represented
     * by a select list option.
     */
    newCondition : function(e)
    {
      var mdFieldId = this._Y.one('#definingMdFieldSelect').get('value');
      if(mdFieldId && mdFieldId !== '')
      {
        var that = this;
        var request = new MDSS.Request({
          onSuccess : function(html)
          {
            var executable = MDSS.util.extractScripts(html);
            var pureHTML = MDSS.util.removeScripts(html);
            
            that._Factory.newElement('#newConditionForm').setInnerHTML(pureHTML);

            eval(executable);
          }
        });
        
        this._MdFormAdminController.newCondition(request, mdFieldId);
      }
    },
    cancelCondition : function(params)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(){
          that._Factory.newElement('#newConditionForm').setInnerHTML('');
          that.getConditions();
        }
      });
      
      return request;
    },
    /**
     * Creates the modal to add a new condition to the selected field.
     */
    viewConditions : function()
    {
      var fieldId = this._selectedNode.data.fieldId;
      
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html){
          that.renderConditionList(html);
        }
      });
      
      this._MdFormAdminController.getConditionList(request, fieldId);
    },
    renderConditionList : function(html)
    {
      var executable = MDSS.util.extractScripts(html);
      var pureHTML = MDSS.util.removeScripts(html);
      
      if(this._conditionsDialog !== null)
      {
        this._conditionsDialog.setInnerHTML(pureHTML);
        this._conditionsDialog.show();
      }
      else
      {
        this._conditionsDialog = this._Factory.newDialog('', {close: true, modal:true});
        this._conditionsDialog.getContentEl().addClassName("condition-dialog-scroll");
        this._conditionsDialog.setInnerHTML(pureHTML);
        this._conditionsDialog.render();
      }
      
      eval(executable);
      
      this._Y.one('#createNewCondition').on('click', this.newCondition, this);
    },
    /**
     * Adds the source node to the destination node and
     * removes the link from the old parent.
     */
    _addNodeToParent : function(sourceNode, destNode, appendTo)
    {
      // remove the source node from its parent
	  var originalParent = sourceNode.parent;
      if (sourceNode === destNode)
      {
        destNode = originalParent;
        appendTo = true;
      }

      sourceNode.tree.popNode(sourceNode);

      if(appendTo)
      {
        destNode.appendChild(sourceNode);      
      }
      else
      {
        sourceNode.insertAfter(destNode);
      }
      
      this._tree.getRoot().refresh();
      
      destNode.expand();
    },
    getGroupNodeHTML : function(display)
    {
      return '<div style="float:left">'+display+'</div><div style="float:left" class="groupDropTarget"></div>';
    },
    enableDropZone : function(plusEl){
      plusEl.style.backgroundColor = '#000000';
      plusEl.style.backgroundImage = 'URL(imgs/icons/tick.png)';
    },
    disableDropZone : function(plusEl){
      plusEl.style.backgroundColor = '';
      plusEl.style.backgroundImage = '';
    },
    /**
     * Checks if the drag target is hovering over a group node to add to the group. If so, the
     * drag hints are changed to reflect a valid group drop operation.
     */
    dragHandler : function(ddProxy){
      
      if(this._dropRegion !== null){
        var point = ddProxy.DDM.interactionInfo.point;
        
        // get the region of the plus sign
        
        var plusEl = this._dropRegion.getContentEl().firstChild.nextSibling;
        var plus = YAHOO.util.Dom.getRegion(plusEl);
        if(plus.contains(point)){
          this.enableDropZone(plusEl);
        }
        else {
          this.disableDropZone(plusEl);
        }
      }
      
    },
    enterHandler : function(sourceDDNode, destId){
    
      var destNode = YAHOO.util.DDM.getDDById(destId).node;
      
      if(destNode.data.nodeType === this.constructor.GROUP_NODE){

        this._dropRegion = destNode;
      }
    },
    exitHandler : function(sourceDDNode, destId){
    
      if(this._dropRegion !== null){
         var plusEl = this._dropRegion.getContentEl().firstChild.nextSibling;
         this.disableDropZone(plusEl);
      }
    
      this._dropRegion = null;
    },
    /**
     * Handler to model the drop operation between a source node (the one being dragged) 
     * and the destinaton node (the one being dropped on).
     */
    dragAndDropHandler : function(sourceDDNode, destId)
    {
      if(this._dropRegion !== null){
        var plusEl = this._dropRegion.getContentEl().firstChild.nextSibling;
        this.disableDropZone(plusEl);
      }
  
      this._dropRegion = null;
    
      var destNode = YAHOO.util.DDM.getDDById(destId).node;
      var sourceNode = sourceDDNode.node;
      
      // do nothing if the node is already a child
      var children = destNode.children;
      for(var i=0, len=children.length; i<len; i++)
      {
        if(sourceNode === children[i])
        {
          //return;
        }
      }
      
      
      // Add the field to the group if the destination is a group node
      if(destNode.data.nodeType === this.constructor.GROUP_NODE)
      {
        // only move the field to the group if the drop occured on a designated group drop zone
        var orig = YAHOO.util.Dom.getRegion(document.getElementById(destId).firstChild.nextSibling);
        var point = sourceDDNode.DDM.interactionInfo.point;

        if(orig.contains(point))      
        {
          var groupId = destNode.data.fieldId;
          var fieldId = sourceNode.data.fieldId;
        
          var that = this;
          var request = new MDSS.Request({
            onSuccess : function(){
               that._addNodeToParent(sourceNode, destNode, true);
               that.fetchFormFields();
            }
          });
        
          this._MdFormUtil.addToGroup(request, groupId, fieldId);
          return;
        }
      }
      
      // reorder the field
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(){
        
          that._addNodeToParent(sourceNode, destNode, false);
      
          // refresh the preview mode of the fields.
          that.fetchFormFields(false);
        }
      });
      
      if(destNode.data.nodeType === this.constructor.FORM_NODE)
      {
        // dragged to the form node, so grab the last field on the form to
        // simulate appending as the last field.
        destNode = destNode.children[destNode.children.length-1];
      }
      
      var prevFieldId = destNode.data.fieldId;
      
      this._MdFormUtil.reorderFields(request, [sourceNode.data.fieldId, prevFieldId]);
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
      YAHOO.util.Event.on(this.constructor.IMPORT_FORM, 'click', this.importForm, null, this);
      this._Y.one('#'+this.constructor.EXISTING_FORMS).delegate('click', this.viewForm, 'li', this);
      this._Y.one('#'+this.constructor.FORM_ITEM_ROW).delegate('click', this.confirmDeleteMdFieldHandler, 'a.form-item-row-delete', this);
      this._Y.one('#'+this.constructor.FORM_ITEM_ROW).delegate('click', this.editFieldHandler, 'li', this);
      
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
        this._fieldsDialog.show(); // FIXME add show/hide to widgets
      }
      else
      {
        // first request, so create the dialog with the available field options.
        this._fieldsDialog = this._Factory.newDialog('', {modal:true});
        
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
      this._Y.one('#'+id).delegate('click', this.newFieldHandler, 'li', this);
    },
    newField : function(mdFieldType)
    {
      var that = this;
      var currentFormId = this._currentMdFormId;
      
      var request = new MDSS.Request({
        onSuccess : function(html){
          that.newMdFieldDialog(html);
        }
      });
      
      this._MdFormAdminController.newMdField(request, mdFieldType, false, currentFormId);
    },
    /**
     * Make a request for a new instance of an MdField.
     */
    newFieldHandler : function(e)
    {
      // this is called via the fields tab so clear any notion of
      // the currently selected field. This is to avoid confusing adding
      // a new group (a field) via the workflow tree.
      this._selectedNode = null;
    
      var mdFieldType = e.currentTarget.get('id');
      this.newField(mdFieldType);
    },
    newMdFieldDialog : function(html)
    {
    	var that = this;
      // the fields dialog might not have been rendered if we're adding 
      // a new group field.
      if(this._fieldsDialog !== null)
      {
        this._fieldsDialog.hide();
      }
    
      var executable = MDSS.util.extractScripts(html);
      var pureHTML = MDSS.util.removeScripts(html);
      
      if(this._fieldFormDialog !== null)
      {
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.show();
      }
      else
      {
        this._fieldFormDialog = this._Factory.newDialog('', {close: false, modal:true});
        this._fieldFormDialog.getContentEl().addClassName("form-dialog-scroll");
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.render();
      }
      

      if(document.getElementById('expressionInputField')){
    	  this.setExpressionEventHandlers();
      }
      
      eval(executable);
    },
    setExpressionEventHandlers : function(){
      var that = this;
      var expFieldInputEL = document.getElementById('expressionInputField');
      
  	  expFieldInputEL.addEventListener('keypress', function(event) {
		  if (event.keyCode == 13) {
			  event.preventDefault();
		  }
	  });
      
      // change listener for expression radio buttons
      YUI().use('node', function (Y) {
    	  
    	  Y.one(document.getElementById('expressionBool.positive')).delegate('click', function (e) {
    	    Y.one("#expressionInputField").show();
    	    Y.one("#expressionInputFieldLabel").show();
    	    Y.one("#expressionCalcContainer").show();
    	  }, 'input[type=radio]');
    	  
    	  Y.one(document.getElementById('expressionBool.negative')).delegate('click', function (e) {
    		  Y.one("#expressionInputField").hide();
    		  Y.one("#expressionInputFieldLabel").hide();
    		  Y.one("#expressionCalcContainer").hide();
    		  
    		  var targetTextArea = document.getElementById("expressionInputField");
    		  targetTextArea.value = "";
    		  
      	  }, 'input[type=radio]');
    	  
    	  // Add attributes to the text area throught the gui
          Y.all('.expressionButton').each(function(node) {
      	     node.on('click', function(e) {
      	        e.preventDefault();
      	        
      	        var attrName = node.get('value'); 
      	        var formattedAttrName = attrName;
      	        var targetTextArea = document.getElementById("expressionInputField");
      	        
      	        that.insertTextAtCursor(targetTextArea, formattedAttrName)
      	        
      	     });
      	  });
          
          // Add operators to the text area through the gui
          Y.all('.expressionOperatorButton').each(function(node) {
       	     node.on('click', function(e) {
       	        e.preventDefault();
       	        
       	        var operator = " " + node.get('value').replace("'", "") + " "; 
       	        var targetTextArea = document.getElementById("expressionInputField");
       	        
       	        that.insertTextAtCursor(targetTextArea, operator)
       	        
       	     });
       	  });
      });
    },
    // Inserts text from an element into the expression field
    insertTextAtCursor : function(el, text) {
	    var val = el.value, endIndex, range;
	    if (typeof el.selectionStart != "undefined" && typeof el.selectionEnd != "undefined") {
	        endIndex = el.selectionEnd;
	        el.value = val.slice(0, el.selectionStart);
	        el.value += text;
	        el.value += val.slice(endIndex);
	        el.selectionStart = el.selectionEnd = endIndex + text.length + 1; // +1 accounts for the added space
	    } else if (typeof document.selection != "undefined" && typeof document.selection.createRange != "undefined") {
	        el.focus();
	        range = document.selection.createRange();
	        range.collapse(false);
	        range.text = text;
	        range.select();
	    }
	},
    updateMdField : function(fieldMap)
    {
      var fieldId = fieldMap['mdField.componentId'];
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(html)
        {
          that._fieldFormDialog.hide();
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
          
          if(node.data.nodeType === that.constructor.GROUP_NODE)
          {
            node.setHtml(that.getGroupNodeHTML(field.toString()));
          }
          else
          {
            node.setHtml(field.toString());
          }
        }
      });
      
      com.runwaysdk.system.metadata.MdField.get(request, fieldId);
    },
    createMdField : function(fieldMap)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(newFieldId)
        {
          that._fieldFormDialog.hide();
          that._postCreateMdField(newFieldId);
        }
      });
      
      // manually set the MdForm id because it is not in the actual form.
      fieldMap['mdFormId'] = this._currentMdFormId;

      return request;
    },
    /**
     * A field can be created in one of three ways, so different post create
     * actions are required. These says are:
     * 
     * 1. created normally within the fields tab
     * 2. created as a group beneath the form
     * 3. created as a group beneath another group
     */
    _postCreateMdField : function(fieldId)
    {
      if(this._selectedNode !== null && this._selectedNode.data.nodeType === this.constructor.GROUP_NODE)
      {
        // this field was created under a group so move it there and refresh the tree
        var that = this;
        var request = new MDSS.Request({
          onSuccess : function(){
            that.fetchFormFields(true);
          }
        });
        
        var groupId = this._selectedNode.data.fieldId;
        this._MdFormUtil.addToGroup(request, groupId, fieldId);
      }
      else
      {
        // created under the form or within the fields tab. Just refresh the tree
        this.fetchFormFields(true);
      }
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
        onSuccess : function(formTree){
          var formTreeObj = Mojo.Util.toObject(formTree);
        
          that.updateFields(formTreeObj);
          
          if(refreshTree)
          {
            that.refreshWorkflowTree(formTreeObj);
          }
        }        
      });
      
      this._MdFormUtil.getFormTree(request, id);
    },
    clearTree : function()
    {
      if(this._tree !== null)
      {
        this._tree.removeChildren(this._tree.getRoot());
      }
    },
    refreshWorkflowTree : function(formTreeObj)
    {
      this.clearTree();
      
      // grab the form's display label as the new quasi-root
      var formNode = new YAHOO.widget.HTMLNode({
        html:formTreeObj.label,
        formId:this._currentMdFormId,
        nodeType:formTreeObj.nodeType} , this._tree.getRoot());

      var groups = [];
      this._recurseFieldsForTree(formNode, formTreeObj.fields, groups);
      
      this._tree.render();
      
      formNode.expand();
    },
    /*
    _nodeExpanded : function(node)
    {
      // expand all GROUP children
      var children = node.children;
      for(var i=0; i<children.length; i++)
      {
        var child = children[i];
        if(child.data.nodeType === this.constructor.GROUP_NODE)
        {
          var dt = document.createElement('dt');
          dt.innerHTML = ' OO ';
          child.getContentEl().parentNode.appendChild(dt);
        }
      }
    },
    */
    _recurseFieldsForTree : function(parent, fields, groups)
    {
      for (var i = 0; i < fields.length; i++)
      {
        var field = fields[i];
        
        if(field.nodeType === this.constructor.GROUP_NODE)
        {
          // recurse into the group and add a drop target
          // to add to the group.
          var html = this.getGroupNodeHTML(field.label);
          var node = new YAHOO.widget.HTMLNode({
            html:html,
            nodeType:field.nodeType,
            fieldId:field.id}, parent); 

          groups.push(node);

          this._recurseFieldsForTree(node, field.fields, groups);
        }
        else
        {
          new YAHOO.widget.HTMLNode({
            html:field.label,
            nodeType:field.nodeType,
            fieldId:field.id}, parent);
        }
      }
    },
    /**
     * Clears a single field by removing it from the tree and field list.
     */
    clearField : function(fieldId)
    {
      // remove the field from the tree and append any group children
      // to the group's parent
      var node = this._tree.getNodeByProperty('fieldId', fieldId);
      if(node.data.nodeType === this.constructor.GROUP_NODE)
      {
        var parent = node.parent;
        var children = node.children;
        for(var i=0, len=children.length; i<len; i++)
        {
          var child = children[i];
          this._tree.popNode(child);
          parent.appendChild(child);
        }

        this._tree.removeNode(node, true);
        this.fetchFormFields();
      }
      else
      {
        // remove the field from list. Note that groups will have no LI and no ListItem
        var li = document.getElementById(fieldId);
        var item = this._fieldList.getItemByLI(li);
        this._fieldList.removeItem(item);
        this._tree.removeNode(node, true);
      }
    },
    /**
     * Updates the list of fields and collapses the tree structure to
     * a linear list and removes the group nodes.
     */
    updateFields : function(formTreeObj){
      
      // clear any prior field items
      this._fieldList.clearItems();
    
      this._recurseFieldsForList(formTreeObj.fields);

      var fieldDiv = document.getElementById(this.constructor.FORM_ITEM_ROW);
      this._fieldList.render(fieldDiv);
    },
    _recurseFieldsForList : function(fields)
    {
      for (var i = 0; i < fields.length; i++)
      {
        var field = fields[i];
        
        if(field.nodeType === this.constructor.GROUP_NODE)
        {
          this._recurseFieldsForList(field.fields);
        }
        else
        {
          var fieldItem = new FieldItem(field.id, field.label);
          var listItem = this._Factory.newListItem(fieldItem);
          listItem.getEl().setId(fieldItem.getId());
          
          var deleteLink = this._Factory.newElement("a");
          deleteLink.addClassName("form-item-row-delete edit-mode-functionality");
          deleteLink.setInnerHTML("Delete");
          deleteLink.setId(fieldItem.getId());
          
          listItem.appendChild(deleteLink);
          
          this._fieldList.addItem(listItem);
        }
      }
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
          
          that.fetchFormFields(true);
        }
      });
      
      return request;
    },
    _updateListener : function(form)
    {
      var that = this;
      var display = form['form.displayLabel'];
      var request = new MDSS.Request({
        onSuccess : function(html)
        {
          var executable = MDSS.util.extractScripts(html);
          var pureHTML = MDSS.util.removeScripts(html);
          document.getElementById(that.constructor.FORM_CONTENT_BOX).innerHTML = pureHTML;
          eval(executable);
          
          that._tree.getRoot().children[0].setHtml(display);
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
    requestExport : function()
    {
      document.getElementById('export_mdFormId').value = this._currentMdFormId;
      
      var form = document.getElementById('exportDefinition');
      form.submit();      
    },
    confirmDeleteForm : function(e)
    {
      var that = this;
      var request = new MDSS.Request({        
    	onMdFormHasInstancesException : function(e)
    	{
    	  new MDSS.ErrorModal(e.getLocalizedMessage());
    	},
    	onConfirmDeleteMdFormException : function(e)
        {
          var wrapperDiv = that._Factory.newElement('div');
          
          var upperDiv = that._Factory.newElement('div');
          upperDiv.addClassName('alertbox modalAlertBox');

          var message = that._Factory.newElement('span');
          message.setInnerHTML(e.getLocalizedMessage());
          upperDiv.appendChild(message);

          // yes/no buttons
          var lowerDiv = that._Factory.newElement('div');
          
          var confirmButton = that._Factory.newElement('button', {id:'confirmDeleteButton'});
          confirmButton.setInnerHTML(MDSS.localize("Confirm"));
          
          var cancelButton = that._Factory.newElement('button', {id:'cancelDeleteButton'});
          cancelButton.setInnerHTML(MDSS.localize("Cancel"));
          
          lowerDiv.appendChild(confirmButton);
          lowerDiv.appendChild(cancelButton);
          
          wrapperDiv.appendChild(upperDiv);
          wrapperDiv.appendChild(lowerDiv);
          
          if(that._confirmDeleteDialog !== null)
          {
            that._confirmDeleteDialog.setClose(false);
            that._confirmDeleteDialog.setInnerHTML("");
            that._confirmDeleteDialog.appendChild(wrapperDiv);
            that._confirmDeleteDialog.show();
          }
          else
          {
            that._confirmDeleteDialog = that._Factory.newDialog('', {close: false, modal:true});
            that._confirmDeleteDialog.appendChild(wrapperDiv);
            that._confirmDeleteDialog.render();
          }
          YAHOO.util.Event.on(that.constructor.CONFIRM_DELETE_BUTTON, 'click', that.deleteForm, null, that);
          YAHOO.util.Event.on(that.constructor.CANCEL_DELETE_BUTTON, 'click', that.cancelDeleteDialog, null, that);
        }
      });
      
      this._MdFormUtil.confirmDeleteForm(request, this._currentMdFormId);
    },
    confirmDeleteMdFieldHandler : function(e)
    {
      var fieldId = e.currentTarget.get('id');
      this.confirmDeleteMdField(fieldId);
    },
    confirmDeleteMdField : function(fieldId)
    {
      var that = this;
      var request = new MDSS.Request({
      	onConfirmDeleteMdFieldException : function(e)
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
          confirmButton.setInnerHTML(MDSS.localize("Confirm"));
          
          var cancelButton = that._Factory.newElement('button', {id:'cancelDeleteButton'});
          cancelButton.setInnerHTML(MDSS.localize("Cancel"));
          
          var lowerDiv = that._Factory.newElement('div');
          lowerDiv.appendChild(confirmButton);
          lowerDiv.appendChild(cancelButton);
          
          wrapperDiv.appendChild(upperDiv);
          wrapperDiv.appendChild(lowerDiv);
          
          if(that._confirmDeleteDialog !== null)
          {
            that._confirmDeleteDialog.setInnerHTML("");
            that._confirmDeleteDialog.appendChild(wrapperDiv);
            that._confirmDeleteDialog.show();
          }
          else
          {
            that._confirmDeleteDialog = that._Factory.newDialog('', {close: false, modal:true});
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
      this._confirmDeleteDialog.hide();
    },
    editField : function(fieldId)
    {
      var that = this;
      var currentFormId = this._currentMdFormId;
      var request = new MDSS.Request({
        onSuccess: function(html){
          that.editMdFieldDialog(html);
        }
      });
      
      this._MdFormAdminController.editMdField(request, fieldId, false, currentFormId);
    },
    editFieldHandler : function(e)
    {
      var type = e.target.get('nodeName');
      if (type !== 'A') {
        var fieldId = e.currentTarget.get('id');
        this.editField(fieldId);
      }
    },
    editMdFieldDialog : function(html)
    {
      var that = this;
      var executable = MDSS.util.extractScripts(html);
      var pureHTML = MDSS.util.removeScripts(html);
      
      if(this._fieldFormDialog !== null)
      {
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.show();
      }
      else
      {
        this._fieldFormDialog = this._Factory.newDialog('', {close: false, modal: true});
        this._fieldFormDialog.getContentEl().addClassName("form-dialog-scroll");
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.render();
      }
      
      if(document.getElementById('expressionInputField')){
          
    	  // open the expression field ui if expression is active
    	  if(document.getElementById("expressionBool.positive").checked){
    		  YUI().use('node', function (Y) {
    			Y.one("#expressionInputField").show();
    			Y.one("#expressionInputFieldLabel").show();
    			Y.one("#expressionCalcContainer").show();
    		  });
    	  }
          
    	  this.setExpressionEventHandlers();
      }
      eval(executable);
    },
    cancelMdField : function(fieldMap)
    {
      if (fieldMap["mdField.isNew"] === "true") {
          this._fieldFormDialog.hide();
      }
      else {
        var that = this;
        var request = new MDSS.Request({
          onSuccess: function(html){
            that._fieldFormDialog.hide();
          }
        });
        return request;
      }
    },
    deleteField : function(e, fieldId)
    {
      this._confirmDeleteDialog.hide();
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
      this._confirmDeleteDialog.hide();
      var that = this;
      var request = new MDSS.Request({
       onSuccess : function(html)
       {
         that._fieldList.clearItems();
         that.existingForms();
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
         that.clearTree();
         that._Y.one('#'+that.constructor.FORM_CONTENT).setStyle('visibility', 'visible');
         that._Y.one('#'+that.constructor.TABBED_FORM_BOX).setStyle('visibility', 'hidden');
       }
      });
      that._MdFormAdminController.newInstance(request);
    },
    importForm : function()
    {
      if(this._uploadModal == null)
      {
        var formId = 'definitionUploadForm';
        var action = 'dss.vector.solutions.form.MdFormAdminController.importDefinition.mojo';

        var html = MDSS.localize('File_Upload_Status')+":<br />";
        html += "<iframe name='definitionIframe' id='definitionIframe' style='height:65px; width:350px; margin-bottom: 15px'></iframe>";
        html += "<form action='"+action+"' enctype='multipart/form-data' target='definitionIframe' id='"+formId+"' method='post'>";
        html += "<input type='file' name='definition' id='definitionId'/><br />";        
        html += "<input type='submit' name='import' id='form-import-button' value='"+MDSS.localize('Submit')+"' />"
        html += "</form>";

        this._uploadModal = new YAHOO.widget.Panel("uploadDefinitionModal", {
          width:"400px",
          height: "400px",
          fixedcenter:true,
          close: true,
          draggable:false,
          zindex:8,
          modal:true,
          visible:true
        });
        this._uploadModal.subscribe('hide', Mojo.Util.bind(this, this.existingForms));

        // wrap content in divs
        var outer = document.createElement('div');

        var header = document.createElement('div');
        header.innerHTML = '<h3>'+MDSS.localize('Import')+'</h3><hr />';
        outer.appendChild(header);

        var contentDiv = document.createElement('div');
        YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
        contentDiv.innerHTML = html;
        outer.appendChild(contentDiv);

        this._uploadModal.setBody(outer);
        this._uploadModal.render(document.body);

        YAHOO.util.Event.on('definitionId', 'change', this._clearDefinitionFrame, null, this);      
      }
      else
      {
        this._clearDefinitionFrame();
        this._uploadModal.show();
      }
    },
    _clearDefinitionFrame : function()
    {
      var frame = document.getElementById('definitionIframe');
      frame.contentDocument.firstChild.innerHTML = '';    
    }
  }
});

/**
 * Primary class to handle control flow in the UI.
 */
Mojo.Meta.newClass('dss.vector.solutions.GridFieldAdmin', 
{
  Constants : {
    AVAILABLE_FIELDS : 'gridAvailableFields',
    FORM_ITEM_ROW : 'gridFormItemRow',
    CANCEL_DELETE_BUTTON : 'gridCancelDeleteButton',
    CONFIRM_DELETE_BUTTON : 'gridConfirmDeleteButton'    
  },
  Instance : {
    initialize : function(compositeFieldId)
    {
      UI.Manager.setFactory("YUI3");
      
      this._Factory = UI.Manager.getFactory();
      this._MdFormAdminController = dss.vector.solutions.form.MdFormAdminController;
      this._MdFormUtil = dss.vector.solutions.generator.MdFormUtil;  
      this._Y = YUI().use('*'); // YUI3 reference          
      
      // Dialog for selecting the available MdFields
      this._compositeFieldId = compositeFieldId;
      this._fieldsDialog = null;      
      this._fieldFormDialog = null;
      this._confirmDeleteDialog = null;
      this._fieldList = this._Factory.newList("Field List", {id: "fieldList"});

      // Action handlers
      var createMdFieldB = Mojo.Util.bind(this, this.createMdField);
      this._MdFormAdminController.setCreateCompositeFieldListener(createMdFieldB);
      
      var cancelMdFieldB = Mojo.Util.bind(this, this.cancelMdField);
      this._MdFormAdminController.setCancelCompositeFieldListener(cancelMdFieldB);
      
      var updateMdFieldB = Mojo.Util.bind(this, this.updateMdField);
      this._MdFormAdminController.setUpdateCompositeFieldListener(updateMdFieldB);      
    },
    render : function()
    {
      YAHOO.util.Event.on(this.constructor.AVAILABLE_FIELDS, 'click', this.availableFields, null, this);
      this._Y.one('#'+this.constructor.FORM_ITEM_ROW).delegate('click', this.confirmDeleteMdFieldHandler, 'a.grid-form-item-row-delete', this);
      this._Y.one('#'+this.constructor.FORM_ITEM_ROW).delegate('click', this.editFieldHandler, 'li', this);

      this.refreshFields();
    },
    /**
     * Makes a request to display all available MdField types for the Form Generator.
     */
    availableFields : function()
    {
      if(this._fieldsDialog !== null)
      {
        this._fieldsDialog.show(); // FIXME add show/hide to widgets
      }
      else
      {
        // first request, so create the dialog with the available field options.
        this._fieldsDialog = this._Factory.newDialog('', {modal:true});
        
        var request = new MDSS.Request({
          that : this,
          onSuccess : function(html){
            var executable = MDSS.util.extractScripts(html);
            var pureHTML = MDSS.util.removeScripts(html);
            
            this.that.createAvailableFieldsDialog(pureHTML);            
          }
        });
        
        this._MdFormAdminController.availableCompositeFields(request);
      }
    },    
    createAvailableFieldsDialog : function(html)
    {
      this._fieldsDialog.setInnerHTML(html);
      this._fieldsDialog.render();
      
      // hook the click handler to the dialog to detect which field type was selected.
      var id = this._fieldsDialog.getContentEl().getId();
      this._Y.one('#'+id).delegate('click', this.newFieldHandler, 'li', this);
    },
    newField : function(mdFieldType)
    {
      var that = this;
      var currentFormId = this._currentMdFormId;
      
      var request = new MDSS.Request({
        onSuccess : function(html){
          that.newMdFieldDialog(html);
        }
      });
      
      this._MdFormAdminController.newMdField(request, mdFieldType, true, currentFormId);
    },
    /**
     * Make a request for a new instance of an MdField.
     */
    newFieldHandler : function(e)
    {
      // this is called via the fields tab so clear any notion of
      // the currently selected field. This is to avoid confusing adding
      // a new group (a field) via the workflow tree.
      this._selectedNode = null;
    
      var mdFieldType = e.currentTarget.get('id');
      this.newField(mdFieldType);
    },
    newMdFieldDialog : function(html)
    {
      // the fields dialog might not have been rendered if we're adding 
      // a new group field.
      if(this._fieldsDialog !== null)
      {
        this._fieldsDialog.hide();
      }
    
      var executable = MDSS.util.extractScripts(html);
      var pureHTML = MDSS.util.removeScripts(html);
      
      if(this._fieldFormDialog !== null)
      {
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.show();
      }
      else
      {
        this._fieldFormDialog = this._Factory.newDialog('', {close: false, modal:true});
        this._fieldFormDialog.getContentEl().addClassName("form-dialog-scroll");
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.render();
      }
      
      eval(executable);
    },
    createMdField : function(params)
    {
      var request = new MDSS.Request({
        that : this,
        onSuccess : function(newFieldId)
        {
          this.that._fieldFormDialog.hide();
          
          this.that.refreshFields();
        }
      });
      
      params['mdCompositeFieldId'] = this._compositeFieldId;
      
      return request;
    },
    refreshFields : function()
    {
      var request = new MDSS.Request({
        that : this,
        onSuccess : function(fields){
          this.that.updateFields(Mojo.Util.toObject(fields));          
        }        
      });
      
      this._MdFormUtil.getFieldsForComposite(request, this._compositeFieldId);
    },
    updateFields : function(fields){
        
      // clear any prior field items
      this._fieldList.clearItems();
      
      for (var i = 0; i < fields.length; i++)
      {
        var field = fields[i];
          
        var fieldItem = new FieldItem(field.id, field.label);
        
        var listItem = this._Factory.newListItem(fieldItem);
        listItem.getEl().setId(fieldItem.getId());
            
        var deleteLink = this._Factory.newElement("a");
        deleteLink.addClassName("grid-form-item-row-delete edit-mode-functionality");
        deleteLink.setInnerHTML("Delete");
        deleteLink.setId(fieldItem.getId());
            
        listItem.appendChild(deleteLink);
            
        this._fieldList.addItem(listItem);
      }

      var fieldDiv = document.getElementById(this.constructor.FORM_ITEM_ROW);
      this._fieldList.render(fieldDiv);
    },
    cancelMdField : function(fieldMap)
    {
      if (fieldMap["mdField.isNew"] === "true") {
          this._fieldFormDialog.hide();
      }
      else 
      {
        var request = new MDSS.Request({
          that : this,
          onSuccess: function(html){
            this.that._fieldFormDialog.hide();
          }
        });
        return request;
      }
    },
    confirmDeleteMdFieldHandler : function(e)
    {
      var fieldId = e.currentTarget.get('id');
      this.confirmDeleteMdField(fieldId);
    },
    confirmDeleteMdField : function(fieldId)
    {
      var request = new MDSS.Request({
      that : this,
        onFailure : function(e)
        {
          var wrapperDiv = this.that._Factory.newElement('div');
          
          var upperDiv = this.that._Factory.newElement('div');
          upperDiv.addClassName('alertbox modalAlertBox');

          var message = this.that._Factory.newElement('span');
          message.setInnerHTML(e.getLocalizedMessage());
          upperDiv.appendChild(message);

          // yes/no buttons
          var lowerDiv = this.that._Factory.newElement('div');
          lowerDiv.addClassName('alertbox modalAlertBox');
          
          var confirmButton = this.that._Factory.newElement('button', {id:this.that.constructor.CONFIRM_DELETE_BUTTON});
          confirmButton.setInnerHTML("Confirm");
          
          var cancelButton = this.that._Factory.newElement('button', {id:this.that.constructor.CANCEL_DELETE_BUTTON});
          cancelButton.setInnerHTML("Cancel");
          
          var lowerDiv = this.that._Factory.newElement('div');
          lowerDiv.appendChild(confirmButton);
          lowerDiv.appendChild(cancelButton);
          
          wrapperDiv.appendChild(upperDiv);
          wrapperDiv.appendChild(lowerDiv);
          
          if(this.that._confirmDeleteDialog !== null)
          {
            this.that._confirmDeleteDialog.setInnerHTML("");
            this.that._confirmDeleteDialog.appendChild(wrapperDiv);
            this.that._confirmDeleteDialog.show();
          }
          else
          {
            this.that._confirmDeleteDialog = this.that._Factory.newDialog('', {close: false, modal:true});
            this.that._confirmDeleteDialog.appendChild(wrapperDiv);
            this.that._confirmDeleteDialog.render();
          }
          YAHOO.util.Event.on(this.that.constructor.CONFIRM_DELETE_BUTTON, 'click', this.that.deleteField, fieldId, this.that);
          YAHOO.util.Event.on(this.that.constructor.CANCEL_DELETE_BUTTON, 'click', this.that.cancelDeleteDialog, null, this.that);
        }
      });
      
      this._MdFormUtil.confirmDeleteCompositeField(request, this._compositeFieldId, fieldId);
    },
    deleteField : function(e, fieldId)
    {
      this._confirmDeleteDialog.hide();
      var request = new MDSS.Request({
       that : this,
       onSuccess : function(html)
       {
         this.that.refreshFields();
       }
      });

      this._MdFormAdminController.deleteCompositeField(request, fieldId);
    },    
    cancelDeleteDialog : function(e)
    {
      this._confirmDeleteDialog.hide();
    },
    editField : function(fieldId)
    {
      var that = this;
      var request = new MDSS.Request({
        onSuccess: function(html){
          that.editMdFieldDialog(html);
        }
      });
      
      this._MdFormAdminController.editMdField(request, fieldId, true);
    },
    editFieldHandler : function(e)
    {
      var type = e.target.get('nodeName');
      if (type !== 'A') {
        var fieldId = e.currentTarget.get('id');
        this.editField(fieldId);
      }
    },
    editMdFieldDialog : function(html)
    {
      var executable = MDSS.util.extractScripts(html);
      var pureHTML = MDSS.util.removeScripts(html);
      
      if(this._fieldFormDialog !== null)
      {
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.show();
      }
      else
      {
        this._fieldFormDialog = this._Factory.newDialog('', {close: false, modal: true});
        this._fieldFormDialog.getContentEl().addClassName("form-dialog-scroll");
        this._fieldFormDialog.setInnerHTML(pureHTML);
        this._fieldFormDialog.render();
      }
      
      eval(executable);
    },    
    updateMdField : function(fieldMap)
    {
      var fieldId = fieldMap['mdField.componentId'];
      var request = new MDSS.Request({
      that : this,
        onSuccess : function(html)
        {
          this.that._fieldFormDialog.hide();
          this.that.refreshFields();
        }
      });
      
      return request;
    }    
  }
});

})();