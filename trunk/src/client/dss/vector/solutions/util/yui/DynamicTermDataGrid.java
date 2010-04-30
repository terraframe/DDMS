package dss.vector.solutions.util.yui;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.business.ViewDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.Halp;

public class DynamicTermDataGrid extends AbstractCompositeGrid implements Reloadable
{
  /**
   * Terms
   */
  private TermDTO[]      terms;

  private String         attributeName;

  private String         label;

  private ViewDataGrid[] generators;

  public DynamicTermDataGrid(ViewDTO view, Map<String, ColumnSetup> map, String[] keys, TermSetup setup, String className, String attributeName, String label, ViewDTO[][] data)
  {
    ClientRequestIF request = view.getRequest();

    ViewDTO[][] t = transpose(data);

    this.terms = TermDTO.getRootChildren(request, className, attributeName, true);
    this.attributeName = attributeName;
    this.label = label;
    this.generators = new ViewDataGrid[terms.length];

    for (int i = 0; i < this.terms.length; i++)
    {
      setup.prepare(map, this.terms[i]);
      String postfix = this.getPostfix(i);

      this.generators[i] = new ViewDataGrid(view, map, keys, t[i], postfix);
    }
  }

  @Override
  public List<String> getColumns()
  {
    List<String> columns = new LinkedList<String>();

    List<String> sub = super.getColumns();

    columns.add("{label:\"" + label + "\", children:[" + Halp.join(sub, ",") + "]}");

    return columns;
  }

  @Override
  protected String getMetadata(int start)
  {
    String metadata = super.getMetadata(start);

    return "{metadata:" + metadata + "}";
  }

  private final String getPostfix(int i)
  {
    return "^^" + attributeName + '_' + i;
  }

  @Override
  public DataGrid[] getGrids()
  {
    return this.generators;
  }

  public static ViewDTO[][] transpose(ViewDTO[][] m)
  {
    if (m.length > 0)
    {
      int r = m.length;
      int c = m[0].length;
      ViewDTO[][] t = new ViewDTO[c][r];

      for (int i = 0; i < r; ++i)
      {
        for (int j = 0; j < c; ++j)
        {
          t[j][i] = m[i][j];
        }
      }
      return t;
    }
    
    return new ViewDTO[0][0];
  }
}
