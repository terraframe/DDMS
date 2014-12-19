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

var PostNewInstanceEvent = Mojo.Meta.newClass('dss.vector.solutions.PostNewInstanceEvent', {
  Extends : CustomEvent,
  Instance : {
    initialize : function () {
      this.$initialize();
    },    
    defaultAction : function() {
    }
  }
});

var CreateNewInstanceEvent = Mojo.Meta.newClass('dss.vector.solutions.CreateNewInstanceEvent', {
  Extends : CustomEvent,
  Instance : {
    initialize : function (mdFormId, criteria) {
      this.$initialize();
      
      this._mdFormId = mdFormId;
      this._criteria = criteria;
    },    
    defaultAction : function() {
      var target = this.getTarget();
      
      var request = new MDSS.Request({
        onSuccess : function(formObjectJSON){
          target.newInstanceFromJSON(formObjectJSON);
        }
      });
      
      var oldOnFailure = request.onFailure;
      request.onFailure = function(e)
      {
        if(e != null && e.getWrappedException() == 'dss.vector.solutions.generator.DuplicateFormInstanceExceptionDTO')
        {
          target.searchInstance();          
        }

        oldOnFailure.call(this, e);
      };
      
      dss.vector.solutions.form.FormObjectController.createNewInstance(request, this._criteria, this._mdFormId);
    }
  }
});

var DeleteAllEvent = Mojo.Meta.newClass('dss.vector.solutions.DeleteAllEvent', {
  Extends : CustomEvent,
  Instance : {
    initialize : function (criteria, type) {
      this.$initialize();
      
      this._criteria = criteria;
      this._type = type;
    },
    getFormObject : function() {
      return this._criteria;
    },    
    defaultAction : function() {
      var target = this.getTarget();
      
      var request = new MDSS.Request({
        onSuccess : function(){
          target.viewAllInstance();
        }
      });
            
      dss.vector.solutions.form.FormObjectController.deleteAll(request, this._criteria, this._type);
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
    
      // dt
      var displayNode = com.getDisplayNode();
      var f = com.getFactory();
      if(displayNode)
      {
        var dt = f.newElement('dt');
        dt.appendChild(displayNode);
        
        this._parent.appendChild(dt);
        com.addDOMParent(dt);
      }
      
      // dd
      var contentNode = com.getContentNode();
      contentNode.addClassName('field-content-node');
      if(contentNode)
      {
        var dd = f.newElement('dd');
        dd.appendChild(contentNode);
        
        // inline errors
        var errorContainer = f.newElement('span');
        errorContainer.addClassName('inline-alert');
        dd.appendChild(errorContainer);
          
        var field = com.getField();
        if(field instanceof FIELD.WebAttribute)
        {
          var attrId = field.getFieldMd().getDefiningMdAttribute();
          errorContainer.setId(attrId);
          com.setErrorContainerId(attrId);
        }
       
        this._parent.appendChild(dd);
        com.addDOMParent(dd);
        
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

var PostRenderViewFieldEvent = Mojo.Meta.newClass('dss.vector.solutions.PostRenderViewFieldEvent', {
  Extends : CustomEvent,
  Instance : {
    initialize : function (fieldComponent) {
      this.$initialize();
      
      this._fieldComponent = fieldComponent;
    },
    getFieldComponent : function() {
      return this._fieldComponent;
    },    
    defaultAction : function() {
      this._fieldComponent.postRender(false);
    }
  }
});


var PostRenderEditFieldEvent = Mojo.Meta.newClass('dss.vector.solutions.PostRenderEditFieldEvent', {
  Extends : CustomEvent,
  Instance : {
    initialize : function (fieldComponent) {
      this.$initialize();
      
      this._fieldComponent = fieldComponent;
    },
    getFieldComponent : function() {
      return this._fieldComponent;
    },    
    defaultAction : function() {
      this._fieldComponent.forceValueChangeEvent();
      this._fieldComponent.postRender(true);
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

var SearchEvent = Mojo.Meta.newClass('dss.vector.solutions.SearchEvent', {
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
      
      target._updateValues(this._formObject);      
      target._table.setCriteria(this._formObject)      
      target._table.resetDataTable();      
    }
  }
});

var ViewAllEvent = Mojo.Meta.newClass('dss.vector.solutions.ViewAllEvent', {
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
      
      target.renderViewAll(this._formObject);      
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
      this._formObjectGenerator.clearFieldComponents();
      
      this._factory = this._formObjectGenerator.getFactory();
      this._editMode = editMode;
      
      // placeholders for known elements in the form.
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
      this._form.appendChild(this._dl);
      this._form.appendChild(this._buttons);
      return this._form;
    },
    _addField : function(formComponent){
      
      var field = formComponent.getField();
      
      if(field.isReadable())
      {
        var skip = this._editMode && (field instanceof FIELD.WebPrimitive) && field.getFieldMd().getIsExpression();
        
        if(!skip)
        {          
          var evt = this._editMode ? new RenderEditFieldEvent(formComponent, this._dl) 
            : new RenderViewFieldEvent(formComponent, this._dl);
        
          // the FormObjectGenerator is the object that dispatches the event, such
          // that calling code can listen for it.
          this._formObjectGenerator.dispatchEvent(evt);
        
          // if the field was added (i.e., the default action executed) then
          // visit the condition if one exists.
          if(!evt.getPreventDefault()){
            this._formObjectGenerator.addFieldComponent(formComponent);
            var cond = formComponent.getField().getCondition();
            if(cond){
              cond.accept(this);
            }
          }
        }
      }
    },
    visitFormObject : function(formObject){
      var com = new FormBody(formObject);
      com.setDefaultNodes();
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
    
    visitGeo : function(field){
      var com = new GeoComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },
    
    visitSingleTerm : function(field){
      var com = new SingleTermComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },

    visitMultipleTerm : function(field){
      var com = new MultipleTermComponent(field);
      com.setDefaultNodes();
      this._addField(com);
    },

    visitSingleTermGrid : function(field){
      var com = new SingleTermGridComponent(field);
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
      cond._setValue(MDSS.Calendar.getLocalizedString(cond.getValue()));
      this._formObjectGenerator.addCondition(cond);
    },
    
    visitAndFieldCondition : function(cond){
      cond.getFirstCondition().accept(this);
      cond.getSecondCondition().accept(this);
    }
  }
});

/**
 * Renders the read-only view of a FormObject. This creates a form tag wit a structured
 * definition list to contain the fields.
 */
var FormObjectSearchVisitor = Mojo.Meta.newClass('dss.vector.solutions.FormObjectSearchVisitor', {
  Extends : FormObjectRenderVisitor,
  Instance : {
    initialize : function(formObjectGenerator, editMode, filter){
      this.$initialize(formObjectGenerator, editMode);      
      
      this._filter = filter;
    },
    _addField : function(formComponent){
      var field = formComponent.getField();
      
      if(field instanceof FIELD.WebAttribute)
      {        
        var attributeName = field.getFieldMd().getFieldName();
        
        if(this._filter(attributeName))
        {
          this.$_addField(formComponent);        
        }
      }
    },
    visitSingleTerm : function(field){
      var com = new SingleTermComponent(field, 'search');
      com.setDefaultNodes();
      this._addField(com);
    },    
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
    },
    /**
     * Called after the successful rendering of the component within the form.
     * Subclasses may override this to provide additional behavior.
     */
    postRender : function(editMode){
      // do nothing by default
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
      this._domParents = [];
      this._errorContainerId = null;
    },
    show : function(){
      for(var i=0, len=this._domParents.length; i<len; i++){
        this._domParents[i].setStyle('display', 'block');
      }
    },
    hide : function(){
      for(var i=0, len=this._domParents.length; i<len; i++){
        this._domParents[i].setStyle('display', 'none');
      }    
    },
    getErrorContainerId : function() {
      return this._errorContainerId;
    },
    setErrorContainerId : function(errorContainerId) {
      this._errorContainerId = errorContainerId;
    },
    addDOMParent : function(parent){
      this._domParents.push(parent);
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
    },
    forceValueChangeEvent : function(){
    
      var el = document.getElementsByName(this.getField().getFieldName())[0];
    
      // FIXME use library
      var evt = document.createEvent("HTMLEvents");
      evt.initEvent("change", true, false);
      el.dispatchEvent(evt);
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
      
      this._inputId = null;
    },
    _getContentNode : function(){
      var fMd = this.getField().getFieldMd();
      var node = this.$_getContentNode();
      node.setAttributes({
        'maxlength':fMd.getMaxLength(),
        'size':fMd.getDisplayLength()
      });
      
      this._inputId = node.getId();
      
      return node;
    },
    getInputId : function() {
      return this._inputId;
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
      node.addEventListener('blur', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    },
    forceValueChangeEvent : function(){
    
      var el = document.getElementsByName(this.getField().getFieldName())[0];
      // FIXME use library
      var evt = document.createEvent("UIEvent");
      evt.initEvent("blur", true, false);
      el.dispatchEvent(evt);
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
    _getReadNode : function(){
      var node = this.getFactory().newElement('span');
      node.setInnerHTML(this.getField().getReferenceDisplay());
      
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

var GeoComponent = Mojo.Meta.newClass('dss.vector.solutions.GeoComponent', {
  Extends : FieldComponent,
  Implements : [ValueFieldIF, com.runwaysdk.event.EventListener],
  Instance : {
    initialize : function(field){
      this.$initialize(field);
      this._inputId = null;
    },
    _getReadNode : function(){
      var node = this.getFactory().newElement('span');
      node.setInnerHTML(this.getField().getFieldMd().getGeoDisplayLabel() || '');
      
      return node;    
    },
    _getContentNode : function(){
    
      var f = this.getFactory();
      
      var div = f.newElement('div');
    
      var input = this.getFactory().newElement('input', {
        'type':'text',
      });
      div.appendChild(input);
      this._inputId = input.getId();

      var hidden = this.getFactory().newElement('input', {
        'type':'hidden',
        'name':this.getField().getFieldName(),
        'value':this.getValue(),
        'id': this._inputId+"_geoEntityId"
      });
      div.appendChild(hidden);
      
      return div;
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    },
    _setupGeoWidget : function(geoId){
      var geoField = this.getField().getGeoField();
    	
      var geoInput = document.getElementById(this._inputId);
      geoInput.value = geoId;
      
      var selectSearch = new MDSS.SingleSelectSearch(geoField.isUnderSystemRoot, geoField.selectSearchRootId);      
      selectSearch.setPolitical(geoField.isPoliticalHierarchy);
      selectSearch.setPopulated(geoField.isPopulationHierarchy);
      selectSearch.setSprayTargetAllowed(geoField.isSprayHierarchy);
      selectSearch.setUrban(geoField.isUrbanHierarchy);
  
      // filter
      selectSearch.setFilter(geoField.filter);
          
      // extra universals
      var extra = geoField.extraUniversals;
      for(var i=0; i<extra.length; i++)
      {
        selectSearch.addExtraUniversal(extra[i]);
      }      
      
      selectSearch.addListener(this);
      var geoSearch = new MDSS.GeoSearch(geoInput, selectSearch);    
    },
    /**
     * Handler method called when a geo entity is selected through the 101 widget or ajax
     * search.
     */
    handleEvent : function(e){
      var geoEntityId = e.getValue().selected.getGeoEntityId();
      this.dispatchEvent(new ValueChangeEvent(geoEntityId));
    },
    postRender : function(editMode){
    
      var that = this;
      if(editMode && this._inputId !== null){
        
        var value = this.getValue();
        if(value !== null && value.length > 0)
        {
          var request = new MDSS.Request({
            onSuccess : function(views){
              var view = views.getResultSet()[0];
              that._setupGeoWidget(view.getGeoId());
            }
          });
          
          dss.vector.solutions.geo.generated.GeoEntity.getAsViews(request, [this.getValue()]);
        }
        else
        {
          this._setupGeoWidget('');
        }
      }
    }
  }
});

var SingleTermComponent = Mojo.Meta.newClass('dss.vector.solutions.SingleTermComponent', {
  Extends : FieldComponent,
  Implements : [ValueFieldIF, com.runwaysdk.event.EventListener],
  Instance : {
    initialize : function(field, prefix){
      this.$initialize(field);
      this._inputId = null;
      this._prefix = prefix;
      
      if(this._prefix == null)
      {
        this._prefix = '';
      }
    },
    _getContentNode : function(){
    
      var f = this.getFactory();
      
      var div = f.newElement('div');
    
      var input = this.getFactory().newElement('input', {
        'type':'hidden',
        'name':this.getField().getFieldName(),
        'value':this.getValue(),
        'maxlength':64,
        'size':64,
        'id': this._prefix + this.getField().getFieldMd().getDefiningAttribute()
      });
      div.appendChild(input);
      this._inputId = input.getId();
      
      var display = f.newElement('input', {
        'type':'text',
        'id': this._inputId+'Display',
        'value':(this.getField().getFieldMd().getTermDisplayLabel() || '')
      });

      div.appendChild(display);      
      
      var btn = f.newElement('span', {
        'id': this._inputId+'Btn'
      });
      btn.addClassName('clickable');
      btn.setInnerHTML('<img class="ontologyOpener" src="./imgs/icons/term.png" title="Browser" alt="Browser">');
      div.appendChild(btn);
      
      return div;  
    },
    _getReadNode : function(){
      var node = this.getFactory().newElement('span');
      node.setInnerHTML(this.getField().getFieldMd().getTermDisplayLabel());
      return node;      
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    },
    handleEvent : function(evt){
      this.dispatchEvent(new ValueChangeEvent(evt.getTermId()));
    },
    postRender : function(editMode){
    
      // if we are editing then create the ontology browser
      if(editMode && this._inputId !== null)
      {
        var clazz = this.getField().getFieldMd().getDefiningClass();
        var browser = new MDSS.GenericOntologyBrowser(clazz, {
          attributeName : this.getField().getFieldMd().getDefiningAttribute(),
          inputId : this._inputId
        });
        
        browser.addTermSelectedListener(this);
      }
    }
  }
});

var MultipleTermComponent = Mojo.Meta.newClass('dss.vector.solutions.MultipleTermComponent', {
  Extends : FieldComponent,
  Implements : [ValueFieldIF, com.runwaysdk.event.EventListener],
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    _getContentNode : function(){
      var f = this.getFactory();
      
      var div = f.newElement('div');
    
      var input = this.getFactory().newElement('input', {
        'type':'text',
        'name':this.getField().getFieldName(),
        'value':'',
        'id':this.getField().getFieldMd().getDefiningAttribute()
      });
      div.appendChild(input);
      this._inputId = input.getId();
      
      var btn = f.newElement('span', {
        'id': this._inputId+'Btn'
      });
      btn.addClassName('clickable');
      btn.setInnerHTML('<img class="ontologyOpener" src="./imgs/icons/term.png" title="Browser" alt="Browser">');
      div.appendChild(btn);
      
      var results = f.newElement('ul', {
        'id': this._inputId+'ResultList',
        'type':'hidden'
      });
      div.appendChild(results);
      
      return div;  
    },
    _getReadNode : function(){
      var node = this.getFactory().newElement('span');
      var field = this.getField();
      var termIds = field.getTermIds();
      var html = '';
      for(var i=0; i<termIds.length; i++)
      {
        var termId = termIds[i];
        var display = field.getTerm(termId);
        html += display + '<br />';
      }
      node.setInnerHTML(html);
      return node;      
    },
    monitorValueChange : function(node){
       node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    handleEvent : function(evt){
      var termId = evt.getTermId();
      
      if(evt instanceof dss.vector.solutions.ontology.TermDeletedEvent)
      {
        this.getField().removeTerm(termId);
      }
      else
      {
        this.getField().addTerm(evt.getTermId());
      }
      
      this.dispatchValueChangeEvent(null);
    },
    dispatchValueChangeEvent : function(e){
      
      var termIds = this.getField().getTermIds();
      
      if(termIds.length > 0)
      {
        // this field is unique in that it has multiple values.
        // send all values in as a batch for a "contains" operation
        this.dispatchEvent(new ValueChangeEvent(termIds));
      }
      else
      {
        // there are no terms, so send in a dummy value that will fail
        this.dispatchEvent(new ValueChangeEvent(''));
      }
    },
    postRender : function(editMode){
    
      // if we are editing then create the ontology browser
      if(editMode && this._inputId !== null)
      {
        var clazz = this.getField().getFieldMd().getDefiningClass();
        var browser = new MDSS.GenericMultiOntologyBrowser(clazz, {
          attributeName : this._inputId
        });
        
        browser.addTermSelectedListener(this);
        var termIds = this.getField().getTermIds();
        for(var i=0; i<termIds.length; i++){
          var termId = termIds[i];
          var display = this.getField().getTerm(termId);
          browser.addSelection(display, termId);
        }
      }
    }
  }
});

var SingleTermGridComponent = Mojo.Meta.newClass('dss.vector.solutions.SingleTermGridComponent', {
  Extends : FieldComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    _getContentNode : function(){
      var fMd = this.getField().getFieldMd();
      var gridId = 'g_'+fMd.getId();
      var node = this.getFactory().newElement('div', {
        id: gridId
      });
      
      return node;
    },
    _getReadNode : function(){
      return this._getContentNode();
    },
    monitorValueChange : function(node){
      //node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      //var value = e.getTarget().value;
      //this.dispatchEvent(new ValueChangeEvent(value));
    },
    forceValueChangeEvent : function(){
    
    },
    postRender : function(editMode){
      var exec = this.getField().getGridExecutable();
      var grid = eval(exec);
      this.getField().setGrid(grid);
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
    },
    forceValueChangeEvent : function(){
    
      var els = document.getElementsByName(this.getField().getFieldName());
      var found = false;
      for(var i=0, len=els.length; i<len; i++){
        var el = els[i];
        if(el.checked){
          // FIXME use library
          found = true;
          var evt = document.createEvent("HTMLEvents");
          evt.initEvent("change", true, false);
          el.dispatchEvent(evt);
        }
      }
      
      if(!found){
        // no box was checked, so all conditions MUST fail since it's neither true nor false.
        this.dispatchEvent(new ValueChangeEvent(null));
      }
    }
  }
});

/**
 * Formats the number according to the locale.
 */
var NumberComponent = Mojo.Meta.newClass('dss.vector.solutions.NumberComponent', {
  Extends : FieldComponent,  
  IsAbstract : true,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    monitorValueChange : function(node){
      node.addEventListener('change', this.dispatchValueChangeEvent, null, this);
    },
    /**
     * Because the condition comparison is strictly within Runway,
     * convert the input back to a normal number.
     */
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    }
  }
});

var IntegerComponent = Mojo.Meta.newClass('dss.vector.solutions.IntegerComponent', {
  Extends : NumberComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    }
  }
});

var LongComponent = Mojo.Meta.newClass('dss.vector.solutions.LongComponent', {
  Extends : NumberComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    }
  }
});

var DoubleComponent = Mojo.Meta.newClass('dss.vector.solutions.DoubleComponent', {
  Extends : NumberComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    }
  }
});

var DecimalComponent = Mojo.Meta.newClass('dss.vector.solutions.DecimalComponent', {
  Extends : NumberComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    }
  }
});

var FloatComponent = Mojo.Meta.newClass('dss.vector.solutions.FloatComponent', {
  Extends : NumberComponent,
  Implements : ValueFieldIF,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
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
      
      var md = this.getField().getFieldMd();
      var config = {};
      config[MDSS.Calendar.Config.BEFORE_TODAY_INCLUSIVE] = md.getBeforeTodayInclusive();
      config[MDSS.Calendar.Config.BEFORE_TODAY_EXCLUSIVE] = md.getBeforeTodayExclusive();
      config[MDSS.Calendar.Config.AFTER_TODAY_INCLUSIVE] = md.getAfterTodayInclusive();
      config[MDSS.Calendar.Config.AFTER_TODAY_EXCLUSIVE] = md.getAfterTodayExclusive();
      config[MDSS.Calendar.Config.START_DATE] = md.getStartDate();
      config[MDSS.Calendar.Config.END_DATE] = md.getEndDate();
      
      MDSS.Calendar.addCalendarListeners(node.getRawEl(), config);
      return node;
    },
    monitorValueChange : function(node){
      node.addEventListener('blur', this.dispatchValueChangeEvent, null, this);
    },
    dispatchValueChangeEvent : function(e){
      var value = e.getTarget().value;
      this.dispatchEvent(new ValueChangeEvent(value));
    },
    forceValueChangeEvent : function(){
    
      var el = document.getElementsByName(this.getField().getFieldName())[0];
    
      // FIXME use library
      var evt = document.createEvent("UIEvent");
      evt.initEvent("blur", false, false);
      el.dispatchEvent(evt);
    }
  }
});

var AnnotationComponent = Mojo.Meta.newClass('dss.vector.solutions.BreakComponent', {
  IsAbstract : true,
  Extends : FieldComponent,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    _getDisplayNode : function() {
      return null;
    },
    _getReadNode : function(){
      return this._getContentNode();
    },
    /**
     * Override to do nothing because conditions can't be based on display fields.
     */
    forceValueChangeEvent : function() {}
  }
});

var BreakComponent = Mojo.Meta.newClass('dss.vector.solutions.BreakComponent', {
  Extends : AnnotationComponent,
  Instance : {
    initialize : function(field){
      this.$initialize(field);
    },
    _getContentNode : function(){
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
    _getContentNode : function(){
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
    },
    _getContentNode : function(){
      var node = this.getFactory().newElement('span');
      node.setInnerHTML(this.getValue());
      return node;
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
    SEARCH_CONTAINER : 'SearchContainer',
    SEARCH_INPUT : 'SearchInput',
    TABLE_CONTAINER : 'TableContainer',
    FORM_CONTAINER : 'FormContainer',
    VIEW_ALL_COMMAND : 'ViewAllCommand',
    VIEW_PARENT_COMMAND : 'ViewParentCommand',
    EXCEL_BUTTONS : 'ExcelButtons',
    PARENT_BREAK : 'ParentBreak',
    OPTION_BREAK : 'OptionBreak'
  },
  Instance : {
    initialize : function(prefix, mdFormId, mdClassType, fields, viewAllFields, searchFields, canDeleteAll){
      this.$initialize();
      this._mdFormId = mdFormId;
      this._mdClassType = mdClassType;
      this._canDeleteAll = canDeleteAll;
      this._parentDiv = null;
      
      // an array of the field names in order defined by MdForm
      this._fields = fields;
            
      this.getManager().setFactory("YUI3");
      
      var col = this.getFactory().newColumn({
        key:Mojo.Util.generateId()+'_view',
        label:'',
        formatter: Mojo.Util.bind(this, this.viewColumnFormatter)
      });
      
      var filter = function(columnName){
        return (viewAllFields.indexOf(columnName) != -1);
      }
      
      this._searchFilter = function(columnName){
        return (searchFields.indexOf(columnName) != -1);
      }
      
      var method = dss.vector.solutions.form.FormObjectController.searchInstance;
      
      this._table = this.getFactory().newDataTable(this._mdClassType, {method:method, preColumns:[col], columns: fields, filter: filter});
      this._table.setTypeFormatter('com.runwaysdk.system.metadata.MdAttributeDate', Mojo.Util.bind(this, this.dateColumnFormatter));
      this._table.setTypeFormatter('com.runwaysdk.system.metadata.MdAttributeDecimal', Mojo.Util.bind(this, this.numberColumnFormatter));
      this._table.setTypeFormatter('com.runwaysdk.system.metadata.MdAttributeDouble', Mojo.Util.bind(this, this.numberColumnFormatter));
      this._table.setTypeFormatter('com.runwaysdk.system.metadata.MdAttributeFloat', Mojo.Util.bind(this, this.numberColumnFormatter));
      this._table.setTypeFormatter('com.runwaysdk.system.metadata.MdAttributeLong', Mojo.Util.bind(this, this.numberColumnFormatter));
      this._table.setTypeFormatter('com.runwaysdk.system.metadata.MdAttributeInteger', Mojo.Util.bind(this, this.numberColumnFormatter));
      this._table.addEventListener(com.runwaysdk.ui.YUI3.PreLoadEvent, this.fireBeforeQueryEvent, null, this);
      this._table.setRequestFactory(function(config) { return new MDSS.Request(config)});
      
      this._Y = YUI().use('*'); // YUI3 reference
      this._newInstanceCommand = this._Y.one('#'+ prefix +this.constructor.NEW_INSTANCE_COMMAND);
      this._newInstanceCommand.on('click', this.newInstance, this);
          
      this._viewAllCommand = this._Y.one('#' + prefix + this.constructor.VIEW_ALL_COMMAND);
      
      if(this._viewAllCommand != null)
      {
        this._viewAllCommand.on('click', this.viewAllInstance, this);
      } 
      
      // div that holds all form id search components
      this._search = this._Y.one('#' + prefix + this.constructor.SEARCH_CONTAINER);
            
      // this break lowers the header on the form create/edit,
      // so we're going to hide it sometimes
      this._break = this._Y.one('#' + prefix + this.constructor.OPTION_BREAK);
      
      // Reference to the current form object that is being modified/created.
      // This will be null when viewing all objects.
      this._formObject = null;
      this._searchObject = null;
      
      this._WebFormObject = com.runwaysdk.form.web.WebFormObject;
      
      this._formContainer = this._Y.one('#' + prefix + this.constructor.FORM_CONTAINER);
      this._tableContainer = this._Y.one('#' + prefix + this.constructor.TABLE_CONTAINER);
      this._excelButtons = this._Y.one('#' + prefix + this.constructor.EXCEL_BUTTONS);

      this._parentBreak = this._Y.one('#' + prefix + this.constructor.PARENT_BREAK);
      this._viewParentCommand = this._Y.one('#' + prefix + this.constructor.VIEW_PARENT_COMMAND);
      
      if(this._viewParentCommand != null)
      {
        this._viewParentCommand.on('click', this.viewParent, this);
      }
      
      this._tableContainer.delegate('click', this.viewInstance, 'span.generatedViewCommand', this);
      
      // mapping between defining MdField and Condition objects
      this._conditions = new com.runwaysdk.structure.HashMap();
      this._fieldComponents = new com.runwaysdk.structure.HashMap();
      
    },
    getMdClassType : function(){
      return this._mdClassType;
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
    addFieldComponent : function(fieldComponent){
      var mdFieldId = fieldComponent.getField().getFieldMd().getId();
      this._fieldComponents.put(mdFieldId, fieldComponent);   
    },
    clearFieldComponents : function(){
      this._fieldComponents.clear();
    },
    handleValueChange : function(valueChangeEvent){
      var component = valueChangeEvent.getTarget();
      var value = valueChangeEvent.getValue();
      var conditions = this._conditions.get(component.getField().getFieldMd().getId());
      var fieldsToCheck = new com.runwaysdk.structure.HashSet();
      
      // if there are multiple values treat evaluation as a "contains" operation.
      if(Mojo.Util.isArray(value)){
        if(conditions){
        
          // first reset all conditions since we need to treat this set
          // of conditions as a fresh transaction
          for(var i=0; i<conditions.length; i++){
            var cond = conditions[i];
            cond._setTrue(false);
            fieldsToCheck.add(cond.getConditionMd().getReferencingMdField());
          }
          
          for(var i=0; i<conditions.length; i++){
            var cond = conditions[i];
            
            // we want to match on at least one value for each condition
            for(var j=0; j<value.length; j++){
              var v = value[j];
              
              cond.evaluate(v);
              
              if(cond.getOperation() === 'EQ' && cond.isTrue()){
                break; // we only need one match for EQ
              }
              else if(cond.getOperation() === 'NEQ' && !cond.isTrue()){
                break; // only one false match is enough to invalidate everything
              }
            }
          }
        }
      }
      else {
        // evaluate all conditions whose definingMdField is that of the FieldComponent that had
        // its value changed
        if(conditions){
          for(var i=0; i<conditions.length; i++)
          {
            var cond = conditions[i];
            cond.evaluate(value);
  
            fieldsToCheck.add(cond.getConditionMd().getReferencingMdField());
          }
        }
      }

      // for each field that has a condition check the
      // root condition (in the case of composites) such that all conditions
      // in the hierarchy are true. If so, show the field.
      var fieldsArr = fieldsToCheck.toArray();
      for(var i=0; i<fieldsArr.length; i++){
        var fieldComponent = this._fieldComponents.get(fieldsArr[i]);
        var cond = fieldComponent.getField().getCondition();
        if(cond.isTrue()){
          fieldComponent.show();
        }
        else {
          fieldComponent.hide();
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
    getSearchObject : function() {
      return this._searchObject;
    },    
    setSearchObject : function(searchObject) {
      this._searchObject = searchObject;
    },    
    createSearchObject : function(searchObjectJSON) {
      this.setSearchObject(this._WebFormObject.parseFromJSON(searchObjectJSON));        
    },    
    /**
     * Formats dates according to the DDMS specification.
     */
    dateColumnFormatter : function(o){
      return MDSS.Calendar.getLocalizedString(o.data[o.field]) || '';
    },
    numberColumnFormatter : function(o){
      var value = o.data[o.field];
      if(Mojo.Util.isNumber(value)){
        return MDSS.formatNumber(value);
      }
      else {
        return '';
      }
    },
    /**
     * Formats the view column by creating a link that contains a Runway object id for
     * dereferencing.
     */
    viewColumnFormatter : function(o){
      return '<span id="'+o.data.id+'" class="generatedViewCommand clickable">'+MDSS.localize('View')+'</span>';
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
      
      var fields = this._fieldComponents.values();
      for(var i=0; i<fields.length; i++){
        var field = fields[i];
        
        this.dispatchEvent(new PostRenderViewFieldEvent(field));
      }      
    },
    renderFormWithJSON : function (formObjectJSON) {
      this.createFormObject(formObjectJSON);
      this.renderForm();
    },
    newInstanceFromJSON : function (formObjectJSON) {     
      this.dispatchEvent(new PostNewInstanceEvent());
      
      this.renderFormWithJSON(formObjectJSON);
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
      //formContent.setStyle('visibility', 'hidden');
      
      this._formContainer.appendChild(formContent.getRawEl());
      
      // we are done visiting the form so all fields are constructed.
      // To initialize the proper show/hide status of the form, check
      // each value manually as if the value had been changed via the UI.
      // Show the form when we're done
      var fields = this._fieldComponents.values();
      for(var i=0; i<fields.length; i++){
        var field = fields[i];
        
        this.dispatchEvent(new PostRenderEditFieldEvent(field));
      }
      
      formContent.setStyle('visibility', 'visible');
    },
    /**
     * Updates the values on the FormObject with the current values
     * of the HTML form.
     */
    _updateValues : function(formObject)
    {
      if(formObject == null)
      {
        formObject = this._formObject;
      }
      
      var values = new com.runwaysdk.structure.HashMap(Mojo.Util.collectFormValues(formObject.getId()));
      var fields = formObject.getFields();
      
      for(var i=0, len=fields.length; i<len ; i++)
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
          else if(field instanceof FIELD.WebMultipleTerm)
          {
            var termIds = field.getTermIds();
            var json = Mojo.Util.toJSON(termIds);
            field.setValue(json);
          }
          else
          {
            field.setValue(value);
          }
        }
        // grids don't have a standard presense in the form as inputs
        else if(field instanceof FIELD.WebSingleTermGrid)
        {
          var grid = field.getGrid();
          
          if(grid != null)
          {            
            var rows = grid.getModel().getRows();
            for(var j=0; j<rows.length; j++)
            {
              var row = rows[j];
              var cols =  Mojo.Util.getKeys(row, true);
              for(var k=0; k<cols.length; k++)
              {
                var col = cols[k];
                var val = row[col];
                if(!Mojo.Util.isValid(val) || (Mojo.Util.isNumber(val) && isNaN(val)))
                {
                  delete row[col]; // removes it from the row to avoid a "null" string as the value
                }
              }
            }
            
            var json = Mojo.Util.toJSON(rows);
            field.setValue(json);
          }
          else
          {
            var json = Mojo.Util.toJSON([]);
            field.setValue(json);
          }
        }
      }
      
    },
    viewInstance : function(e){
      e.preventDefault();
      
      var id = e.target.get('id');
      
      this.viewInstanceById(id);
    },
    viewInstanceById : function(id){
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
      
      this._Y.all('.inline-alert').setStyle('display', 'none');
      this._Y.all('.field-content-node').setStyle('float', 'none');
      
      this._updateValues();
      
      this.dispatchEvent(new UpdateEvent(this.getFormObject()));      
    },
    /**
     * Creates the FormObject instances.
     */
    createInstance : function(e){
      e.preventDefault(); // prevent a synchronous form submit
      
      this._Y.all('.inline-alert').setStyle('display', 'none');
      this._Y.all('.field-content-node').setStyle('float', 'none');
      
      this._updateValues();

      this.dispatchEvent(new CreateEvent(this.getFormObject()));
    },
    editInstance : function(e){
      
      // prevent a synchronous form submit        
      if(e != null)
      {
        e.preventDefault(); 
      }
      
      this.dispatchEvent(new EditEvent(this._mdFormId, this.getFormObject() ));
    },
    /**
     * Cancels the creation or update of a FormObject.
     */
    cancelInstance : function(e){
      e.preventDefault();
        
      this.dispatchEvent(new CancelEvent(this.getFormObject()));
    },
    newInstance : function(e){      
      if(e != null)
      {
        e.preventDefault();        
      }
      
      this.dispatchEvent(new NewInstanceEvent(this._mdFormId));
    },
    createNewInstance : function(e)
    {
      if(e != null)
      {
        e.preventDefault();        
      }
      
      this._updateValues(this._searchObject);

      this.dispatchEvent(new CreateNewInstanceEvent(this._mdFormId, this._searchObject));
    },    
    deleteAll : function(e)
    {
      if(e != null)
      {
        e.preventDefault();        
      }
      
      // Ensure the user wants to delete all of the objects
      if(confirm(MDSS.localize('confirm_delete_all')))
      {
        this._updateValues(this._searchObject);
        
        this.dispatchEvent(new DeleteAllEvent(this._searchObject, this._mdClassType));
      }
    },    
    searchInstance : function(e)
    {
      if(e != null)
      {
        e.preventDefault();
      }
      
      this.dispatchEvent(new SearchEvent(this._searchObject));      
    },    
    hideAllInstance : function(){
      this._newInstanceCommand.hide();
      this._tableContainer.hide();
      
      if(this._search != null)
      {
        this._search.hide();
      }
      
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
      if(this._searchObject != null)
      {              
        this.dispatchEvent(new ViewAllEvent(this._searchObject));
      }
      else
      {
        var request = new MDSS.Request({
          that : this,
          onSuccess : function(formObjectJSON){
            this.that.createSearchObject(formObjectJSON);  
            
            var visitor = new FormObjectSearchVisitor(this.that, true, this.that._searchFilter);
            this.that.getSearchObject().accept(visitor);          
            
            // Add the action buttons
            var searchBtn = this.that.getFactory().newElement('button');
            searchBtn.setInnerHTML(MDSS.localize('Search'));
            searchBtn.getImpl().on('click', this.that.searchInstance, this.that);
            visitor.visitButton(searchBtn);

            var createBtn = this.that.getFactory().newElement('button');
            createBtn.setInnerHTML(MDSS.localize('Create'));
            createBtn.getImpl().on('click', this.that.createNewInstance, this.that);
            visitor.visitButton(createBtn);

            if(this.that._canDeleteAll)
            {              
              var deleteAllBtn = this.that.getFactory().newElement('button');
              deleteAllBtn.setInnerHTML(MDSS.localize('Delete_All'));
              deleteAllBtn.getImpl().on('click', this.that.deleteAll, this.that);            
              visitor.visitButton(deleteAllBtn);
            }
            
            var formContent = visitor.getNode();
            
            this.that._search.appendChild(formContent.getRawEl());
            
            // we are done visiting the form so all fields are constructed.
            // To initialize the proper show/hide status of the form, check
            // each value manually as if the value had been changed via the UI.
            // Show the form when we're done
            var fields = this.that._fieldComponents.values();
            for(var i=0; i<fields.length; i++){
              var field = fields[i];
              
              this.that.dispatchEvent(new PostRenderEditFieldEvent(field));
            }
            
            formContent.setStyle('visibility', 'visible');
            
            
            this.that.dispatchEvent(new ViewAllEvent(this.that._searchObject));
          }
        });
      
        dss.vector.solutions.form.FormObjectController.searchForm(request, this._mdFormId);        
      }      
    },
    renderViewAll : function(criteria){
      this.clearFormContainer();
      this._tableContainer.show();
                      
      if(this._search != null)
      {
        this._search.show();          
      }
        
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
        
      this._table.setCriteria(criteria);
        
      if(this._table.isRendered())
      {
        this._table.resetDataTable();
      }
      else
      {
        this._table.render('#'+this._tableContainer.get('id'));
      }
        
      this._newInstanceCommand.hide();
        
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
      var globalProblems = [];  // List of problems which don't have a corresponding field on the page
      
      for (var i = 0; i < problems.length; i++) {
        var p = problems[i];
        var attributeId = p.getAttributeId();

        var span = document.getElementById(attributeId);
        
        if(span != null)
        {  
          span.innerHTML = p.getLocalizedMessage();
          span.style.display = 'inline-block';
        
          var contentNode = span.parentNode.children.item(0);
          contentNode.style.cssFloat = 'left';
        }
        else
        {
          globalProblems.push(p);
        }
      }
      
      if(globalProblems.length > 0)
      {
        var msg = "";
        for (var i = 0; i < globalProblems.length; i++)
        {
          var p = globalProblems[i];
          
          msg += p.getLocalizedMessage() + "\n";
        }
        
        new MDSS.ErrorModal(msg);
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