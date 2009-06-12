<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
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
      name="dss.vector.solutions.surveillance.DiagnosticGrid"
      action="dss.vector.solutions.surveillance.DiagnosticGridController.viewAll.mojo"
      >
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
    (Incomplete)<mjl:commandLink name="dss.vector.solutions.intervention.monitor.Net" action="dss.vector.solutions.intervention.monitor.NetController.viewAll.mojo" >dss.vector.solutions.intervention.monitor.Net</mjl:commandLink><br />
    (Incomplete)<mjl:commandLink name="dss.vector.solutions.intervention.monitor.Wall" action="dss.vector.solutions.intervention.monitor.WallController.viewAll.mojo" >dss.vector.solutions.intervention.monitor.Wall</mjl:commandLink><br />
    (Incomplete)<mjl:commandLink name="dss.vector.solutions.intervention.monitor.Roof" action="dss.vector.solutions.intervention.monitor.RoofController.viewAll.mojo" >dss.vector.solutions.intervention.monitor.Roof</mjl:commandLink><br />
    (Incomplete)<mjl:commandLink name="dss.vector.solutions.intervention.FeverTreatment" action="dss.vector.solutions.intervention.FeverTreatmentController.viewAll.mojo" >dss.vector.solutions.intervention.FeverTreatment</mjl:commandLink><br />
  </dd>
</dl>
