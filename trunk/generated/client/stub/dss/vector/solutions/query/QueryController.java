package dss.vector.solutions.query;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.terraframe.mojo.ApplicationException;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;
import com.terraframe.mojo.web.json.JSONProblemExceptionDTO;

import dss.vector.solutions.entomology.assay.AbstractAssayDTO;
import dss.vector.solutions.geo.GeoEntityTreeController;
import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.sld.SLDWriter;
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

public class QueryController extends QueryControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID       = 1237863171352L;

  private static final String QUERY_ENTOMOLOGY       = "/WEB-INF/queryScreens/queryEntomology.jsp";

  private static final String QUERY_AGGREGATED_CASES = "/WEB-INF/queryScreens/queryAggregatedCases.jsp";

  private static final String NEW_ENTOMOLOGY_QUERY              = "/WEB-INF/queryScreens/newEntomologyQuery.jsp";

  private static final String NEW_AGGREGATED_CASES_QUERY              = "/WEB-INF/queryScreens/newAggregatedCasesQuery.jsp";

  public QueryController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
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

  /**
   * Creates the screen to query for Entomology (mosquitos).
   */
  @Override
  public void queryEntomology() throws IOException, ServletException
  {
    try
    {
      String json = AbstractAssayDTO.getAssayTree(this.getClientRequest());

      // The Earth is the root. FIXME use country's default root
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      req.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

      req.setAttribute("assayTree", json);

      SavedSearchViewQueryDTO query = EntomologySearchDTO.getEntomologyQueries(this.getClientRequest());
      JSONArray queries = new JSONArray();
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

//  @Override
//  public void newQuery() throws IOException, ServletException
//  {
//    try
//    {
//      SavedSearchViewDTO savedSearch = new SavedSearchViewDTO(this.getClientRequest());
//      req.setAttribute("savedSearch", savedSearch);
//
//      req.getRequestDispatcher(NEW_QUERY).forward(req, resp);
//    }
//    catch (Throwable t)
//    {
//      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
//      resp.setStatus(500);
//      resp.getWriter().print(jsonE.getJSON());
//    }
//  }

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
}
