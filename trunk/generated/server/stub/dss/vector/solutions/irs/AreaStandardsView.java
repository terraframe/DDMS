package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

public class AreaStandardsView extends AreaStandardsViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597920978L;

  public AreaStandardsView()
  {
    super();
  }

  private void populateConcrete(AreaStandards concrete)
  {
    concrete.setRoom(this.getRoom());
    concrete.setHouse(this.getHouse());
    concrete.setHousehold(this.getHousehold());
  }

  private boolean hasConcrete()
  {
    return this.getAreaStandardsId() != null && !this.getAreaStandardsId().equals("");
  }

  @Override
  @Transaction
  public void apply()
  {
    AreaStandards concrete = new AreaStandards();

    if(this.hasConcrete())
    {
      concrete = AreaStandards.get(this.getAreaStandardsId());
    }

    this.populateConcrete(concrete);

    concrete.apply();
    concrete.populateView(this);
  }

  @Override
  @Transaction
  public void deleteConcrete()
  {
    if(this.hasConcrete())
    {
      AreaStandards.get(this.getAreaStandardsId()).delete();
    }
  }
}
