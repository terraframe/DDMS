package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.Property;

public class HouseholdSprayStatusView extends HouseholdSprayStatusViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860697955L;

  public HouseholdSprayStatusView()
  {
    super();
  }

  public void populate(HouseholdSprayStatus status)
  {
    super.populate(status);

    this.setHouseholdId(status.getHouseholdId());
    this.setStructureId(status.getStructureId());
  }

  protected void populateConcrete(HouseholdSprayStatus status, AbstractSpray spray)
  {
    super.populateConcrete(status, spray);

    status.setHouseholdId(this.getHouseholdId());
    status.setStructureId(this.getStructureId());
  }

  @Override
  @Transaction
  public void apply()
  {
    HouseholdSprayStatus status = new HouseholdSprayStatus();

    if (this.hasConcrete())
    {
      status = HouseholdSprayStatus.lock(this.getStatusId());
    }

    this.populateConcrete(status, this.getSpray());

    status.apply();

    this.populate(status);
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      SprayStatus.get(this.getStatusId()).delete();
    }
  }

  @Transaction
  public static HouseholdSprayStatusView[] applyAll(HouseholdSprayStatusView[] views)
  {
    for (HouseholdSprayStatusView view : views)
    {
      view.apply();
    }

    return views;
  }
  
  public static String[] getGeneratedIds()
  {    
    return new String[]{Property.getNextId(), Property.getNextId()};
  }

}
