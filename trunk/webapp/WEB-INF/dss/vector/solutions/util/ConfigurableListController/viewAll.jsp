<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Configurable_Lists" scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <dt><fmt:message key="Configurable_Mo_Terms"/></dt>
  <dd>
    <mjl:commandLink
      name="dss.vector.solutions.mo.ActiveIngredient"
      action="dss.vector.solutions.mo.ActiveIngredientController.viewAll.mojo"
      >
      <fmt:message key="Active_Ingredient_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.CollectionMethod"
      action="dss.vector.solutions.mo.CollectionMethodController.viewAll.mojo"
      >
      <fmt:message key="Collection_Method_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.Generation"
      action="dss.vector.solutions.mo.GenerationController.viewAll.mojo"
      >
      <fmt:message key="Generation_Method_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.IdentificationMethod"
      action="dss.vector.solutions.mo.IdentificationMethodController.viewAll.mojo"
      >
      <fmt:message key="Identification_Method_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.InfectivityMethodology"
      action="dss.vector.solutions.mo.InfectivityMethodologyController.viewAll.mojo"
      >
      <fmt:message key="Infectivity_Methodology_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.InsecticideMethodology"
      action="dss.vector.solutions.mo.InsecticideMethodologyController.viewAll.mojo"
      >
      <fmt:message key="Insecticide_Methodology_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.LarvaeAge"
      action="dss.vector.solutions.mo.LarvaeAgeController.viewAll.mojo"
      >
      <fmt:message key="Larvae_Age_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.MolecularAssayResult"
      action="dss.vector.solutions.mo.MolecularAssayResultController.viewAll.mojo"
      >
      <fmt:message key="Molecular_Assay_Result_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.ResistanceMethodology"
      action="dss.vector.solutions.mo.ResistanceMethodologyController.viewAll.mojo"
      >
      <fmt:message key="Resistance_Methodology_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.Specie"
      action="dss.vector.solutions.mo.SpecieController.viewAll.mojo"
      >
      <fmt:message key="Specie_Method_Label"/>
    </mjl:commandLink><br />
  </dd>
  <dt><fmt:message key="Configurable_Surveillance_Grids"/></dt>
  <dd>
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.AggregatedAgeGroup"
      action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.viewAll.mojo">
      <fmt:message key="Age_Group"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.DiagnosticGrid"
      action="dss.vector.solutions.surveillance.DiagnosticGridController.viewAll.mojo">
      <fmt:message key="Diagnostic_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.ReferralGrid"
      action="dss.vector.solutions.surveillance.ReferralGridController.viewAll.mojo"
      >
      <fmt:message key="Referral_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.TreatmentGrid"
      action="dss.vector.solutions.surveillance.TreatmentGridController.viewAll.mojo"
      >
      <fmt:message key="Treatment_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.TreatmentMethodGrid"
      action="dss.vector.solutions.surveillance.TreatmentMethodGridController.viewAll.mojo"
      >
      <fmt:message key="Treatment_Method_Grid_Label"/>
    </mjl:commandLink><br />
  </dd>
  <dt><fmt:message key="Configurable_Intervention_Monitoring_Grids"/></dt>
  <dd>  
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.ServiceGrid"
      action="dss.vector.solutions.intervention.monitor.ServiceGridController.viewAll.mojo">
      <fmt:message key="Service_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.NonUseReasonGrid"
      action="dss.vector.solutions.intervention.monitor.NonUseReasonGridController.viewAll.mojo">
      <fmt:message key="Non_Use_Reasons_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.TargetGroupGrid"
      action="dss.vector.solutions.intervention.monitor.TargetGroupGridController.viewAll.mojo">
      <fmt:message key="Target_Group_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.DoseGrid"
      action="dss.vector.solutions.intervention.monitor.DoseGridController.viewAll.mojo">
      <fmt:message key="Dose_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.VisitGrid"
      action="dss.vector.solutions.intervention.monitor.VisitGridController.viewAll.mojo">
      <fmt:message key="Visit_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.PatientGrid"
      action="dss.vector.solutions.intervention.monitor.PatientGridController.viewAll.mojo">
      <fmt:message key="Patient_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.TreatmentGrid"
      action="dss.vector.solutions.surveillance.TreatmentGridController.viewAll.mojo">
      <fmt:message key="Treatment_Grid_Label"/>
    </mjl:commandLink><br />
  </dd>
  <dt><fmt:message key="Configurable_Other"/></dt>
  <dd>  
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.TreatmentGrid"
      action="dss.vector.solutions.surveillance.TreatmentGridController.viewAll.mojo"
      >
      <fmt:message key="Drug_Label"/>
      </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.Net"
      action="dss.vector.solutions.intervention.monitor.NetController.viewAll.mojo" >
      <fmt:message key="Net"/>
    </mjl:commandLink><br />  
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.Wall"
      action="dss.vector.solutions.intervention.monitor.WallController.viewAll.mojo" >
      <fmt:message key="Wall"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.Roof"
      action="dss.vector.solutions.intervention.monitor.RoofController.viewAll.mojo" >
      <fmt:message key="Roof"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.FeverTreatment"
      action="dss.vector.solutions.intervention.FeverTreatmentController.viewAll.mojo" >
      <fmt:message key="Fever_Treatment"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.CommercialITNProviderController"
      action="dss.vector.solutions.intervention.monitor.CommercialITNProviderController.viewAll.mojo" >
      <fmt:message key="Commercial_ITN_Provider"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.FreeITNProviderController"
      action="dss.vector.solutions.intervention.monitor.FreeITNProviderController.viewAll.mojo" >
      <fmt:message key="Free_ITN_Provider"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodController"
      action="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodController.viewAll.mojo" >
      <fmt:message key="ITN_Retreatment_Period"/>
    </mjl:commandLink><br />
  </dd>
</dl>
