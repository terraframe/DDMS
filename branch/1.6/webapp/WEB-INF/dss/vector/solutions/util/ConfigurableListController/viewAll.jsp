<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Configurable_Lists" scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <dt><mdss:localize key="Configurable_Mo_Terms"/></dt>
  <dd>
    <mjl:commandLink
      name="dss.vector.solutions.mo.ActiveIngredient"
      action="dss.vector.solutions.mo.ActiveIngredientController.viewAll.mojo"
      >
      <mdss:localize key="Active_Ingredient_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.CollectionMethod"
      action="dss.vector.solutions.mo.CollectionMethodController.viewAll.mojo"
      >
      <mdss:localize key="Collection_Method_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.Generation"
      action="dss.vector.solutions.mo.GenerationController.viewAll.mojo"
      >
      <mdss:localize key="Generation_Method_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.IdentificationMethod"
      action="dss.vector.solutions.mo.IdentificationMethodController.viewAll.mojo"
      >
      <mdss:localize key="Identification_Method_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.InfectivityMethodology"
      action="dss.vector.solutions.mo.InfectivityMethodologyController.viewAll.mojo"
      >
      <mdss:localize key="Infectivity_Methodology_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.InsecticideMethodology"
      action="dss.vector.solutions.mo.InsecticideMethodologyController.viewAll.mojo"
      >
      <mdss:localize key="Insecticide_Methodology_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.LarvaeAge"
      action="dss.vector.solutions.mo.LarvaeAgeController.viewAll.mojo"
      >
      <mdss:localize key="Larvae_Age_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.MolecularAssayResult"
      action="dss.vector.solutions.mo.MolecularAssayResultController.viewAll.mojo"
      >
      <mdss:localize key="Molecular_Assay_Result_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.ResistanceMethodology"
      action="dss.vector.solutions.mo.ResistanceMethodologyController.viewAll.mojo"
      >
      <mdss:localize key="Resistance_Methodology_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.mo.Specie"
      action="dss.vector.solutions.mo.SpecieController.viewAll.mojo"
      >
      <mdss:localize key="Specie_Method_Label"/>
    </mjl:commandLink><br />
  </dd>
  <dt><mdss:localize key="Configurable_Surveillance_Grids"/></dt>
  <dd>
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.AggregatedAgeGroup"
      action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.viewAll.mojo">
      <mdss:localize key="Age_Group"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.DiagnosticGrid"
      action="dss.vector.solutions.surveillance.DiagnosticGridController.viewAll.mojo">
      <mdss:localize key="Diagnostic_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.ReferralGrid"
      action="dss.vector.solutions.surveillance.ReferralGridController.viewAll.mojo"
      >
      <mdss:localize key="Referral_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.TreatmentGrid"
      action="dss.vector.solutions.surveillance.TreatmentGridController.viewAll.mojo"
      >
      <mdss:localize key="Treatment_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.TreatmentMethodGrid"
      action="dss.vector.solutions.surveillance.TreatmentMethodGridController.viewAll.mojo"
      >
      <mdss:localize key="Treatment_Method_Grid_Label"/>
    </mjl:commandLink><br />
  </dd>
  <dt><mdss:localize key="Configurable_Intervention_Monitoring_Grids"/></dt>
  <dd>  
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.ServiceGrid"
      action="dss.vector.solutions.intervention.monitor.ServiceGridController.viewAll.mojo">
      <mdss:localize key="Service_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.NonUseReasonGrid"
      action="dss.vector.solutions.intervention.monitor.NonUseReasonGridController.viewAll.mojo">
      <mdss:localize key="Non_Use_Reasons_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.TargetGroupGrid"
      action="dss.vector.solutions.intervention.monitor.TargetGroupGridController.viewAll.mojo">
      <mdss:localize key="Target_Group_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.DoseGrid"
      action="dss.vector.solutions.intervention.monitor.DoseGridController.viewAll.mojo">
      <mdss:localize key="Dose_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.VisitGrid"
      action="dss.vector.solutions.intervention.monitor.VisitGridController.viewAll.mojo">
      <mdss:localize key="Visit_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.PatientGrid"
      action="dss.vector.solutions.intervention.monitor.PatientGridController.viewAll.mojo">
      <mdss:localize key="Patient_Grid_Label"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.TreatmentGrid"
      action="dss.vector.solutions.surveillance.TreatmentGridController.viewAll.mojo">
      <mdss:localize key="Treatment_Grid_Label"/>
    </mjl:commandLink><br />
  </dd>
  <dt><mdss:localize key="Configurable_Other"/></dt>
  <dd>  
    <mjl:commandLink
      name="dss.vector.solutions.surveillance.TreatmentGrid"
      action="dss.vector.solutions.surveillance.TreatmentGridController.viewAll.mojo"
      >
      <mdss:localize key="Drug_Label"/>
      </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.Net"
      action="dss.vector.solutions.intervention.monitor.NetController.viewAll.mojo" >
      <mdss:localize key="Net"/>
    </mjl:commandLink><br />  
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.Wall"
      action="dss.vector.solutions.intervention.monitor.WallController.viewAll.mojo" >
      <mdss:localize key="Wall"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.Roof"
      action="dss.vector.solutions.intervention.monitor.RoofController.viewAll.mojo" >
      <mdss:localize key="Roof"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.FeverTreatment"
      action="dss.vector.solutions.intervention.FeverTreatmentController.viewAll.mojo" >
      <mdss:localize key="Fever_Treatment"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.CommercialITNProviderController"
      action="dss.vector.solutions.intervention.monitor.CommercialITNProviderController.viewAll.mojo" >
      <mdss:localize key="Commercial_ITN_Provider"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.FreeITNProviderController"
      action="dss.vector.solutions.intervention.monitor.FreeITNProviderController.viewAll.mojo" >
      <mdss:localize key="Free_ITN_Provider"/>
    </mjl:commandLink><br />
    <mjl:commandLink
      name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodController"
      action="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodController.viewAll.mojo" >
      <mdss:localize key="ITN_Retreatment_Period"/>
    </mjl:commandLink><br />
  </dd>
</dl>
