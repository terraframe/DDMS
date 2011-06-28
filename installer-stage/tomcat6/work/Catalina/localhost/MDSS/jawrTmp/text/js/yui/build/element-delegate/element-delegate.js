
(function(){var Event=YAHOO.util.Event,delegates=[],specialTypes={mouseenter:true,mouseleave:true};YAHOO.lang.augmentObject(YAHOO.util.Element.prototype,{delegate:function(type,fn,filter,obj,overrideContext){if(YAHOO.lang.isString(filter)&&!YAHOO.util.Selector){return false;}
if(!Event._createDelegate){return false;}
var sType=Event._getType(type),el=this.get("element"),fnDelegate,fnMouseDelegate,fnWrapper=function(e){return fnDelegate.call(el,e);};if(specialTypes[type]){if(!Event._createMouseDelegate){return false;}
fnMouseDelegate=Event._createMouseDelegate(fn,obj,overrideContext);fnDelegate=Event._createDelegate(function(event,matchedEl,container){return fnMouseDelegate.call(matchedEl,event,container);},filter,obj,overrideContext);}
else{fnDelegate=Event._createDelegate(fn,filter,obj,overrideContext);}
delegates.push([el,sType,fn,fnWrapper]);return this.on(sType,fnWrapper);},removeDelegate:function(type,fn){var sType=Event._getType(type),index=Event._getCacheIndex(delegates,this.get("element"),sType,fn),returnVal,cacheItem;if(index>=0){cacheItem=delegates[index];}
if(cacheItem){returnVal=this.removeListener(cacheItem[1],cacheItem[3]);if(returnVal){delete delegates[index][2];delete delegates[index][3];delegates.splice(index,1);}}
return returnVal;}});}());YAHOO.register("element-delegate",YAHOO.util.Element,{version:"2.8.0r4",build:"2449"});