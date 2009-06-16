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
import org.json.JSONObject;

import com.terraframe.mojo.ApplicationException;
import com.terraframe.mojo.business.BusinessDTO;
import com.terraframe.mojo.business.ComponentDTOFacade;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.transport.attributes.AttributeDTO;
import com.terraframe.mojo.transport.attributes.AttributeReferenceDTO;
import com.terraframe.mojo.transport.attributes.AttributeStructDTO;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;

import dss.vector.solutions.entomology.assay.AbstractAssayDTO;
import dss.vector.solutions.geo.GeoEntityTreeController;
import dss.vector.solutions.geo.generated.EarthDTO;
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

public class QueryController extends QueryControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID       = 1237863171352L;

  private static final String QUERY_ENTOMOLOGY       = "/WEB-INF/queryScreens/queryEntomology.jsp";

  private static final String QUERY_AGGREGATED_CASES = "/WEB-INF/queryScreens/queryAggregatedCases.jsp";

  private static final String QUERY_INDICATOR_SURVEY = "/WEB-INF/queryScreens/queryIndicatorSurveys.jsp";

  private static final String NEW_ENTOMOLOGY_QUERY              = "/WEB-INF/queryScreens/newEntomologyQuery.jsp";

  private static final String NEW_AGGREGATED_CASES_QUERY              = "/WEB-INF/queryScreens/newAggregatedCasesQuery.jsp";

  public QueryController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
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
      if(savedSearchIdValue == null || savedSearchIdValue.trim().length() == 0)
      {
        SavedSearchRequiredExceptionDTO ex = new SavedSearchRequiredExceptionDTO(
            request, req.getLocale());
        message = ex.getLocalizedMessage();
        return;
      }

      if(file == null)
      {
        message = localized.getString("File_Required");
        return;
      }

      // All checks passed. Save the file to the SavedSearch

      // Ensure that a saved search actually exists
      SavedSearchDTO search = SavedSearchDTO.lock(request, savedSearchIdValue);

      String templateId = search.getTemplateFile();

      if(templateId != null && !templateId.equals(""))
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
      e.printStackTrace(this.getResponse().getWriter());
//      message = localized.getString("File_Upload_Failure");
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
      SavedSearchViewQueryDTO query = AggregatedCasesSearchDTO.getAggregatedCasesQueries(this.getClientRequest());
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
      for(AggregatedAgeGroupDTO ageGroup : ageGroups)
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
      AggregatedCaseDTO caseDTO = new AggregatedCaseDTO(this.getClientRequest());

      JSONArray visible = new JSONArray();
      for(String visibleAttribute : visibleAttributes)
      {
        AttributeDTO attributeDTO = ComponentDTOFacade.getAttributeDTO(caseDTO, visibleAttribute);
        if(attributeDTO.isReadable()
            && !(attributeDTO instanceof AttributeReferenceDTO)
            && !(attributeDTO instanceof AttributeStructDTO)
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

      for(AbstractGridDTO grid : gridQuery.getResultSet())
      {
        JSONObject option = new JSONObject();
        option.put("optionName", grid.getOptionName());
        option.put("displayLabel", grid.getDisplayLabel());
        option.put("attributeName", AbstractGridDTO.OPTIONNAME);
        option.put("type", grid.getType());

        if(grid.getType().equals(TreatmentGridDTO.CLASS))
        {
          // CaseTreatmentStock (a relationship) gets a copy of all
          // attributes in Treatment.
          caseTreatmentStock.getJSONArray("options").put(option);
        }

        JSONObject gridType = orderedMap.get(grid.getType());
        gridType.getJSONArray("options").put(option);
      }

      /*
      List<String> keysList = new LinkedList<String>();
      Iterator<?> keys = diagnostic.keys();
      while(keys.hasNext())
      {
        keysList.add((String) keys.next());
      }
      JSONObject amountPositive = new JSONObject(diagnostic, keysList.toArray(new String[keysList.size()]));
      amountPositive.put("relAttribute", CaseDiagnosticDTO.AMOUNTPOSITIVE);
      ordered.put(2, amountPositive); // insert right after Diagnostic with AMOUNT attribute
      */

      req.setAttribute("orderedGrids", ordered.toString());

      req.getRequestDispatcher(QUERY_AGGREGATED_CASES).forward(req, resp);
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

      SavedSearchViewQueryDTO query = EntomologySearchDTO.getEntomologyQueries(this.getClientRequest());
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



  @Override
  public void exportQueryToCSV(String queryXML, String geoEntityType, String savedSearchId)
      throws IOException, ServletException
  {
    try
    {
      InputStream stream = AggregatedCaseDTO.exportQueryToCSV(this.getClientRequest(), queryXML, geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeCSV(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  @Override
  public void exportAggregatedCaseQueryToExcel(String queryXML, String geoEntityType,
      String savedSearchId) throws IOException, ServletException
  {
    try
    {
      InputStream stream = AggregatedCaseDTO.exportQueryToExcel(this.getClientRequest(), queryXML, geoEntityType, savedSearchId);

      SavedSearchDTO search = SavedSearchDTO.get(this.getClientRequest(), savedSearchId);

      FileDownloadUtil.writeXLS(resp, search.getQueryName(), stream);
    }
    catch (Throwable t)
    {
      resp.getWriter().write(t.getLocalizedMessage());
    }
  }

  public void newEntomologyQuery() throws IOException, ServletException
  {
    try
    {
      SavedSearchViewDTO savedSearch = new SavedSearchViewDTO(this.getClientRequest());
      req.setAttribute("savedSearch", savedSearch);

      req.getRequestDispatcher(NEW_ENTOMOLOGY_QUERY).forward(req, resp);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  public void newAggregatedCasesQuery() throws IOException, ServletException
  {
    try
    {
      SavedSearchViewDTO savedSearch = new SavedSearchViewDTO(this.getClientRequest());
      req.setAttribute("savedSearch", savedSearch);

      req.getRequestDispatcher(NEW_AGGREGATED_CASES_QUERY).forward(req, resp);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void saveEntomologyQuery(SavedSearchViewDTO savedQueryView) throws IOException, ServletException
  {
    /*
    try
    {
      SavedSearchDTO search = EntomologySearchDTO.saveSearch(this.getClientRequest(), savedQueryView);

      // add the thematic layer
      ThematicLayerDTO layer = search.getThematicLayer();

      SLDWriter sldWriter = SLDWriter.getSLDWriter(layer);
      sldWriter.write();

      // return a simple json object with the search id and search name. This
      // will
      // be used to update the available query list.
      JSONObject savedSearchJSON = new JSONObject();
      savedSearchJSON.put("savedSearchId", search.getId());
      savedSearchJSON.put("queryName", search.getQueryName());
      savedSearchJSON.put("thematicLayerId", layer.getId());

      resp.getWriter().write(savedSearchJSON.toString());
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    */
  }

  @Override
  public void saveAggregatedCasesQuery(SavedSearchViewDTO savedQueryView) throws IOException, ServletException
  {
    /*
    try
    {
      SavedSearchDTO search = AggregatedCasesSearchDTO.saveSearch(this.getClientRequest(), savedQueryView);

      // add the thematic layer
      ThematicLayerDTO layer = search.getThematicLayer();

      SLDWriter sldWriter = SLDWriter.getSLDWriter(layer);
      sldWriter.write();

      // return a simple json object with the search id and search name. This
      // will
      // be used to update the available query list.
      JSONObject savedSearchJSON = new JSONObject();
      savedSearchJSON.put("savedSearchId", search.getId());
      savedSearchJSON.put("queryName", search.getQueryName());
      savedSearchJSON.put("thematicLayerId", layer.getId());

      resp.getWriter().write(savedSearchJSON.toString());
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    */
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

  public void newIndicatorSurvey() throws IOException, ServletException
  {
    try
    {
      SavedSearchViewDTO savedSearch = new SavedSearchViewDTO(this.getClientRequest());
      req.setAttribute("savedSearch", savedSearch);

      req.getRequestDispatcher(NEW_AGGREGATED_CASES_QUERY).forward(req, resp);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }


  /**
   * Creates the sceen to query for IndicatorSurvey.
   */
  public void queryIndicatorSurvey() throws IOException, ServletException
  {
    try
    {
      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      // Available queries
      SavedSearchViewQueryDTO query = AggregatedCasesSearchDTO.getAggregatedCasesQueries(this.getClientRequest());
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
      for(AggregatedAgeGroupDTO ageGroup : ageGroups)
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
      JSONArray visible = new JSONArray();
      for(String visibleAttribute : visibleAttributes)
      {
        visible.put(visibleAttribute);
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
      //diagnostic.put("relAttribute2", CaseDiagnosticDTO.AMOUNTPOSITIVE);
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

      AbstractGridQueryDTO gridQuery = AbstractGridDTO.getAllInstances(this.getClientRequest(), null, null, null, null);

      for(AbstractGridDTO grid : gridQuery.getResultSet())
      {
        JSONObject option = new JSONObject();
        option.put("optionName", grid.getOptionName());
        option.put("display", grid.getDisplayLabel());
        option.put("attributeName", AbstractGridDTO.OPTIONNAME);
        option.put("type", grid.getType());

        if(grid.getType().equals(TreatmentGridDTO.CLASS))
        {
          // CaseTreatmentStock (a relationship) gets a copy of all
          // attributes in Treatment. Yes, this is hacky.
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
  public void saveIndicatorSurveyQuery(SavedSearchViewDTO savedQueryView) throws IOException, ServletException
  {

  }
}
