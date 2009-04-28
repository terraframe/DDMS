package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.assay.Unit;

public class InsecticideBrandView extends InsecticideBrandViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597920676L;

  public InsecticideBrandView()
  {
    super();
  }

  public void populateConcrete(InsecticideBrand brand)
  {
    brand.setActiveIngredient(this.getActiveIngredient());
    brand.setAmount(this.getAmount());
    brand.setWeight(this.getWeight());
    brand.setSachetsPerRefill(this.getSachetsPerRefill());
    brand.setEnabled(this.getEnabled());
    brand.clearEnum(InsecticideBrand.UNITS);

    for (Unit unit : this.getUnits())
    {
      brand.addUnits(unit);
    }
  }

  private boolean hasInsecticideBrand()
  {
    return this.getInsecticdeId() != null && !this.getInsecticdeId().equals("");
  }

  @Transaction
  public void apply()
  {
    InsecticideBrand brand = new InsecticideBrand();

    if (this.hasInsecticideBrand())
    {
      brand = InsecticideBrand.get(this.getInsecticdeId());
    }

    this.populateConcrete(brand);

    brand.apply();
    brand.populateView(this);
  }

  @Transaction
  public void deleteConcrete()
  {
    if (this.hasInsecticideBrand())
    {
      InsecticideBrand.get(this.getInsecticdeId()).delete();
    }
  }

  @Transaction
  public static InsecticideBrandView[] getAll()
  {
    List<InsecticideBrandView> list = new LinkedList<InsecticideBrandView>();
    InsecticideBrandQuery query = new InsecticideBrandQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends InsecticideBrand> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next().getView());
      }
      return list.toArray(new InsecticideBrandView[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

  @Transaction
  public static InsecticideBrandView[] applyAll(InsecticideBrandView[] insecticides)
  {
    for(InsecticideBrandView view : insecticides)
    {
      view.apply();
    }

    return insecticides;
  }

}
