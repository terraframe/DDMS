package dss.vector.solutions.geo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.web.json.JSONRunwayExceptionDTO;

import dss.vector.solutions.geo.generated.EarthDTO;

public class GeoEntityTreeController extends GeoEntityTreeControllerBase implements Reloadable
{
  private static final long   serialVersionUID                    = 1236364846909L;

  private static final String TREE_JSP                            = "/WEB-INF/geoEntityTree.jsp";

  private static final String TREE_COMPONENT_JSP                  = "/WEB-INF/geoEntityTreeComponent.jsp";

  private static final String SINGLE_SELECT_SEARCH_COMPONENT_JSP  = "/WEB-INF/singleSelectSearchComponent.jsp";

  private static final String MULTIPLE_SELECT_SEARCH_COMPONENT_JS = "/WEB-INF/multipleSelectSearchComponent.jsp";

  public static final String  ROOT_GEO_ENTITY_ID                  = "rootGeoEntityId";

  public GeoEntityTreeController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  @Override
  public void displaySingleSelectSearch(String rootGeoEntityId, Boolean[] flags, String[] extraUniversals) throws IOException, ServletException
  {
    try
    {
      GeoHierarchyViewDTO[] views = GeoHierarchyDTO.collectHierarchies(this.getClientRequest(), rootGeoEntityId, flags, extraUniversals);
      req.setAttribute("views", views);

      req.getRequestDispatcher(SINGLE_SELECT_SEARCH_COMPONENT_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO ex = new JSONRunwayExceptionDTO(t);
      this.resp.setStatus(500);
      this.resp.getWriter().write(ex.getJSON());
    }
  }
  
  @Override
  public void displayMultipleSelectSearch(String rootGeoEntityId, Boolean[] flags, String[] extraUniversals) throws IOException, ServletException
  {
    try
    {
      GeoHierarchyViewDTO[] views = GeoHierarchyDTO.collectHierarchies(this.getClientRequest(), rootGeoEntityId, flags, extraUniversals);
      req.setAttribute("views", views);

      req.getRequestDispatcher(MULTIPLE_SELECT_SEARCH_COMPONENT_JS).forward(req, resp);
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO ex = new JSONRunwayExceptionDTO(t);
      this.resp.setStatus(500);
      this.resp.getWriter().write(ex.getJSON());
    }
  }

  @Override
  public void displayTree(String rootGeoEntityId) throws IOException, ServletException
  {
    try
    {
      if (rootGeoEntityId == null || rootGeoEntityId.trim().length() == 0)
      {
        EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
        rootGeoEntityId = earth.getId();
      }

      if (this.isAsynchronous())
      {
        req.getRequestDispatcher(TREE_COMPONENT_JSP).forward(req, resp);
      }
      else
      {
        req.getRequestDispatcher(TREE_JSP).forward(req, resp);
      }
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO ex = new JSONRunwayExceptionDTO(t);
      this.resp.setStatus(500);
      this.resp.getWriter().write(ex.getJSON());
    }
  }

}
