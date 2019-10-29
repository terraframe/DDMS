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
package dss.vector.solutions.general;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class PopulationGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF       request;

  private PopulationDataViewDTO dto;

  public PopulationGridBuilder(ClientRequestIF request, PopulationDataViewDTO dto)
  {
    this.request = request;
    this.dto = dto;
  }

  public DataGrid build()
  {
    PopulationDataViewDTO[] data = null;

    if (dto.getPopulationType())
    {
      data = PopulationDataViewDTO.getViews(request, dto.getGeoEntity(), dto.getYearOfData());
    }
    else
    {
      data = PopulationDataViewDTO.getFacilityViews(request, dto.getGeoEntity(), dto.getYearOfData());
    }

    String[] keys = getKeys();

    Map<String, ColumnSetup> columns = getColumns(keys, 4, true);

    GridBuilder.setEditable(columns, PopulationDataViewDTO.ENTITYLABEL, false);
    GridBuilder.setValidator(columns, PopulationDataViewDTO.POPULATION, "MDSS.validateNumber");
    GridBuilder.setValidator(columns, PopulationDataViewDTO.GROWTHRATE, "MDSS.validateNumber");

    return new ViewDataGrid(dto, columns, keys, data);
  }

  private String[] getKeys()
  {
    String[] keys = new String[] { PopulationDataViewDTO.CONCRETEID, PopulationDataViewDTO.GEOENTITY, PopulationDataViewDTO.YEAROFDATA, PopulationDataViewDTO.ESTIMATED, PopulationDataViewDTO.ENTITYLABEL, PopulationDataViewDTO.POPULATION, PopulationDataViewDTO.GROWTHRATE };

    upperFirstCharacter(keys);

    return keys;
  }
}
