package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
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

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.surveillance.RequiredDiagnosisDateProblemDTO;
import dss.vector.solutions.util.DefaultConverter;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class IndividualCaseController extends IndividualCaseControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/IndividualCase/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1254360074929L;

  public IndividualCaseController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualCaseQueryDTO query = IndividualCaseDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void search(Date diagnosisDate, Date caseReportDate, String personId) throws IOException, ServletException
  {
    try
    {
      validateSearchCriteria(diagnosisDate, caseReportDate, personId);
      
      ClientRequestIF clientRequest = getClientRequest();
      IndividualCaseDTO individualCase = IndividualCaseDTO.searchForExistingCase(clientRequest, diagnosisDate, personId);
      if (individualCase.isNewInstance())
      {
        individualCase.setDiagnosisDate(diagnosisDate);
        individualCase.setCaseReportDate(caseReportDate);
        renderCreate(individualCase, personId);
      }
      else
      {
        renderView(individualCase);
      }
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      
      String failDiagnosis = (diagnosisDate != null ? new DefaultConverter(Date.class).format(diagnosisDate, req.getLocale()): null);
      String failCase = (caseReportDate != null ? new DefaultConverter(Date.class).format(caseReportDate, req.getLocale()): null);
      
      this.failSearch(failDiagnosis, failCase, personId);
    }
    catch (Throwable t)
    {
      String failDiagnosis = (diagnosisDate != null ? new DefaultConverter(Date.class).format(diagnosisDate, req.getLocale()): null);
      String failCase = (caseReportDate != null ? new DefaultConverter(Date.class).format(caseReportDate, req.getLocale()): null);
      
      this.failSearch(failDiagnosis, failCase, personId);
    }
  }

  private void validateSearchCriteria(Date diagnosisDate, Date caseReportDate, String personId)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (diagnosisDate == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredDiagnosisDateProblemDTO(clientRequest, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  private void renderCreate(IndividualCaseDTO individualCase, String personId) throws IOException, ServletException
  {
    req.setAttribute("individualCase", individualCase);
    req.setAttribute("personId", personId);
    render("createComponent.jsp");
  }

  @Override
  public void failSearch(String diagnosisDate, String caseReportDate, String personId) throws IOException, ServletException
  {
    req.setAttribute("diagnosisDate", diagnosisDate);
    req.setAttribute("caseReportDate", caseReportDate);
    
    if(personId != null && !personId.equals(""))
    {
      req.setAttribute("person", PersonDTO.getView(this.getClientRequest(), personId));
    }

    renderSearch(new IndividualCaseDTO(getClientRequest()));
  }

  public void cancel(IndividualCaseDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(IndividualCaseDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void view(String id) throws IOException, ServletException
  {
    renderView(IndividualCaseDTO.get(super.getClientRequest(), id));
  }

  private void renderView(IndividualCaseDTO individualCaseDTO) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", individualCaseDTO.getId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    req.setAttribute("query", individualCaseDTO.getInstances());
    req.setAttribute("item", individualCaseDTO);

    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void delete(IndividualCaseDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();

      this.newInstance();
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

  public void failDelete(IndividualCaseDTO dto) throws IOException, ServletException
  {
    renderEdit(dto);
  }

  public void edit(String id) throws IOException, ServletException
  {
    IndividualCaseDTO dto = IndividualCaseDTO.lock(super.getClientRequest(), id);
    renderEdit(dto);
  }

  private void renderEdit(IndividualCaseDTO dto) throws IOException, ServletException
  {
    req.setAttribute("individualCase", dto);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualCaseQueryDTO query = IndividualCaseDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void update(IndividualCaseDTO dto) throws IOException, ServletException
  {
    try
    {
      if (dto.getProbableSource() == null)
      {
        dto.setProbableSource(dto.getResidence());
      }

      dto.apply();

      ClientRequestIF request = dto.getRequest();

      ErrorUtility.prepareInformation(request.getInformation(), req);

      this.view(dto.getId());
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

  public void failUpdate(IndividualCaseDTO dto) throws IOException, ServletException
  {
    renderEdit(dto);
  }

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualCaseDTO dto = new IndividualCaseDTO(clientRequest);
    renderSearch(dto);
  }

  private void renderSearch(IndividualCaseDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("searchComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(IndividualCaseDTO dto, String personId) throws IOException, ServletException
  {
    try
    {
      dto.applyWithPersonId(personId);

      ClientRequestIF request = dto.getRequest();

      ErrorUtility.prepareInformation(request.getInformation(), req);

      renderView(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failCreate(dto, personId);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failCreate(dto, personId);
    }
  }

  public void failCreate(IndividualCaseDTO dto, String personId) throws IOException, ServletException
  {
    renderCreate(dto, personId);
  }
}
