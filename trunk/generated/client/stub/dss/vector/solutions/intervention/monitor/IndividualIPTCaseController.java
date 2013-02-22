package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.format.AbstractFormatFactory;
import com.runwaysdk.format.Format;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.RequiredAttributeProblemDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class IndividualIPTCaseController extends IndividualIPTCaseControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/IndividualIPTCase/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1254012840867L;

  public IndividualIPTCaseController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualIPTCaseQueryDTO query = IndividualIPTCaseDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualIPTCaseQueryDTO query = IndividualIPTCaseDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      view(IndividualIPTCaseDTO.getView(super.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failView(id);
      }
    }
  }

  private void view(IndividualIPTCaseViewDTO view) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", view.getConcreteId());
    utility.put("serviceDate", req.getParameter("serviceDate"));
    utility.checkURL(this.getClass().getSimpleName(), "view");

    String sortAttribute = IndividualIPTViewDTO.ADMINISTRATORNAME;
    Boolean isAscending = false;
    Integer pageSize = 20;
    Integer pageNumber = 1;

    ClientRequestIF request = this.getClientRequest();

    PersonViewDTO person = view.getPatientView();

    req.setAttribute("residentialLocation", AttributeUtil.getGeoEntityFromGeoId(PersonViewDTO.RESIDENTIALGEOID, person));
    req.setAttribute("person", person);
    req.setAttribute("serviceDate", req.getParameter("serviceDate"));
    req.setAttribute("query", IndividualIPTViewDTO.getCaseInstances(request, sortAttribute, isAscending, pageSize, pageNumber, view.getConcreteId()));
    req.setAttribute("item", view);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.search();
  }

  @Override
  public void newInstance(String patientId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      // Ensure the user has permissions to create a new Individual IPT Case
      new IndividualIPTCaseDTO(clientRequest);

      PersonViewDTO view = PersonDTO.getView(clientRequest, patientId);
      IndividualIPTCaseViewDTO dto = new IndividualIPTCaseViewDTO(clientRequest);
      IndividualIPTViewDTO instance = new IndividualIPTViewDTO(clientRequest);

      this.newInstance(view, dto, instance);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance(patientId);
      }
    }
  }

  private void newInstance(PersonViewDTO view, IndividualIPTCaseViewDTO dto, IndividualIPTViewDTO instance) throws IOException, ServletException
  {
    dto.setValue(IndividualIPTCaseViewDTO.PATIENT, view.getPersonId());
    dto.setResidentialLocation(AttributeUtil.getString(PersonViewDTO.RESIDENTIALGEOID, view));

    String serviceDate = req.getParameter("serviceDate");

    if (serviceDate != null && !serviceDate.equals(""))
    {
      Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);
      
      instance.setServiceDate((Date) f.parse(serviceDate, req.getLocale()));
    }

    req.setAttribute("item", dto);
    req.setAttribute("instance", instance);
    req.setAttribute("person", view);
    req.setAttribute("healthFacility", HealthFacilityDTO.CLASS);

    render("createComponent.jsp");
  }

  @Override
  public void failNewInstance(String patientId) throws IOException, ServletException
  {
    String serviceDate = req.getParameter("serviceDate");
    Date date = null;

    if (serviceDate != null && !serviceDate.equals(""))
    {
      Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);
      
      date = f.parse(serviceDate, req.getLocale());
    }

    this.viewCasePage(null, null, null, null, date, patientId);
  }

  public void create(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto);
      }
    }
  }

  @Override
  public void createCaseAndInstance(IndividualIPTCaseViewDTO dto, IndividualIPTViewDTO instance) throws IOException, ServletException
  {
    try
    {
      dto.applyWithInstance(instance);

      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreateCaseAndInstance(dto, instance);
      }
    }
  }

  @Override
  public void failCreateCaseAndInstance(IndividualIPTCaseViewDTO dto, IndividualIPTViewDTO instance) throws IOException, ServletException
  {
    this.newInstance(dto.getPatientView(), dto, instance);
  }

  public void failCreate(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      this.edit(IndividualIPTCaseDTO.lockView(super.getClientRequest(), id));
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failEdit(id);
      }
    }

  }

  private void edit(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    String serviceDate = req.getParameter("serviceDate");

    PersonViewDTO person = dto.getPatientView();

    req.setAttribute("person", person);
    req.setAttribute("serviceDate", serviceDate);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void update(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void cancel(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    try
    {
      this.view(IndividualIPTCaseDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
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

  public void failCancel(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void delete(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.deleteConcrete();

      this.search();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto);
  }

  public void search() throws IOException, ServletException
  {
    if (!this.isAsynchronous())
    {
      req.setAttribute("item", new IndividualIPTCaseViewDTO(this.getClientRequest()));
      req.setAttribute("serviceDate", req.getParameter("serviceDate"));
      req.setAttribute("person", new PersonViewDTO(this.getClientRequest()));

      // need this for labels
      render("searchComponent.jsp");
    }
  }

  public void failSearch() throws IOException, ServletException
  {
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  @Override
  public void viewCasePage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, Date serviceDate, String patientId) throws IOException, ServletException
  {
    Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);
    
    try
    {
      validateParameters(serviceDate, patientId);

      ClientRequestIF request = this.getClientRequest();

      IndividualIPTCaseViewDTO[] cases = IndividualIPTCaseViewDTO.searchCases(request, serviceDate, patientId);
      String formatDate = f.format(serviceDate, req.getLocale());

      PersonViewDTO person = PersonDTO.getView(request, patientId);

      req.setAttribute("residential", AttributeUtil.getGeoEntityFromGeoId(PersonViewDTO.RESIDENTIALGEOID, person));
      req.setAttribute("person", person);
      req.setAttribute("view", new IndividualIPTCaseViewDTO(request));
      req.setAttribute("serviceDate", formatDate);
      req.setAttribute("patientId", patientId);
      req.setAttribute("cases", Arrays.asList(cases));

      render("viewAllComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        String date = f.format(serviceDate, req.getLocale());

        this.failViewCasePage(null, null, null, null, date, patientId);
      }
    }
  }

  @Override
  public void failViewCasePage(String sortAttribute, String isAscending, String pageSize, String pageNumber, String serviceDate, String patientId) throws IOException, ServletException
  {
    this.search();
  }

  private void validateParameters(Date serviceDate, String patientId)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (patientId == null || patientId.equals(""))
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();

      problems.add(new RequiredAttributeProblemDTO(clientRequest, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

}
