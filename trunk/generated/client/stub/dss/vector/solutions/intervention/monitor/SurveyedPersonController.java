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

public class SurveyedPersonController extends SurveyedPersonControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/SurveyedPerson/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = -1353408645;

  public SurveyedPersonController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(SurveyedPersonViewDTO dto) throws IOException, ServletException
  {
    SurveyedPersonViewDTO view = SurveyedPersonDTO.unlockView(this.getClientRequest(), dto.getConcreteId());

    this.view(view);
  }

  public void failCancel(SurveyedPersonViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void create(SurveyedPersonViewDTO dto, TermDTO[] locations, TermDTO[] treatments) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(locations, treatments);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failCreate(dto, locations, treatments);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failCreate(dto, locations, treatments);
    }
  }

  public void failCreate(SurveyedPersonViewDTO dto, TermDTO[] locations, TermDTO[] treatments) throws IOException, ServletException
  {    
    this.setupReference(dto, locations, treatments);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void delete(SurveyedPersonViewDTO dto) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();
      HouseholdViewDTO view = HouseholdDTO.getView(request, dto.getValue(SurveyedPersonViewDTO.HOUSEHOLD));

      dto.deleteConcrete();

      new HouseholdController(req, resp, isAsynchronous).view(view);
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

  public void failDelete(SurveyedPersonViewDTO dto) throws IOException, ServletException
  {
    this.setupReference(dto);
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    SurveyedPersonViewDTO dto = SurveyedPersonDTO.lockView(super.getClientRequest(), id);

    this.setupReference(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void newInstance(String householdId) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    SurveyedPersonViewDTO dto = new SurveyedPersonViewDTO(clientRequest);
    dto.setValue(SurveyedPersonViewDTO.HOUSEHOLD, householdId);

    this.setupReference(dto);    
    req.setAttribute("item", dto);
    
    render("createComponent.jsp");
  }

  public void failNewInstance(String householdId) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void update(SurveyedPersonViewDTO dto, TermDTO[] locations, TermDTO[] treatments) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(locations, treatments);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);
      this.failUpdate(dto, locations, treatments);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);
      this.failUpdate(dto, locations, treatments);
    }
  }

  public void failUpdate(SurveyedPersonViewDTO dto, TermDTO[] locations, TermDTO[] treatments) throws IOException, ServletException
  {
    this.setupReference(dto, locations, treatments);
    req.setAttribute("item", dto);
    
    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    this.view(SurveyedPersonDTO.getView(super.getClientRequest(), id));
  }

  private void view(SurveyedPersonViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupReference(dto);
    
    String netId = dto.getValue(SurveyedPersonViewDTO.SLEPTUNDERNET);
    
    if(netId != null && !netId.equals(""))
    {
      ITNInstanceViewDTO net = ITNInstanceDTO.getView(this.getClientRequest(), netId);

      req.setAttribute("net", net);
    }
    
    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    SurveyedPersonQueryDTO query = SurveyedPersonDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    SurveyedPersonQueryDTO query = SurveyedPersonDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }
  
  private void setupReference(SurveyedPersonViewDTO dto)
  {
    this.setupReference(dto, dto.getLocations(), dto.getTreatments());
  }
  
  private void setupReference(SurveyedPersonViewDTO dto, TermDTO[] locations, TermDTO[] treatments)
  {
    ClientRequestIF request = super.getClientSession().getRequest();
    
    String householdId = dto.getValue(SurveyedPersonViewDTO.HOUSEHOLD);

    HouseholdViewDTO household = HouseholdDTO.getView(request, householdId);
    ITNInstanceViewDTO[] itns = household.getItns();
    
    req.setAttribute("locations", Arrays.asList(locations));
    req.setAttribute("treatments", Arrays.asList(treatments));
    req.setAttribute("sleptUnderNet", Arrays.asList(itns));
    req.setAttribute("sex", dto.getSex());
    req.setAttribute("anaemiaTreatment", dto.getAnaemiaTreatment());
    req.setAttribute("bloodslideDetail", dto.getBloodslideDetail());
    req.setAttribute("bloodslideReason", dto.getBloodslideReason());
    req.setAttribute("fever", dss.vector.solutions.ResponseDTO.allItems(request));
    req.setAttribute("haemoglobinMeasured", dss.vector.solutions.RefusedResponseDTO.allItems(request));
    req.setAttribute("headOfHousehold", dto.getHeadOfHousehold());
    req.setAttribute("immuneCompromised", dto.getImmuneCompromised());
    req.setAttribute("malaria", dss.vector.solutions.ResponseDTO.allItems(request));
    req.setAttribute("malariaConformationTechnique", dto.getMalariaConformationTechnique());
    req.setAttribute("payment", dto.getPayment());
    req.setAttribute("performedRDT", dss.vector.solutions.RefusedResponseDTO.allItems(request));
    req.setAttribute("rdtDetail", dto.getRdtDetail());
    req.setAttribute("rdtTreatment", dto.getRdtTreatment());
    req.setAttribute("sex", dto.getSex());
  }

}
