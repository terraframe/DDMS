<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.*"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO"%>
<%@page import="dss.vector.solutions.mo.*"%>
<%@page import="dss.vector.solutions.util.Halp" %>
<%@page import="org.json.*"%>
<%@page import="java.lang.reflect.InvocationTargetException"%>
<%@page import="com.terraframe.mojo.transport.metadata.*"%>
<%@page import="com.terraframe.mojo.business.ViewDTO"%>

<div id="cal1Container" class="yui-skin-sam"></div>
<mjl:messages>
  <mjl:message />
</mjl:messages>
  <dl>
    <dt>
      <label>
        GeoEntinty
      </label>
    </dt>
    <dd>
       ${geoEntity.geoId}
    </dd>
    <dt>
      <label >
        Start Date
      </label>
    </dt>
    <dd class="formatDate">
      ${startDate}
    </dd>
    <dt>
      <label>
        End Date
      </label>
    </dt>
    <dd class="formatDate">
      ${endDate}
    </dd>
  </dl>

<br />


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
        <button type="submit"><fmt:message key="Excel_Import_Header" /></button>
        </span>
        </span>
</form>
<form method="post" action="excelexport" style="display:inline;">
        <input type="hidden" name="type" value="dss.vector.solutions.entomology.MorphologicalSpecieGroupView"/>
        <span class="yui-button yui-push-button">
        <span class="first-child">
        <button type="submit"><fmt:message key="Excel_Export_Header" /></button>
        </span>
        </span>
</form>
<a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a>

</div>


<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
MorphologicalSpecieGroupViewDTO[] rows = (MorphologicalSpecieGroupViewDTO[]) request.getAttribute("collection_points");
String[] attribs = {"GroupId","GeoEntity","DateCollected","Specie","IdentificationMethod","QuantityMale","QuantityFemale","Quantity","Collection"};
MorphologicalSpecieGroupViewDTO mdView = new MorphologicalSpecieGroupViewDTO(clientRequest);

String delete_row = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
//out.println(getColumnSetup(mdView,attribs,delete_row));

%>

<script type="text/javascript">
    <%String[] types_to_load =
  {
     "dss.vector.solutions.entomology.MorphologicalSpecieGroupView"
  };
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));
    %>
    <%=Halp.getDropdownSetup(mdView,attribs,delete_row,clientRequest)%>
    table_data = { rows:<%=Halp.getDataMap(rows,attribs,mdView)%>,
       columnDefs: <%=Halp.getColumnSetup(mdView,attribs,delete_row,false,2)%>,
              defaults: {GroupId:"",GeoEntity:"${geoEntity.id}",Specie:"",DateCollected:"<fmt:formatDate value="${startDate}" dateStyle="SHORT" />",IdentificationMethod:"",QuantityMale:"",QuantityFemale:"",Quantity:""},
              div_id: "MorphologicalSpecieGroups",
              copy_from_above: ["DateCollected","IdentificationMethod"],
              data_type: "Mojo.$.dss.vector.solutions.entomology.MorphologicalSpecieGroupView",
              after_row_load:function(record,dt){dt.getColumn('Collection').editor=null;
              dt.getColumn('Collection').getThLinerEl().innerHTML="";
              record.setData('Collection',('<a href="dss.vector.solutions.entomology.MosquitoCollectionController.viewAssays.mojo?id='+record.getData('Collection')+'">Assays</a>'));},
              after_save:function(){window.location.reload( false );}
          };
    YAHOO.util.Event.onDOMReady(MojoGrid.createDataTable(table_data));
</script>

