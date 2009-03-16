package dss.vector.solutions.geo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.constants.MdBusinessInfo;

import dss.vector.solutions.geo.GeoEntityTypeControllerBase;
import dss.vector.solutions.geo.GeoHierarchyViewQueryDTO;

public class GeoEntityTypeController extends GeoEntityTypeControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236133816932L;

  private static final String NEW_DEFINITION_JSP = "/WEB-INF/newDefinition.jsp";
  private static final String VIEW_DEFINITION_JSP = "/WEB-INF/viewDefinition.jsp";
  private static final String VIEW_ALL_DEFINITIONS_JSP = "/WEB-INF/viewAllDefinitions.jsp";
  
  private static final String TREE_JSP = "/WEB-INF/geoHierarchyTree.jsp";
  
  private static final String TREE_COMPONENT_JSP = "/WEB-INF/geoHierarchyTreeComponent.jsp";

  public static final String ROOT_GEO_HIERARCHY_ID = "rootGeoHierarchyId";
  
  public GeoEntityTypeController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void newDefinition(String parentGeoHierarchyId) throws IOException, ServletException
  {
    GeoEntityDefinitionDTO def = new GeoEntityDefinitionDTO(this.getClientRequest());
    def.setParentGeoHierarchyId(parentGeoHierarchyId);
    
    GeoHierarchyViewQueryDTO query = GeoHierarchyDTO.getGeoEntityHierarchyViews(this.getClientRequest(), MdBusinessInfo.DISPLAY_LABEL, true, null, null);
    List<SpatialMasterDTO> types = SpatialTypesDTO.allItems(this.getClientRequest());
    
    req.setAttribute("availableParents", query.getResultSet());
    req.setAttribute("types", types);
    req.setAttribute("definition", def);
    
    req.getRequestDispatcher(NEW_DEFINITION_JSP).forward(req, resp);
  }
  
  @Override
  public void createDefinition(GeoEntityDefinitionDTO definition) throws IOException, ServletException
  {
    GeoHierarchyViewDTO view = GeoHierarchyDTO.defineGeoEntity(this.getClientRequest(), definition);
    
    // return the id to the calling Ajax process
    resp.getWriter().write(view.getGeoHierarchyId());
  }

  @Override
  public void cancelCreateDefinition() throws IOException, ServletException
  {
    // handled in Ajax calling process
  }
  
  @Override
  public void viewAllDefinitions() throws IOException, ServletException
  {
    GeoHierarchyViewQueryDTO query = GeoHierarchyDTO.getGeoEntityHierarchyViews(this.getClientRequest(), MdBusinessInfo.DISPLAY_LABEL, true, 20, 1);
    
    // set default page number/size
    query.setPageNumber(1);
    query.setPageSize(20);
    
    req.setAttribute("query", query);
    
    req.getRequestDispatcher(VIEW_ALL_DEFINITIONS_JSP).forward(req, resp);
  }
  
  @Override
  public void viewPageDefinitions(String sortAttribute, Boolean isAscending, Integer pageSize,
      Integer pageNumber) throws IOException, ServletException
  {
    GeoHierarchyViewQueryDTO query = GeoHierarchyDTO.getGeoEntityHierarchyViews(this.getClientRequest(), sortAttribute, isAscending, pageSize, pageNumber);
    
    req.setAttribute("query", query);
    
    req.getRequestDispatcher(VIEW_ALL_DEFINITIONS_JSP).forward(req, resp);
  }
  
  @Override
  public void viewHierarchyTree(String rootGeoHierarchyId) throws ServletException, IOException
  {
    req.setAttribute(ROOT_GEO_HIERARCHY_ID, rootGeoHierarchyId);
    
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
