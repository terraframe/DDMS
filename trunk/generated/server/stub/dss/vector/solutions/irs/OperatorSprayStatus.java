/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.irs;

import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Session;

public class OperatorSprayStatus extends OperatorSprayStatusBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -195318466;

  public OperatorSprayStatus()
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
    if (this.getSpray() != null && this.getSprayOperator() != null)
    {
      return this.getSpray().getKey() + " - " + this.getSprayOperator().getKey();
    }

    return this.getId();
  }

  public OperatorSprayStatusView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public OperatorSprayStatusView lockView()
  {
    this.lock();

    return this.getView();
  }

  public OperatorSprayStatusView getView()
  {
    OperatorSprayStatusView view = new OperatorSprayStatusView();

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

  public void validateVerandas(SprayMethod method)
  {
    if (this.getVerandas() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Value is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, VERANDAS);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validateCattleSheds(SprayMethod method)
  {
    if (this.getCattleSheds() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Value is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, CATTLESHEDS);
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
  public void validateVerandasSprayed()
  {
    if (this.getVerandas() != null && this.getVerandasSprayed() != null && this.getVerandas() < this.getVerandasSprayed())
    {
      String msg = "The number of verandas sprayed cannot be greater than the number of verandas";

      SprayedSumProblem p = new SprayedSumProblem(msg);
      p.setNotification(this, VERANDASSPRAYED);
      p.setObjectLabel(this.getMdAttributeDAO(VERANDAS).getDisplayLabel(Session.getCurrentLocale()));
      p.setSprayedObjectLabel(this.getMdAttributeDAO(VERANDASSPRAYED).getDisplayLabel(Session.getCurrentLocale()));
      p.setObjects(this.getVerandas());
      p.setSprayedObjects(this.getVerandasSprayed());
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateCattleShedsSprayed()
  {
    if (this.getCattleSheds() != null && this.getCattleShedsSprayed() != null && this.getCattleSheds() < this.getCattleShedsSprayed())
    {
      String msg = "The number of cattle sheds sprayed cannot be greater than the number of cattle sheds";

      SprayedSumProblem p = new SprayedSumProblem(msg);
      p.setNotification(this, CATTLESHEDSSPRAYED);
      p.setObjectLabel(this.getMdAttributeDAO(CATTLESHEDS).getDisplayLabel(Session.getCurrentLocale()));
      p.setSprayedObjectLabel(this.getMdAttributeDAO(CATTLESHEDSSPRAYED).getDisplayLabel(Session.getCurrentLocale()));
      p.setObjects(this.getCattleSheds());
      p.setSprayedObjects(this.getCattleShedsSprayed());
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

  public void validateVerandasOther(SprayMethod method)
  {
    if (this.getVerandasOther() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Veranda others is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, VERANDASOTHER);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validateCattleShedsOther(SprayMethod method)
  {
    if (this.getCattleShedsOther() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Cattle sheds other is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, CATTLESHEDSOTHER);
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

  public void validateVerandasRefused(SprayMethod method)
  {
    if (this.getVerandasRefused() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Veranda refused is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, VERANDASREFUSED);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validateCattleShedsRefused(SprayMethod method)
  {
    if (this.getCattleShedsRefused() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Cattle sheds refused is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, CATTLESHEDSREFUSED);
        p.apply();
        p.throwIt();
      }
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

  public void validateVerandasLocked(SprayMethod method)
  {
    if (this.getVerandasLocked() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Veranda locked is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, VERANDASLOCKED);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validateCattleShedsLocked(SprayMethod method)
  {
    if (this.getCattleShedsLocked() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Cattle sheds locked is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, CATTLESHEDSLOCKED);
        p.apply();
        p.throwIt();
      }
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

  @Override
  @Transaction
  public void apply()
  {
    SprayMethod method = this.getSprayMethod();

    // Validate MOP-UP
    validateHouseholds(method);
    validateStructures(method);
    validatePrevSprayedHouseholds(method);
    validatePrevSprayedStructures(method);
    validateRooms(method);
    validateVerandas(method);
    validateCattleSheds(method);
    validateNumberOfPeople(method);
    validatePeople(method);
    validateBedNets(method);
    validateRoomsWithBedNets(method);
    validateWrongSurface(method);
    validateLocked(method);
    validateVerandasLocked(method);
    validateCattleShedsLocked(method);
    validateRefused(method);
    validateVerandasRefused(method);
    validateCattleShedsRefused(method);
    validateOther(method);
    validateVerandasOther(method);
    validateCattleShedsOther(method);

    // Validate values
    validateSprayedHouseholds();
    validateSprayedStructures();
    validateSprayedRooms();
    validateVerandasSprayed();
    validateCattleShedsSprayed();

    super.apply();
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

  protected void validateNumberOfPeople(SprayMethod method)
  {
    if (this.getNumberOfPeople() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "# People is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, NUMBEROFPEOPLE);
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
    TeamSpray _spray = this.getSpray();
    List<SprayMethod> method = _spray.getSprayMethod();

    if (method.size() > 0)
    {
      return method.get(0);
    }

    return null;
  }
}
