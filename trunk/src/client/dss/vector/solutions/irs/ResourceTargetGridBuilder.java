package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class ResourceTargetGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF  request;

  private String[] targetIds;
  
  private MalariaSeasonDTO season;

  public ResourceTargetGridBuilder(ClientRequestIF request, String[] targetIds, MalariaSeasonDTO season)
  {
    this.request = request;
    this.targetIds = targetIds;
    this.season = season;
  }

  public DataGrid build()
  {
    ResourceTargetViewDTO view = new ResourceTargetViewDTO(request);
    ResourceTargetViewDTO[] data = ResourceTargetViewDTO.getResourceTargets(request, targetIds, season);

    String[] keys = this.getKeys();
    
    Map<String, ColumnSetup> map = GridBuilder.getColumns(keys, 3, false);
    
    GridBuilder.setEditable(map, ResourceTargetViewDTO.TARGETERNAME, false);
    
    return new ViewDataGrid(view, map, keys, data);
  }
  
  private String[] getKeys()
  {
    List<String> keys = new LinkedList<String>();

    keys.add(ResourceTargetViewDTO.TARGETID);
    keys.add(ResourceTargetViewDTO.SEASON);
    keys.add(ResourceTargetViewDTO.TARGETER);
    keys.add(ResourceTargetViewDTO.TARGETERNAME);

    for (int i = 0; i <= 52; i++)
    {
      keys.add(ResourceTargetViewDTO.TARGET + i);
    }

    String[] array = keys.toArray(new String[keys.size()]);

    upperFirstCharacter(array);

    return array;
  }
}