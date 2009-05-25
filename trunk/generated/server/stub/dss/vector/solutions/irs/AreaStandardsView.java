package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

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
    concrete.setStructureArea(this.getStructureArea());
    concrete.setHousehold(this.getHousehold());
    concrete.setUnitNozzleAreaCoverage(this.getUnitNozzleAreaCoverage()); 
    
    concrete.clearTargetUnit();
    
    for(TargetUnit unit : this.getTargetUnit())
    {
      concrete.addTargetUnit(unit);
    }
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
      concrete = AreaStandards.lock(this.getAreaStandardsId());
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

  @Override
  public void applyClone()
  {
    AreaStandards concrete = new AreaStandards();

    this.populateConcrete(concrete);

    concrete.apply();

    concrete.populateView(this);
  }

  @Transaction
  public static AreaStandardsView getMostRecent()
  {
    AreaStandardsQuery query = new AreaStandardsQuery(new QueryFactory());
    query.ORDER_BY_DESC(query.getCreateDate());

    OIterator<? extends AreaStandards> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        return it.next().getView();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }
}
