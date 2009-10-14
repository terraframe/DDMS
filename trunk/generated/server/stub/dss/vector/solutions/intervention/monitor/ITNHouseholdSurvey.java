package dss.vector.solutions.intervention.monitor;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.terraframe.mojo.dataaccess.MdAttributeBooleanDAOIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.RangeValueProblem;

public class ITNHouseholdSurvey extends ITNHouseholdSurveyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252970203643L;

  public ITNHouseholdSurvey()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    // ITN Household Survey class has no attributes that can form a unique
    // identifier
    return this.getId();
  }

  public static ITNHouseholdSurveyView getView(String id)
  {
    return ITNHouseholdSurvey.get(id).getView();
  }

  public ITNHouseholdSurveyView getView()
  {
    ITNHouseholdSurveyView view = new ITNHouseholdSurveyView();
    view.populateView(this);

    return view;
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    // Lock the grid relationship also, this must be in the same transaction

    for (ITNHouseholdSurveyTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.lock();
    }

    for (ITNHouseholdSurveyNet net : this.getAllNetsRel())
    {
      net.lock();
    }

    for (ITNHouseholdSurveyNonUseReason reason : this.getAllNonUseReasonsRel())
    {
      reason.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    // Unlock the grid relationship also, this must be in the same transaction
    for (ITNHouseholdSurveyTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.unlock();
    }

    for (ITNHouseholdSurveyNet net : this.getAllNetsRel())
    {
      net.unlock();
    }

    for (ITNHouseholdSurveyNonUseReason reason : this.getAllNonUseReasonsRel())
    {
      reason.unlock();
    }
  }

  @Override
  public ITNHouseholdSurveyView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public ITNHouseholdSurveyView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public void apply()
  {
    this.validateStartDate();
    this.validateEndDate();
    this.validateFreeProvider();
    this.validateBoughtProvider();
    this.validateWashFrequency();
    this.validateWashInterval();
    this.validateRetreatmentPeriod();
    this.validateMonthReceived();
    this.validateYearReceived();

    super.apply();
  }
  
  @Override
  public void validateMonthReceived()
  {
    int lowerLimit = 1;
    int upperLimit = 12;
    
    if(this.getMonthReceived() != null && !(this.getMonthReceived() <= upperLimit && this.getMonthReceived() >= lowerLimit))
    {
      String msg = "Month must be between [" + lowerLimit + "] and [" + upperLimit + "]";
      RangeValueProblem p = new RangeValueProblem(msg);
      p.setNotification(this, MONTHRECEIVED);
      p.setInvalidValue(this.getMonthReceived());
      p.setLowerLimit(lowerLimit);
      p.setUpperLimit(upperLimit);
      p.apply();
      
      p.throwIt();
    }
  }
  
  @Override
  public void validateYearReceived()
  {
    if(this.getYearReceived() != null)
    {
      Calendar cal = Calendar.getInstance();
      cal.setTime(new Date());

      int lowerLimit = 1900;
      int upperLimit = cal.get(Calendar.YEAR);
      
      if(! (this.getYearReceived() > lowerLimit && this.getYearReceived() <= upperLimit))
      {
        String msg = "Year must be between [" + lowerLimit + "] and [" + upperLimit + "]";

        RangeValueProblem p = new RangeValueProblem(msg);
        p.setNotification(this, YEARRECEIVED);
        p.setInvalidValue(this.getYearReceived());
        p.setLowerLimit(lowerLimit);
        p.setUpperLimit(upperLimit);
        p.apply();
        
        p.throwIt();
      }
    }
  }

  @Override
  public void validateStartDate()
  {
    if (this.getStartDate() != null)
    {
      super.validateStartDate();

      Date current = new Date();

      if (current.before(this.getStartDate()))
      {
        String msg = "It is impossible to have a start date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getStartDate());
        p.setCurrentDate(current);
        p.setNotification(this, STARTDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateEndDate()
  {
    if (this.getEndDate() != null)
    {
      super.validateEndDate();

      Date current = new Date();

      if (current.before(this.getEndDate()))
      {
        String msg = "It is impossible to have a end date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getEndDate());
        p.setCurrentDate(current);
        p.setNotification(this, ENDDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateFreeProvider()
  {
    if (this.getNetsObtained() != null && this.getFreeProvider() != null && !this.getNetsObtained())
    {
      String msg = "Free net provider is not applicable when the net provider type is 'bought'";
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, FREEPROVIDER);
      p.setInputAttribute(getNetsObtainedMd().getDisplayLabel(locale));
      p.setInputValue( ( (MdAttributeBooleanDAOIF) getNetsObtainedMd() ).getPositiveDisplayLabel(locale));
      p.apply();

      p.throwIt();
    }
  }
  
  @Override
  public void validateBoughtProvider()
  {
    if (this.getNetsObtained() != null && this.getBoughtProvider() != null && this.getNetsObtained())
    {
      String msg = "Bought net provider is not applicable when the net provider type is 'free'";
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, BOUGHTPROVIDER);
      p.setInputAttribute(getNetsObtainedMd().getDisplayLabel(locale));
      p.setInputValue( ( (MdAttributeBooleanDAOIF) getNetsObtainedMd() ).getNegativeDisplayLabel(locale));
      p.apply();

      p.throwIt();
    }
  }
  
  @Override
  public void validateWashFrequency()
  {
    //FIXME MO UPGRADE
//    if (this.getWashed() != null && this.getWashFrequency() != null && !this.getWashed().contains(FeverResponse.YES))
//    {
//      String msg = "Net wash frequency is not applicable when the nets are not washed";
//      Locale locale = Session.getCurrentLocale();
//
//      NotApplicableProblem p = new NotApplicableProblem(msg);
//      p.setNotification(this, WASHFREQUENCY);
//      p.setInputAttribute(getWashedMd().getDisplayLabel(locale));
//      p.setInputValue( FeverResponse.NO.getDisplayLabel() + "/" + FeverResponse.DONT_KNOW.getDisplayLabel());
//      p.apply();
//
//      p.throwIt();
//    }    
  }
  
  @Override
  public void validateWashInterval()
  {
    // FIXME MO UPGRADE
//    if (this.getWashed() != null && this.getWashInterval().size() > 0 && !this.getWashed().contains(FeverResponse.YES))
//    {
//      String msg = "Net wash interval is not applicable when the nets are not washed";
//      Locale locale = Session.getCurrentLocale();
//
//      NotApplicableProblem p = new NotApplicableProblem(msg);
//      p.setNotification(this, WASHINTERVAL);
//      p.setInputAttribute(getWashedMd().getDisplayLabel(locale));
//      p.setInputValue( FeverResponse.NO.getDisplayLabel() + "/" + FeverResponse.DONT_KNOW.getDisplayLabel());
//      p.apply();
//
//      p.throwIt();
//    }        
  }
  
  @Override
  public void validateRetreatmentPeriod()
  {
    if (this.getRetreated() != null && this.getRetreatmentPeriod() != null && !this.getRetreated())
    {
      String msg = "Retreatment period is not applicable when net re-treatment is 'false'";
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, RETREATMENTPERIOD);
      p.setInputAttribute(getRetreatedMd().getDisplayLabel(locale));
      p.setInputValue( ( (MdAttributeBooleanDAOIF) getRetreatedMd() ).getNegativeDisplayLabel(locale));
      p.apply();

      p.throwIt();
    }    
  }
}
