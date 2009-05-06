package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class Roof extends RoofBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241556933913L;
  
  public Roof()
  {
    super();
  }
 
  public RoofView getView()
  {
    RoofView view = new RoofView();
    view.populate(this);
    
    return view;
  }

  public static Roof[] getRoots()
  {
    RoofQuery query = new RoofQuery(new QueryFactory());
    query.WHERE(query.NOT_IN_parentRoofs());
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends Roof> it = query.getIterator();

    try
    {
      List<Roof> roofs = new LinkedList<Roof>();

      while (it.hasNext())
      {
        roofs.add(it.next());
      }

      return roofs.toArray(new Roof[roofs.size()]);
    }
    finally
    {
      it.close();
    }
  }

}
