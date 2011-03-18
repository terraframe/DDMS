package dss.vector.solutions.general;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.surveillance.PeriodType;

public class MalariaSeason extends MalariaSeasonBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242259530708L;

  public MalariaSeason()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    
    return this.buildKey();
  }

  @Override
  protected String buildKey()
  {
    if (this.getSeasonName() != null)
    {
      return this.getSeasonName();
    }
    return this.getId();
  }

  @Transaction
  public void apply()
  {
    validateStartEndDates();
    validateLength();
    validateOverlap();

    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
    
    super.apply();
    
    // Create the epi weeks which are defined by this season
    for(EpiDate date : this.getEpiWeeks())
    {
      // EpiWeek.getEpiWeek will create an epi week if one does not
      // alread exist
      EpiCache.getWeek(date);      
    }
  }


	@Transaction
	public static MalariaSeasonQuery getAllInstancesForDisease(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber) {
		MalariaSeasonQuery query = new MalariaSeasonQuery(new com.runwaysdk.query.QueryFactory());
	    query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
	    getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
	    return query;
	}
	
  public EpiDate[] getEpiWeeks()
  {
    // Date epiStart =
    // Property.getDate(PropertyInfo.EPI_WEEK_PACKAGE,PropertyInfo.EPI_START);
    long seasonStart = this.getStartDate().getTime();
    long seasonEnd = this.getEndDate().getTime();
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(this.getStartDate());
    Integer seasonStartYear = cal.get(Calendar.YEAR);

    ArrayList<EpiDate> weeks = new ArrayList<EpiDate>();
    
    for (Integer i = 0; i <= 106; i++)
    {
      EpiDate epiWeek = EpiDate.getInstanceByPeriod(PeriodType.WEEK, i, seasonStartYear);
      
      long weekStart = epiWeek.getStartDate().getTime();
      
      if (weekStart >= seasonStart && weekStart <= seasonEnd)
      {
        weeks.add(epiWeek);

      }
    }
    EpiDate weeksArr[] = weeks.toArray(new EpiDate[weeks.size()]);
    return  weeksArr;
  }
  
  public EpiDate getEpiWeek(Date date)
  {
    EpiDate[] weeks = this.getEpiWeeks();
    
    for(EpiDate week : weeks)
    {
      Date weekStart = week.getStartDate();
      Date weekEnd = week.getEndDate();

      if(date.equals(weekStart) || date.equals(weekEnd) || (date.after(weekStart) && date.before(weekEnd)))
      {
        return week;
      }
    }
    
    String msg = "Date [" + date + "] is not contained in the Malaira Season [" + this.getSeasonName() +"]";
    MalariaSeasonDateException e = new MalariaSeasonDateException(msg);
    e.setSeasonName(this.getSeasonName());
    e.setWeekDate(date);
    e.apply();
    
    throw e;
  }

  public void validateStartEndDates()
  {
    if (this.getStartDate() != null && this.getEndDate() != null && this.getStartDate().getTime() > this.getEndDate().getTime())
    {
      String msg = "The start date must be less then the end date";
      MalariaSeasonDateProblem e = new MalariaSeasonDateProblem(msg);
      e.apply();
      e.throwIt();
    }
  }

  public void validateOverlap()
  {
    if (this.getStartDate() != null && this.getEndDate() != null && this.getOverlap() != null)
    {
      String msg = "This transmission season overlaps with an existing season";
      MalariaSeasonOverlapProblem e = new MalariaSeasonOverlapProblem(msg);
      e.setOverlap(this.getOverlap().getSeasonName());
      e.apply();
      e.throwIt();
    }
  }

  public void validateLength()
  {
    if (this.getStartDate() != null && this.getEndDate() != null)
    {
      GregorianCalendar calendar = new GregorianCalendar();
      calendar.setTime(this.getStartDate());
      calendar.add(Calendar.YEAR, 1);
      if (this.getEndDate().getTime() > calendar.getTime().getTime())
      {
        String msg = "The season may not be longer then one year";
        MalariaSeasonTooLongProblem e = new MalariaSeasonTooLongProblem(msg);
        e.apply();
        e.throwIt();
      }
    }

  }

  private MalariaSeason getOverlap()
  {

    MalariaSeason startOverlap = MalariaSeason.getSeasonByDate(this.getStartDate());

    if (startOverlap != null && !startOverlap.getId().equals(this.getId()))
    {
      return startOverlap;
    }

    MalariaSeason endOverlap = MalariaSeason.getSeasonByDate(this.getEndDate());

    if (endOverlap != null && !endOverlap.getId().equals(this.getId()))
    {
      return endOverlap;
    }

    return null;
  }

  public static MalariaSeason getSeasonByDate(Date date)
  {
	  MalariaSeasonQuery query = MalariaSeason.getSeasonQueryByDate(date, new QueryFactory());
	  
	  return getSeasonFromQueryByDate(query, date);
  }
  

  public static MalariaSeason getNextSeasonByDate(Date date)
  {
	  MalariaSeasonQuery query = MalariaSeason.getNextSeasonQueryByDate(date, new QueryFactory());

	  return getSeasonFromQueryByDate(query, date);
  }
  

  public static MalariaSeason getSeasonFromQueryByDate(MalariaSeasonQuery query, Date date)
  {
    OIterator<? extends MalariaSeason> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

    }
    finally
    {
      iterator.close();
    }

    return null;
  }
  
  
  public static MalariaSeasonQuery getNextSeasonQueryByDate(Date date, QueryFactory factory)
  {    
    MalariaSeasonQuery query = new MalariaSeasonQuery(factory);
    
    query.AND(query.getStartDate().GE(date));
    query.AND(query.getDisease().EQ(Disease.getCurrent()));

    return query;
  }
  
  public static MalariaSeasonQuery getSeasonQueryByDate(Date date, QueryFactory factory)
  {    
    MalariaSeasonQuery query = new MalariaSeasonQuery(factory);
    
    query.AND(query.getStartDate().LE(date));
    query.AND(query.getEndDate().GE(date));
    query.AND(query.getDisease().EQ(Disease.getCurrent()));


    return query;
  }

  /**
   * @return A list of all malaria seasons with the most recent one first
   */
  public static MalariaSeason[] getAll()
  {
    Date today = new Date();
    MalariaSeasonQuery query = new MalariaSeasonQuery(new QueryFactory());
    query.ORDER_BY_DESC(query.getStartDate());

    LinkedList<MalariaSeason> seasons = new LinkedList<MalariaSeason>();
    seasons.addAll(query.getIterator().getAll());

    MalariaSeason initial = null;

    for (MalariaSeason season : seasons)
    {
      if (season.getStartDate().after(today))
      {
        if (initial == null)
        {
          initial = season;
        }

        if (season.getStartDate().before(initial.getStartDate()))
        {
          initial = season;
        }
      }
    }

    if (initial != null)
    {
      seasons.remove(initial);
      seasons.addFirst(initial);
    }

    return seasons.toArray(new MalariaSeason[seasons.size()]);
  }
  
  /**
   * @return A list of all malaria seasons with the most recent one first
   */
  public static MalariaSeason[] getAllForDisease()
  {
    Date today = new Date();
    MalariaSeasonQuery query = new MalariaSeasonQuery(new QueryFactory());
    query.ORDER_BY_DESC(query.getStartDate());
    query.WHERE(query.getDisease().EQ(Disease.getCurrent()));

    LinkedList<MalariaSeason> seasons = new LinkedList<MalariaSeason>();
    seasons.addAll(query.getIterator().getAll());

    MalariaSeason initial = null;

    for (MalariaSeason season : seasons)
    {
      if (season.getStartDate().after(today))
      {
        if (initial == null)
        {
          initial = season;
        }

        if (season.getStartDate().before(initial.getStartDate()))
        {
          initial = season;
        }
      }
    }

    if (initial != null)
    {
      seasons.remove(initial);
      seasons.addFirst(initial);
    }

    return seasons.toArray(new MalariaSeason[seasons.size()]);
  }
}
