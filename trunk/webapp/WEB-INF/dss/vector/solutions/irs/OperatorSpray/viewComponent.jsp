<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.irs.HouseholdSprayStatusViewDTO"%>
<%@page import="dss.vector.solutions.irs.OperatorSprayViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>

<style type="text/css">
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


<c:set var="page_title" value="View_Operator_Spray" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.OperatorSpray.form.name" id="dss.vector.solutions.irs.OperatorSpray.form.id" method="POST">
  <dl>
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />

    <mjl:component item="${item}" param="dto">
      <table class="irs_header">
        <tr>
        <td>
          <mjl:dt attribute="geoEntity"> ${item.geoEntity.displayString} </mjl:dt>
        </td>
        <td>
          <mjl:dt attribute="teamLeader">
            <c:if test="${item.teamLeader != null}">
              ${item.teamLeader.person.lastName}, ${item.teamLeader.person.firstName}
            </c:if>
          </mjl:dt>
        </td>
        <td>
          <mjl:dt attribute="received"> ${item.received} </mjl:dt>
        </td>
      </tr>
      <tr>
        <td>
          <mjl:dt attribute="brand"> ${brand.brandName} </mjl:dt>
        </td>
        <td>
          <mjl:dt attribute="sprayOperator"> ${item.sprayOperator.person.lastName}, ${item.sprayOperator.person.firstName} </mjl:dt>
        </td>
        <td>
          <mjl:dt attribute="refills"> ${item.refills} </mjl:dt>
        </td>
      </tr>
      <tr>
        <td>
          <mjl:dt attribute="sprayDate">
            <span id="testDateSpan" class="formatDate">${item.sprayDate}</span>
          </mjl:dt>
        </td>
        <td>
          <mjl:dt attribute="target"> ${item.target} </mjl:dt>
        </td>
        <td>
          <mjl:dt attribute="returned"> ${item.returned} </mjl:dt>
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
          <mjl:dt attribute="teamSprayWeek"> ${item.teamSprayWeek} </mjl:dt>
        </td>
        <td>
          <mjl:dt attribute="used"> ${item.used} </mjl:dt>
        </td>
      </tr>
      <tr>
        <td>
          <mjl:dt attribute="surfaceType" >
            <c:if test="${surfaceType != null}">
              ${surfaceType.displayLabel}
            </c:if>
          </mjl:dt>
        </td>
        <td>
          <mjl:dt attribute="operatorSprayWeek"> ${item.operatorSprayWeek} </mjl:dt>
        </td>
      </tr>
    </table>
  </mjl:component>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.OperatorSprayController.edit.mojo" name="dss.vector.solutions.irs.OperatorSpray.form.edit.button" />
</dl>
</mjl:form>

<h2><fmt:message key="Households_Sprayed"/></h2>
<div id="Status">
</div>
<span class="noprint dataTableButtons">
  <input type="text" id="#strucutresInput" size="5" value="1" />  
  <button type="button" id="NewRow"> <fmt:message key="New_Rows"/> </button>
  <button type="button" id="StatusCreate"> <fmt:message key="Create_New_Operator_Spray_Button"/> </button>
</span>


<%=Halp.loadTypes(Arrays.asList(new String[]{HouseholdSprayStatusViewDTO.CLASS}))%>

<%
DataGrid grid = (DataGrid) request.getAttribute("grid");
OperatorSprayViewDTO view = (OperatorSprayViewDTO) request.getAttribute("item");
%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    <%=grid.getDropDownMap()%>

    var structuresInput = document.getElementById('#strucutresInput');

    var createButton = new YAHOO.widget.Button("StatusCreate", {type:"link", href:"dss.vector.solutions.irs.OperatorSprayController.search.mojo"});
    var newRowButton = new YAHOO.widget.Button("NewRow");

    var addRows = false;
    
    var validateValue = function(oData) {
        var re = /^[0-1]$/;
        
        // Validate
        if(re.test(oData) || oData === "") {
            return oData;
        }
        else {
            alert(MDSS.localize("Value_Not_0_1"));
            return undefined;
        }
    }

    var validateStructure = function(oData) {
      var re = /^[1]$/;
        
      // Validate
      if(re.test(oData) || oData === "") {
        return oData;
      }
      else {
        alert(MDSS.localize("Value_Not_1"));
        return undefined;
      }
    }

    var beforeRowHandler = function() {
      addRows = true;
      // Save the existing data before adding new rows
      grid.save();
    }

    var dataListener = function(event) {
      // Only add the new rows on a successful save
      if(event.getType() == MDSS.Event.AFTER_SAVE) {          
        if(addRows == true) {
          addNewRows();
        }
      }
      else if (event.getType() == MDSS.Event.AFTER_PROBLEM) {
        addRows = false;
      }
      else if (event.getType() == MDSS.Event.AFTER_FAILURE) {
        addRows = false;
      }
    }

    var addNewRows = function() {
      var rows = structuresInput.value * 1;
      if(Mojo.Util.isNumber(rows) && rows > 0) {
        var request = new MDSS.Request({
            grid: grid,            
            onSuccess: function(ids)  {
              var householdId = ids[0];
              
              for(var i = 0; i < rows; i++) {
                var event = this.grid.addRow();
                var record = event.getValue().record;
                var index = event.getValue().index;
                var structureId = householdId + "-" + (i + 1);                
                  
                record.setData("HouseholdId", householdId);
                record.setData("StructureId", structureId);

                var model = grid.getModel();
                
                model.setData(index, 'HouseholdId', householdId);
                model.setData(index, 'StructureId', structureId);                
              }
              
              grid.getDataTable().render();
            }
          });

         Mojo.$.dss.vector.solutions.irs.HouseholdSprayStatusView.getGeneratedIds(request);
         addRows = false;         
      }        
    }
    
    var indexHouseholds = 4;
    var indexStructures = 5;
    var indexSprayedHouseholds = 6;
    var indexSprayedStructures = 7;
    var indexPrevSprayedHouseholds = 8;
    var indexPrevSprayedStructures = 9;
    var indexRooms = 10;
    var indexPeople = 12;
    var indexBedNets = 13;
    var indexRoomsWithBedNets = 14;
    var indexLocked = 15;
    var indexRefused = 16;
    var indexOther = 17;
  
    var isMainSpray = <%= (view.getSprayMethod().contains(dss.vector.solutions.irs.SprayMethodDTO.MAIN_SPRAY)) ? 1 : 0 %>;
  
    data = {
      rows:<%=grid.getData()%>,
      columnDefs:<%=grid.getColumnSetupWithDelete()%>,
      defaults:<%=grid.getDefaultValues()%>,
      div_id: "Status",
      data_type: "Mojo.$.<%=HouseholdSprayStatusViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false,
      addButton:false
    };

    if (!isMainSpray) {
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

    var grid = MojoGrid.createDataTable(data);
    grid.addListener(dataListener);
    
    YAHOO.util.Event.on('NewRow', 'click', beforeRowHandler);       
  });
})();
</script>

