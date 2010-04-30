<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="java.util.Map"%>
<%@page import="dss.vector.solutions.util.yui.ColumnSetup"%>
<%@page import="java.util.HashMap"%>
<%@page import="dss.vector.solutions.util.RowSetup"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.InsecticideBrandViewDTO"%>
<%@page import="dss.vector.solutions.irs.NozzleViewDTO"%>
<%@page import="dss.vector.solutions.irs.InsecticideNozzleViewDTO"%>
<%@page import="dss.vector.solutions.util.yui.DataGrid"%>

<c:set var="page_title" value="Configure_Application_Rate"  scope="request"/>

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
      <mjl:input type="hidden" param="startDate" value="${dto.startDate}" classes="DatePick" id="startDate"/>
      <mjl:dt attribute="targetUnit">
        <mjl:select var="current" valueAttribute="enumName" items="${targetUnits}" param="targetUnit">
          <mjl:option selected="${mjl:contains(dto.targetUnitEnumNames, current.enumName) ? 'selected' : 'false'}">
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

<%
DataGrid brandGrid = (DataGrid) request.getAttribute("brandGrid");
DataGrid nozzleGrid = (DataGrid) request.getAttribute("nozzleGrid");
DataGrid configurationGrid = (DataGrid) request.getAttribute("configurationGrid");
%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){ 

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

    var onSaveAndDelete = function(event) {
      if(event.getType() == MDSS.Event.AFTER_SAVE || event.getType() == MDSS.Event.AFTER_DELETE) {
         window.location.reload( false );
      }
    }
  
    brandData = {
      rows:<%=brandGrid.getData()%>,
      columnDefs:<%=brandGrid.getColumnSetupWithDelete()%>,
      defaults:<%=brandGrid.getDefaultValues()%>,
      copy_from_above: [],
      div_id: "InsecticideBrand",
      data_type: "Mojo.$.<%=InsecticideBrandViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false
    };

    nozzleData = {
      rows:<%=nozzleGrid.getData()%>,
      columnDefs:<%=nozzleGrid.getColumnSetupWithDelete()%>,
      defaults:<%=nozzleGrid.getDefaultValues()%>,
      copy_from_above: [],
      div_id: "Nozzle",
      data_type: "Mojo.$.<%=NozzleViewDTO.CLASS%>",
      saveFunction:"applyAll",
      excelButtons:false
    };

    <%=configurationGrid.getDropDownMap()%>

    insecticideNozzleData = {
      rows:<%=configurationGrid.getData()%>,
      columnDefs:<%=configurationGrid.getColumnSetupWithDelete()%>,
      defaults:<%=configurationGrid.getDefaultValues()%>,
      copy_from_above: [],
      div_id: "InsecticideNozzle",
      excelButtons:false,
      data_type: "Mojo.$.<%=InsecticideNozzleViewDTO.CLASS%>",
      saveFunction:"applyAll"
    };

    var brandGrid = MojoGrid.createDataTable(brandData);
    var nozzleGrid = MojoGrid.createDataTable(nozzleData);
    var configurationGrid = MojoGrid.createDataTable(insecticideNozzleData);

    brandGrid.addListener(onSaveAndDelete);
    nozzleGrid.addListener(onSaveAndDelete);        
  });
})();
</script>