package dss.vector.solutions.irs;

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
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.business.generation.GenerationUtil;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.EpiDateDTO;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.Halp;
import dss.vector.solutions.util.RedirectUtility;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class GeoTargetController extends GeoTargetControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/GeoTarget/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1240267414799L;

  public GeoTargetController(HttpServletRequest req, HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void view(GeoEntityDTO geoEntity, MalariaSeasonDTO season, Boolean showChildren) throws IOException, ServletException
  {
    try
    {
      validateParameters(geoEntity, season);

      ClientRequestIF clientRequest = super.getClientRequest();

      GeoTargetViewDTO view = new GeoTargetViewDTO(clientRequest);
      view.setSeason(season);
      view.setGeoEntity(geoEntity);

      GeoTargetViewDTO[] data = GeoTargetViewDTO.getGeoTargetViews(clientRequest, geoEntity.getGeoId(), season);

      EpiDateDTO[] weeks = season.getEpiWeeks();

      String[] keys = this.getKeys(weeks);
      Map<String, ColumnSetup> map = this.getColumns(weeks);

      JSONObject calculated = this.getCalculatedTargets(data);

      req.setAttribute("item", view);
      req.setAttribute("grid", new ViewDataGrid(view, map, keys, data));
      req.setAttribute("calculated", calculated);

      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.viewAll();
      }
    }
  }

  private void validateParameters(GeoEntityDTO geoEntity, MalariaSeasonDTO season)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    if (geoEntity == null)
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

  public void failView(GeoEntityDTO geoEntinty, Integer year, Boolean showChildren) throws IOException, ServletException
  {
    this.viewAll();

  }

  public void viewAll() throws IOException, ServletException
  {
    new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

    ClientRequestIF request = super.getClientSession().getRequest();

    req.setAttribute("seasons", Arrays.asList(MalariaSeasonDTO.getAllForDisease(request)));
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  private String[] getKeys(EpiDateDTO[] weeks)
  {
    List<String> keys = new LinkedList<String>();

    keys.add(GeoTargetViewDTO.TARGETID);
    keys.add(GeoTargetViewDTO.GEOENTITY);
    keys.add(GeoTargetViewDTO.ENTITYNAME);
    keys.add(GeoTargetViewDTO.SEASON);

    for (EpiDateDTO week : weeks)
    {
      Integer numberOfWeeks = week.getNumberOfEpiWeeks();
      int index = ( week.getPeriod() % numberOfWeeks );
      String key = GenerationUtil.upperFirstCharacter(GeoTargetViewDTO.TARGET + index);

      keys.add(key);
    }

    String[] array = keys.toArray(new String[keys.size()]);

    this.upperFirstCharacter(array);

    return array;
  }

  private Map<String, ColumnSetup> getColumns(EpiDateDTO[] weeks)
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    map.put("TargetId", new ColumnSetup(true, false));
    map.put("GeoEntity", new ColumnSetup(true, false));
    map.put("EntityName", new ColumnSetup(false, false));
    map.put("Season", new ColumnSetup(true, false));

    for (EpiDateDTO week : weeks)
    {
      String startDate = Halp.getFormatedDate(req, week.getStartDate());
      String endDate = Halp.getFormatedDate(req, week.getEndDate());
      Integer numberOfWeeks = week.getNumberOfEpiWeeks();

      int index = ( week.getPeriod() % numberOfWeeks );
      String key = GenerationUtil.upperFirstCharacter(GeoTargetViewDTO.TARGET + index);
      String label = new Integer(index + 1).toString();

      ColumnSetup setup = new ColumnSetup(false, true);
      setup.setSum(true);
      setup.setTitle(startDate + " -> " + endDate);
      setup.setLabel(label);
      setup.setWidth(20);

      map.put(key, setup);
    }

    return map;
  }

  private JSONObject getCalculatedTargets(GeoTargetViewDTO[] data) throws JSONException
  {
    JSONObject calcuatedTargets = new JSONObject();

    for (GeoTargetViewDTO geoTarget : data)
    {
      calcuatedTargets.put(geoTarget.getGeoEntity().getId(), new JSONArray(Arrays.asList(geoTarget.getCalculatedTargets())));
    }

    return calcuatedTargets;
  }

  private void upperFirstCharacter(String[] array)
  {
    for (int i = 0; i < array.length; i++)
    {
      array[i] = GenerationUtil.upperFirstCharacter(array[i]);
    }
  }

}
