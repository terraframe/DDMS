package dss.vector.solutions.irs;

import java.util.ArrayList;
import java.util.List;

import dss.vector.solutions.general.MalariaSeasonDTO;

//TODO: delete unused methods from metadata

public class ResourceTargetController extends ResourceTargetControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/ResourceTarget/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1240257007714L;

  public ResourceTargetController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @SuppressWarnings("unchecked")
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();

    List<SprayTeamDTO> sprayTeams = (List<SprayTeamDTO>) SprayTeamDTO.getAllInstances(clientRequest, SprayTeamDTO.TEAMID, true, 0, 0).getResultSet();

    req.setAttribute("sprayTeams", sprayTeams);

    req.setAttribute("seasons", MalariaSeasonDTO.getAllInstances(super.getClientSession().getRequest(), "endDate", true, 0, 0).getResultSet());

    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  @SuppressWarnings("unchecked")
  public void view(java.lang.String id, MalariaSeasonDTO season) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();

    List<SprayTeamDTO> sprayTeams = new ArrayList<SprayTeamDTO>();
    List<SprayOperatorDTO> sprayOperators = new ArrayList<SprayOperatorDTO>();
    if (id.equals("ALL"))
    {
      sprayTeams = (List<SprayTeamDTO>) SprayTeamDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet();
      req.setAttribute("sumLastRow", false);
    }
    else
    {
      SprayTeamDTO team = SprayTeamDTO.get(clientRequest, id);
      sprayTeams.add(team);
      sprayOperators = (List<SprayOperatorDTO>) team.getAllSprayTeamMembers();
      req.setAttribute("sumLastRow", true);
    }

    List<String> targetIds = new ArrayList<String>();
    // add all the team members
    for (SprayOperatorDTO teamMember : sprayOperators)
    {
      targetIds.add(teamMember.getId());
    }

    // add the member's Team or All Teams
    for (SprayTeamDTO team : sprayTeams)
    {
      targetIds.add(team.getId());
    }

    ResourceTargetViewDTO[] resourceTargetViews = ResourceTargetViewDTO.getResourceTargets(clientRequest, (String[]) targetIds.toArray(new String[targetIds.size()]), season);

    req.setAttribute("resourceTargetViews", resourceTargetViews);
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

}
