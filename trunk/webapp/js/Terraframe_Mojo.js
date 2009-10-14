/**
 * Terraframe Mojo Javascript library.
 * 
 * (c) 2009
 */
var Mojo = {

  /**
   * $ is the namespace for Mojo hardcoded/generated classes.
   */
  $ : {},
  
  /**
   * Manages classes.
   */
  Meta : {

    _classes : {},
    
    _native : [],
    
    _isInitialized : false,
    
    _pseudoConstructor : function(){},
    
    newInstance : function(type)
    {
      var klass = Mojo.Meta._classes[type];
      var args = [].splice.call(arguments, 1);
      
      var obj = new klass(Mojo.Meta._pseudoConstructor);
      klass.prototype.initialize.apply(obj, args);
      
      return obj;
    },
    
    shorthand : function(pattern, attachTo)
    {
      var anchorObj = attachTo || window;
      
      var r = '^'+pattern.replace(/\./g, '\\.').replace(/\*/g, '.*')+'$';
      var re = new RegExp(r);
      
      var classNames = Mojo.Util.getKeys(Mojo.Meta._classes);
      for(var i=0; i<classNames.length; i++)
      {
        var className = classNames[i];
        if(re.test(className))
        {
          var klass = Mojo.Meta._classes[className];
          anchorObj[klass.$class.getName()] = klass;
        }
      }
    },
    
    alias : function(pattern, attachTo)
    {
      var anchorObj = attachTo || window;
      
      var r = '^'+pattern.replace(/\./g, '\\.').replace(/\*/g, '.*')+'$';
      var re = new RegExp(r);
      
      var classNames = Mojo.Util.getKeys(Mojo.Meta._classes);
      for(var i=0; i<classNames.length; i++)
      {
        var className = classNames[i];
        if(re.test(className))
        {
          var klass = Mojo.Meta._classes[className];
          var namespace = Mojo.Meta._buildPackage(klass.$class.getPackage(), anchorObj);
          namespace[klass.$class.getName()] = klass;
        }
      }
    },
    
    findClass : function(type)
    {
      return Mojo.Meta._classes[type];
    },
    
    _buildPackage : function(packageName, alias)
    {
      if(packageName == null || packageName === '')
      {
        return alias;
      }
    
      var parts = packageName.split(".");

      var currentBuild = alias;
      for(var i=0; i<parts.length; i++)
      {
        var part = parts[i];

        if(Mojo.IS_OBJECT_TO_STRING !== Object.prototype.toString.call(currentBuild[part]))
        {
          currentBuild[part] = {};
        }

        currentBuild = currentBuild[part];
      }

      return currentBuild;      
    },
    
    dropClass : function(type)
    {
      // FIXME drop all subclasses from tree.
      // FIXME remove all aliases and shorthand
      delete Mojo.Meta._classes[type];
    },
    
    getClasses : function()
    {
      return Mojo.Util.getKeys(Mojo.Meta._classes);
    },
    
    classExists : function(type)
    {
      return type in Mojo.Meta._classes;
    },
    
    _makeSingleton : function(klass)
    {
      // block normal instantiation
      var sInitialize = klass.prototype.initialize;
      klass.prototype.initialize = function(){
     
        throw Error("Cannot instantiate the singleton class ["+this.$class.getQualifiedName()+"]. " +
          "Use the static [getInstance()] method instead.");
      };
      
      klass.getInstance = (function(sInit){
        
        var instance = null;
        
        return function(){
          
          if(instance == null)
          {
            // FIXME avoid this swapping mechanism. (probably need magic in initialize closure
            // to check for $class.isSingleton() with getInstance sending special args
            var temp = klass.prototype.initialize;
            klass.prototype.initialize = sInit;
            instance = new klass();
            klass.prototype.initialize = temp;
          }
 
          return instance;      
        };
      })(sInitialize);
        
      return {name : 'getInstance', isStatic : true, isConstructor : false,
        method : klass.getInstance, klass: klass};  
    },
    
    _createConstructor : function()
    {
      return function(){
      
        if(Mojo.Meta._isInitialized && this.$class.isAbstract())
        {
          var msg = "Cannot instantiate the abstract class ["+this.$class.getQualifiedName()+"]";
          var error = new Error(msg);
          Mojo.log.LogManager.writeError(msg, error);
          throw error;
        }
        
        this.__context__ = {}; // super context
        
        if(arguments.length === 1 && arguments[0] === Mojo.Meta._pseudoConstructor)
        {
          Mojo.Meta._pseudoConstructor(); // for "reflective" newInstance() calls.
        }
        else
        {
          this.initialize.apply(this, [].splice.call(arguments, 0));
        }
      };      
    },
    
    _addOverride : function(klass, methodName)
    {
      // keep track of parent context
      klass.prototype['$'+methodName] = (function(m){
      
        return function(){
        
          // FIXME clean this (use __context__?)
          var execStack = this.__context__[m];
          if(!execStack)
          {
            execStack = [];
            this.__context__[m] = execStack;
          }
          
          var currentKlass = execStack.length === 0 ? this.$class.getSuperClass()
            : execStack[execStack.length-1].$class.getSuperClass();            
          
          execStack.push(currentKlass);
          
          var retObj = currentKlass.prototype[m].apply(this, arguments);
          
          execStack.pop();
          
          return retObj;
        };
      })(methodName);
    },
    
    newClass : function(qualifiedName, definition)
    {
      var metaRef = qualifiedName === 'Mojo.Meta' ? this : Mojo.Meta;

      var superClass;
      if(Mojo.IS_FUNCTION_TO_STRING === Object.prototype.toString.call(definition.Extends))
      {
        superClass = definition.Extends;
      }
      else if(Mojo.IS_STRING_TO_STRING === Object.prototype.toString.call(definition.Extends))
      {
        superClass = metaRef._classes[definition.Extends];
      }
      else
      {
        superClass = metaRef._classes[Mojo.ROOT_PACKAGE+'Base'];
      }
      
      var alias = definition.Alias || null;
      var constants = definition.Constants || {};
      var instances = definition.Instance || {};
      var statics = definition.Static || {};
      var isNative = definition.Native || false;
      var isSingleton = definition.IsSingleton || false;
      var isAbstract = definition.IsAbstract || false;
      
      
      // attach the package/class to the alias
      var packageName;
      var className;
      if(/\./.test(qualifiedName))
      {
        packageName = qualifiedName.substring(0, qualifiedName.lastIndexOf("."));
        className = qualifiedName.substring(packageName.length+1);
      }
      else
      {
        packageName = '';
        className = qualifiedName;
      }

      // make sure a constructor exists
      if(!instances.initialize)
      {
        instances.initialize = function(){};
      }
      
      // wrap the constructor function
      var klass = metaRef._createConstructor();
      
      // always add the namespace to the window
      var namespace = metaRef._buildPackage(packageName, window);
      namespace[className] = klass;
       
      if(alias !== null)
      {
        var namespace = metaRef._buildPackage(packageName, alias);
        namespace[className] = klass;
      }
      
      metaRef._classes[qualifiedName] = klass;
      
      // temp function is used for inheritance instantiation, to
      // avoid calling actual class constructor
      var temp = function(){};
      temp.prototype = superClass.prototype;
      klass.prototype = new temp();

      // reset constructor to point to the class, such that
      // new A().constructor === A
      klass.prototype.constructor = klass;

      // config obj for Class constructor
      var config = {
        packageName : packageName,
        className : className,
        klass : klass,
        superClass : superClass,
        isNative : isNative,
        instanceMethods : {},
        staticMethods : {},
        isAbstract : isAbstract,
        alias : alias,
        qualifiedName : qualifiedName,
        isSingleton : isSingleton,
        constants : []
      };

      for(var m in instances)
      {
        // standard method
        klass.prototype[m] = instances[m];
        
        // check for override
        if(superClass !== Object
          && Mojo.IS_FUNCTION_TO_STRING == Object.prototype.toString.call(superClass.prototype[m]))
        {
          metaRef._addOverride(klass, m);
        }
            
        config.instanceMethods[m] = {name : m, isStatic : false, 
          isConstructor : (m === 'initialize'), method : instances[m], klass: klass};
      }
      
      if(isSingleton)
      {
        var methodDef = this._makeSingleton(klass);
        config.staticMethods['getInstance'] = methodDef;
      }
      
      // add constants
      for(var c in constants)
      {
        config.constants.push({name : c, value: constants[c]});
      }
      
      // add static methods
      for(var m in statics)
      {
        // FIXME wrap static props in a Property class and have them optionally
        // inherited (or use visibility modifiers?)
        if(Mojo.IS_FUNCTION_TO_STRING !== Object.prototype.toString.call(statics[m]))
        {
          klass[m] = statics[m];
        }
        else
        {
          config.staticMethods[m] = {name : m, isStatic : true, 
            isConstructor : false, method : statics[m], klass: klass};
        }
      }
      
      // attach the metadata Class
      if(definition.Native)
      {
        // Class will be constructed later to complete bootstrapping
        klass.$class = config;
        klass.prototype.$class = config;
        Mojo.Meta._native.push(klass);
      }
      else
      {
        var cKlass = Mojo.$.com.terraframe.mojo.Class;
        
        klass.$class = new cKlass(config);
        klass.prototype.$class = klass.$class;
      }
      
      return klass;
    }
  },
  
  // TODO add as static variables
  JSON_ENDPOINT : 'Mojo/JSONControllerServlet',
  
  ATTRIBUTE_DTO_PACKAGE : 'com.terraframe.mojo.transport.attributes.',
  
  MD_DTO_PACKAGE : 'com.terraframe.mojo.transport.metadata.',
  
  ROOT_PACKAGE : 'com.terraframe.mojo.',
  
  BUSINESS_PACKAGE : 'com.terraframe.mojo.business.',
  
  ATTRIBUTE_PROBLEM_PACKAGE : 'com.terraframe.mojo.dataaccess.attributes.',
  
  IS_OBJECT_TO_STRING : Object.prototype.toString.call({}),
  IS_ARRAY_TO_STRING : Object.prototype.toString.call([]),
  IS_FUNCTION_TO_STRING : Object.prototype.toString.call(function(){}),
  IS_DATE_TO_STRING : Object.prototype.toString.call(new Date()),
  IS_STRING_TO_STRING : Object.prototype.toString.call(''),
  IS_NUMBER_TO_STRING : Object.prototype.toString.call(0),
  IS_BOOLEAN_TO_STRING : Object.prototype.toString.call(true)
};

Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'Base', {

  Alias : Mojo.$,
  
  Native : true,
  
  IsAbstract : true,
  
  Extends : Object,

  Instance : {

    initialize : function()
    {
      // Default Constructor
      
    },
  
    equals : function(obj)
    {
      return obj != null && this === obj;
    },
  
    clone : function()
    {
      return Mojo.Meta.newInstance(this.$class.getQualifiedName(), [].splice.call(arguments, 0));
    },
    
    toString : function()
    {
      return '['+this.$class.getQualifiedName()+'] instance';
    }
  }
});

Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'Class', {

  Alias: Mojo.$,
  
  Native : true,
  
  Instance : {
  
    initialize : function(config)
    {
      this._packageName = config.packageName;
      this._className = config.className;
      this._isNative = config.isNative;
      this._isAbstract = config.isAbstract;
      this._isSingleton = config.isSingleton;
      this._klass = config.klass;
      this._superClass = config.superClass;
      this._alias = config.alias;
      this._qualifiedName = config.qualifiedName;
      this._subclasses = {};

      var notBase = this._superClass !== Object;  
      
      // get parents instance/static methods
      var mKlass = Mojo.$.com.terraframe.mojo.Method;
      this._instanceMethods = {};
      this._staticMethods = {};
      if(notBase)
      {
        // instance methods will be copied via prototype
        var pInstances = this._superClass.$class.getInstanceMethods(true);
        for(var i in pInstances)
        {
          this._instanceMethods[i] = pInstances[i];
        }
      }
        
      var tInstances = config.instanceMethods;
      for(var i in tInstances)
      {
        var method;
        if(this._instanceMethods[i] && this._instanceMethods.hasOwnProperty(i))
        {
          // overridden method (this class gets a clone of the original method)
          method = this._instanceMethods[i].clone();
          method._setOverrideClass(this._klass);
        }
        else
        {
          // new method defined by this class
          method = new mKlass(tInstances[i]);
        }
        
        this._instanceMethods[i] = method;
      }
      
      if(notBase)
      {
        // static methods must be explicitly copied
        var pStatics = this._superClass.$class.getStaticMethods(true);
        for(var i in pStatics)
        {
          var mStatic = pStatics[i];
          this._staticMethods[mStatic.getName()] = mStatic.clone();
          
          this._klass[mStatic.getName()] = mStatic.getMethod();
        }
      }
        
      var tStatics = config.staticMethods;
      for(var i in tStatics)
      {
        var method;
        if(this._staticMethods[i] && this._staticMethods.hasOwnProperty(i))
        {
          // overridden method (this class gets a clone of the original method)
          method = this._staticMethods[i].clone();
          method._setOverrideClass(this._klass);
        }
        else
        {
          // new method defined by this class
          method = new mKlass(tStatics[i]);
        }
          
        this._staticMethods[i] = method;
        this._klass[i] = method.getMethod();
      }

      // set constants
      this._constants = {};
      var cKlass = Mojo.$.com.terraframe.mojo.Constant;
      if(notBase)
      {
        var pConstants = this._superClass.$class.getConstants(true);
        for(var i in pConstants)
        {
          if(pConstants.hasOwnProperty(i))
          {
            this._constants[i] = pConstants[i];
          }
        }
      }
      
      for(var i=0; i<config.constants.length; i++)
      {
        var constObj = new cKlass(config.constants[i]);
        
        if(notBase && this._constants[constObj.getName()])
        {
          constObj._setDefiningClass(this._constants[constObj.getName()].getDefiningClass());
          constObj._setOverrideClass(this._klass);
        }
        else
        {
          constObj._setDefiningClass(this._klass);
        }
        
        this._constants[constObj.getName()] = constObj;
        this._klass[constObj.getName()] = constObj.getValue();
      }
      
      if(notBase)
      {
        this._superClass.$class._addSubClass(this._qualifiedName, this._klass);
      }
      
      // Each class constructor function and instance gets
      // a method to return this Class instance.
      // FIXME add method metadata for getMetaClass
      this._klass.getMetaClass = (function(metaClass){
        return function() {
          return metaClass;
        };
      })(this);
      
      this._klass.prototype.getMetaClass = this._klass.getMetaClass;
    },
    
    _addSubClass : function(qualifiedName, klass)
    {
      this._subclasses[qualifiedName] = klass;
    },
    
    isSingleton : function()
    {
      return this._isSingleton;
    },
    
    getSubClasses : function(asMap)
    {
      if(asMap)
      {
        return this._subclasses;
      }
      else
      {
        var values = [];
        for(var i in this._subclasses)
        {
          values.push(this._subclasses[i]);
        }
        return values;
      }
    },
    
    getConstants : function(asMap)
    {
      if(asMap)
      {
        return this._constants;
      }
      else
      {
        var values = [];
        for(var i in this._constants)
        {
          values.push(this._constants[i]);
        }
        return values;
      }
    },
    
    hasConstant : function(name)
    {
      return this._constants[name] != null;
    },
    
    getConstant: function(name)
    {
      return this._constants[name];
    },

    getMethod : function(name)
    {
      // FIXME will not work with instance/static method of same name
      return this._instanceMethods[name] || this._staticMethods[name];
    },
    
    hasInstanceMethod : function(name)
    {
      return this._instanceMethods[name] != null;
    },
    
    getInstanceMethod : function(name)
    {
      return this._instanceMethods[name];
    },
    
    hasStaticMethod : function(name)
    {
      return this._staticMethods[name] != null;
    },
    
    getStaticMethod : function(name)
    {
      return this._staticMethods[name];
    },
    
    getInstanceMethods : function(asMap)
    {
      if(asMap)
      {
        return this._instanceMethods;
      }
      else
      {
        var arr = [];
        for(var i in this._instanceMethods)
        {
          if(true || this._instanceMethods.hasOwnProperty(i))
          {
            arr.push(this._instanceMethods[i]);
          } 
        }
        
        return arr;
      }
    },
    
    getStaticMethods : function(asMap)
    {
      if(asMap)
      {
        return this._staticMethods;
      }
      else
      {
        var arr = [];
        for(var i in this._staticMethods)
        {
          if(true || this._staticMethods.hasOwnProperty(i))
          {
            arr.push(this._staticMethods[i]);
          }
        }
        
        return arr;
      }
    },
    
    isAbstract : function()
    {
      return this._isAbstract;
    },
    
    getMethods : function()
    {
      return [].concat(this.getInstanceMethods(false), this.getStaticMethods(false));
    },
  
    getPackage : function()
    {
      return this._packageName;
    },
    
    getName : function()
    {
      return this._className;
    },
    
    getQualifiedName : function()
    {
      return this._qualifiedName;
    },
    
    _getClass : function(klass)
    {
      var classObj = null;
      if(klass instanceof this.constructor)
      {
        return klass;
      }
      else if(Mojo.Util.isFunction(klass) || klass instanceof Mojo.$.com.terraframe.mojo.Base)
      {
        return klass.$class;
      }
      else if(Mojo.Util.isString(klass))
      {
        var foundClass = Mojo.Meta.findClass(klass);
        if(foundClass)
        {
          return foundClass.$class;
        }
        else
        {
          return null;
        }
      }
      else
      {
        return null;
      }
    },
    
    isSuperClassOf : function(klass)
    {
      var classObj = this._getClass(klass); 
      
      if(this === classObj)
      {
        return true;
      }
    
      // FIXME allow string, func constructor, and Class instance
      var superClass = classObj.getSuperClass();
      while(superClass !== Object)
      {
        if(superClass.$class === this)
        {
          return true;
        }
        
        superClass = superClass.$class.getSuperClass();
      }
      
      return false;
    },
    
    isSubClassOf : function(klass)
    {
      var classObj = this._getClass(klass); 
      return classObj.isSuperClassOf(this);
    },
    
    getSuperClass : function()
    {
      return this._superClass;
    },
    
    isNative : function()
    {
      return this._isNative;
    },
    
    getAlias : function()
    {
      return this._alias;
    },
    
    newInstance : function()
    {
      var args = [this.getQualifiedName()].concat([].splice.call(arguments, 0));
      return Mojo.Meta.newInstance.apply(this, args);
    },
    
    toString : function()
    {
      return '[Class] ' + this.getQualifiedName();
    }
  
  }
});

Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+"Constant", {
  
  Alias: Mojo.$,
  
  Native : true,
  
  Instance : {
  
    initialize : function(config)
    {
      this._name = config.name;
      this._value = config.value;
      this._klass = null;
      this._overrideKlass = null;
    },
    
    _setDefiningClass : function(klass)
    {
      this._klass = klass;
    },
    
    _setOverrideClass : function(klass)
    {
      this._overrideKlass = klass;
    },
    
    getName : function()
    {
      return this._name;
    },
    
    getValue : function()
    {
      return this._value;
    },
    
    getDefiningClass : function()
    {
      return this._klass;
    },
    
    isOverride : function()
    {
      return this._overrideKlass !== null;
    },
    
    getOverrideClass : function()
    {
      return this._overrideKlass;
    }
  }
  
});

Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'Method', {

  Alias: Mojo.$,
  
  Native : true,
  
  Constants : {

    ABSTRACT_METHOD : function(){} 
  },

  Instance : {
  
    initialize : function(config)
    {
      this._name = config.name;
      this._isStatic = config.isStatic;
      this._isConstructor = config.isConstructor;
      this._method = config.method;
      this._klass = config.klass || null;
      this._overrideKlass = config.overrideKlass || null;
      this._isAbstract = this._method === this.constructor.ABSTRACT_METHOD;
      
      this._aspects = [];
    },
    
    clone : function()
    {
      var cloned = new this.constructor({
        name : this.getName(),
        isStatic : this.isStatic(),
        isConstructor : this.isConstructor(),
        method : this.getMethod(),
        klass : this.getDefiningClass(),
        overrideKlass : this.getOverrideClass()
      });
      
      return cloned;
    },
    
    isAbstract : function()
    {
      return this._isAbstract;
    },
    
    _setDefiningClass : function(klass)
    {
      this._klass = klass;
    },
    
    addAspect : function(aspect)
    {
      this._aspects.push(aspect);
    },
    
    getAspects : function()
    {
      return this._aspects;
    },
    
    _setOverrideClass : function(klass)
    {
      this._overrideKlass = klass;
    },
    
    isConstructor : function()
    {
      return this._isConstructor;
    },
    
    getArity : function()
    {
      return this._method.length;
    },
    
    isOverride : function()
    {
      return !this._isStatic && this._overrideKlass !== null;
    },
    
    isHiding : function()
    {
      return this._isStatic && this._overrideKlass !== null;
    },
    
    getOverrideClass : function()
    {
      return this._overrideKlass;
    },
    
    getMethod : function()
    {
      return this._method;
    },
  
    getName : function()
    {
      return this._name;
    },
    
    isStatic : function()
    {
      return this._isStatic;
    },
    
    getDefiningClass : function()
    {
      return this._klass;
    },
    
    toString : function()
    {
      return '[Method] ' + this.getName();
    }
  },
  
});

// Finish bootstrapping the class system
(function(){

  var klass = Mojo.Meta.findClass(Mojo.ROOT_PACKAGE+'Class');
  
  for(var i=0; i<Mojo.Meta._native.length; i++)
  {
    var bootstrapped = Mojo.Meta._native[i];
    
    // Convert the JSON config $class into a Class instance
    // and re-attach the metadata to the class definition.
    var cClass = new klass(bootstrapped.$class);
    bootstrapped.$class = cClass
    bootstrapped.prototype.$class = cClass;
  }
  
  // convert the Meta object to a class
  var metaProps = {};
  for(var i in Mojo.Meta)
  {
    if(Mojo.Meta.hasOwnProperty(i))
    {
      metaProps[i] = Mojo.Meta[i];
    }
  }

  delete Mojo.Meta;
  
  metaProps.newClass('Mojo.Meta', {
    
    Static : metaProps  
  });
  
  Mojo.Meta._isInitialized = true;
  
})();

Mojo.Meta.newClass('Mojo.Util', {

  IsAbstract : true,
  
  Instance : {
  
    initialize : function(){}
  },

  Static : {
    
    isObject : function(o)
    {
      return  o != null && Object.prototype.toString.call(o) === Mojo.IS_OBJECT_TO_STRING;
    },

    isArray : function(o)
    {
      return o != null && Object.prototype.toString.call(o) === Mojo.IS_ARRAY_TO_STRING;
    },

    isFunction : function(o)
    {
      return o != null && Object.prototype.toString.call(o) === Mojo.IS_FUNCTION_TO_STRING;
    },

    isDate : function(o)
    {
      return o != null && Object.prototype.toString.call(o) === Mojo.IS_DATE_TO_STRING;
    },

    isString : function(o)
    {
      return o != null && Object.prototype.toString.call(o) === Mojo.IS_STRING_TO_STRING;
    },

    isNumber : function(o)
    {
      return o != null && Object.prototype.toString.call(o) === Mojo.IS_NUMBER_TO_STRING;
    },
    
    isBoolean : function(o)
    {
      return o != null && Object.prototype.toString.call(o) === Mojo.IS_BOOLEAN_TO_STRING;
    },
    
    isUndefined : function(o)
    {
      return typeof o === 'undefined';
    },
    
    bind : function(thisRef, func)
    {
      var args = [].splice.call(arguments, 2);
      return function(){
        return func.apply(thisRef, args.concat([].splice.call(arguments, 0)))
      }
    },
    
    curry : function(func)
    {
      var args = [].splice.call(arguments, 1);
      return function(){
        return func.apply(this, args.concat([].splice.call(arguments, 0)))
      }
    },
    
    // TODO give credit to
    // http://blog.stevenlevithan.com/archives/faster-trim-javascript
    // and replace all MDSS.util.stripWhitespace
    trim : function(str)
    {
      var str = str.replace(/^\s\s*/, '');
      var ws = /\s/;
      var i = str.length;
      while (ws.test(str.charAt(--i)));
      return str.slice(0, i + 1);
    },
    
    memoize : function(func, context)
    {
      func.memoCache = {};
    
      return function() {
        
        var args = Array.prototype.splice.call(arguments, 0);
        if(func.memoCache[args])
        {
          return func.memoCache[args];
        }
        else
        {
          func.memoCache[args] = func.apply(context || this, args); 
          return func.memoCache[args];
        }
      }
    },
    
    generateId : function(customLength)
    {
      var result = '';
      var totalLength = customLength || 32;
      for(var i=0; i<totalLength; i++)
      {
        result += Math.floor(Math.random()*16).toString(16);
      } 
      return result;
    },
    
    debounce : function(func, threshold, context, enforceWait)
    {
      var timeout = null;
      var isExec = null;

      return function(){

        if(timeout !== null || enforceWait && isExec)
        {
          return;
        }
      
        timeout = setTimeout(function(){
          clearTimeout(timeout);
          timeout = null;          
        }, threshold || 500);

        isExec = true;
        func.apply(context || this, arguments);
        isExec = false;
      };
    },
    
    setISO8601 : function (date, string)
    {
      if(!Mojo.Util.isString(string) || string === '')
      {
        return;
      }
      
      var regexp = "([0-9]{4})(-([0-9]{2})(-([0-9]{2})" +
          "(T([0-9]{2}):([0-9]{2})(:([0-9]{2})(\.([0-9]+))?)?" +
          "(Z|(([-+])([0-9]{2}):([0-9]{2})))?)?)?)?";
      var d = string.match(new RegExp(regexp));

      var offset = 0;
      var tempDate = new Date(d[1], 0, 1);

      if (d[3]) { tempDate.setMonth(d[3] - 1); }
      if (d[5]) { tempDate.setDate(d[5]); }
      if (d[7]) { tempDate.setHours(d[7]); }
      if (d[8]) { tempDate.setMinutes(d[8]); }
      if (d[10]) { tempDate.setSeconds(d[10]); }
      if (d[12]) { tempDate.setMilliseconds(Number("0." + d[12]) * 1000); }
      if (d[14]) {
          offset = (Number(d[16]) * 60) + Number(d[17]);
          offset *= ((d[15] == '-') ? 1 : -1);
      }

      offset -= tempDate.getTimezoneOffset();
      time = (Number(tempDate) + (offset * 60 * 1000));
      date.setTime(Number(time));
    },

    toISO8601 : function (date)
    {
      /* 
         ISO8601 format:
         Complete date plus hours, minutes, seconds and a decimal
         fraction of a second
         YYYY-MM-DDThh:mm:ssZ (eg 1997-07-16T19:20:30.45-0100)
      */
      var format = 6;
      var offset = date.getTimezoneOffset()/60;
      
      var tempDate = date;
         
      var zeropad = function (num) { return ((num < 10) ? '0' : '') + num; }

      var str = "";

      // Set YYYY
      str += tempDate.getUTCFullYear();
      // Set MM
      str += "-" + zeropad(tempDate.getUTCMonth() + 1);
      // Set DD
      str += "-" + zeropad(tempDate.getUTCDate());
      // Set Thh:mm
      str += "T" + zeropad(tempDate.getUTCHours()) + ":" + zeropad(tempDate.getUTCMinutes());
      // Set ss
      str += ":" + zeropad(tempDate.getUTCSeconds());        
      // Set TZD
      str += '-' + zeropad(offset) + '00';
      return str;
    },
    

    /**
     * This JSON object is based on the reference code provided by Douglas Crockford.
     * The original, commented source is located at http://json.org/json2.js.
     */
    JSON : (function ()
    {
      var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
          escapeable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
          gap,
          indent,
          meta = {    // table of character substitutions
              '\b': '\\b',
              '\t': '\\t',
              '\n': '\\n',
              '\f': '\\f',
              '\r': '\\r',
              '"' : '\\"',
              '\\': '\\\\'
          },
          rep;


      function quote(string) {

          escapeable.lastIndex = 0;
          return escapeable.test(string) ?
              '"' + string.replace(escapeable, function (a) {
                  var c = meta[a];
                  if (typeof c === 'string') {
                      return c;
                  }
                  return '\\u' + ('0000' +
                          (+(a.charCodeAt(0))).toString(16)).slice(-4);
              }) + '"' :
              '"' + string + '"';
      }


      function str(key, holder) {

          var i,          // The loop counter.
              k,          // The member key.
              v,          // The member value.
              length,
              mind = gap,
              partial,
              value = holder[key];

          if (typeof rep === 'function') {
              value = rep.call(holder, key, value);
          }

          // Mojo change: A date specific check (server expects timestamps).
          if(Mojo.Util.isDate(value))
          {
            return quote(Mojo.Util.toISO8601(value));
          }

          switch (typeof value) {
          case 'string':
              return quote(value);

          case 'number':

              return isFinite(value) ? String(value) : 'null';

          case 'boolean':
          case 'null':

              return String(value);

          case 'object':

              if (!value) {
                  return 'null';
              }

              gap += indent;
              partial = [];

              if (typeof value.length === 'number' &&
                      !(value.propertyIsEnumerable('length'))) {

                  length = value.length;
                  for (i = 0; i < length; i += 1) {
                      partial[i] = str(i, value) || 'null';
                  }

                  v = partial.length === 0 ? '[]' :
                      gap ? '[\n' + gap +
                              partial.join(',\n' + gap) + '\n' +
                                  mind + ']' :
                            '[' + partial.join(',') + ']';
                  gap = mind;
                  return v;
              }

              if (rep && typeof rep === 'object') {
                  length = rep.length;
                  for (i = 0; i < length; i += 1) {
                      k = rep[i];
                      if (typeof k === 'string') {
                          v = str(k, value, rep);
                          if (v) {
                              partial.push(quote(k) + (gap ? ': ' : ':') + v);
                          }
                      }
                  }
              } else {

                  for (k in value) {
                      if (Object.hasOwnProperty.call(value, k)) {
                          v = str(k, value, rep);
                          if (v) {
                              partial.push(quote(k) + (gap ? ': ' : ':') + v);
                          }
                      }
                  }
              }

              v = partial.length === 0 ? '{}' :
                  gap ? '{\n' + gap + partial.join(',\n' + gap) + '\n' +
                          mind + '}' : '{' + partial.join(',') + '}';
              gap = mind;
              return v;
          }
      }

      return {
          stringify: function (value , replacer, space) {

              var i;
              gap = '';
              indent = '';

              if (typeof space === 'number') {
                  for (i = 0; i < space; i += 1) {
                      indent += ' ';
                  }

              } else if (typeof space === 'string') {
                  indent = space;
              }

              rep = replacer;
              if (replacer && typeof replacer !== 'function' &&
                      (typeof replacer !== 'object' ||
                       typeof replacer.length !== 'number')) {
                  throw new Error('JSON.stringify');
              }

              return str('', {'': value});
          },


          parse: function (text, reviver) {

              var j;

              function walk(holder, key) {

                  var k, v, value = holder[key];
                  if (value && typeof value === 'object') {
                      for (k in value) {
                          if (Object.hasOwnProperty.call(value, k)) {
                              v = walk(value, k);
                              if (v !== undefined) {
                                  value[k] = v;
                              } else {
                                  delete value[k];
                              }
                          }
                      }
                  }
                  return reviver.call(holder, key, value);
              }

              cx.lastIndex = 0;
              if (cx.test(text)) {
                  text = text.replace(cx, function (a) {
                      return '\\u' + ('0000' +
                              (+(a.charCodeAt(0))).toString(16)).slice(-4);
                  });
              }

              if (/^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@').
                replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').
                replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {

                  j = eval('(' + text + ')');

                  return typeof reviver === 'function' ?
                      walk({'': j}, '') : j;
              }

              throw new SyntaxError('JSON.parse');
              }
          };
    })(),

    getKeys : function(obj)
    {
      var keys = [];
      for(var i in obj)
      {
        keys.push(i);
      }

      return keys;
    },

    getValues : function(obj)
    {
      var values = [];
      for(var i in obj)
      {
        values.push(obj[i]);
      }

      return values;
    },

    copy : function(source, dest, ownProp)
    {
      if(Mojo.Util.isObject(source))
      {
        for(var i in source)
        {
          if((ownProp && source.hasOwnProperty(i)) || !ownProp)
          {
            dest[i] = source[i];
          }
        }
      }
      
      return dest;
    },

    getObject : function(json)
    {
      if(Mojo.Util.isString(json))
        return this.JSON.parse(json);
      else
        return json;
    },

    getJSON : function(obj)
    {
      return this.JSON.stringify(obj);
    },

    convertToType : function(value)
    {
      // FIXME types now match those of java, so reflect directly on the type
    
      // void/null returns
      if(value == null)
      {
        return null;
      }
      else if(Mojo.Util.isObject(value))
      {
        // MdEnumeration Items
        if('enumType' in value && 'enumName' in value)
        {
          return Mojo.Meta.findClass(value.enumType)[value.enumName];
        }
        // special case for ValueObjectDTOs, which define both a dto_type and _type property
        else if('dto_type' in value && value.dto_type === Mojo.BUSINESS_PACKAGE+'ValueObjectDTO')
        {
          return Mojo.Meta.newInstance(value.dto_type, value);
        }
        // messages may be type-safe or generic
        else if('dto_type' in value && (value.dto_type === Mojo.BUSINESS_PACKAGE+'WarningDTO' || value.dto_type === Mojo.BUSINESS_PACKAGE+'InformationDTO'))
        {
          if(Mojo.Meta.classExists(value._type))
          {
            return Mojo.Meta.newInstance(value._type, value);
          }
          else
          {
            return Mojo.Meta.newInstance(value.dto_type, value);
          }
        }
        else if('_type' in value && value._type != '')
        {
          // generated dtos
          return Mojo.Meta.newInstance(value._type, value);
        }
        else if('dto_type' in value && value.dto_type != '')
        {
          // hard-coded dtos
          return Mojo.Meta.newInstance(value.dto_type, value);
        }
        else
        {
          return value;
        }
      }
      else if(Mojo.Util.isArray(value))
      {
        for(var i=0; i<value.length; i++)
        {
          value[i] = Mojo.Util.convertToType(value[i]);
        }

        return value;
      }
      else
      {
        return value;
      }
    },
    
    handleSuccess : function(clientRequest, responseText, isController)
    {
      var obj = null;
      if(!isController)
      {
        var json = Mojo.Util.getObject(responseText);
        obj = Mojo.Util.convertToType(json.returnValue);
 
        // add warnings/information to the ClientRequest
        if(Mojo.Util.isArray(json.warnings) && json.warnings.length > 0)
        {
          clientRequest.setWarnings(Mojo.Util.convertToType(json.warnings));
        }
 
        if(Mojo.Util.isArray(json.information) && json.information.length > 0)
        {
          clientRequest.setInformation(Mojo.Util.convertToType(json.information));
        }
      }
      else
      {
        obj = responseText;
      }

      // invoke the success handler
      if(Mojo.Util.isFunction(clientRequest.onSuccess))
      {
        clientRequest.onSuccess(obj);
      }      
    },

    handleException : function(clientRequest, responseText)
    {
      var e = null;
      try
      {
        var obj = Mojo.Util.getObject(responseText);

        var exceptionType = null;
        if('dto_type' in obj && obj.dto_type === Mojo.ROOT_PACKAGE+'MojoExceptionDTO')
        {
          exceptionType = obj.wrappedException;
          e = Mojo.Util.convertToType(obj);
        }
        else if('dto_type' in obj && obj.dto_type === Mojo.ROOT_PACKAGE+'ProblemExceptionDTO')
        {
          exceptionType = obj.dto_type;
          e = Mojo.Util.convertToType(obj);
        }
        else if('_type' in obj)
        {
          exceptionType = obj._type;
          if(Mojo.Meta.classExists(obj._type))
          {
            e = Mojo.Util.convertToType(obj);
          }
          else
          {
            e = Mojo.Meta.newInstance(Mojo.ROOT_PACKAGE+'Exception', obj);
          }
        }
        else
        {
          e = Mojo.Meta.newInstance(Mojo.ROOT_PACKAGE+'Exception', obj)
        }

        // try to match the exception name to an error callback
        if(Mojo.Util.isString(exceptionType) && exceptionType.length > 0)
        {
          var exNameInd = exceptionType.lastIndexOf('.');
          if(exNameInd !== -1)
          {
            exceptionType = exceptionType.substr(exNameInd + 1);
          }

          var handlerName = 'on'+exceptionType;
          if(Mojo.Util.isFunction(clientRequest[handlerName]))
          {
            clientRequest[handlerName](e);
          }
          // no match ... use the default handler
          else if(Mojo.Util.isFunction(clientRequest.onFailure))
          {
            clientRequest.onFailure(e);
          }
        }
        else
        {
          // use the default handler
          if(Mojo.Util.isFunction(clientRequest.onFailure))
          {
            clientRequest.onFailure(e);
          }
        }
      }
      catch(e1)
      {
        // use the default handler
        if(Mojo.Util.isFunction(clientRequest.onFailure))
        {
          var e = Mojo.Meta.newInstance(Mojo.ROOT_PACKAGE+'Exception', responseText);
          clientRequest.onFailure(e);
        }
      }
    },

    collectFormValues : function(formId)
    {

      var keyValues = {};
      function collect(elements)
      {
        for(var i=0; i<elements.length; i++)
        {
          var el = elements[i];
          if(el.disabled)
          {
            continue;
          }

          var name = el.name;

          var nodeName = el.nodeName.toLowerCase();
          switch(nodeName)
          {
            case 'select':
              var values = [];
              var options = el.options;
              for(var j=0; j<options.length; j++)
              {
                var option = options[j];
                if(option.selected)
                  values.push(option.value);
              }
              keyValues[name] = values;
              break;
            case 'textarea':
              keyValues[name] = el.value;
              break;
            case 'input':
              var type = el.type.toLowerCase();
              switch(type)
              {
                case 'radio':
                  if(el.checked)
                    keyValues[name] = el.value;
                  break;
                case 'checkbox':
                  if(!keyValues[name])
                    keyValues[name] = [];

                  if(el.checked)
                    keyValues[name].push(el.value);
                  break;
                default:
                  keyValues[name] = el.value;
              }
              break;
          }
        }
      }

      var form = Mojo.Util.isString(formId) ? document.getElementById(formId) : formId;
      collect(form.getElementsByTagName('input'));
      collect(form.getElementsByTagName('select'));
      collect(form.getElementsByTagName('textarea'));

      return keyValues;
    },

    convertMapToQueryString : function(map)
    {
      if(map == null)
      {
        return '';
      }

      var params = [];
      for(var key in map)
      {
        var entry = map[key];
        if(Mojo.Util.isArray(entry))
        {
          for(var i=0; i<entry.length; i++)
          {
            params.push(key + "[]=" + encodeURIComponent(entry[i]));
          }
        }
        else
        {
          params.push(key + "=" + encodeURIComponent(entry));
        }
      }

      var queryString = params.join("&");
      return queryString;
    }
  
  }
  
});

Mojo.Meta.newClass("Mojo.log.LogManager", {

  IsSingleton : true,
  
  Instance : {
  
    initialize : function()
    {
      this._loggers = [];
    },
    
    _addLogger : function(logger)
    {
      this._loggers.push(logger);
    },
    
    _getLoggers : function()
    {
      return this._loggers;
    },
    
    _removeLogger : function(logger)
    {
      // FIXME impl
    }
   
  },
  
  Static : {
  
    addLogger : function(logger)
    {
      Mojo.log.LogManager.getInstance()._addLogger(logger);
    }, 
    
    getLoggers : function()
    {
      return Mojo.log.LogManager.getInstance()._getLoggers();
    },
    
    removeLogger : function(logger)
    {
      Mojo.log.LogManager.getInstance()._removeLogger(logger);
    },
    
    writeInfo : function(msg)
    {
      var loggers = Mojo.log.LogManager.getLoggers();
      Mojo.Iter.forEach(loggers, function(logger){
        logger.writeInfo(msg);
      });
    },
    
    writeWarning : function(msg)
    {
      var loggers = Mojo.log.LogManager.getLoggers();
      Mojo.Iter.forEach(loggers, function(logger){
        logger.writeWarning(msg);
      });
    },
    
    writeError : function(msg, error)
    {
      var loggers = Mojo.log.LogManager.getLoggers();
      Mojo.Iter.forEach(loggers, function(logger){
        logger.writeError(msg, error);
      });
    },
    
  }

});

Mojo.Meta.newClass("Mojo.log.Logger", {

  IsAbstract : true,
  
  Constants : {
  
    INFO : "INFO",
    WARNING : "WARNING", 
    ERROR : "ERROR"
  },

  Instance : {
  
    initialize : function()
    {
    },
    
    writeInfo : function(msg)
    {
     // FIXME make abstract
    },
    
    writeWarning : function(msg)
    {
     // FIXME make abstract
    },
    
    writeError : function(msg, error)
    {
     // FIXME make abstract
    },
  
  },
});

// FIXME iterate over different object types
Mojo.Meta.newClass('Mojo.Iter', {

  Instance : {
  
    initialize : function()
    {
    }
  },
  
  Static : {
  
    filter : function(obj, func, context)
    {
      var bound = Mojo.Util.bind((context || this), func);
      var filtered = [];
      Mojo.Iter.forEach(obj, function(item, ind){
        
        if(bound(item, ind))
        {
          filtered.push(item);
        }
      });
      
      return filtered;
    },
    
    forEach : function(obj, func, context)
    {
      var bound = Mojo.Util.bind((context || this), func);
      for(var i=0; i<obj.length; i++)
      {
        var item = obj[i];
        bound(item, i);
      }
    },
    
    map : function(obj, func, context)
    {
      var bound = Mojo.Util.bind((context || this), func);
      var mapped = [];
      Mojo.Iter.forEach(obj, function(item, ind){
        
        mapped.push(bound(item, ind));
        
      });
      
      return mapped;
    }
  }
});

Mojo.Meta.newClass('Mojo.aspect.Advice', {

  IsAbstract : true,
  
  Constants : {
  
    MATCH_INSTANCE : 1,
    MATCH_STATIC : 2,
    MATCH_ALL : 3
  },

  Instance : {
  
    initialize : function(classP, methodP, method, matchOn)
    {
      this._method = method;
      this._matchOn = matchOn || Mojo.aspect.Advice.MATCH_ALL;
      
      this._classRE = classP;
      this._methodRE = methodP;
    },
    
    weave : function()
    {
      var matched = [];
      
      var classes = Mojo.Meta.getClasses();
      for(var i=0; i<classes.length; i++)
      {
        var className = classes[i];
        var klass = Mojo.Meta.findClass(className);
        
        if(this._classRE.test(className))
        {
        
          var methods;
          switch(this._matchOn)
          {
            case Mojo.aspect.Advice.MATCH_ALL:
              methods = klass.$class.getMethods();
              break;
            case Mojo.aspect.Advice.MATCH_INSTANCE:
              methods = klass.$class.getInstanceMethods();
              break;
            case Mojo.aspect.Advice.MATCH_STATIC:
              methods = klass.$class.getStaticMethods();
              break;
            default:
              methods = [];
          }
          
          for(var j=0; j<methods.length; j++)
          {
            var method = methods[j];
            if(this._methodRE.test(method.getName()))
            {
              this._doWeave(klass, method);
              matched.push(method);
              method.addAspect(this);
            }
          }
        }
      }
      
      return matched;
    },
    
    // FIXME make abstract
    execute : function(){},
    
    toString : function()
    {
      var str = '';
      str += '['+this.$class.getName()+'] ';
      
      var matchesOn;
      if(this._matchOn === this.constructor.MATCH_INSTANCE)
      {
        matchesOn = 'Instance';
      }
      else if(this._matchOn === this.constructor.MATCH_STATIC)
      {
        matchesOn = 'Static';
      }
      else
      {
        matchesOn = 'Instance/Static';
      }
      
      str += ' Matches on ['+matchesOn+'] with class(es) ['+this._classRE+'] and method(s) ['+this._methodRE+']';
      
      return str;
    }
  
  }

});

Mojo.Meta.newClass('Mojo.aspect.BeforeAdvice', {

  Extends : Mojo.aspect.Advice,
  
  Instance : {
  
    initialize : function(classP, methodP, method, matchOn)
    {
      this.$initialize(classP, methodP, method, matchOn);
    },
  
    _doWeave : function(klass, wrappedMethod)
    {
      var source = wrappedMethod.isStatic() ? klass : klass.prototype;
      var original = source[wrappedMethod.getName()];
      
      source[wrappedMethod.getName()] = (function(k, m, b, o){
        
        return function(){
        
          var args = [].splice.call(arguments, 0);
        
          var context = (m.isStatic() ? window : this);
          b.execute(context, args, k, m);
        
          return o.apply(context, args);
        };        
      })(klass, wrappedMethod, this, original);
    },
    
    execute : function(context, args, klass, method)
    {
      this._method.call(context, args, klass, method);
    }
  }
  
});

Mojo.Meta.newClass('Mojo.aspect.AfterAdvice', {

  Extends : Mojo.aspect.Advice,
  
  Instance : {
  
    initialize : function(classP, methodP, method, matchOn)
    {
      this.$initialize(classP, methodP, method, matchOn);
    },
  
    _doWeave : function(klass, wrappedMethod)
    {
      var source = wrappedMethod.isStatic() ? klass : klass.prototype;
      var original = source[wrappedMethod.getName()];
      
      source[wrappedMethod.getName()] = (function(k, m, a, o){
        
        return function(){
        
          var args = [].splice.call(arguments, 0);
          var context = (m.isStatic() ? window : this);
        
          var obj = o.apply(context, args);

          a.execute(context, args, obj, k, m);
          
          return obj;
        };        
      })(klass, wrappedMethod, this, original);
    },
    
    execute : function(context, args, returnObj, klass, method)
    {
      this._method.call(context, args, returnObj, klass, method);
    }
  }

});

Mojo.Meta.newClass('Mojo.aspect.AroundAdvice', {

  Extends : Mojo.aspect.Advice,
  
  Instance : {
  
    initialize : function(classP, methodP, method, matchOn)
    {
      this.$initialize(classP, methodP, method, matchOn);
    },
  
    _doWeave : function(klass, wrappedMethod)
    {
      var source = wrappedMethod.isStatic() ? klass : klass.prototype;
      var original = source[wrappedMethod.getName()];
      
      source[wrappedMethod.getName()] = (function(k, m, a, o){
        
        return function(){
        
          var args = [].splice.call(arguments, 0);
          var context = (m.isStatic() ? window : this);
        
          var proceed = (function(oP, c){
          
            return function()
            {
              return oP.apply(c, [].splice.call(arguments, 0));
            }
          
          })(o, context);

          return a.execute(context, args, proceed, k, m);
        };        
      })(klass, wrappedMethod, this, original);
    },
    
    execute : function(context, args, proceed, klass, method)
    {
      return this._method.call(context, args, proceed, klass, method);
    }
  }

});


Mojo.Meta.newClass('Mojo.ClientRequest', {

  Instance : {
  
    initialize : function(handler){

      Mojo.Util.copy(handler, this);

      this._warnings = [];
      this._information = [];
      this._transport = null;
    },
     
    getMessages : function() { return _warnings.concat(this._information); },
    
    setWarnings : function(warnings) { this._warnings = warnings; },
    
    getWarnings : function() { return this._warnings; },
    
    setInformation : function(information) { this._information = information; },
    
    getInformation : function() { return this._information; },
    
    getTransport : function() { return this._transport; },
    
    setTransport : function(transport) { this._transport = transport; }
  }
});

Mojo.Meta.newClass('Mojo.ClientSession', {

  Static : {

    getBaseEndpoint : function() { return Mojo.ClientSession._baseEndpoint; },
    
    setBaseEndpoint : function(baseEndpoint) { Mojo.ClientSession._baseEndpoint = baseEndpoint; },
    
    getDefaultOptions : function() { return Mojo.Util.copy(Mojo.ClientSession._defaultOptions, {}); },
    
    setDefaultOptions : function(defaultOptions) { Mojo.Util.copy(defaultOptions, Mojo.ClientSession._defaultOptions); },

  }
});
// FIXME allow static props
Mojo.ClientSession._baseEndpoint = (window.location.protocol + "//" + window.location.host  +'/'+ window.location.pathname.split( '/' )[1] +'/');

Mojo.ClientSession._defaultOptions = {
  'method':'post',
  'contentType':'application/x-www-form-urlencoded',
  'encoding':'UTF-8',
  'asynchronous':true,
  'successRange':[200,299]
};


Mojo.Meta.newClass('Mojo.AjaxCall', {

    Instance : {
  
      initialize: function(endpoint, clientRequest, parameters, isController)
      {
        this.clientRequest = clientRequest;
        this.isController = isController || false;
  
        this.cleanup = function()
        {
          this.clientRequest = null;
          this.xmlHttp = null;
          this.isController = null;
          this.options = null;
          this.readyStateChange = null;
          this.cleanup = null;
        };
      
        this.options = {};
        Mojo.Util.copy(Mojo.ClientSession.getDefaultOptions(), this.options);
        Mojo.Util.copy(this.clientRequest.options, this.options);
  
        // encode the parameters if given a map
        var paramStr = '';
        if(Mojo.Util.isObject(parameters))
        {
          var paramArray = [];
          for(var i in parameters)
          {
            paramArray.push(i+'='+encodeURIComponent(parameters[i]));
          }
          paramStr = paramArray.join('&');
        }
        else
        {
          paramStr = parameters.toString();
        }
  
        this.xmlHttp = null;
        try
        {
          // Firefox, Opera 8.0+, Safari
          this.xmlHttp = new XMLHttpRequest();
        }
        catch (e)
        {
          // Internet Explorer
          try
          {
            this.xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
          }
          catch (e)
          {
            try
            {
              this.xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch (e)
            {
              var message = "The browser does not support Ajax";
              throw Mojo.Meta.newInstance(Mojo.ROOT_PACKAGE+'Exception', message);
            }
          }
        }
  
        this.readyStateChange = function (){
  
          if(this.xmlHttp.readyState == 4)
          {
            this.clientRequest.setTransport(this.xmlHttp);
            
            // signal that the load is complete
            if(Mojo.Util.isFunction(this.clientRequest.onComplete))
            {
              this.clientRequest.onComplete();
            }
  
            if(this.xmlHttp.status >= this.options.successRange[0]
              && this.xmlHttp.status <= this.options.successRange[1])
            {
              // process success response
              Mojo.Util.handleSuccess(this.clientRequest, this.xmlHttp.responseText, this.isController);
            }
            else
            {
              // process error response
              Mojo.Util.handleException(this.clientRequest, this.xmlHttp.responseText);
            }
            
            this.cleanup();
          }
        };
  
        // signal that the Ajax call is about to take place.
        if(Mojo.Util.isFunction(this.clientRequest.onSend))
        {
          this.clientRequest.onSend();
        }
        
        var url = Mojo.ClientSession.getBaseEndpoint() + endpoint;
        var bound = Mojo.Util.bind(this, this.readyStateChange);
        if(this.options.method == 'post')
        {
          this.xmlHttp.open(this.options.method, url, this.options.asynchronous);
          this.xmlHttp.onreadystatechange = bound;
          this.xmlHttp.setRequestHeader("Content-type", this.options.contentType + "; charset="+this.options.encoding);
          this.xmlHttp.setRequestHeader("Content-length", paramStr.length);
          this.xmlHttp.setRequestHeader("Connection", "close");
  
          this.xmlHttp.send(paramStr);
        }
        else
        {
          this.xmlHttp.open(this.options.method, url+"?"+paramStr, this.options.asynchronous);
          this.xmlHttp.onreadystatechange = bound;
          
          this.xmlHttp.send(null);
        }
      }
    
    }
});

Mojo.Meta.newClass('Mojo.Facade', {

  IsAbstract : true,

  Static : {

    /**
     * Import Types.
     *
     * @param clientRequest
     * @param types
     * @param options
     */
    importTypes : function(clientRequest, types, options)
    {
      if(Mojo.Util.isString(types))
      {
        types = [types];
      }
  
      var json = Mojo.Util.getJSON(types);
  
      var params = {
        'method' : 'importTypes',
        'types' : json};
  
      var onSuccessRef = clientRequest.onSuccess;
      var importCallback = function(jsSource)
      {
        if(Mojo.Util.isObject(options))
        {
          if(options.autoEval === true)
          {
            eval(jsSource);
          }
          else if('appendTo' in options)
          {
            var appendTo = options.appendTo;
            var parentEl = (Mojo.Util.isString(appendTo)) ? document.getElementById(appendTo) : appendTo;
  
            var script = document.createElement("script");
            script.type = "text/javascript";
  
            if('textContent' in script)
            {
              script.textContent = jsSource;
            }
            else
            {
              script.text = jsSource;
            }
  
            parentEl.appendChild(script);
          }
        }
  
        if(Mojo.Util.isFunction(onSuccessRef))
          onSuccessRef.call(clientRequest, jsSource);
      };
      clientRequest.onSuccess = importCallback;
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * Wrapper for generated Facade methods.
     */
    _methodWrapper : function(clientRequest, params)
    {
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * Wrapper for generated controller methods
     */
    _controllerWrapper : function(endpoint, clientRequest, params)
    {
      if(Mojo.Util.isObject(params))
        params = {"com.terraframe.mojo.mojaxObject":Mojo.Util.getJSON(params)};
  
      new Mojo.AjaxCall(endpoint, clientRequest, params, true);
    },
  
    /**
     * Login
     */
    login : function (clientRequest, username, password)
    {
      var params = {
        'method' : 'login',
        'username' : username,
        'password' : password};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * LoginUser
     */
    loginUser : function (clientRequest, username, password)
    {
      var params = {
        'method' : 'loginUser',
        'username' : username,
        'password' : password};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * ChangeLogin
     */
    changeLogin : function (clientRequest, username, password)
    {
      var params = {
        'method' : 'changeLogin',
        'username' : username,
        'password' : password};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * LoginAnonymous
     */
    loginAnonymous : function (clientRequest)
    {
      var params = {
        'method' : 'loginAnonymous'
      };
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * Logout
     */
    logout : function (clientRequest)
    {
      var params = {
        'method' : 'logout'};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * NewBusiness
     */
    newBusiness : function (clientRequest, type)
    {
      var params = {
        'method' : 'newBusiness',
        'type' : type};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * CreateSessionComponent
     */
    createSessionComponent : function(clientRequest, sessionDTO)
    {
      // convert the BusinessDTO into a JSON String
      var json = Mojo.Util.getJSON(sessionDTO);
  
      var params = {
        'method' : 'createSessionComponent',
        'sessionDTO' : json};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * CreateBusiness
     */
    createBusiness : function (clientRequest, businessDTO)
    {
      // convert the BusinessDTO into a JSON String
      var json = Mojo.Util.getJSON(businessDTO);
  
      var params = {
        'method' : 'createBusiness',
        'businessDTO' : json};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * NewStruct
     */
    newStruct : function (clientRequest, type)
    {
      var params = {
        'method' : 'newStruct',
        'type' : type};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * NewEntity
     */
    newEntity : function (clientRequest, type)
    {
      var params = {
        'method' : 'newEntity',
        'type' : type};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * CreateStruct
     */
    createStruct : function (clientRequest, structDTO)
    {
      // convert the StructDTO into a JSON String
      var json = Mojo.Util.getJSON(structDTO);
  
      var params = {
        'method' : 'createStruct',
        'structDTO' : json};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * CreateRelationship
     */
    createRelationship : function (clientRequest, relationshipDTO)
    {
      // convert the RelationshipDTO into a JSON String
      var json = Mojo.Util.getJSON(relationshipDTO);
  
      var params = {
        'method' : 'createRelationship',
        'relationshipDTO' : json};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * CheckAdminScreenAccess
     */
    checkAdminScreenAccess : function (clientRequest)
    {
      var params = {
        'method' : 'checkAdminScreenAccess'
      };
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * Update
     */
    update : function (clientRequest, mutableDTO)
    {
      // convert the MutableDTO into a JSON String
      var json = Mojo.Util.getJSON(mutableDTO);
  
      var params = {
        'method' : 'update',
        'mutableDTO' : json};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * DeleteBusiness
     */
    deleteEntity : function (clientRequest, id)
    {
      var params = {
        'method' : 'delete',
        'id' : id};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * GetInstance
     */
    get : function (clientRequest, id)
    {
      var params = {
        'method' : 'get',
        'id' : id};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    getUser : function(clientRequest)
    {
      var params = {
        'method' : 'getUser'
      };
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * GetQuery
     */
    getQuery : function(clientRequest, type)
    {
      var params = {
        'method' : 'getQuery',
        'type' : type
      }
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * AddChild
     */
    addChild : function(clientRequest, parentId, childId, relationshipType)
    {
      var params = {
        'method' : 'addChild',
        'parentId' : parentId,
        'childId' : childId,
        'relationshipType' : relationshipType};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * AddParent
     */
    addParent : function(clientRequest, parentId, childId, relationshipType)
    {
      var params = {
        'method' : 'addParent',
        'parentId' : parentId,
        'childId' : childId,
        'relationshipType' : relationshipType};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * DeleteChild
     */
    deleteChild : function(clientRequest, relationshipId)
    {
      var params = {
        'method' : 'deleteChild',
        'relationshipId' : relationshipId};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * DeleteParent
     */
    deleteParent : function(clientRequest, relationshipId)
    {
      var params = {
        'method' : 'deleteParent',
        'relationshipId' : relationshipId};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * DeleteChildren
     */
    deleteChildren : function(clientRequest, parentId, relationshipType)
    {
      var params = {
        'method' : 'deleteChildren',
        'parentId' : parentId,
        'relationshipType' : relationshipType};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * DeleteParents
     */
    deleteParents : function(clientRequest, childId, relationshipType)
    {
      var params = {
        'method' : 'deleteParents',
        'childId' : childId,
        'relationshipType' : relationshipType};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * getChildren
     */
    getChildren : function(clientRequest, parentId, relationshipType)
    {
      var params = {
        'method' : 'getChildren',
        'parentId' : parentId,
        'relationshipType' : relationshipType};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * GetChildRelationships
     */
    getChildRelationships : function(clientRequest, parentId, relationshipType)
    {
      var params = {
        'method' : 'getChildRelationships',
        'parentId' : parentId,
        'relationshipType' : relationshipType};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * GetParentRelationship
     */
    getParentRelationships : function(clientRequest, childId, relationshipType)
    {
      var params = {
        'method' : 'getParentRelationships',
        'childId' : childId,
        'relationshipType' : relationshipType};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
  
    /**
     * getParents
     */
    getParents : function(clientRequest, childId, relationshipType)
    {
      var params = {
        'method' : 'getParents',
        'childId' : childId,
        'relationshipType' : relationshipType};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
  
    /**
     * Lock
     */
    lock : function(clientRequest, id)
    {
      var params = {
        'method' : 'lock',
        'id' : id};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * Unlock
     */
    unlock : function(clientRequest, id)
    {
      var params = {
        'method' : 'unlock',
        'id' : id};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * GrantTypePermission
     */
    grantTypePermission : function(clientRequest, actorId, mdTypeId, operationNames)
    {
      if(!Mojo.Util.isArray(operationNames))
      {
        operationNames = [operationNames];
      }
  
      var operationNamesJSON = Mojo.Util.getJSON(operationNames);
  
      var params = {
        'method' : 'grantTypePermission',
        'actorId' : actorId,
        'operationNames' : operationNamesJSON,
        'mdTypeId' : mdTypeId};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * GrantStatePermission
     */
    grantStatePermission : function(clientRequest, actorId, stateId, operationNames)
    {
      if(!Mojo.Util.isArray(operationNames))
      {
        operationNames = [operationNames];
      }
  
      var operationNamesJSON = Mojo.Util.getJSON(operationNames);
  
      var params = {
        'method' : 'grantStatePermission',
        'actorId' : actorId,
        'operationNames' : operationNamesJSON,
        'stateId' : stateId};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * GrantAttributePermission
     */
    grantAttributePermission : function(clientRequest, actorId, mdAttributeId, operationNames)
    {
      if(!Mojo.Util.isArray(operationNames))
      {
        operationNames = [operationNames];
      }
  
      var operationNamesJSON = Mojo.Util.getJSON(operationNames);
  
      var params = {
        'method' : 'grantAttributePermission',
        'actorId' : actorId,
        'operationNames' : operationNamesJSON,
        'mdAttributeId' : mdAttributeId};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * GrantAttributeStatePermission
     */
    grantAttributeStatePermission : function(clientRequest, actorId, mdAttributeId, stateId, operationNames)
    {
      if(!Mojo.Util.isArray(operationNames))
      {
        operationNames = [operationNames];
      }
  
      var operationNamesJSON = Mojo.Util.getJSON(operationNames);
  
      var params = {
        'method' : 'grantAttributeStatePermission',
        'actorId' : actorId,
        'operationNames' : operationNamesJSON,
        'mdAttributeId' : mdAttributeId,
        'stateId' : stateId};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * PromoteObject
     */
    promoteObject : function(callback, businessJSON, transitionName)
    {
       var params = {
        'method' : 'promoteObject',
        'businessDTO' : businessJSON,
        'transitionName' : transitionName};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * RevokeTypePermission
     */
    revokeTypePermission : function(clientRequest, actorId, mdTypeId, operationNames)
    {
      if(!Mojo.Util.isArray(operationNames))
      {
        operationNames = [operationNames];
      }
  
      var operationNamesJSON = Mojo.Util.getJSON(operationNames);
  
      var params = {
        'method' : 'revokeTypePermission',
        'actorId' : actorId,
        'operationNames' : operationNamesJSON,
        'mdTypeId' : mdTypeId};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * RevokeStatePermission
     */
    revokeStatePermission : function(clientRequest, actorId, stateId, operationNames)
    {
      if(!Mojo.Util.isArray(operationNames))
      {
        operationNames = [operationNames];
      }
  
      var operationNamesJSON = Mojo.Util.getJSON(operationNames);
  
      var params = {
        'method' : 'revokeStatePermission',
        'actorId' : actorId,
        'operationNames' : operationNamesJSON,
        'stateId' : stateId};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * RevokeAttributePermission
     */
    revokeAttributePermission : function(clientRequest, actorId, mdAttributeId, operationNames)
    {
      if(!Mojo.Util.isArray(operationNames))
      {
        operationNames = [operationNames];
      }
  
      var operationNamesJSON = Mojo.Util.getJSON(operationNames);
  
      var params = {
        'method' : 'revokeAttributePermission',
        'actorId' : actorId,
        'operationNames' : operationNamesJSON,
        'mdAttributeId' : mdAttributeId};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * RevokeAttributeStatePermission
     */
    revokeAttributeStatePermission : function(clientRequest, actorId, mdAttributeId, stateId, operationNames)
    {
      if(!Mojo.Util.isArray(operationNames))
      {
        operationNames = [operationNames];
      }
  
      var operationNamesJSON = Mojo.Util.getJSON(operationNames);
  
      var params = {
        'method' : 'revokeAttributeStatePermission',
        'actorId' : actorId,
        'operationNames' : operationNamesJSON,
        'mdAttributeId' : mdAttributeId,
        'stateId' : stateId};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * InvokeMethod
     */
    invokeMethod : function(clientRequest, metadata, mutableDTO, parameters)
    {
      var mutableDTOJSON = Mojo.Util.getJSON(mutableDTO);
      var metadataJSON = Mojo.Util.getJSON(metadata);
      var parametersJSON = Mojo.Util.getJSON(parameters);
  
      var params = {
        'method' : 'invokeMethod',
        'mutableDTO' : mutableDTOJSON,
        'metadata' : metadataJSON,
        'parameters' : parametersJSON};
  
      // specific callback to invokeMethod()
      var onSuccessRef = clientRequest.onSuccess;
      var invokeCallback = function(objArray)
      {
        var returnObject = objArray[0];
        var calledObject = objArray[1];
  
        if(Mojo.Util.isFunction(onSuccessRef))
          onSuccessRef.call(clientRequest, returnObject, calledObject);
      };
      clientRequest.onSuccess = invokeCallback;
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * GetEnumeration
     */
    getEnumeration : function(clientRequest, enumType, enumName)
    {
      var params = {
        'method' : 'getEnumeration',
        'enumType' : enumType,
        'enumName' : enumName};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * GetEnumerations
     */
    getEnumerations : function(clientRequest, enumType, enumNames)
    {
      var enumNamesJSON = Mojo.Util.getJSON(enumNames);
  
      var params = {
        'method' : 'getEnumerations',
        'enumType' : enumType,
        'enumNames' : enumNamesJSON};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * GetAllEnumerations
     */
    getAllEnumerations : function(clientRequest, enumType)
    {
      var params = {
        'method' : 'getAllEnumerations',
        'enumType' : enumType};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * QueryBusinesses
     */
    queryBusinesses : function(clientRequest, queryDTO)
    {
      queryDTO.clearAttributes();
      var json = Mojo.Util.getJSON(queryDTO);
  
      var params = {
        'method' : 'queryBusinesses',
        'queryDTO' : json};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    groovyValueQuery : function(clientRequest, queryDTO)
    {
      queryDTO.clearAttributes();
      queryDTO.clearResultSet();
      var json = Mojo.Util.getJSON(queryDTO);
  
      var params = {
        'method' : 'groovyValueQuery',
        'queryDTO' : json
      };
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * QueryStructs
     */
    queryStructs : function(clientRequest, queryDTO)
    {
      queryDTO.clearAttributes();
      queryDTO.clearResultSet();
      var json = Mojo.Util.getJSON(queryDTO);
  
      var params = {
        'method' : 'queryStructs',
        'queryDTO' : json};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * QueryEntities
     */
    queryEntities : function(clientRequest, queryDTO)
    {
      queryDTO.clearAttributes();
      queryDTO.clearResultSet();
      var json = Mojo.Util.getJSON(queryDTO);
  
      var params = {
        'method' : 'queryEntities',
        'queryDTO' : json};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    },
  
    /**
     * QueryRelationships
     */
    queryRelationships : function(clientRequest, queryDTO)
    {
      queryDTO.clearAttributes();
      queryDTO.clearResultSet();
      var json = Mojo.Util.getJSON(queryDTO);
  
      var params = {
        'method' : 'queryRelationships',
        'queryDTO' : json};
  
      new Mojo.AjaxCall(Mojo.JSON_ENDPOINT, clientRequest, params);
    }
  }
});

/**
 * ComponentQueryDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ComponentQueryDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Instance : {

    initialize : function(obj)
    {
      this.dto_type = obj.dto_type;
      this.queryType = obj.queryType;
      this.pageSize = obj.pageSize;
      this.pageNumber = obj.pageNumber;
      this.count = obj.count;
      this.countEnabled = obj.countEnabled;
      this.groovyQuery = obj.groovyQuery;

      for(var attributeName in obj.definedAttributes)
      {
        var attribute = obj.definedAttributes[attributeName];
        var attributeDTO = Mojo.Meta.newInstance(attribute.dtoType, attribute);
        obj.definedAttributes[attributeName] = attributeDTO;
      }
      
      this.definedAttributes = obj.definedAttributes;  // keep ref for structs

      // now convert the result set
      this.resultSet = [];
      for(var i=0; i<obj.resultSet.length; i++)
      {
        var result = obj.resultSet[i];
        this.resultSet.push(Mojo.Util.convertToType(result));
      }
    },
  
    getType : function()
    {
      return this.queryType;
    },
  
    setPageNumber : function(pageNumber)
    {
      this.pageNumber = pageNumber;
    },
  
    setPageSize : function(pageSize)
    {
      this.pageSize = pageSize;
    },
  
    getPageNumber : function()
    {
      return this.pageNumber;
    },
  
    getPageSize : function()
    {
      return this.pageSize;
    },
  
    getAttributeDTO : function(attrName)
    {
      return this.definedAttributes[attrName];
    },
  
    getAttributeNames : function()
    {
      return Mojo.Util.getKeys(this.definedAttributes);
    },
  
    clearAttributes : function()
    {
      this.definedAttributes = {};
    },
  
    getResultSet : function()
    {
      return this.resultSet;
    },
  
    clearResultSet : function()
    {
      this.resultSet = [];
    },
  
    getCount : function() { return this.count; },
  
    setCountEnabled : function(countEnabled) {this.countEnabled = countEnabled; },
  
    isCountEnabled : function() { return this.countEnabled; }
  
  }
});

/**
 * ValueQueryDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ValueQueryDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'ComponentQueryDTO',
  
  Instance : {

    initialize : function(obj)
    {
      if(typeof obj === 'string')
      {
        // set defaults here because this object can be instantiated client-side
        this.type = '';
        this.resultSet = [];
        this.definedAttributes = {};
        this.groovyQuery = obj;
        this.pageSize = 0;
        this.pageNumber = 0;
        this.count = 0;
        this.countEnabled = false;
  
        this.dto_type = Mojo.BUSINESS_PACKAGE+'ValueQueryDTO';
      }
      else
      {
        this.$initialize(obj);
      }
    },
  
    getGroovyQuery : function() { return this.groovyQuery; }
  
  }
});

/**
 * ClassQueryDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ClassQueryDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Extends : Mojo.BUSINESS_PACKAGE+'ComponentQueryDTO',
  
  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.classes = {};
    },
  
    getClassTypes : function()
    {
      return Mojo.Util.getKeys(this.classes);
    },
  
    getSuperClasses : function(classType)
    {
      return this.classes[classType];
    }
  
  }
});

/**
 * EntityQueryDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'EntityQueryDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Extends : Mojo.BUSINESS_PACKAGE+'ClassQueryDTO',
  
  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.conditions = {};
      for(var i=0; i<obj.conditions.length; i++)
      {
        var conditionJSON = obj.conditions[i];

        var attribute = conditionJSON.attribute;
        var condition = conditionJSON.condition;
        var value = conditionJSON.value;

        var key = attribute+condition+value;
        
        var klass = Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'Condition');
        this.conditions[key] = new klass(attribute, condition, value);
      }

      this.orderByList = [];
      for(var i=0; i<obj.orderByList.length; i++)
      {
        var orderByJSON = obj.orderByList[i];

        var orderBy;
        if(orderByJSON.length == 3) // StructOrderBy
        {
          var attributeStruct = orderByJSON.attributeStruct;
          var attribute = orderByJSON.attribute;
          var order = orderByJSON.order;

          var klass = Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'StructOrderBy');
          orderBy = new klass(attributeStruct, attribute, order);
        }
        else
        {
          var attribute = orderByJSON.attribute;
          var order = orderByJSON.order;

          var klass = Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'OrderBy');
          orderBy = new klass(attributeStruct, attribute, order);
        }
        
        this.orderByList.push(orderBy);
      }
    },
  
    addCondition : function(attribute, condition, value)
    {
      var key = attribute+condition+value;
      
      var klass = Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'Condition');
      var conditionObj = new klass(attribute, condition, value);
      this.conditions[key] = conditionObj;
    },
  
    clearConditions : function()
    {
      this.conditions = {};
    },
  
    getConditions : function()
    {
      return Mojo.Util.getValues(this.conditions);
    },
  
    addOrderBy : function(attribute, order)
    {
      var klass = Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'OrderBy');
      var orderBy = new klass(attribute, order);
      this.orderByList.push(orderBy);
    },
  
    getOrderByList : function()
    {
      return this.orderByList;
    },
  
    clearOrderByList : function()
    {
      this.orderByList.clear = {};
    }
  
  }
});

// OrderBy
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'OrderBy', {

  Alias : Mojo.$,
  
  Instance : {

    initialize : function(attribute, order)
    {
      this.attribute = attribute;
      this.order = order;
    },
  
    getAttribute : function() { return this.attribute; },
  
    getOrder : function() { return this.order; }
  
  }
});

// Condition
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'Condition', {

  Alias : Mojo.$,
  
  Instance : {

    initialize : function(attribute, condition, value)
    {
      this.attribute = attribute;
      this.condition = condition;
      this.value = value;
    },
  
    getAttribute : function() { return this.attribute; },
  
    getCondition : function() { return this.condition; },
  
    getValue : function() { return this.value; }
  
  }
});

/**
 * ElementQueryDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ElementQueryDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Extends : Mojo.BUSINESS_PACKAGE+'EntityQueryDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this._isAbstract = obj._isAbstract;
    },
  
    isAbstract : function(){ return this._isAbstract; },
  
    addStructOrderBy : function(structAttribute, attribute, order)
    {
      var klass = Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'StructOrderBy');
      var orderBy = new klass(structAttribute, attribute, order);
      this.orderByList.push(orderBy);
    }
  
  }
});

// StructOrderBy
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'StructOrderBy', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'OrderBy',
  
  Instance : {

    initialize : function(attributeStruct, attribute, order)
    {
      this.$initialize(attribute, order);
    
      this.attributeStruct = attributeStruct;
    },
  
    getAttributeStruct: function() { return this.attributeStruct; },
  
    getAttribute : function() { return this.attribute; },
  
    getOrder : function() { return this.order; }
  
  }
});

/**
 * StructQueryDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'StructQueryDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'EntityQueryDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * BusinessQueryDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'BusinessQueryDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'ElementQueryDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      // load MdRelationship information for child and parent
      this.typeInMdRelationshipAsChildList = [];
      this.typeInMdRelationshipAsParentList = [];
      
      for(var i=0; i<obj.typeInMdRelationshipAsChildList.length; i++)
      {
        var asChild = obj.typeInMdRelationshipAsChildList[i];
        var asChildObj = Mojo.Meta.newInstance(Mojo.BUSINESS_PACKAGE+'TypeInMdRelationshipAsChild', asChild);
        this.typeInMdRelationshipAsChildList.push(asChildObj);
      }
 
      for(var i=0; i<obj.typeInMdRelationshipAsParentList.length; i++)
      {
        var asParent = obj.typeInMdRelationshipAsParentList[i];
        var asParentObj = Mojo.Meta.newInstance(Mojo.BUSINESS_PACKAGE+'TypeInMdRelationshipAsParent', asParent);
        this.typeInMdRelationshipAsParentList.push(asParentObj);
      }
    },
  
    getTypeInMdRelationshipAsChildList : function() { return this.typeInMdRelationshipAsChildList; },
  
    getTypeInMdRelationshipAsParentList : function() { return this.typeInMdRelationshipAsParentList; }
  
  }
});

// TypeInMdRelationshipAsChild
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'TypeInMdRelationshipAsChild', {

  Alias : Mojo.$,
  
  Instance : {

    initialize : function(obj)
    {
      this.childDisplayLabel = obj.childDisplayLabel;
      this.relationshipType = obj.relationshipType;
    },
  
    getChildDisplayLabel : function() { return this.childDisplayLabel; },
  
    getRelationshipType : function() { return this.relationshipType; }
  
  }
});

// TypeInMdRelationshipAsParent
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'TypeInMdRelationshipAsParent', {

  Alias : Mojo.$,
  
  Instance : {

    initialize : function(obj)
    {
      this.parentDisplayLabel = obj.parentDisplayLabel;
      this.relationshipType = obj.relationshipType;
    },
  
    getParentDisplayLabel : function() { return this.parentDisplayLabel; },
  
    getRelationshipType : function() { return this.relationshipType; }
  
  }
});

/**
 * RelationshipQueryDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'RelationshipQueryDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'ElementQueryDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.parentMdBusiness = obj.parentMdBusiness;
      this.childMdBusiness = obj.childMdBusiness;
    },
  
    getParentMdBusiness : function() { return this.parentMdBusiness; },
  
    getChildMdBusiness : function() { return this.childMdBusiness; }
  
  }
});

Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ViewQueryDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'ComponentQueryDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * ComponentDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ComponentDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,

  Instance : {

    initialize : function(obj)
    {
      this.dto_type = obj.dto_type;
      
      // Generate a new id per instance instead of using the id
      // of the cached JSON (to avoid all new instances having the same id),
      // and preserve the id of the metadata type.
      //FIXME line below to check for new instance before reseting id
//      this.id = Mojo.Util.generateId()+obj.id.substring(32);
      this.id = obj.id;

      this._type = obj._type;
      
      if(Mojo.Util.isObject(obj._typeMd))
      {
        this._typeMd = Mojo.Meta.newInstance(Mojo.MD_DTO_PACKAGE+'TypeMd', obj._typeMd);
      }
      else
      {
        this._typeMd = null;
      }

      this._toString = obj._toString;
      this.readable = obj.readable;

      for(var attributeName in obj.attributeMap)
      {
        var attribute = obj.attributeMap[attributeName];
        var attributeDTO = Mojo.Meta.newInstance(attribute.dtoType, attribute);
        obj.attributeMap[attributeName] = attributeDTO;
      }
      this.attributeMap = obj.attributeMap; // keep reference for structs
    },
  
    getType : function() { return this._type; },
  
    getTypeMd : function() { return this._typeMd; },
  
    getId : function() { return this.id; },
  
    getIdMd : function() { return this.getAttributeDTO('id').getAttributeMdDTO() },
  
    getAttributeDTO : function(attributeName)
    {
      return this.attributeMap[attributeName];
    },
  
    getMd : function() { return this._typeMd; },
  
    toString : function() { return this._toString; },
  
    isReadable : function() { return this.readable; },
  
    setValue : function(attributeName, value) { this.getAttributeDTO(attributeName).setValue(value); },
  
    getValue : function(attributeName) { return this.getAttributeDTO(attributeName).getValue(); }
  
  }
});

Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'MutableDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Extends : Mojo.BUSINESS_PACKAGE+'ComponentDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.writable = obj.writable;
      this.modified = obj.modified;
      this.newInstance = obj.newInstance;
    },
  
    isWritable : function() {return this.writable; },
  
    setModified : function(modified) { this.modified = modified; },
  
    isModified : function() { return this.modified; },
  
    isNewInstance : function() { return this.newInstance; }
  
  },
  
  Static : {
  
    get : function(clientRequest, id)
    {
      Mojo.Facade.get(clientRequest, id);
    }
  
  }
});

/**
 * ValueObjectDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ValueObjectDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'ComponentDTO',

  Instance : {

    initialize : function (obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * TransientDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'TransientDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Extends : Mojo.BUSINESS_PACKAGE+'MutableDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
    }
    
  }
});

/**
 * LocalizableDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'LocalizableDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Extends : Mojo.BUSINESS_PACKAGE+'TransientDTO',

  Instance : {

    initialize : function (obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * ExceptionDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ExceptionDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'LocalizableDTO',

  Instance : {

    initialize : function (obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * NotificationDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'NotificationDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'LocalizableDTO',
  
  IsAbstract : true,

  Instance : {

    initialize : function (obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * MessageDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'MessageDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'NotificationDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.localizedMessage = obj.localizedMessage;
     
    },
  
    getMessage : function() { return this.localizedMessage; }
  
  }
});

/**
 * InformationDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'InformationDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'MessageDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * WarningDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'WarningDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'MessageDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * ProblemDTO (super-type of all generated problems)
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ProblemDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'NotificationDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.localizedMessage = obj.localizedMessage;
      this.developerMessage = obj.developerMessage;
    },
  
    getLocalizedMessage : function() { return this.localizedMessage; },
  
    getMessage : function() { return this.localizedMessage; },
  
    getDeveloperMessage : function() { return this.developerMessage; }
  
  }
});

/**
 * Super-type of all hard-coded exceptions.
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'MojoProblemDTO', {

  Alias : Mojo.$,
  
  Instance : {

    initialize : function(obj)
    {
      this.localizedMessage = obj.localizedMessage;
      this.developerMessage = obj.developerMessage;
    },
  
    getLocalizedMessage : function() { return this.localizedMessage; },
  
    getMessage : function() { return this.localizedMessage; },
  
    getDeveloperMessage : function() { return this.developerMessage; }
  
  }
});

/**
 * AttributeProblemDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'MojoProblemDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.componentId = obj.componentId;
      this.definingType = obj.definingType;
      this.definingTypeDisplayLabel = obj.definingTypeDisplayLabel;
      this.attributeName = obj.attributeName;
      this.attributeDisplayLabel = obj.attributeDisplayLabel;
    },
  
    getComponentId : function() { return this.componentId; },
  
    getDefiningType : function() { return this.definingType; },
  
    getDefiningTypeDisplayLabel : function() { return this.definingTypeDisplayLabel; },
  
    getAttributeName : function() { return this.attributeName; },
  
    getAttributeDisplayLabel : function() { return this.attributeDisplayLabel; }
  
  }
});

/**
 * EmptyValueProblemDTO
 */
Mojo.Meta.newClass(Mojo.ATTRIBUTE_PROBLEM_PACKAGE+'EmptyValueProblemDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * ImmutableAttributeProblemDTO
 */
Mojo.Meta.newClass(Mojo.ATTRIBUTE_PROBLEM_PACKAGE+'ImmutableAttributeProblemDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * SystemAttributeProblemDTO
 */
Mojo.Meta.newClass(Mojo.ATTRIBUTE_PROBLEM_PACKAGE+'SystemAttributeProblemDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * Exception
 * 
 * This is the actual exception that can be thrown and caught.
 * There is no Mojo Java counterpart, so we throw it in the root namespace.
 */
Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'Exception', {

  Alias : Mojo.$,
  
  Extends : Mojo.ROOT_PACKAGE+'Base',
  
  Instance : {

    initialize : function ()
    {
      if(arguments.length == 1)
      {
        var arg = arguments[0];
        if(arg == null)
        {
          this.localizedMessage = null;
          this.developerMessage = null;
        }
        else if(Mojo.Util.isString(arg))
        {
          this.localizedMessage = arg;
          this.developerMessage = null;
        }
        else if(Mojo.Util.isObject(arg)
          && 'localizedMessage' in arg
          && 'developerMessage' in arg)
        {
          this.localizedMessage = arg.localizedMessage;
          this.developerMessage = arg.developerMessage;
        }
        else
        {
          this.localizedMessage = null;
          this.developerMessage = null;
        }
      }
      else if(arguments.length === 2)
      {
        this.localizedMessage = arguments[0];
        this.developerMessage = arguments[1];
      }
      else
      {
        this.localizedMessage = null;
        this.developerMessage = null;
      }
    },
  
    getLocalizedMessage : function() { return this.localizedMessage; },
  
    getMessage : function() { return this.localizedMessage; },
  
    getDeveloperMessage : function() { return this.developerMessage; },
  
    toString : function() { return this.localizedMessage; }
  
  }
});

/**
 * SmartExceptionDTO
 * 
 * (delegates to an ExceptionDTO)
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'SmartExceptionDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.ROOT_PACKAGE+'Exception',

  Instance : {

    initialize : function (obj)
    {
      this.$initialize(obj);
  
      this.ex = Mojo.Meta.newInstance(Mojo.BUSINESS_PACKAGE+'ExceptionDTO', obj);
    },
  
    getAttributeDTO : function(attributeName){ return this.ex.getAttributeDTO(attributeName); },
  
    getId : function() { return this.ex.getId(); },
  
    getIdMd : function() { return this.ex.getIdMd(); },
  
    getMd : function() { return this.ex.getMd(); },
  
    getType : function() { return this.ex.getType(); },
  
    getTypeMd : function() { return this.ex.getTypeMd(); },
  
    isModified : function() { return this.ex.isModified(); },
  
    isNewInstance : function() { return this.ex.isNewInstance(); },
  
    isReadable : function() { return this.ex.isReadable(); },
  
    isWritable : function() { return this.ex.isWritable(); },
  
    setModified : function(modified) { return this.ex.setModified(modified); }
  
  }
});


/**
 * MojoExceptionDTO
 * 
 * (for hard-coded exceptions)
 */
Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'MojoExceptionDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.ROOT_PACKAGE+'Exception',

  Instance : {

    initialize : function (obj)
    {
      this.$initialize(obj);
  
      if(Mojo.Util.isString(obj.wrappedException))
      {
        this.wrappedException = obj.wrappedException;
      }
      else
      {
      // final resort. Only the localized message was received
        this.wrappedException = null;
      }
    },
  
    getWrappedException : function() { return this.wrappedException; }
  
  }
});

/**
 * ProblemExceptionDTO
 */
Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'ProblemExceptionDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.ROOT_PACKAGE+'Exception',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.problemList = [];
      for(var i=0; i<obj.problemList.length; i++)
      {
        var problemJSON = obj.problemList[i];

        var problem = null;
        if('_type' in problemJSON && Mojo.Meta.classExists(problemJSON._type))
        {
          problem = Mojo.Meta.newInstance(problemJSON._type, problemJSON);
        }
        else if('dto_type' in problemJSON && problemJSON.dto_type === Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO')
        {
          problem = Mojo.Meta.newInstance(Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO', problemJSON);
        }
        else
        {
          problem = Mojo.Meta.newInstance(Mojo.BUSINESS_PACKAGE+'ProblemDTO', problemJSON);
        }

        this.problemList[i] = problem;
      }
    },
  
    getProblems : function()
    {
      return this.problemList;
    }
  
  }
});

/**
 * SessionDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'SessionDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Extends : Mojo.BUSINESS_PACKAGE+'TransientDTO',

  Instance : {

    initialize : function (obj)
    {
      this.$initialize(obj);
    },
  
    apply : function(clientRequest)
    {
      if(this.isWritable())
      {
        if(this.isNewInstance())
          Mojo.Facade.createSessionComponent(clientRequest, this);
        else
          Mojo.Facade.update(clientRequest, this);
      }
    }
  
  }
});

/**
 * UtilDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'UtilDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'SessionDTO',

  Instance : {

    initialize : function (obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 * ViewDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ViewDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'SessionDTO',

  Instance : {

    initialize : function (obj)
    {
      this.$initialize(obj);
    }
  
  }
});

/**
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'EntityDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Extends : Mojo.BUSINESS_PACKAGE+'MutableDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
    },
  
    remove : function(clientRequest)
    {
      Mojo.Facade.deleteEntity(clientRequest, this.getId());
    }
  
  }
});

/**
 * ElementDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ElementDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Extends : Mojo.BUSINESS_PACKAGE+'EntityDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.lockedByCurrentUser = obj.lockedByCurrentUser;
    },
  
    lock : function(clientRequest)
    {
      Mojo.Facade.lock(clientRequest, this.getId());
    },
  
    unlock : function(clientRequest)
    {
      Mojo.Facade.unlock(clientRequest, this.getId());
    },
  
    isLockedByCurrentUser : function() { return this.lockedByCurrentUser; }
  
  },
  
  Static : {

    lock : function(clientRequest, id)
    {
      Mojo.Facade.lock(clientRequest, id);
    },
  
    unlock : function(clientRequest, id)
    {
      Mojo.Facade.unlock(clientRequest, id);
    }
  
  }
});


/*
 * BusinessDTO definition
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'BusinessDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'ElementDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.state = obj.state;
      this.transitions = obj.transitions;
    },
  
    getState : function() { return this.state; },
  
    getTransitions : function() { return this.transitions; },
  
    apply : function(clientRequest)
    {
      if(this.isWritable())
      {
        if(this.isNewInstance())
          Mojo.Facade.createBusiness(clientRequest, this);
        else
          Mojo.Facade.update(clientRequest, this);
      }
    }
  
  }
});

/**
 * EnumerationDTO.
 * 
 * Parent class of generated MdEnumerations.
 */
Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'EnumerationDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Instance : {

    initialize : function(obj)
    {
      this.dto_type = obj.dto_type;
      this.enumType = obj.enumType;
      this._name = obj._name;
      this.displayLabel = obj.displayLabel;
    },
  
    name : function () { return this._name; },
  
    getDisplayLabel : function() { return this.displayLabel; }
  
  }
});

/*
 * RelationshipDTO definition
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'RelationshipDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'ElementDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this._typeMd = Mojo.Meta.newInstance(Mojo.MD_DTO_PACKAGE+'RelationshipMd', obj._relationshipMd);
      this.parentId = obj.parentId;
      this.childId = obj.childId;
    },
  
    getParentId : function() { return this.parentId; },
  
    getChildId : function() { return this.childId; },
  
    apply : function(clientRequest)
    {
      if(this.isWritable())
      {
        if(this.isNewInstance())
          Mojo.Facade.createRelationship(clientRequest, this);
        else
          Mojo.Facade.update(clientRequest, this);
      }
    },
  
    getParent : function(clientRequest)
    {
      Mojo.Facade.get(clientRequest, this.getParentId());
    },
  
    getChild : function(clientRequest)
    {
      Mojo.Facade.get(clientRequest, this.getChildId());
    }

  }
});

/**
 * StructDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'StructDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.BUSINESS_PACKAGE+'EntityDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
    },
  
    apply : function(clientRequest)
    {
      if(this.isWritable())
      {
        if(this.isNewInstance())
          Mojo.Facade.createStruct(clientRequest, this);
        else
          Mojo.Facade.update(clientRequest, this);
      }
    }
  
  }
});

/*
 * Attribute definitions
 */

// attribute
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,

  Instance : {
  
    initialize : function(obj)
    {
      this.attributeName = obj.attributeName;
      this.type = obj.type;
      this.dtoType = obj.dtoType;
      this.value = obj.value;
      this.readable = obj.readable;
      this.writable = obj.writable;
      this.modified = obj.modified;
      
      // instantiate the MdDTO version of this attribute
      var mdDtoType = this.$class.getName().replace('DTO', 'MdDTO');
      var mdKlass = Mojo.Meta.findClass(Mojo.MD_DTO_PACKAGE+ mdDtoType);

      if(!mdKlass)
      {
        mdKlass = Mojo.Meta.findClass("com.terraframe.mojo.gis.transport.metadata."+ mdDtoType);
      }
      this.attributeMdDTO = new mdKlass(obj.attributeMdDTO);
    },
  
    getName : function() { return this.attributeName; },
  
    getValue : function() { return this.value; },
  
    setValue : function(value)
    {
      if(this.isWritable())
      {
        // null and undefined should be null
        this.value = value != null ? value : null;
        this.setModified(true);
      }
    },
  
    isReadable : function() { return this.readable; },
  
    isWritable : function() { return this.writable; },
  
    isModified : function() { return this.modified; },
  
    setModified : function(modified) { this.modified = modified; },
  
    getAttributeMdDTO : function() { return this.attributeMdDTO; }
    
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeMdDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,

  Instance : {

    initialize : function(obj)
    {
      this.displayLabel = obj.displayLabel;
      this.description = obj.description;
      this.required = obj.required;
      this.immutable = obj.immutable;
      this.accessorName = obj.accessorName;
      this.id = obj.id;
      this.system = obj.system;
      this.name = obj.name;
      this.type = obj.type;
    },
  
    getDisplayLabel : function() { return this.displayLabel; },
  
    getDescription : function() { return this.description; },
  
    isRequired : function() { return this.required; },
  
    isImmutable : function() { return this.immutable; },
  
    getId : function() { return this.id; },
  
    isSystem : function() { return this.system; },
  
    getName : function() { return this.name; },
  
    getAccessorName : function() { return this.accessorName; }
  
  }
});

// number
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeNumberDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
    }
    
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeNumberMdDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,
  
  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',

  Instance : {

    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this._rejectZero = obj._rejectZero;
      this._rejectNegative = obj._rejectNegative;
      this._rejectPositive = obj._rejectPositive;
  
    },
  
    rejectZero : function() { return this._rejectZero; },
  
    rejectNegative : function() { return this._rejectNegative; },
  
    rejectPositive : function() { return this._rejectPositive; }
  
  }
});

// integer
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeIntegerDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeNumberDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeIntegerMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeNumberMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// long
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeLongDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeNumberDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeLongMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeNumberMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// dec
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeNumberDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDecMdDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeNumberMdDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.totalLength = obj.totalLength;
      this.decimalLength = obj.decimalLength;
    },
  
    getTotalLength : function() { return this.totalLength; },
  
    getDecimalLength : function() { return this.decimalLength; }
  
  }
});

// decimal
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecimalDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDecimalMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeDecMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// double
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDoubleDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDoubleMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeDecMdDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// float
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeFloatDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeFloatMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeDecMdDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// text
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeTextDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeTextMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// character
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeCharacterDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeCharacterMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.size = obj.size;
    },
  
    getSize : function() { return this.size; }
  
  }
});

// boolean
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeBooleanDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeBooleanMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
      
      this._positiveDisplayLabel = obj.positiveDisplayLabel;
      this._negativeDisplayLabel = obj.negativeDisplayLabel;
    },
    
    getPositiveDisplayLabel : function() { return this._positiveDisplayLabel; },
  
    getNegativeDisplayLabel : function() { return this._negativeDisplayLabel; }
  
  }
});

// struct
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeStructDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.structDTO = obj.structDTO;
    },
  
    getStructDTO : function()
    {
      return this.structDTO;
    },
    
    /**
     * Should only be called when setting the type-safe version
     * of the struct.
     */
    setStructDTO : function(structDTO)
    {
      this.structDTO = structDTO;
    }
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeStructMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.definingMdStruct = obj.definingMdStruct;
    },
  
    getDefiningMdStruct : function() { return this.definingMdStruct; }
  
  }
});

// moment
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeMomentDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      // set internal value as a date
      if(this.value != null && this.value !== '')
      {
        var date = new Date();
          
        Mojo.Util.setISO8601(date, this.value);
 
        this.value = date;
      }
      else
      {
        this.value = null;
      }
    },
  
    setValue : function(value)
    {
      if(this.isWritable())
      {
        if(value != null)
        {
          if(Mojo.Util.isDate(value))
          {
            this.value = value;          
          }
          else
          {
            var date = new Date();
            
            Mojo.Util.setISO8601(date, value);
  
            this.value = date;
          }
        }
        else
        {
          this.value = null;
        }
  
        this.setModified(true);
      }
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeMomentMdDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// datetime
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDateTimeDTO', {

  Alias : Mojo.$,
  
  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeMomentDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDateTimeMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMomentMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// date
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDateDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeMomentDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDateMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMomentMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// time
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeTimeDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeMomentDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeTimeMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMomentMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// reference object
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeReferenceDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeReferenceMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.referencedMdBusiness = obj.referenceMdBusiness;
    },
  
    getReferencedMdBusiness : function() { return this.referencedMdBusiness; }
  
  }
});

// enumeration
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeEnumerationDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      // javascript doesn't have a set, so use a hash with key == value.
      this.enumNames = {};
      for(var i=0; i<obj.enumNames.length; i++)
      {
        var enumName = obj.enumNames[i];
        this.enumNames[enumName] = enumName;
      }
    },
  
    add : function(item)
    {
      if(this.isWritable())
      {
        if(!this.getAttributeMdDTO().selectMultiple())
        {
          this.clear();
        }
  
        var enumName = Mojo.Util.isObject(item) ? item.name() : item;
        this.enumNames[enumName] = enumName;
      }
    },
  
    remove : function(item)
    {
      if(this.isWritable())
      {
        var enumName = Mojo.Util.isObject(item) ? item.name() : item;
        delete this.enumNames[enumName];
      }
    },
  
    clear : function()
    {
      if(this.isWritable())
      {
        this.enumNames = {};
      }
    },
  
    getEnumNames : function()
    {
      return Mojo.Util.getKeys(this.enumNames);
    },
  
    getEnumValues : function(clientRequest)
    {
      var enumType = this.getAttributeMdDTO().getReferencedMdEnumeration();
      var names = Mojo.Util.getKeys(this.enumNames);
      Mojo.ClientSession.getEnumerations(clientRequest, enumType, names);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeEnumerationMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this._selectMultiple = obj._selectMultiple;
      this.referencedMdEnumeration = obj.referencedMdEnumeration;
 
      this.enumNames = {}; // key/value = name/display label
      Mojo.Util.copy(obj.enumNames, this.enumNames);
    }, 
  
    getReferencedMdEnumeration : function() { return this.referencedMdEnumeration; },
  
    selectMultiple : function() { return this._selectMultiple; },
  
    getEnumNames : function()
    {
      return Mojo.Util.getKeys(this.enumNames);
    },
  
    getEnumLabels : function()
    {
      return Mojo.Util.getValues(this.enumNames);
    },
  
    getEnumDisplayLabel : function(enumName)
    {
      return this.enumNames[enumName];
    },
  
    getEnumItems : function()
    {
      var copy = {};
      Mojo.Util.copy(this.enumNames, cop);
      return copy;
    }
  
  }
});

// encryption
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeEncryptionDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeEncryptionMdDTO', {

  Alias : Mojo.$,
  
  IsAbstract : true,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.encryptionMethod = obj.encryptionMethod;
    },
  
    getEncryptionMethod : function() { return this.encryptionMethod; }
  
  }
});

// hash
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeHashDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeEncryptionDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeHashMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeEncryptionMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// symmetric
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeSymmetricDTO', {

  Alias : Mojo.$,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeEncryptionDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeSymmetricMdDTO', {

  Alias : Mojo.$,

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeEncryptionMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
    
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'TypeMd', {

  Alias : Mojo.$,
  
  Instance : {

    initialize : function(obj)
    {
      this.displayLabel = obj.displayLabel;
      this.description = obj.description;
      this.id = obj.id;
    },
  
    getDisplayLabel : function() {return this.displayLabel;},
  
    getDescription : function() {return this.description;},
  
    getId : function() {return this.id;}
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'RelationshipMd', {

  Alias : Mojo.$,
  
  Extends : Mojo.MD_DTO_PACKAGE+'TypeMd',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.parentMdBusiness = obj.parentMdBusiness;
      this.childMdBusiness = obj.childMdBusiness;
    },
  
    getParentMdBusiness : function() { return this.parentMdBusiness; },
  
    getChildMdBusiness  : function() { return this.childMdBusiness; }
  
  }
});