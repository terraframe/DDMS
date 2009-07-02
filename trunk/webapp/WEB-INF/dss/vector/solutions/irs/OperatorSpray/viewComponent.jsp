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
<c:set var="page_title" value="View_Operator_Spray" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.OperatorSpray.form.name" id="dss.vector.solutions.irs.OperatorSpray.form.id" method="POST">
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
<div id="Status"></div>

<mjl:commandLink action="dss.vector.solutions.irs.OperatorSprayController.search.mojo" name="OperatorSprayController.search"><fmt:message key="Operator_Spray_Create_link" /></mjl:commandLink>

<script type="text/javascript">

    <%
      out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId(), new String[]{SprayStatusViewDTO.CLASS}, true));
      out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId(), new String[]{HouseholdSprayStatusViewDTO.CLASS}, true));
    %>
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>

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
              columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, 2)%>,
              defaults: {"Spray":'<%=spray.getSprayId()%>'},
              div_id: "Status",
              data_type: "Mojo.$.<%=HouseholdSprayStatusViewDTO.CLASS%>",
              saveFunction:"applyAll",
              width:"65em",
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

