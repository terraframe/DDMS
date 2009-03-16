<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%> 
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MorphologicalSpecieGroup"%>
<%@page import="dss.vector.solutions.entomology.assay.*"%>
<%@page import="dss.vector.solutions.mo.*"%>
<%@page import="dss.vector.solutions.util.Halp" %>
<%@page import="org.json.*"%>
<%@page import="java.lang.reflect.InvocationTargetException"%>
<%@page import="com.terraframe.mojo.transport.metadata.*"%>
<%@page import="dss.vector.solutions.entomology.MosquitoViewDTO"%>
<%@page import="com.terraframe.mojo.business.ViewDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.UninterestingSpecieGroupViewDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.biochemical.BiochemicalAssayTestResultDTO"%>
<%@page import="com.terraframe.mojo.dataaccess.MdAttributeVirtualDAOIF"%>
<%@page import="dss.vector.solutions.entomology.MosquitoView"%>
<%@page import="com.terraframe.mojo.business.generation.GenerationUtil"%>
<%@page import="dss.vector.solutions.entomology.assay.biochemical.BiochemicalAssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.assay.molecular.MolecularAssayTestResult"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.regex.Pattern"%>


<%!

static String buildChekboxTable(MosquitoViewDTO view, Class superAssayClass ) throws JSONException{
	String s = "<table><tr><th colspan=\"2\">";
	s += superAssayClass.getSimpleName().substring(0,superAssayClass.getSimpleName().indexOf("Assay")) +"</th></tr>";
	
    	Class viewClass = view.getClass();
     
    	MosquitoView mv = new MosquitoView();
     
    	 Map<Class<AssayTestResult>, MdAttributeVirtualDAOIF> assayMap = mv.getAssayMap();
     
        for (Class<AssayTestResult> c : assayMap.keySet())
        {
          // Get the result
          MdAttributeVirtualDAOIF mdAttribute = assayMap.get(c);
          String attributeName = GenerationUtil.upperFirstCharacter(mdAttribute.getAccessorName());
    		try
    		{      
         
         		if(superAssayClass.isAssignableFrom(c) )
         		{
         			 s += "<tr><td><input type=\"checkbox\" id =\""+ attributeName + "\" onclick=\"";
                     s += "showCol('"+ attributeName + "',this.checked)";
                     s += "\"/></td><td>" ;
         			 s += attributeName + "</td></tr>";
         		}
    		}
    		catch (Exception e) {
    			System.out.println("Exception on "+e.getMessage() +" " + e);
    		}
    	}
		
	return s + "</table>";
}

%>

<%@page import="java.text.SimpleDateFormat"%><div id="cal1Container" class="yui-skin-sam"></div> 

<mjl:messages>
	<mjl:message />
</mjl:messages>

<h2>Mosquito Collection</h2>
<mjl:form name="mdss.entomology.MosquitoCollection.form.name"
	id="mdss.entomology.MosquitoCollection.form.id" method="POST">
 <div class="fldContainer">
    <div class="fcTop">
 
	<mjl:input value="${item.id}" type="hidden" param="id" />
	<dl>
		<dt><label> ${item.collectionMethodMd.displayLabel} </label></dt>
		<dd><mjl:commandLink display="${item.collectionMethod.termName}"
			action="mdss.entomology.CollectionMethodController.view.mojo"
			name="mdss.entomology.CollectionMethod.form.view.link">
			<mjl:property value="${item.collectionMethod.id}" name="id" />
		</mjl:commandLink></dd>
		<dt><label> ${item.dateCollectedMd.displayLabel} </label></dt>
		<dd>${item.dateCollected}</dd>
		<dt><label> ${item.geoEntityMd.displayLabel} </label></dt>
		<dd><mjl:commandLink display="${item.geoEntity.geoId}"
			action="mdss.test.GeoEntityController.view.mojo"
			name="mdss.test.GeoEntity.form.view.link">
			<mjl:property value="${item.geoEntity.id}" name="id" />
		</mjl:commandLink></dd>
	</dl>
<div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
 </div>

</mjl:form>

<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
MosquitoCollectionDTO mosquito_collection = (MosquitoCollectionDTO) request.getAttribute("item");
MosquitoViewDTO[] rows = mosquito_collection.getMosquitos();
MosquitoViewDTO mdView = new MosquitoViewDTO(clientRequest);
String[] attribs = { "MosquitoId","Specie","IdentificationMethod","Generation","Isofemale","Sex","TestDate"};

String delete_row = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
//out.println(getColumnSetup(mdView,attribs,delete_row,clientRequest));
%>


<h2>Mosquitos</h2>
<div class="fldContainer">
    <div class="fcTop">
<br>
<div id="checkBoxContanier" style="height:12em;">
<div style="float:left;margin-left:3em;">
<%=buildChekboxTable(mdView, BiochemicalAssayTestResult.class) %>
</div>
<div style="float:left;margin-left:3em;">
<%=buildChekboxTable(mdView, InfectivityAssayTestResult.class) %>
</div>
<div style="float:left;margin-left:3em;">
<%=buildChekboxTable(mdView, MolecularAssayTestResult.class) %>
</div>
</div>
<div id="Mosquitos"></div>
<div id="buttons" class="noprint">
<span id="MosquitosAddrow" class="yui-button yui-push-button"> <span class="first-child">
<button type="button">New Row</button>
</span> </span> <span id="MosquitosSaverows" class="yui-button yui-push-button"> <span class="first-child">
<button type="button">Save Rows To DB</button>
</span> </span>

<form method="get" action="excelimport" style="display:inline;">
       <span class="yui-button yui-push-button">
       <span class="first-child">
        <button type="submit"><f:message key="Excel_Import_Header" /></button>
        </span>
        </span>
</form> 

<form method="post" action="excelexport" style="display:inline;">
        <input type="hidden" name="type" value="dss.vector.solutions.entomology.MosquitoView"/>
        <span class="yui-button yui-push-button"> 
        <span class="first-child">
        <button type="submit"><f:message key="Excel_Export_Header" /></button>
        </span>
        </span>
</form> 

</div>

<script type="text/javascript">   
function showCol(key,checked)
{
  if(checked)
  {
    table_data.myDataTable.showColumn(key);
    table_data.myDataTable.showColumn(key+'Method');
  }
  else
  {
    table_data.myDataTable.hideColumn(key);
    table_data.myDataTable.hideColumn(key+'Method');
  }
}

    <%String[] types_to_load =
  {
     "dss.vector.solutions.entomology.MosquitoView","dss.vector.solutions.entomology.UninterestingSpecieGroupView"
  };
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));   
    %>
    <%=Halp.getDropdownSetup(mdView,attribs,delete_row,clientRequest)%>
    MojoCal.init()
    table_data = {rows:<%=Halp.getDataMap(rows,attribs,mdView)%>,      
                columnDefs:<%=Halp.getColumnSetup(mdView,attribs,delete_row,true)%>,
              defaults: {},
              copy_from_above: ["IdentificationMethod"],
              div_id: "Mosquitos",
              collection_setter: "setCollection('${item.id}')",
              data_type: "Mojo.$.dss.vector.solutions.entomology.MosquitoView",  
                width:"65em"        
          };   
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(table_data));
</script>
 
    <div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>
</div>


<h2>UninterestingSpecieGroups</h2>
<div class="fldContainer">
    <div class="fcTop">
<div id="UninterestingSpecieGroups"></div>
<div class="noprint">
<span id="UninterestingSpecieGroupsAddrow" class="yui-button yui-push-button"> <span class="first-child">
<button type="button" >New Row</button>
</span> </span> 

<span id="UninterestingSpecieGroupsSaverows" class="yui-button yui-push-button"> <span class="first-child">
<button type="button">Save Rows To DB</button>
</span> </span>

<form method="get" action="excelimport" style="display:inline;">
       <span class="yui-button yui-push-button">
       <span class="first-child">
        <button type="submit"><f:message key="Excel_Import_Header" /></button>
        </span>
        </span>
</form> 
<form method="post" action="excelexport" style="display:inline;">
        <input type="hidden" name="type" value="dss.vector.solutions.entomology.MorphologicalSpecieGroupView"/>
        <span class="yui-button yui-push-button"> 
        <span class="first-child">
        <button type="submit"><f:message key="Excel_Export_Header" /></button>
        </span>
        </span>
</form> 
<a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a>

</div>
<%
UninterestingSpecieGroupViewDTO[] unint_rows = mosquito_collection.getUninterestingSpecieGroups();
UninterestingSpecieGroupViewDTO mdUnIntView = new UninterestingSpecieGroupViewDTO(clientRequest);
String[] unint_attribs = { "GroupId","SampleId","Specie","IdentificationMethod","Quantity"};
%>

<script type="text/javascript"> 
<%=Halp.getDropdownSetup(mdUnIntView,unint_attribs,delete_row,clientRequest)%>

UninterestingSpecieGroupData = { rows:<%=Halp.getDataMap(unint_rows,unint_attribs,mdUnIntView)%>,       
       columnDefs: <%=Halp.getColumnSetup(mdUnIntView,unint_attribs,delete_row,false)%>,
              defaults: {},
              div_id: "UninterestingSpecieGroups",
              copy_from_above: ["IdentificationMethod"],
              collection_setter: "setCollection('${item.id}')",
              data_type: "Mojo.$.dss.vector.solutions.entomology.UninterestingSpecieGroupView"
              
          };   
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(UninterestingSpecieGroupData));
</script>
    
    <div class="fcTopLeft"></div></div>
    <div class="fcBottom"><div class="fcBottomLeft"></div></div>

</div>

