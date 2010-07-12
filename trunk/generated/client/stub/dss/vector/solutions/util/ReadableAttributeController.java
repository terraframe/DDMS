package dss.vector.solutions.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.system.RolesDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.permission.MDSSRoleDTO;
import dss.vector.solutions.surveillance.AggregatedAgeGroupDTO;
import dss.vector.solutions.surveillance.AggregatedAgeGroupQueryDTO;

public class ReadableAttributeController extends ReadableAttributeControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239296298324L;

  public ReadableAttributeController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
    this.dir = "WEB-INF/dss/vector/solutions/util/ReadableAttributeController/";
    this.layout = this.dir + "layout.jsp";
  }

  @Override
  public void getUniversal(String actor) throws IOException, ServletException
  {
    if (actor == null)
    {
      actor = MDSSRoleInfo.GUI_VISIBILITY;
    }

    ClientRequestIF clientRequest = super.getClientRequest();
    
    List<RolesDTO> roles = new LinkedList<RolesDTO>();
    
    RolesDTO[] systemRoles = FacadeDTO.getMDSSRoles(clientRequest);
    RolesDTO[] mdssRoles = MDSSRoleDTO.getRoles(clientRequest);
    
    RolesDTO[][] allRoles = new RolesDTO[][]{systemRoles, mdssRoles};
    
    for(int i = 0; i < allRoles.length; i++)
    {
      for(int j = 0; j < allRoles[i].length; j++)
      {
        roles.add(allRoles[i][j]);
      }
    }

    req.setAttribute("actor", actor);
    req.setAttribute("actorOptions", roles);

    render("selectUniversal.jsp");
  }

  @Override
  public void getAttributes(String universal, String actor) throws IOException, ServletException
  {
    try
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("universal", universal);
      utility.put("actor", actor);
      utility.checkURL(this.getClass().getSimpleName(), "getAttributes");
      
      ReadableAttributeViewDTO[] attributeViews = ReadableAttributeViewDTO.getActorAttributes(super.getClientRequest(), universal, actor);
      req.setAttribute("views", Arrays.asList(attributeViews));
      req.setAttribute("universal", universal);
      req.setAttribute("actor", actor);
      render("view.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.forceProblems(e, req);
      this.failGetAttributes(universal, actor);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failGetAttributes(universal, actor);
    }
  }

  @Override
  public void failGetAttributes(String universal, String actor) throws IOException, ServletException
  {
    this.getUniversal(actor);
  }

  @Override
  public void setAttributes(String universal, String actor, ReadableAttributeViewDTO[] attributeViews) throws IOException, ServletException
  {
    try
    {
      ReadableAttributeViewDTO.setActorAttributes(super.getClientRequest(), universal, actor, attributeViews);
      render("success.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.forceProblems(e, req);
      this.failSetAttributes(universal, actor, attributeViews);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failSetAttributes(universal, actor, attributeViews);
    }
  }
  
  @Override
  public void failSetAttributes(String universal, String actor, ReadableAttributeViewDTO[] attributeViews) throws IOException, ServletException
  {
    this.getAttributes(universal, actor);
  }
}
