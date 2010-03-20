package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.transaction.Transaction;

public class InsecticideBrandView extends InsecticideBrandViewBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597920676L;

  public InsecticideBrandView()
  {
    super();
  }

  public void populateConcrete(InsecticideBrand brand)
  {
    brand.setBrandName(this.getBrandName());
    brand.setActiveIngredient(this.getActiveIngredient());
    brand.setAmount(this.getAmount());
    brand.setWeight(this.getWeight());
    brand.setSachetsPerRefill(this.getSachetsPerRefill());
    brand.setEnabled(this.getEnabled());
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
    return InsecticideBrandView.getViews(InsecticideBrand.getAll());
  }

  @Transaction
  public static InsecticideBrandView[] getAllActive()
  {
    return InsecticideBrandView.getViews(InsecticideBrand.getAllActive());
  }

  private static InsecticideBrandView[] getViews(InsecticideBrand[] brands)
  {
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
