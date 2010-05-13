package dss.vector.solutions.intervention.monitor;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.CompositeDataGrid;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.DynamicTermDataGrid;
import dss.vector.solutions.util.yui.TermSetup;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class PersonInterventionGridBuilder extends GridBuilder
{
  private ClientRequestIF            request;

  private ControlInterventionViewDTO dto;

  public PersonInterventionGridBuilder(ClientRequestIF request, ControlInterventionViewDTO dto)
  {
    this.request = request;
    this.dto = dto;
  }

  public DataGrid build()
  {
    PersonInterventionViewDTO[] views = dto.getPersonInterventionViews();
    PersonInterventionMethodViewDTO[][] methods = PersonInterventionViewDTO.getInterventionMethodsForViews(request, views);

    PersonInterventionViewDTO view = new PersonInterventionViewDTO(request);
    DataGrid staticGrid = this.getStaticGrid(view, views);
    DataGrid methodGrid = this.getMethodGrid(view, methods);

    return new CompositeDataGrid(new DataGrid[] { staticGrid, methodGrid });
  }

  private DataGrid getMethodGrid(PersonInterventionViewDTO view, PersonInterventionMethodViewDTO[][] data)
  {
    PersonInterventionMethodViewDTO method = new PersonInterventionMethodViewDTO(request);

    String[] keys = this.getMethodKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 1, true);
    
    GridBuilder.setValidator(columns, PersonInterventionMethodViewDTO.AMOUNT, "YAHOO.widget.DataTable.validateNumber");
    
    String label = view.getInterventionMethodMd().getDisplayLabel();
    TermSetup setup = new TermSetup(PersonInterventionMethodViewDTO.AMOUNT, PersonInterventionMethodViewDTO.TERM);

    return new DynamicTermDataGrid(method, columns, keys, setup, PersonInterventionViewDTO.CLASS, PersonInterventionViewDTO.INTERVENTIONMETHOD, label, data);
  }

  private DataGrid getStaticGrid(PersonInterventionViewDTO view, PersonInterventionViewDTO[] data)
  {
    String[] keys = getViewKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 2, false);

    GridBuilder.setValidator(columns, PersonInterventionViewDTO.VEHICLEDAYS, "YAHOO.widget.DataTable.validateNumber");
    GridBuilder.setEditable(columns, PersonInterventionViewDTO.ENTITYLABEL, false);
       
    return new ViewDataGrid(view, columns, keys, data);
  }
  
  private String[] getViewKeys()
  {
    String[] keys = new String[] { PersonInterventionViewDTO.CONCRETEID, PersonInterventionViewDTO.GEOENTITY, PersonInterventionViewDTO.ENTITYLABEL, PersonInterventionViewDTO.VEHICLEDAYS};

    upperFirstCharacter(keys);

    return keys;
  }

  private String[] getMethodKeys()
  {
    String[] keys = new String[] { PersonInterventionMethodViewDTO.TERM, PersonInterventionMethodViewDTO.AMOUNT };

    upperFirstCharacter(keys);

    return keys;
  }

}
