package dss.vector.solutions.general;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dss.vector.solutions.PeriodMonthProblem;
import dss.vector.solutions.PeriodQuarterProblem;
import dss.vector.solutions.PeriodWeekProblem;
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

      calendar.set(Calendar.MONTH, 3 * ( period - 1 ) + 1);
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

      super.setPeriod( ( month - 1 ) / 3 + 1);
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

  private static GregorianCalendar getEpiCalendar(int year)
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

  public Integer getNumberOfEpiWeeks()
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

  public static Date snapToEpiWeek(Date startDate)
  {
    int period = Calendar.DAY_OF_WEEK;

    Calendar cal = new GregorianCalendar();
    cal.setTime(startDate);
    cal = getEpiCalendar(cal.get(Calendar.YEAR));
    cal.setTime(startDate);

    int piviot = cal.get(period);
    int min = cal.getActualMinimum(period);
    int max = cal.getActualMaximum(period);

    int days_before_piviot = piviot - min;
    int days_after_piviot = max - piviot;

    // beginning of week wins in case of tie
    if (days_before_piviot < days_after_piviot)
    {
      cal.set(period, min);
    }
    else
    {
      cal.set(period, max);
    }
    return cal.getTime();
  }

  public static Date snapToMonth(Date startDate)
  {
    int period = Calendar.DAY_OF_MONTH;

    Calendar cal = new GregorianCalendar();
    cal.setTime(startDate);
    cal = getEpiCalendar(cal.get(Calendar.YEAR));
    cal.setTime(startDate);

    int piviot = cal.get(period);
    int max = cal.getActualMaximum(period);
    int min = cal.getActualMinimum(period);

    int days_before_piviot = piviot - min;
    int days_after_piviot = max - piviot;
    // beginning of month wins in case of tie
    if (days_before_piviot < days_after_piviot)
    {
      cal.set(period, min);
    }
    else
    {
      cal.set(period, max-1);
    }
    return cal.getTime();
  }

  public static Date snapToQuarter(Date startDate)
  {
    int period = Calendar.DAY_OF_YEAR;

    Calendar cal = new GregorianCalendar();
    cal.setTime(startDate);
    cal = getEpiCalendar(cal.get(Calendar.YEAR));
    cal.setTime(startDate);

    int quarter = ( ( cal.get(Calendar.MONTH) - 1 ) / 3 );
    Calendar startOfQuarter = new GregorianCalendar(cal.get(Calendar.YEAR), quarter * 3, 1);
    Calendar endOfQuarter = (Calendar) startOfQuarter.clone();
    endOfQuarter.add(Calendar.MONTH, 3);
    endOfQuarter.add(Calendar.DAY_OF_YEAR, -1);

    Date quarterStartDate = startOfQuarter.getTime();
    Date quarterEndDate = endOfQuarter.getTime();

    int piviot = cal.get(period);
    int min = startOfQuarter.get(period);
    int max = endOfQuarter.get(period);

    int days_before_piviot = piviot - min;
    int days_after_piviot = max - piviot;

    // begining of month wins in case of tie
    if (days_before_piviot < days_after_piviot)
    {
      return quarterStartDate;
    }
    else
    {
      return quarterEndDate;
    }
  }

  public static Date snapToSeason(Date snapable)
  {
    return snapable;
  }

  public PeriodType getEpiPeriodType()
  {
    return (PeriodType) this.getPeriodType().get(0);
  }

  public Integer getYear()
  {
    return super.getEpiYear();
  }

  public static void validate(PeriodType periodType, Integer period, Integer year)
  {
    if (period > periodType.getMaximumPeriod() && periodType.equals(PeriodType.QUARTER))
    {
      PeriodQuarterProblem p = new PeriodQuarterProblem();
      p.setPeriod(period);
      p.setMaxPeriod(periodType.getMaximumPeriod());
      p.throwIt();
    }
    else if (period > periodType.getMaximumPeriod() && periodType.equals(PeriodType.MONTH))
    {
      PeriodMonthProblem p = new PeriodMonthProblem();
      p.setPeriod(period);
      p.setMaxPeriod(periodType.getMaximumPeriod());
      p.throwIt();
    }
    else if (period > periodType.getMaximumPeriod() && periodType.equals(PeriodType.WEEK))
    {
      PeriodWeekProblem p = new PeriodWeekProblem();
      p.setPeriod(period);
      p.setMaxPeriod(periodType.getMaximumPeriod());
      p.throwIt();
    }
  }

}
