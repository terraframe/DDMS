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

public class IndividualPremiseGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF            request;

  private ControlInterventionViewDTO dto;

  public IndividualPremiseGridBuilder(ClientRequestIF request, ControlInterventionViewDTO dto)
  {
    this.request = request;
    this.dto = dto;
  }

  public DataGrid build()
  {
    IndividualPremiseVisitViewDTO[] views = dto.getIndividualPremiseViews();
    IndividualPremiseVisitMethodViewDTO[][] methods = IndividualPremiseVisitViewDTO.getInterventionMethodsForViews(request, views);

    IndividualPremiseVisitViewDTO view = new IndividualPremiseVisitViewDTO(request);
    DataGrid staticGrid = this.getStaticGrid(view, views);
    DataGrid dynamicGrid = this.getMethodGrid(view, methods);

    return new CompositeDataGrid("individualPremise", dto.isIndividulPremiseUniversalReadable(), staticGrid, dynamicGrid);
  }

  private DataGrid getMethodGrid(IndividualPremiseVisitViewDTO view, IndividualPremiseVisitMethodViewDTO[][] data)
  {
    IndividualPremiseVisitMethodViewDTO method = new IndividualPremiseVisitMethodViewDTO(request);
    method.setUsed(false);

    String[] keys = this.getMethodKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 1, true);
    
    GridBuilder.setValidator(columns, IndividualPremiseVisitMethodViewDTO.USED, "validateMethod");

    String label = view.getInterventionMethodMd().getDisplayLabel();
    TermSetup setup = new TermSetup(IndividualPremiseVisitMethodViewDTO.USED, IndividualPremiseVisitMethodViewDTO.TERM);

    DynamicTermDataGrid dynamicGenerator = new DynamicTermDataGrid(method, columns, keys, setup, IndividualPremiseVisitViewDTO.CLASS, IndividualPremiseVisitViewDTO.INTERVENTIONMETHOD, label, data);
    return dynamicGenerator;
  }

  private DataGrid getStaticGrid(IndividualPremiseVisitViewDTO view, IndividualPremiseVisitViewDTO[] data)
  {
    String[] keys = getViewKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 2, false);
       
    GridBuilder.setValidator(columns, IndividualPremiseVisitViewDTO.TREATED, "validateTreated");
    GridBuilder.setValidator(columns, IndividualPremiseVisitViewDTO.REASONSFORNOTTREATED, "validateNotTreated");
    GridBuilder.setEditable(columns, IndividualPremiseVisitViewDTO.ENTITYLABEL, false);
    ColumnSetup setup = GridBuilder.getSetup(columns, IndividualPremiseVisitViewDTO.VISITED);
    setup.setIncludeBlank(true);

    return new ViewDataGrid(view, columns, keys, data);
  }
  
  private String[] getViewKeys()
  {
    String[] keys = new String[] { IndividualPremiseVisitViewDTO.CONCRETEID, IndividualPremiseVisitViewDTO.GEOENTITY, IndividualPremiseVisitViewDTO.ENTITYLABEL, IndividualPremiseVisitViewDTO.VISITED, IndividualPremiseVisitViewDTO.TREATED, IndividualPremiseVisitViewDTO.REASONSFORNOTTREATED };

    upperFirstCharacter(keys);

    return keys;
  }

  private String[] getMethodKeys()
  {
    String[] keys = new String[] { IndividualPremiseVisitMethodViewDTO.TERM, IndividualPremiseVisitMethodViewDTO.USED };

    upperFirstCharacter(keys);

    return keys;
  }
}
