package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class TeamSprayController extends TeamSprayControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/TeamSpray/";

  public static final String LAYOUT           = "/layout.jsp";

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
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto);
      }
    }
  }

  public void failCreate(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  private void setupRequest(TeamSprayViewDTO dto)
  {
    ClientRequestIF request = this.getClientSession().getRequest();
    InsecticideBrandDTO brand = dto.getBrand();
    String geoId = dto.getGeoEntity().getGeoId();
    SprayTeamDTO sprayTeam = dto.getSprayTeam();

    req.setAttribute("brand", InsecticideBrandDTO.getView(request, brand.getId()));
    req.setAttribute("methods", SprayMethodDTO.allItems(request));
    req.setAttribute("brands", Arrays.asList(InsecticideBrandViewDTO.getIRSInsecticideBrands(request)));
    req.setAttribute("teams", Arrays.asList(SprayTeamDTO.findByLocation(request, geoId)));

    if (sprayTeam != null)
    {
      List<? extends TeamMemberDTO> leader = sprayTeam.getAllTeamLeader();

      if (leader.size() > 0)
      {
        String leaderId = leader.get(0).getId();

        req.setAttribute("leaderId", leaderId);
      }

      req.setAttribute("members", Arrays.asList(sprayTeam.getTeamMemberViews()));
      req.setAttribute("teamId", sprayTeam.getId());
    }
    else
    {
      req.setAttribute("members", new LinkedList<TeamMemberViewDTO>());
    }
  }

  public void update(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(TeamSprayDTO.getView(this.getClientRequest(), id));
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

  public void view(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    ClientRequestIF request = this.getClientSession().getRequest();
    InsecticideBrandDTO brand = dto.getBrand();

    this.setupReferences(dto);

    req.setAttribute("brand", InsecticideBrandDTO.getView(request, brand.getId()));
    req.setAttribute("item", dto);
    req.setAttribute("operators", this.buildOperatorsMap(dto));
    req.setAttribute("grid", new TeamSprayGridBuilder(request, dto).build());
    render("viewComponent.jsp");
  }

  private void setupReferences(TeamSprayViewDTO dto)
  {
    req.setAttribute("surfaceType", dto.getSurfaceType());
  }

  private JSONObject buildOperatorsMap(TeamSprayViewDTO view)
  {
    // Map between an entities id and display label
    Map<String, String> map = new HashMap<String, String>();

    SprayTeamDTO team = view.getSprayTeam();
    TeamMemberViewDTO[] members = team.getTeamMemberViews();

    for (TeamMemberViewDTO operator : members)
    {
      String key = operator.getActorId();
      String label = operator.getLabel() + " - " + operator.getMemberId();

      map.put(key, label);
    }

    return new JSONObject(map);

  }

  public void failView(String id) throws IOException, ServletException
  {
    this.search();
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      TeamSprayViewDTO dto = TeamSprayDTO.lockView(super.getClientRequest(), id);

      this.setupRequest(dto);
      this.setupReferences(dto);

      req.setAttribute("item", dto);
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

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void cancel(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    try
    {
      this.view(TeamSprayDTO.unlockView(getClientRequest(), dto.getConcreteId()));
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

  public void failCancel(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void delete(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deleteConcrete();
      this.search();
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failDelete(dto);
    }
  }

  public void failDelete(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void search() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getIRSInsecticideBrands(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);

    req.setAttribute("methods", methods);
    req.setAttribute("method", SprayMethodDTO.MAIN_SPRAY.getName());
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("teams", new LinkedList<SprayTeamDTO>());

    render("searchComponent.jsp");
  }

  public void searchByParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod, SprayTeamDTO team) throws IOException, ServletException
  {

    try
    {
      validateParameters(brand, geoId, date, sprayMethod, team);

      ClientRequestIF request = this.getClientRequest();
      SprayMethodDTO method = SprayMethodDTO.valueOf(sprayMethod);
      TeamSprayViewDTO dto = TeamSprayViewDTO.searchBySprayData(request, geoId, date, method, brand, team.getId());

      if (dto.hasConcrete())
      {
        view(dto);
      }
      else
      {
        // Ensure that the user has the ability to create a team spray
        new TeamSprayDTO(request);

        this.setupRequest(dto);
        this.setupReferences(dto);

        req.setAttribute("item", dto);
        render("createComponent.jsp");
      }
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        String failDate = ( date == null ? null : date.toString() );

        this.failSearchByParameters(brand, geoId, failDate, sprayMethod, team);
      }
    }
  }

  private void validateParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod, SprayTeamDTO team)
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

  public void failSearchByParameters(InsecticideBrandDTO brand, String geoId, String date, String method, SprayTeamDTO team) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getIRSInsecticideBrands(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);

    req.setAttribute("methods", methods);
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("brand", brand);
    req.setAttribute("date", date);
    req.setAttribute("method", method);
    req.setAttribute("team", team);

    if (geoId != null)
    {
      req.setAttribute("teams", Arrays.asList(SprayTeamDTO.findByLocation(clientRequest, geoId)));
      req.setAttribute("geoId", geoId);
    }
    else
    {
      req.setAttribute("teams", new LinkedList<SprayTeamDTO>());
    }

    render("searchComponent.jsp");
  }
}
