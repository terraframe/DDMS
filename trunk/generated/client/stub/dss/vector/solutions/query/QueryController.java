package dss.vector.solutions.query;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.ClientException;
import com.runwaysdk.business.BusinessDTO;
import com.runwaysdk.business.ClassQueryDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.MultipartFileParameter;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdClassDTO;
import com.runwaysdk.system.metadata.MdWebFormDTO;
import com.runwaysdk.web.json.JSONRunwayExceptionDTO;

import dss.vector.solutions.entomology.BiochemicalAssayDTO;
import dss.vector.solutions.entomology.DiagnosticAssayDTO;
import dss.vector.solutions.entomology.ImmatureCollectionDTO;
import dss.vector.solutions.entomology.InfectionAssayDTO;
import dss.vector.solutions.entomology.MolecularAssayDTO;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.entomology.PooledInfectionAssayDTO;
import dss.vector.solutions.entomology.PupalCollectionDTO;
import dss.vector.solutions.entomology.PupalContainerAmountDTO;
import dss.vector.solutions.entomology.PupalContainerDTO;
import dss.vector.solutions.entomology.PupalContainerViewDTO;
import dss.vector.solutions.entomology.TimeResponseAssayDTO;
import dss.vector.solutions.entomology.TimeResponseAssayViewDTO;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.EfficacyAssayDTO;
import dss.vector.solutions.entomology.assay.KnockDownAssayDTO;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO;
import dss.vector.solutions.form.business.FormBedNetDTO;
import dss.vector.solutions.form.business.FormHouseholdDTO;
import dss.vector.solutions.form.business.FormPersonDTO;
import dss.vector.solutions.form.business.FormSurveyDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.generator.MdFormUtilDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO;
import dss.vector.solutions.intervention.monitor.ControlInterventionDTO;
import dss.vector.solutions.intervention.monitor.HouseholdDTO;
import dss.vector.solutions.intervention.monitor.IPTANCVisitDTO;
import dss.vector.solutions.intervention.monitor.IPTDoseDTO;
import dss.vector.solutions.intervention.monitor.IPTPatientsDTO;
import dss.vector.solutions.intervention.monitor.IPTTreatmentDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO;
import dss.vector.solutions.intervention.monitor.ITNDataDTO;
import dss.vector.solutions.intervention.monitor.ITNDataViewDTO;
import dss.vector.solutions.intervention.monitor.ITNDistributionDTO;
import dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO;
import dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO;
import dss.vector.solutions.intervention.monitor.ITNInstanceDTO;
import dss.vector.solutions.intervention.monitor.ITNNetDTO;
import dss.vector.solutions.intervention.monitor.ITNServiceDTO;
import dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO;
import dss.vector.solutions.intervention.monitor.IndividualCaseDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTDTO;
import dss.vector.solutions.intervention.monitor.IndividualInstanceDTO;
import dss.vector.solutions.intervention.monitor.IndividualPremiseVisitDTO;
import dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO;
import dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO;
import dss.vector.solutions.intervention.monitor.LarvacideDTO;
import dss.vector.solutions.intervention.monitor.PersonInterventionDTO;
import dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO;
import dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.intervention.monitor.SurveyedPersonDTO;
import dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO;
import dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO;
import dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO;
import dss.vector.solutions.irs.AbstractSprayDTO;
import dss.vector.solutions.irs.InsecticideBrandDTO;
import dss.vector.solutions.irs.InsecticideBrandViewDTO;
import dss.vector.solutions.irs.OperatorSprayDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.stock.StockEventDTO;
import dss.vector.solutions.stock.StockItemDTO;
import dss.vector.solutions.surveillance.AggregatedAgeGroupDTO;
import dss.vector.solutions.surveillance.AggregatedCaseDTO;
import dss.vector.solutions.surveillance.AggregatedCaseViewDTO;
import dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO;
import dss.vector.solutions.surveillance.CaseDiagnosisTypeDTO;
import dss.vector.solutions.surveillance.CaseDiagnosisTypeViewDTO;
import dss.vector.solutions.surveillance.CaseDiagnosticDTO;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationDTO;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationViewDTO;
import dss.vector.solutions.surveillance.CasePatientTypeAmountDTO;
import dss.vector.solutions.surveillance.CasePatientTypeDTO;
import dss.vector.solutions.surveillance.CasePatientTypeViewDTO;
import dss.vector.solutions.surveillance.CaseReferralDTO;
import dss.vector.solutions.surveillance.CaseStockReferralDTO;
import dss.vector.solutions.surveillance.CaseTreatmentDTO;
import dss.vector.solutions.surveillance.CaseTreatmentMethodDTO;
import dss.vector.solutions.surveillance.CaseTreatmentStockDTO;
import dss.vector.solutions.surveillance.IndividualCaseSymptomDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.FileDownloadUtil;
import dss.vector.solutions.util.Halp;
import dss.vector.solutions.util.LocalizationFacadeDTO;
import dss.vector.solutions.util.QueryUtil;

public class QueryController extends QueryControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long   serialVersionUID                    = 1237863171352L;

  private static final String QUERY_TYPE                          = "/WEB-INF/queryScreens/queryType.jsp";

  private static final String QUERY_ENTOMOLOGY                    = "/WEB-INF/queryScreens/queryEntomology.jsp";

  private static final String QUERY_RESISTANCE                    = "/WEB-INF/queryScreens/queryResistance.jsp";

  private static final String QUERY_RESISTANCE_BIOASSAY           = "/WEB-INF/queryScreens/queryResistanceBioassay.jsp";

  private static final String QUERY_IRS                           = "/WEB-INF/queryScreens/queryIRS.jsp";

  private static final String QUERY_AGGREGATED_CASES              = "/WEB-INF/queryScreens/queryAggregatedCases.jsp";

  private static final String QUERY_AGGREGATED_IPT                = "/WEB-INF/queryScreens/queryAggregatedIPT.jsp";

  private static final String QUERY_INDIVIDUAL_IPT                = "/WEB-INF/queryScreens/queryIndividualIPT.jsp";

  private static final String QUERY_INDIVIDUAL_CASES              = "/WEB-INF/queryScreens/queryIndividualCases.jsp";

  private static final String QUERY_SURVEY                        = "/WEB-INF/queryScreens/querySurvey.jsp";

  private static final String QUERY_STOCK                         = "/WEB-INF/queryScreens/queryStock.jsp";

  private static final String QUERY_LARVACIDE                     = "/WEB-INF/queryScreens/queryLarvacide.jsp";

  private static final String QUERY_IMMATURE_CONTAINER_COLLECTION = "/WEB-INF/queryScreens/queryImmatureContainerCollections.jsp";

  private static final String QUERY_PUPAL_CONTAINER_COLLECTION    = "/WEB-INF/queryScreens/queryPupalContainerCollections.jsp";

  private static final String NEW_QUERY                           = "/WEB-INF/queryScreens/newQuery.jsp";

  private static final String QUERY_EFFICACY_ASSAY                = "/WEB-INF/queryScreens/queryEfficacyAssay.jsp";

  private static final String QUERY_ITN_COMMUNITY_DISTRIBUTION    = "/WEB-INF/queryScreens/queryITNCommunityDistribution.jsp";

  private static final String QUERY_ITN_FACILITY_DISTRIBUTION     = "/WEB-INF/queryScreens/queryITNFacilityDistribution.jsp";

  private static final String QUERY_AGGREGATED_ITN                = "/WEB-INF/queryScreens/queryAggregatedITN.jsp";

  private static final String QUERY_MOSQUITO_COLLECTIONS          = "/WEB-INF/queryScreens/queryMosquitoCollections.jsp";

  private static final String QUERY_INTERVENTION_CONTROL          = "/WEB-INF/queryScreens/queryInterventionControl.jsp";

  public QueryController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  private static class TermComparator implements Comparator<TermDTO>, Reloadable
  {

    public int compare(TermDTO o1, TermDTO o2)
    {
      return o1.getDisplayLabel().compareTo(o2.getDisplayLabel());
    }

    private static TermDTO[] sort(TermDTO[] terms)
    {
      List<TermDTO> termsList = Arrays.asList(terms);
      Collections.sort(termsList, new TermComparator());
      return termsList.toArray(new TermDTO[termsList.size()]);
    }

  }

  /**
   * Loads information common to query screens, including the Earth node for 061
   * and all available queries for the given query screen.
   * 
   * @param queryClass
   * @param queryType
   * @throws JSONException
   */
  private void loadQuerySpecifics(String queryClass, QueryConstants.QueryType queryType) throws JSONException
  {
    this.loadQuerySpecifics(QueryConstants.namespaceQuery(queryClass, queryType));
  }

  /**
   * Loads information common to query screens, including the Earth node for 061
   * and all available queries for the given query screen.
   * 
   * @param queryClass
   * @param queryType
   * @throws JSONException
   */
  private void loadQuerySpecifics(String type, String typeName) throws JSONException
  {
    this.loadQuerySpecifics(QueryConstants.namespaceQuery(type, typeName));
  }

  private void loadQuerySpecifics(String namespacedType) throws JSONException
  {
    ClientRequestIF request = this.getClientRequest();
    SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(request, namespacedType);
    JSONArray queries = new JSONArray();
    for (SavedSearchViewDTO view : query.getResultSet())
    {
      JSONObject idAndName = new JSONObject();
      idAndName.put("id", view.getSavedQueryId());
      idAndName.put("name", view.getQueryName());

      queries.put(idAndName);
    }

    JSONObject queryList = new JSONObject();
    queryList.put("queries", queries);
    queryList.put("namespacedType", namespacedType);

    req.setAttribute("queryList", queryList.toString());
  }

  @Override
  public void querySurvey() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(SurveyPointDTO.CLASS, QueryConstants.QueryType.QUERY_INDICATOR_SURVEY);

      ClientRequestIF request = this.getClientRequest();

      JSONObject ordered = new JSONObject();

      // locations
      JSONObject location = new JSONObject();
      location.put("type", TermDTO.CLASS);
      // location.put("label", MDSSProperties.getObject("Locations"));
      location.put("relType", SurveyedPersonTreatmentLocationDTO.CLASS);
      location.put("relAttribute", QueryUtil.DUMMY_RELATIONSHIP_VALUE_ONE);
      location.put("options", getAllTermsForGrid(request, SurveyedPersonViewDTO.CLASS, SurveyedPersonViewDTO.DISPLAYLOCATIONS));
      ordered.put("locations", location);

      // Treatment
      JSONObject treatment = new JSONObject();
      treatment.put("type", TermDTO.CLASS);
      // treatment.put("label", MDSSProperties.getObject("Treatments"));
      treatment.put("relType", SurveyedPersonTreatmentDTO.CLASS);
      treatment.put("relAttribute", QueryUtil.DUMMY_RELATIONSHIP_VALUE_ONE);
      treatment.put("options", getAllTermsForGrid(request, SurveyedPersonViewDTO.CLASS, SurveyedPersonViewDTO.DISPLAYTREATMENTS));
      ordered.put("treatments", treatment);

      req.setAttribute("orderedGrids", ordered.toString());

      ClassQueryDTO surveyedPerson = request.getQuery(SurveyedPersonDTO.CLASS);
      String surveyedPersonMap = Halp.getDropDownMaps(surveyedPerson, request, ", ");
      req.setAttribute("surveyedPersonMap", surveyedPersonMap);

      ClassQueryDTO iTNInstance = request.getQuery(ITNInstanceDTO.CLASS);
      String itnMap = Halp.getDropDownMaps(iTNInstance, request, ", ");
      req.setAttribute("itnMap", itnMap);

      ClassQueryDTO householdQuery = request.getQuery(HouseholdDTO.CLASS);
      String householdMap = Halp.getDropDownMaps(householdQuery, request, ", ");
      req.setAttribute("householdMap", householdMap);

      req.getRequestDispatcher(QUERY_SURVEY).forward(req, resp);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  @Override
  public void uploadTemplate(String savedSearchId, MultipartFileParameter templateFile) throws IOException, ServletException
  {
    String message = "";

    try
    {
      ClientRequestIF request = this.getClientRequest();
      if (savedSearchId == null || savedSearchId.trim().length() == 0)
      {
        SavedSearchRequiredExceptionDTO ex = new SavedSearchRequiredExceptionDTO(request, req.getLocale());
        message = ex.getLocalizedMessage();
        return;
      }

      if (templateFile == null || templateFile.getSize() > 0)
      {
        message = LocalizationFacadeDTO.getFromBundles(request, "File_Required");
        return;
      }

      // All checks passed. Save the file to the SavedSearch

      // Ensure that a saved search actually exists
      SavedSearchDTO search = SavedSearchDTO.lock(request, savedSearchId);

      String templateId = search.getTemplateFile();

      if (templateId != null && !templateId.equals(""))
      {
        // This search already has a file associated with it.
        // The existing file needs to be deleted
        request.delete(templateId);
      }

      // Upload the template file to the vault
      BusinessDTO templateDTO = request.newSecureFile("template", "rptdesign", templateFile.getInputStream());

      // Associate the template file with the saved search
      search.setTemplateFile(templateDTO.getId());
      search.apply();

      message = LocalizationFacadeDTO.getFromBundles(request, "File_Upload_Success");
    }
    catch (Throwable e)
    {
      this.getResponse().getWriter().write(e.getLocalizedMessage());
    }
    finally
    {
      this.resp.setContentType("text/html;charset=UTF-8");
      this.resp.setCharacterEncoding("UTF-8");
      this.resp.getWriter().write(message);
    }
  }

  /**
   * Creates the sceen to query for AggregatedCases.
   */
  @Override
  public void queryAggregatedCases() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(AggregatedCaseDTO.CLASS, QueryConstants.QueryType.QUERY_AGGREGATED_CASE);

      ClientRequestIF request = this.getClientRequest();

      JSONObject ordered = new JSONObject();

      AggregatedAgeGroupDTO[] ages = AggregatedAgeGroupDTO.getAll(request);
      JSONObject aggCaseMap = new JSONObject();
      JSONObject ageGroups = new JSONObject();
      for (AggregatedAgeGroupDTO age : ages)
      {
        String display = age.getStartAge() + " - " + age.getEndAge();
        ageGroups.put(age.getId(), display);
      }
      aggCaseMap.put("AgeGroup", ageGroups);
      req.setAttribute("ageGroups", aggCaseMap.toString());

      String label = LocalizationFacadeDTO.getFromBundles(request, "Amount");

      JSONObject methods = new JSONObject();
      methods.put("type", TermDTO.CLASS);
      methods.put("label", label);
      methods.put("relType", CaseTreatmentMethodDTO.CLASS);
      methods.put("relAttribute", CaseTreatmentMethodDTO.AMOUNT);
      methods.put("options", getAllTermsForGrid(request, AggregatedCaseViewDTO.CLASS, AggregatedCaseViewDTO.CASETREATMENTMETHOD));
      ordered.put("methods", methods);

      JSONObject treatments = new JSONObject();
      treatments.put("type", TermDTO.CLASS);
      treatments.put("label", label);
      treatments.put("relType", CaseTreatmentDTO.CLASS);
      treatments.put("relAttribute", CaseTreatmentDTO.AMOUNT);
      treatments.put("options", getAllTermsForGrid(request, AggregatedCaseViewDTO.CLASS, AggregatedCaseViewDTO.CASETREATMENTS));
      ordered.put("treatments", treatments);

      JSONObject stocks = new JSONObject();
      stocks.put("type", TermDTO.CLASS);
      stocks.put("label", label);
      stocks.put("relType", CaseTreatmentStockDTO.CLASS);
      stocks.put("relAttribute", CaseTreatmentStockDTO.OUTOFSTOCK);
      stocks.put("options", getAllTermsForGrid(request, AggregatedCaseViewDTO.CLASS, AggregatedCaseViewDTO.CASESTOCKS));
      ordered.put("stocks", stocks);

      JSONObject diagnostics = new JSONObject();
      diagnostics.put("type", TermDTO.CLASS);
      diagnostics.put("label", label);
      diagnostics.put("relType", CaseDiagnosticDTO.CLASS);
      diagnostics.put("relAttribute", CaseDiagnosticDTO.AMOUNT);
      diagnostics.put("options", getAllTermsForGrid(request, AggregatedCaseViewDTO.CLASS, AggregatedCaseViewDTO.CASEDIAGNOSTIC));
      ordered.put("diagnostics", diagnostics);

      JSONObject diagnosticsP = new JSONObject();
      diagnosticsP.put("type", TermDTO.CLASS);
      diagnosticsP.put("label", label);
      diagnosticsP.put("relType", CaseDiagnosticDTO.CLASS);
      diagnosticsP.put("relAttribute", CaseDiagnosticDTO.AMOUNTPOSITIVE);
      diagnosticsP.put("options", getAllTermsForGrid(request, AggregatedCaseViewDTO.CLASS, AggregatedCaseViewDTO.CASEDIAGNOSTIC));
      ordered.put("diagnosticsPositive", diagnosticsP);

      JSONObject referrals = new JSONObject();
      referrals.put("type", TermDTO.CLASS);
      referrals.put("label", label);
      referrals.put("relType", CaseReferralDTO.CLASS);
      referrals.put("relAttribute", CaseReferralDTO.AMOUNT);
      referrals.put("options", getAllTermsForGrid(request, AggregatedCaseViewDTO.CLASS, AggregatedCaseViewDTO.CASEREFERRALS));
      ordered.put("referrals", referrals);

      JSONObject stockReferrals = new JSONObject();
      stockReferrals.put("type", TermDTO.CLASS);
      stockReferrals.put("label", label);
      stockReferrals.put("relType", CaseStockReferralDTO.CLASS);
      stockReferrals.put("relAttribute", CaseStockReferralDTO.AMOUNT);
      stockReferrals.put("options", getAllTermsForGrid(request, AggregatedCaseViewDTO.CLASS, AggregatedCaseViewDTO.CASESTOCKREFERRAL));
      ordered.put("stockReferrals", stockReferrals);

      JSONObject typeOfDiagnosis = new JSONObject();
      typeOfDiagnosis.put("type", TermDTO.CLASS);
      typeOfDiagnosis.put("label", label);
      typeOfDiagnosis.put("relType", CaseDiagnosisTypeDTO.CLASS);
      typeOfDiagnosis.put("relAttribute", CaseDiagnosisTypeDTO.TERM);
      typeOfDiagnosis.put("options", getAllTermsForGrid(request, AggregatedCaseViewDTO.CLASS, AggregatedCaseViewDTO.CASEDIAGNOSISTYPE));
      ordered.put("diagnosisTypes", typeOfDiagnosis);

      JSONObject diagnosisTypeAmounts = new JSONObject();
      diagnosisTypeAmounts.put("type", TermDTO.CLASS);
      diagnosisTypeAmounts.put("label", label);
      diagnosisTypeAmounts.put("relType", CaseDiagnosisTypeAmountDTO.CLASS);
      diagnosisTypeAmounts.put("relAttribute", CaseDiagnosisTypeAmountDTO.AMOUNT);
      diagnosisTypeAmounts.put("options", getAllTermsForGrid(request, CaseDiagnosisTypeViewDTO.CLASS, CaseDiagnosisTypeViewDTO.DIAGNOSISCATEGORY));
      ordered.put("diagnosisTypeAmounts", diagnosisTypeAmounts);

      JSONObject manifestations = new JSONObject();
      manifestations.put("type", TermDTO.CLASS);
      manifestations.put("label", label);
      manifestations.put("relType", CaseDiseaseManifestationDTO.CLASS);
      manifestations.put("relAttribute", CaseDiseaseManifestationDTO.TERM);
      manifestations.put("options", getAllTermsForGrid(request, AggregatedCaseViewDTO.CLASS, AggregatedCaseViewDTO.CASEDISEASEMANIFESTATION));
      ordered.put("manifestations", manifestations);

      JSONObject manifestationAmmounts = new JSONObject();
      manifestationAmmounts.put("type", TermDTO.CLASS);
      manifestationAmmounts.put("label", label);
      manifestationAmmounts.put("relType", CaseDiseaseManifestationAmountDTO.CLASS);
      manifestationAmmounts.put("relAttribute", CaseDiseaseManifestationAmountDTO.AMOUNT);
      manifestationAmmounts.put("options", getAllTermsForGrid(request, CaseDiseaseManifestationViewDTO.CLASS, CaseDiseaseManifestationViewDTO.DISEASECATEGORY));
      ordered.put("manifestationAmmounts", manifestationAmmounts);

      JSONObject patientTypes = new JSONObject();
      patientTypes.put("type", TermDTO.CLASS);
      patientTypes.put("label", label);
      patientTypes.put("relType", CasePatientTypeDTO.CLASS);
      patientTypes.put("relAttribute", CasePatientTypeDTO.TERM);
      patientTypes.put("options", getAllTermsForGrid(request, AggregatedCaseViewDTO.CLASS, AggregatedCaseViewDTO.CASEPATIENTTYPE));
      ordered.put("patientTypes", patientTypes);

      JSONObject patientTypeAmounts = new JSONObject();
      patientTypeAmounts.put("type", TermDTO.CLASS);
      patientTypeAmounts.put("label", label);
      patientTypeAmounts.put("relType", CasePatientTypeAmountDTO.CLASS);
      patientTypeAmounts.put("relAttribute", CasePatientTypeAmountDTO.AMOUNT);
      patientTypeAmounts.put("options", getAllTermsForGrid(request, CasePatientTypeViewDTO.CLASS, CasePatientTypeViewDTO.PATIENTCATEGORY));
      ordered.put("patientTypeAmounts", patientTypeAmounts);

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_AGGREGATED_CASES).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  private void failQuery() throws ServletException, IOException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  private void loadGrid(String relationshipClass, String className, String attributeName, JSONObject gridMeta, JSONArray ordered)
  {
    ordered.put(gridMeta);

    for (TermDTO term : TermDTO.getAllTermsForField(this.getClientRequest(), className, attributeName))
    {
      try
      {
        JSONObject option = new JSONObject();
        option.put("optionName", term.getId());
        option.put("displayLabel", term.getDisplayLabel());
        // option.put("attributeName", AbstractGridDTO.OPTIONNAME);
        option.put("attributeName", TermDTO.ID);
        // option.put("type", grid.getType());
        option.put("type", TermDTO.CLASS);

        gridMeta.getJSONArray("options").put(option);
      }
      catch (JSONException e)
      {
        // Setup problem. Developer error.
        throw new ClientException(e);
      }
    }
  }

  private JSONArray getAllTermsForGrid(ClientRequestIF request, String klass, String attribute) throws JSONException
  {
    JSONArray array = new JSONArray();
    for (TermDTO term : TermDTO.getAllTermsForField(request, klass, attribute))
    {
      JSONObject option = new JSONObject();
      option.put("id", term.getId());
      option.put("displayLabel", term.getDisplayLabel());
      option.put("MOID", term.getTermId());
      option.put("optionName", term.getName());
      option.put("type", TermDTO.CLASS);
      array.put(option);
    }
    return array;
  }

  @Override
  public void newQuery() throws IOException, ServletException
  {
    try
    {
      this.req.setAttribute("savedSearch", new SavedSearchViewDTO(this.getClientRequest()));
      this.req.getRequestDispatcher(NEW_QUERY).forward(this.req, this.resp);
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  /**
   * Creates the screen to query for Entomology (mosquitos).
   */
  @Override
  public void queryAggregatedIPT() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(AggregatedIPTDTO.CLASS, QueryConstants.QueryType.QUERY_AGGREGATED_IPT);

      ClientRequestIF request = this.getClientRequest();

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO aIPT = request.getQuery(AggregatedIPTDTO.CLASS);
      String iptMap = Halp.getDropDownMaps(aIPT, request, ", ");
      req.setAttribute("iptMap", iptMap);

      JSONObject ordered = new JSONObject();

      // Map<String, JSONObject> orderedMap = new HashMap<String, JSONObject>();

      // AggregatedIPTViewDTO av = new AggregatedIPTViewDTO(request);

      // Patients
      JSONObject patients = new JSONObject();
      patients.put("type", TermDTO.CLASS);
      patients.put("label", LocalizationFacadeDTO.getFromBundles(request, "Facility_referred"));
      patients.put("relType", IPTPatientsDTO.CLASS);
      patients.put("relAttribute", IPTPatientsDTO.AMOUNT);
      patients.put("options", getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYPATIENTS));
      ordered.put("patients", patients);

      // Doses
      JSONObject doses = new JSONObject();
      doses.put("type", TermDTO.CLASS);
      doses.put("label", LocalizationFacadeDTO.getFromBundles(request, "Diagnostic_methods"));
      doses.put("relType", IPTDoseDTO.CLASS);
      doses.put("relAttribute", IPTDoseDTO.AMOUNT);
      doses.put("options", getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYDOSE));
      ordered.put("doses", doses);

      // Visits
      JSONObject visits = new JSONObject();
      visits.put("type", TermDTO.CLASS);
      visits.put("label", LocalizationFacadeDTO.getFromBundles(request, "Treatment_methods"));
      visits.put("relType", IPTANCVisitDTO.CLASS);
      visits.put("relAttribute", IPTANCVisitDTO.AMOUNT);
      visits.put("options", getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYVISITS));
      ordered.put("visits", visits);

      // Treatment
      JSONObject treatment = new JSONObject();
      treatment.put("type", TermDTO.CLASS);
      treatment.put("label", LocalizationFacadeDTO.getFromBundles(request, "Treatments"));
      treatment.put("relType", IPTTreatmentDTO.CLASS);
      treatment.put("relAttribute", IPTTreatmentDTO.AMOUNT);
      treatment.put("options", getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYTREATMENTS));
      ordered.put("treatments", treatment);

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_AGGREGATED_IPT).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  @Override
  public void queryAggregatedITN() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(ITNDataDTO.CLASS, QueryConstants.QueryType.QUERY_AGGREGATED_ITN);

      ClientRequestIF request = this.getClientRequest();

      JSONObject ordered = new JSONObject();

      ITNDataViewDTO itn = new ITNDataViewDTO(request);

      // Nets
      JSONObject patients = new JSONObject();
      patients.put("type", TermDTO.CLASS);
      patients.put("label", itn.getDisplayNetsMd().getDisplayLabel());
      patients.put("relType", ITNNetDTO.CLASS);
      patients.put("relAttribute", ITNNetDTO.AMOUNT);
      patients.put("options", getAllTermsForGrid(request, ITNDataViewDTO.CLASS, ITNDataViewDTO.DISPLAYNETS));
      ordered.put("nets", patients);

      // Target Groups
      JSONObject doses = new JSONObject();
      doses.put("type", TermDTO.CLASS);
      doses.put("label", itn.getDisplayTargetGroupsMd().getDisplayLabel());
      doses.put("relType", ITNTargetGroupDTO.CLASS);
      doses.put("relAttribute", ITNTargetGroupDTO.AMOUNT);
      doses.put("options", getAllTermsForGrid(request, ITNDataViewDTO.CLASS, ITNDataViewDTO.DISPLAYTARGETGROUPS));
      ordered.put("targetGroups", doses);

      // Visits
      JSONObject visits = new JSONObject();
      visits.put("type", TermDTO.CLASS);
      visits.put("label", itn.getDisplayServicesMd().getDisplayLabel());
      visits.put("relType", ITNServiceDTO.CLASS);
      visits.put("relAttribute", ITNServiceDTO.AMOUNT);
      visits.put("options", getAllTermsForGrid(request, ITNDataViewDTO.CLASS, ITNDataViewDTO.DISPLAYSERVICES));
      ordered.put("services", visits);

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_AGGREGATED_ITN).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  @Override
  public void queryITNCommunityDistribution() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(ITNCommunityDistributionDTO.CLASS, QueryConstants.QueryType.QUERY_ITN_COMMUNITY_DISTRIBUTION);

      ClientRequestIF request = this.getClientRequest();

      JSONObject ordered = new JSONObject();

      ITNCommunityDistributionViewDTO itnView = new ITNCommunityDistributionViewDTO(request);

      // Load label map
      ClassQueryDTO itn = request.getQuery(ITNCommunityDistributionDTO.CLASS);
      String itnMap = Halp.getDropDownMaps(itn, request, ", ");
      req.setAttribute("itnMap", itnMap);

      // Nets
      JSONObject patients = new JSONObject();
      patients.put("type", TermDTO.CLASS);
      patients.put("label", itnView.getDisplayNetsMd().getDisplayLabel());
      patients.put("relType", ITNCommunityNetDTO.CLASS);
      patients.put("relAttribute", ITNCommunityNetDTO.AMOUNT);
      patients.put("options", getAllTermsForGrid(request, ITNCommunityDistributionViewDTO.CLASS, ITNCommunityDistributionViewDTO.DISPLAYNETS));
      ordered.put("nets", patients);

      // Target Groups
      JSONObject doses = new JSONObject();
      doses.put("type", TermDTO.CLASS);
      doses.put("label", itnView.getDisplayTargetGroupsMd().getDisplayLabel());
      doses.put("relType", ITNCommunityTargetGroupDTO.CLASS);
      doses.put("relAttribute", ITNCommunityTargetGroupDTO.AMOUNT);
      doses.put("options", getAllTermsForGrid(request, ITNCommunityDistributionViewDTO.CLASS, ITNCommunityDistributionViewDTO.DISPLAYTARGETGROUPS));
      ordered.put("targetGroups", doses);

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_ITN_COMMUNITY_DISTRIBUTION).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  @Override
  public void queryITNDistribution() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(ITNDistributionDTO.CLASS, QueryConstants.QueryType.QUERY_ITN_FACILITY_DISTRIBUTION);

      ClientRequestIF request = this.getClientRequest();

      JSONObject ordered = new JSONObject();

      ITNDistributionViewDTO itnView = new ITNDistributionViewDTO(request);

      // Load label map
      ClassQueryDTO itn = request.getQuery(ITNDistributionTargetGroupDTO.CLASS);
      String itnMap = Halp.getDropDownMaps(itn, request, ", ");
      req.setAttribute("itnMap", itnMap);

      // Target Groups
      JSONObject doses = new JSONObject();
      doses.put("type", TermDTO.CLASS);
      doses.put("label", itnView.getTargetGroupsMd().getDisplayLabel());
      doses.put("relType", ITNDistributionTargetGroupDTO.CLASS);
      doses.put("relAttribute", ITNDistributionTargetGroupDTO.AMOUNT);
      doses.put("options", getAllTermsForGrid(request, ITNDistributionViewDTO.CLASS, ITNDistributionViewDTO.TARGETGROUPS));
      ordered.put("targetGroups", doses);

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_ITN_FACILITY_DISTRIBUTION).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  public void queryMosquitoCollections() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(MosquitoCollectionDTO.CLASS, QueryConstants.QueryType.QUERY_MOSQUITO_COLLECTIONS);

      ClientRequestIF request = this.getClientRequest();

      // Load label map
      ClassQueryDTO collectionQuery = request.getQuery(MosquitoCollectionDTO.CLASS);
      String collectionMap = Halp.getDropDownMaps(collectionQuery, request, ", ");
      req.setAttribute("collectionMaps", collectionMap);

      req.getRequestDispatcher(QUERY_MOSQUITO_COLLECTIONS).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  /**
   * Creates the screen to query IndividualIPT
   */
  @Override
  public void queryIndividualIPT() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(IndividualIPTDTO.CLASS, QueryConstants.QueryType.QUERY_INDIVIDUAL_IPT);

      ClientRequestIF request = this.getClientRequest();

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO aIPT = request.getQuery(IndividualIPTDTO.CLASS);
      String iptMap = Halp.getDropDownMaps(aIPT, request, ", ");
      req.setAttribute("iptMap", iptMap);

      req.getRequestDispatcher(QUERY_INDIVIDUAL_IPT).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  /**
   * Creates the screen to query stock
   */
  @Override
  public void queryStock() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(StockItemDTO.CLASS, QueryConstants.QueryType.QUERY_STOCK);

      ClientRequestIF request = this.getClientRequest();

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO stock = request.getQuery(StockEventDTO.CLASS);
      String map = Halp.getDropDownMaps(stock, request, ", ");
      req.setAttribute("stockMap", map);

      req.getRequestDispatcher(QUERY_STOCK).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  /**
   * Creates the screen to query larvacide
   */
  @Override
  public void queryLarvacide() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(LarvacideDTO.CLASS, QueryConstants.QueryType.QUERY_LARVACIDE);

      ClientRequestIF request = this.getClientRequest();

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO larvacide = request.getQuery(LarvacideDTO.CLASS);
      String map = Halp.getDropDownMaps(larvacide, request, ", ");
      req.setAttribute("larvacideMap", map);

      req.getRequestDispatcher(QUERY_LARVACIDE).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  /**
   * Creates the screen to query contaner collections
   */
  public void queryImmatureContainerCollection() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(ImmatureCollectionDTO.CLASS, QueryConstants.QueryType.QUERY_IMMATURE_CONTAINER_COLLECTION);

      ClientRequestIF request = this.getClientRequest();

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO collection = request.getQuery(ImmatureCollectionDTO.CLASS);
      String map = Halp.getDropDownMaps(collection, request, ", ");
      req.setAttribute("collectionMap", map);

      req.getRequestDispatcher(QUERY_IMMATURE_CONTAINER_COLLECTION).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  /**
   * Creates the screen to query contaner collections
   */
  public void queryPupalContainerCollection() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(PupalCollectionDTO.CLASS, QueryConstants.QueryType.QUERY_PUPAL_CONTAINER_COLLECTION);

      ClientRequestIF request = this.getClientRequest();

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO collection = request.getQuery(PupalCollectionDTO.CLASS);
      String map = Halp.getDropDownMaps(collection, request, ", ");
      req.setAttribute("collectionMap", map);

      ClassQueryDTO container = request.getQuery(PupalContainerDTO.CLASS);
      String container_map = Halp.getDropDownMaps(container, request, ", ");
      req.setAttribute("containerMap", container_map);

      JSONObject ordered = new JSONObject();

      // Target Groups
      JSONObject taxons = new JSONObject();
      taxons.put("type", TermDTO.CLASS);
      taxons.put("label", LocalizationFacadeDTO.getFromBundles(request, "Pupae_Amount"));
      taxons.put("relType", PupalContainerAmountDTO.CLASS);
      taxons.put("relAttribute", PupalContainerAmountDTO.AMOUNT);
      taxons.put("options", getAllTermsForGrid(request, PupalContainerViewDTO.CLASS, PupalContainerViewDTO.PUPAEAMOUNT));
      ordered.put("pupaeAmmount", taxons);

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_PUPAL_CONTAINER_COLLECTION).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  /**
   * Creates the screen to query Invervention COntrol
   */
  public void queryInterventionControl() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(ControlInterventionDTO.CLASS, QueryConstants.QueryType.QUERY_INTERVENTION_CONTROL);

      ClientRequestIF request = this.getClientRequest();

      ClassQueryDTO insecticideBrand = request.getQuery(InsecticideBrandDTO.CLASS);
      String insecticideBrandMap = Halp.getDropDownMaps(insecticideBrand, request, ", ");

      // Product names for Intervention Control
      InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getControlInterventionInsecticideBrands(this.getClientRequest());
      insecticideBrandMap = Halp.getDropDownMaps(insecticideBrandMap, brands, InsecticideBrandDTO.PRODUCTNAME);

      req.setAttribute("insecticideBrandMap", insecticideBrandMap);

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO ci = request.getQuery(ControlInterventionDTO.CLASS);
      String map = Halp.getDropDownMaps(ci, request, ", ");
      req.setAttribute("ciMap", map);

      JSONObject ordered = new JSONObject();

      ClassQueryDTO iim = request.getQuery(IndividualPremiseVisitDTO.CLASS);
      String iimap = Halp.getDropDownMaps(iim, request, ", ");
      req.setAttribute("individualPremiseVisit", iimap);

      JSONObject interventionMethods = new JSONObject();
      interventionMethods.put("type", TermDTO.CLASS);
      interventionMethods.put("label", LocalizationFacadeDTO.getFromBundles(request, "Amount"));
      interventionMethods.put("relType", IndividualPremiseVisitMethodDTO.CLASS);
      interventionMethods.put("relAttribute", IndividualPremiseVisitMethodDTO.USED);
      interventionMethods.put("options", getAllTermsForGrid(request, IndividualPremiseVisitViewDTO.CLASS, IndividualPremiseVisitViewDTO.INTERVENTIONMETHOD));
      ordered.put("individualPremiseVisitMethod", interventionMethods);

      // Treat IndividualPremiseVisitDTO.REASONSFORNOTTREATED more like a Grid
      // instead of a reference attribute.
      TermDTO[] reasons = TermDTO.getAllTermsForField(request, IndividualPremiseVisitViewDTO.CLASS, IndividualPremiseVisitViewDTO.REASONSFORNOTTREATED);
      JSONArray reasonsArr = new JSONArray();
      for (TermDTO reason : reasons)
      {
        JSONObject r = new JSONObject();
        r.put("id", reason.getId());
        r.put("key", QueryConstants.REASONS_FOR_NOT_TREATED_PREFIX + reason.getId());
        r.put("label", reason.getDisplayLabel());
        reasonsArr.put(r);
      }
      req.setAttribute("reasons", reasonsArr.toString());

      ClassQueryDTO aim = request.getQuery(AggregatedPremiseVisitDTO.CLASS);
      String aimap = Halp.getDropDownMaps(aim, request, ", ");
      req.setAttribute("aggregatedPremiseVisit", aimap);

      JSONObject aggInterventionMethods = new JSONObject();
      aggInterventionMethods.put("type", TermDTO.CLASS);
      aggInterventionMethods.put("label", LocalizationFacadeDTO.getFromBundles(request, "Amount"));
      aggInterventionMethods.put("relType", AggregatedPremiseMethodDTO.CLASS);
      aggInterventionMethods.put("relAttribute", AggregatedPremiseMethodDTO.AMOUNT);
      aggInterventionMethods.put("options", getAllTermsForGrid(request, AggregatedPremiseVisitViewDTO.CLASS, AggregatedPremiseVisitViewDTO.INTERVENTIONMETHOD));
      ordered.put("aggInterventionMethods", aggInterventionMethods);

      JSONObject aggInterventionReasons = new JSONObject();
      aggInterventionReasons.put("type", TermDTO.CLASS);
      aggInterventionReasons.put("label", LocalizationFacadeDTO.getFromBundles(request, "Amount"));
      aggInterventionReasons.put("relType", AggregatedPremiseReasonDTO.CLASS);
      aggInterventionReasons.put("relAttribute", AggregatedPremiseReasonDTO.AMOUNT);
      aggInterventionReasons.put("options", getAllTermsForGrid(request, AggregatedPremiseVisitViewDTO.CLASS, AggregatedPremiseVisitViewDTO.NONTREATMENTREASON));
      ordered.put("aggInterventionReasons", aggInterventionReasons);

      ClassQueryDTO pim = request.getQuery(PersonInterventionDTO.CLASS);
      String pimap = Halp.getDropDownMaps(pim, request, ", ");
      req.setAttribute("PersonIntervention", pimap);

      JSONObject personInterventionMethod = new JSONObject();
      personInterventionMethod.put("type", TermDTO.CLASS);
      personInterventionMethod.put("label", LocalizationFacadeDTO.getFromBundles(request, "Amount"));
      personInterventionMethod.put("relType", PersonInterventionMethodDTO.CLASS);
      personInterventionMethod.put("relAttribute", PersonInterventionMethodDTO.AMOUNT);
      personInterventionMethod.put("options", getAllTermsForGrid(request, PersonInterventionViewDTO.CLASS, PersonInterventionViewDTO.INTERVENTIONMETHOD));
      ordered.put("personInterventionMethods", personInterventionMethod);

      req.setAttribute("orderedGrids", ordered.toString());

      IndividualPremiseVisitViewDTO individualPremiseVisitViewDTO = new IndividualPremiseVisitViewDTO(this.getClientRequest());

      req.setAttribute("individualMethodLabel", individualPremiseVisitViewDTO.getInterventionMethodMd().getDisplayLabel());
      req.setAttribute("individualReasonLabel", individualPremiseVisitViewDTO.getReasonsForNotTreatedMd().getDisplayLabel());

      AggregatedPremiseVisitViewDTO aggPremiseVisitViewDTO = new AggregatedPremiseVisitViewDTO(this.getClientRequest());

      req.setAttribute("aggMethodLabel", aggPremiseVisitViewDTO.getInterventionMethodMd().getDisplayLabel());
      req.setAttribute("aggReasonLabel", aggPremiseVisitViewDTO.getNonTreatmentReasonMd().getDisplayLabel());

      PersonInterventionViewDTO personInterventionViewDTO = new PersonInterventionViewDTO(this.getClientRequest());

      req.setAttribute("personMethodLabel", personInterventionViewDTO.getInterventionMethodMd().getDisplayLabel());

      req.getRequestDispatcher(QUERY_INTERVENTION_CONTROL).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  /**
   * Creates the screen to query
   */
  @Override
  public void queryIndividualCases() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(IndividualCaseDTO.CLASS, QueryConstants.QueryType.QUERY_INDIVIDUAL_CASES);

      ClientRequestIF request = this.getClientRequest();

      // Load label map
      ClassQueryDTO iInstance = request.getQuery(IndividualInstanceDTO.CLASS);
      String instanceMap = Halp.getDropDownMaps(iInstance, request, ", ");
      req.setAttribute("instanceMaps", instanceMap);

      JSONObject ordered = new JSONObject();

      // Treatment
      JSONObject symptoms = new JSONObject();
      symptoms.put("type", TermDTO.CLASS);
      symptoms.put("label", LocalizationFacadeDTO.getFromBundles(request, "Malaria_Symptom"));
      symptoms.put("relType", IndividualCaseSymptomDTO.CLASS);
      symptoms.put("relAttribute", IndividualCaseSymptomDTO.HASSYMPTOM);
      symptoms.put("options", new JSONArray());

      TermDTO[] allSymptoms = TermDTO.getRootChildren(request, IndividualInstanceDTO.CLASS, IndividualInstanceDTO.SYMPTOM, false);

      allSymptoms = TermComparator.sort(allSymptoms);

      for (TermDTO term : allSymptoms)
      {
        JSONObject option = new JSONObject();
        option.put("id", term.getId());
        option.put("displayLabel", term.getDisplayLabel());
        option.put("MOID", term.getTermId());
        option.put("optionName", term.getName());
        option.put("type", TermDTO.CLASS);
        symptoms.getJSONArray("options").put(option);
      }
      ordered.put("symptoms", symptoms);

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_INDIVIDUAL_CASES).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  @Override
  public void queryEfficacyAssay() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(EfficacyAssayDTO.CLASS, QueryConstants.QueryType.QUERY_EFFICACY_ASSAY);

      ClientRequestIF request = this.getClientRequest();

      ClassQueryDTO insecticideBrand = request.getQuery(InsecticideBrandDTO.CLASS);
      String insecticideBrandMap = Halp.getDropDownMaps(insecticideBrand, request, ", ");

      // Product names for Efficacy Assay
      InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getEfficacyAssayInsecticideBrands(this.getClientRequest());
      insecticideBrandMap = Halp.getDropDownMaps(insecticideBrandMap, brands, InsecticideBrandDTO.PRODUCTNAME);

      req.setAttribute("insecticideBrandMap", insecticideBrandMap);

      req.getRequestDispatcher(QUERY_EFFICACY_ASSAY).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  /**
   * Creates the screen to query for Entomology (mosquitos).
   */

  public void queryEntomology() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(MosquitoCollectionDTO.CLASS, QueryConstants.QueryType.QUERY_ENTOMOLOGY);

      ClientRequestIF request = this.getClientRequest();

      // Load label map
      ClassQueryDTO collectionQuery = request.getQuery(MosquitoCollectionDTO.CLASS);
      String collectionMap = Halp.getDropDownMaps(collectionQuery, request, ", ");
      req.setAttribute("collectionMaps", collectionMap);

      // Load label map
      ClassQueryDTO infectionQuery = request.getQuery(InfectionAssayDTO.CLASS);
      String infectionMap = Halp.getDropDownMaps(infectionQuery, request, ", ");
      req.setAttribute("infectionMaps", infectionMap);

      // Load label map
      ClassQueryDTO pooledInfectionQuery = request.getQuery(PooledInfectionAssayDTO.CLASS);
      String pooledInfectionMap = Halp.getDropDownMaps(pooledInfectionQuery, request, ", ");
      req.setAttribute("pooledInfectionMaps", pooledInfectionMap);

      // Load label map
      ClassQueryDTO biochemicalQuery = request.getQuery(BiochemicalAssayDTO.CLASS);
      String biochemicalMap = Halp.getDropDownMaps(biochemicalQuery, request, ", ");
      req.setAttribute("biochemicalMaps", biochemicalMap);

      // Load label map
      ClassQueryDTO molecularQuery = request.getQuery(MolecularAssayDTO.CLASS);
      String molecularMap = Halp.getDropDownMaps(molecularQuery, request, ", ");
      req.setAttribute("molecularMaps", molecularMap);

      req.getRequestDispatcher(QUERY_ENTOMOLOGY).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  public void queryResistanceBioassay() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(MosquitoCollectionDTO.CLASS, QueryConstants.QueryType.QUERY_RESISTANCE_BIOASSAY);

      ClientRequestIF request = this.getClientRequest();

      ClassQueryDTO adda = request.getQuery(TimeResponseAssayDTO.CLASS);
      String timeResponseMap = Halp.getDropDownMaps(adda, request, ", ");
      req.setAttribute("timeResponseMap", timeResponseMap);

      ClassQueryDTO ldda = request.getQuery(DiagnosticAssayDTO.CLASS);
      String diagnosticMap = Halp.getDropDownMaps(ldda, request, ", ");
      req.setAttribute("diagnosticMap", diagnosticMap);

      JSONObject ordered = new JSONObject();

      // Target Groups
      JSONObject lifeStage = new JSONObject();
      lifeStage.put("type", TermDTO.CLASS);
      // lifeStage.put("label",MDSSProperties.getObject("Life_Stage") );
      lifeStage.put("relType", TimeResponseAssayDTO.CLASS);
      lifeStage.put("relAttribute", TimeResponseAssayDTO.LIFESTAGE);
      lifeStage.put("options", getAllTermsForGrid(request, TimeResponseAssayViewDTO.CLASS, TimeResponseAssayViewDTO.LIFESTAGE));
      ordered.put("lifeStage", lifeStage);

      // Target Groups
      JSONObject assayType = new JSONObject();
      assayType.put("type", TermDTO.CLASS);
      // assayType.put("label",MDSSProperties.getObject("Assay") );
      assayType.put("relType", TimeResponseAssayDTO.CLASS);
      assayType.put("relAttribute", TimeResponseAssayDTO.ASSAY);
      assayType.put("options", getAllTermsForGrid(request, TimeResponseAssayViewDTO.CLASS, TimeResponseAssayViewDTO.ASSAY));
      ordered.put("assayType", assayType);

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_RESISTANCE_BIOASSAY).forward(req, resp);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  /**
   * Creates the screen to query for Entomology (mosquitos).
   */

  public void queryResistance() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(MosquitoCollectionDTO.CLASS, QueryConstants.QueryType.QUERY_RESISTANCE);

      ClientRequestIF request = this.getClientRequest();

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO adda = request.getQuery(AdultDiscriminatingDoseAssayDTO.CLASS);
      String adultMap = Halp.getDropDownMaps(adda, request, ", ");
      req.setAttribute("adultMap", adultMap);

      // Load label map for Larvae Discriminating Dose Assay
      ClassQueryDTO ldda = request.getQuery(LarvaeDiscriminatingDoseAssayDTO.CLASS);
      String larvaeMap = Halp.getDropDownMaps(ldda, request, ", ");
      req.setAttribute("larvaeMap", larvaeMap);

      // Load label map for Knock Down Dose Assay
      ClassQueryDTO kda = request.getQuery(KnockDownAssayDTO.CLASS);
      String knockDownMap = Halp.getDropDownMaps(kda, request, ", ");
      req.setAttribute("knockDownMap", knockDownMap);

      // Load label map for Insecticde
      ClassQueryDTO insecticide = request.getQuery(InsecticideDTO.CLASS);
      String insecticideMap = Halp.getDropDownMaps(insecticide, request, ", ");
      req.setAttribute("insecticideMap", insecticideMap);

      req.getRequestDispatcher(QUERY_RESISTANCE).forward(req, resp);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  /**
   * Creates the screen to query for Entomology (mosquitos).
   */

  public void queryIRS() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(AbstractSprayDTO.CLASS, QueryConstants.QueryType.QUERY_IRS);

      ClientRequestIF request = this.getClientRequest();

      // Load label map for spray data
      ClassQueryDTO operatorSpray = request.getQuery(OperatorSprayDTO.CLASS);
      String sprayDataMap = Halp.getDropDownMaps(operatorSpray, request, ", ");
      req.setAttribute("operatorSprayMap", sprayDataMap);

      ClassQueryDTO insecticideBrand = request.getQuery(InsecticideBrandDTO.CLASS);
      String insecticideBrandMap = Halp.getDropDownMaps(insecticideBrand, request, ", ");

      // Product names for IRS only
      InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getIRSInsecticideBrands(this.getClientRequest());
      insecticideBrandMap = Halp.getDropDownMaps(insecticideBrandMap, brands, InsecticideBrandDTO.PRODUCTNAME);

      req.setAttribute("insecticideBrandMap", insecticideBrandMap);

      req.getRequestDispatcher(QUERY_IRS).forward(req, resp);

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }

  // @Override
  public void exportQueryToCSV(String className, String queryXML, String config, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = QueryBuilderDTO.exportQueryToCSV(this.getClientRequest(), className, queryXML, config, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  // @Override
  public void exportQueryToExcel(String className, String queryXML, String config, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = QueryBuilderDTO.exportQueryToExcel(this.getClientRequest(), className, queryXML, config, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeXLS(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void cancelQuery(SavedSearchViewDTO savedQueryView) throws IOException, ServletException
  {
    // TODO Auto-generated method stub
    super.cancelQuery(savedQueryView);
  }

  @Override
  public void deleteQuery(SavedSearchViewDTO savedQueryView) throws IOException, ServletException
  {
    // TODO Auto-generated method stub
    super.deleteQuery(savedQueryView);
  }

  @Override
  public void failQueryAggregatedCases() throws IOException, ServletException
  {
    failQuery();
  }

  @Override
  public void failQueryAggregatedIPT() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryAggregatedITN() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryEfficacyAssay() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryEntomology() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryImmatureContainerCollection() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryIndicatorSurvey() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryIndividualCases() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryIndividualIPT() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryInterventionControl() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryIRS() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryITNCommunityDistribution() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryITNDistribution() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryLarvacide() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryMosquitoCollections() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryPupalContainerCollection() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryResistance() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryResistanceBioassay() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQueryStock() throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void failQuerySurvey() throws IOException, ServletException
  {
    this.failQuery();
  }

  public void queryType(String type) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      MdWebFormDTO form = MdFormUtilDTO.getForm(request, type);
      MdClassDTO formMdClass = form.getFormMdClass();
      String classType = formMdClass.getPackageName() + "." + formMdClass.getTypeName();

      FormQueryBuilder builder = new FormQueryBuilder(request, QueryConstants.TYPE_QB);
      builder.setQuerySpecifics(classType, QueryConstants.TYPE_QB);
      builder.addForm(MdFormUtilDTO.getForm(request, type), "root");

      String formDisplayLabel = form.getDisplayLabel().toString();
      this.req.setAttribute("localized_page_title", formDisplayLabel);

      builder.populateRequest(req);

      req.getRequestDispatcher(QUERY_TYPE).forward(req, resp);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }

  }

  public void failQueryType(String type) throws IOException, ServletException
  {
    this.failQuery();
  }

  @Override
  public void queryFormSurvey() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      FormQueryBuilder builder = new FormQueryBuilder(request, QueryConstants.FORM_SURVEY_QB);
      builder.setQuerySpecifics(FormSurveyDTO.CLASS, QueryConstants.QueryType.QUERY_FORM_SURVEY);
      builder.addForm(MdFormUtilDTO.getForm(request, FormSurveyDTO.FORM_TYPE), "root");
      builder.addForm(MdFormUtilDTO.getForm(request, FormHouseholdDTO.FORM_TYPE), "root");
      builder.addForm(MdFormUtilDTO.getForm(request, FormPersonDTO.FORM_TYPE), "root");
      builder.addForm(MdFormUtilDTO.getForm(request, FormBedNetDTO.FORM_TYPE), "root");

      builder.populateRequest(req);

      MdWebFormDTO surveyForm = MdFormUtilDTO.getForm(this.getClientRequest(), FormSurveyDTO.FORM_TYPE);
      req.setAttribute("localized_page_title", surveyForm.getDisplayLabel().getValue());

      req.getRequestDispatcher(QUERY_TYPE).forward(req, resp);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failQuery();
      }
    }
  }
}
