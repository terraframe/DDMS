package dss.vector.solutions.intervention.monitor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.util.ErrorUtility;

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

  public void update(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
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

  public void failUpdate(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    /* FIXME MO REFACTOR
    req.setAttribute("labTest", Arrays.asList(DiagnosticGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("patientCategory", Arrays.asList(PatientGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("referralReason", Arrays.asList(ReferralGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("treatment", Arrays.asList(TreatmentGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
    */
  }

  public void create(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
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

  public void failCreate(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    renderCreate(dto, dto.getValue(IndividualInstanceDTO.INDIVIDUALCASE));
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
  {/* FIXME MO REFACTOR
    IndividualInstanceDTO dto = IndividualInstanceDTO.lock(super.getClientRequest(), id);
    req.setAttribute("labTest", Arrays.asList(DiagnosticGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("patientCategory", Arrays.asList(PatientGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("referralReason", Arrays.asList(ReferralGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("treatment", Arrays.asList(TreatmentGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
    */
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
    renderCreate(dto, caseId);
  }

  private void renderCreate(IndividualInstanceDTO dto, String caseId) throws IOException, ServletException
  {
    /* FIXME MO REFACTOR
    ClientRequestIF request = super.getClientSession().getRequest();
    req.setAttribute("labTest", Arrays.asList(DiagnosticGridDTO.getAll(request)));
    req.setAttribute("patientCategory", Arrays.asList(PatientGridDTO.getAll(request)));
    req.setAttribute("referralReason", Arrays.asList(ReferralGridDTO.getAll(request)));
    req.setAttribute("treatment", Arrays.asList(TreatmentGridDTO.getAll(request)));
    req.setAttribute("caseId", caseId);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
    */
  }

  public void failNewInstance(String caseId) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void delete(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
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
    /* FIXME MO REFACTOR
    req.setAttribute("labTest", Arrays.asList(DiagnosticGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("patientCategory", Arrays.asList(PatientGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("referralReason", Arrays.asList(ReferralGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("treatment", Arrays.asList(TreatmentGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
    */
  }

  public void view(String id) throws IOException, ServletException
  {
    /* FIXME MO REFACTOR
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", id);
    utility.checkURL(this.getClass().getSimpleName(), "view");
    ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("labTest", Arrays.asList(DiagnosticGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("patientCategory", Arrays.asList(PatientGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("referralReason", Arrays.asList(ReferralGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("treatment", Arrays.asList(TreatmentGridDTO.getAll(super.getClientSession().getRequest())));
    req.setAttribute("item", IndividualInstanceDTO.get(clientRequest, id));
    render("viewComponent.jsp");
    */
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }
}
