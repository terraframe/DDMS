package dss.vector.solutions.general;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.geo.GeoHierarchyDTO;
import dss.vector.solutions.geo.GeoHierarchyViewDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.irs.RequiredGeoIdProblemDTO;
import dss.vector.solutions.irs.RequiredSeasonProblemDTO;
import dss.vector.solutions.util.ColumnSetup;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.FileDownloadUtil;
import dss.vector.solutions.util.Halp;
import dss.vector.solutions.util.RedirectUtility;

public class ThresholdDataController extends ThresholdDataControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 1256068148447L;

  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/general/ThresholdData/";

  public static final String LAYOUT           = "/layout.jsp";

  public static final String VIEWS            = "views";

  public static final String ITEM             = "item";

  public static final String KEYS             = "keys";

  public static final String COLUMNS          = "columns";

  public ThresholdDataController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void search() throws IOException, ServletException
  {
    if (!this.isAsynchronous())
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.checkURL(this.getClass().getSimpleName(), "search");

      new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "search");

      ClientRequestIF request = this.getClientRequest();

      req.setAttribute("seasons", Arrays.asList(MalariaSeasonDTO.getAll(request)));
      req.setAttribute("item", new ThresholdDataViewDTO(request));
      render("searchComponent.jsp");
    }
  }

  @Override
  public void failSearch() throws IOException, ServletException
  {
    // This should never happen
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }

  @Override
  public void searchForThresholdData(String geoId, MalariaSeasonDTO season, Boolean thresholdType) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, season);

      ClientRequestIF request = this.getClientRequest();

      ThresholdDataViewDTO[] views = null;

      if (thresholdType)
      {
        views = ThresholdDataViewDTO.getViews(request, geoId, season);
      }
      else
      {
        views = ThresholdDataViewDTO.getFacilityViews(request, geoId, season);
      }

      ThresholdDataViewDTO item = new ThresholdDataViewDTO(request);
      item.setGeoEntity(geoId);
      item.setSeason(season);

      EpiDateDTO[] weeks = season.getEpiWeeks();

      String[] keys = getAttributeKeys(weeks);
      Map<String, ColumnSetup> map = getColumns(weeks, thresholdType);

      req.setAttribute(ITEM, item);
      req.setAttribute(VIEWS, views);
      req.setAttribute(KEYS, keys);
      req.setAttribute(COLUMNS, map);
      req.setAttribute("season", season);
      req.setAttribute("entity", GeoEntityDTO.searchByGeoId(request, geoId));

      render("viewComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      String failThresholdType = thresholdType != null ? thresholdType.toString() : "true";
      this.failSearchForThresholdData(geoId, season, failThresholdType);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      String failThresholdType = thresholdType != null ? thresholdType.toString() : "true";
      this.failSearchForThresholdData(geoId, season, failThresholdType);
    }
  }

  private Map<String, ColumnSetup> getColumns(EpiDateDTO[] weeks, boolean thresholdType)
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
    map.put("ConcreteId", new ColumnSetup(true, false));
    map.put("GeoEntity", new ColumnSetup(true, false));
    map.put("Season", new ColumnSetup(true, false));
    map.put("ThresholdType", new ColumnSetup(true, false));
    map.put("EntityLabel", new ColumnSetup(false, false));

    for (int i = 0; i < 53; i++)
    {
      map.put("Outbreak_" + i, new ColumnSetup(true, false));
      map.put("Identification_" + i, new ColumnSetup(true, false));
    }

    for (EpiDateDTO week : weeks)
    {
      String startDate = Halp.getFormatedDate(req, week.getStartDate());
      String endDate = Halp.getFormatedDate(req, week.getEndDate());
      Integer numberOfWeeks = week.getNumberOfEpiWeeks();

      ColumnSetup outbreakSetup = new ColumnSetup(false, true);
      outbreakSetup.setSum(thresholdType);
      outbreakSetup.setTitle(startDate + " -> " + endDate);
      outbreakSetup.setValidator("thresholdValidator");

      ColumnSetup identificationSetup = new ColumnSetup(false, true);
      identificationSetup.setSum(thresholdType);
      identificationSetup.setTitle(startDate + " -> " + endDate);
      identificationSetup.setValidator("thresholdValidator");

      int index = ( week.getPeriod() % numberOfWeeks );

      map.put("Outbreak_" + index, outbreakSetup);
      map.put("Identification_" + index, identificationSetup);
    }

    return map;
  }

  private String[] getAttributeKeys(EpiDateDTO[] weeks)
  {
    List<String> list = new LinkedList<String>();
    list.add("ConcreteId");
    list.add("GeoEntity");
    list.add("Season");
    list.add("EntityLabel");

    List<Integer> indices = getAttributeIndicies(weeks);

    for (Integer i : indices)
    {
      list.add("Outbreak_" + i);
      list.add("Identification_" + i);
    }

    String[] attributes = list.toArray(new String[list.size()]);
    return attributes;
  }

  private List<Integer> getAttributeIndicies(EpiDateDTO[] weeks)
  {
    LinkedList<Integer> set = new LinkedList<Integer>();

    for (EpiDateDTO week : weeks)
    {
      Integer numberOfWeeks = week.getNumberOfEpiWeeks();
      Integer period = week.getPeriod();

      int index = ( period % numberOfWeeks );

      set.add(index);
    }

    for (int i = 0; i < 53; i++)
    {
      if (!set.contains(i))
      {
        set.add(i);
      }
    }

    return set;
  }

  @Override
  public void failSearchForThresholdData(String geoId, MalariaSeasonDTO season, String thresholdType) throws IOException, ServletException
  {
    this.search();
  }

  @Override
  public void exportThresholdData(ThresholdDataViewDTO[] views) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    InputStream stream = ThresholdDataViewDTO.exportToExcel(clientRequest, views);

    FileDownloadUtil.writeXLS(resp, "threshold", stream);
  }

  @Override
  public void failExportThresholdData(ThresholdDataViewDTO[] views) throws IOException, ServletException
  {
    this.search();
  }

  @Override
  public void editThresholdConfiguration() throws IOException, ServletException
  {
    if (!this.isAsynchronous())
    {
      RedirectUtility utility = new RedirectUtility(req, resp);
      utility.checkURL(this.getClass().getSimpleName(), "editThresholdConfiguration");
      new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "editThresholdConfiguration");

      this.editThresholdConfiguration(ThresholdCalculationTypeViewDTO.getCalculationThreshold(this.getClientRequest()));
    }
  }

  private void editThresholdConfiguration(ThresholdCalculationTypeViewDTO item) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientSession().getRequest();

    List<GeoHierarchyViewDTO> universals = this.getPopulationFilterHiearchies();
    List<OutbreakCalculationMasterDTO> countingMethods = OutbreakCalculationDTO.allItems(request);
    Integer percentComplete = ThresholdCalculationTypeViewDTO.getPercentComplete(request);

    req.setAttribute("thresholdCalculationMethods", ThresholdCalculationMethodDTO.allItems(request));
    req.setAttribute("thresholdCalculationCaseTypes", ThresholdCalculationCaseTypesDTO.allItems(request));
    req.setAttribute("methods", countingMethods);
    req.setAttribute("views", universals);

    req.setAttribute("thresholdCalculation", item);
    req.setAttribute("epidemicUniversal", item.getEpidemicUniversal());
    req.setAttribute("active", percentComplete != -1);

    render("editThresholdConfiguration.jsp");
  }

  private List<GeoHierarchyViewDTO> getPopulationFilterHiearchies()
  {
    ClientRequestIF request = this.getClientSession().getRequest();

    List<GeoHierarchyViewDTO> list = Arrays.asList(GeoHierarchyDTO.getAllViews(request));
    List<GeoHierarchyViewDTO> views = new LinkedList<GeoHierarchyViewDTO>();

    for (Iterator<GeoHierarchyViewDTO> it = list.iterator(); it.hasNext();)
    {
      GeoHierarchyViewDTO view = it.next();

      if (view.getPolitical() && view.getPopulationAllowed())
      {
        views.add(view);
      }
    }
    return views;
  }

  @Override
  public void failEditThresholdConfiguration() throws IOException, ServletException
  {
    // This should never happen
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }

  @Override
  public void setThresholdConfiguration(String universal, String calulationMethod, ThresholdCalculationTypeViewDTO thresholdCalculation) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      ThresholdDataViewDTO.setThresholdConfiguration(request, universal, calulationMethod);
      thresholdCalculation.apply();
      this.editThresholdConfiguration();
    }
    catch (java.lang.Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failSetThresholdConfiguration(universal, calulationMethod, thresholdCalculation);
      }
    }
  }

  @Override
  public void failSetThresholdConfiguration(String universal, String calculationMethod, ThresholdCalculationTypeViewDTO thresholdCalculation) throws IOException, ServletException
  {
    this.editThresholdConfiguration(thresholdCalculation);
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

  @Override
  public void calculateThresholds(ThresholdCalculationTypeViewDTO thresholdCalculation, Boolean currentYear) throws IOException, ServletException
  {
    ThresholdCalculationTypeViewDTO.calculateThresholds(this.getClientRequest(), thresholdCalculation, currentYear);
    this.editThresholdConfiguration();
  }

  @Override
  public void failCalculateThresholds(ThresholdCalculationTypeViewDTO thresholdCalculation, String currentYear) throws IOException, ServletException
  {
    super.failCalculateThresholds(thresholdCalculation, currentYear);
  }

  @Override
  public void calculatePoliticalThresholds(ThresholdCalculationTypeViewDTO thresholdCalculation, Boolean currentYear) throws IOException, ServletException
  {
    try
    {
      ThresholdCalculationTypeViewDTO.calculatePoliticalThresholds(this.getClientRequest(), thresholdCalculation, currentYear);
      this.editThresholdConfiguration();
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
    }
  }

  @Override
  public void failCalculatePoliticalThresholds(ThresholdCalculationTypeViewDTO thresholdCalculation, String currentYear) throws IOException, ServletException
  {
    super.failCalculateThresholds(thresholdCalculation, currentYear);
  }

  @Override
  public void calculateFacilityThresholds(ThresholdCalculationTypeViewDTO thresholdCalculation, Boolean currentYear) throws IOException, ServletException
  {
    try
    {
      ThresholdCalculationTypeViewDTO.calculateFacilityThresholds(this.getClientRequest(), thresholdCalculation, currentYear);
      this.editThresholdConfiguration();
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
    }
  }

  @Override
  public void failCalculateFacilityThresholds(ThresholdCalculationTypeViewDTO thresholdCalculation, String currentYear) throws IOException, ServletException
  {
    super.failCalculateThresholds(thresholdCalculation, currentYear);
  }

  @Override
  public void exportHistory() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF request = this.getClientRequest();

      InputStream stream = WeeklyThresholdViewDTO.exportHistory(request);

      FileDownloadUtil.writeXLS(resp, "ThresholdHistory", stream);
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failExportHistory();
      }
    }
  }

  @Override
  public void failExportHistory() throws IOException, ServletException
  {
    this.editThresholdConfiguration();
  }
}
