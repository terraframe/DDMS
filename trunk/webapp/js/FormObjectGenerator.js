/*
 * Manages control flow for the FormObject generator.
 */
(function(){

var UI = Mojo.Meta.alias(Mojo.UI_PACKAGE+'*');
var FIELD = Mojo.Meta.alias(Mojo.FORM_PACKAGE.FIELD+'*');
var CustomEvent = Mojo.$.com.runwaysdk.event.CustomEvent;

var NewInstanceEvent = Mojo.Meta.newClass('dss.vector.solutions.NewInstanceEvent', {
  Extends : CustomEvent,
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

var ViewEvent = Mojo.Meta.newClass('dss.vector.solutions.ViewEvent', {
  Extends : CustomEvent,
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

var BeforeQueryEvent = Mojo.Meta.newClass('dss.vector.solutions.BeforeQueryEvent', {
  Extends : CustomEvent,
  Instance : {
    initialize : function (mdFormId, query) {
      this.$initialize();
      
      this._mdFormId = mdFormId;
      this._query = query;
    },    
    getQuery : function() {
      return this._query;
    },
    defaultAction : function() {
    }
  }
});

var RenderFieldEvent = Mojo.Meta.newClass('dss.vector.solutions.RenderFieldEvent', {
  IsAbstract : true,
  Extends : CustomEvent,
  Instance : {
    initialize : function (formComponent, parent) {
      this.$initialize();
      
      this._formComponent = formComponent;
      this._parent = parent;
    },
    getField : function(){
      return this.getFormComponent().getField();
    },
    getFormComponent : function() {
      return this._formComponent;
    }
  }
});

var RenderEditFieldEvent = Mojo.Meta.newClass('dss.vector.solutions.RenderEditFieldEvent', {
  Extends : RenderFieldEvent,
  Instance : {
    initialize : function (component, parent) {
      this.$initialize(component, parent);
    },
    defaultAction : function() {
    
      var com = this.getFormComponent();
      var f = com.getFactory();
			
			var rowDiv = f.newElement('div');
			var fieldColDiv = f.newElement('div');
			fieldColDiv.setStyle('float', 'left');
			
			this._parent.appendChild(rowDiv);
			rowDiv.appendChild(fieldColDiv);
    
      // dt
      var displayNode = com.getDisplayNode();
      if(displayNode)
      {
        var dt = f.newElement('dt');
        dt.appendChild(displayNode);
        
				fieldColDiv.appendChild(dt);
      }
      
      // dd
      var contentNode = com.getContentNode();
      if(contentNode)
      {
        var dd = f.newElement('dd');
        dd.appendChild(contentNode);
        
        var field = com.getField();
				
        var errorDiv = f.newElement('div');
        errorDiv.addClassName('alertbox');
        errorDiv.setStyle('float', 'left');
        errorDiv.setStyle('visibility', 'hidden');
        rowDiv.appendChild(errorDiv);
				
        if(field instanceof FIELD.WebPrimitive)
        {
          var attrId = field.getFieldMd().getDefiningMdAttribute();
          errorDiv.setId(attrId);
        }        
       
				fieldColDiv.appendChild(dd);
				
				// this allows 2 divs to sit side by side
				var clearDiv = f.newElement('div');
				rowDiv.appendChild(clearDiv);
				clearDiv.setStyle('clear', 'both');
        
        if(ValueFieldIF.getMetaClass().isInstance(com))
        {
          com.monitorValueChange(contentNode);
          // have the FormObjectGenerator listen for changes to the field
          var fog = this.getTarget();
          com.addEventListener(ValueChangeEvent, fog.handleValueChange, null, fog);
        }
      }
    }
  }
});

var RenderViewFieldEvent = Mojo.Meta.newClass('dss.vector.solutions.RenderViewFieldEvent', {
  Extends : RenderFieldEvent,
  Instance : {
    initialize : function (component, parent) {
      this.$initialize(component, parent);
    },
    defaultAction : function() {
    
      var com = this.getFormComponent();
          
      // dt
      var displayNode = com.getDisplayNode();
      var f = com.getFactory();
      if(displayNode)
      {
        var dt = f.newElement('dt');
        dt.appendChild(displayNode);
        
        this._parent.appendChild(dt);
      }
      
      // dd
      var contentNode = com.getReadNode();
      if(contentNode)
      {
        var dd = f.newElement('dd');
        dd.appendChild(contentNode);
        
        this._parent.appendChild(dd);
      }
    }
  }
});


var RenderViewEvent = Mojo.Meta.newClass('dss.vector.solutions.RenderViewEvent', {
  Extends : CustomEvent,
  Instance : {
    initialize : function (mdFormId) {
      this.$initialize();
      
      this._mdFormId = mdFormId;
    },    
    defaultAction : function() {
    }
  }
});

var RenderFormEvent = Mojo.Meta.newClass('dss.vector.solutions.RenderFormEvent', {
  Extends : CustomEvent,
  Instance : {
    initialize : function (mdFormId) {
      this.$initialize();
      
      this._mdFormId = mdFormId;
    },    
    defaultAction : function() {
    }
  }
});

var ViewParentEvent = Mojo.Meta.newClass('dss.vector.solutions.ViewParentEvent', {
  Extends : CustomEvent,
  Instance : {
    initialize : function (mdFormId) {
      this.$initialize();
      
      this._mdFormId = mdFormId;
    },    
    defaultAction : function() {
    }
  }
});

var EditEvent = Mojo.Meta.newClass('dss.vector.solutions.EditEvent', {
  Extends : CustomEvent,
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

var CancelEvent = Mojo.Meta.newClass('dss.vector.solutions.CancelEvent', {
  Extends : CustomEvent,
  Instance : {
    initialize : function (formObject) {
      this.$initialize();
  
      this._formObject = formObject;
    },    
    getFormObject : function() {
      return this._formObject;
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

var DeleteEvent = Mojo.Meta.newClass('dss.vector.solutions.DeleteEvent', {
  Extends : CustomEvent,
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

var UpdateEvent = Mojo.Meta.newClass('dss.vector.solutions.UpdateEvent', {
  Extends : CustomEvent,
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

var CreateEvent = Mojo.Meta.newClass('dss.vector.solutions.CreateEvent', {
  Extends : CustomEvent,
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

var ViewAllEvent = Mojo.Meta.newClass('dss.vector.solutions.ViewAllEvent', {
  Extends : CustomEvent,
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
 * Renders the read-only view of a FormObject. This creates a form tag wit a structured
 * definition list to contain the fields.
 */
var FormObjectRenderVisitor = Mojo.Meta.newClass('dss.vector.solutions.FormObjectViewVisitor', {
  Instance : {
    initialize : function(formObjectGenerator, editMode){
      this.$initialize();
      
      this._formObjectGenerator = formObjectGenerator; // the generator that created this visitor
      this._formObjectGenerator.clearConditions();
      
      this._factory = this._formObjectGenerator.getFactory();
      this._editMode = editMode;
      
      // placeholders for known elements in the form.
      this._header = null;
      this._form = null;
      this._dl = this._factory.newElement('dl');
      this._buttons = this._factory.newElement('div');
    },
    /**
     * Returns the fully constructed Node from the traversal of the FormObject.
     */
    getNode : function(){
      //var frag = this._factory.newDocumentFragment();
      //frag.appendChild(this._form);
      this._form.appendChild(this._header);
      this._form.appendChild(this._dl);
      this._form.appendChild(this._buttons);
      return this._form;
    },
    _addField : function(formComponent){
      
      if(formComponent.getField().isReadable())
      {
        var evt = this._editMode ? new RenderEditFieldEvent(formComponent, this._dl) 
          : new RenderViewFieldEvent(formComponent, this._dl);
        
        // the FormObjectGenerator is the object that dispatches the event, such
        // that calling code can listen for it.
        this._formObjectGenerator.dispatchEvent(evt);
        
        // if the field was added (i.e., the default action executed) then
        // visit the condition if one exists.
        if(!evt.getPreventDefault()){
          var cond = formComponent.getField().getCondition();
          if(cond){
            cond.accept(this);
          }
        }
      }
    },
    visitFormObject : function(formObject){
      var com = new FormBody(formObject);
      com.setDefaultNodes();
      this._header = com.getDisplayNode();
      this._form = com.getContentNode();
    },
    
    visitBoolean : function(field){
      var com = new BooleanComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitCharacter : function(field){
      var com = new CharacterComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitText : function(field){
      var com = new TextComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitDate : function(field){
      var com = new DateComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitInteger : function(field){
      var com = new IntegerComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitLong : function(field){
      var com = new LongComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitDouble : function(field){
      var com = new DoubleComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitDecimal : function(field){
      var com = new DecimalComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitFloat : function(field){
      var com = new FloatComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitBreak : function(field){
      var com = new BreakComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitHeader : function(field){
      var com = new HeaderComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitComment : function(field){
      var com = new CommentComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitReference : function(field){
      var com = new ReferenceComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitGroup : function(field){
    
    },
    
    /**
     * Buttons may one day be implemented as a Field because giving them
     * displays, behavior, and visibility capability would be useful.
     */
    visitButton : function(element){
      var com = new ButtonComponent(element);
      com.setDefaultNodes();
      this._buttons.appendChild(com.getContentNode());
    },
    
    visitCharacterCondition : function(cond){
      this._formObjectGenerator.addCondition(cond);
    },
    
    visitLongCondition : function(cond){
      this._formObjectGenerator.addCondition(cond);
    },

    visitDoubleCondition : function(cond){
      this._formObjectGenerator.addCondition(cond);
    },
    
    visitDateCondition : function(cond){
      this._formObjectGenerator.addCondition(cond);
    },
    
    visitAndFieldCondition : function(field){
    
    }
  }
});


var ContentFragment = Mojo.Meta.newClass('dss.vector.solutions.ContentFragment', {
  IsAbstract : true,
  Instance : {
    initialize : function(){
      this.$initialize();
      this._factory = UI.Manager.getFactory();
      
      // create the default nodes
      this._displayNode = null;
      this._contentNode = null;
      this._readNode = null;
    },
    getFactory : function(){
      return this._factory;
    },
    setDisplayNode : function(node){
      this._displayNode = node;
    },
    setContentNode : function(node){
      this._contentNode = node;
    },
    setReadNode : function(node){
      this._readNode = node;
    },
    _getDisplayNode : function(){
      return null;
    },
    _getContentNode : function(){
      return null;
    },
    _getReadNode : function(){
      return null;
    },
    getDisplayNode : function() { return this._displayNode; },
    getContentNode : function() { return this._contentNode; },
    getReadNode : function() { return this._readNode; },
    setDefaultNodes : function(){
      this.setDisplayNode(this._getDisplayNode());
      this.setContentNode(this._getContentNode());
      this.setReadNode(this._getReadNode());
    }
  }
});

/**
 * The primary container for a form based on a FormObject.
 */
var FormBody = Mojo.Meta.newClass('dss.vector.solutions.FormBody', {
  Extends : ContentFragment,
  Instance : {
    initialize : function(formObject){
      this.$initialize();
      this._formObject = formObject;
    },
    _getDisplayNode : function(){
      var header = this.getFactory().newElement('h2');
      header.setInnerHTML(this._formObject.getMd().getDisplayLabel());
      header.addClassName("pageTitle");
      return header;
    },
    _getContentNode : function(){
      var form = this.getFactory().newElement('form', {
          id: this._formObject.getId(),
          name: this._formObject.getMd().getFormName(),
          method: 'POST'
        });
        
      return form;
    }
  }
});

/**
 * Implemented by classes that have a value that can be changed.
 */
var ValueFieldIF = Mojo.Meta.newInterface('dss.vector.solutions.FormComponent', {
  Instance : {
    monitorValueChange : function(node){},
    dispatchValueChangeEvent : function(e){}
  }
});

var ValueChangeEvent = Mojo.Meta.newClass('dss.vector.solutions.ValueChangeEvent', {
  Extends: CustomEvent,
  Instance : {
    initialize : function(value){
      this.$initialize();
      this._value = value;
    },
    getValue : function() { return this._value; }
  }
});

/**
 * Abstract superclass to represent a single component of a form, whether it be a control, annotation, or button.
 */
var FormComponent = Mojo.Meta.newClass('dss.vector.solutions.FormComponent', {
  Extends: ContentFragment,
  IsAbstract : true,
  Instance : {
    initialize : function(){
      this.$initialize();
    }
  }
});

var FieldComponent = Mojo.Meta.newClass('dss.vector.solutions.FieldComponent', {
  Extends : FormComponent,
  IsAbstract : true,
  Instance : {
    initialize : function(field){
      this.$initialize();
      this._field = field;
    },
    getField : function() { return this._field; },
    /**
     * Returns the value of the field formatted for the DOM.
     */
    getValue : function(){
      var v = this.getField().getValue();
      return v != null ? v : '';
    },
    _getDisplayNode : function(){
        
      var fMd = this.getField().getFieldMd();
      var labelTxt;
      if(fMd.isRequired())
      {
        // Required star styled via CSS
        labelTxt = '* '+fMd.getDisplayLabel();
      }
      else
      {
        labelTxt = fMd.getDisplayLabel();
      }
      
      var desc = fMd.getDescription();
      if(!Mojo.Util.isString(desc) || desc.trim().length === 0)
      {
        desc = fMd.getDisplayLabel();
      }
    
      var node = this.getFactory().newElement('label', {
        'for': this.getField().getFieldName(),
        'title':desc
      });
      
      node.setInnerHTML(labelTxt);
      
      return node;
    },
    _getContentNode : function(){
      return this.getFactory().newElement('input', {
        type: 'text',
        value: this.getValue(),
        name: this.getField().getFieldName()
      });
    },
    _getReadNode : function(){
      var node = this.getFactory().newElement('span');
      node.setInnerHTML(this.getValue());
      return node;
    }
  }  
});

var GroupComponent = Mojo.Meta.newClass('dss.vector.solutions.GroupComponent', {
  Extends : FieldComponent,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    }
  }  
});

var CharacterComponent = Mojo.Meta.newClass('dss.vector.solutions.CharacterComponent', {
  Extends : FieldComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    _getContentNode : function(){
      var fMd = this.getField().getFieldMd();
      var node = this.$_getContentNode();
      node.setAttributes({
        'maxlength':fMd.getMaxLength(),
        'size':fMd.getDisplayLength()
      });
      
      return node;
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    }
  }
});

var TextComponent = Mojo.Meta.newClass('dss.vector.solutions.TextComponent', {
  Extends : FieldComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    _getContentNode : function(){
      var node = this.getFactory().newElement('textarea', {
          'name':this.getField().getFieldName(),
          'rows':this.getField().getFieldMd().getHeight(),
          'cols':this.getField().getFieldMd().getWidth()
      });
      
      var value = this.getValue();
      node.setInnerHTML(value);
      return node;
    },
    _getReadNode : function(){
      var node = this.getFactory().newElement('p');
      node.setInnerHTML(this.getValue());
      
      return node;    
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().innerHTML;
      this.dispatchEvent(new ValueChangeEvent(value));
    }
  }
});

var ReferenceComponent = Mojo.Meta.newClass('dss.vector.solutions.ReferenceComponent', {
  Extends : FieldComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    _getContentNode : function(){
      var input = this.getFactory().newElement('input', {
        'type':'text',
        'name':this.getField().getFieldName(),
        'value':this.getValue(),
        'maxlength':64,
        'size':64
      });
      
      return input;  
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    }
  }
});

var BooleanComponent = Mojo.Meta.newClass('dss.vector.solutions.BooleanComponent', {
  Extends : FieldComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    _getContentNode : function(){
    
      var field = this.getField();
    
      var radioT = this.getFactory().newElement('input', {
        'type':'radio',
        'name':field.getFieldName(),
        'value':'true',
        'group':field.getFieldName()+'_G',
      });
      var tLabel = this.getFactory().newElement('span');
      tLabel.setInnerHTML(field.getFieldMd().getPositiveDisplayLabel());
      
      var radioF = this.getFactory().newElement('input', {
        'type':'radio',
        'name':field.getFieldName(),
        'value':'false',
        'group':field.getFieldName()+'_G',
      });
      var fLabel = this.getFactory().newElement('span');
      fLabel.setInnerHTML(field.getFieldMd().getNegativeDisplayLabel());
      
      var value = this.getValue();
      if(value === 'true')
      {
        radioT.setAttribute('checked', 'checked');
      }
      else if(value === 'false')
      {
        radioF.setAttribute('checked', 'checked');
      }
      
      // div and class to make sure radios and text are not squished together
      var radioContainer = this.getFactory().newElement('div');
      radioContainer.addClassName('boolean-field-container');
      
      radioContainer.appendChild(radioT);
      radioContainer.appendChild(tLabel);
      radioContainer.appendChild(radioF);
      radioContainer.appendChild(fLabel);
      
      return radioContainer;    
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    }
  }
});
var IntegerComponent = Mojo.Meta.newClass('dss.vector.solutions.IntegerComponent', {
  Extends : FieldComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    }
  }
});

var LongComponent = Mojo.Meta.newClass('dss.vector.solutions.LongComponent', {
  Extends : FieldComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    }
  }
});

var DoubleComponent = Mojo.Meta.newClass('dss.vector.solutions.DoubleComponent', {
  Extends : FieldComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    }
  }
});

var DecimalComponent = Mojo.Meta.newClass('dss.vector.solutions.DecimalComponent', {
  Extends : FieldComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    }
  }
});

var FloatComponent = Mojo.Meta.newClass('dss.vector.solutions.FloatComponent', {
  Extends : FieldComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    }
  }
});

/**
 * Adds a special calendar widget to the date input and localizes the value.
 */
var DateComponent = Mojo.Meta.newClass('dss.vector.solutions.DateComponent', {
  Extends : FieldComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    getValue : function(){
      var v = this.$getValue();
      return MDSS.Calendar.getLocalizedString(v) || '';
    },
    _getContentNode : function(){
      var node = this.$_getContentNode();
      MDSS.Calendar.addCalendarListeners(node.getRawEl());
      return node;
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    }
  }
});

var AnnotationComponent = Mojo.Meta.newClass('dss.vector.solutions.BreakComponent', {
  IsAbstract : true,
  Extends : FieldComponent,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    }
  }
});

var BreakComponent = Mojo.Meta.newClass('dss.vector.solutions.BreakComponent', {
  Extends : AnnotationComponent,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    _getReadNode : function(){
      return this.getFactory().newElement('hr');
    }
  }
});

var HeaderComponent = Mojo.Meta.newClass('dss.vector.solutions.HeaderComponent', {
  Extends : AnnotationComponent,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    _getReadNode : function(){
      var node = this.getFactory().newElement('h2');
      node.setInnerHTML(this.getValue());
      
      return node;
    }
  }
});

var CommentComponent = Mojo.Meta.newClass('dss.vector.solutions.CommentComponent', {
  Extends : AnnotationComponent,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    }
  }
});

var ButtonComponent = Mojo.Meta.newClass('dss.vector.solutions.ButtonComponent', {
  Extends : FormComponent,
  Instance : {
    initialize : function(el){
      this.$initialize();
      
      this._el = el;
    },
    _getContentNode : function(){
      return this._el;
    }
  }
});

/**
 * Primary class to handle control flow in the UI.
 */
Mojo.Meta.newClass('dss.vector.solutions.FormObjectGenerator', {
  Extends: UI.Component,
  Constants : {
    NEW_INSTANCE_COMMAND : 'NewInstanceCommand',
    TABLE_CONTAINER : 'TableContainer',
    FORM_CONTAINER : 'FormContainer',
    VIEW_ALL_COMMAND : 'ViewAllCommand',
    VIEW_PARENT_COMMAND : 'ViewParentCommand',
    EXCEL_BUTTONS : 'excelButtons',
    PARENT_BREAK : 'ParentBreak',
    OPTION_BREAK : 'OptionBreak'
  },
  Instance : {
    initialize : function(prefix, mdFormId, mdClassType){
      this.$initialize();
      this._mdFormId = mdFormId;
      this._mdClassType = mdClassType;
      this._parentDiv = null;
            
      this.getManager().setFactory("YUI3");
      
      var col = this.getFactory().newColumn({
        key:Mojo.Util.generateId()+'_view',
        label:'',
        formatter: Mojo.Util.bind(this, this.viewColumnFormatter)
      });
      
      this._table = this.getFactory().newDataTable(this._mdClassType, [col]);
      this._table.setTypeFormatter('com.runwaysdk.system.metadata.MdAttributeDate', Mojo.Util.bind(this, this.dateColumnFormatter));
      this._table.addEventListener(com.runwaysdk.ui.YUI3.PreLoadEvent, this.fireBeforeQueryEvent, null, this);
      
      this._Y = YUI().use('*'); // YUI3 reference
      this._newInstanceCommand = this._Y.one('#'+ prefix +this.constructor.NEW_INSTANCE_COMMAND);
      this._newInstanceCommand.on('click', this.newInstance, this);
          
      this._viewAllCommand = this._Y.one('#' + prefix + this.constructor.VIEW_ALL_COMMAND);
      
      if(this._viewAllCommand != null)
      {
        this._viewAllCommand.on('click', this.viewAllInstance, this);
      } 
            
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

      this._parentBreak = this._Y.one('#' + prefix + this.constructor.PARENT_BREAK);
      this._viewParentCommand = this._Y.one('#' + prefix + this.constructor.VIEW_PARENT_COMMAND);
      
      if(this._viewParentCommand != null)
      {
        this._viewParentCommand.on('click', this.viewParent, this);
      }
      
      this._tableContainer.delegate('click', this.viewInstance, 'span.generatedViewCommand', this);
      
      // mapping between defining MdField and Condition objects
      this._conditions = new com.runwaysdk.structure.HashMap();
    },
    clearConditions : function(){
      this._conditions.clear();
    },
    addCondition : function(cond){
     
      var mdFieldId = cond.getDefiningMdField();
      if(this._conditions.containsKey(mdFieldId))
      {
        this._conditions.get(mdFieldId).push(cond);
      }
      else
      {
        this._conditions.put(mdFieldId, [cond]);
      }
    },
    handleValueChange : function(valueChangeEvent){
      var component = valueChangeEvent.getTarget();
      var value = valueChangeEvent.getValue();
      
      // evaluate all conditions whose definingMdField is that of the FieldComponent that had
      // its value changed
      var conditions = this._conditions.get(component.getField().getFieldMd().getId());
      if(conditions){
        for(var i=0; i<conditions.length; i++)
        {
          var cond = conditions[i];
          cond.evaluate(value);
          console.log('isTrue: '+cond.isTrue());
        }
      }
    },
    getChildren : function(){
      return null; // TODO remove
    },
    hasChild : function(){
      return null; // TODO remove
    },
    getChild : function(id){
      return null; // TODO remove
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
      this.dispatchEvent(new RenderViewEvent(this._mdFormId));
    
      this.clearFormContainer();
      this.hideAllInstance();
      
      if(this._viewAllCommand != null)
      {
        this._viewAllCommand.show();
      } 
      
      if(this._viewParentCommand != null)
      {
        this._viewParentCommand.show();        
      }
      
      if(this._parentBreak != null)
      {
        this._parentBreak.show();
      }

      var visitor = new FormObjectRenderVisitor(this, false);
      this._formObject.accept(visitor);
      

      // edit
      var editBtn = this.getFactory().newElement('button');
      editBtn.setInnerHTML(MDSS.localize('Edit'));
      editBtn.getImpl().on('click', this.editInstance, this);
      
      visitor.visitButton(editBtn);
      
      // delete
      var deleteBtn = this.getFactory().newElement('button');
      deleteBtn.setInnerHTML(MDSS.localize('Delete'));
      deleteBtn.getImpl().on('click', this.deleteInstance, this);
      
      visitor.visitButton(deleteBtn);
      
      // the view of the form is constructed, so add it to the main container.
      var formContent = visitor.getNode();
      this._formContainer.appendChild(formContent.getRawEl());
    },
    renderFormWithJSON : function (formObjectJSON) {
      this.createFormObject(formObjectJSON);
      this.renderForm();
    },
    
    /**
     * Renders the create and upate form.
     */
    renderForm : function(){
      this.dispatchEvent(new RenderFormEvent(this._mdFormId));
        
      this.clearFormContainer();
      this.hideAllInstance();

      if(this._viewAllCommand != null)
      {
        this._viewAllCommand.hide();
      } 
      
      if(this._viewParentCommand != null)
      {
        this._viewParentCommand.hide();
      }
      
      if(this._parentBreak != null)
      {
        this._parentBreak.hide();
      }          
    
      var visitor = new FormObjectRenderVisitor(this, true);
      this._formObject.accept(visitor);
      var formContent = visitor.getNode();
      
      
      // Add the action buttons
      if(this._formObject.isNewInstance())
      {
        // create
        var createBtn = this.getFactory().newElement('button');
        createBtn.setInnerHTML(MDSS.localize('Create'));
        createBtn.getImpl().on('click', this.createInstance, this);

        visitor.visitButton(createBtn);
        
        
        // cancel
        var cancelBtn = this.getFactory().newElement('button');
        cancelBtn.setInnerHTML(MDSS.localize('Cancel'));
        cancelBtn.getImpl().on('click', this.cancelInstance, this);
        
        visitor.visitButton(cancelBtn);
      }
      else
      {
        // update
        var updateBtn = this.getFactory().newElement('button');
        updateBtn.setInnerHTML(MDSS.localize('Update'));
        updateBtn.getImpl().on('click', this.updateInstance, this);
        
        visitor.visitButton(updateBtn);
        
        // cancel
        var cancelBtn = this.getFactory().newElement('button');
        cancelBtn.setInnerHTML(MDSS.localize('Cancel'));
        cancelBtn.getImpl().on('click', this.cancelInstance, this);
        
        visitor.visitButton(cancelBtn);
      }
      
      var formContent = visitor.getNode();
      this._formContainer.appendChild(formContent.getRawEl());
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
          
          if(field instanceof FIELD.WebReference && Mojo.Util.isArray(value)) 
          {        
            field.setValue(value[0]);
          }
          else
          {
            field.setValue(value);
          }
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
      this.dispatchEvent(new ViewParentEvent(this._mdFormId));
    },
    deleteInstance : function(e){
      e.preventDefault();  // prevent a synchronous form submit
            
      this.dispatchEvent(new DeleteEvent(this._mdFormId, this.getFormObject()));
    },
    fireBeforeQueryEvent : function(e){
      this.dispatchEvent(new BeforeQueryEvent(this._mdFormId, e.getQueryObject()));
    },    
    /**
     * Updates the FormObject instance.
     */
    updateInstance : function(e){
      e.preventDefault(); // prevent a synchronous form submit
      
      this._Y.all('.alertbox').setStyle('visibility', 'hidden');
      
      this._updateValues();
      
      this.dispatchEvent(new UpdateEvent(this.getFormObject()));      
    },
    /**
     * Creates the FormObject instances.
     */
    createInstance : function(e){
      e.preventDefault(); // prevent a synchronous form submit
      
      this._Y.all('.alertbox').setStyle('visibility', 'hidden');
      
      this._updateValues();

      this.dispatchEvent(new CreateEvent(this.getFormObject()));
    },
    editInstance : function(e){
      e.preventDefault(); // prevent a synchronous form submit

      this.dispatchEvent(new EditEvent(this._mdFormId, this.getFormObject()));
    },
    /**
     * Cancels the creation or update of a FormObject.
     */
    cancelInstance : function(e){
      e.preventDefault();
        
      this.dispatchEvent(new CancelEvent(this.getFormObject()));
    },
    newInstance : function(){
      this.dispatchEvent(new NewInstanceEvent(this._mdFormId));
    },
    hideAllInstance : function(){
      this._newInstanceCommand.hide();
      this._tableContainer.hide();
      
      if(this._break != null)
      {
        this._break.hide();
      }      
            
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
      this.dispatchEvent(new ViewAllEvent());
    },
    renderViewAll : function(){
      this.clearFormContainer();
      this._tableContainer.show();
      
      if(this._break != null)
      {
        this._break.show();
      }      
      
      if(this._viewAllCommand != null)
      {
        this._viewAllCommand.hide();
      } 
      
      if(this._viewParentCommand != null)
      {
        this._viewParentCommand.hide();        
      }

      if(this._parentBreak != null)
      {
        this._parentBreak.hide();
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
      this.clearFormContainer();
      this._tableContainer.hide();
      
      if(this._break != null)
      {
        this._break.hide();
      }      
      
      if(this._viewAllCommand != null)
      {
        this._viewAllCommand.hide();
      } 
      
      if(this._excelButtons != null)
      {
        this._excelButtons.hide();
      }
      
      if(this._viewParentCommand != null)
      {
        this._viewParentCommand.hide();        
      }
      
      if(this._parentBreak != null)
      {
        this._parentBreak.hide();
      }          
    }
  }
});

})();