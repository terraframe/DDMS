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
<%@page import="dss.vector.solutions.mo.*"%>
<%@page import="dss.vector.solutions.util.Halp" %>
<%@page import="org.json.*"%>
<%@page import="java.lang.reflect.InvocationTargetException"%>
<%@page import="com.terraframe.mojo.transport.metadata.*"%>
<%@page import="com.terraframe.mojo.business.ViewDTO"%>

<mjl:messages>

	<mjl:message />
</mjl:messages>

<mjl:form name="mdss.entomology.MosquitoCollection.form.name"
 id="mdss.entomology.MosquitoCollection.form.id" method="POST">

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

<div class="submitButton_bl"></div>    
 <mjl:command value="Edit"
  action="dss.vector.solutions.entomology.MosquitoCollectionController.edit.mojo"
  name="dss.vector.solutions.entomology.MosquitoCollection.form.edit.button" classes="submitButton"/>
  
  <mjl:commandLink display="ViewAssays" action="dss.vector.solutions.entomology.MosquitoCollectionController.viewAssays.mojo" name="viewAssays.link">
          <mjl:property value="${item.id}" name="id" />
        </mjl:commandLink>
  </mjl:form>

<br/>
<div id="MorphologicalSpecieGroups"></div>
<br/>
<div id="columnshowhide"></div>

<div id="dt-dlg" class="inprogress"><span class="corner_tr"></span>
<span class="corner_tl"></span> <span class="corner_br"></span> <span
	class="corner_bl"></span>
<div class="hd">Choose which columns you would like to see:</div>
<div id="dt-dlg-picker" class="bd"></div>

</div>

<div id="buttons" class="noprint">

<span id="MorphologicalSpecieGroupsAddrow" class="yui-button yui-push-button"> 
<span class="first-child">
<button type="button">New Row</button>
 </span> 
 </span> 

<span id="MorphologicalSpecieGroupsSaverows" class="yui-button yui-push-button"> 
<span class="first-child">
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
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
Object ojbect = request.getAttribute("item");
MosquitoCollectionDTO mosquito_collection = (MosquitoCollectionDTO) ojbect;
MorphologicalSpecieGroupViewDTO[] rows = mosquito_collection.getMorphologicalSpecieGroups();
String[] attribs = { "GroupId","Specie","IdentificationMethod","QuantityMale","QuantityFemale","Quantity"};
MorphologicalSpecieGroupViewDTO mdView = new MorphologicalSpecieGroupViewDTO(clientRequest);

String delete_row = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
//out.println(getColumnSetup(mdView,attribs,delete_row));
%>

<script type="text/javascript">      
    <%String[] types_to_load =
  {
     "dss.vector.solutions.entomology.MorphologicalSpecieGroupView"
  };
    
  // THIS LINE CRASHES TOMCAT WITH Invalid memory access of location 00000000 eip=00000000
  //out.println(Halp.getDropDownMap(SpecieDTO.getAll(clientRequest)));
  //out.println(Halp.getDropDownMap2(Arrays.asList(SpecieDTO.getAll(clientRequest))));
   // SpecieDTO[] arrggg = SpecieDTO.getAll(clientRequest);
    
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));
    //out.println( getDisplayLabels(SpecieDTO.getAll(clientRequest),"Specie"));
   // out.println(getDisplayLabels(IdentificationMethodDTO.getAll(clientRequest),"IdentificationMethod"));
    %>
    <%=Halp.getDropdownSetup(mdView,attribs,delete_row,clientRequest)%>
    table_data = { rows:<%=Halp.getDataMap(rows,attribs,mdView)%>,       
       columnDefs: <%=Halp.getColumnSetup(mdView,attribs,delete_row,false)%>,
              defaults: {GroupId:"",Specie:"",IdentificationMethod:"",QuantityMale:"",QuantityFemale:"",Quantity:""},
              div_id: "MorphologicalSpecieGroups",
              copy_from_above: ["IdentificationMethod"],
              collection_setter: "setCollection('${item.id}')",
              data_type: "Mojo.$.dss.vector.solutions.entomology.MorphologicalSpecieGroupView"
              
          };   
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(table_data));
</script>
