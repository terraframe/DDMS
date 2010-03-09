package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.ResponseDTO;
import dss.vector.solutions.ResponseMasterDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class HouseholdController extends HouseholdControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/Household/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1239641309417L;

  public HouseholdController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(HouseholdViewDTO dto) throws IOException, ServletException
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

  public void failCreate(HouseholdViewDTO dto) throws IOException, ServletException
  {
    this.newInstance(dto);
  }

  public void delete(HouseholdViewDTO dto) throws IOException, ServletException
  {
    try
    {
      SurveyPointDTO surveyPoint = dto.getSurveyPoint();

      dto.deleteConcrete();

      new SurveyPointController(req, resp, false).view(surveyPoint.getId());
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

  public void failDelete(HouseholdViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(HouseholdDTO.getView(super.getClientRequest(), id));
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

  public void view(HouseholdViewDTO dto) throws IOException, ServletException
  {
    // go back to household view after entering person
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupReferences(dto);

    req.setAttribute("people", Arrays.asList(dto.getSurveyedPeople()));
    req.setAttribute("itns", Arrays.asList(dto.getItns()));
    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    new SurveyPointController(req, resp, isAsynchronous).viewAll();
  }

  public void cancel(HouseholdViewDTO dto) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();
      HouseholdDTO.unlock(request, dto.getConcreteId());

      this.view(dto.getConcreteId());
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

  public void failCancel(HouseholdViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    HouseholdQueryDTO query = HouseholdDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void newInstance(String surveyId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      // Ensure the use has permissions to create a new Household
      new HouseholdDTO(clientRequest);

      HouseholdViewDTO dto = new HouseholdViewDTO(clientRequest);
      dto.setSurveyPoint(SurveyPointDTO.get(clientRequest, surveyId));

      this.newInstance(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failNewInstance(surveyId);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failNewInstance(surveyId);
    }
  }

  private void newInstance(HouseholdViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    this.setupReferences(dto);

    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance(String surveyId) throws IOException, ServletException
  {
    new SurveyPointController(req, resp, isAsynchronous).view(surveyId);
  }

  public void update(HouseholdViewDTO dto) throws IOException, ServletException
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

  public void failUpdate(HouseholdViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      HouseholdViewDTO dto = HouseholdDTO.lockView(super.getClientRequest(), id);

      this.edit(dto);
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

  private void edit(HouseholdViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    this.setupReferences(dto);
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    HouseholdQueryDTO query = HouseholdDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  private void setupRequest()
  {
    req.setAttribute("hasBeenSprayed", this.getResponses(this.getClientSession().getRequest()));
  }

  private void setupReferences(HouseholdViewDTO dto)
  {
    req.setAttribute("windowType", AttributeUtil.getValue(HouseholdViewDTO.WINDOWTYPE, dto));
    req.setAttribute("wall", AttributeUtil.getValue(HouseholdViewDTO.WALL, dto));
    req.setAttribute("roof", AttributeUtil.getValue(HouseholdViewDTO.ROOF, dto));
  }

  private List<ResponseMasterDTO> getResponses(ClientRequestIF request)
  {
    List<ResponseMasterDTO> responses = ResponseDTO.items(request, ResponseDTO.YES, ResponseDTO.NO);
    for (ResponseMasterDTO response : dss.vector.solutions.ResponseDTO.allItems(request))
    {
      if (!responses.contains(response))
      {
        responses.add(response);
      }
    }
    return responses;
  }
}
