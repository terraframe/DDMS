package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.AgeConverter;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.surveillance.TreatmentGridDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class IndividualIPTController extends IndividualIPTControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/IndividualIPT/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1253643992094L;

  public IndividualIPTController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualIPTViewQueryDTO query = IndividualIPTViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualIPTViewQueryDTO query = IndividualIPTViewDTO.getPage(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(IndividualIPTDTO.getView(this.getClientRequest(), id));
  }

  private void view(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }
  
  @Override
  public void newInstance(String caseId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = super.getClientRequest();
      IndividualIPTCaseViewDTO view = IndividualIPTCaseDTO.getView(request, caseId);

      IndividualIPTViewDTO dto = new IndividualIPTViewDTO(request);      
      dto.setValue(IndividualIPTViewDTO.IPTCASE, view.getConcreteId());

      if (dto.getAge() == null)
      {
        PersonViewDTO person = view.getPatientView();

        dto.setAge(new AgeConverter(person.getDateOfBirth()).getAge());
      }

      this.setupRequest();

      req.setAttribute("healthFacility", HealthFacilityDTO.CLASS);
      req.setAttribute("item", dto);
      render("createComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failNewInstance(caseId);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failNewInstance(caseId);
    }
  }
  
  @Override
  public void failNewInstance(String caseId) throws IOException, ServletException
  {
    new IndividualIPTCaseController(req, resp, isAsynchronous).view(caseId);
  }

  public void create(IndividualIPTViewDTO dto) throws IOException, ServletException
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

  public void failCreate(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    IndividualIPTViewDTO dto = IndividualIPTDTO.lockView(super.getClientRequest(), id);

    this.setupRequest();

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void update(IndividualIPTViewDTO dto) throws IOException, ServletException
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

  public void failUpdate(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest();

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    IndividualIPTViewDTO view = IndividualIPTDTO.lockView(this.getClientRequest(), dto.getConcreteId());

    this.view(view);
  }

  public void failCancel(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    try
    {
      String caseId = dto.getIptCase().getId();
      
      dto.deleteConcrete();

      new IndividualIPTCaseController(req, resp, isAsynchronous).view(caseId);
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

  public void failDelete(IndividualIPTViewDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  private void setupRequest()
  {
    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("patientType", Arrays.asList(PatientGridDTO.getAll(request)));
    req.setAttribute("doseNumber", Arrays.asList(DoseGridDTO.getAll(request)));
    req.setAttribute("doseType", Arrays.asList(TreatmentGridDTO.getAll(request)));
    req.setAttribute("visitNumber", Arrays.asList(VisitGridDTO.getAll(request)));
  }
}
