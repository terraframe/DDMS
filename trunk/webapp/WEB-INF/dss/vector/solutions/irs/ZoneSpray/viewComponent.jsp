<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.irs.ZoneSprayViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.TeamSprayStatusViewDTO"%>


<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.irs.SprayTeamDTO"%>

<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.yui.ColumnSetup"%>
<%@page import="java.util.HashMap"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
<%@page import="javax.xml.crypto.Data"%><c:set var="page_title" value="View_Zone_Spray"  scope="request"/>

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
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />

 <mjl:component item="${item}" param="dto">
  <table class="irs_header">
    <tr>
      <td>
        <mjl:dt attribute="geoEntity"> ${item.geoEntity.displayString}</mjl:dt>
      </td>
      <td>
        <mjl:dt attribute="supervisor"> ${person.firstName} ${person.lastName} </mjl:dt>
      </td>      
    </tr>
    <tr>
      <td>
        <mjl:dt attribute="brand"> ${brand.productName.termDisplayLabel.value} </mjl:dt>
      </td>
      <td>
        <mjl:dt attribute="sprayDate">
          <span id="testDateSpan" class="formatDate">${item.sprayDate}</span>
        </mjl:dt>          
      </td>
    </tr>
    <tr>
      <td>
        <mjl:dt attribute="sprayMethod">
          <ul>
            <c:forEach var="enumName" items="${item.sprayMethodEnumNames}">
              <li>${item.sprayMethodMd.enumItems[enumName]}</li>
            </c:forEach>
          </ul>            
        </mjl:dt>
      </td>
      <td>
        <mjl:dt attribute="surfaceType" >
          <c:if test="${surfaceType != null}">
            ${surfaceType.displayLabel}
          </c:if>
        </mjl:dt>
      </td>
    </tr>    
  </table>
</mjl:component>
    
    <mdss:localize key="Edit" var="Localized_Edit" />
    
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.irs.ZoneSprayController.edit.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.edit.button" />
  </dl>
</mjl:form>

<h2><mdss:localize key="Team_Sprays"/></h2>
<div id="Status"></div>
<span class="noprint dataTableButtons">
<button type="button" id="StatusCreate"> <mdss:localize key="Create_New_Zone_Spray_Button"/> </button>
</span>


<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayTeamDTO.CLASS, TeamSprayStatusViewDTO.CLASS}))%>

<%
DataGrid grid = (DataGrid) request.getAttribute("grid");
ZoneSprayViewDTO view = (ZoneSprayViewDTO) request.getAttribute("item");
%>

<script type="text/javascript">
(function(){
    YAHOO.util.Event.onDOMReady(function(){
      var createButton = new YAHOO.widget.Button("StatusCreate", {type:"link", href:"dss.vector.solutions.irs.ZoneSprayController.search.mojo"});
      
      teams = <%=request.getAttribute("teams")%>;
      operators = <%=request.getAttribute("operators")%>;
      
      data = {
        rows:<%=grid.getData()%>,
        columnDefs:<%=grid.getColumnSetupWithDelete()%>,
        defaults:<%=grid.getDefaultValues()%>,         
        div_id: "Status",
        data_type: "Mojo.$.<%=TeamSprayStatusViewDTO.CLASS%>",
        saveFunction:"applyAll",
        excelButtons:false,
        after_row_load:function(record){
          // Overwrite the team leader label
          var dataTable = this.getDataTable();
          var model = this.getModel();
          
          var team = record.getData('SprayTeam');
          var leaderId = record.getData('TeamLeader');
             
          if(team !== 'undefined' && leaderId !== null) {
            var row = dataTable.getRecordIndex(record);
            var teamId = model.getData(row, 'SprayTeam');
            var leader = operators[teamId][leaderId];

            record.setData('TeamLeader', leader);
          }

          // Overwrite the team label
          var label = record.getData('TeamLabel');
          record.setData('SprayTeam', label);
        }
     };

     var validateSprayTeam = function(oData) {
       // Validate
       var selectedValues = dataTable.getRecordSet().getRecords().map( function(record) {
         return record.getData('SprayTeam');
       });
    
       if(selectedValues.indexOf(oData) !== -1) {
         return undefined;
       }
       else {
        return oData;
       }
     }

     var beforeRowAdd = function(event) {
       if(event.getType() == MDSS.Event.BEFORE_ROW_ADD) {
         YAHOO.util.Dom.get(data.div_id + 'Saverows-button').click();
       }        
     }
      
     var loadUnusedTeams = function(e){
       var column = dataTable.getColumn('SprayTeam');
       var cell = e.editor.getTdEl();

       var _record = e.editor.getRecord();
       var currentTeam = _record.getData('SprayTeam');

       // Get a list of operators which already have data set for them
       var usedTeams = dataTable.getRecordSet().getRecords().map( function(record) {
         return record.getData('SprayTeam');
       });

       // Filter the list of possible teams by teams which have already been used
       var filteredLabels = SprayTeamLabels.filter(function(operator){
         return (usedTeams.indexOf(operator) === -1);
       });

       // Include the current team in the list of selectable teams
       if(currentTeam != null && currentTeam != "") {
         filteredLabels.unshift(currentTeam);
       }

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
       var row = dataTable.getRecordIndex(e.editor.getRecord());
       var teamId = grid.getModel().getData(row, 'SprayTeam');

       var column = dataTable.getColumn('TeamLeader');
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

     var getColumnIndex = function(key) {
       for(var i = 0; i < data.columnDefs.length; i++) {
         if(data.columnDefs[i].key == key) {
           return i;
         }
       }

       return null;
     }
       
        
     
     SprayTeamLabels=Mojo.Util.getValues(teams);   
     SprayTeamIds=Mojo.Util.getKeys(teams);   

     var sprayTeamIndex = getColumnIndex('SprayTeam');
     var teamLeaderIndex = getColumnIndex('TeamLeader');

     var sprayTeamColumnDef = data.columnDefs[sprayTeamIndex];
     
     sprayTeamColumnDef.editor = new YAHOO.widget.DropdownCellEditor({dropdownOptions:SprayTeamLabels,disableBtns:true,validator:validateSprayTeam});
     sprayTeamColumnDef.save_as_id = true;
     sprayTeamColumnDef.editor.subscribe('showEvent', loadUnusedTeams);

     if(teamLeaderIndex != null) {
       var teamLeaderColumnDef = data.columnDefs[teamLeaderIndex];

       teamLeaderColumnDef.editor = new YAHOO.widget.DropdownCellEditor({dropdownOptions:[],disableBtns:true});
       teamLeaderColumnDef.editor.subscribe('showEvent', swap);
     }
     
     var grid = MojoGrid.createDataTable(data);
     grid.addListener(beforeRowAdd);

     var dataTable = grid.getDataTable();
  });
})();
</script>