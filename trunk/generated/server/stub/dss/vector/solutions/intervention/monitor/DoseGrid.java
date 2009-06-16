package dss.vector.solutions.intervention.monitor;

import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.surveillance.AbstractGrid;

public class DoseGrid extends DoseGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244737040890L;
  
  public DoseGrid()
  {
    super();
  }
  
  public static DoseGrid[] getAll()
  {
    DoseGridQuery query = new DoseGridQuery(new QueryFactory());
    List<DoseGrid> list = AbstractGrid.getAll(query, DoseGrid.class);

    return list.toArray(new DoseGrid[list.size()]);
  }

  public static DoseGrid[] getAllActive()
  {
    DoseGridQuery query = new DoseGridQuery(new QueryFactory());
    List<DoseGrid> list = AbstractGrid.getAllActive(query, DoseGrid.class);

    return list.toArray(new DoseGrid[list.size()]);
  }
}
