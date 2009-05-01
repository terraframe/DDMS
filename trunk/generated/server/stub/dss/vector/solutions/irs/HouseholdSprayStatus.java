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
    if(this.getHouseholds() != null)
    {
      if(method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Household value is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, HOUSEHOLDS);
        p.apply();
        p.throwIt();
      }

      if(this.getHouseholds() != 1 || this.getHouseholds() != 0)
      {
        String msg = "Household value may only be 0 or 1";
        ValueProblem p = new ValueProblem(msg);
        p.setNotification(this, HOUSEHOLDS);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateStructures()
  {
    if(this.getStructures() != null)
    {
      if(this.getStructures() != 1 || this.getStructures() != 0)
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
    if(this.getSprayedHouseholds() != null)
    {
      if(method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Value is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, SPRAYEDHOUSEHOLDS);
        p.apply();
        p.throwIt();
      }

      if(this.getSprayedHouseholds() != 1 || this.getSprayedHouseholds() != 0)
      {
        String msg = "Sprayed Household value may only be 0 or 1";
        ValueProblem p = new ValueProblem(msg);
        p.setNotification(this, SPRAYEDHOUSEHOLDS);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateSprayedStructures()
  {
    if(this.getSprayedStructures() != null)
    {
      if(this.getSprayedStructures() != 1 || this.getSprayedStructures() != 0)
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
    if(this.getPrevSprayedHouseholds() != null)
    {
      if(method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Value is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, PREVSPRAYEDHOUSEHOLDS);
        p.apply();
        p.throwIt();
      }

      if(this.getPrevSprayedHouseholds() != 1 || this.getPrevSprayedHouseholds() != 0)
      {
        String msg = "Previously Sprayed Household value may only be 0 or 1";
        ValueProblem p = new ValueProblem(msg);
        p.setNotification(this, PREVSPRAYEDHOUSEHOLDS);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validatePrevSprayedStructures()
  {
    if(this.getPrevSprayedStructures() != null)
    {
      if(this.getPrevSprayedStructures() != 1 || this.getPrevSprayedStructures() != 0)
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
    if(this.getPrevSprayedHouseholds() != null)
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
//    SprayMethod method = this.getSprayMethod();

//    validateHouseholds(method);
//    validateSprayedHouseholds(method);
//    validatePrevSprayedHouseholds(method);
//    validateRooms(method);
//    validateStructures();
//    validateSprayedStructures();
//    validatePrevSprayedStructures();

    super.apply();
  }

  public static HouseholdSprayStatus getHouseholdByIdAndHousehold(String householdId, Integer household)
  {
    HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getHouseholdId().EQ(householdId));
    query.AND(query.getHouseholds().EQ(household));

    return query(query);
  }

  public static HouseholdSprayStatus getHouseholdByIdAndSprayedHousehold(String householdId, Integer sprayedHousehold)
  {
    HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getHouseholdId().EQ(householdId));
    query.AND(query.getSprayedHouseholds().EQ(sprayedHousehold));

    return query(query);
  }

  public static HouseholdSprayStatus getHouseholdByIdAndPrevSprayed(String householdId, Integer prevSprayedHousehold)
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
