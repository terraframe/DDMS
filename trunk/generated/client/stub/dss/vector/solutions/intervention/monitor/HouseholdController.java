package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

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

  public void create(HouseholdDTO dto, HouseholdNetDTO[] nets) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failCreate(dto, nets);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failCreate(dto, nets);
    }
  }

  public void failCreate(HouseholdDTO dto, HouseholdNetDTO[] nets) throws IOException, ServletException
  {
    this.newInstance(dto, nets);
  }

  public void delete(HouseholdDTO dto) throws IOException, ServletException
  {
    try
    {
      SurveyPointDTO surveyPoint = dto.getSurveyPoint();

      dto.delete();

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

  public void failDelete(HouseholdDTO dto) throws IOException, ServletException
  {
    this.edit(dto, dto.getHouseholdNets());
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(HouseholdDTO.get(super.getClientRequest(), id));
  }

  public void view(HouseholdDTO dto) throws IOException, ServletException
  {
    // go back to household view after entering person
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    ClientRequestIF request = this.getClientSession().getRequest();
    List<PersonViewDTO> people = new LinkedList<PersonViewDTO>();

    for (PersonDTO person : dto.getAllPersons())
    {
      people.add(PersonDTO.getView(request, person.getId()));
    }

    this.setupReferences(dto);

    req.setAttribute("people", people);
    req.setAttribute("nets", Arrays.asList(dto.getHouseholdNets()));
    req.setAttribute("item", dto);
    
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void cancel(HouseholdDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(HouseholdDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
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
    ClientRequestIF clientRequest = super.getClientRequest();

    HouseholdDTO dto = new HouseholdDTO(clientRequest);
    dto.setSurveyPoint(SurveyPointDTO.get(clientRequest, surveyId));

    this.newInstance(dto, dto.getHouseholdNets());
  }

  private void newInstance(HouseholdDTO dto, HouseholdNetDTO[] nets) throws IOException, ServletException
  {
    this.setupRequest(nets);
    this.setupReferences(dto);

    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance(String surveyId) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void update(HouseholdDTO dto, HouseholdNetDTO[] nets) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failUpdate(dto, nets);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failUpdate(dto, nets);
    }
  }

  public void failUpdate(HouseholdDTO dto, HouseholdNetDTO[] nets) throws IOException, ServletException
  {
    this.edit(dto, nets);
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      HouseholdDTO dto = HouseholdDTO.lock(super.getClientRequest(), id);
      HouseholdNetDTO[] nets = dto.getHouseholdNets();
      
      this.edit(dto, nets);
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

  private void edit(HouseholdDTO dto, HouseholdNetDTO[] nets) throws IOException, ServletException
  {
    this.setupRequest(nets);
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
  
  private void setupRequest(HouseholdNetDTO[] nets)
  {
    req.setAttribute("nets", Arrays.asList(nets));
  }
  
  private void setupReferences(HouseholdDTO dto)
  {
    req.setAttribute("windowType", dto.getWindowType());
    req.setAttribute("wall", dto.getWall());
    req.setAttribute("roof", dto.getRoof());
  }

}
