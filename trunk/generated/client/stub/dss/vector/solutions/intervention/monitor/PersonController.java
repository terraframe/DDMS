package dss.vector.solutions.intervention.monitor;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.intervention.BloodslideResponseDTO;
import dss.vector.solutions.intervention.DrugDTO;
import dss.vector.solutions.intervention.FeverResponseDTO;
import dss.vector.solutions.intervention.HumanSexDTO;
import dss.vector.solutions.intervention.RDTResponseDTO;
import dss.vector.solutions.intervention.RDTResultDTO;
import dss.vector.solutions.util.ErrorUtility;

public class PersonController extends PersonControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR = "WEB-INF/dss/vector/solutions/intervention/monitor/Person/";
  public static final String LAYOUT = JSP_DIR + "layout.jsp";

  private static final long serialVersionUID = 1239641308081L;

  public PersonController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    dto.unlock();
    this.view(dto);
  }

  public void failCancel(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    this.view(PersonDTO.get(clientRequest, id));
  }

  public void view(PersonDTO person) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("anaemiaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("bloodslide", BloodslideResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("fever", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("feverTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("household", HouseholdDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("malaria", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("malariaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("payment", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("performedRDT", RDTResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rDTResult", RDTResultDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rdtTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", HumanSexDTO.allItems(super.getClientSession().getRequest()));
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
    PersonDTO dto = PersonDTO.lock(super.getClientRequest(), id);
    req.setAttribute("anaemiaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("bloodslide", BloodslideResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("fever", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("feverTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("household", HouseholdDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("malaria", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("malariaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("payment", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("performedRDT", RDTResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rDTResult", RDTResultDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rdtTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", HumanSexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Person");
    render("editComponent.jsp");
  }

  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.view(id);
  }

  public void update(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failUpdate(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("anaemiaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("bloodslide", BloodslideResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("fever", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("feverTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("household", HouseholdDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("malaria", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("malariaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("payment", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("performedRDT", RDTResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rDTResult", RDTResultDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rdtTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", HumanSexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Update Person");
    render("editComponent.jsp");
  }

  public void delete(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      HouseholdDTO household = dto.getHousehold();

      dto.delete();

      new HouseholdController(req, resp, isAsynchronous).view(household.getId());
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

  public void failDelete(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("anaemiaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("bloodslide", BloodslideResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("fever", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("feverTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("household", HouseholdDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("malaria", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("malariaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("payment", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("performedRDT", RDTResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rDTResult", RDTResultDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rdtTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", HumanSexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Edit Person");

    render("editComponent.jsp");
  }

  public void create(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
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

  public void failCreate(PersonDTO dto) throws java.io.IOException, javax.servlet.ServletException
  {
    req.setAttribute("anaemiaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("bloodslide", BloodslideResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("fever", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("feverTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("household", HouseholdDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("malaria", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("malariaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("payment", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("performedRDT", RDTResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rDTResult", RDTResultDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rdtTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", HumanSexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Person");
    render("createComponent.jsp");
  }

  public void newInstance(String householdId) throws java.io.IOException, javax.servlet.ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    PersonDTO dto = new PersonDTO(clientRequest);
    dto.setHousehold(HouseholdDTO.get(clientRequest, householdId));

    req.setAttribute("anaemiaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("bloodslide", BloodslideResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("fever", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("feverTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("malaria", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("malariaTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("payment", FeverResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("performedRDT", RDTResponseDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rDTResult", RDTResultDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("rdtTreatment", DrugDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("sex", HumanSexDTO.allItems(super.getClientSession().getRequest()));
    req.setAttribute("item", dto);
    req.setAttribute("page_title", "Create Person");
    render("createComponent.jsp");
  }

  public void failNewInstance(String householdId) throws java.io.IOException, javax.servlet.ServletException
  {
    new HouseholdController(req, resp, isAsynchronous).view(householdId);
  }
}
