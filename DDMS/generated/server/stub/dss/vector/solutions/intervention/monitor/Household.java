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
package dss.vector.solutions.intervention.monitor;

import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.session.Session;

import dss.vector.solutions.Response;
import dss.vector.solutions.ResponseMaster;

public class Household extends HouseholdBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641282092L;

  public Household()
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
    else if (this.getHouseholdName() != null)
    {
      return this.getClassDisplayLabel() + ": " + this.getHouseholdName();
    }
    
    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    if (this.getHouseholdName() != null)
    {
      return this.getHouseholdName();
    }

    return this.getId();
  }

  public static HouseholdView getView(String id)
  {
    return Household.get(id).getView();
  }

  public HouseholdView getView()
  {
    HouseholdView view = new HouseholdView();
    view.populateView(this);

    return view;
  }

  @Override
  public HouseholdView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public HouseholdView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public void validateLastSprayed()
  {
    if (this.getLastSprayed() != null)
    {
      if(!this.getHasBeenSprayed().contains(Response.YES))
      {
        String msg = "Cannot have a last sprayed value when has been sprayed is not yes.";
        String value = ResponseMaster.getValueForErrorMsg(this.getHasBeenSprayed());

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, LASTSPRAYED);
        p.setInputAttribute(getHasBeenSprayedMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
      
      if (this.getLastSprayed() > 12)
      {
        String msg = "Not able to have a last spray date greater than 12";
        SprayProblem p = new SprayProblem(msg);
        p.setMonths(this.getLastSprayed());
        p.setNotification(this, LASTSPRAYED);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateWindowType()
  {
    if (this.getHasWindows() != null && !this.getHasWindows())
    {
      if (this.getWindowType() != null)
      {
        String msg = "A window type is not allowed to be set when has windows is false";
        WindowTypeProblem p = new WindowTypeProblem(msg);
        p.setNotification(this, WINDOWTYPE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  @Transaction
  public void apply()
  {
    validateLastSprayed();
    validateWindowType();

    boolean first = !this.isAppliedToDB() && this.isNew();

    super.apply();

    if (first)
    {
      SurveyHousehold surveyHousehold = this.addSurveyPoints(this.getSurveyPoint());
      surveyHousehold.apply();
    }
  }

  @Override
  @Transaction
  public void delete()
  {
    // First delete all of the Surveyed People of this household
    OIterator<? extends ITNInstance> itns = this.getAllITNs();
    
    try
    {
      List<? extends ITNInstance> list = itns.getAll();
      
      for (ITNInstance itn : list)
      {
        itn.delete();
      }
    }
    finally
    {
      itns.close();
    }
    
    OIterator<? extends SurveyedPerson> people = this.getAllSurveyedPeople();

    try
    {
      List<? extends SurveyedPerson> list = people.getAll();

      for (SurveyedPerson person : list)
      {
        person.delete();
      }
    }
    finally
    {
      people.close();
    }

    super.delete();
  }
}
