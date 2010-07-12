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

public class CasePatientTypeGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF       request;

  private AggregatedCaseViewDTO dto;

  private boolean               readonly;

  public CasePatientTypeGridBuilder(ClientRequestIF request, AggregatedCaseViewDTO dto, boolean readonly)
  {
    this.request = request;
    this.dto = dto;
    this.readonly = readonly;
  }

  public DataGrid build()
  {
    CasePatientTypeViewDTO[] data = dto.getPatientTypes();

    DataGrid staticGrid = this.buildStatic(data);
    DataGrid dynamicGrid = this.buildDynamic(data);
    
    return new CompositeDataGrid("patientType", dto.getIsCasePatientTypeReadable(), staticGrid, dynamicGrid);
  }

  private DataGrid buildStatic(CasePatientTypeViewDTO[] data)
  {
    CasePatientTypeViewDTO view = new CasePatientTypeViewDTO(request);
    
    String[] keys = new String[] { CasePatientTypeViewDTO.CONCRETEID, CasePatientTypeViewDTO.TERM};
    
    upperFirstCharacter(keys);

    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);

    GridBuilder.setEditable(columns, CasePatientTypeViewDTO.TERM, false);

    return new ViewDataGrid(view, columns, keys, data);
  }
  
  private DataGrid buildDynamic(CasePatientTypeViewDTO[] views)
  {
    String[] ids = this.getIds(views);
    
    CasePatientTypeAmountViewDTO view = new CasePatientTypeAmountViewDTO(request);
    CasePatientTypeAmountViewDTO[][] data = CasePatientTypeViewDTO.getAmountsForViews(request, ids);
    
    String[] keys = new String[] { CasePatientTypeAmountViewDTO.TERM, CasePatientTypeAmountViewDTO.AMOUNT};
    upperFirstCharacter(keys);
    
    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);
    
    if(readonly)
    {
      GridBuilder.setEditable(columns, CasePatientTypeAmountViewDTO.AMOUNT, false);
    }
    
    TermSetup setup = new TermSetup(CasePatientTypeAmountViewDTO.AMOUNT, CasePatientTypeAmountViewDTO.TERM);

    return new DynamicTermDataGrid(view, columns, keys, setup, CasePatientTypeViewDTO.CLASS, CasePatientTypeViewDTO.PATIENTCATEGORY, null, data);
  }

  private String[] getIds(CasePatientTypeViewDTO[] views)
  {
    String[] ids = new String[views.length];
    
    for(int i = 0; i < ids.length; i++)
    {
      ids[i] = views[i].getConcreteId();
    }
    return ids;
  }
}
