
if(typeof YAHOO=="undefined"||!YAHOO){var YAHOO={};}
YAHOO.namespace=function(){var a=arguments,o=null,i,j,d;for(i=0;i<a.length;i=i+1){d=(""+a[i]).split(".");o=YAHOO;for(j=(d[0]=="YAHOO")?1:0;j<d.length;j=j+1){o[d[j]]=o[d[j]]||{};o=o[d[j]];}}
return o;};YAHOO.log=function(msg,cat,src){var l=YAHOO.widget.Logger;if(l&&l.log){return l.log(msg,cat,src);}else{return false;}};YAHOO.register=function(name,mainClass,data){var mods=YAHOO.env.modules,m,v,b,ls,i;if(!mods[name]){mods[name]={versions:[],builds:[]};}
m=mods[name];v=data.version;b=data.build;ls=YAHOO.env.listeners;m.name=name;m.version=v;m.build=b;m.versions.push(v);m.builds.push(b);m.mainClass=mainClass;for(i=0;i<ls.length;i=i+1){ls[i](m);}
if(mainClass){mainClass.VERSION=v;mainClass.BUILD=b;}else{YAHOO.log("mainClass is undefined for module "+name,"warn");}};YAHOO.env=YAHOO.env||{modules:[],listeners:[]};YAHOO.env.getVersion=function(name){return YAHOO.env.modules[name]||null;};YAHOO.env.ua=function(){var numberfy=function(s){var c=0;return parseFloat(s.replace(/\./g,function(){return(c++==1)?'':'.';}));},nav=navigator,o={ie:0,opera:0,gecko:0,webkit:0,mobile:null,air:0,caja:nav.cajaVersion,secure:false,os:null},ua=navigator&&navigator.userAgent,loc=window&&window.location,href=loc&&loc.href,m;o.secure=href&&(href.toLowerCase().indexOf("https")===0);if(ua){if((/windows|win32/i).test(ua)){o.os='windows';}else if((/macintosh/i).test(ua)){o.os='macintosh';}
if((/KHTML/).test(ua)){o.webkit=1;}
m=ua.match(/AppleWebKit\/([^\s]*)/);if(m&&m[1]){o.webkit=numberfy(m[1]);if(/ Mobile\//.test(ua)){o.mobile="Apple";}else{m=ua.match(/NokiaN[^\/]*/);if(m){o.mobile=m[0];}}
m=ua.match(/AdobeAIR\/([^\s]*)/);if(m){o.air=m[0];}}
if(!o.webkit){m=ua.match(/Opera[\s\/]([^\s]*)/);if(m&&m[1]){o.opera=numberfy(m[1]);m=ua.match(/Opera Mini[^;]*/);if(m){o.mobile=m[0];}}else{m=ua.match(/MSIE\s([^;]*)/);if(m&&m[1]){o.ie=numberfy(m[1]);}else{m=ua.match(/Gecko\/([^\s]*)/);if(m){o.gecko=1;m=ua.match(/rv:([^\s\)]*)/);if(m&&m[1]){o.gecko=numberfy(m[1]);}}}}}}
return o;}();(function(){YAHOO.namespace("util","widget","example");if("undefined"!==typeof YAHOO_config){var l=YAHOO_config.listener,ls=YAHOO.env.listeners,unique=true,i;if(l){for(i=0;i<ls.length;i++){if(ls[i]==l){unique=false;break;}}
if(unique){ls.push(l);}}}})();YAHOO.lang=YAHOO.lang||{};(function(){var L=YAHOO.lang,OP=Object.prototype,ARRAY_TOSTRING='[object Array]',FUNCTION_TOSTRING='[object Function]',OBJECT_TOSTRING='[object Object]',NOTHING=[],ADD=["toString","valueOf"],OB={isArray:function(o){return OP.toString.apply(o)===ARRAY_TOSTRING;},isBoolean:function(o){return typeof o==='boolean';},isFunction:function(o){return(typeof o==='function')||OP.toString.apply(o)===FUNCTION_TOSTRING;},isNull:function(o){return o===null;},isNumber:function(o){return typeof o==='number'&&isFinite(o);},isObject:function(o){return(o&&(typeof o==='object'||L.isFunction(o)))||false;},isString:function(o){return typeof o==='string';},isUndefined:function(o){return typeof o==='undefined';},_IEEnumFix:(YAHOO.env.ua.ie)?function(r,s){var i,fname,f;for(i=0;i<ADD.length;i=i+1){fname=ADD[i];f=s[fname];if(L.isFunction(f)&&f!=OP[fname]){r[fname]=f;}}}:function(){},extend:function(subc,superc,overrides){if(!superc||!subc){throw new Error("extend failed, please check that "+"all dependencies are included.");}
var F=function(){},i;F.prototype=superc.prototype;subc.prototype=new F();subc.prototype.constructor=subc;subc.superclass=superc.prototype;if(superc.prototype.constructor==OP.constructor){superc.prototype.constructor=superc;}
if(overrides){for(i in overrides){if(L.hasOwnProperty(overrides,i)){subc.prototype[i]=overrides[i];}}
L._IEEnumFix(subc.prototype,overrides);}},augmentObject:function(r,s){if(!s||!r){throw new Error("Absorb failed, verify dependencies.");}
var a=arguments,i,p,overrideList=a[2];if(overrideList&&overrideList!==true){for(i=2;i<a.length;i=i+1){r[a[i]]=s[a[i]];}}else{for(p in s){if(overrideList||!(p in r)){r[p]=s[p];}}
L._IEEnumFix(r,s);}},augmentProto:function(r,s){if(!s||!r){throw new Error("Augment failed, verify dependencies.");}
var a=[r.prototype,s.prototype],i;for(i=2;i<arguments.length;i=i+1){a.push(arguments[i]);}
L.augmentObject.apply(this,a);},dump:function(o,d){var i,len,s=[],OBJ="{...}",FUN="f(){...}",COMMA=', ',ARROW=' => ';if(!L.isObject(o)){return o+"";}else if(o instanceof Date||("nodeType"in o&&"tagName"in o)){return o;}else if(L.isFunction(o)){return FUN;}
d=(L.isNumber(d))?d:3;if(L.isArray(o)){s.push("[");for(i=0,len=o.length;i<len;i=i+1){if(L.isObject(o[i])){s.push((d>0)?L.dump(o[i],d-1):OBJ);}else{s.push(o[i]);}
s.push(COMMA);}
if(s.length>1){s.pop();}
s.push("]");}else{s.push("{");for(i in o){if(L.hasOwnProperty(o,i)){s.push(i+ARROW);if(L.isObject(o[i])){s.push((d>0)?L.dump(o[i],d-1):OBJ);}else{s.push(o[i]);}
s.push(COMMA);}}
if(s.length>1){s.pop();}
s.push("}");}
return s.join("");},substitute:function(s,o,f){var i,j,k,key,v,meta,saved=[],token,DUMP='dump',SPACE=' ',LBRACE='{',RBRACE='}',dump,objstr;for(;;){i=s.lastIndexOf(LBRACE);if(i<0){break;}
j=s.indexOf(RBRACE,i);if(i+1>=j){break;}
token=s.substring(i+1,j);key=token;meta=null;k=key.indexOf(SPACE);if(k>-1){meta=key.substring(k+1);key=key.substring(0,k);}
v=o[key];if(f){v=f(key,v,meta);}
if(L.isObject(v)){if(L.isArray(v)){v=L.dump(v,parseInt(meta,10));}else{meta=meta||"";dump=meta.indexOf(DUMP);if(dump>-1){meta=meta.substring(4);}
objstr=v.toString();if(objstr===OBJECT_TOSTRING||dump>-1){v=L.dump(v,parseInt(meta,10));}else{v=objstr;}}}else if(!L.isString(v)&&!L.isNumber(v)){v="~-"+saved.length+"-~";saved[saved.length]=token;}
s=s.substring(0,i)+v+s.substring(j+1);}
for(i=saved.length-1;i>=0;i=i-1){s=s.replace(new RegExp("~-"+i+"-~"),"{"+saved[i]+"}","g");}
return s;},trim:function(s){try{return s.replace(/^\s+|\s+$/g,"");}catch(e){return s;}},merge:function(){var o={},a=arguments,l=a.length,i;for(i=0;i<l;i=i+1){L.augmentObject(o,a[i],true);}
return o;},later:function(when,o,fn,data,periodic){when=when||0;o=o||{};var m=fn,d=data,f,r;if(L.isString(fn)){m=o[fn];}
if(!m){throw new TypeError("method undefined");}
if(d&&!L.isArray(d)){d=[data];}
f=function(){m.apply(o,d||NOTHING);};r=(periodic)?setInterval(f,when):setTimeout(f,when);return{interval:periodic,cancel:function(){if(this.interval){clearInterval(r);}else{clearTimeout(r);}}};},isValue:function(o){return(L.isObject(o)||L.isString(o)||L.isNumber(o)||L.isBoolean(o));}};L.hasOwnProperty=(OP.hasOwnProperty)?function(o,prop){return o&&o.hasOwnProperty(prop);}:function(o,prop){return!L.isUndefined(o[prop])&&o.constructor.prototype[prop]!==o[prop];};OB.augmentObject(L,OB,true);YAHOO.util.Lang=L;L.augment=L.augmentProto;YAHOO.augment=L.augmentProto;YAHOO.extend=L.extend;})();YAHOO.register("yahoo",YAHOO,{version:"2.8.0r4",build:"2449"});