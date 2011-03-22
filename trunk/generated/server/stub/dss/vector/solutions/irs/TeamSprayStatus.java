package dss.vector.solutions.irs;

import java.util.List;

import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.MdssLog;
import dss.vector.solutions.query.Layer;

public class TeamSprayStatus extends TeamSprayStatusBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -451404549;
  
  public TeamSprayStatus()
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
    if(this.getSpray() != null && this.getSprayTeam() != null)
    {
      return this.getSpray().getKey() + " - " + this.getSprayTeam().getKey();
    }
    
    return this.getId();
  }
  
  public TeamSprayStatusView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public TeamSprayStatusView lockView()
  {
    this.lock();

    return this.getView();
  }

  public TeamSprayStatusView getView()
  {
    TeamSprayStatusView view = new TeamSprayStatusView();

    view.populateView(this);

    return view;
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

  @Override
  public void validateSprayedHouseholds()
  {
    if (this.getHouseholds() != null && this.getSprayedHouseholds() != null && this.getHouseholds() < this.getSprayedHouseholds())
    {
      String msg = "The number of sprayed households cannot be greater than the number of households";

      SprayedSumProblem p = new SprayedSumProblem(msg);
      p.setNotification(this, SPRAYEDHOUSEHOLDS);
      p.setObjectLabel(this.getMdAttributeDAO(HOUSEHOLDS).getDisplayLabel(Session.getCurrentLocale()));
      p.setSprayedObjectLabel(this.getMdAttributeDAO(SPRAYEDHOUSEHOLDS).getDisplayLabel(Session.getCurrentLocale()));
      p.setObjects(this.getHouseholds());
      p.setSprayedObjects(this.getSprayedHouseholds());
      p.apply();

      p.throwIt();
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
  }

  @Override
  public void apply()
  {
    SprayMethod method = this.getSprayMethod();

    // Validate MOP-UP
    validateHouseholds(method);
    validateStructures(method);
    validatePrevSprayedHouseholds(method);
    validatePrevSprayedStructures(method);
    validateRooms(method);
    validatePeople(method);
    validateBedNets(method);
    validateRoomsWithBedNets(method);
    validateLocked(method);
    validateRefused(method);
    validateOther(method);

    // Validate values
    validateSprayedHouseholds();
    validateSprayedStructures();
    validateSprayedRooms();

    super.apply();
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

  protected SprayMethod getSprayMethod()
  {
    List<SprayMethod> method = this.getSpray().getSprayMethod();

    if (method.size() > 0)
    {
      return method.get(0);
    }

    return null;
  }

}
