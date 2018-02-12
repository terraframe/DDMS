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

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class ConfigurationGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF request;

  public ConfigurationGridBuilder(ClientRequestIF request)
  {
    this.request = request;
  }

  @Override
  public DataGrid build()
  {
    InsecticideNozzleViewDTO view = new InsecticideNozzleViewDTO(request);
    InsecticideNozzleViewDTO[] data = InsecticideNozzleViewDTO.getAll(request);

    String[] keys = this.getKeys();

    Map<String, ColumnSetup> map = getColumns(keys, 3, false);

    ColumnSetup brandSetup = GridBuilder.getSetup(map, InsecticideNozzleViewDTO.BRAND);
    brandSetup.setType(InsecticideBrandViewDTO.class.getName());
    brandSetup.setMethod("getNozzleInsecticideBrands");
    brandSetup.setGetter("getBrandView");
    brandSetup.setValidator("validateImmutable");

    ColumnSetup nozzleSetup = GridBuilder.getSetup(map, InsecticideNozzleViewDTO.NOZZLE);
    nozzleSetup.setType(NozzleViewDTO.class.getName());
    nozzleSetup.setMethod("getAllActive");
    nozzleSetup.setGetter("getNozzleView");
    nozzleSetup.setValidator("validateImmutable");

    return new ViewDataGrid(view, map, keys, data);
  }

  private String[] getKeys()
  {
    String[] keys = { InsecticideNozzleViewDTO.INSECTICIDENOZZLEID, InsecticideNozzleViewDTO.BRANDLABEL, InsecticideNozzleViewDTO.NOZZLELABEL, InsecticideNozzleViewDTO.CONFIGURATIONDATE, InsecticideNozzleViewDTO.BRAND, InsecticideNozzleViewDTO.NOZZLE, InsecticideNozzleViewDTO.ENABLED };

    upperFirstCharacter(keys);

    return keys;
  }
}
