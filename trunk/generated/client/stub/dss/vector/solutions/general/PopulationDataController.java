package dss.vector.solutions.general;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ConditionalAction;
import dss.vector.solutions.ToggleUniversalAction;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.irs.RequiredGeoIdProblemDTO;
import dss.vector.solutions.surveillance.RequiredYearProblemDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class PopulationDataController extends PopulationDataControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/general/PopulationData/";

  public static final String LAYOUT           = "/layout.jsp";

  public static final String VIEWS            = "views";

  public static final String ITEM             = "item";

  private static final long  serialVersionUID = 1256052009335L;

  public PopulationDataController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void search() throws IOException, ServletException
  {
    try
    {
      if (!this.isAsynchronous())
      {
        RedirectUtility utility = new RedirectUtility(req, resp);
        utility.checkURL(this.getClass().getSimpleName(), "search");

        new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "search");

        List<ConditionalAction> actions = new LinkedList<ConditionalAction>();
        actions.add(new ToggleUniversalAction("populationType.negative", "populationType.positive", HealthFacilityDTO.CLASS));

        req.setAttribute("actions", actions);
        req.setAttribute("item", new PopulationDataViewDTO(this.getClientRequest()));
        render("searchComponent.jsp");
      }
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failSearch();
      }
    }

  }

  @Override
  public void failSearch() throws IOException, ServletException
  {
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }

  @Override
  public void searchForPopulationData(String geoId, Integer yearOfData, Boolean populationType) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoId, yearOfData);

      ClientRequestIF request = this.getClientRequest();

      PopulationDataViewDTO[] data = null;

      if (populationType)
      {
        data = PopulationDataViewDTO.getViews(request, geoId, yearOfData);
      }
      else
      {
        data = PopulationDataViewDTO.getFacilityViews(request, geoId, yearOfData);
      }

      JSONObject calcuatedValues = new JSONObject();

      for (PopulationDataViewDTO view : data)
      {
        calcuatedValues.put(view.getGeoEntity(), new JSONArray(Arrays.asList(view.getCalculatedPopulation())));
      }

      PopulationDataViewDTO item = new PopulationDataViewDTO(request);
      item.setGeoEntity(geoId);
      item.setYearOfData(yearOfData);
      item.setPopulationType(populationType);

      String[] keys = { "ConcreteId", "GeoEntity", "YearOfData", "EntityLabel", "Population", "GrowthRate", "Estimated" };

      ColumnSetup population = new ColumnSetup(false, true);
      population.setSum(item.getPopulationType());

      Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();
      map.put("ConcreteId", new ColumnSetup(true, false));
      map.put("GeoEntity", new ColumnSetup(true, false));
      map.put("YearOfData", new ColumnSetup(true, false));
      map.put("PopulationType", new ColumnSetup(true, false));
      map.put("EntityLabel", new ColumnSetup(false, false));
      map.put("Population", population);
      map.put("GrowthRate", new ColumnSetup(false, true));
      map.put("Estimated", new ColumnSetup(true, false));

      req.setAttribute(ITEM, item);
      req.setAttribute("calculatedValues", calcuatedValues);
      req.setAttribute("grid", new ViewDataGrid(item, map, keys, data));
      req.setAttribute("entity", GeoEntityDTO.searchByGeoId(request, geoId));

      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        String year = ( yearOfData == null ? null : yearOfData.toString() );
        String failPopulationType = populationType != null ? populationType.toString() : "true";

        this.failSearchForPopulationData(geoId, year, failPopulationType);
      }
    }
  }

  @Override
  public void failSearchForPopulationData(String geoId, String yearOfData, String populationType) throws IOException, ServletException
  {
    this.search();
  }

  private void validateParameters(String geoId, Integer year)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoId == null)
    {
      problems.add(new RequiredGeoIdProblemDTO(this.getClientRequest(), req.getLocale()));
    }

    if (year == null)
    {
      problems.add(new RequiredYearProblemDTO(this.getClientRequest(), req.getLocale()));
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

}
