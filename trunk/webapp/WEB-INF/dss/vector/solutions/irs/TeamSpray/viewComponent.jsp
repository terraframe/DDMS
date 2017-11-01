<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
%>

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.irs.TeamSprayViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.OperatorSprayStatusViewDTO"%>

<%@page import="com.runwaysdk.business.ViewDTO"%>


<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.yui.ColumnSetup"%>
<%@page import="java.util.HashMap"%>


<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%><style type="text/css">
.yui-skin-sam .yui-dt th, .yui-skin-sam .yui-dt th a
{
  vertical-align:bottom;
  background-color:#DDDDDD;
  background:none;
}

.yui-dt-label
{
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

<c:set var="page_title" value="View_Team_Spray" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.TeamSpray.form.name" id="dss.vector.solutions.irs.TeamSpray.form.id" method="POST">
  <dl>
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />      
    
    <mjl:component item="${item}" param="dto">
      <table class="irs_header">
        <tr>
          <td>
            <mjl:dt attribute="geoEntity"> ${item.geoEntity.displayString} </mjl:dt>
          </td>
          <td>
            <mjl:dt attribute="supervisor"> ${person.firstName} ${person.lastName} </mjl:dt>
          </td>                  
          <td>
            <mjl:dt attribute="sprayTeam"> ${item.sprayTeam.teamId} </mjl:dt>                   
          </td>
        </tr>
        <tr>
          <td>
            <mjl:dt attribute="brand"> ${brand.productName.termDisplayLabel.value} </mjl:dt>
          </td>
          <td>
            <mjl:dt attribute="teamLeader"> ${item.teamLeader.person.lastName}, ${item.teamLeader.person.firstName} </mjl:dt>
          </td>
        </tr>
        <tr>
          <td>
            <mjl:dt attribute="sprayDate"> <span id="testDateSpan" class="formatDate"> ${item.sprayDate} </span> </mjl:dt>
          </td>
          <td>
            <mjl:dt attribute="sprayMethod">
              <ul>
                <c:forEach var="enumName" items="${item.sprayMethodEnumNames}">
                  <li>${item.sprayMethodMd.enumItems[enumName]}</li>
                </c:forEach>
              </ul>            
            </mjl:dt>
          </td>
        </tr>
        <tr>
          <td>
            <mjl:dt attribute="target"> ${item.target} </mjl:dt>
          </td>
          <td>
            <mjl:dt attribute="surfaceType" >          
              <c:if test="${surfaceType != null}"> ${surfaceType.displayLabel} </c:if>
            </mjl:dt>        
          </td>
        </tr>
      </table>     
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.irs.TeamSprayController.edit.mojo" name="dss.vector.solutions.irs.TeamSpray.form.edit.button" />
  </dl>  
</mjl:form>

<h2><mdss:localize key="Operator_Sprays"/></h2>
<div id="Status"></div>
<span class="noprint dataTableButtons">
<button type="button" id="StatusCreate"> <mdss:localize key="Create_New_Team_Spray_Button"/> </button>
</span>

<%=Halp.loadTypes(Arrays.asList(new String[]{OperatorSprayStatusViewDTO.CLASS}))%>

<%
DataGrid grid = (DataGrid) request.getAttribute("grid");
TeamSprayViewDTO view = (TeamSprayViewDTO) request.getAttribute("item");
%>


<script type="text/javascript">
(function(){
    YAHOO.util.Event.onDOMReady(function(){

    <%=grid.getDropDownMap()%>
    
    var createButton = new YAHOO.widget.Button("StatusCreate", {type:"link", href:"dss.vector.solutions.irs.TeamSprayController.search.mojo"});

    operators = <%=request.getAttribute("operators")%>;
    
    var genericLteCompare = function(sNewValue, oDataTable, compareColumnName)
    {
      if (sNewValue === "") { return ""; }
      
      var nCompare = Number.parseInt(oDataTable.getRecord().getData()[compareColumnName]);
      var nNewValue = Number.parseInt(sNewValue);
      
      if (Number.isNaN(nNewValue))
      {
        alert(MDSS.localize("IRS_CRUD_Not_A_Number"));
        return undefined;
      }
      if (Number.isNaN(nCompare))
      {
        if (nNewValue < 0)
        {
          alert(MDSS.localize("IRS_CRUD_GTE_0"));
          return undefined;
        }
        else
        {
          return sNewValue;
        }
      }
      if (!(nNewValue <= nCompare))
      {
        var compareLabel = oDataTable.getDataTable().getColumn(compareColumnName).label;
        var thisLabel = oDataTable.getColumn().label;
        
        alert("[" + thisLabel + "] " + MDSS.localize("IRS_CRUD_MUST_BE_LTE") + " [" + compareLabel + "] ")
        return undefined;
      }
      if (nNewValue < 0)
      {
        alert(MDSS.localize("IRS_CRUD_GTE_0"));
        return undefined;
      }
      
      return sNewValue;
    }
    
    var gteZero = function(sNewValue, sOldValue, oDataTable)
    {
      if (sNewValue === "") { return ""; }
      
      var nNewValue = Number.parseInt(sNewValue);
      
      if (Number.isNaN(nNewValue))
      {
        alert(MDSS.localize("IRS_CRUD_Not_A_Number"));
        return undefined;
      }
      else if (!(nNewValue >= 0))
      {
        alert(MDSS.localize("IRS_CRUD_GTE_0"));
        return undefined;
      }
      
      return sNewValue;
    }
    
    var lteStructures = function(sNewValue, sOldValue, oDataTable)
    {
      return genericLteCompare(sNewValue, oDataTable, "Structures");
    }
    
    var ltePeopleProtected = function(sNewValue, sOldValue, oDataTable)
    {
      return genericLteCompare(sNewValue, oDataTable, "People");
    }
    
    var lteFemalesProtected = function(sNewValue, sOldValue, oDataTable)
    {
      return genericLteCompare(sNewValue, oDataTable, "NumberFemalesProtected");
    }
    
    var lteRooms = function(sNewValue, sOldValue, oDataTable)
    {
      return genericLteCompare(sNewValue, oDataTable, "Rooms");
    }
    
    var lteItns = function(sNewValue, sOldValue, oDataTable)
    {
      return genericLteCompare(sNewValue, oDataTable, "BedNets");
    }
    
    var ltePeopleSleepingUnderItns = function(sNewValue, sOldValue, oDataTable)
    {
      return genericLteCompare(sNewValue, oDataTable, "NumberPeopleSleepingUnderItns");
    }
    
    data = {
      rows:<%=grid.getData()%>,
      columnDefs:<%=grid.getColumnSetupWithDelete()%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "Status",
      data_type: "Mojo.$.<%=OperatorSprayStatusViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      after_row_load:function(record) {
        var label = record.getData('OperatorLabel');
        
        record.setData('SprayOperator', label);
      }
    };

    var beforeRowAdd = function(event) {
      if(event.getType() == MDSS.Event.BEFORE_ROW_ADD) {
        YAHOO.util.Dom.get(data.div_id + 'Saverows-button').click();
      }        
    }
    
    var validateSprayOperator = function(oData) {
        // Validate
      var selectedValues = dataTable.getRecordSet().getRecords().map( function(record) {
        return record.getData('SprayOperator');
      });
        
      if(selectedValues.indexOf(oData) !== -1) {
        return undefined;
      }
      else {
        return oData;
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
    
    var loadUnusedOperators = function(e){
      var column = dataTable.getColumn('SprayOperator');
      var cell = e.editor.getTdEl();

      var _record = e.editor.getRecord();
      var currentOperator = _record.getData('SprayOperator');     

      // Get a list of operators which already have data set for them
      var usedOperators = dataTable.getRecordSet().getRecords().map( function(record) {
        return record.getData('SprayOperator');
      });

      // Filter the list of possible operators by operators which have already been used
      var filteredLabels = SprayOperatorLabels.filter(function(operator){
        return (usedOperators.indexOf(operator) === -1);
      });

      // Include the current team in the list of selectable teams
      if(currentOperator != null && currentOperator != "") {
        filteredLabels.unshift(currentOperator);
      }

      // Update the editor to use the list of valid operators
      e.editor.dropdownOptions = filteredLabels;

      selectEl = e.editor.dropdown;
      selectEl.innerHTML = "";
      selectedValue = e.editor.getRecord().getData('SprayOperator');

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
       
    SprayOperatorLabels=Mojo.Util.getValues(operators);
    SprayOperatorIds=Mojo.Util.getKeys(operators);   

    var i = getColumnIndex('SprayOperator');
     
    data.columnDefs[i].editor = new YAHOO.widget.DropdownCellEditor({dropdownOptions:SprayOperatorLabels,disableBtns:true,validator:validateSprayOperator});
    data.columnDefs[i].save_as_id = true;
    data.columnDefs[i].editor.subscribe('showEvent', loadUnusedOperators);

    var grid = MojoGrid.createDataTable(data);
    grid.addListener(beforeRowAdd);

    var dataTable = grid.getDataTable();
    
  });
})();
    
</script>
