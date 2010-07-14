package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.DataGrid;

// TODO: delete unused methods from metadata

public class ResourceTargetController extends ResourceTargetControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/ResourceTarget/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1240257007714L;

  public ResourceTargetController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("sprayTeams", new LinkedList<SprayTeamDTO>());
    req.setAttribute("seasons", Arrays.asList(MalariaSeasonDTO.getAllForDisease(request)));

    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id, MalariaSeasonDTO season, String geoId) throws IOException, ServletException
  {
    try
    {
      validateParameters(id, season, geoId);

      ClientRequestIF request = super.getClientRequest();

      String[] targetIds = this.getTargetIds(id, geoId, request);

      DataGrid grid = new ResourceTargetGridBuilder(request, targetIds, season).build();

      req.setAttribute("grid", grid);
      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.viewAll();
      }
    }

  }

  private String[] getTargetIds(String id, String geoId, ClientRequestIF request)
  {
    List<String> targetIds = new ArrayList<String>();
    List<SprayTeamDTO> sprayTeams = new ArrayList<SprayTeamDTO>();
    List<TeamMemberDTO> sprayOperators = new ArrayList<TeamMemberDTO>();

    if (id.equals("ALL"))
    {
      // Get the GeoEntity which corresponds to the GeoId
      sprayTeams.addAll(Arrays.asList(SprayTeamDTO.findByLocation(request, geoId)));
    }
    else
    {
      SprayTeamDTO team = SprayTeamDTO.get(request, id);

      // We do not want to show the team summary row since weeks do not add up
      // sprayTeams.add(team);

      sprayOperators.addAll(team.getAllSprayTeamMembers());
    }

    // add all the team members
    for (TeamMemberDTO teamMember : sprayOperators)
    {
      targetIds.add(teamMember.getId());
    }

    // add the member's Team or All Teams
    for (SprayTeamDTO team : sprayTeams)
    {
      targetIds.add(team.getId());
    }

    return targetIds.toArray(new String[targetIds.size()]);
  }

  private void validateParameters(String teamId, MalariaSeasonDTO season, String geoId)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (teamId == null)
    {
      problems.add(new RequiredSprayTeamProblemDTO(this.getClientRequest(), req.getLocale()));
    }

    if (season == null)
    {
      problems.add(new RequiredSeasonProblemDTO(this.getClientRequest(), req.getLocale()));
    }

    if (geoId == null)
    {
      problems.add(new RequiredGeoIdProblemDTO(this.getClientRequest(), req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  public void failView(java.lang.String id) throws IOException, ServletException
  {
    this.viewAll();
  }
}
