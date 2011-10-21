/*
 * Manages control flow for the FormObject generator.
 */
(function(){

var UI = Mojo.Meta.alias(Mojo.UI_PACKAGE+'*');
var FIELD = Mojo.Meta.alias(Mojo.FORM_PACKAGE.FIELD+'*');

Mojo.Meta.newClass('dss.vector.solutions.NewInstanceEvent', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function (mdFormId) {
      this.$initialize();
  
      this._mdFormId = mdFormId;
    },    
    defaultAction : function() {
      var target = this.getTarget();
      
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
          target.renderFormWithJSON(formObjectJSON);
        }
      });
    
      dss.vector.solutions.form.FormObjectController.newInstance(request, this._mdFormId);
    }
  }
});

Mojo.Meta.newClass('dss.vector.solutions.ViewEvent', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function (id, mdFormId) {
      this.$initialize();
    
      this._id = id;
      this._mdFormId = mdFormId;
    },    
    defaultAction : function() {
      var target = this.getTarget();
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
          target.renderViewWithJSON(formObjectJSON);
        }
      });
        
      dss.vector.solutions.form.FormObjectController.viewInstance(request, this._mdFormId, this._id);          
    }
  }
});


Mojo.Meta.newClass('dss.vector.solutions.RenderViewEvent', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function (mdFormId) {
      this.$initialize();
      
      this._mdFormId = mdFormId;
    },    
    defaultAction : function() {
    }
  }
});

Mojo.Meta.newClass('dss.vector.solutions.RenderFormEvent', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function (mdFormId) {
      this.$initialize();
      
      this._mdFormId = mdFormId;
    },    
    defaultAction : function() {
    }
  }
});

Mojo.Meta.newClass('dss.vector.solutions.ViewParentEvent', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function (mdFormId) {
      this.$initialize();
      
      this._mdFormId = mdFormId;
    },    
    defaultAction : function() {
    }
  }
});

Mojo.Meta.newClass('dss.vector.solutions.EditEvent', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function (mdFormId, formObject) {
      this.$initialize();
    
      this._mdFormId = mdFormId;
      this._formObject = formObject;
    },    
    defaultAction : function() {
      var target = this.getTarget();
      
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
          target.renderFormWithJSON(formObjectJSON);
        }        
      });
        
      dss.vector.solutions.form.FormObjectController.editInstance(request, this._mdFormId, this._formObject.getDataId());          
    }
  }
});

Mojo.Meta.newClass('dss.vector.solutions.CancelEvent', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function (formObject) {
      this.$initialize();
  
      this._formObject = formObject;
    },    
    defaultAction : function() {        
      var target = this.getTarget();
        
      var request = new MDSS.Request({
        that : this,
        onSuccess : function(formObjectJSON){
          
          if(this.that._formObject.isNewInstance())
          {
            target.setFormObject(null);
            target.clearFormContainer();
            target.viewAllInstance();
          }
          else
          {
            target.renderViewWithJSON(formObjectJSON);
          }
        }        
      });
        
      dss.vector.solutions.form.FormObjectController.cancelInstance(request, this._formObject);
    }
  }
});

Mojo.Meta.newClass('dss.vector.solutions.DeleteEvent', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function (mdFormId, formObject) {
      this.$initialize();
  
      this._mdFormId = mdFormId;
      this._formObject = formObject;
    },    
    defaultAction : function() {        
      var request = new MDSS.Request({
      target : this.getTarget(),
        onSuccess : function(formObjectJSON){
          this.target.setFormObject(null);
          this.target.clearFormContainer();
          this.target.viewAllInstance();
        }
      });
        
      dss.vector.solutions.form.FormObjectController.deleteInstance(request, this._mdFormId, this._formObject.getDataId());          
    }
  }
});

Mojo.Meta.newClass('dss.vector.solutions.UpdateEvent', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function (formObject) {
      this.$initialize();
  
      this._formObject = formObject;
    },    
    getFormObject : function() {
      return this._formObject;
    },
    defaultAction : function() {                
      var request = new MDSS.Request({
        target : this.getTarget(),
        onSuccess : function(formObjectJSON)
        {
          this.target.renderViewWithJSON(formObjectJSON);
        },
        onProblemExceptionDTO : function(e)
        {
          this.target.renderProblems(e.getProblems());
        }
      });
      
      dss.vector.solutions.form.FormObjectController.updateInstance(request, this._formObject);    
    }
  }
});

Mojo.Meta.newClass('dss.vector.solutions.CreateEvent', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function (formObject) {
      this.$initialize();
  
      this._formObject = formObject;
    },    
    getFormObject : function() {
      return this._formObject;
    },    
    defaultAction : function() {
      var request = new MDSS.Request({
        target : this.getTarget(),
        onSuccess : function(formObjectJSON)
        {
          this.target.renderViewWithJSON(formObjectJSON);
        },
        onProblemExceptionDTO : function(e)
        {
          this.target.renderProblems(e.getProblems());
        }
      });
        
      dss.vector.solutions.form.FormObjectController.createInstance(request, this._formObject);
    }
  }
});

Mojo.Meta.newClass('dss.vector.solutions.ViewAllEvent', {
  Extends : Mojo.$.com.runwaysdk.event.CustomEvent,
  Instance : {
    initialize : function () {
      this.$initialize();
    },    
    defaultAction : function() {
      var target = this.getTarget();
      
      target.renderViewAll();      
    }
  }
});


/**
 * Primary class to handle control flow in the UI.
 */
Mojo.Meta.newClass('dss.vector.solutions.FormObjectGenerator', {
  Constants : {
    NEW_INSTANCE_COMMAND : 'NewInstanceCommand',
    TABLE_CONTAINER : 'TableContainer',
    FORM_CONTAINER : 'FormContainer',
    VIEW_ALL_COMMAND : 'ViewAllCommand',
    VIEW_PARENT_COMMAND : 'ViewParentCommand',
    EXCEL_BUTTONS : 'excelButtons',
    OPTION_BREAK : 'OptionBreak'
  },
  Instance : {
    initialize : function(prefix, mdFormId, mdClassType){
      this._mdFormId = mdFormId;
      this._mdClassType = mdClassType;
      this._parentDiv = null;
            
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
      this._newInstanceCommand = this._Y.one('#'+ prefix +this.constructor.NEW_INSTANCE_COMMAND);
      this._newInstanceCommand.on('click', this.newInstance, this);
          
      this._viewAllCommand = this._Y.one('#' + prefix + this.constructor.VIEW_ALL_COMMAND);
      this._viewAllCommand.on('click', this.viewAllInstance, this);
            
      // this break lowers the header on the form create/edit,
      // so we're going to hide it sometimes
      this._break = this._Y.one('#' + prefix + this.constructor.OPTION_BREAK);
      
      // Reference to the current form object that is being modified/created.
      // This will be null when viewing all objects.
      this._formObject = null;
      
      this._WebFormObject = com.runwaysdk.form.web.WebFormObject;
      
      this._formContainer = this._Y.one('#' + prefix + this.constructor.FORM_CONTAINER);
      this._tableContainer = this._Y.one('#' + prefix + this.constructor.TABLE_CONTAINER);
      this._excelButtons = this._Y.one('#' + this.constructor.EXCEL_BUTTONS);
      
      this._viewParentCommand = this._Y.one('#' + prefix + this.constructor.VIEW_PARENT_COMMAND);
      
      if(this._viewParentCommand != null)
      {
        this._viewParentCommand.on('click', this.viewParent, this);
      }
      
      this._tableContainer.delegate('click', this.viewInstance, 'span.generatedViewCommand', this);
    },
    getFormObject : function() {
      return this._formObject;
    },
    setFormObject : function(formObject) {
      this._formObject = formObject;
    },
    createFormObject : function(formObjectJSON) {
      this._formObject = this._WebFormObject.parseFromJSON(formObjectJSON);        
    },
    /**
     * Formats dates according to the DDMS specification.
     */
    dateColumnFormatter : function(o){
      return MDSS.Calendar.getLocalizedString(o.data[o.field]) || '';
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
    renderViewWithJSON : function (formObjectJSON) {
      this.createFormObject(formObjectJSON);
      this.renderView();
    },    
    renderView : function(){
      this.dispatchEvent(new dss.vector.solutions.RenderViewEvent(this._mdFormId));
    
      this.clearFormContainer();
      this.hideAllInstance();
      this._viewAllCommand.show();
      
      if(this._viewParentCommand != null)
      {
        this._viewParentCommand.show();        
      }
    
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
    renderFormWithJSON : function (formObjectJSON) {
      this.createFormObject(formObjectJSON);
      this.renderForm();
    },
    /**
     * Renders the create and upate form.
     */
    renderForm : function(){
      this.dispatchEvent(new dss.vector.solutions.RenderFormEvent(this._mdFormId));
        
      this.clearFormContainer();
      this.hideAllInstance();
      this._viewAllCommand.hide();
      
      if(this._viewParentCommand != null)
      {
        this._viewParentCommand.hide();
      }      
    
      var formEl = this._factory.newElement('form');
      formEl.setAttribute('method', 'POST');
      formEl.setAttribute('id', this._formObject.getId());
      formEl.setAttribute('name', this._formObject.getFormName());
    
      var header = this._factory.newElement('h2');
      header.setInnerHTML(this._formObject.getMd().getDisplayLabel());
      header.addClassName("pageTitle");
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
          dd.setInnerHTML('<hr />');
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
          
          // we don't have default values so leave a new instance value blank
          // and let any validation occur naturally (e.g., required)
          if(!this._formObject.isNewInstance())
          {
            if(value === 'true')
            {
              radioT.setAttribute('checked', 'checked');
            }
            else if(value === 'false')
            {
              radioF.setAttribute('checked', 'checked');
            }
          }
          
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
          errorContainer.addClassName('alertbox');
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
      
      var id = e.target.get('id');
      
      var event = new dss.vector.solutions.ViewEvent(id, this._mdFormId);
      
      this.dispatchEvent(event);
    },
    viewParent : function(e){
      this.dispatchEvent(new dss.vector.solutions.ViewParentEvent(this._mdFormId));
    },
    deleteInstance : function(e){
      e.preventDefault();  // prevent a synchronous form submit
            
      this.dispatchEvent(new dss.vector.solutions.DeleteEvent(this._mdFormId, this.getFormObject()));
    },
    /**
     * Updates the FormObject instance.
     */
    updateInstance : function(e){
      e.preventDefault(); // prevent a synchronous form submit
      
      this._Y.all('.alertbox').setStyle('visibility', 'hidden');
      
      this._updateValues();
      
      this.dispatchEvent(new dss.vector.solutions.UpdateEvent(this.getFormObject()));      
    },
    /**
     * Creates the FormObject instances.
     */
    createInstance : function(e){
      e.preventDefault(); // prevent a synchronous form submit
      
      this._Y.all('.alertbox').setStyle('visibility', 'hidden');
      
      this._updateValues();

      this.dispatchEvent(new dss.vector.solutions.CreateEvent(this.getFormObject()));
    },
    editInstance : function(e){
      e.preventDefault(); // prevent a synchronous form submit

      this.dispatchEvent(new dss.vector.solutions.EditEvent(this._mdFormId, this.getFormObject()));
    },
    /**
     * Cancels the creation or update of a FormObject.
     */
    cancelInstance : function(e){
      e.preventDefault();
        
      this.dispatchEvent(new dss.vector.solutions.CancelEvent(this.getFormObject()));
    },
    newInstance : function(){
      this.dispatchEvent(new dss.vector.solutions.NewInstanceEvent(this._mdFormId));
    },
    hideAllInstance : function(){
      this._newInstanceCommand.hide();
      this._break.hide();
      this._tableContainer.hide();
            
      if(this._excelButtons != null)
      {
        this._excelButtons.hide();
      }
    },
    /**
     * Shows the datatable with all the instances of the current
     * type. Unrelated functionality is hidden.
     */
    viewAllInstance : function(){
      this.dispatchEvent(new dss.vector.solutions.ViewAllEvent());
    },
    renderViewAll : function(){
      this.clearFormContainer();
      this._tableContainer.show();
      this._break.show();
      this._viewAllCommand.hide();
      
      if(this._viewParentCommand != null)
      {
        this._viewParentCommand.hide();        
      }
      
      if(this._table.isRendered())
      {
        this._table.resetDataTable();
      }
      else
      {
        this._table.render('#'+this._tableContainer.get('id'));
      }
      
      this._newInstanceCommand.show();
      
      if(this._excelButtons != null)
      {
        this._excelButtons.show();
      }
    },
    /**
     * The initial render shows all instances.
     */
    render : function(){
      this.viewAllInstance();
    },
    renderProblems : function(problems){
      for (var i = 0; i < problems.length; i++) {
        var p = problems[i];
        var attributeId = p.getAttributeId();

        var span = document.getElementById(attributeId);
        span.innerHTML = p.getLocalizedMessage();
        span.style.visibility = 'visible';
      }      
    },
    hide : function(){
      this.hideAllInstance();
      this._viewAllCommand.hide();
      this.clearFormContainer();
      this._tableContainer.hide();
      this._break.hide();
      this._viewAllCommand.hide();
            
      if(this._excelButtons != null)
      {
        this._excelButtons.hide();
      }
      
      if(this._viewParentCommand != null)
      {
        this._viewParentCommand.hide();        
      }
    }
  }
});

})();