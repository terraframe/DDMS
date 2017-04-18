
YAHOO.namespace("deconcept");YAHOO.deconcept=YAHOO.deconcept||{};if(typeof YAHOO.deconcept.util=="undefined"||!YAHOO.deconcept.util)
{YAHOO.deconcept.util={};}
if(typeof YAHOO.deconcept.SWFObjectUtil=="undefined"||!YAHOO.deconcept.SWFObjectUtil)
{YAHOO.deconcept.SWFObjectUtil={};}
YAHOO.deconcept.SWFObject=function(swf,id,w,h,ver,c,quality,xiRedirectUrl,redirectUrl,detectKey)
{if(!document.getElementById){return;}
this.DETECT_KEY=detectKey?detectKey:'detectflash';this.skipDetect=YAHOO.deconcept.util.getRequestParameter(this.DETECT_KEY);this.params={};this.variables={};this.attributes=[];if(swf){this.setAttribute('swf',swf);}
if(id){this.setAttribute('id',id);}
if(w){this.setAttribute('width',w);}
if(h){this.setAttribute('height',h);}
if(ver){this.setAttribute('version',new YAHOO.deconcept.PlayerVersion(ver.toString().split(".")));}
this.installedVer=YAHOO.deconcept.SWFObjectUtil.getPlayerVersion();if(!window.opera&&document.all&&this.installedVer.major>7)
{YAHOO.deconcept.SWFObject.doPrepUnload=true;}
if(c)
{this.addParam('bgcolor',c);}
var q=quality?quality:'high';this.addParam('quality',q);this.setAttribute('useExpressInstall',false);this.setAttribute('doExpressInstall',false);var xir=(xiRedirectUrl)?xiRedirectUrl:window.location;this.setAttribute('xiRedirectUrl',xir);this.setAttribute('redirectUrl','');if(redirectUrl)
{this.setAttribute('redirectUrl',redirectUrl);}};YAHOO.deconcept.SWFObject.prototype={useExpressInstall:function(path)
{this.xiSWFPath=!path?"expressinstall.swf":path;this.setAttribute('useExpressInstall',true);},setAttribute:function(name,value){this.attributes[name]=value;},getAttribute:function(name){return this.attributes[name];},addParam:function(name,value){this.params[name]=value;},getParams:function(){return this.params;},addVariable:function(name,value){this.variables[name]=value;},getVariable:function(name){return this.variables[name];},getVariables:function(){return this.variables;},getVariablePairs:function(){var variablePairs=[];var key;var variables=this.getVariables();for(key in variables)
{if(variables.hasOwnProperty(key))
{variablePairs[variablePairs.length]=key+"="+variables[key];}}
return variablePairs;},getSWFHTML:function(){var swfNode="";var params={};var key="";var pairs="";if(navigator.plugins&&navigator.mimeTypes&&navigator.mimeTypes.length){if(this.getAttribute("doExpressInstall")){this.addVariable("MMplayerType","PlugIn");this.setAttribute('swf',this.xiSWFPath);}
swfNode='<embed type="application/x-shockwave-flash" src="'+this.getAttribute('swf')+'" width="'+this.getAttribute('width')+'" height="'+this.getAttribute('height')+'" style="'+this.getAttribute('style')+'"';swfNode+=' id="'+this.getAttribute('id')+'" name="'+this.getAttribute('id')+'" ';params=this.getParams();for(key in params)
{if(params.hasOwnProperty(key))
{swfNode+=[key]+'="'+params[key]+'" ';}}
pairs=this.getVariablePairs().join("&");if(pairs.length>0){swfNode+='flashvars="'+pairs+'"';}
swfNode+='/>';}else{if(this.getAttribute("doExpressInstall")){this.addVariable("MMplayerType","ActiveX");this.setAttribute('swf',this.xiSWFPath);}
swfNode='<object id="'+this.getAttribute('id')+'" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="'+this.getAttribute('width')+'" height="'+this.getAttribute('height')+'" style="'+this.getAttribute('style')+'">';swfNode+='<param name="movie" value="'+this.getAttribute('swf')+'" />';params=this.getParams();for(key in params)
{if(params.hasOwnProperty(key))
{swfNode+='<param name="'+key+'" value="'+params[key]+'" />';}}
pairs=this.getVariablePairs().join("&");if(pairs.length>0){swfNode+='<param name="flashvars" value="'+pairs+'" />';}
swfNode+="</object>";}
return swfNode;},write:function(elementId)
{if(this.getAttribute('useExpressInstall')){var expressInstallReqVer=new YAHOO.deconcept.PlayerVersion([6,0,65]);if(this.installedVer.versionIsValid(expressInstallReqVer)&&!this.installedVer.versionIsValid(this.getAttribute('version'))){this.setAttribute('doExpressInstall',true);this.addVariable("MMredirectURL",escape(this.getAttribute('xiRedirectUrl')));document.title=document.title.slice(0,47)+" - Flash Player Installation";this.addVariable("MMdoctitle",document.title);}}
if(this.skipDetect||this.getAttribute('doExpressInstall')||this.installedVer.versionIsValid(this.getAttribute('version')))
{var n=(typeof elementId=='string')?document.getElementById(elementId):elementId;n.innerHTML=this.getSWFHTML();return true;}
else
{if(this.getAttribute('redirectUrl')!=="")
{document.location.replace(this.getAttribute('redirectUrl'));}}
return false;}};YAHOO.deconcept.SWFObjectUtil.getPlayerVersion=function()
{var axo=null;var PlayerVersion=new YAHOO.deconcept.PlayerVersion([0,0,0]);if(navigator.plugins&&navigator.mimeTypes.length)
{var x=navigator.plugins["Shockwave Flash"];if(x&&x.description)
{PlayerVersion=new YAHOO.deconcept.PlayerVersion(x.description.replace(/([a-zA-Z]|\s)+/,"").replace(/(\s+r|\s+b[0-9]+)/,".").split("."));}}
else if(navigator.userAgent&&navigator.userAgent.indexOf("Windows CE")>=0)
{var counter=3;while(axo)
{try
{counter++;axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash."+counter);PlayerVersion=new YAHOO.deconcept.PlayerVersion([counter,0,0]);}
catch(e)
{axo=null;}}}
else
{try
{axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");}
catch(e)
{try
{axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");PlayerVersion=new YAHOO.deconcept.PlayerVersion([6,0,21]);axo.AllowScriptAccess="always";}
catch(e)
{if(PlayerVersion.major==6)
{return PlayerVersion;}}
try
{axo=new ActiveXObject("ShockwaveFlash.ShockwaveFlash");}
catch(e){}}
if(axo!==null)
{PlayerVersion=new YAHOO.deconcept.PlayerVersion(axo.GetVariable("$version").split(" ")[1].split(","));}}
return PlayerVersion;};YAHOO.deconcept.PlayerVersion=function(arrVersion)
{this.major=arrVersion[0]!==null?parseInt(arrVersion[0],0):0;this.minor=arrVersion[1]!==null?parseInt(arrVersion[1],0):0;this.rev=arrVersion[2]!==null?parseInt(arrVersion[2],0):0;};YAHOO.deconcept.PlayerVersion.prototype.versionIsValid=function(fv)
{if(this.major<fv.major)
{return false;}
if(this.major>fv.major)
{return true;}
if(this.minor<fv.minor)
{return false;}
if(this.minor>fv.minor)
{return true;}
if(this.rev<fv.rev)
{return false;}
return true;};YAHOO.deconcept.util={getRequestParameter:function(param)
{var q=document.location.search||document.location.hash;if(param===null){return q;}
if(q)
{var pairs=q.substring(1).split("&");for(var i=0;i<pairs.length;i++)
{if(pairs[i].substring(0,pairs[i].indexOf("="))==param)
{return pairs[i].substring((pairs[i].indexOf("=")+1));}}}
return"";}};YAHOO.deconcept.SWFObjectUtil.cleanupSWFs=function()
{var objects=document.getElementsByTagName("OBJECT");for(var i=objects.length-1;i>=0;i--)
{objects[i].style.display='none';for(var x in objects[i])
{if(typeof objects[i][x]=='function')
{objects[i][x]=function(){};}}}};if(YAHOO.deconcept.SWFObject.doPrepUnload)
{if(!YAHOO.deconcept.unloadSet)
{YAHOO.deconcept.SWFObjectUtil.prepUnload=function()
{__flash_unloadHandler=function(){};__flash_savedUnloadHandler=function(){};window.attachEvent("onunload",YAHOO.deconcept.SWFObjectUtil.cleanupSWFs);};window.attachEvent("onbeforeunload",YAHOO.deconcept.SWFObjectUtil.prepUnload);YAHOO.deconcept.unloadSet=true;}}
if(!document.getElementById&&document.all)
{document.getElementById=function(id){return document.all[id];};}
YAHOO.widget.FlashAdapter=function(swfURL,containerID,attributes,buttonSkin)
{this._queue=this._queue||[];this._events=this._events||{};this._configs=this._configs||{};attributes=attributes||{};this._id=attributes.id=attributes.id||YAHOO.util.Dom.generateId(null,"yuigen");attributes.version=attributes.version||"9.0.45";attributes.backgroundColor=attributes.backgroundColor||"#ffffff";this._attributes=attributes;this._swfURL=swfURL;this._containerID=containerID;this._embedSWF(this._swfURL,this._containerID,attributes.id,attributes.version,attributes.backgroundColor,attributes.expressInstall,attributes.wmode,buttonSkin);try
{this.createEvent("contentReady");}
catch(e){}};YAHOO.widget.FlashAdapter.owners=YAHOO.widget.FlashAdapter.owners||{};YAHOO.extend(YAHOO.widget.FlashAdapter,YAHOO.util.AttributeProvider,{_swfURL:null,_containerID:null,_swf:null,_id:null,_initialized:false,_attributes:null,toString:function()
{return"FlashAdapter "+this._id;},destroy:function()
{if(this._swf)
{var container=YAHOO.util.Dom.get(this._containerID);container.removeChild(this._swf);}
var instanceName=this._id;for(var prop in this)
{if(YAHOO.lang.hasOwnProperty(this,prop))
{this[prop]=null;}}
YAHOO.log("FlashAdapter instance destroyed: "+instanceName);},_embedSWF:function(swfURL,containerID,swfID,version,backgroundColor,expressInstall,wmode,buttonSkin)
{var swfObj=new YAHOO.deconcept.SWFObject(swfURL,swfID,"100%","100%",version,backgroundColor);if(expressInstall)
{swfObj.useExpressInstall(expressInstall);}
swfObj.addParam("allowScriptAccess","always");if(wmode)
{swfObj.addParam("wmode",wmode);}
swfObj.addParam("menu","false");swfObj.addVariable("allowedDomain",document.location.hostname);swfObj.addVariable("elementID",swfID);swfObj.addVariable("eventHandler","YAHOO.widget.FlashAdapter.eventHandler");if(buttonSkin){swfObj.addVariable("buttonSkin",buttonSkin);}
var container=YAHOO.util.Dom.get(containerID);var result=swfObj.write(container);if(result)
{this._swf=YAHOO.util.Dom.get(swfID);YAHOO.widget.FlashAdapter.owners[swfID]=this;}
else
{YAHOO.log("Unable to load SWF "+swfURL);}},_eventHandler:function(event)
{var type=event.type;switch(type)
{case"swfReady":this._loadHandler();return;case"log":YAHOO.log(event.message,event.category,this.toString());return;}
this.fireEvent(type,event);},_loadHandler:function()
{this._initialized=false;this._initAttributes(this._attributes);this.setAttributes(this._attributes,true);this._initialized=true;this.fireEvent("contentReady");},set:function(name,value)
{this._attributes[name]=value;YAHOO.widget.FlashAdapter.superclass.set.call(this,name,value);},_initAttributes:function(attributes)
{this.getAttributeConfig("altText",{method:this._getAltText});this.setAttributeConfig("altText",{method:this._setAltText});this.getAttributeConfig("swfURL",{method:this._getSWFURL});},_getSWFURL:function()
{return this._swfURL;},_getAltText:function()
{return this._swf.getAltText();},_setAltText:function(value)
{return this._swf.setAltText(value);}});YAHOO.widget.FlashAdapter.eventHandler=function(elementID,event)
{if(!YAHOO.widget.FlashAdapter.owners[elementID])
{setTimeout(function(){YAHOO.widget.FlashAdapter.eventHandler(elementID,event);},0);}
else
{YAHOO.widget.FlashAdapter.owners[elementID]._eventHandler(event);}};YAHOO.widget.FlashAdapter.proxyFunctionCount=0;YAHOO.widget.FlashAdapter.createProxyFunction=function(func)
{var index=YAHOO.widget.FlashAdapter.proxyFunctionCount;YAHOO.widget.FlashAdapter["proxyFunction"+index]=function()
{return func.apply(null,arguments);};YAHOO.widget.FlashAdapter.proxyFunctionCount++;return"YAHOO.widget.FlashAdapter.proxyFunction"+index.toString();};YAHOO.widget.FlashAdapter.removeProxyFunction=function(funcName)
{if(!funcName||funcName.indexOf("YAHOO.widget.FlashAdapter.proxyFunction")<0)
{return;}
funcName=funcName.substr(26);YAHOO.widget.FlashAdapter[funcName]=null;};YAHOO.widget.Uploader=function(containerId,buttonSkin,forceTransparent)
{var newWMode="window";if(!(buttonSkin)||(buttonSkin&&forceTransparent)){newWMode="transparent";}
YAHOO.widget.Uploader.superclass.constructor.call(this,YAHOO.widget.Uploader.SWFURL,containerId,{wmode:newWMode},buttonSkin);this.createEvent("mouseDown");this.createEvent("mouseUp");this.createEvent("rollOver");this.createEvent("rollOut");this.createEvent("click");this.createEvent("fileSelect");this.createEvent("uploadStart");this.createEvent("uploadProgress");this.createEvent("uploadCancel");this.createEvent("uploadComplete");this.createEvent("uploadCompleteData");this.createEvent("uploadError");}
YAHOO.widget.Uploader.SWFURL="assets/uploader.swf";YAHOO.extend(YAHOO.widget.Uploader,YAHOO.widget.FlashAdapter,{upload:function(fileID,uploadScriptPath,method,vars,fieldName)
{this._swf.upload(fileID,uploadScriptPath,method,vars,fieldName);},uploadThese:function(fileIDs,uploadScriptPath,method,vars,fieldName)
{this._swf.uploadThese(fileIDs,uploadScriptPath,method,vars,fieldName);},uploadAll:function(uploadScriptPath,method,vars,fieldName)
{this._swf.uploadAll(uploadScriptPath,method,vars,fieldName);},cancel:function(fileID)
{this._swf.cancel(fileID);},clearFileList:function()
{this._swf.clearFileList();},removeFile:function(fileID)
{this._swf.removeFile(fileID);},setAllowLogging:function(allowLogging)
{this._swf.setAllowLogging(allowLogging);},setSimUploadLimit:function(simUploadLimit)
{this._swf.setSimUploadLimit(simUploadLimit);},setAllowMultipleFiles:function(allowMultipleFiles)
{this._swf.setAllowMultipleFiles(allowMultipleFiles);},setFileFilters:function(fileFilters)
{this._swf.setFileFilters(fileFilters);},enable:function()
{this._swf.enable();},disable:function()
{this._swf.disable();}});YAHOO.register("uploader",YAHOO.widget.Uploader,{version:"2.8.0r4",build:"2449"});