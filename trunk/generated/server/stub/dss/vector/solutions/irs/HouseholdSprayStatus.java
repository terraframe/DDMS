package dss.vector.solutions.irs;

import java.util.concurrent.locks.ReentrantLock;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class HouseholdSprayStatus extends HouseholdSprayStatusBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long    serialVersionUID = 1240860647013L;

  private static ReentrantLock lock             = new ReentrantLock();

  public HouseholdSprayStatus()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    if(this.getSpray() != null)
    {
      return this.getSpray().getKey() + "." + this.getHouseholdId() + "." + this.getStructureId();
    }
    
    return this.getId();
  }

  public HouseholdSprayStatusView getView()
  {
    HouseholdSprayStatusView view = new HouseholdSprayStatusView();

    view.populate(this);

    return view;
  }

  public void validateHouseholds(SprayMethod method)
  {
    super.validateHouseholds(method);
    
    Integer value = this.getHouseholds();

    if (value != null)
    {
      if (value != 1 && value != 0)
      {
        String msg = "Household value may only be 0 or 1";
        HouseholdValueProblem p = new HouseholdValueProblem(msg);
        p.setHouseholdId(this.getHouseholdId());
        p.setStructureId(this.getStructureId());
        p.setNotification(this, HOUSEHOLDS);
        p.apply();
        p.throwIt();
      }

      if (value == 1)
      {
        HouseholdSprayStatus status = HouseholdSprayStatus.searchByHousehold(this.getHouseholdId(), value);

        if (status != null && !status.getId().equals(this.getId()))
        {
          String msg = "A spray status of this household has already been set to 1";
          CountProblem p = new CountProblem(msg);
          p.setNotification(this, HOUSEHOLDS);
          p.setHouseholdId(this.getHouseholdId());
          p.apply();
          p.throwIt();
        }
      }
      else if (value == 0)
      {
        HouseholdSprayStatus status = HouseholdSprayStatus.searchByHousehold(this.getHouseholdId(), 1);

        if (status == null)
        {
          String msg = "A spray status of this household has already been not been set to 1";
          UncountedProblem p = new UncountedProblem(msg);
          p.setNotification(this, HOUSEHOLDS);
          p.setHouseholdId(this.getHouseholdId());
          p.apply();
          p.throwIt();
        }

      }
    }
  }

  public void validateStructures(SprayMethod method)
  {
    super.validateStructures(method);
    
    if (this.getStructures() != null)
    {
      if (this.getStructures() != 1 && this.getStructures() != 0)
      {
        String msg = "Structure value may only be 0 or 1";
        StructureValueProblem p = new StructureValueProblem(msg);
        p.setHouseholdId(this.getHouseholdId());
        p.setStructureId(this.getStructureId());
        p.setNotification(this, STRUCTURES);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validateSprayedHouseholds()
  {
    super.validateSprayedHouseholds();
    
    Integer value = this.getSprayedHouseholds();
    if (value != null)
    {
      if (value != 1 && value != 0)
      {
        String msg = "Sprayed Household value may only be 0 or 1";
        SprayedHouseholdValueProblem p = new SprayedHouseholdValueProblem(msg);
        p.setHouseholdId(this.getHouseholdId());
        p.setStructureId(this.getStructureId());
        p.setNotification(this, SPRAYEDHOUSEHOLDS);
        p.apply();
        p.throwIt();
      }

      if (value == 1)
      {
        HouseholdSprayStatus status = HouseholdSprayStatus.serachBySprayedHousehold(this
            .getHouseholdId(), 1);

        if (status != null && !status.getId().equals(this.getId()))
        {
          String msg = "A spray status of this household has already been set to 1";
          SprayedCountProblem p = new SprayedCountProblem(msg);
          p.setNotification(this, SPRAYEDHOUSEHOLDS);
          p.setHouseholdId(this.getHouseholdId());
          p.apply();
          p.throwIt();
        }
      }

    }
  }

  @Override
  public void validateSprayedStructures()
  {
    super.validateSprayedStructures();
    
    if (this.getSprayedStructures() != null)
    {
      if (this.getSprayedStructures() != 1 && this.getSprayedStructures() != 0)
      {
        String msg = "Sprayed Structure value may only be 0 or 1";
        SprayedStructureValueProblem p = new SprayedStructureValueProblem(msg);
        p.setNotification(this, SPRAYEDSTRUCTURES);
        p.setHouseholdId(this.getHouseholdId());
        p.setStructureId(this.getStructureId());
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validatePrevSprayedHouseholds(SprayMethod method)
  {
    super.validatePrevSprayedHouseholds(method);
    
    Integer value = this.getPrevSprayedHouseholds();
    if (value != null)
    {
      if (value != 1 && value != 0)
      {
        String msg = "Previously Sprayed Household value may only be 0 or 1";
        PrevSprayedHouseholdValueProblem p = new PrevSprayedHouseholdValueProblem(msg);
        p.setNotification(this, PREVSPRAYEDHOUSEHOLDS);
        p.setHouseholdId(this.getHouseholdId());
        p.setStructureId(this.getStructureId());
        p.apply();
        p.throwIt();
      }

      if (value == 1)
      {
        HouseholdSprayStatus status = HouseholdSprayStatus.searchByPrevSprayed(this.getHouseholdId(), 1);

        if (status != null && !status.getId().equals(this.getId()))
        {
          String msg = "A previous spray status of this household has already been set to 1";
          PrevSprayedCountProblem p = new PrevSprayedCountProblem(msg);
          p.setNotification(this, PREVSPRAYEDHOUSEHOLDS);
          p.setHouseholdId(this.getHouseholdId());
          p.apply();
          p.throwIt();
        }
      }
    }
  }

  @Override
  public void validatePrevSprayedStructures(SprayMethod method)
  {
    super.validatePrevSprayedStructures(method);
    
    if (this.getPrevSprayedStructures() != null)
    {
      if (this.getPrevSprayedStructures() != 1 && this.getPrevSprayedStructures() != 0)
      {
        String msg = "Previously Sprayed Structure value may only be 0 or 1";
        PrevSprayedStructureValueProblem p = new PrevSprayedStructureValueProblem(msg);
        p.setNotification(this, PREVSPRAYEDSTRUCTURES);
        p.setHouseholdId(this.getHouseholdId());
        p.setStructureId(this.getStructureId());
        p.apply();
        p.throwIt();
      }
    }
  }

  @Transaction
  public void apply()
  {
    lock.lock();

    try
    {
      super.apply();
    }
    finally
    {
      lock.unlock();
    }
  }

  private static HouseholdSprayStatus searchByHousehold(String householdId, Integer household)
  {
    HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getHouseholdId().EQ(householdId));
    query.AND(query.getHouseholds().EQ(household));

    return query(query);
  }

  private static HouseholdSprayStatus serachBySprayedHousehold(String householdId,
      Integer sprayedHousehold)
  {
    HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getHouseholdId().EQ(householdId));
    query.AND(query.getSprayedHouseholds().EQ(sprayedHousehold));

    return query(query);
  }

  private static HouseholdSprayStatus searchByPrevSprayed(String householdId,
      Integer prevSprayedHousehold)
  {
    HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getHouseholdId().EQ(householdId));
    query.AND(query.getPrevSprayedHouseholds().EQ(prevSprayedHousehold));

    return query(query);
  }

  private static HouseholdSprayStatus query(HouseholdSprayStatusQuery query)
  {
    OIterator<? extends HouseholdSprayStatus> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }
}
