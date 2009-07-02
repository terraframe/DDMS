package dss.vector.solutions;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.system.RolesDTO;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.FacadeDTO;

public class RoleController extends RoleControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242168689001L;

  public RoleController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
    this.dir = "WEB-INF/dss/vector/solutions/RoleController/";
    this.layout = "/layout.jsp";
  }

  @Override
  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MDSSUserViewQueryDTO query = MDSSUserViewDTO.getPage(clientRequest, null, true, 10, 1);
    req.setAttribute("query", query);
    render("viewAll.jsp");
  }

  @Override
  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
      throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MDSSUserViewQueryDTO query = MDSSUserViewDTO.getPage(clientRequest, sortAttribute, isAscending,
        pageSize, pageNumber);
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
      List<RolesDTO> assigned = new LinkedList<RolesDTO>();

      // Start by assuming that the user has no roles
      List<RolesDTO> revoked = new LinkedList<RolesDTO>(Arrays.asList(FacadeDTO
          .getMDSSRoles(clientRequest)));

      for (RolesDTO assignedRole : userDTO.getAllAssignedRole())
      {
        // If the user has the role, move it from revoked to assigned
        if (revoked.contains(assignedRole))
        {
          revoked.remove(assignedRole);
          assigned.add(assignedRole);
        }
      }

      req.setAttribute("id", id);
      req.setAttribute("assigned", assigned.toArray());
      req.setAttribute("revoked", revoked.toArray());

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
    ClientRequestIF clientRequest = super.getClientRequest();
    MDSSUserDTO.updateRoles(clientRequest, id, assigned, revoked);

    render("success.jsp");
  }
}
