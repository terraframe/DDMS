
YAHOO.util.History=(function(){var _histFrame=null;var _stateField=null;var _initialized=false;var _modules=[];var _fqstates=[];function _getHash(){var i,href;href=top.location.href;i=href.indexOf("#");return i>=0?href.substr(i+1):null;}
function _storeStates(){var moduleName,moduleObj,initialStates=[],currentStates=[];for(moduleName in _modules){if(YAHOO.lang.hasOwnProperty(_modules,moduleName)){moduleObj=_modules[moduleName];initialStates.push(moduleName+"="+moduleObj.initialState);currentStates.push(moduleName+"="+moduleObj.currentState);}}
_stateField.value=initialStates.join("&")+"|"+currentStates.join("&");if(YAHOO.env.ua.webkit){_stateField.value+="|"+_fqstates.join(",");}}
function _handleFQStateChange(fqstate){var i,len,moduleName,moduleObj,modules,states,tokens,currentState;if(!fqstate){for(moduleName in _modules){if(YAHOO.lang.hasOwnProperty(_modules,moduleName)){moduleObj=_modules[moduleName];moduleObj.currentState=moduleObj.initialState;moduleObj.onStateChange(unescape(moduleObj.currentState));}}
return;}
modules=[];states=fqstate.split("&");for(i=0,len=states.length;i<len;i++){tokens=states[i].split("=");if(tokens.length===2){moduleName=tokens[0];currentState=tokens[1];modules[moduleName]=currentState;}}
for(moduleName in _modules){if(YAHOO.lang.hasOwnProperty(_modules,moduleName)){moduleObj=_modules[moduleName];currentState=modules[moduleName];if(!currentState||moduleObj.currentState!==currentState){moduleObj.currentState=currentState||moduleObj.initialState;moduleObj.onStateChange(unescape(moduleObj.currentState));}}}}
function _updateIFrame(fqstate){var html,doc;html='<html><body><div id="state">'+fqstate+'</div></body></html>';try{doc=_histFrame.contentWindow.document;doc.open();doc.write(html);doc.close();return true;}catch(e){return false;}}
function _checkIframeLoaded(){var doc,elem,fqstate,hash;if(!_histFrame.contentWindow||!_histFrame.contentWindow.document){setTimeout(_checkIframeLoaded,10);return;}
doc=_histFrame.contentWindow.document;elem=doc.getElementById("state");fqstate=elem?elem.innerText:null;hash=_getHash();setInterval(function(){var newfqstate,states,moduleName,moduleObj,newHash,historyLength;doc=_histFrame.contentWindow.document;elem=doc.getElementById("state");newfqstate=elem?elem.innerText:null;newHash=_getHash();if(newfqstate!==fqstate){fqstate=newfqstate;_handleFQStateChange(fqstate);if(!fqstate){states=[];for(moduleName in _modules){if(YAHOO.lang.hasOwnProperty(_modules,moduleName)){moduleObj=_modules[moduleName];states.push(moduleName+"="+moduleObj.initialState);}}
newHash=states.join("&");}else{newHash=fqstate;}
top.location.hash=newHash;hash=newHash;_storeStates();}else if(newHash!==hash){hash=newHash;_updateIFrame(newHash);}},50);_initialized=true;YAHOO.util.History.onLoadEvent.fire();}
function _initialize(){var i,len,parts,tokens,moduleName,moduleObj,initialStates,initialState,currentStates,currentState,counter,hash;parts=_stateField.value.split("|");if(parts.length>1){initialStates=parts[0].split("&");for(i=0,len=initialStates.length;i<len;i++){tokens=initialStates[i].split("=");if(tokens.length===2){moduleName=tokens[0];initialState=tokens[1];moduleObj=_modules[moduleName];if(moduleObj){moduleObj.initialState=initialState;}}}
currentStates=parts[1].split("&");for(i=0,len=currentStates.length;i<len;i++){tokens=currentStates[i].split("=");if(tokens.length>=2){moduleName=tokens[0];currentState=tokens[1];moduleObj=_modules[moduleName];if(moduleObj){moduleObj.currentState=currentState;}}}}
if(parts.length>2){_fqstates=parts[2].split(",");}
if(YAHOO.env.ua.ie){if(typeof document.documentMode==="undefined"||document.documentMode<8){_checkIframeLoaded();}else{YAHOO.util.Event.on(top,"hashchange",function(){var hash=_getHash();_handleFQStateChange(hash);_storeStates();});_initialized=true;YAHOO.util.History.onLoadEvent.fire();}}else{counter=history.length;hash=_getHash();setInterval(function(){var state,newHash,newCounter;newHash=_getHash();newCounter=history.length;if(newHash!==hash){hash=newHash;counter=newCounter;_handleFQStateChange(hash);_storeStates();}else if(newCounter!==counter&&YAHOO.env.ua.webkit){hash=newHash;counter=newCounter;state=_fqstates[counter-1];_handleFQStateChange(state);_storeStates();}},50);_initialized=true;YAHOO.util.History.onLoadEvent.fire();}}
return{onLoadEvent:new YAHOO.util.CustomEvent("onLoad"),onReady:function(fn,obj,overrideContext){if(_initialized){setTimeout(function(){var ctx=window;if(overrideContext){if(overrideContext===true){ctx=obj;}else{ctx=overrideContext;}}
fn.call(ctx,"onLoad",[],obj);},0);}else{YAHOO.util.History.onLoadEvent.subscribe(fn,obj,overrideContext);}},register:function(module,initialState,onStateChange,obj,overrideContext){var scope,wrappedFn;if(typeof module!=="string"||YAHOO.lang.trim(module)===""||typeof initialState!=="string"||typeof onStateChange!=="function"){throw new Error("Missing or invalid argument");}
if(_modules[module]){return;}
if(_initialized){throw new Error("All modules must be registered before calling YAHOO.util.History.initialize");}
module=escape(module);initialState=escape(initialState);scope=null;if(overrideContext===true){scope=obj;}else{scope=overrideContext;}
wrappedFn=function(state){return onStateChange.call(scope,state,obj);};_modules[module]={name:module,initialState:initialState,currentState:initialState,onStateChange:wrappedFn};},initialize:function(stateField,histFrame){if(_initialized){return;}
if(YAHOO.env.ua.opera&&typeof history.navigationMode!=="undefined"){history.navigationMode="compatible";}
if(typeof stateField==="string"){stateField=document.getElementById(stateField);}
if(!stateField||stateField.tagName.toUpperCase()!=="TEXTAREA"&&(stateField.tagName.toUpperCase()!=="INPUT"||stateField.type!=="hidden"&&stateField.type!=="text")){throw new Error("Missing or invalid argument");}
_stateField=stateField;if(YAHOO.env.ua.ie&&(typeof document.documentMode==="undefined"||document.documentMode<8)){if(typeof histFrame==="string"){histFrame=document.getElementById(histFrame);}
if(!histFrame||histFrame.tagName.toUpperCase()!=="IFRAME"){throw new Error("Missing or invalid argument");}
_histFrame=histFrame;}
YAHOO.util.Event.onDOMReady(_initialize);},navigate:function(module,state){var states;if(typeof module!=="string"||typeof state!=="string"){throw new Error("Missing or invalid argument");}
states={};states[module]=state;return YAHOO.util.History.multiNavigate(states);},multiNavigate:function(states){var currentStates,moduleName,moduleObj,currentState,fqstate;if(typeof states!=="object"){throw new Error("Missing or invalid argument");}
if(!_initialized){throw new Error("The Browser History Manager is not initialized");}
for(moduleName in states){if(!_modules[moduleName]){throw new Error("The following module has not been registered: "+moduleName);}}
currentStates=[];for(moduleName in _modules){if(YAHOO.lang.hasOwnProperty(_modules,moduleName)){moduleObj=_modules[moduleName];if(YAHOO.lang.hasOwnProperty(states,moduleName)){currentState=states[unescape(moduleName)];}else{currentState=unescape(moduleObj.currentState);}
moduleName=escape(moduleName);currentState=escape(currentState);currentStates.push(moduleName+"="+currentState);}}
fqstate=currentStates.join("&");if(YAHOO.env.ua.ie&&(typeof document.documentMode==="undefined"||document.documentMode<8)){return _updateIFrame(fqstate);}else{top.location.hash=fqstate;if(YAHOO.env.ua.webkit){_fqstates[history.length]=fqstate;_storeStates();}
return true;}},getCurrentState:function(module){var moduleObj;if(typeof module!=="string"){throw new Error("Missing or invalid argument");}
if(!_initialized){throw new Error("The Browser History Manager is not initialized");}
moduleObj=_modules[module];if(!moduleObj){throw new Error("No such registered module: "+module);}
return unescape(moduleObj.currentState);},getBookmarkedState:function(module){var i,len,idx,hash,states,tokens,moduleName;if(typeof module!=="string"){throw new Error("Missing or invalid argument");}
idx=top.location.href.indexOf("#");if(idx>=0){hash=top.location.href.substr(idx+1);states=hash.split("&");for(i=0,len=states.length;i<len;i++){tokens=states[i].split("=");if(tokens.length===2){moduleName=tokens[0];if(moduleName===module){return unescape(tokens[1]);}}}}
return null;},getQueryStringParameter:function(paramName,url){var i,len,idx,queryString,params,tokens;url=url||top.location.href;idx=url.indexOf("?");queryString=idx>=0?url.substr(idx+1):url;idx=queryString.lastIndexOf("#");queryString=idx>=0?queryString.substr(0,idx):queryString;params=queryString.split("&");for(i=0,len=params.length;i<len;i++){tokens=params[i].split("=");if(tokens.length>=2){if(tokens[0]===paramName){return unescape(tokens[1]);}}}
return null;}};})();YAHOO.register("history",YAHOO.util.History,{version:"2.8.0r4",build:"2449"});