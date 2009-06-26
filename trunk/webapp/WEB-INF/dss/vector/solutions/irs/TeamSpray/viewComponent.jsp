<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  OperatorSprayStatusViewDTO view = new OperatorSprayStatusViewDTO(clientRequest);

  TeamSprayViewDTO spray = ((TeamSprayViewDTO) request.getAttribute("item"));  
  OperatorSprayStatusViewDTO[] rows = (OperatorSprayStatusViewDTO[]) request.getAttribute("status");
  
  String[] attributes = {"StatusId", "Spray", "SprayData", "SprayOperator", "OperatorLabel", "OperatorSprayWeek", "Received",
       "Refills", "Returned", "Used",   "Households", "Structures",
       "SprayedHouseholds", "SprayedStructures", "PrevSprayedHouseholds", "PrevSprayedStructures",
       "Rooms", "SprayedRooms", "People", "BedNets", "RoomsWithBedNets", "Locked", "Refused", "Other"};

  String deleteColumn = "";
%>

<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.irs.TeamSprayViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.OperatorSprayStatusViewDTO"%>

<%@page import="com.terraframe.mojo.business.ViewDTO"%>
<%@page import="dss.vector.solutions.irs.SprayStatusViewDTO"%>
<%@page import="dss.vector.solutions.irs.ActorSprayStatusViewDTO"%>
<c:set var="page_title" value="View_Team_Spray" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.TeamSpray.form.name" id="dss.vector.solutions.irs.TeamSpray.form.id" method="POST">
  <dl>
    <mjl:input value="${item.sprayId}" type="hidden" param="id" />      
    
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="geoEntity"> ${item.geoEntity.geoId} </mjl:dt>
      <mjl:dt attribute="brand"> ${item.brand.brandName} </mjl:dt>
      <mjl:dt attribute="sprayDate">
        <span id="testDateSpan" class="formatDate">${item.sprayDate}</span>
      </mjl:dt>
      <mjl:dt attribute="sprayMethod">
        <ul>
          <c:forEach var="enumName" items="${item.sprayMethodEnumNames}">
            <li>${item.sprayMethodMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>            
      </mjl:dt>
      <mjl:dt attribute="sprayTeam">
        ${item.sprayTeam.teamId}
      </mjl:dt>                   
      <mjl:dt attribute="teamLeader">
        ${item.teamLeader.person.lastName}, ${item.teamLeader.person.firstName}
      </mjl:dt>
      <mjl:dt attribute="surfaceType" >          
        <ul>
          <c:forEach var="enumName" items="${item.surfaceTypeEnumNames}">
            <li>${item.surfaceTypeMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>        
      <mjl:dt attribute="teamSprayWeek"> ${item.teamSprayWeek} </mjl:dt>      
      <mjl:dt attribute="target"> ${item.target} </mjl:dt>     
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.irs.TeamSprayController.edit.mojo" name="dss.vector.solutions.irs.TeamSpray.form.edit.button" />
  </dl>  
</mjl:form>

<h2><fmt:message key="Operator_Sprays"/></h2>
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
     out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId(), new String[]{OperatorSprayStatusViewDTO.CLASS}, true));
    %>
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>


    data = {
              rows:<%=Halp.getDataMap(rows, attributes, view)%>,
              columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, 4)%>,
              defaults: {"Spray":'<%=spray.getSprayId()%>'},
              div_id: "Status",
              data_type: "Mojo.$.<%=OperatorSprayStatusViewDTO.CLASS%>",
              saveFunction:"applyAll",
              width:"65em"              
          };
    document.addEventListener('load', MojoGrid.createDataTable(data), false);

</script>


