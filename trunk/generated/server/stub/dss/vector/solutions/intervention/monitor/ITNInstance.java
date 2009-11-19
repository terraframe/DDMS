package dss.vector.solutions.intervention.monitor;

import java.util.Calendar;

import com.terraframe.mojo.dataaccess.MdAttributeBooleanDAOIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.CurrentYearProblem;
import dss.vector.solutions.Response;
import dss.vector.solutions.ResponseMaster;

public class ITNInstance extends ITNInstanceBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1428171551;

  public ITNInstance()
  {
    super();
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
    validateYearRecieved();
    validateMonthRetreated();
    validateYearRetreated();
    validatePurpose();
    validatePurposeComments();
    validateWashFrequency();
    validateWashPeriod();

    boolean first = this.isNew() && !this.isAppliedToDB();

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
  public void validateYearRecieved()
  {
    if (this.getYearRecieved() != null && this.getYearRecieved() > Calendar.getInstance().get(Calendar.YEAR))
    {
      String msg = "Year of net retreatment cannont be greater than the current year";
      CurrentYearProblem p = new CurrentYearProblem(msg);
      p.setNotification(this, YEARRETREATED);
      p.setYearOfDate(this.getYearRetreated());
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateMonthRetreated()
  {
    if (this.getMonthRetreated().size() > 0 && this.getReteated() != null && !this.getReteated())
    {
      String msg = "Cannot have month of net retreatment when retreatment is false";
      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, MONTHRETREATED);
      p.setInputAttribute(getReteatedMd().getDisplayLabel(Session.getCurrentLocale()));
      p.setInputValue( ( (MdAttributeBooleanDAOIF) getReteatedMd() ).getNegativeDisplayLabel(Session.getCurrentLocale()));
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void validateYearRetreated()
  {
    if (this.getYearRetreated() != null)
    {
      if (this.getReteated() != null && !this.getReteated())
      {
        String msg = "Cannot have month of net retreatment when retreatment is false";
        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, YEARRETREATED);
        p.setInputAttribute(getReteatedMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue( ( (MdAttributeBooleanDAOIF) getReteatedMd() ).getNegativeDisplayLabel(Session.getCurrentLocale()));
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
