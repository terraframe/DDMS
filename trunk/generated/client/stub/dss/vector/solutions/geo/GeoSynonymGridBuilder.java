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
package dss.vector.solutions.geo;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class GeoSynonymGridBuilder extends GridBuilder implements Reloadable
{
  private GeoSynonymViewDTO[] data;

  private ClientRequestIF                 request;

  private boolean                         readOnly;

  private GeoSynonymGridBuilder(ClientRequestIF request, boolean readOnly)
  {
    this.request = request;
    this.readOnly = readOnly;
  }

  public GeoSynonymGridBuilder(ClientRequestIF request, boolean readOnly, GeoSynonymViewDTO[] data)
  {
    this(request, readOnly);

    this.data = data;
  }

  public DataGrid build()
  {
    String[] keys = getKeys();

    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);

    if (readOnly)
    {
      for (String key : columns.keySet())
      {
        ColumnSetup column = columns.get(key);

        column.setEditable(false);
      }
    }

    ViewDataGrid grid = new ViewDataGrid("synonymNames", true, new GeoSynonymViewDTO(request), columns, keys, data);
    grid.setDeletable(!readOnly);
    grid.setAddButton(!readOnly);

    return grid;
  }

  private String[] getKeys()
  {
    String[] keys = new String[] { GeoSynonymViewDTO.GEOSYNONYMID, GeoSynonymViewDTO.SYNONYMNAME };

    upperFirstCharacter(keys);

    return keys;
  }
}
