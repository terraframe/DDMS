<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  TeamSprayStatusViewDTO view = new TeamSprayStatusViewDTO(clientRequest);

  ZoneSprayViewDTO spray = ((ZoneSprayViewDTO) request.getAttribute("item"));
  TeamSprayStatusViewDTO[] rows = (TeamSprayStatusViewDTO[]) request.getAttribute("status");

  String[] attributes = {"StatusId", "Spray", "SprayData", "SprayTeam", "TeamLabel", "TeamLeader",
       "TeamSprayWeek", "Received", "Refills", "Returned", "Used", "Households", "Structures",
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
<%@page import="dss.vector.solutions.irs.ActorSprayStatusViewDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.irs.SprayTeamDTO"%>
<c:set var="page_title" value="View_Zone_Spray"  scope="request"/>
<mjl:messages>
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
      <mjl:dt attribute="sprayWeek">
        ${item.sprayWeek}
      </mjl:dt>
      <mjl:dt attribute="supervisorName">
        ${item.supervisorName}
      </mjl:dt>      
      <mjl:dt attribute="supervisorSurname">
        ${item.supervisorSurname}
      </mjl:dt>      
      <mjl:dt attribute="target">
        ${item.target}
      </mjl:dt>            
    </mjl:component>
    
    <mjl:command value="Edit" action="dss.vector.solutions.irs.ZoneSprayController.edit.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.edit.button" />
  </dl>
</mjl:form>

<h2><fmt:message key="Team_Sprays"/></h2>
<div id="Status"></div>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayTeamDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayStatusViewDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{ActorSprayStatusViewDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{TeamSprayStatusViewDTO.CLASS}))%>
<script type="text/javascript" defer="defer">
operators = <%=request.getAttribute("operators")%>;
<%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>
data = {
         rows:<%=Halp.getDataMap(rows, attributes, view)%>,
         columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, 4)%>,
         defaults: {"Spray":'<%=spray.getSprayId()%>'},
         div_id: "Status",
         data_type: "Mojo.$.<%=TeamSprayStatusViewDTO.CLASS%>",
         saveFunction:"applyAll",
         width:"65em",
         after_row_load:function(record){
             var teamId = record.getData('SprayTeam');
             var leaderId = record.getData('TeamLeader');
                     
             var leader = operators[teamId][leaderId];

             record.setData('TeamLeader', leader);
         }
     };
     MojoGrid.createDataTable(data);

     var swap = function(e){
         var teamId = e.editor.getRecord().getData('SprayTeam');
         var column = data.myDataTable.getColumn('TeamLeader');
         var cell = e.editor.getTdEl();
         
         TeamLeaderLabels=Mojo.util.getValues(operators[teamId]);   
         TeamLeaderIds=Mojo.util.getKeys(operators[teamId]);   

         e.editor.dropdownOptions = TeamLeaderLabels;

         selectEl = e.editor.dropdown;
         selectEl.innerHTML = "";
         selectedValue = e.editor.getRecord().getData('TeamLeader');
 
         // We have options to populate
         if(TeamLeaderLabels.length > 0) {
             // Create OPTION elements
             for(var i=0; i < TeamLeaderLabels.length; i++) {
                 var option = TeamLeaderLabels[i];
                 var optionEl = document.createElement("option");

                 optionEl.value = option;
                 optionEl.innerHTML = option;
                 optionEl = selectEl.appendChild(optionEl);

                 if (optionEl.value == selectedValue) {
                     optionEl.selected = true;
                 }
             }
         }
         else {
           selectEl.innerHTML = "<option selected value=\"\"></option>";             
         }
	 }
     
     var column = data.myDataTable.getColumn('TeamLeader');

     column.editor = new YAHOO.widget.DropdownCellEditor({dropdownOptions:[],disableBtns:true});
     column.editor.subscribe('showEvent', swap);     
     column.editor.subscribe('saveEvent', MojoGrid.saveHandler);

</script>