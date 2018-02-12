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
package dss.vector.solutions.intervention.monitor;

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

public class AggregatedPremiseGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF            request;

  private ControlInterventionViewDTO dto;

  public AggregatedPremiseGridBuilder(ClientRequestIF request, ControlInterventionViewDTO dto)
  {
    this.request = request;
    this.dto = dto;
  }

  public DataGrid build()
  {
    AggregatedPremiseVisitViewDTO[] views = dto.getAggregatedPremiseViews();
    AggregatedPremiseReasonViewDTO[][] reasons = AggregatedPremiseVisitViewDTO.getNonTreatmentReasonsForViews(request, views);
    AggregatedPremiseMethodViewDTO[][] methods = AggregatedPremiseVisitViewDTO.getInterventionMethodsForViews(request, views);

    AggregatedPremiseVisitViewDTO view = new AggregatedPremiseVisitViewDTO(request);
    DataGrid staticGrid = this.getStaticGrid(view, views);
    DataGrid reasonGrid = this.getReasonGrid(view, reasons);
    DataGrid methodGrid = this.getMethodGrid(view, methods);

    return new CompositeDataGrid("aggregatedPremise", dto.isAggregatedPremiseUniversalReadable(), staticGrid, reasonGrid, methodGrid);
  }

  private DataGrid getMethodGrid(AggregatedPremiseVisitViewDTO view, AggregatedPremiseMethodViewDTO[][] data)
  {
    AggregatedPremiseMethodViewDTO method = new AggregatedPremiseMethodViewDTO(request);

    String[] keys = this.getMethodKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 1, true);
    
    GridBuilder.setValidator(columns, AggregatedPremiseMethodViewDTO.AMOUNT, "MDSS.validateNumber");
    
    String label = view.getInterventionMethodMd().getDisplayLabel();
    TermSetup setup = new TermSetup(AggregatedPremiseMethodViewDTO.AMOUNT, AggregatedPremiseMethodViewDTO.TERM);

    return new DynamicTermDataGrid(method, columns, keys, setup, AggregatedPremiseVisitViewDTO.CLASS, AggregatedPremiseVisitViewDTO.INTERVENTIONMETHOD, label, data);
  }

  private DataGrid getReasonGrid(AggregatedPremiseVisitViewDTO view, AggregatedPremiseReasonViewDTO[][] data)
  {
    AggregatedPremiseReasonViewDTO method = new AggregatedPremiseReasonViewDTO(request);
    
    String[] keys = this.getReasonKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 1, true);
    
    GridBuilder.setValidator(columns, AggregatedPremiseReasonViewDTO.AMOUNT, "MDSS.validateNumber");
        
    String label = view.getNonTreatmentReasonMd().getDisplayLabel();
    TermSetup setup = new TermSetup(AggregatedPremiseReasonViewDTO.AMOUNT, AggregatedPremiseMethodViewDTO.TERM);
    
    return new DynamicTermDataGrid(method, columns, keys, setup, AggregatedPremiseVisitViewDTO.CLASS, AggregatedPremiseVisitViewDTO.NONTREATMENTREASON, label, data);
  }
  
  private DataGrid getStaticGrid(AggregatedPremiseVisitViewDTO view, AggregatedPremiseVisitViewDTO[] data)
  {
    String[] keys = getViewKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 2, false);

    for(int i = 0; i < keys.length; i++)
    {
      if(i > 2)
      {
        ColumnSetup setup = columns.get(keys[i]);
        setup.setValidator("MDSS.validateNumber");
      }
    }

    GridBuilder.setValidator(columns, AggregatedPremiseVisitViewDTO.PREMISESAVAILABLE, "MDSS.validateNumber");
    GridBuilder.setValidator(columns, AggregatedPremiseVisitViewDTO.PREMISESINCLUDED, "MDSS.validateNumber");
    GridBuilder.setEditable(columns, AggregatedPremiseVisitViewDTO.ENTITYLABEL, false);
       
    return new ViewDataGrid(view, columns, keys, data);
  }
  
  private String[] getViewKeys()
  {
    String[] keys = new String[] { AggregatedPremiseVisitViewDTO.CONCRETEID, AggregatedPremiseVisitViewDTO.GEOENTITY, AggregatedPremiseVisitViewDTO.ENTITYLABEL, AggregatedPremiseVisitViewDTO.PREMISESAVAILABLE, AggregatedPremiseVisitViewDTO.PREMISESINCLUDED, AggregatedPremiseVisitViewDTO.PREMISES, AggregatedPremiseVisitViewDTO.VISITED, AggregatedPremiseVisitViewDTO.TREATED};

    upperFirstCharacter(keys);

    return keys;
  }

  private String[] getMethodKeys()
  {
    String[] keys = new String[] { AggregatedPremiseMethodViewDTO.TERM, AggregatedPremiseMethodViewDTO.AMOUNT };

    upperFirstCharacter(keys);

    return keys;
  }

  private String[] getReasonKeys()
  {
    String[] keys = new String[] { AggregatedPremiseReasonViewDTO.TERM, AggregatedPremiseReasonViewDTO.AMOUNT };
    
    upperFirstCharacter(keys);
    
    return keys;
  }
}
