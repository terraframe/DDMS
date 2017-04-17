package dss.vector.solutions.surveillance;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class CaseTreatmentGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF       request;

  private AggregatedCaseViewDTO dto;

  private boolean               readonly;

  public CaseTreatmentGridBuilder(ClientRequestIF request, AggregatedCaseViewDTO dto, boolean readonly)
  {
    this.request = request;
    this.dto = dto;
    this.readonly = readonly;
  }

  public DataGrid build()
  {
    CaseTreatmentViewDTO view = new CaseTreatmentViewDTO(request);
    CaseTreatmentViewDTO[] data = dto.getTreatments();

    String[] keys = getKeys();
    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);

    GridBuilder.setEditable(columns, CaseTreatmentViewDTO.TERM, false);

    if (readonly)
    {
      for (String key : columns.keySet())
      {
        ColumnSetup column = columns.get(key);

        column.setEditable(false);
      }
    }

    return new ViewDataGrid("treatment", dto.isCaseTreatmentsReadable(), view, columns, keys, data);
  }

  private String[] getKeys()
  {
    String[] keys = new String[] { CaseTreatmentViewDTO.CONCRETEID, CaseTreatmentViewDTO.TERM, CaseTreatmentViewDTO.AMOUNT };

    upperFirstCharacter(keys);

    return keys;
  }
}
