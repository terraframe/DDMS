package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class InsecticideNozzleView extends InsecticideNozzleViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241039480281L;

  public InsecticideNozzleView()
  {
    super();
  }

  public void populateView(InsecticideNozzle insecticideNozzle)
  {
    this.setBrand(insecticideNozzle.getParent());
    this.setNozzle(insecticideNozzle.getChild());
    this.setEnabled(insecticideNozzle.getEnabled());
    this.setInsecticideNozzleId(insecticideNozzle.getId());
  }

  public void populateConcrete(InsecticideNozzle insecticideNozzle)
  {
    insecticideNozzle.setEnabled(this.getEnabled());
  }

  private boolean hasInsecticideNozzle()
  {
    return this.getInsecticideNozzleId() != null && !this.getInsecticideNozzleId().equals("");
  }

  @Transaction
  public void apply()
  {
    InsecticideNozzle insecticideNozzle = null;

    if (this.hasInsecticideNozzle())
    {
      insecticideNozzle = InsecticideNozzle.lock(this.getInsecticideNozzleId());
    }
    else
    {
      insecticideNozzle = new InsecticideNozzle(this.getBrand(), this.getNozzle());
    }

    this.populateConcrete(insecticideNozzle);

    insecticideNozzle.apply();

    this.populateView(insecticideNozzle);
  }

  @Transaction
  public void deleteConcrete()
  {
    if (this.hasInsecticideNozzle())
    {
      InsecticideNozzle.get(this.getInsecticideNozzleId()).delete();
    }
  }

  @Transaction
  public static InsecticideNozzleView[] getAll()
  {
    List<InsecticideNozzleView> list = new LinkedList<InsecticideNozzleView>();
    InsecticideNozzleQuery query = new InsecticideNozzleQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends InsecticideNozzle> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next().getView());
      }
      return list.toArray(new InsecticideNozzleView[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

  @Transaction
  public static InsecticideNozzleView[] applyAll(InsecticideNozzleView[] insecticides)
  {
    for(InsecticideNozzleView view : insecticides)
    {
      view.apply();
    }

    return insecticides;
  }

}
