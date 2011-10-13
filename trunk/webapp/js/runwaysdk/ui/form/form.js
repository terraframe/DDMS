/**
 * RunwaySDK Javascript UI Adapter for native Runway elements.
 * 
 * @author Terraframe
 */
(function(){

var RUNWAY_UI = Mojo.Meta.alias("com.runwaysdk.ui.*");
Mojo.RW_PACKAGE = Mojo.UI_PACKAGE+'RW.';
var STRUCT = Mojo.Meta.alias(Mojo.ROOT_PACKAGE+'structure.*', {});

var Visitable = Mojo.Meta.newInterface(Mojo.RW_PACKAGE+'Visitable', {
  Instance : {
    accept : function(visitor){}
  }
});

var Visitor = Mojo.Meta.newInterface(Mojo.RW_PACKAGE+'Visitor', {
  Instance : {
    visitForm : function(form){},
    visitTextInput : function(textInput){},
    visitTextArea : function(textArea){},
    visitHidden : function(hidden){},
    visitSelect : function(select){}
  }
});

var FormDisplay = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'FormDisplay', {
  IsAbstract : true,
  Implements : [RUNWAY_UI.FormComponentIF, RUNWAY_UI.ElementProviderIF, Visitable],
  Instance : {
    initialize : function(){
      this.$initialize();
    },
    getContentEl : function()
    {
      return this.getEl();
    }
  }
});

var Header = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'Header', {
  Extends : FormDisplay,
  Instance : {
    initialize : function(text)
    {
      this.$initialize();
      this._el = this.getFactory().newElement('h2');
      this._el.setInnerHTML(text);
    },
    accept : function(visitor)
    {
      visitor.visitHeader(this);
    },
    getEl : function()
    {
      return this._el;
    }
  }  
});

var Break = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'Break', {
  Extends : FormDisplay,
  Instance : {
    initialize : function(text)
    {
      this.$initialize();
      this._el = this.getFactory().newElement('br');
    },
    accept : function(visitor)
    {
      visitor.visitBreak(this);
    },
    getEl : function()
    {
      return this._el;
    }
  }  
});

var Comment = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'Comment', {
  Extends : FormDisplay,
  Instance : {
    initialize : function(text)
    {
      this.$initialize();
      this._el = this.getFactory().newElement('p');
      this._el.setInnerHTML(text);
    },
    accept : function(visitor)
    {
      visitor.visitComment(this);
    },
    getEl : function()
    {
      return this._el;
    }
  }  
});

var Label = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'Label', {
  Extends : FormDisplay,
  Instance : {
    initialize : function(text, elFor)
    {
      this.$initialize();
      this._text = text;
      this._el = this.getFactory().newElement('label', {htmlFor:elFor});
      this._el.setInnerHTML(text);
    },
    accept : function(visitor)
    {
      visitor.visitComment(this);
    },
    getEl : function()
    {
      return this._el;
    },
    getContentEl : function()
    {
      return this.getEl();
    },
    toString : function()
    {
      return this._text;
    }
  }
});

var FormControl = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'FormControl', {
  Implements : [RUNWAY_UI.FormComponentIF, RUNWAY_UI.ElementProviderIF, Visitable],
  IsAbstract : true,
  Instance : {
    initialize : function(name, config)
    {
      this.$initialize();
      this._name = name || this.getId();
      this._value = config.value || "";
      this._disabled = config.disabled || false;
      this._readonly = config.readonly || false;
      this.setName(this._name);
    },
    getEl : function()
    {
      return this._el;
    },
    getContentEl : function()
    {
      return this.getEl();
    },
    getChildren : function()
    {
      return this.getEl().getChildren();
    },
    getChild : function(id)
    {
      return this.getEl().getChild(id);
    },
    hasChild : function(child)
    {
      return this.getEl().hasChild(child);
    },
    getName : function()
    {
      return this.getEl().getRawEl().name;
    },
    setName : function(name)
    {
      this.getEl().getRawEl().name = name;
    }
  }
});

var TextInput = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'TextInput', {
  Extends : FormControl,
  Instance : {
    initialize : function(name, config)
    {
      this.$initialize(name, config);
      config = config || {};
      config.type = 'text';
      this._el = this.getFactory().newElement('input', config);
      this._el.setId(this._generateFormId());
    },
    _generateFormId : function() {
      return this.getId()+'_RW_TextInput';
    },
    accept : function(visitor)
    {
      visitor.visitTextInput(this);
    },
    getValue : function()
    {
      return this.getEl().getRawEl().value;
    }
  }
});

var TextArea = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'TextArea', {
  Extends : FormControl,
  Instance : {
    initialize : function(name, config)
    {
      this.$initialize(name, config);
      config = config || {};
      this._rows = config.rows || 5;
      this._cols = config.cols || 40;
      config.rows = this._rows;
      config.cols = this._cols;
      this._el = this.getFactory().newElement('textarea', config);
      this._el.setId(this._generateFormId());
    },
    _generateFormId : function() {
      return this.getId()+'_RW_TextArea';
    },
    accept : function(visitor)
    {
      visitor.visitTextArea(this);
    },
    getValue : function()
    {
      return this.getEl().getRawEl().value;
    }
  }
});

var Select = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'Select', {
  Extends : FormControl,
  Instance : {
    initialize : function(name, config)
    {
      this.$initialize(name, config);
      config = config || {};
      var options = config.options || [];
      this._el = this.getFactory().newElement('select', config);
      this._multiple = config.multiple || false;
      if (this._multiple)
      {
        this._el.getEl().getRawEl().multiple = "multiple";
      }
      this._el.setId(this._generateFormId());
      this._options = [];
    },
    addOption : function(name, valueOf, isSelected)
    {
      isSelected = isSelected || false;
      var opt = this.getFactory().newElement('option', {value: valueOf});
      if (isSelected)
      {
        opt.getEl().getRawEl().selected = "selected";
      }
      opt.setInnerHTML(name);
      this._options.push(name);
      this.getEl().appendChild(opt);
    },
    getOptions : function()
    {
      return this._options;
    },
    _generateFormId : function() {
      return this.getId()+'_RW_Select';
    },
    accept : function(visitor)
    {
      visitor.visitSelect(this);
    },
    isMultiple : function()
    {
      return this._multiple;
    },
    getValues : function()
    {
      var selectedOptions = [];
      var rawEl = this.getEl().getRawEl();
      for (var i = 0; i < rawEl.options.length; i++)
      {
        if (rawEl.options[i].selected)
        {
          selectedOptions.push(rawEl.options[i]);
          if (!this.isMultiple)
          {
            break;
          }
        }
      }
      return selectedOptions;
    }
  }
});

var HiddenInput = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'HiddenInput', {
  Extends : FormControl,
  Instance : {
    initialize : function(name, config)
    {
      this.$initialize(name, config);
      config = config || {};
      config.type = 'hidden';
      this._el = this.getFactory().newElement('input', config);
      this._el.setId(this._generateFormId());
    },
    _generateFormId : function() {
      return this.getId()+'_RW_HiddenInput';
    },
    accept : function(visitor)
    {
      visitor.visitHidden(this);
    },
    getValue : function()
    {
      return this.getEl().getRawEl().value;
    }
  }
});

var FormEntry = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'FormEntry', {
//  Implements : RUNWAY_UI.ComponentIF,
  Instance : {
    initialize : function(term, definition)
    {
      var id = this._generateId();
      this.setId(id);
      this._parent = null;
      this._rendered = false;
      this._isDestroyed = false;
      
      this._dt = this.getFactory().newElement('dt');
      this._dt.setId(this._generateTermId());
      this._dd = this.getFactory().newElement('dd');
      this._dd.setId(this._generateDefId());
      
      // FIXME ? is label.for using id or name of form input?
      //var defId;
      var defName;
      if (definition instanceof FormControl)
      {
        //defId = definition.getEl().getId();
        defName = definition.getName();
      }
      else
      {
        throw new com.runwaysdk.Exception("addEntry accepts only string for term.");
      }
      
      if (Mojo.Util.isString(term))
      {
        //term = new Label(term, defId);
        term = new Label(term, defName);
      }
      else
      {
        throw new com.runwaysdk.Exception("addEntry accepts only FormControl for definition.");
      }
      
      this._term = term;
      this._definition = definition;
      
      this._dt.appendChild(this._term);
      this._dd.appendChild(this._definition);
    },
    getTermEl : function()
    {
      return this._dt;
    },
    getDefEl : function()
    {
      return this._dd;
    },
    getDelimiterEl : function()
    {
      return this._delimiter;
    },
    getTerm : function()
    {
      return this._term;
    },
    getDef : function()
    {
      return this._definition;
    },
    _generateTermId : function() {
      return this.getId()+'_RW_Term';
    },
    _generateDefId : function() {
      return this.getId()+'_RW_Definition';
    },
    getManager: function()
    {
      return RUNWAY_UI.Manager.getInstance();
    },
    getFactory : function()
    {
      return this.getManager().getFactory();
    },
    setParent : function(parent)
    {
      if (Mojo.Util.isUndefined(parent)) {
        throw new com.runwaysdk.Exception("The argument to Component.setParent cannot be undefined.");
      }
      
      // Parent is allowed to be null
      if (parent !== null && !RUNWAY_UI.Util.isComponentIF(parent)) {
        throw new com.runwaysdk.Exception("The argument to Component.setParent must implement ComponentIF.");
      }
      
      this._parent = parent;
    },
    getParent : function()
    {
      return this._parent;
    },
    getId : function()
    {
      return this._id;
    },
    getHashCode : function()
    {
      return this.getId();
    },
    equals : function(obj)
    {
      var eq = this.$equals(obj);
      if(eq)
      {
        return true;
      }
      else if(obj instanceof Mojo.$.com.runwaysdk.Base
        && this.getId() === obj.getId())
      {
        return true;
      }
      
      return this === obj;
    },
    setId : function(id)
    {
      this._id = id;
    },
    _generateId : function()
    {
      return this.getMetaClass().getName()+'_'+Mojo.Util.generateId(16);
    },
    render : function()
    {
      this._rendered = true;
    },
    isRendered : function()
    {
      return this._rendered;
    },
    _setRendered : function(rendered)
    {
      this._rendered = rendered;
    },
  }
});


var Form = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'Form', {
  Extends : RUNWAY_UI.WidgetBase,
  Implements : [RUNWAY_UI.ElementProviderIF, Visitable],
  Instance : {
    initialize : function(header)
    {
      this.$initialize();
      this._header = header || "";
      this._el = this.getFactory().newElement('form');
      this._el.setId(this._generateFormId());
      this._formList = new FormList();
      this._el.appendChild(this._formList);
    },
    // Takes a string for term and a FormControl as definition
    addEntry : function(term, definition)
    {
      var formEntry = new FormEntry(term, definition);
      this._formList.addEntry(formEntry);
      return formEntry;
    },
    getEntries : function()
    {
      return this._formList.getEntries();
    },
    getEl : function(){
      return this._el;
    },
    getContentEl : function(){
      return this.getEl();
    },
    getImpl : function(){
      return this;
    },
    _generateFormId : function() {
      return this.getId()+'_RW_Form';
    },
    getChildren : function()
    {
      var children = this.$getChildren();
      var formListChildren = this._formList.getChildren();
      children = children.concat(formListChildren);
      return children;
    },
    accept : function(visitor)
    {
      visitor.visitForm(this);
      var inputs = this.getChildren();
      for (var i = 0; i < inputs.length; i++)
      {
        inputs[i].accept(visitor);
      }
    }
  }
});

var FormList = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'FormList', {
  Extends : RUNWAY_UI.Component,
  Implements : RUNWAY_UI.ElementProviderIF,
  Instance : {
    initialize : function()
    {
      this.$initialize();
      this._el = this.getFactory().newElement('dl');
      this._entries = new STRUCT.LinkedHashMap();
    },
    getEl : function()
    {
      return this._el;
    },
    getContentEl : function()
    {
      return this.getEl();
    },
    getChildren : function()
    {
      return this._entries.values();
    },
    getChild : function(id)
    {
      return this.getEl().getChild(id);
    },
    hasChild : function(child)
    {
      return this.getEl().hasChild(child);
    },
    addEntry : function(formEntry, delimiter)
    {
      delimiter = delimiter || this.getFactory().newElement('br');
      formEntry.setParent(this);
      this._entries.put(formEntry.getTerm().toString(), formEntry.getDef());
      this.getEl().appendChild(formEntry.getTermEl());
      this.getEl().appendChild(formEntry.getDefEl());
      this.getEl().appendChild(delimiter);
    },
    getEntries : function()
    {
      return this._entries;
    }
  }
});

var FormVisitor = Mojo.Meta.newClass(Mojo.RW_PACKAGE+'FormVisitor', {
  Implements : Visitor,
  Instance : {
    initialize : function()
    {
      this._values = new STRUCT.HashMap();
    },
    visitForm : function(form)
    {
      
    },
    visitTextInput : function(textInput)
    {
      var key = textInput.getName();
      var value = textInput.getValue();
      this._values.put(key, value);
    },
    visitTextArea : function(textArea)
    {
      var key = textArea.getName();
      var value = textArea.getValue();
      this._values.put(key, value);
    },
    visitHidden : function(hidden)
    {
      var key = hidden.getName();
      var value = hidden.getValue();
      this._values.put(key, value);
    },
    visitSelect : function(select)
    {
      var key = select.getName();
      var values = select.getValues();
      this._values.put(key, values);
    }
  }
});

})();