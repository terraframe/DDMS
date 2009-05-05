package dss.vector.solutions.query;

import java.io.IOException;

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
import dss.vector.solutions.query.SavedSearchViewQueryDTO;
import dss.vector.solutions.sld.SLDWriter;
import dss.vector.solutions.surveillance.AggregatedAgeGroupDTO;
import dss.vector.solutions.surveillance.AggregatedCaseDTO;

public class QueryController extends QueryControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID       = 1237863171352L;

  private static final String QUERY_ENTOMOLOGY       = "/WEB-INF/queryScreens/queryEntomology.jsp";

  private static final String QUERY_AGGREGATED_CASES = "/WEB-INF/queryScreens/queryAggregatedCases.jsp";

  private static final String NEW_QUERY              = "/WEB-INF/queryScreens/newQuery.jsp";

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

  @Override
  public void newQuery() throws IOException, ServletException
  {
    try
    {
      SavedSearchViewDTO savedSearch = new SavedSearchViewDTO(this.getClientRequest());
      req.setAttribute("savedSearch", savedSearch);

      req.getRequestDispatcher(NEW_QUERY).forward(req, resp);
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void saveQuery(SavedSearchViewDTO savedQueryView) throws IOException, ServletException
  {
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
      savedSearchJSON.put("id", search.getId());
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
