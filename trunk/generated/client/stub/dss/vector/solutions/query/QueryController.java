package dss.vector.solutions.query;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

import dss.vector.solutions.entomology.MosquitoDTO;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.KnockDownAssayDTO;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.geo.GeoEntityTreeController;
import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO;
import dss.vector.solutions.intervention.monitor.IPTANCVisitDTO;
import dss.vector.solutions.intervention.monitor.IPTDoseDTO;
import dss.vector.solutions.intervention.monitor.IPTPatientsDTO;
import dss.vector.solutions.intervention.monitor.IPTTreatmentDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO;
import dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO;
import dss.vector.solutions.intervention.monitor.ITNDataViewDTO;
import dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO;
import dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO;
import dss.vector.solutions.intervention.monitor.ITNNetDTO;
import dss.vector.solutions.intervention.monitor.ITNServiceDTO;
import dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO;
import dss.vector.solutions.intervention.monitor.IndividualIPTDTO;
import dss.vector.solutions.intervention.monitor.IndividualInstanceDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.irs.AbstractSprayDTO;
import dss.vector.solutions.ontology.TermDTO;
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

public class QueryController extends QueryControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID       = 1237863171352L;

  private static final String QUERY_ENTOMOLOGY       = "/WEB-INF/queryScreens/queryEntomology.jsp";

  private static final String QUERY_RESISTANCE       = "/WEB-INF/queryScreens/queryResistance.jsp";

  private static final String QUERY_IRS              = "/WEB-INF/queryScreens/queryIRS.jsp";

  private static final String QUERY_AGGREGATED_CASES = "/WEB-INF/queryScreens/queryAggregatedCases.jsp";

  private static final String QUERY_AGGREGATED_IPT   = "/WEB-INF/queryScreens/queryAggregatedIPT.jsp";

  private static final String QUERY_INDIVIDUAL_IPT   = "/WEB-INF/queryScreens/queryIndividualIPT.jsp";
  
  private static final String QUERY_INDIVIDUAL_CASES = "/WEB-INF/queryScreens/queryIndividualCases.jsp";

  private static final String QUERY_SURVEY           = "/WEB-INF/queryScreens/querySurvey.jsp";

  private static final String NEW_QUERY              = "/WEB-INF/queryScreens/newQuery.jsp";

  private static final String QUERY_EFFICACY_ASSAY   = "/WEB-INF/queryScreens/queryEfficacyAssay.jsp";
  
  private static final String QUERY_ITN_COMMUNITY_DISTRIBUTION  = "/WEB-INF/queryScreens/queryITNCommunityDistribution.jsp";
  
  private static final String QUERY_ITN_FACILITY_DISTRIBUTION  = "/WEB-INF/queryScreens/queryITNFacilityDistribution.jsp";
  
  private static final String QUERY_AGGREGATED_ITN = "/WEB-INF/queryScreens/queryAggregatedITN.jsp";

  public QueryController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void querySurvey() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(request, QueryConstants.QUERY_INDICATOR_SURVEY);
      JSONArray queries = new JSONArray();
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      // 24. RDT Result (special case). Use new PersonViewDTO object
      // as a template to get display values.
      JSONObject rdtResult = new JSONObject();
//      String display = new PersonViewDTO(this.getClientRequest()).getRDTResultMd().getDisplayLabel();
//      rdtResult.put("displayLabel", display);
//      rdtResult.put("attributeName", PersonViewDTO.RDTRESULT);

//      JSONArray items = new JSONArray();
//      rdtResult.put("items", items);
//      for (TermDTO term : TermDTO.getAllTermsForField(this.getClientRequest(), HouseholdViewDTO.CLASS, HouseholdViewDTO.DISPLAYNETS))
//      {
//        JSONObject item = new JSONObject();
//        item.put("displayLabel", term.getDisplayLabel());
//        item.put("value", term.getId());
//
//        items.put(item);
//      }
//
//      req.setAttribute("rdtResults", rdtResult.toString());
//
//      req.setAttribute("queryList", queries.toString());
//
//      JSONArray nets = new JSONArray();
//      for (TermDTO term : TermDTO.getAllTermsForField(this.getClientRequest(), HouseholdViewDTO.CLASS, HouseholdViewDTO.DISPLAYNETS))
//      {
//        JSONObject net = new JSONObject();
//        net.put("entityAlias", HouseholdNetDTO.CLASS + "_" + term.getId());
//        net.put("key", HouseholdNetDTO.AMOUNT + "_" + term.getId());
//        net.put("displayLabel", term.getDisplayLabel());
//        net.put("attributeName", HouseholdNetDTO.AMOUNT);
//        net.put("type", HouseholdNetDTO.CLASS);
//
//        nets.put(net);
//      }
//
//      req.setAttribute("nets", nets.toString());

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
    ResourceBundle localized = ResourceBundle.getBundle("MDSS");

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
        message = localized.getString("File_Required");
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

      message = localized.getString("File_Upload_Success");
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
      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      // Available queries
      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(), QueryConstants.QUERY_AGGREGATED_CASE);

      JSONArray queries = new JSONArray();
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      req.setAttribute("queryList", queries.toString());

      // Age groups
      AggregatedAgeGroupDTO[] ageGroups = AggregatedAgeGroupDTO.getAll(this.getClientRequest());
      JSONArray groups = new JSONArray();
      for (AggregatedAgeGroupDTO ageGroup : ageGroups)
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

        if (attributeDTO.isReadable() && ! ( attributeDTO instanceof AttributeReferenceDTO ) && ! ( attributeDTO instanceof AttributeStructDTO ) && !attributeDTO.getName().equals(AggregatedCaseDTO.GEOENTITY)
            && !attributeDTO.getName().equals(AggregatedCaseDTO.ID))
        {
          JSONObject json = new JSONObject();
          json.put("attributeName", visibleAttribute);
          json.put("displayLabel", attributeDTO.getAttributeMdDTO().getDisplayLabel());
          json.put("type", AggregatedCaseDTO.CLASS);

          visible.put(json);
        }
      }

      req.setAttribute("visibleAttributes", visible.toString());

      ResourceBundle localized = ResourceBundle.getBundle("MDSS");
      JSONArray ordered = new JSONArray();

      // Referral
      JSONObject referral = new JSONObject();
      referral.put("type", TermDTO.CLASS);
      referral.put("label", localized.getObject("Facility_referred"));
      referral.put("relType", CaseReferralDTO.CLASS);
      referral.put("relAttribute", CaseReferralDTO.AMOUNT);
      referral.put("options", new JSONArray());
      loadGrid(CaseReferralDTO.CLASS, ChildCaseViewDTO.CLASS, ChildCaseViewDTO.CASEREFERRALS, referral, ordered);

      // Diagnostic
      JSONObject diagnostic = new JSONObject();
      diagnostic.put("type", TermDTO.CLASS);
      diagnostic.put("label", localized.getObject("Diagnostic_methods"));
      diagnostic.put("relType", CaseDiagnosticDTO.CLASS);
      diagnostic.put("relAttribute", CaseDiagnosticDTO.AMOUNT);
      diagnostic.put("relAttributeTwo", CaseDiagnosticDTO.AMOUNTPOSITIVE);
      diagnostic.put("options", new JSONArray());
      loadGrid(CaseDiagnosticDTO.CLASS, ChildCaseViewDTO.CLASS, ChildCaseViewDTO.CASEDIAGNOSTIC, diagnostic, ordered);

      // TreatmentMethod
      JSONObject treatmentMethod = new JSONObject();
      treatmentMethod.put("type", TermDTO.CLASS);
      treatmentMethod.put("label", localized.getObject("Treatment_methods"));
      treatmentMethod.put("relType", CaseTreatmentMethodDTO.CLASS);
      treatmentMethod.put("relAttribute", CaseTreatmentMethodDTO.AMOUNT);
      treatmentMethod.put("options", new JSONArray());
      loadGrid(CaseTreatmentMethodDTO.CLASS, ChildCaseViewDTO.CLASS, ChildCaseViewDTO.CASETREATMENTMETHOD, treatmentMethod, ordered);

      // Treatment
      JSONObject treatment = new JSONObject();
      treatment.put("type", TermDTO.CLASS);
      treatment.put("label", localized.getObject("Treatments"));
      treatment.put("relType", CaseTreatmentDTO.CLASS);
      treatment.put("relAttribute", CaseTreatmentDTO.AMOUNT);
      treatment.put("options", new JSONArray());
      loadGrid(CaseTreatmentDTO.CLASS, ChildCaseViewDTO.CLASS, ChildCaseViewDTO.CASETREATMENTS, treatment, ordered);

      // CaseTreatmentStock
      JSONObject caseTreatmentStock = new JSONObject();
      caseTreatmentStock.put("type", TermDTO.CLASS);
      caseTreatmentStock.put("label", localized.getObject("Treatment_out_of_Stock"));
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
  
  private JSONArray getAllTermsForGrid(ClientRequestIF request ,String klass, String attribute) throws JSONException{
    JSONArray array =  new JSONArray();
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
      // The Earth is the root. FIXME use country's default root
      ClientRequestIF request = this.getClientRequest();

      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(), QueryConstants.QUERY_AGGREGATED_IPT);
      JSONArray queries = new JSONArray();
      // Available queries
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO aIPT = request.getQuery(AggregatedIPTDTO.CLASS);
      String iptMap = Halp.getDropDownMaps(aIPT, request, ", ");
      req.setAttribute("iptMap", iptMap);

      req.setAttribute("queryList", queries.toString());

      ResourceBundle localized = ResourceBundle.getBundle("MDSS");
      JSONObject ordered = new JSONObject();

      Map<String, JSONObject> orderedMap = new HashMap<String, JSONObject>();

      AggregatedIPTViewDTO av = new AggregatedIPTViewDTO(request);

      // Patients
      JSONObject patients = new JSONObject();
      patients.put("type", TermDTO.CLASS);
      patients.put("label", localized.getObject("Facility_referred"));
      patients.put("relType", IPTPatientsDTO.CLASS);
      patients.put("relAttribute", IPTPatientsDTO.AMOUNT);
      patients.put("options",getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYPATIENTS));
      ordered.put("patients", patients);

      // Doses
      JSONObject doses = new JSONObject();
      doses.put("type", TermDTO.CLASS);
      doses.put("label", localized.getObject("Diagnostic_methods"));
      doses.put("relType", IPTDoseDTO.CLASS);
      doses.put("relAttribute", IPTDoseDTO.AMOUNT);
      doses.put("options",getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYDOSE));
      ordered.put("doses", doses);

      // Visits
      JSONObject visits = new JSONObject();
      visits.put("type", TermDTO.CLASS);
      visits.put("label", localized.getObject("Treatment_methods"));
      visits.put("relType", IPTANCVisitDTO.CLASS);
      visits.put("relAttribute", IPTANCVisitDTO.AMOUNT);
      visits.put("options",getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYVISITS));
      ordered.put("visits", visits);

      // Treatment
      JSONObject treatment = new JSONObject();
      treatment.put("type", TermDTO.CLASS);
      treatment.put("label", localized.getObject("Treatments"));
      treatment.put("relType", IPTTreatmentDTO.CLASS);
      treatment.put("relAttribute", IPTTreatmentDTO.AMOUNT);
      treatment.put("options",getAllTermsForGrid(request, AggregatedIPTViewDTO.CLASS, AggregatedIPTViewDTO.DISPLAYTREATMENTS));
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
      // The Earth is the root. FIXME use country's default root
      ClientRequestIF request = this.getClientRequest();

      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(), QueryConstants.QUERY_AGGREGATED_ITN);
      JSONArray queries = new JSONArray();
      // Available queries
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      req.setAttribute("queryList", queries.toString());

      JSONObject ordered = new JSONObject();

      ITNDataViewDTO itn = new ITNDataViewDTO(request);

      // Nets
      JSONObject patients = new JSONObject();
      patients.put("type", TermDTO.CLASS);
      patients.put("label", itn.getDisplayNetsMd().getDisplayLabel());
      patients.put("relType", ITNNetDTO.CLASS);
      patients.put("relAttribute", ITNNetDTO.AMOUNT);
      patients.put("options",getAllTermsForGrid(request, ITNDataViewDTO.CLASS, ITNDataViewDTO.DISPLAYNETS));
      ordered.put("nets", patients);

      // Target Groups
      JSONObject doses = new JSONObject();
      doses.put("type", TermDTO.CLASS);
      doses.put("label", itn.getDisplayTargetGroupsMd().getDisplayLabel());
      doses.put("relType", ITNTargetGroupDTO.CLASS);
      doses.put("relAttribute", ITNTargetGroupDTO.AMOUNT);
      doses.put("options",getAllTermsForGrid(request, ITNDataViewDTO.CLASS, ITNDataViewDTO.DISPLAYTARGETGROUPS));
      ordered.put("targetGroups", doses);

      // Visits
      JSONObject visits = new JSONObject();
      visits.put("type", TermDTO.CLASS);
      visits.put("label", itn.getDisplayServicesMd().getDisplayLabel());
      visits.put("relType", ITNServiceDTO.CLASS);
      visits.put("relAttribute", ITNServiceDTO.AMOUNT);
      visits.put("options",getAllTermsForGrid(request, ITNDataViewDTO.CLASS, ITNDataViewDTO.DISPLAYSERVICES));
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
      // The Earth is the root. FIXME use country's default root
      ClientRequestIF request = this.getClientRequest();

      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(), QueryConstants.QUERY_ITN_COMMUNITY_DISTRIBUTION);
      JSONArray queries = new JSONArray();
      // Available queries
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      req.setAttribute("queryList", queries.toString());   

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
      patients.put("options",getAllTermsForGrid(request, ITNCommunityDistributionViewDTO.CLASS, ITNCommunityDistributionViewDTO.DISPLAYNETS));
      ordered.put("nets", patients);

      // Target Groups
      JSONObject doses = new JSONObject();
      doses.put("type", TermDTO.CLASS);
      doses.put("label", itnView.getDisplayTargetGroupsMd().getDisplayLabel());
      doses.put("relType", ITNCommunityTargetGroupDTO.CLASS);
      doses.put("relAttribute", ITNCommunityTargetGroupDTO.AMOUNT);
      doses.put("options",getAllTermsForGrid(request, ITNCommunityDistributionViewDTO.CLASS, ITNCommunityDistributionViewDTO.DISPLAYTARGETGROUPS));
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
      // The Earth is the root. FIXME use country's default root
      ClientRequestIF request = this.getClientRequest();

      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(), QueryConstants.QUERY_ITN_FACILITY_DISTRIBUTION);
      JSONArray queries = new JSONArray();
      // Available queries
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      req.setAttribute("queryList", queries.toString());
      
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
      doses.put("options",getAllTermsForGrid(request, ITNDistributionViewDTO.CLASS, ITNDistributionViewDTO.TARGETGROUPS));
      ordered.put("targetGroups", doses);

     

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_ITN_FACILITY_DISTRIBUTION).forward(req, resp);

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
  public void queryIndividualIPT() throws IOException, ServletException
  {
    try
    {
      // The Earth is the root. FIXME use country's default root
      ClientRequestIF request = this.getClientRequest();

      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(), QueryConstants.QUERY_INDIVIDUAL_IPT);
      JSONArray queries = new JSONArray();
      // Available queries
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO aIPT = request.getQuery(IndividualIPTDTO.CLASS);
      String iptMap = Halp.getDropDownMaps(aIPT, request, ", ");
      req.setAttribute("iptMap", iptMap);

      req.setAttribute("queryList", queries.toString());

      req.getRequestDispatcher(QUERY_INDIVIDUAL_IPT).forward(req, resp);

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
      // The Earth is the root. FIXME use country's default root
      ClientRequestIF request = this.getClientRequest();

      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(), QueryConstants.QUERY_INDIVIDUAL_CASES);
      JSONArray queries = new JSONArray();
      // Available queries
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      // Load label map 
      ClassQueryDTO iInstance = request.getQuery(IndividualInstanceDTO.CLASS);
      String instanceMap = Halp.getDropDownMaps(iInstance, request, ", ");
      req.setAttribute("instanceMaps", instanceMap);

      req.setAttribute("queryList", queries.toString());

      ResourceBundle localized = ResourceBundle.getBundle("MDSS");
      JSONObject ordered = new JSONObject();
      

      // Treatment
      JSONObject symptoms = new JSONObject();
      symptoms.put("type", TermDTO.CLASS);
      symptoms.put("label", localized.getObject("Malaria_Symptom"));
      symptoms.put("relType", IndividualCaseSymptomDTO.CLASS);
      symptoms.put("relAttribute", IndividualCaseSymptomDTO.HASSYMPTOM);
      symptoms.put("options", new JSONArray());
      IndividualInstanceDTO dto = new IndividualInstanceDTO(request);
      for (IndividualCaseSymptomDTO s : dto.getSymptoms())
      {
        TermDTO term = s.getChild();
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
      // The Earth is the root. FIXME use country's default root
      ClientRequestIF request = this.getClientRequest();

      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(), QueryConstants.QUERY_EFFICACY_ASSAY);
      JSONArray queries = new JSONArray();
      // Available queries
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      // Load label map for Adult Discriminating Dose Assay
      ClassQueryDTO aIPT = request.getQuery(IndividualIPTDTO.CLASS);
      String iptMap = Halp.getDropDownMaps(aIPT, request, ", ");
      req.setAttribute("iptMap", iptMap);

      req.setAttribute("queryList", queries.toString());

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
      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(), QueryConstants.QUERY_ENTOMOLOGY);
      JSONArray queries = new JSONArray();
      // Available queries
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      req.setAttribute("queryList", queries.toString());

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
      // The Earth is the root. FIXME use country's default root
      ClientRequestIF request = this.getClientRequest();

      EarthDTO earth = EarthDTO.getEarthInstance(request);
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(request, QueryConstants.QUERY_RESISTANCE);
      JSONArray queries = new JSONArray();
      // Available queries
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      req.setAttribute("queryList", queries.toString());

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
      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());
      ClientRequestIF request = this.getClientRequest();

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(), QueryConstants.QUERY_IRS);
      JSONArray queries = new JSONArray();
      // Available queries
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      req.setAttribute("queryList", queries.toString());

      // Load label map for spray data
      // ClassQueryDTO sprayData = request.getQuery(SprayDataDTO.CLASS);
      // String sprayDataMap = Halp.getDropDownMaps(sprayData, request, ", ");
      // req.setAttribute("sprayDataMap", sprayDataMap);

      // Load label map for InsecticdeBrand
      // ClassQueryDTO insecticideBrand =
      // request.getQuery(InsecticideBrandViewDTO.CLASS);
      // String insecticideMap = Halp.getDropDownMaps(insecticideBrand, request,
      // ", ");

      // InsecticideBrandViewDTO brandView = new
      // InsecticideBrandViewDTO(request);
      // String insecticideMap = Halp.getDropDownMaps(brandView, request);
      // req.setAttribute("insecticideBrandMap", insecticideMap);

      req.getRequestDispatcher(QUERY_IRS).forward(req, resp);

    }
    catch (Throwable t)
    {
      throw new ApplicationException(t);
    }
  }


//  @Override
  public void exportQueryToCSV(String className, String queryXML, String config, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = QueryBuilderDTO.exportQueryToCSV(this.getClientRequest(),className, queryXML, config, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

//  @Override
  public void exportQueryToExcel(String className, String queryXML, String config, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = QueryBuilderDTO.exportQueryToExcel(this.getClientRequest(),className, queryXML, config, savedSearchId);

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
  public void exportAggregatedIPTQueryToExcel(String queryXML, String config, String savedSearchId) throws IOException, ServletException
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
  public void exportIRSQueryToCSV(String queryXML, String geoEntityType, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = AbstractSprayDTO.exportQueryToCSV(this.getClientRequest(), queryXML, geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportIRSQueryToExcel(String queryXML, String geoEntityType, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = AbstractSprayDTO.exportQueryToExcel(this.getClientRequest(), queryXML, geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeXLS(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportEntomologyQueryToCSV(String queryXML, String geoEntityType, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = MosquitoDTO.exportQueryToCSV(this.getClientRequest(), queryXML, geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportEntomologyQueryToExcel(String queryXML, String geoEntityType, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = MosquitoDTO.exportQueryToExcel(this.getClientRequest(), queryXML, geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeXLS(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportResistanceQueryToCSV(String queryXML, String geoEntityType, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = AdultDiscriminatingDoseAssayDTO.exportQueryToCSV(this.getClientRequest(), queryXML, geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportResistanceQueryToExcel(String queryXML, String geoEntityType, String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = AdultDiscriminatingDoseAssayDTO.exportQueryToExcel(this.getClientRequest(), queryXML, geoEntityType, savedSearchId);

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
