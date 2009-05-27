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
     EpiDate newInstance =  new EpiDate();
     newInstance.construct(periodType, period, year);
     return newInstance;
  }

  public static EpiDate getInstanceByDate(Date startDate, Date endDate)
  {
     EpiDate newInstance =  new EpiDate();
     newInstance.construct(startDate,  endDate);
     return newInstance;
  }

  // First Period is Zero
  public void construct(PeriodType periodType, int period, Integer year)
  {
    super.setPeriod(period);
    super.setEpiYear(year);
    super.addPeriodType(periodType);
    GregorianCalendar tempCal = makeRegularCalendar(year);

    if (periodType.equals(PeriodType.WEEK))
    {
      tempCal = makeEpiCalendar(year);
      tempCal.add(Calendar.WEEK_OF_YEAR, period);
      super.setStartDate(tempCal.getTime());
      tempCal.add(Calendar.WEEK_OF_YEAR, 1);
      tempCal.add(Calendar.DAY_OF_MONTH, -1);
      super.setEndDate(tempCal.getTime());
    }
    else if (periodType.equals(PeriodType.MONTH))
    {
      tempCal.add(Calendar.MONTH, period);
      super.setStartDate(tempCal.getTime());
      tempCal.add(Calendar.MONTH, 1);
      tempCal.add(Calendar.DAY_OF_MONTH, -1);
      super.setEndDate(tempCal.getTime());
    }
    else if (periodType.equals(PeriodType.QUARTER))
    {
      tempCal.add(Calendar.MONTH, 3 * period);
      tempCal.add(Calendar.MONTH, -1);
      super.setStartDate(tempCal.getTime());
      tempCal.add(Calendar.MONTH, 3);
      tempCal.add(Calendar.DAY_OF_MONTH, -1);
      super.setEndDate(tempCal.getTime());
    }
  }

  public void construct(Date startDate, Date endDate)
  {
    super.setStartDate(startDate);
    super.setEndDate(endDate);
    GregorianCalendar tempCal = makeRegularCalendar(startDate);
    super.setEpiYear(tempCal.get(Calendar.YEAR));

    if (plusOneWeek(startDate).equals(endDate))
    {
      tempCal = makeEpiCalendar(getEpiYear());
      tempCal.setTime(startDate);
      super.addPeriodType(PeriodType.WEEK);
      super.setPeriod(tempCal.get(Calendar.WEEK_OF_YEAR));
    }
    else if (plusOneMonth(startDate).equals(endDate))
    {
      super.addPeriodType(PeriodType.MONTH);
      int month =  tempCal.get(Calendar.MONTH);
      super.setPeriod( month - 1 );
    }
    else
    {
      super.addPeriodType(PeriodType.QUARTER);
      int month =  tempCal.get(Calendar.MONTH);
      super.setPeriod( month / 3);
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
    int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
    GregorianCalendar cal = new GregorianCalendar();
    cal.clear();
    cal.setFirstDayOfWeek(startDay);
    cal.set(year, 1, 1);
    return cal;
  }

  private GregorianCalendar makeRegularCalendar(Date date)
  {
    int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
    GregorianCalendar cal = new GregorianCalendar();
    cal.clear();
    cal.setFirstDayOfWeek(startDay);
    cal.setTime(date);
    return cal;
  }

  public GregorianCalendar getEpiCal()
  {
    return makeEpiCalendar(getEpiYear());
  }

  private Date plusOneQuarter(Date startDate)
  {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);
    c1.add(Calendar.MONTH, 1);
    c1.add(Calendar.DAY_OF_MONTH, -1);
    return c1.getTime();
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

  public PeriodType getEpiPeriodType()
  {
    return (PeriodType) this.getPeriodType().get(0);
  }

  public Integer getYear()
  {
    return super.getEpiYear();
  }


}
