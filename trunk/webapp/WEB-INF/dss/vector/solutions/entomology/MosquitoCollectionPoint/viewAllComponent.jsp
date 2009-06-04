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

<%@page import="dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionPointDTO"%><c:set var="page_title" value="View_Mosquito_Collection_Points"  scope="request"/>
<fmt:setLocale value="<%=request.getLocale()%>" />
<div id="cal1Container" class="yui-skin-sam"></div>
<mjl:messages>
  <mjl:message />
</mjl:messages>
  <dl>
    <dt>
      <label>
        <fmt:message key="Geo_Entity" />
      </label>
    </dt>
    <dd>
       ${geoEntity.geoId}
    </dd>
    <dt>
      <label >
        <fmt:message key="Start_Date" />
      </label>
    </dt>
    <dd class="formatDate">
      ${startDate}
    </dd>
    <dt>
      <label>
        <fmt:message key="End_Date" />
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


<%
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
MorphologicalSpecieGroupViewDTO[] rows = (MorphologicalSpecieGroupViewDTO[]) request.getAttribute("collection_points");
String[] attribs = {"GroupId", "GeoEntity","DateCollected","Specie","IdentificationMethod","QuantityMale","QuantityFemale","Quantity", "Total", "Collection"};
MosquitoCollectionPointViewDTO mdView = new MosquitoCollectionPointViewDTO(clientRequest);

String delete_row = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";

Integer[] no_show_arr = {0,1,8};
List no_show_list = Arrays.asList(no_show_arr);
Integer[] no_edit_arr = {8,9};
List no_edit_list = Arrays.asList(no_edit_arr);

%>

<script type="text/javascript" defer="defer">
  <%
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , new String[]{MorphologicalSpecieGroupViewDTO.CLASS}, true));
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , new String[]{MosquitoCollectionPointViewDTO.CLASS}, true));
    //out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , new String[]{MosquitoCollectionDTO.CLASS}, true));
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId() , new String[]{MosquitoCollectionPointDTO.CLASS}, true));
  %>

  <%=Halp.getDropdownSetup(mdView,attribs,delete_row,clientRequest)%>

    table_data = { rows:<%=Halp.getDataMap(rows,attribs,mdView)%>,
       columnDefs: <%=Halp.getColumnSetup(mdView,attribs,delete_row,false,no_show_list,no_edit_list)%>,
       defaults: {GroupId:"",GeoEntity:"${geoEntity.id}",Specie:"",DateCollected:"<fmt:formatDate value="${startDate}" pattern="<%=Halp.getDateFormatString(request)%>"/>"},
       div_id: "MorphologicalSpecieGroups",
       copy_from_above: ["DateCollected","IdentificationMethod"],
       data_type: "Mojo.$.<%=MosquitoCollectionPointViewDTO.CLASS%>",
       after_save:function(){window.location.reload( false );},
       after_row_load:function(record){
         var request = new MDSS.Request({
           record:record,
           onSend:function(){},
           onComplete:function(){},
           onFailure:function(){},
           onSuccess : function(result){
             var str = '<a href="dss.vector.solutions.entomology.MosquitoCollectionController.viewAssays.mojo?id='+record.getData('Collection')+'">'+result.getCollectionId()+'</a> ('+record.getData('Total')+')';
             table_data.myDataTable.updateCell(record, 'Collection', str);
           },
         });
       Mojo.get(request,record.getData('Collection'));
       }
     };
    MojoGrid.createDataTable(table_data);

</script>