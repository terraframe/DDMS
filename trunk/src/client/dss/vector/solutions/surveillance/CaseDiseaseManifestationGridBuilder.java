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

public class CaseDiseaseManifestationGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF       request;

  private AggregatedCaseViewDTO dto;

  private boolean               readonly;

  public CaseDiseaseManifestationGridBuilder(ClientRequestIF request, AggregatedCaseViewDTO dto, boolean readonly)
  {
    this.request = request;
    this.dto = dto;
    this.readonly = readonly;
  }

  public DataGrid build()
  {
    CaseDiseaseManifestationViewDTO[] data = dto.getDiseaseManifestations();

    DataGrid staticGrid = this.buildStatic(data);
    DataGrid dynamicGrid = this.buildDynamic(data);
    
    return new CompositeDataGrid("diseaseManifestation", dto.getIsCaseDiseaseManifestationReadable(), staticGrid, dynamicGrid);
  }

  private DataGrid buildStatic(CaseDiseaseManifestationViewDTO[] data)
  {
    CaseDiseaseManifestationViewDTO view = new CaseDiseaseManifestationViewDTO(request);
    
    String[] keys = new String[] { CaseDiseaseManifestationViewDTO.CONCRETEID, CaseDiseaseManifestationViewDTO.TERM};
    
    upperFirstCharacter(keys);

    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);

    GridBuilder.setEditable(columns, CaseDiseaseManifestationViewDTO.TERM, false);

    return new ViewDataGrid(view, columns, keys, data);
  }
  
  private DataGrid buildDynamic(CaseDiseaseManifestationViewDTO[] views)
  {
    String[] ids = this.getIds(views);
    
    CaseDiseaseManifestationAmountViewDTO view = new CaseDiseaseManifestationAmountViewDTO(request);
    CaseDiseaseManifestationAmountViewDTO[][] data = CaseDiseaseManifestationViewDTO.getAmountsForViews(request, ids);
    
    String[] keys = new String[] { CaseDiseaseManifestationAmountViewDTO.TERM, CaseDiseaseManifestationAmountViewDTO.AMOUNT};
    upperFirstCharacter(keys);
    
    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);
    
    if(readonly)
    {
      GridBuilder.setEditable(columns, CaseDiseaseManifestationAmountViewDTO.AMOUNT, false);
    }
    
    TermSetup setup = new TermSetup(CaseDiseaseManifestationAmountViewDTO.AMOUNT, CaseDiseaseManifestationAmountViewDTO.TERM);

    return new DynamicTermDataGrid(view, columns, keys, setup, CaseDiseaseManifestationViewDTO.CLASS, CaseDiseaseManifestationViewDTO.DISEASECATEGORY, null, data);
  }

  private String[] getIds(CaseDiseaseManifestationViewDTO[] views)
  {
    String[] ids = new String[views.length];
    
    for(int i = 0; i < ids.length; i++)
    {
      ids[i] = views[i].getConcreteId();
    }
    return ids;
  }
}
