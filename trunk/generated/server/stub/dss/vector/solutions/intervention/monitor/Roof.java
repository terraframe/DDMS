package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class Roof extends RoofBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241556933913L;

  public Roof()
  {
    super();
  }

  protected String buildKey()
  {
    return this.getRoofName();
  }

  @Override
  public void apply()
  {
    super.apply();

    List<? extends Roof> parents = this.getAllParentRoofs().getAll();

    if(this.getParentRoof() == null)
    {
      if(parents.size() > 0)
      {
        this.deleteAllParents();
      }
    }
    else if(!parents.contains(this.getParentRoof()))
    {
      deleteAllParents();

      Roof parent = this.getParentRoof();

      RoofHeiarchy heiarchy = new RoofHeiarchy(parent, this);
      heiarchy.apply();
    }
  }

  @Transaction
  private void deleteAllParents()
  {
    List<? extends RoofHeiarchy> hierarchy = this.getAllParentRoofsRel().getAll();

    for(RoofHeiarchy h : hierarchy)
    {
      h.delete();
    }
  }

  public RoofView getView()
  {
    RoofView view = new RoofView();
    view.populate(this);

    return view;
  }

  public static Roof[] getAll()
  {
    RoofQuery query = new RoofQuery(new QueryFactory());
    query.getEnabled().EQ(true);

    List<? extends Roof> roofs = query.getIterator().getAll();

    return roofs.toArray(new Roof[roofs.size()]);
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
