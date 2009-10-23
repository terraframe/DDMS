package dss.vector.solutions.general;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;
import com.terraframe.mojo.constants.ClientRequestIF;

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

      MalariaSeasonDTO[] seasons = MalariaSeasonDTO.getAll(this.getClientRequest());

      req.setAttribute("seasons", Arrays.asList(seasons));

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
  public void searchForThresholdData(String geoId, MalariaSeasonDTO season) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, season);

      ClientRequestIF request = this.getClientRequest();

      ThresholdDataViewDTO[] views = ThresholdDataViewDTO.getViews(request, geoId, season);

      if (views.length > 0)
      {
        req.setAttribute(ITEM, views[views.length - 1]);
      }
      else
      {
        ThresholdDataViewDTO item = new ThresholdDataViewDTO(request);
        item.setGeoEntity(geoId);
        item.setSeason(season);

        req.setAttribute(ITEM, item);
      }

      EpiDateDTO[] weeks = season.getEpiWeeks();

      String[] keys = getAttributeKeys(weeks);
      Map<String, ColumnSetup> map = getColumns(weeks);

      req.setAttribute(VIEWS, views);
      req.setAttribute(KEYS, keys);
      req.setAttribute(COLUMNS, map);
      req.setAttribute("season", season);

      render("viewComponent.jsp");
    }
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failSearchForThresholdData(geoId, season);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failSearchForThresholdData(geoId, season);
    }
  }

  private Map<String, ColumnSetup> getColumns(EpiDateDTO[] weeks)
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
    map.put("ConcreteId", new ColumnSetup(true, false));
    map.put("GeoEntity", new ColumnSetup(true, false));
    map.put("Season", new ColumnSetup(true, false));
    map.put("EntityLabel", new ColumnSetup(false, false));
    
    for(int i = 0; i < 53; i++)
    {
      map.put("Outbreak_" + i, new ColumnSetup(true, false));
      map.put("Identification_" + i, new ColumnSetup(true, false));
    }

    for (EpiDateDTO week : weeks)
    {
      String startDate = Halp.getFormatedDate(req, week.getStartDate());
      String endDate = Halp.getFormatedDate(req, week.getEndDate());

      int weekNumber = ( week.getPeriod() % week.getNumberOfEpiWeeks() ) + 1;

      ColumnSetup outbreakSetup = new ColumnSetup(false, true);
      outbreakSetup.setSum(true);
      outbreakSetup.setTitle(startDate + " -> " + endDate);

      ColumnSetup identificationSetup = new ColumnSetup(false, true);
      identificationSetup.setSum(true);
      identificationSetup.setTitle(startDate + " -> " + endDate);

      int index = weekNumber - 1;
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
    List<Integer> indices = new ArrayList<Integer>();
    
    for (EpiDateDTO week : weeks)
    {
      int index = ( week.getPeriod() % week.getNumberOfEpiWeeks() );

      indices.add(index);
    }
    
    for(int i = 0; i < 52; i++)
    {
      if(!indices.contains(i))
      {
        indices.add(i);
      }
    }
    return indices;
  }

  @Override
  public void failSearchForThresholdData(String geoId, MalariaSeasonDTO season) throws IOException, ServletException
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
