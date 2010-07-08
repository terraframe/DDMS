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
    brand.setProductName(this.getProductName());
    for (InsecticideBrandUse insecticideUse: this.getInsecticideUse()) {
    	brand.addInsecticideUse(insecticideUse);
    }
    brand.setUseDetail(this.getUseDetail());
    brand.setActiveIngredient(this.getActiveIngredient());
    brand.setConcentrationQuantifier(this.getConcentrationQuantifier());
    for (InsecticideBrandConcentrationQualifier concentrationQualifier: this.getConcentrationQualifier()) {
    	brand.addConcentrationQualifier(concentrationQualifier);
    }
    brand.setUnitQuantifier(this.getUnitQuantifier());
    for (InsecticideBrandUnitQualifier unitQualifier: this.getUnitQualifier()) {
    	brand.addUnitQualifier(unitQualifier);
    }
    brand.setUnitsPerApplication(this.getUnitsPerApplication());
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
  
  @Transaction
  public static InsecticideBrandView[] getIRSActive()
  {
	  InsecticideBrandUse[] uses = {InsecticideBrandUse.IRS};
    return InsecticideBrandView.getViews(InsecticideBrand.getAll(true, uses));
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
