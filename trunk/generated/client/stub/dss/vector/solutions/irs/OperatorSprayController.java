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

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class OperatorSprayController extends OperatorSprayControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/OperatorSpray/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1240853382362L;

  public OperatorSprayController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(OperatorSprayViewDTO dto) throws IOException, ServletException
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

  public void failCreate(OperatorSprayViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);

    req.setAttribute("item", dto);

    render("createComponent.jsp");
  }

  public void update(OperatorSprayViewDTO dto) throws IOException, ServletException
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

  public void failUpdate(OperatorSprayViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);
    
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(OperatorSprayDTO.getView(this.getClientRequest(), id));
  }

  public void view(OperatorSprayViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getSprayId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    ClientRequestIF request = this.getClientSession().getRequest();

    InsecticideBrandDTO brand = dto.getBrand();

    this.setupReferences(dto);
    
    req.setAttribute("brand", InsecticideBrandDTO.getView(request, brand.getId()));
    req.setAttribute("status", dto.getStatus());
    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  private void setupReferences(OperatorSprayViewDTO dto)
  {
    req.setAttribute("surfaceType", dto.getSurfaceType());
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      OperatorSprayViewDTO dto = OperatorSprayDTO.lockView(super.getClientRequest(), id);

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

  public void cancel(OperatorSprayViewDTO dto) throws IOException, ServletException
  {
    this.view(OperatorSprayDTO.unlockView(getClientRequest(), dto.getSprayId()));
  }

  public void failCancel(OperatorSprayViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(OperatorSprayViewDTO dto) throws IOException, ServletException
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

  public void failDelete(OperatorSprayViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("surfaceTypes", SurfaceTypeDTO.allItems(this.getClientSession().getRequest()));
    req.setAttribute("operators", getTeamMembers(dto.getSprayOperator()));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void search() throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.checkURL(this.getClass().getSimpleName(), "search");

    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);

    req.setAttribute("methods", methods);
    req.setAttribute("method", SprayMethodDTO.MAIN_SPRAY.getName());
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("teams", new LinkedList<SprayTeamDTO>());
    req.setAttribute("operators", new LinkedList<SprayOperatorViewDTO>());

    render("searchComponent.jsp");
  }

  public void searchByParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod, String teamId, SprayOperatorDTO operator) throws IOException, ServletException
  {
    try
    {
      validateParameters(brand, geoId, date, sprayMethod, operator);

      ClientRequestIF request = this.getClientRequest();      
      SprayMethodDTO method = SprayMethodDTO.valueOf(sprayMethod);
      OperatorSprayViewDTO dto = OperatorSprayViewDTO.searchBySprayData(request, geoId, date, method, brand, operator.getId());

      if (dto.hasConcrete())
      {
        this.view(dto);
      }
      else
      {
        //Ensure that the user has the ability to create an operator spray
        new OperatorSprayDTO(request);
        
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

      this.failSearchByParameters(brand, geoId, failDate, sprayMethod, teamId, operator);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failDate = ( date == null ? null : date.toString() );

      this.failSearchByParameters(brand, geoId, failDate, sprayMethod, teamId, operator);
    }
  }

  private void setupRequest(OperatorSprayViewDTO dto)
  {
    ClientRequestIF request = this.getClientSession().getRequest();

    InsecticideBrandDTO brand = dto.getBrand();
    String geoId = dto.getGeoEntity().getGeoId();

    List<? extends SprayTeamDTO> selectedTeam = dto.getSprayOperator().getAllSprayTeam();

    req.setAttribute("brand", InsecticideBrandDTO.getView(request, brand.getId()));
    req.setAttribute("methods", SprayMethodDTO.allItems(request));
    req.setAttribute("brands", Arrays.asList(InsecticideBrandViewDTO.getAll(request)));
    req.setAttribute("teams", Arrays.asList(SprayTeamDTO.findByLocation(request, geoId)));

    if(selectedTeam.size() > 0)
    {
      SprayTeamDTO sprayTeam = selectedTeam.get(0);

      req.setAttribute("operators", Arrays.asList(sprayTeam.getTeamMemberViews()));
      req.setAttribute("teamId", sprayTeam.getId());
    }     
    else
    {      
      req.setAttribute("operators", new LinkedList<SprayOperatorViewDTO>());
    }
  }

  private List<SprayOperatorDTO> getTeamMembers(SprayOperatorDTO operator)
  {
    List<? extends SprayTeamDTO> teams = operator.getAllSprayTeam();
    List<SprayOperatorDTO> operators = new LinkedList<SprayOperatorDTO>();

    for (SprayTeamDTO team : teams)
    {
      operators.addAll(team.getAllSprayTeamMembers());
    }
    return operators;
  }

  private void validateParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod, SprayOperatorDTO operator)
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

    if (operator == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredSprayOperatorProblemDTO(clientRequest, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  public void failSearchByParameters(InsecticideBrandDTO brand, String geoId, String date, String method, String teamId, SprayOperatorDTO operator) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);
    
    req.setAttribute("methods", methods);
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("brand", brand);
    req.setAttribute("method", method);
    req.setAttribute("date", date);
    req.setAttribute("operator", operator);
    
    if(geoId != null)
    {
      req.setAttribute("teams", Arrays.asList(SprayTeamDTO.findByLocation(clientRequest, geoId)));            
      req.setAttribute("geoId", geoId);
    }
    else
    {
      req.setAttribute("teams", new LinkedList<SprayTeamDTO>());      
    }
    
    if(teamId != null)
    {
      req.setAttribute("operators", Arrays.asList(SprayTeamDTO.getTeamMemberViews(clientRequest, teamId)));      
      req.setAttribute("teamId", teamId);      
    }
    else
    {
      req.setAttribute("operators", new LinkedList<SprayOperatorViewDTO>());      
    }

    render("searchComponent.jsp");
  }

}
