package dss.vector.solutions.intervention.monitor;

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
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.RequiredAttributeProblemDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.DefaultConverter;
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
    view(IndividualIPTCaseDTO.getView(super.getClientRequest(), id));
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

    String location = view.getResidentialLocation();
    
    if(location != null && !location.equals(""))
    {
      req.setAttribute("residentialLocation", GeoEntityDTO.searchByGeoId(request, location));
    }
    
    PersonViewDTO person = view.getPatientView();
    
    req.setAttribute("person", person);
    req.setAttribute("serviceDate", req.getParameter("serviceDate"));
    req.setAttribute("query", IndividualIPTViewDTO.getCaseInstances(request, sortAttribute, isAscending, pageSize, pageNumber, view.getConcreteId()));
    req.setAttribute("item", view);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void newInstance(String patientId) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    PersonViewDTO view = PersonDTO.getView(clientRequest, patientId);

    IndividualIPTCaseViewDTO dto = new IndividualIPTCaseViewDTO(clientRequest);
    dto.setValue(IndividualIPTCaseViewDTO.PATIENT, view.getPersonId());
    dto.setResidentialLocation(view.getResidentialGeoId());
    
    IndividualIPTViewDTO instance = new IndividualIPTViewDTO(clientRequest);
    
    String serviceDate = req.getParameter("serviceDate");
    
    if(serviceDate != null && !serviceDate.equals(""))
    {
      instance.setServiceDate((Date) new DefaultConverter(Date.class).parse(serviceDate, req.getLocale()));
    }    
    
    req.setAttribute("item", dto);
    req.setAttribute("instance", instance);
    req.setAttribute("person", view);
    render("createComponent.jsp");
  }

  @Override
  public void failNewInstance(String patientId) throws IOException, ServletException
  {
    this.search();
  }

  public void create(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
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
  
  @Override
  public void createCaseAndInstance(IndividualIPTCaseViewDTO dto, IndividualIPTViewDTO instance) throws IOException, ServletException
  {
    try
    {
      dto.applyWithInstance(instance);
      
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failCreateCaseAndInstance(dto, instance);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failCreateCaseAndInstance(dto, instance);
    }    
  }
  
  @Override
  public void failCreateCaseAndInstance(IndividualIPTCaseViewDTO dto, IndividualIPTViewDTO instance) throws IOException, ServletException
  {
    req.setAttribute("instance", instance);
    req.setAttribute("item", dto);
    render("createComponent.jsp");    
  }

  public void failCreate(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    IndividualIPTCaseViewDTO dto = IndividualIPTCaseDTO.lockView(super.getClientRequest(), id);
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

  public void failUpdate(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("patient", IPTRecipientDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    this.view(IndividualIPTCaseDTO.unlockView(this.getClientRequest(), dto.getConcreteId()));
  }

  public void failCancel(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
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

  public void failDelete(IndividualIPTCaseViewDTO dto) throws IOException, ServletException
  {
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void search() throws IOException, ServletException
  {
    if (!this.isAsynchronous())
    {
//      new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "search");

      req.setAttribute("item", new IndividualIPTCaseViewDTO(this.getClientRequest()));
      req.setAttribute("serviceDate", req.getParameter("serviceDate"));      
      render("searchComponent.jsp");
    }
  }

  public void failSearch() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  @Override
  public void viewCasePage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, Date serviceDate, String patientId) throws IOException, ServletException
  {
    try
    {
    validateParameters(serviceDate, patientId);
    
    ClientRequestIF request = this.getClientRequest();

    IndividualIPTCaseViewDTO[] cases = IndividualIPTCaseViewDTO.searchCases(request, serviceDate, patientId);
    String formatDate = new DefaultConverter(Date.class).format(serviceDate, req.getLocale());
    
    PersonViewDTO person = PersonDTO.getView(request, patientId);
    
    String residential = person.getResidentialGeoId();
    
    if(residential != null && !residential.equals(""))
    {
      GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(request, residential);
      req.setAttribute("residential", entity);
    }
    
    req.setAttribute("person", person);
    req.setAttribute("view", new IndividualIPTCaseViewDTO(request));
    req.setAttribute("serviceDate", formatDate);
    req.setAttribute("patientId", patientId);
    req.setAttribute("cases", Arrays.asList(cases));

    render("viewAllComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String date = (serviceDate == null) ? null : new DefaultConverter(Date.class).format(serviceDate, req.getLocale());
      
      this.failViewCasePage(null, null, null, null, date, patientId);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      
      String date = (serviceDate == null) ? null : new DefaultConverter(Date.class).format(serviceDate, req.getLocale());
      
      this.failViewCasePage(null, null, null, null, date, patientId);
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

    if (serviceDate == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredServiceDateProblemDTO(clientRequest, req.getLocale()));
    }

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
