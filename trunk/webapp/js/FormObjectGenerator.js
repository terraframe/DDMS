/*
 * Manages control flow for the FormObject generator.
 */
(function(){

var UI = Mojo.Meta.alias(Mojo.UI_PACKAGE+'*');
var FIELD = Mojo.Meta.alias(Mojo.FORM_PACKAGE.FIELD+'*');

/**
 * Primary class to handle control flow in the UI.
 */
Mojo.Meta.newClass('dss.vector.solutions.FormObjectGenerator', {
  Constants : {
    NEW_INSTANCE_COMMAND : 'newInstanceCommand',
    TABLE_CONTAINER : 'tableContainer',
    FORM_CONTAINER : 'formContainer',
    VIEW_ALL_COMMAND : 'viewAllCommand',
    EXCEL_BUTTONS : 'excelButtons'
  },
  Instance : {
    initialize : function(mdFormId, mdClassType){
      this._mdFormId = mdFormId;
      this._mdClassType = mdClassType;
      this._parentDiv = null;
      
      this._FormObjectController = dss.vector.solutions.form.FormObjectController;
      
      UI.Manager.setFactory("YUI3");
      this._factory = UI.Manager.getFactory();
      
      var col = this._factory.newColumn({
        key:Mojo.Util.generateId()+'_view',
        label:'',
        formatter: Mojo.Util.bind(this, this.viewColumnFormatter)
      });
      
      this._table = this._factory.newDataTable(this._mdClassType, [col]);
      this._table.setTypeFormatter('com.runwaysdk.system.metadata.MdAttributeDate', Mojo.Util.bind(this, this.dateColumnFormatter));
      
      this._Y = YUI().use('*'); // YUI3 reference
      this._newInstanceCommand = this._Y.one('#'+this.constructor.NEW_INSTANCE_COMMAND);
      this._newInstanceCommand.on('click', this.newInstance, this);
      
      this._viewAllCommand = this._Y.one('#'+this.constructor.VIEW_ALL_COMMAND);
      this._viewAllCommand.on('click', this.viewAllInstance, this);
      
      // Reference to the current form object that is being modified/created.
      // This will be null when viewing all objects.
      this._formObject = null;
      
      this._WebFormObject = com.runwaysdk.form.web.WebFormObject;
      
      this._formContainer = this._Y.one('#'+this.constructor.FORM_CONTAINER);
      this._tableContainer = this._Y.one('#'+this.constructor.TABLE_CONTAINER);
      this._excelButtons = this._Y.one('#'+this.constructor.EXCEL_BUTTONS);
      
      this._tableContainer.delegate('click', this.viewInstance, 'span.generatedViewCommand', this);
    },
    /**
     * Formats dates according to the DDMS specification.
     */
    dateColumnFormatter : function(o){
      return MDSS.Calendar.getLocalizedString(o.data[o.field]);
    },
    /**
     * Formats the view column by creating a link that contains a Runway object id for
     * dereferencing.
     */
    viewColumnFormatter : function(o){
      return '<span id="'+o.data.id+'" class="generatedViewCommand">'+MDSS.localize('View')+'</span>';
    },
    clearFormContainer : function(){
      // clear the form container
      this._formContainer.setContent(''); 
    },
    renderView : function(formObjectJSON){
      this.clearFormContainer();
      this.hideAllInstance();
      this._viewAllCommand.show();
    
      this._formObject = this._WebFormObject.parseFromJSON(formObjectJSON);
    
      var div = this._factory.newElement('div');
      var dl = this._factory.newElement('dl');
      
      div.appendChild(dl);
      
      var fields = this._formObject.getFields();
      for(var i=0, len=fields.length; i<len; i++)
      {
        var field = fields[i];
      
        if(!field.isReadable() || 
          field instanceof FIELD.WebHeader ||
          field instanceof FIELD.WebBreak ||
          field instanceof FIELD.WebComment)
        {
          continue;
        }
      
        var dt = this._factory.newElement('dt');
        var dd = this._factory.newElement('dd');
        dl.appendChild(dt);
        dl.appendChild(dd);
        
        var value = Mojo.Util.isValid(field.getValue()) ? field.getValue() : '';
        if(field instanceof FIELD.WebDate)
        {
          value = MDSS.Calendar.getLocalizedString(value);
        }
        
        dt.setInnerHTML(field.getFieldMd().getDisplayLabel());
        dd.setInnerHTML(value);
      }
      
      // edit
      var editBtn = this._factory.newElement('button');
      editBtn.setInnerHTML(MDSS.localize('Edit'));
      div.appendChild(editBtn);
      
      editBtn.getImpl().on('click', this.editInstance, this);
      
      // delete
      var deleteBtn = this._factory.newElement('button');
      deleteBtn.setInnerHTML(MDSS.localize('Delete'));
      div.appendChild(deleteBtn);

      deleteBtn.getImpl().on('click', this.deleteInstance, this);
      
      div.render('#'+this._formContainer.get('id')); 
    },
    /**
     * Renders the create and upate form.
     */
    renderForm : function(formObjectJSON){
      this.clearFormContainer();
      this.hideAllInstance();
      this._viewAllCommand.hide();
    
      this._formObject = this._WebFormObject.parseFromJSON(formObjectJSON);
    
      var formEl = this._factory.newElement('form');
      formEl.setAttribute('method', 'POST');
      formEl.setAttribute('id', this._formObject.getId());
      formEl.setAttribute('name', this._formObject.getFormName());
    
      var header = this._factory.newElement('h2');
      header.setInnerHTML(this._formObject.getMd().getDisplayLabel());
      formEl.appendChild(header);
    
      var dl = this._factory.newElement('dl');
      formEl.appendChild(dl);
    
      var fields = this._formObject.getFields();
      for(var i=0, len=fields.length; i<len; i++)
      {
        // FIXME use Input classes and better integration with Fields
        var field = fields[i];
        if(!field.isReadable())
        {
          continue; 
        }
                
        var dt = this._factory.newElement('dt');
        var dd = this._factory.newElement('dd');
				
        dl.appendChild(dt);
        dl.appendChild(dd);
				
        var value = Mojo.Util.isValid(field.getValue()) ? field.getValue() : '';
        
        // display and annotation fields
        if(field instanceof FIELD.WebHeader)
        {
          var h = this._factory.newElement('h2');
          h.setInnerHTML(value);
          dd.appendChild(h);
          continue;
        }
        else if(field instanceof FIELD.WebBreak)
        {
          dd.setInnerHTML('<br />');
          continue;
        }
        else if(field instanceof FIELD.WebComment)
        {
          dd.setInnerHTML(value);
          continue;
        }
        
        
        // attribute fields
        var labelTxt;
        if(field.getFieldMd().isRequired())
        {
          // Required star styled via CSS
          labelTxt = '* '+field.getFieldMd().getDisplayLabel();
        }
        else
        {
          labelTxt = field.getFieldMd().getDisplayLabel();
        }
        
        // set the title to the description or the display label
        // if the description is empty or null
        var desc = field.getFieldMd().getDescription();
        if(!Mojo.Util.isString(desc) || desc.trim().length === 0)
        {
          desc = field.getFieldMd().getDisplayLabel();
        }
        
        var labelEl = this._factory.newElement('label', {
          'for': field.getFieldName(),
          'title': desc
        });
        labelEl.setInnerHTML(labelTxt);
        dt.appendChild(labelEl);
        
        if(field instanceof FIELD.WebBoolean)
        {
          var radioT = this._factory.newElement('input', {
            'type':'radio',
            'name':field.getFieldName(),
            'value':'true',
            'group':field.getFieldName()+'_G',
          });
          var tLabel = this._factory.newElement('span');
          tLabel.setInnerHTML(field.getFieldMd().getPositiveDisplayLabel());
          
          var radioF = this._factory.newElement('input', {
            'type':'radio',
            'name':field.getFieldName(),
            'value':'false',
            'group':field.getFieldName()+'_G',
          });
          var fLabel = this._factory.newElement('span');
          fLabel.setInnerHTML(field.getFieldMd().getNegativeDisplayLabel());
          
          
          dd.appendChild(radioT);
          dd.appendChild(tLabel);
          dd.appendChild(radioF);
          dd.appendChild(fLabel);
        }
        else if(field instanceof FIELD.WebCharacter)
        {
          var input = this._factory.newElement('input', {
            'type':'text',
            'name':field.getFieldName(),
            'value':value,
            'maxlength':field.getFieldMd().getMaxLength(),
            'size':field.getFieldMd().getDisplayLength()
          });
          
          dd.appendChild(input);
        }
        else if(field instanceof FIELD.WebText)
        {
          var textArea = this._factory.newElement('textarea', {
            'name':field.getFieldName(),
            'rows':field.getFieldMd().getHeight(),
            'cols':field.getFieldMd().getWidth()
          });
          
          textArea.setInnerHTML(value);
          
          dd.appendChild(textArea);
        }
        else if(field instanceof FIELD.WebDec)
        {
          var input = this._factory.newElement('input', {
            'type':'text',
            'name':field.getFieldName(),
            'value':value
          });
          
          dd.appendChild(input);
        }
        else if(field instanceof FIELD.WebNumber)
        {
          var input = this._factory.newElement('input', {
            'type':'text',
            'name':field.getFieldName(),
            'value':value
          });
          
          dd.appendChild(input);
        }
        else if(field instanceof FIELD.WebDate)
        {
          value = MDSS.Calendar.getLocalizedString(value);
        
          var input = this._factory.newElement('input', {
            'type':'text',
            'name':field.getFieldName(),
            'value':value
          });
          MDSS.Calendar.addCalendarListeners(input.getRawEl());
          dd.appendChild(input);
        }
        else
        {
          var msg = 'The field ['+field+'] is not recognized.';
          throw new com.runwaysdk.Exception(msg);
        }
				
				// this is so we can place error info spatially
				var attr = field.getFieldMd();
				if (attr.getDefiningMdAttribute)
				{
          var errorContainer = this._factory.newElement('span');
          var attrId = field.getFieldMd().getDefiningMdAttribute();
          errorContainer.setId(attrId);
          errorContainer.addClassName('alertbox');// modalAlertBox');
					errorContainer.setStyle('margin-left', '20px');
          errorContainer.setStyle('visibility', 'hidden');
					dd.appendChild(errorContainer);
				}
      }
      
      // Add the action buttons
      if(this._formObject.isNewInstance())
      {
        // create
        var createBtn = this._factory.newElement('button');
        createBtn.setInnerHTML(MDSS.localize('Create'));
        formEl.appendChild(createBtn);
        
        createBtn.getImpl().on('click', this.createInstance, this);
        
        // cancel
        var cancelBtn = this._factory.newElement('button');
        cancelBtn.setInnerHTML(MDSS.localize('Cancel'));
        formEl.appendChild(cancelBtn);

        cancelBtn.getImpl().on('click', this.cancelInstance, this);
      }
      else
      {
        // update
        var updateBtn = this._factory.newElement('button');
        updateBtn.setInnerHTML(MDSS.localize('Update'));
        formEl.appendChild(updateBtn);
        
        updateBtn.getImpl().on('click', this.updateInstance, this);
        
        // cancel
        var cancelBtn = this._factory.newElement('button');
        cancelBtn.setInnerHTML(MDSS.localize('Cancel'));
        formEl.appendChild(cancelBtn);

        cancelBtn.getImpl().on('click', this.cancelInstance, this);
      }
      
      formEl.render('#'+this._formContainer.get('id'));  
    },
    /**
     * Updates the values on the FormObject with the current values
     * of the HTML form.
     */
    _updateValues : function()
    {
      var values = new com.runwaysdk.structure.HashMap(Mojo.Util.collectFormValues(this._formObject.getId()));
      var fields = this._formObject.getFields();
      
      for(var i=0, len=fields.length; i<len; i++)
      {
        var field = fields[i];
        var name = field.getFieldName();
        
        if(values.containsKey(name))
        {
          var value = values.get(name);
          field.setValue(value);
        }
      }
    },
    viewInstance : function(e){
      e.preventDefault();
      
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
          that.renderView(formObjectJSON);
        }
      });
      
      var id = e.target.get('id');
      this._FormObjectController.viewInstance(request, this._mdFormId, id);    
    },
    deleteInstance : function(e){
      e.preventDefault();  // prevent a synchronous form submit
      
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
          that._formObject = null;
          that.clearFormContainer();
          that.viewAllInstance();
        }
      });
      
      this._FormObjectController.deleteInstance(request, this._mdFormId, this._formObject.getDataId());    
    },
    /**
     * Updates the FormObject instance.
     */
    updateInstance : function(e){
      e.preventDefault(); // prevent a synchronous form submit
      
      this._updateValues();
      
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
          that.renderView(formObjectJSON);
        },
				onProblemExceptionDTO : function(e)
				{
          var problems = e.getProblems();
          
          for (var i = 0; i < problems.length; i++) {
            var p = problems[i];
            var attributeId = p.getAttributeId();

            var span = document.getElementById(attributeId);
            span.innerHTML = p.getLocalizedMessage();
						that._Y.one('#'+attributeId).setStyle('visibility', 'visible');
          }
				}
      });
      
      this._FormObjectController.updateInstance(request, this._formObject);    
    },
    /**
     * Creates the FormObject instances.
     */
    createInstance : function(e){
      e.preventDefault(); // prevent a synchronous form submit
      
      this._updateValues();
      
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
          that.renderView(formObjectJSON);
        }
      });
      
      this._FormObjectController.createInstance(request, this._formObject);
    },
    editInstance : function(e){
      e.preventDefault(); // prevent a synchronous form submit

      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
          that.renderForm(formObjectJSON);
        }        
      });
      
      this._FormObjectController.editInstance(request, this._mdFormId, this._formObject.getDataId());          
    },
    /**
     * Cancels the creation or update of a FormObject.
     */
    cancelInstance : function(e){
      e.preventDefault(); // prevent a synchronous form submit
      
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
        
          if(that._formObject.isNewInstance())
          {
            that._formObject = null;
            that.clearFormContainer();
            that.viewAllInstance();
          }
          else
          {
            that.renderView(formObjectJSON);
          }
        }        
      });
      
      this._FormObjectController.cancelInstance(request, this._formObject);
    },
    newInstance : function(){
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
          that.renderForm(formObjectJSON);
        }
      });
  
      this._FormObjectController.newInstance(request, this._mdFormId);
    },
    hideAllInstance : function(){
      this._newInstanceCommand.hide();
      this._tableContainer.hide();
      this._excelButtons.hide();
    },
    /**
     * Shows the datatable with all the instances of the current
     * type. Unrelated functionality is hidden.
     */
    viewAllInstance : function(){
      this.clearFormContainer();
      this._tableContainer.show();
      this._excelButtons.show();
      this._viewAllCommand.hide();
      
      if(this._table.isRendered())
      {
        this._table.resetDataTable();
      }
      else
      {
        this._table.render('#'+this._tableContainer.get('id'));
      }
      
      this._newInstanceCommand.show();
    },
    /**
     * The initial render shows all instances.
     */
    render : function(){
      this.viewAllInstance();
    }
  }
});

})();