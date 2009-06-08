package dss.vector.solutions.intervention.monitor;

import java.util.Arrays;
import java.util.List;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.intervention.BloodslideResponseDTO;
import dss.vector.solutions.intervention.FeverResponseDTO;
import dss.vector.solutions.intervention.FeverTreatmentDTO;
import dss.vector.solutions.intervention.HumanSexDTO;
import dss.vector.solutions.intervention.RDTResponseDTO;
import dss.vector.solutions.intervention.RDTResultDTO;
import dss.vector.solutions.intervention.ResponseMasterDTO;
import dss.vector.solutions.surveillance.TreatmentGridDTO;
import dss.vector.solutions.util.ErrorUtility;

public class PersonController extends PersonControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/intervention/monitor/Person/";
  public static final String LAYOUT = "/layout.jsp";

  private static final long serialVersionUID = 1239641308081L;

  public PersonController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(PersonViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto = PersonDTO.unlockView(super.getClientRequest(), dto.getConcreteId());

    this.view(dto);
  }

  public void failCancel(PersonViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    this.view(PersonDTO.getView(clientRequest, id));
  }

  public void view(PersonViewDTO person) throws java.io.IOException, javax.servlet.ServletException
  {
    if (!req.getRequestURI().contains(".view.mojo"))
    {
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", "PersonController");
      resp.sendRedirect(path.replaceFirst("\\.[a-zA-Z]+\\.mojo", ".view.mojo") + "?id=" + person.getConcreteId());
      return;
    }

    req.setAttribute("item", person);
    req.setAttribute("page_title", "View Person");
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    //This should never happen
    this.viewAll();
  }

  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    PersonViewDTO dto = PersonDTO.lockView(super.getClientRequest(), id);

    this.setupRequest();
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Person");
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void update(PersonViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.forceProblems(e, req);

      this.failUpdate(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failUpdate(dto);
    }
  }

  public void failUpdate(PersonViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update Person");
    render("editComponent.jsp");
  }

  public void delete(PersonViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failDelete(PersonViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Person");

    render("editComponent.jsp");
  }

  public void create(PersonViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      dto.apply();

      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.forceProblems(e, req);

      this.failCreate(dto);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failCreate(dto);
    }
  }

  public void failCreate(PersonViewDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Person");
    render("createComponent.jsp");
  }

  public void newInstance(String householdId) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    PersonViewDTO dto = new PersonViewDTO(clientRequest);
    dto.setHousehold(HouseholdDTO.get(clientRequest, householdId));

    this.setupRequest();
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Person");
    render("createComponent.jsp");
  }

  public void failNewInstance(String householdId) throws java.io.IOException, javax.servlet.ServletException
  {
    new HouseholdController(req, resp, isAsynchronous).view(householdId);
  }
  
  private void setupRequest()
  {
    ClientRequestIF request = super.getClientSession().getRequest();
    List<TreatmentGridDTO> drugs = Arrays.asList(TreatmentGridDTO.getAll(request));
    List<FeverTreatmentDTO> treatments = Arrays.asList(FeverTreatmentDTO.getAllActive(request));
    List<ResponseMasterDTO> response = FeverResponseDTO.allItems(request);
    
    req.setAttribute("anaemiaTreatment", drugs);
    req.setAttribute("bloodslide", BloodslideResponseDTO.allItems(request));
    req.setAttribute("fever", response);
    req.setAttribute("feverTreatment", treatments);
    req.setAttribute("malaria", response);
    req.setAttribute("malariaTreatment", drugs);
    req.setAttribute("payment", response);
    req.setAttribute("performedRDT", RDTResponseDTO.allItems(request));
    req.setAttribute("rDTResult", RDTResultDTO.allItems(request));
    req.setAttribute("rdtTreatment", drugs);
    req.setAttribute("sex", HumanSexDTO.allItems(request));
  }
}
