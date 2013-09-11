package dss.vector.solutions.irs;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.general.UniqueValueProblem;

public class HouseholdSprayStatus extends HouseholdSprayStatusBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long    serialVersionUID = 1240860647013L;

  private static ReentrantLock lock             = new ReentrantLock();

  public HouseholdSprayStatus()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }

    return this.getClassDisplayLabel();
  }

  @Override
  protected String buildKey()
  {
    if (this.getSpray() != null)
    {
      return this.getSpray().getKey() + "." + this.getHouseholdId() + "." + this.getStructureId();
    }

    return this.getId();
  }

  public HouseholdSprayStatusView getView()
  {
    HouseholdSprayStatusView view = new HouseholdSprayStatusView();

    view.populateView(this);

    return view;
  }
  
  @Override
  public void validateStructureId()
  {
    if(this.getStructureId() != null && this.getSpray() != null)
    {
      HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
      query.WHERE(query.getId().NE(this.getId()));
      query.AND(query.getStructureId().EQ(this.getStructureId()));
      query.AND(query.getSpray().EQ(this.getSpray()));
      
      long count = query.getCount();
      
      if(count > 0)
      {
        UniqueValueProblem p = new UniqueValueProblem();
        p.setDisplayLabel(getStructureIdMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setValue(this.getStructureId());
        p.apply();
        
        p.throwIt();
      }
    }
  }

  public void validateHouseholds(SprayMethod method)
  {
    Integer value = this.getHouseholds();

    if (value != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Household value is not applicable on a mop-up spray";
        HouseholdValueNotApplicableProblem p = new HouseholdValueNotApplicableProblem(msg);
        p.setNotification(this, HOUSEHOLDS);
        p.apply();
        p.throwIt();
      }

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
          String msg = "A spray status of this household has not been set to 1";
          
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
    Integer value = this.getStructures();

    if (value != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Household value is not applicable on a mop-up spray";
        StructureValueNotApplicableProblem p = new StructureValueNotApplicableProblem(msg);
        p.setNotification(this, STRUCTURES);
        p.apply();
        p.throwIt();
      }

      if (value != 1 && this.getStructures() != 0)
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
        HouseholdSprayStatus status = HouseholdSprayStatus.serachBySprayedHousehold(this.getHouseholdId(), 1);

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
    if (this.getStructures() != null && this.getSprayedStructures() != null && this.getStructures() < this.getSprayedStructures())
    {
      String msg = "The number of sprayed structures cannot be greater than the number of structures";

      SprayedSumProblem p = new SprayedSumProblem(msg);
      p.setNotification(this, SPRAYEDSTRUCTURES);
      p.setObjectLabel(this.getMdAttributeDAO(STRUCTURES).getDisplayLabel(Session.getCurrentLocale()));
      p.setSprayedObjectLabel(this.getMdAttributeDAO(SPRAYEDSTRUCTURES).getDisplayLabel(Session.getCurrentLocale()));
      p.setObjects(this.getHouseholds());
      p.setSprayedObjects(this.getSprayedHouseholds());
      p.apply();

      p.throwIt();
    }

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
    Integer value = this.getPrevSprayedHouseholds();

    if (value != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Value is not applicable on a mop-up spray";
        PrevSprayedHouseholdValueNotApplicableProblem p = new PrevSprayedHouseholdValueNotApplicableProblem(msg);
        p.setNotification(this, PREVSPRAYEDHOUSEHOLDS);
        p.apply();
        p.throwIt();
      }

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

  public void validatePrevSprayedStructures(SprayMethod method)
  {
    Integer value = this.getPrevSprayedStructures();

    if (value != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Structure value is not applicable on a mop-up spray";
        PrevSprayedStructureValueNotApplicableProblem p = new PrevSprayedStructureValueNotApplicableProblem(msg);
        p.setNotification(this, PREVSPRAYEDSTRUCTURES);
        p.apply();
        p.throwIt();
      }

      if (value != 1 && this.getPrevSprayedStructures() != 0)
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

  public void validateRooms(SprayMethod method)
  {
    if (this.getRooms() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Value is not applicable on a mop-up spray";
        RoomValueNotApplicableProblem p = new RoomValueNotApplicableProblem(msg);
        p.setNotification(this, ROOMS);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateSprayedRooms()
  {
    if (this.getRooms() != null && this.getSprayedRooms() != null && this.getRooms() < this.getSprayedRooms())
    {
      String msg = "The number of sprayed rooms cannot be greater than the number of rooms";

      SprayedSumProblem p = new SprayedSumProblem(msg);
      p.setNotification(this, SPRAYEDROOMS);
      p.setObjectLabel(this.getMdAttributeDAO(ROOMS).getDisplayLabel(Session.getCurrentLocale()));
      p.setSprayedObjectLabel(this.getMdAttributeDAO(SPRAYEDROOMS).getDisplayLabel(Session.getCurrentLocale()));
      p.setObjects(this.getRooms());
      p.setSprayedObjects(this.getSprayedRooms());
      p.apply();

      p.throwIt();
    }
  }

  private void validateOther(SprayMethod method)
  {
    if (this.getOther() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Other is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, OTHER);
        p.apply();
        p.throwIt();
      }
    }
  }

  private void validateRefused(SprayMethod method)
  {
    if (this.getRefused() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "Refused is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, REFUSED);
      p.apply();
      p.throwIt();
    }
  }

  private void validateLocked(SprayMethod method)
  {
    if (this.getLocked() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "Locked is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, LOCKED);
      p.apply();
      p.throwIt();
    }
  }
  
  private void validateWrongSurface(SprayMethod method)
  {
    if (this.getWrongSurface() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "Wrong surface is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, WRONGSURFACE);
      p.apply();
      p.throwIt();
    }
  }

  private void validateRoomsWithBedNets(SprayMethod method)
  {
    if (this.getRoomsWithBedNets() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "Rooms with bed nets is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, ROOMSWITHBEDNETS);
      p.apply();
      p.throwIt();
    }
  }

  private void validateBedNets(SprayMethod method)
  {
    if (this.getBedNets() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "# ITNs is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, BEDNETS);
      p.apply();
      p.throwIt();
    }
  }

  protected void validatePeople(SprayMethod method)
  {
    if (this.getPeople() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "# People protected is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, PEOPLE);
      p.apply();
      p.throwIt();
    }
  }

  @Transaction
  public void apply()
  {
    SprayMethod method = this.getSprayMethod();

    if(!method.equals(SprayMethod.MOP_UP))
    {
      this.setStructures(1);
    }
    else
    {
      this.setHouseholds(null);
      this.setStructures(null);
    }
    
    validateStructureId();

    // Validate MOP-UP
    validateHouseholds(method);
    validateStructures(method);
    validatePrevSprayedHouseholds(method);
    validatePrevSprayedStructures(method);
    validateRooms(method);
    validatePeople(method);
    validateBedNets(method);
    validateRoomsWithBedNets(method);
    validateWrongSurface(method);
    validateLocked(method);
    validateRefused(method);
    validateOther(method);

    // Validate values
    validateSprayedHouseholds();
    validateSprayedStructures();
    validateSprayedRooms();

    super.apply();
  }

  @Override
  @Transaction
  public void delete()
  {
    Integer _households = this.getHouseholds();
    String _householdId = this.getHouseholdId();
    String _id = this.getId();

    super.delete();

    if (_households != null && _households == 1)
    {
      lock.lock();

      try
      {
        HouseholdSprayStatus _other = HouseholdSprayStatus.getOther(_householdId, _id);

        if (_other != null)
        {
          _other.lock();
          _other.setHouseholds(1);
          _other.apply();
        }
      }
      finally
      {
        lock.unlock();
      }
    }
  }

  protected SprayMethod getSprayMethod()
  {
    List<SprayMethod> method = this.getSpray().getSprayMethod();

    if (method.size() > 0)
    {
      return method.get(0);
    }

    return null;
  }

  private static HouseholdSprayStatus getOther(String householdId, String id)
  {
    HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getHouseholdId().EQ(householdId));
    query.AND(query.getId().NE(id));

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

  private static HouseholdSprayStatus searchByHousehold(String householdId, Integer household)
  {
    HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getHouseholdId().EQ(householdId));
    query.AND(query.getHouseholds().EQ(household));

    return query(query);
  }

  private static HouseholdSprayStatus serachBySprayedHousehold(String householdId, Integer sprayedHousehold)
  {
    HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getHouseholdId().EQ(householdId));
    query.AND(query.getSprayedHouseholds().EQ(sprayedHousehold));

    return query(query);
  }

  private static HouseholdSprayStatus searchByPrevSprayed(String householdId, Integer prevSprayedHousehold)
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
