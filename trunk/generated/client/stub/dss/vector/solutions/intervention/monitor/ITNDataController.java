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

import dss.vector.solutions.geo.generated.CollectionSiteDTO;
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

public class ITNDataController extends ITNDataControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/ITNData/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1245774428706L;

  public ITNDataController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      this.view(ITNDataDTO.getView(super.getClientRequest(), id));
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

  public void view(ITNDataViewDTO dto) throws IOException, ServletException
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

  public void newInstance() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    ITNDataDTO dto = new ITNDataDTO(clientRequest);
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void delete(ITNDataViewDTO dto) throws IOException, ServletException
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

  public void failDelete(ITNDataViewDTO dto) throws IOException, ServletException
  {
    this.prepareRelationships(dto);
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void create(ITNDataViewDTO dto, ITNNetDTO[] nets, ITNTargetGroupDTO[] targetGroups, ITNServiceDTO[] services) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets, targetGroups, services);
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto, nets, targetGroups, services);
      }
    }
  }

  public void failCreate(ITNDataViewDTO dto, ITNNetDTO[] nets, ITNTargetGroupDTO[] targetGroups, ITNServiceDTO[] services) throws IOException, ServletException
  {
    this.prepareRelationships(dto, services, targetGroups, nets);

    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void update(ITNDataViewDTO dto, ITNNetDTO[] nets, ITNTargetGroupDTO[] targetGroups, ITNServiceDTO[] services) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets, targetGroups, services);
      this.view(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto, nets, targetGroups, services);
      }
    }
  }

  public void failUpdate(ITNDataViewDTO dto, ITNNetDTO[] nets, ITNTargetGroupDTO[] targetGroups, ITNServiceDTO[] services) throws IOException, ServletException
  {
    this.prepareRelationships(dto, services, targetGroups, nets);

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void search() throws IOException, ServletException
  {
    this.search(new ITNDataViewDTO(super.getClientSession().getRequest()));
  }

  private void search(ITNDataViewDTO dto) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    List<PeriodTypeMasterDTO> allItems = PeriodTypeDTO.allItems(clientRequest);
    List<String> entityUniversals = Arrays.asList(new String[] { HealthFacilityDTO.CLASS, CollectionSiteDTO.CLASS });

    if (dto.getGeoId() != null && !dto.getGeoId().equals(""))
    {
      GeoEntityDTO entity = GeoEntityDTO.searchByGeoId(clientRequest, dto.getGeoId());

      req.setAttribute("entity", entity);
    }

    req.setAttribute("entityUniversals", entityUniversals);
    req.setAttribute("periodType", allItems);
    req.setAttribute("checkedType", PeriodTypeDTO.MONTH.getName());
    req.setAttribute("item", dto);

    render("searchComponent.jsp");
  }

  public void failSearch() throws IOException, ServletException
  {
    // This should never occur
    super.failSearch();
  }

  public void cancel(ITNDataViewDTO dto) throws IOException, ServletException
  {
    try
    {
      this.view(ITNDataDTO.unlockView(dto.getRequest(), dto.getConcreteId()));
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

  public void failCancel(ITNDataViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getConcreteId());
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      ITNDataViewDTO dto = ITNDataDTO.lockView(super.getClientRequest(), id);

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

  public void searchByGeoIdAndPeriod(String geoId, String periodType, Integer period, Integer year) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      validateParameters(geoId, periodType, period, year);

      PeriodTypeDTO type = PeriodTypeDTO.valueOf(periodType);
      GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(request, geoId);

      ITNDataViewDTO dto = ITNDataDTO.searchByGeoEntityAndEpiDate(request, geoEntity, type, period, year);

      if (dto.hasConcreteId())
      {
        this.view(dto);
      }
      else
      {
        // Ensure the user has the ability to create new Aggregated ITN data
        new ITNDataDTO(request);

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

  public void failSearchByGeoIdAndPeriod(String geoId, String periodType, String period, String year) throws IOException, ServletException
  {
    req.setAttribute("period", period);
    req.setAttribute("year", year);
    req.setAttribute("geoId", geoId);
    req.setAttribute("checkedType", periodType);

    this.search();
  }

  @Override
  public void searchByView(ITNDataViewDTO dto) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientSession().getRequest();

      ITNDataViewDTO view = dto.searchByView();

      if (view.hasConcreteId())
      {
        this.view(view);
      }
      else
      {
        // Ensure that the user has the ability to create an aggregated IPT
        new ITNDataDTO(request);

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
  public void failSearchByView(ITNDataViewDTO dto) throws IOException, ServletException
  {
    this.search(dto);
  }

  private void prepareRelationships(ITNDataViewDTO dto)
  {
    this.prepareRelationships(dto, dto.getITNServices(), dto.getITNTargetGroups(), dto.getITNNets());
  }

  private void prepareRelationships(ITNDataViewDTO dto, ITNServiceDTO[] services, ITNTargetGroupDTO[] targetGroups, ITNNetDTO[] nets)
  {
    req.setAttribute("entity", AttributeUtil.getGeoEntityFromGeoId(ITNDataViewDTO.GEOID, dto));
    req.setAttribute("services", Arrays.asList(services));
    req.setAttribute("targetGroups", Arrays.asList(targetGroups));
    req.setAttribute("nets", Arrays.asList(nets));
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
