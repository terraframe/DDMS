package dss.vector.solutions.util.yui;

import com.runwaysdk.generation.loader.Reloadable;

public class CompositeDataGrid extends AbstractCompositeGrid implements Reloadable
{
  private DataGrid[] generators;

  public CompositeDataGrid(DataGrid... generators)
  {
    this.generators = generators;
  }

  @Override
  public DataGrid[] getGrids()
  {
    return this.generators;
  }
}
