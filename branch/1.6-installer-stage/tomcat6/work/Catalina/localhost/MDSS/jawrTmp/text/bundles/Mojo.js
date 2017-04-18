
var Mojo={$:{},Meta:{_classes:{},_native:[],_isInitialized:false,_pseudoConstructor:function(){},newInstance:function(type)
{var klass=Mojo.Meta._classes[type];var args=[].splice.call(arguments,1);var obj=new klass(Mojo.Meta._pseudoConstructor);klass.prototype.initialize.apply(obj,args);return obj;},shorthand:function(pattern,attachTo)
{var anchorObj=attachTo||Mojo.GLOBAL;var r='^'+pattern.replace(/\./g,'\\.').replace(/\*/g,'.*')+'$';var re=new RegExp(r);var classNames=Mojo.Util.getKeys(Mojo.Meta._classes);for(var i=0;i<classNames.length;i++)
{var className=classNames[i];if(re.test(className))
{var klass=Mojo.Meta._classes[className];anchorObj[klass.getMetaClass().getName()]=klass;}}},alias:function(pattern,attachTo)
{var anchorObj=attachTo||Mojo.GLOBAL;var r='^'+pattern.replace(/\./g,'\\.').replace(/\*/g,'.*')+'$';var re=new RegExp(r);var classNames=Mojo.Util.getKeys(Mojo.Meta._classes);for(var i=0;i<classNames.length;i++)
{var className=classNames[i];if(re.test(className))
{var klass=Mojo.Meta._classes[className];var namespace=Mojo.Meta._buildPackage(klass.getMetaClass().getPackage(),anchorObj);namespace[klass.getMetaClass().getName()]=klass;}}},findClass:function(type)
{return Mojo.Meta._classes[type];},_buildPackage:function(packageName,alias)
{if(packageName==null||packageName==='')
{return alias;}
var parts=packageName.split(".");var currentBuild=alias;for(var i=0;i<parts.length;i++)
{var part=parts[i];if(Mojo.IS_OBJECT_TO_STRING!==Object.prototype.toString.call(currentBuild[part]))
{currentBuild[part]={};}
currentBuild=currentBuild[part];}
return currentBuild;},dropClass:function(type)
{delete Mojo.Meta._classes[type];},getClasses:function()
{return Mojo.Util.getKeys(Mojo.Meta._classes);},classExists:function(type)
{return type in Mojo.Meta._classes;},_makeSingleton:function(klass)
{var sInitialize=klass.prototype.initialize;klass.prototype.initialize=function(){throw Error("Cannot instantiate the singleton class ["+this.getMetaClass().getQualifiedName()+"]. "+"Use the static [getInstance()] method instead.");};klass.getInstance=(function(sInit){var instance=null;return function(){if(instance==null)
{var temp=klass.prototype.initialize;klass.prototype.initialize=sInit;instance=new klass();klass.prototype.initialize=temp;}
return instance;};})(sInitialize);return{name:'getInstance',isStatic:true,isConstructor:false,method:klass.getInstance,klass:klass};},_createConstructor:function()
{return function(){if(Mojo.Meta._isInitialized&&this.getMetaClass().isAbstract())
{var msg="Cannot instantiate the abstract class ["+this.getMetaClass().getQualifiedName()+"].";var error=new Error(msg);Mojo.log.LogManager.writeError(msg,error);throw error;}
this.__context={};if(arguments.length===1&&arguments[0]===Mojo.Meta._pseudoConstructor)
{Mojo.Meta._pseudoConstructor();}
else
{this.initialize.apply(this,[].splice.call(arguments,0));}};},_addMethod:function(klass,superClass,methodName,definition)
{var isFunction=Mojo.IS_FUNCTION_TO_STRING===Object.prototype.toString.call(definition);var method=isFunction?definition:definition.method;klass.prototype[methodName]=method;if(superClass!==Object&&Mojo.IS_FUNCTION_TO_STRING===Object.prototype.toString.call(superClass.prototype[methodName]))
{klass.prototype['$'+methodName]=(function(m){return function(){var execStack=this.__context['$'+m];if(!execStack)
{execStack=[];this.__context['$'+m]=execStack;}
var currentKlass=execStack.length===0?this.getMetaClass().getSuperClass():execStack[execStack.length-1].getMetaClass().getSuperClass();execStack.push(currentKlass);var retObj=currentKlass.prototype[m].apply(this,arguments);execStack.pop();return retObj;};})(methodName);}
return{name:methodName,isStatic:false,isAbstract:(!isFunction&&definition.IsAbstract),isConstructor:(methodName==='initialize'),method:method,klass:klass};},newClass:function(qualifiedName,definition)
{var metaRef=qualifiedName==='Mojo.Meta'?this:Mojo.Meta;var superClass;if(Mojo.IS_FUNCTION_TO_STRING===Object.prototype.toString.call(definition.Extends))
{superClass=definition.Extends;if(!superClass)
{throw new Error('The class ['+qualifiedName+'] does not extend a valid class.');}}
else if(Mojo.IS_STRING_TO_STRING===Object.prototype.toString.call(definition.Extends))
{superClass=metaRef._classes[definition.Extends];if(!superClass)
{throw new Error('The class ['+qualifiedName+'] does not extend a valid class.');}}
else
{superClass=metaRef._classes[Mojo.ROOT_PACKAGE+'Base'];}
var alias=definition.Alias||null;var constants=definition.Constants||{};var instances=definition.Instance||{};var statics=definition.Static||{};var isNative=definition.Native||false;var isSingleton=definition.IsSingleton||false;var isAbstract=definition.IsAbstract||false;var packageName;var className;if(/\./.test(qualifiedName))
{packageName=qualifiedName.substring(0,qualifiedName.lastIndexOf("."));className=qualifiedName.substring(packageName.length+1);}
else
{packageName='';className=qualifiedName;}
if(!instances.initialize)
{instances.initialize=function(){};}
var klass=metaRef._createConstructor();var namespace=metaRef._buildPackage(packageName,Mojo.GLOBAL);namespace[className]=klass;if(alias!==null)
{var namespace=metaRef._buildPackage(packageName,alias);namespace[className]=klass;}
metaRef._classes[qualifiedName]=klass;var temp=function(){};temp.prototype=superClass.prototype;klass.prototype=new temp();klass.prototype.constructor=klass;var config={packageName:packageName,className:className,klass:klass,superClass:superClass,isNative:isNative,instanceMethods:{},staticMethods:{},isAbstract:isAbstract,alias:alias,qualifiedName:qualifiedName,isSingleton:isSingleton,constants:[]};for(var m in instances)
{var methodConfig=metaRef._addMethod(klass,superClass,m,instances[m]);config.instanceMethods[m]=methodConfig;}
if(isSingleton)
{var methodDef=this._makeSingleton(klass);config.staticMethods.getInstance=methodDef;}
for(var c in constants)
{config.constants.push({name:c,value:constants[c]});}
for(var m in statics)
{if(Mojo.IS_FUNCTION_TO_STRING===Object.prototype.toString.call(statics[m]))
{config.staticMethods[m]={name:m,isStatic:true,isAbstract:false,isConstructor:false,method:statics[m],klass:klass};}
else
{klass[m]=statics[m];}}
if(definition.Native)
{klass.__metaClass=config;klass.prototype.__metaClass=config;Mojo.Meta._native.push(klass);}
else
{var cKlass=Mojo.$.com.terraframe.mojo.Class;klass.__metaClass=new cKlass(config);klass.prototype.__metaClass=klass.__metaClass;}
return klass;}},JSON_ENDPOINT:'Mojo/JSONControllerServlet',ATTRIBUTE_DTO_PACKAGE:'com.terraframe.mojo.transport.attributes.',MD_DTO_PACKAGE:'com.terraframe.mojo.transport.metadata.',ROOT_PACKAGE:'com.terraframe.mojo.',BUSINESS_PACKAGE:'com.terraframe.mojo.business.',ATTRIBUTE_PROBLEM_PACKAGE:'com.terraframe.mojo.dataaccess.attributes.',IS_OBJECT_TO_STRING:Object.prototype.toString.call({}),IS_ARRAY_TO_STRING:Object.prototype.toString.call([]),IS_FUNCTION_TO_STRING:Object.prototype.toString.call(function(){}),IS_DATE_TO_STRING:Object.prototype.toString.call(new Date()),IS_STRING_TO_STRING:Object.prototype.toString.call(''),IS_NUMBER_TO_STRING:Object.prototype.toString.call(0),IS_BOOLEAN_TO_STRING:Object.prototype.toString.call(true),emptyFunction:function(){},GLOBAL:(function(){return this;})()};Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'Base',{Alias:Mojo.$,Native:true,IsAbstract:true,Extends:Object,Instance:{initialize:function()
{},equals:function(obj)
{return obj!=null&&this===obj;},clone:function()
{return Mojo.Meta.newInstance(this.getMetaClass().getQualifiedName(),[].splice.call(arguments,0));},toString:function()
{return'['+this.getMetaClass().getQualifiedName()+'] instance';}}});Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'Class',{Alias:Mojo.$,Native:true,Instance:{initialize:function(config)
{this._packageName=config.packageName;this._className=config.className;this._isNative=config.isNative;this._isAbstract=config.isAbstract;this._isSingleton=config.isSingleton;this._klass=config.klass;this._superClass=config.superClass;this._alias=config.alias;this._qualifiedName=config.qualifiedName;this._subclasses={};var notBase=this._superClass!==Object;var mKlass=Mojo.$.com.terraframe.mojo.Method;this._instanceMethods={};this._staticMethods={};var abstractMethods={};if(notBase)
{var pInstances=this._superClass.getMetaClass().getInstanceMethods(true);for(var i in pInstances)
{var method=pInstances[i];this._instanceMethods[i]=method;if(method.isAbstract())
{abstractMethods[i]=method;}}}
var tInstances=config.instanceMethods;for(var i in tInstances)
{var definition=tInstances[i];if(this._instanceMethods.hasOwnProperty(i))
{var overridden=this._instanceMethods[i];definition.overrideKlass=definition.klass;definition.klass=overridden.getDefiningClass();}
this._instanceMethods[i]=new mKlass(definition,this);if(i in abstractMethods)
{delete abstractMethods[i];}}
if(!this._isAbstract)
{var unimplemented=[];for(var i in abstractMethods)
{if(abstractMethods.hasOwnProperty(i))
{unimplemented.push(abstractMethods[i].getName());}}
if(unimplemented.length>0)
{var msg="The class ["+this._qualifiedName+"] must "+"implement the abstract method(s) ["+unimplemented.join(', ')+"].";var error=new Error(msg);Mojo.log.LogManager.writeError(msg,error);throw error;}}
if(notBase)
{var pStatics=this._superClass.getMetaClass().getStaticMethods(true);for(var i in pStatics)
{var mStatic=pStatics[i];this._staticMethods[mStatic.getName()]=mStatic;this._klass[mStatic.getName()]=mStatic.getMethod();}}
var tStatics=config.staticMethods;for(var i in tStatics)
{var definition=tStatics[i];if(this._staticMethods.hasOwnProperty(i))
{var overridden=this._staticMethods[i];definition.overrideKlass=definition.klass;definition.klass=overridden.getDefiningClass();}
var method=new mKlass(definition,this);this._staticMethods[i]=method;this._klass[i]=method.getMethod();}
this._constants={};var cKlass=Mojo.$.com.terraframe.mojo.Constant;if(notBase)
{var pConstants=this._superClass.getMetaClass().getConstants(true);for(var i in pConstants)
{if(pConstants.hasOwnProperty(i))
{this._constants[i]=pConstants[i];}}}
for(var i=0;i<config.constants.length;i++)
{var constObj=new cKlass(config.constants[i]);if(notBase&&this._constants[constObj.getName()])
{constObj._setDefiningClass(this._constants[constObj.getName()].getDefiningClass());constObj._setOverrideClass(this._klass);}
else
{constObj._setDefiningClass(this._klass);}
this._constants[constObj.getName()]=constObj;this._klass[constObj.getName()]=constObj.getValue();}
if(notBase)
{this._superClass.getMetaClass()._addSubClass(this._qualifiedName,this._klass);}
this._addMetaClassMethod();},_addMetaClassMethod:function()
{var mName='getMetaClass';this._klass[mName]=(function(metaClass){return function(){return metaClass;};})(this);this._klass.prototype[mName]=this._klass[mName];this._instanceMethods[mName]=new Mojo.$.com.terraframe.mojo.Method({name:mName,isStatic:false,isAbstract:false,isConstructor:false,method:this._klass[mName],klass:this._klass,overrideKlass:this._klass},this);this._staticMethods[mName]=new Mojo.$.com.terraframe.mojo.Method({name:mName,isStatic:true,isAbstract:false,isConstructor:false,method:this._klass[mName],klass:this._klass,overrideKlass:this._klass},this);},_addSubClass:function(qualifiedName,klass)
{this._subclasses[qualifiedName]=klass;},isSingleton:function()
{return this._isSingleton;},getSubClasses:function(asMap)
{if(asMap)
{return this._subclasses;}
else
{var values=[];for(var i in this._subclasses)
{values.push(this._subclasses[i]);}
return values;}},getConstants:function(asMap)
{if(asMap)
{return this._constants;}
else
{var values=[];for(var i in this._constants)
{values.push(this._constants[i]);}
return values;}},hasConstant:function(name)
{return this._constants[name]!=null;},getConstant:function(name)
{return this._constants[name];},getMethod:function(name)
{return this._instanceMethods[name]||this._staticMethods[name];},hasInstanceMethod:function(name)
{return this._instanceMethods[name]!=null;},getInstanceMethod:function(name)
{return this._instanceMethods[name];},hasStaticMethod:function(name)
{return this._staticMethods[name]!=null;},getStaticMethod:function(name)
{return this._staticMethods[name];},getInstanceMethods:function(asMap)
{if(asMap)
{return this._instanceMethods;}
else
{var arr=[];for(var i in this._instanceMethods)
{if(true||this._instanceMethods.hasOwnProperty(i))
{arr.push(this._instanceMethods[i]);}}
return arr;}},getStaticMethods:function(asMap)
{if(asMap)
{return this._staticMethods;}
else
{var arr=[];for(var i in this._staticMethods)
{if(true||this._staticMethods.hasOwnProperty(i))
{arr.push(this._staticMethods[i]);}}
return arr;}},isAbstract:function()
{return this._isAbstract;},getMethods:function()
{return[].concat(this.getInstanceMethods(false),this.getStaticMethods(false));},getPackage:function()
{return this._packageName;},getName:function()
{return this._className;},getQualifiedName:function()
{return this._qualifiedName;},_getClass:function(klass)
{var classObj=null;if(klass instanceof this.constructor)
{return klass;}
else if(Mojo.Util.isFunction(klass)||klass instanceof Mojo.$.com.terraframe.mojo.Base)
{return klass.getMetaClass();}
else if(Mojo.Util.isString(klass))
{var foundClass=Mojo.Meta.findClass(klass);if(foundClass)
{return foundClass.getMetaClass();}
else
{return null;}}
else
{return null;}},isSuperClassOf:function(klass)
{var classObj=this._getClass(klass);if(this===classObj)
{return true;}
var superClass=classObj.getSuperClass();while(superClass!==Object)
{if(superClass.getMetaClass()===this)
{return true;}
superClass=superClass.getMetaClass().getSuperClass();}
return false;},isSubClassOf:function(klass)
{var classObj=this._getClass(klass);return classObj.isSuperClassOf(this);},getSuperClass:function()
{return this._superClass;},isNative:function()
{return this._isNative;},getAlias:function()
{return this._alias;},newInstance:function()
{var args=[this.getQualifiedName()].concat([].splice.call(arguments,0));return Mojo.Meta.newInstance.apply(this,args);},toString:function()
{return'[Class] '+this.getQualifiedName();}}});Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+"Constant",{Alias:Mojo.$,Native:true,Instance:{initialize:function(config)
{this._name=config.name;this._value=config.value;this._klass=null;this._overrideKlass=null;},_setDefiningClass:function(klass)
{this._klass=klass;},_setOverrideClass:function(klass)
{this._overrideKlass=klass;},getName:function()
{return this._name;},getValue:function()
{return this._value;},getDefiningClass:function()
{return this._klass;},isOverride:function()
{return this._overrideKlass!==null;},getOverrideClass:function()
{return this._overrideKlass;}}});Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'Method',{Alias:Mojo.$,Native:true,Instance:{initialize:function(config,metaClass)
{this._name=config.name;this._isStatic=config.isStatic;this._isConstructor=config.isConstructor;this._klass=config.klass||null;this._overrideKlass=config.overrideKlass||null;this._isAbstract=config.isAbstract;if(Mojo.Meta._isInitialized&&!metaClass.isAbstract()&&this._isAbstract)
{var msg="The non-abstract class ["+metaClass.getQualifiedName()+"] cannot "+"cannot declare the abstract method ["+this._name+"].";var error=new Error(msg);Mojo.log.LogManager.writeError(msg,error);throw error;}
var method;if(this._isAbstract)
{method=(function(name){return function(){var definingClass=this.getMetaClass().getMethod(name).getDefiningClass().getMetaClass().getQualifiedName();var msg="Cannot invoke the abstract method ["+name+"] on ["+definingClass+"].";var error=new Error(msg);Mojo.log.LogManager.writeError(msg,error);throw error;};})(this._name);this._klass.prototype[this._name]=method;}
else
{method=config.method;}
this._method=method;this._aspects=[];},isAbstract:function()
{return this._isAbstract;},addAspect:function(aspect)
{this._aspects.push(aspect);},getAspects:function()
{return this._aspects;},isConstructor:function()
{return this._isConstructor;},getArity:function()
{return this._method.length;},isOverride:function()
{return!this._isStatic&&this._overrideKlass!==null;},isHiding:function()
{return this._isStatic&&this._overrideKlass!==null;},getOverrideClass:function()
{return this._overrideKlass;},getMethod:function()
{return this._method;},getName:function()
{return this._name;},isStatic:function()
{return this._isStatic;},getDefiningClass:function()
{return this._klass;},toString:function()
{return'[Method] '+this.getName();}},});(function(){var klass=Mojo.Meta.findClass(Mojo.ROOT_PACKAGE+'Class');for(var i=0;i<Mojo.Meta._native.length;i++)
{var bootstrapped=Mojo.Meta._native[i];var cClass=new klass(bootstrapped.__metaClass);bootstrapped.__metaClass=cClass
bootstrapped.prototype.__metaClass=cClass;}
var metaProps={};for(var i in Mojo.Meta)
{if(Mojo.Meta.hasOwnProperty(i))
{metaProps[i]=Mojo.Meta[i];}}
delete Mojo.Meta;metaProps.newClass('Mojo.Meta',{Static:metaProps});Mojo.Meta._isInitialized=true;})();Mojo.Meta.newClass('Mojo.Util',{IsAbstract:true,Instance:{initialize:function(){}},Static:{ISO8601_REGEX:"^([0-9]{4})-([0-9]{2})-([0-9]{2})T([0-9]{2}):([0-9]{2}):([0-9]{2})([-+])([0-9]{2})([0-9]{2})$",isObject:function(o)
{return o!=null&&Object.prototype.toString.call(o)===Mojo.IS_OBJECT_TO_STRING;},isArray:function(o)
{return o!=null&&Object.prototype.toString.call(o)===Mojo.IS_ARRAY_TO_STRING;},isFunction:function(o)
{return o!=null&&Object.prototype.toString.call(o)===Mojo.IS_FUNCTION_TO_STRING;},isDate:function(o)
{return o!=null&&Object.prototype.toString.call(o)===Mojo.IS_DATE_TO_STRING;},isString:function(o)
{return o!=null&&Object.prototype.toString.call(o)===Mojo.IS_STRING_TO_STRING;},isNumber:function(o)
{return o!=null&&Object.prototype.toString.call(o)===Mojo.IS_NUMBER_TO_STRING;},isBoolean:function(o)
{return o!=null&&Object.prototype.toString.call(o)===Mojo.IS_BOOLEAN_TO_STRING;},isUndefined:function(o)
{return typeof o==='undefined';},bind:function(thisRef,func)
{var args=[].splice.call(arguments,2);return function(){return func.apply(thisRef,args.concat([].splice.call(arguments,0)))}},curry:function(func)
{var args=[].splice.call(arguments,1);return function(){return func.apply(this,args.concat([].splice.call(arguments,0)))}},trim:function(str)
{var str=str.replace(/^\s\s*/,'');var ws=/\s/;var i=str.length;while(ws.test(str.charAt(--i)));return str.slice(0,i+1);},memoize:function(func,context)
{func.memoCache={};return function(){var args=[].splice.call(arguments,0);if(func.memoCache[args])
{return func.memoCache[args];}
else
{func.memoCache[args]=func.apply(context||this,args);return func.memoCache[args];}}},generateId:function(customLength)
{var result='';var totalLength=customLength||32;for(var i=0;i<totalLength;i++)
{result+=Math.floor(Math.random()*16).toString(16);}
return result;},debounce:function(func,threshold,context,enforceWait)
{var timeout=null;var isExec=null;return function(){if(timeout!==null||enforceWait&&isExec)
{return;}
timeout=setTimeout(function(){clearTimeout(timeout);timeout=null;},threshold||500);isExec=true;func.apply(context||this,arguments);isExec=false;};},setISO8601:function(date,string,ignoreTimezone)
{var regexp=new RegExp(Mojo.Util.ISO8601_REGEX);if(!Mojo.Util.isString(string)||string===''||!regexp.test(string))
{return false;}
var d=string.match(regexp);var offset=0;var tempDate=new Date(d[1],0,1);if(d[2]){tempDate.setMonth(d[2]-1);}
if(d[3]){tempDate.setDate(d[3]);}
if(d[4]){tempDate.setHours(d[4]);}
if(d[5]){tempDate.setMinutes(d[5]);}
if(d[6]){tempDate.setSeconds(d[6]);}
if(d[8]){offset=(Number(d[8])*60)+Number(d[9]);offset*=((d[7]=='-')?1:-1);}
var time=Number(tempDate);if(ignoreTimezone!==true)
{offset-=tempDate.getTimezoneOffset();time+=(offset*60*1000);}
date.setTime(Number(time));return true;},toISO8601:function(date)
{var format=6;var offset=date.getTimezoneOffset()/60;var tempDate=date;var zeropad=function(num){var value=(num<0?num*-1:num);return(value<10?'0'+value:value);}
var str="";str+=tempDate.getFullYear();str+="-"+zeropad(tempDate.getMonth()+1);str+="-"+zeropad(tempDate.getDate());str+="T"+zeropad(tempDate.getHours())+":"+zeropad(tempDate.getMinutes());str+=":"+zeropad(tempDate.getSeconds());str+=(offset>0?'-':'+')+zeropad(offset)+'00';return str;},JSON:(function()
{var cx=/[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,escapeable=/[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,gap,indent,meta={'\b':'\\b','\t':'\\t','\n':'\\n','\f':'\\f','\r':'\\r','"':'\\"','\\':'\\\\'},rep;function quote(string){escapeable.lastIndex=0;return escapeable.test(string)?'"'+string.replace(escapeable,function(a){var c=meta[a];if(typeof c==='string'){return c;}
return'\\u'+('0000'+
(+(a.charCodeAt(0))).toString(16)).slice(-4);})+'"':'"'+string+'"';}
function str(key,holder){var i,k,v,length,mind=gap,partial,value=holder[key];if(typeof rep==='function'){value=rep.call(holder,key,value);}
if(Mojo.Util.isDate(value))
{return quote(Mojo.Util.toISO8601(value));}
switch(typeof value){case'string':return quote(value);case'number':return isFinite(value)?String(value):'null';case'boolean':case'null':return String(value);case'object':if(!value){return'null';}
gap+=indent;partial=[];if(typeof value.length==='number'&&!(value.propertyIsEnumerable('length'))){length=value.length;for(i=0;i<length;i+=1){partial[i]=str(i,value)||'null';}
v=partial.length===0?'[]':gap?'[\n'+gap+
partial.join(',\n'+gap)+'\n'+
mind+']':'['+partial.join(',')+']';gap=mind;return v;}
if(rep&&typeof rep==='object'){length=rep.length;for(i=0;i<length;i+=1){k=rep[i];if(typeof k==='string'){v=str(k,value,rep);if(v){partial.push(quote(k)+(gap?': ':':')+v);}}}}else{for(k in value){if(Object.hasOwnProperty.call(value,k)){v=str(k,value,rep);if(v){partial.push(quote(k)+(gap?': ':':')+v);}}}}
v=partial.length===0?'{}':gap?'{\n'+gap+partial.join(',\n'+gap)+'\n'+
mind+'}':'{'+partial.join(',')+'}';gap=mind;return v;}}
return{stringify:function(value,replacer,space){var i;gap='';indent='';if(typeof space==='number'){for(i=0;i<space;i+=1){indent+=' ';}}else if(typeof space==='string'){indent=space;}
rep=replacer;if(replacer&&typeof replacer!=='function'&&(typeof replacer!=='object'||typeof replacer.length!=='number')){throw new Error('JSON.stringify');}
return str('',{'':value});},parse:function(text,reviver){var j;function walk(holder,key){var k,v,value=holder[key];if(value&&typeof value==='object'){for(k in value){if(Object.hasOwnProperty.call(value,k)){v=walk(value,k);if(v!==undefined){value[k]=v;}else{delete value[k];}}}}
return reviver.call(holder,key,value);}
cx.lastIndex=0;if(cx.test(text)){text=text.replace(cx,function(a){return'\\u'+('0000'+
(+(a.charCodeAt(0))).toString(16)).slice(-4);});}
if(/^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,'@').replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,']').replace(/(?:^|:|,)(?:\s*\[)+/g,''))){j=eval('('+text+')');return typeof reviver==='function'?walk({'':j},''):j;}
throw new SyntaxError('JSON.parse');}};})(),getKeys:function(obj,hasOwnProp)
{var keys=[];for(var i in obj)
{if(!hasOwnProp||obj.hasOwnProperty(i))
{keys.push(i);}}
return keys;},getValues:function(obj,hasOwnProp)
{var values=[];for(var i in obj)
{if(!hasOwnProp||obj.hasOwnProperty(i))
{values.push(obj[i]);}}
return values;},copy:function(source,dest,hasOwnProp)
{if(Mojo.Util.isObject(source))
{for(var i in source)
{if(!hasOwnProp||source.hasOwnProperty(i))
{dest[i]=source[i];}}}
return dest;},getObject:function(json)
{if(Mojo.Util.isString(json))
return this.JSON.parse(json);else
return json;},getJSON:function(obj)
{return this.JSON.stringify(obj);},convertToType:function(value)
{if(value==null)
{return null;}
else if(Mojo.Util.isObject(value))
{if('enumType'in value&&'enumName'in value)
{return Mojo.Meta.findClass(value.enumType)[value.enumName];}
else if('dto_type'in value&&value.dto_type===Mojo.BUSINESS_PACKAGE+'ValueObjectDTO')
{return Mojo.Meta.newInstance(value.dto_type,value);}
else if('dto_type'in value&&(value.dto_type===Mojo.BUSINESS_PACKAGE+'WarningDTO'||value.dto_type===Mojo.BUSINESS_PACKAGE+'InformationDTO'))
{if(Mojo.Meta.classExists(value._type))
{return Mojo.Meta.newInstance(value._type,value);}
else
{return Mojo.Meta.newInstance(value.dto_type,value);}}
else if('_type'in value&&value._type!='')
{return Mojo.Meta.newInstance(value._type,value);}
else if('dto_type'in value&&value.dto_type!='')
{return Mojo.Meta.newInstance(value.dto_type,value);}
else
{return value;}}
else if(Mojo.Util.isArray(value))
{for(var i=0;i<value.length;i++)
{value[i]=Mojo.Util.convertToType(value[i]);}
return value;}
else
{return value;}},handleSuccess:function(clientRequest,responseText,isController)
{var obj=null;if(!isController)
{var json=Mojo.Util.getObject(responseText);obj=Mojo.Util.convertToType(json.returnValue);if(Mojo.Util.isArray(json.warnings)&&json.warnings.length>0)
{clientRequest.setWarnings(Mojo.Util.convertToType(json.warnings));}
if(Mojo.Util.isArray(json.information)&&json.information.length>0)
{clientRequest.setInformation(Mojo.Util.convertToType(json.information));}}
else
{obj=responseText;}
if(Mojo.Util.isFunction(clientRequest.onSuccess))
{clientRequest.onSuccess(obj);}},handleException:function(clientRequest,responseText)
{var e=null;try
{var obj=Mojo.Util.getObject(responseText);var exceptionType=null;if('dto_type'in obj&&obj.dto_type===Mojo.ROOT_PACKAGE+'MojoExceptionDTO')
{exceptionType=obj.wrappedException;e=Mojo.Util.convertToType(obj);}
else if('dto_type'in obj&&obj.dto_type===Mojo.ROOT_PACKAGE+'ProblemExceptionDTO')
{exceptionType=obj.dto_type;e=Mojo.Util.convertToType(obj);}
else if('_type'in obj)
{exceptionType=obj._type;if(Mojo.Meta.classExists(obj._type))
{e=Mojo.Util.convertToType(obj);}
else
{e=Mojo.Meta.newInstance(Mojo.ROOT_PACKAGE+'Exception',obj);}}
else
{e=Mojo.Meta.newInstance(Mojo.ROOT_PACKAGE+'Exception',obj)}
if(Mojo.Util.isString(exceptionType)&&exceptionType.length>0)
{var exNameInd=exceptionType.lastIndexOf('.');if(exNameInd!==-1)
{exceptionType=exceptionType.substr(exNameInd+1);}
var handlerName='on'+exceptionType;if(Mojo.Util.isFunction(clientRequest[handlerName]))
{clientRequest[handlerName](e);}
else if(Mojo.Util.isFunction(clientRequest.onFailure))
{clientRequest.onFailure(e);}}
else
{if(Mojo.Util.isFunction(clientRequest.onFailure))
{clientRequest.onFailure(e);}}}
catch(e1)
{if(Mojo.Util.isFunction(clientRequest.onFailure))
{var e=Mojo.Meta.newInstance(Mojo.ROOT_PACKAGE+'Exception',responseText);clientRequest.onFailure(e);}}},collectFormValues:function(formId)
{var keyValues={};function collect(elements)
{for(var i=0;i<elements.length;i++)
{var el=elements[i];if(el.disabled)
{continue;}
var name=el.name;var nodeName=el.nodeName.toLowerCase();switch(nodeName)
{case'select':var values=[];var options=el.options;for(var j=0;j<options.length;j++)
{var option=options[j];if(option.selected)
values.push(option.value);}
keyValues[name]=values;break;case'textarea':keyValues[name]=el.value;break;case'input':var type=el.type.toLowerCase();switch(type)
{case'radio':if(el.checked)
keyValues[name]=el.value;break;case'checkbox':if(!keyValues[name])
keyValues[name]=[];if(el.checked)
keyValues[name].push(el.value);break;default:keyValues[name]=el.value;}
break;}}}
var form=Mojo.Util.isString(formId)?document.getElementById(formId):formId;collect(form.getElementsByTagName('input'));collect(form.getElementsByTagName('select'));collect(form.getElementsByTagName('textarea'));return keyValues;},convertMapToQueryString:function(map)
{if(map==null)
{return'';}
var params=[];for(var key in map)
{var entry=map[key];if(Mojo.Util.isArray(entry))
{for(var i=0;i<entry.length;i++)
{params.push(encodeURIComponent(key)+"[]="+encodeURIComponent(entry[i]));}}
else
{params.push(encodeURIComponent(key)+"="+encodeURIComponent(entry));}}
var queryString=params.join("&");return queryString;}}});Mojo.Meta.newClass("Mojo.log.LogManager",{IsSingleton:true,Instance:{initialize:function()
{this._loggers=[];},_addLogger:function(logger)
{this._loggers.push(logger);},_getLoggers:function()
{return this._loggers;},_removeLogger:function(logger)
{}},Static:{addLogger:function(logger)
{Mojo.log.LogManager.getInstance()._addLogger(logger);},getLoggers:function()
{return Mojo.log.LogManager.getInstance()._getLoggers();},removeLogger:function(logger)
{Mojo.log.LogManager.getInstance()._removeLogger(logger);},writeInfo:function(msg)
{var loggers=Mojo.log.LogManager.getLoggers();Mojo.Iter.forEach(loggers,function(logger){logger.writeInfo(msg);});},writeWarning:function(msg)
{var loggers=Mojo.log.LogManager.getLoggers();Mojo.Iter.forEach(loggers,function(logger){logger.writeWarning(msg);});},writeError:function(msg,error)
{var loggers=Mojo.log.LogManager.getLoggers();Mojo.Iter.forEach(loggers,function(logger){logger.writeError(msg,error);});},}});Mojo.Meta.newClass("Mojo.log.Logger",{IsAbstract:true,Constants:{INFO:"INFO",WARNING:"WARNING",ERROR:"ERROR"},Instance:{initialize:function()
{},writeInfo:{IsAbstract:true},writeWarning:{IsAbstract:true},writeError:{IsAbstract:true},},});Mojo.Meta.newClass('Mojo.Iter',{Instance:{initialize:function()
{}},Static:{filter:function(obj,func,context)
{var bound=Mojo.Util.bind((context||this),func);var filtered=[];Mojo.Iter.forEach(obj,function(item,ind){if(bound(item,ind))
{filtered.push(item);}});return filtered;},forEach:function(obj,func,context)
{var bound=Mojo.Util.bind((context||this),func);if(Mojo.Util.isNumber(obj))
{for(var i=0;i<obj;i++)
{bound(i);}}
else if(Mojo.Util.isArray(obj))
{for(var i=0;i<obj.length;i++)
{var item=obj[i];bound(item,i);}}
else if(Mojo.Util.isObject(obj))
{var keys=Mojo.Util.getKeys(obj);for(var i=0;i<keys.length;i++)
{var key=keys[i];bound(obj[key],key);}}
else if('length'in obj)
{for(var i=0;i<obj.length;i++)
{bound(obj[i],i);}}
else
{throw Error('The object cannot be iterated over.');}},map:function(obj,func,context)
{var bound=Mojo.Util.bind((context||this),func);var mapped=[];Mojo.Iter.forEach(obj,function(item,ind){mapped.push(bound(item,ind));});return mapped;}}});Mojo.Meta.newClass('Mojo.aspect.Advice',{IsAbstract:true,Constants:{MATCH_INSTANCE:1,MATCH_STATIC:2,MATCH_ALL:3},Instance:{initialize:function(classP,methodP,method,matchOn)
{this._method=method;this._matchOn=matchOn||Mojo.aspect.Advice.MATCH_ALL;this._classRE=classP;this._methodRE=methodP;},weave:function()
{var matched=[];var classes=Mojo.Meta.getClasses();for(var i=0;i<classes.length;i++)
{var className=classes[i];var klass=Mojo.Meta.findClass(className);if(this._classRE.test(className))
{var methods;switch(this._matchOn)
{case Mojo.aspect.Advice.MATCH_ALL:methods=klass.getMetaClass().getMethods();break;case Mojo.aspect.Advice.MATCH_INSTANCE:methods=klass.getMetaClass().getInstanceMethods();break;case Mojo.aspect.Advice.MATCH_STATIC:methods=klass.getMetaClass().getStaticMethods();break;default:methods=[];}
for(var j=0;j<methods.length;j++)
{var method=methods[j];if(this._methodRE.test(method.getName()))
{this._doWeave(klass,method);matched.push(method);method.addAspect(this);}}}}
return matched;},execute:{IsAbstract:true},toString:function()
{var str='';str+='['+this.getMetaClass().getName()+'] ';var matchesOn;if(this._matchOn===this.constructor.MATCH_INSTANCE)
{matchesOn='Instance';}
else if(this._matchOn===this.constructor.MATCH_STATIC)
{matchesOn='Static';}
else
{matchesOn='Instance/Static';}
str+=' Matches on ['+matchesOn+'] with class(es) ['+this._classRE+'] and method(s) ['+this._methodRE+']';return str;}}});Mojo.Meta.newClass('Mojo.aspect.BeforeAdvice',{Extends:Mojo.aspect.Advice,Instance:{initialize:function(classP,methodP,method,matchOn)
{this.$initialize(classP,methodP,method,matchOn);},_doWeave:function(klass,wrappedMethod)
{var source=wrappedMethod.isStatic()?klass:klass.prototype;var original=source[wrappedMethod.getName()];source[wrappedMethod.getName()]=(function(k,m,b,o){return function(){var args=[].splice.call(arguments,0);var context=(m.isStatic()?Mojo.GLOBAL:this);b.execute(context,args,k,m);return o.apply(context,args);};})(klass,wrappedMethod,this,original);},execute:function(context,args,klass,method)
{this._method.call(context,args,klass,method);}}});Mojo.Meta.newClass('Mojo.aspect.AfterAdvice',{Extends:Mojo.aspect.Advice,Instance:{initialize:function(classP,methodP,method,matchOn)
{this.$initialize(classP,methodP,method,matchOn);},_doWeave:function(klass,wrappedMethod)
{var source=wrappedMethod.isStatic()?klass:klass.prototype;var original=source[wrappedMethod.getName()];source[wrappedMethod.getName()]=(function(k,m,a,o){return function(){var args=[].splice.call(arguments,0);var context=(m.isStatic()?Mojo.GLOBAL:this);var obj=o.apply(context,args);a.execute(context,args,obj,k,m);return obj;};})(klass,wrappedMethod,this,original);},execute:function(context,args,returnObj,klass,method)
{this._method.call(context,args,returnObj,klass,method);}}});Mojo.Meta.newClass('Mojo.aspect.AroundAdvice',{Extends:Mojo.aspect.Advice,Instance:{initialize:function(classP,methodP,method,matchOn)
{this.$initialize(classP,methodP,method,matchOn);},_doWeave:function(klass,wrappedMethod)
{var source=wrappedMethod.isStatic()?klass:klass.prototype;var original=source[wrappedMethod.getName()];source[wrappedMethod.getName()]=(function(k,m,a,o){return function(){var args=[].splice.call(arguments,0);var context=(m.isStatic()?Mojo.GLOBAL:this);var proceed=(function(oP,c){return function()
{return oP.apply(c,[].splice.call(arguments,0));}})(o,context);return a.execute(context,args,proceed,k,m);};})(klass,wrappedMethod,this,original);},execute:function(context,args,proceed,klass,method)
{return this._method.call(context,args,proceed,klass,method);}}});Mojo.Meta.newClass('Mojo.ClientRequest',{Instance:{initialize:function(handler){Mojo.Util.copy(handler,this);this._warnings=[];this._information=[];this._transport=null;},getMessages:function(){return this._warnings.concat(this._information);},setWarnings:function(warnings){this._warnings=warnings;},getWarnings:function(){return this._warnings;},setInformation:function(information){this._information=information;},getInformation:function(){return this._information;},getTransport:function(){return this._transport;},setTransport:function(transport){this._transport=transport;}}});Mojo.Meta.newClass('Mojo.ClientSession',{IsAbstract:true,Static:{getBaseEndpoint:function(){return Mojo.ClientSession._baseEndpoint;},setBaseEndpoint:function(baseEndpoint){Mojo.ClientSession._baseEndpoint=baseEndpoint;},getDefaultOptions:function(){return Mojo.Util.copy(Mojo.ClientSession._defaultOptions,{});},setDefaultOptions:function(defaultOptions){Mojo.Util.copy(defaultOptions,Mojo.ClientSession._defaultOptions);},_baseEndpoint:(Mojo.GLOBAL.location.protocol+"//"+Mojo.GLOBAL.location.host+'/'+Mojo.GLOBAL.location.pathname.split('/')[1]+'/'),_defaultOptions:{'method':'post','contentType':'application/x-www-form-urlencoded','encoding':'UTF-8','asynchronous':true,'successRange':[200,299]}}});Mojo.Meta.newClass('Mojo.AjaxCall',{Instance:{initialize:function(endpoint,clientRequest,parameters,isController)
{this.clientRequest=clientRequest;this.isController=isController||false;this.cleanup=function()
{this.clientRequest=null;this.xmlHttp=null;this.isController=null;this.options=null;this.readyStateChange=null;this.cleanup=null;};this.options={};Mojo.Util.copy(Mojo.ClientSession.getDefaultOptions(),this.options);Mojo.Util.copy(this.clientRequest.options,this.options);var paramStr='';if(Mojo.Util.isObject(parameters))
{var paramArray=[];for(var i in parameters)
{paramArray.push(encodeURIComponent(i)+'='+encodeURIComponent(parameters[i]));}
paramStr=paramArray.join('&');}
else
{paramStr=parameters.toString();}
this.xmlHttp=null;try
{this.xmlHttp=new XMLHttpRequest();}
catch(e)
{try
{this.xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");}
catch(e)
{try
{this.xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");}
catch(e)
{var message="The browser does not support Ajax";throw Mojo.Meta.newInstance(Mojo.ROOT_PACKAGE+'Exception',message);}}}
this.readyStateChange=function(){if(this.xmlHttp.readyState==4)
{if(Mojo.Util.isFunction(this.clientRequest.onComplete))
{this.clientRequest.onComplete();}
if(this.xmlHttp.status>=this.options.successRange[0]&&this.xmlHttp.status<=this.options.successRange[1])
{Mojo.Util.handleSuccess(this.clientRequest,this.xmlHttp.responseText,this.isController);}
else
{Mojo.Util.handleException(this.clientRequest,this.xmlHttp.responseText);}
this.cleanup();}};this.clientRequest.setTransport(this.xmlHttp);if(Mojo.Util.isFunction(this.clientRequest.onSend))
{this.clientRequest.onSend();}
var url=Mojo.ClientSession.getBaseEndpoint()+endpoint;var bound=Mojo.Util.bind(this,this.readyStateChange);if(this.options.method=='post')
{this.xmlHttp.open(this.options.method,url,this.options.asynchronous);this.xmlHttp.onreadystatechange=bound;this.xmlHttp.setRequestHeader("Content-type",this.options.contentType+"; charset="+this.options.encoding);this.xmlHttp.setRequestHeader("Content-length",paramStr.length);this.xmlHttp.setRequestHeader("Connection","close");this.xmlHttp.send(paramStr);}
else
{this.xmlHttp.open(this.options.method,url+"?"+paramStr,this.options.asynchronous);this.xmlHttp.onreadystatechange=bound;this.xmlHttp.send(null);}}}});Mojo.Meta.newClass('Mojo.Facade',{IsAbstract:true,Static:{importTypes:function(clientRequest,types,options)
{if(Mojo.Util.isString(types))
{types=[types];}
var json=Mojo.Util.getJSON(types);var params={'method':'importTypes','types':json};var onSuccessRef=clientRequest.onSuccess;var importCallback=function(jsSource)
{if(Mojo.Util.isObject(options))
{if(options.autoEval)
{eval(jsSource);}
else if('appendTo'in options)
{var script=document.createElement("script");script.type="text/javascript";try
{script.appendChild(document.createTextNode(jsSource));}
catch(e)
{script.text=jsSource;}
var appendTo=options.appendTo;var parentEl=Mojo.Util.isString(appendTo)?document.getElementById(appendTo):appendTo;parentEl.appendChild(script);}}
if(Mojo.Util.isFunction(onSuccessRef))
{onSuccessRef.call(clientRequest,jsSource);}};clientRequest.onSuccess=importCallback;new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},_methodWrapper:function(clientRequest,params)
{new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},_controllerWrapper:function(endpoint,clientRequest,params)
{if(Mojo.Util.isObject(params))
params={"com.terraframe.mojo.mojaxObject":Mojo.Util.getJSON(params)};new Mojo.AjaxCall(endpoint,clientRequest,params,true);},login:function(clientRequest,username,password)
{var params={'method':'login','username':username,'password':password};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},loginUser:function(clientRequest,username,password)
{var params={'method':'loginUser','username':username,'password':password};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},changeLogin:function(clientRequest,username,password)
{var params={'method':'changeLogin','username':username,'password':password};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},loginAnonymous:function(clientRequest)
{var params={'method':'loginAnonymous'};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},logout:function(clientRequest)
{var params={'method':'logout'};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},newBusiness:function(clientRequest,type)
{var params={'method':'newBusiness','type':type};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},createSessionComponent:function(clientRequest,sessionDTO)
{var json=Mojo.Util.getJSON(sessionDTO);var params={'method':'createSessionComponent','sessionDTO':json};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},createBusiness:function(clientRequest,businessDTO)
{var json=Mojo.Util.getJSON(businessDTO);var params={'method':'createBusiness','businessDTO':json};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},newStruct:function(clientRequest,type)
{var params={'method':'newStruct','type':type};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},newEntity:function(clientRequest,type)
{var params={'method':'newEntity','type':type};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},createStruct:function(clientRequest,structDTO)
{var json=Mojo.Util.getJSON(structDTO);var params={'method':'createStruct','structDTO':json};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},createRelationship:function(clientRequest,relationshipDTO)
{var json=Mojo.Util.getJSON(relationshipDTO);var params={'method':'createRelationship','relationshipDTO':json};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},checkAdminScreenAccess:function(clientRequest)
{var params={'method':'checkAdminScreenAccess'};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},update:function(clientRequest,mutableDTO)
{var json=Mojo.Util.getJSON(mutableDTO);var params={'method':'update','mutableDTO':json};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},deleteEntity:function(clientRequest,id)
{var params={'method':'delete','id':id};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},get:function(clientRequest,id)
{var params={'method':'get','id':id};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},getUser:function(clientRequest)
{var params={'method':'getUser'};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},getQuery:function(clientRequest,type)
{var params={'method':'getQuery','type':type}
new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},addChild:function(clientRequest,parentId,childId,relationshipType)
{var params={'method':'addChild','parentId':parentId,'childId':childId,'relationshipType':relationshipType};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},addParent:function(clientRequest,parentId,childId,relationshipType)
{var params={'method':'addParent','parentId':parentId,'childId':childId,'relationshipType':relationshipType};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},deleteChild:function(clientRequest,relationshipId)
{var params={'method':'deleteChild','relationshipId':relationshipId};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},deleteParent:function(clientRequest,relationshipId)
{var params={'method':'deleteParent','relationshipId':relationshipId};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},deleteChildren:function(clientRequest,parentId,relationshipType)
{var params={'method':'deleteChildren','parentId':parentId,'relationshipType':relationshipType};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},deleteParents:function(clientRequest,childId,relationshipType)
{var params={'method':'deleteParents','childId':childId,'relationshipType':relationshipType};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},getChildren:function(clientRequest,parentId,relationshipType)
{var params={'method':'getChildren','parentId':parentId,'relationshipType':relationshipType};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},getChildRelationships:function(clientRequest,parentId,relationshipType)
{var params={'method':'getChildRelationships','parentId':parentId,'relationshipType':relationshipType};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},getParentRelationships:function(clientRequest,childId,relationshipType)
{var params={'method':'getParentRelationships','childId':childId,'relationshipType':relationshipType};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},getParents:function(clientRequest,childId,relationshipType)
{var params={'method':'getParents','childId':childId,'relationshipType':relationshipType};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},lock:function(clientRequest,id)
{var params={'method':'lock','id':id};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},unlock:function(clientRequest,id)
{var params={'method':'unlock','id':id};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},grantTypePermission:function(clientRequest,actorId,mdTypeId,operationNames)
{if(!Mojo.Util.isArray(operationNames))
{operationNames=[operationNames];}
var operationNamesJSON=Mojo.Util.getJSON(operationNames);var params={'method':'grantTypePermission','actorId':actorId,'operationNames':operationNamesJSON,'mdTypeId':mdTypeId};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},grantStatePermission:function(clientRequest,actorId,stateId,operationNames)
{if(!Mojo.Util.isArray(operationNames))
{operationNames=[operationNames];}
var operationNamesJSON=Mojo.Util.getJSON(operationNames);var params={'method':'grantStatePermission','actorId':actorId,'operationNames':operationNamesJSON,'stateId':stateId};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},grantAttributePermission:function(clientRequest,actorId,mdAttributeId,operationNames)
{if(!Mojo.Util.isArray(operationNames))
{operationNames=[operationNames];}
var operationNamesJSON=Mojo.Util.getJSON(operationNames);var params={'method':'grantAttributePermission','actorId':actorId,'operationNames':operationNamesJSON,'mdAttributeId':mdAttributeId};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},grantAttributeStatePermission:function(clientRequest,actorId,mdAttributeId,stateId,operationNames)
{if(!Mojo.Util.isArray(operationNames))
{operationNames=[operationNames];}
var operationNamesJSON=Mojo.Util.getJSON(operationNames);var params={'method':'grantAttributeStatePermission','actorId':actorId,'operationNames':operationNamesJSON,'mdAttributeId':mdAttributeId,'stateId':stateId};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},promoteObject:function(callback,businessJSON,transitionName)
{var params={'method':'promoteObject','businessDTO':businessJSON,'transitionName':transitionName};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},revokeTypePermission:function(clientRequest,actorId,mdTypeId,operationNames)
{if(!Mojo.Util.isArray(operationNames))
{operationNames=[operationNames];}
var operationNamesJSON=Mojo.Util.getJSON(operationNames);var params={'method':'revokeTypePermission','actorId':actorId,'operationNames':operationNamesJSON,'mdTypeId':mdTypeId};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},revokeStatePermission:function(clientRequest,actorId,stateId,operationNames)
{if(!Mojo.Util.isArray(operationNames))
{operationNames=[operationNames];}
var operationNamesJSON=Mojo.Util.getJSON(operationNames);var params={'method':'revokeStatePermission','actorId':actorId,'operationNames':operationNamesJSON,'stateId':stateId};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},revokeAttributePermission:function(clientRequest,actorId,mdAttributeId,operationNames)
{if(!Mojo.Util.isArray(operationNames))
{operationNames=[operationNames];}
var operationNamesJSON=Mojo.Util.getJSON(operationNames);var params={'method':'revokeAttributePermission','actorId':actorId,'operationNames':operationNamesJSON,'mdAttributeId':mdAttributeId};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},revokeAttributeStatePermission:function(clientRequest,actorId,mdAttributeId,stateId,operationNames)
{if(!Mojo.Util.isArray(operationNames))
{operationNames=[operationNames];}
var operationNamesJSON=Mojo.Util.getJSON(operationNames);var params={'method':'revokeAttributeStatePermission','actorId':actorId,'operationNames':operationNamesJSON,'mdAttributeId':mdAttributeId,'stateId':stateId};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},invokeMethod:function(clientRequest,metadata,mutableDTO,parameters)
{var mutableDTOJSON=Mojo.Util.getJSON(mutableDTO);var metadataJSON=Mojo.Util.getJSON(metadata);var parametersJSON=Mojo.Util.getJSON(parameters);var params={'method':'invokeMethod','mutableDTO':mutableDTOJSON,'metadata':metadataJSON,'parameters':parametersJSON};var onSuccessRef=clientRequest.onSuccess;var invokeCallback=function(objArray)
{var returnObject=objArray[0];var calledObject=objArray[1];if(Mojo.Util.isFunction(onSuccessRef))
onSuccessRef.call(clientRequest,returnObject,calledObject);};clientRequest.onSuccess=invokeCallback;new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},getEnumeration:function(clientRequest,enumType,enumName)
{var params={'method':'getEnumeration','enumType':enumType,'enumName':enumName};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},getEnumerations:function(clientRequest,enumType,enumNames)
{var enumNamesJSON=Mojo.Util.getJSON(enumNames);var params={'method':'getEnumerations','enumType':enumType,'enumNames':enumNamesJSON};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},getAllEnumerations:function(clientRequest,enumType)
{var params={'method':'getAllEnumerations','enumType':enumType};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},queryBusinesses:function(clientRequest,queryDTO)
{queryDTO.clearAttributes();var json=Mojo.Util.getJSON(queryDTO);var params={'method':'queryBusinesses','queryDTO':json};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},groovyValueQuery:function(clientRequest,queryDTO)
{queryDTO.clearAttributes();queryDTO.clearResultSet();var json=Mojo.Util.getJSON(queryDTO);var params={'method':'groovyValueQuery','queryDTO':json};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},queryStructs:function(clientRequest,queryDTO)
{queryDTO.clearAttributes();queryDTO.clearResultSet();var json=Mojo.Util.getJSON(queryDTO);var params={'method':'queryStructs','queryDTO':json};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},queryEntities:function(clientRequest,queryDTO)
{queryDTO.clearAttributes();queryDTO.clearResultSet();var json=Mojo.Util.getJSON(queryDTO);var params={'method':'queryEntities','queryDTO':json};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);},queryRelationships:function(clientRequest,queryDTO)
{queryDTO.clearAttributes();queryDTO.clearResultSet();var json=Mojo.Util.getJSON(queryDTO);var params={'method':'queryRelationships','queryDTO':json};new Mojo.AjaxCall(Mojo.JSON_ENDPOINT,clientRequest,params);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ComponentQueryDTO',{Alias:Mojo.$,IsAbstract:true,Instance:{initialize:function(obj)
{this.dto_type=obj.dto_type;this.queryType=obj.queryType;this.pageSize=obj.pageSize;this.pageNumber=obj.pageNumber;this.count=obj.count;this.countEnabled=obj.countEnabled;this.groovyQuery=obj.groovyQuery;for(var attributeName in obj.definedAttributes)
{var attribute=obj.definedAttributes[attributeName];var attributeDTO=Mojo.Meta.newInstance(attribute.dtoType,attribute);obj.definedAttributes[attributeName]=attributeDTO;}
this.definedAttributes=obj.definedAttributes;this.resultSet=[];for(var i=0;i<obj.resultSet.length;i++)
{var result=obj.resultSet[i];this.resultSet.push(Mojo.Util.convertToType(result));}},getType:function()
{return this.queryType;},setPageNumber:function(pageNumber)
{this.pageNumber=pageNumber;},setPageSize:function(pageSize)
{this.pageSize=pageSize;},getPageNumber:function()
{return this.pageNumber;},getPageSize:function()
{return this.pageSize;},getAttributeDTO:function(attrName)
{return this.definedAttributes[attrName];},getAttributeNames:function()
{return Mojo.Util.getKeys(this.definedAttributes);},clearAttributes:function()
{this.definedAttributes={};},getResultSet:function()
{return this.resultSet;},clearResultSet:function()
{this.resultSet=[];},getCount:function(){return this.count;},setCountEnabled:function(countEnabled){this.countEnabled=countEnabled;},isCountEnabled:function(){return this.countEnabled;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ValueQueryDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'ComponentQueryDTO',Instance:{initialize:function(obj)
{if(typeof obj==='string')
{this.type='';this.resultSet=[];this.definedAttributes={};this.groovyQuery=obj;this.pageSize=0;this.pageNumber=0;this.count=0;this.countEnabled=false;this.dto_type=Mojo.BUSINESS_PACKAGE+'ValueQueryDTO';}
else
{this.$initialize(obj);}},getGroovyQuery:function(){return this.groovyQuery;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ClassQueryDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.BUSINESS_PACKAGE+'ComponentQueryDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.classes={};},getClassTypes:function()
{return Mojo.Util.getKeys(this.classes);},getSuperClasses:function(classType)
{return this.classes[classType];}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'EntityQueryDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.BUSINESS_PACKAGE+'ClassQueryDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.conditions={};for(var i=0;i<obj.conditions.length;i++)
{var conditionJSON=obj.conditions[i];var attribute=conditionJSON.attribute;var condition=conditionJSON.condition;var value=conditionJSON.value;var key=attribute+condition+value;var klass=Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'Condition');this.conditions[key]=new klass(attribute,condition,value);}
this.orderByList=[];for(var i=0;i<obj.orderByList.length;i++)
{var orderByJSON=obj.orderByList[i];var orderBy;if(orderByJSON.length==3)
{var attributeStruct=orderByJSON.attributeStruct;var attribute=orderByJSON.attribute;var order=orderByJSON.order;var klass=Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'StructOrderBy');orderBy=new klass(attributeStruct,attribute,order);}
else
{var attribute=orderByJSON.attribute;var order=orderByJSON.order;var klass=Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'OrderBy');orderBy=new klass(attributeStruct,attribute,order);}
this.orderByList.push(orderBy);}},addCondition:function(attribute,condition,value)
{var key=attribute+condition+value;var klass=Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'Condition');var conditionObj=new klass(attribute,condition,value);this.conditions[key]=conditionObj;},clearConditions:function()
{this.conditions={};},getConditions:function()
{return Mojo.Util.getValues(this.conditions);},addOrderBy:function(attribute,order)
{var klass=Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'OrderBy');var orderBy=new klass(attribute,order);this.orderByList.push(orderBy);},getOrderByList:function()
{return this.orderByList;},clearOrderByList:function()
{this.orderByList.clear={};}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'OrderBy',{Alias:Mojo.$,Instance:{initialize:function(attribute,order)
{this.attribute=attribute;this.order=order;},getAttribute:function(){return this.attribute;},getOrder:function(){return this.order;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'Condition',{Alias:Mojo.$,Instance:{initialize:function(attribute,condition,value)
{this.attribute=attribute;this.condition=condition;this.value=value;},getAttribute:function(){return this.attribute;},getCondition:function(){return this.condition;},getValue:function(){return this.value;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ElementQueryDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.BUSINESS_PACKAGE+'EntityQueryDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this._isAbstract=obj._isAbstract;},isAbstract:function(){return this._isAbstract;},addStructOrderBy:function(structAttribute,attribute,order)
{var klass=Mojo.Meta.findClass(Mojo.BUSINESS_PACKAGE+'StructOrderBy');var orderBy=new klass(structAttribute,attribute,order);this.orderByList.push(orderBy);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'StructOrderBy',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'OrderBy',Instance:{initialize:function(attributeStruct,attribute,order)
{this.$initialize(attribute,order);this.attributeStruct=attributeStruct;},getAttributeStruct:function(){return this.attributeStruct;},getAttribute:function(){return this.attribute;},getOrder:function(){return this.order;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'StructQueryDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'EntityQueryDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'BusinessQueryDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'ElementQueryDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.typeInMdRelationshipAsChildList=[];this.typeInMdRelationshipAsParentList=[];for(var i=0;i<obj.typeInMdRelationshipAsChildList.length;i++)
{var asChild=obj.typeInMdRelationshipAsChildList[i];var asChildObj=Mojo.Meta.newInstance(Mojo.BUSINESS_PACKAGE+'TypeInMdRelationshipAsChild',asChild);this.typeInMdRelationshipAsChildList.push(asChildObj);}
for(var i=0;i<obj.typeInMdRelationshipAsParentList.length;i++)
{var asParent=obj.typeInMdRelationshipAsParentList[i];var asParentObj=Mojo.Meta.newInstance(Mojo.BUSINESS_PACKAGE+'TypeInMdRelationshipAsParent',asParent);this.typeInMdRelationshipAsParentList.push(asParentObj);}},getTypeInMdRelationshipAsChildList:function(){return this.typeInMdRelationshipAsChildList;},getTypeInMdRelationshipAsParentList:function(){return this.typeInMdRelationshipAsParentList;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'TypeInMdRelationshipAsChild',{Alias:Mojo.$,Instance:{initialize:function(obj)
{this.childDisplayLabel=obj.childDisplayLabel;this.relationshipType=obj.relationshipType;},getChildDisplayLabel:function(){return this.childDisplayLabel;},getRelationshipType:function(){return this.relationshipType;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'TypeInMdRelationshipAsParent',{Alias:Mojo.$,Instance:{initialize:function(obj)
{this.parentDisplayLabel=obj.parentDisplayLabel;this.relationshipType=obj.relationshipType;},getParentDisplayLabel:function(){return this.parentDisplayLabel;},getRelationshipType:function(){return this.relationshipType;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'RelationshipQueryDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'ElementQueryDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.parentMdBusiness=obj.parentMdBusiness;this.childMdBusiness=obj.childMdBusiness;},getParentMdBusiness:function(){return this.parentMdBusiness;},getChildMdBusiness:function(){return this.childMdBusiness;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ViewQueryDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'ComponentQueryDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ComponentDTO',{Alias:Mojo.$,IsAbstract:true,Instance:{initialize:function(obj)
{this.dto_type=obj.dto_type;this.id=obj.id;this._type=obj._type;if(Mojo.Util.isObject(obj._typeMd))
{this._typeMd=Mojo.Meta.newInstance(Mojo.MD_DTO_PACKAGE+'TypeMd',obj._typeMd);}
else
{this._typeMd=null;}
this._toString=obj._toString;this.readable=obj.readable;for(var attributeName in obj.attributeMap)
{var attribute=obj.attributeMap[attributeName];var attributeDTO=Mojo.Meta.newInstance(attribute.dtoType,attribute);obj.attributeMap[attributeName]=attributeDTO;}
this.attributeMap=obj.attributeMap;},getType:function(){return this._type;},getTypeMd:function(){return this._typeMd;},getId:function(){return this.id;},getIdMd:function(){return this.getAttributeDTO('id').getAttributeMdDTO()},getAttributeDTO:function(attributeName)
{return this.attributeMap[attributeName];},getMd:function(){return this._typeMd;},toString:function(){return this._toString;},isReadable:function(){return this.readable;},setValue:function(attributeName,value){this.getAttributeDTO(attributeName).setValue(value);},getValue:function(attributeName){return this.getAttributeDTO(attributeName).getValue();}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'MutableDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.BUSINESS_PACKAGE+'ComponentDTO',Instance:{initialize:function(obj)
{if(obj.newInstance)
{obj.id=Mojo.Util.generateId()+obj.id.substring(32);}
this.$initialize(obj);this.writable=obj.writable;this.modified=obj.modified;this.newInstance=obj.newInstance;},isWritable:function(){return this.writable;},setModified:function(modified){this.modified=modified;},isModified:function(){return this.modified;},isNewInstance:function(){return this.newInstance;}},Static:{get:function(clientRequest,id)
{Mojo.Facade.get(clientRequest,id);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ValueObjectDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'ComponentDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'TransientDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.BUSINESS_PACKAGE+'MutableDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'LocalizableDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.BUSINESS_PACKAGE+'TransientDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ExceptionDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'LocalizableDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'NotificationDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'LocalizableDTO',IsAbstract:true,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'MessageDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'NotificationDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.localizedMessage=obj.localizedMessage;},getMessage:function(){return this.localizedMessage;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'InformationDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'MessageDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'WarningDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'MessageDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ProblemDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'NotificationDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.localizedMessage=obj.localizedMessage;this.developerMessage=obj.developerMessage;},getLocalizedMessage:function(){return this.localizedMessage;},getMessage:function(){return this.localizedMessage;},getDeveloperMessage:function(){return this.developerMessage;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'MojoProblemDTO',{Alias:Mojo.$,Instance:{initialize:function(obj)
{this.localizedMessage=obj.localizedMessage;this.developerMessage=obj.developerMessage;},getLocalizedMessage:function(){return this.localizedMessage;},getMessage:function(){return this.localizedMessage;},getDeveloperMessage:function(){return this.developerMessage;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'MojoProblemDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.componentId=obj.componentId;this.definingType=obj.definingType;this.definingTypeDisplayLabel=obj.definingTypeDisplayLabel;this.attributeName=obj.attributeName;this.attributeDisplayLabel=obj.attributeDisplayLabel;},getComponentId:function(){return this.componentId;},getDefiningType:function(){return this.definingType;},getDefiningTypeDisplayLabel:function(){return this.definingTypeDisplayLabel;},getAttributeName:function(){return this.attributeName;},getAttributeDisplayLabel:function(){return this.attributeDisplayLabel;}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_PROBLEM_PACKAGE+'EmptyValueProblemDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_PROBLEM_PACKAGE+'ImmutableAttributeProblemDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_PROBLEM_PACKAGE+'SystemAttributeProblemDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'Exception',{Alias:Mojo.$,Extends:Mojo.ROOT_PACKAGE+'Base',Instance:{initialize:function()
{if(arguments.length==1)
{var arg=arguments[0];if(arg==null)
{this.localizedMessage=null;this.developerMessage=null;}
else if(Mojo.Util.isString(arg))
{this.localizedMessage=arg;this.developerMessage=null;}
else if(Mojo.Util.isObject(arg)&&'localizedMessage'in arg&&'developerMessage'in arg)
{this.localizedMessage=arg.localizedMessage;this.developerMessage=arg.developerMessage;}
else
{this.localizedMessage=null;this.developerMessage=null;}}
else if(arguments.length===2)
{this.localizedMessage=arguments[0];this.developerMessage=arguments[1];}
else
{this.localizedMessage=null;this.developerMessage=null;}},getLocalizedMessage:function(){return this.localizedMessage;},getMessage:function(){return this.localizedMessage;},getDeveloperMessage:function(){return this.developerMessage;},toString:function(){return this.localizedMessage;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'SmartExceptionDTO',{Alias:Mojo.$,Extends:Mojo.ROOT_PACKAGE+'Exception',Instance:{initialize:function(obj)
{this.$initialize(obj);this.ex=Mojo.Meta.newInstance(Mojo.BUSINESS_PACKAGE+'ExceptionDTO',obj);},getAttributeDTO:function(attributeName){return this.ex.getAttributeDTO(attributeName);},getId:function(){return this.ex.getId();},getIdMd:function(){return this.ex.getIdMd();},getMd:function(){return this.ex.getMd();},getType:function(){return this.ex.getType();},getTypeMd:function(){return this.ex.getTypeMd();},isModified:function(){return this.ex.isModified();},isNewInstance:function(){return this.ex.isNewInstance();},isReadable:function(){return this.ex.isReadable();},isWritable:function(){return this.ex.isWritable();},setModified:function(modified){return this.ex.setModified(modified);}}});Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'MojoExceptionDTO',{Alias:Mojo.$,Extends:Mojo.ROOT_PACKAGE+'Exception',Instance:{initialize:function(obj)
{this.$initialize(obj);if(Mojo.Util.isString(obj.wrappedException))
{this.wrappedException=obj.wrappedException;}
else
{this.wrappedException=null;}},getWrappedException:function(){return this.wrappedException;}}});Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'ProblemExceptionDTO',{Alias:Mojo.$,Extends:Mojo.ROOT_PACKAGE+'Exception',Instance:{initialize:function(obj)
{this.$initialize(obj);this.problemList=[];for(var i=0;i<obj.problemList.length;i++)
{var problemJSON=obj.problemList[i];var problem=null;if('_type'in problemJSON&&Mojo.Meta.classExists(problemJSON._type))
{problem=Mojo.Meta.newInstance(problemJSON._type,problemJSON);}
else if('dto_type'in problemJSON&&problemJSON.dto_type===Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO')
{problem=Mojo.Meta.newInstance(Mojo.BUSINESS_PACKAGE+'AttributeProblemDTO',problemJSON);}
else
{problem=Mojo.Meta.newInstance(Mojo.BUSINESS_PACKAGE+'ProblemDTO',problemJSON);}
this.problemList[i]=problem;}},getProblems:function()
{return this.problemList;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'SessionDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.BUSINESS_PACKAGE+'TransientDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);},apply:function(clientRequest)
{if(this.isWritable())
{if(this.isNewInstance())
Mojo.Facade.createSessionComponent(clientRequest,this);else
Mojo.Facade.update(clientRequest,this);}}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'UtilDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'SessionDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ViewDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'SessionDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'EntityDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.BUSINESS_PACKAGE+'MutableDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);},remove:function(clientRequest)
{Mojo.Facade.deleteEntity(clientRequest,this.getId());}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'ElementDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.BUSINESS_PACKAGE+'EntityDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.lockedByCurrentUser=obj.lockedByCurrentUser;},lock:function(clientRequest)
{Mojo.Facade.lock(clientRequest,this.getId());},unlock:function(clientRequest)
{Mojo.Facade.unlock(clientRequest,this.getId());},isLockedByCurrentUser:function(){return this.lockedByCurrentUser;}},Static:{lock:function(clientRequest,id)
{Mojo.Facade.lock(clientRequest,id);},unlock:function(clientRequest,id)
{Mojo.Facade.unlock(clientRequest,id);}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'BusinessDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'ElementDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.state=obj.state;this.transitions=obj.transitions;},getState:function(){return this.state;},getTransitions:function(){return this.transitions;},apply:function(clientRequest)
{if(this.isWritable())
{if(this.isNewInstance())
Mojo.Facade.createBusiness(clientRequest,this);else
Mojo.Facade.update(clientRequest,this);}}}});Mojo.Meta.newClass(Mojo.ROOT_PACKAGE+'EnumerationDTO',{Alias:Mojo.$,IsAbstract:true,Instance:{initialize:function(obj)
{this.dto_type=obj.dto_type;this.enumType=obj.enumType;this._name=obj._name;this.displayLabel=obj.displayLabel;},name:function(){return this._name;},getDisplayLabel:function(){return this.displayLabel;}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'RelationshipDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'ElementDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this._typeMd=Mojo.Meta.newInstance(Mojo.MD_DTO_PACKAGE+'RelationshipMd',obj._relationshipMd);this.parentId=obj.parentId;this.childId=obj.childId;},getParentId:function(){return this.parentId;},getChildId:function(){return this.childId;},apply:function(clientRequest)
{if(this.isWritable())
{if(this.isNewInstance())
Mojo.Facade.createRelationship(clientRequest,this);else
Mojo.Facade.update(clientRequest,this);}},getParent:function(clientRequest)
{Mojo.Facade.get(clientRequest,this.getParentId());},getChild:function(clientRequest)
{Mojo.Facade.get(clientRequest,this.getChildId());}}});Mojo.Meta.newClass(Mojo.BUSINESS_PACKAGE+'StructDTO',{Alias:Mojo.$,Extends:Mojo.BUSINESS_PACKAGE+'EntityDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);},apply:function(clientRequest)
{if(this.isWritable())
{if(this.isNewInstance())
Mojo.Facade.createStruct(clientRequest,this);else
Mojo.Facade.update(clientRequest,this);}}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',{Alias:Mojo.$,IsAbstract:true,Instance:{initialize:function(obj)
{this.attributeName=obj.attributeName;this.type=obj.type;this.dtoType=obj.dtoType;this.value=obj.value;this.readable=obj.readable;this.writable=obj.writable;this.modified=obj.modified;var mdDtoType=this.getMetaClass().getName().replace('DTO','MdDTO');var mdKlass=Mojo.Meta.findClass(Mojo.MD_DTO_PACKAGE+mdDtoType);if(!mdKlass)
{mdKlass=Mojo.Meta.findClass("com.terraframe.mojo.gis.transport.metadata."+mdDtoType);}
this.attributeMdDTO=new mdKlass(obj.attributeMdDTO);},getName:function(){return this.attributeName;},getValue:function(){return this.value;},setValue:function(value)
{if(this.isWritable())
{this.value=value!=null?value:null;this.setModified(true);}},isReadable:function(){return this.readable;},isWritable:function(){return this.writable;},isModified:function(){return this.modified;},setModified:function(modified){this.modified=modified;},getAttributeMdDTO:function(){return this.attributeMdDTO;}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',{Alias:Mojo.$,IsAbstract:true,Instance:{initialize:function(obj)
{this.displayLabel=obj.displayLabel;this.description=obj.description;this.required=obj.required;this.immutable=obj.immutable;this.accessorName=obj.accessorName;this.id=obj.id;this.system=obj.system;this.name=obj.name;},getDisplayLabel:function(){return this.displayLabel;},getDescription:function(){return this.description;},isRequired:function(){return this.required;},isImmutable:function(){return this.immutable;},getId:function(){return this.id;},isSystem:function(){return this.system;},getName:function(){return this.name;},getAccessorName:function(){return this.accessorName;}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeNumberDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeNumberMdDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this._rejectZero=obj._rejectZero;this._rejectNegative=obj._rejectNegative;this._rejectPositive=obj._rejectPositive;},rejectZero:function(){return this._rejectZero;},rejectNegative:function(){return this._rejectNegative;},rejectPositive:function(){return this._rejectPositive;}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeIntegerDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeNumberDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeIntegerMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeNumberMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeLongDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeNumberDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeLongMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeNumberMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeNumberDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDecMdDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.MD_DTO_PACKAGE+'AttributeNumberMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.totalLength=obj.totalLength;this.decimalLength=obj.decimalLength;},getTotalLength:function(){return this.totalLength;},getDecimalLength:function(){return this.decimalLength;}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecimalDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDecimalMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeDecMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDoubleDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDoubleMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeDecMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeFloatDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDecDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeFloatMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeDecMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeTextDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeTextMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeCharacterDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeCharacterMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.size=obj.size;},getSize:function(){return this.size;}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeBooleanDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeBooleanMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this._positiveDisplayLabel=obj.positiveDisplayLabel;this._negativeDisplayLabel=obj.negativeDisplayLabel;},getPositiveDisplayLabel:function(){return this._positiveDisplayLabel;},getNegativeDisplayLabel:function(){return this._negativeDisplayLabel;}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeStructDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.structDTO=obj.structDTO;},getStructDTO:function()
{return this.structDTO;},setStructDTO:function(structDTO)
{this.structDTO=structDTO;}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeStructMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.definingMdStruct=obj.definingMdStruct;},getDefiningMdStruct:function(){return this.definingMdStruct;}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeMomentDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);if(this.value!=null&&this.value!=='')
{var date=new Date();Mojo.Util.setISO8601(date,this.value);this.value=date;}
else
{this.value=null;}},setValue:function(value)
{if(this.isWritable())
{if(value!=null)
{if(Mojo.Util.isDate(value))
{this.value=value;}
else
{var date=new Date();Mojo.Util.setISO8601(date,value);this.value=date;}}
else
{this.value=null;}
this.setModified(true);}}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeMomentMdDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDateTimeDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeMomentDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDateTimeMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMomentMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDateDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeMomentDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeDateMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMomentMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeTimeDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeMomentDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeTimeMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMomentMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeReferenceDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeReferenceMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.referencedMdBusiness=obj.referencedMdBusiness;},getReferencedMdBusiness:function(){return this.referencedMdBusiness;}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeEnumerationDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.enumNames={};for(var i=0;i<obj.enumNames.length;i++)
{var enumName=obj.enumNames[i];this.enumNames[enumName]=enumName;}},add:function(item)
{if(this.isWritable())
{if(!this.getAttributeMdDTO().selectMultiple())
{this.clear();}
var enumName=Mojo.Util.isObject(item)?item.name():item;this.enumNames[enumName]=enumName;}},remove:function(item)
{if(this.isWritable())
{var enumName=Mojo.Util.isObject(item)?item.name():item;delete this.enumNames[enumName];}},clear:function()
{if(this.isWritable())
{this.enumNames={};}},getEnumNames:function()
{return Mojo.Util.getKeys(this.enumNames);},getEnumValues:function(clientRequest)
{var enumType=this.getAttributeMdDTO().getReferencedMdEnumeration();var names=Mojo.Util.getKeys(this.enumNames);Mojo.ClientSession.getEnumerations(clientRequest,enumType,names);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeEnumerationMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this._selectMultiple=obj._selectMultiple;this.referencedMdEnumeration=obj.referencedMdEnumeration;this.enumNames={};Mojo.Util.copy(obj.enumNames,this.enumNames);},getReferencedMdEnumeration:function(){return this.referencedMdEnumeration;},selectMultiple:function(){return this._selectMultiple;},getEnumNames:function()
{return Mojo.Util.getKeys(this.enumNames);},getEnumLabels:function()
{return Mojo.Util.getValues(this.enumNames);},getEnumDisplayLabel:function(enumName)
{return this.enumNames[enumName];},getEnumItems:function()
{var copy={};Mojo.Util.copy(this.enumNames,cop);return copy;}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeEncryptionDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeEncryptionMdDTO',{Alias:Mojo.$,IsAbstract:true,Extends:Mojo.MD_DTO_PACKAGE+'AttributeMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);this.encryptionMethod=obj.encryptionMethod;},getEncryptionMethod:function(){return this.encryptionMethod;}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeHashDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeEncryptionDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeHashMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeEncryptionMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeSymmetricDTO',{Alias:Mojo.$,Extends:Mojo.ATTRIBUTE_DTO_PACKAGE+'AttributeEncryptionDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'AttributeSymmetricMdDTO',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'AttributeEncryptionMdDTO',Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'TypeMd',{Alias:Mojo.$,Instance:{initialize:function(obj)
{this.displayLabel=obj.displayLabel;this.description=obj.description;this.id=obj.id;},getDisplayLabel:function(){return this.displayLabel;},getDescription:function(){return this.description;},getId:function(){return this.id;}}});Mojo.Meta.newClass(Mojo.MD_DTO_PACKAGE+'RelationshipMd',{Alias:Mojo.$,Extends:Mojo.MD_DTO_PACKAGE+'TypeMd',Instance:{initialize:function(obj)
{this.$initialize(obj);this.parentMdBusiness=obj.parentMdBusiness;this.childMdBusiness=obj.childMdBusiness;},getParentMdBusiness:function(){return this.parentMdBusiness;},getChildMdBusiness:function(){return this.childMdBusiness;}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO',{Extends:Mojo.$.com.terraframe.mojo.transport.attributes.AttributeDTO,Abstract:true,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO',{Extends:Mojo.$.com.terraframe.mojo.transport.metadata.AttributeMdDTO,Abstract:true,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributeLineStringDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributeLineStringMdDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributePointDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributePointMdDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributePolygonDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributePolygonMdDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributeMultiLineStringDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributeMultiLineStringMdDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributeMultiPointDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributeMultiPointMdDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.attributes.AttributeMultiPolygonDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.attributes.AttributeGeometryDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.gis.transport.metadata.AttributeMultiPolygonMdDTO',{Extends:Mojo.$.com.terraframe.mojo.gis.transport.metadata.AttributeGeometryMdDTO,Alias:Mojo.$,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.Inspector',{Alias:Mojo.$,IsSingleton:true,Instance:{initialize:function()
{var prefix=this.getMetaClass().getQualifiedName()+'_';this._mainWindowId=prefix+'mainWindow';this._mainMinId=prefix+'mainMin';this._mainExitId=prefix+'mainExit';this._mainDrag=prefix+'mainDrag';this._secWindowId=prefix+'secWindow';this._secMaxId=prefix+'secMax';this._secExitId=prefix+'secExit';this._secDrag=prefix+'secDrag';this._explorerTab=prefix+'explorerTab';this._loggerTab=prefix+'loggerTab';this._tracerTab=prefix+'tracerTab';this._explorerContent=prefix+'explorerContent';this._loggerContent=prefix+'loggerContent';this._tracerContent=prefix+'tracerContent';this._mainWindow=null;this._secWindow=null;this._dragEvent=null;this._clientX=null;this._clientY=null;this._beaconId=null;this._classRE=/^(?:(?!(Mojo.log\..*)|(Mojo.aspect)|(Mojo.Iter)|(Mojo.Util)|(Mojo.Meta)|(com\.terraframe\.mojo\.((Class)|(Method)|(Constant)|(Base)|(inspector\.)))).)*$/;this._methodRE=/^(?!toString).*$/;this._explorer=new com.terraframe.mojo.inspector.Explorer(this,this._explorerTab,this._explorerContent);this._logger=new com.terraframe.mojo.inspector.Logger(this,this._loggerTab,this._loggerContent);this._tracer=new com.terraframe.mojo.inspector.Tracer(this,this._tracerTab,this._tracerContent,this._logger);this._currentContent=this._explorer;this._rendered=false;},isRendered:function()
{return this._rendered;},addNewClass:function(inspector,args,returnObj,klass,method)
{var className=returnObj.getMetaClass().getQualifiedName();var r='^'+className.replace('.','\\.')+'$';var re=new RegExp(r);var methodRE=/^(?!toString).*$/;inspector.addAroundAdvice(re);inspector._explorer.render();},addAroundAdvice:function(classRE)
{var aroundWrapper=Mojo.Util.curry(this.aroundWrapper,this);var around=new Mojo.aspect.AroundAdvice(classRE,this._methodRE,aroundWrapper);around.weave();},aroundWrapper:function(mainWin,args,proceed,klass,method)
{var id=mainWin._tracer.beforeTrace(this,args,klass,method);try
{var obj=proceed.apply(this,(args));}
catch(e)
{mainWin._logger.logError(e,id);throw e;}
mainWin._tracer.afterTrace(this,obj,klass,method);return obj;},render:function()
{this._buildHTML();this._hookEvents();this._explorer.render();this._logger.render();this._tracer.render();this._rendered=true;},_buildHTML:function()
{var tStyle='display: inline; list-style-type: none; width: 50px; height: 30px; cursor: pointer; border: 1px solid black; background-color: #eee; padding: 4px;';var cStyle='padding: 5px 0px 5px 5px;';var html='';html+='<div style="border: 1px solid black; position: absolute; width: 800px; height: 600px; background-color: white">';html+='  <div id="'+this._mainDrag+'" style="cursor: move; height: 30px; width: 100%; border-bottom: 1px solid black;">';html+='    <div id="'+this._mainDrag+'_label" style="float: left; margin: 3px 3px 3px 5px">JS Inspector (Super Ultra Mega Alpha Edition)</div>';html+='    <div id="'+this._mainExitId+'" style="cursor: pointer; float: right; width: 20px; height: 20px; border: 1px solid black; text-align: center; margin: 3px 3px">x</div>';html+='    <div id="'+this._mainMinId+'" style="cursor: pointer; float: right; width: 20px; height: 20px; border: 1px solid black; text-align: center; margin: 3px 3px">-</div>';html+='  </div>';html+='  <div stle="height: 100%; width: 100%">';html+='    <ul style="margin: 15px 0px 4px 0px; padding-left: 5px">';html+='      <li id="'+this._explorerTab+'" style="'+tStyle+' background-color: #00ffff;">Explorer</li>';html+='      <li id="'+this._loggerTab+'" style="'+tStyle+'">Logger</li>';html+='      <li id="'+this._tracerTab+'" style="'+tStyle+'">Tracer</li>';html+='    </ul>';html+='    <div style="border-top: 1px solid black">';html+='      <div id="'+this._explorerContent+'" style="display: block; '+cStyle+'">';html+='      </div>';html+='      <div id="'+this._loggerContent+'" style="display: none; '+cStyle+'">';html+='      </div>';html+='      <div id="'+this._tracerContent+'" style="display: none; '+cStyle+'">';html+='      </div>';html+='    </div>';html+='  </div>';html+='</div>';this._mainWindow=document.createElement('div');this._mainWindow.style.position='absolute';this._mainWindow.style.display='none';this._mainWindow.style.left='10px';this._mainWindow.style.top='10px';this._mainWindow.style.zIndex='999999999';this._mainWindow.style.fontFamily='Arial';this._mainWindow.style.fontSize='10pt';this._mainWindow.id=this._mainWindowId;this._mainWindow.innerHTML=html;html='';html+='<div id="'+this._secDrag+'" style="cursor: move; height: 30px; width: 150px; border: 1px solid black; background-color: white">';html+='  <div id="'+this._secDrag+'_label" style="float: left; margin: 3px 3px 3px 5px">JS Inspector</div>';html+='  <div id="'+this._secExitId+'" style="cursor: pointer; float: right; width: 20px; height: 20px; border: 1px solid black; text-align: center; margin: 3px 3px">x</div>';html+='  <div id="'+this._secMaxId+'" style="cursor: pointer; float: right; width: 20px; height: 20px; border: 1px solid black; text-align: center; margin: 3px 3px">+</div>';html+='</div>';this._secWindow=document.createElement('div');this._secWindow.style.position='absolute';this._secWindow.style.display='block';this._secWindow.style.left='10px';this._secWindow.style.top='10px';this._secWindow.id=this._secWindowId;this._secWindow.style.zIndex='999999999';this._secWindow.innerHTML=html;var body=document.getElementsByTagName('body')[0];body.appendChild(this._mainWindow);body.appendChild(this._secWindow);},startBeacon:function()
{if(this._beaconId==null)
{this._beaconId=setInterval((function(mainWin,secWin){var on=false;return function(){if(on)
{document.getElementById(mainWin).style.backgroundColor='white';document.getElementById(secWin).style.backgroundColor='white';}
else
{document.getElementById(mainWin).style.backgroundColor='red';document.getElementById(secWin).style.backgroundColor='red';}
on=!on;};})(this._mainDrag,this._secDrag),500);}},stopBeacon:function()
{if(this._beaconId!=null)
{clearInterval(this._beaconId);document.getElementById(this._mainDrag).style.backgroundColor='white';document.getElementById(this._secDrag).style.backgroundColor='white';this._beaconId=null;}},_hookEvents:function()
{function alertClose()
{alert("This cannot be closed, only minimized or maximized.\n This is why it's super ultra mega alpha edition.");}
var manager=com.terraframe.mojo.inspector.EventManager.getInstance();var IEvent=com.terraframe.mojo.inspector.IEvent;manager.addEvent(new IEvent(this._mainWindowId,'click',this._delegateClick,this));manager.addEvent(new IEvent(this._mainMinId,'click',this.hide,this));manager.addEvent(new IEvent(this._mainExitId,'click',alertClose,this));manager.addEvent(new IEvent(this._secMaxId,'click',this.show,this));manager.addEvent(new IEvent(this._secExitId,'click',alertClose,this));manager.addEvent(new IEvent(this._mainDrag,'mousedown',this.startDrag,this));manager.addEvent(new IEvent(this._secDrag,'mousedown',this.startDrag,this));manager.addEvent(new IEvent(this._mainDrag,'mouseup',this.endDrag,this));manager.addEvent(new IEvent(this._secDrag,'mouseup',this.endDrag,this));manager.addEvent(new IEvent(this._explorerTab,'click',this.doTab,this,this._explorer));manager.addEvent(new IEvent(this._loggerTab,'click',this.doTab,this,this._logger));manager.addEvent(new IEvent(this._tracerTab,'click',this.doTab,this,this._tracer));manager.addEvent(new IEvent(this._mainWindow,'click',this.stopBeacon,this));manager.addEvent(new IEvent(this._secWindow,'click',this.stopBeacon,this));},doTab:function(e,content)
{if(this._currentContent===content)
{return;}
this._currentContent.hide();content.show();if(!content.rendered())
{content.render();}
document.getElementById(this._explorerTab).style.backgroundColor='#eee';document.getElementById(this._loggerTab).style.backgroundColor='#eee';document.getElementById(this._tracerTab).style.backgroundColor='#eee';content.getTab().style.backgroundColor='#00ffff';this._currentContent=content;},isDragging:function()
{return this._dragEvent!=null;},startDrag:function(e)
{var el=e.target;var win;if(el.id===this._mainDrag||el.id===this._mainDrag+'_label')
{win=this._mainWindow;}
else if(el.id===this._secDrag||el.id===this._secDrag+'_label')
{win=this._secWindow;}
else
{return;}
this._dragEvent=new com.terraframe.mojo.inspector.IEvent(window,'mousemove',this.doDrag,this,win);var manager=com.terraframe.mojo.inspector.EventManager.getInstance();manager.addEvent(this._dragEvent);},endDrag:function(e)
{if(this._dragEvent!=null)
{var manager=com.terraframe.mojo.inspector.EventManager.getInstance();manager.removeEvent(this._dragEvent);this._dragEvent=null;this._clientX=null;this._clientY=null;}},doDrag:function(e,win)
{if(this._clientX!=null&&this._clientY!=null)
{var dX=this._clientX-e.clientX;var dY=this._clientY-e.clientY;var wX=parseInt(win.style.left);var wY=parseInt(win.style.top);var nX=(wX-dX)+'px';var nY=(wY-dY)+'px';this._secWindow.style.left=nX;this._secWindow.style.top=nY;this._mainWindow.style.left=nX;this._mainWindow.style.top=nY;}
this._clientX=e.clientX;this._clientY=e.clientY;},show:function()
{this._secWindow.style.display='none';this._mainWindow.style.display='block';},hide:function()
{this._secWindow.style.display='block';this._mainWindow.style.display='none';},destroy:function()
{var manager=com.terraframe.mojo.inspector.EventManager.getInstance();manager.removeAll();this._mainWindow.parentNode.removeChild(this._mainWindow);this._secWindow.parentNode.removeChild(this._secWindow);this._mainWindow=null;this._secWindow=null;this._logger.stopRowBuffer();this._tracer.stopRowBuffer();this._explorer=null;this._logger=null;this._tracer=null;this._rendered=false;},_delegateClick:function(e)
{var el=e.target;if(el.nodeName==='A'&&el.name&&el.name.match(/\w+:/))
{var pieces=el.name.split(':');var action=pieces[0];var data=pieces[1];if(action==='viewClass')
{this.doTab(null,this._explorer);this._explorer.getClassDefinition(data);}
else if(action==='viewMethod')
{this.doTab(null,this._explorer);this._explorer.getMethodDefinition(data);}
else if(action==='viewTracer')
{this.doTab(null,this._tracer);}
else if(action==='viewHierarchy')
{this.doTab(null,this._explorer);this._explorer.getHierarchy();}}}},Static:{launch:function()
{var inspector=Mojo.$.com.terraframe.mojo.inspector.Inspector.getInstance();inspector.isRendered()?inspector.show():inspector.render();}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.Content',{Alias:Mojo.$,IsAbstract:true,Instance:{initialize:function(mainWin,tabId,contentId)
{this._mainWin=mainWin;this._tabId=tabId;this._contentId=contentId;this._rendered=false;},getTab:function()
{return document.getElementById(this._tabId);},getEl:function()
{return document.getElementById(this._contentId);},newEl:function(type)
{return document.createElement(type);},rendered:function()
{return this._rendered;},show:function()
{this.getEl().style.display='block';},hide:function()
{this.getEl().style.display='none';},render:function()
{this._render();this._rendered=true;}},Static:{makeA:function(content,name,useDefault,href)
{var p=useDefault?'default':'pointer';var h='onmouseover="this.style.color=\'#0000ff\'" onmouseout="this.style.color=\'#000000\'"';return'<a href="'+(href!=null?href:'#')+'" name="'+name+'" style="color: black; text-decoration: none; cursor: '+
p+';" '+(useDefault?'':h)+'>'+content+'</a>';},viewClassAction:function(klass,content)
{var className=klass.getMetaClass().getQualifiedName();return com.terraframe.mojo.inspector.Content.makeA((content?content:className),'viewClass:'+className);},viewMethodAction:function(klass,method)
{var m=method.getName();return com.terraframe.mojo.inspector.Content.makeA(m,'viewMethod:'+klass.getMetaClass().getQualifiedName()+'.'+m);},viewTracerAction:function(id)
{return com.terraframe.mojo.inspector.Content.makeA('GOTO','viewTracer:'+id,false,'#'+id);},viewHierarchyAction:function()
{return com.terraframe.mojo.inspector.Content.makeA('Hierarchy','viewHierarchy:');}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.Explorer',{Extends:com.terraframe.mojo.inspector.Content,Instance:{initialize:function(mainWin,tabId,contentId)
{this.$initialize(mainWin,tabId,contentId);var prefix=this.getMetaClass().getQualifiedName()+'_';this._classList=prefix+'classList';this._definition=prefix+'definition';this._method=prefix+'method';},_render:function()
{var el=this.getEl();var classes=Mojo.Meta.getClasses();classes.sort(function(c1,c2){if(c1===c2)
{return 0;}
else if(c1<c2)
{return-1;}
else
{return 1;}});var classLis='';var pcks={};for(var i=0;i<classes.length;i++)
{var className=classes[i];var klass=Mojo.Meta.findClass(className);var pckName=klass.getMetaClass().getPackage();if(pckName!==''&&!pcks[pckName])
{var a=this.constructor.makeA(pckName,pckName,true);classLis+='<li style="list-style-type: none; font-weight: bold">'+a+'</li>';pcks[pckName]={};}
var a=this.constructor.viewClassAction(klass);classLis+='<li style="list-style-type: none;">'+a+'</li>';}
var pckNames=Mojo.Util.getKeys(pcks);var pckLis='';for(var i=0;i<pckNames.length;i++)
{var pckName=pckNames[i];var a=this.constructor.makeA(pckName,'',false,'#'+pckName);pckLis+='<li style="list-style-type: none;">'+a+'</li>';}
var html='';html+='<div style="float: left; width: 250px; height: 527px; overflow: scroll;">';html+='  '+this.constructor.viewHierarchyAction()+'<hr />';html+='  Packages';html+='  <ul style="padding: 5px; margin-top: 0px;">';html+=pckLis;html+='  </ul><hr />';html+='  Classes';html+='  <ul id="'+this._classList+'" style="padding: 5px; margin-top: 0px">';html+=classLis;html+='  </ul>';html+='</div>';html+='<div id="'+this._definition+'" style="float: left; width: 545px; height: 527px; overflow: scroll;">';html+='</div>';html+='<div id="'+this._method+'" style="float: left; width: 540px; height: 527px; overflow: scroll; display: none">';html+='</div>';el.innerHTML=html;this.getHierarchy();},getHierarchy:function()
{var root=Mojo.$.com.terraframe.mojo.Base;that=this;function getChildren(parent)
{var node='<li style="list-style-type: none">';node+='|--'+that.constructor.viewClassAction(parent);node+='<ul style="margin:0px; padding-left:1em">';var subs=parent.getMetaClass().getSubClasses();subs.sort(function(c1,c2){var n1=c1.getMetaClass().getQualifiedName(),n2=c2.getMetaClass().getQualifiedName();if(n1===n2)
{return 0;}
else if(n1<n2)
{return-1;}
else
{return 1;}});Mojo.Iter.forEach(subs,function(sub){node+=getChildren(sub);});node+='</ul>';return node;}
var nodeLi=getChildren(root);var html='';html+='<div style="margin: 10px">';html+='<div style="font-weight: bold;">Hierarchy</div><hr />';html+='<ul style="margin:0px; padding:0px">'+nodeLi+'</ul>';html+='</div>';var el=document.getElementById(this._definition);el.innerHTML=html;},getMethodDefinition:function(qualifiedMethod)
{var ind=qualifiedMethod.lastIndexOf('.');var className=qualifiedMethod.substring(0,ind);var methodName=qualifiedMethod.substring(ind+1);var klass=Mojo.Meta.findClass(className);var method=klass.getMetaClass().getMethod(methodName);var back=this.constructor.viewClassAction(klass,'&#8656;');var body=method.getMethod().toString();body=body.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;");var highlighter=new Mojo.$.com.terraframe.mojo.inspector.SyntaxHighlighter();var src=highlighter.parse(body);var html='';html+='<div style="margin: 10px">';html+='<div style="font-weight: bold;">'+back+' '+qualifiedMethod+'</div><hr />';html+='<pre>';html+=src;html+='</pre>';var defPane=document.getElementById(this._definition);var methPane=document.getElementById(this._method);methPane.innerHTML=html;defPane.style.display='none';methPane.style.display='block';},getClassDefinition:function(qualifiedName)
{var klass=Mojo.Meta.findClass(qualifiedName);var meta=klass.getMetaClass();var html='';html+='<div style="margin: 10px">';html+='<div style="font-weight: bold;">'+meta.toString()+'</div><hr />';var extendsName;if(meta.getSuperClass()!==Object)
{extendsName=this.constructor.viewClassAction(meta.getSuperClass());}
else
{extendsName='Object [JS Native]';}
var tAttrs='cellpadding="3" cellspacing="0" border="1" style="font-size: 10pt; margin-bottom: 15px; white-space: nowrap; border-collapse: collapse"';var cellStyle='style="padding: 3px;"';var pckName=meta.getPackage();if(pckName==='')
{pckName='&nbsp;';}
var alias=meta.getAlias();var subclasses=meta.getSubClasses();var sublinks=[];for(var i=0;i<subclasses.length;i++)
{var a=this.constructor.viewClassAction(subclasses[i]);sublinks.push(a);}
table=new com.terraframe.mojo.inspector.Table();table.setHeaders('Property','Value');table.addRow(['Package',pckName]);table.addRow(['Class Name',meta.getName()]);table.addRow(['Abstract',meta.isAbstract()]);table.addRow(['Singleton',meta.isSingleton()]);table.addRow(['Extends',extendsName]);table.addRow(['Sub Classes',(sublinks.length>0?sublinks.join('<br />'):'&nbsp;')]);html+='Class Definition:<br />';html+=table.getHTML();table=new com.terraframe.mojo.inspector.Table();table.setHeaders('Name','Value','Defined On');var constants=meta.getConstants();constants.sort(function(c1,c2){var n1=c1.getName(),n2=c2.getName();if(n1===n2)
{return 0;}
else if(n1<n2)
{return-1;}
else
{return 1;}});for(var i=0;i<constants.length;i++)
{var constObj=constants[i];var cClass=constObj.getDefiningClass();var defining;if(cClass===klass)
{defining='[this class]';}
else
{defining=this.constructor.viewClassAction(cClass);}
var value=constObj.getValue();var cValue=null;if(Mojo.Util.isFunction(value)||Mojo.Util.isObject(value)||Mojo.Util.isArray(value))
{cValue=typeof value;}
else
{cValue=value;}
table.addRow(constObj.getName(),cValue,defining);}
html+='Constants:<br />';html+=table.getHTML();function methodsToRows(table,methods,that,isStatic)
{methods.sort(function(m1,m2){var n1=m1.getName(),n2=m2.getName();if(n1===n2)
{return 0;}
else if(n1<n2)
{return-1;}
else
{return 1;}});Mojo.Iter.forEach(methods,function(method){var mClass=method.getDefiningClass();var defining=mClass===klass?'[this class]':this.constructor.viewClassAction(mClass);var nameA=this.constructor.viewMethodAction(klass,method);if(method.isConstructor())
{nameA='<span style="text-decoration: underline">'+nameA+'</span>';}
var override=method.isStatic()?method.isHiding():method.isOverride();if(isStatic)
{table.addRow(nameA,override,method.getArity(),defining,this._listAspects(method));}
else
{table.addRow(nameA,method.isAbstract(),override,method.getArity(),defining,this._listAspects(method));}},that);}
table=new com.terraframe.mojo.inspector.Table();table.setHeaders('Name','Abstract','Override','Arity','Defined On','Aspects');methodsToRows(table,meta.getInstanceMethods(),this,false);html+='Instance Methods:<br />';html+=table.getHTML();table=new com.terraframe.mojo.inspector.Table();table.setHeaders('Name','Hiding','Arity','Defined On','Aspects');methodsToRows(table,meta.getStaticMethods(),this,true);html+='Static Methods:<br />';html+=table.getHTML();html+='</div>';var defPane=document.getElementById(this._definition);defPane.innerHTML=html;document.getElementById(this._method).style.display='none';defPane.style.display='block';},_listAspects:function(method)
{var aspects=method.getAspects();var html='';if(aspects.length!==0)
{for(var i=0;i<aspects.length;i++)
{html+='<span style="font-weight: bold">'+i.toString()+'</span>: '+aspects[i].toString()+'<br />';}}
else
{html+='&nbsp;'}
return html;}},});Mojo.Meta.newClass('com.terraframe.mojo.inspector.LoggerImpl',{Extends:Mojo.log.Logger,Instance:{initialize:function(loggerContent)
{this._content=loggerContent;},writeInfo:function(msg)
{},writeWarning:function(msg)
{},writeError:function(msg,error)
{this._content.logError(error);},}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.Logger',{Extends:com.terraframe.mojo.inspector.Content,Constants:{MAX_ROWS:500,},Instance:{initialize:function(mainWin,tabId,contentId)
{this.$initialize(mainWin,tabId,contentId);var logger=new com.terraframe.mojo.inspector.LoggerImpl(this);Mojo.log.LogManager.addLogger(logger);this._logTable=this.getMetaClass().getQualifiedName()+'_logTable';this._table=null;this._errorBuffer=[];},stopRowBuffer:function()
{this._table.stopRowBuffer();},_render:function()
{var el=this.getEl();var html='';html+='<div style="overflow: scroll; height: 527px;">';this._table=new com.terraframe.mojo.inspector.Table(this._logTable,this.constructor.MAX_ROWS);this._table.setHeaders('#','Type','Time','Tracer','Error','Stack','File','Line');html+='Last 500 Log Entries:<br />';html+='<div>';html+=this._table.getHTML();html+='</div>';html+='</div>';el.innerHTML=html;this._table.startRowBuffer();},logWarning:function(msg)
{},logError:function(error,id)
{var traceA;if(id)
{var traceA=this.constructor.viewTracerAction(id);this._errorBuffer.push(id);}
else
{traceA='';}
this._table.insertRow(['<span style="color: red; font-weight: bold">Error</span>',Mojo.Util.toISO8601(new Date()),traceA,error,'<pre>'+error.stack+'</pre>',error.fileName,error.lineNumber],id);this._mainWin.startBeacon();},purgeErrorBuffer:function()
{var refCopy=this._errorBuffer;this._errorBuffer=[];return refCopy;}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.Tracer',{Extends:com.terraframe.mojo.inspector.Content,Constants:{MAX_ROWS:500,},Instance:{initialize:function(mainWin,tabId,contentId,logger)
{this.$initialize(mainWin,tabId,contentId);this._logger=logger;this._tracerTable=this.getMetaClass().getQualifiedName()+'_tracerTable';this._table=null;},stopRowBuffer:function()
{this._table.stopRowBuffer();},_render:function()
{var el=this.getEl();var html='';html+='<div style="overflow: scroll; height: 527px;">';this._table=new com.terraframe.mojo.inspector.Table(this._tracerTable,this.constructor.MAX_ROWS);this._table.setHeaders('#','Time','Call','Class Name','Method','Context','Parameters','Return');html+='Last 500 Traces:<br />';html+='<div>';html+=this._table.getHTML();html+='</div>'
html+='</div>';el.innerHTML=html;this._table.startRowBuffer();},beforeTrace:function(context,args,klass,method)
{var id=new String(Math.random()).substring(2);var toStr=!method.isStatic()&&method.isConstructor()?Mojo.$.com.terraframe.mojo.Base.prototype.toString.call(context):context.toString();var traceA=this.constructor.makeA('BEFORE',id,true);var argCell='';if(args.length===0)
{argCell='&nbsp;'}
else
{for(var i=0;i<args.length;i++)
{argCell+='<span style="font-weight: bold">'+i.toString()+':</span> ';var arg=args[i];if(arg==null)
{argCell+='[null] null<br />';}
else if(arg instanceof Mojo.$.com.terraframe.mojo.Base)
{argCell+='['+this.constructor.viewClassAction(arg.constructor)+'] '+arg.toString()+'<br />';}
else if(Mojo.Util.isObject(arg)||Mojo.Util.isArray(arg)||Mojo.Util.isFunction(arg))
{argCell+='['+(typeof arg)+'] '+(typeof arg)+'<br />';}
else
{var value;if(Mojo.Util.isString(arg)&&arg.length>50)
{value=arg.substring(0,50)+' ...';}
else
{value=arg;}
argCell+='['+(typeof arg)+'] '+value+'<br />';}}}
this._table.insertRow([Mojo.Util.toISO8601(new Date()),traceA,this.constructor.viewClassAction(klass),this.constructor.viewMethodAction(klass,method),toStr,argCell,'n/a'],id);return id;},afterTrace:function(context,retObj,klass,method)
{var toStr=!method.isStatic()&&method.getName()==='initialize'?Mojo.$.com.terraframe.mojo.Base.prototype.toString.call(context):context.toString();var retCell='';if(typeof retObj==='undefined')
{retCell+='&nbsp;';}
else if(retObj===null)
{retCell+='[null] null<br />';}
else if(retObj instanceof Mojo.$.com.terraframe.mojo.Base)
{retCell+='['+this.constructor.viewClassAction(retObj.constructor)+'] '+retObj.toString()+'<br />';}
else if(Mojo.Util.isObject(retObj)||Mojo.Util.isArray(retObj)||Mojo.Util.isFunction(retObj))
{retCell+='['+(typeof retObj)+'] '+(typeof retObj)+'<br />';}
else
{var value;if(Mojo.Util.isString(retObj)&&retObj.length>50)
{value=retObj.substring(0,50)+' ...';}
else
{value=retObj;}
retCell+='['+(typeof retObj)+'] '+value+'<br />';}
this._table.insertRow([Mojo.Util.toISO8601(new Date()),'AFTER',this.constructor.viewClassAction(klass),this.constructor.viewMethodAction(klass,method),toStr,'n/a',retCell],null);}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.Table',{Constants:{TABLE_STYLE:'cellpadding="3" cellspacing="0" border="1" style="margin-left: 2px; font-size: 10pt; margin-bottom: 15px; white-space: nowrap; border-collapse: collapse;"',CELL_STYLE:'padding: 3px;',BUFFER_TIMEOUT:1000,},Instance:{initialize:function(id,maxRows)
{this._id=id||new String(Math.random()).substring(2);this._maxRows=maxRows||null;this._headers=null;this._rows=[];this._table=null;this._rowCount=0;this._rowBuffer=[];this._intervalId=null;},stopRowBuffer:function()
{clearInterval(this._intervalId);},startRowBuffer:function()
{var bound=Mojo.Util.bind(this,this._clearBuffer);setInterval(bound,1000);},_clearBuffer:function()
{if(this._rowBuffer.length===0)
{return;}
[].splice.apply(this._rows,[0,this._rowBuffer.length].concat(this._rowBuffer));this._rowBuffer=[];if(this._rows.length>this._maxRows)
{this._rows=this._rows.slice(0,this._maxRows);}
var html=this.getHTML(false);this._table.parentNode.innerHTML=html;this._table=null;},setHeaders:function(headers)
{var data=Mojo.Util.isArray(headers)?headers:[].splice.call(arguments,0);var id=id||new String(Math.random()).substring(2);this._headers={data:data,id:id};},insertRow:function(data,id)
{if(this._table==null)
{this._table=document.getElementById(this._id);}
data=Mojo.Util.isArray(data)?data:[].splice.call(arguments,0);id=id||new String(Math.random()).substring(2);data.splice(0,0,this._rowCount++);var row={data:data,id:id};this._rowBuffer.splice(0,0,row);},addRow:function(data,id)
{if(this._table==null)
{this._table=document.getElementById(this._id);}
data=Mojo.Util.isArray(data)?data:[].splice.call(arguments,0);id=id||new String(Math.random()).substring(2);var row={data:data,id:id};this._rows.splice(0,0,row);},_addRow:function(isHeader,row)
{var html='';html+='<tr id="'+row.id+'">';var data=row.data;Mojo.Iter.forEach(data,function(cell){if(isHeader)
{html+='<th style="'+this.constructor.CELL_STYLE+' font-weight: bold;">'+cell+'</th>';}
else
{html+='<td style="'+this.constructor.CELL_STYLE+'">'+cell+'</td>';}},this);html+='</tr>';return html;},getHTML:function(rowsOnly)
{this._rows.reverse();var html='';if(!rowsOnly)
{html+='<table '+this.constructor.TABLE_STYLE+' id="'+this._id+'">';}
html+=this._addRow(true,this._headers);var rowsHTML=Mojo.Iter.map(this._rows,Mojo.Util.curry(this._addRow,false),this);html+=rowsHTML.join('');if(!rowsOnly)
{html+='</table>';}
return html;}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.IEvent',{Instance:{initialize:function(element,type,handler,context,obj)
{this._id=new String(Math.random()).substring(2);this._element=Mojo.Util.isString(element)?document.getElementById(element):element;this._type=type;this._handler=handler;this._context=context;this._obj=obj||{};this._wrapper=Mojo.Util.bind(this,function(e){this._handler.call(this._context,e,this._obj);});},getId:function()
{return this._id;},getElement:function()
{return this._element;},getType:function()
{return this._type;},getWrapper:function()
{return this._wrapper;}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.EventManager',{IsSingleton:true,Instance:{initialize:function()
{this._eventMap={};},_addListener:function(event)
{var wrapper=event.getWrapper();var type=event.getType();var el=event.getElement();if(el.addEventListener)
{el.addEventListener(type,wrapper,false);}
else
{el.attachEvent(type,wrapper);}},_removeListener:function(event)
{var wrapper=event.getWrapper();var type=event.getType();var el=event.getElement();if(el.removeEventListener)
{el.removeEventListener(type,wrapper,false);}
else
{el.detachEvent(type,wrapper);}},addEvent:function(event)
{this._addListener(event);this._eventMap[event.getId()]=event;},removeEvent:function(event)
{this._removeListener(event);delete this._eventMap[event.getId()];},removeAll:function()
{var ids=Mojo.Util.getKeys(this._eventMap);for(var i=0;i<ids.length;i++)
{this._removeListener(this._eventMap[ids[i]]);}
this._eventMap={};}}});Mojo.Meta.newClass('com.terraframe.mojo.inspector.SyntaxHighlighter',{Alias:Mojo.$,Constants:{KEYWORDS:["break","case","comment","continue","default","delete","do","document","else","export","for","function","if","import","in","label","new","null","prototype","return","switch","this","typeof","var","void","while","window","with","goto","true","false","try","catch","throw","throws","finally","Array","Boolean","Date","Error","Math","Number","RegExp","String"]},Instance:{initialize:function()
{},parse:function(src)
{var exp=new RegExp();var reg1=new RegExp();var that=this;src=src.replace(/</g,"&lt;").replace(/>/g,"&gt;");exp.compile("/\\*","g");src=src.replace(exp,function(str,index,s2){var bRet=that._withinString(s2,index);if(bRet){return str;}else{return"<c>"+str;}});exp.compile("\\*/","g");src=src.replace(exp,function(str,index,s2){var bRet=that._withinString(s2,index);if(bRet){return str;}else{return str+"</c>";}});src=src.replace(/\/\/(.*)/g,function(str,s,index,s2){var bRet=that._withinString(s2,index);if(bRet){return"//"+s;}else{return"<c>//"+s+"</c>";}});var index=src.indexOf("<c>");if(index!=-1){var s1=src.substr(0,index);var s2=src.substring(index,src.length);s1=this._processKeyword(reg1,s1);s1=this._processString(s1);src=s1+s2;}else{src=this._processKeyword(reg1,src);src=this._processString(src);}
exp.compile("</c>([^<]*)<c>","g");src=src.replace(exp,function(strOrg,sMatch){sMatch="</c>"+sMatch+"<c>";sMatch=that._processKeyword(reg1,sMatch);sMatch=that._processString(sMatch);return sMatch;});var index=src.lastIndexOf("</c>");if(index!=-1){var s1=src.substr(0,index+4);var s2=src.substring(index+4,src.length);s2=this._processKeyword(reg1,s2);s2=this._processString(s2);src=s1+s2;}
src=src.replace(/ /g,"&nbsp;").replace(/\t/g,"&nbsp;&nbsp;&nbsp;&nbsp;").replace(/\n/g,"<br />");src=src.replace(/<k>/g,"<span style='color: #0000FF'>").replace(/<\/k>/g,"</span>").replace(/<c>/g,"<span style='color: #008200'>").replace(/<\/c>/g,"</span>").replace(/<s>/g,"<span style='color: #A51410'>").replace(/<\/s>/g,"</span>");return src;},_withinString:function(str,index){var len=str.length;var rq=0,rq1=0;for(var i=index;i<len;i++){var ch=str.charAt(i);if(ch=="\""){rq++;}
if(ch=="'"){rq1++;}
if(ch=="\n"){break;}}
if(rq%2!=0){return true;}
if(rq1%2!=0){return true;}
return false;},_processKeyword:function(exp,sMatch){var js_keyword=this.constructor.KEYWORDS;for(var i=0;i<js_keyword.length;i++){exp.compile("\\b"+js_keyword[i]+"\\b","g");sMatch=sMatch.replace(exp,"<k>"+js_keyword[i]+"</k>");}
return sMatch;},_processString:function(sMatch){var that=this;sMatch=sMatch.replace(/(.*)(\".*\")(.*)/g,function(sOrg,s0,s1,s2){s0=that._processString(s0);s1="<s>"+s1+"</s>";s2=that._processString(s2);return s0+s1+s2;});sMatch=sMatch.replace(/(.*)(\'.*\')(.*)/g,function(sOrg,s0,s1,s2){s0=that._processString(s0);s1="<s>"+s1+"</s>";s2=that._processString(s2);return s0+s1+s2;});return sMatch;}}});Mojo.Meta.shorthand('com.terraframe.mojo.transport.attributes.*',window);var MDSS={wait_for_ajax:null,getDisplayLabel:function(type)
{var display=MDSS._displayLabelCache[type];if(display==null)
{var klass=Mojo.Meta.findClass(type);var temp=new klass();display=temp.getMd().getDisplayLabel();MDSS._displayLabelCache[type]=display;}
return display;},_displayLabelCache:{},util:{extractScripts:function(html)
{var scripts=html.match(/<script\b[^>]*>[\s\S]*?<\/script>/img);var executables=[];if(scripts!=null)
{for(var i=0;i<scripts.length;i++)
{var scriptM=scripts[i].match(/<script\b[^>]*>([\s\S]*?)<\/script>/im);executables.push(scriptM[1]);}}
return executables.join('');},removeScripts:function(html)
{return html.replace(/<script\b[^>]*>[\s\S]*?<\/script>/img,'');},stripWhitespace:function(str)
{return str.replace(/^\s+/,'').replace(/\s+$/,'');}},confirmModal:function(message,onYes,onNo)
{var modal=new YAHOO.widget.Panel("confirm_"+Mojo.Util.generateId(),{fixedcenter:true,width:'300px',visible:true,draggable:false,zindex:8000,modal:true,close:false});var upperDiv=document.createElement('div');YAHOO.util.Dom.addClass(upperDiv,'modalAlertBox');var span=document.createElement('span');span.innerHTML=message;upperDiv.appendChild(span);var lowerDiv=document.createElement('div');YAHOO.util.Dom.addClass(lowerDiv,'modalAlertBox');var yes=document.createElement('input');YAHOO.util.Dom.setAttribute(yes,'type','button');YAHOO.util.Dom.setAttribute(yes,'value',MDSS.Localized.Choice_Yes);YAHOO.util.Event.on(yes,'click',function(){modal.destroy();onYes()});lowerDiv.appendChild(yes);var no=document.createElement('input');YAHOO.util.Dom.setAttribute(no,'type','button');YAHOO.util.Dom.setAttribute(no,'value',MDSS.Localized.Choice_No);YAHOO.util.Event.on(no,'click',function(){modal.destroy();onNo()});lowerDiv.appendChild(no);var wrapperDiv=document.createElement('div');wrapperDiv.appendChild(upperDiv);wrapperDiv.appendChild(lowerDiv);modal.setBody(wrapperDiv);modal.render(document.body);modal.bringToTop();},Effect:{toggleVisibility:function(elementId,toggleId,hiddenId)
{YAHOO.util.Event.on(toggleId,'click',function(e,obj){var el=new YAHOO.util.Element(obj.element);var toggle=document.getElementById(obj.toggle);var hidden=document.getElementById(obj.hidden);if(el.getStyle('display')==='block')
{el.setStyle('display','none');toggle.innerHTML=MDSS.Localized.Click_to_Add;hidden.value='false';}
else
{el.setStyle('display','block');toggle.innerHTML=MDSS.Localized.Click_to_Remove;hidden.value='true';}},{toggle:toggleId,element:elementId,hidden:hiddenId},this);}},ErrorModal:function(content)
{var length=Mojo.Util.trim(content).length;if(length==0)
{return;}
var modal=new YAHOO.widget.Panel("errorModal"+Mojo.Util.generateId(),{width:"400px",height:"200px",fixedcenter:true,close:true,draggable:false,zindex:9999,modal:true,visible:true});var div=document.createElement('div');YAHOO.util.Dom.addClass(div,'alert alertbox modalAlertBox');div.innerHTML=content;modal.setBody(div);modal.render(document.body);modal.bringToTop();modal.subscribe('hide',function(){var that=this;setTimeout(function(){that.destroy();},15);});},Request:function(handler)
{this.createModal=function(content)
{new MDSS.ErrorModal(content);};this.onSend=function()
{if(MDSS.util.wait_for_ajax!=null)
{MDSS.util.wait_for_ajax.show();MDSS.util.wait_for_ajax.bringToTop();}
else
{MDSS.util.wait_for_ajax=new YAHOO.widget.Panel("wait_for_ajax",{width:"240px",fixedcenter:true,close:false,draggable:false,zindex:999,modal:true,visible:true});MDSS.util.wait_for_ajax.setHeader(MDSS.Localized.Ajax_Loading);MDSS.util.wait_for_ajax.setBody('<img src="imgs/rel_interstitial_loading.gif" />');MDSS.util.wait_for_ajax.render(document.body);MDSS.util.wait_for_ajax.bringToTop();}}
this.onComplete=function()
{MDSS.util.wait_for_ajax.hide();}
this.onFailure=function(e)
{if(this.getTransport().status===0)
{return;}
this.createModal(e.getLocalizedMessage());};this.onProblemExceptionDTO=function(e)
{var content='';if(e instanceof Mojo.$.com.terraframe.mojo.ProblemExceptionDTO)
{var problems=e.getProblems();for(var i=0;i<problems.length;i++)
{content+=problems[i].getLocalizedMessage()+"<br />";}}
else
{content=e.getLocalizedMessage();}
this.createModal(content);};this.onInvalidSessionException=function(e)
{window.location='/';};Mojo.Util.copy(new Mojo.ClientRequest(handler),this);}};MDSS.Set=function(arr)
{this._set={};if(arr)
{this.addAll(arr);}}
MDSS.Set.prototype={clear:function()
{this._set={};},addAll:function(array)
{for(var i=0;i<array.length;i++)
{this.set(array[i]);}},remove:function(value)
{delete this._set[value];},contains:function(value)
{return this._set[value]!=null;},set:function(value)
{this._set[value]={};},size:function()
{return Mojo.Util.getKeys(this._set).length;},values:function()
{return Mojo.Util.getKeys(this._set);}};(function(){var imgs=['imgs/icons/add.png','imgs/icons/delete.png','imgs/icons/wand.png'];Mojo.Iter.forEach(imgs,function(img){new Image().src=img;});MDSS.rotationCircle=new Image();MDSS.rotationCircle.src='imgs/rotationCircle.png';})();(function(){var registered=[];var E=YAHOO.util.Event;var al=E.addListener;E.addListener=function(el,sType,fn,obj,overrideContext){registered.push(el);al.call(E,el,sType,fn,obj,overrideContext);};YAHOO.util.Event.on(window,'unload',function(e){var E=YAHOO.util.Event;for(var i=0,len=registered.length;i<len;i++)
{try
{E.purgeElement(registered[i],false);}
catch(e)
{}}
delete registered;});})();Mojo.Meta.newClass('MDSS.ProgressRequest',{Instance:{initialize:function(func,polling,key)
{this._func=func;this._polling=polling;this._intervalId=null;this._modal=null;this._bar=null;this._key=Mojo.Util.isString(key)?key:'Ajax_Loading';},_initializeModal:function()
{this._modal=new YAHOO.widget.Panel("progress_modal",{width:"240px",fixedcenter:true,close:false,draggable:false,modal:true,visible:true});this._modal.setHeader(MDSS.localize(this._key));this._modal.setBody("<div id='progress_bar'></div>")
this._modal.render(document.body);this._modal.bringToTop();this._bar=new YAHOO.widget.ProgressBar({minValue:0,maxValue:100,value:0,height:"15px",width:"220px"}).render("progress_bar");},_destroyModal:function()
{if(this._modal!=null)
{this._modal.hide();this._modal==null;}},start:function()
{this._sendRequest();this._startPolling();},_sendRequest:function()
{var request=new MDSS.Request({that:this,onComplete:function()
{this.that._destroyModal();},onSend:function()
{this.that._initializeModal();},onSuccess:function()
{this.that._stopPolling();}});var oldOnProblemExceptionDTO=request.onProblemExceptionDTO;var newOnProblemExceptionDTO=function(e){oldOnProblemExceptionDTO.apply(request,[e]);this.that._destroyModal();this.that._stopPolling();}
var oldOnFailure=request.onFailure;var newOnFailure=function(e){oldOnFailure.apply(request,[e]);this.that._destroyModal();this.that._stopPolling();}
request.onProblemExceptionDTO=newOnProblemExceptionDTO;request.onFailure=newOnFailure;this._func(request);},_startPolling:function()
{var bound=Mojo.Util.bind(this,this._pollServer);this._intervalId=setInterval(bound,1000);},_pollServer:function()
{var request=new MDSS.Request({that:this,onComplete:function(){},onSend:function(){},onFailure:function(){},onProblemExceptionDTO:function(){},onSuccess:function(percent)
{if(percent==-1)
{if(this.that._modal!=null)
{this.that._destroyModal();this.that._stopPolling();}}
else
{this.that._setPercent(percent);}}});this._polling(request);},_setPercent:function(percent)
{if(this._modal!=null){this._bar.set('value',percent);}},_stopPolling:function()
{clearInterval(this._intervalId);}}});(function(){YAHOO.util.Event.onDOMReady(function(){YAHOO.util.Event.on(window,'beforeunload',function(){var elements=YAHOO.util.Selector.query('input');for(var i=0;i<elements.length;i++){elements[i].setAttribute("autocomplete","on");}});});})();