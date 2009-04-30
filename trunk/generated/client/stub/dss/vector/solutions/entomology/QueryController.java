package dss.vector.solutions.entomology;

import java.io.IOException;

import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.terraframe.mojo.ApplicationException;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;

import dss.vector.solutions.entomology.assay.AbstractAssayDTO;
import dss.vector.solutions.geo.GeoEntityTreeController;
import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.query.EntomologySearchDTO;
import dss.vector.solutions.query.SavedSearchViewDTO;
import dss.vector.solutions.query.SavedSearchViewQueryDTO;

public class QueryController extends QueryControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID = 1237863171352L;

  private static final String QUERY_ENTOMOLOGY = "/WEB-INF/queryScreens/queryEntomology.jsp";

  private static final String NEW_QUERY        = "/WEB-INF/queryScreens/newQuery.jsp";

  public QueryController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

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
      for(SavedSearchViewDTO view : query.getResultSet())
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
    // handled in Javascript
//    try
//    {
//      SavedSearchDTO search = EntomologySearchDTO.saveSearch(this.getClientRequest(), savedQueryView);
//      search = null;
//    }
//    catch (ProblemExceptionDTO e)
//    {
//      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
//      resp.setStatus(500);
//      resp.getWriter().print(jsonE.getJSON());
//    }
//    catch (Throwable t)
//    {
//      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
//      resp.setStatus(500);
//      resp.getWriter().print(jsonE.getJSON());
//    }
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
