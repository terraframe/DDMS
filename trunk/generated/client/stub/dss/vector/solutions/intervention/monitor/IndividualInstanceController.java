package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.PhysicianDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.surveillance.IndividualCaseSymptomDTO;
import dss.vector.solutions.util.AttributeUtil;
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

  @Override
  public void update(IndividualInstanceDTO dto, TermDTO[] symptoms) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(symptoms);

      ClientRequestIF request = dto.getRequest();

      ErrorUtility.prepareInformation(request.getInformation(), req);

      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto, symptoms);
      }
    }
  }

  @Override
  public void failUpdate(IndividualInstanceDTO dto, TermDTO[] symptoms) throws IOException, ServletException
  {
    this.renderEdit(dto, symptoms);
  }

  @Override
  public void create(IndividualInstanceDTO dto, TermDTO[] symptoms) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(symptoms);

      ClientRequestIF request = dto.getRequest();

      ErrorUtility.prepareInformation(request.getInformation(), req);

      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto, symptoms);
      }
    }
  }

  @Override
  public void failCreate(IndividualInstanceDTO dto, TermDTO[] symptoms) throws IOException, ServletException
  {
    renderCreate(dto, symptoms, dto.getValue(IndividualInstanceDTO.INDIVIDUALCASE));
  }

  @Override
  public void createWithCase(IndividualInstanceDTO dto, IndividualCaseDTO newCase, String personId, TermDTO[] symptoms) throws IOException, ServletException
  {
    try
    {
      // newCase.applyWithPersonId(personId);
      this.create(dto, symptoms);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreateWithCase(dto, newCase, personId, symptoms);
      }
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
    try
    {
      IndividualInstanceDTO dto = IndividualInstanceDTO.lock(super.getClientRequest(), id);
      renderEdit(dto);
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

  private void renderEdit(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    this.renderEdit(dto, dto.getSymptoms());
  }

  private void renderEdit(IndividualInstanceDTO dto, TermDTO[] symptoms) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();
    PersonViewDTO person = dto.getIndividualCase().getPatient().getPerson().getView();

    PhysicianDTO physician = dto.getPhysician();

    if (physician != null)
    {
      PersonViewDTO view = physician.getView();

      req.setAttribute("physicianId", physician.getId());
      req.setAttribute("physicianLabel", view.getFirstName() + " " + view.getLastName());
    }

    req.setAttribute("person", person);
    req.setAttribute("residential", AttributeUtil.getGeoEntityFromGeoId(PersonViewDTO.RESIDENTIALGEOID, person));
    req.setAttribute("item", dto);
    req.setAttribute("healthFacility", AttributeUtil.getValue(IndividualInstanceDTO.HEALTHFACILITY, dto));
    req.setAttribute("symptoms", Arrays.asList(symptoms));
    req.setAttribute("HEALTH_FACILITY", HealthFacilityDTO.CLASS);
    req.setAttribute("diagnosisType", DiagnosisTypeDTO.allItems(clientRequest));

    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void cancel(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.unlock();

      this.view(dto);
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

  public void failCancel(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void newInstance(String caseId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      IndividualInstanceDTO dto = new IndividualInstanceDTO(clientRequest);
      dto.setActivelyDetected(false);
      dto.setValue(IndividualInstanceDTO.INDIVIDUALCASE, caseId);
      renderCreate(dto, dto.getSymptoms(), caseId);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance(caseId);
      }
    }

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
    req.setAttribute("HEALTH_FACILITY", HealthFacilityDTO.CLASS);

    render("createWithCase.jsp");
  }

  private void renderCreate(IndividualInstanceDTO dto, TermDTO[] symptoms, String caseId) throws IOException, ServletException
  {
    prepareCreateReq(dto, symptoms);

    PersonViewDTO person = dto.getIndividualCase().getPatient().getPerson().getView();

    req.setAttribute("person", person);
    req.setAttribute("residential", AttributeUtil.getGeoEntityFromGeoId(PersonViewDTO.RESIDENTIALGEOID, person));
    req.setAttribute("caseId", caseId);
    req.setAttribute("HEALTH_FACILITY", HealthFacilityDTO.CLASS);

    render("createComponent.jsp");
  }

  private void prepareCreateReq(IndividualInstanceDTO dto, TermDTO[] symptoms)
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    req.setAttribute("item", dto);
    req.setAttribute("healthFacility", AttributeUtil.getValue(IndividualInstanceDTO.HEALTHFACILITY, dto));
    req.setAttribute("symptoms", Arrays.asList(symptoms));
    req.setAttribute("diagnosisType", DiagnosisTypeDTO.allItems(clientRequest));
  }

  public void failNewInstance(String caseId) throws IOException, ServletException
  {
    new IndividualCaseController(req, resp, isAsynchronous).view(caseId);
  }

  public void delete(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    try
    {
      String caseId = dto.getIndividualCase().getId();

      dto.delete();

      new IndividualCaseController(req, resp, isAsynchronous).view(caseId);
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

  public void failDelete(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    renderEdit(dto);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(IndividualInstanceDTO.get(super.getClientRequest(), id));
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

  private void view(IndividualInstanceDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    PersonViewDTO person = dto.getIndividualCase().getPatient().getPerson().getView();
    PersonViewDTO physician = ( dto.getPhysician() == null ? null : dto.getPhysician().getView() );

    req.setAttribute("physician", physician);
    req.setAttribute("person", person);
    req.setAttribute("residential", AttributeUtil.getGeoEntityFromGeoId(PersonViewDTO.RESIDENTIALGEOID, person));
    req.setAttribute("item", dto);
    req.setAttribute("symptoms", Arrays.asList(dto.getSymptoms()));
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    new IndividualCaseController(req, resp, isAsynchronous).newInstance();
  }
}
