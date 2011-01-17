<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
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
<%@page import="dss.vector.solutions.surveillance.CasePatientTypeAmountViewDTO"%>

<style type="text/css">
.yui-skin-sam .yui-dt th, .yui-skin-sam .yui-dt th a
{
  vertical-align:bottom;
  background-color:#DDDDDD;
  background:none;
}

.yui-dt-liner {
overflow: hidden;
}

.yui-dt-label
{
  /*writing-mode: tb-rl;*/
  -moz-transform: rotate(-90deg);
  width:10px;
  height:225px;
  display:block;
  position:relative;
  top:108px;
  left:103px;
}
</style>

<c:set var="page_title" value="View_Aggregated_Case"  scope="request"/>


<%@ include file="form.jsp"%>

<div class="pageTitle"> <mdss:localize key="Enter_data_for_ages"/> ${item.ageGroup.displayLabel} </div> 

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

    <c:if test="${item.isCaseDiagnosisTypeReadable}">
      <dt><label><mdss:localize key="Grid_data_by_diagnosis_type"/></label></dt>
      <dd><div id="diagnosisType"></div></dd>
    </c:if>

    <c:if test="${item.isCaseDiseaseManifestationReadable}">    
      <dt><label><mdss:localize key="Grid_data_by_disease_manifestation"/></label></dt>
      <dd><div id="diseaseManifestation"></div></dd>
    </c:if>

    <c:if test="${item.isCasePatientTypeReadable}">
      <dt><label><mdss:localize key="Grid_data_by_patient_type"/></label></dt>
      <dd><div id="patientType"></div></dd>
    </c:if>

    <c:if test="${item.isCaseTreatmentsReadable}">
      <dt><label><mdss:localize key="Grid_treatment_by_drug"/></label></dt>
      <dd><div id="treatment"></div></dd>
    </c:if>

    <c:if test="${item.isCaseTreatmentMethodReadable}">
      <dt><label><mdss:localize key="Grid_treatment_by_method"/></label></dt>
      <dd><div id="method"></div></dd>
    </c:if>

    <c:if test="${item.isCaseStocksReadable}">
      <dt><label><mdss:localize key="Grid_treatment_by_stock"/></label></dt>
      <dd><div id="stock"></div></dd>
    </c:if>

    <c:if test="${item.isCaseStockReferralReadable}">
      <dt><label><mdss:localize key="Grid_referrals_and_Shortages"/></label></dt>
      <dd><div id="stockReferral"></div></dd>
    </c:if>
    

    <c:if test="${item.isCaseReferralsReadable}">
      <dt><label><mdss:localize key="Grid_referral_Reasons"/></label></dt>
      <dd><div id="referral"></div></dd>
    </c:if>
    

    <c:if test="${item.isCaseDiagnosticReadable}">
      <dt><label><mdss:localize key="Grid_diagnostic_methods"/></label></dt>
      <dd><div id="diagnostic"></div></dd>
    </c:if>
    
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
    <%=treatment.getJavascript()%>

    // SETUP THE method DATA GRID
    <%=method.getJavascript()%>

    // SETUP THE STOCK DATA GRID
    <%=stock.getJavascript()%>

    // SETUP THE referral DATA GRID
    <%=referral.getJavascript()%>

    // SETUP THE diagnostic DATA GRID
    <%=diagnostic.getJavascript()%>

    // SETUP THE stockReferral DATA GRID
    <%=stockReferral.getJavascript()%>

    // SETUP THE diagnosisType DATA GRID
    <%=diagnosisType.getJavascript()%>

    // SETUP THE diseaseManifestation DATA GRID
    <%=diseaseManifestation.getJavascript()%>

    // SETUP THE patientType DATA GRID
    <%=patientType.getJavascript()%>      
  });
})();
        
</script>