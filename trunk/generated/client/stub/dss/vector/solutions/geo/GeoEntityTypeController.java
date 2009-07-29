package dss.vector.solutions.geo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.MdBusinessInfo;
import com.terraframe.mojo.web.json.JSONMojoExceptionDTO;
import com.terraframe.mojo.web.json.JSONProblemExceptionDTO;
import com.terraframe.mojo.web.json.JSONSmartExceptionDTO;

public class GeoEntityTypeController extends GeoEntityTypeControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID              = 1236133816932L;

  private static final String NEW_DEFINITION_JSP            = "/WEB-INF/newDefinition.jsp";

  private static final String EDIT_DEFINITION_JSP           = "/WEB-INF/editDefinition.jsp";

  private static final String VIEW_DEFINITION_JSP           = "/WEB-INF/viewDefinition.jsp";

  private static final String VIEW_DEFINITION_COMPONENT_JSP = "/WEB-INF/viewDefinitionComponent.jsp";

  private static final String TREE_JSP                      = "/WEB-INF/geoHierarchyTree.jsp";

  private static final String TREE_COMPONENT_JSP            = "/WEB-INF/geoHierarchyTreeComponent.jsp";

  public static final String  ROOT_GEO_HIERARCHY_ID         = "rootGeoHierarchyId";

  public GeoEntityTypeController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void newDefinition(String parentGeoHierarchyId) throws IOException, ServletException
  {
    try
    {
      GeoEntityDefinitionDTO def = new GeoEntityDefinitionDTO(this.getClientRequest());
      def.setParentGeoHierarchyId(parentGeoHierarchyId);

      GeoHierarchyViewQueryDTO query = GeoHierarchyDTO.getGeoEntityHierarchyViews(this
          .getClientRequest(), MdBusinessInfo.DISPLAY_LABEL, true, null, null);
      List<SpatialMasterDTO> types = SpatialTypesDTO.allItems(this.getClientRequest());

      req.setAttribute("availableParents", query.getResultSet());
      req.setAttribute("types", types);
      req.setAttribute("definition", def);
      
      req.getRequestDispatcher(NEW_DEFINITION_JSP).forward(req, resp);
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
  public void createDefinition(GeoEntityDefinitionDTO definition) throws IOException, ServletException
  {
    try
    {
      String geoHierarchyViewId = GeoHierarchyDTO.defineGeoEntity(this.getClientRequest(), definition);

      // return the id to the calling Ajax process
      resp.getWriter().write(geoHierarchyViewId);
    }
    catch (ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (SpatialTypeDefinedExceptionDTO e)
    {
      JSONSmartExceptionDTO ex = new JSONSmartExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(ex.getJSON());
    }
    catch (SpatialTypeRequiredExceptionDTO e)
    {
      JSONSmartExceptionDTO ex = new JSONSmartExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(ex.getJSON());
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO jsonE = new JSONMojoExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }

  @Override
  public void cancelCreateDefinition() throws IOException, ServletException
  {
    // handled in Ajax calling process
  }

  @Override
  public void editDefinition(String geoHierarchyId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = this.getClientRequest();
      GeoHierarchyDTO.lock(clientRequest, geoHierarchyId);
      
      GeoHierarchyViewDTO view = GeoHierarchyDTO.getViewForGeoHierarchy(clientRequest, geoHierarchyId);
      GeoEntityDefinitionDTO definition = GeoHierarchyDTO.getGeoEntityDefinition(clientRequest, geoHierarchyId);
      
      req.setAttribute("parentLabel", view.getIsADisplayLabel());            
      req.setAttribute("geoHierarchyId", geoHierarchyId);
      req.setAttribute("definition", definition);
      req.setAttribute("view", view);

      req.getRequestDispatcher(EDIT_DEFINITION_JSP).forward(req, resp);
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
  public void updateDefinition(GeoHierarchyViewDTO view) throws IOException, ServletException
  {
    try
    {
      GeoHierarchyDTO.updateFromView(this.getClientRequest(), view);

      // return the id to the calling Ajax process
      resp.getWriter().write(view.getGeoHierarchyId());
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
  public void cancelUpdateDefinition(String geoHierarchyId) throws IOException, ServletException
  {
    try
    {
      GeoHierarchyDTO.unlock(this.getClientRequest(), geoHierarchyId);

      // return the id to the calling Ajax process
      resp.getWriter().write(geoHierarchyId);
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
  public void viewDefinition(String geoHierarchyId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = this.getClientRequest();
      GeoHierarchyViewDTO view = GeoHierarchyDTO.getViewForGeoHierarchy(clientRequest, geoHierarchyId);
            
      req.setAttribute("view", view);

      if (this.isAsynchronous())
      {
        req.getRequestDispatcher(VIEW_DEFINITION_COMPONENT_JSP).forward(req, resp);
      }
      else
      {
        req.getRequestDispatcher(VIEW_DEFINITION_JSP).forward(req, resp);
      }
    }
    catch (Throwable t)
    {
      JSONMojoExceptionDTO ex = new JSONMojoExceptionDTO(t);
      this.resp.setStatus(500);
      this.resp.getWriter().write(ex.getJSON());
    }
  }

  @Override
  public void viewHierarchyTree(String rootGeoHierarchyId) throws ServletException, IOException
  {
    try
    {
      if (rootGeoHierarchyId == null || rootGeoHierarchyId.trim().length() == 0)
      {
        GeoHierarchyViewDTO view = GeoHierarchyDTO.getEarthGeoHierarchy(this.getClientRequest());
        rootGeoHierarchyId = view.getGeoHierarchyId();
      }

      req.setAttribute(ROOT_GEO_HIERARCHY_ID, rootGeoHierarchyId);

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
      JSONMojoExceptionDTO ex = new JSONMojoExceptionDTO(t);
      this.resp.setStatus(500);
      this.resp.getWriter().write(ex.getJSON());
    }
  }

}
