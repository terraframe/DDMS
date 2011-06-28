
YAHOO.util.Event.onContentReady("mainNav",function(){var oMenuBar=new YAHOO.widget.MenuBar("mainNav",{autosubmenudisplay:true,hidedelay:750,lazyload:true});var aSubmenuData=[{id:"Administration",itemdata:[{text:"Configure_System_Variables(109)",url:"dss.vector.solutions.PropertyController.viewAll.mojo",visibleTo:'Administrator'},{text:"Update_Country_Image",url:"dss.vector.solutions.PropertyController.editFlag.mojo",visibleTo:'Administrator'},{text:"Configure_Malaria_Season(127)",url:"dss.vector.solutions.general.MalariaSeasonController.viewAll.mojo",visibleTo:'Administrator'},{text:"Configure_Epi_Week(119)",url:"dss.vector.solutions.PropertyController.newInstance.mojo",visibleTo:'Administrator'},{text:"Enter_Population_Data(003)",url:"dss.vector.solutions.general.PopulationDataController.search.mojo",visibleTo:'Administrator'},{text:"Alerts",submenu:{id:"Alerts",itemdata:[{text:"View_All_SystemAlert",url:"dss.vector.solutions.general.SystemAlertController.viewAll.mojo",visibleTo:'Administrator'},{text:"View_All_EmailConfiguration",url:"dss.vector.solutions.general.EmailConfigurationController.viewAll.mojo",visibleTo:'Administrator'},{text:"View_All_Email",url:"dss.vector.solutions.general.EmailController.viewAll.mojo",visibleTo:'Administrator'}]}},{text:"User_Administration",submenu:{id:"User_Administration",itemdata:[{text:"Manage_People(114)",url:"dss.vector.solutions.PersonController.viewAll.mojo",visibleTo:'Administrator'},{text:"Edit_Visibility(117)",url:"dss.vector.solutions.util.ReadableAttributeController.getUniversal.mojo",visibleTo:'Administrator'},{text:"Manage_Roles(144)",url:"dss.vector.solutions.RoleController.viewAll.mojo",visibleTo:'Administrator'}]}},{text:"Synchronise",submenu:{id:"Synchronise",itemdata:[{text:"Transaction_Log",url:"dss.vector.solutions.synchronization.TransactionController.viewRecordPage.mojo",visibleTo:'Administrator'},{text:"Import_Log",url:"dss.vector.solutions.synchronization.ImportController.viewLog.mojo",visibleTo:'Administrator'}]}},{text:"Localization",disabled:false,url:"dss.vector.solutions.util.LocalizationController.selectLocales.mojo",visibleTo:'Administrator'},{text:"Ontology_Fields",url:"dss.vector.solutions.ontology.BrowserFieldController.viewAll.mojo",visibleTo:'Administrator'},{text:"Ontology_Admin",url:"dss.vector.solutions.ontology.TermController.viewTree.mojo",visibleTo:'Administrator'}]},{id:"Case_Surveillance",itemdata:[{text:"Aggregated_Cases",submenu:{id:"Aggregated_Cases",itemdata:[{text:"Search_for_Aggregated_Cases(002)",url:"dss.vector.solutions.surveillance.AggregatedCaseController.search.mojo",visibleTo:'Administrator'},{text:"Query_Aggregated_Cases(129)",url:"dss.vector.solutions.query.QueryController.queryAggregatedCases.mojo",visibleTo:'Administrator'}]}},{text:"Individual_Cases",submenu:{id:"Individual_Cases",itemdata:[{text:"Enter_For_Individual_Cases(001)",url:"dss.vector.solutions.intervention.monitor.IndividualCaseController.newInstance.mojo",visibleTo:'Administrator'},{text:"Query_Individual_Cases(128)",url:"dss.vector.solutions.query.QueryController.queryIndividualCases.mojo",visibleTo:'Administrator'}]}},{text:"Thresholds",submenu:{id:"Thresholds",itemdata:[{text:"Configure_Thresholds",url:"dss.vector.solutions.general.ThresholdDataController.editThresholdConfiguration.mojo",visibleTo:'Administrator'},{text:"Enter_Thresholds(105)",url:"dss.vector.solutions.general.ThresholdDataController.search.mojo",visibleTo:'Administrator'}]}},{text:"Configure_Case_Surveillance",url:"dss.vector.solutions.surveillance.AggregatedAgeGroupController.viewAll.mojo",visibleTo:'Administrator'}]},{id:"Entomology_Surveillance",itemdata:[{text:"Search_For_Collections(017)",url:"dss.vector.solutions.entomology.MosquitoCollectionController.search.mojo",visibleTo:'Administrator'},{text:"Query_Mosquito_Collections(148)",url:"dss.vector.solutions.query.QueryController.queryMosquitoCollections.mojo",visibleTo:'Administrator'},{text:"Enter_Infection_Assays(018)",url:"dss.vector.solutions.entomology.AssayController.searchInfectionAssay.mojo",visibleTo:'Administrator'},{text:"Enter_Mechanism_Assays(013)",url:"dss.vector.solutions.entomology.AssayController.searchMechanismAssay.mojo",visibleTo:'Administrator'},{text:"Query_Entomology(145)",url:"dss.vector.solutions.query.QueryController.queryEntomology.mojo",visibleTo:'Administrator'},{text:"Resistance_Monitoring",submenu:{id:"Resistance_Monitoring",itemdata:[{text:"Adult_DDA(004)",url:"dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.viewAll.mojo",visibleTo:'Administrator'},{text:"Larvae_DDA(008)",url:"dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.viewAll.mojo",visibleTo:'Administrator'},{text:"Knock_Down_Assay(006)",url:"dss.vector.solutions.entomology.assay.KnockDownAssayController.viewAll.mojo",visibleTo:'Administrator'},{text:"Query_Resistance(033)",url:"dss.vector.solutions.query.QueryController.queryResistance.mojo",visibleTo:'Administrator'},]}},{text:"Efficacy_studies",submenu:{id:"Efficacy_studies",itemdata:[{text:"Search_for_Efficacy_Studies_Adult(025)",url:"dss.vector.solutions.entomology.assay.EfficacyAssayController.viewAll.mojo",visibleTo:'Administrator'},{text:"Query_Efficacy_Studies(147)",url:"dss.vector.solutions.query.QueryController.queryEfficacyAssay.mojo",visibleTo:'Administrator'}]}},{text:"Configuration",submenu:{id:"Configuration",itemdata:[{text:"Configure_Entomology_Variables",url:"dss.vector.solutions.entomology.ResistancePropertyController.viewAll.mojo",visibleTo:'Administrator'},{text:"Manage_Insecticides",url:"dss.vector.solutions.general.InsecticideController.viewAll.mojo",visibleTo:'Administrator'},{text:"Manage_Knock_Down_Properties(009)",url:"dss.vector.solutions.general.KnockDownTimePropertyController.search.mojo",visibleTo:'Administrator'},{text:"Manage_Lethal_Properties(009)",url:"dss.vector.solutions.general.LethalTimePropertyController.search.mojo",visibleTo:'Administrator'},]}},]},{id:"Surveys",itemdata:[{text:"Enter_Indicator_Survey_records(026)",url:"dss.vector.solutions.intervention.monitor.SurveyPointController.viewAll.mojo",visibleTo:'Administrator'},{text:"Query_Indicator_Surveys(140)",url:"dss.vector.solutions.query.QueryController.querySurvey.mojo",visibleTo:'Administrator'},]},{id:"Intervention_Planning",itemdata:[{text:"IRS_Intervention_Planning",submenu:{id:"IRS_INV",itemdata:[{text:"IRS_Planning(077)",url:"dss.vector.solutions.irs.InterventionPlanningController.search.mojo",visibleTo:'Administrator'},{text:"Configure_Application_Rate(106)",url:"dss.vector.solutions.irs.ApplicationRateController.view.mojo",visibleTo:'Administrator'},{text:"Manage_IRS_Teams(141)",url:"dss.vector.solutions.irs.SprayTeamController.viewAll.mojo",visibleTo:'Administrator'},{text:"Area_Targets(028)",url:"dss.vector.solutions.irs.GeoTargetController.viewAll.mojo",visibleTo:'Administrator'},{text:"Operator_and_Team_Targets(028)",url:"dss.vector.solutions.irs.ResourceTargetController.viewAll.mojo",visibleTo:'Administrator'},]}},]},{id:"Intervention_Monitoring",itemdata:[{text:"IRS",submenu:{id:"IRS_SPRAYS",itemdata:[{text:"Operator_Spray_Level_1(027)",url:"dss.vector.solutions.irs.OperatorSprayController.search.mojo",visibleTo:'Administrator'},{text:"Team_Spray_Level_2(027)",url:"dss.vector.solutions.irs.TeamSprayController.search.mojo",visibleTo:'Administrator'},{text:"Sprayed_Area_Level_3(027)",url:"dss.vector.solutions.irs.ZoneSprayController.search.mojo",visibleTo:'Administrator'},{text:"Query_IRS(132)",url:"dss.vector.solutions.query.QueryController.queryIRS.mojo",visibleTo:'Administrator'}]}},{text:"IPT",submenu:{id:"IPT",itemdata:[{text:"Enter_Aggregated_IPT_records(069)",url:"dss.vector.solutions.intervention.monitor.AggregatedIPTController.search.mojo",visibileTo:'Administrator'},{text:"Query_Aggregated_IPT(131)",url:"dss.vector.solutions.query.QueryController.queryAggregatedIPT.mojo",visibileTo:'Administrator'},{text:"Enter_Individual_IPT_records(081)",url:"dss.vector.solutions.intervention.monitor.IndividualIPTCaseController.search.mojo",visibileTo:'Administrator'},{text:"Query_Individual_IPT(130)",url:"dss.vector.solutions.query.QueryController.queryIndividualIPT.mojo",visibileTo:'Administrator'}]}},{text:"ITN",submenu:{id:"ITN",itemdata:[{text:"Aggregated_ITN_Data_Distribution",url:"dss.vector.solutions.intervention.monitor.ITNDataController.search.mojo",visibileTo:'Administrator'},{text:"Query_Aggregated_ITN_Data_Distribution",url:"dss.vector.solutions.query.QueryController.queryAggregatedITN.mojo",visibileTo:'Administrator'},{text:"ITN_Facility_Distribution",url:"dss.vector.solutions.intervention.monitor.ITNDistributionController.search.mojo",visibileTo:'Administrator'},{text:"Query_ITN_Facility_Distribution",url:"dss.vector.solutions.query.QueryController.queryITNDistribution.mojo",visibileTo:'Administrator'},{text:"ITN_Community_Distribution",url:"dss.vector.solutions.intervention.monitor.ITNCommunityDistributionController.viewAll.mojo",visibileTo:'Administrator'},{text:"Query_ITN_Community_Distribution",url:"dss.vector.solutions.query.QueryController.queryITNCommunityDistribution.mojo",visibileTo:'Administrator'}]}},{text:"Control_of_Immatures",submenu:{id:"Control_of_Immatures",itemdata:[{text:"Enter_for_Control_of_Immatures_records",url:"dss.vector.solutions.intervention.monitor.LarvacideController.viewAll.mojo",visibileTo:'Administrator'},{text:"Query_Control_of_Immatures",url:"dss.vector.solutions.query.QueryController.queryLarvacide.mojo",visibileTo:'Administrator'}]}},]},{id:"StockControl",itemdata:[{text:"Configure_Stock_Items(032)",url:"dss.vector.solutions.stock.StockItemController.viewAll.mojo",visibileTo:'Administrator'},{text:"Manage_Stock(032)",url:"dss.vector.solutions.stock.StockEventController.search.mojo",visibileTo:'Administrator'},{text:"Query_Stock",url:"dss.vector.solutions.query.QueryController.queryStock.mojo",visibileTo:'Administrator'}]},{id:"GIS",itemdata:[{text:"Configure_the_Universal_Tree(101)",url:"dss.vector.solutions.geo.GeoEntityTypeController.viewHierarchyTree.mojo?rootGeoHierarchyId=",visibleTo:'Administrator'},{text:"Manage_Geo_Entities",url:"dss.vector.solutions.geo.GeoEntityTreeController.displayTree.mojo?rootGeoEntityId=",visibleTo:'Administrator'},{text:"Generate_Maps",url:"dss.vector.solutions.query.MappingController.generateMaps.mojo",visibleTo:'Administrator'}]},];function localizeText(text_obj)
{for each(var obj in text_obj)
{if(typeof obj=='object')
{localizeText(obj);}}
if(typeof text_obj.text=='string')
{var label=text_obj.text.split('(')[0];label=MDSS.localize(label);if(text_obj.text.split('(')[1])
{}
text_obj.text=label;}}
localizeText(aSubmenuData);var ua=YAHOO.env.ua,oAnim;function onSubmenuBeforeShow(p_sType,p_sArgs){var oBody,oElement,oShadow,oUL;if(this.parent){oElement=this.element;oShadow=oElement.lastChild;oShadow.style.height="0px";if(oAnim&&oAnim.isAnimated()){oAnim.stop();oAnim=null;}
oBody=this.body;if(this.parent&&!(this.parent instanceof YAHOO.widget.MenuBarItem)){if(ua.gecko||ua.opera){oBody.style.width=oBody.clientWidth+"px";}
if(ua.ie==7){oElement.style.width=oElement.clientWidth+"px";}}
oBody.style.overflow="hidden";oUL=oBody.getElementsByTagName("ul")[0];oUL.style.marginTop=("-"+oUL.offsetHeight+"px");}}
function onTween(p_sType,p_aArgs,p_oShadow){if(this.cfg.getProperty("iframe")){this.syncIframe();}
if(p_oShadow){p_oShadow.style.height=this.element.offsetHeight+"px";}}
function onAnimationComplete(p_sType,p_aArgs,p_oShadow){var oBody=this.body,oUL=oBody.getElementsByTagName("ul")[0];if(p_oShadow){p_oShadow.style.height=this.element.offsetHeight+"px";}
oUL.style.marginTop="";oBody.style.overflow="";if(this.parent&&!(this.parent instanceof YAHOO.widget.MenuBarItem)){if(ua.gecko||ua.opera){oBody.style.width="";}
if(ua.ie==7){this.element.style.width="";}}}
function onSubmenuShow(p_sType,p_sArgs){var oElement,oShadow,oUL;if(this.parent){oElement=this.element;oShadow=oElement.lastChild;oUL=this.body.getElementsByTagName("ul")[0];oAnim=new YAHOO.util.Anim(oUL,{marginTop:{to:0}},.5,YAHOO.util.Easing.easeOut);oAnim.onStart.subscribe(function(){oShadow.style.height="100%";});oAnim.animate();if(YAHOO.env.ua.ie){oShadow.style.height=oElement.offsetHeight+"px";oAnim.onTween.subscribe(onTween,oShadow,this);}
oAnim.onComplete.subscribe(onAnimationComplete,oShadow,this);}}
oMenuBar.subscribe("beforeRender",function(){var nSubmenus=aSubmenuData.length,i;if(this.getRoot()==this){for(i=0;i<nSubmenus;i++){this.getItem(i).cfg.setProperty("submenu",aSubmenuData[i]);}}});oMenuBar.subscribe("beforeShow",onSubmenuBeforeShow);oMenuBar.subscribe("show",onSubmenuShow);oMenuBar.render();});Date.$VERSION=1.02;Date.LZ=function(x){return(x<0||x>9?"":"0")+x};Date.monthNames=new Array('January','February','March','April','May','June','July','August','September','October','November','December');Date.monthAbbreviations=new Array('Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');Date.dayNames=new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday');Date.dayAbbreviations=new Array('Sun','Mon','Tue','Wed','Thu','Fri','Sat');Date.preferAmericanFormat=true;if(!Date.prototype.getFullYear){Date.prototype.getFullYear=function(){var yy=this.getYear();return(yy<1900?yy+1900:yy);};}
Date.parseString=function(val,format){if(typeof(format)=="undefined"||format==null||format==""){var generalFormats=new Array('y-M-d','MMM d, y','MMM d,y','y-MMM-d','d-MMM-y','MMM d','MMM-d','d-MMM');var monthFirst=new Array('M/d/y','M-d-y','M.d.y','M/d','M-d');var dateFirst=new Array('d/M/y','d-M-y','d.M.y','d/M','d-M');var checkList=new Array(generalFormats,Date.preferAmericanFormat?monthFirst:dateFirst,Date.preferAmericanFormat?dateFirst:monthFirst);for(var i=0;i<checkList.length;i++){var l=checkList[i];for(var j=0;j<l.length;j++){var d=Date.parseString(val,l[j]);if(d!=null){return d;}}}
return null;};this.isInteger=function(val){for(var i=0;i<val.length;i++){if("1234567890".indexOf(val.charAt(i))==-1){return false;}}
return true;};this.getInt=function(str,i,minlength,maxlength){for(var x=maxlength;x>=minlength;x--){var token=str.substring(i,i+x);if(token.length<minlength){return null;}
if(this.isInteger(token)){return token;}}
return null;};val=val+"";format=format+"";var i_val=0;var i_format=0;var c="";var token="";var token2="";var x,y;var year=new Date().getFullYear();var month=1;var date=1;var hh=0;var mm=0;var ss=0;var ampm="";while(i_format<format.length){c=format.charAt(i_format);token="";while((format.charAt(i_format)==c)&&(i_format<format.length)){token+=format.charAt(i_format++);}
if(token=="yyyy"||token=="yy"||token=="y"){if(token=="yyyy"){x=4;y=4;}
if(token=="yy"){x=2;y=2;}
if(token=="y"){x=2;y=4;}
year=this.getInt(val,i_val,x,y);if(year==null){return null;}
i_val+=year.length;if(year.length==2){if(year>70){year=1900+(year-0);}
else{year=2000+(year-0);}}}
else if(token=="MMM"||token=="NNN"){month=0;var names=(token=="MMM"?(Date.monthNames.concat(Date.monthAbbreviations)):Date.monthAbbreviations);for(var i=0;i<names.length;i++){var month_name=names[i];if(val.substring(i_val,i_val+month_name.length).toLowerCase()==month_name.toLowerCase()){month=(i%12)+1;i_val+=month_name.length;break;}}
if((month<1)||(month>12)){return null;}}
else if(token=="EE"||token=="E"){var names=(token=="EE"?Date.dayNames:Date.dayAbbreviations);for(var i=0;i<names.length;i++){var day_name=names[i];if(val.substring(i_val,i_val+day_name.length).toLowerCase()==day_name.toLowerCase()){i_val+=day_name.length;break;}}}
else if(token=="MM"||token=="M"){month=this.getInt(val,i_val,token.length,2);if(month==null||(month<1)||(month>12)){return null;}
i_val+=month.length;}
else if(token=="dd"||token=="d"){date=this.getInt(val,i_val,token.length,2);if(date==null||(date<1)||(date>31)){return null;}
i_val+=date.length;}
else if(token=="hh"||token=="h"){hh=this.getInt(val,i_val,token.length,2);if(hh==null||(hh<1)||(hh>12)){return null;}
i_val+=hh.length;}
else if(token=="HH"||token=="H"){hh=this.getInt(val,i_val,token.length,2);if(hh==null||(hh<0)||(hh>23)){return null;}
i_val+=hh.length;}
else if(token=="KK"||token=="K"){hh=this.getInt(val,i_val,token.length,2);if(hh==null||(hh<0)||(hh>11)){return null;}
i_val+=hh.length;hh++;}
else if(token=="kk"||token=="k"){hh=this.getInt(val,i_val,token.length,2);if(hh==null||(hh<1)||(hh>24)){return null;}
i_val+=hh.length;hh--;}
else if(token=="mm"||token=="m"){mm=this.getInt(val,i_val,token.length,2);if(mm==null||(mm<0)||(mm>59)){return null;}
i_val+=mm.length;}
else if(token=="ss"||token=="s"){ss=this.getInt(val,i_val,token.length,2);if(ss==null||(ss<0)||(ss>59)){return null;}
i_val+=ss.length;}
else if(token=="a"){if(val.substring(i_val,i_val+2).toLowerCase()=="am"){ampm="AM";}
else if(val.substring(i_val,i_val+2).toLowerCase()=="pm"){ampm="PM";}
else{return null;}
i_val+=2;}
else{if(val.substring(i_val,i_val+token.length)!=token){return null;}
else{i_val+=token.length;}}}
if(i_val!=val.length){return null;}
if(month==2){if(((year%4==0)&&(year%100!=0))||(year%400==0)){if(date>29){return null;}}
else{if(date>28){return null;}}}
if((month==4)||(month==6)||(month==9)||(month==11)){if(date>30){return null;}}
if(hh<12&&ampm=="PM"){hh=hh-0+12;}
else if(hh>11&&ampm=="AM"){hh-=12;}
return new Date(year,month-1,date,hh,mm,ss);};Date.isValid=function(val,format){return(Date.parseString(val,format)!=null);};Date.prototype.isBefore=function(date2){if(date2==null){return false;}
return(this.getTime()<date2.getTime());};Date.prototype.isAfter=function(date2){if(date2==null){return false;}
return(this.getTime()>date2.getTime());};Date.prototype.equals=function(date2){if(date2==null){return false;}
return(this.getTime()==date2.getTime());};Date.prototype.equalsIgnoreTime=function(date2){if(date2==null){return false;}
var d1=new Date(this.getTime()).clearTime();var d2=new Date(date2.getTime()).clearTime();return(d1.getTime()==d2.getTime());};Date.prototype.format=function(format){format=format+"";var result="";var i_format=0;var c="";var token="";var y=this.getYear()+"";var M=this.getMonth()+1;var d=this.getDate();var E=this.getDay();var H=this.getHours();var m=this.getMinutes();var s=this.getSeconds();var yyyy,yy,MMM,MM,dd,hh,h,mm,ss,ampm,HH,H,KK,K,kk,k;var value=new Object();if(y.length<4){y=""+(+y+1900);}
value["y"]=""+y;value["yyyy"]=y;value["yy"]=y.substring(2,4);value["M"]=M;value["MM"]=Date.LZ(M);value["MMM"]=Date.monthNames[M-1];value["NNN"]=Date.monthAbbreviations[M-1];value["d"]=d;value["dd"]=Date.LZ(d);value["E"]=Date.dayAbbreviations[E];value["EE"]=Date.dayNames[E];value["H"]=H;value["HH"]=Date.LZ(H);if(H==0){value["h"]=12;}
else if(H>12){value["h"]=H-12;}
else{value["h"]=H;}
value["hh"]=Date.LZ(value["h"]);value["K"]=value["h"]-1;value["k"]=value["H"]+1;value["KK"]=Date.LZ(value["K"]);value["kk"]=Date.LZ(value["k"]);if(H>11){value["a"]="PM";}
else{value["a"]="AM";}
value["m"]=m;value["mm"]=Date.LZ(m);value["s"]=s;value["ss"]=Date.LZ(s);while(i_format<format.length){c=format.charAt(i_format);token="";while((format.charAt(i_format)==c)&&(i_format<format.length)){token+=format.charAt(i_format++);}
if(typeof(value[token])!="undefined"){result=result+value[token];}
else{result=result+token;}}
return result;};Date.prototype.getDayName=function(){return Date.dayNames[this.getDay()];};Date.prototype.getDayAbbreviation=function(){return Date.dayAbbreviations[this.getDay()];};Date.prototype.getMonthName=function(){return Date.monthNames[this.getMonth()];};Date.prototype.getMonthAbbreviation=function(){return Date.monthAbbreviations[this.getMonth()];};Date.prototype.clearTime=function(){this.setHours(0);this.setMinutes(0);this.setSeconds(0);this.setMilliseconds(0);return this;};Date.prototype.add=function(interval,number){if(typeof(interval)=="undefined"||interval==null||typeof(number)=="undefined"||number==null){return this;}
number=+number;if(interval=='y'){this.setFullYear(this.getFullYear()+number);}
else if(interval=='M'){this.setMonth(this.getMonth()+number);}
else if(interval=='d'){this.setDate(this.getDate()+number);}
else if(interval=='w'){var step=(number>0)?1:-1;while(number!=0){this.add('d',step);while(this.getDay()==0||this.getDay()==6){this.add('d',step);}
number-=step;}}
else if(interval=='h'){this.setHours(this.getHours()+number);}
else if(interval=='m'){this.setMinutes(this.getMinutes()+number);}
else if(interval=='s'){this.setSeconds(this.getSeconds()+number);}
return this;};var Selectbox={};Selectbox.hasOptions=function(obj){return(obj!=null&&typeof(obj.options)!="undefined"&&obj.options!=null);};Selectbox.selectUnselectMatchingOptions=function(obj,regex,which,only){if(window.RegExp){if(!this.hasOptions(obj)){return false;}
var only=!(typeof(only)=="undefined"||only==null);var re=new RegExp(regex);for(var i=0;i<obj.options.length;i++){if(re.test(obj.options[i].text)){obj.options[i].selected=(which=="select");}
else if(only){obj.options[i].selected=(which=="unselect");}}
return true;}
return false;};Selectbox.selectOptions=function(obj,regex){return this.selectUnselectMatchingOptions(obj,regex,"select",false);};Selectbox.selectOnlyOptions=function(obj,regex){return this.selectUnselectMatchingOptions(obj,regex,"select",true);};Selectbox.unselectOptions=function(obj,regex){return this.selectUnselectMatchingOptions(obj,regex,"unselect",false);};Selectbox.sort=function(obj){var o=[];if(!this.hasOptions(obj)){return false;}
for(var i=0;i<obj.options.length;i++){o[o.length]=new Option(obj.options[i].text,obj.options[i].value,obj.options[i].defaultSelected,obj.options[i].selected);}
if(o.length==0){return true;}
o=o.sort(function(a,b){if((a.text+"")<(b.text+"")){return-1;}
if((a.text+"")>(b.text+"")){return 1;}
return 0;});for(var i=0;i<o.length;i++){obj.options[i]=new Option(o[i].text,o[i].value,o[i].defaultSelected,o[i].selected);}
return true;};Selectbox.selectAllOptions=function(obj){if(!this.hasOptions(obj)){return false;}
for(var i=0;i<obj.options.length;i++){obj.options[i].selected=true;}
return true;};Selectbox.moveSelectedOptions=function(from,to){if(!this.hasOptions(from)){return false;}
if(arguments.length>3){var regex=arguments[3];if(regex!=""){if(!this.unselectOptions(from,regex)){return false;}}}
for(var i=0;i<from.options.length;i++){var o=from.options[i];if(o.selected){var index=(!this.hasOptions(to))?0:to.options.length;to.options[index]=new Option(o.text,o.value,false,false);}}
for(var i=(from.options.length-1);i>=0;i--){var o=from.options[i];if(o.selected){from.options[i]=null;}}
if((arguments.length<3)||(arguments[2])){this.sort(from);this.sort(to);}
from.selectedIndex=to.selectedIndex=-1;return true;};Selectbox.copySelectedOptions=function(from,to){if(!this.hasOptions(from)){return false;}
var options=new Object();if(this.hasOptions(to)){for(var i=0;i<to.options.length;i++){options[to.options[i].value]=to.options[i].text;}}
for(var i=0;i<from.options.length;i++){var o=from.options[i];if(o.selected){if(typeof(options[o.value])=="undefined"||options[o.value]==null||options[o.value]!=o.text){var index=(!this.hasOptions(to))?0:to.options.length;to.options[index]=new Option(o.text,o.value,false,false);}}}
if((arguments.length<3)||(arguments[2]==true)){this.sort(to);}
from.selectedIndex=to.selectedIndex=-1;return true;};Selectbox.moveAllOptions=function(from,to){this.selectAllOptions(from);if(arguments.length==2){this.moveSelectedOptions(from,to);}
else if(arguments.length==3){this.moveSelectedOptions(from,to,arguments[2]);}
else if(arguments.length==4){this.moveSelectedOptions(from,to,arguments[2],arguments[3]);}};Selectbox.copyAllOptions=function(from,to){this.selectAllOptions(from);if(arguments.length==2){this.copySelectedOptions(from,to);}
else if(arguments.length==3){this.copySelectedOptions(from,to,arguments[2]);}};Selectbox.swapOptions=function(obj,i,j){if(!this.hasOptions(obj)){return false;}
var o=obj.options;if(i<0||i>=o.length||j<0||j>=o.length){return false;}
var i_selected=o[i].selected;var j_selected=o[j].selected;var temp=new Option(o[i].text,o[i].value,o[i].defaultSelected,o[i].selected);var temp2=new Option(o[j].text,o[j].value,o[j].defaultSelected,o[j].selected);o[i]=temp2;o[j]=temp;o[i].selected=j_selected;o[j].selected=i_selected;return true;};Selectbox.moveOptionUp=function(obj){if(!this.hasOptions(obj)){return false;}
for(i=0;i<obj.options.length;i++){if(obj.options[i].selected){if(i>0&&!obj.options[i-1].selected){this.swapOptions(obj,i,i-1);obj.options[i-1].selected=true;}}}
return true;};Selectbox.moveOptionDown=function(obj){if(!this.hasOptions(obj)){return false;}
for(i=obj.options.length-1;i>=0;i--){if(obj.options[i].selected){if(i!=(obj.options.length-1)&&!obj.options[i+1].selected){this.swapOptions(obj,i,i+1);obj.options[i+1].selected=true;}}}
return true;};Selectbox.removeSelectedOptions=function(from){if(!this.hasOptions(from)){return false;}
if(from.type=="select-one"&&from.selectedIndex>=0){from.options[from.selectedIndex]=null;}
else{for(var i=(from.options.length-1);i>=0;i--){var o=from.options[i];if(o.selected){from.options[i]=null;}}}
from.selectedIndex=-1;};Selectbox.removeAllQualifiedOptions=function(from,regex){if(!this.unselectOptions(from,regex)){return false;}
this.removeSelectedOptions(from);};Selectbox.removeAllOptions=function(from){if(!this.hasOptions(from)){return false;}
for(var i=(from.options.length-1);i>=0;i--){from.options[i]=null;}
from.selectedIndex=-1;return true;};Selectbox.addOption=function(obj,text,value,selected){if(obj!=null&&obj.options!=null){obj.options[obj.options.length]=new Option(text,value,false,selected);}};Selectbox.containsOption=function(obj,value){for(var i=0;i<obj.options.length;i++)
{if(obj.options[i].value===value)
{return true;}}
return false;};YAHOO.widget.TreeViewDD=function(id,data,dragDropHandler){YAHOO.widget.TreeViewDD.superclass.constructor.call(this,id,data);this.dragDropHandler=dragDropHandler;};YAHOO.lang.extend(YAHOO.widget.TreeViewDD,YAHOO.widget.TreeView,{drawBegin:function(){},drawEnd:function(){this.getRoot().initDD();},render:function(){this.drawBegin();YAHOO.widget.TreeViewDD.superclass.render.call(this);this.drawEnd();}});YAHOO.widget.DDNode=function(){}
YAHOO.widget.DDNode.prototype={applyParentBeforeDD:YAHOO.widget.Node.prototype.applyParent,applyParent:function(p){this.applyParentBeforeDD.call(this,p);},refreshBeforeDD:YAHOO.widget.Node.prototype.refresh,refresh:function(){this.refreshBeforeDD.call(this);this.initDD();},isDescendentOf:function(parentNode){if(!parentNode||this.isRoot())return false;if(this.parent==parentNode)return true
return this.parent.isDescendentOf(parentNode);},dd:null,getDdId:function(){return"ygtvdd"+this.index;},initDD:function(){if(!this.isRoot()&&this instanceof YAHOO.widget.HTMLNode){if(this.dd==null||this.dd==undefined){this.dd=new YAHOO.util.DDNodeProxy(this,this.getDdId());}else{this.dd.unreg();this.dd.init(this.getDdId());}}
for(var i=0;i<this.children.length;++i){this.children[i].initDD();}}};YAHOO.lang.augment(YAHOO.widget.Node,YAHOO.widget.DDNode,true);YAHOO.widget.TextNode.prototype.getDdId=function(){return this.labelElId;};YAHOO.widget.HTMLNode.prototype.getDdId=function(){return this.contentElId;};YAHOO.util.DDNodeProxy=function(node,id,sGroup,config){YAHOO.util.DDNodeProxy.superclass.constructor.call(this,id,sGroup,config);this.node=node;};YAHOO.extend(YAHOO.util.DDNodeProxy,YAHOO.util.DDProxy,{validDest:function(ddid){destNode=YAHOO.util.DDM.getDDById(ddid).node;return!destNode.isDescendentOf(this.node);},endDrag:function(){},startDrag:function(x,y){var dragEl=this.getDragEl();var clickEl=this.getEl();dragEl.innerHTML=clickEl.innerHTML;dragEl.className=clickEl.className+" dragging";},onDragEnter:function(e,id){if(this.validDest(id)){el=this.getElDom(id);el.classNameBeforeDrag=el.className;el.className+=' drag-hint';}},onDragDrop:function(e,id){if(this.validDest(id)){this.node.tree.dragDropHandler.call(this,id);var target=this.getElDom(id);var drag=this.getDragEl();YAHOO.util.Dom.removeClass(target,'drag-hint');YAHOO.util.Dom.removeClass(drag,'dragging');}},onDragOut:function(e,id){if(this.validDest(id)){el=this.getElDom(id);el.className=el.classNameBeforeDrag;}},getElDom:function(id){return YAHOO.util.Dom.get(id);}});MDSS.GeoEntityTree=(function(){var _nodeToGeoEntityMap={};var _geoEntityIdToNodeIdMap={};var _geoEntityViewCache={};var _selectedNode=null;var _geoTree=null;var _menu=null;var _modal=null;var _selectCallback=null;var _uploadModal=null;var _browsers={};var _currentBrowser=null;var _currentType=null;var _validator=null;function _uploadImport()
{if(_uploadModal==null)
{var formId='importUploadForm';var action='excelimport';var html=MDSS.Localized.File_Upload_Status+":<br />";html+="<iframe name='importIframe' id='importIframe' style='height:65px; width:350px; margin-bottom: 15px'></iframe>";html+="<form action='"+action+"' enctype='multipart/form-data' target='importIframe' id='"+formId+"' method='post'>";html+="<input type='hidden' name='parentGeoEntityId' id='parentGeoEntityId' value='' />";html+="<input type='hidden' name='excelType' id='excelType' value='dss.vector.solutions.export.GeoEntityExcelView' />";html+="<input type='file' name='importFile' id='importFile' /><br />";html+="<input type='submit' name='import' value='"+MDSS.Localized.Submit+"' />"
html+="</form>";_uploadModal=new YAHOO.widget.Panel("uploadTemplateModal",{width:"400px",height:"400px",fixedcenter:true,close:true,draggable:false,zindex:8,modal:true,visible:true});var outer=document.createElement('div');var header=document.createElement('div');header.innerHTML='<h3>'+MDSS.Localized.Upload_Template+'</h3><hr />';outer.appendChild(header);var contentDiv=document.createElement('div');YAHOO.util.Dom.addClass(contentDiv,'innerContentModal');contentDiv.innerHTML=html;outer.appendChild(contentDiv);_uploadModal.subscribe('hide',function(){_selectedNode.collapse();_selectedNode.dynamicLoadComplete=false;_selectedNode.expanded=false;_selectedNode.expand();});_uploadModal.setBody(outer);_uploadModal.render(document.body);_uploadModal.bringToTop();YAHOO.util.Event.on(formId,'submit',_uploadImportOnSubmit,null,this);}
else
{_uploadModal.show();}}
function _uploadImportOnSubmit(e)
{var input=document.getElementById('parentGeoEntityId');input.value=_getGeoEntityView(_selectedNode).getGeoEntityId();return true;}
function _destroyAll()
{_nodeToGeoEntityMap={};_geoEntityViewCache={};_selectedNode=null;_modal=null;_selectCallback=null;try
{_menu.destroy();}
catch(e)
{_menu=null;}
_geoTree.destroy();_geoTree=null;}
function _setMapping(node,geoEntityView)
{var nodeId=node.contentElId;var geId=geoEntityView.getGeoEntityId();_nodeToGeoEntityMap[nodeId]=geId;_geoEntityViewCache[geId]=geoEntityView;var nodeIds=_geoEntityIdToNodeIdMap[geId];if(Mojo.Util.isArray(nodeIds))
{var match=false;for(var i=0;i<nodeIds.length;i++)
{if(nodeIds[i]===nodeId)
{match=true;break;}}
if(!match)
{nodeIds.push(nodeId);}}
else
{nodeIds=[];nodeIds.push(nodeId);_geoEntityIdToNodeIdMap[geId]=nodeIds;}}
function _getGeoEntityView(node)
{var nodeId=node instanceof YAHOO.widget.HTMLNode?node.contentElId:node;var geId=_nodeToGeoEntityMap[nodeId];return _geoEntityViewCache[geId];}
function _removeMapping(node)
{var nodeId=node instanceof YAHOO.widget.HTMLNode?node.contentElId:node;var geId=_getGeoEntityView(nodeId).getGeoEntityId();delete _nodeToGeoEntityMap[nodeId];var nodeIds=_geoEntityIdToNodeIdMap[geId];for(var i=0;i<nodeIds.length;i++)
{if(nodeIds[i]===nodeId)
{nodeIds.splice(i,1);break;}}
if(nodeIds.length===0)
{delete _geoEntityViewCache[geId];}}
function _setGeoEntityAttributes(params,geoEntity)
{var entityName=params['dto.entityName'];var geoId=params['dto.geoId'];var activatedVal=params['dto.activated'];var activated=(activatedVal==="true")?true:false;var geoData=params['dto.geoData'];var term=document.getElementById('term');geoEntity.setEntityName(entityName);geoEntity.setGeoId(geoId);geoEntity.setActivated(activated);geoEntity.setGeoData(geoData);if(term!=null)
{geoEntity.setTerm(term.value);}}
function _createNode(type,params,action)
{var geConstructor=Mojo.Meta.findClass(type);var geoEntity=new geConstructor();_setGeoEntityAttributes(params,geoEntity);var request=new MDSS.Request({onSuccess:function(ids,geoEntity){if(_selectedNode.dynamicLoadComplete)
{var view=_copyEntityToView(geoEntity);var div=_createNodeDiv(view,true);var parentGeoEntityView=_getGeoEntityView(_selectedNode);var nodeIds=_geoEntityIdToNodeIdMap[parentGeoEntityView.getGeoEntityId()];for(var i=0;i<nodeIds.length;i++)
{var parentEl=document.getElementById(nodeIds[i]);var parent=_geoTree.getNodeByElement(parentEl);if(parent.dynamicLoadComplete)
{var node=new YAHOO.widget.HTMLNode(div,parent);_setMapping(node,view);if(parent.getElId()===_selectedNode.getElId()||parent.expanded===true)
{parent.expanded=false;parent.refresh();}}}}
_selectedNode.expand();_modal.destroy();}});var parentGeoEntityView=_getGeoEntityView(_selectedNode);geoEntity.applyWithParent(request,parentGeoEntityView.getGeoEntityId(),false,null);}
function _copyEntityToView(geoEntity)
{var view=new Mojo.$.dss.vector.solutions.geo.GeoEntityView();view.setGeoEntityId(geoEntity.getId());view.setGeoId(geoEntity.getGeoId());view.setActivated(geoEntity.getActivated());view.setEntityName(geoEntity.getEntityName());view.setEntityType(geoEntity.getType());view.setTypeDisplayLabel(geoEntity.getTypeMd().getDisplayLabel());return view;}
function _performUpdate(params,geoEntity)
{_setGeoEntityAttributes(params,geoEntity);var request=new MDSS.Request({onSuccess:function(ids,geoEntity){var div=_selectedNode.getContentEl().innerHTML;var view=_copyEntityToView(geoEntity);var span=_createContentSpan(view,true);div=div.replace(/(<div class=["']\w*["']>).*?(<\/div>)/,'$1'+span+'$2');var nodeIds=_geoEntityIdToNodeIdMap[geoEntity.getId()];for(var i=0;i<nodeIds.length;i++)
{var id=nodeIds[i];var el=document.getElementById(id);el.innerHTML=div;}
_setMapping(_selectedNode,view);_updateActivatedOnNodes(ids,geoEntity.getActivated());_modal.destroy();}});geoEntity.updateFromTree(request);}
function _updateNode(params,actions)
{var geoEntityView=_getGeoEntityView(_selectedNode);var request=new MDSS.Request({params:params,onSuccess:function(geoEntity)
{_performUpdate(this.params,geoEntity);}});Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request,geoEntityView.getGeoEntityId());}
function _setCurrentBrowser(type)
{if(!_browsers[type])
{var browser=new MDSS.OntologyBrowser(false,true,type);browser.setHandler(_setField);_browsers[type]=browser;}
_currentBrowser=_browsers[type];_currentType=type;}
function _createTypeSelected(e,obj)
{var type=obj.type;_setCurrentBrowser(type);var request=new MDSS.Request({label:obj.label,onSuccess:function(html){var executable=MDSS.util.extractScripts(html);var html=MDSS.util.removeScripts(html);var outer=document.createElement('div');var header=document.createElement('div');header.innerHTML='<h3>'+this.label+'</h3><hr />';outer.appendChild(header);var contentDiv=document.createElement('div');YAHOO.util.Dom.addClass(contentDiv,'innerContentModal');contentDiv.innerHTML=html;outer.appendChild(contentDiv);_modal.setBody(outer);var termDisplay=document.getElementById('termDisplay')
if(termDisplay!=null)
{var disabled=termDisplay.disabled;if(disabled==false)
{YAHOO.util.Event.on('termBtn','click',_openBrowser);}
var search=new MDSS.GenericSearch('termDisplay','term',_displayFunction,_displayFunction,_idFunction,_searchFunction);new MDSS.OntologyValidator('term',search,_getParameters,_setField);}
eval(executable);}});var controller=Mojo.Meta.findClass(type+"Controller");controller.setCreateListener(Mojo.Util.curry(_createNode,type));controller.setCancelListener(_cancelNode);controller.newInstance(request);}
function _setField(selected)
{var el=document.getElementById('term');var dEl=document.getElementById('termDisplay');if(selected.length>0)
{var sel=selected[0];el.value=_idFunction(sel);dEl.value=_displayFunction(sel);}
else
{el.value='';dEl.value='';}}
function _openBrowser(e)
{var termId=document.getElementById('term').value;var selected=[];if(termId!=='')
{selected.push(termId);}
if(_currentBrowser.isRendered())
{_currentBrowser.reset();_currentBrowser.show();_currentBrowser.setSelection(selected);}
else
{_currentBrowser.render();_currentBrowser.setSelection(selected);}}
function _createModal(html,closeWin)
{_modal=new YAHOO.widget.Panel(Mojo.Util.generateId()+'_modal',{width:"400px",height:"400px",fixedcenter:true,close:closeWin||false,draggable:false,zindex:4,modal:true,visible:true});if(closeWin)
{_modal.subscribe('hide',_destroyModal);}
_modal.setBody(html);_modal.render(document.body);}
function _destroyModal()
{_modal.destroy();_modal=null;}
function _changeType(e,type)
{var geoEntityView=_getGeoEntityView(_selectedNode);var request=new MDSS.Request({oldId:geoEntityView.getGeoEntityId(),onSuccess:function(view){var div=_selectedNode.getContentEl().innerHTML;var span=_createContentSpan(view);div=div.replace(/(<div class=["']\w*["']>).*?(<\/div>)/,'$1'+span+'$2');var newId=view.getGeoEntityId();var nodeIds=_geoEntityIdToNodeIdMap[this.oldId];for(var i=0;i<nodeIds.length;i++)
{var id=nodeIds[i];var el=document.getElementById(id);el.innerHTML=div;}
delete _geoEntityViewCache[this.oldId];var nodeIds=_geoEntityIdToNodeIdMap[this.oldId];delete _geoEntityIdToNodeIdMap[this.oldId];_geoEntityIdToNodeIdMap[newId]=nodeIds;for(var i=0;i<nodeIds.length;i++)
{var nodeId=nodeIds[i];_nodeToGeoEntityMap[nodeId]=newId;}
_setMapping(_selectedNode,view);_modal.destroy();}});Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.changeUniversalType(request,geoEntityView.getGeoEntityId(),type);}
function _changeRootGeoEntity()
{var request=new MDSS.Request({onSuccess:function(){}});var geoEntityView=_getGeoEntityView(_selectedNode);Mojo.$.dss.vector.solutions.MDSSUser.changeRootGeoEntity(request,geoEntityView.getGeoEntityId());}
function _changeTypeHandler()
{var request=new MDSS.Request({onSuccess:function(types){var ul=document.createElement('ul');YAHOO.util.Dom.addClass(ul,'selectableList');YAHOO.util.Event.on(ul,'mouseover',function(e,obj){var li=e.target;var ul=e.currentTarget;if(li.nodeName!=='LI')
{return;}
var lis=YAHOO.util.Selector.query('li.currentSelection',ul);for(var i=0;i<lis.length;i++)
{YAHOO.util.Dom.removeClass(lis[i],'currentSelection');}
YAHOO.util.Dom.addClass(e.target,'currentSelection');});for(var i=0;i<types.length;i++)
{var type=types[i];var li=document.createElement('li');li.innerHTML=MDSS.GeoTreeSelectables.types[type].label;YAHOO.util.Event.on(li,'click',_changeType,type,this);ul.appendChild(li);}
var outer=document.createElement('div');var header=document.createElement('div');header.innerHTML='<h3>'+MDSS.Localized.Change_Type+'</h3><hr />';outer.appendChild(header);var listDiv=document.createElement('div');YAHOO.util.Dom.addClass(listDiv,'innerContentModal');listDiv.appendChild(ul);outer.appendChild(listDiv);_createModal(outer,true);}});var geoEntityView=_getGeoEntityView(_selectedNode);Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getCompatibleTypes(request,geoEntityView.getGeoEntityId());}
function _addNodeHandler()
{var ulRaw=document.createElement('ul');ul=new YAHOO.util.Element(ulRaw);ul.addClass('selectableList');ul.on('mouseover',function(e,obj){var li=e.target;var ul=e.currentTarget;if(li.nodeName!=='LI')
{return;}
var lis=YAHOO.util.Selector.query('li.currentSelection',ul);for(var i=0;i<lis.length;i++)
{YAHOO.util.Dom.removeClass(lis[i],'currentSelection');}
YAHOO.util.Dom.addClass(e.target,'currentSelection');});var geoEntityView=_getGeoEntityView(_selectedNode);var type=geoEntityView.getEntityType();var allowedTypes=[];function collectSubtypes(types,parent)
{var allowedChildren=MDSS.GeoTreeSelectables.types[parent].children;for(var i=0;i<allowedChildren.length;i++)
{var childType=allowedChildren[i];types.push(childType);collectSubtypes(types,childType);}}
collectSubtypes(allowedTypes,type);var set=new MDSS.Set();set.addAll(allowedTypes);var ordered=set.values();ordered.sort();for(var i=0;i<ordered.length;i++)
{var allowedType=ordered[i];var liRaw=document.createElement('li');var li=new YAHOO.util.Element(liRaw);var displayLabel=MDSS.GeoTreeSelectables.types[allowedType].label;li.on('click',_createTypeSelected,{type:allowedType,label:displayLabel});liRaw.innerHTML=displayLabel;ul.appendChild(liRaw);}
var outer=document.createElement('div');var header=document.createElement('div');header.innerHTML='<h3>'+MDSS.Localized.Select_Universal_Type+'</h3><hr />';outer.appendChild(header);var listDiv=document.createElement('div');YAHOO.util.Dom.addClass(listDiv,'innerContentModal');listDiv.appendChild(ulRaw);outer.appendChild(listDiv);_createModal(outer,true);}
function _cancelNode(params,a,b)
{if(params['dto.isNew']==='true')
{_modal.destroy();}
else
{var request=new MDSS.Request({onSuccess:function(){_modal.destroy();}});var geoEntityView=_getGeoEntityView(_selectedNode);Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.unlock(request,geoEntityView.getGeoEntityId());}}
function _postDeleteCleanup(deleteAll)
{if(deleteAll)
{var geoEntityView=_getGeoEntityView(_selectedNode);var nodeIds=_geoEntityIdToNodeIdMap[geoEntityView.getGeoEntityId()];for(var i=nodeIds.length-1;i>=0;i--)
{var nodeId=nodeIds[i];var nodeEl=document.getElementById(nodeId);var node=_geoTree.getNodeByElement(nodeEl);_removeMapping(node);var parent=node.parent;_geoTree.removeNode(node);parent.refresh();}}
else
{_removeMapping(_selectedNode);var parent=_selectedNode.parent;_geoTree.removeNode(_selectedNode);parent.refresh();}}
function _deleteAfterConfirmation(e,obj)
{var geoEntity=obj.childEntity;var request=new MDSS.Request({deleteAll:obj.deleteEntity,modal:obj.modal,onSuccess:function()
{this.modal.destroy();_postDeleteCleanup(this.deleteAll);}});if(obj.deleteEntity)
{geoEntity.deleteEntity(request);}
else
{geoEntity.deleteRelationship(request,obj.parentId);}}
function _performDelete(destroyModal,geoEntity)
{var parent=_selectedNode.parent;var parentGeoEntityView=_getGeoEntityView(parent);var parentId=parentGeoEntityView!=null?parentGeoEntityView.getGeoEntityId():null;var request=new MDSS.Request({destroyModal:destroyModal,childEntity:geoEntity,parentId:parentId,onSuccess:function(){if(this.destroyModal)
{_modal.destroy();}
_postDeleteCleanup(true);},onConfirmDeleteEntityException:function(e){var modal=new YAHOO.widget.Panel("confirmDelete",{fixedcenter:true,width:'300px',visible:true,draggable:false,zindex:8000,modal:true});var upperDiv=document.createElement('div');YAHOO.util.Dom.addClass(upperDiv,'modalAlertBox');var message=document.createElement('span');message.innerHTML=e.getLocalizedMessage();upperDiv.appendChild(message);var lowerDiv=document.createElement('div');YAHOO.util.Dom.addClass(lowerDiv,'modalAlertBox');var delEntityObj={deleteEntity:true,childEntity:this.childEntity,parentId:this.parentId,modal:modal}
var delEntity=document.createElement('input');YAHOO.util.Dom.setAttribute(delEntity,'type','button');YAHOO.util.Dom.setAttribute(delEntity,'value',MDSS.Localized.Delete_Entity);YAHOO.util.Event.on(delEntity,'click',_deleteAfterConfirmation,delEntityObj);lowerDiv.appendChild(delEntity);var delRelObj={deleteEntity:false,childEntity:this.childEntity,parentId:this.parentId,modal:modal}
var delRel=document.createElement('input');YAHOO.util.Dom.setAttribute(delRel,'type','button');YAHOO.util.Dom.setAttribute(delRel,'value',MDSS.Localized.Delete_Relationship);YAHOO.util.Event.on(delRel,'click',_deleteAfterConfirmation,delRelObj);lowerDiv.appendChild(delRel);var wrapperDiv=document.createElement('div');wrapperDiv.appendChild(upperDiv);wrapperDiv.appendChild(lowerDiv);modal.bringToTop();modal.setBody(wrapperDiv);modal.render(document.body);}});if(parent==null||parentGeoEntityView==null)
{geoEntity.remove(request);}
else
{geoEntity.confirmDeleteEntity(request,parentGeoEntityView.getGeoEntityId());}}
function _deleteNode()
{var geoEntityView=_getGeoEntityView(_selectedNode);var request=new MDSS.Request({onSuccess:function(geoEntity)
{_performDelete(true,geoEntity);}});Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request,geoEntityView.getGeoEntityId());}
function _editNodeHandler()
{var geoEntityView=_getGeoEntityView(_selectedNode);var typeToEdit=geoEntityView.getEntityType();_setCurrentBrowser(typeToEdit);var request=new MDSS.Request({typeToEdit:typeToEdit,onSuccess:function(html){var executable=MDSS.util.extractScripts(html);var html=MDSS.util.removeScripts(html);var outer=document.createElement('div');var header=document.createElement('div');header.innerHTML='<h3>'+MDSS.GeoTreeSelectables.types[this.typeToEdit].label+'</h3><hr />';outer.appendChild(header);var contentDiv=document.createElement('div');YAHOO.util.Dom.addClass(contentDiv,'innerContentModal');contentDiv.innerHTML=html;outer.appendChild(contentDiv);_createModal(outer,false);var termDisplay=document.getElementById('termDisplay');if(termDisplay!=null)
{var disabled=termDisplay.disabled;if(disabled==false)
{YAHOO.util.Event.on('termBtn','click',_openBrowser);}
var search=new MDSS.GenericSearch('termDisplay','term',_displayFunction,_displayFunction,_idFunction,_searchFunction);new MDSS.OntologyValidator('term',search,_getParameters,_setField);}
eval(executable);}});var controller=Mojo.Meta.findClass(typeToEdit+"Controller");controller.setDeleteListener(_deleteNode);controller.setUpdateListener(_updateNode);controller.setCancelListener(_cancelNode);controller.edit(request,geoEntityView.getGeoEntityId());}
function _displayFunction(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return MDSS.OntologyBrowser.formatLabelFromView(valueObject);}
return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);}
function _idFunction(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return valueObject.getTermId();}
return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);}
function _getParameters()
{return[_currentType,null];}
function _searchFunction(request,value)
{var params=_getParameters();Mojo.$.dss.vector.solutions.ontology.Term.termQueryWithRoots(request,value,params);}
function _deleteNodeHandler()
{var geoEntityView=_getGeoEntityView(_selectedNode);var request=new MDSS.Request({onSuccess:function(geoEntity)
{_performDelete(false,geoEntity);}});Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request,geoEntityView.getGeoEntityId());}
function _customSelectHandler()
{var geoEntityView=_getGeoEntityView(_selectedNode);_selectCallback(geoEntityView,_selectedNode);}
function _nodeMenuSelect()
{var oTarget=this.contextEventTarget;var htmlNode=YAHOO.util.Dom.hasClass(oTarget,"ygtvhtml")?oTarget:YAHOO.util.Dom.getAncestorByClassName(oTarget,"ygtvhtml");if(htmlNode){_selectedNode=_geoTree.getNodeByElement(htmlNode);if(_selectCallback!=null)
{var geoEntityView=_getGeoEntityView(_selectedNode);var geoId=geoEntityView.getGeoId();if(_validator!=null)
{var item=this.itemData[0];item.cfg.setProperty('disabled',true);var request=new MDSS.Request({item:item,onSend:function(){},onComplete:function(){},onFailure:function(){},onProblemExceptionDTO:function(){},onSuccess:function()
{this.item.cfg.setProperty('disabled',false);}});_validator(request,geoId);}}
this.bringToTop();}
else{this.cancel();}}
function _dynamicLoad(parentNode,fnLoadComplete)
{var request=new MDSS.Request({onSuccess:function(query){var childNodes=query.getResultSet();for(var i=0;i<childNodes.length;i++)
{var child=childNodes[i];var div=_createNodeDiv(child);var node=new YAHOO.widget.HTMLNode(div);parentNode.appendChild(node);_setMapping(node,child);}
fnLoadComplete();parentNode.refresh();}});var geoEntityView=_getGeoEntityView(parentNode);Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getOrderedChildren(request,geoEntityView.getGeoEntityId(),'');}
function _addChildToParent(e,obj)
{var clone=obj.clone;var ddThis=this;var request=new MDSS.Request({onSuccess:function(){var ddThis=obj.references.ddThis;var parentId=obj.references.parentId;ddThis.getElDom(parentId).className=ddThis.getElDom(parentId).classNameBeforeDrag;var destNode=YAHOO.util.DDM.getDDById(parentId).node;var childNode=null;if(obj.clone)
{var div=ddThis.node.getContentEl().innerHTML;childNode=new YAHOO.widget.HTMLNode(div);}
else
{thisParent=ddThis.node.parent;ddThis.node.tree.popNode(ddThis.node);if(thisParent.children.length==0){thisParent.expanded=false;}
thisParent.refresh();childNode=ddThis.node;}
if(destNode.dynamicLoadComplete)
{childNode.appendTo(destNode);destNode.refresh();}
destNode.expanded=false;destNode.expand();if(obj.clone)
{var geoEntityView=_getGeoEntityView(ddThis.node);_setMapping(childNode,geoEntityView);}}});var childGeoEntityView=_getGeoEntityView(obj.references.childId);var parentGeoEntityView=_getGeoEntityView(obj.references.parentId);var childId=childGeoEntityView.getGeoEntityId();var parentId=parentGeoEntityView.getGeoEntityId();var oldParentId=_getGeoEntityView(obj.references.ddThis.node.parent).getGeoEntityId();Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.applyWithParent(request,childId,parentId,obj.clone,oldParentId);obj.references.modal.destroy();}
function _dragDropHandler(id)
{var request=new MDSS.Request({references:{childId:this.id,parentId:id,ddThis:this},onConfirmParentChangeException:function(e)
{var modal=new YAHOO.widget.Panel("confirmParentChange",{fixedcenter:true,width:'300px',visible:true,draggable:false,zindex:8000,modal:true});var upperDiv=document.createElement('div');YAHOO.util.Dom.addClass(upperDiv,'modalAlertBox');var message=document.createElement('span');message.innerHTML=e.getLocalizedMessage();upperDiv.appendChild(message);var lowerDiv=document.createElement('div');YAHOO.util.Dom.addClass(lowerDiv,'modalAlertBox');this.references.modal=modal;var yes=document.createElement('input');YAHOO.util.Dom.setAttribute(yes,'type','button');YAHOO.util.Dom.setAttribute(yes,'value',MDSS.Localized.Choice.Yes);YAHOO.util.Event.on(yes,'click',_addChildToParent,{clone:false,references:this.references});lowerDiv.appendChild(yes);var no=document.createElement('input');YAHOO.util.Dom.setAttribute(no,'type','button');YAHOO.util.Dom.setAttribute(no,'value',MDSS.Localized.Choice.No);YAHOO.util.Event.on(no,'click',_addChildToParent,{clone:true,references:this.references});lowerDiv.appendChild(no);var wrapperDiv=document.createElement('div');wrapperDiv.appendChild(upperDiv);wrapperDiv.appendChild(lowerDiv);modal.bringToTop();modal.setBody(wrapperDiv);modal.render(document.body);}});var childGeoEntityView=_getGeoEntityView(this.id);var childEl=document.getElementById(this.id);var childNode=_geoTree.getNodeByElement(childEl);var parentGeoEntityView=_getGeoEntityView(childNode.parent);var childId=childGeoEntityView.getGeoEntityId();var parentId=parentGeoEntityView.getGeoEntityId();Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.confirmChangeParent(request,childId,parentId);}
function _createNodeDiv(geoEntityView,scrapeTerm)
{var activeClass=geoEntityView.getActivated()===true?'activeEntity':'inactiveEntity';var span=_createContentSpan(geoEntityView,scrapeTerm);var div="<div class='"+activeClass+"'>"+span+"</div>";return div;}
function _createContentSpan(geoEntityView,scrapeTerm)
{if(scrapeTerm)
{var display=document.getElementById('termDisplay');if(display&&Mojo.Util.trim(display.value).length>0)
{var termName=MDSS.OntologyBrowser.extractName(display.value);geoEntityView.setMoSubType(termName);}}
var display=MDSS.AbstractSelectSearch.getDisplayWithSubtype(geoEntityView);return"<span title='"+geoEntityView.getGeoId()+"'>"+display+"</span>";}
function _updateActivatedOnNodes(ids,activated)
{for(var i=0;i<ids.length;i++)
{var id=ids[i];var nodeIds=_geoEntityIdToNodeIdMap[id];for(var j=0;j<nodeIds.length;j++)
{var el=document.getElementById(nodeIds[j]);var nodeDiv=new YAHOO.util.Element(el.firstChild);if(activated===true)
{nodeDiv.removeClass('inactiveEntity');nodeDiv.addClass('activeEntity');}
else
{nodeDiv.removeClass('activeEntity');nodeDiv.addClass('inactiveEntity');}}}}
function _renderTree(treeId,geoEntity,selectCallback)
{var view=_copyEntityToView(geoEntity);var div=_createNodeDiv(view);var node={type:"HTML",html:div};_geoTree=new YAHOO.widget.TreeViewDD(treeId,[node],_dragDropHandler);_geoTree.setDynamicLoad(_dynamicLoad);_geoTree.render();var itemData=[];if(Mojo.Util.isFunction(selectCallback))
{_selectCallback=selectCallback;var selectMenuItem=new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Select);selectMenuItem.subscribe("click",_customSelectHandler);itemData.push(selectMenuItem);}
var importMenuItem=new YAHOO.widget.ContextMenuItem(MDSS.Localized.Import_Button);importMenuItem.subscribe("click",_uploadImport);itemData.push(importMenuItem);var createMenuItem=new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Create);createMenuItem.subscribe("click",_addNodeHandler);itemData.push(createMenuItem);var editMenuItem=new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Edit);editMenuItem.subscribe("click",_editNodeHandler);itemData.push(editMenuItem);var deleteMenuItem=new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Delete);deleteMenuItem.subscribe("click",_deleteNodeHandler);itemData.push(deleteMenuItem);_menu=new YAHOO.widget.ContextMenu("treeMenu",{trigger:treeId,lazyload:true,itemdata:itemData,zindex:500});_menu.subscribe("triggerContextMenu",_nodeMenuSelect);_setMapping(_geoTree.getRoot().children[0],view);}
function _initializeTree(treeId,selectCallback){var request=new MDSS.Request({onSuccess:function(geoEntity){_renderTree(treeId,geoEntity,selectCallback);}});Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request,MDSS.GeoEntityTreeRootId);}
function _setValidator(validator){_validator=validator;}
return{initializeTree:_initializeTree,setValidator:_setValidator};})();MDSS.GeoHierarchyTree=(function(){var _nodeToGeoHierarchyMap={};var _geoHierarchyCache={};var _geoHierarchyIdToNodeIdMap={};var _selectedNode=null;var _hierarchyTree=null;var _menu=null;var _modal=null;var _sharedBrowser=null
function _destroyAll()
{_nodeToGeoHierarchyMap={};_geoHierarchyCache={};_selectedNode=null;_modal=null;try
{_menu.destroy();}
catch(e)
{_menu=null;}
_hierarchyTree.destroy();_hierarchyTree=null;}
function _setMapping(node,geoEntity)
{var nodeId=node.contentElId;var geId=geoEntity.getGeoHierarchyId();_nodeToGeoHierarchyMap[nodeId]=geId;_geoHierarchyCache[geId]=geoEntity;var nodeIds=_geoHierarchyIdToNodeIdMap[geId];if(Mojo.Util.isArray(nodeIds))
{var match=false;for(var i=0;i<nodeIds.length;i++)
{if(nodeIds[i]===nodeId)
{match=true;break;}}
if(!match)
{nodeIds.push(nodeId);}}
else
{nodeIds=[];nodeIds.push(nodeId);_geoHierarchyIdToNodeIdMap[geId]=nodeIds;}}
function _getGeoHierarchyView(node)
{var nodeId=node instanceof YAHOO.widget.HTMLNode?node.contentElId:node;var geId=_nodeToGeoHierarchyMap[nodeId];return _geoHierarchyCache[geId];}
function _removeMapping(node)
{var nodeId=node instanceof YAHOO.widget.HTMLNode?node.contentElId:node;var geId=_getGeoHierarchyView(nodeId).getGeoHierarchyId();delete _nodeToGeoHierarchyMap[nodeId];var nodeIds=_geoHierarchyIdToNodeIdMap[geId];for(var i=0;i<nodeIds.length;i++)
{if(nodeIds[i]===nodeId)
{nodeIds.splice(i,1);break;}}
if(nodeIds.length===0)
{delete _geoHierarchyCache[geId];}}
function _createNode(params,action)
{var request=new MDSS.Request({onSuccess:function(geoHierarchyId){var request=new MDSS.Request({onSuccess:function(geoHierarchyView){if(_selectedNode.dynamicLoadComplete)
{var parentGeoHierarchy=_getGeoHierarchyView(_selectedNode);var nodeIds=_geoHierarchyIdToNodeIdMap[parentGeoHierarchy.getGeoHierarchyId()];for(var i=0;i<nodeIds.length;i++)
{var parentEl=document.getElementById(nodeIds[i]);var parent=_hierarchyTree.getNodeByElement(parentEl);if(parent.dynamicLoadComplete)
{var node=new YAHOO.widget.HTMLNode(geoHierarchyView.getDisplayLabel(),parent);_setMapping(node,geoHierarchyView);if(parent.getElId()===_selectedNode.getElId()||parent.expanded===true)
{parent.expanded=false;parent.refresh();}}}}
_selectedNode.expand();_modal.destroy();}});Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getViewForGeoHierarchy(request,geoHierarchyId);}});return request;}
function _updateNode(params,actions)
{var request=new MDSS.Request({onSuccess:function(geoHierarchyId){var request=new MDSS.Request({onSuccess:function(geoHierarchy)
{var nodeIds=_geoHierarchyIdToNodeIdMap[geoHierarchy.getGeoHierarchyId()];for(var i=0;i<nodeIds.length;i++)
{var id=nodeIds[i];var el=document.getElementById(id);el.innerHTML=geoHierarchy.getDisplayLabel();}
_setMapping(_selectedNode,geoHierarchy);_modal.destroy();}});Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getViewForGeoHierarchy(request,geoHierarchyId);}});return request;}
function _createModal(html,closeWin)
{_modal=new YAHOO.widget.Panel("select",{width:"400px",height:"500px",fixedcenter:true,close:arguments.length>1?closeWin:true,draggable:false,zindex:4,modal:true,visible:true});_modal.setBody(html);_modal.render(document.body);YAHOO.util.Event.on('termBtn','click',_openBrowser);var search=new MDSS.GenericSearch('termDisplay','term',_displayFunction,_displayFunction,_idFunction,_searchFunction);new MDSS.OntologyValidator('term',search,_getParameters,_setField);}
function _displayFunction(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return MDSS.OntologyBrowser.formatLabelFromView(valueObject);}
return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);}
function _idFunction(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return valueObject.getTermId();}
return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);}
function _getParameters()
{return['dss.vector.solutions.geo.GeoEntityDefinition','term'];}
function _searchFunction(request,value)
{var params=_getParameters();Mojo.$.dss.vector.solutions.ontology.Term.termQueryWithRoots(request,value,params);}
function _addNodeHandler()
{var geoHierarchyView=_getGeoHierarchyView(_selectedNode);var request=new MDSS.Request({parentLabel:geoHierarchyView.getDisplayLabel(),onSuccess:function(html){var executable=MDSS.util.extractScripts(html);var html=MDSS.util.removeScripts(html);var header=MDSS.Localized.New_Universal_Located_In;header=header.replace(/\[parent\]/,this.parentLabel);var labelEl="<h3>"+header+"</h3><hr />";html=labelEl+html;_createModal(html);eval(executable);}});var controller=Mojo.$.dss.vector.solutions.geo.GeoEntityTypeController;controller.setCreateDefinitionListener(_createNode);controller.setCancelCreateDefinitionListener(function(){_modal.destroy();});controller.newDefinition(request,geoHierarchyView.getGeoHierarchyId());}
function _cancelNode()
{var request=new MDSS.Request({onSuccess:function(){_modal.destroy();}});return request;}
function _postDeleteCleanup(deleteAll,ids)
{if(deleteAll)
{function deleteNodeFromTree(geoHierarchyId)
{var nodeIds=_geoHierarchyIdToNodeIdMap[geoHierarchyId];if(!nodeIds)return;for(var i=nodeIds.length-1;i>=0;i--)
{var nodeId=nodeIds[i];var nodeEl=document.getElementById(nodeId);var node=_hierarchyTree.getNodeByElement(nodeEl);_removeMapping(node);var parent=node.parent;_hierarchyTree.removeNode(node);parent.refresh();}}
for(var i=0;i<ids.length;i++)
{deleteNodeFromTree(ids[i]);}}
else
{_removeMapping(_selectedNode);var parent=_selectedNode.parent;_hierarchyTree.removeNode(_selectedNode);parent.refresh();}}
function _deleteAfterConfirmation(e,obj)
{var geoHierarchyView=obj.childHierarchy;var request=new MDSS.Request({deleteAll:obj.deleteHierarchy,modal:obj.modal,onSuccess:function(ids)
{this.modal.destroy();_postDeleteCleanup(this.deleteAll,ids);}});var geoHierarchyId=geoHierarchyView.getGeoHierarchyId();if(obj.deleteHierarchy)
{Mojo.$.dss.vector.solutions.geo.GeoHierarchy.deleteGeoHierarchy(request,geoHierarchyId);}
else
{Mojo.$.dss.vector.solutions.geo.GeoHierarchy.deleteRelationship(request,geoHierarchyId,obj.parentId);}}
function _performDelete(destroyModal,geoHierarchyView)
{var parent=_selectedNode.parent;var parentGeoHierarchyView=_getGeoHierarchyView(parent);var parentId=parentGeoHierarchyView!=null?parentGeoHierarchyView.getGeoHierarchyId():null;var request=new MDSS.Request({destroyModal:destroyModal,childHierarchy:geoHierarchyView,parentId:parentId,onSuccess:function(ids){if(this.destroyModal)
{_modal.destroy();}
_postDeleteCleanup(true,ids);},onConfirmDeleteHierarchyException:function(e){var modal=new YAHOO.widget.Panel("confirmDelete",{fixedcenter:true,width:'300px',visible:true,draggable:false,zindex:8000,modal:true});var upperDiv=document.createElement('div');YAHOO.util.Dom.addClass(upperDiv,'modalAlertBox');var message=document.createElement('span');message.innerHTML=e.getLocalizedMessage();upperDiv.appendChild(message);var lowerDiv=document.createElement('div');YAHOO.util.Dom.addClass(lowerDiv,'modalAlertBox');var delEntityObj={deleteHierarchy:true,childHierarchy:this.childHierarchy,parentId:this.parentId,modal:modal}
var delEntity=document.createElement('input');YAHOO.util.Dom.setAttribute(delEntity,'type','button');YAHOO.util.Dom.setAttribute(delEntity,'value',MDSS.Localized.Delete_Universal);YAHOO.util.Event.on(delEntity,'click',_deleteAfterConfirmation,delEntityObj);lowerDiv.appendChild(delEntity);var delRelObj={deleteHierarchy:false,childHierarchy:this.childHierarchy,parentId:this.parentId,modal:modal}
var delRel=document.createElement('input');YAHOO.util.Dom.setAttribute(delRel,'type','button');YAHOO.util.Dom.setAttribute(delRel,'value',MDSS.Localized.Delete_Relationship);YAHOO.util.Event.on(delRel,'click',_deleteAfterConfirmation,delRelObj);lowerDiv.appendChild(delRel);var wrapperDiv=document.createElement('div');wrapperDiv.appendChild(upperDiv);wrapperDiv.appendChild(lowerDiv);modal.bringToTop();modal.setBody(wrapperDiv);modal.render(document.body);}});var geoHierarchyId=geoHierarchyView.getGeoHierarchyId();if(parent==null||parentGeoHierarchyView==null)
{Mojo.$.dss.vector.solutions.geo.GeoHierarchy.deleteGeoHierarchy(request,geoHierarchyId);}
else
{var parentGeoHierarchyId=parentGeoHierarchyView.getGeoHierarchyId();Mojo.$.dss.vector.solutions.geo.GeoHierarchy.confirmDeleteHierarchy(request,geoHierarchyId,parentGeoHierarchyId);}}
function _deleteNode()
{var geoHierarchyView=_getGeoHierarchyView(_selectedNode);_performDelete(true,geoHierarchyView);}
function _editNodeHandler()
{var geoHierarchyView=_getGeoHierarchyView(_selectedNode);var request=new MDSS.Request({displayLabel:geoHierarchyView.getDisplayLabel(),onSuccess:function(html){var executable=MDSS.util.extractScripts(html);var html=MDSS.util.removeScripts(html);var labelEl="<h3>"+this.displayLabel+"</h3><hr />";html=labelEl+html;_createModal(html,false);eval(executable);}});var controller=Mojo.$.dss.vector.solutions.geo.GeoEntityTypeController;controller.setUpdateDefinitionListener(_updateNode);controller.setCancelUpdateDefinitionListener(_cancelNode);controller.editDefinition(request,geoHierarchyView.getGeoHierarchyId());}
function _deleteNodeHandler()
{var geoHierarchyView=_getGeoHierarchyView(_selectedNode);_performDelete(false,geoHierarchyView);}
function _nodeMenuSelect()
{var oTarget=this.contextEventTarget;var htmlNode=YAHOO.util.Dom.hasClass(oTarget,"ygtvhtml")?oTarget:YAHOO.util.Dom.getAncestorByClassName(oTarget,"ygtvhtml");if(htmlNode){_selectedNode=_hierarchyTree.getNodeByElement(htmlNode);}
else{this.cancel();}}
function _dynamicLoad(parentNode,fnLoadComplete)
{var request=new MDSS.Request({onSuccess:function(query){var childNodes=query.getResultSet();for(var i=0;i<childNodes.length;i++)
{var child=childNodes[i];var node=new YAHOO.widget.HTMLNode(child.getDisplayLabel());parentNode.appendChild(node);_setMapping(node,child);}
fnLoadComplete();parentNode.refresh();}});var geoHierarchyView=_getGeoHierarchyView(parentNode);Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getOrderedChildren(request,geoHierarchyView.getGeoHierarchyId());}
function _addChildToParent(e,obj)
{var clone=obj.clone;var ddThis=this;var request=new MDSS.Request({onSuccess:function(){var ddThis=obj.references.ddThis;var parentId=obj.references.parentId;ddThis.getElDom(parentId).className=ddThis.getElDom(parentId).classNameBeforeDrag;var destNode=YAHOO.util.DDM.getDDById(parentId).node;var childNode=null;if(obj.clone)
{var div=ddThis.node.getContentEl().innerHTML;childNode=new YAHOO.widget.HTMLNode(div);}
else
{thisParent=ddThis.node.parent;ddThis.node.tree.popNode(ddThis.node);if(thisParent.children.length==0){thisParent.expanded=false;}
thisParent.refresh();childNode=ddThis.node;}
if(destNode.dynamicLoadComplete)
{childNode.appendTo(destNode);destNode.refresh();}
destNode.expanded=false;destNode.expand();if(obj.clone)
{var geoHierarchyView=_getGeoHierarchyView(ddThis.node);_setMapping(childNode,geoHierarchyView);}}});var childGeoHierarchyView=_getGeoHierarchyView(obj.references.childId);var parentGeoHierarchyView=_getGeoHierarchyView(obj.references.parentId);var childId=childGeoHierarchyView.getGeoHierarchyId();var parentId=parentGeoHierarchyView.getGeoHierarchyId();Mojo.$.dss.vector.solutions.geo.GeoHierarchy.applyExistingWithParent(request,childId,parentId,obj.clone);obj.references.modal.destroy();}
function _dragDropHandler(id)
{var request=new MDSS.Request({references:{childId:this.id,parentId:id,ddThis:this},onConfirmHierarchyParentChangeException:function(e)
{var modal=new YAHOO.widget.Panel("confirmParentChange",{fixedcenter:true,width:'300px',visible:true,draggable:false,zindex:8000,modal:true});var upperDiv=document.createElement('div');YAHOO.util.Dom.addClass(upperDiv,'modalAlertBox');var message=document.createElement('span');message.innerHTML=e.getLocalizedMessage();upperDiv.appendChild(message);var lowerDiv=document.createElement('div');YAHOO.util.Dom.addClass(lowerDiv,'modalAlertBox');this.references.modal=modal;var yes=document.createElement('input');YAHOO.util.Dom.setAttribute(yes,'type','button');YAHOO.util.Dom.setAttribute(yes,'value',MDSS.Localized.Choice.Yes);YAHOO.util.Event.on(yes,'click',_addChildToParent,{clone:false,references:this.references});lowerDiv.appendChild(yes);var no=document.createElement('input');YAHOO.util.Dom.setAttribute(no,'type','button');YAHOO.util.Dom.setAttribute(no,'value',MDSS.Localized.Choice.No);YAHOO.util.Event.on(no,'click',_addChildToParent,{clone:true,references:this.references});lowerDiv.appendChild(no);var wrapperDiv=document.createElement('div');wrapperDiv.appendChild(upperDiv);wrapperDiv.appendChild(lowerDiv);modal.bringToTop();modal.setBody(wrapperDiv);modal.render(document.body);}});var childGeoHierarchyView=_getGeoHierarchyView(this.id);var childEl=document.getElementById(this.id);var childNode=_hierarchyTree.getNodeByElement(childEl);var parentGeoHierarchyView=_getGeoHierarchyView(childNode.parent);var childId=childGeoHierarchyView.getGeoHierarchyId();var parentId=parentGeoHierarchyView.getGeoHierarchyId();Mojo.$.dss.vector.solutions.geo.GeoHierarchy.confirmChangeParent(request,childId,parentId);}
function _renderTree(treeId,geoHierarchyView)
{var node={type:"HTML",html:geoHierarchyView.getDisplayLabel()};_hierarchyTree=new YAHOO.widget.TreeViewDD(treeId,[node],_dragDropHandler);_hierarchyTree.setDynamicLoad(_dynamicLoad);_hierarchyTree.render();var itemData=[];var createMenuItem=new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Create);createMenuItem.subscribe("click",_addNodeHandler);itemData.push(createMenuItem);var editMenuItem=new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Edit);editMenuItem.subscribe("click",_editNodeHandler);itemData.push(editMenuItem);var deleteMenuItem=new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Delete);deleteMenuItem.subscribe("click",_deleteNodeHandler);itemData.push(deleteMenuItem);_menu=new YAHOO.widget.ContextMenu("treeMenu",{trigger:treeId,lazyload:true,itemdata:itemData});_menu.subscribe("triggerContextMenu",_nodeMenuSelect);_setMapping(_hierarchyTree.getRoot().children[0],geoHierarchyView);}
function _openBrowser(e)
{var termId=document.getElementById('term').value;var selected=[];if(termId!=='')
{selected.push(termId);}
if(_sharedBrowser.isRendered())
{_sharedBrowser.reset();_sharedBrowser.show();_sharedBrowser.setSelection(selected);}
else
{_sharedBrowser.render();_sharedBrowser.setSelection(selected);}}
function _setField(selected)
{var el=document.getElementById('term');var dEl=document.getElementById('termDisplay');if(selected.length>0)
{var sel=selected[0];el.value=_idFunction(sel);dEl.value=_displayFunction(sel);}
else
{el.value='';dEl.value='';}}
function _initializeTree(treeId){var request=new MDSS.Request({onSuccess:function(geoHierarchyView){_renderTree(treeId,geoHierarchyView);}});_sharedBrowser=new MDSS.OntologyBrowser(false,'dss.vector.solutions.geo.GeoEntityDefinition','term');_sharedBrowser.setHandler(_setField);Mojo.$.dss.vector.solutions.geo.GeoHierarchy.getViewForGeoHierarchy(request,MDSS.GeoHierarchyTreeRootId);}
return{initializeTree:_initializeTree,getGeoEntity:_getGeoHierarchyView};})();Mojo.Meta.newClass('MDSS.AbstractSelectSearch',{IsAbstract:true,Instance:{initialize:function(enforceRoot)
{this._enforceRoot=enforceRoot;this._selectHandler=null;this._treeSelectHandler=null;this._hideHandler=null;this._SELECT_CONTAINER_ID="selectSearchComponent";this._geoEntityViewCache={};this._searchModal=null;this._geoTreePanel=null;this._filterType='';this._selectLists=[];this._typeAndSelectMap={};this._autocompletes=[];this._currentSearchType=null;this._political=true;this._populated=false;this._sprayTargetAllowed=false;this._extraUniversals=[],this._rendered=false;},enforcesRoot:function()
{return this._enforceRoot;},_copyEntityToView:function(geoEntity)
{var view=new Mojo.$.dss.vector.solutions.geo.GeoEntityView();view.setGeoEntityId(geoEntity.getId());view.setGeoId(geoEntity.getGeoId());view.setActivated(geoEntity.getActivated());view.setEntityName(geoEntity.getEntityName());view.setEntityType(geoEntity.getType());view.setTypeDisplayLabel(geoEntity.getTypeMd().getDisplayLabel());return view;},isInitialized:function()
{return this._searchModal!=null;},show:function()
{this._searchModal.show();},hide:function()
{this._searchModal.hide();},setSelectHandler:function(handler)
{this._selectHandler=handler;},getSelectHandler:function()
{return this._selectHandler;},setTreeSelectHandler:function(handler)
{this._treeSelectHandler=handler;},setHideHandler:function(handler)
{this._hideHandler=handler;},_createRoot:function()
{var request=new MDSS.Request({searchRef:this,onSuccess:function(results){this.searchRef._clearAndAddAll(0,results);}});this._doCreateRoot(request);},_doCreateRoot:{IsAbstract:true},eventHandler:function(e){if(e.getType()==MDSS.Event.BEFORE_SEARCH){var autocomplete=e.getValue().autocomplete;var searchedId=autocomplete.getDisplayElement().id;for(var i=0;i<this._autocompletes.length;i++){var element=this._autocompletes[i];var elementId=element.getDisplayElement().id;if(searchedId!=elementId){element.hide();}}}},_closeAllResultPanels:function(){for(var i=0;i<this._autocompletes.length;i++){var element=this._autocompletes[i];element.hide();}},render:function()
{var request=new MDSS.Request({searchRef:this,onSuccess:function(html){this.searchRef._searchModal=new YAHOO.widget.Panel("searchSelectModal",{width:"100%",height:"100%",fixedcenter:true,close:true,draggable:false,zindex:4,modal:true,visible:true});this.searchRef._searchModal.subscribe('beforeHide',function(){this._closeAllResultPanels();if(this._geoTreePanel!=null)
{this._geoTreePanel.hide();}
this._notifyHideHandler();},null,this.searchRef);this.searchRef._searchModal.setBody(html);this.searchRef._searchModal.render(document.body);this.searchRef._doFilter();var treeOpener=new YAHOO.util.Element("treeOpener");treeOpener.on('click',this.searchRef._openTree,null,this.searchRef);var selects=YAHOO.util.Selector.query('select.typeSelect',this.searchRef._SELECT_CONTAINER_ID);for(var i=0;i<selects.length;i++)
{var select=selects[i];this.searchRef._typeAndSelectMap[select.name]=i;this.searchRef._selectLists.push(select);YAHOO.util.Event.on(select,this._getChildren,null,this.searchRef);}
var ajaxSearches=YAHOO.util.Selector.query('input.ajaxSearch',this._SELECT_CONTAINER_ID);for(var i=0;i<ajaxSearches.length;i++)
{var search=ajaxSearches[i];var type=search.id.replace(/_search/,'');var sFunction=Mojo.Util.bind(this.searchRef,function(typeRef,request,value){Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByEntityNameOrGeoId(request,typeRef,value,this.enforcesRoot());},type);var sHandler=Mojo.Util.bind(this,function(typeRef,option){this.searchRef._resetWithSelection(typeRef,option.id);},type);var lF=this.searchRef._modalListFunction;var dF=this.searchRef._modalDisplayFunction;var iF=this.searchRef._modalIdFunction;var listener=Mojo.Util.bind(this.searchRef,this.searchRef.eventHandler);var autocomplete=new MDSS.GenericSearch(search,null,lF,dF,iF,sFunction,sHandler);autocomplete.addListener(listener);this.searchRef._autocompletes.push(autocomplete);}
this.searchRef._postRender();this.searchRef._rendered=true;this.searchRef._createRoot();}});var method=this._getControllerAction();method(request,MDSS.SelectSearchRootId,this.getPolitical(),this.getSprayTargetAllowed(),this.getExtraUniversals());},_postRender:{IsAbstract:true},_setEntityOption:function(geoEntityView,overrideIndex)
{var select;if(overrideIndex!=null)
{select=this._selectLists[overrideIndex];}
else
{select=document.getElementById(geoEntityView.getEntityType());}
if(select&&!this._geoEntityViewCache[geoEntityView.getGeoEntityId()])
{if(this._disableAllowed())
{select.disabled=false;}
var optionRaw=document.createElement('option');optionRaw.value=geoEntityView.getGeoEntityId();optionRaw.id=geoEntityView.getGeoEntityId();optionRaw.innerHTML=geoEntityView.getEntityName();select.appendChild(optionRaw);var option=new YAHOO.util.Element(optionRaw);option.on('click',this._getChildren,null,this);this._geoEntityViewCache[geoEntityView.getGeoEntityId()]=geoEntityView;}},_notifyHideHandler:{IsAbstract:true},_notifySelectHandler:{IsAbstract:true},_notifyTreeSelectHandler:{IsAbstract:true},_getControllerAction:{IsAbstract:true},_updateSelection:{IsAbstract:true},_openTree:function()
{var containerId="treeViewContainer";if(this._geoTreePanel==null)
{this._geoTreePanel=new YAHOO.widget.Panel(containerId,{width:'400px',height:'400px',zindex:9});this._geoTreePanel.render();this._geoTreePanel.bringToTop();var wrappedHandler=(function(searchRef)
{return function(geoEntity){searchRef._notifyTreeSelectHandler(geoEntity);searchRef._geoTreePanel.hide();}})(this);YAHOO.util.Dom.setStyle(containerId,'overflow','scroll');MDSS.GeoEntityTree.initializeTree("treeView",wrappedHandler);}
else
{this._geoTreePanel.show();this._geoTreePanel.bringToTop();}},setFilter:function(filter)
{this._filterType=filter;if(this.isInitialized())
{this._doFilter();}},getFilter:function()
{return this._filterType;},setPopulated:function(populated)
{this._populated=populated;},getPopulated:function()
{return this._populated;},setPolitical:function(political)
{this._political=political;},getPolitical:function()
{return this._political;},setSprayTargetAllowed:function(sprayTargetAllowed)
{this._sprayTargetAllowed=sprayTargetAllowed;},getSprayTargetAllowed:function()
{return this._sprayTargetAllowed;},addExtraUniversal:function(universal)
{this._extraUniversals.push(universal);},getExtraUniversals:function()
{return this._extraUniversals;},_doFilter:function()
{if(this._filterType==null||this._filterType==='')
{var selects=YAHOO.util.Selector.query('select',this._SELECT_CONTAINER_ID);for(var i=0;i<selects.length;i++)
{var type=selects[i].id;var dt=new YAHOO.util.Element(type+"_dt");var dd=new YAHOO.util.Element(type+"_dd");dt.setStyle('display','block');dd.setStyle('display','block');}
return;}
function collectParents(tree,allowedArr,typeEntry)
{var parentType=typeEntry.parent;if(Mojo.Util.isString(parentType))
{var parentEntry=tree.types[parentType];allowedArr.push(parentType);collectParents(tree,allowedArr,parentEntry);}}
function collectChildren(tree,allowedArr,typeEntry)
{var children=typeEntry.children;for(var i=0;i<children.length;i++)
{var childEntry=tree.types[children[i]];allowedArr.push(children[i]);collectChildren(tree,allowedArr,childEntry);}}
var allowed=[];var tree=MDSS.GeoTreeSelectables;var typeEntry=tree.types[this._filterType];allowed.push(this._filterType);collectParents(tree,allowed,typeEntry);collectChildren(tree,allowed,typeEntry);var selects=YAHOO.util.Selector.query('select',this._SELECT_CONTAINER_ID);for(var i=0;i<selects.length;i++)
{var type=selects[i].id;var dt=new YAHOO.util.Element(type+"_dt");var dd=new YAHOO.util.Element(type+"_dd");if(allowed.indexOf(type)===-1)
{dt.setStyle('display','none');dd.setStyle('display','none');}
else
{dt.setStyle('display','block');dd.setStyle('display','block');}}},_modalListFunction:function(valueObject){var GeoEntity=Mojo.$.dss.vector.solutions.geo.generated.GeoEntity;var geoIdAttr=GeoEntity.GEOID;var entityNameAttr=GeoEntity.ENTITYNAME;var entityName=valueObject.getValue(entityNameAttr);var displayLabel=valueObject.getValue('displayLabel');var geoId=valueObject.getValue(geoIdAttr);var moSubType=valueObject.getValue('moSubType');return MDSS.AbstractSelectSearch.formatDisplay2(entityName,displayLabel,geoId,moSubType);},_modalDisplayFunction:function(valueObject){var GeoEntity=Mojo.$.dss.vector.solutions.geo.generated.GeoEntity;var geoIdAttr=GeoEntity.GEOID;var geoId=valueObject.getValue(geoIdAttr);return geoId;},_modalIdFunction:function(valueObject){var GeoEntity=Mojo.$.dss.vector.solutions.geo.generated.GeoEntity;var idAttr=GeoEntity.ID;var id=valueObject.getValue(idAttr);return id;},_clearAndAddAllFindIndex:function(results,geoId)
{var selectIndex=0;for(var i=0;i<results.length;i++)
{var childView=results[i];if(childView.getGeoId()==geoId)
{var type=childView.getEntityType();selectIndex=this._typeAndSelectMap[type];}}
this._clearAndAddAll(selectIndex,results,geoId);},_clearAndAddAll:function(selectIndex,results,entityOrGeoId)
{this._clearSelectLists(0);for(var i=0;i<results.length;i++)
{var childView=results[i];var overrideIndex=childView.getGeoEntityId()==entityOrGeoId||childView.getGeoId()==entityOrGeoId?selectIndex:null;this._setEntityOption(childView,overrideIndex);}
for(var i=selectIndex;i>=0;i--)
{var select=this._selectLists[i];if(select.options.length>this._getStartIndex())
{select.selectedIndex=this._getStartIndex();}}
var select=this._selectLists[selectIndex];var firstInd=selectIndex==0?0:this._getStartIndex();var firstEntry=select.options[firstInd];var geoEntityView=this._geoEntityViewCache[firstEntry.id];this._notifySelectHandler(geoEntityView,true);},_resetWithSelection:function(type,geoEntityId)
{var request=new MDSS.Request({searchRef:this,type:type,onSuccess:function(results)
{var selectIndex=this.searchRef._typeAndSelectMap[this.type];this.searchRef._clearAndAddAll(selectIndex,results,geoEntityId);}});Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedIn(request,geoEntityId,true,this._filterType);},populateSelections:function(geoId)
{var request=new MDSS.Request({that:this,geoId:geoId,onSuccess:function(results)
{this.that._clearAndAddAllFindIndex(results,geoId);}});Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedInByGeoId(request,geoId,true,this._filterType);},_getChildren:function(e)
{var currentOption=e.target;var select=currentOption.parentNode;var parentEntityView=this._geoEntityViewCache[currentOption.id];var request=new MDSS.Request({searchRef:this,currentType:parentEntityView.getEntityType(),parentEntityView:parentEntityView,onSuccess:function(query){this.searchRef._clearSelectLists.call(this.searchRef,this.currentType);var geoEntities=query.getResultSet();for(var i=0;i<geoEntities.length;i++)
{var childView=geoEntities[i];this.searchRef._setEntityOption(childView,null);}
this.searchRef._notifySelectHandler(this.parentEntityView,true);}});Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getOrderedChildren(request,parentEntityView.getGeoEntityId(),this._filterType);},_clearSelectLists:function(typeIndex,inclusive)
{var index=Mojo.Util.isString(typeIndex)?this._typeAndSelectMap[typeIndex]:typeIndex;if(index==this._selectLists.length-1)
{return;}
var startIndex=inclusive?index:index+1;for(var i=startIndex;i<this._selectLists.length;i++)
{var select=this._selectLists[i];this._clearOptionsOnSelect(i,select);}},_clearOptionsOnSelect:function(selectIndex,select)
{var options=select.options;var oLength=options.length;var startInd=selectIndex==0?0:this._getStartIndex()-1;for(var i=oLength-1;i>startInd;i--)
{var option=options[i];this._removeOptionNode(option);}},_getStartIndex:{IsAbstract:true},_removeOptionNode:function(option)
{var optionEl=Mojo.Util.isString(option)?document.getElementById(option):option;var select=optionEl.parentNode;select.removeChild(optionEl);delete this._geoEntityViewCache[optionEl.id];if(select.options.length==this._getStartIndex())
{select.selectedIndex=0;if(this._disableAllowed())
{select.disabled=true;}}},_disableAllowed:{IsAbstract:true},},Static:{formatDisplay:function(geoEntityView)
{return MDSS.AbstractSelectSearch.formatDisplay2(geoEntityView.getEntityName(),geoEntityView.getTypeDisplayLabel(),geoEntityView.getGeoId(),null);},getDisplayWithSubtype:function(geoEntityView)
{return MDSS.AbstractSelectSearch.formatDisplay2(geoEntityView.getEntityName(),geoEntityView.getTypeDisplayLabel(),geoEntityView.getGeoId(),geoEntityView.getMoSubType());},formatDisplay2:function(entityName,typeDisplayLabel,geoId,moSubType)
{var mo=moSubType!=null&&moSubType.length>0?" : "+moSubType:'';return entityName+' ('+typeDisplayLabel+mo+') - '+geoId;}}});Mojo.Meta.newClass('MDSS.SingleSelectSearch',{Extends:MDSS.AbstractSelectSearch,Instance:{initialize:function(enforceRoot)
{this.$initialize(enforceRoot);this._currentSelection=null;this._CURRENT_SELECTION='currentSelection';this._listeners=[];this._geoId=null;this.setSelectHandler(Mojo.Util.bind(this,this.selectHandler));this.setTreeSelectHandler(Mojo.Util.bind(this,this.selectHandler));},setGeoId:function(geoId)
{this._geoId=geoId;},_postRender:function()
{},_notifyHideHandler:function()
{},selectHandler:function(selected,ignoreSetting)
{var valid=true;if(selected!=null)
{if(typeof selected=='string'){var request=new MDSS.Request({that:this,onSend:function(){},onComplete:function(){},onFailure:function(){},onSuccess:function(result){this.that.selectHandler(result);}});Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getView(request,selected);return;}
var currentFilter=this.getFilter();if(currentFilter)
{var expectedKlass=Mojo.Meta.findClass(currentFilter);var givenKlass=Mojo.Meta.findClass(selected.getEntityType());valid=givenKlass.getMetaClass().isSubClassOf(expectedKlass);}}
var currentgeoEntityIdInput=document.getElementById(MDSS.GeoSearch.currentGeoIdInput.id+'_geoEntityId');var geoInfo=document.getElementById(MDSS.GeoSearch.currentGeoIdInput.id+'Info');if(selected===null)
{geoInput.value='';geoInfo.innerHTML='';if(currentgeoEntityIdInput)currentgeoEntityIdInput.value='';}
else
{if(!ignoreSetting)
{MDSS.GeoSearch.currentGeoIdInput.value=selected.getGeoId();}
if(currentgeoEntityIdInput){currentgeoEntityIdInput.value=selected.getGeoEntityId();}
geoInfo.innerHTML=this.constructor.formatDisplay(selected);}
if(valid)
{YAHOO.util.Dom.removeClass(geoInfo,'alert');}
else
{YAHOO.util.Dom.addClass(geoInfo,'alert');}
if(valid&&typeof onValidGeoEntitySelected!=='undefined'&&Mojo.Util.isFunction(onValidGeoEntitySelected))
{onValidGeoEntitySelected();}
this._fireEvent(new MDSS.Event(MDSS.Event.AFTER_SELECTION,{}));},addListener:function(listener)
{this._listeners.push(listener);},_fireEvent:function(event)
{for(var i=0;i<this._listeners.length;i++)
{this._listeners[i].handleEvent(event);}},_updateSelection:function(geoEntityView)
{var div=document.getElementById(this._CURRENT_SELECTION);div.innerHTML=this.constructor.formatDisplay(geoEntityView);this._currentSelection=geoEntityView;},_notifySelectHandler:function(geoEntityView,updateSelection)
{if(updateSelection)
{this._updateSelection(geoEntityView);}
if(Mojo.Util.isFunction(this._selectHandler))
{this._selectHandler(geoEntityView);}},_notifyTreeSelectHandler:function(geoEntityView)
{this._updateSelection(geoEntityView);if(Mojo.Util.isFunction(this._treeSelectHandler))
{this._treeSelectHandler(geoEntityView);}},getSelectHandler:function()
{return(this._selectHandler);},_getStartIndex:function()
{return 1;},_getControllerAction:function()
{return Mojo.$.dss.vector.solutions.geo.GeoEntityTreeController.displaySingleSelectSearch;},_doCreateRoot:function(request)
{if(this._geoId!=null&&this._geoId!=='')
{this.populateSelections(this._geoId);}
else
{Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedIn(request,MDSS.SelectSearchRootId,false,this._filterType);}},_disableAllowed:function()
{return true;}}});Mojo.Meta.newClass("MDSS.GeoSearch",{Instance:{initialize:function(geoInput,selectSearch)
{this._selectSearch=selectSearch||null;this._geoInput=Mojo.Util.isString(geoInput)?document.getElementById(geoInput):geoInput;this._geoElement=document.getElementById(this._geoInput.id+'_geoEntityId');this._opener=document.createElement('img');this._opener.src="./imgs/icons/world.png";this._opener.id=this._geoInput.id+'Go';YAHOO.util.Dom.addClass(this._opener,'geoOpener');YAHOO.util.Dom.insertAfter(this._opener,this._geoInput);var geoInfo=document.createElement('div');geoInfo.id=this._geoInput.id+'Info';geoInfo.innerHTML='()';YAHOO.util.Dom.insertAfter(geoInfo,this._opener);var geoSearchResults=document.createElement('div');geoSearchResults.id=this._geoInput.id+'_results';geoSearchResults.className="yui-panel-container show-scrollbars shadow";YAHOO.util.Dom.insertAfter(geoSearchResults,geoInfo);var dF=Mojo.Util.bind(this,this._displayFunction);var iF=Mojo.Util.bind(this,this._idFunction);var lF=Mojo.Util.bind(this,this._listFunction);var sF=Mojo.Util.bind(this,this._searchFunction);var sEH=Mojo.Util.bind(this,this._selectEventHandler);this._genericSearch=new MDSS.GenericSearch(this._geoInput,this._geoElement,lF,dF,iF,sF,sEH);YAHOO.util.Event.on(this._opener,"click",this._clickHandler,this,this);YAHOO.util.Event.on(this._geoInput,'focus',this._setCurrentInput,null,this);YAHOO.util.Event.on(this._geoInput,'blur',this._blurHandler,this,this);var GeoEntity=Mojo.$.dss.vector.solutions.geo.generated.GeoEntity
this._idAttr=GeoEntity.ID;this._entityNameAttr=GeoEntity.ENTITYNAME;this._geoIdAttr=GeoEntity.GEOID;this._typeAttr=GeoEntity.TYPE;if(this._geoInput.value.length>0)
{var request=new MDSS.Request({that:this,onSend:function(){},onComplete:function(){},onFailure:function(){},onSuccess:function(result){this.that.showGeoInfo(result,this.that._geoInput,true);}});Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getViewByGeoId(request,this._geoInput.value);}
if(this._selectSearch!=null)
{this._selectSearch.addListener(this);}},_clickHandler:function(e)
{var initialized=this._selectSearch.isInitialized();var geoId=this._geoInput.value;this._selectSearch.setGeoId(geoId);this._openPicker(e,this._geoInput);this._genericSearch.resetCache();this._setTreeValidator();if(initialized)
{this._selectSearch.populateSelections(geoId);}},_setTreeValidator:function()
{MDSS.GeoEntityTree.setValidator(Mojo.Util.bind(this,this._validator));},destroy:function()
{YAHOO.util.Event.removeListener(this._opener,'click',this._openPicker);YAHOO.util.Event.removeListener(this._geoInput,'focus',this._setCurrentInput);this._opener=null;this._geoInput=null;},_setCurrentInput:function()
{this.constructor.currentGeoIdInput=this._geoInput;},_openPicker:function(e,newGeoInput)
{this.constructor.currentGeoIdInput=newGeoInput;if(this._selectSearch.isInitialized())
{this._selectSearch.show();}
else
{this._selectSearch.render();}},_displayFunction:function(valueObj)
{return valueObj.getValue(this._geoIdAttr);},_listFunction:function(valueObj)
{return MDSS.AbstractSelectSearch.formatDisplay2(valueObj.getValue(this._entityNameAttr),valueObj.getValue('displayLabel'),valueObj.getValue(this._geoIdAttr),valueObj.getValue('moSubType'));},_idFunction:function(valueObj)
{return valueObj.getValue(this._idAttr);},_selectEventHandler:function(selected)
{var geoEntityId=selected.id;var handler=this._selectSearch.getSelectHandler();handler(geoEntityId,true);this._validateSelection();},_searchFunction:function(request,value)
{var type=this._selectSearch.getFilter();var enforceRoot=this._selectSearch.enforcesRoot();if(Mojo.Util.isString(type)&&type!='')
{Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByEntityNameOrGeoId(request,type,value,enforceRoot);}
else
{var political=this._selectSearch.getPolitical();var populated=this._selectSearch.getPopulated();var sprayTarget=this._selectSearch.getSprayTargetAllowed();var parameters=[political,populated,sprayTarget].concat(this._selectSearch.getExtraUniversals());Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByParameters(request,value,parameters,enforceRoot);}},_getValidationRequest:function()
{var request=new MDSS.Request({that:this,onSend:function(){},onComplete:function(){},onFailure:function(e){MDSS.Calendar.removeError(this.that._opener);MDSS.Calendar.addError(this.that._opener,e.getMessage());},onProblemExceptionDTO:function(e){MDSS.Calendar.removeError(this.that._opener);var problems=e.getProblems();for(var i=0;i<problems.length;i++){var problem=problems[i];MDSS.Calendar.addError(this.that._opener,problem.getMessage());}},onSuccess:function(){MDSS.Calendar.removeError(this.that._opener);}});return request;},_blurHandler:function(e)
{if(e)
{var blurEl=e.explicitOriginalTarget||document.activeElement;var ul=YAHOO.util.Dom.getAncestorByClassName(blurEl,"selectableList")
if(ul)
{return;}
this._validateSelection();}},_validateSelection:function()
{var geoId=this._geoInput.value;if(Mojo.Util.isString(geoId)&&geoId!='')
{var request=this._getValidationRequest();this._validator(request,geoId);}},_validator:function(request,geoId)
{var type=this._selectSearch.getFilter();if(Mojo.Util.isString(type)&&type!='')
{Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.validateByType(request,geoId,type);}
else
{var political=this._selectSearch.getPolitical();var populated=this._selectSearch.getPopulated();var sprayTarget=this._selectSearch.getSprayTargetAllowed();var parameters=[political,populated,sprayTarget].concat(this._selectSearch.getExtraUniversals());Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.validateByParameters(request,geoId,parameters);}},showGeoInfo:function(selected,currentGeoIdInput,valid)
{var currentgeoEntityIdInput=document.getElementById(currentGeoIdInput.id+'_geoEntityId');var geoInfo=document.getElementById(currentGeoIdInput.id+'Info');if(selected===null)
{geoInput.value='';geoInfo.innerHTML='';if(currentgeoEntityIdInput)currentgeoEntityIdInput.value='';}
else
{currentGeoIdInput.value=selected.getGeoId();if(currentgeoEntityIdInput){currentgeoEntityIdInput.value=selected.getGeoEntityId();}
geoInfo.innerHTML=MDSS.AbstractSelectSearch.formatDisplay(selected);}
if(valid)
{YAHOO.util.Dom.removeClass(geoInfo,'alert');}
else
{YAHOO.util.Dom.addClass(geoInfo,'alert');}},handleEvent:function(event)
{if(event.getType()==MDSS.Event.AFTER_SELECTION)
{this._validateSelection();}},setFilter:function(filter)
{if(this._selectSearch!=null){this._selectSearch.setFilter(filter);}
this._genericSearch.resetCache();}},Static:{currentGeoIdInput:null}});Mojo.Meta.newClass('MDSS.MultipleSelectSearch',{Extends:MDSS.AbstractSelectSearch,Instance:{initialize:function()
{this.$initialize(true);this._criteriaMap={};this._CURRENT_SELECTIONS='currentSelections';this._initSelectedUniversals=[];},_notifyTreeSelectHandler:function(geoEntityView)
{this._updateSelection(geoEntityView,true);},setSelectedUniversals:function(selected)
{if(this._rendered)
{var checkboxes=YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');for(var i=0;i<checkboxes.length;i++)
{var check=checkboxes[i];if(check.checked)
{check.checked=false;}}}
else
{this._initSelectedUniversals=[];}
for(var i=0;i<selected.length;i++)
{if(this._rendered)
{var check=document.getElementById(selected[i]+'_selectUniversalType');if(check)
{check.checked=true;}}
else
{this._initSelectedUniversals.push(selected[i]);}}},_getChildren:function(e)
{if(e.shiftKey)
{var currentOption=e.target;var select=currentOption.parentNode;var geoEntityView=this._geoEntityViewCache[currentOption.id];if(geoEntityView.getEntityType()==="dss.vector.solutions.geo.generated.Earth")
{return;}
this._updateSelection(geoEntityView,true);}
else
{this.$_getChildren(e);}},setCriteria:function(criteria)
{this._criteriaMap={};if(this._rendered)
{var selections=document.getElementById(this._CURRENT_SELECTIONS);selections.innerHTML='';}
for(var i=0;i<criteria.length;i++)
{this._updateSelection(criteria[i],this._rendered);}},_updateSelection:function(geoEntityView,updateList)
{var id=geoEntityView.getGeoEntityId();if(Mojo.Util.isObject(this._criteriaMap[id]))
{return;}
else
{this._criteriaMap[id]=geoEntityView;}
if(updateList)
{this._updateSelectionList(geoEntityView);}},_updateSelectionList:function(geoEntityView)
{var liId=geoEntityView.getGeoEntityId()+"_selected";var li=document.createElement('li');li.id=liId;var del=document.createElement('img');YAHOO.util.Dom.setAttribute(del,'src','imgs/icons/delete.png');YAHOO.util.Event.on(del,'click',this._deleteSelection,liId,this);var span=document.createElement('span');span.innerHTML=this.constructor.formatDisplay(geoEntityView);var div=document.createElement('div');div.appendChild(del);div.appendChild(span);li.appendChild(div);var selections=document.getElementById(this._CURRENT_SELECTIONS);selections.appendChild(li);},_doCreateRoot:function(request)
{Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedIn(request,MDSS.SelectSearchRootId,false,'');},_deleteSelection:function(e,liId)
{var id=liId.replace(/_selected/,'');delete this._criteriaMap[id];var li=document.getElementById(liId);var ul=li.parentNode;ul.removeChild(li);},_notifySelectHandler:function()
{},_notifyHideHandler:function()
{if(Mojo.Util.isFunction(this._hideHandler))
{var entities=Mojo.Util.getValues(this._criteriaMap);var checkboxes=YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');var selected=[];for(var i=0;i<checkboxes.length;i++)
{var check=checkboxes[i];if(check.checked)
{var type=check.value;selected.push(type);}}
this._hideHandler(entities,selected);}},_restrictType:function(e)
{var select=e.target;var option=select.options[select.selectedIndex];var type=option.value;if(select.selectedIndex===0)
{return;}
if(this._restrictingType!=null)
{var checkboxes=YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');for(var i=0;i<checkboxes.length;i++)
{checkboxes[i].checked=false;}
var selections=document.getElementById(this._CURRENT_SELECTIONS);selections.innerHTML='';this._criteriaMap={};this._createRoot();}
document.getElementById(type+"_selectUniversalType").checked=true;this._restrictingType=type;var construct=Mojo.Meta.findClass(type);var geoEntity=new construct();var geoEntityView=this._copyEntityToView(geoEntity);this._notifySelectHandler(geoEntityView);},_postRender:function()
{var toggles=YAHOO.util.Selector.query('input.selectUniversalType',this._SELECT_CONTAINER_ID);for(var i=0;i<toggles.length;i++)
{var toggle=toggles[i];YAHOO.util.Event.on(toggle,'click',this._notifySelectUniversalTypeHandler,toggle.value,this);}
var views=Mojo.Util.getValues(this._criteriaMap);for(var i=0;i<views.length;i++)
{this._updateSelectionList(views[i]);}
for(var i=0;i<this._initSelectedUniversals.length;i++)
{var check=document.getElementById(this._initSelectedUniversals[i]+"_selectUniversalType");if(check)
{check.checked=true;}}},_getStartIndex:function()
{return 1;},_getControllerAction:function()
{return Mojo.$.dss.vector.solutions.geo.GeoEntityTreeController.displayMultipleSelectSearch;},_disableAllowed:function()
{return true;}}});var MojoGrid=YAHOO.namespace('MojoGrid');MojoGrid.cellLock=false;MojoGrid.limitTab=false;Mojo.Meta.newClass('MDSS.Event',{Instance:{initialize:function(type,value){this.type=type;this.value=value;},getType:function(){return this.type;},getValue:function(){return this.value;}},Static:{AFTER_ROW_ADD:1,BEFORE_ROW_ADD:2,AFTER_SAVE:3,AFTER_PROBLEM:4,AFTER_FAILURE:5,AFTER_SELECTION:6,AFTER_DELETE:7,BEFORE_SEARCH:8}});Mojo.Meta.newClass('MDSS.dataGrid',{IsAbstract:false,Instance:{initialize:function(data)
{this.tableData=data;this.myDataSource=null;this.myDataTable=null;this.bReverseSorted=false;this.btnSaveRows=false;this.btnAddRow=false;this.disableButton=!Mojo.Util.isBoolean(data.cleanDisable)?true:data.cleanDisable;if(typeof this.tableData.fields==='undefined'){this.tableData.fields=this.tableData.columnDefs.map(function(c){return c.key;}).filter(function(c){return(c!=='delete');});}
if(typeof this.tableData.saveFunction==='undefined'){this.tableData.saveFunction="saveAll";}
if(typeof this.tableData.addButton==='undefined'){this.tableData.addButton="allreadyThere";}
if(typeof this.tableData.copy_from_above==='undefined'){this.tableData.copy_from_above=[];}
this.tableData.dirty=false;this.myDataSource=new YAHOO.util.DataSource(this.tableData.rows);this.myDataSource.responseType=YAHOO.util.DataSource.TYPE_JSARRAY;this.myDataSource.responseSchema={fields:this.tableData.fields};if(this.tableData.width){this.myDataTable=new YAHOO.widget.ScrollingDataTable(this.tableData.div_id,this.tableData.columnDefs,this.myDataSource,{width:this.tableData.width});}
else
{this.myDataTable=new YAHOO.widget.DataTable(this.tableData.div_id,this.tableData.columnDefs,this.myDataSource,{});}
this.myDataTable.tableData=this.tableData;this.tableData.myDataTable=this.myDataTable;this._mapRecords();this.myDataTable.set("selectionMode","singlecell");this.myDataTable.render();this.myDataTable.subscribe("columnSortEvent",this._trackReverseSorts);this.myDataTable.subscribe("cellMouseoverEvent",this._highlightEditableCell);this.myDataTable.subscribe("cellMouseoutEvent",this.myDataTable.onEventUnhighlightCell);this.myDataTable.subscribe("cellClickEvent",this.onCellClick,null,this);this.myDataTable.subscribe("editorKeydownEvent",this.editorKeyEvent,null,this);this.myDataTable.subscribe("editorSaveEvent",this.saveSomeData,null,this);this._setUpButtons();this._listeners=[];},_getDisableButton:function(){return this.disableButton;},_getLabelFromId:function(feild,id){var i=window[feild+"Ids"].indexOf(id);return window[feild+"Labels"][i];},_trackReverseSorts:function(oArg){bReverseSorted=(oArg.dir===YAHOO.widget.DataTable.CLASS_DESC);},_highlightEditableCell:function(oArgs){var elCell=oArgs.target;if(YAHOO.util.Dom.hasClass(elCell,"yui-dt-editable")){this.highlightCell(elCell);}},_saveHandler:function(request,view_array){var klass=Mojo.Meta.findClass(this.tableData.data_type.substring(7));var saveMethod=klass[this.tableData.saveFunction];saveMethod(request,view_array);},_mapRecords:function(){this.recordIndex=0;this.myDataTable.getRecordSet().getRecords().map(function(record){this.record=record;this.tableData.columnDefs.map(function(feild){var editor=this.myDataTable.getColumn(feild.key).editor;if(feild.save_as_id){var label=this._getLabelFromId(feild.key,this.record.getData(feild.key));this.record.setData(feild.key,label);}
else{if(editor&&editor instanceof YAHOO.widget.DropdownCellEditor){for(var i=0;i<editor.dropdownOptions.length;i++){var recordValue=this.record.getData(feild.key);var optionValue=editor.dropdownOptions[i].value;var label=editor.dropdownOptions[i].label;if(recordValue===optionValue){this.record.setData(feild.key,label);}}}}
if(editor instanceof YAHOO.widget.OntologyTermEditor)
{editor.tableData=this.tableData;var data=this.record.getData(feild.key);if(data){var id=data.split('^^^^')[1];var displayLabel=data.split('^^^^')[0];var r=this.tableData.rows[this.recordIndex];if(r){r[feild.key]=id;this.record.setData(feild.key,displayLabel);}}}
if(editor&&editor instanceof YAHOO.widget.DateCellEditor){var date=MDSS.Calendar.parseDate(this.record.getData(feild.key));this.myDataTable.updateCell(this.record,feild.key,date);}
if(feild.title){var th=this.myDataTable.getThEl(this.myDataTable.getColumn(feild.key));if(th)
{th.title=feild.title;}}},this);if(this.tableData.after_row_load){this.tableData.after_row_load(this.record);}
this.recordIndex++;},this);this.record=null;this.recordIndex=null;},_setUpButtons:function(record){if(YAHOO.util.Dom.get('buttons')===null){var tableDiv=YAHOO.util.Dom.get(this.tableData.div_id);var buttons=document.createElement('span');if(!this.tableData.saveLabelKey){this.tableData.saveLabelKey='Save_Rows_To_DB';}
buttons.id=this.tableData.div_id+'Buttons';YAHOO.util.Dom.addClass(buttons,'noprint');YAHOO.util.Dom.addClass(buttons,'dataTableButtons');buttons.innerHTML='';if(this.tableData.addButton!==false){buttons.innerHTML+='<button type="button" id="'+this.tableData.div_id+'Addrow">'+MDSS.localize('New_Row')+'</button>';}
buttons.innerHTML+='<button type="button" id="'+this.tableData.div_id+'Saverows">'+MDSS.localize(this.tableData.saveLabelKey)+'</button>';if(this.tableData.excelButtons!==false){buttons.innerHTML+='<form method="get" action="excelimport" style="display: inline;"><input type="hidden" name="excelType" value="'+this.tableData.excelType+'" /><span class="yui-button yui-push-button"> <span class="first-child"><button type="submit">'+MDSS.localize('Excel_Import_Header')+'</button></span></span></form>';buttons.innerHTML+='<form method="post" action="excelexport" style="display: inline;"><input type="hidden" name="excelType" value="'+this.tableData.excelType+'" /><span class="yui-button yui-push-button"> <span class="first-child"><button type="submit">'+MDSS.localize('Excel_Export_Header')+'</button></span></span></form>';}
if(Mojo.Util.isArray(this.tableData.customButtons)){for(var i=0;i<this.tableData.customButtons.length;i++){var config=this.tableData.customButtons[i];buttons.innerHTML+='<button type="button" id="'+this.tableData.div_id+'.'+config.id+'">'+config.label+'</button>';}}
YAHOO.util.Dom.insertAfter(buttons,tableDiv);}
if(YAHOO.util.Dom.get(this.tableData.div_id+'Addrow')){this.btnAddRow=new YAHOO.widget.Button(this.tableData.div_id+"Addrow");this.btnAddRow.on("click",this.addRow,null,this);}
if(YAHOO.util.Dom.get(this.tableData.div_id+'Saverows')){this.btnSaveRows=new YAHOO.widget.Button(this.tableData.div_id+"Saverows");this.btnSaveRows.on("click",this.persistData,null,this);this.toggleSaveButton(this._getDisableButton());}
if(Mojo.Util.isArray(this.tableData.customButtons)){for(var i=0;i<this.tableData.customButtons.length;i++){var config=this.tableData.customButtons[i];var customButton=new YAHOO.widget.Button(this.tableData.div_id+"."+config.id);customButton.on("click",config.action,null,this);}}},findNext:function(cell,e){var newCell=null;var nKey=e.keyCode;if(nKey===13)
{if(e.shiftKey){newCell=this.myDataTable.getAboveTdEl(cell);}
else{newCell=this.myDataTable.getBelowTdEl(cell);}}
else if(nKey===9)
{if(e.shiftKey){newCell=this.myDataTable.getPreviousTdEl(cell);}else{newCell=this.myDataTable.getNextTdEl(cell);}
while(newCell!==null&&(this.myDataTable.getColumn(newCell).editor===null||this.myDataTable.getColumn(newCell).hidden===true)){if(e.shiftKey){newCell=this.myDataTable.getPreviousTdEl(newCell);}else{newCell=this.myDataTable.getNextTdEl(newCell);}}}
return(newCell);},editorKeyEvent:function(obj){var e=obj.event;if(e.keyCode===9||e.keyCode===13){e.preventDefault();YAHOO.util.Event.stopEvent(e);if(MojoGrid.cellLock)
{return;}
else
{MojoGrid.cellLock=true;}
try
{var cell=this.myDataTable.getCellEditor().getTdEl();var nextCell=this.findNext(cell,e);this.myDataTable.saveCellEditor();if(nextCell){this.myDataTable.unselectAllCells();this.myDataTable.showCellEditor(nextCell);this.myDataTable.selectCell(nextCell);}}
finally
{MojoGrid.cellLock=false;}}},saveSomeData:function(oArgs){var record=oArgs.editor.getRecord();var editor=oArgs.editor;var index=this.myDataTable.getRecordIndex(record);if(oArgs.newData==oArgs.oldData)
{return;}
if(editor instanceof YAHOO.widget.DropdownCellEditor&&window[oArgs.editor.getColumn().key+"Labels"])
{var i=window[oArgs.editor.getColumn().key+"Labels"].indexOf(oArgs.newData);var id=window[oArgs.editor.getColumn().key+"Ids"][i];this.tableData.rows[index][oArgs.editor.getColumn().key]=id;}
else if(editor instanceof YAHOO.widget.OntologyTermEditor)
{var id=oArgs.newData;var displayLabel=editor.getInputDisplayLabel();this.tableData.rows[index][oArgs.editor.getColumn().key]=id;this.myDataTable.updateCell(record,editor.getColumn(),displayLabel);}
else
{this.tableData.rows[index][oArgs.editor.getColumn().key]=oArgs.newData;if(editor instanceof YAHOO.widget.DropdownCellEditor){Mojo.Iter.forEach(editor.dropdownOptions,function(opt){if(oArgs.newData===opt.value){this.myDataTable.updateCell(record,editor.getColumn(),opt.label);}},this);}}
this.tableData.dirty=true;this.enableSaveButton();if(this.tableData.after_row_edit){this.tableData.after_row_edit(record);}
this._sumColumn.apply(this,[oArgs]);},_sumColumn:function(oArgs){if(oArgs.editor.getColumn().sum&&this.tableData.rows.length>1){var record=oArgs.editor.getRecord();var cellValue=record.getData(oArgs.editor.getColumn().key);var lastIndex=this.tableData.rows.length-1;var lastRecord=this.myDataTable.getRecord(lastIndex);var editor=oArgs.editor;var index=this.myDataTable.getRecordIndex(record);var dt=this.myDataTable;var manualLastRowData=this.tableData.rows[lastIndex][editor.getColumn().key];var lastTd=this.myDataTable.getTdEl({record:lastRecord,column:editor.getColumn()});var editedTd=this.myDataTable.getTdEl({record:record,column:editor.getColumn()});if(!(oArgs.newData||oArgs.oldData))
{return;}
YAHOO.util.Dom.removeClass(this.myDataTable.getTdLinerEl(editedTd),"calculated");var newData=parseInt(oArgs.newData,10);newData=newData||0;var oldData=parseInt(oArgs.oldData,10);oldData=oldData||0;var oldTotal=parseInt(lastRecord.getData(editor.getColumn().key),10);oldTotal=oldTotal||0;var newTotal=oldTotal+newData-oldData;if(index!==lastIndex&&(!manualLastRowData)&&(oArgs.newData!=''||cellValue=='')){dt.updateCell(lastRecord,editor.getColumn(),newTotal);YAHOO.util.Dom.addClass(this.myDataTable.getTdLinerEl(lastTd),"calculated");}
var sum=0;dt.getRecordSet().getRecords().map(function(row){var x=parseInt(row.getData(editor.getColumn().key),10);if(x&&dt.getRecordIndex(row)!==lastIndex){sum+=x;}});if(parseInt(lastRecord.getData(editor.getColumn().key),10)!=sum){YAHOO.util.Dom.addClass(lastTd,"dataTableSumError");}else{YAHOO.util.Dom.removeClass(lastTd,"dataTableSumError");}}},_setValue:function(view,attribute,value){var attributeName=attribute.key.substring(0,1).toLowerCase()+attribute.key.substring(1);var setter_exists=Mojo.Util.isFunction(view['set'+attribute.key]);if(setter_exists){if(value){if(view.attributeMap[attributeName]instanceof com.terraframe.mojo.transport.attributes.AttributeDateDTO){view['set'+attribute.key](MDSS.Calendar.parseDate(value));}
else{view['set'+attribute.key](value);}}}
else{var setter_exists=Mojo.Util.isFunction(view['add'+attribute.key]);if(setter_exists){view['add'+attribute.key](value);}}},createObjectRepresentation:function(){var view_arr=new Array();for(var r=0;r<this.tableData.rows.length;r++){var row=this.tableData.rows[r];var view_contructor=Mojo.Meta.findClass(this.tableData.data_type.substring(7));var view=new view_contructor();for(var i=0;i<this.tableData.fields.length;i++){var attrib=this.tableData.fields[i];var val=row[attrib.key];this._setValue(view,attrib,val);}
view_arr.push(view);}
return view_arr;},persistData:function(){this.myDataTable.saveCellEditor();var request=new MDSS.Request({dataTable:this.myDataTable,tableData:this.tableData,btnSaveRows:this.btnSaveRows,thisRef:this,onSuccess:function(savedRows){if(!this.tableData.dont_update_on_save){if(!this.tableData.reloadKeys){this.tableData.reloadKeys=[this.tableData.fields[0].key];}
else{this.tableData.reloadKeys.push(this.tableData.fields[0].key);}
for(var i=0;i<savedRows.length;i++){var record=this.dataTable.getRecord(i);var row=this.tableData.rows[i];for(var j=0;j<this.tableData.reloadKeys.length;j++){var reloadKey=this.tableData.reloadKeys[j];var attributeName=reloadKey.substr(0,1).toLowerCase()+reloadKey.substr(1);var reloadValue="";if(savedRows[i].getAttributeDTO(attributeName)instanceof AttributeReferenceDTO){reloadValue=savedRows[i]["getValue"](attributeName);}
else{reloadValue=savedRows[i]["get"+reloadKey]();}
record.setData(reloadKey,reloadValue);row[reloadKey]=reloadValue;}}
this.tableData.dirty=false;this.thisRef.toggleSaveButton(this.thisRef._getDisableButton());this.dataTable.render();if(this.tableData.after_save){this.tableData.after_save();}
this.thisRef.myDataTable.fireEvent("tableSaveEvent");this.thisRef.fireEvent(new MDSS.Event(MDSS.Event.AFTER_SAVE,{}));}}});var oldOnProblemExceptionDTO=request.onProblemExceptionDTO;var newOnProblemExceptionDTO=function(e){oldOnProblemExceptionDTO.apply(request,[e]);this.thisRef.enableSaveButton();this.thisRef.fireEvent(new MDSS.Event(MDSS.Event.AFTER_PROBLEM,{}))}
var oldOnFailure=request.onFailure;var newOnFailure=function(e){oldOnFailure.apply(request,[e]);this.thisRef.enableSaveButton();this.thisRef.fireEvent(new MDSS.Event(MDSS.Event.AFTER_FAILURE,{}))}
request.onProblemExceptionDTO=newOnProblemExceptionDTO;request.onFailure=newOnFailure;var view_arr=this.createObjectRepresentation();this.disableSaveButton();if(Mojo.Util.isFunction(this.tableData.saveHandler)){this.tableData.saveHandler(request,view_arr);}
else
{this._saveHandler(request,view_arr);}},toggleSaveButton:function(disabled){this.btnSaveRows.set("disabled",disabled);},enableSaveButton:function(){this.toggleSaveButton(false);},disableSaveButton:function(){this.toggleSaveButton(true);},addRow:function(){var event=new MDSS.Event(MDSS.Event.BEFORE_ROW_ADD,{});this.fireEvent(event);if(this.bReverseSorted){this.myDataTable.set("sortedBy",null);}
var new_data_row=this.getDefaultValues();var new_label_row=this.getDefaultLabels();if(this.tableData.rows.length>0){var last_row_index=this.tableData.rows.length-1;this.tableData.copy_from_above.map(function(feild){new_data_row[feild]=this.tableData.rows[last_row_index][feild];var label=this.myDataTable.getRecord(last_row_index).getData(feild);new_label_row[feild]=label;},this);}
this.tableData.rows.push(new_data_row);this.myDataTable.addRow(new_label_row);this.tableData.dirty=true;this.enableSaveButton();var index=this.myDataTable.getRecordSet().getLength()-1;var record=this.myDataTable.getRecord(index);var event=new MDSS.Event(MDSS.Event.AFTER_ROW_ADD,{index:index,record:record});this.fireEvent(event);return event;},fireEvent:function(event){for(var i=0;i<this._listeners.length;i++){this._listeners[i](event);}},getDefaultValues:function(){var row=new Object();var defaults=this.tableData.defaults;var keys=Mojo.Util.getKeys(defaults);for(var i=0;i<keys.length;i++){var key=keys[i];var value=((defaults[key].value!=null)?defaults[key].value:defaults[key]);row[key]=value;}
return row;},getDefaultLabels:function(){var row=new Object();var defaults=this.tableData.defaults;var keys=Mojo.Util.getKeys(defaults);for(var i=0;i<keys.length;i++){var key=keys[i];var value=((defaults[key].value!=null)?defaults[key].value:defaults[key]);var label=defaults[key].label;row[key]=((label!=null)?label:value);}
return row;},onCellClick:function(oArgs){var target=oArgs.target;this.myDataTable.unselectAllCells();this.myDataTable.selectCell(target);var column=this.myDataTable.getColumn(target);if(column.hidden)
{return;}
switch(column.action){case'delete':var record=this.myDataTable.getRecord(target);var row_id=record.getData(this.tableData.fields[0].key);var row_index=this.myDataTable.getRecordIndex(record);if(confirm(MDSS.Localized.Confirm_Delete_Row+(row_index+1)+'?')){if(typeof row_id!=='undefined'&&row_id.length>1){var request=new MDSS.Request({row_id:row_id,dataTable:this.myDataTable,row_index:row_index,thisRef:this,onSuccess:function(deletedRow){this.thisRef.removeRow(this.row_index,this.row_id);}});Mojo.Facade.deleteEntity(request,row_id);}
else{this.removeRow(row_index,row_id);}}
break;default:this.myDataTable.onEventShowCellEditor(oArgs);break;}},removeRow:function(index,id){this.tableData.rows.splice(index,1);this.myDataTable.deleteRow(index);this.fireEvent(new MDSS.Event(MDSS.Event.AFTER_DELETE,{id:id}));},addListener:function(listener){this._listeners.push(listener);}}});MojoGrid.createDataTable=function(data){return new MDSS.dataGrid(data);};MDSS.Calendar={};(function(){var Dom=YAHOO.util.Dom,Event=YAHOO.util.Event,cal1,init_not_done=true,over_cal=false,cur_field='',java_date_format=MDSS.DateSettings.java_date_format,db_datetime_format=MDSS.DateSettings.db_datetime_format,db_date_format=MDSS.DateSettings.db_date_format;var cfg={DATE_FIELD_DELIMITER:MDSS.DateSettings.DATE_FIELD_DELIMITER,DATE_RANGE_DELIMITER:MDSS.DateSettings.DATE_RANGE_DELIMITER,MDY_DAY_POSITION:MDSS.DateSettings.MDY_DAY_POSITION,MDY_MONTH_POSITION:MDSS.DateSettings.MDY_MONTH_POSITION,MDY_YEAR_POSITION:MDSS.DateSettings.MDY_YEAR_POSITION,MONTHS_LONG:MDSS.DateSettings.MONTHS_LONG,WEEKDAYS_SHORT:MDSS.DateSettings.WEEKDAYS_SHORT,navigator:true};var setupListeners=function(){Event.addListener('cal1Container','mouseover',function(){over_cal=true;});Event.addListener('cal1Container','mouseout',function(){over_cal=false;});}
var getDate=function(){var calDate=this.getSelectedDates()[0];cur_field.value=calDate.format(java_date_format);over_cal=false;hideCal();fireOnblur(cur_field);}
var parseISO8601=function(date_string){var tempDate=new Date();var success=Mojo.Util.setISO8601(tempDate,date_string,false);if(success)
{return tempDate;}
return null;}
var var_to_date=function(date_str){if(date_str instanceof Date)return date_str;if(Mojo.Util.isString(date_str)){date_str=date_str.replace(/^\s\s*/,'').replace(/\s\s*$/,'');}
if(date_str==null||date_str=='')return date_str;var date=parseISO8601(date_str);if(date==null)date=Date.parseString(date_str,java_date_format);if(date==null)date=Date.parseString(date_str,db_datetime_format);if(date==null)date=Date.parseString(date_str,db_date_format);if(date==null)date=Date.parseString(date_str);if(date==null&&date_str.length>16)date=new Date(date_str);return date;}
var var_to_java_date=function(date_str){if(date_str instanceof Date)return date_str;if(Mojo.Util.isString(date_str)){date_str=date_str.replace(/^\s\s*/,'').replace(/\s\s*$/,'');}
if(date_str==null||date_str=='')return date_str;return Date.parseString(date_str,java_date_format);}
MDSS.Calendar.parseDate=var_to_date;MDSS.Calendar.parseJavaFormatDate=var_to_java_date;var var_to_db_string=function(date_str){var date=var_to_date(date_str);if(date==null||date=='')return null;return date.format(db_date_format);}
MDSS.Calendar.getMojoDateString=var_to_db_string;var var_to_localized_string=function(date_str){var date=var_to_date(date_str);if(date==null||date=='')return null;return date.format(java_date_format);}
MDSS.Calendar.getLocalizedString=var_to_localized_string;var showCal=function(ev){var tar=Event.getTarget(ev);cur_field=tar;var xy=Dom.getXY(tar);var date_str=Dom.get(tar).value;var date=var_to_date(date_str);if(Dom.hasClass(tar,'NoFuture'))
{cal1.cfg.setProperty('maxdate',new Date());}
else
{cal1.cfg.setProperty('maxdate',null);}
if(date_str&&(date!=null)){cal1.cfg.setProperty('selected',date_str);cal1.cfg.setProperty('pagedate',date,true);}else{cal1.cfg.setProperty('selected','');cal1.cfg.setProperty('pagedate',new Date(),true);}
cal1.render();Dom.setStyle('cal1Container','display','block');xy[1]=xy[1]+20;Dom.setXY('cal1Container',xy);}
var hideCal=function(ev){if(!over_cal){Dom.setStyle('cal1Container','display','none');}
if(ev){validate(ev);}}
function fireOnblur(target){if(document.dispatchEvent){var oEvent=document.createEvent("MouseEvents");oEvent.initMouseEvent("blur",true,true,window,1,1,1,1,1,false,false,false,false,0,target);target.dispatchEvent(oEvent);}
else if(document.fireEvent){target.fireEvent("onblur");}}
var validate=function(ev){var tar=Event.getTarget(ev);var date_str=Dom.get(tar).value;var today=new Date();removeError(tar);if(date_str.length==0)
{return;}
var date=Date.parseString(date_str,java_date_format);if(date_str.length>0&&(date==null))
{addError(tar,MDSS.localize('Invalid_Date_Format'));}
if(Dom.hasClass(tar,'NoFuture')&&date>today)
{addError(tar,MDSS.localize('Future_Dates_Not_Allowed'));}}
var validateYear=function(ev){var tar=Event.getTarget(ev);var date_str=tar.value;var today=new Date();var re=/^(19|20)[0-9]{2}$/;removeError(tar);if(date_str.length==0)
{return;}
var date=Date.parseString(date_str,java_date_format);if(!re.test(date_str))
{addError(tar,MDSS.localize('Invalid_Year'));}
if(parseInt(date_str,10)>parseInt(today.getFullYear(),10))
{addError(tar,MDSS.localize('Future_Dates_Not_Allowed'));}}
var validateNumber=function(ev){var tar=Event.getTarget(ev);var number_str=tar.value;var re=/^[0-9]+$/;removeError(tar);if(number_str.length==0)
{return;}
if(!re.test(number_str))
{addError(tar,MDSS.localize('Invalid_Number'));}}
function addError(tar,errorMessage)
{if(tar instanceof String)
{tar=document.getElementById(tar);}
var errorInfo=document.createElement('span');errorInfo.id=tar.id+'errorInfo';errorInfo.innerHTML=' '+errorMessage;Dom.insertAfter(errorInfo,tar);Dom.addClass(errorInfo,'alert');}
function removeError(tar)
{if(tar instanceof String)
{tar=document.getElementById(tar);}
var delMe=Dom.get(tar.id+'errorInfo');if(delMe)
{var parent=delMe.parentNode;parent.removeChild(delMe);}}
MDSS.Calendar.addError=addError;MDSS.Calendar.removeError=removeError;function addCalendarListeners(el)
{Event.addListener(el.id,'focus',showCal);Event.addListener(el.id,'blur',hideCal);el.value=var_to_localized_string(el.value);}
MDSS.Calendar.addCalendarListeners=addCalendarListeners;var init=function(){var el;if(init_not_done)
{for each(el in Dom.getElementsByClassName("formatDate"))
{el.innerHTML=var_to_localized_string(el.innerHTML);}}
if(init_not_done)
{for each(el in Dom.getElementsByClassName("NoFutureYear"))
{Event.addListener(el,'blur',validateYear);}}
if(init_not_done)
{for each(el in Dom.getElementsByClassName("NumbersOnly"))
{Event.addListener(el,'blur',validateNumber);}}
if(init_not_done){if(!Dom.get('cal1Container'))
{caldiv=document.createElement('div');caldiv.id="cal1Container";document.getElementsByTagName('body')[0].appendChild(caldiv);YAHOO.util.Dom.addClass('cal1Container','yui-skin-sam');}
cal1=new YAHOO.widget.Calendar("cal1","cal1Container",cfg);cal1.selectEvent.subscribe(getDate,cal1,true);cal1.renderEvent.subscribe(setupListeners,cal1,true);for each(el in Dom.getElementsByClassName("DatePick"))
{Event.addListener(el.id,'focus',showCal);Event.addListener(el.id,'blur',hideCal);el.value=var_to_localized_string(el.value);}
cal1.render();init_not_done=false;}
return cal1;}
MDSS.Calendar.init=init;Event.addListener(window,'load',init);})();MDSS.collectionSearch=function(config){var searchEl=Mojo.Util.isString(config.search)?document.getElementById(config.search):config.search;var concreteEl=Mojo.Util.isString(config.concrete)?document.getElementById(config.concrete):config.concrete;var listFunction=function(view){var formattedDate=MDSS.Calendar.getLocalizedString(view.getCollectionDate());return view.getGeoEntityLabel()+', '+formattedDate+' - '+view.getCollectionId();};var idFunction=function(view){return view.getConcreteId();};var displayFunction=function(view){return view.getCollectionId();};var searchFunction=Mojo.$.dss.vector.solutions.entomology.MosquitoCollectionView.searchCollection;var selectEventHandler=function(){};var search=new MDSS.GenericSearch(searchEl,concreteEl,listFunction,displayFunction,idFunction,searchFunction,selectEventHandler);search.addParameter(config.type);var checkManualEntry=function(){var request=new MDSS.Request({searchEl:searchEl,concreteEl:concreteEl,onSend:function(){},onComplete:function(){},onFailure:function(){},onSuccess:function(result){if(result!==null){searchEl.value=result.getCollectionId();concreteEl.value=result.getId();}}});}
YAHOO.util.Event.on(searchEl,'blur',checkManualEntry,null,null);}
MDSS.operatorSearch=function(config){var to=Mojo.Util.isString(config.to)?document.getElementById(config.to):config.to;var from=Mojo.Util.isString(config.from)?document.getElementById(config.from):config.from;var button=Mojo.Util.isString(config.button)?document.getElementById(config.button):config.button;var display=Mojo.Util.isString(config.display)?document.getElementById(config.display):config.display;var id=Mojo.Util.isString(config.from)?document.getElementById(config.from):config.from;var listFunction=function(valueObject){return valueObject.text;};var idFunction=function(valueObject){return valueObject.value;};var displayFunction=function(valueObject){return valueObject.text;};var searchFunction=function(request,value){var filtered=[];for(var i=0;i<from.options.length;i++){var element=from.options[i];var text=element.text.toUpperCase();if(text.search(value.toUpperCase())!==-1)
{filtered.push(element);}}
var query={resultSet:filtered,getResultSet:function(){return this.resultSet;}};request.onSuccess(query);};var selectEventHandler=function(){};var removeOption=function(selectbox,value){for(var i=0;i<selectbox.options.length;i++){if(selectbox.options[i].value===value)
selectbox.remove(i);}}
var addOption=function(selectbox,text,value){if(!Selectbox.containsOption(selectbox,value)){Selectbox.addOption(selectbox,text,value,false);}}
var addClick=function(){var displayValue=display.value;var idValue=id.value;if(idValue!==null&&display.value!==null&&idValue!==''&&display.value!==''){removeOption(from,idValue);addOption(to,display.value,idValue);id.value=null;display.value=null;}}
var search=new MDSS.GenericSearch(display,id,listFunction,displayFunction,idFunction,searchFunction,selectEventHandler);YAHOO.util.Event.on(button,'click',addClick,null,null);}
Mojo.Meta.newClass('MDSS.TeamMemberSearch',{Instance:{initialize:function(){},_listFunction:function(valueObject){var firstName=valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.FIRSTNAME);var lastName=valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.LASTNAME);var memberId=valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.MEMBERID);return firstName+' '+lastName+' - '+memberId;},_idFunction:function(valueObject){var id=valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.ID);return id;},_displayFunction:function(valueObject){var firstName=valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.FIRSTNAME);var lastName=valueObject.getValue(Mojo.$.dss.vector.solutions.irs.TeamMemberView.LASTNAME);return firstName+' '+lastName;}}});Mojo.Meta.newClass('MDSS.SprayLeaderSearch',{Extends:MDSS.TeamMemberSearch,Instance:{initialize:function(config){var searchEl=Mojo.Util.isString(config.search)?document.getElementById(config.search):config.search;var concreteEl=Mojo.Util.isString(config.concrete)?document.getElementById(config.concrete):config.concrete;var dF=Mojo.Util.bind(this,this._displayFunction);var iF=Mojo.Util.bind(this,this._idFunction);var lF=Mojo.Util.bind(this,this._listFunction);var sF=Mojo.Util.bind(this,this._searchFunction);var sEH=Mojo.Util.bind(this,this._selectEventHandler);this.search=new MDSS.GenericSearch(searchEl,concreteEl,lF,dF,iF,sF,sEH);},_searchFunction:function(request,value){Mojo.$.dss.vector.solutions.irs.TeamMember.searchForLeader(request,value);},_selectEventHandler:function(){}}});Mojo.Meta.newClass('MDSS.OperatorSearch',{Extends:MDSS.TeamMemberSearch,Instance:{initialize:function(config){var searchEl=Mojo.Util.isString(config.search)?document.getElementById(config.search):config.search;var concreteEl=Mojo.Util.isString(config.concrete)?document.getElementById(config.concrete):config.concrete;var dF=Mojo.Util.bind(this,this._displayFunction);var iF=Mojo.Util.bind(this,this._idFunction);var lF=Mojo.Util.bind(this,this._listFunction);var sF=Mojo.Util.bind(this,this._searchFunction);var sEH=Mojo.Util.bind(this,this._selectEventHandler);this.search=new MDSS.GenericSearch(searchEl,concreteEl,lF,dF,iF,sF,sEH);},_searchFunction:function(request,value){Mojo.$.dss.vector.solutions.irs.TeamMemberView.searchOperators(request,value);},_selectEventHandler:function(){}}});Mojo.Meta.newClass('MDSS.UnassignedOperatorsSearch',{Extends:MDSS.TeamMemberSearch,Instance:{initialize:function(config){this._labelEl=Mojo.Util.isString(config.label)?document.getElementById(config.label):config.label;this._searchEl=Mojo.Util.isString(config.search)?document.getElementById(config.search):config.search;this._concreteEl=Mojo.Util.isString(config.concrete)?document.getElementById(config.concrete):config.concrete;this._assigned=Mojo.Util.isString(config.assigned)?document.getElementById(config.assigned):config.assigned;this._teamOps=Mojo.Util.isString(config.teamOps)?document.getElementById(config.teamOps):config.teamOps;if(this._searchEl!=null){var dF=Mojo.Util.bind(this,this._displayFunction);var iF=Mojo.Util.bind(this,this._idFunction);var lF=Mojo.Util.bind(this,this._listFunction);var sF=Mojo.Util.bind(this,this._searchFunction);var sEH=Mojo.Util.bind(this,this._selectEventHandler);this.search=new MDSS.GenericSearch(this._searchEl,this._concreteEl,lF,dF,iF,sF,sEH);}},_searchFunction:function(request,value){Mojo.$.dss.vector.solutions.irs.TeamMemberView.getUnassignedOperators(request,value);},_selectEventHandler:function(selected){if(this._labelEl!=null){this._labelEl.value=selected.label;}
if(this._teamOps!=null){this._teamOps.selectedIndex=0;}
if(this._assigned!=null){this._assigned.value='';}}}});Mojo.Meta.newClass('MDSS.AssignedOperatorsSearch',{Extends:MDSS.TeamMemberSearch,Instance:{initialize:function(config){this._teamEl=Mojo.Util.isString(config.team)?document.getElementById(config.team):config.team;this._labelEl=Mojo.Util.isString(config.label)?document.getElementById(config.label):config.label;this._searchEl=Mojo.Util.isString(config.search)?document.getElementById(config.search):config.search;this._concreteEl=Mojo.Util.isString(config.concrete)?document.getElementById(config.concrete):config.concrete;this._unassigned=Mojo.Util.isString(config.unassigned)?document.getElementById(config.unassigned):config.unassigned;this._teamOps=Mojo.Util.isString(config.teamOps)?document.getElementById(config.teamOps):config.teamOps;if(this._searchEl!=null){var dF=Mojo.Util.bind(this,this._displayFunction);var iF=Mojo.Util.bind(this,this._idFunction);var lF=Mojo.Util.bind(this,this._listFunction);var sF=Mojo.Util.bind(this,this._searchFunction);var sEH=Mojo.Util.bind(this,this._selectEventHandler);this._search=new MDSS.GenericSearch(this._searchEl,this._concreteEl,lF,dF,iF,sF,sEH);}
if(this._teamEl!=null){YAHOO.util.Event.on(this._teamEl,'change',this._resetCache,this,this);}},_resetCache:function(){this._search.resetCache();},_searchFunction:function(request,value){if(this._teamEl!=null){var teamId=this._teamEl.value;if(teamId){Mojo.$.dss.vector.solutions.irs.TeamMemberView.getOtherOperators(request,value,teamId);}}},_selectEventHandler:function(selected){if(this._labelEl!=null){this._labelEl.value=selected.label;}
if(this._teamOps!=null){this._teamOps.selectedIndex=0;}
if(this._unassigned!=null){this._unassigned.value='';}}}});Mojo.Meta.newClass('MDSS.SearchValidator',{Instance:{initialize:function(searchType,prop){this._searchDate=document.getElementById(searchType+'.positive');this._searchPeriod=document.getElementById(searchType+'.negative');this._dateValidator=new MDSS.DateSearchValidator(prop);this._epiValidator=new MDSS.EpiSearchValidator(prop);this.validate();YAHOO.util.Event.on(this._searchDate,'change',this.validate,this,this);YAHOO.util.Event.on(this._searchPeriod,'change',this.validate,this,this);},validate:function(){if(this._searchDate.checked==true){this._dateValidator.validate();}
else{this._epiValidator.validate();}},eventHandler:function(e){if(e.getType()==MDSS.Event.AFTER_SELECTION){this.validate();}}}});Mojo.Meta.newClass('MDSS.DateSearchValidator',{Instance:{initialize:function(prop){this._button=Mojo.Util.isString(prop.button)?document.getElementById(prop.button):prop.button;this._geoId=Mojo.Util.isString(prop.geoId)?document.getElementById(prop.geoId):prop.geoId;this._startDate=Mojo.Util.isString(prop.startDate)?document.getElementById(prop.startDate):prop.startDate;this._endDate=Mojo.Util.isString(prop.endDate)?document.getElementById(prop.endDate):prop.endDate;YAHOO.util.Event.on(this._geoId,'blur',this.validate,this,this);YAHOO.util.Event.on(this._startDate,'blur',this.validate,this,this);YAHOO.util.Event.on(this._endDate,'blur',this.validate,this,this);YAHOO.util.Event.on(this._startDate,'keydown',this.keyHandler,this,this);YAHOO.util.Event.on(this._endDate,'keydown',this.keyHandler,this,this);},disableButton:function(){this._button.disabled=true;},enableButton:function(){this._button.disabled=false;},keyHandler:function(e){if(e.keyCode==9||e.keyCode==39||e.keyCode==37){this.validateButton();}},validateButton:function(){this.disableButton();if(this._startDate.value!=''&&this._endDate.value!='')
{var startDate=MDSS.Calendar.parseJavaFormatDate(this._startDate.value);var endDate=MDSS.Calendar.parseJavaFormatDate(this._endDate.value);if(startDate instanceof Date&&endDate instanceof Date&&this._geoId.value!=''){this.enableButton();}}},validate:function(){this.disableButton();if(this._startDate.value!=''&&this._endDate.value!='')
{var startDate=MDSS.Calendar.parseJavaFormatDate(this._startDate.value);var endDate=MDSS.Calendar.parseJavaFormatDate(this._endDate.value);if(startDate instanceof Date&&endDate instanceof Date){if(startDate>endDate){alert(MDSS.localize('Invalid_Dates'));}
else if(this._geoId.value!=''){this.enableButton();}}}}}});Mojo.Meta.newClass('MDSS.EpiSearchValidator',{Instance:{initialize:function(prop){this._button=Mojo.Util.isString(prop.button)?document.getElementById(prop.button):prop.button;this._geoId=Mojo.Util.isString(prop.geoId)?document.getElementById(prop.geoId):prop.geoId;this._year=Mojo.Util.isString(prop.year)?document.getElementById(prop.year):prop.year;this._period=Mojo.Util.isString(prop.period)?document.getElementById(prop.period):prop.period;this._periodType=Mojo.Util.isString(prop.periodType)?YAHOO.util.Selector.query('.'+prop.periodType):prop.periodType;YAHOO.util.Event.on(this._geoId,'blur',this.validate,this,this);YAHOO.util.Event.on(this._periodType,'change',this.validate,this,this);YAHOO.util.Event.on(this._period,'keyup',this.keyUpHandler,this,this);YAHOO.util.Event.on(this._year,'keyup',this.keyUpHandler,this,this);YAHOO.util.Event.on(this._startDate,'keydown',this.keyDownHandler,this,this);YAHOO.util.Event.on(this._endDate,'keydown',this.keyDownHandler,this,this);},disableButton:function(){this._button.disabled=true;},enableButton:function(){this._button.disabled=false;},_hasValidEpiInput:function(){var re=/^[0-9]+$/;if(!re.test(this._year.value)||!re.test(this._period.value)){return false;}
var yearValue=this._year.value;var selectedType=this._getSelectedEpiType();var hasGeoEntity=(this._geoId.value!='');var hasYear=(yearValue!=''&&yearValue.length==4);var hasPeriod=(this._period.value!='');var hasPeriodType=(selectedType!='');return(hasGeoEntity&&hasYear&&hasPeriod&&hasPeriodType);},_getSelectedEpiType:function(){for(var i=0;i<this._periodType.length;i++){var radio=this._periodType[i];if(radio.checked){return radio.value;}}
return'';},_clearMessages:function(){MDSS.Calendar.removeError(this._geoId);MDSS.Calendar.removeError(this._year);MDSS.Calendar.removeError(this._period);},keyDownHandler:function(e){if(e.keyCode==9||e.keyCode==39||e.keyCode==37){this.validate();}},keyUpHandler:function(e){if(e.keyCode<37||e.keyCode>40){this.validate();}},validate:function(){this.disableButton();if(this._hasValidEpiInput()){var request=new MDSS.Request({that:this,onSuccess:function(){this.that._clearMessages();this.that.enableButton();if(this.e&&e.keyCode===9){this.that._button.focus();}},onFailure:function(e){this.that._clearMessages();MDSS.Calendar.addError(this.that._geoId,e.getLocalizedMessage());},onProblemExceptionDTO:function(e){this.that._clearMessages();var problems=e.getProblems();for each(p in problems){if(p.getType()=="dss.vector.solutions.FuturePeriodProblem"){MDSS.Calendar.addError(this.that._year,p.getLocalizedMessage());}
else{MDSS.Calendar.addError(this.that._period,p.getLocalizedMessage());}}}});var periodValue=parseInt(this._period.value);var yearValue=parseInt(this._year.value);var selectedType=this._getSelectedEpiType();Mojo.$.dss.vector.solutions.surveillance.AggregatedCaseView.validateSearchCriteria(request,this._geoId.value,selectedType,periodValue,yearValue);}}}});Mojo.Meta.newClass('MDSS.TeamSearch',{Instance:{initialize:function(geoId,teamSelect,operatorSelect,leaderSelect){this._geoId=Mojo.Util.isString(geoId)?document.getElementById(geoId):geoId;this._teamSelect=Mojo.Util.isString(teamSelect)?document.getElementById(teamSelect):teamSelect;this._operatorSelect=Mojo.Util.isString(operatorSelect)?document.getElementById(operatorSelect):operatorSelect;this._leaderSelect=Mojo.Util.isString(leaderSelect)?document.getElementById(leaderSelect):leaderSelect;this._pollingId=null;YAHOO.util.Event.addListener(this._teamSelect,"change",this.populateFields,this,true);},getGeoId:function(){return this._geoId;},getTeamSelect:function(){return this._teamSelect;},getOperatorSelect:function(){return this._operatorSelect;},getLeaderSelect:function(){return this._leaderSelect;},getTeamId:function(){return this.teamId;},clearSelect:function(select){if(select){Selectbox.removeAllOptions(select);select.disabled=true;}},clearTeamMembers:function(){this.clearSelect(this.getOperatorSelect());this.clearSelect(this.getLeaderSelect());},clearAll:function(){this.clearSelect(this.getTeamSelect());this.clearSelect(this.getOperatorSelect());this.clearSelect(this.getLeaderSelect());},populateFields:function(){this.clearTeamMembers();if(this.getOperatorSelect()!=null){this._populateOperators();}
if(this.getLeaderSelect()!=null){this._populateLeaders();}},_populateOperatorList:function(select,operators,blank){if(select){Selectbox.removeAllOptions(select);if(blank!=null&&blank==true){Selectbox.addOption(select,"","",false);}
for(var i=0;i<operators.length;i++){var label=operators[i].getMemberId()+" - "+operators[i].getLastName()+", "+operators[i].getFirstName();Selectbox.addOption(select,label,operators[i].getActorId(),false);}
select.disabled=false;}},_populateOperators:function(){if(this.getTeamSelect().value!='')
{var request=new MDSS.Request({that:this,onFailure:function(){this.that.clearTeamMembers();},onProblemExceptionDTO:function(){this.that.clearTeamMembers();},onSuccess:function(operators){this.that._populateOperatorList(this.that.getOperatorSelect(),operators,true);}});Mojo.$.dss.vector.solutions.irs.SprayTeam.getOperatorViews(request,this.getTeamSelect().value);}},_populateLeaders:function(){if(this.getTeamSelect().value!='')
{var request=new MDSS.Request({that:this,onFailure:function(){this.that.clearTeamMembers();},onProblemExceptionDTO:function(){this.that.clearTeamMembers();},onSuccess:function(operators){this.that._populateOperatorList(this.that.getLeaderSelect(),operators,true);}});Mojo.$.dss.vector.solutions.irs.SprayTeam.getTeamMemberViews(request,this.getTeamSelect().value);}},populateSprayTeams:function(){this.clearAll();if(this.getGeoId().value!='')
{var request=new MDSS.Request({obj:this,onFailure:function(){this.obj.clearAll();},onProblemExceptionDTO:function(){this.obj.clearAll();},onSuccess:function(teams){Selectbox.removeAllOptions(this.obj.getTeamSelect());Selectbox.addOption(this.obj.getTeamSelect(),'Select Team','',false);for(var i=0;i<teams.length;i++){Selectbox.addOption(this.obj.getTeamSelect(),teams[i].getTeamId(),teams[i].getId(),false);}
this.obj.getTeamSelect().disabled=false;}});Mojo.$.dss.vector.solutions.irs.SprayTeam.findByLocation(request,this.getGeoId().value);}}}});Mojo.Meta.newClass('MDSS.ElementCondition',{IsAbstract:true,Instance:{initialize:function(option,condition){this.option=option;this.condition=condition;},getOption:function(){return this.option;},getCondition:function(){return this.condition;},evaluate:{IsAbstract:true}}});Mojo.Meta.newClass('MDSS.RadioElementCondition',{Extends:MDSS.ElementCondition,Instance:{initialize:function(option,condition){this.$initialize(option,condition);},evaluate:function(){if(this.getOption()&&this.getCondition()){return(this.getOption().checked==this.getCondition());}
return true;}}});Mojo.Meta.newClass('MDSS.SelectElementCondition',{Extends:MDSS.ElementCondition,Instance:{initialize:function(option,condition){this.$initialize(option,condition);},evaluate:function(){if(this.getOption()&&this.getCondition()){return(this.getOption().selected==this.getCondition());}
return true;}}});Mojo.Meta.newClass('MDSS.AbstractHiddenElement',{IsAbstract:true,Instance:{initialize:function(prop){this._visible=true;this._clearValue=Mojo.Util.isBoolean(prop.clearValue)?prop.clearValue:true;this._elements=YAHOO.util.Selector.query('.'+prop.element);},updateValues:function(){IsAbstract:true},clearValues:function(){IsAbstract:true},resetValues:{IsAbstract:true},getClearValue:function(){return this._clearValue;},getElements:function(){return this._elements;},hideElement:function(){if(this._visible==true){this.updateValues();var clear=this.getClearValue();if(clear===true){this.clearValues();}
var elements=this.getElements();for(var i=0;i<elements.length;i++){elements[i].style.display="none";}
this._visible=false;}},showElement:function(){if(this._visible==false){this.resetValues();var elements=this.getElements();for(var i=0;i<elements.length;i++){var element=elements[i];if(element.tagName&&element.tagName=='div'){element.style.display="block";}
element.style.display="inline";}
this._visible=true;}}}});Mojo.Meta.newClass('MDSS.HiddenBooleanElement',{Extends:MDSS.AbstractHiddenElement,Instance:{initialize:function(prop){this.$initialize(prop);this._positiveElement=document.getElementById(prop.element+'.positive');this._negativeElement=document.getElementById(prop.element+'.negative');this.updateValues();},updateValues:function(){if(this._positiveElement!=null){this._postivieChecked=this._positiveElement.checked;}
if(this._negativeElement!=null){this._negativeChecked=this._negativeElement.checked;}},clearValues:function(){if(this._positiveElement!=null){this._positiveElement.checked=false;}
if(this._negativeElement!=null){this._negativeElement.checked=false;}},resetValues:function(){if(this._positiveElement!=null){this._positiveElement.checked=this._postivieChecked;}
if(this._negativeElement!=null){this._negativeElement.checked=this._negativeChecked;}}}});Mojo.Meta.newClass('MDSS.HiddenRadioElement',{Extends:MDSS.AbstractHiddenElement,Instance:{initialize:function(prop){this.$initialize(prop);this._options=YAHOO.util.Selector.query('.'+prop.option);this._values=[];this.updateValues();},updateValues:function(){for(var i=0;i<this._options.length;i++){this._values[i]=this._options[i].checked;}},clearValues:function(){for(var i=0;i<this._options.length;i++){this._options[i].checked=false;}},resetValues:function(){for(var i=0;i<this._options.length;i++){this._options[i].checked=this._values[i];}}}});Mojo.Meta.newClass('MDSS.HiddenSelectElement',{Extends:MDSS.AbstractHiddenElement,Instance:{initialize:function(prop){this.$initialize(prop);this._inputElement=document.getElementById(prop.element);this._clearValue=Mojo.Util.isBoolean(prop.clearValue)?prop.clearValue:true;this.updateValues();},updateValues:function(){if(this._inputElement!=null){this._selectedIndex=this._inputElement.selectedIndex;}},clearValues:function(){if(this._inputElement!=null&&this._inputElement.value!=''){var blankOption=document.createElement('option');blankOption.text='';blankOption.value='';this._inputElement.add(blankOption,null);this._inputElement.selectedIndex=this._inputElement.length-1;}},resetValues:function(){if(this._inputElement!=null){this._inputElement.selectedIndex=this._selectedIndex;this._inputElement.remove(this._inputElement.length-1);}}}});Mojo.Meta.newClass('MDSS.HiddenInputElement',{Extends:MDSS.AbstractHiddenElement,Instance:{initialize:function(prop){this.$initialize(prop);this._inputElement=document.getElementById(prop.element);this._clearValue=Mojo.Util.isBoolean(prop.clearValue)?prop.clearValue:true;this.updateValues();},updateValues:function(){if(this._inputElement!=null){this._inputValue=this._inputElement.value;}},clearValues:function(){if(this._inputElement!=null){this._inputElement.value='';}},resetValues:function(){if(this._inputElement!=null){this._inputElement.value=this._inputValue;}}},Static:{toArray:function(elements){var array=new Array();for(var i=0;i<elements.length;i++){var element=new MDSS.HiddenInputElement({element:elements[i]});array.push(element);}
return array;}}});Mojo.Meta.newClass('MDSS.HiddenMultiTermElement',{Extends:MDSS.AbstractHiddenElement,Instance:{initialize:function(prop){this.$initialize(prop);this._resultList=document.getElementById(prop.element+'ResultList');this._inputElement=document.getElementById(prop.element);this._clearValue=Mojo.Util.isBoolean(prop.clearValue)?prop.clearValue:true;this.updateValues();},updateValues:function(){if(this._inputElement!=null){this._inputValue=this._inputElement.value;}
if(this._resultList!=null){this._results=this._resultList.innerHTML;}},clearValues:function(){if(this._inputElement!=null){this._inputElement.value='';}
if(this._resultList!=null){this._resultList.innerHTML='';}},resetValues:function(){if(this._inputElement!=null){this._inputElement.value=this._inputValue;}
if(this._resultList!=null){this._resultList.innerHTML=this._results;}}}});Mojo.Meta.newClass('MDSS.ElementHandler',{Instance:{initialize:function(condition,elements){this.condition=condition;this.elements=elements;this._listeners=[];this.optionHandler();},getElements:function(){return this.elements;},getCondition:function(){return this.condition;},optionHandler:function(){if(this.getCondition())
{var evaluate=this.getCondition().evaluate();if(evaluate)
{for(var i=0;i<this.elements.length;i++){this.elements[i].showElement();}}
else
{for(var i=0;i<this.elements.length;i++){this.elements[i].hideElement();}}
for(var i=0;i<this._listeners.length;i++){this._listeners[i].optionHandler();}}},addListener:function(listener){this._listeners.push(listener);}},Static:{setupBooleanHandler:function(conditionElement,trigger,elements){conditionElement=MDSS.ElementHandler.getElement(conditionElement);var condition=new MDSS.RadioElementCondition(conditionElement,true);var handler=new MDSS.ElementHandler(condition,elements);MDSS.ElementHandler.addEventListener(conditionElement,handler);MDSS.ElementHandler.addEventListener(trigger,handler);return handler;},setupSelectHandler:function(conditionElement,trigger,elements){conditionElement=MDSS.ElementHandler.getElement(conditionElement);var condition=new MDSS.SelectElementCondition(conditionElement,true);var handler=new MDSS.ElementHandler(condition,elements);MDSS.ElementHandler.addEventListener(conditionElement,handler);MDSS.ElementHandler.addEventListener(trigger,handler);return handler;},addEventListener:function(obj,handler){if(Mojo.Util.isArray(obj)){for(var key in obj){var element=obj[key];element=MDSS.ElementHandler.getElement(element);YAHOO.util.Event.addListener(element,"change",handler.optionHandler,handler,true);}}
else{var element=MDSS.ElementHandler.getElement(obj);YAHOO.util.Event.addListener(element,"change",handler.optionHandler,handler,true);}},getElement:function(obj){if(Mojo.Util.isString(obj)){return document.getElementById(obj);}
return obj;}}});Mojo.Meta.newClass('MDSS.DataSource',{Instance:{initialize:function(callback,searchFunction){this.callback=callback;this.searchFunction=searchFunction;this.requestCount=0;this.currentRequest=0;this.cache={};this._enabled=true;},nextNumber:function(){return++this.requestCount;},retrievedResults:function(value,requestNumber,results){this.cache[value]=results;if(requestNumber>this.currentRequest){this.currentRequest=requestNumber;this.callback.displayResults(results);}},getResultsFromServer:function(value,parameters){var requestNumber=this.nextNumber();var request=new MDSS.Request({that:this,value:value,requestNumber:requestNumber,onSend:function(){},onComplete:function(){},onSuccess:function(query){var results=query.getResultSet();this.that.retrievedResults(this.value,this.requestNumber,results);}});if(parameters){if(Mojo.Util.isArray(parameters)){var args=[request,value];args=args.concat(parameters);this.searchFunction.apply(this,args);}
else{this.searchFunction(request,value,parameters);}}
else{this.searchFunction(request,value);}},enable:function(){this._enabled=true;},disable:function(){this._enabled=false;},getResults:function(value,parameters){if(this.cache[value]&&this._enabled){var requestNumber=this.nextNumber();this.retrievedResults(value,requestNumber,this.cache[value]);}
else{this.getResultsFromServer(value,parameters);}},resetCache:function(){this.cache={};}}});Mojo.Meta.newClass('MDSS.ResultPanel',{Instance:{initialize:function(autocomplete,element){this._autocomplete=autocomplete;this.ul=document.createElement('ul');this.ul.id=element.id+'_results_ul';this.index=0;var resultsDiv=document.createElement('div');resultsDiv.id=element.id+'_results';resultsDiv.className="yui-panel-container show-scrollbars shadow";YAHOO.util.Dom.insertAfter(resultsDiv,element);this.panel=new YAHOO.widget.Panel(resultsDiv,{zindex:15,draggable:false,close:false,visible:false});var listener=new YAHOO.util.KeyListener(document,{keys:27},{fn:this.panel.hide,scope:this.panel,correctScope:true},"keyup");this.panel.cfg.queueProperty("keylisteners",listener);var outer=document.createElement('div');var inner=document.createElement('div');outer.appendChild(inner);inner.appendChild(this.ul);this.panel.setBody(outer);outer.appendChild(inner);YAHOO.util.Dom.addClass(this.ul,'selectableList')
YAHOO.util.Event.on(this.ul,'mouseover',function(e,obj){var li=e.target;var ul=e.currentTarget;if(li.nodeName==='SPAN'){li=li.parentNode;}
if(li.nodeName!=='LI'){return;}
this.selectOption(li.index);},this,this);YAHOO.util.Event.on(this.ul,'click',function(e,obj){var li=e.target;var ul=e.currentTarget;if(li.nodeName==='SPAN')
{li=li.parentNode;}
if(li.nodeName!=='LI')
{return;}
this._autocomplete.selectHandler(li);},this,this);},_getOption:function(i){return this.ul.children[i];},_unselectOption:function(i){var option=this._getOption(i);YAHOO.util.Dom.removeClass(option,'currentSelection');},_selectOption:function(i){var option=this._getOption(i);YAHOO.util.Dom.addClass(option,'currentSelection');},setOptions:function(options){this.index=null;this.ul.innerHTML='';this.ul.appendChild(options);if(this.ul.children.length>0){this.panel.render();this.show();this.panel.bringToTop();}
else{this.hide();}},show:function(){this.panel.show();},hide:function(){this.panel.hide();},isVisible:function(){return this.panel.cfg.getProperty("visible");},selectOption:function(i){if(this.index!=null){this._unselectOption(this.index);}
if(i<this.ul.children.length&&i>=0){this.index=i;this._selectOption(this.index);}},selectPrevious:function(){if(this.index!=null){var i=(this.index-1>=0)?this.index-1:this.ul.children.length-1;this.selectOption(i);}
else{this.selectOption(this.ul.children.length-1);}},selectNext:function(){if(this.index!=null){var i=(this.index+1<this.ul.children.length)?this.index+1:0;this.selectOption(i);}
else{this.selectOption(0);}},selectCurrent:function(){if(this.index!=null){var option=this._getOption(this.index);this._autocomplete.selectHandler(option);}
else if(this.ul.children.length==1){var option=this._getOption(0);this._autocomplete.selectHandler(option);}},hasOptions:function(){return this.ul.children.length>0;}}});Mojo.Meta.newClass('MDSS.OptionBuilder',{Instance:{initialize:function(listFunction,displayFunction,idFunction){this.listFunction=listFunction;this.displayFunction=displayFunction;this.idFunction=idFunction;},createOption:function(valueObj,searchValue,index){var displayStr=this.getListDisplay(valueObj);var matched=displayStr.replace(new RegExp("(.*?)("+searchValue+")(.*?)","gi"),"$1<span class='searchMatch'>$2</span>$3");var li=document.createElement('li');li.id=this.getId(valueObj);li.label=this.getDisplay(valueObj);li.innerHTML=matched;li.index=index;return li;},getDisplay:function(valueObject){return this.displayFunction(valueObject);},getListDisplay:function(valueObject){return this.listFunction(valueObject);},getId:function(valueObject){return this.idFunction(valueObject);}}});Mojo.Meta.newClass('MDSS.AutoComplete',{IsAbstract:true,Instance:{initialize:function(dataSource,optionBuilder,panel,selectEventHandler,prop){this._selectEventHandler=selectEventHandler;this._dataSource=dataSource;this._optionBuilder=optionBuilder;this._panel=panel;this._value=this.getValue();this._hasChanged=false;this._hasSelection=false;this.listeners=[];this.parameters=null;if(prop==null){prop={minLength:2};}
this.minLength=(Mojo.Util.isNumber(prop.minLength*1)?prop.minLength*1:2);},disableCache:function(){this._dataSource.disable();},preventFormSubmit:function(e)
{if((e.keyCode||e.charCode)===13)
{YAHOO.util.Event.preventDefault(e);}},hide:function(){this._panel.hide();},show:function(){this._panel.show();},getPanel:function(){return this._panel;},getParameters:function(){return this.parameters;},addParameter:function(parameter){this.parameters=parameter;},addListener:function(listener){this.listeners.push(listener);},displayResults:function(results){if(!this._hasSelection){var searchValue=this.getValue();var options=document.createDocumentFragment();for(var i=0;i<results.length;i++){var result=results[i];var option=this._optionBuilder.createOption(result,searchValue,i);options.appendChild(option);}
this._panel.setOptions(options);this.focus();}},selectHandler:function(selected){if(selected){this._hasSelection=true;this.setOption(selected);if(Mojo.Util.isFunction(this._selectEventHandler)){this._selectEventHandler(selected);}
this.fireEvent(new MDSS.Event(MDSS.Event.AFTER_SELECTION,{selected:selected}));}
this.hide();},keyHandler:function(oData){var value=this.getValue();if(oData.keyCode==9||oData.keyCode==39||oData.keyCode==37){}
else
{this._hasSelection=false;if(oData.keyCode===40){var visible=this._panel.isVisible();YAHOO.util.Event.preventDefault(oData);if(!visible||!this._panel.hasOptions()){if(this._hasChanged){this.performSearch(value);}
else{this.performSearch('');}}
else{this._panel.selectNext();}}
else if(oData.keyCode===38){var visible=this._panel.isVisible();YAHOO.util.Event.preventDefault(oData);if(!visible||!this._panel.hasOptions()){if(this._hasChanged){this.performSearch(value);}
else{this.performSearch('');}}
else{this._panel.selectPrevious();}}
else if(oData.keyCode===27){this.hide();}
else if(oData.keyCode===13){this._panel.selectCurrent();}
else{if(this._isDifferent(value)){this._setCurrentValue(value);}
if(value.length>=this.minLength){this.performSearch(value);}}}},forceSearch:function(){var value=this.getValue();this.performSearch(value);},fireEvent:function(event){for(var i=0;i<this.listeners.length;i++){this.listeners[i](event);}},performSearch:function(value){var parameters=this.getParameters();this.fireEvent(new MDSS.Event(MDSS.Event.BEFORE_SEARCH,{value:value,autocomplete:this}));this._dataSource.getResults(value,parameters);},setElementValue:function(element,value){if(element){if(value){element.value=value;}
else{element.value='';}}},_isDifferent:function(value){return(this._value!=value);},_setCurrentValue:function(value){this.resetSelected();this.value=value;this._hasChanged=true;},resetCache:function(){this._dataSource.resetCache();},focus:function(){IsAbstract:true},getValue:function(){IsAbstract:true},setOption:function(selected){IsAbstract:true},resetSelected:function(){IsAbstract:true}}});Mojo.Meta.newClass('MDSS.GenericSearch',{Extends:MDSS.AutoComplete,Instance:{initialize:function(displayElement,concreteElement,listFunction,displayFunction,idFunction,searchFunction,selectEventHandler,prop){this._displayElement=Mojo.Util.isString(displayElement)?document.getElementById(displayElement):displayElement;this._concreteElement=Mojo.Util.isString(concreteElement)?document.getElementById(concreteElement):concreteElement;if(this._displayElement!=null)
{var dataSource=new MDSS.DataSource(this,searchFunction);var optionBuilder=new MDSS.OptionBuilder(listFunction,displayFunction,idFunction);var panel=new MDSS.ResultPanel(this,this._displayElement);this.$initialize(dataSource,optionBuilder,panel,selectEventHandler,prop);this._displayElement.setAttribute("autocomplete","off");YAHOO.util.Event.on(this._displayElement,'keypress',this.preventFormSubmit,null,this);YAHOO.util.Event.on(this._displayElement,'keyup',this.keyHandler,this,this);}},getDisplayElement:function(){return this._displayElement;},getConcreteElement:function(){return this._concreteElement;},focus:function(){this.getDisplayElement().focus();},getValue:function(){return this._displayElement.value;},setOption:function(selected){this.setElementValue(this.getDisplayElement(),selected.label);this.setElementValue(this.getConcreteElement(),selected.id);},resetSelected:function(){this.setElementValue(this.getConcreteElement(),'');}},Static:{createYearSearch:function(element){element=Mojo.Util.isString(element)?document.getElementById(element):element;var years=new Array();var d=new Date();var year=d.getFullYear();for(var i=0;i<10;i++){years.push(year+'');year--;}
var listFunction=function(valueObject){return valueObject;};var idFunction=function(valueObject){};var displayFunction=function(valueObject){return valueObject;};var searchFunction=function(request,value){var filtered=[];for(var i=0;i<years.length;i++){var year=years[i];if(year.search(value)!==-1||value===''){filtered.push(year);}}
var query={resultSet:filtered,getResultSet:function(){return this.resultSet;}};request.onSuccess(query);};var selectEventHandler=function(){};var search=new MDSS.GenericSearch(element,null,listFunction,displayFunction,idFunction,searchFunction,selectEventHandler,{minLength:0});YAHOO.util.Event.on(element,'focus',search.forceSearch,search,search);return search;}}});Mojo.Meta.newClass('MDSS.MultiInputAutoComplete',{Extends:MDSS.AutoComplete,Instance:{initialize:function(prop){this._concreteElement=Mojo.Util.isString(prop.concrete)?document.getElementById(prop.concrete):prop.concrete;this._elements=[];this._focusElement=null;if(Mojo.Util.isArray(prop.elements)){for(var i=0;i<prop.elements.length;i++){this._initializeElement(prop.elements[i]);}}
else{this._initializeElement(prop.elements);}
if(this._elements.length>0){var dataSource=new MDSS.DataSource(this,prop.search);var optionBuilder=new MDSS.OptionBuilder(prop.list,prop.display,prop.id);var panel=new MDSS.ResultPanel(this,this._elements[0]);this.$initialize(dataSource,optionBuilder,panel,prop.selectEventHandler,prop);}},_initializeElement:function(elementId){var element=Mojo.Util.isString(elementId)?document.getElementById(elementId):elementId;this._elements.push(element);element.setAttribute("autocomplete","off");YAHOO.util.Event.on(element,'keypress',this.preventFormSubmit,null,this);YAHOO.util.Event.on(element,'keyup',this.keyHandler,this,this);},keyHandler:function(oData){var target=oData.originalTarget;this._focusElement=target;this.$keyHandler(oData);},focus:function(){if(this._focusElement){this._focusElement.focus();}},getValue:function(){var value="";for(var i=0;i<this._elements.length;i++){var element=this._elements[i];value+=element.value+" ";}
return value.replace(/^\s+|\s+$/g,"");},setOption:function(selected){for(var i=0;i<this._elements.length;i++){var element=this._elements[i];var id=element.id;var label=selected.label[id];this.setElementValue(element,label);}
this.setElementValue(this._concreteElement,selected.id);},resetSelected:function(){this.setElementValue(this._concreteElement,'');}}});Mojo.Meta.newClass("MDSS.OntologyBrowser",{Constants:{ENTRY_SUFFIX:'_entry',BREADCRUMB_SUFFIX:'_breadcrumb',SELECT_SUFFIX:'_select',DELETE_SUFFIX:'_select',SELECTION_SUFFIX:'_selection'},Instance:{initialize:function(multipleSelect)
{this._cache={};this._childCache={};this._searchCache={};this._multipleSelect=multipleSelect||false;if(arguments.length===1)
{this._defaultRoot=true;}
else if(arguments.length==2)
{this._mdAttributeId=arguments[1];}
else if(arguments.length===3&&arguments[1]===true)
{this._universalType=arguments[2];}
else
{this._className=arguments[1];this._attributeName=arguments[2];}
this._rendered=false;this._panel=null;this._id=new String(Math.random()).substring(2);this._breadcrumbId=this._id+'_breadcrumb';this._contentId=this._id+'_contentId';this._selectionId=this._id+'_selection';this._backButton=this._id+'_back';this._saveButton=this._id+'_save';this._cancelButton=this._id+'_cancel';this._searchInput=this._id+'_search';this._ROOT="ROOT";this._selection={};this._breadcrumbs=[];this._customHandler=null;this._searchPanel=null;this._currentParents=[];},show:function()
{this._panel.show();this._panel.bringToTop();this._focusSearch();},getDisplay:function(termId)
{if(this._cache[termId])
{return this.constructor.formatLabelFromValueObject(this._cache[termId]);}
else
{return null;}},_focusSearch:function()
{var search=document.getElementById(this._searchInput);search.value='';search.focus();},hide:function()
{this._panel.hide();},_setContent:function(views)
{this._currentParents=[];var nodes=Mojo.Iter.map(views,function(view){this._currentParents.push(view.getTermId());return this._createTermEntry(view);},this);var content=document.getElementById(this._contentId);content.innerHTML=nodes.join('');},_getRootContent:function()
{var cached=this._getCachedChildren(this._ROOT);if(cached)
{this._setContent(cached);this._focusSearch();}
else
{var request=new MDSS.Request({that:this,onSuccess:function(roots)
{this.that._setCachedChildren(this.that._ROOT,roots);this.that._setContent(roots);this.that._focusSearch();}});if(this._defaultRoot)
{Mojo.$.dss.vector.solutions.ontology.BrowserRoot.getDefaultRoot(request);}
else if(this._universalType)
{Mojo.$.dss.vector.solutions.ontology.BrowserRoot.getDefaultGeoRoots(request,this._universalType);}
else if(this._mdAttributeId)
{Mojo.$.dss.vector.solutions.ontology.BrowserRoot.getAttributeRoots(request,'',this._mdAttributeId);}
else
{Mojo.$.dss.vector.solutions.ontology.BrowserRoot.getAttributeRoots(request,this._className,this._attributeName);}}},_setCachedChildren:function(parentId,childViews)
{var children=Mojo.Iter.map(childViews,function(view){var toCache=view;if(view instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{var nView=new Mojo.$.dss.vector.solutions.ontology.TermView();nView.setTermName(view.getTermName());nView.setTermId(view.getTermId());nView.setTermOntologyId(view.getTermOntologyId());toCache=nView;}
this._cacheSet(view.getTermId(),toCache);return view;},this);this._childCache[parentId]=children;},_getCachedChildren:function(parentId)
{return this._childCache[parentId];},_cacheSet:function(termId,view)
{this._cache[termId]=view;},_cacheGet:function(termId)
{return this._cache[termId];},_doTermSelectHandler:function(e)
{var el=e.target;if(el.nodeName==='SPAN')
{var termId=el.id.replace(this.constructor.ENTRY_SUFFIX,'');this._doTermSelect(termId);}
else if(el.nodeName==='IMG')
{var termId=el.id.replace(this.constructor.SELECT_SUFFIX,'');this._addToSelection(termId);if(!this.isMultiSelect())
{this._save();}}},_addToSelection:function(termId)
{if(this._selection[termId])
{this.hide();return;}
var selection=document.getElementById(this._selectionId);if(!this._multipleSelect)
{this._selection={};selection.innerHTML='';}
this._selection[termId]=this._cacheGet(termId);var li=this._getSelectionLi(termId);selection.appendChild(li);var divParent=selection.parentNode;var sHeight=divParent.scrollHeight;var oHeight=divParent.offsetHeight;if(sHeight>oHeight)
{divParent.scrollTop=sHeight-oHeight;}
if(!this.isMultiSelect())
{}},_doTermSelect:function(termId,dontAdd)
{var cached=this._getCachedChildren(termId);if(cached)
{this._setContent(cached);}
else
{var request=new MDSS.Request({that:this,termId:termId,dontAdd:dontAdd,onSuccess:function(query)
{var results=query.getResultSet();this.that._setCachedChildren(this.termId,results);this.that._setContent(results);}});Mojo.$.dss.vector.solutions.ontology.Term.getOntologyChildren(request,termId,true);}
if(!dontAdd)
{var term=this._cacheGet(termId);this._breadcrumbs.push(term);}
this._resetBreadcrumbs();},_getSelectionLi:function(termId)
{var term=this._cacheGet(termId);var imgId=termId+this.constructor.DELETE_SUFFIX;var liId=termId+this.constructor.SELECTION_SUFFIX;var img='<img src="imgs/icons/delete.png" style="margin-right: 5px" id="'+imgId+'" />';var li=document.createElement('li');li.id=liId;var label=this._displayFunction(term);li.innerHTML=img+label;return li;},newSpan:function(content,id)
{return'<span id="'+id+'" class="linkify">'+content+'</span>';},_resetBreadcrumbs:function()
{var el=document.getElementById(this._breadcrumbId);var breadcrumbs=this._getBreadcrumbs();el.innerHTML=breadcrumbs;var divParent=el.parentNode;var sHeight=divParent.scrollHeight;var oHeight=divParent.offsetHeight;if(sHeight>oHeight)
{divParent.scrollTop=sHeight-oHeight;}},_getBreadcrumbs:function()
{var last=this._breadcrumbs.length-1;var breadcrumbs=Mojo.Iter.map(this._breadcrumbs,function(breadcrumb,ind){var span=this.newSpan(breadcrumb.getTermName(),breadcrumb.getTermId()+this.constructor.BREADCRUMB_SUFFIX);return'<li class="breadcrumbNavItem'+(ind===last?' currentBreadcrumbsNav':'')+'">'+span+'</li>';},this);return breadcrumbs.join('<li class="termDelimeter">/</li>');},render:function()
{var rootView=new Mojo.$.dss.vector.solutions.ontology.TermView();rootView.setTermId(this._ROOT);rootView.setTermName(MDSS.Localized.ROOT);rootView.setTermOntologyId(this._ROOT);this._breadcrumbs.push(rootView);this._panel=new YAHOO.widget.Panel(this._id,{width:'400px',height:'400px',fixedcenter:true,close:false,draggable:false,zindex:4,modal:true,visible:true});var html='';html+='<div class="browserWrapper">';html+='  <div class="browserBack">';html+='     <button id="'+this._backButton+'">&larr;</button>';html+='  </div>';html+='  <div class="browserBreadcrumbs autosize">';html+='    <div>';html+='    <div class="browserResizeRestrict">';html+='    <ul class="breadcrumbsNav" id="'+this._breadcrumbId+'">';html+=this._getBreadcrumbs();html+='    </ul>';html+='    </div>';html+='    </div>';html+='  </div>';html+='  <div class="browserSearch">';html+='    <span>'+MDSS.Localized.Search+':</span>';html+='    <input type="text" id="'+this._searchInput+'" class="browserSearchInput" />';html+='  </div>';html+='  <div class="browserContent">';html+='    <ul class="currentNodes" id="'+this._contentId+'">';html+='    </ul>';html+='  </div>';html+='  <div class="browserSelection">';html+='    <ul class="selectedNodes" id="'+this._selectionId+'">';html+='    </ul>';html+='  </div>';html+='  <div class="browserButtons">';html+='    <input type="button" id="'+this._cancelButton+'" class="browserCancelButton" value="'+MDSS.Localized.Cancel+'" />';html+='    <input type="button" id="'+this._saveButton+'" class="browserSaveButton" value="'+MDSS.Localized.save+'" />';html+='  </div>';html+='</div>';this._panel.setBody(html);this._panel.render(document.body);this._panel.bringToTop();this._hookEvents();this._attachSearch();this._getRootContent();this._rendered=true;},_displayFunction:function(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return MDSS.OntologyBrowser.formatLabelFromView(valueObject);}
return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);},_listFunction:function(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView)
{var termId=valueObject.getTermId();this._searchCache[termId]=valueObject;return MDSS.OntologyBrowser.formatLabelFromView(valueObject);}
else
{var termId=valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);this._searchCache[termId]=valueObject;return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);}},_idFunction:function(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return valueObject.getTermId();}
return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);},_selectEventHandler:function(selected)
{var termId=selected.id;this._cache[termId]=this._searchCache[termId];this._searchCache={};document.getElementById(this._searchInput).value='';this._addToSelection(termId);if(!this.isMultiSelect())
{this._save();}},_searchFunction:function(request,value)
{Mojo.$.dss.vector.solutions.ontology.Term.termQuery(request,value,this._currentParents);},_attachSearch:function()
{var displayElement=document.getElementById(this._searchInput);var dF=Mojo.Util.bind(this,this._displayFunction);var iF=Mojo.Util.bind(this,this._idFunction);var lF=Mojo.Util.bind(this,this._listFunction);var sF=Mojo.Util.bind(this,this._searchFunction);var sEH=Mojo.Util.bind(this,this._selectEventHandler);this._searchPanel=new MDSS.GenericSearch(displayElement,null,lF,dF,iF,sF,sEH);var that=this;this._panel.subscribe('beforeHide',function(){that._searchPanel.hide();});},isRendered:function()
{return this._rendered;},reset:function()
{this._breadcrumbs=this._breadcrumbs.slice(0,1);this._resetToDefault();this._resetSelection();},setSelection:function(termIds)
{var toFetch=[];var cached=[];Mojo.Iter.forEach(termIds,function(termId){var term=this._cacheGet(termId);if(term)
{cached.push(term);}
else
{toFetch.push(termId);}},this);if(toFetch.length>0)
{var request=new MDSS.Request({that:this,cached:cached,onSuccess:function(query)
{var views=query.getResultSet();var total=this.cached.concat(views);total.sort(function(term1,term2){var t1=term1.getTermName();var t2=term2.getTermName();if(t1>t2)
{return 1;}
else if(t1<t2)
{return-1;}
else
{return 0;}});Mojo.Iter.forEach(total,function(valueObject){var termId=valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);this._cacheSet(termId,valueObject);this._addToSelection(termId);},this.that);}});Mojo.$.dss.vector.solutions.ontology.Term.getByIds(request,toFetch);}
else
{Mojo.Iter.forEach(cached,function(view){this._addToSelection(this._idFunction(view));},this);}},_resetSelection:function()
{this._selection={};var selection=document.getElementById(this._selectionId);selection.innerHTML='';},isMultiSelect:function(){return this._multipleSelect;},setHandler:function(handler,context)
{this._customHandler=Mojo.Util.bind(context||this,handler);},_save:function()
{if(Mojo.Util.isFunction(this._customHandler));{var selected=Mojo.Util.getValues(this._selection);this._customHandler(selected);}
this.hide();},_hookEvents:function()
{var Event=YAHOO.util.Event;Event.on(this._breadcrumbId,'click',this._doNavigate,null,this);Event.on(this._contentId,'click',this._doTermSelectHandler,null,this);Event.on(this._selectionId,'click',this._doSelectionAction,null,this);Event.on(this._backButton,'click',this._goBack,null,this);Event.on(this._saveButton,'click',this._save,null,this);Event.on(this._cancelButton,'click',this.hide,null,this);},_createTermEntry:function(view)
{var li;if(view instanceof Mojo.$.dss.vector.solutions.ontology.TermView||view.getSelectable())
{var imgId=this._idFunction(view)+this.constructor.SELECT_SUFFIX;li='<li><img src="imgs/icons/add.png" style="margin-right: 5px;position:relative; top:3px;" id="'+imgId+'" />';}
else
{li='<li style="padding-left: 21px">';}
var content=this._displayFunction(view);return li+this.newSpan(content,this._idFunction(view)+this.constructor.ENTRY_SUFFIX)+'</li>';},_goBack:function(e)
{var size=this._breadcrumbs.length;if(size===1)
{return;}
this._breadcrumbs.pop();size=this._breadcrumbs.length;if(size===1)
{this._resetToDefault();}
else
{var termId=this._breadcrumbs[size-1].id;this._doTermSelect(termId,true);}},_doNavigate:function(e)
{var el=e.target;if(el.nodeName!=='SPAN')
{return;}
var termId=el.id.replace(this.constructor.BREADCRUMB_SUFFIX,'');var isRoot=false;var newEndInd;if(termId===this._ROOT)
{newEndInd=1;isRoot=true;}
else
{Mojo.Iter.forEach(this._breadcrumbs,function(breadcrumb,ind){if(breadcrumb.getTermId()===termId)
{newEndInd=ind;}},this);}
this._breadcrumbs=this._breadcrumbs.slice(0,newEndInd);if(isRoot)
{this._resetToDefault();}
else
{this._doTermSelect(termId);}},_resetToDefault:function()
{this._getRootContent();this._resetBreadcrumbs();},_doSelectionAction:function(e)
{var el=e.target;if(el.nodeName!=='IMG')
{return;}
var termId=el.id.replace(this.constructor.DELETE_SUFFIX,'');delete this._selection[termId];var li=el.parentNode;li.parentNode.removeChild(li);},isRendered:function()
{return this._rendered;}},Static:{formatLabelFromView:function(term)
{return MDSS.OntologyBrowser.formatLabel(term.getTermName(),term.getTermOntologyId());},formatLabelFromValueObject:function(valueObject)
{var displayLabel=valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.DISPLAY);var termId=valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.TERMID);return MDSS.OntologyBrowser.formatLabel(displayLabel,termId);},formatLabel:function(label,termId){return label;},extractName:function(html)
{html=Mojo.Util.trim(html);html=html.replace(/ \(.*?\)$/,'');return html;}}});Mojo.Meta.newClass("MDSS.OntologyValidator",{Instance:{initialize:function(attributeName,search,getParameters,setField){this._attributeEl=document.getElementById(attributeName);this._displayEl=document.getElementById(attributeName+'Display');this._button=document.getElementById(attributeName+'Btn');this._search=search;this._getParameters=getParameters;this.setField=setField;YAHOO.util.Event.on(this._displayEl,"blur",this._blurHandler,this,this);},_blurHandler:function(e){if(e){var blurEl=e.explicitOriginalTarget||document.activeElement;var ul=YAHOO.util.Dom.getAncestorByClassName(blurEl,"selectableList")
if(ul){return;}
this._validateSelection();}},_validateSelection:function(){var termId=this._displayEl.value;var concreteId=this._attributeEl.value;if((termId!=null&&termId!='')&&(concreteId==null||concreteId=='')){var parameters=this._getParameters();var request=this._getValidationRequest();Mojo.$.dss.vector.solutions.ontology.Term.getTermById(request,termId,parameters);}
else{MDSS.Calendar.removeError(this._button);}},_getValidationRequest:function(){var request=new MDSS.Request({that:this,onSend:function(){},onComplete:function(){},onFailure:function(e){MDSS.Calendar.removeError(this.that._button);MDSS.Calendar.addError(this.that._button,e.getMessage());},onProblemExceptionDTO:function(e){MDSS.Calendar.removeError(this.that._button);var problems=e.getProblems();for(var i=0;i<problems.length;i++){var problem=problems[i];MDSS.Calendar.addError(this.that._button,problem.getMessage());}},onSuccess:function(view){MDSS.Calendar.removeError(this.that._button);if(view!=null){this.that.setField([view]);this.that._search.hide();}}});return request;},}});Mojo.Meta.newClass("MDSS.GenericOntologyBrowser",{Instance:{initialize:function(className,configs){var config=Mojo.Util.isArray(configs)&&configs.length>0?configs[0]:configs;this._attributeName=config.attributeName;this._attributeClass=Mojo.Util.isString(config.className)?config.className:className;this._browserField=Mojo.Util.isString(config.browserField)?config.browserField:config.attributeName;this._enabled=Mojo.Util.isBoolean(config.enabled)?config.enabled:true;this._attributeEl=document.getElementById(this._attributeName);this._displayEl=document.getElementById(this._attributeName+'Display');this._button=document.getElementById(this._attributeName+'Btn');this._roots=[];this._browser=new MDSS.OntologyBrowser(false,this._attributeClass,this._browserField);this._browser.setHandler(Mojo.Util.bind(this,this.setField));if(this._enabled){YAHOO.util.Event.on(this._button,"click",this.openBrowser,{browser:this._browser,attributeName:this._attributeName});}
var dF=Mojo.Util.bind(this,this._displayFunction);var iF=Mojo.Util.bind(this,this._idFunction);var lF=Mojo.Util.bind(this,this._displayFunction);var sF=Mojo.Util.bind(this,this._searchFunction);var selF=Mojo.Util.bind(this,this._selectFunction);this._search=new MDSS.GenericSearch(this._displayEl,this._attributeEl,lF,dF,iF,sF,selF);var gP=Mojo.Util.bind(this,this._getParameters);var sF=Mojo.Util.bind(this,this.setField);new MDSS.OntologyValidator(this._attributeName,this._search,gP,sF);},addRoot:function(root){this._roots.push(root);},_getParameters:function(){return[this._attributeClass,this._attributeName];},_searchFunction:function(request,value){if(this._roots.length>0){Mojo.$.dss.vector.solutions.ontology.Term.searchByRoots(request,value,this._roots);}
else{var parameters=this._getParameters();Mojo.$.dss.vector.solutions.ontology.Term.termQueryWithRoots(request,value,parameters);}},_selectFunction:function(){MDSS.Calendar.removeError(this._button);},setField:function(selected){if(selected.length>0){var sel=selected[0];this._attributeEl.value=this._idFunction(sel);this._displayEl.value=this._displayFunction(sel);}
else
{this._attributeEl.value='';this._displayEl.value='';}
MDSS.Calendar.removeError(this._button);},openBrowser:function(e,config){var browser=config.browser;var selected=[];var attributeName=config.attributeName;var termId=document.getElementById(attributeName).value;if(termId!=null&&termId!=='')
{selected.push(termId);}
if(browser.isRendered()){browser.show();browser.reset();}
else{browser.render();}
browser.setSelection(selected);},_displayFunction:function(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return MDSS.OntologyBrowser.formatLabelFromView(valueObject);}
return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);},_idFunction:function(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return valueObject.getTermId();}
return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);},_attachSearch:function(attributeElement,displayElement,searchFunction)
{}}});Mojo.Meta.newClass("MDSS.GenericMultiOntologyBrowser",{Instance:{initialize:function(className,config){this.attributeName=config.attributeName;this.attributeClass=Mojo.Util.isString(config.className)?config.className:className;this.browserField=Mojo.Util.isString(config.browserField)?config.browserField:config.attributeName;this.enabled=Mojo.Util.isBoolean(config.enabled)?config.enabled:true;this.index=-1;this.map={};this._roots=[];this.browser=new MDSS.OntologyBrowser(true,this.attributeClass,this.browserField);this.browser.setHandler(Mojo.Util.bind(this,this.setField));if(this.enabled==true){YAHOO.util.Event.on(this.attributeName+'Btn',"click",this.openBrowser,this,this);}
this.attributeElement=document.getElementById(this.attributeName);var dF=Mojo.Util.bind(this,this._displayFunction);var iF=Mojo.Util.bind(this,this._idFunction);var lF=Mojo.Util.bind(this,this._displayFunction);var sF=Mojo.Util.bind(this,this._searchFunction);var sH=Mojo.Util.bind(this,this._selectionHandler);var search=new MDSS.GenericSearch(this.attributeElement,null,lF,dF,iF,sF,sH);},setField:function(selected){var resultEl=document.getElementById(this.attributeName+'ResultList');if(selected.length>0){this.map={};var innerHTML='';for(var i=0;i<selected.length;i++){var label=this._displayFunction(selected[i]);var id=this._idFunction(selected[i]);innerHTML+=this._getInnerHTML(i,label,id);this.map[id]=label;}
resultEl.innerHTML=innerHTML;this.index=selected.length;}
else{resultEL.innerHTML='';this.index=0;this.map={};}},openBrowser:function(e){var browser=this.browser;var selected=[];var attributeName=this.attributeName;var terms=YAHOO.util.Selector.query('.'+attributeName);for(var i=0;i<terms.length;i++){var termId=terms[i].value;if(termId!=null&&termId!==''){selected.push(termId);}}
if(browser.isRendered()){browser.show();browser.reset();}
else{browser.render();}
browser.setSelection(selected);},_nextTermNumber:function()
{return++this.index;},addRoot:function(root){this._roots.push(root);},addSelection:function(label,id)
{if(this.map[id]==null)
{var resultEl=document.getElementById(this.attributeName+'ResultList');var index=this._nextTermNumber();var innerHTML=this._getInnerHTML(index,label,id);resultEl.innerHTML+=innerHTML;this.map[id]=label;}},_getInnerHTML:function(index,label,id)
{var component=this.attributeName+'_'+index;innerHTML='<li>\n';innerHTML+='<input type="hidden" class="'+this.attributeName+'" name="'+component+'.componentId" value="'+id+'" />\n';innerHTML+='<input type="hidden" name="'+component+'.isNew" value="false" />\n';innerHTML+=label+'\n';innerHTML+='<li>\n';return innerHTML;},_displayFunction:function(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return MDSS.OntologyBrowser.formatLabelFromView(valueObject);}
return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);},_idFunction:function(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return valueObject.getTermId();}
return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);},_searchFunction:function(request,value)
{if(this._roots.length>0){Mojo.$.dss.vector.solutions.ontology.Term.searchByRoots(request,value,this._roots);}
else{var parameters=[this.attributeClass,this.browserField];Mojo.$.dss.vector.solutions.ontology.Term.termQueryWithRoots(request,value,parameters);}},_selectionHandler:function(selection)
{this.addSelection(selection.label,selection.id);this.attributeElement.value='';}}});YAHOO.widget.OntologyTermEditor=function(oConfigs){this._sId=Mojo.Util.generateId();this._klass=oConfigs.klass;this._attribute=oConfigs.attribute;var attributeName=this._attribute.substring(0,1).toLowerCase()+this._attribute.substring(1);this._browser=new MDSS.OntologyBrowser(false,this._klass,attributeName);this._browser.setHandler(this._setSelected,this);this._lastSelected=null;oConfigs.disableBtns=false;YAHOO.widget.OntologyTermEditor.superclass.constructor.call(this,"ontology",oConfigs);};YAHOO.lang.extend(YAHOO.widget.OntologyTermEditor,YAHOO.widget.BaseCellEditor,{_setSelected:function(views)
{this._lastSelected=views.length>0?views[0]:null;this.save();},renderForm:function(){this.getContainerEl().innerHTML='';},handleDisabledBtns:function(){},resetForm:function(){this._lastSelected=null;this.getContainerEl().innerHTML='';},focus:function(){if(this._browser.isRendered())
{this._browser.reset();this._browser.show();}
else
{this._browser.render();}
this.tableData=this.getDataTable().tableData;if(this.tableData)
{var row=this.getDataTable().getTrIndex(this.getRecord());var column=this.getColumn().getField();var termId=this.tableData.rows[row][column];var selected=[];if(Mojo.Util.isString(termId)&&termId!==''){selected.push(termId);}
this._browser.setSelection(selected);}},getInputValue:function(){if(this._lastSelected!=null)
{if(this._lastSelected instanceof Mojo.$.dss.vector.solutions.ontology.TermView||this._lastSelected instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return this._lastSelected.getTermId();}
return this._lastSelected.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);}
return'';},getInputDisplayLabel:function(){if(this._lastSelected!=null)
{if(this._lastSelected instanceof Mojo.$.dss.vector.solutions.ontology.TermView||this._lastSelected instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return MDSS.OntologyBrowser.formatLabelFromView(this._lastSelected);}
return MDSS.OntologyBrowser.formatLabelFromValueObject(this._lastSelected);}
return'';}});YAHOO.lang.augmentObject(YAHOO.widget.OntologyTermEditor,YAHOO.widget.BaseCellEditor);Mojo.Meta.newClass("MDSS.OntologyFields",{Constants:{DIV_ID:"ontologyFields",TABLE_SUFFIX:"_table",ROW_SUFFIX:"_row",TERM_SUFFIX:"_term",TERMNAME_SUFFIX:"_termName",DEFAULT_DISPLAY_SUFFIX:"_defaultTermDisplay",DEFAULT_TERM_SUFFIX:"_defaultTerm",DEFAULT_TERM_BUTTON_SUFFIX:"_defaultTermBtn",},Instance:{initialize:function()
{YAHOO.util.Event.on(this.constructor.DIV_ID,'click',this._handleClick,null,this);this._currentModal=null;this._currentRootInput='term';this._currentRootDisplay='termDisplay';this._currentRootBtn='termBtn';this._rootBrowser=new MDSS.OntologyBrowser(false);this._rootBrowser.setHandler(this._setField,this);this._rootSearch=null;this._currentDefaultInput=null;this._currentDefaultDisplay=null;this._rootController=Mojo.$.dss.vector.solutions.ontology.BrowserRootController;var cancelB=Mojo.Util.bind(this,this._cancelRoot);this._rootController.setCancelListener(cancelB);var updateB=Mojo.Util.bind(this,this._updateRoot);this._rootController.setUpdateListener(updateB);var defaultTerms=YAHOO.util.Selector.query('div.defaultFieldTerm span');;Mojo.Iter.forEach(defaultTerms,function(defaultTerm){var mdAttributeId=defaultTerm.id.replace(this.constructor.DEFAULT_TERM_BUTTON_SUFFIX,'');var browser=new MDSS.OntologyBrowser(false,mdAttributeId);browser.setHandler(this._setDefault,this);var obj={browser:browser,mdAttributeId:mdAttributeId};YAHOO.util.Event.on(defaultTerm,'click',this._openDefaultBrowser,obj,this);var dF=Mojo.Util.bind(this,this._displayFunction);var iF=Mojo.Util.bind(this,this._idFunction);var lF=Mojo.Util.bind(this,this._displayFunction);var sF=Mojo.Util.bind(this,this._defaultSearch,mdAttributeId);var sEH=Mojo.Util.bind(this,this._selectEventHandler,mdAttributeId);var display=mdAttributeId+this.constructor.DEFAULT_DISPLAY_SUFFIX;var input=mdAttributeId+this.constructor.DEFAULT_TERM_SUFFIX;var search=new MDSS.GenericSearch(display,input,lF,dF,iF,sF,sEH);search.disableCache();var _getter=Mojo.Util.bind(this,this._getDefaultParameters,mdAttributeId);var _setter=Mojo.Util.bind(this,this._setDefault);new MDSS.OntologyValidator(input,search,_getter,_setter);},this);},_selectEventHandler:function(mdAttributeId,selected)
{var view=new Mojo.$.dss.vector.solutions.ontology.FieldDefaultView();view.setMdAttribute(mdAttributeId);var request=new MDSS.Request({onSuccess:function(){}});view.setDefaultValue(selected.id);view.applyDefaultValue(request);MDSS.Calendar.removeError(document.getElementById(mdAttributeId+this.constructor.DEFAULT_TERM_BUTTON_SUFFIX));},_getDefaultParameters:function(mdAttributeId)
{return[null,mdAttributeId];},_defaultSearch:function(mdAttributeId,request,value)
{var params=[null,mdAttributeId];Mojo.$.dss.vector.solutions.ontology.Term.termQueryWithRoots(request,value,params);},_displayFunction:function(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return MDSS.OntologyBrowser.formatLabelFromView(valueObject);}
return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);},_idFunction:function(valueObject)
{if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView||valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
{return valueObject.getTermId();}
return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);},_searchFunction:function(request,value)
{var params=[null,null];Mojo.$.dss.vector.solutions.ontology.Term.termQueryWithRoots(request,value,params);},_createModal:function(html,closeWin)
{var modal=new YAHOO.widget.Panel("select",{width:"400px",height:"400px",fixedcenter:true,close:closeWin||false,draggable:false,zindex:4,modal:true,visible:true});modal.setBody(html);modal.render(document.body);modal.bringToTop();var dF=Mojo.Util.bind(this,this._displayFunction);var iF=Mojo.Util.bind(this,this._idFunction);var lF=Mojo.Util.bind(this,this._displayFunction);var sF=Mojo.Util.bind(this,this._searchFunction);this._rootSearch=new MDSS.GenericSearch(this._currentRootDisplay,this._currentRootInput,lF,dF,iF,sF);this._rootSearch.disableCache();YAHOO.util.Event.on(this._currentRootBtn,'click',this._openBrowser,null,this);return modal;},_handleClick:function(e)
{var el=e.target;var Dom=YAHOO.util.Dom;if(Dom.hasClass(el,'addRootBtn'))
{var fieldId=el.value;this._addRoot(fieldId);}
else if(Dom.hasClass(el,'editRootBtn'))
{var rootId=el.value;this._editRoot(rootId);}
else if(Dom.hasClass(el,'deleteRootBtn'))
{var rootId=el.value;this._deleteRoot(rootId);}},_openDefaultBrowser:function(e,obj)
{var browser=obj.browser;var mdAttributeId=obj.mdAttributeId;this._currentDefaultDisplay=mdAttributeId+this.constructor.DEFAULT_DISPLAY_SUFFIX;this._currentDefaultInput=mdAttributeId+this.constructor.DEFAULT_TERM_SUFFIX;this._launchBrowser(browser,this._currentDefaultInput);},_openBrowser:function()
{this._launchBrowser(this._rootBrowser,this._currentRootInput);},_launchBrowser:function(browser,termInputId)
{var termId=document.getElementById(termInputId).value;var selected=[];if(termId!=='')
{selected.push(termId);}
if(browser.isRendered())
{browser.reset();browser.show();}
else
{browser.render();}
browser.setSelection(selected);},_setDefault:function(selected)
{var mdAttributeId=this._currentDefaultInput.replace(this.constructor.DEFAULT_TERM_SUFFIX,'');var view=new Mojo.$.dss.vector.solutions.ontology.FieldDefaultView();view.setMdAttribute(mdAttributeId);var request=new MDSS.Request({onSuccess:Mojo.Util.bind(this,this._setInput,selected,this._currentDefaultInput,this._currentDefaultDisplay)});if(selected.length>0)
{var sel=selected[0];var termId=this._idFunction(sel);view.setDefaultValue(termId);view.applyDefaultValue(request);}
else
{view.deleteConcrete(request);}},_setField:function(selected)
{this._setInput(selected,this._currentRootInput,this._currentRootDisplay);},_setInput:function(selected,inputId,displayId)
{var el=document.getElementById(inputId);var dEl=document.getElementById(displayId);if(selected.length>0)
{var sel=selected[0];el.value=this._idFunction(sel);dEl.value=this._displayFunction(sel);}
else
{el.value='';dEl.value='';}},_cancelRoot:function(params)
{if(params['dto.isNew']==='true')
{this._currentModal.destroy();}
else
{var request=new MDSS.Request({that:this,onSuccess:function()
{this.that._currentModal.destroy();}});var id=params['dto.componentId'];Mojo.$.dss.vector.solutions.ontology.BrowserRoot.unlock(request,id);}},_addRoot:function(fieldId)
{var request=new MDSS.Request({that:this,onSuccess:function(html)
{var executable=MDSS.util.extractScripts(html);var html=MDSS.util.removeScripts(html);this.that._currentModal=this.that._createModal(html);eval(executable);}});var createB=Mojo.Util.bind(this,this._createRoot,fieldId);this._rootController.setCreateListener(createB);this._rootController.newInstance(request);},_editRoot:function(rootId)
{var request=new MDSS.Request({that:this,onSuccess:function(html)
{var executable=MDSS.util.extractScripts(html);var html=MDSS.util.removeScripts(html);this.that._currentModal=this.that._createModal(html);eval(executable);}});this._rootController.edit(request,rootId);},_updateRoot:function(params)
{var request=new MDSS.Request({that:this,onSuccess:function(rootView)
{var tr=document.getElementById(rootView.getBrowserRootId()+MDSS.OntologyFields.ROW_SUFFIX);this.that._populateRow(tr,rootView);this.that._currentModal.destroy();}});var id=params['dto.componentId'];var browserRoot=this._populateRoot(params);Mojo.$.dss.vector.solutions.ontology.BrowserRoot.update(request,id,browserRoot);},_deleteRoot:function(rootId)
{var request=new MDSS.Request({rootId:rootId,onSuccess:function()
{var tr=document.getElementById(rootId+MDSS.OntologyFields.ROW_SUFFIX);var table=tr.offsetParent;table.deleteRow(tr.rowIndex);}});Mojo.Facade.deleteEntity(request,rootId);},_populateRoot:function(params)
{var termId=params['dto.term'];var selectable=params['dto.selectable'];var browserRoot=new Mojo.$.dss.vector.solutions.ontology.BrowserRoot();browserRoot.setTerm(termId);browserRoot.setSelectable(selectable);return browserRoot;},_populateRow:function(tr,rootView)
{tr.id=rootView.getBrowserRootId()+MDSS.OntologyFields.ROW_SUFFIX;var html='';html+='<td>'+rootView.getTermName()+' ('+rootView.getTermOntologyId()+')</td>';html+='<td>'+rootView.getSelectable()+'</td>';html+='<td><button class="editRootBtn" value="'+rootView.getBrowserRootId()+'">'+MDSS.Localized.Edit+'</button></td>';html+='<td><button class="deleteRootBtn" value="'+rootView.getBrowserRootId()+'">'+MDSS.Localized.Delete+'</button></td>';tr.innerHTML=html;},_createRoot:function(fieldId,params)
{var browserRoot=this._populateRoot(params);var request=new MDSS.Request({that:this,fieldId:fieldId,onSuccess:function(rootView){var table=document.getElementById(this.fieldId+MDSS.OntologyFields.TABLE_SUFFIX);var size=table.getElementsByTagName('tr').length;var tr=table.insertRow(size);this.that._populateRow(tr,rootView);this.that._currentModal.destroy();}});Mojo.$.dss.vector.solutions.ontology.BrowserField.addBrowserRoot(request,fieldId,browserRoot);}}});Mojo.Meta.newClass('MDSS.PersonModal',{Instance:{initialize:function(prop){this._recipientIdEl=(Mojo.Util.isString(prop.concrete)?document.getElementById(prop.concrete):prop.concrete);this._clickable=(Mojo.Util.isString(prop.clickable)?document.getElementById(prop.clickable):prop.clickable);this._button=(Mojo.Util.isString(prop.button)?document.getElementById(prop.button):prop.button);this._elements=prop.elements;YAHOO.util.Dom.setAttribute(this._clickable,'tabindex','0');this._calendarIdEl=prop.calendar;this._firstName=prop.firstName;this._lastName=prop.lastName;this._currentModal=null;this._id=new String(Math.random()).substring(2);this.controller=Mojo.$.dss.vector.solutions.PersonController;var updateListener=Mojo.Util.bind(this,this.updateListener);var cancelListener=Mojo.Util.bind(this,this.cancelListener);this.controller.setUpdateRecipientListener(updateListener);this.controller.setViewAllListener(cancelListener);YAHOO.util.Event.on(this._clickable,'click',this.handleClick,null,this);YAHOO.util.Event.on(this._clickable,'keyup',this.handleKeyup,null,this);this._selectSearch=new MDSS.SingleSelectSearch();this._residentialGeoSearch=null;this._workGeoSearch=null;},updateListener:function(params){var request=new MDSS.Request({that:this,onSuccess:function(personId){this.that.destroyModal();var id=Mojo.Util.trim(personId);this.that._recipientIdEl.value=id;this.that._button.onclick();}});return request;},cancelListener:function(params){var request=new MDSS.Request({that:this,onSuccess:function(){this.that.destroyModal();}});return request;},handleClick:function(e){var request=new MDSS.Request({that:this,onSuccess:function(html){this.that.createModal(html);}});var id=this._recipientIdEl.value;this.controller.editRecipient(request,id);},handleKeyup:function(e){if(e.keyCode===13){var request=new MDSS.Request({that:this,onSuccess:function(html){this.that.createModal(html);}});var id=this._recipientIdEl.value;this.controller.editRecipient(request,id);}},createModal:function(html){var executable=MDSS.util.extractScripts(html);html=MDSS.util.removeScripts(html);this._currentModal=new YAHOO.widget.Panel(this._id,{width:'400px',height:'600px',fixedcenter:true,close:false,draggable:false,zindex:4,modal:true,visible:true});this._currentModal.setBody(html);this._currentModal.render(document.body);this._currentModal.bringToTop();var calendar=document.getElementById(this._calendarIdEl);MDSS.Calendar.addCalendarListeners(calendar);eval(executable);this._currentModal._removeFocusHandlers();if(this._recipientIdEl!=''){document.getElementById(this._firstName).value=document.getElementById(this._elements[0]).value;document.getElementById(this._lastName).value=document.getElementById(this._elements[1]).value;}},destroyModal:function()
{this._currentModal.destroy();this._currentModal=null;}},Static:{setUpPersonModal:function(prop){prop.concrete=(Mojo.Util.isString(prop.concrete)?document.getElementById(prop.concrete):prop.concrete);prop.createLink=(Mojo.Util.isString(prop.createLink)?document.getElementById(prop.createLink):prop.createLink);prop.editLink=(Mojo.Util.isString(prop.editLink)?document.getElementById(prop.editLink):prop.editLink);prop.button=(Mojo.Util.isString(prop.button)?document.getElementById(prop.button):prop.button);var listFunction=function(valueObject){var firstName=valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME);var lastName=valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.LASTNAME);var dateOfBirth=valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.DATEOFBIRTH);var sex=valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.SEX);var residential=valueObject.getValue('residentialGeoEntity_displayLabel');var formattedDateOfBirth=MDSS.Calendar.getLocalizedString(dateOfBirth);if(residential!=null&&residential!=''){return firstName+' '+lastName+' ('+sex+'), DOB: '+formattedDateOfBirth+', '+residential;}
return firstName+' '+lastName+' ('+sex+'), DOB: '+formattedDateOfBirth;};var idFunction=function(valueObject){var id=Mojo.$.dss.vector.solutions.PersonView.ID;return valueObject.getValue(id);};var displayFunction=function(valueObject){var firstName=Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME;var lastName=Mojo.$.dss.vector.solutions.PersonView.LASTNAME;var firstNameKey=prop.elements[0];var lastNameKey=prop.elements[1];var map={};map[firstNameKey]=valueObject.getValue(firstName);map[lastNameKey]=valueObject.getValue(lastName);return map;};var searchFunction=Mojo.$.dss.vector.solutions.Person.searchForPerson;var selectEventHandler=function(){prop.createLink.style.display="none";prop.editLink.style.display="inline";prop.button.disabled=false;};var showCreatePatient=function(e){prop.editLink.style.display="none";prop.createLink.style.display="inline";prop.button.disabled=true;}
var eventHandler=function(e){if(e.getType()==MDSS.Event.BEFORE_SEARCH){showCreatePatient();}}
var autocomplete={elements:prop.elements,concrete:prop.concrete,list:listFunction,display:displayFunction,id:idFunction,search:searchFunction,selectEventHandler:selectEventHandler,minLength:2};var search=new MDSS.MultiInputAutoComplete(autocomplete);search.addListener(eventHandler);var modal=new MDSS.PersonModal(prop);if(prop.concrete.value!=''){selectEventHandler();}
else{showCreatePatient();}}}});Mojo.Meta.newClass("MDSS.OntologyTree",{Instance:{initialize:function(treeViewId)
{this._treeViewId=treeViewId;this._tree=null;this._menu=null;this._Term=Mojo.$.dss.vector.solutions.ontology.Term;this._controller=Mojo.$.dss.vector.solutions.ontology.TermController;this._controller.setCreateListener(Mojo.Util.bind(this,this._createListener));this._controller.setUpdateListener(Mojo.Util.bind(this,this._updateListener));this._controller.setCancelListener(Mojo.Util.bind(this,this._cancelListener));this._currentNode=null;this._selectedNode=null;},_changeParentListener:function(cloneOperation,childNode,parentNode,params)
{var request=new MDSS.Request({that:this,cloneOperation:cloneOperation,childNode:childNode,parentNode:parentNode,onSuccess:function(view)
{if(this.parentNode.dynamicLoadComplete)
{var node=this.that._createNode(view);this.parentNode.appendChild(node);if(this.parentNode.children.length===1)
{this.parentNode.getChildrenEl().style.display='block';}
if(this.parentNode.expanded)
{this.parentNode.refresh();}
else
{this.parentNode.expand();}}
else
{this.parentNode.expand();}
if(!this.cloneOperation)
{this.that._tree.removeNode(this.childNode,true);}
this.that._destroyModal();}});var childId=params['childId'];var parentId=params['parentId'];var oldParentId=childNode.parent.data.termId;this._Term.applyWithParent(request,childId,parentId,cloneOperation,oldParentId);},_cancelListener:function(params)
{var request=new MDSS.Request({that:this,onSuccess:function()
{this.that._destroyModal();}});return request;},_createListener:function(params)
{var parentId=this._selectedNode.data.termId;var request=new MDSS.Request({that:this,parentId:parentId,onSuccess:function(view)
{var that=this.that;var nodes=this.that._tree.getNodesByProperty('termId',this.parentId);Mojo.Iter.forEach(nodes,function(parent){if(parent.dynamicLoadComplete)
{var node=this._createNode(view);parent.appendChild(node);if(parent.children.length===1)
{parent.getChildrenEl().style.display='block';}
if(parent.expanded)
{parent.refresh();}
else
{parent.expand();}}
else if(parent===this._selectedNode)
{parent.expand();}},that);that._destroyModal();}});var term=this._getTermFromParams(params);term.applyWithParent(request,parentId,false,null);},_getTermFromParams:function(params)
{var term=new this._Term();term.setName(params['dto.name']);term.setDisplay(params['dto.display']);term.setNamespace(params['dto.namespace']);term.setTermId(params['dto.termId']);term.setComment(params['dto.comment']);term.setDef(params['dto.def']);term.setObsolete(params['dto.obsolete']);return term;},_updateListener:function(params)
{var request=new MDSS.Request({that:this,onSuccess:function()
{this.that._destroyModal();this.that._updateTermHTML();}});return request;},_updateTermHTML:function()
{var request=new MDSS.Request({that:this,onSuccess:function(query)
{var view=query.getResultSet()[0];var html=this.that._createNodeHTML(view);var nodes=this.that._tree.getNodesByProperty('termId',view.getTermId());Mojo.Iter.forEach(nodes,function(node){node.setHtml(html);},this.that);}});var id=this._selectedNode.data.termId;this._Term.getByIds(request,[id]);},_createNodeHTML:function(view)
{return MDSS.OntologyBrowser.formatLabelFromView(view);},_createNode:function(view,pureObject)
{var html=this._createNodeHTML(view);var data={termId:view.getTermId()};if(pureObject)
{return{type:'HTML',html:html,data:data};}
else
{return new YAHOO.widget.HTMLNode({html:html,data:data});}},_nodeMenuSelect:function(a,b,c,d)
{var oTarget=this._menu.contextEventTarget;var htmlNode=YAHOO.util.Dom.hasClass(oTarget,"ygtvhtml")?oTarget:YAHOO.util.Dom.getAncestorByClassName(oTarget,"ygtvhtml");if(htmlNode){this._selectedNode=this._tree.getNodeByElement(htmlNode);this._menu.bringToTop();}
else{this._menu.cancel();}},_dynamicLoad:function(parentNode,fnLoadComplete)
{var request=new MDSS.Request({that:this,onSuccess:function(query)
{var views=query.getResultSet();Mojo.Iter.forEach(views,function(view){var node=this._createNode(view);parentNode.appendChild(node);},this.that);fnLoadComplete();parentNode.refresh();}});var termId=parentNode.data.termId;this._Term.getOntologyChildren(request,termId,false);},_dragDropHandler:function(ontologyTree,id)
{var request=new MDSS.Request({that:ontologyTree,onSuccess:function(html)
{this.that._createModal(html,true);}});var childNode=this.node;var childId=childNode.data.termId;var parentEl=document.getElementById(id);var parentNode=this.node.tree.getNodeByElement(parentEl);var parentId=parentNode.data.termId;var termController=Mojo.$.dss.vector.solutions.ontology.TermController;termController.setDoCloneListener(Mojo.Util.bind(ontologyTree,ontologyTree._changeParentListener,true,childNode,parentNode));termController.setDoNotCloneListener(Mojo.Util.bind(ontologyTree,ontologyTree._changeParentListener,false,childNode,parentNode));Mojo.$.dss.vector.solutions.ontology.TermController.confirmChangeParent(request,childId,parentId);},_addNodeHandler:function()
{var request=new MDSS.Request({that:this,onSuccess:function(html)
{this.that._createModal(html,false,true);}});this._controller.newInstance(request);},_editNodeHandler:function()
{var request=new MDSS.Request({that:this,onSuccess:function(html)
{this.that._createModal(html,false);}});var termId=this._selectedNode.data.termId;this._controller.edit(request,termId);},_deleteNodeHandler:function()
{var termId=this._selectedNode.data.termId;var parentId=this._selectedNode.parent.data.termId;var request=new MDSS.Request({that:this,onSuccess:function()
{var that=this.that;that._tree.removeNode(that._selectedNode,true);},onConfirmDeleteTermException:function(e)
{var modal=new YAHOO.widget.Panel("confirmDelete",{fixedcenter:true,width:'300px',visible:true,draggable:false,zindex:8000,modal:true});var upperDiv=document.createElement('div');YAHOO.util.Dom.addClass(upperDiv,'modalAlertBox');var message=document.createElement('span');message.innerHTML=e.getLocalizedMessage();upperDiv.appendChild(message);var lowerDiv=document.createElement('div');YAHOO.util.Dom.addClass(lowerDiv,'modalAlertBox');var that=this.that;var delObj={deleteTerm:true,childId:termId,parentId:parentId,modal:modal}
var delTerm=document.createElement('input');YAHOO.util.Dom.setAttribute(delTerm,'type','button');YAHOO.util.Dom.setAttribute(delTerm,'value',MDSS.localize('Delete_Term'));YAHOO.util.Event.on(delTerm,'click',that._deleteAfterConfirmation,delObj,that);lowerDiv.appendChild(delTerm);delObj={deleteTerm:false,childId:termId,parentId:parentId,modal:modal};var delRel=document.createElement('input');YAHOO.util.Dom.setAttribute(delRel,'type','button');YAHOO.util.Dom.setAttribute(delRel,'value',MDSS.Localized.Delete_Relationship);YAHOO.util.Event.on(delRel,'click',that._deleteAfterConfirmation,delObj,that);lowerDiv.appendChild(delRel);var wrapperDiv=document.createElement('div');wrapperDiv.appendChild(upperDiv);wrapperDiv.appendChild(lowerDiv);modal.bringToTop();modal.setBody(wrapperDiv);modal.render(document.body);},});this._Term.confirmDeleteTerm(request,termId,parentId);},_deleteAfterConfirmation:function(e,obj)
{var childId=obj.childId;var parentId=obj.parentId;var deleteTerm=obj.deleteTerm;var modal=obj.modal;var request=new MDSS.Request({that:this,onSuccess:function()
{var that=this.that;if(deleteTerm)
{var nodes=this.that._tree.getNodesByProperty('termId',childId);Mojo.Iter.forEach(nodes,function(node){that._tree.removeNode(node,true);},that);}
else
{that._tree.removeNode(that._selectedNode,true);}
modal.destroy();}});if(deleteTerm)
{Mojo.$.dss.vector.solutions.ontology.Term.deleteTerm(request,childId);}
else
{Mojo.$.dss.vector.solutions.ontology.Term.deleteRelationship(request,childId,parentId);}},_createModal:function(html,useSmall,closeIt)
{var executable=MDSS.util.extractScripts(html);html=MDSS.util.removeScripts(html);var id=new String(Math.random()).substring(2);this._panel=new YAHOO.widget.Panel(id,{width:(useSmall?'300px':'400px'),height:(useSmall?'200px':'410px'),fixedcenter:true,close:closeIt||false,draggable:false,zindex:4,modal:true,visible:true});if(!useSmall)
{html='<h3>&nbsp;</h3><div class="innerContentModal">'+html+"</div>";}
this._panel.setBody(html);this._panel.render(document.body);this._panel.bringToTop();eval(executable);},_destroyModal:function()
{this._panel.destroy();this._panel=null;},_setupTree:function(roots)
{this._tree=new YAHOO.widget.TreeViewDD(this._treeViewId,roots,Mojo.Util.curry(this._dragDropHandler,this));var loadB=Mojo.Util.bind(this,this._dynamicLoad);this._tree.setDynamicLoad(loadB);this._tree.render();this._setupMenu();},_setupMenu:function()
{var itemData=[];var createMenuItem=new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Create);createMenuItem.subscribe("click",Mojo.Util.bind(this,this._addNodeHandler));itemData.push(createMenuItem);var editMenuItem=new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Edit);editMenuItem.subscribe("click",Mojo.Util.bind(this,this._editNodeHandler));itemData.push(editMenuItem);var deleteMenuItem=new YAHOO.widget.ContextMenuItem(MDSS.Localized.Tree.Delete);deleteMenuItem.subscribe("click",Mojo.Util.bind(this,this._deleteNodeHandler));itemData.push(deleteMenuItem);this._menu=new YAHOO.widget.ContextMenu("ontologyTreeMenu",{trigger:this._treeViewId,lazyload:true,itemdata:itemData,zindex:500});this._menu.subscribe("triggerContextMenu",Mojo.Util.bind(this,this._nodeMenuSelect));},render:function()
{var request=new MDSS.Request({that:this,onSuccess:function(query)
{var views=query.getResultSet();var roots=Mojo.Iter.map(views,function(view){return this._createNode(view,true);},this.that);this.that._setupTree(roots);}});this._Term.getDefaultRoots(request);}}});