package dss.vector.solutions.intervention.monitor;

import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.surveillance.AbstractGrid;

public class VisitGrid extends VisitGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244737057142L;

  public VisitGrid()
  {
    super();
  }

  public static VisitGrid[] getAll()
  {
    VisitGridQuery query = new VisitGridQuery(new QueryFactory());
    List<VisitGrid> list = AbstractGrid.getAll(query, VisitGrid.class);

    return list.toArray(new VisitGrid[list.size()]);
  }

  public static VisitGrid[] getAllActive()
  {
    VisitGridQuery query = new VisitGridQuery(new QueryFactory());
    List<VisitGrid> list = AbstractGrid.getAllActive(query, VisitGrid.class);

    return list.toArray(new VisitGrid[list.size()]);
  }
}
