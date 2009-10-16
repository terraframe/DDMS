package dss.vector.solutions.query;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
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
import org.json.JSONObject;

import com.terraframe.mojo.ApplicationException;
import com.terraframe.mojo.business.BusinessDTO;
import com.terraframe.mojo.business.ClassQueryDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.system.metadata.MdAttributeDTO;
import com.terraframe.mojo.transport.attributes.AttributeDTO;
import com.terraframe.mojo.transport.attributes.AttributeReferenceDTO;
import com.terraframe.mojo.transport.attributes.AttributeStructDTO;
import com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;

import dss.vector.solutions.entomology.MosquitoDTO;
import dss.vector.solutions.entomology.SexMasterDTO;
import dss.vector.solutions.entomology.assay.AbstractAssayDTO;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.KnockDownAssayDTO;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO;
import dss.vector.solutions.general.InsecticideDTO;
import dss.vector.solutions.geo.GeoEntityTreeController;
import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.intervention.BloodslideResponseDTO;
import dss.vector.solutions.intervention.BloodslideResponseMasterDTO;
import dss.vector.solutions.intervention.FeverResponseDTO;
import dss.vector.solutions.intervention.FeverTreatmentDTO;
import dss.vector.solutions.intervention.HumanSexDTO;
import dss.vector.solutions.intervention.ResponseMasterDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTDTO;
import dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO;
import dss.vector.solutions.intervention.monitor.HouseholdDTO;
import dss.vector.solutions.intervention.monitor.HouseholdNetDTO;
import dss.vector.solutions.intervention.monitor.IPTANCVisitDTO;
import dss.vector.solutions.intervention.monitor.IPTDoseDTO;
import dss.vector.solutions.intervention.monitor.IPTPatientsDTO;
import dss.vector.solutions.intervention.monitor.IPTTreatmentDTO;
import dss.vector.solutions.intervention.monitor.NetDTO;
import dss.vector.solutions.intervention.monitor.PersonDTO;
import dss.vector.solutions.intervention.monitor.PersonViewDTO;
import dss.vector.solutions.intervention.monitor.RoofViewDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.intervention.monitor.WallViewDTO;
import dss.vector.solutions.intervention.monitor.WindowMasterDTO;
import dss.vector.solutions.intervention.monitor.WindowTypeDTO;
import dss.vector.solutions.irs.AbstractSprayDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.surveillance.AbstractGridDTO;
import dss.vector.solutions.surveillance.AbstractGridQueryDTO;
import dss.vector.solutions.surveillance.AggregatedAgeGroupDTO;
import dss.vector.solutions.surveillance.AggregatedCaseDTO;
import dss.vector.solutions.surveillance.CaseDiagnosticDTO;
import dss.vector.solutions.surveillance.CaseReferralDTO;
import dss.vector.solutions.surveillance.CaseTreatmentDTO;
import dss.vector.solutions.surveillance.CaseTreatmentMethodDTO;
import dss.vector.solutions.surveillance.CaseTreatmentStockDTO;
import dss.vector.solutions.surveillance.DiagnosticGridDTO;
import dss.vector.solutions.surveillance.ReferralGridDTO;
import dss.vector.solutions.surveillance.TreatmentGridDTO;
import dss.vector.solutions.surveillance.TreatmentMethodGridDTO;
import dss.vector.solutions.util.FileDownloadUtil;
import dss.vector.solutions.util.Halp;

public class QueryController extends QueryControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID       = 1237863171352L;

  private static final String QUERY_ENTOMOLOGY       = "/WEB-INF/queryScreens/queryEntomology.jsp";

  private static final String QUERY_RESISTANCE      = "/WEB-INF/queryScreens/queryResistance.jsp";

  private static final String QUERY_IRS              = "/WEB-INF/queryScreens/queryIRS.jsp";

  private static final String QUERY_AGGREGATED_CASES = "/WEB-INF/queryScreens/queryAggregatedCases.jsp";
  
  private static final String QUERY_AGGREGATED_IPT   = "/WEB-INF/queryScreens/queryAggregatedIPT.jsp";

  private static final String QUERY_SURVEY           = "/WEB-INF/queryScreens/querySurvey.jsp";

  private static final String NEW_QUERY              = "/WEB-INF/queryScreens/newQuery.jsp";

  public QueryController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  private JSONArray createBooleanItems(AttributeBooleanMdDTO mdDTO)
  {
    JSONArray items = new JSONArray();

    try
    {
      JSONObject pos = new JSONObject();
      pos.put("displayLabel", mdDTO.getPositiveDisplayLabel());
      pos.put("value", true);

      JSONObject neg = new JSONObject();
      neg.put("displayLabel", mdDTO.getNegativeDisplayLabel());
      neg.put("value", false);

      items.put(pos);
      items.put(neg);

      return items;
    }
    catch (Throwable t)
    {
      throw new ApplicationException(t);
    }
  }

  @Override
  public void querySurvey() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(request,
          QueryConstants.QUERY_INDICATOR_SURVEY);
      JSONArray queries = new JSONArray();
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }

      req.setAttribute("queryList", queries.toString());

      // Map of menu items. Key/Value where key is the attribute name
      // on Person and value is an object with display label and ids.
      JSONObject householdMenuItems = new JSONObject();

      ClassQueryDTO household = request.getQuery(HouseholdDTO.CLASS);

      // 5. House type (urban/rural)
      AttributeBooleanMdDTO urbanMd = (AttributeBooleanMdDTO) household.getAttributeDTO(HouseholdDTO.URBAN).getAttributeMdDTO();
      householdMenuItems.put(HouseholdDTO.URBAN, createBooleanItems(urbanMd));

      // 7. Walls
      JSONArray items = new JSONArray();
      for(WallViewDTO wall : Arrays.asList(WallViewDTO.getAll(request)))
      {
        JSONObject item = new JSONObject();
        item.put("displayLabel", wall.getDisplayLabel());
        item.put("isAbstract" , wall.getHasChildren());
        item.put("value", wall.getWallId());

        items.put(item);
      }
      householdMenuItems.put(HouseholdDTO.WALL, items);

      // 8. Roof
      items = new JSONArray();
      for(RoofViewDTO roof : Arrays.asList(RoofViewDTO.getAll(request)))
      {
        JSONObject item = new JSONObject();
        item.put("displayLabel", roof.getDisplayLabel());
        item.put("isAbstract" , roof.getHasChildren());
        item.put("value", roof.getRoofId());

        items.put(item);
      }
      householdMenuItems.put(HouseholdDTO.ROOF, items);

      // 9. Windows
      items = new JSONArray();
      for(WindowMasterDTO window : WindowTypeDTO.allItems(request))
      {
        JSONObject item = new JSONObject();
        item.put("displayLabel", window.getDisplayLabel());
        item.put("value", window.getId());

        items.put(item);
      }
      householdMenuItems.put(HouseholdDTO.WINDOWTYPE, items);

      req.setAttribute("householdMenuItems", householdMenuItems.toString());

      // All available net options (not abstract)
      JSONArray nets = new JSONArray();
      for(NetDTO netDTO : NetDTO.getAllLeafs(request))
      {
        JSONObject net = new JSONObject();
        net.put("entityAlias", HouseholdNetDTO.CLASS+"_"+netDTO.getNetName());
        net.put("key", HouseholdNetDTO.AMOUNT+"_"+netDTO.getNetName());
        net.put("displayLabel", netDTO.getDisplayLabel().getDefaultLocale());
        net.put("attributeName", HouseholdNetDTO.AMOUNT);
        net.put("type", HouseholdNetDTO.CLASS);

        nets.put(net);
      }

      req.setAttribute("nets", nets.toString());

      // Map of menu items. Key/Value where key is the attribute name
      // on Person and value is an object with display label and ids.
      JSONObject personMenuItems = new JSONObject();
      PersonViewDTO person = new PersonViewDTO(request);

      // 17. Sex
      items = new JSONArray();
      for (SexMasterDTO sex : HumanSexDTO.allItems(request))
      {
        JSONObject item = new JSONObject();
        item.put("displayLabel", sex.getDisplayLabel());
        item.put("value", sex.getId());

        items.put(item);
      }
      personMenuItems.put(PersonDTO.SEX, items);

      // 18. Pregnant
      personMenuItems.put(PersonDTO.PREGNANT, createBooleanItems(person.getPregnantMd()));

      // 19. Slept Under Net
      personMenuItems.put(PersonDTO.SLEPTUNDERNET, createBooleanItems(person.getSleptUnderNetMd()));

      // 20. Hemoglobin measured
      personMenuItems.put(PersonDTO.HAEMOGLOBINMEASURED, createBooleanItems(person.getSleptUnderNetMd()));

      // 21. Anemia Treatment, 23. RDT Treatment, 31. Malaria Treatment
      items = new JSONArray();
      for (TreatmentGridDTO drug : Arrays.asList(TreatmentGridDTO.getAll(request)))
      {
        JSONObject item = new JSONObject();
        item.put("displayLabel", drug.getDisplayLabel());
        item.put("value", drug.getId());

        items.put(item);
      }
      personMenuItems.put(PersonDTO.ANAEMIATREATMENT, items);
      personMenuItems.put(PersonDTO.RDTTREATMENT, items);
      personMenuItems.put(PersonDTO.MALARIATREATMENT, items);

      // 22. Iron given
      personMenuItems.put(PersonDTO.IRON, createBooleanItems(person.getIronMd()));

      // 24. RDT Result
      //FIXME: MO UPGRADE
//      items = new JSONArray();
//      JSONArray positives = new JSONArray();
//      List<RDTResultMasterDTO> results = RDTResultDTO.allItems(request);
//      for (RDTResultMasterDTO result : results)
//      {
//        JSONObject item = new JSONObject();
//        item.put("displayLabel", result.getDisplayLabel());
//        item.put("value", result.getId());
//
//        if(!result.getEnumName().equals(RDTResultDTO.NOT_VALID.getName()) &&
//            !result.getEnumName().equals(RDTResultDTO.NEGATIVE.getName()))
//        {
//          positives.put(result.getId());
//        }
//
//        items.put(item);
//      }
//      personMenuItems.put(PersonDTO.RDTRESULT, items);
//      req.setAttribute("positives", positives.toString());

      // 27. Bloodslide
      items = new JSONArray();
      for (BloodslideResponseMasterDTO response : BloodslideResponseDTO
          .allItems(request))
      {
        JSONObject item = new JSONObject();
        item.put("displayLabel", response.getDisplayLabel());
        item.put("value", response.getId());

        items.put(item);
      }
      personMenuItems.put(PersonDTO.BLOODSLIDE, items);

      // 29. Fever Treatment
      items = new JSONArray();
      for (FeverTreatmentDTO treatment : Arrays.asList(FeverTreatmentDTO.getAllActive(request)))
      {
        JSONObject item = new JSONObject();
        item.put("displayLabel", treatment.getDisplayLabel());
        item.put("value", treatment.getId());

        items.put(item);
      }
      personMenuItems.put(PersonDTO.FEVERTREATMENT, items);

      // 28. Fever, 30. Malaria 32. Payment
      items = new JSONArray();
      for (ResponseMasterDTO response : FeverResponseDTO.allItems(request))
      {
        JSONObject item = new JSONObject();
        item.put("displayLabel", response.getDisplayLabel());
        item.put("value", response.getId());

        items.put(item);
      }
      personMenuItems.put(PersonDTO.FEVER, items);
      personMenuItems.put(PersonDTO.MALARIA, items);
      personMenuItems.put(PersonDTO.PAYMENT, items);

      req.setAttribute("personMenuItems", personMenuItems.toString());

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
        SavedSearchRequiredExceptionDTO ex = new SavedSearchRequiredExceptionDTO(request, req
            .getLocale());
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

        if (attributeDTO.isReadable() && ! ( attributeDTO instanceof AttributeReferenceDTO )
            && ! ( attributeDTO instanceof AttributeStructDTO )
            && !attributeDTO.getName().equals(AggregatedCaseDTO.GEOENTITY)
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

      Map<String, JSONObject> orderedMap = new HashMap<String, JSONObject>();

      // Referral
      JSONObject referral = new JSONObject();
      referral.put("type", ReferralGridDTO.CLASS);
      referral.put("label", localized.getObject("Facility_referred"));
      referral.put("relType", CaseReferralDTO.CLASS);
      referral.put("relAttribute", CaseReferralDTO.AMOUNT);
      referral.put("options", new JSONArray());
      ordered.put(referral);
      orderedMap.put(ReferralGridDTO.CLASS, referral);

      // Diagnostic
      JSONObject diagnostic = new JSONObject();
      diagnostic.put("type", DiagnosticGridDTO.CLASS);
      diagnostic.put("label", localized.getObject("Diagnostic_methods"));
      diagnostic.put("relType", CaseDiagnosticDTO.CLASS);
      diagnostic.put("relAttribute", CaseDiagnosticDTO.AMOUNT);
      diagnostic.put("relAttributeTwo", CaseDiagnosticDTO.AMOUNTPOSITIVE);
      diagnostic.put("options", new JSONArray());
      ordered.put(diagnostic);
      orderedMap.put(DiagnosticGridDTO.CLASS, diagnostic);

      // TreatmentMethod
      JSONObject treatmentMethod = new JSONObject();
      treatmentMethod.put("type", TreatmentMethodGridDTO.CLASS);
      treatmentMethod.put("label", localized.getObject("Treatment_methods"));
      treatmentMethod.put("relType", CaseTreatmentMethodDTO.CLASS);
      treatmentMethod.put("relAttribute", CaseTreatmentMethodDTO.AMOUNT);
      treatmentMethod.put("options", new JSONArray());
      ordered.put(treatmentMethod);
      orderedMap.put(TreatmentMethodGridDTO.CLASS, treatmentMethod);

      // Treatment
      JSONObject treatment = new JSONObject();
      treatment.put("type", TreatmentGridDTO.CLASS);
      treatment.put("label", localized.getObject("Treatments"));
      treatment.put("relType", CaseTreatmentDTO.CLASS);
      treatment.put("relAttribute", CaseTreatmentDTO.AMOUNT);
      treatment.put("options", new JSONArray());
      ordered.put(treatment);
      orderedMap.put(TreatmentGridDTO.CLASS, treatment);

      // CaseTreatmentStock
      JSONObject caseTreatmentStock = new JSONObject();
      caseTreatmentStock.put("type", CaseTreatmentStockDTO.CLASS);
      caseTreatmentStock.put("label", localized.getObject("Treatment_out_of_Stock"));
      caseTreatmentStock.put("relType", CaseTreatmentStockDTO.CLASS);
      caseTreatmentStock.put("relAttribute", CaseTreatmentStockDTO.OUTOFSTOCK);
      caseTreatmentStock.put("options", new JSONArray());
      ordered.put(caseTreatmentStock);
      orderedMap.put(CaseTreatmentStockDTO.CLASS, caseTreatmentStock);

      AbstractGridQueryDTO gridQuery = AggregatedCaseDTO.getGridInstances(this.getClientRequest());

      for (AbstractGridDTO grid : gridQuery.getResultSet())
      {
        JSONObject option = new JSONObject();
        option.put("optionName", grid.getOptionName());
        option.put("displayLabel", grid.getDisplayLabel());
        option.put("attributeName", AbstractGridDTO.OPTIONNAME);
        option.put("type", grid.getType());

        if (grid.getType().equals(TreatmentGridDTO.CLASS))
        {
          // CaseTreatmentStock (a relationship) gets a copy of all
          // attributes in Treatment.
          caseTreatmentStock.getJSONArray("options").put(option);
        }

        JSONObject gridType = orderedMap.get(grid.getType());
        gridType.getJSONArray("options").put(option);
      }

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_AGGREGATED_CASES).forward(req, resp);
    }
    catch (Throwable t)
    {
      throw new ApplicationException(t);
    }
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

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(),
          QueryConstants.QUERY_AGGREGATED_IPT);
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


      //IPTANCVisit[] getIPTANCVisits()
      //IPTDose[] getIPTDoses()
      //IPTPatients[] getIPTPatients()
      //IPTTreatment[] getIPTTreatments()
      AggregatedIPTViewDTO av = new AggregatedIPTViewDTO(request);

      
      // Patients
      JSONObject patients = new JSONObject();
      patients.put("type", TermDTO.CLASS);
      patients.put("label", localized.getObject("Facility_referred"));
      patients.put("relType", IPTPatientsDTO.CLASS);
      patients.put("relAttribute", IPTPatientsDTO.AMOUNT);
      patients.put("options", new JSONArray());
      ordered.put("patients",patients);
      //orderedMap.put("patients", patients);

      // Doses
      JSONObject doses = new JSONObject();
      doses.put("type", TermDTO.CLASS);
      doses.put("label", localized.getObject("Diagnostic_methods"));
      doses.put("relType", IPTDoseDTO.CLASS);
      doses.put("relAttribute", IPTDoseDTO.AMOUNT);
      doses.put("options", new JSONArray());
      for (TermDTO term : TermDTO.getAllTermsByAttribute(request,MdAttributeDTO.get(request, av.getDisplayDoseMd().getId())))
      {
        JSONObject option = new JSONObject();
        option.put("id", term.getId());
        option.put("displayLabel", term.getDisplayLabel());
        option.put("MOID", term.getTermId());
        option.put("optionName", term.getTermName());
        option.put("type", TermDTO.CLASS);
        doses.getJSONArray("options").put(option);
      }
      
      ordered.put("doses",doses);
      //orderedMap.put("doses", doses);

      //Visits
      JSONObject visits = new JSONObject();
      visits.put("type", TermDTO.CLASS);
      visits.put("label", localized.getObject("Treatment_methods"));
      visits.put("relType", IPTANCVisitDTO.CLASS);
      visits.put("relAttribute", IPTANCVisitDTO.AMOUNT);
      visits.put("options", new JSONArray());
      ordered.put("visits", visits);
      //orderedMap.put("visits", visits);

      // Treatment
      JSONObject treatment = new JSONObject();
      treatment.put("type", TermDTO.CLASS);
      treatment.put("label", localized.getObject("Treatments"));
      treatment.put("relType", IPTTreatmentDTO.CLASS);
      treatment.put("relAttribute", IPTTreatmentDTO.AMOUNT);
      treatment.put("options", new JSONArray());
      ordered.put("treatments",treatment);
      //orderedMap.put("treatments", treatment);

      req.setAttribute("orderedGrids", ordered.toString());
      

      req.getRequestDispatcher(QUERY_AGGREGATED_IPT).forward(req, resp);

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

      String json = AbstractAssayDTO.getAssayTree(this.getClientRequest());

      req.setAttribute("assayTree", json);

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(),
          QueryConstants.QUERY_ENTOMOLOGY);
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

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(request,
          QueryConstants.QUERY_RESISTANCE);
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

      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(),
          QueryConstants.QUERY_IRS);
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

      //Load label map for InsecticdeBrand
     // ClassQueryDTO insecticideBrand = request.getQuery(InsecticideBrandViewDTO.CLASS);
      //String insecticideMap = Halp.getDropDownMaps(insecticideBrand, request, ", ");

      //InsecticideBrandViewDTO brandView = new InsecticideBrandViewDTO(request);
      //String insecticideMap = Halp.getDropDownMaps(brandView, request);
      //req.setAttribute("insecticideBrandMap", insecticideMap);


      req.getRequestDispatcher(QUERY_IRS).forward(req, resp);

    }
    catch (Throwable t)
    {
      throw new ApplicationException(t);
    }
  }

  public void exportAggregatedCaseQueryToCSV(String queryXML, String config, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = AggregatedCaseDTO.exportQueryToCSV(this.getClientRequest(), queryXML, config,
          savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportAggregatedCaseQueryToExcel(String queryXML, String config, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = AggregatedCaseDTO.exportQueryToExcel(this.getClientRequest(), queryXML,
          config, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeXLS(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportAggregatedIPTQueryToExcel(String queryXML, String config, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = AggregatedCaseDTO.exportQueryToExcel(this.getClientRequest(), queryXML,
          config, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeXLS(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  
  @Override
  public void exportSurveyQueryToCSV(String queryXML, String config, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = SurveyPointDTO.exportQueryToCSV(this.getClientRequest(), queryXML, config,
          savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportSurveyQueryToExcel(String queryXML, String config, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = SurveyPointDTO.exportQueryToExcel(this.getClientRequest(), queryXML,
          config, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeXLS(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportIRSQueryToCSV(String queryXML, String geoEntityType, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = AbstractSprayDTO.exportQueryToCSV(this.getClientRequest(), queryXML,
          geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportIRSQueryToExcel(String queryXML, String geoEntityType, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = AbstractSprayDTO.exportQueryToExcel(this.getClientRequest(), queryXML,
          geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeXLS(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportEntomologyQueryToCSV(String queryXML, String geoEntityType, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = MosquitoDTO.exportQueryToCSV(this.getClientRequest(), queryXML,
          geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportEntomologyQueryToExcel(String queryXML, String geoEntityType, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = MosquitoDTO.exportQueryToExcel(this.getClientRequest(), queryXML,
          geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeXLS(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportResistanceQueryToCSV(String queryXML, String geoEntityType, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = AdultDiscriminatingDoseAssayDTO.exportQueryToCSV(this.getClientRequest(), queryXML,
          geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }


  @Override
  public void exportResistanceQueryToExcel(String queryXML, String geoEntityType, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = AdultDiscriminatingDoseAssayDTO.exportQueryToExcel(this.getClientRequest(), queryXML,
          geoEntityType, savedSearchId);

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
