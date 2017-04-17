package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.geo.generated.SprayZoneDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class SprayTeamController extends SprayTeamControllerBase implements Reloadable
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
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      renderCreate(clientRequest, new SprayTeamDTO(clientRequest));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance();
      }
    }
  }

  @Override
  public void createAndAssign(SprayTeamDTO team, String geoId, String leaderId, String[] operatorIDs) throws IOException, ServletException
  {
    try
    {
      team.create(geoId, leaderId, operatorIDs);

      this.view(team.getId());
      return;
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req, resp, this.isAsynchronous());
    }

    req.setAttribute("leaderId", leaderId);
    renderCreate(super.getClientRequest(), team);
  }

  private void renderCreate(ClientRequestIF clientRequest, SprayTeamDTO team) throws IOException, ServletException
  {
    req.setAttribute("SprayZone", SprayZoneDTO.CLASS);
    req.setAttribute("item", team);
    // req.setAttribute("leaders",
    // SprayLeaderDTO.getAllInstances(super.getClientSession().getRequest(),
    // "keyName", true, 0, 0).getResultSet());

    List<TeamMemberViewDTO> currentOperators = new LinkedList<TeamMemberViewDTO>();
    List<TeamMemberViewDTO> assignedOperators = new LinkedList<TeamMemberViewDTO>();
    List<TeamMemberViewDTO> availableOperators = new LinkedList<TeamMemberViewDTO>();

    for (TeamMemberViewDTO operator : TeamMemberViewDTO.getAllOperators(clientRequest))
    {
      if (!operator.getIsAssigned())
      {
        availableOperators.add(operator);
      }
    }

    if (!team.isNewInstance())
    {
      currentOperators.addAll(Arrays.asList(SprayTeamDTO.getOperatorViews(clientRequest, team.getId())));
    }

    req.setAttribute("current", currentOperators);
    req.setAttribute("available", availableOperators);
    req.setAttribute("assigned", assignedOperators);
    req.setAttribute("view", new SprayTeamViewDTO(this.getClientRequest()));

    render("createComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      SprayTeamDTO team = SprayTeamDTO.lock(clientRequest, id);
      req.setAttribute("item", team);
      req.setAttribute("SprayZone", SprayZoneDTO.CLASS);

      // req.setAttribute("leaders",
      // SprayLeaderDTO.getAllInstances(super.getClientSession().getRequest(),
      // "keyName", true, 0, 0).getResultSet());

      TeamMemberViewDTO[] assigned = TeamMemberViewDTO.getAllOperatorsForLocation(clientRequest, team.getSprayZone().getGeoId());
      List<String> locatedIn = new LinkedList<String>();

      for (TeamMemberViewDTO view : assigned)
      {
        locatedIn.add(view.getActorId());
      }

      List<TeamMemberViewDTO> assignedOperators = new LinkedList<TeamMemberViewDTO>();
      List<TeamMemberViewDTO> currentOperators = new LinkedList<TeamMemberViewDTO>();
      List<TeamMemberViewDTO> availableOperators = new LinkedList<TeamMemberViewDTO>();

      for (TeamMemberViewDTO operator : TeamMemberViewDTO.getAllOperators(clientRequest))
      {
        if (operator.getIsAssigned())
        {
          if (operator.getTeamId().equals(team.getTeamId()))
          {
            currentOperators.add(operator);
          }
          else if (locatedIn.contains(operator.getActorId()))
          {
            assignedOperators.add(operator);
          }
        }
        else
        {
          availableOperators.add(operator);
        }
      }

      List<? extends TeamMemberDTO> leader = team.getAllTeamLeader();

      if (leader.size() > 0)
      {
        TeamMemberDTO l = leader.get(0);
        PersonDTO person = l.getPerson();

        req.setAttribute("leaderLabel", person.getFirstName() + " " + person.getLastName() + " - " + l.getMemberId());
        req.setAttribute("leaderId", l.getId());
      }

      req.setAttribute("current", currentOperators);
      req.setAttribute("available", availableOperators);
      req.setAttribute("assigned", assignedOperators);
      req.setAttribute("view", new SprayTeamViewDTO(this.getClientRequest()));

      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failEdit(id);
      }
    }

  }

  @Override
  public void updateAssignments(SprayTeamDTO team, String geoId, String leaderId, String[] operatorIds, String[] removedIds) throws IOException, ServletException
  {
    try
    {
      team.edit(geoId, leaderId, operatorIds, removedIds);
      this.view(team.getId());

      return;
    }
    catch (Exception e)
    {
      ErrorUtility.prepareThrowable(e, req, resp, this.isAsynchronous());
    }

    req.setAttribute("leaderId", leaderId);
    renderCreate(super.getClientRequest(), team);
  }

  private void renderView(SprayTeamDTO sprayTeamDTO) throws IOException, ServletException
  {
    List<? extends TeamMemberDTO> allTeamLeader = sprayTeamDTO.getAllTeamLeader();

    if (allTeamLeader.size() > 0)
    {
      req.setAttribute("leader", allTeamLeader.get(0).getPerson().getView());
    }

    req.setAttribute("operators", TeamMemberViewDTO.getAllOperatorsForTeam(super.getClientRequest(), sprayTeamDTO));
    req.setAttribute("item", sprayTeamDTO);
    req.setAttribute("view", new SprayTeamViewDTO(this.getClientRequest()));
    render("viewComponent.jsp");
  }

  public void create(SprayTeamDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }

  public void failCreate(SprayTeamDTO dto) throws IOException, ServletException
  {
    req.setAttribute("SprayZone", SprayZoneDTO.CLASS);
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
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void failUpdate(SprayTeamDTO dto) throws IOException, ServletException
  {
    req.setAttribute("SprayZone", SprayZoneDTO.CLASS);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    SprayTeamViewQueryDTO query = SprayTeamViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    SprayTeamViewQueryDTO query = SprayTeamViewDTO.getPage(clientRequest, SprayTeamDTO.TEAMID, true, 20, 1);

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
    try
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("id", id);
      utility.checkURL(this.getClass().getSimpleName(), "view");

      ClientRequestIF clientRequest = super.getClientRequest();
      SprayTeamDTO sprayTeamDTO = SprayTeamDTO.get(clientRequest, id);
      renderView(sprayTeamDTO);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failView(id);
      }
    }

  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void cancel(SprayTeamDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.unlock();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCancel(dto);
      }
    }
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
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(SprayTeamDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    this.edit(dto.getId());
  }
}
