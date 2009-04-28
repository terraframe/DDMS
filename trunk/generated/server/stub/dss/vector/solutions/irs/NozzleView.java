package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class NozzleView extends NozzleViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597925945L;

  public NozzleView()
  {
    super();
  }

  public void populateConcrete(Nozzle nozzle)
  {
    nozzle.setDisplayLabel(this.getDisplayLabel());
    nozzle.setEnabled(this.getEnabled());
    nozzle.setRatio(this.getRatio());
  }

  @Override
  @Transaction
  public void apply()
  {
    Nozzle nozzle = new Nozzle();

    if(this.hasConcrete())
    {
      nozzle = Nozzle.get(this.getNozzleId());
    }

    this.populateConcrete(nozzle);

    nozzle.apply();
    nozzle.populateView(this);
  }

  @Override
  @Transaction
  public void deleteConcrete()
  {
    if(this.hasConcrete())
    {
      Nozzle.get(this.getNozzleId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getNozzleId() != null && !this.getNozzleId().equals("");
  }

  public static NozzleView[] getAll()
  {
    List<NozzleView> list = new LinkedList<NozzleView>();
    NozzleQuery query = new NozzleQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends Nozzle> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next().getView());
      }
      return list.toArray(new NozzleView[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

  @Transaction
  public static NozzleView[] applyAll(NozzleView[] nozzles)
  {
    for(NozzleView view : nozzles)
    {
      view.apply();
    }

    return nozzles;
  }

}
