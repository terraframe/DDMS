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
import dss.vector.solutions.util.yui.CompositeDataGrid;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.DynamicTermDataGrid;
import dss.vector.solutions.util.yui.TermSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class CaseDiagnosisTypeGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF       request;

  private AggregatedCaseViewDTO dto;

  private boolean               readonly;

  public CaseDiagnosisTypeGridBuilder(ClientRequestIF request, AggregatedCaseViewDTO dto, boolean readonly)
  {
    this.request = request;
    this.dto = dto;
    this.readonly = readonly;
  }

  public DataGrid build()
  {
    CaseDiagnosisTypeViewDTO[] data = dto.getDiagnosticTypes();

    DataGrid staticGrid = this.buildStatic(data);
    DataGrid dynamicGrid = this.buildDynamic(data);
    
    return new CompositeDataGrid("diagnosisType", dto.getIsCaseDiagnosisTypeReadable(), staticGrid, dynamicGrid);
  }

  private DataGrid buildStatic(CaseDiagnosisTypeViewDTO[] data)
  {
    CaseDiagnosisTypeViewDTO view = new CaseDiagnosisTypeViewDTO(request);
    
    String[] keys = new String[] { CaseDiagnosisTypeViewDTO.CONCRETEID, CaseDiagnosisTypeViewDTO.TERM};
    
    upperFirstCharacter(keys);

    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);

    GridBuilder.setEditable(columns, CaseDiagnosisTypeViewDTO.TERM, false);

    return new ViewDataGrid(view, columns, keys, data);
  }
  
  private DataGrid buildDynamic(CaseDiagnosisTypeViewDTO[] views)
  {
    String[] ids = this.getIds(views);
    
    CaseDiagnosisTypeAmountViewDTO view = new CaseDiagnosisTypeAmountViewDTO(request);
    CaseDiagnosisTypeAmountViewDTO[][] data = CaseDiagnosisTypeViewDTO.getAmountsForViews(request, ids);
    
    String[] keys = new String[] { CaseDiagnosisTypeAmountViewDTO.TERM, CaseDiagnosisTypeAmountViewDTO.AMOUNT};
    upperFirstCharacter(keys);
    
    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);
    
    if(readonly)
    {
      GridBuilder.setEditable(columns, CaseDiagnosisTypeAmountViewDTO.AMOUNT, false);
    }
    
    TermSetup setup = new TermSetup(CaseDiagnosisTypeAmountViewDTO.AMOUNT, CaseDiagnosisTypeAmountViewDTO.TERM);

    return new DynamicTermDataGrid(view, columns, keys, setup, CaseDiagnosisTypeViewDTO.CLASS, CaseDiagnosisTypeViewDTO.DIAGNOSISCATEGORY, null, data);
  }

  private String[] getIds(CaseDiagnosisTypeViewDTO[] views)
  {
    String[] ids = new String[views.length];
    
    for(int i = 0; i < ids.length; i++)
    {
      ids[i] = views[i].getConcreteId();
    }
    return ids;
  }
}
