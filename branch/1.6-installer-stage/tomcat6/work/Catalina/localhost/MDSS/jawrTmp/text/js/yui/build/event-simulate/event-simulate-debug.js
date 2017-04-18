
YAHOO.util.UserAction={simulateKeyEvent:function(target,type,bubbles,cancelable,view,ctrlKey,altKey,shiftKey,metaKey,keyCode,charCode)
{target=YAHOO.util.Dom.get(target);if(!target){throw new Error("simulateKeyEvent(): Invalid target.");}
if(YAHOO.lang.isString(type)){type=type.toLowerCase();switch(type){case"keyup":case"keydown":case"keypress":break;case"textevent":type="keypress";break;default:throw new Error("simulateKeyEvent(): Event type '"+type+"' not supported.");}}else{throw new Error("simulateKeyEvent(): Event type must be a string.");}
if(!YAHOO.lang.isBoolean(bubbles)){bubbles=true;}
if(!YAHOO.lang.isBoolean(cancelable)){cancelable=true;}
if(!YAHOO.lang.isObject(view)){view=window;}
if(!YAHOO.lang.isBoolean(ctrlKey)){ctrlKey=false;}
if(!YAHOO.lang.isBoolean(altKey)){altKey=false;}
if(!YAHOO.lang.isBoolean(shiftKey)){shiftKey=false;}
if(!YAHOO.lang.isBoolean(metaKey)){metaKey=false;}
if(!YAHOO.lang.isNumber(keyCode)){keyCode=0;}
if(!YAHOO.lang.isNumber(charCode)){charCode=0;}
var customEvent=null;if(YAHOO.lang.isFunction(document.createEvent)){try{customEvent=document.createEvent("KeyEvents");customEvent.initKeyEvent(type,bubbles,cancelable,view,ctrlKey,altKey,shiftKey,metaKey,keyCode,charCode);}catch(ex){try{customEvent=document.createEvent("Events");}catch(uierror){customEvent=document.createEvent("UIEvents");}finally{customEvent.initEvent(type,bubbles,cancelable);customEvent.view=view;customEvent.altKey=altKey;customEvent.ctrlKey=ctrlKey;customEvent.shiftKey=shiftKey;customEvent.metaKey=metaKey;customEvent.keyCode=keyCode;customEvent.charCode=charCode;}}
target.dispatchEvent(customEvent);}else if(YAHOO.lang.isObject(document.createEventObject)){customEvent=document.createEventObject();customEvent.bubbles=bubbles;customEvent.cancelable=cancelable;customEvent.view=view;customEvent.ctrlKey=ctrlKey;customEvent.altKey=altKey;customEvent.shiftKey=shiftKey;customEvent.metaKey=metaKey;customEvent.keyCode=(charCode>0)?charCode:keyCode;target.fireEvent("on"+type,customEvent);}else{throw new Error("simulateKeyEvent(): No event simulation framework present.");}},simulateMouseEvent:function(target,type,bubbles,cancelable,view,detail,screenX,screenY,clientX,clientY,ctrlKey,altKey,shiftKey,metaKey,button,relatedTarget)
{target=YAHOO.util.Dom.get(target);if(!target){throw new Error("simulateMouseEvent(): Invalid target.");}
if(YAHOO.lang.isString(type)){type=type.toLowerCase();switch(type){case"mouseover":case"mouseout":case"mousedown":case"mouseup":case"click":case"dblclick":case"mousemove":break;default:throw new Error("simulateMouseEvent(): Event type '"+type+"' not supported.");}}else{throw new Error("simulateMouseEvent(): Event type must be a string.");}
if(!YAHOO.lang.isBoolean(bubbles)){bubbles=true;}
if(!YAHOO.lang.isBoolean(cancelable)){cancelable=(type!="mousemove");}
if(!YAHOO.lang.isObject(view)){view=window;}
if(!YAHOO.lang.isNumber(detail)){detail=1;}
if(!YAHOO.lang.isNumber(screenX)){screenX=0;}
if(!YAHOO.lang.isNumber(screenY)){screenY=0;}
if(!YAHOO.lang.isNumber(clientX)){clientX=0;}
if(!YAHOO.lang.isNumber(clientY)){clientY=0;}
if(!YAHOO.lang.isBoolean(ctrlKey)){ctrlKey=false;}
if(!YAHOO.lang.isBoolean(altKey)){altKey=false;}
if(!YAHOO.lang.isBoolean(shiftKey)){shiftKey=false;}
if(!YAHOO.lang.isBoolean(metaKey)){metaKey=false;}
if(!YAHOO.lang.isNumber(button)){button=0;}
var customEvent=null;if(YAHOO.lang.isFunction(document.createEvent)){customEvent=document.createEvent("MouseEvents");if(customEvent.initMouseEvent){customEvent.initMouseEvent(type,bubbles,cancelable,view,detail,screenX,screenY,clientX,clientY,ctrlKey,altKey,shiftKey,metaKey,button,relatedTarget);}else{customEvent=document.createEvent("UIEvents");customEvent.initEvent(type,bubbles,cancelable);customEvent.view=view;customEvent.detail=detail;customEvent.screenX=screenX;customEvent.screenY=screenY;customEvent.clientX=clientX;customEvent.clientY=clientY;customEvent.ctrlKey=ctrlKey;customEvent.altKey=altKey;customEvent.metaKey=metaKey;customEvent.shiftKey=shiftKey;customEvent.button=button;customEvent.relatedTarget=relatedTarget;}
if(relatedTarget&&!customEvent.relatedTarget){if(type=="mouseout"){customEvent.toElement=relatedTarget;}else if(type=="mouseover"){customEvent.fromElement=relatedTarget;}}
target.dispatchEvent(customEvent);}else if(YAHOO.lang.isObject(document.createEventObject)){customEvent=document.createEventObject();customEvent.bubbles=bubbles;customEvent.cancelable=cancelable;customEvent.view=view;customEvent.detail=detail;customEvent.screenX=screenX;customEvent.screenY=screenY;customEvent.clientX=clientX;customEvent.clientY=clientY;customEvent.ctrlKey=ctrlKey;customEvent.altKey=altKey;customEvent.metaKey=metaKey;customEvent.shiftKey=shiftKey;switch(button){case 0:customEvent.button=1;break;case 1:customEvent.button=4;break;case 2:break;default:customEvent.button=0;}
customEvent.relatedTarget=relatedTarget;target.fireEvent("on"+type,customEvent);}else{throw new Error("simulateMouseEvent(): No event simulation framework present.");}},fireMouseEvent:function(target,type,options)
{options=options||{};this.simulateMouseEvent(target,type,options.bubbles,options.cancelable,options.view,options.detail,options.screenX,options.screenY,options.clientX,options.clientY,options.ctrlKey,options.altKey,options.shiftKey,options.metaKey,options.button,options.relatedTarget);},click:function(target,options){this.fireMouseEvent(target,"click",options);},dblclick:function(target,options){this.fireMouseEvent(target,"dblclick",options);},mousedown:function(target,options){this.fireMouseEvent(target,"mousedown",options);},mousemove:function(target,options){this.fireMouseEvent(target,"mousemove",options);},mouseout:function(target,options){this.fireMouseEvent(target,"mouseout",options);},mouseover:function(target,options){this.fireMouseEvent(target,"mouseover",options);},mouseup:function(target,options){this.fireMouseEvent(target,"mouseup",options);},fireKeyEvent:function(type,target,options)
{options=options||{};this.simulateKeyEvent(target,type,options.bubbles,options.cancelable,options.view,options.ctrlKey,options.altKey,options.shiftKey,options.metaKey,options.keyCode,options.charCode);},keydown:function(target,options){this.fireKeyEvent("keydown",target,options);},keypress:function(target,options){this.fireKeyEvent("keypress",target,options);},keyup:function(target,options){this.fireKeyEvent("keyup",target,options);}};YAHOO.register("event-simulate",YAHOO.util.UserAction,{version:"2.8.0r4",build:"2449"});