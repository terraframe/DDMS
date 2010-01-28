<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  OperatorSprayViewDTO spray = ((OperatorSprayViewDTO) request.getAttribute("item"));

  HouseholdSprayStatusViewDTO[] rows = (HouseholdSprayStatusViewDTO[]) request.getAttribute("status");
  HouseholdSprayStatusViewDTO view = new HouseholdSprayStatusViewDTO(clientRequest);
  view.setValue(HouseholdSprayStatusViewDTO.SPRAY, spray.getConcreteId());
  
  // If the order of these attributes are changed, you need to change the javascript indexes at the bottom!
  String[] attributes = {"ConcreteId", "Spray", "HouseholdId", "StructureId", "Households", "Structures",
       "SprayedHouseholds", "SprayedStructures", "PrevSprayedHouseholds", "PrevSprayedStructures",
       "Rooms", "SprayedRooms", "People", "BedNets", "RoomsWithBedNets", "Locked", "Refused", "Other"};

  String deleteColumn = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
  
  Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
  map.put("ConcreteId", new ColumnSetup(true, false));
  map.put("Spray", new ColumnSetup(true, false));
  map.put("Households", new ColumnSetup(true, false));    
  map.put("Structures", new ColumnSetup(true, false));
  map.put("SprayedHouseholds", new ColumnSetup(false, true, "validateValue", null, null));    
  map.put("SprayedStructures", new ColumnSetup(false, true, "validateValue", null, null));    
  map.put("PrevSprayedHouseholds", new ColumnSetup(false, true, "validateValue", null, null));    
  map.put("PrevSprayedStructures", new ColumnSetup(false, true, "validateValue", null, null));
%>


<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.irs.HouseholdSprayStatusViewDTO"%>
<%@page import="dss.vector.solutions.irs.OperatorSprayViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>

<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="java.util.HashMap"%>


<%@page import="java.util.Arrays"%><style type="text/css">
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
      <mjl:dt attribute="geoEntity"> ${item.geoEntity.displayString} </mjl:dt>
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
      <mjl:dt attribute="sprayOperator">
        ${item.sprayOperator.person.lastName}, ${item.sprayOperator.person.firstName}
      </mjl:dt>
                
      <mjl:dt attribute="teamLeader">
        <c:if test="${item.teamLeader != null}">
          ${item.teamLeader.person.lastName}, ${item.teamLeader.person.firstName}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="surfaceType" >
        <c:if test="${surfaceType != null}">
          ${surfaceType.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="teamSprayWeek"> ${item.teamSprayWeek} </mjl:dt>
      <mjl:dt attribute="target"> ${item.target} </mjl:dt>
      <mjl:dt attribute="operatorSprayWeek"> ${item.operatorSprayWeek} </mjl:dt>
      <mjl:dt attribute="received"> ${item.received} </mjl:dt>
      <mjl:dt attribute="refills"> ${item.refills} </mjl:dt>
      <mjl:dt attribute="returned"> ${item.returned} </mjl:dt>
      <mjl:dt attribute="used"> ${item.used} </mjl:dt>
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

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){ 
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>

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
      grid.persistData();
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
            data: data,
            
            onSuccess: function(ids)
            {
              var householdId = ids[0];
              
              for(var i = 0; i < rows; i++) {
                var event = this.grid.addRow();
                var record = event.getValue().record;
                var index = event.getValue().index;
                  
                record.setData("HouseholdId", householdId);
                record.setData("StructureId", i);
                
                this.data.rows[index]['HouseholdId'] = householdId;
                this.data.rows[index]['StructureId'] = i;
              }
              
              this.data.myDataTable.render();
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
  
    var isMainSpray = <%= (spray.getSprayMethod().contains(dss.vector.solutions.irs.SprayMethodDTO.MAIN_SPRAY)) ? 1 : 0 %>;
  
    data = {
      rows:<%=Halp.getDataMap(rows, attributes, view)%>,
      columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, map)%>,
      defaults:<%=Halp.getDefaultValues(view, attributes)%>,
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

