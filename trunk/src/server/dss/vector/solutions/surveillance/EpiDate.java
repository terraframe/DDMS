package dss.vector.solutions.surveillance;

import java.util.Calendar;
import java.util.Date;

import com.terraframe.mojo.generation.loader.Reloadable;

public class EpiDate implements Reloadable
{
  private PeriodType type;

  private Integer    period;

  private Integer    year;

  private Date       startDate;

  private Date       endDate;

  public EpiDate(PeriodType periodType, int period, Integer year)
  {
    this.type = periodType;
    this.period = period;
    this.year = year;

    if (periodType.equals(PeriodType.QUARTER))
    {
      initializeQuarter(period, year);
    }
    else if (periodType.equals(PeriodType.MONTH))
    {
      initializeMonth(period, year);
    }
    else if (periodType.equals(PeriodType.WEEK))
    {
      initalizeWeek(period, year);
    }
  }

  public EpiDate(Date startDate, Date endDate)
  {
    if (this.getWeek(startDate).equals(endDate))
    {
      Calendar c1 = Calendar.getInstance();
      c1.setTime(startDate);

      this.type = PeriodType.WEEK;
      this.period = c1.get(Calendar.WEEK_OF_YEAR);
      this.year = c1.get(Calendar.YEAR);
    }
    else if (this.getMonth(startDate).equals(endDate))
    {
      Calendar c1 = Calendar.getInstance();
      c1.setTime(startDate);

      this.type = PeriodType.MONTH;
      this.period = c1.get(Calendar.MONTH);
      this.year = c1.get(Calendar.YEAR);
    }
    else
    {
      Calendar c1 = Calendar.getInstance();
      c1.setTime(startDate);
      int month = c1.get(Calendar.MONTH);

      this.type = PeriodType.QUARTER;
      this.year = c1.get(Calendar.YEAR);

      if (month < 4)
      {
        this.period = 1;
      }
      else if (month < 7)
      {
        this.period = 2;
      }
      else if (month < 10)
      {
        this.period = 3;
      }
      else
      {
        this.period = 4;
      }
    }
  }

  private Date getMonth(Date startDate)
  {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);
    c1.add(Calendar.MONTH, 1);
    c1.add(Calendar.DAY_OF_MONTH, -1);

    return c1.getTime();
  }

  private Date getWeek(Date startDate)
  {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);
    c1.add(Calendar.DAY_OF_MONTH, 6);

    return c1.getTime();
  }

  private void initalizeWeek(Integer period, Integer year)
  {
    Calendar c1 = Calendar.getInstance();
    c1.clear();
    c1.set(year, 1, 1, 0, 0, 0);
    c1.set(Calendar.DATE, 1);
    c1.add(Calendar.WEEK_OF_YEAR, period);

    startDate = c1.getTime();

    c1.add(Calendar.DAY_OF_MONTH, 6);
    endDate = c1.getTime();
  }

  private void initializeMonth(Integer period, Integer year)
  {
    Calendar c1 = Calendar.getInstance();
    c1.clear();
    c1.set(year, 1, 1, 0, 0, 0);
    c1.set(Calendar.DATE, 1);
    c1.set(Calendar.MONTH, period);

    startDate = c1.getTime();

    c1.add(Calendar.MONTH, 1);
    c1.add(Calendar.DAY_OF_MONTH, -1);
    endDate = c1.getTime();
  }

  private void initializeQuarter(Integer period, Integer year)
  {
    Calendar c1 = Calendar.getInstance();
    c1.clear();
    c1.set(year, 1, 1, 0, 0, 0);
    c1.set(Calendar.DATE, 1);

    switch (period)
    {
      case 1:
        c1.set(Calendar.MONTH, 1);
        break;
      case 2:
        c1.set(Calendar.MONTH, 4);
        break;
      case 3:
        c1.set(Calendar.MONTH, 7);
        break;
      default:
        c1.set(Calendar.MONTH, 10);
        break;
    }

    startDate = c1.getTime();

    c1.add(Calendar.MONTH, 3);
    c1.add(Calendar.DAY_OF_MONTH, -1);
    endDate = c1.getTime();
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public PeriodType getType()
  {
    return type;
  }

  public Integer getPeriod()
  {
    return period;
  }

  public Integer getYear()
  {
    return year;
  }
}
