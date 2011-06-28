
YAHOO.namespace("util");YAHOO.util.Cookie={_createCookieString:function(name,value,encodeValue,options){var lang=YAHOO.lang,text=encodeURIComponent(name)+"="+(encodeValue?encodeURIComponent(value):value);if(lang.isObject(options)){if(options.expires instanceof Date){text+="; expires="+options.expires.toUTCString();}
if(lang.isString(options.path)&&options.path!==""){text+="; path="+options.path;}
if(lang.isString(options.domain)&&options.domain!==""){text+="; domain="+options.domain;}
if(options.secure===true){text+="; secure";}}
return text;},_createCookieHashString:function(hash){var lang=YAHOO.lang;if(!lang.isObject(hash)){throw new TypeError("Cookie._createCookieHashString(): Argument must be an object.");}
var text=[];for(var key in hash){if(lang.hasOwnProperty(hash,key)&&!lang.isFunction(hash[key])&&!lang.isUndefined(hash[key])){text.push(encodeURIComponent(key)+"="+encodeURIComponent(String(hash[key])));}}
return text.join("&");},_parseCookieHash:function(text){var hashParts=text.split("&"),hashPart=null,hash={};if(text.length>0){for(var i=0,len=hashParts.length;i<len;i++){hashPart=hashParts[i].split("=");hash[decodeURIComponent(hashPart[0])]=decodeURIComponent(hashPart[1]);}}
return hash;},_parseCookieString:function(text,decode){var cookies={};if(YAHOO.lang.isString(text)&&text.length>0){var decodeValue=(decode===false?function(s){return s;}:decodeURIComponent);var cookieParts=text.split(/;\s/g),cookieName=null,cookieValue=null,cookieNameValue=null;for(var i=0,len=cookieParts.length;i<len;i++){cookieNameValue=cookieParts[i].match(/([^=]+)=/i);if(cookieNameValue instanceof Array){try{cookieName=decodeURIComponent(cookieNameValue[1]);cookieValue=decodeValue(cookieParts[i].substring(cookieNameValue[1].length+1));}catch(ex){}}else{cookieName=decodeURIComponent(cookieParts[i]);cookieValue="";}
cookies[cookieName]=cookieValue;}}
return cookies;},exists:function(name){if(!YAHOO.lang.isString(name)||name===""){throw new TypeError("Cookie.exists(): Cookie name must be a non-empty string.");}
var cookies=this._parseCookieString(document.cookie,true);return cookies.hasOwnProperty(name);},get:function(name,options){var lang=YAHOO.lang,converter;if(lang.isFunction(options)){converter=options;options={};}else if(lang.isObject(options)){converter=options.converter;}else{options={};}
var cookies=this._parseCookieString(document.cookie,!options.raw);if(!lang.isString(name)||name===""){throw new TypeError("Cookie.get(): Cookie name must be a non-empty string.");}
if(lang.isUndefined(cookies[name])){return null;}
if(!lang.isFunction(converter)){return cookies[name];}else{return converter(cookies[name]);}},getSub:function(name,subName,converter){var lang=YAHOO.lang,hash=this.getSubs(name);if(hash!==null){if(!lang.isString(subName)||subName===""){throw new TypeError("Cookie.getSub(): Subcookie name must be a non-empty string.");}
if(lang.isUndefined(hash[subName])){return null;}
if(!lang.isFunction(converter)){return hash[subName];}else{return converter(hash[subName]);}}else{return null;}},getSubs:function(name){var isString=YAHOO.lang.isString;if(!isString(name)||name===""){throw new TypeError("Cookie.getSubs(): Cookie name must be a non-empty string.");}
var cookies=this._parseCookieString(document.cookie,false);if(isString(cookies[name])){return this._parseCookieHash(cookies[name]);}
return null;},remove:function(name,options){if(!YAHOO.lang.isString(name)||name===""){throw new TypeError("Cookie.remove(): Cookie name must be a non-empty string.");}
options=YAHOO.lang.merge(options||{},{expires:new Date(0)});return this.set(name,"",options);},removeSub:function(name,subName,options){var lang=YAHOO.lang;options=options||{};if(!lang.isString(name)||name===""){throw new TypeError("Cookie.removeSub(): Cookie name must be a non-empty string.");}
if(!lang.isString(subName)||subName===""){throw new TypeError("Cookie.removeSub(): Subcookie name must be a non-empty string.");}
var subs=this.getSubs(name);if(lang.isObject(subs)&&lang.hasOwnProperty(subs,subName)){delete subs[subName];if(!options.removeIfEmpty){return this.setSubs(name,subs,options);}else{for(var key in subs){if(lang.hasOwnProperty(subs,key)&&!lang.isFunction(subs[key])&&!lang.isUndefined(subs[key])){return this.setSubs(name,subs,options);}}
return this.remove(name,options);}}else{return"";}},set:function(name,value,options){var lang=YAHOO.lang;options=options||{};if(!lang.isString(name)){throw new TypeError("Cookie.set(): Cookie name must be a string.");}
if(lang.isUndefined(value)){throw new TypeError("Cookie.set(): Value cannot be undefined.");}
var text=this._createCookieString(name,value,!options.raw,options);document.cookie=text;return text;},setSub:function(name,subName,value,options){var lang=YAHOO.lang;if(!lang.isString(name)||name===""){throw new TypeError("Cookie.setSub(): Cookie name must be a non-empty string.");}
if(!lang.isString(subName)||subName===""){throw new TypeError("Cookie.setSub(): Subcookie name must be a non-empty string.");}
if(lang.isUndefined(value)){throw new TypeError("Cookie.setSub(): Subcookie value cannot be undefined.");}
var hash=this.getSubs(name);if(!lang.isObject(hash)){hash={};}
hash[subName]=value;return this.setSubs(name,hash,options);},setSubs:function(name,value,options){var lang=YAHOO.lang;if(!lang.isString(name)){throw new TypeError("Cookie.setSubs(): Cookie name must be a string.");}
if(!lang.isObject(value)){throw new TypeError("Cookie.setSubs(): Cookie value must be an object.");}
var text=this._createCookieString(name,this._createCookieHashString(value),false,options);document.cookie=text;return text;}};YAHOO.register("cookie",YAHOO.util.Cookie,{version:"2.8.0r4",build:"2449"});