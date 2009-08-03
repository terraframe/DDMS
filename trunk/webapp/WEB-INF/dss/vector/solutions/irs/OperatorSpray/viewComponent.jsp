<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  HouseholdSprayStatusViewDTO view = new HouseholdSprayStatusViewDTO(clientRequest);

  OperatorSprayViewDTO spray = ((OperatorSprayViewDTO) request.getAttribute("item"));
  HouseholdSprayStatusViewDTO[] rows = (HouseholdSprayStatusViewDTO[]) request.getAttribute("status");

  // If the order of these attributes are changed, you need to change the javascript indexes at the bottom!
  String[] attributes = {"StatusId", "Spray", "HouseholdId", "StructureId", "Households", "Structures",
       "SprayedHouseholds", "SprayedStructures", "PrevSprayedHouseholds", "PrevSprayedStructures",
       "Rooms", "SprayedRooms", "People", "BedNets", "RoomsWithBedNets", "Locked", "Refused", "Other"};

  String deleteColumn = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
%>


<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.irs.HouseholdSprayStatusViewDTO"%>
<%@page import="dss.vector.solutions.irs.OperatorSprayViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.SprayStatusViewDTO"%>

<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="java.util.HashMap"%>

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
    <mjl:input value="${item.sprayId}" type="hidden" param="id" />

    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="geoEntity"> ${item.geoEntity.geoId} </mjl:dt>
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
<button type="button" id="StatusCreate"> <fmt:message key="Create_New_Operator_Spray_Button"/> </button>
</span>


<script type="text/javascript" defer="defer">

    var createButton = new YAHOO.widget.Button("StatusCreate", 
      {
        type:"link",
        href:"dss.vector.solutions.irs.OperatorSprayController.search.mojo"
      });

    <%
      Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
      map.put("StatusId", new ColumnSetup(true, false));
      map.put("Spray", new ColumnSetup(true, false));
      map.put("SprayData", new ColumnSetup(true, false));
      map.put("Households", new ColumnSetup(false, true, "validateValue", null, null));    
      map.put("Structures", new ColumnSetup(false, true, "validateStructure", null, null));    
      map.put("SprayedHouseholds", new ColumnSetup(false, true, "validateValue", null, null));    
      map.put("SprayedStructures", new ColumnSetup(false, true, "validateValue", null, null));    
      map.put("PrevSprayedHouseholds", new ColumnSetup(false, true, "validateValue", null, null));    
      map.put("PrevSprayedStructures", new ColumnSetup(false, true, "validateValue", null, null));    

      out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId(), new String[]{SprayStatusViewDTO.CLASS}, true));
      out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId(), new String[]{HouseholdSprayStatusViewDTO.CLASS}, true));
    %>
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>

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

    var beforeRowAdd = function() {        
        YAHOO.util.Dom.get(data.div_id + 'Saverows-button').click();
    }

    var afterRowAdd = function(record, index) {

    	var request = new MDSS.Request({
          record: record,
          index: index,
          data: data,
          onSend: function(){},
          onComplete: function(){},
          onSuccess: function(ids)
          {
        	  this.record.setData("HouseholdId", ids[0]);
        	  this.record.setData("StructureId", ids[1]);    
        	  this.data.rows[index]['HouseholdId'] = ids[0];
        	  this.data.rows[index]['StructureId'] = ids[0];

        	  this.data.myDataTable.render();
          }
      });

      Mojo.$.dss.vector.solutions.irs.HouseholdSprayStatusView.getGeneratedIds(request);        
    }
    
	var indexHouseholds = 4;
	var indexStructures = 5;
	var indexSprayedHouseholds = 6;
	var indexSprayedStructures = 7;
	var indexPrevSprayedHouseholds = 8;
	var indexPrevSprayedStructures = 9;
	var indexRooms = 10;
	var isMainSpray = <%= (spray.getSprayMethod().contains(dss.vector.solutions.irs.SprayMethodDTO.MAIN_SPRAY)) ? 1 : 0 %>;
	
    data = {
              rows:<%=Halp.getDataMap(rows, attributes, view)%>,
              columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, map)%>,
              defaults: {"Spray":'<%=spray.getSprayId()%>'},
              div_id: "Status",
              data_type: "Mojo.$.<%=HouseholdSprayStatusViewDTO.CLASS%>",
              saveFunction:"applyAll",
              excelButtons:false
          };

    if (isMainSpray) {
    	//delete data.columnDefs[indexHouseholds].editor;
    	//delete data.columnDefs[indexStructures].editor;
    	data.defaults.Households = 1;
    	data.defaults.Structures = 1;
    } else {
    	delete data.columnDefs[indexHouseholds].editor;
    	delete data.columnDefs[indexStructures].editor;
    	delete data.columnDefs[indexPrevSprayedHouseholds].editor;
    	delete data.columnDefs[indexPrevSprayedStructures].editor;
    	delete data.columnDefs[indexRooms].editor;
    }    
    
    document.addEventListener('load', MojoGrid.createDataTable(data), false);

</script>

