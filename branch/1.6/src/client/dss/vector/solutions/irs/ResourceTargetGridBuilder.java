package dss.vector.solutions.irs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.general.EpiDateDTO;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.util.Halp;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class ResourceTargetGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF  request;

  private ResourceTargetViewDTO[] data;
    
  private MalariaSeasonDTO season;
  
  private boolean sum;
  
  private HttpServletRequest req;

  public ResourceTargetGridBuilder(ClientRequestIF request, ResourceTargetViewDTO[] data, MalariaSeasonDTO season, boolean sum, HttpServletRequest req)
  {
    this.request = request;
    this.data = data;
    this.season = season;
    this.sum = sum;
    this.req = req;
  }

  public DataGrid build()
  {
    ResourceTargetViewDTO view = new ResourceTargetViewDTO(request);

    
    EpiDateDTO[] weeks = season.getEpiWeeks();

    String[] keys = this.getKeys(weeks);
    Map<String, ColumnSetup> map = this.getColumns(weeks);
    
    GridBuilder.setEditable(map, ResourceTargetViewDTO.TARGETERNAME, false);
    
    return new ViewDataGrid(view, map, keys, data);
  }
  
  private Map<String, ColumnSetup> getColumns(EpiDateDTO[] weeks)
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    map.put("TargetId", new ColumnSetup(true, false));
    map.put("Targeter", new ColumnSetup(true, false));
    map.put("TargeterName", new ColumnSetup(false, false));
    map.put("Season", new ColumnSetup(true, false));

    for (EpiDateDTO week : weeks)
    {
      String startDate = Halp.getFormatedDate(req, week.getStartDate());
      String endDate = Halp.getFormatedDate(req, week.getEndDate());
      Integer numberOfWeeks = week.getNumberOfEpiWeeks();

      int index = ( week.getPeriod() % numberOfWeeks );
      String key = CommonGenerationUtil.upperFirstCharacter(ResourceTargetViewDTO.TARGET + index);
      String label = new Integer(index + 1).toString();

      ColumnSetup setup = new ColumnSetup(false, true);
      setup.setSum(sum);
      setup.setTitle(startDate + " -> " + endDate);
      setup.setLabel(label);
      setup.setWidth(20);
      setup.setIndicateRequired(false);

      map.put(key, setup);
    }

    return map;
  }
  
  private String[] getKeys(EpiDateDTO[] weeks)
  {
    List<String> keys = new LinkedList<String>();

    keys.add(ResourceTargetViewDTO.TARGETID);
    keys.add(ResourceTargetViewDTO.SEASON);
    keys.add(ResourceTargetViewDTO.TARGETER);
    keys.add(ResourceTargetViewDTO.TARGETERNAME);

    for (EpiDateDTO week : weeks)
    {
      Integer numberOfWeeks = week.getNumberOfEpiWeeks();
      int index = ( week.getPeriod() % numberOfWeeks );
      String key = CommonGenerationUtil.upperFirstCharacter(ResourceTargetViewDTO.TARGET + index);

      keys.add(key);
    }

    String[] array = keys.toArray(new String[keys.size()]);

    upperFirstCharacter(array);

    return array;
  }  
}