<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  InsecticideBrandViewDTO brandDTO = new InsecticideBrandViewDTO(clientRequest);
  InsecticideBrandViewDTO[] brandRows = InsecticideBrandViewDTO.getAll(clientRequest);
  String[] brandAttributes = {"InsecticdeId", "ActiveIngredient", "Amount", "Units", "Weight", "SachetsPerRefill", "Enabled"};

  NozzleViewDTO nozzleDTO = new NozzleViewDTO(clientRequest);
  NozzleViewDTO[] nozzleRows = NozzleViewDTO.getAll(clientRequest);
  String[] nozzleAttributes = {"NozzleId", "DisplayLabel", "Ratio", "Enabled"};

  InsecticideNozzleViewDTO insecticideNozzleDTO = new InsecticideNozzleViewDTO(clientRequest);
  InsecticideNozzleViewDTO[] insecticideNozzleRows = InsecticideNozzleViewDTO.getAll(clientRequest);
  String[] insecticideNozzleAttributes = {"InsecticideNozzleId", "Brand", "Nozzle", "Enabled"};

  AreaStandardsViewDTO standards = AreaStandardsViewDTO.getMostRecent(clientRequest);

  String deleteColumn = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
%>

<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.InsecticideBrandViewDTO"%>
<%@page import="dss.vector.solutions.irs.NozzleViewDTO"%>
<%@page import="dss.vector.solutions.irs.InsecticideNozzleViewDTO"%>
<%@page import="dss.vector.solutions.irs.AreaStandardsDTO"%>
<%@page import="dss.vector.solutions.irs.AreaStandardsViewDTO"%><br />

<h2><fmt:message key="Insecticide_Brand"/></h2>
<div id="InsecticideBrand"></div>
<div id="buttons" class="noprint"><span id="InsecticideBrandAddrow" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="New_Row"/></button>
</span> </span> <span id="InsecticideBrandSaverows" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="Save_Rows_To_DB"/></button>
</span> </span> <a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a></div>

<h2><fmt:message key="Nozzle"/></h2>
<div id="Nozzle"></div>
<div id="buttons" class="noprint"><span id="NozzleAddrow" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="New_Row"/></button>
</span> </span> <span id="NozzleSaverows" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="Save_Rows_To_DB"/></button>
</span> </span> <a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a></div>

<h2><fmt:message key="InsecticideNozzle"/></h2>
<div id="InsecticideNozzle"></div>
<div id="buttons" class="noprint"><span id="InsecticideNozzleAddrow" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="New_Row"/></button>
</span> </span> <span id="InsecticideNozzleSaverows" class="yui-button yui-push-button"> <span class="first-child">
<button type="button"><fmt:message key="Save_Rows_To_DB"/></button>
</span> </span> <a href="javascript:window.print()"><img src="./imgs/icons/printer.png"></a></div>

<fmt:message key="Area"/>
<dl>
  <mjl:form name="standards.form.name" id="standards.form" method="POST">
    <mjl:component item="${dto}" param="dto">
      <mjl:input type="hidden" param="areaStandardsId" value="${dto.areaStandardsId}"/>
      <mjl:dt attribute="room" type="text" />
      <mjl:dt attribute="house" type="text" />
      <mjl:dt attribute="household" type="text" />
    </mjl:component>
    <mjl:command value="Update" id="update.id" action="dss.vector.solutions.irs.ApplicationRateController.update.mojo" name="update.button" />
    <mjl:command value="Create" id="create.id" action="dss.vector.solutions.irs.ApplicationRateController.create.mojo" name="create.button" />
  </mjl:form>
</dl>

<script type="text/javascript">

    <%
      String[] types_to_load =
        {
          "dss.vector.solutions.irs.InsecticideBrand",
          "dss.vector.solutions.irs.InsecticideBrandView",
          "dss.vector.solutions.irs.Nozzle",
          "dss.vector.solutions.irs.NozzleView",
          "dss.vector.solutions.irs.InsecticideNozzle",
          "dss.vector.solutions.irs.InsecticideNozzleView"
        };
      out.println(com.terraframe.mojo.web.json.JSONController.importTypes(clientRequest.getSessionId(), types_to_load, true));
    %>
    <%=Halp.getDropdownSetup(brandDTO, brandAttributes, deleteColumn, clientRequest)%>

    MojoCal.init()

    brandData = {
              rows:<%=Halp.getDataMap(brandRows, brandAttributes, brandDTO)%>,
              columnDefs:<%=Halp.getColumnSetup(brandDTO, brandAttributes, deleteColumn, true)%>,
              defaults: {"Units":"PERCENT"},
              copy_from_above: [],
              div_id: "InsecticideBrand",
              data_type: "Mojo.$.dss.vector.solutions.irs.InsecticideBrandView",
              saveFunction:"applyAll",
              after_save:function(){window.location.reload( false );}
          };

    <%=Halp.getDropdownSetup(nozzleDTO, nozzleAttributes, deleteColumn, clientRequest)%>

    MojoCal.init()

    nozzleData = {
              rows:<%=Halp.getDataMap(nozzleRows, nozzleAttributes, nozzleDTO)%>,
              columnDefs:<%=Halp.getColumnSetup(nozzleDTO, nozzleAttributes, deleteColumn, true)%>,
              defaults: {"Enabled":"true"},
              copy_from_above: [],
              div_id: "Nozzle",
              data_type: "Mojo.$.dss.vector.solutions.irs.NozzleView",
              saveFunction:"applyAll",
              after_save:function(){window.location.reload( false );}
          };

    <%=Halp.getDropdownSetup(insecticideNozzleDTO, insecticideNozzleAttributes, deleteColumn, clientRequest)%>

    MojoCal.init()

    insecticideNozzleData = {
              rows:<%=Halp.getDataMap(insecticideNozzleRows, insecticideNozzleAttributes, insecticideNozzleDTO)%>,
              columnDefs:<%=Halp.getColumnSetup(insecticideNozzleDTO, insecticideNozzleAttributes, deleteColumn, true)%>,
              defaults: {"Enabled":"true"},
              copy_from_above: [],
              div_id: "InsecticideNozzle",
              data_type: "Mojo.$.dss.vector.solutions.irs.InsecticideNozzleView",
              saveFunction:"applyAll"
          };


    YAHOO.util.Event.onDOMReady(function(){
      MojoGrid.createDataTable(brandData);
      MojoGrid.createDataTable(nozzleData);
      MojoGrid.createDataTable(insecticideNozzleData);
    });


</script>