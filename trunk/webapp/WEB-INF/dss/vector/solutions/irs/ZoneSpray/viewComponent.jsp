<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  ZoneSprayViewDTO spray = ((ZoneSprayViewDTO) request.getAttribute("item"));

  TeamSprayStatusViewDTO view = new TeamSprayStatusViewDTO(clientRequest);
  view.setValue(TeamSprayStatusViewDTO.SPRAYDATA, spray.getSprayData().getId());
  
  TeamSprayStatusViewDTO[] rows = (TeamSprayStatusViewDTO[]) request.getAttribute("status");

  String[] attributes = {"StatusId", "SprayData", "SprayTeam", "TeamLeader",
       "TeamSprayWeek", "Target", "Received", "Refills", "Returned", "Used", "Households", "Structures",
       "SprayedHouseholds", "SprayedStructures", "PrevSprayedHouseholds", "PrevSprayedStructures",
       "Rooms", "SprayedRooms", "People", "BedNets", "RoomsWithBedNets", "Locked", "Refused", "Other"};

  String deleteColumn = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
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

<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="java.util.HashMap"%><c:set var="page_title" value="View_Zone_Spray"  scope="request"/>

<style type="text/css">
.yui-skin-sam .yui-dt th, .yui-skin-sam .yui-dt th a
{
vertical-align:bottom;
background-color:#DDDDDD;
background:none;
}

.yui-dt-label{
/*writing-mode: tb-rl;*/
-moz-transform: rotate(-90deg);
  width:10px;
  height:240px;
  display:block;
  position:relative;
  top:110px;
  left:110px;
}

</style>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.ZoneSpray.form.name" id="dss.vector.solutions.irs.ZoneSpray.form.id" method="POST">
  <dl>
    <mjl:input value="${item.sprayId}" type="hidden" param="id" />

    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="geoEntity"> ${item.geoEntity.displayString}  </mjl:dt>
      <mjl:dt attribute="brand"> ${brand.brandName} </mjl:dt>
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
      <mjl:dt attribute="surfaceType" >
        <c:if test="${surfaceType != null}">
          ${surfaceType.displayLabel}
        </c:if>
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
<span class="noprint dataTableButtons">
<button type="button" id="StatusCreate"> <fmt:message key="Create_New_Zone_Spray_Button"/> </button>
</span>


<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayTeamDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayStatusViewDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{ActorSprayStatusViewDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{TeamSprayStatusViewDTO.CLASS}))%>
<%
Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
map.put("StatusId", new ColumnSetup(true, false));
map.put("SprayData", new ColumnSetup(true, false));
map.put("Spray", new ColumnSetup(true, false));
map.put("TeamLabel", new ColumnSetup(true, false));
%>

<script type="text/javascript" defer="defer">

  var createButton = new YAHOO.widget.Button("StatusCreate", {
    type:"link",
    href:"dss.vector.solutions.irs.ZoneSprayController.search.mojo"
  });


teams = <%=request.getAttribute("teams")%>;
operators = <%=request.getAttribute("operators")%>;
<%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>
data = {
         rows:<%=Halp.getDataMap(rows, attributes, view)%>,
         columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, map)%>,
         defaults:<%=Halp.getDefaultValues(view, attributes)%>,
         
         div_id: "Status",
         data_type: "Mojo.$.<%=TeamSprayStatusViewDTO.CLASS%>",
         saveFunction:"applyAll",
         excelButtons:false,
         after_row_load:function(record){
             var team = record.getData('SprayTeam');
             var leaderId = record.getData('TeamLeader');
             
             if(team !== 'undefined' && leaderId !== null) {
               var row = data.myDataTable.getRecordIndex(record);
               var teamId = data.rows[row].SprayTeam;
               var leader = operators[teamId][leaderId];

               record.setData('TeamLeader', leader);
             }
         }
     };

     var validateSprayTeam = function(oData) {
       // Validate
       var selectedValues = data.myDataTable.getRecordSet().getRecords().map( function(record) {
	     return record.getData('SprayTeam');
       });
    
       if(selectedValues.indexOf(oData) !== -1) {
         return undefined;
       }
       else {
        return oData;
       }
     }

     var beforeRowAdd = function() {        
         YAHOO.util.Dom.get(data.div_id + 'Saverows-button').click();
     }
      
     var loadUnusedTeams = function(e){
         var column = data.myDataTable.getColumn('SprayTeam');
         var cell = e.editor.getTdEl();
         var currentTeam = e.editor.getRecord().getColumn('SprayTeam').getData();

         // Get a list of operators which already have data set for them
 	    var usedTeams = data.myDataTable.getRecordSet().getRecords().map( function(record) {
 		    return record.getData('SprayTeam');
 	    });


        usedTeams.filter(function(team){
          return team != currentTeam;
        });

         // Filter the list of possible operators by operators which have already been used
 	    var filteredLabels = SprayTeamLabels.filter(function(operator){
 		    return (usedTeams.indexOf(operator) === -1);
 	    });

         // Update the editor to use the list of valid operators
         e.editor.dropdownOptions = filteredLabels;

         selectEl = e.editor.dropdown;
         selectEl.innerHTML = "";
         selectedValue = e.editor.getRecord().getData('SprayTeam');

         // We have options to populate
         if(filteredLabels.length > 0) {
             // Create OPTION elements
             for(var i=0; i < filteredLabels.length; i++) {
                 var option = filteredLabels[i];
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
     

     var swap = function(e){
         var row = data.myDataTable.getRecordIndex(e.editor.getRecord());
         var teamId = data.rows[row].SprayTeam;

         var column = data.myDataTable.getColumn('TeamLeader');
         var cell = e.editor.getTdEl();
         
         TeamLeaderLabels=Mojo.Util.getValues(operators[teamId]);   
         TeamLeaderIds=Mojo.Util.getKeys(operators[teamId]);   

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

 	var indexHouseholds = 10;
	var indexStructures = 11;
	var indexSprayedHouseholds = 12;
	var indexSprayedStructures = 13;
	var indexPrevSprayedHouseholds = 14;
	var indexPrevSprayedStructures = 15;
	var indexRooms = 16;
	var indexPeople = 18;
	var indexBedNets = 19;
	var indexRoomsWithBedNets = 20;
	var indexLocked = 21;
	var indexRefused = 22;
	var indexOther = 23;
	
	var isMainSpray = <%= (spray.getSprayMethod().contains(dss.vector.solutions.irs.SprayMethodDTO.MAIN_SPRAY)) ? 1 : 0 %>;

    if (!isMainSpray)
    {
    	delete data.columnDefs[indexHouseholds].editor;
    	delete data.columnDefs[indexStructures].editor;
    	delete data.columnDefs[indexPrevSprayedHouseholds].editor;
    	delete data.columnDefs[indexPrevSprayedStructures].editor;
    	delete data.columnDefs[indexRooms].editor;
    	delete data.columnDefs[indexPeople].editor;
    	delete data.columnDefs[indexBedNets].editor;
    	delete data.columnDefs[indexRoomsWithBedNets].editor;
    	delete data.columnDefs[indexLocked].editor;
    	delete data.columnDefs[indexRefused].editor;
    	delete data.columnDefs[indexOther].editor;    	    	
    }    	
     
     
     SprayTeamLabels=Mojo.Util.getValues(teams);   
     SprayTeamIds=Mojo.Util.getKeys(teams);   
 
     data.columnDefs[2].editor = new YAHOO.widget.DropdownCellEditor({dropdownOptions:SprayTeamLabels,disableBtns:true,validator:validateSprayTeam});
     data.columnDefs[2].save_as_id = true;
     data.columnDefs[2].editor.subscribe('showEvent', loadUnusedTeams);     

     data.columnDefs[3].editor = new YAHOO.widget.DropdownCellEditor({dropdownOptions:[],disableBtns:true});
     data.columnDefs[3].editor.subscribe('showEvent', swap);
     
     MojoGrid.createDataTable(data);



</script>