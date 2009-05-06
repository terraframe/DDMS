<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  TeamSprayStatusViewDTO view = new TeamSprayStatusViewDTO(clientRequest);

  ZoneSprayViewDTO spray = ((ZoneSprayViewDTO) request.getAttribute("item"));  
  TeamSprayStatusViewDTO[] rows = (TeamSprayStatusViewDTO[]) request.getAttribute("status");
  
  String[] attributes = {"StatusId", "Spray", "SprayData", "SprayTeam", "TeamLabel", "TeamSprayWeek", "Households", "Structures",
       "SprayedHouseholds", "SprayedStructures", "PrevSprayedHouseholds", "PrevSprayedStructures",
       "Rooms", "SprayedRooms", "People", "BedNets", "RoomsWithBedNets", "Locked", "Refused", "Other"};

  String deleteColumn = "";
%>



<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.irs.ZoneSprayViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.TeamSprayStatusViewDTO"%>


<%@page import="dss.vector.solutions.irs.SprayStatusViewDTO"%>
<%@page import="dss.vector.solutions.irs.ActorSprayStatusViewDTO"%><mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.ZoneSpray.form.name" id="dss.vector.solutions.irs.ZoneSpray.form.id" method="POST">
  <dl>
    <mjl:input value="${item.sprayId}" type="hidden" param="id" />      
    
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="surfaceType" >          
        <ul>
          <c:forEach var="enumName" items="${item.surfaceTypeEnumNames}">
            <li>${item.surfaceTypeMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>        
    </mjl:component>
  </dl>  
  <mjl:command value="Edit" action="dss.vector.solutions.irs.ZoneSprayController.edit.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.edit.button" />
</mjl:form>

<h2><fmt:message key="Team_Sprays"/></h2>
<div id="Status"></div>
<div id="buttons" class="noprint">
  <span id="StatusSaverows" class="yui-button yui-push-button">
    <span class="first-child">
      <button type="button"><fmt:message key="Save_Rows_To_DB"/></button>
    </span>
  </span>
  <a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a>
</div>

<script type="text/javascript">

<%
out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId(), new String[]{SprayStatusViewDTO.CLASS}, true));
out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId(), new String[]{ActorSprayStatusViewDTO.CLASS}, true));
out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId(), new String[]{TeamSprayStatusViewDTO.CLASS}, true));
%>
<%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>


data = {
         rows:<%=Halp.getDataMap(rows, attributes, view)%>,
         columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, 4)%>,
         defaults: {"Spray":'<%=spray.getSprayId()%>'},
         div_id: "Status",
         data_type: "Mojo.$.<%=TeamSprayStatusViewDTO.CLASS%>",
         saveFunction:"applyAll",
         width:"65em"              
     };
document.addEventListener('load', MojoGrid.createDataTable(data), false);

</script>


