
YAHOO.util.SWFStore=function(containerID,shareData,useCompression)
{var browser;var newValue;shareData=shareData.toString();useCompression=useCompression.toString();if(YAHOO.env.ua.ie)browser="ie";else if(YAHOO.env.ua.gecko)browser="gecko";else if(YAHOO.env.ua.webkit)browser="webkit";else if(YAHOO.env.ua.caja)browser="caja";else if(YAHOO.env.ua.opera)browser="opera";else browser="other";if(YAHOO.util.Cookie.get("swfstore")==null||YAHOO.util.Cookie.get("swfstore")=="null"||YAHOO.util.Cookie.get("swfstore")=="")
{newValue=Math.round(Math.random()*Math.PI*100000);YAHOO.util.Cookie.set("swfstore",newValue);}
else
{newValue=YAHOO.util.Cookie.get("swfstore");}
var params={version:9.115,useExpressInstall:false,fixedAttributes:{allowScriptAccess:"always",allowNetworking:"all",scale:"noScale"},flashVars:{shareData:shareData,browser:newValue,useCompression:useCompression}};this.embeddedSWF=new YAHOO.widget.SWF(containerID,YAHOO.util.SWFStore.SWFURL,params);this.createEvent("error");this.createEvent("quotaExceededError");this.createEvent("securityError");this.createEvent("save");this.createEvent("clear");this.createEvent("pending");this.createEvent("openingDialog");this.createEvent("inadequateDimensions");};YAHOO.extend(YAHOO.util.SWFStore,YAHOO.util.AttributeProvider,{on:function(type,listener)
{this.embeddedSWF.addListener(type,listener);},addListener:function(type,listener)
{this.embeddedSWF.addListener(type,listener);},toString:function()
{return"SWFStore "+this._id;},getShareData:function()
{return this.embeddedSWF.callSWF("getShareData");},setShareData:function(value)
{this.embeddedSWF.callSWF("setShareData",[value]);},hasAdequateDimensions:function()
{return this.embeddedSWF.callSWF("hasAdequateDimensions");},getUseCompression:function()
{return this.embeddedSWF.callSWF("getUseCompression");},setUseCompression:function(value)
{this.embeddedSWF.callSWF("setUseCompression",[value]);},setItem:function(location,data)
{return this.embeddedSWF.callSWF("setItem",[location,data]);},getValueAt:function(index)
{return this.embeddedSWF.callSWF("getValueAt",[index]);},getNameAt:function(index)
{return this.embeddedSWF.callSWF("getNameAt",[index]);},getValueOf:function(location)
{return this.embeddedSWF.callSWF("getValueOf",[location]);},getTypeOf:function(location)
{return this.embeddedSWF.callSWF("getTypeOf",[location]);},getTypeAt:function(index)
{return this.embeddedSWF.callSWF("getTypeAt",[index]);},getItems:function()
{return this.embeddedSWF.callSWF("getItems",[]);},removeItem:function(location)
{return this.embeddedSWF.callSWF("removeItem",[location]);},removeItemAt:function(index)
{return this.embeddedSWF.callSWF("removeItemAt",[index]);},getLength:function()
{return this.embeddedSWF.callSWF("getLength",[]);},clear:function()
{return this.embeddedSWF.callSWF("clear",[]);},calculateCurrentSize:function()
{return this.embeddedSWF.callSWF("calculateCurrentSize",[]);},getModificationDate:function()
{return this.embeddedSWF.callSWF("getModificationDate",[]);},setSize:function(value)
{var result=this.embeddedSWF.callSWF("setSize",[value]);return result;},displaySettings:function()
{this.embeddedSWF.callSWF("displaySettings",[]);}});YAHOO.util.SWFStore.SWFURL="swfstore.swf";YAHOO.register("swfstore",YAHOO.util.SWFStore,{version:"2.8.0r4",build:"2449"});YAHOO.register("swfstore",YAHOO.util.SWFStore,{version:"2.8.0r4",build:"2449"});