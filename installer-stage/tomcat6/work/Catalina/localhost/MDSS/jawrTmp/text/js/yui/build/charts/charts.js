
YAHOO.widget.Chart=function(type,containerId,dataSource,configurationAttributes)
{this._type=type;this._dataSource=dataSource;var possibleParams={align:"",allowNetworking:"",allowScriptAccess:"",base:"",bgcolor:"",menu:"",name:"",quality:"",salign:"",scale:"",tabindex:"",wmode:""};var attributes={fixedAttributes:{allowScriptAccess:"always"},flashVars:{allowedDomain:document.location.hostname},backgroundColor:"#ffffff",host:this,version:9.045};for(var i in configurationAttributes)
{if(possibleParams.hasOwnProperty(i))
{attributes.fixedAttributes[i]=configurationAttributes[i];}
else
{attributes[i]=configurationAttributes[i];}}
this._id=attributes.id=attributes.id||YAHOO.util.Dom.generateId(null,"yuigen");if(attributes.version&&attributes.version!=null&&attributes.version!=undefined&&attributes.version!="undefined")
{var version=(/\w*.\w*/.exec(((attributes.version).toString()).replace(/.0./g,"."))).toString();var verSplit=version.split(".");version=verSplit[0]+".";switch((verSplit[1].toString()).length)
{case 1:version+="00";break;case 2:version+="0";break;}
version+=verSplit[1];attributes.version=parseFloat(version);}
this._swfURL=YAHOO.widget.Chart.SWFURL;this._containerID=containerId;this._attributes=attributes
this._swfEmbed=new YAHOO.widget.SWF(containerId,YAHOO.widget.Chart.SWFURL,attributes);this._swf=this._swfEmbed.swf;this._swfEmbed.subscribe("swfReady",this._eventHandler,this,true);try
{this.createEvent("contentReady");}
catch(e){}
this.createEvent("itemMouseOverEvent");this.createEvent("itemMouseOutEvent");this.createEvent("itemClickEvent");this.createEvent("itemDoubleClickEvent");this.createEvent("itemDragStartEvent");this.createEvent("itemDragEvent");this.createEvent("itemDragEndEvent");};YAHOO.extend(YAHOO.widget.Chart,YAHOO.util.AttributeProvider,{_type:null,_pollingID:null,_pollingInterval:null,_dataTipFunction:null,_legendLabelFunction:null,_seriesFunctions:null,toString:function()
{return"Chart "+this._id;},setStyle:function(name,value)
{value=YAHOO.lang.JSON.stringify(value);this._swf.setStyle(name,value);},setStyles:function(styles)
{styles=YAHOO.lang.JSON.stringify(styles);this._swf.setStyles(styles);},setSeriesStyles:function(styles)
{for(var i=0;i<styles.length;i++)
{styles[i]=YAHOO.lang.JSON.stringify(styles[i]);}
this._swf.setSeriesStyles(styles);},destroy:function()
{if(this._dataSource!==null)
{if(this._pollingID!==null)
{this._dataSource.clearInterval(this._pollingID);this._pollingID=null;}}
if(this._dataTipFunction)
{YAHOO.widget.Chart.removeProxyFunction(this._dataTipFunction);}
if(this._legendLabelFunction)
{YAHOO.widget.Chart.removeProxyFunction(this._legendLabelFunction);}
if(this._swf)
{var container=YAHOO.util.Dom.get(this._containerID);container.removeChild(this._swf);}
var instanceName=this._id;for(var prop in this)
{if(YAHOO.lang.hasOwnProperty(this,prop))
{this[prop]=null;}}},_initAttributes:function(attributes)
{this.setAttributeConfig("altText",{method:this._setAltText,getter:this._getAltText});this.setAttributeConfig("swfURL",{getter:this._getSWFURL});this.setAttributeConfig("request",{method:this._setRequest,getter:this._getRequest});this.setAttributeConfig("dataSource",{method:this._setDataSource,getter:this._getDataSource});this.setAttributeConfig("series",{method:this._setSeriesDefs,getter:this._getSeriesDefs});this.setAttributeConfig("categoryNames",{validator:YAHOO.lang.isArray,method:this._setCategoryNames,getter:this._getCategoryNames});this.setAttributeConfig("dataTipFunction",{method:this._setDataTipFunction,getter:this._getDataTipFunction});this.setAttributeConfig("legendLabelFunction",{method:this._setLegendLabelFunction,getter:this._legendLabelFunction});this.setAttributeConfig("polling",{method:this._setPolling,getter:this._getPolling});},_eventHandler:function(event)
{if(event.type=="swfReady")
{this._swf=this._swfEmbed._swf;this._loadHandler();this.fireEvent("contentReady");}},_loadHandler:function()
{if(!this._swf||!this._swf.setType)return;this._swf.setType(this._type);if(this._attributes.style)
{var style=this._attributes.style;this.setStyles(style);}
this._initialized=false;this._initAttributes(this._attributes);this.setAttributes(this._attributes,true);this._initialized=true;if(this._dataSource)
{this.set("dataSource",this._dataSource);}},refreshData:function()
{if(!this._initialized)
{return;}
if(this._dataSource!==null)
{if(this._pollingID!==null)
{this._dataSource.clearInterval(this._pollingID);this._pollingID=null;}
if(this._pollingInterval>0)
{this._pollingID=this._dataSource.setInterval(this._pollingInterval,this._request,this._loadDataHandler,this);}
this._dataSource.sendRequest(this._request,this._loadDataHandler,this);}},_loadDataHandler:function(request,response,error)
{if(this._swf)
{if(error)
{}
else
{var i;if(this._seriesFunctions)
{var count=this._seriesFunctions.length;for(i=0;i<count;i++)
{YAHOO.widget.Chart.removeProxyFunction(this._seriesFunctions[i]);}
this._seriesFunctions=null;}
this._seriesFunctions=[];var dataProvider=[];var seriesCount=0;var currentSeries=null;if(this._seriesDefs!==null)
{seriesCount=this._seriesDefs.length;for(i=0;i<seriesCount;i++)
{currentSeries=this._seriesDefs[i];var clonedSeries={};for(var prop in currentSeries)
{if(YAHOO.lang.hasOwnProperty(currentSeries,prop))
{if(prop=="style")
{if(currentSeries.style!==null)
{clonedSeries.style=YAHOO.lang.JSON.stringify(currentSeries.style);}}
else if(prop=="labelFunction")
{if(currentSeries.labelFunction!==null)
{clonedSeries.labelFunction=YAHOO.widget.Chart.getFunctionReference(currentSeries.labelFunction);this._seriesFunctions.push(clonedSeries.labelFunction);}}
else if(prop=="dataTipFunction")
{if(currentSeries.dataTipFunction!==null)
{clonedSeries.dataTipFunction=YAHOO.widget.Chart.getFunctionReference(currentSeries.dataTipFunction);this._seriesFunctions.push(clonedSeries.dataTipFunction);}}
else if(prop=="legendLabelFunction")
{if(currentSeries.legendLabelFunction!==null)
{clonedSeries.legendLabelFunction=YAHOO.widget.Chart.getFunctionReference(currentSeries.legendLabelFunction);this._seriesFunctions.push(clonedSeries.legendLabelFunction);}}
else
{clonedSeries[prop]=currentSeries[prop];}}}
dataProvider.push(clonedSeries);}}
if(seriesCount>0)
{for(i=0;i<seriesCount;i++)
{currentSeries=dataProvider[i];if(!currentSeries.type)
{currentSeries.type=this._type;}
currentSeries.dataProvider=response.results;}}
else
{var series={type:this._type,dataProvider:response.results};dataProvider.push(series);}
try
{if(this._swf.setDataProvider)this._swf.setDataProvider(dataProvider);}
catch(e)
{this._swf.setDataProvider(dataProvider);}}}},_request:"",_getRequest:function()
{return this._request;},_setRequest:function(value)
{this._request=value;this.refreshData();},_dataSource:null,_getDataSource:function()
{return this._dataSource;},_setDataSource:function(value)
{this._dataSource=value;this.refreshData();},_seriesDefs:null,_getSeriesDefs:function()
{return this._seriesDefs;},_setSeriesDefs:function(value)
{this._seriesDefs=value;this.refreshData();},_getCategoryNames:function()
{return this._swf.getCategoryNames();},_setCategoryNames:function(value)
{this._swf.setCategoryNames(value);},_setDataTipFunction:function(value)
{if(this._dataTipFunction)
{YAHOO.widget.Chart.removeProxyFunction(this._dataTipFunction);}
if(value)
{this._dataTipFunction=value=YAHOO.widget.Chart.getFunctionReference(value);}
this._swf.setDataTipFunction(value);},_setLegendLabelFunction:function(value)
{if(this._legendLabelFunction)
{YAHOO.widget.Chart.removeProxyFunction(this._legendLabelFunction);}
if(value)
{this._legendLabelFunction=value=YAHOO.widget.Chart.getFunctionReference(value);}
this._swf.setLegendLabelFunction(value);},_getPolling:function()
{return this._pollingInterval;},_setPolling:function(value)
{this._pollingInterval=value;this.refreshData();},_swfEmbed:null,_swfURL:null,_containerID:null,_swf:null,_id:null,_initialized:false,_attributes:null,set:function(name,value)
{this._attributes[name]=value;YAHOO.widget.Chart.superclass.set.call(this,name,value);},_getSWFURL:function()
{return this._swfURL;},_getAltText:function()
{return this._swf.getAltText();},_setAltText:function(value)
{this._swf.setAltText(value);}});YAHOO.widget.Chart.proxyFunctionCount=0;YAHOO.widget.Chart.createProxyFunction=function(func,scope)
{var scope=scope||null;var index=YAHOO.widget.Chart.proxyFunctionCount;YAHOO.widget.Chart["proxyFunction"+index]=function()
{return func.apply(scope,arguments);};YAHOO.widget.Chart.proxyFunctionCount++;return"YAHOO.widget.Chart.proxyFunction"+index.toString();};YAHOO.widget.Chart.getFunctionReference=function(value)
{if(typeof value=="function")
{value=YAHOO.widget.Chart.createProxyFunction(value);}
else if(value.func&&typeof value.func=="function")
{var args=[value.func];if(value.scope&&typeof value.scope=="object")
{args.push(value.scope);}
value=YAHOO.widget.Chart.createProxyFunction.apply(this,args);}
return value;}
YAHOO.widget.Chart.removeProxyFunction=function(funcName)
{if(!funcName||funcName.indexOf("YAHOO.widget.Chart.proxyFunction")<0)
{return;}
funcName=funcName.substr(26);YAHOO.widget.Chart[funcName]=null;};YAHOO.widget.Chart.SWFURL="assets/charts.swf";YAHOO.widget.PieChart=function(containerId,dataSource,attributes)
{YAHOO.widget.PieChart.superclass.constructor.call(this,"pie",containerId,dataSource,attributes);};YAHOO.lang.extend(YAHOO.widget.PieChart,YAHOO.widget.Chart,{_initAttributes:function(attributes)
{YAHOO.widget.PieChart.superclass._initAttributes.call(this,attributes);this.setAttributeConfig("dataField",{validator:YAHOO.lang.isString,method:this._setDataField,getter:this._getDataField});this.setAttributeConfig("categoryField",{validator:YAHOO.lang.isString,method:this._setCategoryField,getter:this._getCategoryField});},_getDataField:function()
{return this._swf.getDataField();},_setDataField:function(value)
{this._swf.setDataField(value);},_getCategoryField:function()
{return this._swf.getCategoryField();},_setCategoryField:function(value)
{this._swf.setCategoryField(value);}});YAHOO.widget.CartesianChart=function(type,containerId,dataSource,attributes)
{YAHOO.widget.CartesianChart.superclass.constructor.call(this,type,containerId,dataSource,attributes);};YAHOO.lang.extend(YAHOO.widget.CartesianChart,YAHOO.widget.Chart,{_xAxisLabelFunctions:[],_yAxisLabelFunctions:[],destroy:function()
{this._removeAxisFunctions(this._xAxisLabelFunctions);this._removeAxisFunctions(this._yAxisLabelFunctions);YAHOO.widget.CartesianChart.superclass.destroy.call(this);},_initAttributes:function(attributes)
{YAHOO.widget.CartesianChart.superclass._initAttributes.call(this,attributes);this.setAttributeConfig("xField",{validator:YAHOO.lang.isString,method:this._setXField,getter:this._getXField});this.setAttributeConfig("yField",{validator:YAHOO.lang.isString,method:this._setYField,getter:this._getYField});this.setAttributeConfig("xAxis",{method:this._setXAxis});this.setAttributeConfig("xAxes",{method:this._setXAxes});this.setAttributeConfig("yAxis",{method:this._setYAxis});this.setAttributeConfig("yAxes",{method:this._setYAxes});this.setAttributeConfig("constrainViewport",{method:this._setConstrainViewport});},_getXField:function()
{return this._swf.getHorizontalField();},_setXField:function(value)
{this._swf.setHorizontalField(value);},_getYField:function()
{return this._swf.getVerticalField();},_setYField:function(value)
{this._swf.setVerticalField(value);},_getClonedAxis:function(value)
{var clonedAxis={};for(var prop in value)
{if(prop=="labelFunction")
{if(value.labelFunction&&value.labelFunction!==null)
{clonedAxis.labelFunction=YAHOO.widget.Chart.getFunctionReference(value.labelFunction);}}
else
{clonedAxis[prop]=value[prop];}}
return clonedAxis;},_removeAxisFunctions:function(axisFunctions)
{if(axisFunctions&&axisFunctions.length>0)
{var len=axisFunctions.length;for(var i=0;i<len;i++)
{if(axisFunctions[i]!==null)
{YAHOO.widget.Chart.removeProxyFunction(axisFunctions[i]);}}
axisFunctions=[];}},_setXAxis:function(value)
{if(value.position!="bottom"&&value.position!="top")value.position="bottom";this._removeAxisFunctions(this._xAxisLabelFunctions);value=this._getClonedAxis(value);this._xAxisLabelFunctions.push(value.labelFunction);this._swf.setHorizontalAxis(value);},_setXAxes:function(value)
{this._removeAxisFunctions(this._xAxisLabelFunctions);var len=value.length;for(var i=0;i<len;i++)
{if(value[i].position=="left")value[i].position="bottom";value[i]=this._getClonedAxis(value[i]);if(value[i].labelFunction)this._xAxisLabelFunctions.push(value[i].labelFunction);this._swf.setHorizontalAxis(value[i]);}},_setYAxis:function(value)
{this._removeAxisFunctions(this._yAxisLabelFunctions);value=this._getClonedAxis(value);this._yAxisLabelFunctions.push(value.labelFunction);this._swf.setVerticalAxis(value);},_setYAxes:function(value)
{this._removeAxisFunctions(this._yAxisLabelFunctions);var len=value.length;for(var i=0;i<len;i++)
{value[i]=this._getClonedAxis(value[i]);if(value[i].labelFunction)this._yAxisLabelFunctions.push(value[i].labelFunction);this._swf.setVerticalAxis(value[i]);}},_setConstrainViewport:function(value)
{this._swf.setConstrainViewport(value);},setSeriesStylesByIndex:function(index,style)
{style=YAHOO.lang.JSON.stringify(style);if(this._swf&&this._swf.setSeriesStylesByIndex)this._swf.setSeriesStylesByIndex(index,style);}});YAHOO.widget.LineChart=function(containerId,dataSource,attributes)
{YAHOO.widget.LineChart.superclass.constructor.call(this,"line",containerId,dataSource,attributes);};YAHOO.lang.extend(YAHOO.widget.LineChart,YAHOO.widget.CartesianChart);YAHOO.widget.ColumnChart=function(containerId,dataSource,attributes)
{YAHOO.widget.ColumnChart.superclass.constructor.call(this,"column",containerId,dataSource,attributes);};YAHOO.lang.extend(YAHOO.widget.ColumnChart,YAHOO.widget.CartesianChart);YAHOO.widget.BarChart=function(containerId,dataSource,attributes)
{YAHOO.widget.BarChart.superclass.constructor.call(this,"bar",containerId,dataSource,attributes);};YAHOO.lang.extend(YAHOO.widget.BarChart,YAHOO.widget.CartesianChart);YAHOO.widget.StackedColumnChart=function(containerId,dataSource,attributes)
{YAHOO.widget.StackedColumnChart.superclass.constructor.call(this,"stackcolumn",containerId,dataSource,attributes);};YAHOO.lang.extend(YAHOO.widget.StackedColumnChart,YAHOO.widget.CartesianChart);YAHOO.widget.StackedBarChart=function(containerId,dataSource,attributes)
{YAHOO.widget.StackedBarChart.superclass.constructor.call(this,"stackbar",containerId,dataSource,attributes);};YAHOO.lang.extend(YAHOO.widget.StackedBarChart,YAHOO.widget.CartesianChart);YAHOO.widget.Axis=function()
{};YAHOO.widget.Axis.prototype={type:null,reverse:false,labelFunction:null,labelSpacing:2,title:null};YAHOO.widget.NumericAxis=function()
{YAHOO.widget.NumericAxis.superclass.constructor.call(this);};YAHOO.lang.extend(YAHOO.widget.NumericAxis,YAHOO.widget.Axis,{type:"numeric",minimum:NaN,maximum:NaN,majorUnit:NaN,minorUnit:NaN,snapToUnits:true,stackingEnabled:false,alwaysShowZero:true,scale:"linear",roundMajorUnit:true,calculateByLabelSize:true,position:"left",adjustMaximumByMajorUnit:true,adjustMinimumByMajorUnit:true});YAHOO.widget.TimeAxis=function()
{YAHOO.widget.TimeAxis.superclass.constructor.call(this);};YAHOO.lang.extend(YAHOO.widget.TimeAxis,YAHOO.widget.Axis,{type:"time",minimum:null,maximum:null,majorUnit:NaN,majorTimeUnit:null,minorUnit:NaN,minorTimeUnit:null,snapToUnits:true,stackingEnabled:false,calculateByLabelSize:true});YAHOO.widget.CategoryAxis=function()
{YAHOO.widget.CategoryAxis.superclass.constructor.call(this);};YAHOO.lang.extend(YAHOO.widget.CategoryAxis,YAHOO.widget.Axis,{type:"category",categoryNames:null,calculateCategoryCount:false});YAHOO.widget.Series=function(){};YAHOO.widget.Series.prototype={type:null,displayName:null};YAHOO.widget.CartesianSeries=function()
{YAHOO.widget.CartesianSeries.superclass.constructor.call(this);};YAHOO.lang.extend(YAHOO.widget.CartesianSeries,YAHOO.widget.Series,{xField:null,yField:null,axis:"primary",showInLegend:true});YAHOO.widget.ColumnSeries=function()
{YAHOO.widget.ColumnSeries.superclass.constructor.call(this);};YAHOO.lang.extend(YAHOO.widget.ColumnSeries,YAHOO.widget.CartesianSeries,{type:"column"});YAHOO.widget.LineSeries=function()
{YAHOO.widget.LineSeries.superclass.constructor.call(this);};YAHOO.lang.extend(YAHOO.widget.LineSeries,YAHOO.widget.CartesianSeries,{type:"line"});YAHOO.widget.BarSeries=function()
{YAHOO.widget.BarSeries.superclass.constructor.call(this);};YAHOO.lang.extend(YAHOO.widget.BarSeries,YAHOO.widget.CartesianSeries,{type:"bar"});YAHOO.widget.PieSeries=function()
{YAHOO.widget.PieSeries.superclass.constructor.call(this);};YAHOO.lang.extend(YAHOO.widget.PieSeries,YAHOO.widget.Series,{type:"pie",dataField:null,categoryField:null,labelFunction:null});YAHOO.widget.StackedBarSeries=function()
{YAHOO.widget.StackedBarSeries.superclass.constructor.call(this);};YAHOO.lang.extend(YAHOO.widget.StackedBarSeries,YAHOO.widget.CartesianSeries,{type:"stackbar"});YAHOO.widget.StackedColumnSeries=function()
{YAHOO.widget.StackedColumnSeries.superclass.constructor.call(this);};YAHOO.lang.extend(YAHOO.widget.StackedColumnSeries,YAHOO.widget.CartesianSeries,{type:"stackcolumn"});YAHOO.register("charts",YAHOO.widget.Chart,{version:"2.8.0r4",build:"2449"});