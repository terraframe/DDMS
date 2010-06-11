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
<%@page import="dss.vector.solutions.surveillance.CaseDiagnosticViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CaseStockReferralViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CasePatientTypeViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.CasePatientTypeAmountViewDTO"%><mjl:messages>
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

    <div id="diagnosisType"></div>
    <div id="diseaseManifestation"></div>
    <div id="patientType"></div>   
    <div id="treatment"></div>
    <div id="method"></div>
    <div id="stock"></div>
    <div id="stockReferral"></div>
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
  DataGrid stockReferral = (DataGrid) request.getAttribute("stockReferral");
  DataGrid diagnosisType = (DataGrid) request.getAttribute("diagnosisType");
  DataGrid diseaseManifestation = (DataGrid) request.getAttribute("diseaseManifestation");
  DataGrid patientType = (DataGrid) request.getAttribute("patientType");
%>

<%=Halp.loadTypes(new String[]{AggregatedCaseViewDTO.CLASS, CaseTreatmentViewDTO.CLASS, CaseTreatmentMethodViewDTO.CLASS, CaseTreatmentStockViewDTO.CLASS, CaseDiagnosticViewDTO.CLASS, CaseReferralViewDTO.CLASS, CaseStockReferralViewDTO.CLASS, CasePatientTypeViewDTO.CLASS, CasePatientTypeAmountViewDTO.CLASS}) %>

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


    // SETUP THE stockReferral DATA GRID
    var stockReferralData = {
      columnDefs:<%=stockReferral.getColumns()%>,
      defaults:<%=stockReferral.getDefaultValues()%>,
      div_id: "stockReferral",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var stockReferralGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=stockReferral.getMetadata()%>), <%=stockReferral.getData()%>, null), stockReferralData);    

    // SETUP THE diagnosisType DATA GRID
    var diagnosisTypeData = {
      columnDefs:<%=diagnosisType.getColumns()%>,
      defaults:<%=diagnosisType.getDefaultValues()%>,
      div_id: "diagnosisType",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var diagnosisTypeGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=diagnosisType.getMetadata()%>), <%=diagnosisType.getData()%>, null), diagnosisTypeData);

    // SETUP THE diseaseManifestation DATA GRID
    var diseaseManifestationData = {
      columnDefs:<%=diseaseManifestation.getColumns()%>,
      defaults:<%=diseaseManifestation.getDefaultValues()%>,
      div_id: "diseaseManifestation",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var diseaseManifestationGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=diseaseManifestation.getMetadata()%>), <%=diseaseManifestation.getData()%>, null), diseaseManifestationData);    

    // SETUP THE patientType DATA GRID
    var patientTypeData = {
      columnDefs:<%=patientType.getColumns()%>,
      defaults:<%=patientType.getDefaultValues()%>,
      div_id: "patientType",
      excelButtons:false,
      addButton:false,
      saveButton:false
    };
        
    var patientTypeGrid = new MDSS.DataGrid(new MDSS.DataGridModel(new MDSS.ModelMetadata.init(<%=patientType.getMetadata()%>), <%=patientType.getData()%>, null), patientTypeData);

  });
})();
        
</script>