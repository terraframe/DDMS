package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.surveillance.IndividualCaseSymptomDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class IndividualInstanceController extends IndividualInstanceControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/IndividualInstance/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1254360075111L;

  public IndividualInstanceController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualInstanceQueryDTO query = IndividualInstanceDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void update(IndividualInstanceDTO dto, IndividualCaseSymptomDTO[] symptoms) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(symptoms);
      this.view(dto.getId());
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failUpdate(dto, symptoms);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failUpdate(dto, symptoms);
    }
  }

  public void failUpdate(IndividualInstanceDTO dto, IndividualCaseSymptomDTO[] symptoms) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("symptoms", Arrays.asList(symptoms));
    render("editComponent.jsp");
  }

  public void create(IndividualInstanceDTO dto, IndividualCaseSymptomDTO[] symptoms) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(symptoms);
      this.view(dto.getId());
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failCreate(dto, symptoms);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failCreate(dto, symptoms);
    }
  }
  
  public void failCreate(IndividualInstanceDTO dto, IndividualCaseSymptomDTO[] symptoms) throws IOException, ServletException
  {
    renderCreate(dto, symptoms, dto.getValue(IndividualInstanceDTO.INDIVIDUALCASE));
  }
  
  public void createWithCase(IndividualInstanceDTO dto, IndividualCaseDTO newCase, String personId, IndividualCaseSymptomDTO[] symptoms) throws IOException, ServletException
  {
    try
    {
      newCase.applyWithPersonId(personId);
      this.create(dto, symptoms);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failCreateWithCase(dto, newCase, personId, symptoms);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failCreateWithCase(dto, newCase, personId, symptoms);
    }
  }
  
  public void failCreateWithCase(IndividualInstanceDTO dto, IndividualCaseDTO newCase, String personId, IndividualCaseSymptomDTO[] symptoms) throws IOException, ServletException
  {
    renderCreateWithCase(dto, newCase, personId);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualInstanceQueryDTO query = IndividualInstanceDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void edit(String id) throws IOException, ServletException
  {
    IndividualInstanceDTO dto = IndividualInstanceDTO.lock(super.getClientRequest(), id);
    renderEdit(dto);
  }

  private void renderEdit(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    req.setAttribute("symptoms", Arrays.asList(dto.getSymptoms()));
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void cancel(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void newInstance(String caseId) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualInstanceDTO dto = new IndividualInstanceDTO(clientRequest);
    dto.setValue(IndividualInstanceDTO.INDIVIDUALCASE, caseId);
    renderCreate(dto, dto.getSymptoms(), caseId);
  }
  
  public void newInstanceWithCase(IndividualCaseDTO newCase, String personId) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualInstanceDTO dto = new IndividualInstanceDTO(clientRequest);
    renderCreateWithCase(dto, newCase, personId);
  }
  
  private void renderCreateWithCase(IndividualInstanceDTO dto, IndividualCaseDTO newCase, String personId) throws IOException, ServletException
  {
    prepareCreateReq(dto, dto.getSymptoms());
    req.setAttribute("newCase", newCase);
    req.setAttribute("personId", personId);
    render("createWithCase.jsp");
  }

  private void renderCreate(IndividualInstanceDTO dto, IndividualCaseSymptomDTO[] symptoms, String caseId) throws IOException, ServletException
  {
    prepareCreateReq(dto, symptoms);
    req.setAttribute("caseId", caseId);
    render("createComponent.jsp");
  }

  private void prepareCreateReq(IndividualInstanceDTO dto, IndividualCaseSymptomDTO[] symptoms)
  {
    req.setAttribute("item", dto);
    req.setAttribute("symptoms", Arrays.asList(symptoms));
  }

  public void failNewInstance(String caseId) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void delete(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    try
    {
      String caseId = dto.getIndividualCase().getId();
      
      dto.delete();

      new IndividualCaseController(req, resp, isAsynchronous).view(caseId);
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

  public void failDelete(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    renderEdit(dto);
  }

  public void view(String id) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    
    IndividualInstanceDTO dto = IndividualInstanceDTO.get(super.getClientRequest(), id);
    req.setAttribute("item", dto);
    req.setAttribute("symptoms", Arrays.asList(dto.getSymptoms()));
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }
}
