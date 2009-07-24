package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class HouseholdController extends HouseholdControllerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/Household/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1239641309417L;

  public HouseholdController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void create(HouseholdDTO dto, HouseholdNetDTO[] nets) throws java.io.IOException,
      javax.servlet.ServletException
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

  public void failCreate(HouseholdDTO dto, HouseholdNetDTO[] nets) throws java.io.IOException,
      javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    req.setAttribute("windowType", WindowTypeDTO.allItems(clientRequest));
    req.setAttribute("walls", Arrays.asList(WallViewDTO.getAll(clientRequest)));
    req.setAttribute("roofs", Arrays.asList(RoofViewDTO.getAll(clientRequest)));
    req.setAttribute("item", dto);
    req.setAttribute("nets", Arrays.asList(nets));
    render("createComponent.jsp");
  }

  public void delete(HouseholdDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failDelete(HouseholdDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();
    req.setAttribute("windowType", WindowTypeDTO.allItems(clientRequest));
    req.setAttribute("walls", Arrays.asList(WallViewDTO.getAll(clientRequest)));
    req.setAttribute("roofs", Arrays.asList(RoofViewDTO.getAll(clientRequest)));
    req.setAttribute("item", dto);
    req.setAttribute("nets", Arrays.asList(dto.getHouseholdNets()));
    render("editComponent.jsp");
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(HouseholdDTO.get(super.getClientRequest(), id));
  }

  public void view(HouseholdDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    // go back to household view after entering person
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    ClientRequestIF request = this.getClientSession().getRequest();
    List<PersonViewDTO> people = new LinkedList<PersonViewDTO>();
    
    for(PersonDTO person : dto.getAllPersons())
    {
      people.add(PersonDTO.getView(request, person.getId()));
    }

    req.setAttribute("item", dto);
    req.setAttribute("people", people);
    req.setAttribute("nets", Arrays.asList(dto.getHouseholdNets()));
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void cancel(HouseholdDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(HouseholdDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.edit(dto.getId());
  }

  public void viewPage(java.lang.String sortAttribute, java.lang.Boolean isAscending,
      java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    HouseholdQueryDTO query = HouseholdDTO.getAllInstances(clientRequest, sortAttribute, isAscending,
        pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(java.lang.String sortAttribute, java.lang.String isAscending,
      java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException,
      javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void newInstance(String surveyId) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    HouseholdDTO dto = new HouseholdDTO(clientRequest);
    dto.setSurveyPoint(SurveyPointDTO.get(clientRequest, surveyId));

    HouseholdNetDTO[] nets = dto.getHouseholdNets();

    req.setAttribute("windowType", WindowTypeDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("nets", Arrays.asList(nets));
    req.setAttribute("walls", Arrays.asList(WallViewDTO.getAll(clientRequest)));
    req.setAttribute("roofs", Arrays.asList(RoofViewDTO.getAll(clientRequest)));
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance(String surveyId) throws java.io.IOException,
      javax.servlet.ServletException
  {
    this.viewAll();
  }

  public void update(HouseholdDTO dto, HouseholdNetDTO[] nets) throws java.io.IOException,
      javax.servlet.ServletException
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

  public void failUpdate(HouseholdDTO dto, HouseholdNetDTO[] nets) throws java.io.IOException,
      javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();
    req.setAttribute("windowType", WindowTypeDTO.allItems(clientRequest));
    req.setAttribute("walls", Arrays.asList(WallViewDTO.getAll(clientRequest)));
    req.setAttribute("roofs", Arrays.asList(RoofViewDTO.getAll(clientRequest)));
    req.setAttribute("item", dto);
    req.setAttribute("nets", Arrays.asList(nets));

    render("editComponent.jsp");
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      edit(HouseholdDTO.lock(super.getClientRequest(), id));
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

  private void edit(HouseholdDTO dto) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();
    req.setAttribute("windowType", WindowTypeDTO.allItems(clientRequest));
    req.setAttribute("walls", Arrays.asList(WallViewDTO.getAll(clientRequest)));
    req.setAttribute("roofs", Arrays.asList(RoofViewDTO.getAll(clientRequest)));
    req.setAttribute("nets", Arrays.asList(HouseholdDTO.getHouseholdNets(clientRequest, dto.getId())));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    HouseholdQueryDTO query = HouseholdDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }
}
