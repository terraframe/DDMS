package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class Nozzle extends NozzleBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597892193L;

  public Nozzle()
  {
    super();
  }
    
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if(this.getDisplayLabel() != null)
    {
      return this.getDisplayLabel();
    }

    return super.toString();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getDisplayLabel();
  }

  public void populateView(NozzleView view)
  {
    view.setDisplayLabel(this.getDisplayLabel());
    view.setEnabled(this.getEnabled());
    view.setNozzleId(this.getId());
    view.setRatio(this.getRatio());
  }

  public NozzleView getView()
  {
    NozzleView view = new NozzleView();

    this.populateView(view);

    return view;
  }

  @Transaction
  public static NozzleView getView(String id)
  {
    return Nozzle.get(id).getView();
  }

  @Transaction
  public static NozzleView unlockView(String id)
  {
    return Nozzle.unlock(id).getView();
  }

  @Transaction
  public static NozzleView lockView(String id)
  {
    return Nozzle.lock(id).getView();
  }
  
  @Transaction
  public static Nozzle[] getAllActive()
  {
    NozzleQuery query = new NozzleQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getCreateDate());
    
    return Nozzle.getNozzles(query);
  }

  @Transaction
  public static Nozzle[] getAll()
  {
    NozzleQuery query = new NozzleQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getCreateDate());

    return Nozzle.getNozzles(query);
  }

  private static Nozzle[] getNozzles(NozzleQuery query)
  {
    List<Nozzle> list = new LinkedList<Nozzle>();
    OIterator<? extends Nozzle> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next());
      }
      return list.toArray(new Nozzle[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

}
