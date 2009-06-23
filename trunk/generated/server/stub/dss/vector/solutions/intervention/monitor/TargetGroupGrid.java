package dss.vector.solutions.intervention.monitor;

import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.surveillance.AbstractGrid;

public class TargetGroupGrid extends TargetGroupGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245774440423L;
  
  public TargetGroupGrid()
  {
    super();
  }
  
  public static TargetGroupGrid[] getAll()
  {
    TargetGroupGridQuery query = new TargetGroupGridQuery(new QueryFactory());
    List<TargetGroupGrid> list = AbstractGrid.getAll(query, TargetGroupGrid.class);

    return list.toArray(new TargetGroupGrid[list.size()]);
  }

  public static TargetGroupGrid[] getAllActive()
  {
    TargetGroupGridQuery query = new TargetGroupGridQuery(new QueryFactory());
    List<TargetGroupGrid> list = AbstractGrid.getAllActive(query, TargetGroupGrid.class);

    return list.toArray(new TargetGroupGrid[list.size()]);
  }
}
