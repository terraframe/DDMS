
MDSS.QueryXML={DEBUG:true,Operator:{EQ:'EQ',GT:'GT',GE:'GE',LT:'LT',LE:'LE',CONTAINS_ANY:MDSS.Localized.CONTAINS_ANY},Functions:{SUM:'SUM',MIN:'MIN',MAX:'MAX',AVG:'AVG'},DateGroupOpts:{DATEGROUP_EPIWEEK:MDSS.Localized.DATEGROUP_EPIWEEK,DATEGROUP_MONTH:MDSS.Localized.DATEGROUP_MONTH,DATEGROUP_QUARTER:MDSS.Localized.DATEGROUP_QUARTER,DATEGROUP_YEAR:MDSS.Localized.DATEGROUP_YEAR,DATEGROUP_EPIYEAR:MDSS.Localized.DATEGROUP_EPIYEAR,DATEGROUP_SEASON:MDSS.Localized.DATEGROUP_SEASON},GROUP_BY_FUNCTION:'GB',COUNT_FUNCTION:'COUNT',RATIO_FUNCTION:'ratio_of_this_row_to_total_count',};MDSS.QueryXML.objectToXML=function(obj)
{function toXML(name,content)
{return'<'+name+'>'+content+'</'+name+'>'+(MDSS.QueryXML.DEBUG?'\n':'');}
var xml='';for(var key in obj)
{var value=obj[key];if(Mojo.Util.isObject(value))
{var fragment=MDSS.QueryXML.objectToXML(value);xml+=toXML(key,fragment);}
else if(Mojo.Util.isArray(value))
{var subXML='';for(var i=0;i<value.length;i++)
{var entry=value[i];subXML+=MDSS.QueryXML.objectToXML(entry);}
xml+=toXML(key,subXML);}
else
{xml+=toXML(key,obj[key]);}}
return xml;}
MDSS.QueryXML.Query=function()
{this._entities=new MDSS.QueryXML.Entities();this._select=new MDSS.QueryXML.Select();this._groupBy=new MDSS.QueryXML.GroupBy();this._having=new MDSS.QueryXML.Having();this._orderBy=new MDSS.QueryXML.OrderBy();}
MDSS.QueryXML.Query.prototype={addEntity:function(entity)
{this._entities.addEntity(entity.getAlias(),entity);},getEntity:function(alias)
{return this._entities.getEntity(alias);},getGroupBy:function()
{return this._groupBy;},addSelectable:function(key,selectable)
{this._select.addSelectable(key,selectable);},removeSelectable:function(key)
{this._select.removeSelectable(key);},getXML:function()
{var entitiesArray=this._entities.build();var selectArray=this._select.build();var groupByArray=this._groupBy.build();var havingArray=this._having.build();var orderByArray=this._orderBy.build();var obj={'query':[entitiesArray,selectArray,groupByArray,havingArray,orderByArray]};var xml='<?xml version=\"1.0\" encoding=\"UTF-8\"?>';xml+=MDSS.QueryXML.objectToXML(obj);return xml;}};MDSS.QueryXML.Entities=function()
{this._entityMap={};}
MDSS.QueryXML.Entities.prototype={addEntity:function(key,entity)
{this._entityMap[key]=entity;},getEntity:function(key,entity)
{return this._entityMap[key];},removeEntity:function(key)
{delete this._entityMap[key];},build:function()
{var entities=Mojo.Util.getValues(this._entityMap);var entitiesArray=[];for(var i=0;i<entities.length;i++)
{entitiesArray.push(entities[i].build());}
var obj={'entities':entitiesArray};return obj;}};MDSS.QueryXML.Entity=function(type,alias)
{this._type=type;this._alias=alias;this._condition=null;}
MDSS.QueryXML.Entity.prototype={getType:function(){return this._type;},getAlias:function(){return this._alias;},setCondition:function(condition){this._condition=condition;},getCondition:function(){return this._condition;},clearCondition:function(){this._condition=null;},build:function()
{var conditionObj=this._condition!=null?this._condition.build():'';var obj={'entity':{'type':this._type,'alias':this._alias,'criteria':conditionObj}};return obj;}};MDSS.QueryXML.CompositeCondition=function(component)
{this._component=component;}
MDSS.QueryXML.CompositeCondition.prototype={getComponent:function()
{return this._component;},build:function()
{var componentObj=this._component.build();var obj={'compositeCondition':componentObj};return obj;}}
MDSS.QueryXML.BasicCondition=function(selectable,operator,value)
{this._selectable=selectable;this._operator=operator;this._value=value;}
MDSS.QueryXML.BasicCondition.prototype={getSelectable:function(){return this._selectable;},getValue:function(){return this._value;},build:function()
{var selectableObj=this._selectable.build();var obj={'basicCondition':[selectableObj,{'operator':this._operator},{'value':this._value}]};return obj;}}
MDSS.QueryXML.Or=function()
{this._conditions={};}
MDSS.QueryXML.Or.prototype={getConditions:function(){return this._conditions;},getSize:function()
{return Mojo.Util.getKeys(this._conditions).length;},addCondition:function(key,condition)
{this._conditions[key]=condition;},getCondition:function(key)
{return this._conditions[key];},removeCondition:function(key)
{delete this._conditions[key];},build:function()
{var conditions=Mojo.Util.getValues(this._conditions);var conditionsArray=[];for(var i=0;i<conditions.length;i++)
{conditionsArray.push(conditions[i].build());}
var obj={'or':conditionsArray};return obj;}}
MDSS.QueryXML.And=function()
{this._conditions={};}
MDSS.QueryXML.And.prototype={getSize:function()
{return Mojo.Util.getKeys(this._conditions).length;},addCondition:function(key,condition)
{this._conditions[key]=condition;},getCondition:function(key)
{return this._conditions[key];},removeCondition:function(key)
{delete this._conditions[key];},build:function()
{var conditions=Mojo.Util.getValues(this._conditions);var conditionsArray=[];for(var i=0;i<conditions.length;i++)
{conditionsArray.push(conditions[i].build());}
var obj={'and':conditionsArray};return obj;}}
MDSS.QueryXML.Select=function()
{this._selectableMap={};}
MDSS.QueryXML.Select.prototype={addSelectable:function(key,selectable)
{this._selectableMap[key]=selectable;},removeSelectable:function(key)
{delete this._selectableMap[key];},getSelectable:function(key)
{return this._selectableMap[key];},getSelectableMap:function()
{return this._selectableMap();},build:function()
{var selectables=Mojo.Util.getValues(this._selectableMap);var selectablesArray=[];for(var i=0;i<selectables.length;i++)
{selectablesArray.push(selectables[i].build());}
var obj={'select':selectablesArray};return obj;}}
MDSS.QueryXML.Selectable=function(component)
{this._component=component;}
MDSS.QueryXML.Selectable.prototype={getComponent:function(){return this._component;},build:function()
{var componentObj=this._component.build();var obj={'selectable':componentObj};return obj;}}
MDSS.QueryXML.Attribute=function(entityAlias,name,userAlias,userDisplayLabel)
{this._entityAlias=entityAlias;this._name=name;this._userAlias=userAlias||'';this._userDisplayLabel=userDisplayLabel||'';}
MDSS.QueryXML.Attribute.prototype={getName:function(){return this._name;},getEntityAlias:function(){return this._entityAlias;},getUserAlias:function(){return this._userAlias;},build:function()
{var obj={'attribute':{'entityAlias':this._entityAlias,'name':this._name,'userAlias':this._userAlias,'userDisplayLabel':this._userDisplayLabel}};return obj;}}
MDSS.QueryXML.Sqlinteger=function(entityAlias,name,userAlias,userDisplayLabel,isAggregate)
{this._entityAlias=entityAlias;this._name=name;this._userAlias=userAlias||'';this._userDisplayLabel=userDisplayLabel||'';this._isAggregate=isAggregate||false;}
MDSS.QueryXML.Sqlinteger.prototype={getName:function(){return this._name;},getEntityAlias:function(){return this._entityAlias;},getUserAlias:function(){return this._userAlias;},build:function()
{var obj={'sqlinteger':{'name':this._name,'userAlias':this._userAlias,'userDisplayLabel':this._userDisplayLabel,'isaggregate':this._isAggregate}};return obj;}}
MDSS.QueryXML.Sqlfloat=function(entityAlias,name,userAlias,userDisplayLabel,isAggregate)
{this._entityAlias=entityAlias;this._name=name;this._userAlias=userAlias||'';this._userDisplayLabel=userDisplayLabel||'';this._isAggregate=isAggregate||false;}
MDSS.QueryXML.Sqlfloat.prototype={getName:function(){return this._name;},getEntityAlias:function(){return this._entityAlias;},getUserAlias:function(){return this._userAlias;},build:function()
{var obj={'sqlfloat':{'name':this._name,'userAlias':this._userAlias,'userDisplayLabel':this._userDisplayLabel,'isaggregate':this._isAggregate}};return obj;}}
MDSS.QueryXML.Sqldouble=function(entityAlias,name,userAlias,userDisplayLabel,isAggregate)
{this._entityAlias=entityAlias;this._name=name;this._userAlias=userAlias||'';this._userDisplayLabel=userDisplayLabel||'';this._isAggregate=isAggregate||false;}
MDSS.QueryXML.Sqldouble.prototype={getName:function(){return this._name;},getEntityAlias:function(){return this._entityAlias;},getUserAlias:function(){return this._userAlias;},build:function()
{var obj={'sqldouble':{'name':this._name,'userAlias':this._userAlias,'userDisplayLabel':this._userDisplayLabel,'isaggregate':this._isAggregate}};return obj;}}
MDSS.QueryXML.Sqlcharacter=function(entityAlias,name,userAlias,userDisplayLabel,isAggregate)
{this._entityAlias=entityAlias;this._name=name;this._userAlias=userAlias||'';this._userDisplayLabel=userDisplayLabel||'';this._isAggregate=isAggregate||false;}
MDSS.QueryXML.Sqlcharacter.prototype={getName:function(){return this._name;},getEntityAlias:function(){return this._entityAlias;},getUserAlias:function(){return this._userAlias;},build:function()
{var obj={'sqlcharacter':{'name':this._name,'userAlias':this._userAlias,'userDisplayLabel':this._userDisplayLabel,'isaggregate':this._isAggregate}};return obj;}}
MDSS.QueryXML.Sqldate=function(entityAlias,name,userAlias,userDisplayLabel)
{this._entityAlias=entityAlias;this._name=name;this._userAlias=userAlias||'';this._userDisplayLabel=userDisplayLabel||'';}
MDSS.QueryXML.Sqldate.prototype={getName:function(){return this._name;},getEntityAlias:function(){return this._entityAlias;},getUserAlias:function(){return this._userAlias;},build:function()
{var obj={'sqldate':{'name':this._name,'userAlias':this._userAlias,'userDisplayLabel':this._userDisplayLabel}};return obj;}}
MDSS.QueryXML.SUM=function(selectable,userAlias,userDisplayLabel)
{this._selectable=selectable;this._userAlias=userAlias;this._userDisplayLabel=userDisplayLabel||'';}
MDSS.QueryXML.SUM.prototype={getSelectable:function(){return this._selectable;},build:function()
{var selectableObj=this._selectable.build();var alias=this._userAlias!=null?this._userAlias:'';var obj={'sum':[selectableObj,{'userAlias':alias,'userDisplayLabel':this._userDisplayLabel}]};return obj;}}
MDSS.QueryXML.MIN=function(selectable,userAlias,userDisplayLabel)
{this._selectable=selectable;this._userAlias=userAlias;this._userDisplayLabel=userDisplayLabel||'';}
MDSS.QueryXML.MIN.prototype={getSelectable:function(){return this._selectable;},build:function()
{var selectableObj=this._selectable.build();var alias=this._userAlias!=null?this._userAlias:'';var obj={'min':[selectableObj,{'userAlias':alias,'userDisplayLabel':this._userDisplayLabel}]};return obj;}}
MDSS.QueryXML.MAX=function(selectable,userAlias,userDisplayLabel)
{this._selectable=selectable;this._userAlias=userAlias;this._userDisplayLabel=userDisplayLabel||'';}
MDSS.QueryXML.MAX.prototype={getSelectable:function(){return this._selectable;},build:function()
{var selectableObj=this._selectable.build();var alias=this._userAlias!=null?this._userAlias:'';var obj={'max':[selectableObj,{'userAlias':alias,'userDisplayLabel':this._userDisplayLabel}]};return obj;}}
MDSS.QueryXML.AVG=function(selectable,userAlias,userDisplayLabel)
{this._selectable=selectable;this._userAlias=userAlias;this._userDisplayLabel=userDisplayLabel||'';}
MDSS.QueryXML.AVG.prototype={getSelectable:function(){return this._selectable;},build:function()
{var selectableObj=this._selectable.build();var alias=this._userAlias!=null?this._userAlias:'';var obj={'avg':[selectableObj,{'userAlias':alias,'userDisplayLabel':this._userDisplayLabel}]};return obj;}}
MDSS.QueryXML.COUNT=function(selectable,userAlias,userDisplayLabel)
{this._selectable=selectable;this._userAlias=userAlias;this._userDisplayLabel=userDisplayLabel||'';}
MDSS.QueryXML.COUNT.prototype={getSelectable:function(){return this._selectable;},build:function()
{var selectableObj=this._selectable.build();var alias=this._userAlias!=null?this._userAlias:'';var obj={'count':[selectableObj,{'userAlias':alias,'userDisplayLabel':this._userDisplayLabel}]};return obj;}}
MDSS.QueryXML.GroupBy=function()
{this._selectableMap=[];}
MDSS.QueryXML.GroupBy.prototype={clearGroupBy:function(){this._selectableMap=[];},addSelectable:function(key,selectable)
{this._selectableMap[key]=selectable;},removeSelectable:function(key)
{delete this._selectableMap[key];},build:function()
{var selectables=Mojo.Util.getValues(this._selectableMap);var selectablesArray=[];for(var i=0;i<selectables.length;i++)
{selectablesArray.push(selectables[i].build());}
var obj={'groupby':selectablesArray};return obj;}};MDSS.QueryXML.Having=function()
{}
MDSS.QueryXML.Having.prototype={build:function()
{var obj={'having':''};return obj;}}
MDSS.QueryXML.OrderBy=function()
{}
MDSS.QueryXML.OrderBy.prototype={build:function()
{var obj={'orderby':''};return obj;}}
MDSS.Query={};MDSS.Query.Config=function(configJSON)
{this._config={selectedUniversals:{},criteriaEntities:{},terms:{},date_attribute:{}};if(configJSON!=null)
{var config=Mojo.Util.getObject(configJSON);Mojo.Util.copy(config,this._config);}};MDSS.Query.Config.prototype={setGeoAttributes:function(attributeKeys)
{Mojo.Iter.forEach(attributeKeys,function(key){this._config.selectedUniversals[key]=[];this._config.criteriaEntities[key]=[];},this);},setCriteriaEntities:function(attributeKey,entities)
{var ids=Mojo.Iter.map(entities,function(entity){return entity.getGeoEntityId();});this._config.criteriaEntities[attributeKey]=ids;},getCriteriaEntities:function(attributeKey)
{return this._config.criteriaEntities[attributeKey];},addSelectedUniversal:function(attributeKey,universal)
{this._config.selectedUniversals[attributeKey].push(universal);},getSelectedUniversals:function(attributeKey)
{return this._config.selectedUniversals[attributeKey];},clearSelectedUniversals:function(attributeKey)
{if(attributeKey)
{this._config.selectedUniversals[attributeKey]=[];}
else
{for(var key in this._config.selectedUniversals)
{this._config.selectedUniversals[key]=[];}}},setDateAttribute:function(value)
{this._config.date_attribute=value;},getDateAttribute:function(value)
{return(this._config.date_attribute);},setProperty:function(key,value)
{this._config[key]=value;},getProperty:function(key)
{return this._config[key];},addTerms:function(key,termIds)
{this.removeTerms(key);for(var i=0;i<termIds.length;i++)
{this._config.terms[key][termIds[i]]='';}},getTermDisplay:function(key,termId)
{return this._config.terms[key][termId];},removeTerms:function(key)
{this._config.terms[key]={};},getJSON:function()
{return Mojo.Util.getJSON(this._config);}};MDSS.Query.Parser=function(xml)
{this._xmlDoc;var p=new DOMParser();this._xmlDoc=p.parseFromString(xml,"text/xml");};MDSS.Query.Parser.prototype={_getValue:function(parent,tagName)
{var nodes=parent.getElementsByTagName(tagName)
var value=(nodes!=null&&nodes.length>0&&nodes[0].firstChild)?nodes[0].firstChild.nodeValue:'';return value;},parseSelectables:function(handlers)
{var select=this._xmlDoc.getElementsByTagName('select')[0];var children=select.childNodes;for(var i=0;i<children.length;i++)
{var child=children[i];if(child.nodeName!=='selectable')
{continue;}
var first=child.firstChild;if(Mojo.Util.isFunction(handlers[first.nodeName]))
{var entityAlias=this._getValue(first,'entityAlias');var attributeName=this._getValue(first,'name');var userAlias=this._getValue(first,'userAlias');handlers[first.nodeName](entityAlias,attributeName,userAlias);}}},parseCriteria:function(handlers)
{var criteria=this._xmlDoc.getElementsByTagName('criteria');if(criteria==null||criteria.length==0)
{return;}
for(var i=0;i<criteria.length;i++)
{var criteriaNode=criteria[i];var selectables=criteriaNode.getElementsByTagName('selectable');for(var j=0;j<selectables.length;j++)
{var selectable=selectables[j];var attribute=selectable.firstChild;if(Mojo.Util.isFunction(handlers[attribute.nodeName]))
{var entityAlias=this._getValue(selectable,'entityAlias');var attributeName=this._getValue(selectable,'name');var userAlias=this._getValue(selectable,'userAlias');if(attributeName==='parentGeoEntity')
{continue;}
var parent=selectable.parentNode;var operator=this._getValue(parent,'operator');var value=this._getValue(parent,'value');handlers[attribute.nodeName](entityAlias,attributeName,userAlias,operator,value);}}}}};MDSS.QueryPanel=function(queryClass,queryPanelId,mapPanelId,config)
{this._queryClass=queryClass;var minWidth=1250;var minHeight=500;var pWidth=(window.innerWidth-30)>minWidth?(window.innerWidth-30):minWidth;var pHeight=(window.innerHeight-160)>minHeight?(window.innerHeight-160):minHeight;this._queryLayout=new YAHOO.widget.Layout(queryPanelId,{height:pHeight,width:pWidth,units:[{position:'top',height:40,resize:false,body:'',gutter:'2'},{position:'left',width:220,resize:true,body:'',gutter:'0 5 0 2',scroll:true},{position:'bottom',height:40,body:'',gutter:'2'},{position:'center',body:'<div id="'+this.QUERY_DATA_TABLE+'"></div><div id="'+this.PAGINATION_SECTION+'"></div>',gutter:'0 2 0 0',scroll:false},{position:'right',width:150,body:'<div style="margin-left: 10px" id="'+this.QUERY_SUMMARY+'"></div>',resize:true,scroll:true,gutter:'0 5 0 2'}]});this._pWidth=pWidth;this._pHeight=pHeight;this._config=config;this._queryItems=[];this._dataTable=null;this._startDate=null;this._endDate=null;this._qTopUnit=null;this._qLeftUnit=null;this._qBottomUnit=null;this._qCenterUnit=null;this._qRightUnit=null;this._querySummary=null;this._uploadModal=null;this._availableQueries=[];this._queryList=null;this._currentSavedSearch=null;this._map=null;this._headerMenuBuilders={};this._queryMenuBuilders={};};MDSS.QueryPanel.prototype={QUERY_ITEMS:"queryItemsList",AVAILABLE_QUERY_LIST:"availableQueryList",QUERY_DATA_TABLE:"queryDataTable",PAGINATION_SECTION:"paginationSection",DATE_RANGE_DIV:"dateRange",DATE_GROUP_ID:"dateGroupSelection",START_DATE_RANGE:"startDateRange",END_DATE_RANGE:"endDateRange",GEO_ENTITY_PANEL_LIST:"geoEntityPanelList",COLUMNS_LIST:"columnsList",QUERY_SUMMARY:"querySummary",EDIT_VARIABLE_STYLE:"editVariableStyle",EDIT_DEFAULT_STYLE:"editDefaultStyle",setCurrentSavedSearch:function(savedSearch)
{this._currentSavedSearch=savedSearch;},getCurrentSavedSearch:function()
{return this._currentSavedSearch;},updateColumnLabel:function(key,prepend)
{var li=document.getElementById(key+"_summary");var prependEl=li.firstChild;prependEl.innerHTML=(prepend==='')?'':'('+prepend+') ';},getSelectedDisplayLabel:function(key)
{var li=document.getElementById(key+"_summary");var display=li.firstChild.textContent+li.firstChild.nextSibling.textContent;return display;},_addSelectedColumn:function(column)
{var ul=document.getElementById(this.COLUMNS_LIST);var li=document.createElement('li');li.id=column.getKey()+"_summary";if(column.attribute){var whereFilters=column.attribute._whereValues.filter(function(a){return a.checked;}).map(function(a){return('<li id= "'+a.uuid+'_summary" >'+a.text+'</li>');});li.innerHTML="<span></span>"+column.label+'<ul id="'+column.getKey()+'_whereValues">'+whereFilters.join('')+'</ul>';}else{li.innerHTML="<span></span>"+column.label+'<ul id="'+column.getKey()+'_whereValues"></ul>';}
ul.appendChild(li);},clearWhereCriteria:function(key)
{var whereValues=document.getElementById(key+"_whereValues");if(whereValues)
{whereValues.innerHTML='';}},addWhereCriteria:function(key,value,display)
{var id=key+'-'+value+'-where';var li=document.getElementById(id);if(li)
{return;}
var whereValues=document.getElementById(key+"_whereValues");li=document.createElement('li');li.id=id;li.innerHTML=display;whereValues.appendChild(li);},removeWhereCriteria:function(key,value)
{var id=key+'-'+value+'-where';var li=document.getElementById(id);if(li)
{li.parentNode.removeChild(li);}},_removeSelectedColumn:function(column)
{var ul=document.getElementById(this.COLUMNS_LIST);var li=document.getElementById(column.getKey()+"_summary");ul.removeChild(li);},addSelectedGeoEntities:function(attributeKey,displayLabel,geoEntities)
{var parent=document.getElementById(this.GEO_ENTITY_PANEL_LIST);var ulId=attributeKey+'_criteriaEntitiesUl';var spanId=attributeKey+'_criteriaEntitiesSpan';var ul=document.getElementById(ulId);var span=document.getElementById(spanId);if(geoEntities.length>0)
{if(ul)
{ul.innerHTML='';}
else
{ul=document.createElement('ul');ul.id=ulId;span=document.createElement('span');span.id=spanId;span.innerHTML=displayLabel;parent.appendChild(span);parent.appendChild(ul);}
var frag=document.createDocumentFragment();for(var i=0;i<geoEntities.length;i++)
{var geoEntityView=geoEntities[i];var li=document.createElement('li');li.innerHTML=MDSS.AbstractSelectSearch.formatDisplay(geoEntityView);frag.appendChild(li);}
ul.appendChild(frag);}
else if(ul)
{parent.removeChild(ul);parent.removeChild(span);}},addAvailableQuery:function(obj)
{this._availableQueries.push(obj);if(this._queryList!=null)
{var option=document.createElement('option');YAHOO.util.Dom.setAttribute(option,'value',obj.id);option.innerHTML=obj.name;this._queryList.appendChild(option);var el=document.getElementById(this._queryList.get('id'));el.selectedIndex=el.options.length-1;}},getStartDate:function()
{return this._startDate;},getStartDateCheck:function()
{return this._startDateRangeCheck;},getEndDate:function()
{return this._endDate;},getEndDateCheck:function()
{return this._endDateRangeCheck;},_buildDateRange:function()
{var dateRange=new YAHOO.util.Element(document.createElement('div'));dateRange.set('id',this.DATE_RANGE_DIV);var startLabel=document.createElement('span');startLabel.innerHTML=MDSS.Localized.Query.Start_Date;this._startDate=document.createElement('input');YAHOO.util.Dom.setAttribute(this._startDate,'type','text');this._startDate.id=this.START_DATE_RANGE;YAHOO.util.Dom.addClass(this._startDate,'DatePick');this._startDateRangeCheck=document.createElement('input');YAHOO.util.Dom.setAttribute(this._startDateRangeCheck,'type','checkbox');YAHOO.util.Dom.setAttribute(this._startDateRangeCheck,'id','start_date_range');var endLabel=document.createElement('span');endLabel.innerHTML=MDSS.Localized.Query.End_Date;this._endDate=document.createElement('input');YAHOO.util.Dom.setAttribute(this._endDate,'type','text');this._endDate.id=this.END_DATE_RANGE;YAHOO.util.Dom.addClass(this._endDate,'DatePick');this._endDateRangeCheck=document.createElement('input');YAHOO.util.Dom.setAttribute(this._endDateRangeCheck,'type','checkbox');YAHOO.util.Dom.setAttribute(this._endDateRangeCheck,'id','end_date_range');var toggleDatesSpan=document.createElement('span');toggleDatesSpan.innerHTML=MDSS.Localized.Toggle_Show;if(this._queryClass._dateAttribs){this._queryClass._buildDateAttributesSelect(dateRange);}
dateRange.appendChild(startLabel);dateRange.appendChild(this._startDateRangeCheck);dateRange.appendChild(this._startDate);dateRange.appendChild(endLabel);dateRange.appendChild(this._endDateRangeCheck);dateRange.appendChild(this._endDate);var dateGroupLabel=document.createElement('span');dateGroupLabel.innerHTML=MDSS.localize("Snap_To_Nearest");this._dateGroupBy=document.createElement('select');this._dateGroupBy.id=this.DATE_GROUP_ID;var options=[''];var keys=[''];options=options.concat(Mojo.Util.getValues(MDSS.QueryXML.DateGroupOpts));keys=keys.concat(Mojo.Util.getKeys(MDSS.QueryXML.DateGroupOpts));for(var j=0;j<options.length;j++)
{var optionEl=document.createElement('option');optionEl.innerHTML=options[j];YAHOO.util.Dom.setAttribute(optionEl,'value',keys[j]);this._dateGroupBy.appendChild(optionEl);}
dateRange.appendChild(dateGroupLabel);dateRange.appendChild(this._dateGroupBy);if(this._queryClass._geoEntityAttribs)
{this._queryClass._addGeoAttributes(dateRange);}
var body=new YAHOO.util.Element(this._qTopUnit.body);body.appendChild(dateRange);},disableDateCheck:function()
{if(this._startDate.value.length==0)
{if(this._startDateRangeCheck.checked)
{this._startDateRangeCheck.click();}
this._startDateRangeCheck.disabled=true;}
else
{this._startDateRangeCheck.disabled=false;}
if(this._endDate.value.length==0)
{if(this._endDateRangeCheck.checked)
{this._endDateRangeCheck.click();}
this._endDateRangeCheck.disabled=true;}
else
{this._endDateRangeCheck.disabled=false;}},_buildQueryItems:function()
{var ul=new YAHOO.util.Element(document.createElement('ul'));ul.set('id',this.QUERY_ITEMS);for(var i=0;i<this._queryItems.length;i++)
{var queryItem=this._queryItems[i];var li=document.createElement('li');var liE=new YAHOO.util.Element(li);if(Mojo.Util.isString(queryItem.html))
{li.innerHTML=queryItem.html;}
else
{li.appendChild(queryItem.html);}
if(Mojo.Util.isObject(queryItem.onclick))
{liE.on('click',queryItem.onclick.handler,queryItem.onclick.obj);}
liE.set('id',queryItem.id);ul.appendChild(li);if(Mojo.Util.isFunction(queryItem.menuBuilder))
{this._queryMenuBuilders[queryItem.id]=queryItem.menuBuilder;liE.addClass('contextMenuContainer');}}
var body=new YAHOO.util.Element(this._qLeftUnit.body);body.appendChild(ul);var menu=new YAHOO.widget.ContextMenu(this.QUERY_ITEMS+"_menu",{trigger:this.QUERY_ITEMS,lazyload:true,zindex:9999});menu.subscribe("beforeShow",this._queryMenuBeforeShow,{thisRef:this});menu.subscribe("triggerContextMenu",this._queryMenuTrigger,{thisRef:this});},_postRender:function()
{this._qTopUnit=this._queryLayout.getUnitByPosition('top');this._qLeftUnit=this._queryLayout.getUnitByPosition('left');this._qBottomUnit=this._queryLayout.getUnitByPosition('bottom');this._qCenterUnit=this._queryLayout.getUnitByPosition('center');this._qRightUnit=this._queryLayout.getUnitByPosition('right');this._buildButtons();this._buildDateRange();this._buildQueryItems();this._buildContentGrid();this._buildQuerySummary();YAHOO.util.Event.on(this.PAGINATION_SECTION,'click',this._paginationHandler,null,this);if(Mojo.Util.isFunction(this._config.postRender))
{this._config.postRender.call(this._queryClass);}},_buildQuerySummary:function()
{var html='<h3>'+MDSS.Localized.Columns+'</h3><ul id="'+this.COLUMNS_LIST+'"></ul>';html+='<h3>'+MDSS.Localized.Selected_Entities+'</h3><div id="'+this.GEO_ENTITY_PANEL_LIST+'"></div>';var querySummary=document.getElementById(this.QUERY_SUMMARY);querySummary.innerHTML=html;},_exportXLS:function(e,obj)
{if(Mojo.Util.isFunction(this._config.exportXLS))
{this._config.exportXLS.apply(this._queryClass,Mojo.Util.getValues(obj));}},_exportCSV:function(e,obj)
{if(Mojo.Util.isFunction(this._config.exportCSV))
{this._config.exportCSV.apply(this._queryClass,Mojo.Util.getValues(obj));}},_exportReport:function(e,obj)
{if(Mojo.Util.isFunction(this._config.exportReport))
{this._config.exportReport.apply(this._queryClass,Mojo.Util.getValues(obj));}},_buildCSVForm:function()
{var form=document.createElement('form');YAHOO.util.Dom.setAttribute(form,'method','POST');YAHOO.util.Dom.setAttribute(form,'target','messageFrame');var xmlInput=document.createElement('textarea');YAHOO.util.Dom.setAttribute(xmlInput,'name','queryXML');var config=document.createElement('input');YAHOO.util.Dom.setAttribute(config,'type','hidden');YAHOO.util.Dom.setAttribute(config,'name','config');var queryClassInput=document.createElement('input');YAHOO.util.Dom.setAttribute(queryClassInput,'type','hidden');YAHOO.util.Dom.setAttribute(queryClassInput,'name','queryClass');var searchIdInput=document.createElement('input');YAHOO.util.Dom.setAttribute(searchIdInput,'type','hidden');YAHOO.util.Dom.setAttribute(searchIdInput,'name','savedSearchId');var obj={form:form,xmlInput:xmlInput,config:config,searchIdInput:searchIdInput,queryClassInput:queryClassInput};var exportCSVButton=document.createElement('input');YAHOO.util.Dom.setAttribute(exportCSVButton,'type','button');YAHOO.util.Dom.setAttribute(exportCSVButton,'value',MDSS.Localized.Export_CSV);YAHOO.util.Dom.addClass(exportCSVButton,'queryButton');YAHOO.util.Event.on(exportCSVButton,'click',this._exportCSV,obj,this);form.appendChild(xmlInput);form.appendChild(config);form.appendChild(searchIdInput);form.appendChild(queryClassInput);document.getElementById('CSVFormContainer').appendChild(form);return exportCSVButton;},_buildReportForm:function()
{var form=document.createElement('form');YAHOO.util.Dom.setAttribute(form,'method','POST');YAHOO.util.Dom.setAttribute(form,'target','messageFrame');var xmlInput=document.createElement('textarea');YAHOO.util.Dom.setAttribute(xmlInput,'name','queryXML');var config=document.createElement('input');YAHOO.util.Dom.setAttribute(config,'type','hidden');YAHOO.util.Dom.setAttribute(config,'name','config');var searchIdInput=document.createElement('input');YAHOO.util.Dom.setAttribute(searchIdInput,'type','hidden');YAHOO.util.Dom.setAttribute(searchIdInput,'name','savedSearchId');var obj={form:form,xmlInput:xmlInput,config:config,searchIdInput:searchIdInput};var exportReportButton=document.createElement('input');YAHOO.util.Dom.setAttribute(exportReportButton,'type','button');YAHOO.util.Dom.setAttribute(exportReportButton,'value',MDSS.Localized.Export_Report);YAHOO.util.Dom.addClass(exportReportButton,'queryButton');YAHOO.util.Event.on(exportReportButton,'click',this._exportReport,obj,this);form.appendChild(xmlInput);form.appendChild(config);form.appendChild(searchIdInput);document.getElementById('ReportFormContainer').appendChild(form);return exportReportButton;},_buildXLSForm:function()
{var form=document.createElement('form');YAHOO.util.Dom.setAttribute(form,'method','POST');YAHOO.util.Dom.setAttribute(form,'target','messageFrame');var xmlInput=document.createElement('textarea');YAHOO.util.Dom.setAttribute(xmlInput,'name','queryXML');var config=document.createElement('input');YAHOO.util.Dom.setAttribute(config,'type','hidden');YAHOO.util.Dom.setAttribute(config,'name','config');var queryClassInput=document.createElement('input');YAHOO.util.Dom.setAttribute(queryClassInput,'type','hidden');YAHOO.util.Dom.setAttribute(queryClassInput,'name','queryClass');var searchIdInput=document.createElement('input');YAHOO.util.Dom.setAttribute(searchIdInput,'type','hidden');YAHOO.util.Dom.setAttribute(searchIdInput,'name','savedSearchId');var obj={form:form,xmlInput:xmlInput,config:config,searchIdInput:searchIdInput,queryClassInput:queryClassInput};var exportXLSButton=document.createElement('input');YAHOO.util.Dom.setAttribute(exportXLSButton,'type','button');YAHOO.util.Dom.setAttribute(exportXLSButton,'value',MDSS.Localized.Excel_Export_Nav);YAHOO.util.Dom.addClass(exportXLSButton,'queryButton');YAHOO.util.Event.on(exportXLSButton,'click',this._exportXLS,obj,this);form.appendChild(xmlInput);form.appendChild(config);form.appendChild(searchIdInput);form.appendChild(queryClassInput);document.getElementById('XLSFormContainer').appendChild(form);return exportXLSButton;},_uploadTemplateOnSubmit:function()
{var input=document.getElementById('savedSearchIdInput');input.value=this._currentSavedSearch!=null?this._currentSavedSearch.getSavedQueryId():'';return true;},_uploadTemplate:function()
{if(this._uploadModal==null)
{var formId='templateUploadForm';var action='dss.vector.solutions.query.QueryController.uploadTemplate.mojo';var html=MDSS.Localized.File_Upload_Status+":<br />";html+="<iframe name='templateIframe' id='templateIframe' style='height:65px; width:350px; margin-bottom: 15px'></iframe>";html+="<form action='"+action+"' enctype='multipart/form-data' target='templateIframe' id='"+formId+"' method='post'>";html+="<input type='hidden' name='savedSearchId' id='savedSearchIdInput' value='' />";html+="<input type='file' name='templateFile' /><br />";html+="<input type='submit' name='import' value='"+MDSS.Localized.Submit+"' />"
html+="</form>";this._uploadModal=new YAHOO.widget.Panel("uploadTemplateModal",{width:"400px",height:"400px",fixedcenter:true,close:true,draggable:false,zindex:8,modal:true,visible:true});var outer=document.createElement('div');var header=document.createElement('div');header.innerHTML='<h3>'+MDSS.Localized.Upload_Template+'</h3><hr />';outer.appendChild(header);var contentDiv=document.createElement('div');YAHOO.util.Dom.addClass(contentDiv,'innerContentModal');contentDiv.innerHTML=html;outer.appendChild(contentDiv);this._uploadModal.setBody(outer);this._uploadModal.render(document.body);YAHOO.util.Event.on(formId,'submit',this._uploadTemplateOnSubmit,null,this);}
else
{this._uploadModal.show();}},_buildButtons:function()
{var uploadTemplate=new YAHOO.util.Element(document.createElement('input'));uploadTemplate.set('type','button');uploadTemplate.set('value',MDSS.Localized.Upload_Template);uploadTemplate.set('id',"uploadTemplateButton");uploadTemplate.addClass('queryButton');uploadTemplate.on('click',this._uploadTemplate,{},this);var saveButton=new YAHOO.util.Element(document.createElement('input'));saveButton.set('type','button');saveButton.set('value',MDSS.Localized.Query.Save);saveButton.set('id',"saveQueryButton");saveButton.addClass('queryButton');saveButton.on('click',this._saveQuery,{},this);var saveAsButton=new YAHOO.util.Element(document.createElement('input'));saveAsButton.set('type','button');saveAsButton.set('value',MDSS.Localized.Query_Save_As);saveAsButton.set('id',"saveAsQueryButton");saveAsButton.addClass('queryButton');saveAsButton.on('click',this._saveQueryAs,{},this);var deleteButton=new YAHOO.util.Element(document.createElement('input'));deleteButton.set('type','button');deleteButton.set('value',MDSS.localize("Delete_Query"));deleteButton.set('id',this.LOAD_QUERY_BUTTON);deleteButton.addClass('queryButton');deleteButton.on('click',this._deleteQuery,{},this);this._queryList=new YAHOO.util.Element(document.createElement('select'));this._queryList.set('id',this.AVAILABLE_QUERY_LIST);this._queryList.addClass('queryList');var defaultOption=document.createElement('option');this._queryList.appendChild(defaultOption);for(var i=0;i<this._availableQueries.length;i++)
{var obj=this._availableQueries[i];var option=document.createElement('option');YAHOO.util.Dom.setAttribute(option,'value',obj.id);option.innerHTML=obj.name;this._queryList.appendChild(option);}
this._queryList.on('change',this._loadQuery,{},this);var exportReportButton=this._buildReportForm();var exportCSVButton=this._buildCSVForm();var exportXLSButton=this._buildXLSForm();var runButton=new YAHOO.util.Element(document.createElement('input'));runButton.set('type','button');runButton.set('value',MDSS.Localized.Query.Run);runButton.set('id',this.RUN_QUERY_BUTTON);runButton.addClass('queryButton');runButton.on('click',this._executeQuery,{},this);var rightDiv=new YAHOO.util.Element(document.createElement('div'));rightDiv.setStyle('float','right');rightDiv.appendChild(uploadTemplate);rightDiv.appendChild(exportReportButton);rightDiv.appendChild(exportCSVButton);rightDiv.appendChild(exportXLSButton);rightDiv.appendChild(runButton);var leftDiv=new YAHOO.util.Element(document.createElement('div'));leftDiv.setStyle('float','left');leftDiv.appendChild(this._queryList);leftDiv.appendChild(saveButton);leftDiv.appendChild(saveAsButton);leftDiv.appendChild(deleteButton);var qBottom=new YAHOO.util.Element(this._qBottomUnit.body);qBottom.appendChild(leftDiv);qBottom.appendChild(rightDiv);},_tableMenuTrigger:function(a,b,c)
{var oTarget=this.contextEventTarget;if(c.thisRef._getHeader(oTarget)==null)
{this.cancel();}},_queryMenuTrigger:function(a,b,c)
{var oTarget=this.contextEventTarget;if(c.thisRef._getListEntry(oTarget)==null)
{this.cancel();}},_getHeader:function(oTarget)
{var nodeName=oTarget.nodeName.toUpperCase();if(nodeName==='TH')
{return oTarget;}
else
{var parent=YAHOO.util.Dom.getAncestorByTagName(oTarget,"TH");if(parent!=null)
{return parent;}}
return null;},_getListEntry:function(oTarget)
{var nodeName=oTarget.nodeName.toUpperCase();if(YAHOO.util.Dom.hasClass(nodeName,'contextMenuContainer'))
{return oTarget;}
else
{var parent=YAHOO.util.Dom.getAncestorByClassName(oTarget,"contextMenuContainer");if(parent!=null)
{return parent;}}
return null;},_tableMenuBeforeShow:function(a,b,c)
{this.clearContent();var header=c.thisRef._getHeader(this.contextEventTarget);if(header!=null)
{var column=c.thisRef._dataTable.getColumn(header);var builder=c.thisRef._headerMenuBuilders[column!=null?column.getKey():''];var menuItems=builder!=null?builder(column):[];this.addItems(menuItems);}
else
{this.addItems([]);}
this.render();},_queryMenuBeforeShow:function(a,b,c)
{var cet=this.contextEventTarget
if(cet!=null)
{var liEntry=c.thisRef._getListEntry(cet);this.clearContent();if(liEntry!=null)
{var builder=c.thisRef._queryMenuBuilders[liEntry.id];var menuItems=builder!=null?builder(liEntry,cet):[];this.addItems(menuItems);}
else
{this.addItems([]);}
this.render();}},_buildContentGrid:function()
{var dataSource=new YAHOO.util.DataSource([]);dataSource.responseType=YAHOO.util.DataSource.TYPE_JSARRAY;dataSource.responseSchema={fields:[]};this._dataTable=new YAHOO.widget.DataTable(this.QUERY_DATA_TABLE,[],dataSource,{draggableColumns:true,resizeableColumns:true});this._dataTable.render();this._dataTable.subscribe("columnReorderEvent",function(){},this,true);var menu=new YAHOO.widget.ContextMenu(this.QUERY_DATA_TABLE+"_menu",{trigger:this.QUERY_DATA_TABLE,lazyload:true,zindex:9999});menu.subscribe("beforeShow",this._tableMenuBeforeShow,{thisRef:this});menu.subscribe("triggerContextMenu",this._tableMenuTrigger,{thisRef:this});},addQueryItem:function(menuObj)
{this._queryItems.push(menuObj);},insertColumn:function(column,menuBuilder)
{var attrib=column.attribute;column.resizeable=true;column=this._dataTable.insertColumn(column);column.attribute=attrib;if(Mojo.Util.isFunction(menuBuilder))
{this._headerMenuBuilders[column.getKey()]=menuBuilder;}
this._addSelectedColumn(column);return column;},removeColumn:function(column)
{this._dataTable.removeColumn(column);this._removeSelectedColumn(column);},getColumn:function(column)
{return this._dataTable.getColumn(column);},getColumnSet:function()
{return this._dataTable.getColumnSet();},setRowData:function(rowData)
{this._dataTable.addRows(rowData);},clearAllRecords:function()
{this._dataTable.deleteRows(0,this._dataTable.getRecordSet().getLength());},_loadQuery:function()
{if(Mojo.Util.isFunction(this._config.loadQuery))
{var queries=document.getElementById(this.AVAILABLE_QUERY_LIST);var savedSearchId=queries.options[queries.selectedIndex].value;if(savedSearchId)
{this._config.loadQuery.call(this._queryClass,savedSearchId);}
else
{this._queryClass._resetToDefault();this._queryClass._loadDefaultSearch();}}},_doDeleteQuery:function(savedSearchId,queries)
{var request=new MDSS.Request({queries:queries,thisRef:this,selectedIndex:queries.selectedIndex,onSuccess:function(deletedRow){this.queries.options[this.selectedIndex].selected=false;this.queries.options[0].selected=true;this.queries.options[this.selectedIndex]=null;this.thisRef._queryClass._resetToDefault();this.thisRef._queryClass._loadDefaultSearch();}});Mojo.Facade.deleteEntity(request,savedSearchId);},_deleteQuery:function()
{var queries=document.getElementById(this.AVAILABLE_QUERY_LIST);var savedSearchId=queries.options[queries.selectedIndex].value;if(savedSearchId)
{var doDel=Mojo.Util.bind(this,this._doDeleteQuery,savedSearchId,queries);MDSS.confirmModal(MDSS.Localized.Confirm_Delete_Query,doDel,function(){});}},_saveQuery:function()
{var queries=document.getElementById(this.AVAILABLE_QUERY_LIST);var savedSearchId=queries.options[queries.selectedIndex].value;if(savedSearchId)
{if(Mojo.Util.isFunction(this._config.saveQuery))
{this._config.saveQuery.call(this._queryClass);}}
else
{this._saveQueryAs();}},_saveQueryAs:function()
{if(Mojo.Util.isFunction(this._config.saveQueryAs))
{this._config.saveQueryAs.call(this._queryClass);}},_executeQuery:function()
{if(Mojo.Util.isFunction(this._config.executeQuery))
{this._config.executeQuery.call(this._queryClass);}},setPagination:function(count,pageNumber,pageSize)
{var pagination=new MDSS.Pagination(pageNumber,pageSize,count);var pages=pagination.getPages();var section=document.getElementById(this.PAGINATION_SECTION);section.innerHTML='';var frag=document.createDocumentFragment();for(var i=0;i<pages.length;i++)
{var page=pages[i];var span=document.createElement('span');YAHOO.util.Dom.addClass(span,'page');if(page.isLeft())
{span.innerHTML='...';}
else if(page.isRight())
{span.innerHTML='...';}
else if(page.isCurrentPage())
{span.innerHTML=page.getPageNumber();YAHOO.util.Dom.addClass(span,'currentPage');}
else
{span.innerHTML=page.getPageNumber();}
frag.appendChild(span);}
var countSpan=document.createElement('span');YAHOO.util.Dom.addClass(countSpan,'resultCount');var max=(pageNumber*pageSize);if(max>count)max=count;countSpan.innerHTML=" "+(((pageNumber-1)*pageSize)+1)+"-"+max+" "+MDSS.localize('Of')+" "+count;frag.appendChild(countSpan);section.appendChild(frag);},clearPagination:function()
{var section=document.getElementById(this.PAGINATION_SECTION);section.innerHTML='';},_paginationHandler:function(e)
{if(e.target.nodeName==='SPAN'&&Mojo.Util.isFunction(this._config.paginationHandler))
{var pageNumber=e.target.innerHTML;this._config.paginationHandler.call(this._queryClass,pageNumber);}},render:function()
{this._queryLayout.render();this._postRender();}};MDSS.Pagination=function(pageNumber,pageSize,count)
{this._pageNumber=pageNumber;this._pageSize=pageSize;this._count=count;this._pages=[];this.MAX_DISPLAY_PAGES=10;this.calculate();};MDSS.Pagination.prototype={calculate:function()
{if(this._count===0)
{return;}
if(this._pageSize==0||this._pageNumber==0)
{this._pageSize=this._count;this._pageNumber=1;}
var totalPages=parseInt(Math.ceil(this._count/this._pageSize));var l=Math.max(this._pageNumber-4,1);var u=Math.min(this._pageNumber+4,totalPages);var lowerBound=Math.max(1,Math.min(this._pageNumber-4,u-this.MAX_DISPLAY_PAGES));var upperBound=Math.min(Math.max(this._pageNumber+4,l+this.MAX_DISPLAY_PAGES),totalPages);if(lowerBound!=1)
{this._pages.push(new MDSS.Pagination.Page(false,1));if(lowerBound!=2)
{var page=new MDSS.Pagination.Page();page.markLeft();this._pages.push(page);}}
for(var i=lowerBound;i<=upperBound;i++)
{this._pages.push(new MDSS.Pagination.Page((this._pageNumber==i),i));}
if(upperBound!=totalPages)
{if(upperBound!=totalPages-1)
{var page=new MDSS.Pagination.Page();page.markRight();this._pages.push(page);}
this._pages.push(new MDSS.Pagination.Page(false,totalPages));}},getPages:function()
{return this._pages;}};MDSS.Pagination.Page=function(isCurrent,pageNumber)
{this._isCurrent=Mojo.Util.isBoolean(isCurrent)?isCurrent:false;this._pageNumber=Mojo.Util.isNumber(pageNumber)?pageNumber:0;this._isLeft=false;this._isRight=false;};MDSS.Pagination.Page.prototype={markLeft:function(){this._isLeft=true;},markRight:function(){this._isRight=true;},isLeft:function(){return this._isLeft;},isRight:function(){return this._isRight;},isCurrentPage:function(){return this._isCurrent;},getPageNumber:function(){return this._pageNumber;},};Mojo.Meta.newClass('MDSS.QueryBase',{IsAbstract:true,Constants:{GEO_ATTRIBUTES:'geoAttributes',DATE_ATTRIBUTES:'dateAttributes',TARGET:'target'},Instance:{initialize:function(queryList)
{this._queryPanel=new MDSS.QueryPanel(this,'queryPanel','mapPanel',{executeQuery:this.executeQuery,saveQuery:this.saveQuery,saveQueryAs:this.saveQueryAs,loadQuery:this.loadQuery,exportXLS:this.exportXLS,exportCSV:this.exportCSV,exportReport:this.exportReport,paginationHandler:this.paginationHandler,postRender:this.postRender});this._geoAttributes={};this._allPathQueries={};this._geoEntityTypes={};this._geoEntitySelectables={};this._criteriaEntities={};this._currentPage=1;this._startDate=null;this._endDate=null;this._dateGroupSelectables=[];this._config=new MDSS.Query.Config();this.PAGE_SIZE=15;this.ALL_PATHS="dss.vector.solutions.geo.AllPaths";var hideBound=Mojo.Util.bind(this,this._hideHandler);this._selectSearch=new MDSS.MultipleSelectSearch();this._selectSearch.setHideHandler(hideBound);this._selectSearch.setFilter('');this._defaults=[];this._browsers={};this._namespacedQueryType=queryList.namespacedType;var queries=queryList.queries;for(var i=0;i<queries.length;i++)
{this._queryPanel.addAvailableQuery(queries[i]);}},getGeoPicker:function()
{return this._selectSearch;},_toggleVisibility:function(toggle,element)
{YAHOO.util.Event.on(toggle,'click',function(e,obj){var el=obj.element;var toggle=obj.toggle;if(YAHOO.util.Dom.getStyle(el,'display')==='block')
{YAHOO.util.Dom.setStyle(el,'display','none');toggle.innerHTML=MDSS.Localized.Toggle_Show;}
else
{YAHOO.util.Dom.setStyle(el,'display','block');toggle.innerHTML=MDSS.Localized.Toggle_Hide;}},{toggle:toggle,element:element},this);},getBrowser:function(attribute)
{return this._browsers[(Mojo.Util.isString(attribute)?attribute:attribute.getKey())+'_li'];},clearBrowserTerms:function(attribute)
{var browser=this.getBrowser(attribute);if(browser)
{browser.clearTerms();}},_attachBrowser:function(elementId,handler,attribute,fieldClass,fieldAttribute,multipleSelect)
{var bound=null;if(Mojo.Util.isFunction(handler))
{bound=Mojo.Util.bind(this,handler);}
else
{bound=Mojo.emptyFunction;}
var browser=new MDSS.QueryBrowser(this,bound,attribute,fieldClass,fieldAttribute,multipleSelect);this._browsers[elementId]=browser;},getCurrentPage:function()
{return this._currentPage;},setCurrentPage:function(page)
{this._currentPage=page;},exportCSV:function(form,xmlInput,config,searchIdInput)
{var queryXML=this._constructQuery();var xml=queryXML.getXML();var savedSearchView=this._queryPanel.getCurrentSavedSearch();var savedSearchId=(savedSearchView!=null?savedSearchView.getSavedQueryId():"");var action=this._getExportCSVAction();form.action=action;xmlInput.innerHTML=xml;config.value=this._config.getJSON();searchIdInput.value=savedSearchId;form.submit();},_dateGroupHandler:function(e,group)
{var check=e.target;if(check.checked)
{var attribute=new MDSS.QueryXML.Sqlcharacter('',group,group.toLowerCase(),MDSS.QueryXML.DateGroupOpts[group]);var selectable=new MDSS.QueryXML.Selectable(attribute);this._dateGroupSelectables[group]=selectable;this._queryPanel.insertColumn({key:group.toLowerCase(),label:MDSS.QueryXML.DateGroupOpts[group]});}
else
{var column=this._queryPanel.getColumn(group.toLowerCase());this._queryPanel.removeColumn(column);this._dateGroupSelectables[group]=null;}},_dateSnapHandler:function(e)
{var option=e.target;var group=option.value;if(group!=='')
{var dateEl=this._queryPanel.getStartDate();this._snapDate(dateEl.value,dateEl,group,true);dateEl=this._queryPanel.getEndDate();this._snapDate(dateEl.value,dateEl,group,false);}
option.parentNode.selectedIndex=0;},_snapDate:function(date,targetEl,dateGroupPeriod,snapToFirstDay){date=MDSS.util.stripWhitespace(date);if(date.length==0){return;}
date=MDSS.Calendar.parseDate(date);var request=new MDSS.Request({el:targetEl,onSend:function(){},onComplete:function(){},onSuccess:function(result){this.el.value=MDSS.Calendar.getLocalizedString(result);}});switch(dateGroupPeriod)
{case'DATEGROUP_EPIWEEK':Mojo.$.dss.vector.solutions.general.EpiDate.snapToEpiWeek(request,date,snapToFirstDay);break;case'DATEGROUP_EPIYEAR':Mojo.$.dss.vector.solutions.general.EpiDate.snapToEpiYear(request,date,snapToFirstDay);break;case'DATEGROUP_MONTH':Mojo.$.dss.vector.solutions.general.EpiDate.snapToMonth(request,date,snapToFirstDay);break;case'DATEGROUP_QUARTER':Mojo.$.dss.vector.solutions.general.EpiDate.snapToQuarter(request,date,snapToFirstDay);break;case'DATEGROUP_YEAR':Mojo.$.dss.vector.solutions.general.EpiDate.snapToYear(request,date,snapToFirstDay);break;case'DATEGROUP_SEASON':Mojo.$.dss.vector.solutions.general.EpiDate.snapToSeason(request,date,snapToFirstDay);break;default:targetEl.set('value',MDSS.Calendar.getLocalizedString(result));}},exportReport:function(form,xmlInput,config,searchIdInput,queryTypeInput)
{var queryXML=this._constructQuery();var xml=queryXML.getXML();var savedSearchView=this._queryPanel.getCurrentSavedSearch();var savedSearchId=(savedSearchView!=null?savedSearchView.getSavedQueryId():"");var action=this._getExportReportAction();form.action=action;xmlInput.innerHTML=xml;config.value=this._config.getJSON();searchIdInput.value=savedSearchId;form.submit();},exportXLS:function(form,xmlInput,config,searchIdInput)
{var queryXML=this._constructQuery();var xml=queryXML.getXML();var savedSearchView=this._queryPanel.getCurrentSavedSearch();var savedSearchId=(savedSearchView!=null?savedSearchView.getSavedQueryId():"");var action=this._getExportXLSAction();form.action=action;xmlInput.innerHTML=xml;config.value=this._config.getJSON();searchIdInput.value=savedSearchId;form.submit();},resetQueryResults:function(query)
{var columnSet=this._queryPanel.getColumnSet();var columns=columnSet.keys;var resultSet=query.getResultSet();var jsonData=[];for(var i=0;i<resultSet.length;i++)
{var result=resultSet[i];var entry={};for(var j=0;j<columns.length;j++)
{var column=columns[j];var attr=column.getKey();var dto=result.getAttributeDTO(attr);var value=dto.getValue();if(dto instanceof AttributeDateDTO){value=MDSS.Calendar.getLocalizedString(value);}
else if(dto instanceof AttributeDecDTO){if(value!=null){value=value.toFixed(2);}}
else if(dto instanceof AttributeBooleanDTO){var displayValue=null;if(value===true)
{displayValue=query.getAttributeDTO(attr).getAttributeMdDTO().getPositiveDisplayLabel();}
else if(value===false)
{displayValue=query.getAttributeDTO(attr).getAttributeMdDTO().getNegativeDisplayLabel();}
if(Mojo.Util.isString(displayValue)&&displayValue!=='')
{value=displayValue;}}
entry[attr]=value;}
jsonData.push(entry);}
this._queryPanel.clearAllRecords();this._queryPanel.setRowData(jsonData);this._queryPanel.setPagination(query.getCount(),this.getCurrentPage(),this.PAGE_SIZE);},_loadDefaultSearch:function()
{var view=new Mojo.$.dss.vector.solutions.query.SavedSearchView();this._populateSearch(null,view);var request=new MDSS.Request({thisRef:this,onSuccess:function(savedSearchView){this.thisRef._queryPanel.setCurrentSavedSearch(savedSearchView);}});Mojo.$.dss.vector.solutions.query.SavedSearch.loadDefaultSearch(request,view);},postRender:function()
{YAHOO.util.Event.on(MDSS.QueryBase.TARGET,'click',this._displaySearch,null,this);var options=this._queryPanel._dateGroupBy.options;for(var i=0;i<options.length;i++)
{YAHOO.util.Event.on(options[i],'click',this._dateSnapHandler,'',this);}
var startCheck=this._queryPanel.getStartDateCheck();var endCheck=this._queryPanel.getEndDateCheck();YAHOO.util.Event.on(startCheck,'click',this.toggleDates,'START_DATE_RANGE',this);YAHOO.util.Event.on(endCheck,'click',this.toggleDates,'END_DATE_RANGE',this);var startDate=this._queryPanel.getStartDate();var endDate=this._queryPanel.getEndDate();this._defaults.push({element:startCheck,checked:false});this._defaults.push({element:endCheck,checked:false});this._defaults.push({element:startDate,value:''});this._defaults.push({element:endDate,value:''});this._customPostRender();this._loadDefaultSearch();},_customPostRender:function()
{},paginationHandler:function(pageNumber)
{this.setCurrentPage(pageNumber);this.executeQuery();},toggleDates:function(e,range)
{var check=e.target;if(check.checked)
{var attribute=new MDSS.QueryXML.Sqldate('',range,range.toLowerCase(),MDSS.localize(range));var selectable=new MDSS.QueryXML.Selectable(attribute);this._dateGroupSelectables[range]=selectable;this._queryPanel.insertColumn({key:range.toLowerCase(),label:MDSS.localize(range),});}
else
{var column=this._queryPanel.getColumn(range.toLowerCase());this._queryPanel.removeColumn(column);this._dateGroupSelectables[range]=null;}},_getExportXLSAction:{IsAbstract:true},_getExportCSVAction:{IsAbstract:true},_getExportReportAction:function()
{return'dss.vector.solutions.report.ReportController.generateReport.mojo';},_getCountDiv:function(that,divName,klass,useRatio){var visibleDiv=document.createElement('div');var labelDiv=document.createElement('div');YAHOO.util.Dom.addClass(labelDiv,'queryItemLabel');labelDiv.innerHTML=MDSS.localize(divName);var toggleDiv=document.createElement('div');YAHOO.util.Dom.addClass(toggleDiv,'clickable');YAHOO.util.Dom.addClass(toggleDiv,'queryItemLabel');toggleDiv.innerHTML=MDSS.Localized.Toggle_Show;visibleDiv.appendChild(labelDiv);visibleDiv.appendChild(toggleDiv);var visibleUl=document.createElement('ul');visibleUl.id="countDivLi";YAHOO.util.Dom.addClass(visibleUl,'gridList');YAHOO.util.Dom.setStyle(visibleUl,'clear','both');YAHOO.util.Dom.setStyle(visibleUl,'display','none');that._toggleVisibility(toggleDiv,visibleUl);visibleDiv.appendChild(visibleUl);var countAttribute=new MDSS.BasicAttribute({type:klass.CLASS,displayLabel:MDSS.QueryXML.COUNT_FUNCTION,attributeName:'id'},this);var countCheck=document.createElement('input');YAHOO.util.Dom.setAttribute(countCheck,'type','checkbox');YAHOO.util.Dom.setAttribute(countCheck,'id',countAttribute.getKey());YAHOO.util.Dom.addClass(countCheck,'uncheckMeOnQueryTypeSwitch');YAHOO.util.Event.on(countCheck,'click',that._toggleCount,countAttribute,that);this._defaults.push({element:countCheck,checked:false});var countSpan=document.createElement('span');countSpan.innerHTML=countAttribute.getDisplayLabel()+' (GB)';var li=document.createElement('li');li.appendChild(countCheck);li.appendChild(countSpan);visibleUl.appendChild(li);if(useRatio)
{var ratioAttribute=new MDSS.BasicAttribute({type:'sqlfloat',key:MDSS.QueryXML.RATIO_FUNCTION,displayLabel:'RATIO',attributeName:MDSS.QueryXML.RATIO_FUNCTION,isAggregate:true,},this);var ratioCheck=document.createElement('input');YAHOO.util.Dom.setAttribute(ratioCheck,'type','checkbox');YAHOO.util.Dom.setAttribute(ratioCheck,'id',ratioAttribute.getKey());YAHOO.util.Dom.addClass(ratioCheck,'uncheckMeOnQueryTypeSwitch');YAHOO.util.Event.on(ratioCheck,'click',that._toggleRatio,ratioAttribute,that);this._defaults.push({element:ratioCheck,checked:false});var ratioSpan=document.createElement('span');ratioSpan.innerHTML=ratioAttribute.getDisplayLabel()+' (GB)';var li=document.createElement('li');li.appendChild(ratioCheck);li.appendChild(ratioSpan);visibleUl.appendChild(li);}
var options=Mojo.Util.getValues(MDSS.QueryXML.DateGroupOpts);var keys=Mojo.Util.getKeys(MDSS.QueryXML.DateGroupOpts);for(var j=0;j<options.length;j++)
{var countCheck=document.createElement('input');YAHOO.util.Dom.setAttribute(countCheck,'type','checkbox');YAHOO.util.Dom.setAttribute(countCheck,'value',keys[j]);YAHOO.util.Dom.setAttribute(countCheck,'id',keys[j].toLowerCase());YAHOO.util.Dom.addClass(countCheck,'uncheckMeOnQueryTypeSwitch');YAHOO.util.Event.on(countCheck,'click',that._dateGroupHandler,keys[j],that);this._defaults.push({element:countCheck,checked:false});var countSpan=document.createElement('span');countSpan.innerHTML=options[j];var li=document.createElement('li');li.appendChild(countCheck);li.appendChild(countSpan);visibleUl.appendChild(li);}
return visibleDiv;},render:{IsAbstract:true},executeQuery:{IsAbstract:true},mapQuery:{IsAbstract:true},_reconstructSearch:function(view)
{var attributeKeys=Mojo.Util.getKeys(this._geoAttributes);var loaded={};for(var i=0;i<attributeKeys.length;i++)
{var attributeKey=attributeKeys[i];var entityCriteria=this._config.getCriteriaEntities(attributeKey);if(entityCriteria)
{for(var j=0;j<entityCriteria.length;j++)
{var id=entityCriteria[j];loaded[id]=null;}}}
var toLoad=Mojo.Util.getKeys(loaded);if(toLoad.length>0)
{var request=new MDSS.Request({thisRef:this,onSuccess:function(query)
{var results=query.getResultSet();var found=[];for(var i=0;i<results.length;i++)
{var view=results[i];var id=view.getGeoEntityId();loaded[id]=view;found.push(id);}
for(var i=0;i<attributeKeys.length;i++)
{var attributeKey=attributeKeys[i];var views=[];for(var j=0;j<found.length;j++)
{var view=loaded[found[j]];views.push(view);}
var selectedUniversals=this.thisRef._config.getSelectedUniversals(attributeKey);this.thisRef._hideHandler(views,selectedUniversals,attributeKey);}}});Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getAsViews(request,toLoad);}
else
{for(var i=0;i<attributeKeys.length;i++)
{var attributeKey=attributeKeys[i];var selectedUniversals=this._config.getSelectedUniversals(attributeKey);this._hideHandler([],selectedUniversals,attributeKey);}
MDSS.util.wait_for_ajax.hide();}},_delegateToOption:function(e,attribute)
{var select=e.target;var option=select.options[select.selectedIndex];this._fireClickOnOption(option);},_uncheckBox:function(check)
{var checkEl=Mojo.Util.isString(check)?document.getElementById(check):check;if(checkEl!=null&&checkEl.checked)
{checkEl.click();return true;}
else
{return false;}},_checkBox:function(check)
{var checkEl=Mojo.Util.isString(check)?document.getElementById(check):check;if(checkEl!=null&&!checkEl.checked)
{checkEl.click();return true;}
else
{return false;}},_chooseOption:function(option)
{var optionEl=Mojo.Util.isString(option)?document.getElementById(option):option;if(optionEl==null)
{return false;}
var select=optionEl.parentNode;var options=select.options;for(var i=0;i<options.length;i++)
{if(options[i].id===optionEl.id)
{select.selectedIndex=i;break;}}
this._fireClickOnOption(optionEl);return true;},_resetToDefault:function()
{var attributeKeys=Mojo.Util.getKeys(this._geoAttributes);for(var i=0;i<attributeKeys.length;i++)
{this._hideHandler([],[],attributeKeys[i]);}
var browsers=Mojo.Util.getValues(this.browsers);for(var i=0;i<browsers.length;i++)
{browsers[i].clearTerms();}
for(var i=0;i<this._defaults.length;i++)
{var obj=this._defaults[i];var element=obj.element;if(element.nodeName==='INPUT'&&element.type==='checkbox')
{var checked=obj.checked;if(element.checked!==checked)
{if(obj.bypass)
{element.checked=checked;}
else
{element.click();}}}
else if(element.nodeName==='INPUT'&&element.type==='text')
{var value=obj.value;element.value=value;}
else if(element.nodeName==='SELECT')
{var index=obj.index;if(!element.disabled)
{element.selectedIndex=index;this._fireClickOnOption(element.options[index]);}
if(!obj.active&&index===0)
{element.disabled=true;}}
if(obj.disabled)
{element.disabled=true;}}},_menuBuilder:function(outerLi,targetEl)
{var li=null;if(targetEl.nodeName==='LI')
{li=targetEl;}
else
{var parent=YAHOO.util.Dom.getAncestorByTagName(targetEl,"LI");if(parent!=null)
{li=parent;}}
if(li==null||!li.firstChild.checked)
{return[];}
var items=this._menus[li.id];if(items!=null&&items.length>0)
{var itemSorter=function(a,b){x=a.text.toUpperCase();y=b.text.toUpperCase();return x>y?1:x<y?-1:0;};return items.sort(itemSorter);}
var browser=this._browsers[li.id];if(browser)
{browser.openBrowser();return[];}
return[];},loadQuery:function(savedSearchId)
{var request=new MDSS.Request({thisRef:this,savedSearchId:savedSearchId,onComplete:function(){},onSuccess:function(savedSearchView){this.thisRef._resetToDefault();this.thisRef._config=new MDSS.Query.Config(savedSearchView.getConfig());this.thisRef._queryPanel.setCurrentSavedSearch(savedSearchView);this.thisRef._reconstructSearch(savedSearchView);this.thisRef._loadQueryState(savedSearchView);}});Mojo.$.dss.vector.solutions.query.SavedSearch.loadSearch(request,savedSearchId);},_loadQueryState:{IsAbstract:true},_fireClickOnOption:function(option)
{var evObj=document.createEvent('UIEvents');evObj.initUIEvent('click',true,true,window,1);option.dispatchEvent(evObj);},_cancelQueryListener:function(modal,params,action)
{modal.destroy();},saveQuery:function()
{var view=this._queryPanel.getCurrentSavedSearch();if(view!=null)
{this._populateSearch(null,view);}
var request=new MDSS.Request({onSuccess:function()
{}});Mojo.$.dss.vector.solutions.query.SavedSearch.updateSearch(request,view);},saveQueryAs:function()
{var controller=Mojo.$.dss.vector.solutions.query.QueryController;var request=new MDSS.Request({thisRef:this,controller:controller,onSuccess:function(html)
{var modal=this.thisRef._createModal(html,MDSS.Localized.Query.Save);var saved=Mojo.Util.bind(this.thisRef,this.thisRef._saveQueryListener,modal);var canceled=Mojo.Util.bind(this.thisRef,this.thisRef._cancelQueryListener,modal);this.controller.setSaveQueryListener(saved);this.controller.setCancelQueryListener(canceled);}});controller.newQuery(request);},_saveQueryListener:function(modal,params,action)
{var view=new Mojo.$.dss.vector.solutions.query.SavedSearchView();this._populateSearch(params!=null?params['savedQueryView.queryName']:null,view);var request=new MDSS.Request({thisRef:this,modal:modal,onSuccess:function(savedSearchView){this.thisRef._queryPanel.setCurrentSavedSearch(savedSearchView);var obj={id:savedSearchView.getSavedQueryId(),name:savedSearchView.getQueryName()};this.thisRef._queryPanel.addAvailableQuery(obj);this.modal.destroy();}});Mojo.$.dss.vector.solutions.query.SavedSearch.saveSearch(request,view);},_populateSearch:function(queryName,view)
{var queryXML=this._constructQuery();var xml=queryXML.getXML();var queryType=this._getQueryType();if(queryName!=null)
{view.setQueryName(queryName);}
view.setQueryXml(xml);view.setConfig(this._config.getJSON());view.setQueryType(queryType);},_getQueryType:function()
{return this._namespacedQueryType;},_constructQuery:function()
{var queryXML=new MDSS.QueryXML.Query();var attributeKeys=Mojo.Util.getKeys(this._allPathQueries);for(var i=0;i<attributeKeys.length;i++)
{var attributeKey=attributeKeys[i];var allPaths=this._allPathQueries[attributeKey];var or=new MDSS.QueryXML.Or();var criteriaEntities=this._criteriaEntities[attributeKey];for(var i=0;i<criteriaEntities.length;i++)
{var geoEntityView=criteriaEntities[i];var attribute=new MDSS.QueryXML.Attribute(allPaths.getAlias(),'parentGeoEntity');var selectable=new MDSS.QueryXML.Selectable(attribute);var geoIdCondition=new MDSS.QueryXML.BasicCondition(selectable,MDSS.QueryXML.Operator.EQ,geoEntityView.getGeoEntityId());or.addCondition(attributeKey+'__'+geoEntityView.getGeoEntityId()+'_'+i,geoIdCondition);}
var compositeCondition=new MDSS.QueryXML.CompositeCondition(or);allPaths.setCondition(compositeCondition);queryXML.addEntity(allPaths);}
var geoSelectables=Mojo.Util.getKeys(this._geoEntitySelectables);for(var i=0;i<geoSelectables.length;i++)
{var name=geoSelectables[i];var selectable=this._geoEntitySelectables[name];queryXML.addSelectable(name,selectable);}
var startDateEl=this._queryPanel.getStartDate();var startDate=MDSS.util.stripWhitespace(startDateEl.value);var endDateEl=this._queryPanel.getEndDate();var endDate=MDSS.util.stripWhitespace(endDateEl.value);this._endDate=null;this._startDate=null;startDate=MDSS.Calendar.getMojoDateString(startDate);endDate=MDSS.Calendar.getMojoDateString(endDate);if(this._dateAttributes){var dateAttrib=this._dateAttributes[this._dateAttributeSelect.value];var dateObj={'date_attribute':dateAttrib.attribute,'klass':dateAttrib.klass,'start':startDate,'end':endDate};this._config.setDateAttribute(dateObj);}
else
{if(startDate)
{var startDateCondition=new MDSS.QueryXML.BasicCondition(this._startDateSelectable,MDSS.QueryXML.Operator.GE,startDate);this._startDate=startDateCondition;}
if(endDate)
{var endDateCondition=new MDSS.QueryXML.BasicCondition(this._endDateSelectable,MDSS.QueryXML.Operator.LE,endDate);this._endDate=endDateCondition;}}
return queryXML;},_addUniversalEntity:function(attributeKey,geoEntityView)
{if(geoEntityView.getEntityType()==='dss.vector.solutions.geo.generated.Earth')
{return;}
var type=geoEntityView.getEntityType();var entityAlias=attributeKey+"__"+type;var namespace=attributeKey+'_';var geoEntityQuery=this._geoEntityTypes[namespace+type];if(!Mojo.Util.isObject(geoEntityQuery))
{this._geoEntityTypes[namespace+type]=new MDSS.QueryXML.Entity(type,entityAlias);}
var typeName=type.substring(type.lastIndexOf('.')+1).toLowerCase();var addColumn=Mojo.Util.bind(this,function(geoAttr,selectableMap){var columnKey=attributeKey.replace(/\./g,'_')+'__'+typeName+'_'+geoAttr;if(this._geoEntitySelectables[columnKey]==null)
{var obj={key:columnKey,label:(geoEntityView.getTypeDisplayLabel()+" "
+geoEntityView.getAttributeDTO(geoAttr).getAttributeMdDTO().getDisplayLabel()
+" ("+this._geoAttributes[attributeKey]+")")};var column=new YAHOO.widget.Column(obj);this._queryPanel.insertColumn(column);var attr=new MDSS.QueryXML.Attribute(entityAlias,geoAttr,columnKey,obj.label);var sel=new MDSS.QueryXML.Selectable(attr);this._geoEntitySelectables[columnKey]=sel;}});addColumn(geoEntityView.getEntityNameMd().getName());addColumn(geoEntityView.getGeoIdMd().getName());},_removeUniversalColumns:function(attributeKey)
{var keys=Mojo.Util.getKeys(this._geoEntityTypes);Mojo.Iter.forEach(keys,function(key){if(key.indexOf(attributeKey)!==-1)
{delete this._geoEntityTypes[key];}},this);keys=Mojo.Util.getKeys(this._geoEntitySelectables);Mojo.Iter.forEach(keys,function(key){if(key.indexOf(attributeKey.replace(/\./g,'_'))!==-1)
{var column=this._queryPanel.getColumn(key);if(column)
{this._queryPanel.removeColumn(column);}
delete this._geoEntitySelectables[key];}},this);},_hideHandler:function(criteriaEntities,selectedUniversals,currentAttribute)
{if(!currentAttribute)
{currentAttribute=this._getCurrentGeoAttribute();}
this._queryPanel.clearAllRecords();this._config.clearSelectedUniversals(currentAttribute);this._removeUniversalColumns(currentAttribute);for(var i=0;i<selectedUniversals.length;i++)
{var universal=selectedUniversals[i];var construct=Mojo.Meta.findClass(universal);var geoEntity=new construct();var geoEntityView=this._selectSearch._copyEntityToView(geoEntity);this._addUniversalEntity(currentAttribute,geoEntityView);this._config.addSelectedUniversal(currentAttribute,geoEntityView.getEntityType());}
this._criteriaEntities[currentAttribute]=criteriaEntities;if(criteriaEntities.length>0)
{var entityAlias=this.ALL_PATHS+'_'+currentAttribute;var allPaths=new MDSS.QueryXML.Entity(this.ALL_PATHS,entityAlias);this._allPathQueries[currentAttribute]=allPaths;}
else
{delete this._allPathQueries[currentAttribute];}
this._config.setCriteriaEntities(currentAttribute,criteriaEntities);var display=this._geoAttributes[currentAttribute];this._queryPanel.addSelectedGeoEntities(currentAttribute,display,criteriaEntities);},_getCurrentGeoAttribute:function()
{var target=document.getElementById(MDSS.QueryBase.GEO_ATTRIBUTES);if(target.nodeName==='INPUT')
{return target.value;}
else
{return target.options[target.selectedIndex].value;}},_addGeoAttributes:function(div)
{var attributes=this._geoEntityAttribs
var html;var attributeKeys=[];if(attributes.length>1)
{var html='<select id="'+MDSS.QueryBase.GEO_ATTRIBUTES+'">';for(var i=0;i<attributes.length;i++)
{var attribute=attributes[i];attributeKeys.push(attribute.keyName);this._geoAttributes[attribute.keyName]=attribute.display;this._criteriaEntities[attribute.keyName]=[];html+='<option value="'+attribute.keyName+'">'+attribute.display+'</option>';}
html+='</select>';}
else
{var attribute=attributes[0];attributeKeys.push(attribute.keyName);this._geoAttributes[attribute.keyName]=attribute.display;this._criteriaEntities[attribute.keyName]=[];html='<input type="hidden" value="'+attribute.keyName+'" id="'+MDSS.QueryBase.GEO_ATTRIBUTES+'" />';html+=attribute.display;}
var boundSearch=Mojo.Util.bind(this,this._displaySearch);var geospan=document.createElement('span');geospan.id=MDSS.QueryBase.GEO_ATTRIBUTES+'Span';geospan.innerHTML=html+' <img id="'+MDSS.QueryBase.TARGET+'" class="clickable" src="./imgs/icons/world.png"/>';div.appendChild(geospan);this._config.setGeoAttributes(attributeKeys);return html;},_displaySearch:function()
{var currentAttribute=this._getCurrentGeoAttribute();var selectedUniversals=this._config.getSelectedUniversals(currentAttribute);var criteria=this._criteriaEntities[currentAttribute];this._selectSearch.setSelectedUniversals(selectedUniversals);this._selectSearch.setCriteria(criteria);if(this._selectSearch!=null&&this._selectSearch.isInitialized())
{this._selectSearch.show();}
else
{this._selectSearch.render();}},_createModal:function(html,title,useLarge)
{var executable=MDSS.util.extractScripts(html);var html=MDSS.util.removeScripts(html);var modal=new YAHOO.widget.Panel("editQuery",{width:"400px",height:useLarge?"530px":"400px",fixedcenter:true,close:false,draggable:false,zindex:4,modal:true,visible:true});var outer=document.createElement('div');var header=document.createElement('div');header.innerHTML='<h3>'+title+'</h3><hr />';outer.appendChild(header);var contentDiv=document.createElement('div');YAHOO.util.Dom.addClass(contentDiv,(useLarge?'innerContentModalLarge':'innerContentModal'));contentDiv.innerHTML=html;outer.appendChild(contentDiv);modal.setBody(outer);modal.render(document.body);eval(executable);modal.bringToTop();return modal;}}});Mojo.Meta.newClass('MDSS.AbstractAttribute',{IsAbstract:true,Instance:{initialize:function(obj)
{this._type=obj.type;this._dtoType=obj.dtoType;if(obj.displayLabel)
{this._displayLabel=obj.displayLabel;}
else
{this._displayLabel=MDSS.localize(obj.key);}
this._attributeName=obj.attributeName;this._entityAlias=obj.entityAlias||this._type;this._whereValues=[];this._isAggregate=obj.isAggregate||false;this._isTerm=false;this._isEnum=false;if(obj.isTerm){this._isTerm=obj.isTerm;}
if(obj.isEnum){this._isEnum=obj.isEnum;}
if(obj.key)
{this._key=obj.key;}
else
{this._genKey();}},setTerm:function(isTerm)
{this._isTerm=isTerm;},getTerm:function()
{return this._isTerm;},_genKey:function()
{this._key=this._type.replace(/\./g,'_')+'__'+this._attributeName;},getKey:function()
{return this._key;},setKey:function(key)
{this._key=key;},getType:function()
{return this._type;},setType:function(type)
{this._type=type;},getDtoType:function()
{return this._dtoType;},getWhereValues:function()
{return this._type;},getAttributeName:function()
{return this._attributeName;},setAttributeName:function(attributeName)
{this._attributeName=attributeName;},getDisplayLabel:function()
{return this._displayLabel;},getEntityAlias:function()
{return this._entityAlias;},getColumnObject:function()
{return{key:this._key,label:this._displayLabel};},getSelectable:function(dereference,asClass)
{var attrName=this._attributeName;if(this._isTerm&&dereference)
{var attribute=new MDSS.QueryXML.Sqlcharacter(this._entityAlias,attrName+'_displayLabel',this._key,this._displayLabel);return new MDSS.QueryXML.Selectable(attribute);}
else if(this._isEnum&&dereference)
{var attribute=new MDSS.QueryXML.Sqlcharacter(this._entityAlias,attrName+'_displayLabel',this._key,this._displayLabel);return new MDSS.QueryXML.Selectable(attribute);}
else if(dereference)
{attrName=attrName+'.displayLabel.currentValue';}
var attribute;if(asClass!=null)
{attribute=new asClass(this._entityAlias,attrName,this._key);}
else
{attribute=new MDSS.QueryXML.Attribute(this._entityAlias,attrName,this._key,this._displayLabel);}
var selectable=new MDSS.QueryXML.Selectable(attribute);return selectable;}}});Mojo.Meta.newClass('MDSS.QueryBrowser',{Instance:{initialize:function(query,handler,attribute,fieldClass,fieldAttribute,multiSelect)
{this._query=query;this._fieldClass=fieldClass;this._fieldAttribute=fieldAttribute;this._multiSelect=multiSelect||false;this._attribute=attribute;this._handler=handler;this._browser=new MDSS.OntologyBrowser(this._multiSelect,this._fieldClass,this._fieldAttribute);this._browser.setHandler(this.setTermsHandler,this);this._terms=[];},addTerm:function(termId)
{this._terms.push(termId);},getAttribute:function()
{return this._attribute;},openBrowser:function()
{if(this._browser.isRendered())
{this._browser.reset();this._browser.show();}
else
{this._browser.render();}
this._browser.setSelection(this._terms);},clearTerms:function()
{this._query._config.removeTerms(this._attribute.getKey());this._terms=[];},getTerms:function()
{return this._terms;},getDisplay:function(termId)
{var display=this._browser.getDisplay(termId)
if(display)
{return display;}
else
{return this._query._config.getTermDisplay(this._attribute.getKey(),termId);}},setTermsHandler:function(terms)
{this._terms=Mojo.Iter.map(terms,function(term){return term.getTermId();});this._query._config.addTerms(this._attribute.getKey(),this._terms);if(this._handler)
{this._handler(this,terms);}}}});Mojo.Meta.newClass('MDSS.BasicAttribute',{Extends:MDSS.AbstractAttribute,Instance:{initialize:function(obj)
{this.$initialize(obj);}}});Mojo.Meta.newClass('MDSS.QueryBaseNew',{Extends:MDSS.QueryBase,Instance:{initialize:function(selectableGroups,queryList)
{this.$initialize(queryList);this._preconfiguredColumns=[];this._countSelectable=null;this._ratioSelectable=null;this._visibleSelectables={};this._whereOptions={};this._visibleAggregateSelectables={};this._exclusionClasses=[];this._showRatioSelectable=true;this._singleAndRangeAttributes=[];this._dataQueryFunction=Mojo.$.dss.vector.solutions.query.QueryBuilder.getQueryResults;this._mapQueryFunction=Mojo.$.dss.vector.solutions.query.QueryBuilder.mapQuery;this._xmlToValueQueryClass=this._mainQueryClass;this._menus={};this._menuItems={};this._buildQueryItems(selectableGroups);this._buildColumns();},_getExportXLSAction:function()
{return'dss.vector.solutions.query.QueryController.exportQueryToExcel.mojo';},exportXLS:function(form,xmlInput,config,searchIdInput,queryClassInput)
{var queryXML=this._constructQuery();var xml=queryXML.getXML();var savedSearchView=this._queryPanel.getCurrentSavedSearch();var savedSearchId=(savedSearchView!=null?savedSearchView.getSavedQueryId():"");var action=this._getExportXLSAction();form.action=action;xmlInput.innerHTML=xml;config.value=this._config.getJSON();searchIdInput.value=savedSearchId;queryClassInput.value=this._xmlToValueQueryClass;form.submit();},_getExportCSVAction:function()
{return'dss.vector.solutions.query.QueryController.exportQueryToCSV.mojo';},exportCSV:function(form,xmlInput,config,searchIdInput,queryClassInput)
{var queryXML=this._constructQuery();var xml=queryXML.getXML();var savedSearchView=this._queryPanel.getCurrentSavedSearch();var savedSearchId=(savedSearchView!=null?savedSearchView.getSavedQueryId():"");var action=this._getExportCSVAction();form.action=action;xmlInput.innerHTML=xml;config.value=this._config.getJSON();searchIdInput.value=savedSearchId;queryClassInput.value=this._xmlToValueQueryClass;form.submit();},executeQuery:function()
{var queryXML=this._constructQuery();var xml=queryXML.getXML();var request=new MDSS.Request({thisRef:this,onSuccess:function(query)
{this.thisRef.resetQueryResults(query);}});var debug=document.getElementById('debug_xml');if(debug){debug.value=xml;}
var page=this.getCurrentPage();this._dataQueryFunction(request,this._xmlToValueQueryClass,xml,this._config.getJSON(),'',true,page,this.PAGE_SIZE);},mapQuery:function()
{var queryXML=this._constructQuery(true);var xml=queryXML.getXML();var request=new MDSS.Request({thisRef:this,onSuccess:function(layers){var layersObj=Mojo.Util.getObject(layers);this.thisRef._queryPanel.createMap(layersObj);}});var layerIds=this._queryPanel.getSelectedLayers();var savedSearchView=this._queryPanel.getCurrentSavedSearch();var savedSearchId=(savedSearchView!=null?savedSearchView.getSavedQueryId():"");this._mapQueryFunction(request,this._mainQueryClass,xml,this._config.getJSON(),layerIds,savedSearchId);},_constructQuery:function(formapping)
{var queryXML=MDSS.QueryBase.prototype._constructQuery.call(this,formapping);var mainQuery=new MDSS.QueryXML.Entity(this._mainQueryClass,this._mainQueryClass);var addedEntities=[this._mainQueryClass];addedEntities=addedEntities.concat(this._commonQueryClasses);var conditions=[];var selNames=Mojo.Util.getKeys(this._visibleSelectables);for(var i=0;i<selNames.length;i++)
{var name=selNames[i];var selectable=this._visibleSelectables[name];queryXML.addSelectable(mainQuery.getAlias()+'_'+name,selectable);if(selectable.attribute)
{var t=selectable.attribute.getType();var n=selectable.attribute.getAttributeName().replace(/.displayLabel.currentValue/,'').replace(/.name/,'');var k=selectable.attribute.getKey().replace(/.displayLabel.currentValue/,'').replace(/.name/,'');if(t=='sqlcharacter')
{n=selectable.attribute.getAttributeName().replace(/_defaultLocale/,'');k=selectable.attribute.getKey().replace(/_defaultLocale/,'');var whereSelectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlcharacter('',n,k));}else if(t=='sqlfloat')
{var whereSelectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlfloat('',n,k));}else if(t=='sqldouble')
{var whereSelectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqldouble('',n,k));}else if(t=='sqlinteger')
{var whereSelectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlinteger('',n,k));}else
{var whereSelectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Attribute(t,n,k));if(addedEntities.indexOf(t)<0){var query=new MDSS.QueryXML.Entity(t,t);queryXML.addEntity(query);addedEntities.concat(t);}}
var items=this._menus[selectable.attribute.getKey()+'_li'];if(items)
{if(selectable.attribute.getDtoType()&&selectable.attribute.getDtoType().indexOf('AttributeEnumeration')!=-1)
{var enumIds=items.filter(function(a){return a.checked;}).map(function(a){return a.uuid;}).join(',');if(enumIds.length>0)
{var condition=new MDSS.QueryXML.BasicCondition(whereSelectable,MDSS.QueryXML.Operator.CONTAINS_ANY,enumIds);conditions.push(condition);}}else{var whereIds=items.filter(function(a){return a.checked&&a.uuid;}).map(function(a){if(a.uuid==='false')return 0;if(a.uuid==='true')return 1;return a.uuid;});if(whereIds.length==1)
{if(t=='sqlinteger')
{this._config.setProperty(k+'Criteria',whereIds[0]);}
else
{var condition=new MDSS.QueryXML.BasicCondition(whereSelectable,MDSS.QueryXML.Operator.EQ,whereIds[0]);conditions.push(condition);}}
if(whereIds.length>1)
{if(t=='sqlinteger')
{this._config.setProperty(k+'Criteria',whereIds[0]+' - '+whereIds[1]);}
else
{var orConds=new MDSS.QueryXML.Or();for(var idNum=0;idNum<whereIds.length;idNum++)
{var condition=new MDSS.QueryXML.BasicCondition(whereSelectable,MDSS.QueryXML.Operator.EQ,whereIds[idNum]);orConds.addCondition(('orCond'+i+idNum),condition);}
var composite=new MDSS.QueryXML.CompositeCondition(orConds);conditions.push(composite);}}}}
var queryBrowser=this.getBrowser(selectable.attribute);if(queryBrowser)
{var terms=queryBrowser.getTerms();if(terms.length>0)
{if(this._typeOverride&&this._typeOverride[selectable.attribute.getKey()])
{t=this._typeOverride[selectable.attribute.getKey()];}
var termClass='dss.vector.solutions.ontology.AllPaths';var termAlias=n+'__'+t.replace(/[.]/g,'_')+'__'+selectable.attribute.getKey();var termQuery=new MDSS.QueryXML.Entity(termClass,termAlias);queryXML.addEntity(termQuery);var termParent=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Attribute(termAlias,"parentTerm"));var or=new MDSS.QueryXML.Or();Mojo.Iter.forEach(terms,function(restrictorID){var restrictCondition=new MDSS.QueryXML.BasicCondition(termParent,MDSS.QueryXML.Operator.EQ,restrictorID);or.addCondition(restrictorID,restrictCondition);});var composite=new MDSS.QueryXML.CompositeCondition(or);termQuery.setCondition(composite);}}}}
var aggNames=Mojo.Util.getKeys(this._visibleAggregateSelectables);for(var i=0;i<aggNames.length;i++)
{var name=aggNames[i];var selectable=this._visibleAggregateSelectables[name];var t=selectable.getComponent().getSelectable().getComponent().getEntityAlias();if(addedEntities.indexOf(t)<0&&t.indexOf('dss.vector.solutions')==0){var query=new MDSS.QueryXML.Entity(t,t);queryXML.addEntity(query);addedEntities.concat(t);}
queryXML.addSelectable(mainQuery.getAlias()+'_'+name,selectable);}
if(this._startDate!=null||this._endDate!=null)
{var dateAnd=new MDSS.QueryXML.And();if(this._startDate!=null)
{dateAnd.addCondition(('StartDateRange'),this._startDate);}
if(this._endDate!=null)
{dateAnd.addCondition(('EndDateRange'),this._endDate);}
var composite=new MDSS.QueryXML.CompositeCondition(dateAnd);conditions.push(composite);}
var keys=Mojo.Util.getKeys(this._dateGroupSelectables);for(var i=0;i<keys.length;i++)
{var selectable=this._dateGroupSelectables[keys[i]];if(selectable!=null)
{queryXML.addSelectable(keys[i],selectable);}}
if(this._countSelectable!=null)
{queryXML.addSelectable(mainQuery.getAlias()+'_globalCount',this._countSelectable);}
if(this._ratioSelectable!=null)
{queryXML.addSelectable(MDSS.QueryXML.RATIO_FUNCTION,this._ratioSelectable);}
var where=null;if(conditions.length>0)
{var where=new MDSS.QueryXML.And();for(var i=0;i<conditions.length;i++)
{where.addCondition(('cond'+i),conditions[i]);}
var composite=new MDSS.QueryXML.CompositeCondition(where);mainQuery.setCondition(composite);}
queryXML.addEntity(mainQuery);addedEntities.map(function(klass){if(queryXML.getEntity(klass)==null)
{var query=new MDSS.QueryXML.Entity(klass,klass);queryXML.addEntity(query);}},this);return queryXML;},_addVisibleAttribute:function(attribute)
{var attributeName=attribute.getAttributeName();if(attribute.mainQueryClass)
{}
if(attribute.getType()=='sqlcharacter'){var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlcharacter('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;}else
if(attribute.getType()=='sqlinteger'){var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlinteger('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;}else
if(attribute.getType()=='sqlfloat'){var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlfloat('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;}else
if(attribute.getType()=='sqldouble'){var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqldouble('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;}
else
{var selectable=attribute.getSelectable(true);selectable.attribute=attribute;var column=new YAHOO.widget.Column(attribute.getColumnObject());column.attribute=attribute;}
column=this._queryPanel.insertColumn(column);this._visibleSelectables[attribute.getKey()]=selectable;},_removeVisibleAttribute:function(attribute,removeColumn,removeSelectable)
{var attributeName=attribute.getAttributeName();var key=attribute.getKey();if(removeSelectable)
{delete this._visibleSelectables[attribute.getKey()];}
delete this._visibleAggregateSelectables[attribute.getKey()];if(removeColumn)
{var column=this._queryPanel.getColumn(key);this._queryPanel.removeColumn(column);}},_visibleAttributeHandler:function(e,attribute)
{var check=e.target;var liTarget=YAHOO.util.Dom.getAncestorByTagName(check,"LI");if(check.checked)
{this._uncheckAllNotInGroup(check);this._addVisibleAttribute(attribute);var select=check.nextSibling;select.selectedIndex=0;select.disabled=false;}
else
{this._removeVisibleAttribute(attribute,true,true,true);var select=check.nextSibling;select.selectedIndex=0;select.disabled=true;var menus=this._menus[liTarget.id];if(menus)
{Mojo.Iter.forEach(menus,function(ck){if(ck.checked)ck.checked=false;},this);}
this.clearBrowserTerms(attribute);}},_uncheckAllNotInGroup:function(target)
{var uncheckClasses=this._exclusionClasses.filter(function(klass){return!YAHOO.util.Dom.hasClass(target,klass);});var queryTypeSwitched=uncheckClasses.filter(function(uncheckClass){return this._uncheckAllByClass(uncheckClass).length>0;},this);if(queryTypeSwitched.length>0)
{this._uncheckAllByClass('uncheckMeOnQueryTypeSwitch');}},_uncheckAllByClass:function(klass,root)
{return YAHOO.util.Dom.getElementsByClassName(klass,'input',root).filter(function(check){if(check.checked)
{if(YAHOO.util.Dom.hasClass(check,'selectAllCheck')){check.checked=false;}else{check.click();return true;}}
return false;});},_toggleCount:function(e,attribute)
{var check=e.target;attribute.setType(this._mainQueryClass);if(check.checked)
{var selectable=attribute.getSelectable();var count=new MDSS.QueryXML.COUNT(selectable,attribute.getKey());var aggSelectable=new MDSS.QueryXML.Selectable(count);this._countSelectable=aggSelectable;this._queryPanel.insertColumn(attribute.getColumnObject());}
else
{var column=this._queryPanel.getColumn(attribute.getKey());this._queryPanel.removeColumn(column);this._countSelectable=null;}},_toggleRatio:function(e,attribute)
{var check=e.target;if(check.checked)
{var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlcharacter('',attribute.getAttributeName(),attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;this._ratioSelectable=selectable;this._queryPanel.insertColumn(column);}
else
{var column=this._queryPanel.getColumn(attribute.getKey());this._queryPanel.removeColumn(column);this._ratioSelectable=null;}},_visibleAggregateHandler:function(e,attribute)
{var func=e.target.value;var attributeName=attribute.getAttributeName();var key=attribute.getKey();var selectable=attribute.getSelectable();if(attribute.getType()=='sqlinteger'){var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlinteger('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),false));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;}
if(attribute.getType()=='sqlfloat'){var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlfloat('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),false));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;}
if(attribute.getType()=='sqldouble'){var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqldouble('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),false));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;}
this._queryPanel.updateColumnLabel(key,func);if(func==='')
{this._removeVisibleAttribute(attribute,false,true,false);this._visibleSelectables[attribute.getKey()]=selectable;return;}
var aggFunc=null;var displayLabel="("+func+") "+attribute.getDisplayLabel();if(func===MDSS.QueryXML.Functions.SUM)
{aggFunc=new MDSS.QueryXML.SUM(selectable,key,displayLabel);}
else if(func===MDSS.QueryXML.Functions.MIN)
{aggFunc=new MDSS.QueryXML.MIN(selectable,key,displayLabel);}
else if(func===MDSS.QueryXML.Functions.MAX)
{aggFunc=new MDSS.QueryXML.MAX(selectable,key,displayLabel);}
else if(func===MDSS.QueryXML.Functions.AVG)
{aggFunc=new MDSS.QueryXML.AVG(selectable,key,displayLabel);}
this._removeVisibleAttribute(attribute,false,true,false);var aggSelectable=new MDSS.QueryXML.Selectable(aggFunc);this._visibleAggregateSelectables[attribute.getKey()]=aggSelectable;},_whereValueHandler:function(eventType,event,obj)
{var attribute=obj.attribute;var value=obj.value;var display=obj.display;var item=this._menuItems[attribute.getKey()+'-'+value];item.checked=!item.checked;if(item.checked)
{this._queryPanel.addWhereCriteria(attribute.getKey(),value,display);}
else
{this._queryPanel.removeWhereCriteria(attribute.getKey(),value);}},_loadQueryState:function(view)
{var thisRef=this;var dateRestrictions=thisRef._config.getDateAttribute();if(dateRestrictions)
{if(dateRestrictions.start)
{var start=thisRef._queryPanel.getStartDate();var formatted=MDSS.Calendar.getLocalizedString(dateRestrictions.start);start.value=formatted;}
if(dateRestrictions.end)
{var end=thisRef._queryPanel.getEndDate();var formatted=MDSS.Calendar.getLocalizedString(dateRestrictions.end);end.value=formatted;}}
var xml=view.getQueryXml();var parser=new MDSS.Query.Parser(xml);parser.parseSelectables({attribute:function(entityAlias,attributeName,userAlias){var checked=thisRef._checkBox(userAlias);var key=userAlias+'Criteria';var crit=thisRef._config.getProperty(key);if(checked&&crit){thisRef._queryPanel.addWhereCriteria(userAlias,crit,crit);if(crit.indexOf(' - ')>0)
{crit=crit.split(' - ');thisRef._toggleRange(userAlias,true,crit[0],crit[1]);}
else
{thisRef._toggleSingle(userAlias,true,crit);}}},sum:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.SUM);},min:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.MIN);},max:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.MAX);},avg:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.AVG);},count:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);},sqlcharacter:function(entityAlias,attributeName,userAlias){var key=userAlias+'_li';var browser=thisRef._browsers[key];if(browser){thisRef._checkBox(userAlias);var termList=thisRef._config._config.terms[userAlias];for(var termId in termList){browser.addTerm(termId);attribute=browser.getAttribute();display=browser.getDisplay(termId);thisRef._queryPanel.addWhereCriteria(attribute.getKey(),termId,display);}}
else
{thisRef._checkBox(userAlias);}},sqlinteger:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);},sqldouble:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);},sqlfloat:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);},sqldate:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);}});parser.parseCriteria({attribute:function(entityAlias,attributeName,userAlias,operator,value){if(!!thisRef._dateAttribute&&userAlias===thisRef._dateAttribute.getUserAlias())
{var formatted=MDSS.Calendar.getLocalizedString(value);if(operator===MDSS.QueryXML.Operator.GE)
{var start=thisRef._queryPanel.getStartDate();start.value=formatted;}
else
{var end=thisRef._queryPanel.getEndDate();end.value=formatted;}}
else
{var item=thisRef._menuItems[userAlias+'-'+value];if(!item)
{item=(value==='1')?thisRef._menuItems[userAlias+'-true']:item;item=(value==='0')?thisRef._menuItems[userAlias+'-false']:item;}
if(item)
{item.checked=true;var attribute=item.onclick.obj.attribute;var display=item.onclick.obj.display;thisRef._queryPanel.addWhereCriteria(attribute.getKey(),value,display);}}}});},_resetToDefault:function()
{MDSS.QueryBase.prototype._resetToDefault.call(this);var keys=Mojo.Util.getKeys(this._menuItems);for(var i=0;i<keys.length;i++)
{var item=this._menuItems[keys[i]];item.checked=false;}
this._queryPanel.clearPagination();},_getVizDiv:function(that,visibleAttributes,divName,mainQueryClass,checkClass)
{var visibleDiv=document.createElement('div');var labelDiv=document.createElement('div');YAHOO.util.Dom.addClass(labelDiv,'queryItemLabel');labelDiv.innerHTML=MDSS.localize(divName);var toggleDiv=document.createElement('div');YAHOO.util.Dom.addClass(toggleDiv,'clickable');YAHOO.util.Dom.addClass(toggleDiv,'queryItemLabel');toggleDiv.innerHTML=MDSS.Localized.Toggle_Show;visibleDiv.appendChild(labelDiv);visibleDiv.appendChild(toggleDiv);var visibleUl=document.createElement('ul');visibleUl.id=divName+"Li";YAHOO.util.Dom.addClass(visibleUl,'gridList');YAHOO.util.Dom.setStyle(visibleUl,'clear','both');YAHOO.util.Dom.setStyle(visibleUl,'display','none');that._toggleVisibility(toggleDiv,visibleUl);that._attachSelectAll(visibleUl,checkClass);for(var i=0;i<visibleAttributes.length;i++)
{var visibleObj=visibleAttributes[i];if(visibleObj.isAggregate){visibleObj.displayLabel=MDSS.localize(visibleObj.key)+MDSS.localize("selectable_is_aggreated");}
var attribute=new MDSS.BasicAttribute(visibleObj);attribute.mainQueryClass=mainQueryClass;var li=document.createElement('li');var check=document.createElement('input');YAHOO.util.Dom.setAttribute(check,'type','checkbox');YAHOO.util.Dom.addClass(check,checkClass);YAHOO.util.Event.on(check,'click',that._visibleAttributeHandler,attribute,that);check.id=attribute.getKey();li.appendChild(check);this._defaults.push({element:check,checked:false});if(visibleObj.dtoType&&visibleObj.dtoType.indexOf('AttributeCharacterDTO')!=-1)
{li.id=attribute.getKey()+'_li';var items=[];var single=this._createSingleItem(check,li,attribute,'queryTextCriteria');this._menuItems[attribute.getKey()+'-single']=single;items.push(single);this._menus[li.id]=items;}
if(visibleObj.dtoType&&visibleObj.dtoType.indexOf('AttributeIntegerDTO')!=-1)
{li.id=attribute.getKey()+'_li';var select=document.createElement('select');var options=[''];options=options.concat(Mojo.Util.getValues(MDSS.QueryXML.Functions));for(var j=0;j<options.length;j++)
{var option=options[j];var optionEl=document.createElement('option');optionEl.id=attribute.getKey()+'-'+option;optionEl.innerHTML=option;YAHOO.util.Dom.setAttribute(optionEl,'value',option);YAHOO.util.Event.on(optionEl,'click',this._visibleAggregateHandler,attribute,this);select.appendChild(optionEl);}
select.disabled=true;this._defaults.push({element:select,index:0});li.appendChild(select);var items=[];var single=this._createSingleItem(check,li,attribute,'queryNumberCriteria');var range=this._createRangeItem(check,li,attribute);this._menuItems[attribute.getKey()+'-single']=single;this._menuItems[attribute.getKey()+'-range']=range;items.push(single);items.push(range);this._menus[li.id]=items;}
var span=document.createElement('span');span.innerHTML=attribute.getDisplayLabel();li.appendChild(span);if(visibleObj.dropDownMap)
{li.id=attribute.getKey()+'_li';var options=Mojo.Util.getKeys(visibleObj.dropDownMap);var displayLabels=Mojo.Util.getValues(visibleObj.dropDownMap);var items=[];for(var j=0;j<options.length;j++)
{var item={checked:false,text:displayLabels[j],uuid:options[j],myIndex:j,onclick:{fn:that._whereValueHandler,obj:{attribute:attribute,value:options[j],display:displayLabels[j]},scope:this}}
items.push(item);this._menuItems[attribute.getKey()+'-'+options[j]]=item;}
this._menus[li.id]=items;}
else
if(attribute.getTerm())
{li.id=attribute.getKey()+'_li';var n=attribute.getAttributeName().replace(/.name/,'');if(this._moUsesView!==false)
{this._attachBrowser(li.id,this._genericBrowserHandler,attribute,visibleObj.type+"View",n,true);}
else
{this._attachBrowser(li.id,this._genericBrowserHandler,attribute,visibleObj.type,n,true);}}
visibleUl.appendChild(li);}
visibleDiv.appendChild(visibleUl);return visibleDiv;},_genericBrowserHandler:function(browser,selected)
{var attribute=browser.getAttribute();var key=attribute.getKey();this._queryPanel.clearWhereCriteria(key);Mojo.Iter.forEach(selected,function(sel){var display=MDSS.OntologyBrowser.formatLabelFromView(sel);this._queryPanel.addWhereCriteria(attribute.getKey(),sel.getTermId(),display);},this);},_buildQueryItems:function(selectableGroups)
{this._queryPanel.addQueryItem({html:this._getCountDiv(this,"Dates_And_Count",this._groupByClass,!!this._showRatioSelectable),id:'globalCount'});var setupDiv=function(group,idx){this._queryPanel.addQueryItem({html:this._getVizDiv(this,group.values,group.title,group.klass,group.group),id:group.group+'_checkbox_group',menuBuilder:Mojo.Util.bind(this,this._menuBuilder)});this._exclusionClasses.push(group.group);};Mojo.Iter.forEach(selectableGroups,setupDiv,this);},_attachSelectAll:function(ul,klass)
{var check=document.createElement('input');YAHOO.util.Dom.setAttribute(check,'type','checkbox');YAHOO.util.Dom.addClass(check,'selectAllCheck');YAHOO.util.Dom.addClass(check,klass);YAHOO.util.Event.on(check,'click',this._toggleSelectAll,ul,this);this._defaults.push({element:check,checked:false,bypass:true});var span=document.createElement('span');span.innerHTML=MDSS.Localized.Select_All;var li=document.createElement('li');li.appendChild(check);li.appendChild(span);ul.appendChild(li);},_toggleSelectAll:function(e,ul)
{var check=e.target;var checks=YAHOO.util.Selector.query('input[type="checkbox"]',ul);var doCheck=check.checked;for(var i=0;i<checks.length;i++)
{var check=checks[i];if(doCheck!==check.checked)
{check.click();}}},_toggleVisibility:function(toggle,element)
{YAHOO.util.Event.on(toggle,'click',function(e,obj){var el=obj.element;var toggle=obj.toggle;if(YAHOO.util.Dom.getStyle(el,'display')==='block')
{YAHOO.util.Dom.setStyle(el,'display','none');toggle.innerHTML=MDSS.Localized.Toggle_Show;}
else
{YAHOO.util.Dom.setStyle(el,'display','block');toggle.innerHTML=MDSS.Localized.Toggle_Hide;}},{toggle:toggle,element:element},this);},_buildColumns:function()
{},render:function()
{this._queryPanel.render();for(var i=0;i<this._preconfiguredColumns.length;i++)
{var column=this._preconfiguredColumns[i];this._addVisibleAttribute(column);}},_createSingleItem:function(check,li,attribute,klass)
{this._singleAndRangeAttributes.push(attribute);var singleInput=document.createElement('input');YAHOO.util.Dom.setAttribute(singleInput,'type','text');YAHOO.util.Dom.setStyle(singleInput,'display','none');YAHOO.util.Dom.addClass(singleInput,klass);singleInput.id=attribute.getKey()+"-single";var obj={attribute:attribute,type:'single'};YAHOO.util.Event.on(singleInput,'keyup',this._setNumberCriteria,obj,this);li.appendChild(singleInput);if(klass=='queryTextCriteria')
{this._buildTextAttributeAutoSuggest(singleInput,attribute,obj,this);}
YAHOO.util.Event.on(check,'click',function(e,attr){if(!e.target.checked)
{this._toggleSingle(attr,false);}},attribute,this);return item={text:MDSS.Localized.Single_Value,checked:false,onclick:{fn:this._toggleNumberInputs,obj:obj,scope:this}};},_buildTextAttributeAutoSuggest:function(searchEl,attribute,obj,queryObject)
{var listFunction=function(valueObject){return valueObject.getValue('attributeCount')+') '+valueObject.getValue('attribute');};var displayFunction=function(valueObject){return valueObject.getValue('attribute');};var idFunction=function(valueObject){return null;};var searchFunction=Mojo.$.dss.vector.solutions.query.QueryBuilder.getTextAttributeSugestions;var selectEventHandler=function(selected){queryObject._setNumberCriteria(null,obj);};var search=new MDSS.GenericSearch(searchEl,null,listFunction,displayFunction,idFunction,searchFunction,selectEventHandler,{minLength:0});search.addParameter([attribute.getType(),attribute.getAttributeName()]);},_buildDateAttributesSelect:function(div)
{attributes=this._dateAttribs.map(function(d){var tmp=new d.klass();var attrib=tmp.attributeMap[d.accessor];return{keyName:d.klass.CLASS+'.'+d.accessor,klass:d.klass.CLASS,attribute:d.accessor,display:attrib.attributeMdDTO.displayLabel};});var sel;this._dateAttributes=[];sel=document.createElement('select');sel.id=MDSS.QueryBase.DATE_ATTRIBUTES;for(var i=0;i<attributes.length;i++)
{var attribute=attributes[i];var optionEl=document.createElement('option');optionEl.innerHTML=attribute.display;optionEl.value=i;this._dateAttributes[i]=attribute;sel.appendChild(optionEl);}
div.appendChild(sel);var label=document.createElement('span');label.innerHTML=' ';div.appendChild(label);this._dateAttributeSelect=sel;},_createRangeItem:function(check,li,attribute)
{this._singleAndRangeAttributes.push(attribute);var rangeInput1=document.createElement('input');YAHOO.util.Dom.setAttribute(rangeInput1,'type','text');YAHOO.util.Dom.setStyle(rangeInput1,'display','none');YAHOO.util.Dom.addClass(rangeInput1,'queryNumberCriteria');rangeInput1.id=attribute.getKey()+"-range1";var obj={attribute:attribute,type:'range'};YAHOO.util.Event.on(rangeInput1,'keyup',this._setNumberCriteria,obj,this);var rangeSign=document.createElement('span');rangeSign.innerHTML='-';YAHOO.util.Dom.setStyle(rangeSign,'display','none');rangeSign.id=attribute.getKey()+"-rangeSign";var rangeInput2=document.createElement('input');YAHOO.util.Dom.setAttribute(rangeInput2,'type','text');YAHOO.util.Dom.setStyle(rangeInput2,'display','none');YAHOO.util.Dom.addClass(rangeInput2,'queryNumberCriteria');rangeInput2.id=attribute.getKey()+"-range2";YAHOO.util.Event.on(rangeInput2,'keyup',this._setNumberCriteria,obj,this);li.appendChild(rangeInput1);li.appendChild(rangeSign);li.appendChild(rangeInput2);YAHOO.util.Event.on(check,'click',function(e,attr){if(!e.target.checked)
{this._toggleRange(attr,false);}},attribute,this);return item={text:MDSS.Localized.Set_Range,checked:false,onclick:{fn:this._toggleNumberInputs,obj:obj,scope:this}};},_toggleSingle:function(attribute,toggleOverride,value)
{var key=(Mojo.Util.isString(attribute)?attribute:attribute.getKey())+'-single';var item=this._menuItems[key];if(item==null)
{return;}
item.checked=Mojo.Util.isBoolean(toggleOverride)?toggleOverride:!item.checked;var single=document.getElementById(key);if(item.checked)
{YAHOO.util.Dom.setStyle(single,'display','inline');if(value)
{single.value=value;}}
else
{single.value='';YAHOO.util.Dom.setStyle(single,'display','none');}
return item.checked;},_toggleRange:function(attribute,toggleOverride,value1,value2)
{var key=Mojo.Util.isString(attribute)?attribute:attribute.getKey();var item=this._menuItems[key+'-range'];if(item==null)
{return;}
item.checked=Mojo.Util.isBoolean(toggleOverride)?toggleOverride:!item.checked;var range1=document.getElementById(key+"-range1");var rangeSign=document.getElementById(key+"-rangeSign");var range2=document.getElementById(key+"-range2");if(item.checked)
{YAHOO.util.Dom.setStyle(range1,'display','inline');YAHOO.util.Dom.setStyle(rangeSign,'display','inline');YAHOO.util.Dom.setStyle(range2,'display','inline');if(value1)
{range1.value=value1;}
if(value2)
{range2.value=value2;}}
else
{range1.value='';range2.value='';YAHOO.util.Dom.setStyle(range1,'display','none');YAHOO.util.Dom.setStyle(rangeSign,'display','none');YAHOO.util.Dom.setStyle(range2,'display','none');}
return item.checked;},_toggleNumberInputs:function(eventType,event,obj)
{var attribute=obj.attribute;var that=this;var checked;if(obj.type==='single')
{this._toggleRange(attribute,false);checked=this._toggleSingle(attribute);}
else
{this._toggleSingle(attribute,false);checked=this._toggleRange(attribute);}
this._queryPanel.clearWhereCriteria(attribute.getKey());if(!checked)
{this._config.setProperty(attribute.getKey()+'Criteria',null);}},_setNumberInputValues:function(obj,value1,value2)
{var attribute=obj.attribute;if(obj.type==='single')
{if(value1!=null)
{document.getElementById(attribute.getKey()+"-single").value=value1;}}
else
{if(value1!=null)
{document.getElementById(attribute.getKey()+"-range1").value=value1;}
if(value2!=null)
{document.getElementById(attribute.getKey()+"-range2").value=value2;}}},_setNumberCriteria:function(e,obj)
{var attribute=obj.attribute;var value;if(obj.type==='single')
{var single=document.getElementById(attribute.getKey()+"-single");value=single.value;}
else
{var range1=document.getElementById(attribute.getKey()+"-range1");var range2=document.getElementById(attribute.getKey()+"-range2");value=range1.value+' - '+range2.value;}
if(value===''||value===' - ')
{value=null;}
this._config.setProperty(attribute.getKey()+'Criteria',value);this._queryPanel.clearWhereCriteria(attribute.getKey());if(value!=null)
{this._queryPanel.addWhereCriteria(attribute.getKey(),value,value);}}},Static:{mapAttribs:function(attribName,index){var attrib=this.obj.attributeMap[attribName];var row={};if(attrib){row.attributeName=attrib.attributeName;if(attrib.dtoType.indexOf('AttributeReferenceDTO')!=-1)
{if(attrib.getAttributeMdDTO().getReferencedMdBusiness().indexOf('Term')!=-1)
{row.isTerm=true;}
if(attrib.getAttributeMdDTO().getReferencedMdBusiness().indexOf('dss.vector.solutions.geo.generated')!=-1)
{row.attributeName=attribName+'_displayLabel';row.type='sqlcharacter';row.displayLabel=attrib.attributeMdDTO.displayLabel;row.key=attribName;row.dtoType="AttributeCharacterDTO";row.isGeoEntity=true;return row;}}
if(attrib.dtoType.indexOf('AttributeEnumerationDTO')!=-1)
{row.isEnum=true;}
row.key=attrib.attributeName+this.suffix;if(this.type)
{row.type=this.type;}else
{row.type=this.obj.getType();}
row.dtoType=attrib.dtoType;row.displayLabel=attrib.attributeMdDTO.displayLabel;var uppFirst=attrib.attributeName.slice(0,1).toUpperCase()+attrib.attributeName.slice(1);if(this.dropDownMaps[uppFirst]){row.dropDownMap=this.dropDownMaps[uppFirst];}}else{row.attributeName=attribName;row.type='sqlinteger';row.displayLabel=MDSS.localize(attribName);row.key=attribName;row.dtoType="AttributeIntegerDTO";if(this.obj.row)
{row.type=this.obj.row.type;row.dtoType=this.obj.row.dtoType;}}
return row;},mapInts:function(attribName,index){var attrib=this.obj.attributeMap[attribName];var row={};row.attributeName=attrib.attributeName;row.type='sqlinteger';row.displayLabel=attrib.attributeMdDTO.displayLabel;row.key=attribName;row.dtoType="AttributeIntegerDTO";return row;},mapMo:function(term,index){var row={};row.dtoType="AttributeIntegerDTO";row.displayLabel=term.displayLabel;row.key=this.relAttribute+'__'+this.relType.replace(/[.]/g,'_')+'__'+term.id;;row.type='sqlinteger';row.attributeName='term'+term.MOID.replace(':','');return row;},mapBooleanMo:function(term,index){var row={};row.dtoType="AttributeBooleanDTO";row.displayLabel=term.displayLabel;row.key=this.relAttribute+'__'+this.relType.replace(/[.]/g,'_')+'__'+term.id;;row.type='sqlinteger';row.attributeName='term'+term.MOID.replace(':','');row.dropDownMap={'0':'0','1':'1'};return row;}}});Mojo.Meta.newClass('MDSS.QueryAggregatedCases',{Extends:MDSS.QueryBase,Instance:{initialize:function(ageGroups,visibleAttributes,orderedGrids,queryList)
{this.$initialize(queryList);this._aggregatedCase=new Mojo.$.dss.vector.solutions.surveillance.AggregatedCase();var aggregatedCase=Mojo.$.dss.vector.solutions.surveillance.AggregatedCase;this._startDate=null;this._endDate=null;this._ageGroupCriteria={};this._visibleSelectables={};this._visibleAggregateSelectables={};this._gridEntities={};this._gridSelectables={};this._gridAggregateSelectables={};this._countSelectable=null;this._geoEntityAttribs=[{keyName:this._aggregatedCase.constructor.CLASS+'.'+this._aggregatedCase.constructor.GEOENTITY,display:this._aggregatedCase.getGeoEntityMd().getDisplayLabel()}];var startDateAttr=new MDSS.QueryXML.Attribute(aggregatedCase.CLASS,aggregatedCase.STARTDATE,aggregatedCase.STARTDATE);this._startDateSelectable=new MDSS.QueryXML.Selectable(startDateAttr);var endDateAttr=new MDSS.QueryXML.Attribute(aggregatedCase.CLASS,aggregatedCase.ENDDATE,aggregatedCase.ENDDATE);this._endDateSelectable=new MDSS.QueryXML.Selectable(endDateAttr);this._defaultAgeGroups=[];this._buildQueryItems(ageGroups,visibleAttributes,orderedGrids);var picker=this.getGeoPicker();picker.setPolitical(true);picker.setSprayTargetAllowed(false);picker.addExtraUniversal('dss.vector.solutions.geo.generated.CollectionSite*');picker.addExtraUniversal('dss.vector.solutions.geo.generated.HealthFacility*');},_customPostRender:function()
{for(var i=0;i<this._defaultAgeGroups.length;i++)
{this._defaultAgeGroups[i].click();}},_getExportXLSAction:function()
{return'dss.vector.solutions.query.QueryController.exportAggregatedCaseQueryToExcel.mojo';},_getExportCSVAction:function()
{return'dss.vector.solutions.query.QueryController.exportAggregatedCaseQueryToCSV.mojo';},_loadQueryState:function(view)
{var aggregatedCase=Mojo.$.dss.vector.solutions.surveillance.AggregatedCase;var thisRef=this;var xml=view.getQueryXml();var parser=new MDSS.Query.Parser(xml);parser.parseSelectables({attribute:function(entityAlias,attributeName,userAlias){if(attributeName!==aggregatedCase.ENDAGE)
{thisRef._checkBox(userAlias);}},sum:function(entityAlias,attributeName,userAlias){if(thisRef._checkBox(userAlias))
{thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.SUM);}},min:function(entityAlias,attributeName,userAlias){if(thisRef._checkBox(userAlias))
{thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.MIN);}},max:function(entityAlias,attributeName,userAlias){if(thisRef._checkBox(userAlias))
{thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.MAX);}},avg:function(entityAlias,attributeName,userAlias){if(thisRef._checkBox(userAlias))
{thisRef._chooseOption(userAlias+'-'+MDSS.QueryXML.Functions.AVG);}},count:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);},sqlcharacter:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);},sqldouble:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);},sqldate:function(entityAlias,attributeName,userAlias){thisRef._checkBox(userAlias);},});for(var i=0;i<this._defaultAgeGroups.length;i++)
{this._uncheckBox(this._defaultAgeGroups[i]);}
parser.parseCriteria({attribute:function(entityAlias,attributeName,userAlias,operator,value){if(userAlias===aggregatedCase.STARTDATE)
{thisRef._queryPanel.getStartDate().value=value;}
else if(userAlias===aggregatedCase.ENDDATE)
{thisRef._queryPanel.getEndDate().value=value;}
else if(/^group_/.test(userAlias))
{thisRef._checkBox(userAlias);}}});},executeQuery:function()
{var queryXML=this._constructQuery();var xml=queryXML.getXML();var request=new MDSS.Request({thisRef:this,onSuccess:function(query)
{this.thisRef.resetQueryResults(query);}});var page=this.getCurrentPage();Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.queryAggregatedCase(request,xml,this._config.getJSON(),page,this.PAGE_SIZE);},mapQuery:function()
{var queryXML=this._constructQuery(true);var xml=queryXML.getXML();var request=new MDSS.Request({thisRef:this,onSuccess:function(layers){var layersObj=Mojo.Util.getObject(layers);this.thisRef._queryPanel.createMap(layersObj);}});var layerIds=this._queryPanel.getSelectedLayers();var savedSearchView=this._queryPanel.getCurrentSavedSearch();var savedSearchId=(savedSearchView!=null?savedSearchView.getSavedQueryId():"");Mojo.$.dss.vector.solutions.query.MappingController.mapAggregatedCaseQuery(request,xml,this._config.getJSON(),layerIds,savedSearchId);},_constructQuery:function(forMapping)
{var queryXML=MDSS.QueryBase.prototype._constructQuery.call(this,forMapping);var aggregatedCase=Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;var aggregatedCaseQuery=new MDSS.QueryXML.Entity(aggregatedCase,aggregatedCase);queryXML.addEntity(aggregatedCaseQuery);var aggregatedCase=Mojo.$.dss.vector.solutions.surveillance.AggregatedCase;if(this._countSelectable!=null)
{queryXML.addSelectable(aggregatedCaseQuery.getAlias()+'_globalCount',this._countSelectable);}
var ageGroupIds=Mojo.Util.getKeys(this._ageGroupCriteria);var ageGroupOr=null;if(ageGroupIds.length>0)
{var ageGroupOr=new MDSS.QueryXML.Or();for(var i=0;i<ageGroupIds.length;i++)
{var id=ageGroupIds[i];var compositeCondition=this._ageGroupCriteria[id];ageGroupOr.addCondition(id,compositeCondition);}}
var selNames=Mojo.Util.getKeys(this._visibleSelectables);for(var i=0;i<selNames.length;i++)
{var name=selNames[i];var selectable=this._visibleSelectables[name];queryXML.addSelectable(aggregatedCaseQuery.getAlias()+'_'+name,selectable);}
var aggNames=Mojo.Util.getKeys(this._visibleAggregateSelectables);for(var i=0;i<aggNames.length;i++)
{var name=aggNames[i];var selectable=this._visibleAggregateSelectables[name];queryXML.addSelectable(aggregatedCaseQuery.getAlias()+'_'+name,selectable);}
var conditions=[];if(this._startDate!=null)
{conditions.push(this._startDate);}
if(this._endDate!=null)
{conditions.push(this._endDate);}
var keys=Mojo.Util.getKeys(this._dateGroupSelectables);for(var i=0;i<keys.length;i++)
{var selectable=this._dateGroupSelectables[keys[i]];if(selectable!=null)
{queryXML.addSelectable(keys[i],selectable);}}
var dateAndOr=null;if(conditions.length===2)
{dateAndOr=new MDSS.QueryXML.And();dateAndOr.addCondition('date1',conditions[0]);dateAndOr.addCondition('date2',conditions[1]);}
else if(conditions.length===1)
{dateAndOr=new MDSS.QueryXML.Or();dateAndOr.addCondition('date1',conditions[0]);}
if(ageGroupOr!=null&&dateAndOr!=null)
{var and=new MDSS.QueryXML.And();and.addCondition('ageGroup',new MDSS.QueryXML.CompositeCondition(ageGroupOr));and.addCondition('dateRange',new MDSS.QueryXML.CompositeCondition(dateAndOr));var composite=new MDSS.QueryXML.CompositeCondition(and);aggregatedCaseQuery.setCondition(composite);}
else if(ageGroupOr!=null)
{var composite=new MDSS.QueryXML.CompositeCondition(ageGroupOr);aggregatedCaseQuery.setCondition(composite);}
else if(dateAndOr!=null)
{var composite=new MDSS.QueryXML.CompositeCondition(dateAndOr);aggregatedCaseQuery.setCondition(composite);}
var eAliases=Mojo.Util.getKeys(this._gridEntities);for(var i=0;i<eAliases.length;i++)
{var alias=eAliases[i];var entity=this._gridEntities[alias];queryXML.addEntity(entity);}
var sAliases=Mojo.Util.getKeys(this._gridSelectables);for(var i=0;i<sAliases.length;i++)
{var alias=sAliases[i];var selectable=this._gridSelectables[alias];queryXML.addSelectable(alias,selectable);}
var gridAggAliases=Mojo.Util.getKeys(this._gridAggregateSelectables);for(var i=0;i<gridAggAliases.length;i++)
{var alias=gridAggAliases[i];var selectable=this._gridAggregateSelectables[alias];queryXML.addSelectable(alias,selectable);}
return queryXML;},_toggleCount:function(e,attribute)
{var check=e.target;if(check.checked)
{var selectable=attribute.getSelectable();var count=new MDSS.QueryXML.COUNT(selectable,attribute.getKey());var aggSelectable=new MDSS.QueryXML.Selectable(count);this._countSelectable=aggSelectable;this._queryPanel.insertColumn(attribute.getColumnObject());}
else
{var column=this._queryPanel.getColumn(attribute.getKey());this._queryPanel.removeColumn(column);this._countSelectable=null;}},_ageGroupCheckHandler:function(e,ageGroup)
{var check=e.target;if(check.checked)
{var aggregatedCase=Mojo.$.dss.vector.solutions.surveillance.AggregatedCase;var leftSide=new MDSS.QueryXML.Attribute(aggregatedCase.CLASS,aggregatedCase.STARTAGE,'group_'+ageGroup.id);var leftSelectable=new MDSS.QueryXML.Selectable(leftSide);var leftCondition=new MDSS.QueryXML.BasicCondition(leftSelectable,MDSS.QueryXML.Operator.GE,ageGroup.startAge);var rightSide=new MDSS.QueryXML.Attribute(aggregatedCase.CLASS,aggregatedCase.ENDAGE,aggregatedCase.ENDAGE);var rightSelectable=new MDSS.QueryXML.Selectable(rightSide);var rightCondition=new MDSS.QueryXML.BasicCondition(rightSelectable,MDSS.QueryXML.Operator.LE,ageGroup.endAge);var and=new MDSS.QueryXML.And();and.addCondition('startAge',leftCondition);and.addCondition('endAge',rightCondition);var andCondition=new MDSS.QueryXML.CompositeCondition(and);this._ageGroupCriteria[ageGroup.id]=andCondition;}
else if(ageGroup!=null)
{delete this._ageGroupCriteria[ageGroup.id];}},_addVisibleAttribute:function(attribute)
{var column=new YAHOO.widget.Column(attribute.getColumnObject());column=this._queryPanel.insertColumn(column);var attributeName=attribute.getAttributeName();var selectable;if(attribute.getType()=='sqldouble')
{selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqldouble('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),true));}
else if(attribute.getType()=='sqlcharacter')
{selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlcharacter('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),false));}
else
{selectable=attribute.getSelectable();}
this._visibleSelectables[attribute.getKey()]=selectable;},_addGridAttribute:function(attribute)
{var column=new YAHOO.widget.Column(attribute.getColumnObject());column=this._queryPanel.insertColumn(column);var optionName=attribute.getOptionName();var relationshipType=attribute.getRelationshipType();var relationshipAlias=attribute.getRelationshipAlias();var relEntity=new MDSS.QueryXML.Entity(relationshipType,relationshipAlias);this._gridEntities[relationshipAlias]=relEntity;var rSelectable=attribute.getRelationshipSelectable();this._gridSelectables[attribute.getKey()]=rSelectable;var type=attribute.getType();var businessAlias=attribute.getBusinessAlias();var entity=new MDSS.QueryXML.Entity(type,businessAlias);var bSelectable=attribute.getBusinessSelectable();var condition=new MDSS.QueryXML.BasicCondition(bSelectable,MDSS.QueryXML.Operator.EQ,optionName);entity.setCondition(condition);this._gridEntities[businessAlias]=entity;},_removeVisibleAttribute:function(attribute,removeColumn,removeSelectable)
{var attributeName=attribute.getAttributeName();var key=attribute.getKey();if(removeSelectable)
{delete this._visibleSelectables[attribute.getKey()];}
delete this._visibleAggregateSelectables[attribute.getKey()];if(removeColumn)
{var column=this._queryPanel.getColumn(key);this._queryPanel.removeColumn(column);}},_removeGridAttribute:function(attribute,removeColumn,removeSelectable)
{if(removeSelectable)
{delete this._gridSelectables[attribute.getKey()];}
delete this._gridAggregateSelectables[attribute.getKey()];if(removeColumn)
{var column=this._queryPanel.getColumn(attribute.getKey());this._queryPanel.removeColumn(column);delete this._gridEntities[attribute.getBusinessAlias()];delete this._gridEntities[attribute.getRelationshipAlias()];}},_visibleAttributeHandler:function(e,attribute)
{var check=e.target;if(check.checked)
{this._addVisibleAttribute(attribute);check.nextSibling.disabled=false;}
else
{this._removeVisibleAttribute(attribute,true,true);var select=check.nextSibling;select.selectedIndex=0;select.disabled=true;}},_gridAttributeHandler:function(e,attribute)
{var check=e.target;if(check.checked)
{this._addGridAttribute(attribute);check.nextSibling.disabled=false;}
else
{this._removeGridAttribute(attribute,true,true);var select=check.nextSibling;select.selectedIndex=0;select.disabled=true;}},_gridAggregateHandler:function(e,attribute)
{var func=e.target.value;var optionName=attribute.getOptionName();var attributeName=attribute.getAttributeName();var relationshipAlias=attribute.getRelationshipAlias();var key=attribute.getKey();var selectable=attribute.getRelationshipSelectable();this._queryPanel.updateColumnLabel(key,func);if(func==='')
{this._removeGridAttribute(attribute,false,true);this._gridSelectables[key]=selectable;return;}
var aggFunc=null;var displayLabel="("+func+") "+attribute.getDisplayLabel();if(func===MDSS.QueryXML.Functions.SUM)
{aggFunc=new MDSS.QueryXML.SUM(selectable,key,displayLabel);}
else if(func===MDSS.QueryXML.Functions.MIN)
{aggFunc=new MDSS.QueryXML.MIN(selectable,key,displayLabel);}
else if(func===MDSS.QueryXML.Functions.MAX)
{aggFunc=new MDSS.QueryXML.MAX(selectable,key,displayLabel);}
else if(func===MDSS.QueryXML.Functions.AVG)
{aggFunc=new MDSS.QueryXML.AVG(selectable,key,displayLabel);}
this._removeGridAttribute(attribute,false,true);var aggSelectable=new MDSS.QueryXML.Selectable(aggFunc);this._gridAggregateSelectables[key]=aggSelectable;},_visibleAggregateHandler:function(e,attribute)
{var func=e.target.value;var attributeName=attribute.getAttributeName();var key=attribute.getKey();var selectable=attribute.getSelectable();this._queryPanel.updateColumnLabel(key,func);if(func==='')
{this._removeVisibleAttribute(attribute,false,true);this._visibleSelectables[attribute.getKey()]=selectable;return;}
var aggFunc=null;var displayLabel="("+func+") "+attribute.getDisplayLabel();if(func===MDSS.QueryXML.Functions.SUM)
{aggFunc=new MDSS.QueryXML.SUM(selectable,key,displayLabel);}
else if(func===MDSS.QueryXML.Functions.MIN)
{aggFunc=new MDSS.QueryXML.MIN(selectable,key,displayLabel);}
else if(func===MDSS.QueryXML.Functions.MAX)
{aggFunc=new MDSS.QueryXML.MAX(selectable,key,displayLabel);}
else if(func===MDSS.QueryXML.Functions.AVG)
{aggFunc=new MDSS.QueryXML.AVG(selectable,key,displayLabel);}
this._removeVisibleAttribute(attribute,false,true);var aggSelectable=new MDSS.QueryXML.Selectable(aggFunc);this._visibleAggregateSelectables[attribute.getKey()]=aggSelectable;},_showAgeGroupAttributes:function(e,attributes)
{var check=e.target;for(var i=0;i<attributes.length;i++)
{var attribute=attributes[i];if(check.checked)
{this._addVisibleAttribute(attribute);}
else
{this._removeVisibleAttribute(attribute,true,true);}}},_buildQueryItems:function(ageGroups,visibleAttributes,orderedGrids)
{var ageGroupDiv=document.createElement('div');var ageDiv=document.createElement('div');ageDiv.innerHTML=MDSS.Localized.Age_Group;var startAge=this._aggregatedCase.getStartAgeMd().getName();var startAgeAttribute=new MDSS.BasicAttribute({type:this._aggregatedCase.getType(),attributeName:startAge,displayLabel:this._aggregatedCase.getStartAgeMd().getDisplayLabel(),});var endAge=this._aggregatedCase.getEndAgeMd().getName();var endAgeAttribute=new MDSS.BasicAttribute({type:this._aggregatedCase.getType(),displayLabel:this._aggregatedCase.getEndAgeMd().getDisplayLabel(),attributeName:endAge});var show=document.createElement('ul');var showLi=document.createElement('li');var showSpan=document.createElement('span');var showCheck=document.createElement('input');YAHOO.util.Dom.setAttribute(showCheck,'type','checkbox');showCheck.id=startAgeAttribute.getKey();YAHOO.util.Event.on(showCheck,'click',this._showAgeGroupAttributes,[startAgeAttribute,endAgeAttribute],this);this._defaults.push({element:showCheck,checked:false});showSpan.innerHTML=MDSS.Localized.Toggle_Show;showLi.appendChild(showCheck);showLi.appendChild(showSpan);show.appendChild(showLi);var groups=document.createElement('ul');YAHOO.util.Dom.setAttribute(groups,'id','ageGroupsList');for(var i=0;i<ageGroups.length;i++)
{var group=ageGroups[i];var li=document.createElement('li');var span=document.createElement('span');span.innerHTML=group.startAge+" - "+group.endAge;var check=document.createElement('input');YAHOO.util.Dom.setAttribute(check,'type','checkbox');YAHOO.util.Dom.setAttribute(check,'value',group.id);check.id='group_'+group.id;YAHOO.util.Event.on(check,'click',this._ageGroupCheckHandler,group,this);this._defaults.push({element:check,checked:true});li.appendChild(check);li.appendChild(span);groups.appendChild(li);this._defaultAgeGroups.push(check);}
ageGroupDiv.appendChild(ageDiv);ageGroupDiv.appendChild(show);ageGroupDiv.appendChild(groups);this._queryPanel.addQueryItem({html:ageGroupDiv,id:"ageGroupItem"});this._queryPanel.addQueryItem({html:this._getCountDiv(this,"Dates_And_Count",Mojo.$.dss.vector.solutions.surveillance.AggregatedCase),id:'globalCount'});var visibleDiv=document.createElement('div');var labelDiv=document.createElement('div');YAHOO.util.Dom.addClass(labelDiv,'queryItemLabel');labelDiv.innerHTML=MDSS.Localized.Aggregated_Case;var toggleDiv=document.createElement('div');YAHOO.util.Dom.addClass(toggleDiv,'clickable');YAHOO.util.Dom.addClass(toggleDiv,'queryItemLabel');toggleDiv.innerHTML=MDSS.Localized.Toggle_Show;visibleDiv.appendChild(labelDiv);visibleDiv.appendChild(toggleDiv);var visibleUl=document.createElement('ul');YAHOO.util.Dom.setStyle(visibleUl,'clear','both');YAHOO.util.Dom.setStyle(visibleUl,'display','none');this._toggleVisibility(toggleDiv,visibleUl);this._attachSelectAll(visibleUl);var type=Mojo.$.dss.vector.solutions.surveillance.AggregatedCase.CLASS;for(var i=0;i<visibleAttributes.length;i++)
{var visibleObj=visibleAttributes[i];var attribute=new MDSS.BasicAttribute(visibleObj);var li=document.createElement('li');var span=document.createElement('span');span.innerHTML=attribute.getDisplayLabel();var check=document.createElement('input');YAHOO.util.Dom.setAttribute(check,'type','checkbox');check.id=attribute.getKey();YAHOO.util.Event.on(check,'click',this._visibleAttributeHandler,attribute,this);this._defaults.push({element:check,checked:false});li.appendChild(check);if(attribute.getAttributeName()===this._aggregatedCase.constructor.GEOENTITY)
{attribute.setType('sqlcharacter');attribute.setAttributeName(attribute.getAttributeName()+'_displayLabel');}
else
{var select=document.createElement('select');this._defaults.push({element:select,index:0});var options=[''];options=options.concat(Mojo.Util.getValues(MDSS.QueryXML.Functions));for(var j=0;j<options.length;j++)
{var option=options[j];var optionEl=document.createElement('option');optionEl.innerHTML=option;optionEl.id=attribute.getKey()+'-'+option;YAHOO.util.Dom.setAttribute(optionEl,'value',option);YAHOO.util.Event.on(optionEl,'click',this._visibleAggregateHandler,attribute,this);select.appendChild(optionEl);}
select.disabled=true;li.appendChild(select);}
li.appendChild(span);visibleUl.appendChild(li);}
visibleDiv.appendChild(visibleUl);this._queryPanel.addQueryItem({html:visibleDiv,id:'visibleAttributesItem'});for(var i=0;i<orderedGrids.length;i++)
{var grid=orderedGrids[i];var gridDiv=document.createElement('div');var labelDiv=document.createElement('div');YAHOO.util.Dom.addClass(labelDiv,'queryItemLabel');labelDiv.innerHTML=grid.label;var toggleDiv=document.createElement('div');YAHOO.util.Dom.addClass(toggleDiv,'clickable');YAHOO.util.Dom.addClass(toggleDiv,'queryItemLabel');toggleDiv.innerHTML=MDSS.Localized.Toggle_Show;gridDiv.appendChild(labelDiv);gridDiv.appendChild(toggleDiv);var ul=document.createElement('ul');YAHOO.util.Dom.addClass(ul,'gridList');YAHOO.util.Dom.setStyle(ul,'clear','both');YAHOO.util.Dom.setStyle(ul,'display','none');this._toggleVisibility(toggleDiv,ul);this._attachSelectAll(ul);var options=grid.options;for(var j=0;j<options.length;j++)
{var option=options[j];var attribute=new MDSS.GridAttribute(option,grid);var li=document.createElement('li');var span=document.createElement('span');span.innerHTML=attribute.getDisplayLabel();var check=document.createElement('input');check.id=attribute.getKey();YAHOO.util.Dom.setAttribute(check,'type','checkbox');this._defaults.push({element:check,checked:false});var second=null;if(attribute.getRelationshipType()==='dss.vector.solutions.surveillance.CaseDiagnostic')
{attribute._displayLabel+=' ('+MDSS.Localized.Total_Tests+')';YAHOO.util.Event.on(check,'click',this._gridAttributeHandler,attribute,this);var optionCopy={};var gridCopy={};Mojo.Util.copy(option,optionCopy);Mojo.Util.copy(grid,gridCopy);gridCopy.relAttribute=gridCopy.relAttributeTwo;second=new MDSS.GridAttribute(optionCopy,gridCopy);second._displayLabel+=' ('+MDSS.Localized.Positive+')';YAHOO.util.Event.on(check,'click',this._gridAttributeHandler,second,this);}
else
{YAHOO.util.Event.on(check,'click',this._gridAttributeHandler,attribute,this);}
var select=document.createElement('select');this._defaults.push({element:select,index:0});var aggOptions=[''];aggOptions=aggOptions.concat(Mojo.Util.getValues(MDSS.QueryXML.Functions));for(var k=0;k<aggOptions.length;k++)
{var aggOption=aggOptions[k];var optionEl=document.createElement('option');optionEl.id=attribute.getKey()+'-'+aggOption;optionEl.innerHTML=aggOption;YAHOO.util.Dom.setAttribute(optionEl,'value',aggOption);YAHOO.util.Event.on(optionEl,'click',this._gridAggregateHandler,attribute,this);if(second!=null)
{YAHOO.util.Event.on(optionEl,'click',this._gridAggregateHandler,second,this);}
select.appendChild(optionEl);}
select.disabled=true;li.appendChild(check);li.appendChild(select);li.appendChild(span);ul.appendChild(li);}
gridDiv.appendChild(ul);this._queryPanel.addQueryItem({html:gridDiv,id:"gridItem_"+1});}},_attachSelectAll:function(ul)
{var check=document.createElement('input');YAHOO.util.Dom.setAttribute(check,'type','checkbox');YAHOO.util.Event.on(check,'click',this._toggleSelectAll,ul,this);this._defaults.push({element:check,checked:false,bypass:true});var span=document.createElement('span');span.innerHTML=MDSS.Localized.Select_All;var li=document.createElement('li');li.appendChild(check);li.appendChild(span);ul.appendChild(li);},_toggleSelectAll:function(e,ul)
{var check=e.target;var checks=YAHOO.util.Selector.query('input[type="checkbox"]',ul);var doCheck=check.checked;for(var i=0;i<checks.length;i++)
{var check=checks[i];if(doCheck!==check.checked)
{check.click();}}},render:function()
{this._queryPanel.render();}}});Mojo.Meta.newClass('MDSS.GridAttribute',{Extends:MDSS.AbstractAttribute,Instance:{initialize:function(obj,meta)
{this.$initialize(obj);this._optionName=obj.optionName;this._meta=meta;var busType=this._type;var relType=this._meta.relType;this._relAlias=this._getGridRelAlias(relType,this._optionName);this._busAlias=this._getGridBusAlias(relType,this._optionName,busType);this._key+='_'+this._optionName+'_'+this._meta.relAttribute;},getOptionName:function()
{return this._optionName;},getBusinessAlias:function()
{return this._busAlias;},getRelationshipAlias:function()
{return this._relAlias;},getRelationshipType:function()
{return this._meta.relType;},getRelationshipSelectable:function()
{var attribute=new MDSS.QueryXML.Attribute(this._relAlias,this._meta.relAttribute,this._key,this._displayLabel);var selectable=new MDSS.QueryXML.Selectable(attribute);return selectable;},getBusinessSelectable:function()
{var attribute=new MDSS.QueryXML.Attribute(this._busAlias,this._attributeName);var selectable=new MDSS.QueryXML.Selectable(attribute);return selectable;},_getGridRelAlias:function(relType,optionName)
{var relTypeName=this._extractTypeName(relType);return relTypeName+'_'+optionName;},_getGridBusAlias:function(relType,optionName,busType)
{var relTypeName=this._extractTypeName(relType);var busTypeName=this._extractTypeName(busType);return relTypeName+'_'+optionName+'_'+busTypeName;},_extractTypeName:function(type)
{var ind=type.lastIndexOf('.');var typeName=type.substring(ind+1);return typeName;},isThematic:function()
{return true;}}});Mojo.Meta.newClass('MDSS.QuerySurvey',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._groupByClass=dss.vector.solutions.intervention.monitor.SurveyPoint;this._mainQueryClass=this._groupByClass.CLASS;this._surveyPoint=new this._groupByClass;this._commonQueryClasses=[];this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,display:this._surveyPoint.getGeoEntityMd().getDisplayLabel()},];this._dateAttribs=[{klass:this._groupByClass,accessor:this._groupByClass.SURVEYDATE,}];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);var picker=this.getGeoPicker();picker.setPolitical(true);picker.setSprayTargetAllowed(false);picker.addExtraUniversal('dss.vector.solutions.geo.generated.SentinelSite');}}});Mojo.Meta.newClass('MDSS.QueryEntomology',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._groupByClass=Mojo.$.dss.vector.solutions.entomology.MosquitoCollection;this._mainQueryClass=this._groupByClass.CLASS;this._mosquitoCollection=new this._groupByClass();this._showRatioSelectable=true;this._commonQueryClasses=[];this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,display:this._mosquitoCollection.getGeoEntityMd().getDisplayLabel()},];this._dateAttribs=[{klass:this._groupByClass,accessor:this._groupByClass.COLLECTIONDATE,}];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);this._exclusionClasses.shift();this._xmlToValueQueryClass=dss.vector.solutions.entomology.InfectionAssay.CLASS;var picker=this.getGeoPicker();picker.setPolitical(false);picker.setSprayTargetAllowed(false);}}});Mojo.Meta.newClass('MDSS.QueryResistance',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._groupByClass=Mojo.$.dss.vector.solutions.entomology.MosquitoCollection;this._mainQueryClass=this._groupByClass.CLASS;this._mosquitoCollection=new this._groupByClass();this._commonQueryClasses=[dss.vector.solutions.entomology.assay.AbstractAssay.CLASS,];this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,display:this._mosquitoCollection.getGeoEntityMd().getDisplayLabel()},];this._dateAttribs=[{klass:this._groupByClass,accessor:this._groupByClass.COLLECTIONDATE,},{klass:dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay,accessor:dss.vector.solutions.entomology.assay.AbstractAssay.TESTDATE,}];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);this._exclusionClasses.shift();this._xmlToValueQueryClass=dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS;var picker=this.getGeoPicker();picker.setPolitical(false);picker.setSprayTargetAllowed(false);},_addVisibleAttribute:function(attribute)
{var attributeName=attribute.getAttributeName();if(attribute.mainQueryClass)
{this._mainQueryClass=attribute.mainQueryClass;}
if(attribute.getType()=='sqlcharacter'){var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlcharacter('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;}else
if(attribute.getType()=='sqlinteger'){var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlinteger('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;}else
if(attribute.getType()=='sqlfloat'){var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqlfloat('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;}else
if(attribute.getType()=='sqldouble'){var selectable=new MDSS.QueryXML.Selectable(new MDSS.QueryXML.Sqldouble('',attributeName,attribute.getKey(),attribute.getDisplayLabel(),attribute._isAggregate));selectable.attribute=attribute;var column=new YAHOO.widget.Column({key:attribute.getKey(),label:attribute.getDisplayLabel()});column.attribute=attribute;}
else
{var selectable=attribute.getSelectable(true);selectable.attribute=attribute;var column=new YAHOO.widget.Column(attribute.getColumnObject());column.attribute=attribute;}
column=this._queryPanel.insertColumn(column);this._visibleSelectables[attribute.getKey()]=selectable;},}});Mojo.Meta.newClass('MDSS.QueryEfficacyAssay',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._efficacyAssay=dss.vector.solutions.entomology.assay.EfficacyAssay;this._mainQueryClass=this._efficacyAssay.CLASS;this._groupByClass=this._efficacyAssay;this._commonQueryClasses=[];this._exclusionClasses=[];this._dataQueryFunction=dss.vector.solutions.query.QueryBuilder.getQueryResults;this._mapQueryFunction=dss.vector.solutions.query.QueryBuilder.mapQuery;this._typeOverride={'specie_efficacy':dss.vector.solutions.entomology.assay.AbstractAssay.CLASS};var efficacyInstance=new this._efficacyAssay();this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,display:efficacyInstance.getGeoEntityMd().getDisplayLabel()}];this._dateAttribs=[{klass:this._efficacyAssay,accessor:dss.vector.solutions.entomology.assay.AbstractAssay.TESTDATE,},];this.$initialize(selectableGroups,queryList);this._queryType=this._mainQueryClass;var picker=this.getGeoPicker();picker.setPolitical(false);picker.setSprayTargetAllowed(false);}}});Mojo.Meta.newClass('MDSS.QueryIRS',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._preconfiguredColumns=[];this._countSelectable=null;this._ratioSelectable=null;this._specieGroupSelectables={};this._visibleSelectables={};this._whereOptions={};this._visibleAggregateSelectables={};var abstractSpray=dss.vector.solutions.irs.AbstractSpray;this._mainQueryClass=abstractSpray.CLASS;this._groupByClass=abstractSpray;var abstractSprayInstance=new dss.vector.solutions.irs.OperatorSpray();this._commonQueryClasses=[];this._geoEntityAttribs=[{keyName:abstractSpray.CLASS+'.'+abstractSpray.GEOENTITY,display:abstractSprayInstance.getGeoEntityMd().getDisplayLabel()},];this._dateAttribs=[{klass:dss.vector.solutions.irs.OperatorSpray,accessor:abstractSpray.SPRAYDATE,}];this._exclusionClasses=[];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);var picker=this.getGeoPicker();picker.setPolitical(false);picker.setSprayTargetAllowed(true);}}});Mojo.Meta.newClass('MDSS.QueryAggreatedIPT',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._aggregatedIPT=Mojo.$.dss.vector.solutions.intervention.monitor.AggregatedIPT;this._dateAttribute=new MDSS.QueryXML.Attribute(this._aggregatedIPT.CLASS,this._aggregatedIPT.STARTDATE,this._aggregatedIPT.STARTDATE);var startDateAttr=new MDSS.QueryXML.Attribute(this._aggregatedIPT.CLASS,this._aggregatedIPT.STARTDATE,this._aggregatedIPT.STARTDATE);this._startDateSelectable=new MDSS.QueryXML.Selectable(startDateAttr);var endDateAttr=new MDSS.QueryXML.Attribute(this._aggregatedIPT.CLASS,this._aggregatedIPT.ENDDATE,this._aggregatedIPT.ENDDATE);this._endDateSelectable=new MDSS.QueryXML.Selectable(endDateAttr);this._mainQueryClass=this._aggregatedIPT.CLASS;this._groupByClass=Mojo.$.dss.vector.solutions.intervention.monitor.AggregatedIPT;this._commonQueryClasses=[Mojo.$.dss.vector.solutions.intervention.monitor.IPTDose.CLASS,Mojo.$.dss.vector.solutions.intervention.monitor.IPTPatients.CLASS,Mojo.$.dss.vector.solutions.intervention.monitor.IPTTreatment.CLASS,Mojo.$.dss.vector.solutions.intervention.monitor.IPTANCVisit.CLASS];this._exclusionClasses=[];var instance=new this._aggregatedIPT();this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,display:instance.getGeoEntityMd().getDisplayLabel()}];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);var picker=this.getGeoPicker();picker.setPolitical(false);picker.setSprayTargetAllowed(false);}}});Mojo.Meta.newClass('MDSS.QueryIndividualIPT',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._groupByClass=Mojo.$.dss.vector.solutions.intervention.monitor.IndividualIPT;this._mainQueryClass=this._groupByClass.CLASS;this._individualIPT=new this._groupByClass();this._dateAttribs=[{klass:this._groupByClass,accessor:this._groupByClass.SERVICEDATE,}];this._commonQueryClasses=["dss.vector.solutions.intervention.monitor.IndividualIPTCase",Mojo.$.dss.vector.solutions.Person.CLASS,];this._exclusionClasses=[];var tmpPerson=new Mojo.$.dss.vector.solutions.Person();this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.FACILITY,display:this._individualIPT.getFacilityMd().getDisplayLabel()},{keyName:Mojo.$.dss.vector.solutions.Person.CLASS+'.'+Mojo.$.dss.vector.solutions.Person.RESIDENTIALGEOENTITY,display:tmpPerson.getResidentialGeoEntityMd().getDisplayLabel()},{keyName:Mojo.$.dss.vector.solutions.Person.CLASS+'.'+Mojo.$.dss.vector.solutions.Person.WORKGEOENTITY,display:tmpPerson.getWorkGeoEntityMd().getDisplayLabel()}];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);var picker=this.getGeoPicker();picker.setPolitical(false);picker.setSprayTargetAllowed(false);}}});Mojo.Meta.newClass('MDSS.QueryIndividualCases',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._groupByClass=Mojo.$.dss.vector.solutions.intervention.monitor.IndividualCase;this._individualCase=new this._groupByClass();this._mainQueryClass=this._groupByClass.CLASS;this._individualInstance=Mojo.$.dss.vector.solutions.intervention.monitor.IndividualInstance;this._commonQueryClasses=[this._groupByClass.CLASS,this._individualInstance.CLASS,Mojo.$.dss.vector.solutions.Person.CLASS,];this._exclusionClasses=[];var tmpPerson=new Mojo.$.dss.vector.solutions.Person();var tmpCase=new this._groupByClass;var tmpInstance=new this._individualInstance();this._dateAttribs=[{klass:this._groupByClass,accessor:this._groupByClass.DIAGNOSISDATE,},{klass:this._groupByClass,accessor:this._groupByClass.CASEREPORTDATE,},{klass:this._groupByClass,accessor:this._groupByClass.CASEENTRYDATE,},{klass:this._individualInstance,accessor:this._individualInstance.SYMPTOMONSET,},{klass:this._individualInstance,accessor:this._individualInstance.FACILITYVISIT,},{klass:this._individualInstance,accessor:this._individualInstance.ADMISSIONDATE,},{klass:this._individualInstance,accessor:this._individualInstance.RELEASEDATE,},{klass:this._individualInstance,accessor:this._individualInstance.TESTSAMPLEDATE,},{klass:this._individualInstance,accessor:this._individualInstance.LABTESTDATE,},];this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.PROBABLESOURCE,display:tmpCase.getProbableSourceMd().getDisplayLabel()},{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.RESIDENCE,display:tmpCase.getResidenceMd().getDisplayLabel()},{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.WORKPLACE,display:tmpCase.getWorkplaceMd().getDisplayLabel()},{keyName:this._individualInstance.CLASS+'.'+this._individualInstance.HEALTHFACILITY,display:tmpInstance.getHealthFacilityMd().getDisplayLabel()},];this._queryType=this._mainQueryClass;this._moUsesView=false;this.$initialize(selectableGroups,queryList);var picker=this.getGeoPicker();picker.setPolitical(true);picker.setSprayTargetAllowed(false);picker.addExtraUniversal('dss.vector.solutions.geo.generated.HealthFacility*');},}});Mojo.Meta.newClass('MDSS.QueryITNCommunityDistribution',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._aggregatedITN=Mojo.$.dss.vector.solutions.intervention.monitor.ITNCommunityDistribution;this._dateAttribute=new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS,this._aggregatedITN.STARTDATE,this._aggregatedITN.STARTDATE);var startDateAttr=new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS,this._aggregatedITN.STARTDATE,this._aggregatedITN.STARTDATE);this._startDateSelectable=new MDSS.QueryXML.Selectable(startDateAttr);var endDateAttr=new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS,this._aggregatedITN.ENDDATE,this._aggregatedITN.ENDDATE);this._endDateSelectable=new MDSS.QueryXML.Selectable(endDateAttr);this._mainQueryClass=this._aggregatedITN.CLASS;this._groupByClass=Mojo.$.dss.vector.solutions.intervention.monitor.ITNCommunityDistribution;this._commonQueryClasses=[Mojo.$.dss.vector.solutions.intervention.monitor.ITNCommunityNet.CLASS,Mojo.$.dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroup.CLASS,];this._exclusionClasses=[];var instance=new this._aggregatedITN();this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.DISTRIBUTIONLOCATION,display:instance.getDistributionLocationMd().getDisplayLabel()},{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.HOUSEHOLDADDRESS,display:instance.getHouseholdAddressMd().getDisplayLabel()}];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);var picker=this.getGeoPicker();picker.setPolitical(false);picker.setSprayTargetAllowed(false);}}});Mojo.Meta.newClass('MDSS.QueryITNFacilityDistribution',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._itn=Mojo.$.dss.vector.solutions.intervention.monitor.ITNDistribution;this._mainQueryClass=this._itn.CLASS;this._groupByClass=Mojo.$.dss.vector.solutions.intervention.monitor.ITNDistribution;this._dateAttribs=[{klass:this._groupByClass,accessor:this._groupByClass.DISTRIBUTIONDATE,}];this._commonQueryClasses=[Mojo.$.dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup.CLASS,Mojo.$.dss.vector.solutions.Person.CLASS,];this._exclusionClasses=[];var instance=new this._itn();var tmpPerson=new Mojo.$.dss.vector.solutions.Person();this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.FACILITY,display:instance.getFacilityMd().getDisplayLabel()},{keyName:Mojo.$.dss.vector.solutions.Person.CLASS+'.'+Mojo.$.dss.vector.solutions.Person.RESIDENTIALGEOENTITY,display:tmpPerson.getResidentialGeoEntityMd().getDisplayLabel()},{keyName:Mojo.$.dss.vector.solutions.Person.CLASS+'.'+Mojo.$.dss.vector.solutions.Person.WORKGEOENTITY,display:tmpPerson.getWorkGeoEntityMd().getDisplayLabel()}];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);var picker=this.getGeoPicker();picker.setPolitical(false);picker.setSprayTargetAllowed(false);}}});Mojo.Meta.newClass('MDSS.QueryAggreatedITN',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._aggregatedITN=Mojo.$.dss.vector.solutions.intervention.monitor.ITNData;this._dateAttribute=new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS,this._aggregatedITN.STARTDATE,this._aggregatedITN.STARTDATE);var startDateAttr=new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS,this._aggregatedITN.STARTDATE,this._aggregatedITN.STARTDATE);this._startDateSelectable=new MDSS.QueryXML.Selectable(startDateAttr);var endDateAttr=new MDSS.QueryXML.Attribute(this._aggregatedITN.CLASS,this._aggregatedITN.ENDDATE,this._aggregatedITN.ENDDATE);this._endDateSelectable=new MDSS.QueryXML.Selectable(endDateAttr);this._mainQueryClass=this._aggregatedITN.CLASS;this._groupByClass=Mojo.$.dss.vector.solutions.intervention.monitor.ITNData;this._commonQueryClasses=[Mojo.$.dss.vector.solutions.intervention.monitor.ITNNet.CLASS,Mojo.$.dss.vector.solutions.intervention.monitor.ITNTargetGroup.CLASS,Mojo.$.dss.vector.solutions.intervention.monitor.ITNService.CLASS,];this._exclusionClasses=[];var instance=new this._aggregatedITN();this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,display:instance.getGeoEntityMd().getDisplayLabel()}];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);var picker=this.getGeoPicker();picker.setPolitical(false);picker.setSprayTargetAllowed(false);}}});Mojo.Meta.newClass('MDSS.QueryStock',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._groupByClass=Mojo.$.dss.vector.solutions.stock.StockEvent;this._mainQueryClass=Mojo.$.dss.vector.solutions.stock.StockItem.CLASS,this._stock=new this._groupByClass();this._dateAttribs=[{klass:this._groupByClass,accessor:this._groupByClass.EVENTDATE,}];this._commonQueryClasses=[this._groupByClass.CLASS,Mojo.$.dss.vector.solutions.stock.StockItem.CLASS,Mojo.$.dss.vector.solutions.Person.CLASS,];this._exclusionClasses=[];var tmpPerson=new Mojo.$.dss.vector.solutions.Person();this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.STOCKDEPOT,display:this._stock.getStockDepotMd().getDisplayLabel()},];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);var picker=this.getGeoPicker();picker.setPolitical(true);picker.setSprayTargetAllowed(false);picker.addExtraUniversal('dss.vector.solutions.geo.generated.StockDepot*');this._exclusionClasses.shift();},}});Mojo.Meta.newClass('MDSS.QueryLarvacide',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._groupByClass=Mojo.$.dss.vector.solutions.intervention.monitor.Larvacide;this._mainQueryClass=this._groupByClass.CLASS;this._larvacide=new this._groupByClass();this._dateAttribute=new MDSS.QueryXML.Attribute(this._groupByClass.CLASS,this._groupByClass.STARTDATE,this._groupByClass.STARTDATE);var startDateAttr=new MDSS.QueryXML.Attribute(this._groupByClass.CLASS,this._groupByClass.STARTDATE,this._groupByClass.STARTDATE);this._startDateSelectable=new MDSS.QueryXML.Selectable(startDateAttr);var endDateAttr=new MDSS.QueryXML.Attribute(this._groupByClass.CLASS,this._groupByClass.COMPLETIONDATE,this._groupByClass.COMPLETIONDATE);this._endDateSelectable=new MDSS.QueryXML.Selectable(endDateAttr);this._commonQueryClasses=[this._groupByClass.CLASS,"dss.vector.solutions.intervention.monitor.LarvacideInstance",Mojo.$.dss.vector.solutions.Person.CLASS,];this._exclusionClasses=[];var tmpPerson=new Mojo.$.dss.vector.solutions.Person();this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,display:this._larvacide.getGeoEntityMd().getDisplayLabel()},];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);var picker=this.getGeoPicker();picker.setPolitical(false);picker.setSprayTargetAllowed(false);},}});Mojo.Meta.newClass('MDSS.QueryMosquitoCollections',{Extends:MDSS.QueryBaseNew,Instance:{initialize:function(selectableGroups,queryList)
{this._groupByClass=Mojo.$.dss.vector.solutions.entomology.MosquitoCollection;this._mainQueryClass=this._groupByClass.CLASS;this._mosquitoCollection=new this._groupByClass();this._showRatioSelectable=true;this._commonQueryClasses=[Mojo.$.dss.vector.solutions.entomology.SubCollection.CLASS,];this._exclusionClasses=[];this._geoEntityAttribs=[{keyName:this._groupByClass.CLASS+'.'+this._groupByClass.GEOENTITY,display:this._mosquitoCollection.getGeoEntityMd().getDisplayLabel()},];this._dateAttribs=[{klass:this._groupByClass,accessor:this._groupByClass.COLLECTIONDATE,}];this._queryType=this._mainQueryClass;this.$initialize(selectableGroups,queryList);},_visibleAttributeHandler:function(e,attribute)
{var check=e.target;var liTarget=YAHOO.util.Dom.getAncestorByTagName(check,"LI");if(check.checked)
{this._uncheckAllNotInGroup(check);if(check.id.indexOf('abundance_')>-1)
{this._checkBox('taxon');this._checkBox('collectionMethod_ab');}
this._addVisibleAttribute(attribute);var select=check.nextSibling;select.selectedIndex=0;select.disabled=false;}
else
{this._removeVisibleAttribute(attribute,true,true,true);var select=check.nextSibling;select.selectedIndex=0;select.disabled=true;var menus=this._menus[liTarget.id];if(menus)
{Mojo.Iter.forEach(menus,function(ck){if(ck.checked)ck.checked=false;},this);}
this.clearBrowserTerms(attribute);if(check.id=='taxon')
{_uncheckAllByClass(liTarget,'ab')}}},}});