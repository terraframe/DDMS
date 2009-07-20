package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.surveillance.PeriodTypeDTO;
import dss.vector.solutions.surveillance.PeriodTypeMasterDTO;
import dss.vector.solutions.surveillance.RequiredGeoIdProblemDTO;
import dss.vector.solutions.surveillance.RequiredPeriodProblemDTO;
import dss.vector.solutions.surveillance.RequiredPeriodTypeProblemDTO;
import dss.vector.solutions.surveillance.RequiredYearProblemDTO;
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
    this.view(ITNDataDTO.getView(super.getClientRequest(), id));
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
    this.viewAll();
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

  public void failDelete(ITNDataViewDTO dto) throws IOException, ServletException
  {
    this.prepareRelationships(dto);
    req.setAttribute("item", dto);

    render("editComponent.jsp");
  }

  public void create(ITNDataViewDTO dto, ITNNetDTO[] nets, ITNTargetGroupDTO[] targetGroups,
      ITNServiceDTO[] services) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets, targetGroups, services);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failCreate(dto, nets, targetGroups, services);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failCreate(dto, nets, targetGroups, services);
    }
  }

  public void failCreate(ITNDataViewDTO dto, ITNNetDTO[] nets, ITNTargetGroupDTO[] targetGroups,
      ITNServiceDTO[] services) throws IOException, ServletException
  {
    this.prepareRelationships(services, targetGroups, nets);

    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void update(ITNDataViewDTO dto, ITNNetDTO[] nets, ITNTargetGroupDTO[] targetGroups,
      ITNServiceDTO[] services) throws IOException, ServletException
  {
    try
    {
      dto.applyAll(nets, targetGroups, services);
      this.view(dto);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failUpdate(dto, nets, targetGroups, services);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failUpdate(dto, nets, targetGroups, services);
    }
  }

  public void failUpdate(ITNDataViewDTO dto, ITNNetDTO[] nets, ITNTargetGroupDTO[] targetGroups,
      ITNServiceDTO[] services) throws IOException, ServletException
  {
    this.prepareRelationships(services, targetGroups, nets);

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void search() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    List<PeriodTypeMasterDTO> allItems = PeriodTypeDTO.allItems(clientRequest);

    req.setAttribute("periodType", allItems);
    req.setAttribute("checkedType", PeriodTypeDTO.MONTH.getName());

    render("searchComponent.jsp");
  }

  public void failSearch() throws IOException, ServletException
  {
    // This should never occur
    super.failSearch();
  }

  public void cancel(ITNDataViewDTO dto) throws IOException, ServletException
  {
    this.view(ITNDataDTO.unlockView(dto.getRequest(), dto.getConcreteId()));
  }

  public void failCancel(ITNDataViewDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
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

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void searchByGeoIdAndPeriod(String geoId, String periodType, Integer period, Integer year)
      throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientSession().getRequest();
      validateParameters(geoId, periodType, period, year);

      PeriodTypeDTO type = PeriodTypeDTO.valueOf(periodType);
      GeoEntityDTO geoEntity = GeoEntityDTO.searchByGeoId(this.getClientRequest(), geoId);

      ITNDataViewDTO dto = ITNDataDTO
          .searchByGeoEntityAndEpiDate(request, geoEntity, type, period, year);

      String jsp = "createComponent.jsp";

      if (dto.hasConcreteId())
      {
        jsp = "viewComponent.jsp";
      }

      // Load all of the corresponding grid values
      this.prepareRelationships(dto);
      req.setAttribute("item", dto);
      render(jsp);
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failPeriod = period == null ? null : period.toString();
      String failYear = year == null ? null : year.toString();

      this.failSearchByGeoIdAndPeriod(geoId, periodType, failPeriod, failYear);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failPeriod = period == null ? null : period.toString();
      String failYear = year == null ? null : year.toString();

      this.failSearchByGeoIdAndPeriod(geoId, periodType, failPeriod, failYear);
    }
  }

  public void failSearchByGeoIdAndPeriod(String geoId, String periodType, String period, String year)
      throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientSession().getRequest();
    List<PeriodTypeMasterDTO> allItems = PeriodTypeDTO.allItems(clientRequest);

    req.setAttribute("periodType", allItems);
    req.setAttribute("period", period);
    req.setAttribute("year", year);
    req.setAttribute("geoId", geoId);
    req.setAttribute("checkedType", periodType);

    render("searchComponent.jsp");
  }

  private void prepareRelationships(ITNDataViewDTO dto)
  {
    prepareRelationships(dto.getITNServices(), dto.getITNTargetGroups(), dto.getITNNets());
  }

  private void prepareRelationships(ITNServiceDTO[] services, ITNTargetGroupDTO[] targetGroups,
      ITNNetDTO[] nets)
  {
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
