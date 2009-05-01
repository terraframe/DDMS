package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

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
      brand = InsecticideBrand.lock(this.getInsecticdeId());
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
    InsecticideBrand[] brands = InsecticideBrand.getAll();
    InsecticideBrandView[] views = new InsecticideBrandView[brands.length];

    for(int i = 0; i < brands.length; i++)
    {
      views[i] = brands[i].getView();
    }

    return views;
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
