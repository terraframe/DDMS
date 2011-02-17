/**
 * Terraframe Mojo Javascript library.
 * Author: Justin Naifeh
 * 
 * (c) 2010
 */
(function(){

  var Mojo = {
  
    // protected namespace for all classes
    $ : {},
    
    // core constants
    JSON_ENDPOINT : 'Mojo/JSONControllerServlet',
    ATTRIBUTE_DTO_PACKAGE : 'com.runwaysdk.transport.attributes.',
    MD_DTO_PACKAGE : 'com.runwaysdk.transport.metadata.',
    ROOT_PACKAGE : 'com.runwaysdk.',
    BUSINESS_PACKAGE : 'com.runwaysdk.business.',
    ATTRIBUTE_PROBLEM_PACKAGE : 'com.runwaysdk.dataaccess.attributes.',
    
    // toString constants used for type checking
    IS_OBJECT_TO_STRING : Object.prototype.toString.call({}),
    IS_ARRAY_TO_STRING : Object.prototype.toString.call([]),
    IS_FUNCTION_TO_STRING : Object.prototype.toString.call(function(){}),
    IS_DATE_TO_STRING : Object.prototype.toString.call(new Date()),
    IS_STRING_TO_STRING : Object.prototype.toString.call(''),
    IS_NUMBER_TO_STRING : Object.prototype.toString.call(0),
    IS_BOOLEAN_TO_STRING : Object.prototype.toString.call(true),
    
    META_CLASS_GETTER : 'getMetaClass',
    
    // general purpose empty function
    emptyFunction : function(){},
    
    // reference to global object (e.g., window)
    GLOBAL : (function(){ return this; })()
  };
  
  // Make Mojo visible to the global namespace
  Mojo.GLOBAL.Mojo = Mojo;
  
  var isObject = function(o)
  {
    return  o != null && Object.prototype.toString.call(o) === Mojo.IS_OBJECT_TO_STRING;
  };

  var isArray = function(o)
  {
    return o != null && Object.prototype.toString.call(o) === Mojo.IS_ARRAY_TO_STRING;
  };

  var isFunction = function(o)
  {
    return o != null && Object.prototype.toString.call(o) === Mojo.IS_FUNCTION_TO_STRING;
  };

  var isDate = function(o)
  {
    return o != null && Object.prototype.toString.call(o) === Mojo.IS_DATE_TO_STRING;
  };

  var isString = function(o)
  {
    return o != null && Object.prototype.toString.call(o) === Mojo.IS_STRING_TO_STRING;
  };

  var isNumber = function(o)
  {
    return o != null && Object.prototype.toString.call(o) === Mojo.IS_NUMBER_TO_STRING;
  };
  
  var isBoolean = function(o)
  {
    return o != null && Object.prototype.toString.call(o) === Mojo.IS_BOOLEAN_TO_STRING;
  };
  
  var isUndefined = function(o)
  {
    return typeof o === 'undefined';
  };
  
  var isElement = function(o)
  {
    return o != null && o instanceof Element; // TODO make cross-browser
  };
  
  Mojo.SUPPORTS_NATIVE_PARSING = Mojo.GLOBAL.JSON != null && isFunction(Mojo.GLOBAL.JSON.parse) && isFunction(Mojo.GLOBAL.JSON.stringify);
  
  var _isInitialized = false;
  var _classes = {};
  var _pseudoConstructor = function(){};
  var _native = []; // array of native bootstrapping classes
  
  // references to commonly used classes to avoid slow lookups
  var _baseClassRef = null;
  var _metaClassRef = null;
  var _exceptionClassRef = null;
  var _constantClassRef = null;
  var _methodClassRef = null;
  
  var meta = {

    newInstance : function(type)
    {
      if (!Mojo.Meta.classExists(type))
      {
        throw new _exceptionClassRef("Unable to newInstance " + type + ". The specified class does not exist.");
      }
      
      var klass = _classes[type];
      var args = [].splice.call(arguments, 1, arguments.length);
      
      var obj = new klass(_pseudoConstructor);
      klass.prototype.initialize.apply(obj, args);
      
      return obj;
    },
    
    shorthand : function(pattern, attachTo, includePackage)
    {
      var anchorObj = attachTo || Mojo.GLOBAL;
      
      var r = '^'+pattern.replace(/\./g, '\\.').replace(/\*/g, '.*')+'$';
      var re = new RegExp(r);
      
      var classNames = Mojo.Util.getKeys(_classes);
      for(var i=0; i<classNames.length; i++)
      {
        var className = classNames[i];
        if(re.test(className))
        {
          var klass = _classes[className];
          
          if(includePackage)
          {
            var namespace = Mojo.Meta._buildPackage(klass.getMetaClass().getPackage(), anchorObj);
            namespace[klass.getMetaClass().getName()] = klass;
          }
          else
          {
            anchorObj[klass.getMetaClass().getName()] = klass;
          }
        }
      }
    },
    
    findClass : function(type)
    {
      return _classes[type];
    },
    
    classCount : function()
    {
      return Mojo.Util.getKeys(_classes).length;
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

        if(!currentBuild[part])
        {
          currentBuild[part] = {};
        }

        currentBuild = currentBuild[part];
      }

      return currentBuild;      
    },
    
    dropClass : function(type)
    {
      // FIXME drop all subclasses from tree (maybe add destroy() method to MetaClass that drops its own children)
      // FIXME remove all aliases and shorthand
      if (!Mojo.Meta.classExists(type))
      {
        throw new _exceptionClassRef("Unable to dropClass " + type + ". The specified class does not exist.");
      }
      
      delete _classes[type];
    },
    
    getClasses : function()
    {
      return Mojo.Util.getKeys(_classes);
    },
    
    classExists : function(type)
    {
      return type in _classes;
    },
    
    _makeSingleton : function(klass)
    {
      // block normal instantiation
      var sInitialize = klass.prototype.initialize;
      klass.prototype.initialize = function(){
     
        var message = "Cannot instantiate the singleton class ["+this.getMetaClass().getQualifiedName()+"]. " +
          "Use the static [getInstance()] method instead.";
        throw new _exceptionClassRef(message);        
      };
      
      klass.getInstance = (function(sInit){
        
        var instance = null;
        
        return function(){
          
          if(instance == null)
          {
            // TODO use something other than function swapping?
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
      
        if(_isInitialized && this.getMetaClass().isAbstract())
        {
          var msg = "Cannot instantiate the abstract class ["+this.getMetaClass().getQualifiedName()+"].";
          throw new _exceptionClassRef(msg);
        }
        
        this.__context = {}; // super context
        
        if(arguments.length === 1 && arguments[0] === _pseudoConstructor)
        {
          _pseudoConstructor(); // for "reflective" newInstance()
                      // calls.
        }
        else
        {
          this.initialize.apply(this, [].splice.call(arguments,0,arguments.length));
        }
      };      
    },
    
    _addMethod : function(klass, superClass, methodName, definition)
    {
      var isFunc = isFunction(definition);
      var method = isFunc ? definition : definition.Method;
    
      // add instance method to the prototype
      klass.prototype[methodName] = method;
      
      // add override accessor if the parent class defines the same method
      if(superClass !== Object && isFunction(superClass.prototype[methodName]))
      {
        var superName = '$'+methodName;
        klass.prototype[superName] = (function(m){
      
          return function(){
            
            // find the next different method on the superclass prototype
            // and execute it because it is the overridden super method.
            var current = this.__context[superName] || this.constructor;
            var next = current.getMetaClass().getSuperClass();
            
            while(current.prototype[m] === next.prototype[m])
            {
              next = next.getMetaClass().getSuperClass();
            }
            
            this.__context[superName] = next;
            
            var retObj = next.prototype[m].apply(this, arguments);
            
            this.__context[superName] = current;
            
            return retObj;
          };
        })(methodName);
      }
      
      var ret = {name : methodName, isStatic : false, isAbstract : (!isFunc && definition.IsAbstract),
          isConstructor : (methodName === 'initialize'), method : method, klass: klass,
          enforceArity: (definition.EnforceArity || false)};
      
      klass = null;
      superClass = null;
      methodName = null;
      definition = null;
      isFunc = null;
      method = null;
      
      return ret;
    },
    
    _newType : function(metaRef, qualifiedName, def, isIF)
    {
      if(!isString(qualifiedName) || qualifiedName.length === 0)
      {
        throw new _exceptionClassRef('The first parameter must be a valid qualified type name.');
      }
      else if(def != null && !isObject(def))
      {
        throw new _exceptionClassRef('The second parameter must be a configuration object literal.');
      }
      
      // Set type defaults
      def = def || {};

      var extendsClass = null;
      var constants = {};
      var instances = {};
      
      var statics = {};
      var isSingleton = false;
      var isAbstract = false;
      var interfaces = [];
      
      // override defaults and validate properties
      for(var prop in def)
      {
        if(def.hasOwnProperty(prop))
        {
          switch(prop){
            case 'Extends':extendsClass = def[prop]; break;
            case 'Constants': constants = def[prop]; break;
            case 'Instance': instances = def[prop]; break;
            case 'Static':
              if(isIF)
              {
                throw new _exceptionClassRef('The interface ['+qualifiedName+'] cannot define static properties or methods.');
              }
              statics = def[prop]; break;
            case 'IsSingleton':
              if(isIF)
              {
                throw new _exceptionClassRef('The interface ['+qualifiedName+'] cannot be a singleton.');
              }
              isSingleton = def[prop]; break;
            case 'IsAbstract':
              if(isIF)
              {
                throw new _exceptionClassRef('The interface ['+qualifiedName+'] cannot be abstract.');
              }
              isAbstract = def[prop]; break;
            case 'Implements':
              if(isIF)
              {
                throw new _exceptionClassRef('The interface ['+qualifiedName+'] cannot implement other Interfaces.');
              }
              
              var ifs = def[prop];
              if(!isArray(ifs))
              {
                ifs = [ifs];
              }
              
              for(var i=0; i<ifs.length; i++)
              {
                var IF = ifs[i];
                var ifKlass = isString(IF) ? _classes[IF] : IF;
                if(ifKlass.getMetaClass().isInterface())
                {
                  interfaces.push(ifKlass);
                }
                else
                {
                  throw new _exceptionClassRef('The class ['+qualifiedName+'] cannot implement the class['+ifKlass.getQualifiedName()+'].');
                }
              }
              
              break;
            default: throw new _exceptionClassRef('The property ['+prop+'] on type ['+qualifiedName+'] is not recognized.');
          }
        }
      }
      
      var superClass;
      if(isFunction(extendsClass))
      {
        superClass = extendsClass;
      }
      else if(isString(extendsClass))
      {
        superClass = _classes[extendsClass];
      }
      else
      {
        superClass = _baseClassRef;
      }
      
      if(!superClass)
      {
        throw new _exceptionClassRef('The class ['+qualifiedName+'] does not extend a valid class.');
      }
      
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
      if(!isIF && !instances.initialize)
      {
        instances.initialize = function(){};
      }
      else if(isIF && instances.initialize)
      {
        throw new _exceptionClassRef('The interface ['+qualifiedName+'] cannot define an initialize() constructor.');
      }
      else if(isIF)
      {
        instances.initialize = function(obj){
          this.getMetaClass()._enforceAnonymousInnerClass(obj);
          Mojo.Util.copy(obj, this);
        };
      }
      
      // wrap the constructor function
      var klass = metaRef._createConstructor();
      
      // add the namespace to the global object and Mojo.$
      var namespace = metaRef._buildPackage(packageName, Mojo.GLOBAL);
      namespace[className] = klass;
       
      namespace = metaRef._buildPackage(packageName, Mojo.$);
      namespace[className] = klass;
      
      _classes[qualifiedName] = klass;
      
      // temp function is used for inheritance instantiation, to
      // avoid calling actual class constructor
      var temp = function(){};
      temp.prototype = superClass.prototype;
      klass.prototype = new temp();

      // reset constructor to point to the class, such that
      // new A().constructor === A
      klass.prototype.constructor = klass;

      // config obj for MetaClass constructor
      var config = {
        packageName : packageName,
        className : className,
        klass : klass,
        superClass : superClass,
        instanceMethods : {},
        staticMethods : {},
        isAbstract : isAbstract,
        isInterface : isIF,
        qualifiedName : qualifiedName,
        isSingleton : isSingleton,
        interfaces : interfaces,
        constants : []
      };

      for(var m in instances)
      {
        var methodConfig = metaRef._addMethod(klass, superClass, m, instances[m]);
        config.instanceMethods[m] = methodConfig;
      }
      
      if(isSingleton)
      {
        var methodDef = this._makeSingleton(klass);
        config.staticMethods.getInstance = methodDef;
      }
      
      // add constants
      for(var c in constants)
      {
        config.constants.push({name : c, value: constants[c]});
      }
      
      // add static methods
      for(var m in statics)
      {
        if(isFunction(statics[m]))
        {
          if (statics[m].IsAbstract)
          {
            throw new _exceptionClassRef("The method " + m + " defined on the class " + className + " cannot be both static and abstract.");
          }      

          config.staticMethods[m] = {name : m, isStatic : true, isAbstract : false, 
            isConstructor : false, method : statics[m], klass: klass, enforceArity: false};
        }
        else
        {
          // FIXME wrap static props in a Property class and have them
      // optionally
          // inherited (or use visibility modifiers?)
          klass[m] = statics[m];
        }
      }
      
      // attach the metadata Class
      if(_native !== null)
      {
        // MetaClass will be constructed later to complete bootstrapping
        klass.__metaClass = config;
        klass.prototype.__metaClass = config;
        _native.push(klass);
      }
      else
      {
        klass.__metaClass = new _metaClassRef(config);
        klass.prototype.__metaClass = klass.__metaClass;
      }
      
      return klass;      
    },
    
    newInterface : function(qualifiedName, definition)
    {
      var metaRef = Mojo.Meta || this;
      return metaRef._newType(metaRef, qualifiedName, definition, true);
    },
    
    newClass : function(qualifiedName, definition)
    {
      var metaRef = Mojo.Meta || this;
      return metaRef._newType(metaRef, qualifiedName, definition, false);
    }
  };


  _baseClassRef = meta.newClass(Mojo.ROOT_PACKAGE+'Base', {

  IsAbstract : true,
  
  Extends : Object,

  Instance : {

    initialize : function()
    {
      // Default Constructor
    },
  
    equals : function(obj)
    {
      return this === obj;
    },
  
    clone : function()
    {
      return Mojo.Meta.newInstance(this.getMetaClass().getQualifiedName(), [].splice.call(arguments, 0, arguments.length));
    },
    
    valueOf : function()
    {
      return this;
    },
    
    toString : function()
    {
      return '['+this.getMetaClass().getQualifiedName()+'] instance';
    }
  }
});

  _metaClassRef = meta.newClass(Mojo.ROOT_PACKAGE+'MetaClass', {

  Instance : {
  
    initialize : function(config)
    {
      this._packageName = config.packageName;
      this._className = config.className;
      this._isAbstract = config.isAbstract;
      this._isInterface = config.isInterface;
      this._isSingleton = config.isSingleton;
      this._klass = config.klass;
      this._superClass = config.superClass;
      this._qualifiedName = config.qualifiedName;
      this._subclasses = {};
      this._interfaces = config.interfaces;
      
      var notBase = this._superClass !== Object;  
      
      this._addInstanceMethods(notBase, config.instanceMethods);
      this._addStaticMethods(notBase, config.staticMethods);      
      this._addConstants(notBase, config.constants);
      
      if(notBase)
      {
        this._superClass.getMetaClass()._addSubClass(this._qualifiedName, this._klass);
      }
      
      this._addMetaClassMethod();
      
      if(_isInitialized && !this._isInterface)
      {
        this._enforceInterfaceMethods();
      }
    },
    
    _addInstanceMethods : function(notBase, tInstances)
    {
      var mKlass = _methodClassRef;
      
      this._instanceMethods = {};
      var abstractMethods = {};
      if(notBase)
      {
        // instance methods will be copied via prototype
        var pInstances = this._superClass.getMetaClass().getInstanceMethods(true);
        for(var i in pInstances)
        {
          var method = pInstances[i];
          this._instanceMethods[i] = method;
          
          if(method.isAbstract())
          {
            abstractMethods[i] = method;
          }
        }
      }
        
      for(var i in tInstances)
      {
        var definition = tInstances[i];
      
        // Check for a method override
        if(this._instanceMethods.hasOwnProperty(i))
        {
          var overridden = this._instanceMethods[i];
          definition.overrideKlass = definition.klass;
          definition.klass = overridden.getDefiningClass();
        }
        
        this._instanceMethods[i] = new mKlass(definition, this);
        
        if(i in abstractMethods)
        {
          delete abstractMethods[i]; // abstract method implemented!
        }
      }
      
      // Make sure all abstract methods are implemented
      if(!this._isAbstract)
      {
        var unimplemented = [];
        for(var i in abstractMethods)
        {
          if(abstractMethods.hasOwnProperty(i))
          {
            unimplemented.push(abstractMethods[i].getName());
          }
        }
        
        if(unimplemented.length > 0)
        {
          var msg = "The class ["+this._qualifiedName+"] must " + 
            "implement the abstract method(s) ["+unimplemented.join(', ')+"].";
          throw new _exceptionClassRef(msg);
        }
      }
    },
    
    _addStaticMethods : function(notBase, tStatics)
    {
      var mKlass = _methodClassRef;
      
      this._staticMethods = {};
      
      if(notBase)
      {
        // static methods must be explicitly copied
        var pStatics = this._superClass.getMetaClass().getStaticMethods(true);
        for(var i in pStatics)
        {
          var mStatic = pStatics[i];
          this._staticMethods[mStatic.getName()] = mStatic;
          
          this._klass[mStatic.getName()] = mStatic.getMethod();
        }
      }
        
      for(var i in tStatics)
      {
        var definition = tStatics[i];
      
        if(this._staticMethods.hasOwnProperty(i))
        {
          var overridden = this._staticMethods[i];
          definition.overrideKlass = definition.klass;
          definition.klass = overridden.getDefiningClass();
        }
        
        // add the method metadata
        var method = new mKlass(definition, this);
        this._staticMethods[i] = method;
        
        // add the actual method to the class
        this._klass[i] = definition.method;
      }
    },
    
    _addConstants : function(notBase, constants)
    {
      this._constants = {};
      var cKlass = _constantClassRef;
      if(notBase)
      {
        var pConstants = this._superClass.getMetaClass().getConstants(true);
        for(var i in pConstants)
        {
          if(pConstants.hasOwnProperty(i))
          {
            this._constants[i] = pConstants[i];
          }
        }
      }
      
      for(var i=0; i<constants.length; i++)
      {
        var constObj = new cKlass(constants[i]);
        
        if(notBase && this._constants[constObj.getName()])
        {
        // FIXME remove _setOverride and use same methodology as instance/static
    // methods above
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
    },
    
    _addMetaClassMethod : function()
    {
      var mName = Mojo.META_CLASS_GETTER;
      
      // Each class constructor function and instance gets
      // a method to return this Class instance.
      this._klass[mName] = (function(metaClass){
        return function() {
          return metaClass;
        };
      })(this);
      
      this._klass.prototype[mName] = this._klass[mName];
      
      var baseClass = _baseClassRef;
      this._instanceMethods[mName] = new _methodClassRef({
        name : mName,
        isStatic : false,
        isAbstract : false, 
        isConstructor : false,
        method : this._klass[mName],
        klass: baseClass,
        overrideKlass : this._klass,
        enforceArity : false
      }, this);
      
     this._staticMethods[mName] = new _methodClassRef({
        name : mName,
        isStatic : true,
        isAbstract : false, 
        isConstructor : false,
        method : this._klass[mName],
        klass: baseClass,
        overrideKlass : this._klass,
        enforceArity : false
      }, this);
    },
    
    _addSubClass : function(qualifiedName, klass)
    {
      this._subclasses[qualifiedName] = klass;
    },
    
    isSingleton : function()
    {
      return this._isSingleton;
    },
    
    isInterface : function()
    {
      return this._isInterface;
    },
    
    getInterfaces : function()
    {
      return this._interfaces;
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
      if(klass instanceof this.constructor)
      {
        return klass;
      }
      else if(Mojo.Util.isFunction(klass) || klass instanceof _baseClassRef)
      {
        return klass.getMetaClass();
      }
      else if(Mojo.Util.isString(klass))
      {
        var foundClass = Mojo.Meta.findClass(klass);
        if(foundClass)
        {
          return foundClass.getMetaClass();
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
    
      var superClass = classObj.getSuperClass();
      while(superClass !== Object)
      {
        if(superClass.getMetaClass() === this)
        {
          return true;
        }
        
        superClass = superClass.getMetaClass().getSuperClass();
      }
      
      return false;
    },
    
    isSubClassOf : function(klass)
    {
      var classObj = this._getClass(klass); 
      return classObj.isSuperClassOf(this);
    },
    
    getFunction : function()
    {
      return this._klass;
    },
    
    _enforceAnonymousInnerClass : function(obj)
    {
      var IFs = [this._klass];
      var parentClass = this._superClass;
      while(parentClass.getMetaClass().isInterface())
      {
        var parentMeta = parentClass.getMetaClass();
        IFs.push(parentMeta.getFunction());
        parentClass = parentMeta.getSuperClass();
      }
      
      var toImplement = [];
      for(var i=0; i<IFs.length; i++)
      {
        toImplement = toImplement.concat(IFs[i].getMetaClass().getInstanceMethods());
      }
      
      var unimplemented = [];
      for(var i=0; i<toImplement.length; i++)
      {
        var method = toImplement[i];
        if(!method.getDefiningClass().getMetaClass().isInterface())
        {
          continue;
        }
        
        var name = method.getName();
        if(!(isFunction(obj[name])) ||
            !obj.hasOwnProperty(name))
        {
          unimplemented.push(name);
        }
      }
      
      if(unimplemented.length > 0)
      {
        var msg = "The anonymous inner class ["+obj+"] must " + 
        "implement the interface method(s) ["+unimplemented.join(', ')+"].";
        throw new _exceptionClassRef(msg);
      }
    },
    
    _enforceInterfaceMethods : function()
    {
      var IFs = this.getInterfaces();
      var parentClass = this._superClass;
      while(parentClass !== Object)
      {
        var parentMeta = parentClass.getMetaClass();
        IFs = IFs.concat(parentMeta.getInterfaces());
        parentClass = parentMeta.getSuperClass();
      }
      
      var toImplement = [];
      for(var i=0; i<IFs.length; i++)
      {
        toImplement = toImplement.concat(IFs[i].getMetaClass().getInstanceMethods());
      }
      
      var unimplemented = [];
      for(var i=0; i<toImplement.length; i++)
      {
        var method = toImplement[i];
        if(!method.getDefiningClass().getMetaClass().isInterface())
        {
          continue;
        }
        
        var name = method.getName();
        var isDefined = (name in this._instanceMethods);
        if(!this._isAbstract && !isDefined)
        {
          unimplemented.push(name);
        }
        else if(isDefined && method.enforcesArity())
        {
          var implemented = this._instanceMethods[name];
          if(method.getArity() !== implemented.getArity())
          {
            var ifMethod = method.getDefiningClass().getMetaClass().getQualifiedName()+'.'+name;
            var msg = "The method ["+this._qualifiedName+"."+name+"] must " + 
            "define ["+method.getArity()+"] arguments as required by the interface method ["+ifMethod+"].";
            throw new _exceptionClassRef(msg);
          }
        }
      }
      
      if(unimplemented.length > 0)
      {
        var msg = "The class ["+this._qualifiedName+"] must " + 
        "implement the interface method(s) ["+unimplemented.join(', ')+"].";
        throw new _exceptionClassRef(msg);
      }
    },    
    
    doesImplement : function(IFinput)
    {
      if(this.isInterface() && this.isSubClassOf(IFinput))
      {
        // check for anonymous inner classes
        return true;
      }
      else if(!this.isInterface())
      {
        var klass = this._klass;
        while(klass !== Object)
        {
          var meta = klass.getMetaClass();
          var IFs = meta.getInterfaces();
          for(var i=0; i<IFs.length; i++)
          {
            var IF = IFs[i];
            if(IF.getMetaClass().isSubClassOf(IFinput))
            {
              return true;
            }
          }
          klass = meta.getSuperClass();
        }
        
        return false;
      }
      else
      {
        return false;
      }
    },
    
    isInstance : function(obj)
    {
      if(!isObject(obj))
      {
        return false;
      }
      
      // cover regular classes and anonymous inner classes
      if(obj instanceof this._klass)
      {
        return true;
      }

      if(this.isInterface())
      {
        if(obj instanceof _baseClassRef)
        {
          // compare the object's interfaces to *this* IF class.
          var klass = obj.constructor;
          while(klass !== Object)
          {
            var meta = klass.getMetaClass();
            var IFs = meta.getInterfaces();
            for(var i=0; i<IFs.length; i++)
            {
              var IF = IFs[i];
              if(IF.getMetaClass().isSubClassOf(this._klass))
              {
                return true;
              }
            }
            klass = meta.getSuperClass();
          }
          
          return false;
        }
        else
        {
          return false;
        }
      }
      else
      {
        return false; // we don't know what this object is
      }
    },
    
    getSuperClass : function()
    {
      return this._superClass;
    },
    
    newInstance : function()
    {
      var args = [this.getQualifiedName()].concat([].splice.call(arguments, 0, arguments.length));
      return Mojo.Meta.newInstance.apply(this, args);
    },
    
    toString : function()
    {
      return '[MetaClass] ' + this.getQualifiedName();
    },
    
    toJSON : function ()
    {
      return undefined;
    }
  }
});

  _constantClassRef = meta.newClass(Mojo.ROOT_PACKAGE+"Constant", {
  
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

  _methodClassRef = meta.newClass(Mojo.ROOT_PACKAGE+'Method', {

  Instance : {
  
    initialize : function(config, metaClass)
    {
      this._name = config.name;
      this._isStatic = config.isStatic;
      this._isConstructor = config.isConstructor;
      this._klass = config.klass || null;
      this._overrideKlass = config.overrideKlass || null;
      this._isAbstract = config.isAbstract;
      this._enforceArity = config.enforceArity || false;
      
      if(_isInitialized && !metaClass.isAbstract()
         && this._isAbstract)
      {
        var msg = "The non-abstract class ["+metaClass.getQualifiedName()+"] cannot " + 
          "cannot declare the abstract method ["+this._name+"].";
        throw new _exceptionClassRef(msg);
      }
      
      if(this._isAbstract)
      {
        this._createAbstractMethod();
        this._arity = 0;
      }
      else
      {
        this._arity = config.method.length;
      }
    },
    
    _createAbstractMethod : function()
    {
      // Add the abstract method to always throw an error. This
      // will replace any method already on the prototype.
      this._klass.prototype[this._name] = (function(name){
        return function(){

          var definingClass = this.getMetaClass().getMethod(name).getDefiningClass().getMetaClass().getQualifiedName();

          var msg = "Cannot invoke the abstract method ["+name+"] on ["+definingClass+"].";
          throw new _exceptionClassRef(msg);
        };
      })(this._name);
    },
    
    isAbstract : function()
    {
      return this._isAbstract;
    },
    
    enforcesArity : function()
    {
      return this._enforceArity;
    },
    
    isConstructor : function()
    {
      return this._isConstructor;
    },
    
    getArity : function()
    {
      return this._arity;
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
      var klass = this._overrideKlass || this._klass;
      return this._isStatic ? klass[this._name] : klass.prototype[this._name];
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
  }
  
});

// Finish bootstrapping the class system
for(var i=0; i<_native.length; i++)
{
  var bootstrapped = _native[i];
  
  // Convert the JSON config __metaClass into a MetaClass instance
  // and re-attach the metadata to the class definition.
  var cClass = new _metaClassRef(bootstrapped.__metaClass);
  bootstrapped.__metaClass = cClass;
  bootstrapped.prototype.__metaClass = cClass;
}
_native = null;

// convert the Meta object to a class
var metaProps = {};
for(var i in meta)
{
  if(meta.hasOwnProperty(i))
  {
    metaProps[i] = meta[i];
  }
}

Mojo.Meta = metaProps.newClass('Mojo.Meta', {
  Static : metaProps  
});
meta = null;

_isInitialized = true;



Mojo.Meta.newClass('Mojo.Util', {

  IsAbstract : true,
  
  Instance : {
  
    initialize : function(){}
  },

  Static : {
  ISO8601_REGEX : "^([0-9]{4})-([0-9]{2})-([0-9]{2})T([0-9]{2}):([0-9]{2}):([0-9]{2})([-+])([0-9]{2})([0-9]{2})$",
    
    isObject : isObject,

    isArray : isArray,

    isFunction : isFunction,

    isDate : isDate,

    isString : isString,

    isNumber : isNumber,
    
    isBoolean : isBoolean,
    
    isUndefined : isUndefined,
    
    isElement : isElement,
    
    bind : function(thisRef, func)
    {
      if (!Mojo.Util.isFunction(func))
      {
        throw new _exceptionClassRef("Mojo.Util.bind: Unable to bind,  the second parameter is not a function.");
      }
    
      var args = [].splice.call(arguments, 2, arguments.length);
      return function(){
        return func.apply(thisRef, args.concat([].splice.call(arguments, 0, arguments.length)));
      };
    },
    
    curry : function(func)
    {
      var args = [].splice.call(arguments, 1, arguments.length);
      return function(){
        return func.apply(this, args.concat([].splice.call(arguments, 0, arguments.length)));
      };
    },
    
    /**
   * Extracts all script tag contents and returns a string of executable code
   * that can be evaluated.
   */
    extractScripts : function(html)
    {
      var scripts = html.match(/<script\b[^>]*>[\s\S]*?<\/script>/img);
      var executables = [];
      if(scripts != null)
      {
        for(var i=0; i<scripts.length; i++)
        {
          var scriptM = scripts[i].match(/<script\b[^>]*>([\s\S]*?)<\/script>/im);
          executables.push(scriptM[1]);
        }
      }

      return executables.join('');
    },

    /**
   * Removes all scripts from the HTML and returns a string of the processed
   * HTML.
   */
    removeScripts : function(html)
    {
      return html.replace(/<script\b[^>]*>[\s\S]*?<\/script>/img, '');
    },    
    
    // TODO give credit to
    // http://blog.stevenlevithan.com/archives/faster-trim-javascript
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
        
        var args = [].splice.call(arguments, 0, arguments.length);
        if(func.memoCache[args])
        {
          return func.memoCache[args];
        }
        else
        {
          func.memoCache[args] = func.apply(context || this, args); 
          return func.memoCache[args];
        }
      };
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
    
    setISO8601 : function (date, string, ignoreTimezone)
    {
      var regexp = new RegExp(Mojo.Util.ISO8601_REGEX);

      if(!Mojo.Util.isString(string) || string === '' || !regexp.test(string))
      {
        return false;
      }
            
      var d = string.match(regexp);

      var offset = 0;
      var tempDate = new Date(d[1], 0, 1);

      if (d[2]) { tempDate.setMonth(d[2] - 1); }
      if (d[3]) { tempDate.setDate(d[3]); }
      if (d[4]) { tempDate.setHours(d[4]); }
      if (d[5]) { tempDate.setMinutes(d[5]); }
      if (d[6]) { tempDate.setSeconds(d[6]); }
      if (d[8]) {
          offset = (Number(d[8]) * 60) + Number(d[9]);
          offset *= ((d[7] == '-') ? 1 : -1);
      }
      
      var time = Number(tempDate);

      if(ignoreTimezone !== true)
      {
        offset -= tempDate.getTimezoneOffset();
        time += (offset * 60 * 1000);
      }
      
      date.setTime(Number(time));
      
      return true;
    },

    toISO8601 : function (date, ignoreTimezone)
    {
      /*
     * ISO8601 format: Complete date plus hours, minutes, seconds and a
     * decimal fraction of a second YYYY-MM-DDThh:mm:ssZ (eg
     * 1997-07-16T19:20:30.45-0100)
     */
      var format = 6;
      var offset = date.getTimezoneOffset()/60;
      
      var tempDate = date;
         
      var zeropad = function (num) {
      var value = (num < 0 ? num * -1 : num);
      
        return (value < 10 ? '0' + value : value);
      };

      var str = "";

      // Set YYYY
      str += tempDate.getFullYear();
      // Set MM
      str += "-" + zeropad(tempDate.getMonth() + 1);
      // Set DD
      str += "-" + zeropad(tempDate.getDate());
      // Set Thh:mm
      str += "T" + zeropad(tempDate.getHours()) + ":" + zeropad(tempDate.getMinutes());
      // Set ss
      str += ":" + zeropad(tempDate.getSeconds());        
      // Set TZD
      
      if(!ignoreTimezone)
      {
        str += (offset > 0 ? '-' : '+') + zeropad(offset) + '00';
      }
      
      return str;
    },
    
    /**
   * This JSON object is based on the reference code provided by Douglas
   * Crockford. The original, commented source is located at
   * http://json.org/json2.js.
   */    
    JSON : (function(){

        var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
            escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
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

            escapable.lastIndex = 0;
            return escapable.test(string) ?
                '"' + string.replace(escapable, function (a) {
                    var c = meta[a];
                    return Mojo.Util.isString(c) ? c :
                        '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                }) + '"' :
                '"' + string + '"';
        }

        function f(n) {
          // Format integers to have at least two digits.
          return n < 10 ? '0' + n : n;
        }

        // Normally we wouldn't modify the prototype of a native object
        // but the spec defines the following behavior for Date serialization.
        if (typeof Date.prototype.toJSON !== 'function') {

          Date.prototype.toJSON = function (key) {

              return isFinite(this.valueOf()) ?
                     this.getUTCFullYear()   + '-' +
                   f(this.getUTCMonth() + 1) + '-' +
                   f(this.getUTCDate())      + 'T' +
                   f(this.getUTCHours())     + ':' +
                   f(this.getUTCMinutes())   + ':' +
                   f(this.getUTCSeconds())   + 'Z' : null;
          };
        }


        function str(key, holder) {

            var i,          // The loop counter.
                k,          // The member key.
                v,          // The member value.
                length,
                mind = gap,
                partial,
                value = holder[key];

            if(typeof value === 'function')
            {
              return undefined;
            }
            
            var isClass = value instanceof _baseClassRef;
            
            if (value && typeof value === 'object' &&
                typeof value.toJSON === 'function') {
              value = value.toJSON(key);
            }

            if (typeof rep === 'function') {
                value = rep.call(holder, key, value);
            }
            
            // Special case: if this is a Runway classes then return
            // because it has already been serialized.
            if(isClass)
            {
              return value;
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

                if (Mojo.Util.isArray(value)) {

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
                            v = str(k, value);
                            if (v) {
                                partial.push(quote(k) + (gap ? ': ' : ':') + v);
                            }
                        }
                    }
                } else {

                    for (k in value) {
                        if (Object.hasOwnProperty.call(value, k)) {
                            v = str(k, value);
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
        
            stringify : function (value, replacer, space) {

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
                    throw new _exceptionClassRef('Mojo.Util.getJSON');
                }

                return str('', {'': value});
            },


            parse : function (text, reviver) {

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

                text = String(text);
                cx.lastIndex = 0;
                if (cx.test(text)) {
                    text = text.replace(cx, function (a) {
                        return '\\u' +
                            ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                    });
                }

                if (/^[\],:{}\s]*$/.
                  test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@').
                  replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').
                  replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {

                    j = eval('(' + text + ')');

                    return typeof reviver === 'function' ?
                        walk({'': j}, '') : j;
                }

                throw new _exceptionClassRef('Mojo.Util.getObject');
            }
            
        };

    })(),
    
    getKeys : function(obj, hasOwnProp)
    {
      var keys = [];
      for(var i in obj)
      {
        if(!hasOwnProp || obj.hasOwnProperty(i))
        {
          keys.push(i);
        }
      }

      return keys;
    },

    getValues : function(obj, hasOwnProp)
    {
      var values = [];
      for(var i in obj)
      {
        if(!hasOwnProp || obj.hasOwnProperty(i))
        {
          values.push(obj[i]);
        }
      }

      return values;
    },

    copy : function(source, dest, hasOwnProp)
    {
      if(Mojo.Util.isObject(source))
      {
        for(var i in source)
        {
          if(!hasOwnProp || source.hasOwnProperty(i))
          {
            dest[i] = source[i];
          }
        }
      }
      
      return dest;
    },

    toObject : function(json, reviver)
    {
      if (Mojo.Util.isString(json))
      {
        var useNativeParsing = Mojo.ClientSession.isNativeParsingEnabled();

        if (useNativeParsing && Mojo.SUPPORTS_NATIVE_PARSING)
        {
          return JSON.parse(json, reviver);
        }
        else
        {
          return Mojo.Util.JSON.parse(json, reviver);
        }
      }
      else
      {
        return json;
      }
    },
    
    getObject : function(json, reviver)
    {
      return Mojo.Util.toObject(json, reviver);
    },

    toJSON : function(obj, replacer)
    {
      var useNativeParsing = Mojo.ClientSession.isNativeParsingEnabled();
      
      // Use the browser's toJSON if it exists
      if (useNativeParsing && Mojo.SUPPORTS_NATIVE_PARSING)
      {
         return JSON.stringify(obj, replacer);
      }
      else
      {
        // Otherwise use Runway's
        return Mojo.Util.JSON.stringify(obj, replacer);
      }      
    },
    
    // alias for toJSON()
    getJSON : function(obj, replacer)
    {
      return Mojo.Util.toJSON(obj, replacer);
    },

    convertToType : function(value)
    {
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
        // special case for ValueObjectDTOs, which define both a dto_type and
    // _type property
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
            e = new _exceptionClassRef(obj);
          }
        }
        else
        {
          e = new _exceptionClassRef(obj);
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
          var e = new _exceptionClassRef(responseText);
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
      
      // FIXME use form.elements[] instead and remove inner function

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
            params.push(encodeURIComponent(key) + "[]=" + encodeURIComponent(entry[i]));
          }
        }
        else
        {
          params.push(encodeURIComponent(key) + "=" + encodeURIComponent(entry));
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
    }
    
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
    
    writeInfo : {
      IsAbstract : true
    },
    
    writeWarning : {
      IsAbstract : true
    },
    
    writeError : {
      IsAbstract : true
    }
  
  }
});

var iterableIF = Mojo.Meta.newInterface("Mojo.IterableIF", {
  Instance : {
    iterator : function(){},
    iterate : function(callback){}
  }
});

var iteratorIF = Mojo.Meta.newInterface("Mojo.IteratorIF", {
  Instance : {
    next:function(){},
    hasNext:function(){},
    remove:function(){}
  }
});

var collection = Mojo.Meta.newClass("Mojo.Collection", {
  Implements : [iterableIF, iteratorIF],
  IsAbstract: true,
  Instance : {
    iterator:function(){},
    iterate:function(){},
    next:function(){},
    hasNext:function(){},
    remove:function(){}
  }
});

Mojo.Meta.newClass("Mojo.Map", {
  Extends : collection,
  Instance : {
    
    initialize : function()
    {
  
      this._map = {};
      this._size = 0;
    },
    
    put : function(key, value)
    {
      this._map[key] = value;
      this._size++;
    },
    
    remove : function(key)
    {
      delete this._map[key];
      this._size--;
    },
    
    get : function(key)
    {
      return this._map[key];
    },
    
    clear : function()
    {
      for (var key in this._map)
      {
        this.remove(key);
      }
    },
    
    containsKey : function(key)
    {
      if (this._map[key] != undefined)
      {
        return true;
      }
      
      return false;
    },
    
    containsValue : function(value)
    {
      for (var k in this._map)
      {
        if (this._map[k] === value)
          return true;
      }
      
      return false;
    },
    
    isEmpty : function()
    {
      return (this._size == 0);
    },
    
    keySet : function()
    {
      return Mojo.Util.getKeys(this._map, true);
    },
    
    putAll : function(map)
    {
      if (typeof map == "Mojo.Map")
      {
        map = map.getData();
      }
      
      for (var k in map)
      {
        this.put(k, map[k]);
      }
    },
    
    size : function()
    {
      return this._size;
    },
    
    values : function()
    {
      return Mojo.Util.getValues(this._map, true);
    },

    getData : function()
    {
      return this._map;
    }
    
  }
  
});

Mojo.Meta.newClass("Mojo.Set", {
  Extends: collection,
  Instance : {
    
    initialize : function(collection)
    {
      this._map = Mojo.Meta.newInstance("Mojo.Map");
      

      if(collection)
      {
        this.addAll(collection);
      }
    },
    
    add : function(obj)
    {
      this._map.put(obj, obj);
    },
    
    addAll : function(obj)
    {
      if(isArray(obj))
      {
        for(var i=0; i<obj.length; i++)
        {
          this.add(obj[i]);
        }
      }
      else if(isObject(obj))
      {
        for (var k in obj)
        {
          if(obj.hasOwnProperty(k))
          {
            this.add(obj[k]);
          }
        }
      }
      else
      {
        throw new _exceptionClassRef('Object type ['+typeof obj+'] is not a recognized ' +
            'parameter for ['+this.getMetaClass().getQualifiedName()+'.addAll].');
      }
    },
    
    clear : function()
    {
      this._map.clear();
    },
    
    contains : function(obj)
    {
      return this._map.containsKey(obj);
    },
    
    containsAll : function(obj, isExact)
    {
      if (typeof obj == "Mojo.Set" || typeof obj == "Mojo.Map")
      {
        obj = obj.getData();
      }
      
      var size = 0;
      for (var k in obj)
      {
        if (!this.contains(obj[k]))
          return false;
          
        size++;
      }
      
      if (isExact && size != this.size())
        return false;
      
      return true;
    },
    
    containsExactly : function(obj)
    {
      return this.containsAll(obj, true);
    },
    
    isEmpty : function()
    {
      return this._map.isEmpty();
    },
    
    remove : function(obj)
    {
      this._map.remove(obj);
    },
    
    removeAll : function()
    {
      this._map.clear();
    },
    
    retainAll : function(obj)
    {
      if (typeof obj == "Mojo.Set")
      {
        for (var k in this.getData())
        {
          if (!obj.contains(k))
            this.remove(k);
        }
      }
      else // Allow them to pass us stuff other than sets (quadratic searching though)
      {
        for (var k in this.getData())
        {
          var contains = false;
          for (var kk in obj)
          {
            if (obj[kk] == k)
            {
              contains = true;
              break;
            }
          }
          
          if (!contains)
            this.remove(k);
        }
      }
    },
    
    size : function()
    {
      return this._map.size();
    },
    
    toArray : function()
    {
      var array = new Array();
      
      for (var k in this.getData())
      {
        array.push(k);
      }
      
      return array;
    },
    
    toMap : function()
    {
      return this._map;
    },
    
    getData : function()
    {
      return this._map.getData();
    }
    
  }
  
});

// FIXME iterate over different object types
// Look at prototype 1.6 and
// http://closure-library.googlecode.com/svn/trunk/closure/goog/docs/closure_goog_iter_iter.js.source.html
// for more iter methods and integrated iteration
Mojo.Meta.newClass('Mojo.Iter', {

  Instance : {
  
    initialize : function()
    {
      // todo take in arr/obj/iterable and wrap it to allow iteration functions
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
      if(Mojo.Util.isNumber(obj))
      {
        for(var i=0; i<obj; i++)
        {
          bound(i); 
        }
      }
      else if(Mojo.Util.isArray(obj))
      {
        for(var i=0; i<obj.length; i++)
        {
          var item = obj[i];
          bound(item, i);
        }
      }
      else if(Mojo.Util.isObject(obj))
      {
        var keys = Mojo.Util.getKeys(obj);
        for(var i=0; i<keys.length; i++)
        {
          var key = keys[i];
          bound(obj[key], key);
        }
      }
      else if('length' in obj)
      {
        for(var i=0; i<obj.length; i++)
        {
          bound(obj[i], i);
        }
      }
      else
      {
        throw Error('The object cannot be iterated over.');
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

Mojo.Meta.newClass('Mojo.ClientRequest', {

  Instance : {
  
    initialize : function(handler){

      Mojo.Util.copy(handler, this);

      this._warnings = [];
      this._information = [];
      this._transport = null;
    },
     
    getMessages : function() { return this._warnings.concat(this._information); },
    
    setWarnings : function(warnings) { this._warnings = warnings; },
    
    getWarnings : function() { return this._warnings; },
    
    setInformation : function(information) { this._information = information; },
    
    getInformation : function() { return this._information; },
    
    getTransport : function() { return this._transport; },
    
    setTransport : function(transport) { this._transport = transport; }
  }
});

Mojo.Meta.newClass('Mojo.ClientSession', {

  IsSingleton : true,
  
  Instance : {
    initialize : function()
    {
      this._nativeParsingEnabled = true;
      
      // FIXME use constants for the keys
      this._ajaxOptions ={
          'method':'post',
          'contentType':'application/x-www-form-urlencoded',
          'encoding':'UTF-8',
          'asynchronous':true,
          'successRange':[200,299]
      };
      
      this._baseEndpoint = (Mojo.GLOBAL.location.protocol + "//" + Mojo.GLOBAL.location.host  +'/'+ Mojo.GLOBAL.location.pathname.split( '/' )[1] +'/');
    }
  },
  
  Static : {
    
    isNativeParsingEnabled : function() { return Mojo.ClientSession.getInstance()._nativeParsingEnabled; },
    
    setNativeParsingEnabled : function(enabled){ Mojo.ClientSession.getInstance()._nativeParsingEnabled = enabled; },

    getBaseEndpoint : function() { return Mojo.ClientSession.getInstance()._baseEndpoint; },
    
    setBaseEndpoint : function(baseEndpoint) { Mojo.ClientSession.getInstance()._baseEndpoint = baseEndpoint; },
    
    getAjaxOptions : function() { return Mojo.Util.copy(Mojo.ClientSession.getInstance()._ajaxOptions, {}); },
    
    setAjaxOptions : function(defaultOptions) { Mojo.Util.copy(defaultOptions, Mojo.ClientSession.getInstance()._ajaxOptions); }
  }
});

Mojo.Meta.newClass('Mojo.Ajax', {

  Instance : {
    
    initialize: function (url, parameters, options)
    {
      if (url == null || url == "")
      {
        throw new _exceptionClassRef("URL of Ajax call is undefined or incorrect.");
      }
      
      // encode the parameters if given a map
      this.paramStr = '';
      if(Mojo.Util.isObject(parameters))
      {
        var paramArray = [];
        for(var i in parameters)
        {
        paramArray.push(encodeURIComponent(i)+'='+encodeURIComponent(parameters[i]));
        }
        this.paramStr = paramArray.join('&');
      }
      else if (parameters != null)
      {
        this.paramStr = parameters.toString();
      }
      
      this.options = {};
           Mojo.Util.copy(Mojo.ClientSession.getAjaxOptions(), this.options);
          Mojo.Util.copy(options, this.options);
      
      this.url = url;
      
      // bind their handle functions so that they can access the xmlhttp object and stuff
      if (Mojo.Util.isFunction(options.onSend))
      {
        this.options.onSend = Mojo.Util.bind(this, options.onSend);
      }
      if (Mojo.Util.isFunction(options.onSuccess))
      {
        this.options.onSuccess = Mojo.Util.bind(this, options.onSuccess);
      }
      if (Mojo.Util.isFunction(options.onFailure))
      {
        this.options.onFailure = Mojo.Util.bind(this, options.onFailure);
      }
    },
    
    apply: function ()
    {
      try
      {
        // Firefox, Opera 8.0+, Safari
        this.xmlhttp = new XMLHttpRequest();
      }
      catch (e)
      {
        // Internet Explorer
        try
        {
        this.xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e)
        {
        try
        {
          this.xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (e)
        {
          var message = "The browser does not support Ajax";
          throw new _exceptionClassRef(message);
        }
        }
      }
      
      var onreadystatechange = function ()
      {
        if(this.xmlhttp.readyState == 4)
        {
          if (Mojo.Util.isFunction(this.options.onReturn))
        {
          this.options.onReturn();
        }
        
        if(this.xmlhttp.status >= this.options.successRange[0]
          && this.xmlhttp.status <= this.options.successRange[1])
        {
          if (Mojo.Util.isFunction(this.options.onSuccess))
          {
            this.options.onSuccess();
          }
        }
        else
        {
          if (Mojo.Util.isFunction(this.options.onFailure))
          {
            this.options.onFailure();
          }
        }
        }
      };
      
      if (Mojo.Util.isFunction(this.options.onSend))
      {
        this.options.onSend();
      }
      
      var bound = Mojo.Util.bind(this, onreadystatechange);
      if(this.options.method == 'post')
      {
        this.xmlhttp.open(this.options.method, this.url, this.options.asynchronous);
        this.xmlhttp.onreadystatechange = bound;
        this.xmlhttp.setRequestHeader("Content-type", this.options.contentType + "; charset="+this.options.encoding);
        this.xmlhttp.setRequestHeader("Content-length", this.paramStr.length);
        this.xmlhttp.setRequestHeader("Connection", "close");
    
        this.xmlhttp.send(this.paramStr);
      }
      else
      {
        this.xmlhttp.open(this.options.method, this.url+"?"+this.paramStr, this.options.asynchronous);
        
        this.xmlhttp.send();
      }
    }
  
  }

});

Mojo.Meta.newClass('Mojo.AjaxCallList', {

  Instance : {
    
    initialize: function ()
    {
      this.array = new Array();
    },
    
    add: function(obj)
    {
      // FIXME use Interface/superclass to accept other request types
      if ( !(obj instanceof Mojo.Meta.findClass("Mojo.Ajax")) )
      {
        throw new _exceptionClassRef("Mojo.AjaxCallList.add: Unable to add object to the call list, the provided object is not an AjaxCall");
      }
      
      this.array.push(obj);
    },
    
    apply: function(sequential)
    {
      var key = 0;
      while(key < this.array.length)
      {
        if (sequential)
        {
          if (key+1 < this.array.length) // set the onReturn method to call the apply of the next AjaxCall
          {
            this.array[key].options._nextAjaxCall = Mojo.Util.bind(this.array[key+1], this.array[key+1].apply);
          
            if (Mojo.Util.isFunction(this.array[key].options.onReturn))
            {
              this.array[key].options._userOnReturn = Mojo.Util.bind(this.array[key].options, this.array[key].options.onReturn);
            
              this.array[key].options.onReturn = function () { this._userOnReturn(); this._nextAjaxCall(); };
            }
            else
            {
              this.array[key].options.onReturn = function () { this._nextAjaxCall(); };
            }
          }
        }
        else
        {
          this.array[key].apply();
        }
        
        key++;
      }
      
      if (sequential)
      {
        this.array[0].apply();
      }
    }
    
  }

});

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
        Mojo.Util.copy(Mojo.ClientSession.getAjaxOptions(), this.options);
        Mojo.Util.copy(this.clientRequest.options, this.options);
  
        // encode the parameters if given a map
        var paramStr = '';
        if(Mojo.Util.isObject(parameters))
        {
          var paramArray = [];
          for(var i in parameters)
          {
            paramArray.push(encodeURIComponent(i)+'='+encodeURIComponent(parameters[i]));
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
              throw new _exceptionClassRef(message);
            }
          }
        }
  
        this.readyStateChange = function (){
  
          if(this.xmlHttp.readyState == 4)
          {
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
        
        this.clientRequest.setTransport(this.xmlHttp);
  
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
          if(options.autoEval)
          {
            eval(jsSource);
          }
          else if('appendTo' in options)
          {
            var script = document.createElement("script");
            script.type = "text/javascript";
  
            try
            {
              script.appendChild(document.createTextNode(jsSource));
            }
            catch(e)
            {
              script.text = jsSource; // IE
            }
  
            var appendTo = options.appendTo;
            var parentEl = Mojo.Util.isString(appendTo) ? document.getElementById(appendTo) : appendTo;
            parentEl.appendChild(script);
          }
        }
  
        if(Mojo.Util.isFunction(onSuccessRef))
        {
          onSuccessRef.call(clientRequest, jsSource);
        }
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
      {
        params = {"com.runwaysdk.mojaxObject":Mojo.Util.getJSON(params)};
      }
  
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
      };
  
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

var serializableIF = Mojo.Meta.newInterface(Mojo.ROOT_PACKAGE+'Serializable', {
  
  Instance : {
    toJSON : {
      Method : function(key) {},
      EnforceArity : true
    }
  }
});

/**
 * Class that serializes basic objects with optional key/value overriding.
 * Functions and the __context variables are ignored.
 */
var standardSerializer = Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'StandardSerializer',{
  
  Instance : {
    initialize : function(source, override)
    {
      source = source || {};
      this._destination = {};
      
      // Copy the non-function properties to the destination object.
      // This will also remove any infinite recursion via toJSON()
      // declarations on the original source object.
      for(var i in source)
      {
        if(!isFunction(source[i]))
        {
          this._destination[i] = source[i];
        }
      }
      this._override = override || null;
    },
    
    toJSON : function()
    {
      var ssRef = this;
      var replacer = function(key, value)
      {
        if(ssRef._override !== null && key in ssRef._override)
        {
          return ssRef._override[key];
        }
        else if(key === '__context' || isFunction(this[key]))
        {
          return undefined;
        }
        else
        {
          return value;
        }
      };
      
      return Mojo.Util.toJSON(this._destination, replacer);
    }
  }
});

/**
 * ComponentQueryDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ComponentQueryDTO', {

  Implements : serializableIF,
  
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
  
    isCountEnabled : function() { return this.countEnabled; },
    
    toJSON : function(key)
    {
      return new standardSerializer(this).toJSON();
    }
  
  }
});

/**
 * ValueQueryDTO
 */
Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ValueQueryDTO', {

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

  Implements : serializableIF,
  
  IsAbstract : true,

  Instance : {

    initialize : function(obj)
    {
      this.dto_type = obj.dto_type;
      
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
  
    getIdMd : function() { return this.getAttributeDTO('id').getAttributeMdDTO(); },
  
    getAttributeDTO : function(attributeName)
    {
      return this.attributeMap[attributeName];
    },
  
    getMd : function() { return this._typeMd; },
  
    toString : function() { return this._toString; },
  
    isReadable : function() { return this.readable; },
  
    setValue : function(attributeName, value) { this.getAttributeDTO(attributeName).setValue(value); },
  
    getValue : function(attributeName) { return this.getAttributeDTO(attributeName).getValue(); },
    
    getAttributeNames : function()
    {
      return Mojo.Util.getKeys(this.attributeMap, true);
    },
    
    toJSON : function(key)
    {
      return new standardSerializer(this).toJSON();
    }
  }
});

Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'MutableDTO', {

  IsAbstract : true,
  
  Extends : Mojo.BUSINESS_PACKAGE+'ComponentDTO',

  Instance : {

    initialize : function(obj)
    {
    
      // Generate a new id per instance instead of using the id
      // of the cached JSON (to avoid all new instances having the same id),
      // and preserve the id of the metadata type.
      if(obj.newInstance)
      {
        obj.id = Mojo.Util.generateId()+obj.id.substring(32);    
      }
      
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
 * This is the actual exception that can be thrown and caught. There is no Mojo
 * Java counterpart, so we throw it in the root namespace.
 */
_exceptionClassRef = Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'Exception', {

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
      
      // Make the message public to conform with the Error API
      this.message = this.developerMessage || this.localizedMessage;
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
      var mdDtoType = this.getMetaClass().getName().replace('DTO', 'MdDTO');
      var mdKlass = Mojo.Meta.findClass(Mojo.MD_DTO_PACKAGE+ mdDtoType);

      if(!mdKlass)
      {
        mdKlass = Mojo.Meta.findClass("com.runwaysdk.gis.transport.metadata."+ mdDtoType);
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
  
    getAttributeMdDTO : function() { return this.attributeMdDTO; },
    
    toJSON : function(key)
    {
      return new standardSerializer(this).toJSON();
    }
    
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeMdDTO', {

  IsAbstract : true,

  Instance : {

    initialize : function(obj)
    {
      this.displayLabel = obj.displayLabel;
      this.description = obj.description;
      this.required = obj.required;
      this.immutable = obj.immutable;
      this.columnName = obj.columnName;
      this.id = obj.id;
      this.system = obj.system;
      this.name = obj.name;
    },
  
    getDisplayLabel : function() { return this.displayLabel; },
  
    getDescription : function() { return this.description; },
  
    isRequired : function() { return this.required; },
  
    isImmutable : function() { return this.immutable; },
  
    getId : function() { return this.id; },
  
    isSystem : function() { return this.system; },
  
    getName : function() { return this.name; },
  
    getColumnName : function() { return this.columnName; },
    
    toJSON : function() { return undefined; }
  
  }
});

// number
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeNumberDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeNumberDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeIntegerMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeNumberDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeLongMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDecimalMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDoubleMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeFloatMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeTextMdDTO',        {

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

// clob
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeClobDTO', {
  
  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  
  Instance : {
  
  initialize : function(obj)
  {
  this.$initialize(obj);
  }

}
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeClobMdDTO',        {
  
  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
  
  Instance : {
  
  initialize : function(obj)
  {
  this.$initialize(obj);
  }

}
});

// local text
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeLocalTextDTO', {
  
  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  
  Instance : {
  
  initialize : function(obj)
  {
    this.$initialize(obj);
  }

}
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeLocalTextMdDTO',        {
  
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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeCharacterMdDTO', {

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

// local character
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeLocalCharacterDTO', {

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeLocalCharacterMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeBooleanMdDTO', {

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
   * Should only be called when setting the type-safe version of the struct.
   */
    setStructDTO : function(structDTO)
    {
      this.structDTO = structDTO;
    }
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeStructMdDTO', {

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

  IsAbstract : true,

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',

  Instance : {
  
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this._ignoreTimezone = false;
      
      // set internal value as a date
      if(this.value != null && this.value !== '')
      {
        var date = new Date();
        var ignoreTimezone = this.getIgnoreTimezone();          
        Mojo.Util.setISO8601(date, this.value, ignoreTimezone);
 
        this.value = date;
      }
      else
      {
        this.value = null;
      }
    },
    
    setIgnoreTimezone : function(ignoreTimezone)
    {
      this._ignoreTimezone = ignoreTimezone;
    },
    
    getIgnoreTimezone : function()
    {
      return this._ignoreTimezone;
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
            
            var ignoreTimezone = this.getIgnoreTimezone();
            Mojo.Util.setISO8601(date, value, ignoreTimezone);
  
            this.value = date;
          }
        }
        else
        {
          this.value = null;
        }
  
        this.setModified(true);
      }
    },
    
    toJSON : function(key)
    {
      var date = this.getValue();
      if(isDate(date))
      {
        date = Mojo.Util.toISO8601(date, this.getIgnoreTimezone());
      }
      
      var override = {value:date};
      return new standardSerializer(this, override).toJSON();
    }
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeMomentMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeMomentDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDateTimeMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeMomentDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
      
      this.setIgnoreTimezone(true);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDateMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeMomentDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeTimeMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeReferenceMdDTO', {

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
  
      this.referencedMdBusiness = obj.referencedMdBusiness;
    },
  
    getReferencedMdBusiness : function() { return this.referencedMdBusiness; }
  
  }
});

// enumeration
Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeEnumerationDTO', {

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
      Mojo.Facade.getEnumerations(clientRequest, enumType, names);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeEnumerationMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeEncryptionDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeHashMdDTO', {

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

  Extends : Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeEncryptionDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeSymmetricMdDTO', {

  Extends : Mojo.MD_DTO_PACKAGE+'AttributeEncryptionMdDTO',
  
  Instance : {
    
    initialize : function(obj)
    {
      this.$initialize(obj);
    }
    
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'TypeMd', {

  Instance : {

    initialize : function(obj)
    {
      this.displayLabel = obj.displayLabel;
      this.description = obj.description;
      this.id = obj.id;
    },
  
    getDisplayLabel : function() {return this.displayLabel;},
  
    getDescription : function() {return this.description;},
  
    getId : function() {return this.id;},
    
    toJSON : function() { return undefined; }
  
  }
});

Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'RelationshipMd', {

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
})();