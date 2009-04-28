package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class InsecticideNozzle extends InsecticideNozzleBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597942330L;

  public InsecticideNozzle(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public InsecticideNozzle(dss.vector.solutions.irs.InsecticideBrand parent, dss.vector.solutions.irs.Nozzle child)
  {
    this(parent.getId(), child.getId());
  }

  public static InsecticideNozzle[] getAll()
  {
    List<InsecticideNozzle> list = new LinkedList<InsecticideNozzle>();
    InsecticideNozzleQuery query = new InsecticideNozzleQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends InsecticideNozzle> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        InsecticideNozzle next = it.next();

        if(next.getParent().getEnabled() && next.getChild().getEnabled())
        {
          list.add(next);
        }
      }
      return list.toArray(new InsecticideNozzle[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

  @Transaction
  public static InsecticideNozzle[] applyAll(InsecticideNozzle[] insecticideNozzles)
  {
    for(InsecticideNozzle concrete : insecticideNozzles)
    {
      concrete.apply();
    }

    return insecticideNozzles;
  }

}
