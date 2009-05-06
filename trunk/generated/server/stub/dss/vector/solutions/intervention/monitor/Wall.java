package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class Wall extends WallBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241556936582L;
  
  public Wall()
  {
    super();
  }
  
  public WallView getView()
  {
    WallView view = new WallView();
    view.populate(this);
    
    return view;
  }
  
  public static Wall[] getRoots()
  {
    WallQuery query = new WallQuery(new QueryFactory());
    query.WHERE(query.NOT_IN_parentRoofs());
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends Wall> it = query.getIterator();

    try
    {
      List<Wall> Walls = new LinkedList<Wall>();

      while (it.hasNext())
      {
        Walls.add(it.next());
      }

      return Walls.toArray(new Wall[Walls.size()]);
    }
    finally
    {
      it.close();
    }
  }
}
