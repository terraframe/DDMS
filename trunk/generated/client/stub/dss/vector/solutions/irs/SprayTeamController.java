package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class SprayTeamController extends SprayTeamControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/SprayTeam/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1241327457793L;

  public SprayTeamController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    renderCreate(clientRequest, new SprayTeamDTO(clientRequest));
  }

  @Override
  public void createAndAssign(SprayTeamDTO team, String geoId, String leaderId, String[] operatorIDs)
      throws IOException, ServletException
  {
    try
    {
      team.create(geoId, leaderId, operatorIDs);

      this.view(team.getId());
      return;
    }
    catch (ProblemExceptionDTO p)
    {
      ErrorUtility.prepareProblems(p, req);
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req);
    }

    req.setAttribute("leaderId", leaderId);
    renderCreate(super.getClientRequest(), team);
  }

  private void renderCreate(ClientRequestIF clientRequest, SprayTeamDTO team) throws IOException,
      ServletException
  {
    req.setAttribute("item", team);
    req.setAttribute("leaders", SprayLeaderDTO.getAllInstances(super.getClientSession().getRequest(),
        "keyName", true, 0, 0).getResultSet());

    List<SprayOperatorViewDTO> currentOperators = new LinkedList<SprayOperatorViewDTO>();
    List<SprayOperatorViewDTO> assignedOperators = new LinkedList<SprayOperatorViewDTO>();
    List<SprayOperatorViewDTO> availableOperators = new LinkedList<SprayOperatorViewDTO>();
    
    for (SprayOperatorViewDTO operator : SprayOperatorViewDTO.getAll(clientRequest))
    {
      if (operator.getIsAssigned())
      {
//        assignedOperators.add(operator);
      }
      else
      {
        availableOperators.add(operator);
      }
    }
    req.setAttribute("current", currentOperators);
    req.setAttribute("available", availableOperators);
    req.setAttribute("assigned", assignedOperators);

    render("createComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      SprayTeamDTO team = SprayTeamDTO.lock(clientRequest, id);
      req.setAttribute("item", team);
      req.setAttribute("leaders", SprayLeaderDTO.getAllInstances(super.getClientSession().getRequest(),
          "keyName", true, 0, 0).getResultSet());

      List<SprayOperatorViewDTO> currentOperators = new LinkedList<SprayOperatorViewDTO>();
      List<SprayOperatorViewDTO> assignedOperators = new LinkedList<SprayOperatorViewDTO>();
      List<SprayOperatorViewDTO> availableOperators = new LinkedList<SprayOperatorViewDTO>();
      for (SprayOperatorViewDTO operator : SprayOperatorViewDTO.getAll(clientRequest))
      {
        if (operator.getIsAssigned())
        {
          if (operator.getTeamId().equals(team.getTeamId()))
            currentOperators.add(operator);
          else
            assignedOperators.add(operator);
        }
        else
        {
          availableOperators.add(operator);
        }
      }

      List<? extends SprayLeaderDTO> leader = team.getAllTeamLeader();

      if (leader.size() > 0)
      {
        req.setAttribute("leaderId", leader.get(0).getId());
      }

      req.setAttribute("current", currentOperators);
      req.setAttribute("available", availableOperators);
      req.setAttribute("assigned", assignedOperators);

      render("editComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failEdit(id);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failEdit(id);
    }

  }

  @Override
  public void updateAssignments(SprayTeamDTO team, String geoId, String leaderId, String[] operatorIds,
      String[] removedIds) throws IOException, ServletException
  {
    try
    {
      team.edit(geoId, leaderId, operatorIds, removedIds);
      this.view(team.getId());

      return;
    }
    catch (ProblemExceptionDTO p)
    {
      ErrorUtility.prepareProblems(p, req);
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req);
    }

    req.setAttribute("leaderId", leaderId);
    renderCreate(super.getClientRequest(), team);
  }

  private void renderView(SprayTeamDTO sprayTeamDTO) throws IOException, ServletException
  {
    List<? extends SprayLeaderDTO> allTeamLeader = sprayTeamDTO.getAllTeamLeader();
    if (allTeamLeader.size() > 0)
    {
      req.setAttribute("leader", allTeamLeader.get(0).getPerson());
    }
    req.setAttribute("operators", SprayOperatorViewDTO.getAllForTeam(super.getClientRequest(),
        sprayTeamDTO));
    req.setAttribute("item", sprayTeamDTO);
    render("viewComponent.jsp");
  }

  public void create(SprayTeamDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }

  public void failCreate(SprayTeamDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void update(SprayTeamDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void failUpdate(SprayTeamDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
      throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    SprayTeamQueryDTO query = SprayTeamDTO.getAllInstances(clientRequest, sortAttribute, isAscending,
        pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber)
      throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    SprayTeamQueryDTO query = SprayTeamDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void view(String id) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");

    ClientRequestIF clientRequest = super.getClientRequest();
    SprayTeamDTO sprayTeamDTO = SprayTeamDTO.get(clientRequest, id);
    renderView(sprayTeamDTO);
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void cancel(SprayTeamDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(SprayTeamDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void delete(SprayTeamDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(SprayTeamDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    this.edit(dto.getId());
  }
}
