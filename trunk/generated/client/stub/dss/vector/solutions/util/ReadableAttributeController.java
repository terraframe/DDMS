package dss.vector.solutions.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.RolesDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.permission.MDSSRoleDTO;

public class ReadableAttributeController extends ReadableAttributeControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/util/ReadableAttributeController/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long serialVersionUID = 1239296298324L;

  public ReadableAttributeController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void getUniversal(String actor) throws IOException, ServletException
  {
    try
    {
      // Ensure the user has permissions to read this page
      new ReadableAttributeViewDTO(getClientRequest());
      
      if (actor == null)
      {
        actor = MDSSRoleInfo.GUI_VISIBILITY;
      }

      ClientRequestIF clientRequest = super.getClientRequest();

      Map<String, RolesDTO> roles = this.getAllRoles(clientRequest);

      req.setAttribute("actor", actor);
      req.setAttribute("actorOptions", roles.values());

      render("selectUniversal.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failGetUniversal(actor);
      }
    }
  }
  
  private Map<String, RolesDTO> getAllRoles(ClientRequestIF clientRequest) {
      Map<String, RolesDTO> roles = new LinkedHashMap<String, RolesDTO>();

      RolesDTO[] systemRoles = FacadeDTO.getMDSSRoles(clientRequest);
      for (int i = 0; i < systemRoles.length; i++) {
          roles.put(systemRoles[i].getRoleName(), systemRoles[i]);
      }
      
      RolesDTO[] mdssRoles = MDSSRoleDTO.getRoles(clientRequest);
      for (int i = 0; i < mdssRoles.length; i++) {
          roles.put(mdssRoles[i].getRoleName(), mdssRoles[i]);
      }

      return roles;
}

@Override
  public void failGetUniversal(String actor) throws IOException, ServletException
  {
    this.req.getRequestDispatcher("/index").forward(this.req, this.resp);
  }

  @Override
  public void getAttributes(String universal, String actor) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
        
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("universal", universal);
      utility.put("actor", actor);
      utility.checkURL(this.getClass().getSimpleName(), "getAttributes");

      ReadableAttributeViewDTO[] attributeViews = ReadableAttributeViewDTO.getActorAttributes(clientRequest, universal, actor);
      req.setAttribute("views", Arrays.asList(attributeViews));
      req.setAttribute("universal", universal);
      req.setAttribute("actor", actor);
      req.setAttribute("actorLabel", this.getAllRoles(clientRequest).get(actor).getDisplayLabel());
      req.setAttribute("component", req.getParameter("component"));
      
      render("view.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failGetAttributes(universal, actor);
      }
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
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSetAttributes(universal, actor, attributeViews);
      }
    }
  }

  @Override
  public void failSetAttributes(String universal, String actor, ReadableAttributeViewDTO[] attributeViews) throws IOException, ServletException
  {
    this.getAttributes(universal, actor);
  }
}
