/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.irs;

import java.util.List;
import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class OperatorSprayGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF      request;

  private OperatorSprayViewDTO dto;

  public OperatorSprayGridBuilder(ClientRequestIF request, OperatorSprayViewDTO dto)
  {
    this.request = request;
    this.dto = dto;
  }

  public DataGrid build()
  {
    HouseholdSprayStatusViewDTO view = new HouseholdSprayStatusViewDTO(request);
    view.setValue(HouseholdSprayStatusViewDTO.SPRAY, dto.getConcreteId());

    HouseholdSprayStatusViewDTO[] data = dto.getStatus();

    String[] keys = this.getKeys();

    Map<String, ColumnSetup> map = getColumns(keys, 2, false);

    List<SprayMethodDTO> method = dto.getSprayMethod();

    if (method.contains(SprayMethodDTO.MOP_UP))
    {      
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.HOUSEHOLDS, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.STRUCTURES, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.STRUCTURETYPE, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.SPRAYEDHOUSEHOLDS, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.SPRAYEDSTRUCTURES, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.REASONNOTSPRAYED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.PREVSPRAYEDHOUSEHOLDS, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.PREVSPRAYEDSTRUCTURES, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.ROOMS, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.VERANDAS, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.CATTLESHEDS, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.NUMBEROFPEOPLE, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.PEOPLE, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.BEDNETS, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.ROOMSWITHBEDNETS, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.LOCKED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.VERANDASLOCKED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.CATTLESHEDS, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.REFUSED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.VERANDASREFUSED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.CATTLESHEDSREFUSED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.OTHER, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.VERANDASOTHER, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.CATTLESHEDSOTHER, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.WRONGSURFACE, false);
      
      // new as of 3792
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDSICK, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDLOCKED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDFUNERAL, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDREFUSED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDNOONEHOME, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDOTHER, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.NUMBERMALESPROTECTED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.NUMBERFEMALESPROTECTED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.NUMBERPREGNANTWOMENPROTECTED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVEPROTECTED, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.NUMBERROOMSNOTSPRAYEDSICK, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.NUMBERITNSINUSE, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.NUMBERPEOPLESLEEPINGUNDERITNS, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, false);
      GridBuilder.setEditable(map, HouseholdSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, false);
    }
    else
    {
      // The validators here are javascript functions that should exist in:
      // /MDSS/webapp/WEB-INF/dss/vector/solutions/irs/OperatorSpray/viewComponent.jsp
      
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.STRUCTURES, "validateStructures");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.SPRAYEDSTRUCTURES, "validateStructures");
      
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.SPRAYEDHOUSEHOLDS, "validateValue");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.PREVSPRAYEDHOUSEHOLDS, "validateValue");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.PREVSPRAYEDSTRUCTURES, "validateValue");
      
      // new as of 3792
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDSICK, "lteStructures");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDLOCKED, "lteStructures");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDFUNERAL, "lteStructures");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDREFUSED, "lteStructures");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDNOONEHOME, "lteStructures");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDOTHER, "lteStructures");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.NUMBERMALESPROTECTED, "ltePeopleProtected");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.NUMBERFEMALESPROTECTED, "ltePeopleProtected");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.NUMBERPREGNANTWOMENPROTECTED, "lteFemalesProtected");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVEPROTECTED, "ltePeopleProtected");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.NUMBERROOMSNOTSPRAYEDSICK, "lteRooms");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.NUMBERITNSINUSE, "lteItns");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.NUMBERPEOPLESLEEPINGUNDERITNS, "gteZero");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, "ltePeopleSleepingUnderItns");
      GridBuilder.setValidator(map, HouseholdSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, "ltePeopleSleepingUnderItns");
    }

    return new ViewDataGrid(view, map, keys, data);
  }

  private String[] getKeys()
  {
    String[] keys = {
        HouseholdSprayStatusViewDTO.CONCRETEID,
        HouseholdSprayStatusViewDTO.SPRAY,
        HouseholdSprayStatusViewDTO.HOUSEHOLDID,
        HouseholdSprayStatusViewDTO.STRUCTUREID,
        HouseholdSprayStatusViewDTO.HOUSEHOLDS,
        HouseholdSprayStatusViewDTO.STRUCTURES,
        HouseholdSprayStatusViewDTO.STRUCTURETYPE,
        
        HouseholdSprayStatusViewDTO.SPRAYEDHOUSEHOLDS,
        HouseholdSprayStatusViewDTO.SPRAYEDSTRUCTURES,
        
        HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDSICK,
        HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDLOCKED,
        HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDFUNERAL,
        HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDREFUSED,
        HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDNOONEHOME,
        HouseholdSprayStatusViewDTO.STRUCTURESNOTSPRAYEDOTHER,
        
        HouseholdSprayStatusViewDTO.REASONNOTSPRAYED,
        HouseholdSprayStatusViewDTO.PREVSPRAYEDHOUSEHOLDS,
        HouseholdSprayStatusViewDTO.PREVSPRAYEDSTRUCTURES,
        HouseholdSprayStatusViewDTO.ROOMS,
        HouseholdSprayStatusViewDTO.SPRAYEDROOMS,
        HouseholdSprayStatusViewDTO.LOCKED,
        HouseholdSprayStatusViewDTO.REFUSED,
        HouseholdSprayStatusViewDTO.WRONGSURFACE,
        HouseholdSprayStatusViewDTO.NUMBERROOMSNOTSPRAYEDSICK,
        HouseholdSprayStatusViewDTO.OTHER,
        
        HouseholdSprayStatusViewDTO.VERANDAS,
        HouseholdSprayStatusViewDTO.VERANDASSPRAYED,
        HouseholdSprayStatusViewDTO.VERANDASLOCKED,
        HouseholdSprayStatusViewDTO.VERANDASREFUSED,
        HouseholdSprayStatusViewDTO.VERANDASOTHER,
        
        HouseholdSprayStatusViewDTO.CATTLESHEDS,
        HouseholdSprayStatusViewDTO.CATTLESHEDSSPRAYED,
        HouseholdSprayStatusViewDTO.CATTLESHEDSOTHER,
        HouseholdSprayStatusViewDTO.CATTLESHEDSLOCKED,
        HouseholdSprayStatusViewDTO.CATTLESHEDSREFUSED,
        
        HouseholdSprayStatusViewDTO.NUMBEROFPEOPLE,
        HouseholdSprayStatusViewDTO.PEOPLE,
        HouseholdSprayStatusViewDTO.NUMBERMALESPROTECTED,
        HouseholdSprayStatusViewDTO.NUMBERFEMALESPROTECTED,
        HouseholdSprayStatusViewDTO.NUMBERPREGNANTWOMENPROTECTED,
        HouseholdSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVEPROTECTED,
        
        HouseholdSprayStatusViewDTO.BEDNETS,
        HouseholdSprayStatusViewDTO.ROOMSWITHBEDNETS,
        HouseholdSprayStatusViewDTO.NUMBERITNSINUSE,
        HouseholdSprayStatusViewDTO.NUMBERPEOPLESLEEPINGUNDERITNS,
        HouseholdSprayStatusViewDTO.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS,
        HouseholdSprayStatusViewDTO.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS
      };

    

    upperFirstCharacter(keys);

    return keys;
  }
}
