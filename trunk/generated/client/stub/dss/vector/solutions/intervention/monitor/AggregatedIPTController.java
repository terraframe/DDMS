package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.surveillance.PeriodTypeDTO;
import dss.vector.solutions.surveillance.PeriodTypeMasterDTO;
import dss.vector.solutions.surveillance.RequiredGeoIdProblemDTO;
import dss.vector.solutions.surveillance.RequiredPeriodProblemDTO;
import dss.vector.solutions.surveillance.RequiredPeriodTypeProblemDTO;
import dss.vector.solutions.surveillance.RequiredYearProblemDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class AggregatedIPTController extends AggregatedIPTControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/AggregatedIPT/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1244737056550L;

  public AggregatedIPTController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void searchByView(AggregatedIPTViewDTO dto) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientSession().getRequest();

      AggregatedIPTViewDTO view = dto.searchByView();

      if (view.hasConcreteId())
      {
        this.view(view);
      }
      else
      {
        // Ensure that the user has the ability to create an aggregated IPT
        new AggregatedIPTDTO(request);

        // Load all of the corresponding grid values
        this.prepareRelationships(view);
        req.setAttribute("item", view);
        render("createComponent.jsp");
      }

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSearchByView(dto);
      }
    }
  }

  @Override
  public void failSearchByView(AggregatedIPTViewDTO dto) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    if (dto.getGeoId() != null && !dto.getGeoId().equals(""))
    {
      GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(clientRequest, dto.getGeoId());

      req.setAttribute("entity", entity);
    }

    req.setAttribute("periodType", PeriodTypeDTO.allItems(clientRequest));
    req.setAttribute("item", dto);

    render("searchComponent.jsp");
  }

  public void searchByGeoIdAndPeriod(String geoId, String periodType, Integer period, Integer year) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      validateParameters(geoId, periodType, period, year);

      PeriodTypeDTO type = PeriodTypeDTO.valueOf(periodType);
      GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(this.getClientRequest(), geoId);

      AggregatedIPTViewDTO dto = AggregatedIPTDTO.searchByGeoEntityAndEpiDate(request, geoEntity, type, period, year);

      if (dto.hasConcreteId())
      {
        this.view(dto);
      }
      else
      {
        // Ensure that the user has the ability to create an aggregated IPT
        new AggregatedIPTDTO(request);

        // Load all of the corresponding grid values
        this.prepareRelationships(dto);
        req.setAttribute("item", dto);
        render("createComponent.jsp");
      }

    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        String failPeriod = period == null ? null : period.toString();
        String failYear = year == null ? null : year.toString();

        this.failSearchByGeoIdAndPeriod(geoId, periodType, failPeriod, failYear);
      }
    }

  }

  private void prepareRelationships(AggregatedIPTViewDTO dto)
  {
    this.prepareRelationships(dto, dto.getIPTPatients(), dto.getIPTANCVisits(), dto.getIPTDoses(), dto.getIPTTreatments());
  }

  private void prepareRelationships(AggregatedIPTViewDTO dto, IPTPatientsDTO[] patients, IPTANCVisitDTO[] visits, IPTDoseDTO[] doses, IPTTreatmentDTO[] treatments)
  {
    req.setAttribute("entity", AttributeUtil.getGeoEntityFromGeoId(AggregatedIPTViewDTO.GEOID, dto));
    req.setAttribute("patients", Arrays.asList(patients));
    req.setAttribute("visits", Arrays.asList(visits));
    req.setAttribute("doses", Arrays.asList(doses));
    req.setAttribute("treatments", Arrays.asList(treatments));
  }

  public void failSearchByGeoIdAndPeriod(String geoId, String periodType, String period, String year) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();
    List<PeriodTypeMasterDTO> allItems = PeriodTypeDTO.allItems(clientRequest);

    req.setAttribute("periodType", allItems);
    req.setAttribute("period", period);
    req.setAttribute("year", year);

    if (geoId != null && !geoId.equals(""))
    {
      GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(clientRequest, geoId);

      req.setAttribute("geoId", entity);
    }

    List<String> entityUniversals = Arrays.asList(new String[] { HealthFacilityDTO.CLASS });
    req.setAttribute("entityUniversals", entityUniversals);
    req.setAttribute("HealthFacility", HealthFacilityDTO.CLASS);
    req.setAttribute("checkedType", periodType);

    render("searchComponent.jsp");
  }

  public void search() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    List<String> entityUniversals = Arrays.asList(new String[] { HealthFacilityDTO.CLASS });

    req.setAttribute("periodType", PeriodTypeDTO.allItems(clientRequest));
    req.setAttribute("checkedType", PeriodTypeDTO.MONTH.getName());
    req.setAttribute("item", new AggregatedIPTViewDTO(clientRequest));

    req.setAttribute("entityUniversals", entityUniversals);
    req.setAttribute("HealthFacility", HealthFacilityDTO.CLASS);

    render("searchComponent.jsp");
  }

  public void failSearch() throws IOException, ServletException
  {
    // This should never occur
    super.failSearch();
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      AggregatedIPTViewDTO dto = AggregatedIPTDTO.lockView(super.getClientRequest(), id);

      this.prepareRelationships(dto);
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

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      AggregatedIPTViewDTO dto = new AggregatedIPTViewDTO(clientRequest);

      // Ensure that the user has the ability to create an aggregated IPT
      new AggregatedIPTDTO(clientRequest);

      this.prepareRelationships(dto);
      req.setAttribute("item", dto);
      render("createComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance();
      }
    }
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.search();
  }

  public void cancel(AggregatedIPTViewDTO dto) throws IOException, ServletException
  {
    try
    {
      this.view(AggregatedIPTDTO.unlockView(dto.getRequest(), dto.getConcreteId()));
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

  public void failCancel(AggregatedIPTViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(AggregatedIPTDTO.getView(super.getClientRequest(), id));
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

  public void view(AggregatedIPTViewDTO dto) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", dto.getConcreteId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    this.prepareRelationships(dto);
    req.setAttribute("item", dto);
    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.search();
  }

  public void delete(AggregatedIPTViewDTO dto) throws IOException, ServletException
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

  public void failDelete(AggregatedIPTViewDTO dto) throws IOException, ServletException
  {
    this.prepareRelationships(dto);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void update(AggregatedIPTViewDTO dto, IPTPatientsDTO[] patients, IPTANCVisitDTO[] visits, IPTDoseDTO[] doses, IPTTreatmentDTO[] treatments) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(patients, visits, doses, treatments);
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto, patients, visits, doses, treatments);
      }
    }
  }

  public void failUpdate(AggregatedIPTViewDTO dto, IPTPatientsDTO[] patients, IPTANCVisitDTO[] visits, IPTDoseDTO[] doses, IPTTreatmentDTO[] treatments) throws IOException, ServletException
  {
    this.prepareRelationships(dto, patients, visits, doses, treatments);
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void create(AggregatedIPTViewDTO dto, IPTPatientsDTO[] patients, IPTANCVisitDTO[] visits, IPTDoseDTO[] doses, IPTTreatmentDTO[] treatments) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(patients, visits, doses, treatments);
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto, patients, visits, doses, treatments);
      }
    }
  }

  public void failCreate(AggregatedIPTViewDTO dto, IPTPatientsDTO[] patients, IPTANCVisitDTO[] visits, IPTDoseDTO[] doses, IPTTreatmentDTO[] treatments) throws IOException, ServletException
  {
    this.prepareRelationships(dto, patients, visits, doses, treatments);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  private void validateParameters(String geoId, String periodType, Integer period, Integer year)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoId == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredGeoIdProblemDTO(clientRequest, req.getLocale()));
    }

    if (periodType == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredPeriodTypeProblemDTO(clientRequest, req.getLocale()));
    }

    if (period == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredPeriodProblemDTO(clientRequest, req.getLocale()));
    }

    if (year == null)
    {
      ClientRequestIF clientRequest = super.getClientSession().getRequest();
      problems.add(new RequiredYearProblemDTO(clientRequest, req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

}
