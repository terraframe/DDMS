package dss.vector.solutions.surveillance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.PeriodMonthProblem;
import dss.vector.solutions.PeriodQuarterProblem;
import dss.vector.solutions.PeriodWeekProblem;

public class EpiDate implements Reloadable
{
  private PeriodType type;

  private Integer period;

  private String year;

  private Date startDate;

  private Date endDate;

  public EpiDate(PeriodType periodType, int period, String year)
  {
    this.type = periodType;
    this.period = period;
    this.year = year;

    try
    {
      if (periodType.equals(PeriodType.QUARTER))
      {
        if(period > periodType.getMaximumPeriod())
        {
          PeriodQuarterProblem p = new PeriodQuarterProblem();
          p.setPeriod(period);
          p.setMaxPeriod(periodType.getMaximumPeriod());
          p.throwIt();
        }

        initializeQuarter(period, year);
      }
      else if(periodType.equals(PeriodType.MONTH))
      {
        if(period > periodType.getMaximumPeriod())
        {
          PeriodMonthProblem p = new PeriodMonthProblem();
          p.setPeriod(period);
          p.setMaxPeriod(periodType.getMaximumPeriod());
          p.throwIt();
        }

        initializeMonth(period, year);
      }
      else if(periodType.equals(PeriodType.WEEK))
      {
        if(period > periodType.getMaximumPeriod())
        {
          PeriodWeekProblem p = new PeriodWeekProblem();
          p.setPeriod(period);
          p.setMaxPeriod(periodType.getMaximumPeriod());
          p.throwIt();
        }

        initalizeWeek(period, year);
      }
    }
    catch (ParseException e)
    {
      //TODO change this runtime exception
      throw new RuntimeException(e);
    }
  }

  private void initalizeWeek(Integer period, String year) throws ParseException
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy");

    Calendar c1 = Calendar.getInstance();
    c1.setTime(format.parse(year));

    c1.set(Calendar.DATE, 1);
    c1.add(Calendar.WEEK_OF_YEAR, period);

    startDate = c1.getTime();

    c1.add(Calendar.DAY_OF_MONTH, 6);
    endDate = c1.getTime();
  }

  private void initializeMonth(Integer period, String year) throws ParseException
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy");

    Calendar c1 = Calendar.getInstance();
    c1.setTime(format.parse(year));
    c1.set(Calendar.DATE, 1);
    c1.set(Calendar.MONTH, period);

    startDate = c1.getTime();

    c1.add(Calendar.MONTH, 1);
    c1.add(Calendar.DAY_OF_MONTH, -1);
    endDate = c1.getTime();
  }

  private void initializeQuarter(Integer period, String year) throws ParseException
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy");

    Calendar c1 = Calendar.getInstance();
    c1.setTime(format.parse(year));
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

  public String getYear()
  {
    return year;
  }
}
