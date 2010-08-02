package dss.vector.solutions.irs;

import java.util.List;
import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class ZoneSprayGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF  request;

  private ZoneSprayViewDTO dto;

  public ZoneSprayGridBuilder(ClientRequestIF request, ZoneSprayViewDTO dto)
  {
    this.request = request;
    this.dto = dto;
  }

  public DataGrid build()
  {
    TeamSprayStatusViewDTO view = new TeamSprayStatusViewDTO(request);
    view.setValue(TeamSprayStatusViewDTO.SPRAY, dto.getConcreteId());

    TeamSprayStatusViewDTO[] data = dto.getStatus();
    
    String[] keys = this.getKeys();
    
    Map<String, ColumnSetup> map = getColumns(keys, 3, false);
    
    List<SprayMethodDTO> method = dto.getSprayMethod();
    
    if(method.contains(SprayMethodDTO.MOP_UP))
    {
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.HOUSEHOLDS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.STRUCTURES, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.PREVSPRAYEDHOUSEHOLDS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.PREVSPRAYEDSTRUCTURES, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.ROOMS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.PEOPLE, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.BEDNETS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.ROOMSWITHBEDNETS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.LOCKED, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.REFUSED, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.OTHER, false);
    }

    return new ViewDataGrid(view, map, keys, data);
  }

  private String[] getKeys()
  {
    String[] keys = { TeamSprayStatusViewDTO.CONCRETEID, TeamSprayStatusViewDTO.SPRAY,  TeamSprayStatusViewDTO.TEAMLABEL, TeamSprayStatusViewDTO.SPRAYTEAM, TeamSprayStatusViewDTO.TEAMLEADER,  TeamSprayStatusViewDTO.TARGET,  TeamSprayStatusViewDTO.RECEIVED,  TeamSprayStatusViewDTO.REFILLS,  TeamSprayStatusViewDTO.RETURNED,  TeamSprayStatusViewDTO.USED,  TeamSprayStatusViewDTO.HOUSEHOLDS,  TeamSprayStatusViewDTO.STRUCTURES,  TeamSprayStatusViewDTO.SPRAYEDHOUSEHOLDS,  TeamSprayStatusViewDTO.SPRAYEDSTRUCTURES,  TeamSprayStatusViewDTO.PREVSPRAYEDHOUSEHOLDS,  TeamSprayStatusViewDTO.PREVSPRAYEDSTRUCTURES,  TeamSprayStatusViewDTO.ROOMS,  TeamSprayStatusViewDTO.SPRAYEDROOMS,  TeamSprayStatusViewDTO.PEOPLE,  TeamSprayStatusViewDTO.BEDNETS,  TeamSprayStatusViewDTO.ROOMSWITHBEDNETS,  TeamSprayStatusViewDTO.LOCKED,  TeamSprayStatusViewDTO.REFUSED,  TeamSprayStatusViewDTO.OTHER};

    upperFirstCharacter(keys);

    return keys;
  }
}
