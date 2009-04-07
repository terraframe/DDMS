package dss.vector.solutions.geo;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.ApplicationException;

import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;

public class GeoEntityTreeController extends GeoEntityTreeControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236364846909L;
  
  private static final String TREE_JSP = "/WEB-INF/geoEntityTree.jsp";
  
  private static final String TREE_COMPONENT_JSP = "/WEB-INF/geoEntityTreeComponent.jsp";

  private static final String CONFIRM_PARENT_CHANGE_JSP = "/WEB-INF/confirmParentChange.jsp";
  
  private static final String SELECT_SEARCH_COMPONENT_JSP = "/WEB-INF/selectSearchComponent.jsp";
  
  public static final String ROOT_GEO_ENTITY_ID = "rootGeoEntityId";
  
  public GeoEntityTreeController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  @Override
  public void displaySelectSearch(String rootGeoEntityId) throws IOException, ServletException
  {
    req.setAttribute(ROOT_GEO_ENTITY_ID, rootGeoEntityId);
    
    GeoHierarchyViewDTO[] views = GeoHierarchyDTO.getPoliticalGeoHierarchies(this.getClientRequest(), rootGeoEntityId);
    req.setAttribute("views", views);
    
    req.getRequestDispatcher(SELECT_SEARCH_COMPONENT_JSP).forward(req, resp);
  }
  
  @Override
  public void displayTree(String rootGeoEntityId) throws IOException, ServletException
  {
    if(rootGeoEntityId == null || rootGeoEntityId.trim().length() == 0)
    {
      EarthDTO earth = EarthDTO.getEarthInstance(this.getClientRequest());
      rootGeoEntityId = earth.getId();
    }
    
    req.setAttribute(ROOT_GEO_ENTITY_ID, rootGeoEntityId);
    
    if(this.isAsynchronous())
    {
      req.getRequestDispatcher(TREE_COMPONENT_JSP).forward(req, resp);
    }
    else
    {
      req.getRequestDispatcher(TREE_JSP).forward(req, resp);
    }
  }
  
}
