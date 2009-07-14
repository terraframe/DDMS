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

import dss.vector.solutions.entomology.MosquitoDTO;
import dss.vector.solutions.entomology.assay.AbstractAssayDTO;
import dss.vector.solutions.geo.GeoEntityTreeController;
import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.intervention.monitor.SurveyPointDTO;
import dss.vector.solutions.irs.AbstractSprayDTO;
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

  private static final String QUERY_IRS = "/WEB-INF/queryScreens/queryIRS.jsp";

  private static final String QUERY_AGGREGATED_CASES = "/WEB-INF/queryScreens/queryAggregatedCases.jsp";

  private static final String QUERY_SURVEY = "/WEB-INF/queryScreens/querySurvey.jsp";
  
  private static final String NEW_QUERY = "/WEB-INF/queryScreens/newQuery.jsp";

  public QueryController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  @Override
  public void querySurvey() throws IOException, ServletException
  {
    try
    {
      
      SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(this.getClientRequest(), QueryConstants.QUERY_INDICATOR_SURVEY);
      JSONArray queries = new JSONArray();
      for (SavedSearchViewDTO view : query.getResultSet())
      {
        JSONObject idAndName = new JSONObject();
        idAndName.put("id", view.getSavedQueryId());
        idAndName.put("name", view.getQueryName());

        queries.put(idAndName);
      }
      

      req.setAttribute("queryList", queries.toString());     
      
      SurveyPointDTO surveyPointDTO = new SurveyPointDTO(this.getClientRequest());
      
      req.getRequestDispatcher(QUERY_SURVEY).forward(req, resp); 
    }
    catch(Throwable t)
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
    catch(Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
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

  public void queryIRS() throws IOException, ServletException
  {
    try
    {
      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

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
  public void exportAggregatedCaseQueryToExcel(String queryXML, String config,
      String savedSearchId) throws IOException, ServletException
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
  public void exportSurveyQueryToCSV(String queryXML, String config, String savedSearchId)
  throws IOException, ServletException
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
  public void exportSurveyQueryToExcel(String queryXML, String config,
      String savedSearchId) throws IOException, ServletException
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
  public void exportIRSQueryToCSV(String queryXML, String geoEntityType, String savedSearchId)
      throws IOException, ServletException
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
  public void exportIRSQueryToExcel(String queryXML, String geoEntityType,
      String savedSearchId) throws IOException, ServletException
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
  public void exportEntomologyQueryToCSV(String queryXML, String geoEntityType, String savedSearchId)
      throws IOException, ServletException
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
  public void exportEntomologyQueryToExcel(String queryXML, String geoEntityType,
      String savedSearchId) throws IOException, ServletException
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
