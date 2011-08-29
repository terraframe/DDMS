/**
 * RunwaySDK UI library.
 * 
 * @author Terraframe
 */
(function(){

Mojo.UI_PACKAGE = Mojo.ROOT_PACKAGE+'ui.';
Mojo.FORM_PACKAGE = {
  FORM: Mojo.ROOT_PACKAGE+'form.',
  WEB: Mojo.ROOT_PACKAGE+'form.web.',
  FIELD: Mojo.ROOT_PACKAGE+'form.web.field.',
  METADATA: Mojo.ROOT_PACKAGE+'form.web.metadata.'
};

// FIXME use module shortcut from RunwaySDK.js to access these (e.g., Mojo.EVENT)
var EVENT = Mojo.Meta.alias(Mojo.ROOT_PACKAGE+'event.*', {});
var STRUCT = Mojo.Meta.alias(Mojo.ROOT_PACKAGE+'structure.*', {});


var Manager = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'Manager', {
  IsSingleton : true,
  Instance : {
    initialize : function()
    {
      // set the default factory
      this._factory = new rwComponentFactory();
      
      this._components = new STRUCT.HashMap();
    },
    setFactory : function(factory)
    {
      factory = Mojo.Util.isString(factory) ? Mojo.Meta.findClass(factory) : factory;
      
      var meta = abstractFactoryIF.getMetaClass();
      if(meta.isInstance(factory))
      {
        this._factory = factory;
      }
      else
      {
        throw new com.runwaysdk.Exception('The provided factory ['+factory+'] is not a valid '+meta.getName()+' implementation.');
      }
    },
    getFactory : function()
    {
      return this._factory;
    }
  },
  Static : {
    setFactory : function(factory)
    {
      Manager.getInstance().setFactory(factory);
    },
    getFactory : function(factory)
    {
      return Manager.getInstance().getFactory();
    }
  }
});

/**
 * Abstract Factory to create a set of related UI widgets from distinct toolkits.
 */
var abstractFactoryIF = Mojo.Meta.newInterface(Mojo.UI_PACKAGE+'AbstractComponentFactoryIF', {
  Instance : {
    createElement : function(el, attributes, styles){}
  }
});

/**
 * The AbstractComponentFactoryIF implementation to create Runway UI components.
 */
var rwComponentFactory = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'RunwayComponentFactory', {
  Implements : abstractFactoryIF,
  Instance: {
    createElement : function(el, attributes, styles){
      if (ElementIF.getMetaClass().isInstance(el))
      {
        // Runway object (no work to be done)
        return el;
      }
      else if (Mojo.Util.isString(el))
      {
        return new HtmlElement(el, attributes, styles);
      }
      else if (Mojo.Util.isObject(el))
      {
        return new HtmlElement(el);
      }
    }
  }
});

/*
 * UI bridge Interfaces
 */

var ComponentIF = Mojo.Meta.newInterface(Mojo.UI_PACKAGE+'ComponentIF', {
  Instance : {
    getParent : function(){},
    getFactory : function(){},
    getManager : function(){},
    getId : function(){},
    _generateId : function(){},
    render : function(){},
    destroy : function(){}
  }
});

var CompositeIF = Mojo.Meta.newInterface(Mojo.UI_PACKAGE+'CompositeIF', {
  Extends : ComponentIF,
  Instance : {
    addComponent : function(component){},
    removeComponent : function(component){}
  }
});

var ContainerIF = Mojo.Meta.newInterface(Mojo.UI_PACKAGE+'ContainerIF', {
  Extends: CompositeIF,
  Instance : {
    addClassName : function(c){},
    setStyle : function(k,v){}
  }
});

/**
 * Simple interface that marks the any implementing class as operating on an underlying
 * ElementIF object. This interface is useful for classes that perform complex operations and
 * decorate Elements yet still want to expose the underlying ElementIF to DOM operations.
 */
var ElementProvider = Mojo.Meta.newInterface(Mojo.UI_PACKAGE+'ElementProvider', {
  Instance : {
    getEl : function(){}
  }
});

var ElementIF = Mojo.Meta.newInterface(Mojo.UI_PACKAGE+'ElementIF', {
  Extends : ComponentIF,
  Constants : {
    ELEMENT_NODE : 1,
    ATTRIBUTE_NODE : 2,
    TEXT_NODE : 3,
    CDATA_SECTION_NODE : 4,
    ENTITY_REFERENCE_NODE : 5,
    ENTITY_NODE : 6,
    PROCESSING_INSTRUCTION_NODE : 7,
    COMMENT_NODE : 8,
    DOCUMENT_NODE : 9,
    DOCUMENT_TYPE_NODE : 10,
    DOCUMENT_FRAGMENT_NODE : 11,
    NOTATION_NODE : 12
  },
  Instance : {
    getNodeName : function(){},
    getNodeValue : function(){},
    getNodeType : function(){},
    getParentNode : function(){},
    getChildNodes : function(){},
    getLastChild : function(){},
    getPreviousSibling : function(){},
    getNextSibling : function(){},
    getAttributes : function(){},
    getAttribute : function(key){},
    setAttribute : function(key, value){},
    getOwnerDocument : function(){},
    insertBefore : function(newChild, refChild){},
    replaceChild : function(newChild, oldChild){},
    removeChild : function(oldChild){},
    appendChild : function(newChild){},
    hasChildNodes : function(){},
    cloneNode : function(deep){},
    normalize : function(){},
    isSupported : function(feature, version){},
    getNamespaceURI : function(){},
    getPrefix : function(){},
    getLocalName : function(){},
    hasAttributes : function(){},
    getEl : function(){},
    setValue : function(value){},
    getValue : function(){},
  }
});

/*
 * DOM Interfaces
 */
var HtmlElementIF = Mojo.Meta.newInterface(Mojo.UI_PACKAGE+'HTMLElementIF', {
  Extends: ElementIF,
  Instance : {
    getElementsByClassName:function(classNames){},
    setInnerHTML:function(html){},
    getInnerHTML:function(){},
    setOuterHTML:function(html){},
    getOuterHTML:function(){},
    insertAdjacentHTML:function(position, text){},
    getId:function(){},
    setId:function(id){},
    getTitle:function(){},
    setTitle:function(title){},
    getLang:function(){},
    setLang:function(lang){},
    getDir:function(){},
    setDir:function(dir){},
    hasClassName:function(c){},
    addClassName:function(c){},
    getClassName:function(){},
		removeClassName:function(c){},
    getDataset:function(){},
    isHidden:function(){},
    setHidden:function(hidden){},
    click:function(){},
    scrollIntoView:function(top){},
    setStyle:function(){},
    getStyle:function(){},
    offsetLeft:function(){},
    offsetTop:function(){},
    offsetWidth:function(){},
    offsetHeight:function(){},
    getPos:function(){},
    getSize:function(){}
    /* TODO Add the selectors
     querySelector(),
     querySelectorAll(),
     */
  }
});

/*
 * Adapter classes
 */
var ComponentAdapter = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'ComponentAdapter',{
  Implements: [ComponentIF, EVENT.EventTarget],
  IsAbstract : true,
  Instance : {
    initialize : function(id)
    {
      this._id = id || this._generateId();
      this._parent = null;
      this._rendered = false;
      this._listeners = null;
    },
    getManager: function()
    {
      return Manager.getInstance();
    },
    getFactory : function()
    {
      return this.getManager().getFactory();
    },
    setParent : function(parent)
    {
      parent = Mojo.Util.isElement(parent) ? this.getFactory().wrapElement(parent) : parent;
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
      return 'com_'+Mojo.Util.generateId(16);
    },
    render : function()
    {
      this._rendered = true;
    },
    isRendered : function()
    {
      return this._rendered;
    },
    destroy : function()
    {
      // TODO remove handlers (bidirectional?)
    },

    /**
     * Dispatches the given event. Note that custom events do not support
     * a capturing phase.
     */
    dispatchEvent : function(evt)
    {
      if(evt.getEventPhase() === EVENT.EventIF.AT_TARGET)
      {
        evt._setTarget(this);
      }
      
      evt._setCurrentTarget(this);
      
      // dispatch the event for all listeners of this object (the current target)
      Mojo.$.com.runwaysdk.event.Registry.getInstance().dispatchEvent(evt);
            
      // simulate bubbling by dispatching the event on this object's parent
      if(evt.getBubbles() && !evt.getStopPropagation() && this.getParent() != null)
      {
        evt._setEventPhase(EVENT.EventIF.BUBBLING_PHASE);
        this.getParent().dispatchEvent(evt);
      }
      
      if(evt.getTarget().equals(this) && !evt.getPreventDefault())
      {
        evt.defaultAction();
      }
    }
  }
});

var CompositeAdapter = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'CompositeAdapter',{
  Extends: ComponentAdapter,
  Implements: CompositeIF,
  IsAbstract : true,
  Instance : {
    initialize : function(id)
    {
      this.$initialize(id);
      this._components = new STRUCT.HashMap();
    },
    addComponent : function(component)
    {
      this._components.put(component.getId(), component);
    },
    removeComponent : function(component)
    {
      var id = Mojo.Util.isString(component) ? component : component.getId();
      this._components.remove(id);
    },
    destroy : function()
    {
      var components = this._components.values();
      for(var i=0; i<components.length; i++)
      {
        components[i].destroy();
      }
      
      this._components = null;
      this.$destroy();
    }
  }
});

/**
 * Container class to hold other HTML elements or classes that implement
 * ElementWrapper, which provide underlying HTML elements.
 */
var ContainerAdapter = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'ContainerAdapter',{
  Extends: CompositeAdapter,
  Implements: ContainerIF,
  IsAbstract : true,
  Instance : {
    initialize : function(id)
    {
      this.$initialize(id);

      this._element = this.getFactory().createElement("div");
      this._element.setParent(this);
    },
    
    render : function(newParent)
    {
      this._element.render(newParent || DOMFacade.getBody());
      
      this.$render();
    },

    addComponent : function(child)
    {
      this.$addComponent(child);
      
      child = this.getFactory().createElement(child);
      this._element.appendChild(child);
    },
    
    addClassName : function(c)
    {
      this._element.addClassName(c);
    },
    
    setStyle : function(k,v)
    {
      this._element.setStyle(k,v);
    },
    
    getEl : function()
    {
      return this._element.getEl();
    }
  }
});

var ElementAdapter = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'ElementAdapter',{
  Extends: ComponentAdapter,
  Implements: [ElementIF, ElementProvider],
  IsAbstract : true,
  Instance : {
    initialize : function(el, attributes, styles)
    {
      if(Mojo.Util.isString(el))
      {
        this._el = DOMFacade.createElement(el);
      }
      else if(el instanceof Element)
      {
        this._el = el;
      }
      else
      {
        throw new com.runwaysdk.Exception('The first argument must be a node name or reference to an Element.');
      }
  
      DOMFacade.updateElement(this._el, attributes, styles);
  
      this.$initialize();
    },

    /**
     * Alias for addEventListener.
     */
    on : function()
    {
      this.addEventListener.apply(this, arguments);
    },

    /**
     * Alias for removeEventListener
     */
    remove : function()
    {
      this.removeEventListener.apply(this, arguments);
    },
    render : function(newParent)
    {
      var parent = this.dereference(newParent || this.getParent());
      parent.appendChild(this.getEl());
      
      this.$render();
    },
    getValue : function()
    {
      return this.getEl().value;
    },
    setValue : function(value)
    {
      // FIXME implement
    },
    getTextContent : function()
    {
      return DOMFacade.getTextContent(this.getEl());
      return el.textContent != null ? el.textContent : el.innerText;
    },
    setTextContent : function(text)
    {
      DOMFacade.setTextContent(this.getEl(), text);
      var el = this.getEl();
      if(el.textContent != null)
      {
        el.textContent = text;
      }
      else
      {
        el.innerText = text;
      }
    },
    getEl : function()
    {
      return this._el;
    },
    setId : function(id)
    {
      this.setAttribute('id', id);
      this.$setId(id);
    },
    _generateId : function()
    {
      var id = this.getAttribute('id');
      if(Mojo.Util.isString(id) && id.length > 0)
      {
        return id;
      }
      else
      {
        var newId = 'el_'+Mojo.Util.generateId(16);
        this.setAttribute('id', newId);
        return newId;
      }
    },
    dereference : function(el)
    {
      return Mojo.Util.isElement(el) ? el : el.getEl();
    },
    setAttribute : function(attr, value)
    {
      DOMFacade.setAttribute(this.getEl(), attr, value);
    },
    getAttribute : function(attr)
    {
      DOMFacade.getAttribute(this.getEl(), attr);
    },
    getNodeName : function()
    {
      return this.getEl().nodeName;
    },
    getNodeValue : function()
    {
      return this.getEl().nodeValue;
    },
    getNodeType : function()
    {
      return this.getEl().nodeType;
    },
    getParentNode : function()
    {
      return this.getEl().parentNode;
    },
    getChildNodes : function()
    {
      return this.getEl().childNodes;
    },
    getLastChild : function()
    {
      return this.getEl().lastChild;
    },
    getPreviousSibling : function()
    {
      return this.getEl().previousSibling;
    },
    getNextSibling : function()
    {
      return this.getEl().nextSibling;
    },
    getAttributes : function()
    {
      return this.getEl().getAttributes();
    },
    getAttribute : function(key)
    {
      return this.getEl().getAttribute(key);
    },
    setAttribute : function(key, value)
    {
      this.getEl().setAttribute(key, value);
    },
    getOwnerDocument : function()
    {
      return this.getEl().ownerDocument;
    },
    insertBefore : function(newChild, refChild)
    {
      newChild = this.dereference(newChild);
      refChild = this.dereference(refChild);
      return this.getEl().insertBefore(newChild, refChild);
    },
    replaceChild : function(newChild, oldChild)
    {
      newChild = this.dereference(newChild);
      oldChild = this.dereference(oldChild);
      return this.getEl().replaceChild(newChild, oldChild);
    },
    removeChild : function(oldChild)
    {
      oldChild = this.dereference(oldChild);
      return this.getEl().removeChild(oldChild);
    },
    appendChild : function(newChild)
    {
      newChild.setParent(this);
      newChild = this.dereference(newChild);
      return this.getEl().appendChild(newChild);
    },
    hasChildNodes : function()
    {
      return this.getEl().hasChildNodes();
    },
    cloneNode : function(deep)
    {
      return this.getEl().cloneNode(deep);
    },
    normalize : function()
    {
      this.getEl().normalize();
    },
    isSupported : function(feature, version)
    {
      this.getEl().isSupported(feature, version);
    },
    getNamespaceURI : function()
    {
      return this.getEl().getNamespaceURI();
    },
    getPrefix : function()
    {
      return this.getEl().prefix;
    },
    getLocalName : function()
    {
      return this.getEl().localName;
    },
    hasAttributes : function()
    {
      return this.getEl().hasAttributes();
    },
    toString : function()
    {
      return 'Element: ['+this.getAttribute('id')+'].';
    },
    destroy : function()
    {
      this.$destroy();
      // TODO remove all event listeners
      //this.setEl(null);
    },
  }
  
});

var HtmlElementAdapter = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'HTMLElementAdapter',{
  Extends: ElementAdapter,
  Implements: HtmlElementIF,
  IsAbstract : true,
  Instance : {
    initialize : function(el, attributes, styles)
    {
      this.$initialize(el, attributes, styles);
    },
    render : function(newParent)
    {
      var parent = this.dereference(newParent || this.getParent() || DOMFacade.getBody());
      this.$render(parent);
    },
    getElementsByClassName:function(classNames)
    {
      return this.getEl().getElementsByClassName(className);
    },
    setInnerHTML:function(html)
    {
      this.getEl().innerHTML = html;
    },
    getInnerHTML:function()
    {
      return this.getEl().innerHTML;
    },
    setOuterHTML:function(html)
    {
      this.getEl().outerHTML = html;
    },
    getOuterHTML:function()
    {
      return this.getEl().outerHTML;
    },
    insertAdjacentHTML:function(position, text)
    {
      this.getEl().insertAdjacentHTML(position, text);
    },
    getTitle:function()
    {
      return this.getEl().getAttribute('title');
    },
    setTitle:function(title)
    {
      this.getEl().setAttribute('title', title);
    },
    getLang:function()
    {
      return this.getEl().getAttribute('lang');
    },
    setLang:function(lang)
    {
      this.getEl().setAttribute('lang', lang);
    },
    getDir:function()
    {
      return this.getEl().getAttribute('dir');
    },
    setDir:function(dir)
    {
      this.getEl().setAttribute('dir', dir);
    },
    addClassName : function(c)
    {
      DOMFacade.addClassName(this, c);
    },
    hasClassName : function(c)
    {
      return DOMFacade.hasClassName(this, c);
    },
    removeClassName : function(c)
    {
      DOMFacade.removeClassName(this, c);
    },
    getClassName : function()
    {
      return DOMFacade.getClassName(this);
    },
    getDataset:function()
    {
      return this.getEl().dataset;// TODO use getAttribute() AND verify this property
    },
    isHidden:function()
    {
      return this.getEl().hidden; // TODO use getAttribute()/getStyle() AND verify this property
    },
    setHidden:function(hidden)
    {
      this.getEl().hidden = hidden; // TODO use setAttribute()/setStyle() AND verify this property
    },
    click:function()
    {
      this.getEl().click();
    },
    scrollIntoView:function(top)
    {
      this.getEl().scrollIntoView(top);
    },
		setStyle : function(style, value)
    {
      DOMFacade.setStyle(this.getEl(), style, value);
    },
		getStyle : function(style)
    {
      return DOMFacade.getStyle(this.getEl(), style);
    },
    offsetLeft : function()
    {
      return this.getEl().offsetLeft;
    },
    offsetTop : function()
    {
      return this.getEl().offsetTop;
    },
    offsetWidth : function()
    {
      return this.getEl().offsetWidth;
    },
    offsetHeight : function()
    {
      return this.getEl().offsetHeight;
    },
    getSize : function()
    {
      return DOMFacade.getSize(this);
    },
    getPos : function()
    {
      return DOMFacade.getPos(this);
    }
  }
});

/*
 * Runway implementations
 */
var Component = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'Component', {
  Extends : ComponentAdapter,
  Instance : {
    initialize : function(id)
    {
      this.$initialize(id);
    }
  }
});

var Composite = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'Composite', {
  Extends : CompositeAdapter,
  Instance : {
    initialize : function(id)
    {
      this.$initialize(id);
    }
  }
});

var HtmlElement = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'HTMLElement', {
  Extends : HtmlElementAdapter,
  Instance : {
  initialize : function(el, attributes, styles)
  {
    this.$initialize(el, attributes, styles);
  }
}

});

var Container = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'Container', {
  Extends : ContainerAdapter,
  Instance : {
    initialize : function(id)
    {
      this.$initialize(id);
    }
  }
});

/**
 * Helper class to access and modify the dom in a safe, cross-browser manner.
 */
var DOMFacade = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'DOMFacade', {
  
  Static : {
  
    get : function(el)
    {
			if (el.getEl) // Wrapped Object
        return el.getEl();
    
      return Mojo.Util.isString(el) ? document.getElementById(el) : el;
    },
    
    createElement : function(type, attributes, styles)
    {
      var el = document.createElement(type);
     
      DOMFacade.updateElement(el, attributes, styles);
      
      return el;
    },
    
    updateElement : function(el, attributes, styles)
    {
      el = DOMFacade.get(el);
  
      if(Mojo.Util.isObject(attributes))
      {
        for(var attr in attributes)
        {
          DOMFacade.setAttribute(el, attr, attributes[attr]);
        }
      }

      if(Mojo.Util.isObject(styles))
      {
        for(var style in styles)
        {
          DOMFacade.setStyle(el, style, styles[style]);
        }
      }
    },
    getTextContent : function(el)
    {
    // FIXME null check may not be sufficient
      return el.textContent != null ? el.textContent : el.innerText;
    },
    setTextContent : function(el, text)
    {
    // FIXME null check may not be sufficient
      if(el.textContent != null)
      {
        el.textContent = text;
      }
      else
      {
        el.innerText = text;
      }
    },
    setAttribute : function(el, key, value)
    {
      el = DOMFacade.get(el);
      el[key] = value;
    },
    
    getAttribute : function(el, key)
    {
      el = DOMFacade.get(el);
      return el[key];
    },
    
    setStyle : function(el, key, value)
    {
      el = DOMFacade.get(el);
      return el.style[key] = value;
    },
    
    getStyle : function(el, key)
    {
      el = DOMFacade.get(el);
      return el.style[key];
    },
		
		addClassName : function(el, c)
    {
      return this.setAttribute(el, "className", this.getAttribute(el, "className") + " " + c);
    },
		
    hasClassName : function(el, c)
    {
			var reg = new RegExp( "(^|\\s+)" + c + "(\\s+|$)" );
      return reg.test( this.getClassName(el) );
    },
		 
		removeClassName : function(el, c)
		{
		  el = DOMFacade.get(el);
			c = Mojo.Util.trim(c);
			
      if (this.hasClassName(el, c))
			{
          el.className = el.className.replace(' ' + c + ' ',' ');
      }
		},
		
		getClassName : function(el)
		{
			return this.getAttribute(el, "className");
		},
    
    getText : function(el)
    {
      el = DOMFacade.get(el);
      return el.textContent != null ? el.textContent : el.innerText;
    },
    
    getBody : function()
    {
      return document.body; // FIXME make cross-browser and iframe compatible
    },
    
    setText : function(el, text)
    {
      el = DOMFacade.get(el);
      if(el.textContent != null)
      {
        el.textContent = text;
      }
      else
      {
        el.innerText = text;
      }
    },
		
		getClassName : function(el)
		{
			return this.getAttribute(el, "className");
		},
    
		getPos : function(e)
    {
      e = DOMFacade.get(e);
      
      var left = 0;
      var top = 0;
      var l;
      
      if (Mojo.Util.isFunction(e.offsetLeft) && (l = e.offsetLeft()) != 0)
      {
        left += l;
        top += e.offsetTop();
      }
      else if (Mojo.Util.isNumber(e.offsetLeft) && (l = e.offsetLeft) != 0)
      {
        left += l;
        top += e.offsetTop;
      }
      else
      {
        left += parseInt(DOMFacade.getStyle(e, "left"));
        top += parseInt(DOMFacade.getStyle(e, "top"));
      }
      
      return { x:left, y:top };
    },
    
    getSize : function(e)
    {
      e = DOMFacade.get(e);
      
      var w;
      var h;
      
      if (Mojo.Util.isFunction(e.offsetWidth) && (w = e.offsetWidth()) != 0 && w != null)
      {
        h = e.offsetHeight();
      }
      else if (Mojo.Util.isNumber(e.offsetWidth) && (w = e.offsetWidth) != 0 && w != null)
      {
        h = e.offsetHeight;
      }
      else
      {
        w = parseInt(DOMFacade.getStyle(e,"width"));
        h = parseInt(DOMFacade.getStyle(e,"height"));
      }
      
      return { x:w, y:h };
    },
    
    getHeight : function(e)
    {
      e = DOMFacade.get(e);
      
      var h;
      
      if (Mojo.Util.isFunction(e.offsetHeight) && (h = e.offsetHeight()) != 0 && h != null)
      {
      }
      else if (Mojo.Util.isNumber(e.offsetHeight) && (h = e.offsetHeight) != 0 && h != null)
      {
      }
      else
      {
        h = parseInt(DOMFacade.getStyle(e,"height"));
      }
      
      return h;
    },
    
    getWidth : function(e)
    {
      e = DOMFacade.get(e);
      
      var w;
      
      if (Mojo.Util.isFunction(e.offsetWidth) && (w = e.offsetWidth()) != 0 && w != null)
      {
      }
      else if (Mojo.Util.isNumber(e.offsetWidth) && (w = e.offsetWidth) != 0 && w != null)
      {
      }
      else
      {
        w = parseInt(DOMFacade.getStyle(e,"width"));
      }
      
      return w;
    },
    
    setSize : function(e,w,h)
    {
      DOMFacade.setWidth(e,w);
      DOMFacade.setHeight(e,h);
    },
    
    setHeight : function(e,h)
    {
      if (typeof h == "number")
        h = h + "px";
        
      DOMFacade.setStyle(e, "height", h);
    },
    
    setWidth : function(e,w)
    {
      if (typeof w == "number")
        w = w + "px";
        
      DOMFacade.setStyle(e, "width", w);
    }
  
  }
});

var Util = Mojo.Meta.newClass(Mojo.UI_PACKAGE+"Util", {
  
  IsAbstract : true,

  Static : {
  
    getMousePos : function(ev)
		{
      if (ev.pageX || ev.pageY)
      {
        return { x: ev.pageX, y: ev.pageY };
      }
			
      return {
               x: ev.clientX + document.body.scrollLeft - document.body.clientLeft,
               y: ev.clientY + document.body.scrollTop - document.body.clientTop
      };
		},
    
    isWithinBounds : function( pos, x, y, w, h ) // calcs if pos{x,y} is within the rectangle defined by x,y,w,h
    {
      if (pos.x > x && pos.y > y && pos.x < x + w && pos.y < y + h)
        return true;
      return false;
    },
    
    isInsideElement : function( pos, el ) // calcs if pos is inside the element
    {
      var ePos = DOMFacade.getPos(el);
      var size = DOMFacade.getSize(el);
      return this.isWithinBounds( pos, ePos.x, ePos.y, size.x, size.y );
    },
    
    getScreenSize : function()
    {
      var h = 512;
      var w = 512;
      
      if (self.innerHeight)
      {
        w = self.innerWidth;
        h = self.innerHeight;
      }
      else if (document.documentElement && document.documentElement.clientHeight)
      {
        w = document.documentElement.clientWidth;
        h = document.documentElement.clientHeight;
      }
      else if (document.body)
      {
        w = document.body.clientWidth;
        h = document.body.clientHeight;
      }
      
      return { x:w, y:h };
    },
    
    getScrH : function()
    {
      return Util.getScreenSize().y;
    },
    
    getScrW : function()
    {
      return Util.getScreenSize().x;
    },
		
		// Prevents users from highlighting (selecting) the element
		disableSelection : function(el)
		{
			// this function is currently needed for drag drop
			// FIXME : do this via event prevent default
			el = DOMFacade.get(el);
			
      el.onselectstart = function() { return false; };
      el.unselectable = "on";
      el.style.MozUserSelect = "none";
		},
    
    max : function(a, b)
    {
      if (a > b)
        return a;
      
      return b;
    }
  }

});
var DragDrop = Mojo.Meta.newClass(Mojo.UI_PACKAGE+"DragDrop", {

  IsSingleton : true,
	
	Instance : {
		
		initialize: function()
    {
      this.$initialize();
      
      this._dragHandles = new com.runwaysdk.structure.HashMap();
    },
		
		makeDraggable : function(el, dragHandle)
    {
			el = DOMFacade.get(el);
			dragHandle = DOMFacade.get(dragHandle);
			
      if (dragHandle != undefined)
			{
				  this._dragHandles.put(el.id, dragHandle);
			}
			
			Util.disableSelection(el);
			DOMFacade.addClassName(el, "draggable");
    },
		
		handleEvent : function(event)
    {
      switch(event.type)
      {
        case "mousedown":
          if (DOMFacade.hasClassName(event.target, "draggable"))
          {
            this._dragObj = this._dragHandles.get(event.target.id) || event.target;
            this._mouseDownPos = Util.getMousePos(event);
            
            var dragObjPos = DOMFacade.getPos(this._dragObj);
            this._mouseClickOffset = { x: this._mouseDownPos.x - dragObjPos.x, y: this._mouseDownPos.y - dragObjPos.y };
            
            OverlayManager.bringToFront(this._dragObj);
          }
          break;
        
        case "mousemove":
          if (this._dragObj != null)
          {
            var mousePos = Util.getMousePos(event);
            
					  DOMFacade.setStyle(this._dragObj, "left", mousePos.x - this._mouseClickOffset.x + "px");
						DOMFacade.setStyle(this._dragObj, "top", mousePos.y - this._mouseClickOffset.y + "px")
          }
          break;
        
        case "mouseup":
          this._dragObj = null;
          break;
      }
    }
		
	},

  Static : {
    
		makeDraggable : function(el, dragHandle)
		{
			this.getInstance().makeDraggable(el, dragHandle);
		},
    
    enable : function()
    {
			var hand = Mojo.Util.bind(this.getInstance(), this.getInstance().handleEvent);
			
			// FIXME
      document.addEventListener("mousedown", hand, false);
      document.addEventListener("mousemove", hand, false);
      document.addEventListener("mouseup", hand, false);
    },
    
    disable : function()
    {
			var hand = Mojo.Util.bind(this.getInstance(), this.getInstance().handleEvent);
			
			// FIXME
      document.removeEventListener("mousedown", hand, false);
      document.removeEventListener("mousemove", hand, false);
      document.removeEventListener("mouseup", hand, false);
    }
    
  }

});

var OverlayManager = Mojo.Meta.newClass(Mojo.UI_PACKAGE+"OverlayManager", {

  IsSingleton : true,

  Instance : {
    
    initialize: function()
    {
      this.$initialize();
      
      this._topIndex = 1;
      this._bottomIndex = -1;
      
      this._set = new com.runwaysdk.structure.HashSet();
    },
    
    appendOverlay : function(overlay)
    {
      if (!this.isManaged(overlay))
      {
        this._set.add(overlay);
        this.bringToFront(overlay);
      }
    },
    
    removeOverlay : function(overlay)
    {
      if (this.isManaged(overlay))
      {
        this._set.remove(overlay);
        this.bringToIndex(overlay, 0);
      }
    },
    
    isManaged : function(obj)
    {
      return this._set.contains(obj);
    },
    
    getOverlays : function()
    {
      return this._set;
    },
    
    bringToFront : function(el)
    {
      //if (this.isManaged(el))
      //{
        this.bringToIndex(el, this._topIndex)
      
        this._topIndex++;
     //}
    },
    
    bringToBack : function(el)
    {
      //if (this.isManaged(el))
      //{
        this.bringToIndex(el, this._bottomIndex)
      
        this._bottomIndex--;
      //}
    },
    
// Private:
    bringToIndex : function(el, index)
    {
			DOMFacade.setStyle(el, "z-index", index);
			DOMFacade.setStyle(el, "zIndex", index);
    }
    
  },
  
  Static : {
    
    appendOverlay : function(el)
    {
      OverlayManager.getInstance().appendOverlay(el);
    },
    
    removeOverlay : function(el)
    {
      OverlayManager.getInstance().removeOverlay(el);
    },
    
    bringToFront : function(el)
    {
      OverlayManager.getInstance().bringToFront(el);
    },
    
    bringToBack : function(el)
    {
      OverlayManager.getInstance().bringToBack(el);
    },
    
    getOverlays : function()
    {
      return OverlayManager.getInstance().getOverlays();
    }
    
  }

});
var Overlay = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'Overlay', {
  
  Extends : Container,
  
  Instance : {
    
    initialize: function(id, isModal)
    {
      this.$initialize(id);
      
      if (isModal)
      {
        this._Dimmer = this.getFactory().createElement("div", null, this._createDimmerStyle());
        this.addComponent(this._Dimmer);
      }
      
      DOMFacade.updateElement(this, null, this._createStyle());
      
      this._isModal = isModal;
    },
    
    _createStyle : function()
    {
      var style = {};
      
      style.position = "absolute";
      style.top = "50%";
      style.left = "50%";
      
      return style;
    },
    
    _createDimmerStyle : function ()
    {
      var style = {}
      
      style.position = "fixed";
      style.top = "0px";
      style.left = "0px";
      style.width = "100%";
      style.height = "100%";
      style.backgroundColor = "#000000";
      style.filter = "alpha(opacity=60)";
      style.opacity = "0.6";
      
      return style;
    },
    
    show : function()
    {
      this.setStyle("display", "inline");
      
      if (this._Dimmer)
        this._Dimmer.setStyle("display", "inline")
    },
    
    hide : function()
    {
      this.setStyle("display", "none");
      
      if (this._Dimmer)
        this._Dimmer.setStyle("display", "none")
    },
    
    bringToFront : function()
    {
      OverlayManager.bringToFront(this);
    },
    
    render : function(parent)
    {
      if (this._Dimmer)
      {
        this._Dimmer.render(parent);
        OverlayManager.appendOverlay(this._Dimmer);
      }
      
      this.$render(parent);
      OverlayManager.appendOverlay(this);
    },
    
    destroy : function()
    {
      OverlayManager.removeOverlay(this._Dimmer);
      OverlayManager.removeOverlay(this);
      
      this.$destroy();
    }
    
  }
});
var Dialog = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'Dialog', {
  
  Extends : Overlay,
  
  Instance : {
    
    initialize : function(id, config)
    {
			// There are several ways to call this constructor:
			// (id, config)
			// (config)
			// (titleContent)
			
      if (typeof id == "object" && typeof config == "undefined")
        config = id;
			else if (typeof id == "string" && typeof config == "undefined")
			  config = {titleContent:id}
      else if (typeof config == "undefined")
        config = {};
      
      if (typeof id == "string")
        this.$initialize(id, config.isModal);
      else
        this.$initialize(null, config.isModal);
		  
			var dialogStyle = this._createStyle();
      
      // Title Bar
      this._divTitle = this.getFactory().createElement("div", null, (config.titleStyle || this._createTitleStyle()));
			DragDrop.makeDraggable(this._divTitle, this);
      this._divTitle.setInnerHTML(config.titleContent || "Dialog"); // Set title label text the easy way
      this.addComponent(this._divTitle);
      
      // Title Label (The hard and buggy way)
      /*
      this._pTitleLabel = titleLabel || this.getFactory().createElement("p");
      DOMFacade.updateElement(this._pTitleLabel, null, this.createTitleLabelStyle());
      this._pTitleLabel.setInnerHTML(titleLabelText);
      this._divTitle.appendChild(this._pTitleLabel);
      // Center the label
      this.render();
      var titleSize = Util.getSize(this._divTitle);
      var lblSize = Util.getSize(this._pTitleLabel);
      this._pTitleLabel.setStyle("left", (titleSize.x/2 - lblSize.x/2) + "px");
      this._pTitleLabel.setStyle("top", (titleSize.y/2 - lblSize.y/2) + "px");
      */
      
      // Div for Close Button(X)
      this._divClose = this.getFactory().createElement("div");
			DragDrop.makeDraggable(this._divClose, this);
      this._divClose.setStyle("float", "right");
      this._divClose.setStyle("cssFloat", "right"); // Firefox
      this._divTitle.appendChild(this._divClose);
      this.addComponent(this._divClose);
      
      // Close Button (X)
      var dialog = this;
      this._bClose = this.getFactory().createElement("input", {type:"button", value:"X", onclick: function() {dialog.hide()}}, {height:dialogStyle.buttonHeight + "px"});
      this._divClose.appendChild(this._bClose);
     
      // Content
      var contentStyle = this._createContentStyle();
      this._divContent = this.getFactory().createElement("div", null, contentStyle);
      this.addComponent(this._divContent);
      
      // Div for buttons
      this._divButtons = this.getFactory().createElement("div");
      this.addComponent(this._divButtons);
      var size = {x:parseInt(contentStyle.width), y:parseInt(contentStyle.height)};
      //this._divButtons.setStyle("height", dialogStyle.buttonHeight + "px");
      //this._divButtons.setStyle("width", size.x);
      //this._divButtons.setStyle("display", "inline-block");
      this._divButtons.setStyle("margin", "0px");
      this._divButtons.setStyle("padding", "0px");
      this._divButtons.setStyle("left", "0px");
      
      this._buttons = new com.runwaysdk.structure.HashSet();
    },
		_createStyle : function(onlyNeedWidth)
		{
			var style = this.$_createStyle();
			
			//style["min-width"] = "200px";
      //style["min-height"] = "200px";
			
			if (onlyNeedWidth) // fix for circular dependencies
        return style;
			
			style.buttonHeight = 20; // in px
			
			style.backgroundColor = "white";
			style.border = "1px solid black";
			style.height = style.buttonHeight + parseInt(this._createTitleStyle().height) + parseInt(this._createContentStyle().height);
      style.margin = "0px";
      style.padding = "0px";
      //style.display = "inline-block";

			return style;
		},
    _createTitleStyle : function()
    {
      var style = {};
			
			var dialogStyle = this._createStyle(true);
      
      style.width = "100%"; //parseInt(dialogStyle.width);
      style.height = "25px";
      style.backgroundColor = "gray";
      style.cursor = "move";
			style["border-bottom"] = "1px solid black";
      style.margin = "0px";
      style.padding = "0px";
      style.display = "block"
      
      // Label
      style["font-size"] = parseInt(style.height) - 4 + "px";
      style["text-align"] = "center";
      style.color = "white";
      
      return style;
    },
    _createContentStyle : function()
    {
      var titleStyle = this._createTitleStyle();
      var dialogStyle = this._createStyle(true);
		
      var style = {};
      
      //style.width = "100%"; //parseInt(dialogStyle.width);
      //style.height = "200px";
      //style.backgroundColor = "white";
      //style.border = titleStyle.border;
      style.display = "inline-block";
      style.margin = "0px";
      style.padding = "0px";
      
      return style;
    },
    setTitleStyle : function(k,v)
    {
      this._divTitle.setStyle(k,v);
    },
    setTitleContent : function(html)
    {
      this._divTitle.setInnerHTML(html);
    },
    /*
    createTitleLabelStyle : function()
    {
      var titleStyle = this.createTitleStyle();
      
      var style = {};
      
      var height = parseInt(titleStyle.height) - 4 + "px";
      //var width = 
      
      style.position = "absolute";
      //style.top = "2px"; //((parseInt(titleStyle.height) - height)/2) + "px";
      style["font-size"] = height;
      //style["text-align"] = "center";
      //style.left = "50%";
      //style["text-align"] = "center";
      style.color = "white";
      style.backgroundColor = "purple";
      style.height = height;
      //style.width = "40px";
      style["z-index"] = zIndex.next();
      style.zIndex = zIndex.current();
      style["margin-top"] = "0px"; //(parseInt(style.height) - parseInt(style["font-size"]))/2 + "px";
      
      return style;
    },
    */
    setStyle : function(k,v)
    {
      if (k === "visibility") // forward visibility to dimmer, otherwise it overrides it since it was set by dimScreen
        this._Dimmer.setStyle(k,v);
        
      return this.$setStyle(k,v);
    },
    appendContent : function(content)
    {
      this._divContent.appendChild(content);
    },
    setContentHTML : function(html)
    {
      this._divContent.setInnerHTML(html);
    },
    getContentHTML : function()
    {
      return this._divContent.getInnerHTML();
    },
    addDialogButton : function(label, handler, context)
    {
      var button = new Button(label, handler, context);
      
      this._buttons.add(button, handler);
      this._divButtons.appendChild(button);
      this.addComponent(button);
      
      // button styles
      button.setStyle("float", "right");
      button.setStyle("cssFloat", "right");
      button.setStyle("position", "relative");
			button.setStyle("height", this._createStyle().buttonHeight + "px")
      
      return button;
    },
    destroy : function()
    {
      delete this._Dimmer;
      delete this._divTitle;
      delete this._divClose;
      delete this._bClose;
      delete this._divContent;
      this._buttons.removeAll();
      this.getEl().parentNode.removeChild(this.getEl());
      this.$destroy();
    }
  }
  
});

var Button = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'Button', {
  
  Extends : HtmlElement,
  
  Instance : {
    initialize : function(label, handler, context) {
      var boundHandler;
      if (context)
        boundHandler = Mojo.Util.bind(context, handler);
      else
        boundHandler = handler;
      this._button = this.getFactory().createElement("input", {type:"button", value:label, onclick: boundHandler});
      
      this.$initialize(this._button.getEl());
    },
    
    getEl : function()
    {
      return this._button.getEl();
    }
  }
});

var DataSourceIF = Mojo.Meta.newInterface(Mojo.UI_PACKAGE+'DataSourceIF', {

  Instance : {
    getData:function(){}
  }
  
});

var DataSource = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'DataSource', {

  Implements:[DataSourceIF],
	
	Instance : {
		
		initialize : function(data)
    {
      this.$initialize();
    },
    
    getData : function()
    {
      
    }
    
  }
  
});

var ArrayDataSource = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'ArrayDataSource', {

  Extends : DataSource,

  Implements:[DataSourceIF],
  
  Instance : {
    
    initialize : function(data)
    {
      this.$initialize();
      
      this._data = data;
    },
    
    getData : function()
    {
      return this._data;
    }
    
  }
  
});

var DTODataSource = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'DTODataSource', {

  Extends : DataSource,

  Implements:[DataSourceIF],
  
  Instance : {
    
    initialize : function()
    {
      this.$initialize();
    },
    
    onFailure : function(e)
    {
      throw new Exception("An error occured while fetching data: " + e.getLocalizedMessage());
    },
    
    onSuccess : function(dataClass, callback)
    {
      Y.log("Data fetch success", "debug");
      
      this._dataClassInstance = dataClass;
      callback();
    },
    
    defineDTO : function(dataClass, id)
    {
      this._dataClass = dataClass;
      this._id = id;
    },
    
    fetchData : function(callback)
    {
      Y.log("Fetching data", "debug");
      
      var myThis = this;
      
      var clientRequest = new Mojo.ClientRequest({onFailure:this.onFailure, onSuccess: function(dc){Y.log("called","debug");myThis.onSuccess(dc, callback)}});
      
      this._dataClass.get(clientRequest, this._id);
    },
    
    getData : function()
    {
      if (this._dataClassInstance && this._keyMap)
      {
        var array = new Array();
        
        var headers = new Array();
        for (key in this._keyMap)
        {
          headers.push(key);
        }
        array.push(headers);
        
        var values = new Array();
        for (key in this._keyMap)
        {
          if (Mojo.Util.isFunction(this._dataClassInstance[this._keyMap[key]])) {
            var value = this._dataClassInstance[this._keyMap[key]]();
            values.push(value);
          }
        }
        array.push(values);
        
        return array;
      }
      else {
        throw new Exception("Attempt to getData, but data has not been fetched yet!");
      }
    },
    
    mapKeys : function(keyMap)
    {
      this._keyMap = keyMap;
    }
    
  }
  
});

var Column = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'Column', {
  
  Extends : Component,
  
  Instance : {
    initialize : function(config) {
      this.sortable = config.sortable || true;
      this.resizable = config.resizable || true;
      this.draggable = config.draggable || true;
    }
  }
});

var Row = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'Row', {
  
  Extends : HtmlElement,
  
  Instance : {
    initialize : function(config) {
      this._tr = this.getFactory().createElement("tr");
      this.resizable = (config && config.resizable) || true;
      this.draggable = (config && config.draggable) || true;
      this.isHeader = (config && config.isHeader) || false;
      
      this.$initialize(this._tr.getEl());
    },
    
    getEl : function()
    {
      return this._tr.getEl();
    },
    
    addData : function(data)
    {
      var td;
      
      if (this.isHeader)
        td = this.getFactory().createElement("th");
      else
        td = this.getFactory().createElement("td");
      
      if (typeof data != "object") {
        td.setInnerHTML(data);
      }
      else {
        td.appendChild(data);
      }
      
      this._tr.appendChild(td);
    }
  }
});

var DataTable = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'DataTable', {
  
  Extends : Component,
  Implements : ElementProvider,
  Instance : {
    
    initialize : function(dataSource, title, config)
    {
      this.$initialize();
      
      this._container = new Container();
      
      title = title || "";
      config = config || {};
      config.border = config.border || "1";
      var styles = Mojo.Util.merge(config.styles, this._createStyle());
      
      this._table = this.getFactory().createElement("table", {border: config.border}, styles);
      var caption = this.getFactory().createElement("caption");
      caption.setInnerHTML(title);
      this._table.appendChild(caption);
      
      
      this._tBody = this.getFactory().createElement("tbody");
      this._table.appendChild(this._tBody);
      
      this._headerRow = new Row({isHeader:true});
      this._tBody.appendChild(this._headerRow);
      
      this._rows = [];
      
      this.acceptArray( dataSource.getData() );

      this._container.addComponent(this._table);
    },
    
    getEl : function()
    {
      return this._container.getEl();
    },
    
    _createStyle: function()
    {
      var style = {}
      
      return style;
    },
    
    acceptArray : function(array)
    {
      this.addHeader(array[0]);
      
      var v;
      for (var k = 1; k < array.length; ++k)
      {
        this.addRow( array[k] );
      }
      
      this._data = array;
    },
    
    addHeader: function(inner) // can take a string or a collection of strings
    {
      if (Mojo.Util.isString(inner)) {
        this._headerRow.addData(inner);
      }
      else if (Mojo.Util.isObject(inner)) {
        for (key in inner) {
          this.addHeader(inner[key]);
        }
      }
    },
    
    addRow : function(rowData, repeatNumber) // if repeatNumber is 2 this will add the same row twice (if its 1 or 0 or null it does nothing)
    {
      if (!Mojo.Util.isObject(rowData)) {
        var row = new Row();
        this._rows.push(row);
        this._tBody.appendChild(row);
        
        return;
      }
      else if (typeof rowData[0] != "object") { // rowData is an array of strings
        var row = new Row();
        this._rows.push(row);
        this._tBody.appendChild(row);
        
        this.addData(rowData, this._rows.length);
        
        if (typeof repeatNumber == "number") {
          repeatNumber--;
          
          for (var i = 0; i < repeatNumber; i++) {
            this.addRow(rowData);
          }
        }
      }
      else if (typeof rowData[0] == "object") { // rowData is an array of arrays
        for (var k in rowData) {
          this.addRow(rowData[k]);
        }
      }
    },
    
    deleteRow : function(rowNumber, deleteNumber) // This function works exactly like array.splice
    {
      var row;
      deleteNumber = deleteNumber || 1;
      var rowIndex = rowNumber - 1;
      var exitCondition = rowIndex + deleteNumber;
      
      for (; rowIndex < exitCondition; ++rowIndex) {
        row = this._rows[rowIndex];
        if (row == undefined) {
          deleteNumber = rowIndex - (rowNumber-1);
          break;
        }
        
        this._tBody.removeChild(row);
      }
      
      this._rows.splice(rowNumber-1, deleteNumber);
    },
    
    getRow : function(rowNumber)
    {
      if (rowNumber)
        return this._rows[rowNumber-1];
      else
        return this._rows;
    },
    
    addData : function(inner, rowNumber) // rowNumber starts from 1
    {
      if (typeof inner != "object") {
        var row = this.getRow(rowNumber);
        if (row == undefined) 
          throw new com.runwaysdk.Exception("Attempt to addData to a row (" + rowNumber + ") that does not exist.");
        
        row.addData(inner);
      }
      else
      {
        for (key in inner)
          this.addData(inner[key], rowNumber);
      }
    }
    
  }
  
});

var Draggable = Mojo.Meta.newInterface(Mojo.UI_PACKAGE+'Draggable', {
  Instance : {
    getDragDelegate : function(){},
    getDraggables : function(){},
    getDragData : function(e){},
    setDragDisplay : function(e){}
  }
});

var Droppable = Mojo.Meta.newInterface(Mojo.UI_PACKAGE+'Droppable', {
  Instance : {
    getDropDelegate : function(){},
    acceptDrop : function(e){},
    handleDrop : function(e){}
  }
});

var DragTarget = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'DragTarget', {
  Instance : {
    initialize : function(draggable)
    {
      this._draggable = draggable;
      
      
      // mark all draggable elements with the required draggable=true attribute
      var draggables = this._draggable.getDraggables();
      for(var i=0, len=draggables.length; i<len; i++)
      {
        draggables[i].getEl().setAttribute('draggable', true);
      }
      
      var el = this._draggable.getDragDelegate();
      el.addEventListener('dragstart', this.onDragStart, null, this);
      el.addEventListener('drag', this.onDrag, null, this);
      el.addEventListener('dragend', this.onDragEnd, null, this);
    },
    
    onDragStart : function(e)
    {
      var data = this._draggable.getDragData(e);
      
      var dt = e.getDataTransfer();
      dt.effectAllowed = 'move';
      // FIXME allow custom encoding (e.g., Files)
      e.getDataTransfer().setData('text/html', data);
      
      this._draggable.setDragDisplay(e);
    },
    
    onDrag : function(e)
    {
    },
    
    onDragEnd : function(e)
    {
    }
  }
});

var DropTarget  = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'DropTarget', {
  Instance : {
    initialize : function(droppable)
    {
      this._droppable = droppable;
      
      var el = this._droppable.getDropDelegate();
      el.addEventListener('dragenter', this.onDragEnter, null, this);
      el.addEventListener('dragleave', this.onDragLeave, null, this);
      el.addEventListener('dragover', this.onDragOver, null, this);
      el.addEventListener('drop', this.onDrop, null, this);
    },
    
    onDragEnter : function(e)
    {
      e.preventDefault();
    },
    
    onDragLeave : function(e)
    {
    },
    
    onDragOver : function(e)
    {
      e.preventDefault();
      e.getDataTransfer().dropEffect = 'move';
    },
    
    onDrop : function(e)
    {
      if(this._droppable.acceptDrop(e))
      {
        e.preventDefault();
        e.stopPropagation();
        
        this._droppable.handleDrop(e);
      }
    }
  }
});

var AbstractList = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'AbstractList', {
  Extends : Component,
  IsAbstract : true,
  Instance : {
    initialize : function()
    {
      this.$initialize();
    }  
  }
});

var OrderableList = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'OrderableList', {
  Extends : AbstractList,
  Implements : [Draggable, Droppable],
  Instance : {
    
    initialize : function(ordered)
    {
      this.$initialize();
      this._list = this.getFactory().createElement('ol');
      this._items = new STRUCT.HashMap();
    },
    render : function(parent)
    {
      this._list.render(parent);
    },
    /**
     * Returns the list element used for event delegation instead of
     * attaching the drag events to the individual list items.
     */
    getDragDelegate : function()
    {
      return this._list;
    },
    getDropDelegate : function()
    {
      return this._list;
    },
    getDraggables : function()
    {
      return this._items.values();
    },
    getDragData : function(e)
    {
      return e.getTarget().id; // The id of the LI being dragged
    },
    setDragDisplay : function(e)
    {
      e.getDataTransfer().addElement(e.getTarget());
    },
    acceptDrop : function(e)
    {
      return this._items.containsKey(e.getDataTransfer().getData('text/html'));
    },
    handleDrop : function(e)
    {
      var newItem = this._items.get(e.getDataTransfer().getData('text/html')).getEl();
      var oldItem = e.getTarget();
      
      this._list.insertBefore(newItem, oldItem);
    },
    destroy : function()
    {
      var items = this._items.values();
      for(var i=0; i<items.length; i++)
      {
        items[i].destroy();
      }
      this._list.getParentNode().removeChild(this.list);
      this.$destroy();
    },
    addListItem : function(listItem)
    {
      this._items.put(listItem.getEl(), listItem);
      this._list.appendChild(listItem.getEl());
    },
    removeListItem : function(listItem)
    {
      this._items.remove(listItem.getEl());
      this._list.removeChild(listItem);
    }
  }
});

var ListItem = Mojo.Meta.newClass(Mojo.UI_PACKAGE+'ListItem', {
  Extends : Component,
  Implements : ElementProvider,
  Instance : {
    
    initialize : function(html)
    {
      this.$initialize();
      this._li = this.getFactory().createElement('li');
      this._li.setInnerHTML(html);
      
      this._payload = null;
    },
    getEl : function()
    {
      return this._li;
    }
  }
});

var FormMd = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FORM+'FormMd', {
  Extends: Mojo.MD_DTO_PACKAGE+'TypeMd',
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebFormMd = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.WEB+'WebFormMd', {
  Extends: FormMd,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
      this._formName = obj.formName;
      this._formMdClass = obj.formMdClass;
    },
    getFormName : function(){ return this._formName; },
    getFormMdClass : function(){ return this._formMdClass; }
  }
});

var FormObjectRenderer = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FORM+'FormObjectRenderer', {
  Extends : Component,
  Instance : {
    initialize : function(formObject){
      this.$initialize();
      this._formObject = formObject;
      this._domForm = null;
      
      FormObjectCache.getInstance().add(formObject);
    },
    render : function(parent){
      // FIXME have Will implement this with a factory
      this._domForm = document.createElement('form');
      this._domForm.setAttribute('method', 'POST');
      this._domForm.setAttribute('id', this._formObject.getId());
      this._domForm.setAttribute('name', this._formObject.getFormName());
      
      var header = document.createElement('h2');
      header.innerHTML = this._formObject.getMd().getDisplayLabel();
      this._domForm.appendChild(header);
      
      // add each field (use text for now)
      var dl = document.createElement('dl');
      this._domForm.appendChild(dl);
      var fields = this._formObject.getFields();
      var dt = null;
      var dd = null;
      var input = null;
      for(var i=0, len=fields.length; i<len; i++)
      {
        var field = fields[i];
        dt = document.createElement('dt');
        dt.innerHTML = field.getFieldMd().getDisplayLabel();
        dd = document.createElement('dd');
        
        input = document.createElement('input');
        input.setAttribute('type', 'text');
        input.setAttribute('name', field.getFieldName());
        input.setAttribute('value', field.getValue());
        
        dt.appendChild(input);
        
        dl.appendChild(dt);
        dl.appendChild(dd);
      }
      
      document.getElementById(parent).appendChild(this._domForm);
    },
    destroy : function(){
      FormObjectCache.getInstance().remove(this._formObject);
      // FIXME this._domForm.getParent().removeChild(this._domForm)
      this.$destroy();
    }
  }
});

var FormObjectCache = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FORM+'FormObjectCache', {
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

var FormObject = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FORM+'FormObject', {
  IsAbstract: true,
  Instance: {
    initialize : function(obj) {

      // default values
      this._formMd = new WebFormMd(obj.formMd);
      this._fields = new com.runwaysdk.structure.LinkedHashMap();
      this._id = obj.id;
      this._newInstance = obj.newInstance;
      this._readable = obj.readable;
      this._writable = obj.writable;
      this._type = obj.type;
      
      var fields = obj.fields;
      for(var i=0, len=fields.length; i<len; i++)
      {
        var fieldObj = fields[i];
        var field = Mojo.Meta.newInstance(fieldObj.js_class, fieldObj);
        this._fields.put(field.getFieldName(), field);
      }
    },
    getFormName : function() { return this.getMd().getFormName(); },
    getHashCode : function(){ return this._id; },
    getId: function(){ return this._id; },
    isNewInstance: function(){ return this._newInstance; },
    getMd: function(){ return this._formMd; },
    getType : function(){ return this._type; },
    isReadable : function(){ return this._readable; },
    isWritable : function(){ return this._writable; },
    getValue : function(field){ return this._fields[field].getValue(); },
    getFieldMap : function(){ return this._fields; },
    getFields : function(){ return this._fields.values(); },
    getFieldNames : function(){ return this._fields.keySet(); }
  }
});

var WebFormObject = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.WEB+'WebFormObject', {
  Extends: FormObject,
  Instance: {
    initialize : function(obj){
      this.$initialize(obj);
    }
  },
  Static : {
    parseFromJSON : function(json){
      var obj = Mojo.Util.toObject(json);
      return new WebFormObject(obj);
    }
  }
});

var FieldIF = Mojo.Meta.newInterface(Mojo.FORM_PACKAGE.FIELD+'FieldIF', {
  Instance : {
    getType : function(){},
    getFieldMd : function(){},
    getValue : function(){},
    getFieldName : function(){},
    isWritable : function(){},
    isReadable : function(){},
    isModified : function(){},
  }
});

var WebField = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FIELD+'WebField', {
  Implements: FieldIF,
  IsAbstract : true,
  Instance : {
    initialize : function(obj){
      this.$initialize();
      
      var fieldMd = obj.fieldMd;
      this._fieldMd = Mojo.Meta.newInstance(fieldMd.js_class, fieldMd);
      this._writable = obj.writable;
      this._readable = obj.readable;
      this._value = obj.value;
      this._modified = obj.modified;
      this._type = obj.type;
    },
    getType : function(){ return this._type; },
    getFieldMd : function(){ return this._fieldMd; },
    getValue : function(){ return this._value; },
    getFieldName : function(){ return this.getFieldMd().getFieldName(); },
    isWritable : function(){},
    isReadable : function(){},
    isModified : function(){}
  }
});

var WebPrimitive = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FIELD+'WebPrimitive', {
  Extends : WebField,
  IsAbstract : true,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebBoolean = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FIELD+'WebBoolean', {
  Extends : WebPrimitive,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});
var WebCharacter = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FIELD+'WebCharacter', {
  Extends : WebPrimitive,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebText = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FIELD+'WebText', {
  Extends : WebPrimitive,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebInteger = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FIELD+'WebInteger', {
  Extends : WebPrimitive,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebDouble = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FIELD+'WebDouble', {
  Extends : WebPrimitive,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebFloat = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FIELD+'WebFloat', {
  Extends : WebPrimitive,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebDate = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.FIELD+'WebDate', {
  Extends : WebPrimitive,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var FieldMdIF = Mojo.Meta.newInterface(Mojo.FORM_PACKAGE.FIELD+'FieldMdIF', {
  Instance : {
    getDefiningMdForm : function(){},
    getFieldName : function(){},
    getFieldOrder : function(){},
    getDisplayLabel : function(){},
    getId : function(){},
  }
});

var WebFieldMd = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.METADATA+'WebPrimitiveMd', {
  Implements : FieldMdIF,
  IsAbstract : true,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
      this._definingMdForm = obj.definingMdForm;
      this._displayLabel = obj.displayLabel;
      this._fieldName = obj.fieldName;
      this._fieldOrder = obj.fieldOrder;
      this._id = obj.id;
    },
    getDefiningMdForm : function(){ return this._definingMdForm; },
    getFieldName : function(){ return this._fieldName; },
    getFieldOrder : function(){ return this._fieldOrder; },
    getDisplayLabel : function(){ return this._displayLabel; },
    getId : function(){ return this.id; }
  }
});

var WebPrimitiveMd = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.METADATA+'WebPrimitiveMd', {
  Extends : WebFieldMd,
  IsAbstract : true,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebBooleanMd = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.METADATA+'WebBooleanMd', {
  Extends : WebPrimitiveMd,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});
var WebCharacterMd = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.METADATA+'WebCharacterMd', {
  Extends : WebPrimitiveMd,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebTextMd = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.METADATA+'WebTextMd', {
  Extends : WebPrimitiveMd,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebIntegerMd = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.METADATA+'WebIntegerMd', {
  Extends : WebPrimitiveMd,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebDoubleMd = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.METADATA+'WebDoubleMd', {
  Extends : WebPrimitiveMd,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebFloatMd = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.METADATA+'WebFloatMd', {
  Extends : WebPrimitiveMd,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

var WebDateMd = Mojo.Meta.newClass(Mojo.FORM_PACKAGE.METADATA+'WebDateMd', {
  Extends : WebPrimitiveMd,
  Instance : {
    initialize : function(obj){
      this.$initialize(obj);
    }
  }
});

})();