<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MorphologicalSpecieGroup"%>
<%@page import="dss.vector.solutions.entomology.assay.*"%>
<%@page import="dss.vector.solutions.mo.*"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="org.json.*"%>
<%@page import="java.lang.reflect.InvocationTargetException"%>
<%@page import="com.terraframe.mojo.transport.metadata.*"%>
<%@page import="dss.vector.solutions.entomology.MosquitoViewDTO"%>
<%@page import="com.terraframe.mojo.business.ViewDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.AssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.UninterestingSpecieGroupViewDTO"%>
<%@page import="com.terraframe.mojo.dataaccess.MdAttributeVirtualDAOIF"%>
<%@page import="dss.vector.solutions.entomology.MosquitoView"%>
<%@page import="com.terraframe.mojo.business.generation.GenerationUtil"%>
<%@page import="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.terraframe.mojo.dataaccess.database.Database"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.PrintStream"%>
<%@page import="dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO"%>

<%!static String buildChekboxTable(MosquitoViewDTO view, Class superAssayClass ) throws JSONException{
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
          String displayLabel = mdAttribute.getDisplayLabel(java.util.Locale.ENGLISH);
    		try
    		{

         		if(superAssayClass.isAssignableFrom(c) )
         		{
         			 s += "<tr><td><input type=\"checkbox\" id =\""+ attributeName + "\" onclick=\"";
                     s += "showCol('"+ attributeName + "',this.checked)";
                     s += "\"/></td><td>" ;
         			 s += displayLabel + "</td></tr>";
         		}
    		}
    		catch (Exception e) {
    			System.out.println("Exception on "+e.getMessage() +" " + e);
    		}
    	}

	return s + "</table>";
}%>





<div id="cal1Container" class="yui-skin-sam"></div>
<c:set var="page_title" value="Mosquito_Assays"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<h2>Mosquito Collection</h2>
<mjl:form name="dss.vector.solutions.entomology.MosquitoCollection.form.name" id="dss.vector.solutions.entomology.MosquitoCollection.form.id" method="POST">

  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt><label> ${item.geoEntityMd.displayLabel} </label></dt>
    <dd>${item.geoEntity.geoId}</dd>
    <dt><label> ${item.dateCollectedMd.displayLabel} </label></dt>
    <dd class="formatDate">${item.dateCollected}</dd>

  </dl>

</mjl:form>

<%
	ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
	ConcreteMosquitoCollectionDTO mosquito_collection = (ConcreteMosquitoCollectionDTO) request.getAttribute("item");
	MosquitoViewDTO[] rows = mosquito_collection.getMosquitos();
	MosquitoViewDTO mdView = new MosquitoViewDTO(clientRequest);
	String[] attribs = {"MosquitoId", "SampleId", "Specie",
			"IdentificationMethod", "Generation", "Isofemale", "Sex",
			"TestDate"};

	String delete_row = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
	//out.println(getColumnSetup(mdView,attribs,delete_row,clientRequest));
%>


<h2>Mosquitos</h2>
<div class="fldContainer"><br>
<div id="checkBoxContanier" style="height: 12em;">
<div style="float: left; margin-left: 3em;"><%=buildChekboxTable(mdView,InfectivityAssayTestResult.class)%></div>
<div style="float: left; margin-left: 3em;"><%=buildChekboxTable(mdView,TargetSiteAssayTestResult.class)%></div>
<div style="float: left; margin-left: 3em;"><%=buildChekboxTable(mdView,MetabolicAssayTestResult.class)%></div>
</div>
<div id="Mosquitos"></div>
<div id="buttons" class="noprint"><span id="MosquitosAddrow" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="New_Row"/></button>
</span> </span> <span id="MosquitosSaverows" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="Save_Rows_To_DB"/></button>
</span> </span> <a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a></div>

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

    <%String[] types_to_load = {
					"dss.vector.solutions.entomology.MosquitoView",
					"dss.vector.solutions.entomology.UninterestingSpecieGroupView"};
			out.println(com.terraframe.mojo.web.json.JSONController
					.importTypes(clientRequest.getSessionId(), types_to_load,
							true));%>
    <%=Halp.getDropdownSetup(mdView, attribs, delete_row,
							clientRequest)%>
    MojoCal.init()
    table_data = {rows:<%=Halp.getDataMap(rows, attribs, mdView)%>,
                columnDefs:<%=Halp.getColumnSetup(mdView, attribs, delete_row, true)%>,
              defaults: {},
              copy_from_above: ["IdentificationMethod"],
              div_id: "Mosquitos",
              collection_setter: "setCollection('${item.id}')",
              data_type: "Mojo.$.dss.vector.solutions.entomology.MosquitoView",
                width:"65em"
          };
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(table_data));
</script></div>


<h2>UninterestingSpecieGroups</h2>
<div class="fldContainer">
<div id="UninterestingSpecieGroups"></div>
<div class="noprint"><span id="UninterestingSpecieGroupsAddrow" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="New_Row"/></button>
</span> </span> <span id="UninterestingSpecieGroupsSaverows" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="Save_Rows_To_DB"/></button>
</span> </span>

<form method="get" action="excelimport" style="display: inline;"><span class="yui-button yui-push-button"> <span class="first-child">
<button type="submit"><fmt:message key="Excel_Import_Header" /></button>
</span> </span></form>
<form method="post" action="excelexport" style="display: inline;"><input type="hidden" name="type" value="dss.vector.solutions.entomology.MorphologicalSpecieGroupView" /> <span
  class="yui-button yui-push-button"> <span class="first-child">
<button type="submit"><fmt:message key="Excel_Export_Header" /></button>
</span> </span></form>
<a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a></div>
<%
	UninterestingSpecieGroupViewDTO[] unint_rows = mosquito_collection.getUninterestingSpecieGroups();
	UninterestingSpecieGroupViewDTO mdUnIntView = new UninterestingSpecieGroupViewDTO(clientRequest);
	String[] unint_attribs = {"GroupId", "SampleId", "Specie",
			"IdentificationMethod", "Quantity"};
%> <script type="text/javascript">
<%=Halp.getDropdownSetup(mdUnIntView, unint_attribs,
							delete_row, clientRequest)%>

UninterestingSpecieGroupData = { rows:<%=Halp.getDataMap(unint_rows, unint_attribs, mdUnIntView)%>,
       columnDefs: <%=Halp.getColumnSetup(mdUnIntView, unint_attribs,
							delete_row, false)%>,
              defaults: {},
              div_id: "UninterestingSpecieGroups",
              copy_from_above: ["IdentificationMethod"],
              collection_setter: "setCollection('${item.id}')",
              data_type: "Mojo.$.dss.vector.solutions.entomology.UninterestingSpecieGroupView"

          };
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(UninterestingSpecieGroupData));
</script></div>