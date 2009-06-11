package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class DoseGrid extends DoseGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244737040890L;
  
  public DoseGrid()
  {
    super();
  }
  
  public static DoseGrid[] getAll()
  {
    List<DoseGrid> list = new LinkedList<DoseGrid>();
    DoseGridQuery query = new DoseGridQuery(new QueryFactory());
    query.WHERE(query.getActive().EQ(true));

    for(DoseGrid d : query.getIterator())
    {
      list.add(d);
    }

    return list.toArray(new DoseGrid[list.size()]);
  }
}
