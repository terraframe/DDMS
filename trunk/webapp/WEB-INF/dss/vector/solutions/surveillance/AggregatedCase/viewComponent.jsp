<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@page import="dss.vector.solutions.util.yui.DataGrid"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CaseTreatmentViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CaseTreatmentMethodViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CaseTreatmentStockViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CaseReferralViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CaseDiagnosticViewDTO"%><mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="page_title" value="View_Aggregated_Case"  scope="request"/>


<%@ include file="form.jsp"%>

<div class="pageTitle"> <fmt:message key="Enter_data_for_ages"/> ${item.ageGroup.displayLabel} </div> 

<dl>
  <mjl:form name="dss.vector.solutions.surveillance.AggregatedCase.form.name" id="dss.vector.solutions.surveillance.AggregatedCase.form.id" method="POST">
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="geoEntity">
        ${item.geoEntity.displayString} 
      </mjl:dt>
      <mjl:dt attribute="startDate">
        <span class="formatDate">${item.startDate}</span>
      </mjl:dt>
      <mjl:dt attribute="endDate">
        <span class="formatDate">${item.endDate}</span>      
      </mjl:dt>      
      <mjl:dt attribute="ageGroup">
        ${item.ageGroup.displayLabel}
      </mjl:dt>          
    
      <mjl:dt attribute="cases"> ${item.cases} </mjl:dt>
      <mjl:dt attribute="positiveCases"> ${item.positiveCases} </mjl:dt>
      <mjl:dt attribute="negativeCases"> ${item.negativeCases} </mjl:dt>
      <mjl:dt attribute="deaths"> ${item.deaths} </mjl:dt>
    </mjl:component>

    <div id="treatment"></div>
    <div id="method"></div>
    <div id="stock"></div>
    <div id="referral"></div>
    <div id="diagnostic"></div>
    
    <hr />

    <mjl:command value="Edit" action="dss.vector.solutions.surveillance.AggregatedCaseController.edit.mojo" name="dss.vector.solutions.surveillance.AggregatedCase.form.edit.button" />
    <mjl:command value="Create_New_Aggregated_Case_Button" action="dss.vector.solutions.surveillance.AggregatedCaseController.search.mojo" name="AggregatedCaseController.search" />
  </mjl:form>
</dl>

<%
  DataGrid treatment = (DataGrid) request.getAttribute("treatment");
  DataGrid method = (DataGrid) request.getAttribute("method");
  DataGrid stock = (DataGrid) request.getAttribute("stock");
  DataGrid referral = (DataGrid) request.getAttribute("referral");
  DataGrid diagnostic = (DataGrid) request.getAttribute("diagnostic");
%>

<%=Halp.loadTypes(new String[]{AggregatedCaseViewDTO.CLASS, CaseTreatmentViewDTO.CLASS, CaseTreatmentMethodViewDTO.CLASS, CaseTreatmentStockViewDTO.CLASS, CaseDiagnosticViewDTO.CLASS, CaseReferralViewDTO.CLASS}) %>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){

    // SETUP THE TREATMENT DATA GRID
    var treatmentData = {
      columnDefs:<%=treatment.getColumns()%>,
      defaults:<%=treatment.getDefaultValues()%>,
      div_id: "treatment",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var treatmentGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=treatment.getMetadata()%>), <%=treatment.getData()%>, null), treatmentData);

    // SETUP THE method DATA GRID
    var methodData = {
      columnDefs:<%=method.getColumns()%>,
      defaults:<%=method.getDefaultValues()%>,
      div_id: "method",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var methodGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=method.getMetadata()%>), <%=method.getData()%>, null), methodData);

    // SETUP THE STOCK DATA GRID
    var stockData = {
      columnDefs:<%=stock.getColumns()%>,
      defaults:<%=stock.getDefaultValues()%>,
      div_id: "stock",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var stockGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=stock.getMetadata()%>), <%=stock.getData()%>, null), stockData);

    // SETUP THE referral DATA GRID
    var referralData = {
      columnDefs:<%=referral.getColumns()%>,
      defaults:<%=referral.getDefaultValues()%>,
      div_id: "referral",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var referralGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=referral.getMetadata()%>), <%=referral.getData()%>, null), referralData);

    // SETUP THE diagnostic DATA GRID
    var diagnosticData = {
      columnDefs:<%=diagnostic.getColumns()%>,
      defaults:<%=diagnostic.getDefaultValues()%>,
      div_id: "diagnostic",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var diagnosticGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=diagnostic.getMetadata()%>), <%=diagnostic.getData()%>, null), diagnosticData);
  });
})();
        
</script>