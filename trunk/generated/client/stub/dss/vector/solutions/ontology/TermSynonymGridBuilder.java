package dss.vector.solutions.ontology;

import java.util.Map;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.ontology.TermSynonymViewDTO;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class TermSynonymGridBuilder extends GridBuilder implements Reloadable
{
  private TermSynonymViewDTO[] data;

  private ClientRequestIF                 request;

  private boolean                         readOnly;

  private TermSynonymGridBuilder(ClientRequestIF request, boolean readOnly)
  {
    this.request = request;
    this.readOnly = readOnly;
  }

  public TermSynonymGridBuilder(ClientRequestIF request, boolean readOnly, TermSynonymViewDTO[] data)
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

    ViewDataGrid grid = new ViewDataGrid("synonymNames", true, new TermSynonymViewDTO(request), columns, keys, data);
    grid.setDeletable(!readOnly);
    grid.setAddButton(!readOnly);

    return grid;
  }

  private String[] getKeys()
  {
    String[] keys = new String[] { TermSynonymViewDTO.TERMSYNONYMID, TermSynonymViewDTO.SYNONYMNAME };

    upperFirstCharacter(keys);

    return keys;
  }
}