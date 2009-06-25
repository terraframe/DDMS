package dss.vector.solutions.irs;

import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class HouseholdSprayStatus extends HouseholdSprayStatusBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860647013L;

  public HouseholdSprayStatus()
  {
    super();
  }

  public HouseholdSprayStatusView getView()
  {
    HouseholdSprayStatusView view = new HouseholdSprayStatusView();

    view.populate(this);

    return view;
  }

  private SprayMethod getSprayMethod()
  {
    List<SprayMethod> method = this.getSpray().getSprayData().getSprayMethod();

    if(method.size() > 0)
    {
      return method.get(0);
    }

    return null;
  }

  public void validateHouseholds(SprayMethod method)
  {
    Integer value = this.getHouseholds();

    if(value != null)
    {
      if(method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Household value is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, HOUSEHOLDS);
        p.apply();
        p.throwIt();
      }

      if(value != 1 && value != 0)
      {
        String msg = "Household value may only be 0 or 1";
        ValueProblem p = new ValueProblem(msg);
        p.setNotification(this, HOUSEHOLDS);
        p.apply();
        p.throwIt();
      }
      
      if(value == 1)
      {
        HouseholdSprayStatus status = HouseholdSprayStatus.searchByHousehold(this.getHouseholdId(), value);

        if(status != null && !status.getId().equals(this.getId()))
        {
          String msg = "A spray status of this household has already been set to 1";
          CountProblem p = new CountProblem(msg);
          p.setNotification(this, HOUSEHOLDS);
          p.setHouseholdId(this.getHouseholdId());
          p.apply();
          p.throwIt();
        }
      }
    }
  }

  @Override
  public void validateStructures()
  {
    if(this.getStructures() != null)
    {
      if(this.getStructures() != 1 && this.getStructures() != 0)
      {
        String msg = "Structure value may only be 0 or 1";
        ValueProblem p = new ValueProblem(msg);
        p.setNotification(this, STRUCTURES);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validateSprayedHouseholds(SprayMethod method)
  {
    Integer value = this.getSprayedHouseholds();
    if(value != null)
    {
      if(value != 1 && value != 0)
      {
        String msg = "Sprayed Household value may only be 0 or 1";
        ValueProblem p = new ValueProblem(msg);
        p.setNotification(this, SPRAYEDHOUSEHOLDS);
        p.apply();
        p.throwIt();
      }
      
      if(value == 1)
      {
        HouseholdSprayStatus status = HouseholdSprayStatus.serachBySprayedHousehold(this.getHouseholdId(), 1);

        if(status != null && !status.getId().equals(this.getId()))
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
    if(this.getSprayedStructures() != null)
    {
      if(this.getSprayedStructures() != 1 && this.getSprayedStructures() != 0)
      {
        String msg = "Sprayed Structure value may only be 0 or 1";
        ValueProblem p = new ValueProblem(msg);
        p.setNotification(this, SPRAYEDSTRUCTURES);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validatePrevSprayedHouseholds(SprayMethod method)
  {
    Integer value = this.getPrevSprayedHouseholds();
    if(value != null)
    {
      if(method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Value is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, PREVSPRAYEDHOUSEHOLDS);
        p.apply();
        p.throwIt();
      }

      if(value != 1 && value != 0)
      {
        String msg = "Previously Sprayed Household value may only be 0 or 1";
        ValueProblem p = new ValueProblem(msg);
        p.setNotification(this, PREVSPRAYEDHOUSEHOLDS);
        p.apply();
        p.throwIt();
      }
      
      if(value == 1)
      {
        HouseholdSprayStatus status = HouseholdSprayStatus.searchByPrevSprayed(this.getHouseholdId(), 1);

        if(status != null && !status.getId().equals(this.getId()))
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
  public void validatePrevSprayedStructures()
  {
    if(this.getPrevSprayedStructures() != null)
    {
      if(this.getPrevSprayedStructures() != 1 && this.getPrevSprayedStructures() != 0)
      {
        String msg = "Previously Sprayed Structure value may only be 0 or 1";
        ValueProblem p = new ValueProblem(msg);
        p.setNotification(this, PREVSPRAYEDSTRUCTURES);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validateRooms(SprayMethod method)
  {
    if(this.getRooms() != null)
    {
      if(method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Value is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, ROOMS);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void apply()
  {
    SprayMethod method = this.getSprayMethod();

    validateHouseholds(method);
    validateSprayedHouseholds(method);
    validatePrevSprayedHouseholds(method);
    validateRooms(method);
    validateStructures();
    validateSprayedStructures();
    validatePrevSprayedStructures();

    super.apply();
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
      if(it.hasNext())
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
