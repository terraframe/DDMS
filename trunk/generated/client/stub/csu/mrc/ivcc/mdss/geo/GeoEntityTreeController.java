package csu.mrc.ivcc.mdss.geo;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.ApplicationException;

import csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO;

public class GeoEntityTreeController extends GeoEntityTreeControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236364846909L;
  
  private static final String TREE_JSP = "/WEB-INF/geoEntityTree.jsp";
  
  private static final String CONFIRM_PARENT_CHANGE_JSP = "/WEB-INF/confirmParentChange.jsp";
  
  public GeoEntityTreeController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  @Override
  public void displayTree(String rootGeoEntityId) throws IOException, ServletException
  {
    req.setAttribute("rootGeoEntityId", rootGeoEntityId);
    
    req.getRequestDispatcher(TREE_JSP).forward(req, resp);
  }
  
  @Override
  public void confirmChangeParent(String childId, String parentId) throws IOException, ServletException
  {
    req.setAttribute("parentId", parentId);
    req.setAttribute("childId", childId);
    
    try
    {
      GeoEntityDTO.confirmChangeParent(this.getClientRequest(), childId, parentId);
      
      // We should never reach here
      String error = "Unable to confirm parent change.";
      ApplicationException ae = new ApplicationException(error);
      String message = ae.getLocalizedMessage();
      resp.sendError(500, message);
    }
    catch(ConfirmParentChangeExceptionDTO e)
    {
      String message = e.getLocalizedMessage();
      req.setAttribute("confirmMessage", message);
      
      req.getRequestDispatcher(CONFIRM_PARENT_CHANGE_JSP).forward(req, resp);
    }
  }
  
}
