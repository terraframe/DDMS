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
    FORM_CONTAINER : 'formContainer'
  },
  Instance : {
    initialize : function(mdFormId, mdClassType){
      this._mdFormId = mdFormId;
      this._mdClassType = mdClassType;
      this._parentDiv = null;
      
      this._FormObjectController = dss.vector.solutions.form.FormObjectController;
      
      UI.Manager.setFactory("YUI3");
      this._factory = UI.Manager.getFactory();
      
      this._table = this._factory.newDataTable(this._mdClassType);
      
      this._Y = YUI().use('*'); // YUI3 reference
      this._Y.one('#'+this.constructor.NEW_INSTANCE_COMMAND).on('click', this.newInstance, this);
      
      // Reference to the current form object that is being modified/created.
      // This will be null when viewing all objects.
      this._formObject = null;
      
      this._WebFormObject = com.runwaysdk.form.web.WebFormObject;
    },
    renderView : function(formObjectJSON){
      var webForm = this._WebFormObject.parseFromJSON(formObjectJSON);
      this._formObject = webForm;     
    
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
        
        dt.setInnerHTML(field.getFieldMd().getDisplayLabel());
        dd.setInnerHTML(value);
      }
      
      // edit
      var createBtn = this._factory.newElement('button');
      createBtn.setInnerHTML(MDSS.localize('Create'));
      div.appendChild(createBtn);
      
      createBtn.getImpl().on('click', this.createInstance, this);
      
      // delete
      var deleteBtn = this._factory.newElement('button');
      deleteBtn.setInnerHTML(MDSS.localize('Delete'));
      div.appendChild(deleteBtn);

      deleteBtn.getImpl().on('click', this.deleteInstance, this);
      
      div.render('#'+this.constructor.FORM_CONTAINER);
    },
    /**
     * Renders the create and upate form.
     */
    renderForm : function(formObjectJSON){
      var webForm = this._WebFormObject.parseFromJSON(formObjectJSON);
      this._formObject = webForm;    
    
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
        var displayField = false;
        if(field instanceof FIELD.WebHeader)
        {
          var h = this._factory.newElement('h2');
          h.setInnerHTML(value);
          dd.appendChild(h);
          displayField = true;
        }
        else if(field instanceof FIELD.WebBreak)
        {
          dd.setInnerHTML('<br />');
          displayField = true;
        }
        else if(field instanceof FIELD.WebComment)
        {
          dd.setInnerHTML(value);
          displayField = true;
        }
        
        if(displayField)
        {
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
      
      formEl.render('#'+this.constructor.TABLE_CONTAINER);  
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
    deleteInstance : function(e){
      e.preventDefault();  // prevent a synchronous form submit
      
      var that = this;
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
          that.renderView(formObjectJSON);
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
    render : function(){
      this._table.render('#'+this.constructor.TABLE_CONTAINER);
    }
  }
});

/*
var FormObjectCache = Mojo.Meta.newClass('dss.vector.solutions.FormObjectCache', {
  IsSingleton: true,
  Instance : {
    initialize : function(){
      this._forms = new com.runwaysdk.structure.HashMap();
    },
    add : function(form){
      this._forms.put(form.getId(), form);
    },
    get : function(id){
      return this._forms.get(id);
    },
    remove : function(form){
      this._forms.remove(form);
    }
  }
});
*/

})();