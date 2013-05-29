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