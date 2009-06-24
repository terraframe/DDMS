package dss.vector.solutions.general;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.surveillance.PeriodType;

public class MalariaSeason extends MalariaSeasonBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242259530708L;

  public MalariaSeason()
  {
    super();
  }

  @Transaction
  public void apply()
  {
    super.validateEndDate();
    super.validateEndDate();

    validateStartEndDates();
    validateLength();
    validateOverlap();

    super.apply();
  }

  public EpiDate[] getEpiWeeks() {
    //Date epiStart = Property.getDate(PropertyInfo.EPI_WEEK_PACKAGE,PropertyInfo.EPI_START);
    long seasonStart = this.getStartDate().getTime();
    long seasonEnd = this.getEndDate().getTime();
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(this.getStartDate());
    Integer seasonStartYear = cal.get(Calendar.YEAR);

    ArrayList<EpiDate> weeks = new ArrayList<EpiDate>();

    for(Integer i = 0;i<=800;i++)
    {
      EpiDate  epiWeek = EpiDate.getInstanceByPeriod(PeriodType.WEEK,i,seasonStartYear);
      long weekStart = epiWeek.getStartDate().getTime();

      if(weekStart > seasonStart && weekStart < seasonEnd )
      {
       weeks.add(epiWeek);


      }
    }
    return (EpiDate[]) weeks.toArray();
  }

  public void validateStartEndDates()
  {
    if (this.getStartDate().getTime() > this.getEndDate().getTime())
    {
      String msg = "The start date must be less then the end date";
      MalariaSeasonDateProblem e = new MalariaSeasonDateProblem(msg);
      e.apply();
      e.throwIt();
    }
  }

  public void validateOverlap()
  {
    if (this.getOverlap() != null)
    {
      String msg = "This malaria season overlaps with an existing season";
      MalariaSeasonOverlapProblem e = new MalariaSeasonOverlapProblem(msg);
      e.setOverlap(this.getOverlap().getSeasonName());
      e.apply();
      e.throwIt();
    }
  }

  public void validateLength()
  {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(this.getStartDate());
    calendar.add(Calendar.YEAR,1);
    if(this.getEndDate().getTime() > calendar.getTime().getTime())
    {
      String msg = "The season may not be longer then one year";
      MalariaSeasonTooLongProblem e = new MalariaSeasonTooLongProblem(msg);
      e.apply();
      e.throwIt();
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

    if (endOverlap != null && ! endOverlap.getId().equals(this.getId()))
    {
      return endOverlap;
    }

    return null;
  }
  
  public static MalariaSeason getSeasonByDate(Date date)
  {

    MalariaSeason malariaSeason = null;

    QueryFactory factory = new QueryFactory();
    MalariaSeasonQuery query = new MalariaSeasonQuery(factory);

    query.AND(query.getStartDate().LE(date));
    query.AND(query.getEndDate().GE(date));

    OIterator<? extends MalariaSeason> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {

        malariaSeason = iterator.next();
      }

    }
    finally
    {
      iterator.close();
    }

    return malariaSeason;
  }
  
  /**
   * @return A list of all malaria seasons with the most recent one first
   */
  public static MalariaSeason[] getAll()
  {
    Date today = new Date();
    MalariaSeasonQuery query = new MalariaSeasonQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getStartDate());
 
    LinkedList<MalariaSeason> seasons = new LinkedList<MalariaSeason>();
    seasons.addAll(query.getIterator().getAll());

    MalariaSeason initial = null;
    
    for(MalariaSeason season : seasons)
    {
      if(season.getStartDate().after(today))
      {
        if(initial == null)
        {
          initial = season;
        }
        
        if(season.getStartDate().before(initial.getStartDate()))
        {
          initial = season;
        }
      }
    }
        
    if(initial != null)
    {
      seasons.remove(initial);
      seasons.addFirst(initial);
    }
    
    return seasons.toArray(new MalariaSeason[seasons.size()]);
  }
}
