package dss.vector.solutions.entomology;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalViewDTO;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class AdultDiscriminatingDoseIntervalGridBuilder extends GridBuilder implements Reloadable
{
  private AdultDiscriminatingDoseAssayDTO dto;

  private ClientRequestIF                 request;

  private boolean                         readOnly;

  public AdultDiscriminatingDoseIntervalGridBuilder(ClientRequestIF request, AdultDiscriminatingDoseAssayDTO dto, boolean readOnly)
  {
    this.request = request;
    this.dto = dto;
    this.readOnly = readOnly;
  }

  public DataGrid build()
  {
    AdultDiscriminatingDoseIntervalViewDTO[] data = dto.getIntervals();

    String[] keys = getKeys();

    Map<String, ColumnSetup> columns = getColumns(keys, 2, true);

    GridBuilder.setEditable(columns, AdultDiscriminatingDoseIntervalViewDTO.CONCRETEID, false);
    GridBuilder.setEditable(columns, AdultDiscriminatingDoseIntervalViewDTO.ASSAY, false);
    GridBuilder.setValidator(columns, AdultDiscriminatingDoseIntervalViewDTO.INTERVALTIME, "MDSS.validateNumber");
    GridBuilder.setValidator(columns, AdultDiscriminatingDoseIntervalViewDTO.AMOUNT, "MDSS.validateNumber");

    if (readOnly)
    {
      for (String key : columns.keySet())
      {
        ColumnSetup column = columns.get(key);

        column.setEditable(false);
      }
    }

    ViewDataGrid grid = new ViewDataGrid("intervals", true, new AdultDiscriminatingDoseIntervalViewDTO(request), columns, keys, data);
    grid.setDeletable(!readOnly);
    grid.setAddButton(!readOnly);

    return grid;
  }

  private String[] getKeys()
  {
    String[] keys = new String[] { AdultDiscriminatingDoseIntervalViewDTO.CONCRETEID, AdultDiscriminatingDoseIntervalViewDTO.ASSAY, AdultDiscriminatingDoseIntervalViewDTO.INTERVALTIME, AdultDiscriminatingDoseIntervalViewDTO.AMOUNT };

    upperFirstCharacter(keys);

    return keys;
  }
}