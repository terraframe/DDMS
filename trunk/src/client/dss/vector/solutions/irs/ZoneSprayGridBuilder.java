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

    if (method.contains(SprayMethodDTO.MOP_UP))
    {
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.HOUSEHOLDS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.STRUCTURES, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.PREVSPRAYEDHOUSEHOLDS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.PREVSPRAYEDSTRUCTURES, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.ROOMS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.VERANDAS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.CATTLESHEDS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.NUMBEROFPEOPLE, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.PEOPLE, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.BEDNETS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.ROOMSWITHBEDNETS, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.LOCKED, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.VERANDASLOCKED, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.CATTLESHEDSLOCKED, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.REFUSED, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.VERANDASREFUSED, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.CATTLESHEDSREFUSED, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.OTHER, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.VERANDASOTHER, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.CATTLESHEDSOTHER, false);
      GridBuilder.setEditable(map, TeamSprayStatusViewDTO.WRONGSURFACE, false);
    }
    else
    {
      // The validators here are javascript functions that should exist in:
      // /MDSS/webapp/WEB-INF/dss/vector/solutions/irs/ZoneSpray/viewComponent.jsp
      
      // new as of 3792
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDSICK, "lteStructures");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDLOCKED, "lteStructures");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDFUNERAL, "lteStructures");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDREFUSED, "lteStructures");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDNOONEHOME, "lteStructures");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDOTHER, "lteStructures");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.NUMBERMALESPROTECTED, "ltePeopleProtected");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.NUMBERFEMALESPROTECTED, "ltePeopleProtected");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.NUMBERPREGNANTWOMENPROTECTED, "lteFemalesProtected");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVEPROTECTED, "ltePeopleProtected");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.NUMBERROOMSNOTSPRAYEDSICK, "lteRooms");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.NUMBERITNSINUSE, "lteItns");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.NUMBERPEOPLESLEEPINGUNDERITNS, "gteZero");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, "ltePeopleSleepingUnderItns");
      GridBuilder.setValidator(map, TeamSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, "ltePeopleSleepingUnderItns");
    }

    return new ViewDataGrid(view, map, keys, data);
  }

  private String[] getKeys()
  {
    String[] keys = {
        TeamSprayStatusViewDTO.CONCRETEID,
        TeamSprayStatusViewDTO.SPRAY,
        TeamSprayStatusViewDTO.TEAMLABEL,
        TeamSprayStatusViewDTO.SPRAYTEAM,
        TeamSprayStatusViewDTO.TEAMLEADER,
        TeamSprayStatusViewDTO.TARGET,
        TeamSprayStatusViewDTO.RECEIVED,
        TeamSprayStatusViewDTO.REFILLS,
        TeamSprayStatusViewDTO.RETURNED,
        TeamSprayStatusViewDTO.USED,
        TeamSprayStatusViewDTO.HOUSEHOLDS,
        TeamSprayStatusViewDTO.STRUCTURES,
        TeamSprayStatusViewDTO.SPRAYEDHOUSEHOLDS,
        TeamSprayStatusViewDTO.SPRAYEDSTRUCTURES,
        TeamSprayStatusViewDTO.PREVSPRAYEDHOUSEHOLDS,
        TeamSprayStatusViewDTO.PREVSPRAYEDSTRUCTURES,
        TeamSprayStatusViewDTO.ROOMS,
        TeamSprayStatusViewDTO.SPRAYEDROOMS,
        TeamSprayStatusViewDTO.VERANDAS,
        TeamSprayStatusViewDTO.VERANDASSPRAYED,
        TeamSprayStatusViewDTO.CATTLESHEDS,
        TeamSprayStatusViewDTO.CATTLESHEDSSPRAYED,
        TeamSprayStatusViewDTO.NUMBEROFPEOPLE,
        TeamSprayStatusViewDTO.PEOPLE,
        TeamSprayStatusViewDTO.BEDNETS,
        TeamSprayStatusViewDTO.ROOMSWITHBEDNETS,
        TeamSprayStatusViewDTO.LOCKED,
        TeamSprayStatusViewDTO.VERANDASLOCKED,
        TeamSprayStatusViewDTO.CATTLESHEDSLOCKED,
        TeamSprayStatusViewDTO.REFUSED,
        TeamSprayStatusViewDTO.VERANDASREFUSED,
        TeamSprayStatusViewDTO.CATTLESHEDSREFUSED,
        TeamSprayStatusViewDTO.OTHER,
        TeamSprayStatusViewDTO.VERANDASOTHER,
        TeamSprayStatusViewDTO.CATTLESHEDSOTHER,
        TeamSprayStatusViewDTO.WRONGSURFACE,
        TeamSprayStatusViewDTO.NOZZLESUSED,
        TeamSprayStatusViewDTO.PUMPSUSED,
        
        // new as of 3792
        TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDSICK,
        TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDLOCKED,
        TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDFUNERAL,
        TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDREFUSED,
        TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDNOONEHOME,
        TeamSprayStatusViewDTO.STRUCTURESNOTSPRAYEDOTHER,
        TeamSprayStatusViewDTO.NUMBERMALESPROTECTED,
        TeamSprayStatusViewDTO.NUMBERFEMALESPROTECTED,
        TeamSprayStatusViewDTO.NUMBERPREGNANTWOMENPROTECTED,
        TeamSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVEPROTECTED,
        TeamSprayStatusViewDTO.NUMBERROOMSNOTSPRAYEDSICK,
        TeamSprayStatusViewDTO.NUMBERITNSINUSE,
        TeamSprayStatusViewDTO.NUMBERPEOPLESLEEPINGUNDERITNS,
        TeamSprayStatusViewDTO.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS,
        TeamSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS
     };

    upperFirstCharacter(keys);

    return keys;
  }
}
