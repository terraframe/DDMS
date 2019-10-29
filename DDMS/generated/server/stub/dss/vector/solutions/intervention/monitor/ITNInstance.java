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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.CurrentYearProblem;
import dss.vector.solutions.MonthOfYear;
import dss.vector.solutions.Response;
import dss.vector.solutions.ResponseMaster;

public class ITNInstance extends ITNInstanceBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1428171551;

  public ITNInstance()
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
    else if (this.getNetId() != null)
    {
      return this.getClassDisplayLabel() + ": " + this.getNetId();
    }
    
    return super.toString();
  }
  
  @Override
  protected String buildKey()
  {
    if (this.getNetId() != null)
    {
      return this.getNetId();
    }

    return this.getId();
  }

  @Override
  @Transaction
  public void apply()
  {
    validateYearReceived();
    validateMonthRetreated();
    validateYearRetreated();
    validatePurpose();
    validatePurposeComments();
    validateWashFrequency();
    validateWashPeriod();

    boolean first = this.isNew() && !this.isAppliedToDB();
    
    this.setReceivedDate();
    this.setRetreatedDate();
    
    super.apply();

    if (first)
    {
      HouseholdITNInstance householdPerson = new HouseholdITNInstance(this.getHousehold(), this);
      householdPerson.apply();
    }
    
    Household household = this.getHousehold();

    if(household != null)
    {
      household.lock();
      
      long count = ITNInstance.getCount(household);
      
      household.setNets((int) count);
      household.apply();
    }
  }
  
  @Override
  @Transaction
  public void delete()
  {
    super.delete();
    
    Household household = this.getHousehold();

    if(household != null)
    {
      household.lock();
      
      long count = ITNInstance.getCount(household);
      
      household.setNets((int) count);
      household.apply();
    }
  }
  
  private Date getDate(Integer year, List<MonthOfYear> list)
  {
    if(year != null)
    {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.set(Calendar.YEAR, year);    
    
      if(list.size() > 0)
      {
        int month = 1;
        MonthOfYear monthOfYear = list.get(0);
                
        switch(monthOfYear)
        {
          case JANUARY:
            month = 0;
            break;
          case FEBRUARY:
            month = 1;
            break;
          case MARCH:
            month = 2;
            break;
          case APRIL:
            month = 3;
            break;
          case MAY:
            month = 4;
            break;
          case JUNE:
            month = 5;
            break;
          case JULY:
            month = 6;
            break;
          case AUGUST:
            month = 7;
            break;
          case SEPTEMBER:
            month = 8;
            break;
          case OCTOBER:
            month = 9;
            break;
          case NOVEMBER:
            month = 10;
            break;
          default:
            month = 11;
            break;
        }
        
        calendar.set(Calendar.MONTH, month);
      }
      
      return calendar.getTime();      
    }

    return null;
  }

  private void setRetreatedDate()
  {
    Date date = this.getDate(this.getYearRetreated(), this.getMonthRetreated());

    this.setRetreatedDate(date);
  }

  private void setReceivedDate()
  {
    Date date = this.getDate(this.getYearReceived(), this.getMonthReceived());

    this.setReceivedDate(date);
  }

  @Override
  public void validateYearReceived()
  {
    if (this.getYearReceived() != null && this.getYearReceived() > Calendar.getInstance().get(Calendar.YEAR))
    {
      String msg = "Year net is received cannont be greater than the current year";
      CurrentYearProblem p = new CurrentYearProblem(msg);
      p.setNotification(this, YEARRECEIVED);
      p.setYearOfDate(this.getYearReceived());
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateMonthRetreated()
  {
    if (this.getMonthRetreated().size() > 0 && this.getRetreated() != null && !this.getRetreated())
    {
      String msg = "Cannot have month of net retreatment when retreatment is false";
      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, MONTHRETREATED);
      p.setInputAttribute(getRetreatedMd().getDisplayLabel(Session.getCurrentLocale()));
      p.setInputValue( ( (MdAttributeBooleanDAOIF) getRetreatedMd() ).getNegativeDisplayLabel(Session.getCurrentLocale()));
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void validateYearRetreated()
  {
    if (this.getYearRetreated() != null)
    {
      if (this.getRetreated() != null && !this.getRetreated())
      {
        String msg = "Cannot have month of net retreatment when retreatment is false";
        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, YEARRETREATED);
        p.setInputAttribute(getRetreatedMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue( ( (MdAttributeBooleanDAOIF) getRetreatedMd() ).getNegativeDisplayLabel(Session.getCurrentLocale()));
        p.apply();
        p.throwIt();
      }

      if (this.getYearRetreated() > Calendar.getInstance().get(Calendar.YEAR))
      {
        String msg = "Year of net retreatment cannont be greater than the current year";
        CurrentYearProblem p = new CurrentYearProblem(msg);
        p.setNotification(this, YEARRETREATED);
        p.setYearOfDate(this.getYearRetreated());
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validatePurpose()
  {
    if (this.getPurpose() != null)
    {
      if (this.getNotUsedForSleeping() != null && !this.getNotUsedForSleeping())
      {
        String msg = "Cannot have a purpose when used for sleeping is true";
        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, PURPOSE);
        p.setInputAttribute(getNotUsedForSleepingMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue( ( (MdAttributeBooleanDAOIF) getNotUsedForSleepingMd() ).getNegativeDisplayLabel(Session.getCurrentLocale()));
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validatePurposeComments()
  {
    String comments = this.getPurposeComments();
    
    if (comments != null && !comments.equals(""))
    {
      if (this.getNotUsedForSleeping() != null && !this.getNotUsedForSleeping())
      {
        String msg = "Cannot have a purpose comments when used for sleeping is true";
        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, PURPOSECOMMENTS);
        p.setInputAttribute(getNotUsedForSleepingMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue( ( (MdAttributeBooleanDAOIF) getNotUsedForSleepingMd() ).getNegativeDisplayLabel(Session.getCurrentLocale()));
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateWashFrequency()
  {
    if (this.getWashFrequency() != null)
    {
      if (!this.getWashed().contains(Response.YES))
      {
        String msg = "Cannot have a wash frequency when washed is not 'Yes'";        
        String value = ResponseMaster.getValueForErrorMsg(this.getWashed());

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, WASHFREQUENCY);
        p.setInputAttribute(getWashedMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateWashPeriod()
  {
    if (this.getWashPeriod() != null)
    {
      if (!this.getWashed().contains(Response.YES))
      {
        String msg = "Cannot have a wash period when washed is not Yes";
        String value = ResponseMaster.getValueForErrorMsg(this.getWashed());

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, WASHPERIOD);
        p.setInputAttribute(getWashedMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  @Transaction
  public ITNInstanceView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public ITNInstanceView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public ITNInstanceView getView()
  {
    ITNInstanceView view = new ITNInstanceView();

    view.populateView(this);

    return view;
  }

  public static long getCount(Household household)
  {
    ITNInstanceQuery query = new ITNInstanceQuery(new QueryFactory());
    query.WHERE(query.getHousehold().EQ(household));
    
    return query.getCount();
    
  }
}
