package dss.vector.solutions.query;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.ApplicationException;
import com.terraframe.mojo.business.BusinessDTO;
import com.terraframe.mojo.business.ClassQueryDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.transport.attributes.AttributeDTO;
import com.terraframe.mojo.transport.attributes.AttributeReferenceDTO;
import com.terraframe.mojo.transport.attributes.AttributeStructDTO;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;

import dss.vector.solutions.entomology.BiochemicalAssayDTO;
import dss.vector.solutions.entomology.InfectionAssayDTO;
import dss.vector.solutions.entomology.MolecularAssayDTO;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.entomology.PooledInfectionAssayDTO;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.EfficacyAssayDTO;
import dss.vector.solutions.entomology.assay.KnockDownAssayDTO;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO;
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
import dss.vector.solutions.intervention.monitor.LarvacideDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.intervention.monitor.SurveyedPersonDTO;
import dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO;
import dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO;
import dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO;
import dss.vector.solutions.irs.AbstractSprayDTO;
import dss.vector.solutions.irs.OperatorSprayDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.stock.StockEventDTO;
import dss.vector.solutions.stock.StockItemDTO;
import dss.vector.solutions.surveillance.AggregatedAgeGroupDTO;
import dss.vector.solutions.surveillance.AggregatedCaseDTO;
import dss.vector.solutions.surveillance.CaseDiagnosticDTO;
import dss.vector.solutions.surveillance.CaseReferralDTO;
import dss.vector.solutions.surveillance.CaseTreatmentDTO;
import dss.vector.solutions.surveillance.CaseTreatmentMethodDTO;
import dss.vector.solutions.surveillance.CaseTreatmentStockDTO;
import dss.vector.solutions.surveillance.ChildCaseViewDTO;
import dss.vector.solutions.surveillance.IndividualCaseSymptomDTO;
import dss.vector.solutions.util.FileDownloadUtil;
import dss.vector.solutions.util.Halp;
import dss.vector.solutions.util.MDSSProperties;
import dss.vector.solutions.util.QueryUtil;

public class QueryController extends QueryControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID                 = 1237863171352L;

  private static final String QUERY_ENTOMOLOGY                 = "/WEB-INF/queryScreens/queryEntomology.jsp";

  private static final String QUERY_RESISTANCE                 = "/WEB-INF/queryScreens/queryResistance.jsp";

  private static final String QUERY_IRS                        = "/WEB-INF/queryScreens/queryIRS.jsp";

  private static final String QUERY_AGGREGATED_CASES           = "/WEB-INF/queryScreens/queryAggregatedCases.jsp";

  private static final String QUERY_AGGREGATED_IPT             = "/WEB-INF/queryScreens/queryAggregatedIPT.jsp";

  private static final String QUERY_INDIVIDUAL_IPT             = "/WEB-INF/queryScreens/queryIndividualIPT.jsp";

  private static final String QUERY_INDIVIDUAL_CASES           = "/WEB-INF/queryScreens/queryIndividualCases.jsp";

  private static final String QUERY_SURVEY                     = "/WEB-INF/queryScreens/querySurvey.jsp";

  private static final String QUERY_STOCK                      = "/WEB-INF/queryScreens/queryStock.jsp";

  private static final String QUERY_LARVACIDE                  = "/WEB-INF/queryScreens/queryLarvacide.jsp";

  private static final String NEW_QUERY                        = "/WEB-INF/queryScreens/newQuery.jsp";

  private static final String QUERY_EFFICACY_ASSAY             = "/WEB-INF/queryScreens/queryEfficacyAssay.jsp";

  private static final String QUERY_ITN_COMMUNITY_DISTRIBUTION = "/WEB-INF/queryScreens/queryITNCommunityDistribution.jsp";

  private static final String QUERY_ITN_FACILITY_DISTRIBUTION  = "/WEB-INF/queryScreens/queryITNFacilityDistribution.jsp";

  private static final String QUERY_AGGREGATED_ITN             = "/WEB-INF/queryScreens/queryAggregatedITN.jsp";

  private static final String QUERY_MOSQUITO_COLLECTIONS       = "/WEB-INF/queryScreens/queryMosquitoCollections.jsp";

  public QueryController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
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
    ClientRequestIF request = this.getClientRequest();
    String namespacedType = QueryConstants.namespaceQuery(queryClass, queryType);

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
      throw new ApplicationException(t);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void uploadTemplate(String savedSearchId) throws IOException, ServletException
  {
    String message = "";

    try
    {
      // Create a factory for disk-based file items
      FileItemFactory factory = new DiskFileItemFactory();

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);

      String savedSearchIdValue = null;
      FileItem file = null;
      List<FileItem> items = upload.parseRequest(this.req);
      for (FileItem item : items)
      {
        if (item.getFieldName().equals("savedSearchId"))
        {
          savedSearchIdValue = item.getString();
        }
        else if (!item.isFormField() && item.getSize() > 0)
        {
          file = item;
        }
      }

      ClientRequestIF request = this.getClientRequest();
      if (savedSearchIdValue == null || savedSearchIdValue.trim().length() == 0)
      {
        SavedSearchRequiredExceptionDTO ex = new SavedSearchRequiredExceptionDTO(request, req.getLocale());
        message = ex.getLocalizedMessage();
        return;
      }

      if (file == null)
      {
        message = MDSSProperties.getString("File_Required");
        return;
      }

      // All checks passed. Save the file to the SavedSearch

      // Ensure that a saved search actually exists
      SavedSearchDTO search = SavedSearchDTO.lock(request, savedSearchIdValue);

      String templateId = search.getTemplateFile();

      if (templateId != null && !templateId.equals(""))
      {
        // This search already has a file associated with it.
        // The existing file needs to be deleted
        request.delete(templateId);
      }

      // Upload the template file to the vault
      BusinessDTO templateDTO = request.newSecureFile("template", "rptdesign", file.getInputStream());

      // Associate the template file with the saved search
      search.setTemplateFile(templateDTO.getId());
      search.apply();

      message = MDSSProperties.getString("File_Upload_Success");
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

      AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(this.getClientRequest());
      JSONArray groups = new JSONArray();
      for (AggregatedAgeGroupDTO ageGroup : ageGroups)
      // Age groups
      {
        JSONObject group = new JSONObject();
        group.put("id", ageGroup.getId());
        group.put("startAge", ageGroup.getStartAge().intValue());
        group.put("endAge", ageGroup.getEndAge().intValue());

        groups.put(group);
      }

      req.setAttribute("ageGroups", groups.toString());

      // Visible attributes
      String[] visibleAttributes = AggregatedCaseDTO.getVisibleAttributeNames(this.getClientRequest());

      ClassQueryDTO classQuery = this.getClientRequest().getQuery(AggregatedCaseDTO.CLASS);

      JSONArray visible = new JSONArray();
      for (String visibleAttribute : visibleAttributes)
      {
        AttributeDTO attributeDTO = classQuery.getAttributeDTO(visibleAttribute);

        if (attributeDTO.isReadable() && ! ( attributeDTO instanceof AttributeReferenceDTO ) && ! ( attributeDTO instanceof AttributeStructDTO ) && !attributeDTO.getName().equals(AggregatedCaseDTO.GEOENTITY) && !attributeDTO.getName().equals(AggregatedCaseDTO.ID))
        {
          JSONObject json = new JSONObject();
          json.put("attributeName", visibleAttribute);
          json.put("displayLabel", attributeDTO.getAttributeMdDTO().getDisplayLabel());
          json.put("type", AggregatedCaseDTO.CLASS);

          visible.put(json);
        }
      }

      req.setAttribute("visibleAttributes", visible.toString());

      JSONArray ordered = new JSONArray();

      // Referral
      JSONObject referral = new JSONObject();
      referral.put("type", TermDTO.CLASS);
      referral.put("label", MDSSProperties.getObject("Facility_referred"));
      referral.put("relType", CaseReferralDTO.CLASS);
      referral.put("relAttribute", CaseReferralDTO.AMOUNT);
      referral.put("options", new JSONArray());
      loadGrid(CaseReferralDTO.CLASS, ChildCaseViewDTO.CLASS, ChildCaseViewDTO.CASEREFERRALS, referral, ordered);

      // Diagnostic
      JSONObject diagnostic = new JSONObject();
      diagnostic.put("type", TermDTO.CLASS);
      diagnostic.put("label", MDSSProperties.getObject("Diagnostic_methods"));
      diagnostic.put("relType", CaseDiagnosticDTO.CLASS);
      diagnostic.put("relAttribute", CaseDiagnosticDTO.AMOUNT);
      diagnostic.put("relAttributeTwo", CaseDiagnosticDTO.AMOUNTPOSITIVE);
      diagnostic.put("options", new JSONArray());
      loadGrid(CaseDiagnosticDTO.CLASS, ChildCaseViewDTO.CLASS, ChildCaseViewDTO.CASEDIAGNOSTIC, diagnostic, ordered);

      // TreatmentMethod
      JSONObject treatmentMethod = new JSONObject();
      treatmentMethod.put("type", TermDTO.CLASS);
      treatmentMethod.put("label", MDSSProperties.getObject("Treatment_methods"));
      treatmentMethod.put("relType", CaseTreatmentMethodDTO.CLASS);
      treatmentMethod.put("relAttribute", CaseTreatmentMethodDTO.AMOUNT);
      treatmentMethod.put("options", new JSONArray());
      loadGrid(CaseTreatmentMethodDTO.CLASS, ChildCaseViewDTO.CLASS, ChildCaseViewDTO.CASETREATMENTMETHOD, treatmentMethod, ordered);

      // Treatment
      JSONObject treatment = new JSONObject();
      treatment.put("type", TermDTO.CLASS);
      treatment.put("label", MDSSProperties.getObject("Treatments"));
      treatment.put("relType", CaseTreatmentDTO.CLASS);
      treatment.put("relAttribute", CaseTreatmentDTO.AMOUNT);
      treatment.put("options", new JSONArray());
      loadGrid(CaseTreatmentDTO.CLASS, ChildCaseViewDTO.CLASS, ChildCaseViewDTO.CASETREATMENTS, treatment, ordered);

      // CaseTreatmentStock
      JSONObject caseTreatmentStock = new JSONObject();
      caseTreatmentStock.put("type", TermDTO.CLASS);
      caseTreatmentStock.put("label", MDSSProperties.getObject("Treatment_out_of_Stock"));
      caseTreatmentStock.put("relType", CaseTreatmentStockDTO.CLASS);
      caseTreatmentStock.put("relAttribute", CaseTreatmentStockDTO.OUTOFSTOCK);
      caseTreatmentStock.put("options", new JSONArray());
      loadGrid(CaseTreatmentStockDTO.CLASS, ChildCaseViewDTO.CLASS, ChildCaseViewDTO.CASESTOCKS, caseTreatmentStock, ordered);

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_AGGREGATED_CASES).forward(req, resp);
    }
    catch (Throwable t)
    {
      throw new ApplicationException(t);
    }
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
        throw new ApplicationException(e);
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
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
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
      patients.put("label", MDSSProperties.getObject("Facility_referred"));
      patients.put("relType", IPTPatientsDTO.CLASS);
      patients.put("relAttribute", IPTPatientsDTO.AMOUNT);
      patients.put("options", getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYPATIENTS));
      ordered.put("patients", patients);

      // Doses
      JSONObject doses = new JSONObject();
      doses.put("type", TermDTO.CLASS);
      doses.put("label", MDSSProperties.getObject("Diagnostic_methods"));
      doses.put("relType", IPTDoseDTO.CLASS);
      doses.put("relAttribute", IPTDoseDTO.AMOUNT);
      doses.put("options", getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYDOSE));
      ordered.put("doses", doses);

      // Visits
      JSONObject visits = new JSONObject();
      visits.put("type", TermDTO.CLASS);
      visits.put("label", MDSSProperties.getObject("Treatment_methods"));
      visits.put("relType", IPTANCVisitDTO.CLASS);
      visits.put("relAttribute", IPTANCVisitDTO.AMOUNT);
      visits.put("options", getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYVISITS));
      ordered.put("visits", visits);

      // Treatment
      JSONObject treatment = new JSONObject();
      treatment.put("type", TermDTO.CLASS);
      treatment.put("label", MDSSProperties.getObject("Treatments"));
      treatment.put("relType", IPTTreatmentDTO.CLASS);
      treatment.put("relAttribute", IPTTreatmentDTO.AMOUNT);
      treatment.put("options", getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYTREATMENTS));
      ordered.put("treatments", treatment);

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_AGGREGATED_IPT).forward(req, resp);

    }
    catch (Throwable t)
    {
      throw new ApplicationException(t);
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
      throw new ApplicationException(t);
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
      throw new ApplicationException(t);
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
      throw new ApplicationException(t);
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
      throw new ApplicationException(t);
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
      throw new ApplicationException(t);
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
      throw new ApplicationException(t);
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
      throw new ApplicationException(t);
    }
  }

  /**
   * Creates the screen to query for Entomology (mosquitos).
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
      symptoms.put("label", MDSSProperties.getObject("Malaria_Symptom"));
      symptoms.put("relType", IndividualCaseSymptomDTO.CLASS);
      symptoms.put("relAttribute", IndividualCaseSymptomDTO.HASSYMPTOM);
      symptoms.put("options", new JSONArray());
      
      TermDTO[] allSymptoms = TermDTO.getRootChildren(request, IndividualInstanceDTO.CLASS, IndividualInstanceDTO.SYMPTOM, false);
      
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
      throw new ApplicationException(t);
    }
  }

  @Override
  public void queryEfficacyAssay() throws IOException, ServletException
  {
    try
    {
      loadQuerySpecifics(EfficacyAssayDTO.CLASS, QueryConstants.QueryType.QUERY_EFFICACY_ASSAY);

      ClientRequestIF request = this.getClientRequest();

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO aIPT = request.getQuery(IndividualIPTDTO.CLASS);
      String iptMap = Halp.getDropDownMaps(aIPT, request, ", ");
      req.setAttribute("iptMap", iptMap);

      req.getRequestDispatcher(QUERY_EFFICACY_ASSAY).forward(req, resp);

    }
    catch (Throwable t)
    {
      throw new ApplicationException(t);
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
      throw new ApplicationException(t);
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
      throw new ApplicationException(t);
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

      req.getRequestDispatcher(QUERY_IRS).forward(req, resp);

    }
    catch (Throwable t)
    {
      throw new ApplicationException(t);
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
  public void exportAggregatedCaseQueryToCSV(String queryXML, String config, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = AggregatedCaseDTO.exportQueryToCSV(this.getClientRequest(), queryXML, config, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportAggregatedCaseQueryToExcel(String queryXML, String config, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = AggregatedCaseDTO.exportQueryToExcel(this.getClientRequest(), queryXML, config, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeXLS(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportSurveyQueryToCSV(String queryXML, String config, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = SurveyPointDTO.exportQueryToCSV(this.getClientRequest(), queryXML, config, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportSurveyQueryToExcel(String queryXML, String config, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = SurveyPointDTO.exportQueryToExcel(this.getClientRequest(), queryXML, config, savedSearchId);

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

}
