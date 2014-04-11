package dss.vector.solutions.irs;

import java.util.List;
import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class TeamSprayGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF  request;

  private TeamSprayViewDTO dto;

  public TeamSprayGridBuilder(ClientRequestIF request, TeamSprayViewDTO dto)
  {
    this.request = request;
    this.dto = dto;
  }

  public DataGrid build()
  {
    OperatorSprayStatusViewDTO view = new OperatorSprayStatusViewDTO(request);
    view.setValue(OperatorSprayStatusViewDTO.SPRAY, dto.getConcreteId());

    OperatorSprayStatusViewDTO[] data = dto.getStatus();
    
    String[] keys = this.getKeys();
    
    Map<String, ColumnSetup> map = getColumns(keys, 3, false);
    
    List<SprayMethodDTO> method = dto.getSprayMethod();
    
    if(method.contains(SprayMethodDTO.MOP_UP))
    {
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.HOUSEHOLDS, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.STRUCTURES, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.PREVSPRAYEDHOUSEHOLDS, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.PREVSPRAYEDSTRUCTURES, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.ROOMS, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.VERANDAS, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.CATTLESHEDS, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.NUMBEROFPEOPLE, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.PEOPLE, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.BEDNETS, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.ROOMSWITHBEDNETS, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.LOCKED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.VERANDASLOCKED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.CATTLESHEDSLOCKED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.REFUSED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.VERANDASREFUSED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.CATTLESHEDSREFUSED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.OTHER, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.VERANDASOTHER, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.CATTLESHEDSOTHER, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.WRONGSURFACE, false);
    }

    return new ViewDataGrid(view, map, keys, data);
  }

  private String[] getKeys()
  {
    String[] keys = {
        OperatorSprayStatusViewDTO.CONCRETEID,
        OperatorSprayStatusViewDTO.SPRAY,
        OperatorSprayStatusViewDTO.OPERATORLABEL,
        OperatorSprayStatusViewDTO.SPRAYOPERATOR,
        OperatorSprayStatusViewDTO.OPERATORTARGET,
        OperatorSprayStatusViewDTO.RECEIVED,
        OperatorSprayStatusViewDTO.REFILLS,
        OperatorSprayStatusViewDTO.RETURNED,  
        OperatorSprayStatusViewDTO.USED,  
        OperatorSprayStatusViewDTO.HOUSEHOLDS,  
        OperatorSprayStatusViewDTO.STRUCTURES,  
        OperatorSprayStatusViewDTO.SPRAYEDHOUSEHOLDS,  
        OperatorSprayStatusViewDTO.SPRAYEDSTRUCTURES,  
        OperatorSprayStatusViewDTO.PREVSPRAYEDHOUSEHOLDS,  
        OperatorSprayStatusViewDTO.PREVSPRAYEDSTRUCTURES,  
        OperatorSprayStatusViewDTO.ROOMS,
        OperatorSprayStatusViewDTO.SPRAYEDROOMS,
        OperatorSprayStatusViewDTO.VERANDAS,
        OperatorSprayStatusViewDTO.VERANDASSPRAYED,
        OperatorSprayStatusViewDTO.CATTLESHEDS,
        OperatorSprayStatusViewDTO.CATTLESHEDSSPRAYED,
        OperatorSprayStatusViewDTO.NUMBEROFPEOPLE,
        OperatorSprayStatusViewDTO.PEOPLE,
        OperatorSprayStatusViewDTO.BEDNETS,
        OperatorSprayStatusViewDTO.ROOMSWITHBEDNETS,
        OperatorSprayStatusViewDTO.LOCKED,
        OperatorSprayStatusViewDTO.VERANDASLOCKED,
        OperatorSprayStatusViewDTO.CATTLESHEDSLOCKED,
        OperatorSprayStatusViewDTO.REFUSED,
        OperatorSprayStatusViewDTO.VERANDASREFUSED,
        OperatorSprayStatusViewDTO.CATTLESHEDSREFUSED,
        OperatorSprayStatusViewDTO.OTHER,
        OperatorSprayStatusViewDTO.VERANDASOTHER,
        OperatorSprayStatusViewDTO.CATTLESHEDSOTHER,
        OperatorSprayStatusViewDTO.WRONGSURFACE,
        OperatorSprayStatusViewDTO.NOZZLESUSED,
        OperatorSprayStatusViewDTO.PUMPSUSED
      };

    upperFirstCharacter(keys);

    return keys;
  }
}
