<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.ColumnSetup"%>
<%@page import="java.util.HashMap"%>


<%@page import="dss.vector.solutions.util.RowSetup"%>
<%@page import="java.util.Arrays"%><c:set var="page_title" value="Configure_Application_Rate"  scope="request"/>

<%
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  InsecticideBrandViewDTO brandDTO = new InsecticideBrandViewDTO(clientRequest);
  InsecticideBrandViewDTO[] brandRows = InsecticideBrandViewDTO.getAllActive(clientRequest);
  String[] brandAttributes = {"InsecticdeId", "BrandName", "ActiveIngredient", "Amount", "Weight", "SachetsPerRefill", "Enabled"};

  Map<String, ColumnSetup> brandMap = new HashMap<String, ColumnSetup>();
  brandMap.put("InsecticdeId", new ColumnSetup(true, false));
  brandMap.put("BrandName", new ColumnSetup(false, true));
  brandMap.put("ActiveIngredient", new ColumnSetup(false, true));
  brandMap.put("Amount", new ColumnSetup(false, true, "validateAmount", null, null));    
  brandMap.put("Weight", new ColumnSetup(false, true));
  brandMap.put("SachetsPerRefill", new ColumnSetup(false, true));   
  brandMap.put("Enabled", new ColumnSetup(false, true));
  
  NozzleViewDTO nozzleDTO = new NozzleViewDTO(clientRequest);
  NozzleViewDTO[] nozzleRows = NozzleViewDTO.getAll(clientRequest);
  String[] nozzleAttributes = {"NozzleId", "DisplayLabel", "Ratio", "Enabled"};

  InsecticideNozzleViewDTO insecticideNozzleDTO = new InsecticideNozzleViewDTO(clientRequest);
  InsecticideNozzleViewDTO[] insecticideNozzleRows = InsecticideNozzleViewDTO.getAll(clientRequest);
  String[] insecticideNozzleAttributes = {"InsecticideNozzleId", "Brand", "Nozzle", "Enabled"};

  Map<String, ColumnSetup> configurationMap = new HashMap<String, ColumnSetup>();
  configurationMap.put("InsecticideNozzleId", new ColumnSetup(true, false));
  configurationMap.put("Brand", new ColumnSetup(false, true, null, brandDTO.getClass().getName(), "getAllActive"));
  configurationMap.put("Nozzle", new ColumnSetup(false, true, null, nozzleDTO.getClass().getName(), "getAllActive"));
  configurationMap.put("Enabled", new ColumnSetup(false, true));
  configurationMap.put("BrandLabel", new ColumnSetup(true, false));
  configurationMap.put("NozzleLabel", new ColumnSetup(true, false));
  
  Map<String, RowSetup> rowMap = new HashMap<String, RowSetup>();
  rowMap.put("Brand", new RowSetup("getBrandView"));
  rowMap.put("Nozzle", new RowSetup("getNozzleView"));
  
  Map<String, String> map = new HashMap<String, String>();
  map.put("Brand", InsecticideBrandViewDTO.class.getName());
  map.put("Nozzle", NozzleViewDTO.class.getName());

  String deleteColumn = "{key:'delete', label:' ', className: 'delete-button', action:'delete', madeUp:true}";
%>

<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.InsecticideBrandViewDTO"%>
<%@page import="dss.vector.solutions.irs.NozzleViewDTO"%>
<%@page import="dss.vector.solutions.irs.InsecticideNozzleViewDTO"%>

<h2><fmt:message key="Insecticide_Brand"/></h2>
<div id="InsecticideBrand"></div>

<h2><fmt:message key="Nozzle"/></h2>
<div id="Nozzle"></div>

<h2><fmt:message key="InsecticideNozzle"/></h2>
<div id="InsecticideNozzle"></div>


  <mjl:form name="standards.form.name" id="standards.form" method="POST">
   <h2><fmt:message key="Area"/></h2>
   <dl>
      <mjl:component item="${dto}" param="dto">
      <mjl:input type="hidden" param="areaStandardsId" value="${dto.areaStandardsId}"/>
      <mjl:dt attribute="targetUnit">
        <mjl:select var="current" valueAttribute="enumName" items="${targetUnits}" param="targetUnit">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="unitNozzleAreaCoverage" type="text" />
      <mjl:dt attribute="room" type="text" />
      <mjl:dt attribute="structureArea" type="text" />
      <mjl:dt attribute="household" type="text" />
      </mjl:component>
      <mjl:command value="Update" id="update.id" action="dss.vector.solutions.irs.ApplicationRateController.update.mojo" name="update.button" />
      <mjl:command value="Create" id="create.id" action="dss.vector.solutions.irs.ApplicationRateController.create.mojo" name="create.button" />
   </dl>
  </mjl:form>
  <mjl:commandLink name="history" action="dss.vector.solutions.irs.AreaStandardsController.viewAll.mojo"><fmt:message key="Area_History"/></mjl:commandLink>

  <%=Halp.loadTypes(Arrays.asList(new String[]{InsecticideBrandViewDTO.CLASS, NozzleViewDTO.CLASS, InsecticideNozzleViewDTO.CLASS}))%>


<script type="text/javascript">
	var validateAmount = function(oData) {
	    var re = /^(100|[0-9]?[0-9])$/;
	    
	    // Validate
	    if(re.test(oData) || oData === "") {
	        return oData;
	    }
	    else {
	        alert(MDSS.localize("Value_Not_Between_0_and_100"));
	        return undefined;
	    }
	}
	
    <%=Halp.getDropdownSetup(brandDTO, brandAttributes, deleteColumn, clientRequest)%>


    brandData = {
              rows:<%=Halp.getDataMap(brandRows, brandAttributes, brandDTO)%>,
              columnDefs:<%=Halp.getColumnSetup(brandDTO, brandAttributes, deleteColumn, true, brandMap)%>,
              defaults:<%=Halp.getDefaultValues(brandDTO, brandAttributes)%>,
              copy_from_above: [],
              div_id: "InsecticideBrand",
              data_type: "Mojo.$.<%=InsecticideBrandViewDTO.CLASS%>",
              saveFunction:"applyAll",
              excelButtons:false,
              after_save:function(){window.location.reload( false );}
          };

    <%=Halp.getDropdownSetup(nozzleDTO, nozzleAttributes, deleteColumn, clientRequest)%>


    nozzleData = {
              rows:<%=Halp.getDataMap(nozzleRows, nozzleAttributes, nozzleDTO)%>,
              columnDefs:<%=Halp.getColumnSetup(nozzleDTO, nozzleAttributes, deleteColumn, true)%>,
              defaults:<%=Halp.getDefaultValues(nozzleDTO, nozzleAttributes)%>,
              copy_from_above: [],
              div_id: "Nozzle",
              data_type: "Mojo.$.<%=NozzleViewDTO.CLASS%>",
              saveFunction:"applyAll",
              excelButtons:false,
              after_save:function(){window.location.reload( false );}
          };

    <%=Halp.getDropdownSetup(insecticideNozzleDTO, insecticideNozzleAttributes, deleteColumn, clientRequest, map)%>


    insecticideNozzleData = {
              rows:<%=Halp.getDataMap(insecticideNozzleRows, insecticideNozzleAttributes, insecticideNozzleDTO, rowMap)%>,
              columnDefs:<%=Halp.getColumnSetup(insecticideNozzleDTO, insecticideNozzleAttributes, deleteColumn, true, configurationMap)%>,
              defaults:<%=Halp.getDefaultValues(insecticideNozzleDTO, insecticideNozzleAttributes)%>,
              copy_from_above: [],
              div_id: "InsecticideNozzle",
              excelButtons:false,
              data_type: "Mojo.$.<%=InsecticideNozzleViewDTO.CLASS%>",
              saveFunction:"applyAll"
          };


    YAHOO.util.Event.onDOMReady(function(){
      MojoGrid.createDataTable(brandData);
      MojoGrid.createDataTable(nozzleData);
      MojoGrid.createDataTable(insecticideNozzleData);
    });


</script>