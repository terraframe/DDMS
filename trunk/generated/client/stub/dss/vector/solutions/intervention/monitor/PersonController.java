package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class PersonController extends PersonControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/Person/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1239641308081L;

  public PersonController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(PersonViewDTO dto) throws IOException, ServletException
  {
    dto = PersonDTO.unlockView(super.getClientRequest(), dto.getConcreteId());

    this.view(dto);
  }

  public void failCancel(PersonViewDTO dto) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    this.view(PersonDTO.getView(clientRequest, id));
  }

  public void view(PersonViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupReferences(dto);
    req.setAttribute("results", Arrays.asList(dto.getRDTResults()));
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    // This should never happen
    this.viewAll();
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      PersonViewDTO dto = PersonDTO.lockView(super.getClientRequest(), id);

      this.edit(dto, dto.getRDTResults());
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

  private void edit(PersonViewDTO dto, TermDTO[] results) throws IOException, ServletException
  {
    this.setupReferences(dto);
    
    req.setAttribute("results", Arrays.asList(results));
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }
  
  @Override
  public void update(PersonViewDTO dto, TermDTO[] results) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(results);
      
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.forceProblems(e, req);

      this.failUpdate(dto, results);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failUpdate(dto, results);
    }
  }
  
  @Override
  public void failUpdate(PersonViewDTO dto, TermDTO[] results) throws IOException, ServletException
  {
    this.edit(dto, results);
  }

  public void delete(PersonViewDTO dto) throws IOException, ServletException
  {
    try
    {
      HouseholdDTO household = dto.getHousehold();

      dto.delete();

      new HouseholdController(req, resp, isAsynchronous).view(household);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.forceProblems(e, req);

      this.failDelete(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failDelete(dto);
    }
  }

  public void failDelete(PersonViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto, dto.getRDTResults());
  }
  
  @Override
  public void create(PersonViewDTO dto, TermDTO[] results) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(results);

      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.forceProblems(e, req);

      this.failCreate(dto, results);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failCreate(dto, results);
    }
  }
  
  @Override
  public void failCreate(PersonViewDTO dto, TermDTO[] results) throws IOException, ServletException
  {
    this.newInstance(dto, results);
  }

  public void newInstance(String householdId) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    PersonViewDTO dto = new PersonViewDTO(clientRequest);
    dto.setHousehold(HouseholdDTO.get(clientRequest, householdId));

    this.newInstance(dto, dto.getRDTResults());
  }

  private void newInstance(PersonViewDTO dto, TermDTO[] results) throws IOException, ServletException
  {
    this.setupReferences(dto);
    
    req.setAttribute("results", Arrays.asList(results));
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance(String householdId) throws IOException, ServletException
  {
    new HouseholdController(req, resp, isAsynchronous).view(householdId);
  }
  
  private void setupReferences(PersonViewDTO dto)
  {
    req.setAttribute("anaemiaTreatment", dto.getAnaemiaTreatment());
    req.setAttribute("bloodslide", dto.getBloodslide());
    req.setAttribute("fever", dto.getFever());
    req.setAttribute("feverTreatment", dto.getFeverTreatment());
    req.setAttribute("malaria", dto.getMalaria());
    req.setAttribute("malariaTreatment", dto.getMalariaTreatment());
    req.setAttribute("payment", dto.getPayment());
    req.setAttribute("performedRDT", dto.getPerformedRDT());
    req.setAttribute("rdtTreatment", dto.getRdtTreatment());
    req.setAttribute("sex", dto.getSex());    
  }
}
