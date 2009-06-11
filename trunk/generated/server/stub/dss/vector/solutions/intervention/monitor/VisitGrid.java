package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class VisitGrid extends VisitGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244737057142L;
  
  public VisitGrid()
  {
    super();
  }
  
  public static VisitGrid[] getAll()
  {
    List<VisitGrid> list = new LinkedList<VisitGrid>();
    VisitGridQuery query = new VisitGridQuery(new QueryFactory());
    query.WHERE(query.getActive().EQ(true));
    
    for(VisitGrid d : query.getIterator())
    {
      list.add(d);
    }
    
    return list.toArray(new VisitGrid[list.size()]);
  }
}
