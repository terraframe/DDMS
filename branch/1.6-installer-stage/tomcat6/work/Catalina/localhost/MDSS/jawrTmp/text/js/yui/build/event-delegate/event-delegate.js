
(function(){var Event=YAHOO.util.Event,Lang=YAHOO.lang,delegates=[],getMatch=function(el,selector,container){var returnVal;if(!el||el===container){returnVal=false;}
else{returnVal=YAHOO.util.Selector.test(el,selector)?el:getMatch(el.parentNode,selector,container);}
return returnVal;};Lang.augmentObject(Event,{_createDelegate:function(fn,filter,obj,overrideContext){return function(event){var container=this,target=Event.getTarget(event),selector=filter,bDocument=(container.nodeType===9),matchedEl,context,sID,sIDSelector;if(Lang.isFunction(filter)){matchedEl=filter(target);}
else if(Lang.isString(filter)){if(!bDocument){sID=container.id;if(!sID){sID=Event.generateId(container);}
sIDSelector=("#"+sID+" ");selector=(sIDSelector+filter).replace(/,/gi,(","+sIDSelector));}
if(YAHOO.util.Selector.test(target,selector)){matchedEl=target;}
else if(YAHOO.util.Selector.test(target,((selector.replace(/,/gi," *,"))+" *"))){matchedEl=getMatch(target,selector,container);}}
if(matchedEl){context=matchedEl;if(overrideContext){if(overrideContext===true){context=obj;}else{context=overrideContext;}}
return fn.call(context,event,matchedEl,container,obj);}};},delegate:function(container,type,fn,filter,obj,overrideContext){var sType=type,fnMouseDelegate,fnDelegate;if(Lang.isString(filter)&&!YAHOO.util.Selector){return false;}
if(type=="mouseenter"||type=="mouseleave"){if(!Event._createMouseDelegate){return false;}
sType=Event._getType(type);fnMouseDelegate=Event._createMouseDelegate(fn,obj,overrideContext);fnDelegate=Event._createDelegate(function(event,matchedEl,container){return fnMouseDelegate.call(matchedEl,event,container);},filter,obj,overrideContext);}
else{fnDelegate=Event._createDelegate(fn,filter,obj,overrideContext);}
delegates.push([container,sType,fn,fnDelegate]);return Event.on(container,sType,fnDelegate);},removeDelegate:function(container,type,fn){var sType=type,returnVal=false,index,cacheItem;if(type=="mouseenter"||type=="mouseleave"){sType=Event._getType(type);}
index=Event._getCacheIndex(delegates,container,sType,fn);if(index>=0){cacheItem=delegates[index];}
if(container&&cacheItem){returnVal=Event.removeListener(cacheItem[0],cacheItem[1],cacheItem[3]);if(returnVal){delete delegates[index][2];delete delegates[index][3];delegates.splice(index,1);}}
return returnVal;}});}());YAHOO.register("event-delegate",YAHOO.util.Event,{version:"2.8.0r4",build:"2449"});