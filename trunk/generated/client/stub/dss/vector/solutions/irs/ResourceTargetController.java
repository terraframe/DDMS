package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.util.ErrorUtility;

// TODO: delete unused methods from metadata

public class ResourceTargetController extends ResourceTargetControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/ResourceTarget/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1240257007714L;

  public ResourceTargetController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("sprayTeams", new LinkedList<SprayTeamDTO>());
    req.setAttribute("seasons", MalariaSeasonDTO.getAllInstances(request, "endDate", true, 0, 0).getResultSet());

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

      List<SprayTeamDTO> sprayTeams = new ArrayList<SprayTeamDTO>();
      List<SprayOperatorDTO> sprayOperators = new ArrayList<SprayOperatorDTO>();

      if (id.equals("ALL"))
      {
        //Get the GeoEntity which corresponds to the GeoId
        sprayTeams.addAll(Arrays.asList(SprayTeamDTO.findByLocation(request, geoId)));
        req.setAttribute("sumLastRow", false);
      }
      else
      {
        SprayTeamDTO team = SprayTeamDTO.get(request, id);
        sprayTeams.add(team);
        
        sprayOperators.addAll(team.getAllSprayTeamMembers());

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

      String[] array = targetIds.toArray(new String[targetIds.size()]);

      ResourceTargetViewDTO[] targets = ResourceTargetViewDTO.getResourceTargets(request, array, season);

      req.setAttribute("resourceTargetViews", targets);
      render("viewComponent.jsp");
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
    
    if(geoId == null)
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
