<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>

<%!static String buildChekboxTable(MosquitoViewDTO view, String title, Class<?> superAssayClass , ClientRequestIF clientRequest) throws JSONException{
  String s = "<table><tr><th colspan=\"2\">"+ title;
  //s += superAssayClass.getSimpleName().substring(0,superAssayClass.getSimpleName().indexOf("Assay")) +"</th></tr>";

    Class<?> viewClass = view.getClass();

    MdAttributeVirtualDTO[] mdArray = MosquitoViewDTO.getAccessors(clientRequest,superAssayClass.getCanonicalName());

    for (MdAttributeVirtualDTO md : mdArray)
    {
       String acc =  md.getAttributeName();
       acc = acc.substring(0, 1).toUpperCase() + acc.substring(1);

        s += "<tr><td><input class=\"results\" + type=\"checkbox\" id =\""+ acc + "\" onclick=\"";
        s += "showCol('"+ acc + "',this.checked)";
        s += "\"/></td><td>" ;
     s += md.getMdAttributeConcrete().getDisplayLabel()+ "</td></tr>";
    }

  return s + "</table>";
}%>




<div id="cal1Container" class="yui-skin-sam"></div>
<c:set var="page_title" value="Mosquito_Assays"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<h2><fmt:message key="Collection"/></h2>
<mjl:form name="dss.vector.solutions.entomology.MosquitoCollection.form.name" id="dss.vector.solutions.entomology.MosquitoCollection.form.id" method="POST">

  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt><label> ${item.collectionIdMd.displayLabel} </label></dt>
    <dd>${item.collectionId}</dd>
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
  String[] attribs = {"MosquitoId","Collection", "SampleId", "Specie", "IdentificationMethod", "Generation", "Isofemale", "Sex", "TestDate"};

  String delete_row = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
%>


<h2><fmt:message key="Mosquitos"/></h2>
<div class="fldContainer"><br>
<div id="checkBoxContanier" style="height: 12em;">
<div style="float: left; margin-left: 3em;"><%=buildChekboxTable(mdView,"Biochemical Assays",MetabolicAssayTestResult.class,clientRequest)%></div>
<div style="float: left; margin-left: 3em;"><%=buildChekboxTable(mdView,"Molecular Assays",TargetSiteAssayTestResult.class,clientRequest)%></div>
<div style="float: left; margin-left: 3em;"><%=buildChekboxTable(mdView,"Infectivity Assays",InfectivityAssayTestResult.class,clientRequest)%></div>
</div>
<div id="Mosquitos"></div>

<script type="text/javascript">
function showCol(key,checked)
{
  var methodKey = key +'Method';

  if(checked)
  {        
      table_data.myDataTable.showColumn(key);
      table_data.myDataTable.showColumn(methodKey);
  }
  else
  {        
      table_data.myDataTable.hideColumn(key);
      table_data.myDataTable.hideColumn(methodKey);
  }
}

<%String[] types_to_load = {MosquitoViewDTO.CLASS, UninterestingSpecieGroupViewDTO.CLASS}; %>
<%=Halp.getDropdownSetup(mdView, attribs, delete_row,clientRequest)%>

var editors = Mojo.util.getObject(<%=request.getAttribute("editors")%>);

MDSS.Calendar.init()
table_data = {rows:<%=Halp.getDataMap(rows, attribs, mdView)%>,
          columnDefs:<%=Halp.getColumnSetup(mdView, attribs, delete_row, true,2)%>,
          defaults: {Collection:'${item.id}'},
          copy_from_above: ["IdentificationMethod"],
          div_id: "Mosquitos",
          data_type: "Mojo.$.dss.vector.solutions.entomology.MosquitoView",
          width:"65em",
          excelButtons:false          
      };
YAHOO.util.Event.addListener(window, 'load', MojoGrid.createDataTable(table_data));
</script></div>
<%=Halp.loadTypes((List<String>) Arrays.asList(types_to_load))%>

<h2><fmt:message key="Uninteresting_Specie_Groups"/></h2>
<div class="fldContainer">
  <div id="UninterestingSpecieGroups">
</div>

<%
  UninterestingSpecieGroupViewDTO[] unint_rows = mosquito_collection.getUninterestingSpecieGroups();
  UninterestingSpecieGroupViewDTO mdUnIntView = new UninterestingSpecieGroupViewDTO(clientRequest);
  String[] unint_attribs = {"GroupId","Collection", "SampleId", "Specie", "IdentificationMethod", "Quantity"};
%>

<script type="text/javascript">
<%=Halp.getDropdownSetup(mdUnIntView, unint_attribs, delete_row, clientRequest)%>

UninterestingSpecieGroupData = { rows:<%=Halp.getDataMap(unint_rows, unint_attribs, mdUnIntView)%>,
       columnDefs: <%=Halp.getColumnSetup(mdUnIntView, unint_attribs, delete_row, false,2)%>,
              defaults: {Collection:'${item.id}'},
              div_id: "UninterestingSpecieGroups",
              copy_from_above: ["IdentificationMethod"],
              //collection_setter: "setCollection()",
              data_type: "Mojo.$.dss.vector.solutions.entomology.UninterestingSpecieGroupView",
              excelType: "dss.vector.solutions.export.UninterestingSpecieGroupExcelView"
          };
YAHOO.util.Event.addListener(window, 'load', MojoGrid.createDataTable(UninterestingSpecieGroupData));
</script></div>


<script type="text/javascript" defer="defer">

//Toggle all of the result columns
var reloadColumns = function()
{
  var checkboxes = YAHOO.util.Selector.query(".results");
    
  for (var i = 0; i < checkboxes.length; i++)
  {
    showCol(checkboxes[i].id,checkboxes[i].checked);  
  }  
}

reloadColumns();
</script>
