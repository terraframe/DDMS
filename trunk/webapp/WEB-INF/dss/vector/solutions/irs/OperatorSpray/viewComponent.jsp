<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  HouseholdSprayStatusViewDTO view = new HouseholdSprayStatusViewDTO(clientRequest);

  OperatorSprayViewDTO spray = ((OperatorSprayViewDTO) request.getAttribute("item"));
  spray.setModified(true);
  spray.setModified(OperatorSprayViewDTO.SPRAYID, true);

  HouseholdSprayStatusViewDTO[] rows = spray.getStatus();

  String[] attributes = {"StatusId", "Spray", "HouseholdId", "StructureId", "Households", "Structures",
       "SprayedHouseholds", "SprayedStructures", "PrevSprayedHouseholds", "PrevSprayedStructures",
       "Rooms", "SprayedRooms", "People", "BedNets", "RoomsWithBedNets", "Locked", "Refused", "Other"};

  String deleteColumn = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
%>


<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.irs.HouseholdSprayStatusViewDTO"%>
<%@page import="dss.vector.solutions.irs.OperatorSprayViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%><mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.OperatorSpray.form.name" id="dss.vector.solutions.irs.OperatorSpray.form.id" method="POST">
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
      <mjl:dt attribute="teamSprayWeek"> ${item.teamSprayWeek} </mjl:dt>
      <mjl:dt attribute="target"> ${item.target} </mjl:dt>
      <mjl:dt attribute="operatorSprayWeek"> ${item.operatorSprayWeek} </mjl:dt>
      <mjl:dt attribute="received"> ${item.received} </mjl:dt>
      <mjl:dt attribute="refills"> ${item.refills} </mjl:dt>
      <mjl:dt attribute="returned"> ${item.returned} </mjl:dt>
      <mjl:dt attribute="used"> ${item.used} </mjl:dt>
    </mjl:component>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.OperatorSprayController.edit.mojo" name="dss.vector.solutions.irs.OperatorSpray.form.edit.button" />
</mjl:form>

<h2><fmt:message key="Households_Sprayed"/></h2>
<div id="Status"></div>
<div id="buttons" class="noprint"><span id="StatusAddrow" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="New_Row"/></button>
</span> </span> <span id="StatusSaverows" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="Save_Rows_To_DB"/></button>
</span> </span> <a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a></div>

<script type="text/javascript">

    <%
      String[] types_to_load =
        {


          "dss.vector.solutions.irs.SprayStatusView"
        };
      out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId(), types_to_load, true));

      String[] types_to_load2 =
      {
        "dss.vector.solutions.irs.HouseholdSprayStatusView"
      };
    out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId(), types_to_load2, true));
    %>
    <%=Halp.getDropdownSetup(view, attributes, deleteColumn, clientRequest)%>


    data = {
              rows:<%=Halp.getDataMap(rows, attributes, view)%>,
              columnDefs:<%=Halp.getColumnSetup(view, attributes, deleteColumn, true, 2)%>,
              defaults: {"Spray":'<%=spray.getSprayId()%>'},
              div_id: "Status",
              data_type: "Mojo.$.dss.vector.solutions.irs.HouseholdSprayStatusView",
              saveFunction:"applyAll",
              width:"65em"
          };
    document.addEventListener('load', MojoGrid.createDataTable(data), false);

</script>

