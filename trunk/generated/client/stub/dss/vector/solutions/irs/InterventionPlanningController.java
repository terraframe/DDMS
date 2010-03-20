package dss.vector.solutions.irs;

import java.io.IOException;
import java.io.InputStream;
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

import dss.vector.solutions.PropertyDTO;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.FileDownloadUtil;
import dss.vector.solutions.util.RedirectUtility;

public class InterventionPlanningController extends InterventionPlanningControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/InterventionPlanning/";

  public static final String LAYOUT           = "/layout.jsp";

  public static final String VIEWS            = "views";

  public static final String ITEM             = "item";

  private static final long  serialVersionUID = 1254263184991L;

  public InterventionPlanningController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void search() throws IOException, ServletException
  {
    this.search(null);
  }

  @Override
  public void search(String option) throws IOException, ServletException
  {
    if (!this.isAsynchronous())
    {
      if (option == null)
      {
        option = "time";
      }

      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.put("option", option);
      utility.checkURL(this.getClass().getSimpleName(), "search");

      new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "search");

      this.setupSearch(option);

      render("searchComponent.jsp");
    }
  }

  @Override
  public void searchForTimePlanning(String geoId, MalariaSeasonDTO season) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, season);

      ClientRequestIF request = this.getClientRequest();

      TimeInterventionPlanningViewDTO[] views = TimeInterventionPlanningViewDTO.getViews(request, geoId, season);

      TimeInterventionPlanningViewDTO item = new TimeInterventionPlanningViewDTO(request);
      item.setGeoEntity(GeoEntityDTO.searchByGeoId(request, geoId));
      item.setSeason(season);

      Integer unitsPerDay = PropertyDTO.getInt(request, PropertyInfo.STANDARDS_PACKAGE, PropertyInfo.DEFAULT_UNITS);

      req.setAttribute("unitsPerDay", unitsPerDay);
      req.setAttribute(VIEWS, views);
      req.setAttribute(ITEM, item);

      render("viewTimeComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failSearchForTimePlanning(geoId, season);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failSearchForTimePlanning(geoId, season);
    }
  }

  @Override
  public void failSearchForTimePlanning(String geoId, MalariaSeasonDTO season) throws IOException, ServletException
  {
    search("time");
  }

  @Override
  public void exportTimePlanning(TimeInterventionPlanningViewDTO[] views) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    InputStream stream = TimeInterventionPlanningViewDTO.exportToExcel(clientRequest, views);

    FileDownloadUtil.writeXLS(resp, "planning", stream);
  }

  @Override
  public void exportInsecticidePlanning(InsecticideInterventionPlanningViewDTO[] views) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    InputStream stream = InsecticideInterventionPlanningViewDTO.exportToExcel(clientRequest, views);

    FileDownloadUtil.writeXLS(resp, "planning", stream);
  }

  @Override
  public void exportOperatorPlanning(OperatorInterventionPlanningViewDTO[] views) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    InputStream stream = OperatorInterventionPlanningViewDTO.exportToExcel(clientRequest, views);

    FileDownloadUtil.writeXLS(resp, "planning", stream);
  }

  @Override
  public void searchForInsceticidePlanning(String geoId, MalariaSeasonDTO season) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, season);

      ClientRequestIF request = this.getClientRequest();

      InsecticideInterventionPlanningViewDTO[] views = InsecticideInterventionPlanningViewDTO.getViews(request, geoId, season);

      InsecticideInterventionPlanningViewDTO item = new InsecticideInterventionPlanningViewDTO(request);
      item.setGeoEntity(GeoEntityDTO.searchByGeoId(request, geoId));
      item.setSeason(season);

      InsecticideNozzleViewDTO[] configurations = InsecticideNozzleViewDTO.getAllActive(request);

      req.setAttribute("configurations", Arrays.asList(configurations));
      req.setAttribute(VIEWS, views);
      req.setAttribute(ITEM, item);

      render("viewInsecticideComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failSearchForInsceticidePlanning(geoId, season);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failSearchForInsceticidePlanning(geoId, season);
    }
  }

  @Override
  public void failSearchForInsceticidePlanning(String geoId, MalariaSeasonDTO season) throws IOException, ServletException
  {
    search("insecticide");
  }

  @Override
  public void searchForOperatorPlanning(String geoId, MalariaSeasonDTO season) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, season);

      ClientRequestIF request = this.getClientRequest();

      OperatorInterventionPlanningViewDTO[] views = OperatorInterventionPlanningViewDTO.getViews(request, geoId, season);

      OperatorInterventionPlanningViewDTO item = new OperatorInterventionPlanningViewDTO(request);
      item.setGeoEntity(GeoEntityDTO.searchByGeoId(request, geoId));
      item.setSeason(season);

      req.setAttribute(VIEWS, views);
      req.setAttribute(ITEM, item);

      render("viewOperatorComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failSearchForOperatorPlanning(geoId, season);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failSearchForOperatorPlanning(geoId, season);
    }
  }

  @Override
  public void failSearchForOperatorPlanning(String geoId, MalariaSeasonDTO season) throws IOException, ServletException
  {
    search("operator");
  }

  @Override
  public void setSprayedUnitsPerDay(Integer unitsPerDay) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      PropertyDTO.setUnitsPerDay(request, unitsPerDay);

      search();
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        String failUnits = ( unitsPerDay == null ? null : unitsPerDay.toString() );

        this.failSetSprayedUnitsPerDay(failUnits);
      }
    }
  }

  @Override
  public void failSetSprayedUnitsPerDay(String unitsPerDay) throws IOException, ServletException
  {
    search();
  }

  private void setupSearch(String option)
  {
    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("seasons", Arrays.asList(MalariaSeasonDTO.getAll(request)));

    req.setAttribute("time", "");
    req.setAttribute("insecticide", "");
    req.setAttribute("operator", "");

    req.setAttribute(option, "checked=\"checked\"");
  }

  private void validateParameters(String geoId, MalariaSeasonDTO season)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoId == null)
    {
      problems.add(new RequiredGeoIdProblemDTO(this.getClientRequest(), req.getLocale()));
    }

    if (season == null)
    {
      problems.add(new RequiredSeasonProblemDTO(this.getClientRequest(), req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }
}
