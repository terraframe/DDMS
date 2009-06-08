package dss.vector.solutions.general;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.surveillance.PeriodType;

public class EpiDate extends EpiDateBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1243373494497L;

  public EpiDate()
  {
    super();
  }

  public static EpiDate getInstanceByPeriod(PeriodType periodType, Integer period, Integer year)
  {
    EpiDate newInstance = new EpiDate();
    newInstance.construct(periodType, period, year);
    return newInstance;
  }

  public static EpiDate getInstanceByDate(Date startDate, Date endDate)
  {
    EpiDate newInstance = new EpiDate();
    newInstance.construct(startDate, endDate);
    return newInstance;
  }

  // First Period is Zero
  public void construct(PeriodType periodType, int period, Integer year)
  {
    super.clearPeriodType();
    super.setPeriod(period);
    super.setEpiYear(year);
    super.addPeriodType(periodType);
        
    if (periodType.equals(PeriodType.WEEK))
    {
      Calendar calendar = makeEpiCalendar(year);
      
      calendar.add(Calendar.WEEK_OF_YEAR, period);
      super.setStartDate(calendar.getTime());
      calendar.add(Calendar.WEEK_OF_YEAR, 1);
      calendar.add(Calendar.DAY_OF_MONTH, -1);
      super.setEndDate(calendar.getTime());
    }
    else if (periodType.equals(PeriodType.MONTH))
    {
      Calendar calendar = makeRegularCalendar(year);
      
      calendar.set(Calendar.MONTH, period);
      super.setStartDate(calendar.getTime());
      calendar.add(Calendar.MONTH, 1);
      calendar.add(Calendar.DAY_OF_MONTH, -1);
      super.setEndDate(calendar.getTime());
    }
    else if (periodType.equals(PeriodType.QUARTER))
    {
      Calendar calendar = makeRegularCalendar(year);

      calendar.set(Calendar.MONTH, 3 * (period-1) + 1);
      super.setStartDate(calendar.getTime());
      calendar.add(Calendar.MONTH, 3);
      calendar.add(Calendar.DAY_OF_MONTH, -1);
      super.setEndDate(calendar.getTime());
    }
  }

  public void construct(Date startDate, Date endDate)
  {
    GregorianCalendar calendar = makeRegularCalendar(startDate);

    super.clearPeriodType();
    super.setStartDate(startDate);
    super.setEndDate(endDate);    
    super.setEpiYear(calendar.get(Calendar.YEAR));
    
    if (plusOneWeek(startDate).equals(endDate))
    {
      calendar = makeEpiCalendar(getEpiYear());
      calendar.setTime(startDate);
      
      super.addPeriodType(PeriodType.WEEK);
      super.setPeriod(calendar.get(Calendar.WEEK_OF_YEAR));
    }
    else if (plusOneMonth(startDate).equals(endDate))
    {
      int month = calendar.get(Calendar.MONTH);

      super.setPeriod(month);
      super.addPeriodType(PeriodType.MONTH);
    }
    else
    {
      int month = calendar.get(Calendar.MONTH);

      super.setPeriod((month-1)/3 + 1);
      super.addPeriodType(PeriodType.QUARTER);
    }
  }

  private GregorianCalendar makeEpiCalendar(int year)
  {
    int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
    GregorianCalendar cal = new GregorianCalendar();
    cal.clear();
    cal.setFirstDayOfWeek(startDay);
    // The last day of the first epi week is the first SAT in Jan provided it is
    // 4 or more days in.
    cal.setMinimalDaysInFirstWeek(4 + startDay);
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.WEEK_OF_YEAR, 1);
    cal.add(Calendar.DAY_OF_WEEK, 1);
    return cal;
  }

  private GregorianCalendar makeRegularCalendar(int year)
  {
    GregorianCalendar cal = new GregorianCalendar();
    cal.clear();
    cal.set(year, 0, 1);
    
    return cal;
  }

  private GregorianCalendar makeRegularCalendar(Date date)
  {
    GregorianCalendar cal = new GregorianCalendar();
    cal.clear();
    cal.setTime(date);
    return cal;
  }

  public GregorianCalendar getEpiCal()
  {
    return makeEpiCalendar(getEpiYear());
  }

  private Date plusOneMonth(Date startDate)
  {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);
    c1.add(Calendar.MONTH, 1);
    c1.add(Calendar.DAY_OF_MONTH, -1);
    return c1.getTime();
  }

  private Date plusOneWeek(Date startDate)
  {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);
    c1.add(Calendar.DAY_OF_YEAR, 6);
    return c1.getTime();
  }

  public  Integer getNumberOfEpiWeeks()
  {
    Calendar thisYear = makeEpiCalendar(this.getEpiYear());
    Calendar nextYear = makeEpiCalendar(this.getEpiYear() + 1);
    // add 52 weeks + 1 day and see if that puts us into the next epi year
    thisYear.add(Calendar.WEEK_OF_YEAR, 52);
    thisYear.add(Calendar.DAY_OF_WEEK, 1);
    if (nextYear.after(thisYear))
    {
      return 53;
    }
    else
    {
      return 52;
    }
  }

  public PeriodType getEpiPeriodType()
  {
    return (PeriodType) this.getPeriodType().get(0);
  }

  public Integer getYear()
  {
    return super.getEpiYear();
  }

}
