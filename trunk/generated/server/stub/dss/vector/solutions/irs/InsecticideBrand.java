package dss.vector.solutions.irs;

import dss.vector.solutions.entomology.assay.Unit;

public class InsecticideBrand extends InsecticideBrandBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597944432L;

  public InsecticideBrand()
  {
    super();
  }

  public void populateView(InsecticideBrandView view)
  {
    view.setActiveIngredient(this.getActiveIngredient());
    view.setAmount(this.getAmount());
    view.setWeight(this.getWeight());
    view.setSachetsPerRefill(this.getSachetsPerRefill());
    view.setInsecticdeId(this.getId());
    view.setEnabled(this.getEnabled());
    view.clearEnum(InsecticideBrandView.UNITS);

    for(Unit unit : this.getUnits())
    {
      view.addUnits(unit);
    }
  }

  public InsecticideBrandView getView()
  {
    InsecticideBrandView view = new InsecticideBrandView();

    this.populateView(view);

    return view;
  }

  public static InsecticideBrandView lockView(String id)
  {
    return InsecticideBrand.lock(id).getView();
  }

  public static InsecticideBrandView unlockView(String id)
  {
    return InsecticideBrand.unlock(id).getView();
  }

  public static InsecticideBrandView getView(String id)
  {
    return InsecticideBrand.get(id).getView();
  }
}
