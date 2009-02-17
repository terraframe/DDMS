/**
 * Terraframe Mojo Javascript library.
 * 
 * (c) 2009
 */
var Mojo = {

  /**
   * $ is the namespace for generated types.
   */
  $ : {},
  
  /**
   * Namespace for hard-coded DTO representations in Javascript.
   */
  dto : {},

  /**
   * Creates and extends classes.
   */
  Class : {
    create : function() {
      return function() {
        this.initialize.apply(this, [].splice.call(arguments, 0));
      }
    },
    extend : function(superClass, classDef) {
      // must pass an explicit null as the first argument so the classes
      // know the instantiation is for inheritance, not objects.
      var temp = new superClass(null);
  
      Mojo.util.copy(classDef, temp);

      return temp;
    }
  },
  
  util : {
  
    JSON_ENDPOINT : "Mojo/JSONControllerServlet",
    MOJO_HARDCODED_PACKAGE : /^Mojo\./,
    MOJO_GENERATED_PACKAGE : /^Mojo\.\$\./,
    IS_OBJECT_TO_STRING : Object.prototype.toString.call({}),
    IS_ARRAY_TO_STRING : Object.prototype.toString.call([]),
    IS_FUNCTION_TO_STRING : Object.prototype.toString.call(function(){}),
    IS_DATE_TO_STRING : Object.prototype.toString.call(new Date()),
    IS_STRING_TO_STRING : Object.prototype.toString.call(''),
  
    isObject : function(o)
    {
      return  o != null && Object.prototype.toString.call(o) === this.IS_OBJECT_TO_STRING;
    },
    
    isArray : function(o)
    {
      return o != null && Object.prototype.toString.call(o) === this.IS_ARRAY_TO_STRING;
    },
    
    isFunction : function(o)
    {
      return o != null && Object.prototype.toString.call(o) === this.IS_FUNCTION_TO_STRING;
    },
    
    isDate : function(o)
    {
      return o != null && Object.prototype.toString.call(o) === this.IS_DATE_TO_STRING;
    },
    
    isString : function(o)
    {
      return o != null && Object.prototype.toString.call(o) === this.IS_STRING_TO_STRING;
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
          if(Mojo.util.isDate(value))
          {
            return String(value.getTime());
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
    
    copy : function(source, dest)
    {
      if(Mojo.util.isObject(source))
      {
        for(var i in source)
        {
          dest[i] = source[i];
        }
      }
    },
    
    getObject : function(json)
    {
      if(Mojo.util.isString(json))
        return this.JSON.parse(json);
      else
        return json;
    },
    
    getJSON : function(obj)
    {
      return this.JSON.stringify(obj);
    },
    
    buildPackage : function(packageName)
    {
      var parts = packageName.split(".");  
  
      var currentBuild = Mojo.$;
      for(var i=0; i<parts.length; i++)
      {
        var part = parts[i];
        
        if(!Mojo.util.isObject(currentBuild[part]))
        {
          currentBuild[part] = {};
        }
          
        currentBuild = currentBuild[part];
      }
    },
    
    typeExists : function(type)
    {
      var currentBuild = Mojo.$;
      if(this.MOJO_GENERATED_PACKAGE.test(type))
      {
        type = type.replace(this.MOJO_GENERATED_PACKAGE, '');
      }
      else if(this.MOJO_HARDCODED_PACKAGE.test(type))
      {
        type = type.replace(this.MOJO_HARDCODED_PACKAGE, '');
        currentBuild = Mojo;
      }
  
      var parts = type.split(".");
      for(var i=0; i<parts.length; i++)
      {
        var part = parts[i];
        
        var next = currentBuild[part];
        if(next)
        {
          currentBuild = currentBuild[part];
        }
        else
        {
          return false;
        }
      }
      
      return true;
    },
    
    getType : function(type)
    {
      var currentBuild = Mojo.$;
      if(this.MOJO_GENERATED_PACKAGE.test(type))
      {
        type = type.replace(this.MOJO_GENERATED_PACKAGE, '');
      }
      else if(this.MOJO_HARDCODED_PACKAGE.test(type))
      {
        type = type.replace(this.MOJO_HARDCODED_PACKAGE, '');
        currentBuild = Mojo;
      }
  
      var parts = type.split(".");
      for(var i=0; i<parts.length; i++)
      {
        var part = parts[i];
        
        var next = currentBuild[part];
        if(next)
        {
          currentBuild = currentBuild[part];
        }
        else
        {
          var error = 'The type ['+type+'] is not defined.';
          throw new Mojo.dto.Exception(error);
        }
      }
      
      return currentBuild;
    },
    
    convertToType : function(value)
    {
      // void/null returns
      if(value == null)
      {
        return null;
      }
      else if(Mojo.util.isObject(value))
      {
        // MdEnumeration Items
        if('enumType' in value && 'enumName' in value)
        {
          return Mojo.util.getType(value.enumType)[value.enumName];  
        }
        // special case for ValueObjectDTOs, which define both a dto_type and _type property
        else if('dto_type' in value && value.dto_type === 'Mojo.dto.ValueObjectDTO')
        {
          return new Mojo.dto.ValueObjectDTO(value);
        }
        // messages may be type-safe or generic
        else if('dto_type' in value && (value.dto_type === 'Mojo.dto.WarningDTO' || value.dto_type === 'Mojo.dto.InformationDTO'))
        {
          if(Mojo.util.typeExists(value._type))
          {
            var constructor_ = Mojo.util.getType(value._type);
            return new constructor_(value);
          }
          else
          {
            var constructor_ = Mojo.util.getType(value.dto_type);
            return new constructor_(value);
          }
        }
        else if('_type' in value && value._type != '')
        {
          // generated dtos
          var constructor_ = Mojo.util.getType(value._type);
          return new constructor_(value);
        }
        else if('dto_type' in value && value.dto_type != '')
        {
          // hard-coded dtos
          var constructor_ = Mojo.util.getType(value.dto_type);
          return new constructor_(value);
        }
        else
        {
          return value;
        }
      }
      else if(Mojo.util.isArray(value))
      {
        for(var i=0; i<value.length; i++)
        {
          value[i] = Mojo.util.convertToType(value[i]);
        }
          
        return value;
      }
      else if(Mojo.util.isString(value) && /^\/Date\((\d+)\)\/$/.test(value))
      {
        return eval(value.replace(/^\/Date\((\d+)\)\/$/, "new Date($1)"));
      }
      else
      {
        return value;
      }
    },
    
    handleException : function(transport, clientRequest)
    {
      var e = null;
      var responseText = transport.responseText;
      try
      {
        var obj = Mojo.util.getObject(responseText);
        
        var exceptionType = null;
        if('dto_type' in obj && obj.dto_type === 'Mojo.dto.MojoExceptionDTO')
        {
          exceptionType = obj.wrappedException;
          e = Mojo.util.convertToType(obj);
        }
        else if('dto_type' in obj && obj.dto_type === 'Mojo.dto.ProblemExceptionDTO')
        {
          exceptionType = obj.dto_type;
          e = Mojo.util.convertToType(obj);
        }
        else if('_type' in obj && Mojo.util.typeExists(obj._type))
        {
          exceptionType = obj._type;
          e = Mojo.util.convertToType(obj);
        }
        else
        {
          e = new Mojo.dto.Exception(obj);
        }
        
        // try to match the exception name to an error callback
        if(Mojo.util.isString(exceptionType) && exceptionType.length > 0)
        {
          var exNameInd = exceptionType.lastIndexOf('.');
          if(exNameInd !== -1)
          {
            exceptionType = exceptionType.substr(exNameInd + 1);
          }
          
          var handlerName = 'on'+exceptionType;
          if(Mojo.util.isFunction(clientRequest[handlerName]))
          {
            clientRequest[handlerName](e);
          }
          // no match ... use the default handler
          else if(Mojo.util.isFunction(clientRequest.onFailure))
          {
            clientRequest.onFailure(e);
          }
        }
        else
        {
          // use the default handler
          if(Mojo.util.isFunction(clientRequest.onFailure))
          {
            clientRequest.onFailure(e);
          }
        }
      }
      catch(e1)
      {
        // use the default handler
        if(Mojo.util.isFunction(clientRequest.onFailure))
        {
          var e = new Mojo.dto.Exception(responseText);
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
      
      var form = Mojo.util.isString(formId) ? document.getElementById(formId) : formId;
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
        if(Mojo.util.isArray(entry))
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
  },
  /**
   * ClientRequest object that is used to perform an individual
   * Ajax call.
   */
  ClientRequest : function(handler){
    
    Mojo.util.copy(handler, this);
    
    var _warnings = [];
    var _information = [];
    var _transport = null;
    
    this.getMessages = function() { return _warnings.concat(_information); },
    this.setWarnings = function(warnings) { _warnings = warnings; },
    this.getWarnings = function() { return _warnings; },
    this.setInformation = function(information) { _information = information; },
    this.getInformation = function() { return _information; },
    this.getTransport = function() { return _transport; }
    this.setTransport = function(transport) { _transport = transport; }
  },

  /**
   * ClientSession reperents the current session
   * in the browser and sets the default options
   * for AJAX calls.
   */
  ClientSession : (function(){

    var baseEndpoint = '@@BASE_ENDPOINT@@';
  
    var defaultOptions = {
      'method':'post',
      'contentType':'application/x-www-form-urlencoded',
      'encoding':'UTF-8',
      'asynchronous':true,
      'successRange':[200,299]
    };
  
    var AjaxCall = function(endpoint, clientRequest, parameters, isController)
    {
      // success handler
      var filterSuccess = function(transport)
      {
        var responseText = transport.responseText;
        clientRequest.setTransport(transport);
        
        var obj = null;
        if(!isController)
        {
          var json = Mojo.util.getObject(responseText);
          obj = Mojo.util.convertToType(json.returnValue);
        
          // add warnings/information to the ClientRequest
  
          if(Mojo.util.isArray(json.warnings) && json.warnings.length > 0)
          {
            clientRequest.setWarnings(Mojo.util.convertToType(json.warnings));
          }
        
          if(Mojo.util.isArray(json.information) && json.information.length > 0)
          {
            clientRequest.setInformation(Mojo.util.convertToType(json.information));
          }
        }
        else
        {
          obj = responseText;
        }
  
        // invoke the success handler
        if(Mojo.util.isFunction(clientRequest.onSuccess))
        {
          clientRequest.onSuccess(obj);
        }
      };
    
      // failure handler
      var filterFailure = function(transport)
      {
        Mojo.util.handleException(transport, clientRequest);
      };
      
      this.requestOptions = {};
      Mojo.util.copy(defaultOptions, this.requestOptions);
      
      // encode the parameters if given a map
      var paramStr = '';
      if(Mojo.util.isObject(parameters))
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
        this.xmlHttp=new XMLHttpRequest();
      }
      catch (e)
      {
        // Internet Explorer
        try
        {
          this.xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e)
        {
          try
          {
            this.xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
          }
          catch (e)
          {
            var message = "The browser does not support Ajax";
            throw new Mojo.dto.Exception(message);
          }
        }
      }
    
      var that = this;
      this.xmlHttp.onreadystatechange = function (){
          
        var cb = function()
        {
          if(this.xmlHttp.readyState == 4)
          {
            if(this.xmlHttp.status >= this.requestOptions.successRange[0]
              && this.xmlHttp.status <= this.requestOptions.successRange[1])
            {
              filterSuccess.call(this, this.xmlHttp);
            }
            else
            {
              filterFailure.call(this, this.xmlHttp);
            }
          }
        }
          
        cb.call(that);
      };
      
      var url = baseEndpoint + endpoint;
      if(this.requestOptions.method == 'post')
      {
        this.xmlHttp.open(this.requestOptions.method, url, this.requestOptions.asynchronous);
    
        this.xmlHttp.setRequestHeader("Content-type", this.requestOptions.contentType + "; charset="+this.requestOptions.encoding);
        this.xmlHttp.setRequestHeader("Content-length", paramStr.length);
        this.xmlHttp.setRequestHeader("Connection", "close");
     
        this.xmlHttp.send(paramStr);
      }
      else
      {
        this.xmlHttp.open(this.requestOptions.method, url+"?"+paramStr, this.requestOptions.asynchronous);
        this.xmlHttp.send(null);
      }
    };
    
    // public methods
    return {
      getBaseEndpoint : function()
      {
        return baseEndpoint;
      },
      setBaseEndpoint : function(newBaseEndpoint)
      {
        baseEndpoint = newBaseEndpoint;
      },
      getDefaultOptions : function()
      {
        return Mojo.util.copy(defaultOptions, {});
      },
      setDefaultOPtions : function(newDefaultOptions)
      {
        Mojo.util.copy(newDefaultOptions, defaultOptions);
      },
      AjaxCall : AjaxCall
    };
  })(),

  /**
   * Import Types.
   * 
   * @param clientRequest
   * @param types
   * @param options
   */
  importTypes : function(clientRequest, types, options)
  {
    if(Mojo.util.isString(types))
    {
      types = [types];
    }
      
    var json = Mojo.util.getJSON(types); 
    
    var params = {
      'method' : 'importTypes',
      'types' : json};
  
    var onSuccessRef = clientRequest.onSuccess;
    var importCallback = function(jsSource)
    {
      if(Mojo.util.isObject(options))
      {
        if(options.autoEval === true)
        {
          eval(jsSource);
        }
        else if('appendTo' in options)
        {
          var appendTo = options.appendTo;
          var parentEl = (Mojo.util.isString(appendTo)) ? document.getElementById(appendTo) : appendTo;
          
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
      
      if(Mojo.util.isFunction(onSuccessRef))
        onSuccessRef.call(clientRequest, jsSource);
    };
    clientRequest.onSuccess = importCallback;
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);  
  },
  
  /**
   * Wrapper for generated Facade methods.
   */
  _methodWrapper : function(clientRequest, params)
  {
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);  
  },
  
  /**
   * Wrapper for generated controller methods
   */
  _controllerWrapper : function(endpoint, clientRequest, params)
  {
  	if(Mojo.util.isObject(params))
      params = {"com.terraframe.mojo.mojaxObject":Mojo.util.getJSON(params)};
  	
    new Mojo.ClientSession.AjaxCall(endpoint, clientRequest, params, true);  
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * LoginAnonymous
   */
  loginAnonymous : function (clientRequest)
  {
    var params = {
      'method' : 'loginAnonymous'
    };
    
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * Logout
   */
  logout : function (clientRequest)
  {
    var params = {
      'method' : 'logout'};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * NewBusiness
   */
  newBusiness : function (clientRequest, type)
  {
    var params = { 
      'method' : 'newBusiness',
      'type' : type};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * CreateSessionComponent
   */
  createSessionComponent : function(clientRequest, sessionDTO)
  {
    // convert the BusinessDTO into a JSON String
    var json = Mojo.util.getJSON(sessionDTO);
  
    var params = {
      'method' : 'createSessionComponent',
      'sessionDTO' : json};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * CreateBusiness
   */
  createBusiness : function (clientRequest, businessDTO)
  {
    // convert the BusinessDTO into a JSON String
    var json = Mojo.util.getJSON(businessDTO);
  
    var params = {
      'method' : 'createBusiness',
      'businessDTO' : json};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * NewStruct
   */
  newStruct : function (clientRequest, type)
  {
    var params = { 
      'method' : 'newStruct',
      'type' : type};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * NewEntity
   */
  newEntity : function (clientRequest, type)
  {
    var params = { 
      'method' : 'newEntity',
      'type' : type};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * CreateStruct
   */
  createStruct : function (clientRequest, structDTO)
  {
    // convert the StructDTO into a JSON String
    var json = Mojo.util.getJSON(structDTO);
  
    var params = {
      'method' : 'createStruct',
      'structDTO' : json};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * CreateRelationship
   */
  createRelationship : function (clientRequest, relationshipDTO)
  {
    // convert the RelationshipDTO into a JSON String
    var json = Mojo.util.getJSON(relationshipDTO);
  
    var params = {
      'method' : 'createRelationship',
      'relationshipDTO' : json};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * CheckAdminScreenAccess
   */
  checkAdminScreenAccess : function (clientRequest)
  {
    var params = {
      'method' : 'checkAdminScreenAccess'
    };
    
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * Update
   */
  update : function (clientRequest, mutableDTO)
  {
    // convert the MutableDTO into a JSON String
    var json = Mojo.util.getJSON(mutableDTO);
  
    var params = {
      'method' : 'update',
      'mutableDTO' : json}; 
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * DeleteBusiness
   */
  deleteEntity : function (clientRequest, id)
  {
    var params = {
      'method' : 'delete',
      'id' : id};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * GetInstance
   */
  get : function (clientRequest, id)
  {
    var params = {
      'method' : 'get',
      'id' : id};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  getUser : function(clientRequest)
  {
    var params = {
      'method' : 'getUser'
    };
    
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);  
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
    
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * DeleteChild
   */
  deleteChild : function(clientRequest, relationshipId)
  {
    var params = {
      'method' : 'deleteChild',
      'relationshipId' : relationshipId};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * DeleteParent
   */
  deleteParent : function(clientRequest, relationshipId)
  {
    var params = {
      'method' : 'deleteParent',
      'relationshipId' : relationshipId};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  
  /**
   * Lock
   */
  lock : function(clientRequest, id)
  {
    var params = {
      'method' : 'lock',
      'id' : id};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * Unlock
   */
  unlock : function(clientRequest, id)
  {
    var params = {
      'method' : 'unlock',
      'id' : id};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * GrantTypePermission
   */
  grantTypePermission : function(clientRequest, actorId, mdTypeId, operationNames)
  {
    if(!Mojo.util.isArray(operationNames))
    {
      operationNames = [operationNames];
    }
  
    var operationNamesJSON = Mojo.util.getJSON(operationNames);
  
    var params = {
      'method' : 'grantTypePermission',
      'actorId' : actorId,
      'operationNames' : operationNamesJSON,
      'mdTypeId' : mdTypeId};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * GrantStatePermission
   */
  grantStatePermission : function(clientRequest, actorId, stateId, operationNames)
  {
    if(!Mojo.util.isArray(operationNames))
    {
      operationNames = [operationNames];
    }
  
    var operationNamesJSON = Mojo.util.getJSON(operationNames);
  
    var params = {
      'method' : 'grantStatePermission',
      'actorId' : actorId,
      'operationNames' : operationNamesJSON,
      'stateId' : stateId};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * GrantAttributePermission
   */
  grantAttributePermission : function(clientRequest, actorId, mdAttributeId, operationNames)
  {
    if(!Mojo.util.isArray(operationNames))
    {
      operationNames = [operationNames];
    }
  
    var operationNamesJSON = Mojo.util.getJSON(operationNames);
  
    var params = {
      'method' : 'grantAttributePermission',
      'actorId' : actorId,
      'operationNames' : operationNamesJSON,
      'mdAttributeId' : mdAttributeId};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * GrantAttributeStatePermission
   */
  grantAttributeStatePermission : function(clientRequest, actorId, mdAttributeId, stateId, operationNames)
  {
    if(!Mojo.util.isArray(operationNames))
    {
      operationNames = [operationNames];
    }
  
    var operationNamesJSON = Mojo.util.getJSON(operationNames);
  
    var params = {
      'method' : 'grantAttributeStatePermission',
      'actorId' : actorId,
      'operationNames' : operationNamesJSON,
      'mdAttributeId' : mdAttributeId,
      'stateId' : stateId};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * RevokeTypePermission
   */
  revokeTypePermission : function(clientRequest, actorId, mdTypeId, operationNames)
  {
    if(!Mojo.util.isArray(operationNames))
    {
      operationNames = [operationNames];
    }
      
    var operationNamesJSON = Mojo.util.getJSON(operationNames);
      
    var params = {
      'method' : 'revokeTypePermission',
      'actorId' : actorId,
      'operationNames' : operationNamesJSON,
      'mdTypeId' : mdTypeId};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * RevokeStatePermission
   */
  revokeStatePermission : function(clientRequest, actorId, stateId, operationNames)
  {
    if(!Mojo.util.isArray(operationNames))
    {
      operationNames = [operationNames];
    }
  
    var operationNamesJSON = Mojo.util.getJSON(operationNames);
  
    var params = {
      'method' : 'revokeStatePermission',
      'actorId' : actorId,
      'operationNames' : operationNamesJSON,
      'stateId' : stateId};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * RevokeAttributePermission
   */
  revokeAttributePermission : function(clientRequest, actorId, mdAttributeId, operationNames)
  {
    if(!Mojo.util.isArray(operationNames))
    {
      operationNames = [operationNames];
    }
  
    var operationNamesJSON = Mojo.util.getJSON(operationNames);
      
    var params = {
      'method' : 'revokeAttributePermission',
      'actorId' : actorId,
      'operationNames' : operationNamesJSON,
      'mdAttributeId' : mdAttributeId};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * RevokeAttributeStatePermission
   */
  revokeAttributeStatePermission : function(clientRequest, actorId, mdAttributeId, stateId, operationNames)
  {
    if(!Mojo.util.isArray(operationNames))
    {
      operationNames = [operationNames];
    }
  
    var operationNamesJSON = Mojo.util.getJSON(operationNames);
      
    var params = {
      'method' : 'revokeAttributeStatePermission',
      'actorId' : actorId,
      'operationNames' : operationNamesJSON,
      'mdAttributeId' : mdAttributeId,
      'stateId' : stateId};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * InvokeMethod
   */
  invokeMethod : function(clientRequest, metadata, mutableDTO, parameters)
  {
    var mutableDTOJSON = Mojo.util.getJSON(mutableDTO);
    var metadataJSON = Mojo.util.getJSON(metadata);
    var parametersJSON = Mojo.util.getJSON(parameters);
  
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
  
      if(Mojo.util.isFunction(onSuccessRef))
        onSuccessRef.call(clientRequest, returnObject, calledObject);
    };
    clientRequest.onSuccess = invokeCallback;
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
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
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * GetEnumerations
   */
  getEnumerations : function(clientRequest, enumType, enumNames)
  {
    var enumNamesJSON = Mojo.util.getJSON(enumNames);
    
    var params = {
      'method' : 'getEnumerations',
      'enumType' : enumType,
      'enumNames' : enumNamesJSON};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * GetAllEnumerations
   */
  getAllEnumerations : function(clientRequest, enumType)
  {
    var params = {
      'method' : 'getAllEnumerations',
      'enumType' : enumType};
  
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * QueryBusinesses
   */
  queryBusinesses : function(clientRequest, queryDTO)
  {
    queryDTO.clearAttributes();
    var json = Mojo.util.getJSON(queryDTO);
    
    var params = {
      'method' : 'queryBusinesses',
      'queryDTO' : json};
    
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  groovyValueQuery : function(clientRequest, queryDTO)
  {
    queryDTO.clearAttributes();
    queryDTO.clearResultSet();
    var json = Mojo.util.getJSON(queryDTO);
    
    var params = {
      'method' : 'groovyValueQuery',
      'queryDTO' : json
    };
    
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * QueryStructs
   */
  queryStructs : function(clientRequest, queryDTO)
  {
    queryDTO.clearAttributes();
    queryDTO.clearResultSet();
    var json = Mojo.util.getJSON(queryDTO);
    
    var params = {
      'method' : 'queryStructs',
      'queryDTO' : json};
    
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * QueryEntities
   */
  queryEntities : function(clientRequest, queryDTO)
  {
    queryDTO.clearAttributes();
    queryDTO.clearResultSet();
    var json = Mojo.util.getJSON(queryDTO);
    
    var params = {
      'method' : 'queryEntities',
      'queryDTO' : json};
    
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  },
  
  /**
   * QueryRelationships
   */
  queryRelationships : function(clientRequest, queryDTO)
  {
    queryDTO.clearAttributes();
    queryDTO.clearResultSet();
    var json = Mojo.util.getJSON(queryDTO);
    
    var params = {
      'method' : 'queryRelationships',
      'queryDTO' : json};
    
    new Mojo.ClientSession.AjaxCall(Mojo.util.JSON_ENDPOINT, clientRequest, params);
  }
};

/**
 * ComponentQueryDTO
 */
Mojo.dto.ComponentQueryDTO = Mojo.Class.create();
Mojo.dto.ComponentQueryDTO.prototype = {
  initialize : function(obj)
  {
    if(obj)
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
        var attributeDTO = new Mojo.dto[attribute.dtoType](attribute);
        obj.definedAttributes[attributeName] = attributeDTO;
      }
      this.definedAttributes = obj.definedAttributes;  // keep reference (trick for structs)
      
      // now convert the result set
      this.resultSet = [];
      for(var i=0; i<obj.resultSet.length; i++)
      {
        var result = obj.resultSet[i];
        this.resultSet.push(Mojo.util.convertToType(result));
      }
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
    return Mojo.util.getKeys(this.definedAttributes);
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
};

/**
 * ValueQueryDTO
 */
Mojo.dto.ValueQueryDTO = Mojo.Class.create();
Mojo.dto.ValueQueryDTO.prototype = Mojo.Class.extend(Mojo.dto.ComponentQueryDTO, {
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
      
      this.dto_type = 'Mojo.dto.ValueQueryDTO';
    }
    else
    {
      Mojo.dto.ComponentQueryDTO.prototype.initialize.call(this,obj);
    }
  },
  
  getGroovyQuery : function() { return this.groovyQuery; }
});

/**
 * ClassQueryDTO
 */
Mojo.dto.ClassQueryDTO = Mojo.Class.create();
Mojo.dto.ClassQueryDTO.prototype = Mojo.Class.extend(Mojo.dto.ComponentQueryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.ComponentQueryDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.classes = {};
    }
  },
  
  getClassTypes : function()
  {
    return Mojo.util.getKeys(this.classes);
  },
  
  getSuperClasses : function(classType)
  {
    return this.classes[classType];
  }
});

/**
 * EntityQueryDTO
 */
Mojo.dto.EntityQueryDTO = Mojo.Class.create();
Mojo.dto.EntityQueryDTO.prototype = Mojo.Class.extend(Mojo.dto.ClassQueryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.ClassQueryDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.conditions = {};
      for(var i=0; i<obj.conditions.length; i++)
      {
        var conditionJSON = obj.conditions[i];
        
        var attribute = conditionJSON.attribute;
        var condition = conditionJSON.condition;
        var value = conditionJSON.value;
        
        var key = attribute+condition+value;
        this.conditions[key] = new Mojo.dto.EntityQueryDTO.Condition(attribute, condition, value);
      }
      
      this.orderByList = [];
      for(var i=0; i<obj.orderByList.length; i++)
      {
        var orderByJSON = obj.orderByList[i];
        
        if(orderByJSON.length == 3) // StructOrderBy
        {
          var attributeStruct = orderByJSON.attributeStruct;
          var attribute = orderByJSON.attribute;
          var order = orderByJSON.order;
          
          this.orderByList.push(new Mojo.dto.ElementQueryDTO.StructOrderBy(attributeStruct, attribute, order));
        }
        else
        {
          var attribute = orderByJSON.attribute;
          var order = orderByJSON.order;
          
          this.orderByList.push(new Mojo.dto.EntityQueryDTO.OrderBy(attribute, order));
        }
      }
    }
  },
  
  addCondition : function(attribute, condition, value)
  {
    var key = attribute+condition+value;
    var conditionObj = new Mojo.dto.EntityQueryDTO.Condition(attribute, condition, value);
    this.conditions[key] = conditionObj;
  },
  
  clearConditions : function()
  {
    this.conditions = {};
  },
  
  getConditions : function()
  {
    return Mojo.util.getValues(this.conditions);
  },
  
  addOrderBy : function(attribute, order)
  {
    var orderBy = new Mojo.dto.EntityQueryDTO.OrderBy(attribute, order);
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
});

// OrderBy
Mojo.dto.EntityQueryDTO.OrderBy = Mojo.Class.create();
Mojo.dto.EntityQueryDTO.OrderBy.prototype = {
  initialize : function(attribute, order)
  {
    this.attribute = attribute;
    this.order = order;
  },
  
  getAttribute : function() { return this.attribute; },
  
  getOrder : function() { return this.order; }
};

// Condition
Mojo.dto.EntityQueryDTO.Condition = Mojo.Class.create();
Mojo.dto.EntityQueryDTO.Condition.prototype = {
  initialize : function(attribute, condition, value)
  {
    this.attribute = attribute;
    this.condition = condition;
    this.value = value;
  },
  
  getAttribute : function() { return this.attribute; },
  
  getCondition : function() { return this.condition; },
  
  getValue : function() { return this.value; }
};

/**
 * ElementQueryDTO
 */
Mojo.dto.ElementQueryDTO = Mojo.Class.create();
Mojo.dto.ElementQueryDTO.prototype = Mojo.Class.extend(Mojo.dto.EntityQueryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.EntityQueryDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this._isAbstract = obj._isAbstract;
    }
  },
  
  isAbstract : function(){ return this._isAbstract; },
  
  addStructOrderBy : function(structAttribute, attribute, order)
  {
    var orderBy = new Mojo.dto.ElementQueryDTO.StructOrderBy(structAttribute, attribute, order);
    this.orderByList.push(orderBy);
  }
});

// StructOrderBy
Mojo.dto.ElementQueryDTO.StructOrderBy = Mojo.Class.create();
Mojo.dto.ElementQueryDTO.StructOrderBy.prototype = {
  initialize : function(attributeStruct, attribute, order)
  {
    this.attributeStruct = attributeStruct;
    this.attribute = attribute;
    this.order = order;
  },
  
  getAttributeStruct: function() { return this.attributeStruct; },
  
  getAttribute : function() { return this.attribute; },
  
  getOrder : function() { return this.order; }
};

/**
 * StructQueryDTO
 */
Mojo.dto.StructQueryDTO = Mojo.Class.create();
Mojo.dto.StructQueryDTO.prototype = Mojo.Class.extend(Mojo.dto.EntityQueryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.EntityQueryDTO.prototype.initialize.call(this,obj);
  }
});

/**
 * BusinessQueryDTO
 */
Mojo.dto.BusinessQueryDTO = Mojo.Class.create();
Mojo.dto.BusinessQueryDTO.prototype = Mojo.Class.extend(Mojo.dto.ElementQueryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.ElementQueryDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      // load MdRelationship information for child and parent
      this.typeInMdRelationshipAsChildList = [];
      this.typeInMdRelationshipAsParentList = [];
      for(var i=0; i<obj.typeInMdRelationshipAsChildList.length; i++)
      {
        var asChild = obj.typeInMdRelationshipAsChildList[i];
        var asChildObj = new Mojo.dto.BusinessQueryDTO.TypeInMdRelationshipAsChild(asChild);
        this.typeInMdRelationshipAsChildList.push(asChildObj);
      }
      
      for(var i=0; i<obj.typeInMdRelationshipAsParentList.length; i++)
      {
        var asParent = obj.typeInMdRelationshipAsParentList[i];
        var asParentObj = new Mojo.dto.BusinessQueryDTO.TypeInMdRelationshipAsParent(asParent);
        this.typeInMdRelationshipAsParentList.push(asParentObj);
      }
    }
  },
  
  getTypeInMdRelationshipAsChildList : function() { return this.typeInMdRelationshipAsChildList; },
  
  getTypeInMdRelationshipAsParentList : function() { return this.typeInMdRelationshipAsParentList; }
});

// TypeInMdRelationshipAsChild
Mojo.dto.BusinessQueryDTO.TypeInMdRelationshipAsChild = new Mojo.Class.create();
Mojo.dto.BusinessQueryDTO.TypeInMdRelationshipAsChild.prototype = {
  initialize : function(obj)
  {
    if(obj)
    {
      this.childDisplayLabel = obj.childDisplayLabel;
      this.relationshipType = obj.relationshipType;
    }
  },
  
  getChildDisplayLabel : function() { return this.childDisplayLabel; },
  
  getRelationshipType : function() { return this.relationshipType; }
};

// TypeInMdRelationshipAsParent
Mojo.dto.BusinessQueryDTO.TypeInMdRelationshipAsParent = new Mojo.Class.create();
Mojo.dto.BusinessQueryDTO.TypeInMdRelationshipAsParent.prototype = {
  initialize : function(obj)
  {
    if(obj)
    {
      this.parentDisplayLabel = obj.parentDisplayLabel;
      this.relationshipType = obj.relationshipType;
    }
  },
  
  getParentDisplayLabel : function() { return this.parentDisplayLabel; },
  
  getRelationshipType : function() { return this.relationshipType; }
};

/**
 * RelationshipQueryDTO
 */
Mojo.dto.RelationshipQueryDTO = Mojo.Class.create();
Mojo.dto.RelationshipQueryDTO.prototype = Mojo.Class.extend(Mojo.dto.ElementQueryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.ElementQueryDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.parentMdBusiness = obj.parentMdBusiness;
      this.childMdBusiness = obj.childMdBusiness;
    }
  },
  
  getParentMdBusiness : function() { return this.parentMdBusiness; },
  
  getChildMdBusiness : function() { return this.childMdBusiness; }
});

Mojo.dto.ViewQueryDTO = Mojo.Class.create();
Mojo.dto.ViewQueryDTO.prototype = Mojo.Class.extend(Mojo.dto.ClassQueryDTO, {
  initialize : function(obj)
  {
    Mojo.dto.ClassQueryDTO.prototype.initialize.call(this,obj);
  }
});

/**
 * ComponentDTO
 */
Mojo.dto.ComponentDTO = Mojo.Class.create();
Mojo.dto.ComponentDTO.prototype = {
  initialize : function(obj)
  {
    if(obj)
    {
      this.dto_type = obj.dto_type;
      this.id = obj.id;
      this._type = obj._type;
      this._typeMd = (obj._typeMd) ? new Mojo.dto.TypeMd(obj._typeMd) : null;
      this._toString = obj._toString;
      this.readable = obj.readable;

      for(var attributeName in obj.attributeMap)
      {
        var attribute = obj.attributeMap[attributeName];
        var attributeDTO = new Mojo.dto[attribute.dtoType](attribute);
        obj.attributeMap[attributeName] = attributeDTO; 
      }
      this.attributeMap = obj.attributeMap; // keep reference for structs
    }
  },
  
  getType : function() { return this._type; },
  
  getTypeMd : function() { return this.getAttributeDTO('type').getAttributeMdDTO() },
  
  getId : function() { return this.id; },
  
  getIdMd : function() { return this.getAttributeDTO('id').getAttributeMdDTO() },
  
  getAttributeDTO : function(attributeName)  
  {
    return this.attributeMap[attributeName];
  },
  
  getMd : function() { return this._typeMd; },
  
  toString : function() { return this._toString; },
  
  isReadable : function() { return this.readable; }
};

/**
 * MutableDTO
 */
Mojo.dto.MutableDTO = Mojo.Class.create();

Mojo.dto.MutableDTO.get = function(clientRequest, id)
{
  Mojo.get(clientRequest, id);  
};

Mojo.dto.MutableDTO.prototype = Mojo.Class.extend(Mojo.dto.ComponentDTO, {
  initialize : function(obj)
  {
    Mojo.dto.ComponentDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.writable = obj.writable;
      this.modified = obj.modified;
      this.newInstance = obj.newInstance;
    }
  },
  
  isWritable : function() {return this.writable; },
  
  setModified : function(modified) { this.modified = modified; },
  
  isModified : function() { return this.modified; },
  
  isNewInstance : function() { return this.newInstance; }
});

/**
 * ValueObjectDTO
 */
Mojo.dto.ValueObjectDTO = Mojo.Class.create();
Mojo.dto.ValueObjectDTO.prototype = Mojo.Class.extend(Mojo.dto.ComponentDTO, {
  initialize : function (obj)
  {
    Mojo.dto.ComponentDTO.prototype.initialize.call(this,obj);
  }
});

/**
 * TransientDTO
 */
Mojo.dto.TransientDTO = Mojo.Class.create();
Mojo.dto.TransientDTO.prototype = Mojo.Class.extend(Mojo.dto.MutableDTO, {
  initialize : function(obj)
  {
    Mojo.dto.MutableDTO.prototype.initialize.call(this,obj);      
  }
});

/**
 * LocalizableDTO
 */
Mojo.dto.LocalizableDTO = Mojo.Class.create();
Mojo.dto.LocalizableDTO.prototype = Mojo.Class.extend(Mojo.dto.TransientDTO, {
  initialize : function (obj)
  {
    Mojo.dto.TransientDTO.prototype.initialize.call(this, obj);
  }
});

/**
 * ExceptionDTO
 */
Mojo.dto.ExceptionDTO = Mojo.Class.create();
Mojo.dto.ExceptionDTO.prototype = Mojo.Class.extend(Mojo.dto.LocalizableDTO, {
  initialize : function (obj)
  {
    Mojo.dto.LocalizableDTO.prototype.initialize.call(this, obj);
  }
});

/**
 * NotificationDTO
 */
Mojo.dto.NotificationDTO = Mojo.Class.create();
Mojo.dto.NotificationDTO.prototype = Mojo.Class.extend(Mojo.dto.LocalizableDTO, {
  initialize : function (obj)
  {
    Mojo.dto.LocalizableDTO.prototype.initialize.call(this, obj);
  }
});

/**
 * MessageDTO
 */
Mojo.dto.MessageDTO = Mojo.Class.create();
Mojo.dto.MessageDTO.prototype = Mojo.Class.extend(Mojo.dto.NotificationDTO, {
  initialize : function(obj)
  {
    Mojo.dto.NotificationDTO.prototype.initialize.call(this, obj);
    
    if(obj)
    {
      this.localizedMessage = obj.localizedMessage;
    }
  },
  
  getMessage : function() { return this.localizedMessage; }
});

/**
 * InformationDTO
 */
Mojo.dto.InformationDTO = Mojo.Class.create();
Mojo.dto.InformationDTO.prototype = Mojo.Class.extend(Mojo.dto.MessageDTO, {
  initialize : function(obj)
  {
    Mojo.dto.MessageDTO.prototype.initialize.call(this, obj);
  }
});

/**
 * WarningDTO
 */
Mojo.dto.WarningDTO = Mojo.Class.create();
Mojo.dto.WarningDTO.prototype = Mojo.Class.extend(Mojo.dto.MessageDTO, {
  initialize : function(obj)
  {
    Mojo.dto.MessageDTO.prototype.initialize.call(this, obj);
  }
});

/**
 * ProblemDTO
 */
Mojo.dto.ProblemDTO = Mojo.Class.create();
Mojo.dto.ProblemDTO.prototype = Mojo.Class.extend(Mojo.dto.NotificationDTO, {
  initialize : function(obj)
  {
    Mojo.dto.NotificationDTO.prototype.initialize.call(this, obj);
    
    if(obj)
    {
      this.localizedMessage = obj.localizedMessage;
      this.message = obj.message;
    }
  },
  
  getLocalizedMessage : function() { return this.localizedMessage; },
  
  getMessage : function() { return this.message; }
});

Mojo.dto.FrameworkProblemDTO = Mojo.Class.create();
Mojo.dto.FrameworkProblemDTO.prototype = {
  initialize : function(obj)
  {
    if(obj)
    {
      this.localizedMessage = obj.localizedMessage;
      this.message = obj.message;
    }
  },
  
  getLocalizedMessage : function() { return this.localizedMessage; },
  
  getMessage : function() { return this.message; }
};

/**
 * AttributeProblemDTO
 */
Mojo.dto.AttributeProblemDTO = Mojo.Class.create();
Mojo.dto.AttributeProblemDTO.prototype = Mojo.Class.extend(Mojo.dto.FrameworkProblemDTO, {
  initialize : function(obj)
  {
    Mojo.dto.FrameworkProblemDTO.prototype.initialize.call(this, obj);
    
    if(obj)
    {
      this.componentId = obj.componentId;
      this.definingType = obj.definingType;
      this.definingTypeDisplayLabel = obj.definingTypeDisplayLabel;
      this.attributeName = obj.attributeName;
      this.attributeDisplayLabel = obj.attributeDisplayLabel;
    }
  },
  
  getComponentId : function() { return this.componentId; },
  
  getDefiningType : function() { return this.definingType; },
  
  getDefiningTypeDisplayLabel : function() { return this.definingTypeDisplayLabel; },
  
  getAttributeName : function() { return this.attributeName; },
  
  getAttributeDisplayLabel : function() { return this.attributeDisplayLabel; }
});

/**
 * EmptyValueProblemDTO
 */
Mojo.dto.EmptyValueProblemDTO = Mojo.Class.create();
Mojo.dto.EmptyValueProblemDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeProblemDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeProblemDTO.prototype.initialize.call(this, obj);
  }
});

/**
 * ImmutableAttributeProblemDTO
 */
Mojo.dto.ImmutableAttributeProblemDTO = Mojo.Class.create();
Mojo.dto.ImmutableAttributeProblemDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeProblemDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeProblemDTO.prototype.initialize.call(this, obj);
  }
});

/**
 * SystemAttributeProblemDTO
 */
Mojo.dto.SystemAttributeProblemDTO = Mojo.Class.create();
Mojo.dto.SystemAttributeProblemDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeProblemDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeProblemDTO.prototype.initialize.call(this, obj);
  }
});

/**
 * Exception
 * 
 * This is the actual exception that can be thrown and caught.
 */
Mojo.dto.Exception = Mojo.Class.create();
Mojo.dto.Exception.prototype = {
  initialize : function ()
  {
    if(arguments.length == 1)
    {
      var arg1 = arguments[0];
      if(arg1 === null)
      {
        this.localizedMessage = null;
        this.message = null;
      }
      else if(typeof arg1 === 'string')
      {
        this.localizedMessage = arg1;
          this.message = arg1;
      }
      else if(typeof arg1 === 'object' 
        && 'localizedMessage' in arg1 
        && 'message' in arg1)
      {
        this.localizedMessage = arg1.localizedMessage;
        this.message = arg1.message;
      }
      else
      {
        this.localizedMessage = null;
        this.message = null;
      }
    }
    else if(arguments.length === 2)
    {
      this.localizedMessage = arguments[1];
      this.message = arguments[2];
    }
    else
    {
      this.localizedMessage = null;
      this.message = null;
    }
  },
  
  getLocalizedMessage : function() { return this.localizedMessage; },
  
  getMessage : function() { return this.message; },
  
  toString : function() { return this.localizedMessage; }
};

/**
 * SmartExceptionDTO
 * 
 * (delegates to an ExceptionDTO)
 */
Mojo.dto.SmartExceptionDTO = Mojo.Class.create();
Mojo.dto.SmartExceptionDTO.prototype = Mojo.Class.extend(Mojo.dto.Exception, {
  initialize : function (obj)
  {
    Mojo.dto.Exception.prototype.initialize.call(this,obj);  
    
    if(obj)
    {
      this.ex = new Mojo.dto.ExceptionDTO(obj);
    }
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
});

/**
 * MojoExceptionDTO
 * 
 * (for hard-coded exceptions)
 */
Mojo.dto.MojoExceptionDTO = Mojo.Class.create();
Mojo.dto.MojoExceptionDTO.prototype = Mojo.Class.extend(Mojo.dto.Exception, {
  initialize : function (obj)
  {
    Mojo.dto.Exception.prototype.initialize.call(this,obj);  
                
    if(obj !== null)
    {
      if(typeof obj === 'object')
      {  
        this.wrappedException = obj.wrappedException;
      }  
      else
      {
        // final resort. Only the localized message was received  
        this.wrappedException = null;
      }
    }    
  },
  
  getWrappedException : function() { return this.wrappedException; }
});

/**
 * ProblemExceptionDTO
 */
Mojo.dto.ProblemExceptionDTO = Mojo.Class.create();
Mojo.dto.ProblemExceptionDTO.prototype = Mojo.Class.extend(Mojo.dto.Exception, {
  initialize : function(obj)
  {
      Mojo.dto.Exception.prototype.initialize.call(this,obj);
      
      if(obj)
      {
        this.problemList = [];
        for(var i=0; i<obj.problemList.length; i++)
        {
          var problemJSON = obj.problemList[i];
          
          var problem = null;
          if('_type' in problemJSON && Mojo.util.typeExists(problemJSON._type))
          {
            var constructor_ = Mojo.util.getType(problemJSON._type);
            problem = new constructor_(problemJSON);
          }
          else
          {
            problem = new Mojo.dto.ProblemDTO(problemJSON);
          }
          
          this.problemList[i] = problem;
        }
      }
  },
  
  getProblems : function()
  {
    return this.problemList;
  }
});

/**
 * SessionDTO
 */
Mojo.dto.SessionDTO = Mojo.Class.create();
Mojo.dto.SessionDTO.prototype = Mojo.Class.extend(Mojo.dto.TransientDTO, {
  initialize : function (obj)
  {
    Mojo.dto.TransientDTO.prototype.initialize.call(this,obj);
  },
  
  apply : function(clientRequest)
  {
    if(this.isWritable())
    {
      if(this.isNewInstance())  
        Mojo.createSessionComponent(clientRequest, this);
      else
        Mojo.update(clientRequest, this);
    }
  }
});

/**
 * UtilDTO
 */
Mojo.dto.UtilDTO = Mojo.Class.create();
Mojo.dto.UtilDTO.prototype = Mojo.Class.extend(Mojo.dto.SessionDTO, {
  initialize : function (obj)
  {
    Mojo.dto.SessionDTO.prototype.initialize.call(this,obj);
  }
});

/**
 * ViewDTO
 */
Mojo.dto.ViewDTO = Mojo.Class.create();
Mojo.dto.ViewDTO.prototype = Mojo.Class.extend(Mojo.dto.SessionDTO, {
  initialize : function (obj)
  {
    Mojo.dto.SessionDTO.prototype.initialize.call(this,obj);
  }
});

/**
 */
Mojo.dto.EntityDTO = Mojo.Class.create();

Mojo.dto.EntityDTO.prototype = Mojo.Class.extend(Mojo.dto.MutableDTO, {
  
  initialize : function(obj)
  {
    Mojo.dto.MutableDTO.prototype.initialize.call(this,obj);
  },
  
  remove : function(clientRequest)
  {
    Mojo.deleteEntity(clientRequest, this.getId());  
  }  
});

/**
 * ElementDTO
 */
Mojo.dto.ElementDTO = Mojo.Class.create();

Mojo.dto.ElementDTO.prototype = Mojo.Class.extend(Mojo.dto.EntityDTO, {
  initialize : function(obj)
  {
    Mojo.dto.EntityDTO.prototype.initialize.call(this,obj);
          
    if(obj)
    {
      this.lockedByCurrentUser = obj.lockedByCurrentUser;
    }
  },
  
  lock : function(clientRequest)
  {
    Mojo.lock(clientRequest, this.getId());
  },
  
  unlock : function(clientRequest)
  {
    Mojo.unlock(clientRequest, this.getId());
  },
  
  isLockedByCurrentUser : function() { return this.lockedByCurrentUser; }
});


/*
 * BusinessDTO definition
 */
Mojo.dto.BusinessDTO = Mojo.Class.create();
Mojo.dto.BusinessDTO.prototype = Mojo.Class.extend(Mojo.dto.ElementDTO, {
  initialize : function(obj)
  {
    Mojo.dto.ElementDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.state = obj.state;
      this.transitions = obj.transitions;
    }
  },
  
  getState : function() { return this.state; },
  
  getTransitions : function() { return this.transitions; },
  
  apply : function(clientRequest)
  {
    if(this.isWritable())
    {
      if(this.isNewInstance())  
        Mojo.createBusiness(clientRequest, this);
      else
        Mojo.update(clientRequest, this);
    }
  }
});
 
/**
 * EnumerationDTOIF
 */
Mojo.dto.EnumerationDTOIF = Mojo.Class.create();
Mojo.dto.EnumerationDTOIF.prototype = {
  initialize : function(obj)
  {
    if(obj)
    {
      this.dto_type = obj.dto_type;
      this.enumType = obj.enumType;
      this._name = obj._name;
      this.displayLabel = obj.displayLabel;
    }
  },
  
  name : function () { return this._name; },
  
  getDisplayLabel : function() { return this.displayLabel; }
}
 
/*
 * RelationshipDTO definition
 */
Mojo.dto.RelationshipDTO = Mojo.Class.create();
Mojo.dto.RelationshipDTO.prototype = Mojo.Class.extend(Mojo.dto.ElementDTO, {
  initialize : function(obj)
  {
    Mojo.dto.ElementDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this._typeMd = new Mojo.dto.RelationshipMd(obj._relationshipMd);
      this.parentId = obj.parentId;
      this.childId = obj.childId;
    }
  },
  
  getParentId : function() { return this.parentId; },
  
  getChildId : function() { return this.childId; },
  
  apply : function(clientRequest)
  {
    if(this.isWritable())
    {
      if(this.isNewInstance())  
        Mojo.createRelationship(clientRequest, this);
      else
        Mojo.update(clientRequest, this);
    }
  },
  
  getParent : function(clientRequest)
  {
    Mojo.get(clientRequest, this.getParentId());  
  },
  
  getChild : function(clientRequest)
  {
    Mojo.get(clientRequest, this.getChildId());
  }

});

/**
 * StructDTO
 */
Mojo.dto.StructDTO = Mojo.Class.create();
Mojo.dto.StructDTO.prototype = Mojo.Class.extend(Mojo.dto.EntityDTO, {
  initialize : function(obj)
  {
    Mojo.dto.EntityDTO.prototype.initialize.call(this,obj);
  },
  
  apply : function(clientRequest)
  {
    if(this.isWritable())
    {
      if(this.isNewInstance())  
        Mojo.createStruct(clientRequest, this);
      else
        Mojo.update(clientRequest, this);
    }
  }
});

/*
 * Attribute definitions
 */
 
// attribute
Mojo.dto.AttributeDTO = Mojo.Class.create();
Mojo.dto.AttributeDTO.prototype = {
  initialize : function(obj)
  {
    if(obj)
    {
      this.attributeName = obj.attributeName;
      this.type = obj.type;
      this.dtoType = obj.dtoType;
      this.value = obj.value;
      this.readable = obj.readable;
      this.writable = obj.writable;
      this.modified = obj.modified;
    }
  },
  
  getName : function() { return this.attributeName; },
  
  getValue : function() { return this.value; },  
  
  setValue : function(value)
  {
    if(this.isWritable())
    {
      this.value = value;
      this.setModified(true);
    }
  },  
  
  isReadable : function() { return this.readable; },
  
  isWritable : function() { return this.writable; },
  
  isModified : function() { return this.modified; },
  
  setModified : function(modified) { this.modified = modified; },  
  
  getAttributeMdDTO : function() { return this.attributeMdDTO; }  
};

Mojo.dto.AttributeMdDTO = Mojo.Class.create();
Mojo.dto.AttributeMdDTO.prototype = {
  initialize : function(obj)
  {
    if(obj)
    {
      this.displayLabel = obj.displayLabel;
      this.description = obj.description;
      this.required = obj.required;
      this.immutable = obj.immutable;
      this.id = obj.id;
      this.system = obj.system;
      this.name = obj.name;
    }
  },
    
  getDisplayLabel : function() { return this.displayLabel; },
  
  getDescription : function() { return this.description; },
  
  isRequired : function() { return this.required; },
  
  isImmutable : function() { return this.immutable; },
  
  getId : function() { return this.id; },
  
  isSystem : function() { return this.system; },
  
  getName : function() { return this.name; }
};

// number
Mojo.dto.AttributeNumberDTO = Mojo.Class.create();
Mojo.dto.AttributeNumberDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDTO.prototype.initialize.call(this,obj);
  }
});

Mojo.dto.AttributeNumberMdDTO = Mojo.Class.create();
Mojo.dto.AttributeNumberMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMdDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this._rejectZero = obj._rejectZero;
      this._rejectNegative = obj._rejectNegative;
      this._rejectPositive = obj._rejectPositive;
    }

  },
  
  rejectZero : function() { return this._rejectZero; },
  
  rejectNegative : function() { return this._rejectNegative; },
  
  rejectPositive : function() { return this._rejectPositive; } 
});

// integer
Mojo.dto.AttributeIntegerDTO = Mojo.Class.create();
Mojo.dto.AttributeIntegerDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeNumberDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeNumberDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeIntegerMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeIntegerMdDTO = Mojo.Class.create();
Mojo.dto.AttributeIntegerMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeNumberMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeNumberMdDTO.prototype.initialize.call(this,obj);
  }
});

// long
Mojo.dto.AttributeLongDTO = Mojo.Class.create();
Mojo.dto.AttributeLongDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeNumberDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeNumberDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeLongMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeLongMdDTO = Mojo.Class.create();
Mojo.dto.AttributeLongMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeNumberMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeNumberMdDTO.prototype.initialize.call(this,obj);
  }
}); 

// dec
Mojo.dto.AttributeDecDTO = Mojo.Class.create();
Mojo.dto.AttributeDecDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeNumberDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeNumberDTO.prototype.initialize.call(this,obj);
  }
});

Mojo.dto.AttributeDecMdDTO = Mojo.Class.create();
Mojo.dto.AttributeDecMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeNumberMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeNumberMdDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.totalLength = obj.totalLength;
      this.decimalLength = obj.decimalLength;
    }

  },
  
  getTotalLength : function() { return this.totalLength; },
  
  getDecimalLength : function() { return this.decimalLength; }
});

// decimal
Mojo.dto.AttributeDecimalDTO = Mojo.Class.create();
Mojo.dto.AttributeDecimalDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDecDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDecDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeDecimalMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeDecimalMdDTO = Mojo.Class.create();
Mojo.dto.AttributeDecimalMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDecMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDecMdDTO.prototype.initialize.call(this,obj);
  }
});

// double
Mojo.dto.AttributeDoubleDTO = Mojo.Class.create();
Mojo.dto.AttributeDoubleDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDecDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDecDTO.prototype.initialize.call(this,obj);

    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeDoubleMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeDoubleMdDTO = Mojo.Class.create();
Mojo.dto.AttributeDoubleMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDecMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDecMdDTO.prototype.initialize.call(this,obj);
  }
});

// float
Mojo.dto.AttributeFloatDTO = Mojo.Class.create();
Mojo.dto.AttributeFloatDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDecDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDecDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeFloatMdDTO(obj.attributeMdDTO);
    }
  }
}); 

Mojo.dto.AttributeFloatMdDTO = Mojo.Class.create();
Mojo.dto.AttributeFloatMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDecMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDecMdDTO.prototype.initialize.call(this,obj);
  }
});

// text
Mojo.dto.AttributeTextDTO = Mojo.Class.create();
Mojo.dto.AttributeTextDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeTextMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeTextMdDTO = Mojo.Class.create();
Mojo.dto.AttributeTextMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMdDTO.prototype.initialize.call(this,obj);
  }
});

// character
Mojo.dto.AttributeCharacterDTO = Mojo.Class.create();
Mojo.dto.AttributeCharacterDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeCharacterMdDTO(obj.attributeMdDTO);
    }
  }
}); 

Mojo.dto.AttributeCharacterMdDTO = Mojo.Class.create();
Mojo.dto.AttributeCharacterMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMdDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.size = obj.size;
    }
  },
  
  getSize : function() { return this.size; }
});

// boolean
Mojo.dto.AttributeBooleanDTO = Mojo.Class.create();
Mojo.dto.AttributeBooleanDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeBooleanMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeBooleanMdDTO = Mojo.Class.create();
Mojo.dto.AttributeBooleanMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMdDTO.prototype.initialize.call(this,obj);
  }
});

// struct
Mojo.dto.AttributeStructDTO = Mojo.Class.create();
Mojo.dto.AttributeStructDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeStructMdDTO(obj.attributeMdDTO);
      
      this.structDTO = obj.structDTO;
    }
  },
  
  getStructDTO : function()
  {
    return this.structDTO;
  }  
});

Mojo.dto.AttributeStructMdDTO = Mojo.Class.create();
Mojo.dto.AttributeStructMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMdDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.definingMdStruct = obj.definingMdStruct;
    }
  },
  
  getDefiningMdStruct : function() { return this.definingMdStruct; }
});

// moment
Mojo.dto.AttributeMomentDTO = Mojo.Class.create();
Mojo.dto.AttributeMomentDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      // set internal value as a date
      if(this.value != null && this.value != '')
      {
        this.value = new Date(this.value);  
      }
      else
      {
        this.value = null;
      }
    }
  },
  
  setValue : function(value)
  {
    if(this.isWritable())
    {
      this.value = Mojo.util.isDate(value) ? value : new Date(value);

      this.setModified(true);
    }
  }
});

Mojo.dto.AttributeMomentMdDTO = Mojo.Class.create();
Mojo.dto.AttributeMomentMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMdDTO.prototype.initialize.call(this,obj);
  }
});

// datetime
Mojo.dto.AttributeDateTimeDTO = Mojo.Class.create();
Mojo.dto.AttributeDateTimeDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMomentDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMomentDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeDateTimeMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeDateTimeMdDTO = Mojo.Class.create();
Mojo.dto.AttributeDateTimeMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMomentMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMomentMdDTO.prototype.initialize.call(this,obj);
  }
});

// date
Mojo.dto.AttributeDateDTO = Mojo.Class.create();
Mojo.dto.AttributeDateDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMomentDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMomentDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeDateMdDTO(obj.attributeMdDTO);
    }
  }
}); 

Mojo.dto.AttributeDateMdDTO = Mojo.Class.create();
Mojo.dto.AttributeDateMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMomentMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMomentMdDTO.prototype.initialize.call(this,obj);
  }
});

// time
Mojo.dto.AttributeTimeDTO = Mojo.Class.create();
Mojo.dto.AttributeTimeDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMomentDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMomentDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeTimeMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeTimeMdDTO = Mojo.Class.create();
Mojo.dto.AttributeTimeMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMomentMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMomentMdDTO.prototype.initialize.call(this,obj);
  }
});

// reference object
Mojo.dto.AttributeReferenceDTO = Mojo.Class.create();
Mojo.dto.AttributeReferenceDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeReferenceMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeReferenceMdDTO = Mojo.Class.create();
Mojo.dto.AttributeReferenceMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMdDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.referencedMdBusiness = obj.referenceMdBusiness;
    }
  },
  
  getReferencedMdBusiness : function() { return this.referencedMdBusiness; }
});

// enumeration
Mojo.dto.AttributeEnumerationDTO = Mojo.Class.create();
Mojo.dto.AttributeEnumerationDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDTO.prototype.initialize.call(this,obj);
      
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeEnumerationMdDTO(obj.attributeMdDTO);
    
      // javascript doesn't have a set, so use a hash with key == value.
      this.enumNames = {};
      for(var i=0; i<obj.enumNames.length; i++)
      {
        var enumName = obj.enumNames[i];
        this.enumNames[enumName] = enumName;
      }
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

      var enumName = Mojo.util.isObject(item) ? item.name() : item;
      this.enumNames[enumName] = enumName;
    }
  },
  
  remove : function(item)
  {
    if(this.isWritable())
    {
      var enumName = Mojo.util.isObject(item) ? item.name() : item;
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
    return Mojo.util.getKeys(this.enumNames);
  },
  
  getEnumValues : function(clientRequest)
  {
    var enumType = this.getAttributeMdDTO().getReferencedMdEnumeration();
    var names = Mojo.util.getKeys(this.enumNames);
    Mojo.ClientSession.getEnumerations(clientRequest, enumType, names);
  }
}); 

Mojo.dto.AttributeEnumerationMdDTO = Mojo.Class.create();
Mojo.dto.AttributeEnumerationMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMdDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this._selectMultiple = obj._selectMultiple;
      this.referencedMdEnumeration = obj.referencedMdEnumeration;
      
      this.enumNames = {}; // key/value = name/display label
      Mojo.util.copy(obj.enumNames, this.enumNames);
    }
  },
  
  getReferencedMdEnumeration : function() { return this.referencedMdEnumeration; },
  
  selectMultiple : function() { return this._selectMultiple; },
  
  getEnumNames : function()
  {
    return Mojo.util.getKeys(this.enumNames);
  },
  
  getEnumLabels : function()
  {
  	return Mojo.util.getValues(this.enumNames);
  },
  
  getEnumDisplayLabel : function(enumName)
  {
  	return this.enumNames[enumName];
  },
  
  getEnumItems : function()
  {
  	var copy = {};
  	Mojo.util.copy(this.enumNames, cop);
  	return copy;
  }
});

// encryption
Mojo.dto.AttributeEncryptionDTO = Mojo.Class.create();
Mojo.dto.AttributeEncryptionDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeDTO.prototype.initialize.call(this,obj);
  }
});

Mojo.dto.AttributeEncryptionMdDTO = Mojo.Class.create();
Mojo.dto.AttributeEncryptionMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeMdDTO.prototype.initialize.call(this,obj);
    
    if(obj)
    {
      this.encryptionMethod = obj.encryptionMethod;
    }
  },
  
  getEncryptionMethod : function() { return this.encryptionMethod; }
});

// hash
Mojo.dto.AttributeHashDTO = Mojo.Class.create();
Mojo.dto.AttributeHashDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeEncryptionDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeEncryptionDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {      
      this.attributeMdDTO = new Mojo.dto.AttributeHashMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeHashMdDTO = Mojo.Class.create();
Mojo.dto.AttributeHashMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeEncryptionMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeEncryptionMdDTO.prototype.initialize.call(this,obj);
  }
}); 

// symmetric
Mojo.dto.AttributeSymmetricDTO = Mojo.Class.create();
Mojo.dto.AttributeSymmetricDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeEncryptionDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeEncryptionDTO.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.attributeMdDTO = new Mojo.dto.AttributeSymmetricMdDTO(obj.attributeMdDTO);
    }
  }
});

Mojo.dto.AttributeSymmetricMdDTO = Mojo.Class.create();
Mojo.dto.AttributeSymmetricMdDTO.prototype = Mojo.Class.extend(Mojo.dto.AttributeEncryptionMdDTO, {
  initialize : function(obj)
  {
    Mojo.dto.AttributeEncryptionMdDTO.prototype.initialize.call(this,obj);
  }
});

Mojo.dto.TypeMd = Mojo.Class.create();
Mojo.dto.TypeMd.prototype = {
  initialize : function(obj)
  {
    if(obj)
    {
      this.displayLabel = obj.displayLabel;
      this.description = obj.description;
      this.id = obj.id;
    }
  },
  
  getDisplayLabel : function() {return this.displayLabel;},
  
  getDescription : function() {return this.description;},
  
  getId : function() {return this.id;}
};

Mojo.dto.RelationshipMd = Mojo.Class.create();
Mojo.dto.RelationshipMd.prototype = Mojo.Class.extend(Mojo.dto.TypeMd, {
  initialize : function(obj)
  {
    Mojo.dto.TypeMd.prototype.initialize.call(this,obj);    
    
    if(obj)
    {
      this.parentMdBusiness = obj.parentMdBusiness;
      this.childMdBusiness = obj.childMdBusiness;
    }
  },
  
  getParentMdBusiness : function() { return this.parentMdBusiness; },
  
  getChildMdBusiness  : function() { return this.childMdBusiness; }
});