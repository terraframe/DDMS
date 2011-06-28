
(function(){var Event=YAHOO.util.Event,Lang=YAHOO.lang,addListener=Event.addListener,removeListener=Event.removeListener,getListeners=Event.getListeners,delegates=[],specialTypes={mouseenter:"mouseover",mouseleave:"mouseout"},remove=function(el,type,fn){var index=Event._getCacheIndex(delegates,el,type,fn),cacheItem,returnVal;if(index>=0){cacheItem=delegates[index];}
if(el&&cacheItem){returnVal=removeListener.call(Event,cacheItem[0],type,cacheItem[3]);if(returnVal){delete delegates[index][2];delete delegates[index][3];delegates.splice(index,1);}}
return returnVal;};Lang.augmentObject(Event._specialTypes,specialTypes);Lang.augmentObject(Event,{_createMouseDelegate:function(fn,obj,overrideContext){return function(event,container){var el=this,relatedTarget=Event.getRelatedTarget(event),context,args;if(el!=relatedTarget&&!YAHOO.util.Dom.isAncestor(el,relatedTarget)){context=el;if(overrideContext){if(overrideContext===true){context=obj;}else{context=overrideContext;}}
args=[event,el,obj];if(container){args.splice(2,0,container);}
return fn.apply(context,args);}};},addListener:function(el,type,fn,obj,overrideContext){var fnDelegate,returnVal;if(specialTypes[type]){fnDelegate=Event._createMouseDelegate(fn,obj,overrideContext);fnDelegate.mouseDelegate=true;delegates.push([el,type,fn,fnDelegate]);returnVal=addListener.call(Event,el,type,fnDelegate);}
else{returnVal=addListener.apply(Event,arguments);}
return returnVal;},removeListener:function(el,type,fn){var returnVal;if(specialTypes[type]){returnVal=remove.apply(Event,arguments);}
else{returnVal=removeListener.apply(Event,arguments);}
return returnVal;},getListeners:function(el,type){var listeners=[],elListeners,bMouseOverOrOut=(type==="mouseover"||type==="mouseout"),bMouseDelegate,i,l;if(type&&(bMouseOverOrOut||specialTypes[type])){elListeners=getListeners.call(Event,el,this._getType(type));if(elListeners){for(i=elListeners.length-1;i>-1;i--){l=elListeners[i];bMouseDelegate=l.fn.mouseDelegate;if((specialTypes[type]&&bMouseDelegate)||(bMouseOverOrOut&&!bMouseDelegate)){listeners.push(l);}}}}
else{listeners=getListeners.apply(Event,arguments);}
return(listeners&&listeners.length)?listeners:null;}},true);Event.on=Event.addListener;}());YAHOO.register("event-mouseenter",YAHOO.util.Event,{version:"2.8.0r4",build:"2449"});