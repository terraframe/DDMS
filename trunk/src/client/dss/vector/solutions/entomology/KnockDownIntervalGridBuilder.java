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
package dss.vector.solutions.entomology;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.entomology.assay.KnockDownAssayDTO;
import dss.vector.solutions.entomology.assay.KnockDownIntervalViewDTO;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class KnockDownIntervalGridBuilder extends GridBuilder implements Reloadable
{
  private KnockDownAssayDTO dto;

  private ClientRequestIF   request;

  private boolean           readOnly;

  public KnockDownIntervalGridBuilder(ClientRequestIF request, KnockDownAssayDTO dto, boolean readOnly)
  {
    this.request = request;
    this.dto = dto;
    this.readOnly = readOnly;
  }

  public DataGrid build()
  {
    KnockDownIntervalViewDTO[] data = dto.getIntervals();

    String[] keys = getKeys();

    Map<String, ColumnSetup> columns = getColumns(keys, 2, true);

    GridBuilder.setEditable(columns, KnockDownIntervalViewDTO.CONCRETEID, false);
    GridBuilder.setEditable(columns, KnockDownIntervalViewDTO.ASSAY, false);
    GridBuilder.setValidator(columns, KnockDownIntervalViewDTO.INTERVALTIME, "MDSS.validateNumber");
    GridBuilder.setValidator(columns, KnockDownIntervalViewDTO.AMOUNT, "MDSS.validateNumber");

    if (readOnly)
    {
      for (String key : columns.keySet())
      {
        ColumnSetup column = columns.get(key);

        column.setEditable(false);
      }
    }

    ViewDataGrid grid = new ViewDataGrid("intervals", true, new KnockDownIntervalViewDTO(request), columns, keys, data);
    grid.setDeletable(!readOnly);
    grid.setAddButton(!readOnly);

    return grid;
  }

  private String[] getKeys()
  {
    String[] keys = new String[] { KnockDownIntervalViewDTO.CONCRETEID, KnockDownIntervalViewDTO.ASSAY, KnockDownIntervalViewDTO.INTERVALTIME, KnockDownIntervalViewDTO.AMOUNT };

    upperFirstCharacter(keys);

    return keys;
  }
}
