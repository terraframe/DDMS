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

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class OperatorSprayController extends OperatorSprayControllerBase implements com.runwaysdk.generation.loader.Reloadable
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
    try
    {
      this.view(OperatorSprayDTO.getView(this.getClientRequest(), id));
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failView(id);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failView(id);
    }
  }

  public void view(OperatorSprayViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    ClientRequestIF request = this.getClientSession().getRequest();

    InsecticideBrandDTO brand = dto.getBrand();

    this.setupReferences(dto);
    
    req.setAttribute("grid", this.getGrid(dto, request));
    req.setAttribute("brand", InsecticideBrandDTO.getView(request, brand.getId()));
    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  private ViewDataGrid getGrid(OperatorSprayViewDTO dto, ClientRequestIF request)
  {
    HouseholdSprayStatusViewDTO view = new HouseholdSprayStatusViewDTO(request);
    view.setValue(HouseholdSprayStatusViewDTO.SPRAY, dto.getConcreteId());

    HouseholdSprayStatusViewDTO[] data = dto.getStatus();
    
    // If the order of these attributes are changed, you need to change the javascript indexes at the bottom!
    String[] keys = {"ConcreteId", "Spray", "HouseholdId", "StructureId", "Households", "Structures",
         "SprayedHouseholds", "SprayedStructures", "PrevSprayedHouseholds", "PrevSprayedStructures",
         "Rooms", "SprayedRooms", "People", "BedNets", "RoomsWithBedNets", "Locked", "Refused", "Other"};

    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
    map.put("ConcreteId", new ColumnSetup(true, false));
    map.put("Spray", new ColumnSetup(true, false));
    map.put("Households", new ColumnSetup(true, false));    
    map.put("Structures", new ColumnSetup(true, false));
    map.put("SprayedHouseholds", new ColumnSetup(false, true, "validateValue", null, null));    
    map.put("SprayedStructures", new ColumnSetup(false, true, "validateValue", null, null));    
    map.put("PrevSprayedHouseholds", new ColumnSetup(false, true, "validateValue", null, null));    
    map.put("PrevSprayedStructures", new ColumnSetup(false, true, "validateValue", null, null));
    
    return new ViewDataGrid(view, map, keys, data);
  }

  private void setupReferences(OperatorSprayViewDTO dto)
  {
    req.setAttribute("surfaceType", dto.getSurfaceType());
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.search();
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
    try
    {
      this.view(OperatorSprayDTO.unlockView(getClientRequest(), dto.getConcreteId()));
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

  public void failCancel(OperatorSprayViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void delete(OperatorSprayViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deleteConcrete();

      this.search();
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failDelete(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failDelete(dto);
    }
  }

  public void failDelete(OperatorSprayViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest(dto);
    this.setupReferences(dto);

    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void search() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    InsecticideBrandViewDTO[] brands = InsecticideBrandViewDTO.getAll(clientRequest);
    List<SprayMethodMasterDTO> methods = SprayMethodDTO.allItems(clientRequest);

    req.setAttribute("methods", methods);
    req.setAttribute("brands", Arrays.asList(brands));
    req.setAttribute("item", new OperatorSprayViewDTO(clientRequest));

    if (req.getAttribute("teams") == null)
    {
      req.setAttribute("teams", new LinkedList<SprayTeamDTO>());
    }

    if (req.getAttribute("operators") == null)
    {
      req.setAttribute("operators", new LinkedList<TeamMemberViewDTO>());
    }

    if (req.getAttribute("method") == null)
    {
      req.setAttribute("method", SprayMethodDTO.MAIN_SPRAY.getName());
    }

    render("searchComponent.jsp");
  }

  @Override
  public void searchByParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod, String teamId, TeamMemberDTO operator) throws IOException, ServletException
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
        dto.setValue(OperatorSprayViewDTO.SPRAYTEAM, teamId);

        // Ensure that the user has the ability to create an operator spray
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

    SprayTeamDTO sprayTeam = (SprayTeamDTO) AttributeUtil.getValue(OperatorSprayViewDTO.SPRAYTEAM, dto);

    req.setAttribute("brand", InsecticideBrandDTO.getView(request, brand.getId()));
    req.setAttribute("methods", SprayMethodDTO.allItems(request));
    req.setAttribute("brands", Arrays.asList(InsecticideBrandViewDTO.getAll(request)));
    req.setAttribute("teams", Arrays.asList(SprayTeamDTO.findByLocation(request, geoId)));

    String operatorId = dto.getValue(OperatorSprayViewDTO.SPRAYOPERATOR);

    if (operatorId != null && !operatorId.equals(""))
    {
      req.setAttribute("operator", TeamMemberDTO.getView(request, operatorId));
    }

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

  private void validateParameters(InsecticideBrandDTO brand, String geoId, Date date, String sprayMethod, TeamMemberDTO operator)
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

  public void failSearchByParameters(InsecticideBrandDTO brand, String geoId, String date, String method, String teamId, TeamMemberDTO operator) throws IOException, ServletException
  {

    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    req.setAttribute("brand", brand);
    req.setAttribute("method", method);
    req.setAttribute("date", date);

    if (operator != null)
    {
      req.setAttribute("operator", operator.getView());
    }

    if (geoId != null)
    {
      req.setAttribute("teams", Arrays.asList(SprayTeamDTO.findByLocation(clientRequest, geoId)));
      req.setAttribute("geoId", geoId);
    }

    if (teamId != null)
    {
      req.setAttribute("operators", Arrays.asList(SprayTeamDTO.getTeamMemberViews(clientRequest, teamId)));
      req.setAttribute("teamId", teamId);
    }

    this.search();
  }

}
