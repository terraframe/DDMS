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

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.PersonDTO;
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
    this.setupRequest(dto);
    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  private void setupRequest(TeamSprayViewDTO dto)
  {
    ClientRequestIF request = this.getClientSession().getRequest();
    InsecticideBrandDTO brand = dto.getBrand();
    String geoId = dto.getGeoEntity().getGeoId();
    
    req.setAttribute("brand", InsecticideBrandDTO.getView(request, brand.getId()));
    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(request));
    req.setAttribute("operators", Arrays.asList(dto.getSprayTeam().getTeamMemberViews()));
    req.setAttribute("methods", SprayMethodDTO.allItems(request));
    req.setAttribute("brands", Arrays.asList(InsecticideBrandViewDTO.getAll(request)));
    req.setAttribute("teams", Arrays.asList(SprayTeamDTO.findByLocation(request, geoId)));    
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
    this.setupRequest(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(TeamSprayDTO.getView(this.getClientRequest(), id));
  }

  public void view(TeamSprayViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getSprayId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    ClientRequestIF request = this.getClientSession().getRequest();
    InsecticideBrandDTO brand = dto.getBrand();
    
    req.setAttribute("brand", InsecticideBrandDTO.getView(request, brand.getId()));
    req.setAttribute("item", dto);
    req.setAttribute("status", dto.getStatus());
    req.setAttribute("operators", this.buildOperatorsMap(dto));
    render("viewComponent.jsp");
  }

  private JSONObject buildOperatorsMap(TeamSprayViewDTO view)
  {
    // Map between an entities id and display label
    Map<String, String> map = new HashMap<String, String>();

    SprayTeamDTO team = view.getSprayTeam();
    SprayOperatorDTO[] members = team.getTeamMembers();

    for (SprayOperatorDTO operator : members)
    {
      PersonDTO person = operator.getPerson();
      String key = operator.getId();
      String label = operator.getOperatorId() + " - " + person.getFirstName() + ", "
          + person.getLastName();

      map.put(key, label);
    }

    return new JSONObject(map);

  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      TeamSprayViewDTO dto = TeamSprayDTO.lockView(super.getClientRequest(), id);

      this.setupRequest(dto);
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
    req.setAttribute("teams", new LinkedList<SprayTeamDTO>());

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
        this.setupRequest(dto);

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
      String method, SprayTeamDTO team) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);

    req.setAttribute("methods", methods);
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("brand", brand);
    req.setAttribute("date", date);
    req.setAttribute("method", method);
    req.setAttribute("team", team);
    
    if(geoId != null)
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
