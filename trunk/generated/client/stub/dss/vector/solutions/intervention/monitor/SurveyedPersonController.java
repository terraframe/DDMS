package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.RefusedResponseDTO;
import dss.vector.solutions.ResponseDTO;
import dss.vector.solutions.ResponseMasterDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.AttributeUtil;
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
    try
    {
      SurveyedPersonViewDTO view = SurveyedPersonDTO.unlockView(this.getClientRequest(), dto.getConcreteId());

      this.view(view);
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

  public void failCancel(SurveyedPersonViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void create(SurveyedPersonViewDTO dto, TermDTO[] locations, TermDTO[] treatments) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(locations, treatments);
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto, locations, treatments);
      }
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
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
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
    try
    {
      SurveyedPersonViewDTO dto = SurveyedPersonDTO.lockView(super.getClientRequest(), id);

      this.setupReference(dto);
      req.setAttribute("item", dto);
      render("editComponent.jsp");
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

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void newInstance(String householdId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      // Ensure the user has permissions to create a SurveyedPerson
      new SurveyedPersonDTO(clientRequest);

      SurveyedPersonViewDTO dto = new SurveyedPersonViewDTO(clientRequest);
      dto.setValue(SurveyedPersonViewDTO.HOUSEHOLD, householdId);
      dto.addHaemoglobinMeasured(RefusedResponseDTO.NO);
      dto.addPerformedRDT(RefusedResponseDTO.NO);
      dto.addFever(ResponseDTO.NO);
      dto.addMalaria(ResponseDTO.NO);

      this.setupReference(dto);
      req.setAttribute("item", dto);

      render("createComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance(householdId);
      }
    }
  }

  public void failNewInstance(String householdId) throws IOException, ServletException
  {
    new HouseholdController(req, resp, isAsynchronous).view(householdId);
  }

  public void update(SurveyedPersonViewDTO dto, TermDTO[] locations, TermDTO[] treatments) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(locations, treatments);
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto, locations, treatments);
      }
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
    try
    {
      this.view(SurveyedPersonDTO.getView(super.getClientRequest(), id));
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

  private void view(SurveyedPersonViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.setupReference(dto);

    ITNInstanceDTO net = (ITNInstanceDTO) AttributeUtil.getValue(SurveyedPersonViewDTO.SLEPTUNDERNET, dto);

    if (net != null)
    {
      req.setAttribute("net", net.getView());
    }

    req.setAttribute("item", dto);

    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    new SurveyPointController(req, resp, isAsynchronous).viewAll();
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
    req.setAttribute("anaemiaTreatment", AttributeUtil.getValue(SurveyedPersonViewDTO.ANAEMIATREATMENT, dto));
    req.setAttribute("bloodslideDetail", AttributeUtil.getValue(SurveyedPersonViewDTO.BLOODSLIDEDETAIL, dto));
    req.setAttribute("bloodslideReason", AttributeUtil.getValue(SurveyedPersonViewDTO.BLOODSLIDEREASON, dto));
    req.setAttribute("fever", this.getResponses(request));
    req.setAttribute("haemoglobinMeasured", this.getRefusedResponses(request));
    req.setAttribute("headOfHousehold", AttributeUtil.getValue(SurveyedPersonViewDTO.HEADOFHOUSEHOLD, dto));
    req.setAttribute("immuneCompromised", AttributeUtil.getValue(SurveyedPersonViewDTO.IMMUNECOMPROMISED, dto));
    req.setAttribute("malaria", this.getResponses(request));
    req.setAttribute("malariaConformationTechnique", AttributeUtil.getValue(SurveyedPersonViewDTO.MALARIACONFORMATIONTECHNIQUE, dto));
    req.setAttribute("payment", AttributeUtil.getValue(SurveyedPersonViewDTO.PAYMENT, dto));
    req.setAttribute("performedRDT", this.getRefusedResponses(request));
    req.setAttribute("rdtDetail", AttributeUtil.getValue(SurveyedPersonViewDTO.RDTDETAIL, dto));
    req.setAttribute("rdtTreatment", AttributeUtil.getValue(SurveyedPersonViewDTO.RDTTREATMENT, dto));
    req.setAttribute("sex", AttributeUtil.getValue(SurveyedPersonViewDTO.SEX, dto));
  }

  private List<ResponseMasterDTO> getResponses(ClientRequestIF request)
  {
    List<ResponseMasterDTO> responses = ResponseDTO.items(request, ResponseDTO.YES, ResponseDTO.NO);
    for (ResponseMasterDTO response : dss.vector.solutions.ResponseDTO.allItems(request))
    {
      if (!responses.contains(response))
      {
        responses.add(response);
      }
    }
    return responses;
  }

  private List<ResponseMasterDTO> getRefusedResponses(ClientRequestIF request)
  {
    List<ResponseMasterDTO> responses = RefusedResponseDTO.items(request, RefusedResponseDTO.YES, RefusedResponseDTO.NO);
    for (ResponseMasterDTO response : dss.vector.solutions.RefusedResponseDTO.allItems(request))
    {
      if (!responses.contains(response))
      {
        responses.add(response);
      }
    }
    return responses;
  }
}
