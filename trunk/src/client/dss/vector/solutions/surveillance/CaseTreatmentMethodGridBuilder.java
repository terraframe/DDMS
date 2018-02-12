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
package dss.vector.solutions.surveillance;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class CaseTreatmentMethodGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF       request;

  private AggregatedCaseViewDTO dto;

  private boolean               readonly;

  public CaseTreatmentMethodGridBuilder(ClientRequestIF request, AggregatedCaseViewDTO dto, boolean readonly)
  {
    this.request = request;
    this.dto = dto;
    this.readonly = readonly;
  }

  public DataGrid build()
  {
    CaseTreatmentMethodViewDTO view = new CaseTreatmentMethodViewDTO(request);
    CaseTreatmentMethodViewDTO[] data = dto.getTreatmentMethods();

    String[] keys = getKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);

    GridBuilder.setEditable(columns, CaseTreatmentMethodViewDTO.TERM, false);

    if (readonly)
    {
      for (String key : columns.keySet())
      {
        ColumnSetup column = columns.get(key);

        column.setEditable(false);
      }
    }

    return new ViewDataGrid("method", dto.getIsCaseTreatmentMethodReadable(), view, columns, keys, data);
  }

  private String[] getKeys()
  {
    String[] keys = new String[] { CaseTreatmentMethodViewDTO.CONCRETEID, CaseTreatmentMethodViewDTO.TERM, CaseTreatmentMethodViewDTO.AMOUNT };

    upperFirstCharacter(keys);

    return keys;
  }}
