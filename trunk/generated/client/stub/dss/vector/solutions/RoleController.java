package dss.vector.solutions;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.system.RolesDTO;

import dss.vector.solutions.permission.MDSSRoleDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.FacadeDTO;
import dss.vector.solutions.util.RedirectUtility;

public class RoleController extends RoleControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242168689001L;

  public RoleController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
    this.dir = "WEB-INF/dss/vector/solutions/RoleController/";
    this.layout = "/layout.jsp";
  }

  @Override
  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    MDSSUserViewQueryDTO query = MDSSUserViewDTO.getPage(clientRequest, null, true, 10, 1);
    req.setAttribute("query", query);
    render("viewAll.jsp");
  }

  @Override
  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MDSSUserViewQueryDTO query = MDSSUserViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAll.jsp");
  }

  @Override
  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      MDSSUserDTO userDTO = MDSSUserDTO.get(clientRequest, id);

      // Start by assuming that the user has no roles
      RolesDTO[] systemRoles = FacadeDTO.getMDSSRoles(clientRequest);
      List<RolesDTO> roles = new LinkedList<RolesDTO>(Arrays.asList(MDSSRoleDTO.getRoles(clientRequest)));
      List<? extends RolesDTO> assigned = userDTO.getAllAssignedRole();
      List<String> list = new LinkedList<String>();
                  
      for(RolesDTO role : systemRoles)
      {
        if(role.getRoleName().equalsIgnoreCase(MDSSRoleInfo.SYSTEM))
        {
          roles.add(0, role);
        }
      }
     
      for(RolesDTO role : assigned)
      {
        list.add(role.getId());
      }

      req.setAttribute("id", id);
      req.setAttribute("assigned", list);
      req.setAttribute("roles", roles);
      req.setAttribute("user", userDTO);
      req.setAttribute("view", new MDSSUserViewDTO(clientRequest));
      
      render("edit.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.viewAll();
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.viewAll();
    }
  }

  @Override
  public void save(String id, String[] assigned, String[] revoked) throws IOException, ServletException
  {
    try
    {
    ClientRequestIF clientRequest = super.getClientRequest();
    MDSSUserDTO.updateRoles(clientRequest, id, assigned, revoked);

    render("success.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failSave(id, assigned, revoked);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failSave(id, assigned, revoked);
    }
  }
  
  @Override
  public void failSave(String id, String[] assigned, String[] revoked) throws IOException, ServletException
  {
    this.edit(id);
  }
}
