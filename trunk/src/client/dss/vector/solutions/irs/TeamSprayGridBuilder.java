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
      
      // new as of 3792
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDSICK, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDLOCKED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDFUNERAL, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDREFUSED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDNOONEHOME, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDOTHER, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.NUMBERMALESPROTECTED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.NUMBERFEMALESPROTECTED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.NUMBERPREGNANTWOMENPROTECTED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVEPROTECTED, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.NUMBERROOMSNOTSPRAYEDSICK, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.NUMBERITNSINUSE, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.NUMBERPEOPLESLEEPINGUNDERITNS, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, false);
      GridBuilder.setEditable(map, OperatorSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, false);
    }
    else
    {
      // The validators here are javascript functions that should exist in:
      // /MDSS/webapp/WEB-INF/dss/vector/solutions/irs/TeamSpray/viewComponent.jsp
      
      // new as of 3792
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDSICK, "lteStructures");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDLOCKED, "lteStructures");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDFUNERAL, "lteStructures");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDREFUSED, "lteStructures");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDNOONEHOME, "lteStructures");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDOTHER, "lteStructures");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.NUMBERMALESPROTECTED, "ltePeopleProtected");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.NUMBERFEMALESPROTECTED, "ltePeopleProtected");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.NUMBERPREGNANTWOMENPROTECTED, "lteFemalesProtected");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVEPROTECTED, "ltePeopleProtected");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.NUMBERROOMSNOTSPRAYEDSICK, "lteRooms");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.NUMBERITNSINUSE, "lteItns");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.NUMBERPEOPLESLEEPINGUNDERITNS, "gteZero");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, "ltePeopleSleepingUnderItns");
      GridBuilder.setValidator(map, OperatorSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, "ltePeopleSleepingUnderItns");
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
        
        OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDSICK,
        OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDLOCKED,
        OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDFUNERAL,
        OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDREFUSED,
        OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDNOONEHOME,
        OperatorSprayStatusViewDTO.STRUCTURESNOTSPRAYEDOTHER,
        
        OperatorSprayStatusViewDTO.PREVSPRAYEDHOUSEHOLDS,  
        OperatorSprayStatusViewDTO.PREVSPRAYEDSTRUCTURES,  
        OperatorSprayStatusViewDTO.ROOMS,
        OperatorSprayStatusViewDTO.SPRAYEDROOMS,
        
        OperatorSprayStatusViewDTO.LOCKED,
        OperatorSprayStatusViewDTO.REFUSED,
        OperatorSprayStatusViewDTO.WRONGSURFACE,
        OperatorSprayStatusViewDTO.NUMBERROOMSNOTSPRAYEDSICK,
        OperatorSprayStatusViewDTO.OTHER,
        
        OperatorSprayStatusViewDTO.VERANDAS,
        OperatorSprayStatusViewDTO.VERANDASSPRAYED,
        OperatorSprayStatusViewDTO.VERANDASLOCKED,
        OperatorSprayStatusViewDTO.VERANDASREFUSED,
        OperatorSprayStatusViewDTO.VERANDASOTHER,
        
        OperatorSprayStatusViewDTO.CATTLESHEDS,
        OperatorSprayStatusViewDTO.CATTLESHEDSSPRAYED,
        OperatorSprayStatusViewDTO.CATTLESHEDSLOCKED,
        OperatorSprayStatusViewDTO.CATTLESHEDSREFUSED,
        OperatorSprayStatusViewDTO.CATTLESHEDSOTHER,
        
        OperatorSprayStatusViewDTO.NUMBEROFPEOPLE,
        OperatorSprayStatusViewDTO.PEOPLE,
        OperatorSprayStatusViewDTO.NUMBERMALESPROTECTED,
        OperatorSprayStatusViewDTO.NUMBERFEMALESPROTECTED,
        OperatorSprayStatusViewDTO.NUMBERPREGNANTWOMENPROTECTED,
        OperatorSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVEPROTECTED,
        
        OperatorSprayStatusViewDTO.BEDNETS,
        OperatorSprayStatusViewDTO.ROOMSWITHBEDNETS,
        OperatorSprayStatusViewDTO.NUMBERITNSINUSE,
        OperatorSprayStatusViewDTO.NUMBERPEOPLESLEEPINGUNDERITNS,
        OperatorSprayStatusViewDTO.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS,
        OperatorSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS,
        
        OperatorSprayStatusViewDTO.NOZZLESUSED,
        OperatorSprayStatusViewDTO.PUMPSUSED
      };

    upperFirstCharacter(keys);

    return keys;
  }
}
