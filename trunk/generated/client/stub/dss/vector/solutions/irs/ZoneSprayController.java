package dss.vector.solutions.irs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.json.JSONObject;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.Halp;
import dss.vector.solutions.util.RedirectUtility;

public class ZoneSprayController extends ZoneSprayControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/ZoneSpray/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1240860686933L;

  public ZoneSprayController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(ZoneSprayViewDTO dto) throws IOException, ServletException
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

  public void failCreate(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void update(ZoneSprayViewDTO dto) throws IOException, ServletException
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

  public void failUpdate(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  private void setupRequest(ZoneSprayViewDTO dto)
  {
    ClientRequestIF request = this.getClientSession().getRequest();
    InsecticideBrandDTO brand = dto.getBrand();

    req.setAttribute("brand", InsecticideBrandDTO.getView(request, brand.getId()));
    req.setAttribute("methods", SprayMethodDTO.allItems(request));
    req.setAttribute("brands", Arrays.asList(InsecticideBrandViewDTO.getAll(request)));
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(ZoneSprayDTO.getView(this.getClientRequest(), id));
  }

  public void view(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getSprayId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    ClientRequestIF request = this.getClientRequest();

    TeamSprayStatusViewDTO[] status = dto.getStatus();
    SprayTeamDTO[] teams = SprayTeamDTO.findByLocation(request, dto.getGeoEntity().getGeoId());
    InsecticideBrandDTO brand = dto.getBrand();

    JSONObject teamMap = buildTeamsMap(teams);
    String operators = buildOperatorsMap(teams);
    
    this.setupReferences(dto);

    req.setAttribute("brand", InsecticideBrandDTO.getView(request, brand.getId()));
    req.setAttribute("teams", teamMap);
    req.setAttribute("operators", operators);
    req.setAttribute("status", status);
    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  private void setupReferences(ZoneSprayViewDTO dto)
  {
    req.setAttribute("surfaceType", dto.getSurfaceType());
  }

  private JSONObject buildTeamsMap(SprayTeamDTO[] teams)
  {
    // Map between an entities id and display label
    Map<String, String> map = new LinkedHashMap<String, String>();

    for (SprayTeamDTO team : teams)
    {
      map.put(team.getId(), team.getTeamId());
    }

    return new JSONObject(map);
  }

  private String buildOperatorsMap(SprayTeamDTO[] teams)
  {
    // IMPORTANT: Even though this method essentially returns a JSONObject, it
    // cannot actually use the JSONObject class because JSONObject does not
    // preserve order. We need to preserve order such that the Team Leader is
    // the first operator in each team list.

    // Build the map of possible team leaders for every spray team
    List<String> operators = new LinkedList<String>();

    for (SprayTeamDTO team : teams)
    {
      // Map between an entities id and display label
      List<String> list = new LinkedList<String>();

      SprayOperatorViewDTO[] members = team.getTeamMemberViews();

      for (SprayOperatorViewDTO operator : members)
      {
        String key = operator.getActorId();
        String label = operator.getFirstName() + ", " + operator.getLastName() + " - " + operator.getOperatorId();

        list.add("\"" + key + "\":\"" + label + "\"");
      }

      operators.add("\"" + team.getId() + "\":" + "{" + Halp.join(list) + "}");
    }

    return "{" + Halp.join(operators) + "}";
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      ZoneSprayViewDTO dto = ZoneSprayDTO.lockView(super.getClientRequest(), id);

      this.setupRequest(dto);
      this.setupReferences(dto);
      
      req.setAttribute("item", dto);
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

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void cancel(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    this.view(ZoneSprayDTO.unlockView(getClientRequest(), dto.getSprayId()));
  }

  public void failCancel(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(ZoneSprayViewDTO dto) throws IOException, ServletException
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

  public void failDelete(ZoneSprayViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void search() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);

    req.setAttribute("methods", methods);
    req.setAttribute("method", SprayMethodDTO.MAIN_SPRAY.getName());
    req.setAttribute("brands", Arrays.asList(brands));

    render("searchComponent.jsp");
  }

  public void searchByParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod) throws IOException, ServletException
  {

    try
    {
      validateParameters(brand, geoId, date, sprayMethod);

      SprayMethodDTO method = SprayMethodDTO.valueOf(sprayMethod);

      ZoneSprayViewDTO dto = ZoneSprayViewDTO.searchBySprayData(this.getClientRequest(), geoId, date, method, brand);

      if (dto.hasConcrete())
      {
        this.view(dto);
      }
      else
      {
        this.setupRequest(dto);
        this.setupReferences(dto);

        req.setAttribute("item", dto);
        render("createComponent.jsp");
      }
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failDate = ( date == null ? null : date.toString() );

      this.failSearchByParameters(brand, geoId, failDate, sprayMethod);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failDate = ( date == null ? null : date.toString() );

      this.failSearchByParameters(brand, geoId, failDate, sprayMethod);
    }
  }

  private void validateParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod)
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

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  public void failSearchByParameters(InsecticideBrandDTO brand, String geoId, String date, String method) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);

    req.setAttribute("methods", methods);
    req.setAttribute("brands", Arrays.asList(brands));

    req.setAttribute("brand", brand);
    req.setAttribute("date", date);
    req.setAttribute("geoId", geoId);
    req.setAttribute("method", method);

    render("searchComponent.jsp");
  }
}
