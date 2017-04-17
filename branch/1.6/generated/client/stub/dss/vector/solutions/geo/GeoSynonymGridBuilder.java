package dss.vector.solutions.geo;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class GeoSynonymGridBuilder extends GridBuilder implements Reloadable
{
  private GeoSynonymViewDTO[] data;

  private ClientRequestIF                 request;

  private boolean                         readOnly;

  private GeoSynonymGridBuilder(ClientRequestIF request, boolean readOnly)
  {
    this.request = request;
    this.readOnly = readOnly;
  }

  public GeoSynonymGridBuilder(ClientRequestIF request, boolean readOnly, GeoSynonymViewDTO[] data)
  {
    this(request, readOnly);

    this.data = data;
  }

  public DataGrid build()
  {
    String[] keys = getKeys();

    Map<String, ColumnSetup> columns = getColumns(keys, 1, false);

    if (readOnly)
    {
      for (String key : columns.keySet())
      {
        ColumnSetup column = columns.get(key);

        column.setEditable(false);
      }
    }

    ViewDataGrid grid = new ViewDataGrid("synonymNames", true, new GeoSynonymViewDTO(request), columns, keys, data);
    grid.setDeletable(!readOnly);
    grid.setAddButton(!readOnly);

    return grid;
  }

  private String[] getKeys()
  {
    String[] keys = new String[] { GeoSynonymViewDTO.GEOSYNONYMID, GeoSynonymViewDTO.SYNONYMNAME };

    upperFirstCharacter(keys);

    return keys;
  }
}