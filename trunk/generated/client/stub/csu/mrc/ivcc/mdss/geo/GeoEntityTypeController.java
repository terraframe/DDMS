package csu.mrc.ivcc.mdss.geo;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.constants.MdBusinessInfo;

public class GeoEntityTypeController extends GeoEntityTypeControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236133816932L;

  private static final String NEW_DEFINITION_JSP = "/WEB-INF/newDefinition.jsp";
  private static final String VIEW_DEFINITION_JSP = "/WEB-INF/viewDefinition.jsp";
  
  public GeoEntityTypeController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void newDefinition() throws IOException, ServletException
  {
    GeoEntityDefinitionDTO def = new GeoEntityDefinitionDTO(this.getClientRequest());

    GeoHierarchyQueryDTO query = GeoHierarchyDTO.getAllInstances(this.getClientRequest(), MdBusinessInfo.KEY, true, 10, 1);
    
    req.setAttribute("availableParents", query.getResultSet());
    req.setAttribute("allowedInParents", query.getResultSet());
    req.setAttribute("definition", def);
    
    req.getRequestDispatcher(NEW_DEFINITION_JSP).forward(req, resp);
  }
  
  @Override
  public void createDefinition(GeoEntityDefinitionDTO definition, String[] allowedInIds) throws IOException, ServletException
  {
    GeoHierarchyDTO.defineGeoEntity(this.getClientRequest(), definition, allowedInIds);
    
    req.getRequestDispatcher(VIEW_DEFINITION_JSP).forward(req, resp);
  }

  @Override
  public void cancelCreateDefinition() throws IOException, ServletException
  {
  }
  
}
