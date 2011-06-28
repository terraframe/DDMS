
YAHOO.namespace("widget");(function(){var version=0;var UA=YAHOO.env.ua;var sF="ShockwaveFlash";if(UA.gecko||UA.webkit||UA.opera){if((mF=navigator.mimeTypes['application/x-shockwave-flash'])){if((eP=mF.enabledPlugin)){var vS=[];vS=eP.description.replace(/\s[rd]/g,'.').replace(/[A-Za-z\s]+/g,'').split('.');version=vS[0]+'.';switch((vS[2].toString()).length)
{case 1:version+="00";break;case 2:version+="0";break;}
version+=vS[2];version=parseFloat(version);}}}
else if(UA.ie){try
{var ax6=new ActiveXObject(sF+"."+sF+".6");ax6.AllowScriptAccess="always";}
catch(e)
{if(ax6!=null)
{version=6.0;}}
if(version==0){try
{var ax=new ActiveXObject(sF+"."+sF);var vS=[];vS=ax.GetVariable("$version").replace(/[A-Za-z\s]+/g,'').split(',');version=vS[0]+'.';switch((vS[2].toString()).length)
{case 1:version+="00";break;case 2:version+="0";break;}
version+=vS[2];version=parseFloat(version);}catch(e){}}}
UA.flash=version;YAHOO.util.SWFDetect={getFlashVersion:function(){return version;},isFlashVersionAtLeast:function(ver){return version>=ver;}};var Dom=YAHOO.util.Dom,Event=YAHOO.util.Event,SWFDetect=YAHOO.util.SWFDetect,Lang=YAHOO.lang,FLASH_CID="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000",FLASH_TYPE="application/x-shockwave-flash",FLASH_VER="10.22",EXPRESS_INSTALL_URL="http://fpdownload.macromedia.com/pub/flashplayer/update/current/swf/autoUpdater.swf?"+Math.random(),EVENT_HANDLER="YAHOO.widget.SWF.eventHandler",possibleAttributes={align:"",allowNetworking:"",allowScriptAccess:"",base:"",bgcolor:"",menu:"",name:"",quality:"",salign:"",scale:"",tabindex:"",wmode:""};YAHOO.widget.SWF=function(p_oElement,swfURL,p_oAttributes){this._queue=this._queue||[];this._events=this._events||{};this._configs=this._configs||{};this._id=Dom.generateId(null,"yuiswf");var _id=this._id;var oElement=Dom.get(p_oElement);var flashVersion=(p_oAttributes["version"]||FLASH_VER);var isFlashVersionRight=SWFDetect.isFlashVersionAtLeast(flashVersion);var canExpressInstall=(UA.flash>=8.0);var shouldExpressInstall=canExpressInstall&&!isFlashVersionRight&&p_oAttributes["useExpressInstall"];var flashURL=(shouldExpressInstall)?EXPRESS_INSTALL_URL:swfURL;var objstring='<object ';var w,h;var flashvarstring="YUISwfId="+_id+"&YUIBridgeCallback="+EVENT_HANDLER+"&";YAHOO.widget.SWF._instances[_id]=this;if(oElement&&(isFlashVersionRight||shouldExpressInstall)&&flashURL){objstring+='id="'+_id+'" ';if(UA.ie){objstring+='classid="'+FLASH_CID+'" '}
else{objstring+='type="'+FLASH_TYPE+'" data="'+flashURL+'" ';}
w="100%";h="100%";objstring+='width="'+w+'" height="'+h+'">';if(UA.ie){objstring+='<param name="movie" value="'+flashURL+'"/>';}
for(var attribute in p_oAttributes.fixedAttributes){if(possibleAttributes.hasOwnProperty(attribute)){objstring+='<param name="'+attribute+'" value="'+p_oAttributes.fixedAttributes[attribute]+'"/>';}}
for(var flashvar in p_oAttributes.flashVars){var fvar=p_oAttributes.flashVars[flashvar];if(Lang.isString(fvar)){flashvarstring+="&"+flashvar+"="+encodeURIComponent(fvar);}}
if(flashvarstring){objstring+='<param name="flashVars" value="'+flashvarstring+'"/>';}
objstring+="</object>";oElement.innerHTML=objstring;}
YAHOO.widget.SWF.superclass.constructor.call(this,Dom.get(_id));this._swf=Dom.get(_id);};YAHOO.widget.SWF._instances=YAHOO.widget.SWF._instances||{};YAHOO.widget.SWF.eventHandler=function(swfid,event){YAHOO.widget.SWF._instances[swfid]._eventHandler(event);};YAHOO.extend(YAHOO.widget.SWF,YAHOO.util.Element,{_eventHandler:function(event)
{if(event.type=="swfReady"){this.createEvent("swfReady");this.fireEvent("swfReady",event);}
else{this.fireEvent(event.type,event);}},callSWF:function(func,args)
{if(this._swf[func]){return(this._swf[func].apply(this._swf,args));}else{return null;}}});})();YAHOO.register("swf",YAHOO.widget.SWF,{version:"2.8.0r4",build:"2449"});