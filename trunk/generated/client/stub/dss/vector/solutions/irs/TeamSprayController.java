package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.util.ErrorUtility;

public class TeamSprayController extends TeamSprayControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/TeamSpray/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  private static final long  serialVersionUID = 1240860635607L;

  public TeamSprayController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();

      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failCreate(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failCreate(dto);
    }
  }

  public void failCreate(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("operators", getTeamMembers(dto.getSprayTeam()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Team Sprays");

    render("createComponent.jsp");
  }

  public void update(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failUpdate(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failUpdate(dto);
    }
  }

  public void failUpdate(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("operators", getTeamMembers(dto.getSprayTeam()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update Team Sprays");
    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(TeamSprayViewDTO.get(this.getClientRequest(), id));
  }

  public void view(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    if (!req.getRequestURI().contains(".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", this.getClass().getSimpleName());
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".view.mojo") + "?id=" + dto.getId());
      return;
    }
    
    // FIXME: This is a hack to ensure the dto is dirty when its sent back to
    // the server. Remove when nathan has submitted his fix.
    dto.setModified(true);
    dto.setModified(TeamSprayViewDTO.SPRAYID, true);

    req.setAttribute("item", dto);
    req.setAttribute("status", dto.getStatus());
    req.setAttribute("page_title", "View Team Sprays");
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void edit(String id) throws IOException, ServletException
  {
    TeamSprayViewDTO dto = TeamSprayDTO.lockView(super.getClientRequest(), id);

    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("operators", getTeamMembers(dto.getSprayTeam()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Team Sprays");
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void cancel(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    this.view(TeamSprayDTO.unlockView(getClientRequest(), dto.getSprayId()));
  }

  public void failCancel(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deleteConcrete();
      this.search();
    }
    catch (com.terraframe.mojo.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("operators", getTeamMembers(dto.getSprayTeam()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Team Sprays");
    render("editComponent.jsp");
  }

  public void search() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandDTO[] brands = InsecticideBrandDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);
    List<? extends SprayTeamDTO> teams = SprayTeamDTO.getAllInstances(clientRequest,
        SprayTeamDTO.TEAMID, true, 0, 0).getResultSet();

    req.setAttribute("methods", methods);
    req.setAttribute("method", SprayMethodDTO.MAIN_SPRAY.getName());
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("teams", teams);
    req.setAttribute("page_title", "Search for an Team Spray");

    render("searchComponent.jsp");
  }

  public void searchByParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod,
      SprayTeamDTO team) throws IOException, ServletException
  {

    try
    {
      validateParameters(brand, geoId, date, sprayMethod, team);

      SprayMethodDTO method = SprayMethodDTO.valueOf(sprayMethod);

      TeamSprayViewDTO dto = TeamSprayViewDTO.searchBySprayData(this.getClientRequest(), geoId, date,
          method, brand, team.getId());

      if (dto.hasConcrete())
      {
        view(dto);
      }
      else
      {
        req.setAttribute("page_title", "New Team Spray ");
        req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
        req.setAttribute("operators", getTeamMembers(team));
        req.setAttribute("item", dto);
        render("createComponent.jsp");
      }
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failDate = ( date == null ? null : date.toString() );

      this.failSearchByParameters(brand, geoId, failDate, sprayMethod, team);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failDate = ( date == null ? null : date.toString() );

      this.failSearchByParameters(brand, geoId, failDate, sprayMethod, team);
    }
  }
  
  private List<SprayOperatorDTO> getTeamMembers(SprayTeamDTO team)
  {
    List<SprayOperatorDTO> operators = new LinkedList<SprayOperatorDTO>();
    
    operators.addAll(team.getAllSprayTeamMembers());
    
    return operators;
  }


  private void validateParameters(InsecticideBrandDTO brand, String geoId, Date date,
      String sprayMethod, SprayTeamDTO team)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (brand == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredInsecticideBrandProblemDTO(clientRequest, req.getLocale()));
    }

    if (geoId == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredGeoIdProblemDTO(clientRequest, req.getLocale()));
    }

    if (date == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredSprayDateProblemDTO(clientRequest, req.getLocale()));
    }

    if (sprayMethod == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredSprayMethodProblemDTO(clientRequest, req.getLocale()));
    }

    if (team == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredSprayTeamProblemDTO(clientRequest, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  public void failSearchByParameters(InsecticideBrandDTO brand, String geoId, String date,
      String method, SprayTeamDTO operator) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandDTO[] brands = InsecticideBrandDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);
    List<? extends SprayTeamDTO> teams = SprayTeamDTO.getAllInstances(clientRequest,
        SprayTeamDTO.TEAMID, true, 0, 0).getResultSet();

    req.setAttribute("methods", methods);
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("teams", teams);
    req.setAttribute("page_title", "Search for an Team Spray");

    req.setAttribute("brand", brand);
    req.setAttribute("date", date);
    req.setAttribute("geoId", geoId);
    req.setAttribute("method", method);
    req.setAttribute("team", operator);
    req.setAttribute("page_title", "Search for an Team Spray");

    render("searchComponent.jsp");
  }
}
