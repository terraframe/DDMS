package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.business.generation.GenerationUtil;
import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

// TODO: delete unused methods from metadata

public class ResourceTargetController extends ResourceTargetControllerBase implements
    com.runwaysdk.generation.loader.Reloadable
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

      ResourceTargetViewDTO view = new ResourceTargetViewDTO(request);
      ResourceTargetViewDTO[] data = ResourceTargetViewDTO.getResourceTargets(request, targetIds, season);
      
      String[] keys = this.getKeys();
      Map<String, ColumnSetup> map = this.getColumns(keys, false);
      
      req.setAttribute("grid", new ViewDataGrid(view, map, keys, data));
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

  private String[] getTargetIds(String id, String geoId, ClientRequestIF request)
  {
    List<String> targetIds = new ArrayList<String>();
    List<SprayTeamDTO> sprayTeams = new ArrayList<SprayTeamDTO>();
    List<TeamMemberDTO> sprayOperators = new ArrayList<TeamMemberDTO>();

    if (id.equals("ALL"))
    {
      //Get the GeoEntity which corresponds to the GeoId
      sprayTeams.addAll(Arrays.asList(SprayTeamDTO.findByLocation(request, geoId)));
    }
    else
    {
      SprayTeamDTO team = SprayTeamDTO.get(request, id);
      
      //We do not want to show the team summary row since weeks do not add up 
      //sprayTeams.add(team);
      
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
  
  private String[] getKeys()
  {
    List<String> keys = new LinkedList<String>();
    
    keys.add(ResourceTargetViewDTO.TARGETID);
    keys.add(ResourceTargetViewDTO.SEASON);
    keys.add(ResourceTargetViewDTO.TARGETER);
    keys.add(ResourceTargetViewDTO.TARGETERNAME);
    
    for(int i=0; i <= 52; i++)
    {
      keys.add(ResourceTargetViewDTO.TARGET + i);
    }
    
    String[] array = keys.toArray(new String[keys.size()]);
    
    this.upperFirstCharacter(array);
    
    return array;
  }
  
  private Map<String, ColumnSetup> getColumns(String[] keys, Boolean sum)
  {   
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    for (int i = 0; i < keys.length; i++)
    {
      ColumnSetup setup = ( i < 3 ? new ColumnSetup(true, false) : new ColumnSetup(false, true) );
      
      if(i >= 3 && ! sum)
      {
        setup.setSum(sum);
      }
      
      map.put(keys[i], setup);
    }

    return map;
  }
  
  private void upperFirstCharacter(String[] array)
  {
    for (int i = 0; i < array.length; i++)
    {
      array[i] = GenerationUtil.upperFirstCharacter(array[i]);
    }
  }

}
